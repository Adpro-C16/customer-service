package id.ac.ui.cs.advprog.heymart.customerservice.controller;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.service.HistoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class HistoryControllerTest {

    @Mock
    private HistoryServiceImpl historyService;


    @Test
    public void testGetHistoryById() {
        Long id = 1L;
        History history = new History();
        when(historyService.existsById(id)).thenReturn(true);
        when(historyService.getHistoryById(id)).thenReturn(history);

        ResponseEntity<?> response = historyController.getHistoryById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(history, response.getBody());
    }

    @Test
    public void testGetHistoryByIdNotFound() {
        Long id = 1L;
        when(historyService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = historyController.getHistoryById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("History with id " + id + " not found.", response.getBody());
    }

    @Test
    public void testAddNewHistory() {
        Long custId = 1L;
        Long supermarketId = 2L;
        double totalPrice = 200.0;
        Long idHistory = 3L;
        List<Product> productList = new ArrayList<>();

        History history= new History();
        history.setIdHistory(idHistory);
        history.setSupermarketId(supermarketId);
        history.setCustId(custId);
        history.setProductList(productList);
        history.setTotalPrice(totalPrice);


        when(historyService.addNewHistory(idHistory, custId, supermarketId, productList, totalPrice)).thenReturn(history);

        HashMap<String, Object> request = new HashMap<>();
        request.put("custId", custId);
        request.put("supemarketId", supermarketId);
        request.put("productList", productList);
        request.put("totalPrice", totalPrice);
        request.put("idHistory", idHistory);

        ResponseEntity<?> response = historyController.addNewHistory(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success add new history with id: " + history.getIdHistory(), response.getBody());
    }

    @Test
    public void testDeleteHistory() {
        Long idHistory = 10L;

        when(historyService.existsById(idHistory)).thenReturn(true);

        ResponseEntity<?> response = historyController.deleteHistory(idHistory);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success delete history with id " + idHistory , response.getBody());
    }

    @Test
    public void testDeleteHistoryNotFound() {
        Long idHistory = 1L;
        when(historyService.existsById(idHistory)).thenReturn(false);

        ResponseEntity<?> response = historyController.deleteHistory(idHistory);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("History with id " + idHistory + " not found.", response.getBody());
    }


}
