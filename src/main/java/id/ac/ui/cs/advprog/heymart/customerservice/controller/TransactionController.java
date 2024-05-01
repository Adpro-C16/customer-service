package id.ac.ui.cs.advprog.heymart.customerservice.controller;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Transaction;
import id.ac.ui.cs.advprog.heymart.customerservice.service.ProductService;
import id.ac.ui.cs.advprog.heymart.customerservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Object> findAllTransaction() {
        List<Transaction> transactionList = transactionService.findAll();
        return new ResponseEntity<>(transactionList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findTransactionById(@PathVariable Long id) {
        Optional<Transaction> transaction = transactionService.findById(id);
        return transaction.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Transaction not found", HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<Object> addCommentToTransaction(@PathVariable Long id, @RequestBody String comment) {
        try {
            Transaction updatedTransaction = transactionService.addComment(id, comment);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
