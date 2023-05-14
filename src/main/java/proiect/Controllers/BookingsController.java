package proiect.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.DatabaseTables.Bookings;
import proiect.DatabaseTables.Flights;
import proiect.DatabaseTables.Users;
import proiect.Services.BookingsService;
import proiect.Services.FlightsService;
import proiect.Services.UsersService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

    private final BookingsService bookingsService;
    private final FlightsService flightsService;
    private final UsersService usersService;

    @Autowired
    public BookingsController(BookingsService bookingsService, FlightsService flightsService, UsersService usersService) {
        this.bookingsService = bookingsService;
        this.flightsService = flightsService;
        this.usersService = usersService;
    }


    @GetMapping("/id/{bookingId}")
    public ResponseEntity<?> getBookingById(@PathVariable Long bookingId) {
        try {
            Bookings booking = bookingsService.getBookingById(bookingId);
            if (booking != null) {
                return ResponseEntity.ok(booking);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving booking data");
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBooking(@RequestBody UpdateBookingRequest newBookingRequest) {
        try {
            Flights flight = flightsService.getFlightById(newBookingRequest.getFlightId());
            Optional<Users> userOptional = usersService.getUserById(newBookingRequest.getUserId());

            if (flight == null || userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid flight or user ID");
            }

            Bookings newBooking = new Bookings(flight, userOptional.get(), newBookingRequest.getUserId(), newBookingRequest.getSeat());
            Bookings savedBooking = bookingsService.saveBooking(newBooking);

            return ResponseEntity.ok(savedBooking);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving booking");
        }
    }

    @PutMapping("/id/{bookingId}")
    public ResponseEntity<?> updateBooking(@PathVariable Long bookingId, @RequestBody UpdateBookingRequest updatedBooking) {
        try {
            Bookings existingBooking = bookingsService.getBookingById(bookingId);
            if (existingBooking != null) {
                Flights flight = flightsService.getFlightById(updatedBooking.getFlightId());
                Optional<Users> userOptional = usersService.getUserById(updatedBooking.getUserId());

                if (flight == null || userOptional.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid flight or user ID");
                }

                existingBooking.setFlight(flight);
                existingBooking.setUser(userOptional.get());
                existingBooking.setSeat(updatedBooking.getSeat());

                // Save the updated booking
                Bookings updated = bookingsService.saveBooking(existingBooking);

                return ResponseEntity.ok(updated);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating booking");
        }
    }

    @DeleteMapping("/delete/id/{bookingId}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long bookingId) {
        try {
            Bookings existingBooking = bookingsService.getBookingById(bookingId);
            if (existingBooking != null) {
                bookingsService.deleteBooking(bookingId);
                return ResponseEntity.ok("Booking deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting booking");
        }
    }



    @GetMapping("/all")
    public ResponseEntity<?> getAllBookings() {
        try {
            List<Bookings> bookings = bookingsService.getAllBookings();
            if (!bookings.isEmpty()) {
                return ResponseEntity.ok(bookings);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bookings found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving bookings data");
        }
    }

    @GetMapping("/user/{user_id}")
    public ResponseEntity<?> getBookingByUserId(@PathVariable("user_id") Long userId) {
        try {
            List<Bookings> bookings = bookingsService.getBookingsByUserId(userId);
            if (!bookings.isEmpty()) {
                return ResponseEntity.ok(bookings);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No bookings found for the user");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving booking data");
        }
    }

}
