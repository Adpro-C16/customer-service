package id.ac.ui.cs.advprog.heymart.customerservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.ui.cs.advprog.heymart.customerservice.state.CartState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "SUPERMARKET_ID")
    private Long marketId;

    @Column(name = "comment")
    private String comment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TRANSACTION_ID")
    private List<PurchasedItem> purchasedItems;
}