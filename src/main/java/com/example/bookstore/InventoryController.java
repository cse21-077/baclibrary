package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class InventoryController {

    @FXML
    private Button back_btn;

    @FXML
    private ListView<String> inventorylist;

    private FileDatabaseConnection fileDatabaseConnection;

    public InventoryController() {
        fileDatabaseConnection = new FileDatabaseConnection("books.txt");
    }

    @FXML
    private void initialize() {
        loadInventory();
        back_btn.setOnAction(this::handleBackButtonAction);
    }

    private void loadInventory() {
        ObservableList<String> inventoryItems = FXCollections.observableArrayList();

        List<Book> books = fileDatabaseConnection.readBooks();
        for (Book book : books) {
            if (book.getQuantity() > 0) {
                String item = book.getTitle() + " by " + book.getAuthor() +
                        " - Genre: " + book.getGenre() +
                        ", Price: $" + book.getPrice() +
                        ", Quantity: " + book.getQuantity();
                inventoryItems.add(item);
            }
        }

        inventorylist.setItems(inventoryItems);
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
