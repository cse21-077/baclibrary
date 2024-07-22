package com.example.bookstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PurchaseHistoryController {

    @FXML
    private ListView<String> historylist;

    private FileDatabaseConnection fileDatabaseConnection;

    public PurchaseHistoryController() {
        fileDatabaseConnection = new FileDatabaseConnection("soldbooks.txt");
    }

    @FXML
    private void initialize() {
        loadPurchaseHistory();
    }

    private void loadPurchaseHistory() {
        ObservableList<String> purchaseHistory = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(fileDatabaseConnection.getFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the file format is similar to:
                // isbn,title,author,genre,price,quantity,sold_date
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String item = parts[1] + " by " + parts[2] +
                            " - Genre: " + parts[3] +
                            ", Price: $" + parts[4] +
                            ", Quantity: " + parts[5] +
                            ", Date: " + parts[6];
                    purchaseHistory.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        historylist.setItems(purchaseHistory);
    }
}
