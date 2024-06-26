package com.example.dekstopshopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

/**
 * Klasa odpowiedzialna za uruchomienie panelu zarządzania administratora w aplikacji Desktop Shop.
 * Zarządza inicjalizacją sceny, obsługą zdarzeń myszy oraz konfiguracją okna Stage.
 */
public class DashBoardPanel {
    private double x=0;
    private double y=0;

    /**
     * Metoda uruchamiająca panel zarządzania administratora.
     *
     * @param stage Obiekt Stage, na którym ma być wyświetlony panel.
     * @throws IOException W przypadku problemów z załadowaniem pliku FXML.
     */
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("DashBoard.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        Image icon = new Image("cpu-Icon.png");

        scene.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        scene.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(icon);
        stage.setTitle("Panel Administratora CYBERZONE");
        stage.setScene(scene);
        stage.show();


    }
}
