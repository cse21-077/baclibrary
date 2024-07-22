package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SalesController {

    @FXML
    private Button back_btn;

    @FXML
    private ListView<String> soldlist;

    @FXML
    private TextField total_txt;

    private FileDatabaseConnection fileDatabaseConnection;

    public SalesController() {
        // Initialize with the correct file path for sold books
        fileDatabaseConnection = new FileDatabaseConnection("soldbooks.txt");
    }

    @FXML
    private void initialize() {
        loadSoldItems();
        calculateAndDisplayTotalRevenue();
        back_btn.setOnAction(this::handleBackButtonAction);
    }

    private void loadSoldItems() {
        ObservableList<String> soldItems = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(fileDatabaseConnection.getFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // Update to 6 fields based on file format
                    String item = parts[1] + " by " + parts[2] + // Title and Author
                            " - Genre: " + parts[3] + // Genre
                            ", Price: P" + parts[4] + // Price
                            ", Quantity: " + parts[5]; // Quantity
                    soldItems.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        soldlist.setItems(soldItems);
    }

    private void calculateAndDisplayTotalRevenue() {
        double totalRevenue = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileDatabaseConnection.getFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) { // Update to 6 fields based on file format
                    double price = Double.parseDouble(parts[4]); // Price
                    int quantity = Integer.parseInt(parts[5]); // Quantity
                    totalRevenue += price * quantity;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        total_txt.setText(String.format("%.2f", totalRevenue));
    }

    private void handleBackButtonAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
