package id.ac.ui.cs.advprog.heymart.customerservice.repository;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingRepositoryTest {

    @Mock
    private RatingRepository ratingRepository;

    private List<Rating> ratings;

    @BeforeEach
    public void setUp() {
        ratings = new ArrayList<>();
        ratings.add(new Rating(1L, 1L, 4, "just so so"));
        ratings.add(new Rating(1L, 2L, 3, "i will not buy this product again"));
        ratings.add(new Rating(2L, 3L, 7, "love this product"));
        ratings.add(new Rating(3L, 1L, 6, "good product"));
    }

    @Test
    public void testFindByOwnerId() {
        Long custId = 1L;
        List<Rating> expectedRatings = new ArrayList<>();
        expectedRatings.add(ratings.get(0));
        expectedRatings.add(ratings.get(1));

        when(ratingRepository.findByCustId(custId)).thenReturn(Optional.of(expectedRatings));

        Optional<List<Rating>> foundRatings = ratingRepository.findByCustId(custId);

        assertEquals(expectedRatings, foundRatings.get());
    }

    @Test
    public void testFindByMarketId() {
        Long supermarketId = 1L;
        List<Rating> expectedRatings = new ArrayList<>();
        expectedRatings.add(ratings.get(0));
        expectedRatings.add(ratings.get(2));

        when(ratingRepository.findBySupermarketId(supermarketId)).thenReturn(Optional.of(expectedRatings));

        Optional<List<Rating>> foundRatings = ratingRepository.findBySupermarketId(supermarketId);

        assertEquals(expectedRatings, foundRatings.get());
    }
}