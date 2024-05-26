package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


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

    @Test
    public void testGetAllRatings() throws ExecutionException, InterruptedException {
        Rating rating1 = new Rating(1L, 2L, 4, "Good");
        Rating rating2 = new Rating(3L, 4L, 5, "Excellent");

        List<Rating> ratings = Arrays.asList(rating1, rating2);

        when(ratingRepository.findAll()).thenReturn(ratings);

        CompletableFuture<List<Rating>> future = ratingService.getAllRatings();
        List<Rating> result = future.get();

        assertEquals(ratings, result);
    }

    @Test
    public void testGetRatingsByCustId() throws ExecutionException, InterruptedException {
        Long custId = 1L;
        Rating rating1 = new Rating(custId, 2L, 4, "Good");
        Rating rating2 = new Rating(custId, 3L, 5, "Excellent");

        List<Rating> ratings = Arrays.asList(rating1, rating2);

        when(ratingRepository.findByCustId(custId)).thenReturn(Optional.of(ratings));

        CompletableFuture<List<Rating>> future = ratingService.getRatingsByCustId(custId);
        List<Rating> result = future.get();

        assertEquals(ratings, result);
    }

    @Test
    public void testGetRatingsByMarketId() throws ExecutionException, InterruptedException {
        Long supermarketId = 2L;
        Rating rating1 = new Rating(1L, supermarketId, 4, "Good");
        Rating rating2 = new Rating(3L, supermarketId, 5, "Excellent");

        List<Rating> ratings = Arrays.asList(rating1, rating2);

        when(ratingRepository.findBySupermarketId(supermarketId)).thenReturn(Optional.of(ratings));

        CompletableFuture<List<Rating>> future = ratingService.getRatingsBySupermarketId(supermarketId);
        List<Rating> result = future.get();

        assertEquals(ratings, result);
    }
}
