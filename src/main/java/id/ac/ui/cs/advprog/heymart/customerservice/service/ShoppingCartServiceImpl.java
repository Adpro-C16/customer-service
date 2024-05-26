package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.dto.CartItemDto;
import id.ac.ui.cs.advprog.heymart.customerservice.dto.ShoppingCartDto;
import id.ac.ui.cs.advprog.heymart.customerservice.dto.request.CartItemRequest;
import id.ac.ui.cs.advprog.heymart.customerservice.model.CartItem;
import id.ac.ui.cs.advprog.heymart.customerservice.model.ShoppingCart;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Transaction;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.CartItemRepository;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.ShoppingCartRepository;
import id.ac.ui.cs.advprog.heymart.customerservice.state.EmptyCart;
import id.ac.ui.cs.advprog.heymart.customerservice.state.SupermarketCart;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    private void validateCartItemRequest(CartItemRequest request) throws BadRequestException {
        if (request.getProductId() == null || request.getProductId().isBlank()) {
            throw new BadRequestException("Invalid product id");
        }
        if (request.getAmount() <= 0) {
            throw new BadRequestException("Amount should be more than zero");
        }
    }

    @Override
    public ShoppingCartDto addItem(CartItemRequest request) throws BadRequestException {
        validateCartItemRequest(request);
        Long userId = request.getUserId();
        String productId = request.getProductId();
        CartItem item;
        Optional<CartItem> getItem = getUserCartItemByProductId(userId, productId);
        if (getItem.isEmpty()) {
            item = new CartItem();
            item.setUserId(userId);
            item.setProductId(productId);
            item.setSupermarketId(request.getSupermarketId());
            item.setAmount(request.getAmount());
        } else {
            item = getItem.get();
            item.setAmount(item.getAmount() + request.getAmount());
        }

        ShoppingCart cart = getCartByUserId(userId);
        cart.addItem(item);

        shoppingCartRepository.save(cart);

        return createShoppingCartDto(cart);
    }

    private ShoppingCartDto createShoppingCartDto(ShoppingCart shoppingCart){
        ShoppingCartDto cartDto = new ShoppingCartDto();
        cartDto.setSupermarketId(shoppingCart.getSupermarketId());
        cartDto.setCartItems(cartItemRepository.getCartItemDtoByUserId(shoppingCart.getUserId()));
        return cartDto;
    }

    @Override
    public ShoppingCartDto removeItem(CartItemRequest request) throws BadRequestException {
        validateCartItemRequest(request);
        Long userId = request.getUserId();
        String productId = request.getProductId();

        CartItem item;
        Optional<CartItem> getItem = getUserCartItemByProductId(userId, productId);
        if (getItem.isEmpty()) {
            throw new BadRequestException("No such item in the cart");
        } else {
            item = getItem.get();
            int itemAmount = item.getAmount();
            if (itemAmount < request.getAmount()){
                throw new BadRequestException("Amount requested to be removed is more than the amount in the cart");
            }
            if (itemAmount == request.getAmount()){
                cartItemRepository.delete(item);
            } else {
                item.setAmount(item.getAmount() - request.getAmount());
                cartItemRepository.save(item);
            }
        }

        ShoppingCart cart = getCartByUserId(userId);
        initializeCartState(cart);
        shoppingCartRepository.save(cart);
        return createShoppingCartDto(cart);
    }

    private Optional<CartItem> getUserCartItemByProductId(Long userId, String productId){
        return cartItemRepository.getCartItemByUserIdAndProductId(userId, productId);
    }

    @Override
    public ShoppingCartDto getCartDtoByUserId(Long userId) {
        return createShoppingCartDto(getCartByUserId(userId));
    }

    @Override
    public Transaction checkout(Long userId) {
        // TODO: Validate that checkout can be done (consider user's balance too)
        // TODO: Decrease user's balance if balance >= total
        // TODO: Create a new transaction
        List<CartItemDto> cartItems = cartItemRepository.getCartItemDtoByUserId(userId);
        // TODO: For every items in cartItems, create a new purchase item, adding them to transaction
        // TODO: Save them all
        ShoppingCart cart = getCartByUserId(userId);
        List<CartItem> items = cart.checkout();
        cartItemRepository.deleteAll(items);
        return null;
    }

    private ShoppingCart getCartByUserId(Long userId) {
        ShoppingCart cart;
        Optional<ShoppingCart> getCart = shoppingCartRepository.getByUserId(userId);
        cart = getCart.orElseGet(() -> createNewCartForUser(userId));
        initializeCartState(cart);
        return cart;
    }

    private ShoppingCart createNewCartForUser(Long userId){
        ShoppingCart cart = new ShoppingCart();
        cart.setUserId(userId);
        cart.setCartItems(new ArrayList<>());
        cart.setSupermarketId(-1L);
        shoppingCartRepository.save(cart);
        return cart;
    }

    private void initializeCartState(ShoppingCart cart){
        if (cart.getCartItems().isEmpty()){
            cart.setState(new EmptyCart(cart));
        } else {
            Long supermarketId = cart.getFirst().getSupermarketId();
            cart.setState(new SupermarketCart(cart, supermarketId));
        }
    }
}
