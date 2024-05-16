package id.ac.ui.cs.advprog.heymart.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PURCHASED_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "SUPERMARKET_ID")
    private Long supermarketId;

    @Column(name = "PRICE_PER_ITEM")
    private Long pricePerItem;

    @Column(name = "TRANSACTION_ID")
    private Long transactionId;
}
