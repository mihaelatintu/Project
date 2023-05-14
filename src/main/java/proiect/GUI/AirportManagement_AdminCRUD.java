package proiect.GUI;

import java.awt.*;
import javax.swing.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import proiect.DatabaseTables.Airports;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;


public class AirportManagement_AdminCRUD extends JPanel {
	public AirportManagement_AdminCRUD() {
		initComponents();
		attachListeners();
	}

	private void initComponents() {
		JLabel label3 = new JLabel();
        textField1 = new JTextField();
        searchButton = new JButton();
		JLabel label4 = new JLabel();
		JLabel label1 = new JLabel();
        airportCodeField = new JTextField();
		JLabel label2 = new JLabel();
        airportNameField = new JTextField();
        addAirportbutton = new JButton();
        updateAirportButton = new JButton();
        deleteAirportButton = new JButton();
        mainMenuButton = new JButton();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder
        (0,0,0,0), "JFormDesigner Evaluation",javax.swing.border.TitledBorder.CENTER,javax.swing.border
        .TitledBorder.BOTTOM,new java.awt.Font("Dialog",java.awt.Font.BOLD,12),java.awt
        .Color.red), getBorder())); addPropertyChangeListener(e -> {if("border".equals(e.getPropertyName()))throw new RuntimeException()
        ;});

        //---- label3 ----
        label3.setText("Find by airport code");

        //---- searchButton ----
        searchButton.setText("Search");

        //---- label4 ----
        label4.setText("You can also enter data in the fields below, and press Add airport to save a new airport in the database");

        //---- label1 ----
        label1.setText("Airport code");

        //---- label2 ----
        label2.setText("Airport name");
        label2.setLabelFor(airportNameField);

        //---- addAirportbutton ----
        addAirportbutton.setText("Add airport");

        //---- updateAirportButton ----
        updateAirportButton.setText("Update airport");

        //---- deleteAirportButton ----
        deleteAirportButton.setText("Delete airport");

        //---- mainMenuButton ----
        mainMenuButton.setText("Return to the main menu");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGap(428, 428, 428)
                            .addComponent(mainMenuButton, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(deleteAirportButton, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateAirportButton, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE)
                                .addComponent(addAirportbutton, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE))))
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(80, 80, 80)
                    .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addComponent(label4)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 99, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(airportCodeField, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                                    .addComponent(airportNameField, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(99, Short.MAX_VALUE))))
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup()
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 695, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(label3)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                    .addComponent(searchButton)
                    .addGap(30, 30, 30)
                    .addComponent(label4)
                    .addGap(32, 32, 32)
                    .addComponent(label1)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(airportCodeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(label2)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(airportNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(39, 39, 39)
                    .addComponent(addAirportbutton)
                    .addGap(39, 39, 39)
                    .addComponent(updateAirportButton)
                    .addGap(39, 39, 39)
                    .addComponent(deleteAirportButton)
                    .addGap(69, 69, 69)
                    .addComponent(mainMenuButton)
                    .addContainerGap())
        );
		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}
	private void attachListeners() {
		searchButton.addActionListener(e -> {
			String airportCode = textField1.getText().trim();
			if (!airportCode.isEmpty()) {
				Airports airport = getAirportByCode(airportCode);
				if (airport != null) {
					airportCodeField.setText(airport.getCode());
					airportNameField.setText(airport.getName());
				} else {
					showErrorDialog("Airport not found.");
				}
			} else {
				showErrorDialog("Please enter an airport code.");
			}
		});

		addAirportbutton.addActionListener(e -> {
			String airportCode = airportCodeField.getText().trim();
			String airportName = airportNameField.getText().trim();

			if (!airportCode.isEmpty() && !airportName.isEmpty()) {
				if (isValidAirportCode(airportCode) && isValidAirportName(airportName)) {
					Airports newAirport = new Airports(airportCode, airportName);
					addAirport(newAirport);
				} else {
					showErrorDialog("Invalid airport code or name.");
				}
			} else {
				showErrorDialog("Please enter both airport code and name.");
			}
		});

		deleteAirportButton.addActionListener(e -> {
			String airportCode = airportCodeField.getText().trim();
			if (!airportCode.isEmpty()) {
				int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this airport?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					deleteAirport(airportCode);
				}
			} else {
				showErrorDialog("Please enter an airport code.");
			}
		});

		updateAirportButton.addActionListener(e -> {
			String airportCode = airportCodeField.getText().trim();
			String airportName = airportNameField.getText().trim();

			if (!airportCode.isEmpty() && !airportName.isEmpty()) {
				if (isValidAirportCode(airportCode) && isValidAirportName(airportName)) {
					Airports updatedAirport = new Airports(airportCode, airportName);
					updateAirport(updatedAirport);
				} else {
					showErrorDialog("Invalid airport code or name.");
				}
			} else {
				showErrorDialog("Please enter both airport code and name.");
			}
		});

		mainMenuButton.addActionListener(e -> {
			Window window = SwingUtilities.getWindowAncestor(this);
			window.dispose();
		});
	}

		private void clearFields() {
		textField1.setText("");
		airportCodeField.setText("");
		airportNameField.setText("");
	}
	private boolean isValidAirportCode(String airportCode) {
		return airportCode.length() == 3;
	}

	private boolean isValidAirportName(String airportName) {
		int length = airportName.length();
		return length >= 3 && length <= 255;
	}

	private void updateAirport(Airports airport) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/airports/" + airport.getCode()))
				.header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(Objects.requireNonNull(serializeAirportToJson(airport))))
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == HttpStatus.OK.value()) {
				JOptionPane.showMessageDialog(null, "Airport updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				clearFields();
			} else {
				handleErrorResponse(response);
			}
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private Airports getAirportByCode(String code) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/airports/" + code))
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == HttpStatus.OK.value()) {
				return parseAirportFromResponse(response.body());
			} else {
				JOptionPane.showMessageDialog(null, "Airport not found", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}

		return null;
	}
	private void showErrorDialog(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
	private void addAirport(Airports airport) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/airports/add"))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(Objects.requireNonNull(serializeAirportToJson(airport))))
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == HttpStatus.CREATED.value()) {
				JOptionPane.showMessageDialog(null, "Airport added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				clearFields();
			} else {
				handleErrorResponse(response);
			}
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	private void deleteAirport(String code) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/airports/" + code))
				.DELETE()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == HttpStatus.NO_CONTENT.value()) {
				clearFields();
				JOptionPane.showMessageDialog(null, "Airport deleted successfully", "Success", JOptionPane.ERROR_MESSAGE);
			} else {
				handleErrorResponse(response);
			}
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	private void handleErrorResponse(HttpResponse<String> response) {
		int statusCode = response.statusCode();
		String responseBody = response.body();


		switch (statusCode) {
			case 400 -> {
				if (responseBody.contains("Email already exists.")) {
					JOptionPane.showMessageDialog(null, "Email already exists.", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (responseBody.contains("Phone number already exists.")) {
					JOptionPane.showMessageDialog(null, "Phone number already exists.", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (responseBody.contains("Duplicate booking: The seat is already booked for the selected flight.")) {
					JOptionPane.showMessageDialog(null, "Duplicate booking: The seat is already booked for the selected flight.", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (responseBody.contains("Duplicate entry: The airport code already exists.")) {
					JOptionPane.showMessageDialog(null, "Duplicate entry: The airport code already exists.", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (responseBody.contains("Could not delete the record because it is referenced in another table.")) {
					JOptionPane.showMessageDialog(null, "Could not delete the record because it is referenced in another table.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Data integrity violation: " + responseBody, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			case 500 ->
					JOptionPane.showMessageDialog(null, "An error occurred: " + responseBody, "Error", JOptionPane.ERROR_MESSAGE);
			default ->
					JOptionPane.showMessageDialog(null, "Unexpected error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
		}

		}



	private Airports parseAirportFromResponse(String response) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.readValue(response, Airports.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String serializeAirportToJson(Airports airport) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(airport);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}


	private JTextField textField1;
    private JButton searchButton;
	private JTextField airportCodeField;
	private JTextField airportNameField;
    private JButton addAirportbutton;
    private JButton updateAirportButton;
    private JButton deleteAirportButton;
    private JButton mainMenuButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
