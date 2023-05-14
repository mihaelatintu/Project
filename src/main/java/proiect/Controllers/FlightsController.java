package proiect.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.DatabaseTables.Flights;
import proiect.Services.FlightsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    private final FlightsService flightsService;

    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<Flights>> getAllFlights() {
        List<Flights> flights = flightsService.getAllFlights();
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("list/{id}")
    public ResponseEntity<Flights> getFlightById(@PathVariable("id") Long id) {
        Flights flight = flightsService.getFlightById(id);
        if (flight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Flights> saveFlight(@RequestBody Flights flight) {
        Flights savedFlight = flightsService.saveFlight(flight);
        return new ResponseEntity<>(savedFlight, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Flights> updateFlight(@PathVariable("id") Long id, @RequestBody Flights flight) {
        Flights existingFlight = flightsService.getFlightById(id);
        if (existingFlight == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingFlight.setAirline(flight.getAirline());
        existingFlight.setDepartureAirport(flight.getDepartureAirport());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setArrivalAirport(flight.getArrivalAirport());
        existingFlight.setArrivalTime(flight.getArrivalTime());
        existingFlight.setDuration(flight.getDuration());
        existingFlight.setPrice(flight.getPrice());
        Flights updatedFlight = flightsService.saveFlight(existingFlight);
        return new ResponseEntity<>(updatedFlight, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable("id") Long id) {
        Flights flight = flightsService.getFlightById(id);
        if (flight == null) {
            return new ResponseEntity<>("Flight with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        flightsService.deleteFlight(id);
        return new ResponseEntity<>("Flight with ID " + id + " deleted successfully", HttpStatus.NO_CONTENT);
    }
    @GetMapping("/list/airline/{airline}")
    public ResponseEntity<List<Flights>> getFlightsByAirline(@PathVariable("airline") String airline) {
        List<Flights> flights = flightsService.getFlightsByAirline(airline);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @GetMapping("/list/date/{date}")
    public ResponseEntity<List<Flights>> getFlightsByDepartureDate(@PathVariable("date") String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            LocalDateTime startDateTime = date.atStartOfDay();
            LocalDateTime endDateTime = startDateTime.plusDays(1).minusSeconds(1);

            List<Flights> flights = flightsService.getFlightsByDepartureDate(date);
            return new ResponseEntity<>(flights, HttpStatus.OK);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}

