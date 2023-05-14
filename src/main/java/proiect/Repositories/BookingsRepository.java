package proiect.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.DatabaseTables.Bookings;

import java.util.List;
import java.util.Optional;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByUsersId(Long id);
}


