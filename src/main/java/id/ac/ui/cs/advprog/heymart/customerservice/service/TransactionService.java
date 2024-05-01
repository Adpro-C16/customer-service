package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Transaction;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    // Get all transaction
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    // Get transaction by id
    public Optional<Transaction> findById(@PathVariable long id) {
        return transactionRepository.findById(id);
    }
}
