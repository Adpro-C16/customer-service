package id.ac.ui.cs.advprog.heymart.customerservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "rating",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cust_id", "supermarket_id"})
        })
@Entity
public class Rating {
    private Long custId;
    private Long supermarketId;
    private int score;
    private String review;

    @Id
    private Long id;

    public Rating() {
    }

    public Rating(Long custId, Long supermarketId, int score, String review) {
        this.custId = custId;
        this.supermarketId = supermarketId;
        this.score = score;
        this.review = review;
    }

}
