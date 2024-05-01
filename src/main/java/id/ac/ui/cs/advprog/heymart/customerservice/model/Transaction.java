package id.ac.ui.cs.advprog.heymart.customerservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @JoinColumn(name = "marketName")
    private String marketName;

    @JoinColumn(name = "customer")
    private String customer;

    @JoinColumn(name = "komentar")
    private String comment;

    @JoinColumn(name = "productList")
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Transaction() {}

    public Transaction(String marketName, String customer, String comment) {
        this.marketName = marketName;
        this.customer = customer;
        this.comment = comment;
    }
}