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

    @Id
    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private Double productPrice;

    @Column(name = "SUPERMARKET_ID")
    private Long supermarketId;

    @ManyToOne
    @JoinColumn(name = "HISTORY_ID")
    private History history;

    public Product(String id, String productName, double productPrice, Long supermarketId) {
        this.productId = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.supermarketId =supermarketId;
    }

}