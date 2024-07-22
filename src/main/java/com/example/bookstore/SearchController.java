package com.example.bookstore;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchController {

    @FXML
    private TextArea resultlist;

    @FXML
    private TextField searchbar;

    @FXML
    private Button searchbtn;

    private ObservableList<Book> bookList = FXCollections.observableArrayList();

    public SearchController() {
        loadBooksFromFile();
    }

    @FXML
    void initialize() {
        searchbtn.setOnAction(event -> searchBooks());
    }

    private void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Assuming the format: isbn,title,author,genre,price,quantity
                if (parts.length == 6) {
                    Book book = new Book(
                            parts[0], // ISBN
                            parts[1], // Title
                            parts[2], // Author
                            parts[3], // Genre
                            Double.parseDouble(parts[4]), // Price
                            Integer.parseInt(parts[5]) // Quantity
                    );
                    bookList.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchBooks() {
        String searchTerm = searchbar.getText().trim();
        if (!searchTerm.isEmpty()) {
            // Clear the text area before setting new data
            resultlist.clear();
            for (Book book : bookList) {
                if (containsIgnoreCase(book.getTitle(), searchTerm)) {
                    resultlist.appendText(book.toString() + "\n");
                }
            }
        } else {
            // If search term is empty, clear the text area
            resultlist.clear();
        }
    }

    // Helper method to check if a string contains another string (case-insensitive)
    private boolean containsIgnoreCase(String source, String searchStr) {
        return source.toLowerCase().contains(searchStr.toLowerCase());
    }
}
