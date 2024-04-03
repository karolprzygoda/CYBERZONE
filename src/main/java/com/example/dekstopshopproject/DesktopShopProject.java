package com.example.dekstopshopproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Główna klasa aplikacji DesktopShopProject.
 *
 * Klasa `DesktopShopProject` jest klasą główną aplikacji. Implementuje interfejs
 * `Application` i zawiera metodę `main()`, która uruchamia aplikację. Klasa
 * inicjalizuje scenę i okno aplikacji, wczytuje plik FXML reprezentujący widok
 * logowania, definiuje zachowanie dla przeciągania okna po ekranie oraz wyświetla
 * okno aplikacji.
 */
public class DesktopShopProject extends Application {
    private static final Logger logger = LogManager.getLogger(DesktopShopProject.class);
    private double x = 0;
    private double y = 0;

    /**
     * Metoda start.
     *
     * Metoda `start()` jest główną metodą aplikacji. Inicjalizuje scenę i okno aplikacji,
     * wczytuje plik FXML reprezentujący widok logowania, ustawia przezroczystość sceny
     * oraz definiuje zachowanie dla przeciągania okna po ekranie. Po zakończeniu
     * konfiguracji, wyświetla okno aplikacji.
     *
     * @param stage Referencja do obiektu klasy Stage, reprezentującego okno aplikacji.
     * @throws IOException Wyjątek, który może zostać zgłoszony w przypadku błędu
     *                     podczas wczytywania pliku FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DesktopShopProject.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 650);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);

        Image icon = new Image("cpu-Icon.png");

        scene.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        scene.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.getIcons().add(icon);
        stage.setTitle("CYBERZONE");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda main.
     *
     * Główna metoda aplikacji, która uruchamia aplikację DesktopShopProject.
     * Wywołuje metodę `launch()`, która inicjalizuje i uruchamia aplikację JavaFX.
     *
     * @param args Argumenty wiersza poleceń przekazane do metody main.
     */
    public static void main(String[] args) {
        logger.info("Uruchomiono aplikację");
        launch();
    }
}