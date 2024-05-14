package id.ac.ui.cs.advprog.heymart.customerservice.model;

import id.ac.ui.cs.advprog.heymart.customerservice.state.CartState;
import id.ac.ui.cs.advprog.heymart.customerservice.state.EmptyCart;
import id.ac.ui.cs.advprog.heymart.customerservice.state.SupermarketCart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingCartTest {
    private ShoppingCart cart;

    private CartItem item;

    @BeforeEach
    void setUp(){
        cart = new ShoppingCart();
        cart.setUserId(1L);
        cart.setSupermarketId(2L);
        cart.setState(new EmptyCart(cart));
        cart.setCartItems(new ArrayList<>());


        item = new CartItem();
        item.setSupermarketId(3L);
    }

    @Test
    void testEmptyShoppingCart(){
        ShoppingCart newCart = new ShoppingCart(cart.getUserId(), cart.getSupermarketId(), cart.getCartItems(), null);
        newCart.setState(new EmptyCart(newCart));

        assertInstanceOf(EmptyCart.class, newCart.getState());
        assertEquals(cart.getUserId(), newCart.getUserId());
        assertEquals(cart.getSupermarketId(), newCart.getSupermarketId());
        assertEquals(-1L, newCart.getSupermarketId());
        assertEquals(cart.getCartItems(), newCart.getCartItems());
    }

    @Test
    void testAddItemEmptyCart(){
        assertEquals(-1L, cart.getSupermarketId());
        cart.addItem(item);
        assertEquals(cart.getFirst(), item);
        assertEquals(3L, cart.getSupermarketId());
        assertInstanceOf(SupermarketCart.class, cart.getState());

        cart.removeItem(item);
        assertTrue(cart.getCartItems().isEmpty());
        assertEquals(-1L, cart.getSupermarketId());
    }

    @Test
    void testAddItemNonEmptyCart(){
        CartItem newItem = new CartItem();
        newItem.setSupermarketId(item.getSupermarketId());

        cart.addItem(item);
        cart.addItem(newItem);

        assertEquals(cart.getCartItems().getFirst(), item);
        assertEquals(cart.getCartItems().get(1), newItem);
    }

    @Test
    void testRemoveEmptyCart(){
        assertThrows(IllegalStateException.class, () ->
                    cart.removeItem(new CartItem())
                );
    }

    @Test
    void checkoutEmptyCart(){
        assertThrows(IllegalStateException.class, () ->
                cart.checkout()
        );
    }

    @Test
    void checkoutNonEmptyCart(){
        cart.addItem(item);

        assertInstanceOf(SupermarketCart.class, cart.getState());

        cart.checkout();

        assertInstanceOf(EmptyCart.class, cart.getState());
        assertEquals(0, cart.getCartItems().size());
    }

    @Test
    void testAddItemDifferentSupermarket(){
        cart.addItem(item);
        CartItem newItem = new CartItem();
        newItem.setSupermarketId(5L);

        assertThrows(IllegalStateException.class, () ->
                cart.addItem(newItem)
        );
    }
}
