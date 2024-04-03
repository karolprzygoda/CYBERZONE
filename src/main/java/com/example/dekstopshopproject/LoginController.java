package com.example.dekstopshopproject;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Date;

/**
 * Kontroler interfejsu logowania i rejestracji konta
 */
public class LoginController {

    private static final Logger logger = LogManager.getLogger(LoginController.class);
    @FXML
    private Button closeBtn, minBtn, loginBtn;
    @FXML
    private TextField mailLabel;
    @FXML
    private PasswordField passwordLabel;

    @FXML
    private Button RegisterBtn;

    @FXML
    private TextField register_imieTextField;

    @FXML
    private Button register_loginBtn;

    @FXML
    private TextField register_mailLabel;

    @FXML
    private TextField register_nazwiskoTextField;

    @FXML
    private TextField register_nrTelefonuTextField;

    @FXML
    private PasswordField register_passwordLabel;

    @FXML
    private AnchorPane sidePane;

    /**
     * przechowuje imie administratora który się zalogował
     */
    public static String adminName;
    /**
     * przechowuje ID administratora który się zalogował
     */
    public static Integer adminID;

    /**
     * przechowuje imie uzytkownika który się zalogował
     */
    public static String clientName;

    /**
     * przechowuje id uzytkownika który się zalogował
     */
    public static Integer clientId;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    /**
     * Metoda odpowiedzialna za przełączanie formularzy rejestracji i logowania.
     *
     * Metoda `switchForm()` obsługuje zdarzenie akcji, które występuje po kliknięciu
     * przycisku rejestracji lub przycisku powrotu do logowania. Metoda animuje przesunięcie
     * formularza na bocznym panelu w celu wyświetlenia odpowiedniego formularza. Przesunięcie
     * wykonywane jest za pomocą efektu TranslateTransition.
     *
     * @param event Zdarzenie akcji wywołane przez kliknięcie przycisku rejestracji lub powrotu do logowania.
     */
    public void switchForm(ActionEvent event){

        TranslateTransition slider = new TranslateTransition();

        if(event.getSource() == RegisterBtn){
            slider.setNode(sidePane);
            slider.setToX(450);
            slider.setDuration(Duration.seconds(.5));
            slider.play();
        }else if(event.getSource() == register_loginBtn){
            slider.setNode(sidePane);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));
            slider.play();
        }
    }

    /**
     * Metoda obsługująca proces logowania administratora lub użytkownika.
     *
     * Metoda `adminLogin()` jest odpowiedzialna za weryfikację danych logowania
     * administratora lub użytkownika w bazie danych. Na podstawie wprowadzonych
     * danych, metoda sprawdza, czy istnieje dopasowanie w tabeli administrator
     * lub w tabeli uzytkownik. Jeśli dopasowanie zostanie znalezione, otwierane
     * są odpowiednie panele: Panel Administratora (DashBoardPanel) lub Panel
     * Użytkownika (ClientDashBoardPanel).
     * Po zalogowaniu, okno logowania zostaje ukryte, a odpowiedni panel zostaje
     * wyświetlony.
     * Informacje o zalogowanym administratorze lub użytkowniku są zapisywane
     * w zmiennych globalnych adminName, adminID, clientName, clientId, które
     * mogą być wykorzystane w innych częściach aplikacji.
     *
     * @throws Exception W przypadku wystąpienia błędu podczas przetwarzania danych logowania.
     */
    public void adminLogin(){
        String sql = "SELECT * FROM administrator WHERE adres_mailowy = '" + mailLabel.getText() +"' AND haslo = '" + passwordLabel.getText() + "'";
        String sql2 = "SELECT * FROM uzytkownik WHERE mail = '" + mailLabel.getText() +"' AND haslo = '" + passwordLabel.getText() + "'";
        connect = BazaDanych.connectToDb();
        try {
            Alert alert;
            if(mailLabel.getText().isEmpty() || passwordLabel.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Proszę uzupełnić wszystkie puste pola");
                alert.showAndWait();
            }else{
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();
                if(result.next()){
                    loginBtn.getScene().getWindow().hide();
                    DashBoardPanel dashBoardPanel = new DashBoardPanel();
                    Stage stage = new Stage();
                    adminName = result.getString("imie");
                    adminID = result.getInt("id_admin");
                    logger.info("Administrotr o ID: " + adminID + " zalogowal sie");
                    dashBoardPanel.start(stage);
                }else {
                    prepare = connect.prepareStatement(sql2);
                    result = prepare.executeQuery();

                    if(result.next())
                    {
                        loginBtn.getScene().getWindow().hide();
                        ClientDashBoardPanel clientDashBoardPanel = new ClientDashBoardPanel();
                        Stage stage = new Stage();
                        clientName = result.getString("imie");
                        clientId = result.getInt("id_uzytkownika");
                        logger.info("Uzytkownik o ID: " + clientId + " zalogowal sie");
                        clientDashBoardPanel.start(stage);
                    }else {

                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Wprowadzono błędne dane logowania");
                        mailLabel.clear();
                        passwordLabel.clear();
                        alert.showAndWait();
                    }
                }
            }

        }catch (Exception e){e.printStackTrace();}
    }

    /**
     * Metoda obsługująca proces rejestracji użytkownika.
     *
     * Metoda `register()` jest odpowiedzialna za weryfikację i zapisanie danych
     * nowo rejestrującego się użytkownika do bazy danych. Metoda sprawdza, czy
     * wszystkie wymagane pola rejestracyjne zostały uzupełnione. Następnie
     * sprawdzane jest, czy podany adres e-mail i numer telefonu są już zarejestrowane
     * w bazie danych dla użytkowników lub administratorów. Jeśli dane są unikalne,
     * następuje zapisanie nowego użytkownika do tabeli uzytkownik wraz z datą rejestracji.
     * Dodatkowo, tworzone są rekordy w tabelach koszyk i lista_zyczen dla nowo zarejestrowanego
     * użytkownika. Po pomyślnym zarejestrowaniu, pola formularza są czyszczone, a
     * wyświetlany jest komunikat informacyjny.
     *
     * @throws MysqlDataTruncation W przypadku przekroczenia dozwolonej liczby znaków przy zapisywaniu danych do bazy.
     * @throws Exception W przypadku wystąpienia innych błędów podczas przetwarzania danych rejestracyjnych.
     */
    public void register(){
        String sql = "INSERT INTO uzytkownik (imie, nazwisko,nr_telefonu, mail, haslo, data_rejestracji) VALUES(?,?,?,?,?,?)";

        connect = BazaDanych.connectToDb();

        try{

            Alert alert;

            if(register_imieTextField.getText().isEmpty()
                    || register_nazwiskoTextField.getText().isEmpty()
                    || register_mailLabel.getText().isEmpty()
                    || register_passwordLabel.getText().isEmpty()
                    || register_nrTelefonuTextField.getText().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze uzupełnić wszystkie puste pola");
                alert.showAndWait();
            }else {

                String checkDataMail = "SELECT mail FROM uzytkownik WHERE mail = '" + register_mailLabel.getText()+"'";
                String checkDataTel = "SELECT nr_telefonu FROM uzytkownik WHERE nr_telefonu = '" + register_nrTelefonuTextField.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkDataMail);

                if(result.next() || statement.executeQuery("SELECT adres_mailowy FROM administrator WHERE adres_mailowy = '" + register_mailLabel.getText()+"'").next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Adres Mailowy: " + register_mailLabel.getText() + " już jest zarejestrowany");
                    alert.showAndWait();
                }
                else if(statement.executeQuery(checkDataTel).next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Numer Telefonu: " + register_nrTelefonuTextField.getText() + " już jest zarejestrowany");
                    alert.showAndWait();
                }
                else {

                    Date currentDate = new Date();

                    java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, register_imieTextField.getText());
                    prepare.setString(2, register_nazwiskoTextField.getText());
                    prepare.setString(3, register_nrTelefonuTextField.getText());
                    prepare.setString(4, register_mailLabel.getText());
                    prepare.setString(5, register_passwordLabel.getText());
                    prepare.setDate(6,sqlDate);

                    prepare.executeUpdate();

                    sql = "INSERT INTO koszyk (id_uzytkownika) VALUES (LAST_INSERT_ID())";
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    sql = "INSERT INTO lista_zyczen (id_uzytkownika) VALUES (LAST_INSERT_ID())";
                    prepare = connect.prepareStatement(sql);
                    prepare.executeUpdate();

                    logger.info("Zarejestrowal sie nowy uzytkownik o adresie mailowym: " + register_mailLabel.getText());
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    register_imieTextField.setText("");
                    register_nazwiskoTextField.setText("");
                    register_nrTelefonuTextField.setText("");
                    register_mailLabel.setText("");
                    register_passwordLabel.setText("");
                    alert.setContentText("Zarejestrowano pomyślnie");
                    alert.showAndWait();

                }
            }

        }catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        }catch (Exception e){
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();}
    }

    /**
     * Zamyka aplikację i kończy jej działanie.
     *
     * Metoda jest wywoływana po kliknięciu przycisku "closeBtn". Zamyka okno aplikacji oraz kończy jej działanie. W logach
     * zostaje zarejestrowane zdarzenie zamknięcia aplikacji. Metoda uzyskuje dostęp do obiektu Stage, reprezentującego
     * aktualne okno aplikacji, za pomocą metody `getScene().getWindow()`. Następnie wywołuje metodę `close()` na tym obiekcie,
     * powodując zamknięcie okna i zakończenie działania aplikacji.
     */
    @FXML
    public void close(){
        logger.info("Aplikacja zostala zamknieta");
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Minimalizuje okno aplikacji.
     *
     * Metoda jest wywoływana po kliknięciu przycisku "minBtn". Minimalizuje aktualne okno aplikacji, zmniejszając je do ikony
     * na pasku zadań. Metoda uzyskuje dostęp do obiektu Stage, reprezentującego aktualne okno aplikacji, za pomocą metody
     * `getScene().getWindow()`. Następnie wywołuje metodę `setIconified(true)` na tym obiekcie, powodując minimalizację okna.
     */
    @FXML
    public void minimize() {
            Stage stage = (Stage) minBtn.getScene().getWindow();
            stage.setIconified(true);
    }


}