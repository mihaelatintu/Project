package proiect.GUI;

import com.fatboyindustrial.gsonjavatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.springframework.http.HttpStatus;
import proiect.DatabaseTables.Flights;
import proiect.Services.AirportsService;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/*
 * Created by JFormDesigner on Sat May 13 23:50:24 EEST 2023
 */



/**
 * @author Eduard Oprea
 */
public class FlightManagement_AdminCRUD extends JPanel {


	public FlightManagement_AdminCRUD() {
		initComponents();
	}

	private String departureAirportCode;
	private String arrivalAirportCode;
	private String selectedFlightId;
	private String flightId2;
	private static final String BASE_URL = "http://localhost:8080/flights";  // Replace with your actual server URL
	private static final OkHttpClient httpClient = new OkHttpClient();
	Gson gson = Converters.registerAll(new GsonBuilder()).create();

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
		// Generated using JFormDesigner Evaluation license - Eduard Oprea
		airlineRadioButton = new JRadioButton();
		dateRadioButton = new JRadioButton();
		airlineTextField = new JTextField();
		dateTextField = new JTextField();
		label1 = new JLabel();
		findFlightsButton = new JButton();
		airlineNameField = new JTextField();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		arrivalAirportTextField = new JTextField();
		label5 = new JLabel();
		departureAirportTextField = new JTextField();
		label6 = new JLabel();
		textField5 = new JTextField();
		label7 = new JLabel();
		arrivalTimeTextField = new JTextField();
		etaField = new JLabel();
		label9 = new JLabel();
		priceTextField = new JTextField();
		addNewFlightButton = new JButton();
		updateFlightButton = new JButton();
		deleteFlightButton = new JButton();
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(airlineRadioButton);
		radioGroup.add(dateRadioButton);
		airlineRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFlightFields();
			}
		});

		dateRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearFlightFields();
			}
		});

		//======== this ========
		setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
				border.EmptyBorder(0, 0, 0, 0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax.swing.border.TitledBorder.CENTER
				, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("D\u0069alog", java.awt.Font
				.BOLD, 12), java.awt.Color.red), getBorder()));
		addPropertyChangeListener(
				new java.beans.PropertyChangeListener() {
					@Override
					public void propertyChange(java.beans.PropertyChangeEvent e) {
						if ("\u0062order"
								.equals(e.getPropertyName())) throw new RuntimeException();
					}
				});

		//---- airlineRadioButton ----
		airlineRadioButton.setText("Airline");

		//---- dateRadioButton ----
		dateRadioButton.setText("Date");

		//---- label1 ----
		label1.setText("yyyymmdd format - example: 20230516");
		label1.setHorizontalAlignment(SwingConstants.CENTER);

		//---- findFlightsButton ----
		findFlightsButton.setText("Find flights");

		//---- label2 ----
		label2.setText("Selected flight data:");

		//---- label3 ----
		label3.setText("Airline");

		//---- label4 ----
		label4.setText("Departure Aiport");

		//---- label5 ----
		label5.setText("Arrival Aiport");

		//---- label6 ----
		label6.setText("Departure Time");

		//---- label7 ----
		label7.setText("Arrival Time");

		//---- etaField ----
		etaField.setText("Expected time of the flight is");

		//---- label9 ----
		label9.setText("Price");

		//---- addNewFlightButton ----
		addNewFlightButton.setText("Add new flight");

		//---- updateFlightButton ----
		updateFlightButton.setText("Update flight");

		//---- deleteFlightButton ----
		deleteFlightButton.setText("Delete flight");

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGap(152, 152, 152)
								.addComponent(etaField, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addGap(56, 56, 56)
								.addGroup(layout.createParallelGroup()
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup()
														.addComponent(label2)
														.addComponent(label3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
														.addComponent(airlineNameField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
														.addComponent(label4)
														.addComponent(departureAirportTextField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
														.addComponent(label5, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
														.addComponent(arrivalAirportTextField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup()
														.addComponent(arrivalTimeTextField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
														.addComponent(textField5, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
														.addComponent(label6)
														.addComponent(label9, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
														.addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
														.addComponent(label7, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
												.addGap(269, 269, 269))
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																.addComponent(dateRadioButton)
																.addGap(18, 18, 18)
																.addComponent(dateTextField))
														.addComponent(findFlightsButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																.addComponent(airlineRadioButton)
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																		.addComponent(label1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
																		.addComponent(airlineTextField, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
																.addGap(0, 0, Short.MAX_VALUE)))
												.addGap(263, 263, 263))
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
														.addComponent(deleteFlightButton, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
														.addComponent(updateFlightButton, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
														.addComponent(addNewFlightButton, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
												.addGap(0, 0, Short.MAX_VALUE))))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup()
						.addGroup(layout.createSequentialGroup()
								.addGap(57, 57, 57)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(airlineTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(airlineRadioButton))
								.addGap(22, 22, 22)
								.addComponent(label1)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(dateRadioButton)
										.addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(findFlightsButton)
								.addGap(53, 53, 53)
								.addComponent(label2)
								.addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label3)
										.addComponent(label6))
								.addGap(2, 2, 2)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(airlineNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label4)
										.addComponent(label7))
								.addGap(4, 4, 4)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(departureAirportTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(arrivalTimeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(label5)
										.addComponent(label9))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(arrivalAirportTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(etaField)
								.addGap(47, 47, 47)
								.addComponent(addNewFlightButton)
								.addGap(18, 18, 18)
								.addComponent(updateFlightButton)
								.addGap(18, 18, 18)
								.addComponent(deleteFlightButton)
								.addContainerGap(56, Short.MAX_VALUE))
		);
		findFlightsButton.addActionListener(e -> {
			String searchQuery = null;

			if (airlineRadioButton.isSelected()) {
				searchQuery = "/list/airline/" + airlineTextField.getText();
			} else if (dateRadioButton.isSelected()) {
				searchQuery = "/list/date/" + dateTextField.getText();
			}

			if (searchQuery != null) {
				try {
					Request request = new Request.Builder().url(BASE_URL + searchQuery).build();
					Response response = httpClient.newCall(request).execute();

					if (response.isSuccessful()) {
						Type listType = new TypeToken<ArrayList<Flights>>() {
						}.getType();
						List<Flights> flightsList = gson.fromJson(response.body().string(), listType);

						// Show flight selection dialog and retrieve the selected flight ID
						String selectedFlightId = showFlightSelectionDialog(flightsList);

						// Store the selected flight ID
						this.selectedFlightId = selectedFlightId;
					} else {
						JOptionPane.showMessageDialog(null, "No bookings found!");
					}
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Error occurred while fetching flights data.");
				}
			}
		});

		//To do in alta versiune , no more :) !

		addNewFlightButton.setVisible(false);
		updateFlightButton.setVisible(false);

		deleteFlightButton.addActionListener(e ->

		{
			String flightId = flightId2;
			if (flightId != null && !flightId.isEmpty()) {
				int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this flight?", "Confirmation", JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					if (deleteFlight(flightId)) JOptionPane.showMessageDialog(this, "Flight deleted successfully!");
					else JOptionPane.showMessageDialog(this, "Error! The flight is not empty!");
				}
			} else {
				showErrorDialog("Please select a flight first.");
			}
		});

		// JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
	}


	private boolean deleteFlight(String code) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/flights/" + code))
				.DELETE()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == HttpStatus.NO_CONTENT.value()) {
				clearFlightFields();
				JOptionPane.showMessageDialog(null, "Flight deleted successfully", "Success", JOptionPane.ERROR_MESSAGE);
				return true;
			}
		} catch (IOException | InterruptedException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	private void showErrorDialog(String errorMessage) {
		JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
	private String showFlightSelectionDialog(List<Flights> flightsList) {
		String[] options = new String[flightsList.size()];

		for (int i = 0; i < flightsList.size(); i++) {
			Flights flight = flightsList.get(i);
			String option = "Flight ID: " + flight.getId() + ", Airline: " + flight.getAirline();
			options[i] = option;
		}

		String selectedOption = (String) JOptionPane.showInputDialog(null, "Multiple flights found. Please select one:", "Select Flight", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (selectedOption != null) {
			int selectedIndex = -1;
			for (int i = 0; i < options.length; i++) {
				if (options[i].equals(selectedOption)) {
					selectedIndex = i;
					break;
				}
			}

			if (selectedIndex != -1) {
				Flights selectedFlight = flightsList.get(selectedIndex);
				arrivalAirportCode= String.valueOf(selectedFlight.getArrivalAirport().getCode());
				departureAirportCode=selectedFlight.getDepartureAirport().getCode();
				flightId2= String.valueOf(selectedFlight.getId());
				airlineNameField.setText(selectedFlight.getAirline());
				departureAirportTextField.setText(String.valueOf(selectedFlight.getDepartureAirport()));
				textField5.setText(selectedFlight.getDepartureTime().toString());
				arrivalAirportTextField.setText(String.valueOf(selectedFlight.getArrivalAirport()));
				arrivalTimeTextField.setText(selectedFlight.getArrivalTime().toString());
				etaField.setText("Estimated time of flight: "+ selectedFlight.getDuration()+ " minutes");
				priceTextField.setText(String.valueOf(selectedFlight.getPrice()));
				arrivalTimeTextField.setText(String.valueOf(selectedFlight.getArrivalTime()));
			}
		}
		return selectedOption;
	}
	public void clearFlightFields() {
		airlineNameField.setText("");
		departureAirportTextField.setText("");
		textField5.setText("");
		arrivalAirportTextField.setText("");
		arrivalTimeTextField.setText("");
		etaField.setText("");
		priceTextField.setText("");
		dateTextField.setText("");
		airlineTextField.setText("");
	}

	private List<Flights> parseFlightsResponse(Response response) throws IOException {
		Type listType = new TypeToken<List<Flights>>() {}.getType();
		return gson.fromJson(response.body().string(), listType);
	}
	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
	// Generated using JFormDesigner Evaluation license - Eduard Oprea
	private JRadioButton airlineRadioButton;
	private JRadioButton dateRadioButton;
	private JTextField airlineTextField;
	private JTextField dateTextField;
	private JLabel label1;
	private JButton findFlightsButton;
	private JTextField airlineNameField;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JTextField arrivalAirportTextField;
	private JLabel label5;
	private JTextField departureAirportTextField;
	private JLabel label6;
	private JTextField textField5;
	private JLabel label7;
	private JTextField arrivalTimeTextField;
	private JLabel etaField;
	private JLabel label9;
	private JTextField priceTextField;
	private JButton addNewFlightButton;
	private JButton updateFlightButton;
	private JButton deleteFlightButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
