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
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
