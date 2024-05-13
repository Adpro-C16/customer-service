package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;

import java.util.List;

public interface HistoryService {
    History getHistoryById(Long id);
    History addNewHistory(Long idHistory, Long ownerId, Long marketId, List<Product> purchases, double totalSpent);
    void deleteHistory(Long id);
    boolean existsById(Long id);

}


