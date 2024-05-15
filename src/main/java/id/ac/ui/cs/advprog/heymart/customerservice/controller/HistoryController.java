package id.ac.ui.cs.advprog.heymart.customerservice.controller;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

import java.util.concurrent.CompletableFuture;







@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


    @GetMapping("/get/{idHistory}")
    public ResponseEntity<?> getHistoryById(@PathVariable Long idHistory) {
        if (historyService.existsById(idHistory)) {
            History history = historyService.getHistoryById(idHistory);
            return ResponseEntity.ok(history);
        } else {
            return ResponseEntity.badRequest().body("History with id " + idHistory + " not found.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewHistory(@RequestBody HashMap<String, Object> request) {
        Long idHistory = Long.parseLong(request.get("idHistory").toString());
        Long custId = Long.parseLong(request.get("custId").toString());
        Long supermarketId = Long.parseLong(request.get("supermarketId").toString());
        double totalPrice = Double.parseDouble(request.get("totalPrice").toString());
        List<Product> productList = (List<Product>) request.get("productList");

        History newHistory = historyService.addNewHistory(idHistory, custId, supermarketId, totalPrice, productList);
        return ResponseEntity.ok("Success add new history with id: " + newHistory.getIdHistory());
    }


    @DeleteMapping("/delete/{idHistory}")
    public ResponseEntity<?> deleteHistory(@PathVariable Long idHistory) {
        if (historyService.existsById(idHistory)) {
            historyService.deleteHistory(idHistory);
            return ResponseEntity.ok("Success delete history with id " + idHistory);
        } else {
            return ResponseEntity.badRequest().body("History with id " + idHistory + " not found.");
        }
    }

    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<List<History>>> getAllHistory() {
        return historyService.getAllHistory()
                .thenApply(ResponseEntity::ok)
                .exceptionally(this::handleException);
    }

    @GetMapping("/cust/{custId}")
    public CompletableFuture<ResponseEntity<List<History>>> getHistoryByCustId(@PathVariable Long custId) {
        return historyService.getHistoryByCustId(custId)
                .thenApply(ResponseEntity::ok)
                .exceptionally(this::handleException);
    }

    @GetMapping("/supermarket/{supermarketId}")
    public CompletableFuture<ResponseEntity<List<History>>> getHistoryBySupermarketId(@PathVariable Long supermarketId) {
        return historyService.getHistoryByCustId(supermarketId)
                .thenApply(ResponseEntity::ok)
                .exceptionally(this::handleException);
    }

    private ResponseEntity<List<History>> handleException(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }


}
