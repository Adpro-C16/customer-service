package id.ac.ui.cs.advprog.heymart.customerservice.repository;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
