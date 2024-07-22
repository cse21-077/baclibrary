package com.example.bookstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class BookController {

    @FXML
    private ListView<Book> availablelist;

    @FXML
    private Button searchnav;

    @FXML
    private Button purchasehistory_btn;

    @FXML
    private ListView<Book> topsellerslist;

    private FileDatabaseConnection fileDatabaseConnection;
    private int currentUserId; // User ID of the logged-in user

    public BookController() {
        fileDatabaseConnection = new FileDatabaseConnection("books.txt");
    }

    @FXML
    void initialize() {
        loadAvailableBooks();
        loadTopSellers();

        searchnav.setOnAction(event -> openSearchPage());

        purchasehistory_btn.setOnAction(event -> openPurchaseHistory());

        availablelist.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Book selectedBook = availablelist.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    openDetailsPage(selectedBook);
                }
            }
        });

        // Add random books for demonstration
        fileDatabaseConnection.addRandomBooks(10);
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
    }

    private void loadAvailableBooks() {
        ObservableList<Book> availableBooks = fileDatabaseConnection.readBooks();
        availablelist.setItems(availableBooks);
    }

    private void loadTopSellers() {
        ObservableList<Book> allBooks = fileDatabaseConnection.readBooks();
        ObservableList<Book> topSellers = FXCollections.observableArrayList();
        allBooks.stream()
                .sorted((b1, b2) -> Integer.compare(b2.getQuantity(), b1.getQuantity()))
                .limit(5)
                .forEach(topSellers::add);
        topsellerslist.setItems(topSellers);
    }

    private void openDetailsPage(Book selectedBook) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detailspage.fxml"));
            Parent root = loader.load();

            DetailsController detailsController = loader.getController();
            detailsController.setSelectedBook(selectedBook);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Book Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openSearchPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Search");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openPurchaseHistory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("purchasehistory.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Purchase History");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
