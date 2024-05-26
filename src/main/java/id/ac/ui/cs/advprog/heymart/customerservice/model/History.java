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


    @OneToMany(mappedBy = "history", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> productList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHistory;

    private Long supermarketId;

    private Long custId;


    private double totalPrice;
    public History(){}

    public void addProductList(Product product) {
        productList.add(product);
        product.setHistory(this);
        totalPrice += product.getProductPrice();
    }

    public History(Builder builder) {
        this.productList = builder.productList;
        this.supermarketId = builder.supermarketId;
        this.custId = builder.custId;
        this.totalPrice = builder.totalPrice;
    }

    public static class Builder {
        private Long custId;
        private Long supermarketId;
        private List<Product> productList = new ArrayList<>();
        private double totalPrice;

        public Builder custId(Long custId) {
            this.custId = custId;
            return this;
        }

        public Builder supermarketId(Long supermarketId) {
            this.supermarketId = supermarketId;
            return this;
        }

        public Builder productList(List<Product> productList) {
            this.productList = productList;
            return this;
        }

        public Builder totalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public History build() {
            return new History(this);
        }
    }

}