package com.example.bookstore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DetailsController {

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label genreLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;

    @FXML
    private Button buyButton;

    private Book selectedBook;
    private FileDatabaseConnection fileDatabaseConnection;
    private int currentUserId; // User ID of the logged-in user

    public DetailsController() {
        fileDatabaseConnection = new FileDatabaseConnection("books.txt"); // Path to the books file
        // Initialize connections if needed
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
        updateLabels();
    }

    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        System.out.println("DetailsController: Current User ID set to: " + userId); // Debugging statement
    }

    private void updateLabels() {
        if (selectedBook != null) {
            titleLabel.setText(selectedBook.getTitle());
            authorLabel.setText(selectedBook.getAuthor());
            genreLabel.setText(selectedBook.getGenre());
            priceLabel.setText(String.valueOf(selectedBook.getPrice()));
            quantityLabel.setText(String.valueOf(selectedBook.getQuantity()));
        }
    }

    @FXML
    void buyBook() {
        System.out.println("Buy button clicked."); // Debugging statement
        if (selectedBook != null) {
            try {
                // Check if the book is available
                if (selectedBook.getQuantity() > 0) {
                    // Decrease the quantity of the book
                    int newQuantity = selectedBook.getQuantity() - 1;
                    selectedBook.setQuantity(newQuantity);

                    // Update the book quantity in the text file
                    updateBookQuantity(selectedBook.getIsbn(), newQuantity);

                    // Add to soldbooks text file
                    addSoldBook(selectedBook);

                    // Update the quantity label
                    quantityLabel.setText(String.valueOf(newQuantity));

                    // Success message
                    System.out.println("Successfully bought book: " + selectedBook.getTitle());
                } else {
                    // Handle case when the book is out of stock
                    System.out.println("Book is out of stock: " + selectedBook.getTitle());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateBookQuantity(String isbn, int quantity) throws IOException {
        String filePath = fileDatabaseConnection.getFilePath();
        StringBuilder fileContent = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6 && parts[0].equals(isbn)) {
                    // Update quantity
                    line = String.join(",", parts[0], parts[1], parts[2], parts[3], parts[4], String.valueOf(quantity));
                }
                fileContent.append(line).append("\n");
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(fileContent.toString());
        }
    }

    private void addSoldBook(Book book) throws IOException {
        String filePath = "soldbooks.txt"; // Path to the soldbooks text file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            String soldEntry = String.join(",",
                    book.getIsbn(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getGenre(),
                    String.valueOf(book.getPrice()),
                    "1", // Assuming 1 book bought at a time
                    String.valueOf(System.currentTimeMillis()) // Using timestamp as milliseconds
            );
            bw.write(soldEntry);
            bw.newLine();
        }
    }
}
