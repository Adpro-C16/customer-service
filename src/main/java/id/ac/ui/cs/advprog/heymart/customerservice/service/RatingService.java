package id.ac.ui.cs.advprog.heymart.customerservice.service;

import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RatingService {

    Rating getRatingById(Long id);
    Rating addNewRating(Long ownerId, Long marketId, int score, String review);
    void deleteRating(Long id);
    boolean existsById(Long id);


}
