package com.example.bookstore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterController {

    @FXML
    private Button registerbtn;

    @FXML
    private TextField username_txt;

    @FXML
    private TextField pass_txt;

    private static final String FILE_PATH = "users.txt";

    @FXML
    void register(ActionEvent event) {
        String username = username_txt.getText();
        String password = pass_txt.getText();

        try {
            if (checkUsernameExists(username)) {
                showAlert(Alert.AlertType.ERROR, "Username Exists", "Username already exists. Please choose another one.");
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                bw.write(username + "," + password + ",customer\n");
                bw.flush();
                System.out.println("User saved: " + username + ", " + password); // Debug statement
            }
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "User registered successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "File Error", "An error occurred while writing to the user file.");
        }
    }

    private boolean checkUsernameExists(String username) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void loginnav(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the login page.");
        }
    }
}
