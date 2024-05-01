package id.ac.ui.cs.advprog.heymart.customerservice.state;

import id.ac.ui.cs.advprog.heymart.customerservice.model.CartItem;
import id.ac.ui.cs.advprog.heymart.customerservice.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class EmptyCart implements CartState {

    public EmptyCart(ShoppingCart cart){
        cart.setSupermarketId(-1L);
    }

    @Override
    public void addItem(ShoppingCart cart, CartItem cartItem) {
        cart.getCartItems().add(cartItem);
        cart.setState(new SupermarketCart(cart, cartItem.getSupermarketId()));
    }

    @Override
    public void removeItem(ShoppingCart cart, CartItem cartItem) {
        throw new IllegalStateException("Cart is already empty");
    }

    @Override
    public List<CartItem> checkout(ShoppingCart cart) {
        throw new IllegalStateException("Cannot checkout an empty cart");
    }
}
