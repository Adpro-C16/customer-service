package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface HistoryService {
    History getHistoryById(Long id);
    History addNewHistory(Long idHistory, Long ownerId, Long marketId, double totalPrice, List<Product> productList);
    void deleteHistory(Long id);
    boolean existsById(Long id);

    CompletableFuture<List<History>> getAllHistory();
    CompletableFuture<List<History>> getHistoryByCustId(Long custId);
    CompletableFuture<List<History>> getHistoryBySupermarketId(Long supermarketId);

}


