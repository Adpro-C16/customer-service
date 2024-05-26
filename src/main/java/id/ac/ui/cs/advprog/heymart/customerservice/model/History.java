package id.ac.ui.cs.advprog.heymart.customerservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class History {


    @OneToMany(mappedBy = "history")
    List<Product> productList = new ArrayList<>();

    @Id
    private Long idHistory;

    private Long supermarketId;

    private Long custId;


    private double totalPrice;

    public History(Long idHistory, Long custId, Long supermarketId, double totalPrice, List<Product> productList ) {
        this.productList = productList;
        this.idHistory = idHistory;
        this.supermarketId = supermarketId;
        this.custId = custId;
        this.totalPrice = totalPrice;
    }

    public History(){}

}