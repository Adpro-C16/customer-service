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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class HistoryRepositoryTest {

    @Mock
    private HistoryRepository historyRepository;
    History history1;
    History history2;
    History history3;

    public List<History> historyList;

    @BeforeEach
    void setUp(){

        historyList = new ArrayList<>();

        history1 = new History();
        history1.setIdHistory(1L);
        history1.setSupermarketId(1L);
        history1.setCustId(1L);
        history1.setTotalPrice(100.0);
        historyList.add(history1);

        history2 = new History();
        history2.setIdHistory(2L);
        history2.setSupermarketId(2L);
        history2.setCustId(2L);
        history2.setTotalPrice(150.0);
        historyList.add(history2);

        history3 = new History();
        history3.setIdHistory(3L);
        history3.setSupermarketId(3L);
        history3.setCustId(3L);
        history3.setTotalPrice(200.0);
        historyList.add(history3);

    }

    @Test
    void testFindByCustId(){
        Long custId = 1L;
        List<History> expectedHistoryList = new ArrayList<>();
        expectedHistoryList.add(historyList.get(0));
        expectedHistoryList.add(historyList.get(1));

        when(historyRepository.findByCustId(custId)).thenReturn(Optional.of(expectedHistoryList));
        Optional<List<History>> foundHistories = historyRepository.findByCustId(custId);

        assertEquals(expectedHistoryList, foundHistories.get());
    }


    @Test
    void testFindbySupermarketId(){
        Long supermarketId = 2L;
        List<History> expectedHistoryList = new ArrayList<>();
        expectedHistoryList.add(historyList.get(0));
        expectedHistoryList.add(historyList.get(2));

        when(historyRepository.findByCustId(supermarketId)).thenReturn(Optional.of(expectedHistoryList));
        Optional<List<History>> foundHistories = historyRepository.findByCustId(supermarketId);

        assertEquals(expectedHistoryList, foundHistories.get());

    }



}
