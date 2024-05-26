package id.ac.ui.cs.advprog.heymart.customerservice.service;


import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.HistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;




@ExtendWith(MockitoExtension.class)
public class HistoryServiceTest {
    @InjectMocks
    private HistoryServiceImpl historyService;

    @Mock
    private HistoryRepository historyRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetHistoryById() {
        Long id = 1L;
        History expectedHistory = new History.Builder().build();
        when(historyRepository.findById(id)).thenReturn(Optional.of(expectedHistory));

        History actualHistory = historyService.getHistoryById(id);

        assertEquals(expectedHistory, actualHistory);
    }

    @Test
    void getHistoryByIdNotExist() {
        Long id = 1L;
        when(historyRepository.findById(id)).thenReturn(Optional.empty());

        History actualHistory = historyService.getHistoryById(id);

        assertNull(actualHistory);
    }

    @Test
    void testAddNewHistory() {
        Long custId = 1L;
        Long supermarketId = 2L;
        List<Product> productList = new ArrayList<>();
        double totalPrice = 100.0;
        History expectedHistory = new History.Builder()
                .custId(custId)
                .supermarketId(supermarketId)
                .productList(productList)
                .totalPrice(totalPrice)
                .build();
        when(historyRepository.save(any(History.class))).thenReturn(expectedHistory);

        History actualHistory = historyService.addNewHistory(custId, supermarketId, totalPrice, productList);

        assertEquals(expectedHistory, actualHistory);
        verify(historyRepository, times(1)).save(any(History.class));
    }

    @Test
    void testDeleteHistory() {

        historyService.deleteHistory(1L);
        verify(historyRepository).deleteById(1L);
    }


    @Test
    void testExistsById() {
        Long id = 1L;
        when(historyRepository.existsById(id)).thenReturn(true);

        boolean exists = historyService.existsById(id);

        assertTrue(exists);
    }

    @Test
    void testExistsByIdNotExist() {
        Long id = 1L;
        when(historyRepository.existsById(id)).thenReturn(false);

        boolean exists = historyService.existsById(id);

        assertFalse(exists);
    }

    @Test
    void testGetAllHistory() {
        List<History> expectedHistories = new ArrayList<>();
        when(historyRepository.findAll()).thenReturn(expectedHistories);

        CompletableFuture<List<History>> futureHistories = historyService.getAllHistory();
        List<History> actualHistories = futureHistories.join();

        assertEquals(expectedHistories, actualHistories);
    }

    @Test
    void testGetHistoryByCustId() {
        Long custId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        when(historyRepository.findByCustId(custId)).thenReturn(Optional.of(expectedHistories));

        CompletableFuture<List<History>> futureHistories = historyService.getHistoryByCustId(custId);
        List<History> actualHistories = futureHistories.join();

        assertEquals(expectedHistories, actualHistories);
    }

    @Test
    void getHistoryBySupermarketId() {
        Long supermarketId = 1L;
        List<History> expectedHistories = new ArrayList<>();
        when(historyRepository.findBySupermarketId(supermarketId)).thenReturn(Optional.of(expectedHistories));

        CompletableFuture<List<History>> futureHistories = historyService.getHistoryBySupermarketId(supermarketId);
        List<History> actualHistories = futureHistories.join();

        assertEquals(expectedHistories, actualHistories);
    }

}
