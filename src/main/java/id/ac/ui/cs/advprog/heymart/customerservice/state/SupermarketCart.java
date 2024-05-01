package id.ac.ui.cs.advprog.heymart.customerservice.state;

import id.ac.ui.cs.advprog.heymart.customerservice.model.CartItem;
import id.ac.ui.cs.advprog.heymart.customerservice.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SupermarketCart implements CartState {
    private Long supermarketId;

    public SupermarketCart(ShoppingCart cart, Long supermarketId){
        this.supermarketId = supermarketId;
        cart.setSupermarketId(supermarketId);
    }

    @Override
    public void addItem(ShoppingCart cart, CartItem cartItem) {
        if (Objects.equals(supermarketId, cartItem.getSupermarketId())){
            cart.getCartItems().add(cartItem);
        } else {
            throw new IllegalStateException("Cannot add items from different supermarkets");
        }
    }

    @Override
    public void removeItem(ShoppingCart cart, CartItem cartItem) {
        List<CartItem> items = cart.getCartItems();
        if (items.contains(cartItem)){
            items.remove(cartItem);
            if (items.isEmpty()) cart.setState(new EmptyCart(cart));
        }
    }


    @Override
    public List<CartItem> checkout(ShoppingCart cart) {
        List<CartItem> items = cart.getCartItems();
        cart.setCartItems(new ArrayList<>());
        cart.setState(new EmptyCart(cart));

        return items;
    }
}
