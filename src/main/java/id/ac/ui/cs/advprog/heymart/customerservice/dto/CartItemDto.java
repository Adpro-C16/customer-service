package id.ac.ui.cs.advprog.heymart.customerservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

    private String productId;

    private String productName;

    private Integer amount;

    private Long supermarketId;

    private Double pricePerAmount;

    public double getTotalPrice(){
        return (double) this.getAmount() * this.pricePerAmount;
    }
}
