package proiect.GUI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import proiect.DatabaseTables.Users;

import javax.swing.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static proiect.GUI.LoginUserUI.loggedInUserId;

public class UserManagementUI extends javax.swing.JDialog {
    private static String hashedPasswordDBA;

    public UserManagementUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        parent.setVisible(false);
    }



    private void initComponents() {

        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea jTextArea1 = new JTextArea();
        emailField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();
        JLabel jLabel6 = new JLabel();
        JLabel phoneNumberLabel = new JLabel();
        phoneNumberField = new javax.swing.JTextField();
        JButton updateUserButton = new JButton();
        JLabel jLabel10 = new JLabel();
        JLabel jLabel11 = new JLabel();
        JLabel jLabel1 = new JLabel();
        newPassField = new javax.swing.JTextField();
        JLabel jLabel2 = new JLabel();
        newPassConfirmField = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        emailField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emailField.setToolTipText("Enter your email here");

        firstNameField.setToolTipText("Your first name");

        lastNameField.setToolTipText("Your last name");
        lastNameField.addActionListener(this::lastNameFieldActionPerformed);

        jLabel6.setText("Email");

        phoneNumberLabel.setText("Phone number");

        updateUserButton.setText("Update user data");
        updateUserButton.addActionListener(this::updateUserButtonActionPerformed);

        jLabel10.setText("Last name");

        jLabel11.setText("First name");

        jLabel1.setText("New password");

        jLabel2.setText("Confirm new password");

        jCheckBox1.setText("I want to change my password, as well");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jCheckBox1)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(newPassConfirmField)
                                        .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneNumberLabel))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(phoneNumberField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastNameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNameField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(emailField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(updateUserButton)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberLabel))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(newPassField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(newPassConfirmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(updateUserButton)
                .addContainerGap(71, Short.MAX_VALUE))
        );


        String endpoint = "http://localhost:8080/users/id/" + loggedInUserId;


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Parse the response body
                ObjectMapper mapper = new ObjectMapper();
                Users user = mapper.readValue(response.body(), Users.class);
                //selectedUserId = user.getId();

                // Populate the UI fields
                emailField.setText(user.getEmail());
                firstNameField.setText(user.getFirstName());
                lastNameField.setText(user.getLastName());
                phoneNumberField.setText(user.getPhoneNumber());
                hashedPasswordDBA= user.getPassword();

            } else {
                JOptionPane.showMessageDialog(null, "User not found");
            }
        }
        catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error retrieving user data");
        }
        updateUserButton.addActionListener(e -> updateUserButton());

        pack();
    }

    private void lastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException |
                 InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserManagementUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            UserManagementUI dialog = new UserManagementUI(new JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    dialog.dispose();

                }
            });
            dialog.setVisible(true);
        });


    }

    private void updateUserButton() {

        String userID = String.valueOf(loggedInUserId);


        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String phoneNumber = phoneNumberField.getText();

        // Validate that no field is empty
        if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        // Validate email with regex pattern
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if (!email.matches(emailRegex)) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email address");
            return;
        }

        // Validate phone number
        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Phone number must contain digits only");
            return;
        }

        // Check if the password needs to be updated
        boolean updatePassword = jCheckBox1.isSelected();
        String oldPassword;
        String newPassword = null;
        if (updatePassword && (newPassField.getText().length()>=8)&&(newPassConfirmField.getText().length()>=8)) {
            // Prompt the user to enter their old password
            JPasswordField passwordField = new JPasswordField();
            int result = JOptionPane.showConfirmDialog(null, passwordField, "Enter your old password", JOptionPane.OK_CANCEL_OPTION);
            if (result != JOptionPane.OK_OPTION) {
                // User canceled the password prompt
                return;
            }
            oldPassword = new String(passwordField.getPassword());
            String storedHashedPassword = hashedPasswordDBA;

            // Check if old password is correct
            if (!BCrypt.checkpw(oldPassword, storedHashedPassword)) {
                JOptionPane.showMessageDialog(null, "Incorrect old password");
                return;
            }
            oldPassword = new String(passwordField.getPassword());

            String newPassChars = newPassField.getText();
            String newPassConfirm = newPassConfirmField.getText();

            // Validate new password
            if (!newPassChars.equals(newPassConfirm)) {
                JOptionPane.showMessageDialog(null, "New passwords don't match");
                return;
            }

            if (newPassChars.length() < 8) {
                JOptionPane.showMessageDialog(null, "New password must be at least 8 characters long");
                return;
            }

            if (newPassChars.equals(oldPassword)) {
                JOptionPane.showMessageDialog(null, "New password can't be the same as the old password");
                return;
            }
            newPassword=newPassChars;
        }
        else if (updatePassword){
            JOptionPane.showMessageDialog(null, "Password fields are not meeting the requirements! ");
            return;
        }

        // Create a new user object
        Users user = new Users();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(newPassword);

        endpointID(userID, user);
    }


    public void endpointID(String userID, Users user) {
        String endpoint = "http://localhost:8080/users/update/user/" + userID;
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(user)))
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JOptionPane.showMessageDialog(null, "User updated successfully");
            } else if (response.statusCode() == 400) {
                String errorMessage = response.body();
                if (errorMessage.contains("mail")) {
                    JOptionPane.showMessageDialog(null, "Email already exists");
                } else if (errorMessage.contains("number")) {
                    JOptionPane.showMessageDialog(null, "Phone number already exists");
                } else if (errorMessage.contains("password")) {
                    JOptionPane.showMessageDialog(null, "Invalid old password");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update user");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user");
            }
        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error updating user");
        }
    }

    private javax.swing.JTextField emailField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JTextField newPassConfirmField;
    private javax.swing.JTextField newPassField;
    private javax.swing.JTextField phoneNumberField;
}
