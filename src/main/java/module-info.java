module com.example.bookstore {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
   // requires mysql.connector.j;


    opens com.example.bookstore to javafx.fxml;
    exports com.example.bookstore;
}