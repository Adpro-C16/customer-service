//package id.ac.ui.cs.advprog.heymart.customerservice.controller;
//
//import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
//import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
//
//import id.ac.ui.cs.advprog.heymart.customerservice.service.HistoryServiceImpl;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//
//import java.util.HashMap;
//import java.util.List;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//
//
//
//
//
//@ExtendWith(MockitoExtension.class)
//public class HistoryControllerTest {
//
//    @Mock
//    private HistoryServiceImpl historyService;
//    @InjectMocks
//    private HistoryController historyController;
//
//
//    @Test
//    public void testGetHistoryById() {
//        Long id = 1L;
//        History history = new History();
//        when(historyService.existsById(id)).thenReturn(true);
//        when(historyService.getHistoryById(id)).thenReturn(history);
//
//        ResponseEntity<?> response = historyController.getHistoryById(id);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(history, response.getBody());
//    }
//
//
//    @Test
//    public void testGetHistoryByIdNotFound() {
//        Long id = 1L;
//        when(historyService.existsById(id)).thenReturn(false);
//
//        ResponseEntity<?> response = historyController.getHistoryById(id);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("History with id " + id + " not found.", response.getBody());
//    }
//
//    @Test
//    public void testAddNewHistory() {
//        Long custId = 1L;
//        Long supermarketId = 2L;
//        double totalPrice = 200.0;
//        Long idHistory = 3L;
//        Product product = new Product();
//        List<Product> productList = new ArrayList<>();
//        productList.add(product);
//
//        History history= new History(idHistory,supermarketId,custId, totalPrice, productList);
//
//
//        when(historyService.addNewHistory(idHistory, custId, supermarketId, totalPrice, productList)).thenReturn(history);
//
//        HashMap<String, Object> request = new HashMap<>();
//        request.put("idHistory", idHistory);
//        request.put("custId", custId);
//        request.put("supermarketId", supermarketId);
//        request.put("totalPrice", totalPrice);
//        request.put("productList", productList);
//
//
//        ResponseEntity<?> response = historyController.addNewHistory(request);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Success add new history with id: " + history.getIdHistory(), response.getBody());
//    }
//
//
//    @Test
//    public void testDeleteHistory() {
//        Long idHistory = 10L;
//
//        when(historyService.existsById(idHistory)).thenReturn(true);
//
//        ResponseEntity<?> response = historyController.deleteHistory(idHistory);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Success delete history with id " + idHistory , response.getBody());
//    }
//
//    @Test
//    public void testDeleteHistoryNotFound() {
//        Long idHistory = 1L;
//        when(historyService.existsById(idHistory)).thenReturn(false);
//
//        ResponseEntity<?> response = historyController.deleteHistory(idHistory);
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("History with id " + idHistory + " not found.", response.getBody());
//    }
//
//}
