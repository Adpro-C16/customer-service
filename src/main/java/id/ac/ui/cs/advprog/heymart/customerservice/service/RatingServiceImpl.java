package id.ac.ui.cs.advprog.heymart.customerservice.service;


import id.ac.ui.cs.advprog.heymart.customerservice.model.Rating;
import id.ac.ui.cs.advprog.heymart.customerservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).get();
    }

    @Override
    public Rating addNewRating(Long ownerId, Long marketId, int score, String review) {
        Rating newRating = new Rating(ownerId, marketId, score, review);
        return ratingRepository.save(newRating);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);

    }

    @Override
    public boolean existsById(Long id) {
        return ratingRepository.existsById(id);
    }
}
