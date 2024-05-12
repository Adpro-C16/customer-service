package id.ac.ui.cs.advprog.heymart.customerservice.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long transactionId;

    @Column(name = "marketId")
    private long marketId;

    @Column(name = "marketName")
    private String marketName;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "comment")
    private String comment;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Transaction() {}
    public Transaction(long marketId, String marketName, String customerName, String comment) {
        this.marketId = marketId;
        this.marketName = marketName;
        this.customerName = customerName;
        this.comment = comment;
        this.productList = new ArrayList<>();
    }
}