package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddBookController {

    @FXML
    private Button addbook_btn;

    @FXML
    private TextField author_txt;

    @FXML
    private TextField genre_txt;

    @FXML
    private TextField isbn_txt;

    @FXML
    private TextField price_txt;

    @FXML
    private TextField quantity_txt;

    @FXML
    private TextField title_txt;

    // Path to the books.txt file
    private static final String FILE_PATH = "books.txt";

    @FXML
    private void initialize() {
        addbook_btn.setOnAction(event -> addBook());
    }

    private void addBook() {
        // Get the text from the text fields
        String isbn = isbn_txt.getText();
        String title = title_txt.getText();
        String author = author_txt.getText();
        String genre = genre_txt.getText();
        double price;
        int quantity;

        try {
            price = Double.parseDouble(price_txt.getText());
            quantity = Integer.parseInt(quantity_txt.getText());
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Invalid input", "Price and Quantity must be numeric.");
            return;
        }

        // Create a new Book object
        Book book = new Book(isbn, title, author, genre, price, quantity);

        // Write the book to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.format("%s,%s,%s,%s,%.2f,%d%n",
                    book.getIsbn(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPrice(), book.getQuantity()));
            showAlert(AlertType.INFORMATION, "Success", "Book added successfully.");
            clearFields();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(AlertType.ERROR, "File Error", "Could not add book to the file.");
        }
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        isbn_txt.clear();
        title_txt.clear();
        author_txt.clear();
        genre_txt.clear();
        price_txt.clear();
        quantity_txt.clear();
    }
}
