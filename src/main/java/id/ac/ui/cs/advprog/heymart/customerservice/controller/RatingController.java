package id.ac.ui.cs.advprog.heymart.customerservice.controller;


import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;
import id.ac.ui.cs.advprog.heymart.customerservice.service.RatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingServiceImpl ratingService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getRatingById(@PathVariable Long id) {
        if (ratingService.existsById(id)) {
            Rating rating = ratingService.getRatingById(id);
            return ResponseEntity.ok(rating);
        } else {
            return ResponseEntity.badRequest().body("Rating with id " + id + " not found.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addNewRating(@RequestBody HashMap<String, Object> request) {
        Long ownerId = Long.parseLong(request.get("ownerId").toString());
        Long marketId = Long.parseLong(request.get("marketId").toString());
        int rating = Integer.parseInt(request.get("rating").toString());
        String review = request.get("review").toString();

        Rating newRating = ratingService.addNewRating(ownerId, marketId, rating, review);
        return ResponseEntity.ok("New rating added with id: " + newRating.getId());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRating(@PathVariable Long id) {
        if (ratingService.existsById(id)) {
            ratingService.deleteRating(id);
            return ResponseEntity.ok("Rating with id " + id + " deleted.");
        } else {
            return ResponseEntity.badRequest().body("Rating with id " + id + " not found.");
        }
    }

    private ResponseEntity<List<Rating>> handleGetRatingException(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
    }


}
