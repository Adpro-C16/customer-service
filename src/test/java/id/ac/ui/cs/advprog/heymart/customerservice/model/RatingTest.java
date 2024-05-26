package id.ac.ui.cs.advprog.heymart.customerservice.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {

    private Rating rating;

    @BeforeEach
    public void setUp() {
        rating = new Rating();
    }

    @Test
    public void testGetSetCustId() {
        Long custId = 1L;
        rating.setCustId(custId);
        assertEquals(custId, rating.getCustId());
    }

    @Test
    public void testGetSetMarketId() {
        Long supermarketId = 456L;
        rating.setSupermarketId(supermarketId);
        assertEquals(supermarketId, rating.getSupermarketId());
    }

    @Test
    public void testGetSetScore() {
        int score = 10;
        rating.setScore(score);
        assertEquals(score, rating.getScore());
    }

    @Test
    public void testGetSetReview() {
        String review = "so so";
        rating.setReview(review);
        assertEquals(review, rating.getReview());
    }

    @Test
    public void testConstructor() {
        Long custId = 123L;
        Long supermarketId = 456L;
        int score = 10;
        String review = "Good";
        Rating rating = new Rating(custId, supermarketId, score, review);
        assertEquals(custId, rating.getCustId());
        assertEquals(supermarketId, rating.getSupermarketId());
        assertEquals(score, rating.getScore());
        assertEquals(review, rating.getReview());
    }
}