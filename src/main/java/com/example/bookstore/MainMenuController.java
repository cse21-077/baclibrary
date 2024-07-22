package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class MainMenuController {

    @FXML
    private Button addbook_nav_btn;

    @FXML
    private Button logoffbtn;

    @FXML
    private Button managein_nav_btn;

    @FXML
    private Button sales_nav_btn;

    @FXML
    private void initialize() {
        addbook_nav_btn.setOnAction(event -> navigateTo(event, "addbook.fxml"));
        sales_nav_btn.setOnAction(event -> navigateTo(event, "sales.fxml"));
        managein_nav_btn.setOnAction(event -> navigateTo(event, "inventory.fxml"));
        logoffbtn.setOnAction(event -> navigateTo(event, "hello-view.fxml"));
    }

    private void navigateTo(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlFile)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

