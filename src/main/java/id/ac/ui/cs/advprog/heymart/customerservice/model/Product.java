package id.ac.ui.cs.advprog.heymart.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @ManyToOne
    @JoinColumn(name = "history_id")
    private History history;

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private Double productPrice;

    @Column(name = "SUPERMARKET_ID")
    private Long supermarketId;

    public Product(String productId, String productName, Double productPrice, Long supermarketId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.supermarketId = supermarketId;
    }

}