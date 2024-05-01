package id.ac.ui.cs.advprog.heymart.customerservice.repository;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Transaction;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
