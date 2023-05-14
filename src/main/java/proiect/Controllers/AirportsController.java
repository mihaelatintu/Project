package proiect.Controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.DatabaseTables.Airports;
import proiect.Services.AirportsService;

@RestController
@RequestMapping("/airports")
public class AirportsController {
    private final AirportsService airportsService;

    public AirportsController(AirportsService airportsService) {
        this.airportsService = airportsService;
    }

    @PostMapping("/add")
    public ResponseEntity<Airports> addAirport(@RequestBody Airports airport) {
        Airports newAirport = airportsService.saveAirport(airport);
        return new ResponseEntity<>(newAirport, HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Airports> getAirportByCode(@PathVariable String code) {
        Airports airport = airportsService.findByCode(code);
        if (airport != null) {
            return new ResponseEntity<>(airport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteAirport(@PathVariable String code) {
        boolean deleted = airportsService.deleteAirport(code);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity<Airports> updateAirport(@PathVariable String code, @RequestBody Airports airport) {
        Airports updatedAirport = airportsService.updateAirport(code, airport);
        if (updatedAirport != null) {
            return new ResponseEntity<>(updatedAirport, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
