package id.ac.ui.cs.advprog.heymart.customerservice.repository;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class HistoryRepositoryTest {

    @Mock
    private HistoryRepository historyRepository;

    @Test
    void testFindByCustId() {
        Long custId = 1L;
        List<History> histories = new ArrayList<>();
        when(historyRepository.findByCustId(custId)).thenReturn(Optional.of(histories));

        Optional<List<History>> actualHistories = historyRepository.findByCustId(custId);

        assertEquals(histories, actualHistories.orElse(null));
        verify(historyRepository, times(1)).findByCustId(custId);
    }

    @Test
    void testFindBySupermarketId() {
        Long supermarketId = 1L;
        List<History> histories = new ArrayList<>();
        when(historyRepository.findBySupermarketId(supermarketId)).thenReturn(Optional.of(histories));

        Optional<List<History>> actualHistories = historyRepository.findBySupermarketId(supermarketId);

        assertEquals(histories, actualHistories.orElse(null));
        verify(historyRepository, times(1)).findBySupermarketId(supermarketId);
    }

    @Test
    void testFindById(){
        Long id = 1L;
        History histories = new History.Builder().build();
        when(historyRepository.findById(id)).thenReturn(Optional.of(histories));

        Optional<History> actualHistory = historyRepository.findById(id);

        assertEquals(histories, actualHistory.orElse(null));
        verify(historyRepository, times(1)).findById(id);
    }

    @Test
    void testSavesHistory() {
        History history = new History.Builder().build();
        when(historyRepository.save(history)).thenReturn(history);

        History savedHistory = historyRepository.save(history);

        assertEquals(history, savedHistory);
        verify(historyRepository, times(1)).save(history);
    }


    @Test
    void existsByIdHistory() {
        Long id = 1L;
        when(historyRepository.existsById(id)).thenReturn(true);

        boolean exists = historyRepository.existsById(id);

        assertTrue(exists);
        verify(historyRepository, times(1)).existsById(id);
    }

    @Test
    void findAllHistories() {
        List<History> histories = new ArrayList<>();
        when(historyRepository.findAll()).thenReturn(histories);

        List<History> actualHistories = historyRepository.findAll();

        assertEquals(histories, actualHistories);
        verify(historyRepository, times(1)).findAll();
    }

    @Test
    void testFindByCustIdNotExist() {
        Long custId = 1L;
        when(historyRepository.findByCustId(custId)).thenReturn(Optional.empty());

        Optional<List<History>> actualHistories = historyRepository.findByCustId(custId);

        assertFalse(actualHistories.isPresent());
        verify(historyRepository, times(1)).findByCustId(custId);
    }

    @Test
    void testFindBySupermarketIdNotExist() {
        Long supermarketId = 1L;
        when(historyRepository.findBySupermarketId(supermarketId)).thenReturn(Optional.empty());

        Optional<List<History>> actualHistories = historyRepository.findBySupermarketId(supermarketId);

        assertFalse(actualHistories.isPresent());
        verify(historyRepository, times(1)).findBySupermarketId(supermarketId);
    }

    @Test
    void testFindByIdNotExits() {
        Long id = 1L;
        when(historyRepository.findById(id)).thenReturn(Optional.empty());

        Optional<History> actualHistory = historyRepository.findById(id);

        assertFalse(actualHistory.isPresent());
        verify(historyRepository, times(1)).findById(id);
    }

    @Test
    void testFindByIdNotExist() {
        Long id = 1L;
        when(historyRepository.existsById(id)).thenReturn(false);

        boolean exists = historyRepository.existsById(id);

        assertFalse(exists);
        verify(historyRepository, times(1)).existsById(id);
    }

    @Test
    void testDeletehistoryById(){
        Long id = 1L;
        doNothing().when(historyRepository).deleteById(id);

        historyRepository.deleteById(id);

        verify(historyRepository, times(1)).deleteById(id);
    }

}