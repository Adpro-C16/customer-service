package id.ac.ui.cs.advprog.heymart.customerservice.service;


import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public History getHistoryById(Long id) {
        return historyRepository.findById(id).get();
    }

    @Override
    public History addNewHistory(Long idhistory, Long custId, Long superMarketId, double totalPrice, List<Product> productList) {
        History history = new History();
        history.setCustId(custId);
        history.setProductList(productList);
        history.setTotalPrice(totalPrice);
        history.setIdHistory(idhistory);
        history.setSupermarketId(superMarketId);

        return historyRepository.save(history);
    }

    @Override
    public void deleteHistory(Long id) {
        historyRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return historyRepository.existsById(id);
    }

    @Override
    public CompletableFuture<List<History>> getAllHistory() {
        List<History> allHistory = historyRepository.findAll();
        allHistory.forEach(history -> history.getProductList().size());
        return CompletableFuture.completedFuture(allHistory);
    }

    @Override
    public CompletableFuture<List<History>> getHistoryByCustId(Long custId) {
        List<History> historyByCustId = historyRepository.findByCustId(custId).orElse(List.of());
        historyByCustId.forEach(history -> history.getProductList().size());
        return CompletableFuture.completedFuture(historyByCustId);
    }

    @Override
    public CompletableFuture<List<History>> getHistoryBySupermarketId(Long supermarketId) {
        List<History> historyByMarketId = historyRepository.findBySupermarketId(supermarketId).orElse(List.of());
        historyByMarketId.forEach(history -> history.getProductList().size());
        return CompletableFuture.completedFuture(historyByMarketId);
    }


}
