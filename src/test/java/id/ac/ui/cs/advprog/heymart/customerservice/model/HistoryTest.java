package id.ac.ui.cs.advprog.heymart.customerservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class HistoryTest {

    @BeforeEach
    void setUp(){
        history = new History();
    }

    @Test
    void testGetSetIdHistory(){
        Long idHistory = 1L;
        history.setIdHistory(idHistory);
        assertEquals(idHistory, history.getIdHistory());
    }

    @Test
    void testGetSetSupermarketId(){
        Long supermarketId = 2L;
        history.setSupermarketId(supermarketId);
        assertEquals(supermarketId, history.getSupermarketId());
    }

    @Test
    void testGetSetCustId(){
        Long custId = 3L;
        history.setCustId(custId);
        assertEquals(custId, history.getCustId());
    }


    @Test
    void testProductList(){
        List<Product> purchases = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        purchases.add(product1);
        purchases.add(product2);
        history.setPurchases(purchases);
        assertEquals(purchases, history.getPurchases());
    }

    @Test
    void testTotalPrice(){
        double totalSpent = 50.0;
        history.setTotal(totalSpent);
        assertEquals(totalSpent, history.getTotal());
    }

}
