package proiect.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.DatabaseTables.Flights;
import proiect.DatabaseTables.Ratings;
import proiect.Services.RatingsService;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    private final RatingsService ratingsService;

    @Autowired
    public RatingsController(RatingsService ratingsService) {
        this.ratingsService = ratingsService;
    }

    @PostMapping("/create")
    public Ratings createRating(@RequestBody Ratings rating) {
        return ratingsService.createRating(rating);
    }

    @GetMapping("/{id}")
    public Ratings getRating(@PathVariable Long id) {
        return ratingsService.getRatingById(id);
    }
    @GetMapping("/list/all")
    public ResponseEntity<List<Ratings>> getAllRatings() {
        List<Ratings> ratings = ratingsService.getAllRatings();
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Ratings updateRating(@PathVariable Long id, @RequestBody Ratings rating) {
        return ratingsService.updateRating(id, rating);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingsService.deleteRatingById(id);
    }

    @GetMapping("/flight/{flightId}")
    public List<Ratings> getRatingsByFlight(@PathVariable Long flightId) {
        return ratingsService.getRatingsByFlightId(flightId);
    }
}
