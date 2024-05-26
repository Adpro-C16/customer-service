package id.ac.ui.cs.advprog.heymart.customerservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HistoryTest {

    private History history;


    @Test
    public void testConstructor() {
        Long custId = 1L;
        Long supermarketId = 2L;
        List<Product> productList = new ArrayList<>();
        double totalPrice = 100.0;

        History history = new History.Builder()
                .custId(custId)
                .supermarketId(supermarketId)
                .productList(productList)
                .totalPrice(totalPrice)
                .build();

        assertEquals(custId, history.getCustId());
        assertEquals(supermarketId, history.getSupermarketId());
        assertEquals(productList, history.getProductList());
        assertEquals(totalPrice, history.getTotalPrice(), 0.001);
    }

    @Test
    public void testAddPurchase() {
        History history = new History.Builder().build();
        Product product1 = new Product();
        product1.setProductPrice(10.0);
        Product product2 = new Product();
        product2.setProductPrice(20.0);

        history.addProductList(product1);
        history.addProductList(product2);

        assertEquals(2, history.getProductList().size());
        assertEquals(product1, history.getProductList().get(0));
        assertEquals(product2, history.getProductList().get(1));
        assertEquals(30.0, history.getTotalPrice(), 0.001);
    }

    @Test
    public void testNullPurchase() {
        History history = new History.Builder().build();
        assertThrows(NullPointerException.class, () -> history.addProductList(null));
    }



//    @BeforeEach
//    void setUp(){
//        history = new History();
//    }
//
//    @Test
//    void testGetSetIdHistory(){
//        Long idHistory = 1344L;
//        history.setIdHistory(idHistory);
//        assertEquals(idHistory, history.getIdHistory());
//    }
//
//    @Test
//    void testGetSetSupermarketId(){
//        Long supermarketId = 2L;
//        history.setSupermarketId(supermarketId);
//        assertEquals(supermarketId, history.getSupermarketId());
//    }
//
//    @Test
//    void testGetSetCustId(){
//        Long custId = 3L;
//        history.setCustId(custId);
//        assertEquals(custId, history.getCustId());
//    }
//
//    @Test
//    void testProductList(){
//        List<Product> purchases = new ArrayList<>();
//        Product product1 = new Product();
//        Product product2 = new Product();
//        purchases.add(product1);
//        purchases.add(product2);
//        history.setProductList(purchases);
//        assertEquals(purchases, history.getProductList());
//    }
//
//    @Test
//    void testTotalPrice(){
//        double totalSpent = 50.0;
//        history.setTotalPrice(totalSpent);
//        assertEquals(totalSpent, history.getTotalPrice());
//    }

}