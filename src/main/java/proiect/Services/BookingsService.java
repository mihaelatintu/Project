package proiect.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.DatabaseTables.Bookings;
import proiect.Repositories.BookingsRepository;

import java.util.List;
import java.util.Optional;
@Service
public class BookingsService {
    private final BookingsRepository bookingsRepository;

    @Autowired
    public BookingsService(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }

    public void addBooking(Bookings booking) {
        bookingsRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingsRepository.deleteById(id);
    }

    public Bookings getBookingById(Long bookingId) {
        Optional<Bookings> optionalBooking = bookingsRepository.findById(bookingId);
        return optionalBooking.orElse(null);
    }

    public List<Bookings> getBookingsByUserId(Long userId) {
        return bookingsRepository.findByUsersId(userId);
    }

    public Bookings saveBooking(Bookings booking) {
        return bookingsRepository.save(booking);
    }
}


