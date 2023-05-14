package proiect.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.DatabaseTables.Ratings;

import java.util.List;

@Repository
public interface RatingsRepository extends JpaRepository<Ratings, Long> {
    List<Ratings> findByFlightsId(Long flightId);
}
