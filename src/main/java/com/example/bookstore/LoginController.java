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
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginbtn;

    @FXML
    private TextField usertxt;

    @FXML
    private TextField passtxt;

    @FXML
    private Button registerbtn;

    @FXML
    void login(ActionEvent event) {
        String username = usertxt.getText();
        String password = passtxt.getText();

        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            boolean userFound = false;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                    userFound = true;
                    String userType = userDetails[2];
                    openPage(userType, username);
                    break;
                }
            }
            if (!userFound) {
                showAlert(Alert.AlertType.ERROR, "Invalid Credentials", "Please enter valid username and password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "File Error", "An error occurred while reading the user file.");
        }
    }

    @FXML
    void register(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("registerpage.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Register");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the register page.");
        }
    }

    private void openPage(String userType, String username) {
        Stage stage = (Stage) loginbtn.getScene().getWindow();
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root;
            if ("admin".equals(userType)) {
                loader.setLocation(getClass().getResource("mainmenu.fxml"));
                root = loader.load();
            } else if ("customer".equals(userType)) {
                loader.setLocation(getClass().getResource("customer_view.fxml"));
                root = loader.load();

                BookController customerController = loader.getController();
               // customerController.setCurrentUsername(username); // Pass username
            } else {
                showAlert(Alert.AlertType.ERROR, "Invalid User Type", "Invalid user type.");
                return;
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while loading the page.");
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
