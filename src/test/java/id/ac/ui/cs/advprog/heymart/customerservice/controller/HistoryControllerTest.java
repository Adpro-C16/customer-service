package id.ac.ui.cs.advprog.heymart.customerservice.controller;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;

import id.ac.ui.cs.advprog.heymart.customerservice.service.HistoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;






@ExtendWith(MockitoExtension.class)
public class HistoryControllerTest {

    @Mock
    private HistoryServiceImpl historyService;
    @InjectMocks
    private HistoryController historyController;


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
        Long idHistory = 3L;
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);


        History history = new History.Builder().build();
        when(historyService.existsById(idHistory)).thenReturn(true);
        when(historyService.getHistoryById(idHistory)).thenReturn(history);

        ResponseEntity<?> response = historyController.getHistoryById(idHistory);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(history, response.getBody());
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
