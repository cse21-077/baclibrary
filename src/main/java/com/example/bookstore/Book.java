package com.example.bookstore;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantity;

    // Constructor
    public Book(String isbn, String title, String author, String genre, double price, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString method to display Book information
//    @Override
//    public String toString() {
//        return title + " by " + author + " - Genre: " + genre + ", Price: P" + price + ", Quantity: " + quantity;
//    }

    @Override
    public String toString() {
        return String.format("%s by %s - Genre: %s, Price: P%.2f, Quantity: %d",
                title, author, genre, price, quantity);
    }

}
