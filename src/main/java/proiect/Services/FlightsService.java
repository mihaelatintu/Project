package proiect.Services;


import org.springframework.stereotype.Service;
import proiect.DatabaseTables.Flights;
import proiect.Repositories.FlightsRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightsService {

    private final FlightsRepository flightsRepository;

    public FlightsService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }

    public List<Flights> getAllFlights() {
        return flightsRepository.findAll();
    }

    public Flights getFlightById(Long id) {
        return flightsRepository.findById(id).orElse(null);
    }

    public Flights saveFlight(Flights flight) {
        return flightsRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        Optional<Flights> flight = flightsRepository.findById(id);
        if (flight.isPresent()) {
            flightsRepository.deleteById(id);
        }
    }
    public List<Flights> getFlightsByAirline(String airline) {
        return flightsRepository.findByAirline(airline);
    }

    public List<Flights> getFlightsByDepartureDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        return flightsRepository.findByDepartureTimeBetween(startOfDay, endOfDay);
    }
    public List<Flights> getFlightsByDepartureDateTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return flightsRepository.findByDepartureTimeBetween(startDateTime, endDateTime);
    }

}
