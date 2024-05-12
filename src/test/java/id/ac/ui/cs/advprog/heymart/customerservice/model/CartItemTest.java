package id.ac.ui.cs.advprog.heymart.customerservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class CartItemTest {

    private CartItem item;
    private String dummy = "dummy";

    @BeforeEach
    void setUp(){
        item = new CartItem();
        item.setId(1L);
        item.setUserId(2L);
        item.setProductId(dummy);
        item.setSupermarketId(3L);
        item.setAmount(4);
    }

    @Test
    void testCartItem(){
        CartItem item1 = new CartItem(item.getId(), item.getUserId(), item.getProductId(), item.getAmount(), item.getSupermarketId());

        assertEquals(item.getId(), item1.getId());
        assertEquals(item.getUserId(), item1.getUserId());
        assertEquals(item.getProductId(), item1.getProductId());
        assertEquals(item.getSupermarketId(), item1.getSupermarketId());
        assertEquals(item.getAmount(), item1.getAmount());
    }
}
