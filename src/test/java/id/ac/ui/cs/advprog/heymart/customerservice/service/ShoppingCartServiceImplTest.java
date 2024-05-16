package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.dto.ShoppingCartDto;
import id.ac.ui.cs.advprog.heymart.customerservice.dto.request.CartItemRequest;
import id.ac.ui.cs.advprog.heymart.customerservice.model.CartItem;
import id.ac.ui.cs.advprog.heymart.customerservice.model.ShoppingCart;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.CartItemRepository;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.ShoppingCartRepository;
import id.ac.ui.cs.advprog.heymart.customerservice.state.EmptyCart;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceImplTest {

    @Mock
    CartItemRepository cartItemRepository;

    @Mock
    ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    ShoppingCartServiceImpl service;

    ShoppingCart emptyCart;

    ShoppingCart notEmptyCart;

    CartItem item;

    String dummy = "dummy";

    @BeforeEach
    void setUp(){
        emptyCart = new ShoppingCart();
        emptyCart.setUserId(1L);
        emptyCart.setSupermarketId(2L);
        emptyCart.setState(new EmptyCart(emptyCart));
        emptyCart.setCartItems(new ArrayList<>());

        notEmptyCart = new ShoppingCart();
        notEmptyCart.setUserId(1L);
        notEmptyCart.setSupermarketId(2L);
        notEmptyCart.setState(new EmptyCart(notEmptyCart));
        notEmptyCart.setCartItems(new ArrayList<>());

        item = new CartItem();
        item.setProductId(dummy);
        item.setSupermarketId(3L);
        item.setAmount(2);

        notEmptyCart.addItem(item);
    }

    @Test
    void testInvalidCartItemRequest(){
        CartItemRequest request = new CartItemRequest();
        assertThrows(BadRequestException.class, () ->
                    service.addItem(request)
                );

        assertThrows(BadRequestException.class, () ->
                service.removeItem(request)
        );

        request.setProductId(" ");
        assertThrows(BadRequestException.class, () ->
                service.addItem(request)
        );

        assertThrows(BadRequestException.class, () ->
                service.removeItem(request)
        );

        request.setAmount(0);
        assertThrows(BadRequestException.class, () ->
                service.addItem(request)
        );

        assertThrows(BadRequestException.class, () ->
                service.removeItem(request)
        );

        request.setProductId(dummy);
        request.setAmount(-1);
        assertThrows(BadRequestException.class, () ->
                service.addItem(request)
        );

        assertThrows(BadRequestException.class, () ->
                service.removeItem(request)
        );
    }

    @Test
    void testAddExistingCartItem() throws BadRequestException {
        CartItemRequest request = new CartItemRequest();
        request.setProductId(dummy);
        request.setAmount(3);
        request.setSupermarketId(item.getSupermarketId());

        Optional<CartItem> getItem = Optional.of(item);
        when(cartItemRepository.getCartItemByUserIdAndProductId(any(), any())).thenReturn(getItem);
        when(shoppingCartRepository.getByUserId(any())).thenReturn(Optional.of(emptyCart));
        when(shoppingCartRepository.save(any())).thenReturn(emptyCart);
        when(cartItemRepository.getCartItemDtoByUserId(any())).thenReturn(new ArrayList<>());

        ShoppingCartDto dto = service.addItem(request);

        assertEquals(request.getSupermarketId(), dto.getSupermarketId());
    }

    @Test
    void testAddNotExistingCartItem() throws BadRequestException {
        CartItemRequest request = new CartItemRequest();
        request.setProductId(dummy);
        request.setAmount(3);
        request.setSupermarketId(item.getSupermarketId());

        Optional<CartItem> getItem = Optional.empty();
        when(cartItemRepository.getCartItemByUserIdAndProductId(any(), any())).thenReturn(getItem);
        when(shoppingCartRepository.getByUserId(any())).thenReturn(Optional.of(emptyCart));
        when(shoppingCartRepository.save(any())).thenReturn(emptyCart);
        when(cartItemRepository.getCartItemDtoByUserId(any())).thenReturn(new ArrayList<>());

        ShoppingCartDto dto = service.addItem(request);

        assertEquals(request.getSupermarketId(), dto.getSupermarketId());
    }

    @Test
    void testRemoveNonExistingCartItem() {
        CartItemRequest request = new CartItemRequest();
        request.setProductId(dummy);
        request.setAmount(3);
        request.setSupermarketId(item.getSupermarketId());

        Optional<CartItem> getItem = Optional.empty();
        when(cartItemRepository.getCartItemByUserIdAndProductId(any(), any())).thenReturn(getItem);

        assertThrows(BadRequestException.class, () ->
                    service.removeItem(request)
                );
    }

    @Test
    void testRemoveExistingCartItem_invalidAmount() {
        CartItemRequest request = new CartItemRequest();
        request.setProductId(dummy);
        request.setAmount(3);
        request.setSupermarketId(item.getSupermarketId());

        Optional<CartItem> getItem = Optional.of(item);
        when(cartItemRepository.getCartItemByUserIdAndProductId(any(), any())).thenReturn(getItem);

        assertThrows(BadRequestException.class, () ->
                service.removeItem(request)
        );
    }

    @Test
    void testRemoveExistingCartItem_lessAmount() throws BadRequestException {
        CartItemRequest request = new CartItemRequest();
        request.setProductId(dummy);
        request.setAmount(1);
        request.setSupermarketId(item.getSupermarketId());

        Optional<CartItem> getItem = Optional.of(item);
        when(cartItemRepository.getCartItemByUserIdAndProductId(any(), any())).thenReturn(getItem);
        when(cartItemRepository.save(any())).thenReturn(item);
        when(shoppingCartRepository.getByUserId(any())).thenReturn(Optional.of(notEmptyCart));
        when(shoppingCartRepository.save(any())).thenReturn(notEmptyCart);
        when(cartItemRepository.getCartItemDtoByUserId(any())).thenReturn(new ArrayList<>());

        service.removeItem(request);

        assertEquals(1, item.getAmount());
        verify(cartItemRepository, times(1)).save(any());
    }

    @Test
    void testRemoveExistingCartItem_equalAmount() throws BadRequestException {
        CartItemRequest request = new CartItemRequest();
        request.setProductId(dummy);
        request.setAmount(2);
        request.setSupermarketId(item.getSupermarketId());

        Optional<CartItem> getItem = Optional.of(item);
        when(cartItemRepository.getCartItemByUserIdAndProductId(any(), any())).thenReturn(getItem);
        doNothing().when(cartItemRepository).delete(item);
        when(shoppingCartRepository.getByUserId(any())).thenReturn(Optional.of(notEmptyCart));
        when(shoppingCartRepository.save(any())).thenReturn(notEmptyCart);
        when(cartItemRepository.getCartItemDtoByUserId(any())).thenReturn(new ArrayList<>());

        service.removeItem(request);

        assertEquals(2, item.getAmount());
        verify(cartItemRepository, times(1)).delete(item);
    }

    @Test
    void testGetNonExistantCart(){
        when(shoppingCartRepository.getByUserId(any())).thenReturn(Optional.empty());
        when(shoppingCartRepository.save(any())).thenReturn(emptyCart);
        when(cartItemRepository.getCartItemDtoByUserId(any())).thenReturn(new ArrayList<>());

        service.getCartDtoByUserId(4L);

        verify(shoppingCartRepository, times(1)).save(any());
    }

    @Test
    void testGetExistingCart(){
        when(shoppingCartRepository.getByUserId(any())).thenReturn(Optional.of(notEmptyCart));
        when(cartItemRepository.getCartItemDtoByUserId(any())).thenReturn(new ArrayList<>());

        ShoppingCartDto dto = service.getCartDtoByUserId(emptyCart.getUserId());

        assertEquals(notEmptyCart.getSupermarketId(), dto.getSupermarketId());
    }
}
