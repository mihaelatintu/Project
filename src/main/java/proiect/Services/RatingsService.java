package proiect.Services;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.DatabaseTables.Ratings;
import proiect.Repositories.RatingsRepository;

import java.util.List;

@Service
public class RatingsService {

    private final RatingsRepository ratingsRepository;

    @Autowired
    public RatingsService(RatingsRepository ratingsRepository) {
        this.ratingsRepository = ratingsRepository;
    }

    public Ratings createRating(Ratings rating) {
        return ratingsRepository.save(rating);
    }

    public Ratings getRatingById(Long id) {
        return ratingsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating"));
    }

    public Ratings updateRating(Long id, Ratings rating) {
        Ratings existingRating = getRatingById(id);

        existingRating.setRating(rating.getRating());
        existingRating.setComment(rating.getComment());

        return ratingsRepository.save(existingRating);
    }

    public void deleteRatingById(Long id) {
        ratingsRepository.deleteById(id);
    }

    public List<Ratings> getRatingsByFlightId(Long flightId) {
        return ratingsRepository.findByFlightsId(flightId);
    }
}
