package id.ac.ui.cs.advprog.heymart.customerservice.repository;


import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<List<Rating>> findByCustId(Long ownerId);
    Optional<List<Rating>> findBySupermarketId(Long marketId);
}