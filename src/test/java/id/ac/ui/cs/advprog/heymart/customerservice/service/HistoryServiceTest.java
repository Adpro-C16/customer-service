package id.ac.ui.cs.advprog.heymart.customerservice.service;


import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Transaction;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {
    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistoryRepository historyRepository;

    public History history;
    public List<Product> productList;

    @BeforeEach
    void setUp(){
        productList = new ArrayList<>();


        Product product1 = new Product("1","Product 1", 10.0, 6L);
        productList.add(product1);

        Product product2 = new Product("2", "Product 2", 20.0, 6L);
        productList.add(product2);

        Product product3 = new Product("3", "Product 3", 30.0, 6L);
        productList.add(product3);


        history = new History();
        history.setIdHistory(100L);
        history.setProductList(productList);
        history.setCustId(5L);
        history.setTotalPrice(60.0);
        history.setSupermarketId(6L);

    }

    @Test
    void testGetHistoryById(){

        History history = new History();
        history.setIdHistory(100L);

        when(historyRepository.findById(100L)).thenReturn(Optional.of(history));

        History result = historyService.getHistoryById(100L);

        assertEquals(history, result);
    }


    @Test
    void testAddNewHistory() {
        when(historyRepository.save(any())).thenReturn(history);
        History result = historyService.addNewHistory(2L,1L, 1L, 250.0, productList);
        assertEquals(history, result);
    }

    @Test
    void testDeleteHistory() {
        // Calling the method under test
        historyService.deleteHistory(1L);

        // Verifying that the deleteById method of the repository was called once with the correct argument
        verify(historyRepository).deleteById(1L);
    }

    @Test
    void testExistsById() {

        when(historyRepository.existsById(1L)).thenReturn(true);
        boolean result = historyService.existsById(1L);
        assertTrue(result);
    }

    @Test
    public void testGetAllHistory() throws ExecutionException, InterruptedException {
        History history1 = new History();
        History history2 = new History();

        List<History> histories = Arrays.asList(history1, history2);

        when(historyRepository.findAll()).thenReturn(histories);

        CompletableFuture<List<History>> future = historyService.getAllHistory();
        List<History> result = future.get();

        assertEquals(histories, result);
    }

    @Test
    public void testGetHistoryByCustId() throws ExecutionException, InterruptedException {
        Long custId = 1L;
        History history1 = new History();
        History history2 = new History();

        List<History> histories = Arrays.asList(history1, history2);

        when(historyRepository.findByCustId(custId)).thenReturn(Optional.of(histories));

        CompletableFuture<List<History>> future = historyService.getHistoryByCustId(custId);
        List<History> result = future.get();

        assertEquals(histories, result);
    }

    @Test
    public void testGetHistoryBySupermarketId() throws ExecutionException, InterruptedException {
        Long supermarketId = 2L;
        History history1 = new History();
        History history2 = new History();

        List<History> histories = Arrays.asList(history1, history2);

        when(historyRepository.findBySupermarketId(supermarketId)).thenReturn(Optional.of(histories));

        CompletableFuture<List<History>> future = historyService.getHistoryBySupermarketId(supermarketId);
        List<History> result = future.get();

        assertEquals(histories, result);
    }


}
