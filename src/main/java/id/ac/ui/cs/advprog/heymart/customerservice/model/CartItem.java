package id.ac.ui.cs.advprog.heymart.customerservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CART_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "SUPERMARKET_ID")
    private Long supermarketId;
}
