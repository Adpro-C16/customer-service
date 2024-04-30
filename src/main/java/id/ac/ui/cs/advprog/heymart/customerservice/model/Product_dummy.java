package id.ac.ui.cs.advprog.heymart.customerservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product_dummy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @Column(name = "productName")
    private String productName;

    @Column(name = "productPrice")
    private int productPrice;

    @Column(name = "productStock")
    private int productStock;

    public Product_dummy() {}

    public Product_dummy(String productName, int productPrice, int productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
}
