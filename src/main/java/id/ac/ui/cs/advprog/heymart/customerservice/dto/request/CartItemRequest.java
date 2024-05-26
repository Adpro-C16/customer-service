package id.ac.ui.cs.advprog.heymart.customerservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {
    private Long userId = -1L;
    private String productId;
    private Integer amount;
    private Long supermarketId;
}
