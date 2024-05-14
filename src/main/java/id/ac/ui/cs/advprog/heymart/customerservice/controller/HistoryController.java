package id.ac.ui.cs.advprog.heymart.customerservice.controller;

import id.ac.ui.cs.advprog.heymart.customerservice.model.History;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.service.HistoryService;
import id.ac.ui.cs.advprog.heymart.customerservice.service.HistoryServiceImpl;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }


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
        Long idHistory = Long.parseLong(request.get("idHistory").toString());
        Long custId = Long.parseLong(request.get("custId").toString());
        Long marketId = Long.parseLong(request.get("marketId").toString());
        double totalSpent = Double.parseDouble(request.get("totalSpent").toString());
        List<Product> purchases = (List<Product>) request.get("purchases");

        History newHistory = historyService.addNewHistory(idHistory, custId, marketId, totalSpent, purchases);
        return ResponseEntity.ok("Success add new history with id: " + newHistory.getIdHistory());
    }


//    @PostMapping("/add")
//    public ResponseEntity<?> addNewHistory(@RequestBody HashMap<String, Object> request) {
//        Long idHistory = Long.parseLong(request.get("idHistory").toString());
//        Long custId = Long.parseLong(request.get("custId").toString());
//        Long marketId = Long.parseLong(request.get("marketId").toString());
//        List<Product> purchases = (List<Product>) request.get("purchases");
//        double totalSpent = Double.parseDouble(request.get("totalSpent").toString());
//
//        History newHistory = historyService.addNewHistory(idHistory, custId, marketId, totalSpent, purchases);
//        return ResponseEntity.ok("New history added with id: " + newHistory.getIdHistory());
//    }

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
