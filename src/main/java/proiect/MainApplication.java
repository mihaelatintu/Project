package proiect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import proiect.GUI.LoginUserUI;
import javax.swing.*;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories("proiect.Repositories")
@EntityScan("proiect.DatabaseTables")
public class MainApplication {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(MainApplication.class, args);
        startUI();
    }

    public static void startUI() {
        java.awt.EventQueue.invokeLater(() -> {
            LoginUserUI mainWindow = new LoginUserUI(new JFrame(), true);
            mainWindow.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            mainWindow.setVisible(true);
        });
    }
}
