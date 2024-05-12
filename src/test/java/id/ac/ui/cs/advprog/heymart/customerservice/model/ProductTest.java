package id.ac.ui.cs.advprog.heymart.customerservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private Product product;

    @BeforeEach
    void setUp(){
        product = new Product();
        product.setProductId("id");
        product.setProductName("name");
        product.setSupermarketId(1L);
        product.setProductPrice(2d);
    }

    @Test
    void testProduct(){
        Product newProduct = new Product(product.getProductId(), product.getProductName(),
                                    product.getProductPrice(), product.getSupermarketId());
        assertEquals(product.getProductId(), newProduct.getProductId());
        assertEquals(product.getProductName(), newProduct.getProductName());
        assertEquals(product.getSupermarketId(), newProduct.getSupermarketId());
        assertEquals(product.getProductPrice(), newProduct.getProductPrice());
    }
}
