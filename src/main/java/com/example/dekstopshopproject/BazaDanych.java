package com.example.dekstopshopproject;

import javafx.scene.control.Alert;

import java.sql.*;

/**
 * Klasa BazaDanych zawiera metodę do nawiązywania połączenia z bazą danych.
 */
public class BazaDanych {
    /**
     * Metoda connectToDb służy do nawiązywania połączenia z bazą danych.
     *
     * @return Połączenie z bazą danych (obiekt typu Connection) lub null, jeśli nie udało się nawiązać połączenia.
     */
    public static Connection connectToDb(){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sklep","root","Password123!");
        }catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("BRAK POLACZENIA Z BAZA DANYCH");
            alert.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
