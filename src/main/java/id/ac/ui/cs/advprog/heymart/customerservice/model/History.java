package id.ac.ui.cs.advprog.heymart.customerservice.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
public class History {

    @Id
    private Long idHistory;

    private Long supermarketId;

    private Long custId;


    private double totalPrice;

    public History(){}




}
