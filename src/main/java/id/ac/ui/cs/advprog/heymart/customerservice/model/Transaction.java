package id.ac.ui.cs.advprog.heymart.customerservice.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "transaksi")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    @JoinColumn(name = "marketName")
    private String marketName;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Product_dummy> getProductList() {
        return productList;
    }

    public void setProductList(List<Product_dummy> productList) {
        this.productList = productList;
    }

    @JoinColumn(name = "customer")
    private String customer;

    @JoinColumn(name = "komentar")
    private String comment;

    @JoinColumn(name = "productList")
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<Product_dummy> productList;

    public Transaction() {}

    public Transaction(String marketName, String customer, String comment) {
        this.marketName = marketName;
        this.customer = customer;
        this.comment = comment;
    }
}