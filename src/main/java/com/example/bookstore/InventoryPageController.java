package com.example.bookstore;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

public class InventoryPageController {

    @FXML
    private ListView<Book> inventoryList;

    @FXML
    private Button editBtn;

    @FXML
    private Button deleteBtn;

    private FileDatabaseConnection fileDatabaseConnection;

    public InventoryPageController() {
        fileDatabaseConnection = new FileDatabaseConnection("books.txt");
    }

    @FXML
    public void initialize() {
        // Load inventory data on initialization
        loadInventory();
    }

    // Method to load inventory data into the ListView
    private void loadInventory() {
        inventoryList.getItems().clear(); // Clear the list before loading new data
        // Fetch all books from the file
        List<Book> books = fileDatabaseConnection.readBooks();
        inventoryList.getItems().addAll(books); // Add each book to the list
    }

    // Method to handle editing a book
    @FXML
    private void editBook() {
        Book selectedBook = inventoryList.getSelectionModel().getSelectedItem();
        // Implement editing functionality (e.g., open a new window for editing)
        System.out.println("Edit book: " + selectedBook);
    }

    // Method to handle deleting a book
    @FXML
    private void deleteBook() {
        Book selectedBook = inventoryList.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            List<Book> books = fileDatabaseConnection.readBooks();
            books.removeIf(book -> book.getIsbn().equals(selectedBook.getIsbn()));
            fileDatabaseConnection.writeBooks(books);
            // Reload inventory after deletion
            loadInventory();
        }
    }
}
