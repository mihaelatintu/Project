package proiect.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.DatabaseTables.Airports;
import proiect.Repositories.AirportsRepository;

@Service
public class AirportsService {
    private final AirportsRepository airportsRepository;

    public AirportsService(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    public Airports saveAirport(Airports airport) {
        return airportsRepository.save(airport);
    }

    public Airports findByCode(String code) {
        return airportsRepository.findByCode(code);
    }

    public boolean deleteAirport(String code) {
        Airports airport = airportsRepository.findByCode(code);
        if (airport != null) {
            airportsRepository.delete(airport);
            return true;
        }
        return false;
    }

    public Airports updateAirport(String code, Airports updatedAirport) {
        Airports airport = airportsRepository.findByCode(code);
        if (airport != null) {
            airport.setCode(updatedAirport.getCode());
            airport.setName(updatedAirport.getName());
            return airportsRepository.save(airport);
        }
        return null;
    }
}
