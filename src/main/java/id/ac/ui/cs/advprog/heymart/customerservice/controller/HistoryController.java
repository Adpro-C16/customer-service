package id.ac.ui.cs.advprog.heymart.customerservice.controller;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.service.HistoryServiceImpl;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryServiceImpl historyService;


    @GetMapping("/get/{id}")
    public ResponseEntity<?> getHistoryById(@PathVariable Long id) {
        if (historyService.existsById(id)) {
            History history = historyService.getHistoryById(id);
            return ResponseEntity.ok(history);
        } else {
            return ResponseEntity.badRequest().body("History with id " + id + " not found.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewHistory(@RequestBody HashMap<String, Object> request) {

        Long idHistory = Long.parseLong(request.get("custId").toString());
        Long custId = Long.parseLong(request.get("custId").toString());
        Long supermarketId = Long.parseLong(request.get("supermarketId").toString());
        List<Product> productList = (List<Product>) request.get("productList");
        double totalPrice = Double.parseDouble(request.get("totalPrice").toString());

        History newHistory = historyService.addNewHistory(idHistory, custId, supermarketId, productList, totalPrice);
        return ResponseEntity.ok("Success add new history with id: " + newHistory.getIdHistory());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteHistory(@PathVariable Long idHistory) {
        if (historyService.existsById(idHistory)) {
            historyService.deleteHistory(idHistory);
            return ResponseEntity.ok("Success delete history with id " + idHistory);
        } else {
            return ResponseEntity.badRequest().body("History with id " + idHistory + " not found.");
        }
    }


}
