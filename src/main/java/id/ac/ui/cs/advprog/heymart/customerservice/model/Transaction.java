package id.ac.ui.cs.advprog.heymart.customerservice.model;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "markets")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @Column(name = "marketName")
    private String marketName;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Transaction() {}
    public Transaction(String marketName, String customerName, String comment) {
        this.marketName = marketName;
        this.customerName = customerName;
        this.comment = comment;
        this.productList = new ArrayList<>();
    }
}
