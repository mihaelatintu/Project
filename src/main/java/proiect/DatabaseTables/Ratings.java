package proiect.DatabaseTables;



import jakarta.persistence.*;



@Entity
@Table(name = "ratings")
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flights flights;

    @Column(nullable = false)
    private Integer rating;

    @Column
    private String comment;

    public Ratings() {
    }

    public Ratings(Users users, Flights flights, Integer rating, String comment) {
        this.users = users;
        this.flights = flights;
        this.rating = rating;
        this.comment = comment;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public Flights getFlight() {
        return flights;
    }

    public void setFlight(Flights flights) {
        this.flights = flights;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
