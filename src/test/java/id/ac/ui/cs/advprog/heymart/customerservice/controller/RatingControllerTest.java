package id.ac.ui.cs.advprog.heymart.customerservice.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;
import id.ac.ui.cs.advprog.heymart.customerservice.service.RatingServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;



@ExtendWith(MockitoExtension.class)
public class RatingControllerTest {

    @Mock
    private RatingServiceImpl ratingService;

    @InjectMocks
    private RatingController ratingController;


    @Test
    public void testGetRatingById() {
        Long id = 5L;
        Rating rating = new Rating();
        when(ratingService.existsById(id)).thenReturn(true);
        when(ratingService.getRatingById(id)).thenReturn(rating);

        ResponseEntity<?> response = ratingController.getRatingById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rating, response.getBody());
    }

    @Test
    public void testGetRatingByIdNotFound() {
        Long id = 2L;
        when(ratingService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = ratingController.getRatingById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating with id " + id + " not found.", response.getBody());
    }


    @Test
    public void testAddNewRating() {
        Long custId = 1L;
        Long supermarketId = 2L;
        int score = 5;
        String review = "good product";
        Rating newRating = new Rating(custId, supermarketId, score, review);
        when(ratingService.addNewRating(custId, supermarketId, score, review)).thenReturn(newRating);

        HashMap<String, Object> request = new HashMap<>();
        request.put("custId", custId);
        request.put("supermarketId", supermarketId);
        request.put("score", score);
        request.put("review", review);

        ResponseEntity<?> response = ratingController.addNewRating(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Success add new rating ith id: " + newRating.getId(), response.getBody());
    }

    @Test
    public void testDeleteRating() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(true);

        ResponseEntity<?> response = ratingController.deleteRating(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Rating with id " + id + " deleted.", response.getBody());
    }

    @Test
    public void testDeleteRatingNotFound() {
        Long id = 1L;
        when(ratingService.existsById(id)).thenReturn(false);

        ResponseEntity<?> response = ratingController.deleteRating(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Rating with id " + id + " not found.", response.getBody());
    }
}
