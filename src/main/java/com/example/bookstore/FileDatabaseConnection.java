package com.example.bookstore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.List;
import java.util.Random;

public class FileDatabaseConnection {

    private String filePath;

    public FileDatabaseConnection(String filePath) {
        this.filePath = filePath;
    }


    public String getFilePath() {
        return filePath;
    }

    public ObservableList<Book> readBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Book book = new Book(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]), Integer.parseInt(parts[5]));
                    books.add(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void writeBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                bw.write(book.getIsbn() + "," + book.getTitle() + "," + book.getAuthor() + "," + book.getGenre() + "," + book.getPrice() + "," + book.getQuantity());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRandomBooks(int count) {
        Random random = new Random();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            for (int i = 0; i < count; i++) {
                String isbn = "ISBN" + random.nextInt(100000);
                String title = "Title" + random.nextInt(100);
                String author = "Author" + random.nextInt(100);
                String genre = "Genre" + random.nextInt(10);
                double price = random.nextDouble() * 100;
                int quantity = random.nextInt(100);
                bw.write(isbn + "," + title + "," + author + "," + genre + "," + price + "," + quantity);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
