package id.ac.ui.cs.advprog.heymart.customerservice;

import id.ac.ui.cs.advprog.heymart.customerservice.model.*;
import id.ac.ui.cs.advprog.heymart.customerservice.model.Transaction;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.ProductRepository;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ProductRepository productRepository;
    @Bean
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {

            // Dummy data
            System.out.println("Running...");
            Transaction t1 = new Transaction ("Market A", "Hilmy", null);
            Transaction t2 = new Transaction ("Market B", "Hilmy Hebat", null);
            Product p1 = new Product("Product A", 20.0, t1);
            Product p2 = new Product("Product B", 30.0, t1);
            Product p3 = new Product("Product B", 30.0, t2);

            productRepository.saveAll(List.of(p1, p2, p3));
            transactionRepository.saveAll(List.of(t1, t2));

        };
    }
}
