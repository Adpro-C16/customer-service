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
@Table(name = "SHOPPING_CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "SUPERMARKET_ID")
    private Long supermarketId = -1L;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private List<CartItem> cartItems;

    @Transient
    @JsonIgnore
    private CartState state;

    public void addItem(CartItem cartItem){
        state.addItem(this, cartItem);
    }

    public void removeItem(CartItem cartItem){
        state.removeItem(this, cartItem);
    }

    public List<CartItem> checkout(){
        return state.checkout(this);
    }

    public CartItem getFirst() {
        if (cartItems != null && !cartItems.isEmpty()) {
            return cartItems.get(0);
        } else {
            return null; 
        }
    }
}
