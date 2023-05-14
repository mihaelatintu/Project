package proiect.GUI;

import org.json.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.http.*;
import java.net.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static proiect.GUI.LoginUserUI.loggedInUserId;

public class BookingManagement_UserUI extends JFrame {


    private static final Long userId = Long.valueOf(loggedInUserId);


    public BookingManagement_UserUI(Long ignoredUserId) {
        setLayout(new BorderLayout());

        JButton openBookingsButton = new JButton("Open Bookings");
        openBookingsButton.addActionListener(e -> showBookings());
        add(openBookingsButton, BorderLayout.CENTER);

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Hide on close
        setLocationRelativeTo(null); // Set frame location to center of the screen
        setVisible(true);


    }



    private void showBookings() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/bookings/all"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONArray bookingsArray = new JSONArray(response.body());
                JList<Booking> bookingsList = new JList<>(getBookingsForUser(bookingsArray));
                bookingsList.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent evt) {
                        if (evt.getClickCount() == 2) {
                            Booking selectedBooking = bookingsList.getSelectedValue();
                            showFlightSelectionPopup(selectedBooking.flightId());
                        }
                    }
                });
                System.out.println(loggedInUserId);
                JOptionPane.showMessageDialog(null, new JScrollPane(bookingsList), "Bookings", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error retrieving bookings");
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error retrieving bookings");
            ex.printStackTrace();
        }
    }

    private Booking[] getBookingsForUser(JSONArray bookingsArray) {
        ArrayList<Booking> bookings = new ArrayList<>();
        for (int i = 0; i < bookingsArray.length(); i++) {
            JSONObject bookingObj = bookingsArray.getJSONObject(i);
            long userId = bookingObj.getJSONObject("user").getLong("id");
            if (userId == BookingManagement_UserUI.userId) {
                long id = bookingObj.getLong("id");
                long flightId = bookingObj.getJSONObject("flight").getLong("id");
                String seat = bookingObj.getString("seat");
                bookings.add(new Booking(id, flightId, seat));
            }
        }
        return bookings.toArray(new Booking[0]);
    }

    private void showFlightSelectionPopup(long flightId) {
        // Send a GET request to retrieve the list of flights
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/flights/list/" + flightId))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JSONObject flightObj = new JSONObject(response.body());
                flightObj.getLong("id");
                String airline = flightObj.getString("airline");
                String departureAirport = flightObj.getJSONObject("departureAirport").getString("name");
                String arrivalAirport = flightObj.getJSONObject("arrivalAirport").getString("name");
                String departureDate = flightObj.getString("departureTime");

                // Format the date and duration
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime departureTime = LocalDateTime.parse(departureDate);
                LocalDateTime arrivalTime = departureTime.plusMinutes(flightObj.getInt("duration"));

                String formattedDepartureTime = departureTime.format(dateFormatter) + " " + departureTime.format(timeFormatter);
                String formattedArrivalTime = arrivalTime.format(dateFormatter) + " " + arrivalTime.format(timeFormatter);

                int durationMinutes = flightObj.getInt("duration");
                int hours = durationMinutes / 60;
                int minutes = durationMinutes % 60;
                String formattedDuration = hours + " hours " + minutes + " minutes";

                JOptionPane.showMessageDialog(null,
                        "Airline: " + airline + "\n"
                                + "Departure Airport: " + departureAirport + "\n"
                                + "Arrival Airport: " + arrivalAirport + "\n"
                                + "Departure Time: " + formattedDepartureTime + "\n"
                                + "Arrival Time: " + formattedArrivalTime + "\n"
                                + "Duration: " + formattedDuration + "\n"
                                + "Price: " + flightObj.getDouble("price") + " RON");

            } else {

                JOptionPane.showMessageDialog(null, "Error retrieving flight");
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error retrieving flight");
            ex.printStackTrace();
        }

    }


    public static void main(String[] args) {
        // Replace "previousWindow" with the name of your previous window class
        UserCRUD_startPage dialog = new UserCRUD_startPage(new javax.swing.JFrame());
        dialog.setVisible(false);
        System.out.print(loggedInUserId);
        BookingManagement_UserUI bookingManagementUI = new BookingManagement_UserUI(userId); // Replace 1L with actual logged-in user's ID
        bookingManagementUI.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Show the previous window when the BookingManagement_UserUI window is closed
                dialog.setVisible(true);
            }
        });
    }

}

record Booking(Long id, Long flightId, String seat) {

    @Override
    public String toString() {
        return "Booking ID: " + id + ", Flight ID: " + flightId + ", Seat: " + seat;
    }
}

@SuppressWarnings("ALL")
record Flight(long id, String airline, String departureAirport, String arrivalAirport, String departureDate) {

    @Override
    public String toString() {
        return id + " - " + airline + " | Departure: " + departureAirport + " | Arrival: " + arrivalAirport + " | Date: " + departureDate;
    }
}
