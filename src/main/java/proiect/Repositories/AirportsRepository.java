package proiect.Repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import proiect.DatabaseTables.Airports;



public interface AirportsRepository extends JpaRepository<Airports, Long> {
    Airports findByCode(String code);
}
