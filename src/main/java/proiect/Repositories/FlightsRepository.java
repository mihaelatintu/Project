package proiect.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import proiect.DatabaseTables.Flights;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights, Long> {
    List<Flights> findByAirline(String airline);
    List<Flights> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);


}
