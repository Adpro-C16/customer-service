package id.ac.ui.cs.advprog.heymart.customerservice.dto;

import id.ac.ui.cs.advprog.heymart.customerservice.model.CartItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ShoppingCartDto {

    private Long supermarketId = -1L;

    private List<CartItemDto> cartItems;
}
