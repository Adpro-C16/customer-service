package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;




import org.mockito.InjectMocks;
import org.mockito.Mock;




public class RatingServiceTest {


    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRatingById() {
        Long ratingId = 789L;
        Rating rating = new Rating(123L, 456L, 4, "Great product!");

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        Rating retrievedRating = ratingService.getRatingById(ratingId);

        assertEquals(rating, retrievedRating);
    }

    @Test
    public void testAddNewRating() {
        Long custId = 1L;
        Long supermarketId = 2L;
        int score = 4;
        String review = "just so so";
        Rating rating1 = new Rating(custId, supermarketId, score, review);

        when(ratingRepository.save(ArgumentMatchers.any(Rating.class))).thenReturn(rating1);

        Rating addRating1 = ratingService.addNewRating(custId, supermarketId, score, review);

        assertEquals(rating1, addRating1);
    }

    @Test
    public void testDeleteRating() {
        Long ratingId = 10L;

        ratingService.deleteRating(ratingId);

        verify(ratingRepository).deleteById(ratingId);
    }

    @Test
    public void testExistsById() {
        Long ratingId = 10L;

        when(ratingRepository.existsById(ratingId)).thenReturn(true);

        boolean exists = ratingService.existsById(ratingId);

        assertTrue(exists);
    }
}
