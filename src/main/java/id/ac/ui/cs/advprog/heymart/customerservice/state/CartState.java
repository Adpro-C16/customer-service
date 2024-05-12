package id.ac.ui.cs.advprog.heymart.customerservice.state;

import id.ac.ui.cs.advprog.heymart.customerservice.model.CartItem;
import id.ac.ui.cs.advprog.heymart.customerservice.model.ShoppingCart;

import java.util.List;

public interface CartState {
    void addItem(ShoppingCart cart, CartItem cartItem);

    void removeItem(ShoppingCart cart, CartItem cartItem);

    List<CartItem> checkout(ShoppingCart cart);
}
