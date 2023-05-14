package proiect.DatabaseTables;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
@Table(name = "bookings")
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flights flights;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users users;


    @Column(name = "seat", nullable = false)
    private String seat;

    public Bookings() {
    }

    public Bookings(Flights flights, Users users, Long userId, String seat) {
        this.flights = flights;
        this.users = users;
        this.users.setId(userId); // Set the user id
        this.seat = seat;
    }


    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Flights getFlight() {
        return flights;
    }

    public void setFlight(Flights flights) {
        this.flights = flights;
    }

    //@JsonIgnore
    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
