package com.example.dekstopshopproject;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;

import static com.example.dekstopshopproject.LoginController.adminID;

/**
 * Kontroler interfejsu administratora
 */
public class DashBoardController implements Initializable {

    private static final Logger logger = LogManager.getLogger(DashBoardController.class);

    @FXML
    private Button closeBtn, minBtn, logOutBtn, homeBtn, itemBtn, customerBtn, orderBtn, employeBtn, discountBtn;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField produkty_nazwaProduktuLabel, produkty_nazwaProducentaLabel, produkty_cenaLabel, produkty_QuantityLabel;
    @FXML
    private TextField produkty_searchField;
    @FXML
    private AnchorPane homePane, itemsPane, customerPane, orderPane, employePane, discountPane;

    @FXML
    private TableView<itemData> produkty_ProduktyTableView;
    @FXML
    private TableColumn<itemData, String> produkty_idProdutku;

    @FXML
    private TableColumn<itemData, String> produkty_Cena;

    @FXML
    private TableColumn<itemData, String> produkty_DataProdukcji;

    @FXML
    private TableColumn<itemData, String> produkty_Dostepnosc;

    @FXML
    private TableColumn<itemData, String> produkty_Kategoria;

    @FXML
    private TableColumn<itemData, String> produkty_NazwaProduktu;

    @FXML
    private TableColumn<itemData, String> produkty_Producent;

    @FXML
    private ComboBox<String> produkty_kategoriaBox;

    @FXML
    private TableColumn<klienciData, String> klienci_dateColumn;

    @FXML
    private TableColumn<klienciData, String> klienci_idColumn;

    @FXML
    private TableColumn<klienciData, String> klienci_imieCloumn;

    @FXML
    private TextField klienci_imieTex;

    @FXML
    private TableColumn<klienciData, String> klienci_mailColumn;

    @FXML
    private TextField klienci_mailText;

    @FXML
    private TableColumn<klienciData, String> klienci_nazwiskoColumn;

    @FXML
    private TextField klienci_nazwiskoText;

    @FXML
    private TextField klienci_searchField;

    @FXML
    private TableColumn<klienciData, String> klienci_telColumn;

    @FXML
    private TextField klienci_telText;

    @FXML
    private TableView<klienciData> klienci_tableView;
    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_dataZlozeniaColumn;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<itemData> addItemsList = FXCollections.observableArrayList();
    private ObservableList<klienciData> klienciList = FXCollections.observableArrayList();
    private ObservableList<pracownicyData> pracownicyList = FXCollections.observableArrayList();
    private ObservableList<rabatyData> rabatyList = FXCollections.observableArrayList();
    private ObservableList<zamowieniaData> zamowieniaList = FXCollections.observableArrayList();
    private ObservableList<zamowieniaZawartoscData> zamowieniaZawartoscList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<pracownicyData, String> pracownicy_idColumn;

    @FXML
    private TableColumn<pracownicyData, String> pracownicy_imieColumn;

    @FXML
    private TextField pracownicy_imiePracownika;

    @FXML
    private TableColumn<pracownicyData, String> pracownicy_mailColumn;

    @FXML
    private TextField pracownicy_mailPracownika;

    @FXML
    private TableColumn<pracownicyData, String> pracownicy_nazwiskoColumn;

    @FXML
    private TextField pracownicy_nazwiskoPracownika;

    @FXML
    private TextField pracownicy_search;

    @FXML
    private TableColumn<pracownicyData, String> pracownicy_stanowiskoColumn;

    @FXML
    private ComboBox<String> pracownicy_stanowiskoPracownika;

    @FXML
    private TableView<pracownicyData> pracownicy_tableView;

    @FXML
    private TableColumn<pracownicyData, String> pracownicy_telColumn;

    @FXML
    private TextField pracownicy_telPracownika;

    @FXML
    private TableColumn<pracownicyData, String> pracownicy_zarobkiColumn;

    @FXML
    private TextField pracownicy_zarobkiPracownika;

    @FXML
    private TableColumn<rabatyData, String> rabaty_idColumn;

    @FXML
    private TableColumn<rabatyData, String> rabaty_kodColumn;

    @FXML
    private TextField rabaty_kodRabatu;

    @FXML
    private TextField rabaty_search;

    @FXML
    private TableView<rabatyData> rabaty_tableView;

    @FXML
    private TextField rabaty_znizka;

    @FXML
    private TableColumn<rabatyData, String> rabaty_znizkaColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_dataDostawyColumn;
    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_cenaColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_idKlientaColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_idZamowieniaColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_imieColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_kodPocztowyColumn;

    @FXML
    private TextField zamowienia_kodPocztowyTextField;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_mailColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_miastoColumn;

    @FXML
    private TextField zamowienia_miastoTextField;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_nazwiskoColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_nrBudynkuColumn;

    @FXML
    private TextField zamowienia_nrBudynkuTextField;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_nrMieszkaniaColumn;

    @FXML
    private TextField zamowienia_nrMieszkaniaTextField;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_statusColumn;

    @FXML
    private ComboBox<String> zamowienia_statusComboBox;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_telColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_ulicaColumn;
    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_sposobDostawyColumn;

    @FXML
    private TextField zamowienia_ulicaTextField;

    @FXML
    private TextField zamowienia_searchField;

    @FXML
    private TableView<zamowieniaData> zamowienia_tableView;

    @FXML
    private Label customerNumber;

    @FXML
    private Label employeNumber;


    @FXML
    private AreaChart<zamowieniaData, String> incomeChart;


    @FXML
    private Label orderNumber;

    @FXML
    private DatePicker produkty_datePicker, zamowienia_datePicker;


    @FXML
    private TableView<zamowieniaZawartoscData> zamowienia_ZawartoscTableView;

    @FXML
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_cenaProduktu;

    @FXML
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_idProduktu;

    @FXML
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_iloscProduktu;

    @FXML
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_kategoriaProduktu;

    @FXML
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_nazwaProduktu;

    @FXML
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_producentProduktu;

    @FXML
    private TextField zamowienia_szczegolySearchField;


    private String[] kategoriaLista = {"procesor", "karta graficzna", "płyta główna",
            "zasilacz komputerowy", "pamięć RAM", "głośniki",
            "klawiatura", "myszka", "monitor", "dysk HDD",
            "dysk SSD", "drukarka", "obudowa"};
    private String[] stanowiskoLista = {"Dział Obsługi Klienta", "Informatyk", "Koordynator Projektu", "Analityk Systemowy"};
    private String[] statusLista = {"oczekuje na realizacje", "w trakcie pakowania", "gotowe do wysylki", "w drodze", "odebrane", "anulowano", "nie odebrano"};


    /**
     * Metoda homePracownicy() aktualizuje liczbę pracowników wyświetlaną na stronie startowej.
     * Pobiera liczbę pracowników z bazy danych i ustawia ją jako tekst dla odpowiedniego elementu interfejsu.
     */
    public void homePracownicy() {

        String sql = "SELECT COUNT(id_pracownika) FROM pracownicy";

        connect = BazaDanych.connectToDb();
        int Count = 0;


        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                Count = result.getInt("COUNT(id_pracownika)");
            }

            employeNumber.setText(String.valueOf(Count));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metoda homePracownicy() aktualizuje liczbę pracowników wyświetlaną na stronie startowej.
     * Pobiera liczbę pracowników z bazy danych i ustawia ją jako tekst dla odpowiedniego elementu interfejsu.
     */
    public void homeKlienci() {

        String sql = "SELECT COUNT(id_uzytkownika) FROM uzytkownik";

        connect = BazaDanych.connectToDb();
        int Count = 0;


        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                Count = result.getInt("COUNT(id_uzytkownika)");
            }

            customerNumber.setText(String.valueOf(Count));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metoda homeZamowienia() aktualizuje liczbę zamówień wyświetlaną na stronie startowej.
     * Pobiera liczbę zamówień z bazy danych, wykluczając zamówienia o statusie "anulowano",
     * i ustawia ją jako tekst dla odpowiedniego elementu interfejsu.
     */
    public void homeZamowienia() {

        String sql = "SELECT COUNT(id_zamowienia) FROM zamowienia WHERE status_zamowienia != 'anulowano'";

        connect = BazaDanych.connectToDb();
        int Count = 0;


        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                Count = result.getInt("COUNT(id_zamowienia)");
            }

            orderNumber.setText(String.valueOf(Count));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metoda homeChart() generuje wykres przychodów na stronie startowej.
     * Wykres przedstawia dzienny przychód na podstawie danych pobranych z bazy danych.
     * Metoda najpierw czyści dane na wykresie, a następnie wykonuje zapytanie do bazy danych,
     * grupując przychody według daty złożenia zamówienia. Wyniki zapytania są dodawane do serii danych wykresu,
     * a serię danych dodaje się do wykresu przychodów.
     */
    public void homeChart() {

        incomeChart.getData().clear();

        String sql = "SELECT data_zlozenia, SUM(cena_calkowita) FROM zamowienia WHERE status_zamowienia != 'anulowano' GROUP BY data_zlozenia ORDER BY TIMESTAMP(data_zlozenia) ASC";

        connect = BazaDanych.connectToDb();

        try {
            XYChart.Series chart = new XYChart.Series();

            chart.setName("Dzienny przychód");

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data(result.getString(1), result.getDouble(2)));
            }

            incomeChart.getData().add(chart);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Metoda addItemsListData() pobiera dane dotyczące produktów z bazy danych i tworzy listę obiektów itemData,
     * reprezentujących poszczególne produkty. Następnie zwraca tę listę jako obiekt typu ObservableList.
     *
     * @return Lista obiektów itemData zawierających dane o produktach.
     */
    public ObservableList<itemData> addItemsListData() {
        ObservableList<itemData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM produkty";

        connect = BazaDanych.connectToDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            itemData itemD;

            while (result.next()) {
                itemD = new itemData(result.getInt("id_produktu")
                        , result.getString("nazwa")
                        , result.getString("nazwa_producenta")
                        , result.getDouble("cena")
                        , result.getInt("liczba_dostepnych")
                        , result.getString("nazwa_kategorii")
                        , result.getDate("data_wyprodukowania"));

                listData.add(itemD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Metoda produktyDelete() służy do usuwania produktu z bazy danych oraz powiązanych z nim informacji
     * (takich jak zawartość koszyków czy lista życzeń). Metoda pobiera wybrany produkt z tabeli, wyświetla
     * potwierdzenie usunięcia i po potwierdzeniu usuwa produkt oraz związane z nim dane. Następnie aktualizuje
     * wyświetlaną listę produktów i czyści pola formularza. Jeśli nie zostanie wybrany żaden produkt, wyświetla
     * odpowiedni komunikat błędu.
     */
    public void produktyDelete() {

        itemData itemD = produkty_ProduktyTableView.getSelectionModel().getSelectedItem();

        try {
            String deleteFromProducts = "DELETE FROM produkty WHERE id_produktu = '" + itemD.getItemId() + "'";
            String deleteFromCarts = "DELETE FROM zawartosci_koszykow WHERE id_produktu = '" + itemD.getItemId() + "'";
            String deleteFromWishList = "DELETE FROM lista_przedmiotow WHERE id_produktu = '" + itemD.getItemId() + "'";
            connect = BazaDanych.connectToDb();
            Alert alert;

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);

            alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ produkt o ID: " + itemD.getItemId() + " ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                logger.info("Administrotr o ID: " + adminID + " usunal produkt: " + itemD.getNazwaProduktu() + " ze sklepu");
                statement = connect.createStatement();
                statement.executeUpdate(deleteFromProducts);
                statement.executeUpdate(deleteFromCarts);
                statement.executeUpdate(deleteFromWishList);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Usunięto pomyślnie");
                alert.showAndWait();
                addItemsShowListData();
                produktyClear();
            }

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć produkt który chcesz USUNĄĆ !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }

    }

    /**
     * Metoda produktyUpdate() służy do aktualizacji informacji o wybranym produkcie w bazie danych.
     * Metoda pobiera wybrany produkt z tabeli, pobiera wprowadzone wartości z pól formularza i generuje
     * odpowiednie zapytania SQL do aktualizacji danych w tabeli "produkty". Po potwierdzeniu aktualizacji
     * przez użytkownika, metoda wykonuje poszczególne zapytania, loguje zmiany, wyświetla powiadomienie
     * o pomyślnym zaktualizowaniu danych, aktualizuje wyświetlaną listę produktów i czyści pola formularza.
     * Jeśli nie zostanie wybrany żaden produkt lub nie zostaną wprowadzone żadne zmiany, wyświetla
     * odpowiedni komunikat błędu.
     * W przypadku wystąpienia błędów podczas operacji na bazie danych, metoda wyświetla odpowiednie
     * komunikaty błędu.
     */
    public void produktyUpdate() {

        itemData itemD = produkty_ProduktyTableView.getSelectionModel().getSelectedItem();
        try {
            String nazwaUpdate = "UPDATE produkty SET nazwa = '" + produkty_nazwaProduktuLabel.getText() + "'WHERE id_produktu = '" + itemD.getItemId() + "'";
            String producentUpdate = "UPDATE produkty SET nazwa_producenta = '" + produkty_nazwaProducentaLabel.getText() + "'WHERE id_produktu = '" + itemD.getItemId() + "'";
            String cenaUpdate = "UPDATE produkty SET cena = '" + produkty_cenaLabel.getText() + "'WHERE id_produktu = '" + itemD.getItemId() + "'";
            String dostepnosctUpdate = "UPDATE produkty SET liczba_dostepnych = '" + produkty_QuantityLabel.getText() + "'WHERE id_produktu = '" + itemD.getItemId() + "'";
            String kategoriaUpdate = "UPDATE produkty SET nazwa_kategorii = '" + produkty_kategoriaBox.getSelectionModel().getSelectedItem() + "'WHERE id_produktu = '" + itemD.getItemId() + "'";
            String dataProdukcjiUpdate = "UPDATE produkty SET data_wyprodukowania = '" + produkty_datePicker.getValue() + "'WHERE id_produktu = '" + itemD.getItemId() + "'";

            connect = BazaDanych.connectToDb();
            Alert alert;

            if (produkty_nazwaProduktuLabel.getText().isEmpty()
                    && produkty_nazwaProducentaLabel.getText().isEmpty()
                    && produkty_cenaLabel.getText().isEmpty()
                    && produkty_QuantityLabel.getText().isEmpty()
                    && produkty_QuantityLabel.getText().isEmpty()
                    && (produkty_datePicker.getValue() == null)
                    && produkty_kategoriaBox.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze uzupełnić wszystkie puste pola");
                alert.showAndWait();
            } else {

                String checkDataMail = "SELECT nazwa FROM produkty WHERE nazwa = '" + produkty_nazwaProduktuLabel.getText() + "' AND id_produktu != '" + itemD.getItemId() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkDataMail);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Nazwa Produktu: " + produkty_nazwaProduktuLabel.getText() + " już jest zarejestrowana");
                    alert.showAndWait();
                } else {
                    boolean nazwaUpdated = false;
                    boolean producentUpdated = false;
                    boolean cenaUpdated = false;
                    boolean dostepnoscUpdated = false;
                    boolean kategoriaUpdated = false;
                    boolean dataUpdated = false;


                    if (!produkty_nazwaProduktuLabel.getText().isEmpty()) {
                        nazwaUpdated = true;
                    } else {
                        nazwaUpdated = false;
                    }

                    if (!produkty_nazwaProducentaLabel.getText().isEmpty()) {
                        producentUpdated = true;
                    } else {
                        producentUpdated = false;
                    }

                    if (!produkty_cenaLabel.getText().isEmpty()) {
                        cenaUpdated = true;
                    } else {
                        cenaUpdated = false;
                    }

                    if (!produkty_QuantityLabel.getText().isEmpty()) {
                        dostepnoscUpdated = true;
                    } else {
                        dostepnoscUpdated = false;
                    }

                    if (!(produkty_datePicker.getValue() == null)) {
                        dataUpdated = true;
                    } else {
                        dataUpdated = false;
                    }

                    if (!produkty_kategoriaBox.getSelectionModel().isEmpty()) {
                        kategoriaUpdated = true;
                    } else {
                        kategoriaUpdated = false;
                    }

                    boolean anyFieldUpdated = nazwaUpdated || producentUpdated || cenaUpdated || dostepnoscUpdated || dataUpdated || kategoriaUpdated;

                    if (anyFieldUpdated) {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Jestes pewnien ze chcesz ZAKTUALIZOWAC produkt o ID: " + itemD.getItemId() + " ?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {

                            if (nazwaUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizował nazwe produtu o ID: " +itemD.getItemId() + " z nazwy: " + itemD.getNazwaProduktu() + " na nazwe: " + produkty_nazwaProduktuLabel.getText());
                                statement.executeUpdate(nazwaUpdate);
                            }

                            if (producentUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizował nazwe producenta produktu o ID: " +itemD.getItemId() + " z nazwy: " + itemD.getNazwaProducenta() + " na nazwe: " + produkty_nazwaProducentaLabel.getText());
                                statement.executeUpdate(producentUpdate);
                            }

                            if (cenaUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizował cene produtu o ID: " +itemD.getItemId() + " z ceny: " + itemD.getCenaProduktu() + " zł na cene: " + produkty_cenaLabel.getText() + " zł");
                                statement.executeUpdate(cenaUpdate);
                            }

                            if (dostepnoscUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizował liczbe dostepnych sztuk produtu o ID: " +itemD.getItemId() + " z liczby: " + itemD.getDostepnoscProdutku() + " na liczbe: " + produkty_QuantityLabel.getText());
                                statement.executeUpdate(dostepnosctUpdate);
                            }


                            if (dataUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizował date produkcji produtu o ID: " +itemD.getItemId() + " z daty: " + itemD.getDataWyprodukowania() + " na date: " + produkty_datePicker.getValue());
                                statement.executeUpdate(dataProdukcjiUpdate);
                            }

                            if (kategoriaUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizował kategorie produtu o ID: " +itemD.getItemId() + " z kategori: " + itemD.getNazwaKategori() + " na kategorie: " + produkty_kategoriaBox.getSelectionModel().getSelectedItem());
                                statement.executeUpdate(kategoriaUpdate);
                            }


                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Zaktualizowano pomyślnie");
                            alert.showAndWait();
                            addItemsShowListData();
                            produktyClear();
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć produkt który chcesz zaktualizować !");
            alert.showAndWait();
        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Dodaje nowy produkt do bazy danych.
     * Metoda ta wykonuje operację dodawania nowego rekordu do tabeli "produkty" w bazie danych.
     * Sprawdza, czy wszystkie wymagane pola zostały uzupełnione poprawnie.
     * Jeśli nie, wyświetla odpowiednie komunikaty o błędzie.
     * Jeśli wszystkie dane są poprawne, dodaje nowy produkt do bazy danych i wyświetla komunikat potwierdzający dodanie.
     * Na koniec odświeża listę produktów i czyści pola wprowadzania.
     *
     * @throws MysqlDataTruncation Jeśli wprowadzone dane przekraczają dozwoloną liczbę znaków w bazie danych.
     * @throws SQLException        Jeśli wprowadzony format danych jest nieprawidłowy dla kolumn w bazie danych.
     * @throws Exception           Jeśli wystąpił nieprzewidziany wyjątek.
     */
    public void produktyAdd() {
        String sql = "INSERT INTO produkty (nazwa, nazwa_producenta, cena,liczba_dostepnych, nazwa_kategorii,data_wyprodukowania) VALUES(?,?,?,?,?,?)";

        connect = BazaDanych.connectToDb();

        try {

            Alert alert;

            if (produkty_nazwaProduktuLabel.getText().isEmpty()
                    || produkty_nazwaProducentaLabel.getText().isEmpty()
                    || produkty_cenaLabel.getText().isEmpty()
                    || produkty_cenaLabel.getText().isEmpty()
                    || produkty_QuantityLabel.getText().isEmpty()
                    || (produkty_datePicker.getValue() == null)
                    || produkty_kategoriaBox.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze uzupełnić wszystkie puste pola z wyjątkiem pola ID produktu");
                alert.showAndWait();
            } else {

                String checkData = "SELECT nazwa FROM produkty WHERE nazwa = '" + produkty_nazwaProduktuLabel.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkData);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Nazwa produktu: " + produkty_nazwaProduktuLabel.getText() + " jest już zarejestrowana");
                    alert.showAndWait();
                } else {

                    LocalDate localDate = produkty_datePicker.getValue();

                    java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

                    prepare = connect.prepareStatement(sql);

                    prepare.setString(1, produkty_nazwaProduktuLabel.getText());
                    prepare.setString(2, produkty_nazwaProducentaLabel.getText());
                    prepare.setString(3, produkty_cenaLabel.getText());
                    prepare.setString(4, produkty_QuantityLabel.getText());
                    prepare.setString(5, (String) produkty_kategoriaBox.getSelectionModel().getSelectedItem());
                    prepare.setDate(6, sqlDate);



                    prepare.executeUpdate();
                    logger.info("Administrotr o ID: " + adminID + " dodał nowy produkt do sklepu o nazwie: " + produkty_nazwaProduktuLabel.getText());

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodano pomyślnie");
                    alert.showAndWait();

                    addItemsShowListData();
                    produktyClear();
                }
            }

        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        }catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadzono nieprawidłowy format !");
            alert.showAndWait();
        }catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Czyści pola wprowadzania danych dla nowego produktu.
     * Metoda ta resetuje wartości pól tekstowych i kontrolki wyboru kategorii produktu do ich wartości domyślnych.
     * Wykorzystywana jest głównie po dodaniu produktu lub w przypadku anulowania operacji dodawania produktu.
     * Po wywołaniu metody, wszystkie pola tekstowe zostaną wyczyszczone, kontrolka wyboru kategorii zostanie zresetowana,
     * a kontrolka wyboru daty zostanie wyłączona.
     */
    public void produktyClear() {

        produkty_nazwaProduktuLabel.setText("");
        produkty_nazwaProducentaLabel.setText("");
        produkty_datePicker.setValue(null);
        produkty_QuantityLabel.setText("");
        produkty_cenaLabel.setText("");
        produkty_kategoriaBox.getSelectionModel().clearSelection();
    }

    /**
     * Ustawia listę kategorii produktów w kontrolce wyboru kategorii.
     * Metoda ta pobiera listę kategorii produktów i ustawia je jako elementy do wyboru w kontrolce wyboru kategorii.
     * W celu ustawienia elementów, lista kategorii jest konwertowana na obiekt typu ObservableList,
     * który jest odpowiedni do wykorzystania w kontrolce wyboru w bibliotece JavaFX.
     * Po wywołaniu metody, kontrolka wyboru kategorii zostanie zaktualizowana z nowymi elementami do wyboru.
     */
    public void produktyKategoria() {
        List<String> listS = new ArrayList<>();

        for (String data : kategoriaLista) {
            listS.add(data);
        }

        ObservableList listD = FXCollections.observableArrayList(listS);
        produkty_kategoriaBox.setItems(listD);
    }

    /**
     * Aktualizuje wyświetlaną listę produktów w tabeli.
     * Metoda ta pobiera dane dotyczące produktów z metody addItemsListData(),
     * a następnie ustawia odpowiednie wartości dla kolumn w tabeli wykorzystując PropertyValueFactory.
     * Kolumny tabeli są skonfigurowane do wyświetlania odpowiednich właściwości obiektów produktów.
     * Na koniec metoda ustawia zaktualizowaną listę produktów jako dane źródłowe dla tabeli.
     * Po wywołaniu tej metody, tabela zostanie zaktualizowana z nowymi danymi produktów do wyświetlenia.
     */
    public void addItemsShowListData() {
        addItemsList = addItemsListData();
        produkty_idProdutku.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        produkty_NazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaProduktu"));
        produkty_Producent.setCellValueFactory(new PropertyValueFactory<>("nazwaProducenta"));
        produkty_Cena.setCellValueFactory(cellData -> {
            Double cena = cellData.getValue().getCenaProduktu();
            String cenaZl = cena + " zł";
            return new SimpleStringProperty(cenaZl);
        });
        produkty_Dostepnosc.setCellValueFactory(new PropertyValueFactory<>("dostepnoscProdutku"));
        produkty_Kategoria.setCellValueFactory(new PropertyValueFactory<>("nazwaKategori"));
        produkty_DataProdukcji.setCellValueFactory(new PropertyValueFactory<>("dataWyprodukowania"));

        produkty_ProduktyTableView.setItems(addItemsList);

    }

    /**
     * Filtruje wyświetlaną listę produktów w tabeli na podstawie wprowadzonego tekstu wyszukiwania.
     * Metoda ta wykonuje filtrowanie listy produktów na podstawie wprowadzonego tekstu wyszukiwania.
     * Tworzy nowy obiekt typu FilteredList, który inicjalnie zawiera całą listę produktów.
     * Następnie ustawia Predicate dla FilteredList w taki sposób, aby sprawdzał, czy każdy element
     * spełnia kryteria wyszukiwania oparte na różnych właściwościach produktu.
     * Gdy tekst wyszukiwania się zmienia, metoda aktualizuje filtr i odświeża wyświetlaną listę produktów
     * na podstawie pasujących elementów.
     * Metoda ta również korzysta z obiektu SortedList, który umożliwia sortowanie wyników filtrowania
     * w zgodności z komparatorem ustawionym na kontrolce tabeli.
     * Po wywołaniu tej metody, tabela zostanie zaktualizowana z wynikami filtrowania zgodnie z wprowadzonym tekstem wyszukiwania.
     */
    public void produktySearch() {

        FilteredList<itemData> filter = new FilteredList<>(addItemsList, e -> true);

        produkty_searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getItemId().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaProduktu().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaProducenta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getCenaProduktu().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getDostepnoscProdutku().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaKategori().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getDataWyprodukowania().toString().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;

            });
        });

        SortedList<itemData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(produkty_ProduktyTableView.comparatorProperty());
        produkty_ProduktyTableView.setItems(sortedList);
    }

    public void addItemsSelect() {
        itemData itemD = produkty_ProduktyTableView.getSelectionModel().getSelectedItem();
        int num = produkty_ProduktyTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        produkty_nazwaProduktuLabel.setText(String.valueOf(itemD.getNazwaProduktu()));
        produkty_nazwaProducentaLabel.setText(String.valueOf(itemD.getNazwaProducenta()));
        produkty_cenaLabel.setText(String.valueOf(itemD.getCenaProduktu()));
        produkty_QuantityLabel.setText(String.valueOf(itemD.getDostepnoscProdutku()));
        produkty_datePicker.setValue(itemD.getDataWyprodukowania().toLocalDate());

        produkty_kategoriaBox.setValue(itemD.getNazwaKategori());
    }

    /**
     * Ustawia wybrane wartości produktu w polach tekstowych i kontrolkach po wybraniu elementu z tabeli.
     * Metoda ta pobiera zaznaczony element z tabeli produktów oraz jego indeks.
     * Jeśli indeks jest mniejszy od -1, metoda kończy działanie.
     * W przeciwnym razie, metoda ustawia wartości właściwości wybranego produktu
     * w polach tekstowych i kontrolkach odpowiednio: nazwaProduktu, nazwaProducenta, cenaProduktu,
     * dostepnoscProdutku, dataWyprodukowania oraz kategoria.
     * Po wywołaniu tej metody, pola tekstowe i kontrolki zostaną wypełnione danymi wybranego produktu.
     */
    public ObservableList<klienciData> klienciListData() {
        ObservableList<klienciData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM uzytkownik";

        connect = BazaDanych.connectToDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            klienciData klienciD;

            while (result.next()) {
                klienciD = new klienciData(result.getInt("id_uzytkownika")
                        , result.getString("imie")
                        , result.getString("nazwisko")
                        , result.getString("mail")
                        , result.getString("nr_telefonu")
                        , result.getDate("data_rejestracji"));

                listData.add(klienciD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Aktualizuje wyświetlaną listę klientów w tabeli.
     * Metoda ta pobiera dane dotyczące klientów z metody klienciListData(),
     * a następnie ustawia odpowiednie wartości dla kolumn w tabeli wykorzystując PropertyValueFactory.
     * Kolumny tabeli są skonfigurowane do wyświetlania odpowiednich właściwości obiektów klientów.
     * Na koniec metoda ustawia zaktualizowaną listę klientów jako dane źródłowe dla tabeli.
     * Po wywołaniu tej metody, tabela zostanie zaktualizowana z nowymi danymi klientów do wyświetlenia.
     */
    public void klienciShowListData() {
        klienciList = klienciListData();

        klienci_idColumn.setCellValueFactory(new PropertyValueFactory<>("klientId"));
        klienci_imieCloumn.setCellValueFactory(new PropertyValueFactory<>("imieKlienta"));
        klienci_nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("nazwiskoKlienta"));
        klienci_mailColumn.setCellValueFactory(new PropertyValueFactory<>("mailKlienta"));
        klienci_telColumn.setCellValueFactory(new PropertyValueFactory<>("nrTelKlienta"));
        klienci_dateColumn.setCellValueFactory(new PropertyValueFactory<>("dataZalozeniaKonta"));

        klienci_tableView.setItems(klienciList);

    }

    /**
     * Ustawia wybrane wartości klienta w polach tekstowych po wybraniu elementu z tabeli.
     * Metoda ta pobiera zaznaczony element z tabeli klientów oraz jego indeks.
     * Jeśli indeks jest mniejszy od -1, metoda kończy działanie.
     * W przeciwnym razie, metoda ustawia wartości właściwości wybranego klienta
     * w polach tekstowych odpowiednio: imieTex, nazwiskoText, mailText, telText.
     * Po wywołaniu tej metody, pola tekstowe zostaną wypełnione danymi wybranego klienta.
     */
    public void klienciSelect() {
        klienciData klienciD = klienci_tableView.getSelectionModel().getSelectedItem();
        int num = klienci_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        klienci_imieTex.setText(String.valueOf(klienciD.getImieKlienta()));
        klienci_nazwiskoText.setText(String.valueOf(klienciD.getNazwiskoKlienta()));
        klienci_mailText.setText(String.valueOf(klienciD.getMailKlienta()));
        klienci_telText.setText(String.valueOf(klienciD.getNrTelKlienta()));

    }

    /**
     * Czyści zawartość pól tekstowych związanych z danymi klienta.
     * Metoda ta ustawia puste wartości dla pól tekstowych: imieTex, nazwiskoText, mailText, telText.
     * Po wywołaniu tej metody, pola tekstowe zostaną wyczyszczone i nie będą zawierać żadnych danych.
     */
    public void klienciClear() {

        klienci_imieTex.setText("");
        klienci_nazwiskoText.setText("");
        klienci_mailText.setText("");
        klienci_telText.setText("");

    }

    /**
     * Aktualizuje dane klienta na podstawie wprowadzonych zmian w polach tekstowych.
     * Metoda ta wykonuje aktualizację danych klienta na podstawie wprowadzonych zmian w polach tekstowych:
     * imieTex, nazwiskoText, mailText, telText. Wykorzystuje wartości zaznaczonego elementu w tabeli klienci_tableView
     * do identyfikacji klienta, którego dane mają zostać zaktualizowane. Przed wykonaniem aktualizacji, sprawdzane są
     * różne warunki, takie jak puste pola tekstowe, unikalność adresu e-mailowego i numeru telefonu. Po zatwierdzeniu
     * aktualizacji przez użytkownika, dane zostaną zaktualizowane w bazie danych, a następnie zostanie wyświetlony
     * komunikat informacyjny. Metoda również wywołuje metody klienciShowListData() i klienciClear() w celu odświeżenia
     * widoku listy klientów i wyczyszczenia pól tekstowych.
     *
     * @throws NullPointerException      Jeśli nie jest zaznaczony żaden klient do aktualizacji.
     * @throws MysqlDataTruncation       Jeśli przekroczono dozwoloną liczbę znaków.
     * @throws Exception                 Jeśli wystąpił nieprzewidziany wyjątek.
     */
    public void klienciUpdate() {

        klienciData klienciD = klienci_tableView.getSelectionModel().getSelectedItem();
        try {

            String updateImie = "UPDATE uzytkownik SET imie = '" + klienci_imieTex.getText() + "' WHERE id_uzytkownika = '" + klienciD.getKlientId() + "'";
            String updateNazwisko = "UPDATE uzytkownik SET nazwisko = '" + klienci_nazwiskoText.getText() + "' WHERE id_uzytkownika = '" + klienciD.getKlientId() + "'";
            String updateMail = "UPDATE uzytkownik SET mail = '" + klienci_mailText.getText() + "' WHERE id_uzytkownika = '" + klienciD.getKlientId() + "'";
            String updateNrTel = "UPDATE uzytkownik SET nr_telefonu = '" + klienci_telText.getText() + "' WHERE id_uzytkownika = '" + klienciD.getKlientId() + "'";

            connect = BazaDanych.connectToDb();

            Alert alert;

            if (klienci_imieTex.getText().isEmpty()
                    && klienci_nazwiskoText.getText().isEmpty()
                    && klienci_mailText.getText().isEmpty()
                    && klienci_telText.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wszystkie pola są pustę !");
                alert.showAndWait();
            } else if (prepare.executeQuery("SELECT * FROM uzytkownik WHERE mail = '" + klienci_mailText.getText() + "'AND id_uzytkownika != '" + klienciD.getKlientId() + "'").next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Adres Mailowy: " + klienci_mailText.getText() + " jest już zarejestrowany");
                alert.showAndWait();
            } else if (prepare.executeQuery("SELECT * FROM uzytkownik WHERE nr_telefonu = '" + klienci_telText.getText() + "'AND id_uzytkownika != '" + klienciD.getKlientId() + "'").next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Numer Telefonu: " + klienci_telText.getText() + " jest już zarejestrowany");
                alert.showAndWait();
            } else {
                boolean imieUpdated = false;
                boolean nazwiskoUpdated = false;
                boolean mailUpdated = false;
                boolean nrTelefonuupdated = false;


                if (!klienci_imieTex.getText().isEmpty()) {
                    imieUpdated = true;
                } else {
                    imieUpdated = false;
                }

                if (!klienci_nazwiskoText.getText().isEmpty()) {
                    nazwiskoUpdated = true;
                } else {
                    nazwiskoUpdated = false;
                }

                if (!klienci_telText.getText().isEmpty()) {
                    nrTelefonuupdated = true;
                } else {
                    nrTelefonuupdated = false;
                }

                if (!klienci_mailText.getText().isEmpty()) {
                    mailUpdated = true;
                } else {
                    mailUpdated = false;
                }


                boolean anyFieldUpdated = imieUpdated || nazwiskoUpdated || mailUpdated || nrTelefonuupdated;

                if (anyFieldUpdated) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Jestes pewnien ze chcesz ZAKTUALIZOWAC dane użytkownika o ID: " + klienciD.getKlientId() + " ?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {

                        if (imieUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizował imie klienta o ID: " +klienciD.getKlientId() + " z imienia: " + klienciD.getImieKlienta() + " na imie: " + klienci_imieTex.getText());
                            statement.executeUpdate(updateImie);
                        }

                        if (nazwiskoUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizował nazwisko klienta o ID: " +klienciD.getKlientId() + " z nazwiska: " + klienciD.getNazwiskoKlienta() + " na nazwisko: " + klienci_nazwiskoText.getText());
                            statement.executeUpdate(updateNazwisko);
                        }

                        if (mailUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizował adres mailowy klienta o ID: " +klienciD.getKlientId() + " z adresu: " + klienciD.getMailKlienta() + " na adres mailowy: " + klienci_mailText.getText());
                            statement.executeUpdate(updateMail);
                        }

                        if (nrTelefonuupdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizował nr telefonu klienta o ID: " +klienciD.getKlientId() + "z nr telefonu: " + klienciD.getNrTelKlienta() + " na nr telefonu: " + klienci_telText.getText());
                            statement.executeUpdate(updateNrTel);
                        }


                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Zaktualizowano pomyślnie");
                        alert.showAndWait();
                        klienciShowListData();
                        klienciClear();
                    }
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć klienta którego dane chcesz zaktualizować !");
            alert.showAndWait();
        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Usuwa klienta w oparciu o zaznaczony element w tabeli `klienci_tableView`.
     * Metoda ta usuwa klienta na podstawie zaznaczonego elementu w tabeli `klienci_tableView`. Wykorzystuje identyfikator
     * klienta (`klientId`) w celu usunięcia odpowiednich rekordów związanych z tym klientem w różnych tabelach
     * bazy danych. Przed usunięciem klienta, wyświetlany jest komunikat potwierdzający operację. Po zatwierdzeniu
     * usunięcia przez użytkownika, odpowiednie rekordy są usuwane z bazy danych, a następnie wyświetlany jest komunikat
     * informacyjny. Metoda również wywołuje metody `klienciShowListData()` i `klienciClear()` w celu odświeżenia widoku
     * listy klientów i wyczyszczenia pól tekstowych.
     *
     * @throws NullPointerException      Jeśli nie jest zaznaczony żaden klient do usunięcia.
     * @throws Exception                 Jeśli wystąpił nieprzewidziany wyjątek.
     */
    public void klienciDelete() {

        klienciData klienciD = klienci_tableView.getSelectionModel().getSelectedItem();
        try {

            String sql = "DELETE FROM zawartosci_koszykow WHERE id_koszyka IN (SELECT id_koszyka FROM koszyk WHERE id_uzytkownika = '" + klienciD.getKlientId() + "')";
            String sql2 = "DELETE FROM koszyk WHERE id_uzytkownika = '" + klienciD.getKlientId() + "'";
            String sql3 = "DELETE FROM lista_przedmiotow WHERE id_lista_zyczen IN (SELECT id_listy FROM lista_zyczen WHERE id_uzytkownika = '" + klienciD.getKlientId() + "')";
            String sql4 = "DELETE FROM lista_zyczen WHERE id_uzytkownika = '" + klienciD.getKlientId() + "'";
            String sql5 = "DELETE FROM uzytkownik WHERE id_uzytkownika = '" + klienciD.getKlientId() + "'";
            String sql6 = "UPDATE zamowienia SET id_uzytkownika = NULL WHERE id_uzytkownika = '" + klienciD.getKlientId() + "';";
            connect = BazaDanych.connectToDb();

            Alert alert;

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ klienta o ID: " + klienciD.getKlientId() + " ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                logger.info("Administrotr o ID: " + adminID + " usunal klienta o ID: " +klienciD.getKlientId());
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                statement.executeUpdate(sql2);
                statement.executeUpdate(sql3);
                statement.executeUpdate(sql4);
                statement.executeUpdate(sql5);
                statement.executeUpdate(sql6);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Usunięto pomyślnie");
                alert.showAndWait();
                klienciShowListData();
                klienciClear();
            }

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć klienta którego chcesz USUNĄĆ !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }

    }

    /**
     * Wyszukuje klientów na podstawie wprowadzonego tekstu w polu wyszukiwania `klienci_searchField`.
     * Metoda ta filtruje listę klientów (`klienciList`) na podstawie wprowadzonego tekstu w polu wyszukiwania
     * `klienci_searchField`. Wykorzystuje filtr (`FilteredList`) do porównania wprowadzonego tekstu z różnymi
     * atrybutami klientów, takimi jak identyfikator, imię, nazwisko, adres e-mail, numer telefonu i data założenia konta.
     * Jeśli wprowadzony tekst pasuje do jakiegokolwiek atrybutu klienta, to klient jest uwzględniany w wynikowej liście.
     * Wynikowa lista jest sortowana i przypisywana do tabeli `klienci_tableView` w celu wyświetlenia wyników.
     */
    public void klienciSearch() {

        FilteredList<klienciData> filter = new FilteredList<>(klienciList, e -> true);

        klienci_searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getKlientId().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getImieKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwiskoKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getMailKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNrTelKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getDataZalozeniaKonta().toString().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;

            });
        });

        SortedList<klienciData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(klienci_tableView.comparatorProperty());
        klienci_tableView.setItems(sortedList);
    }

    /**
     * Pobiera dane zamówień z bazy danych i zwraca je jako listę obiektów klasy `zamowieniaData`.
     *
     * Metoda ta wykonuje zapytanie do bazy danych, które pobiera dane zamówień wraz z informacjami o dostawach
     * i sposobach dostawy. Następnie tworzy obiekty klasy `zamowieniaData` na podstawie wyników zapytania
     * i dodaje je do listy `listData`. Ostatecznie zwraca tę listę jako wynik.
     *
     * @return Lista obiektów klasy `zamowieniaData` zawierająca dane zamówień.
     */
    public ObservableList<zamowieniaData> zamowieniaListData() {
        ObservableList<zamowieniaData> listData = FXCollections.observableArrayList();

        String sql = "SELECT zamowienia.id_zamowienia, id_uzytkownika, data_zlozenia, status_zamowienia, cena_calkowita,imie,nazwisko,mail,nr_telefonu,miejscowosc, kod_pocztowy,ulica, numer_domu, numer_lokalu, przewidywany_termin_dostawy,nazwa FROM zamowienia\n" +
                "JOIN dostawy ON zamowienia.id_zamowienia = dostawy.id_zamowienia\n" +
                "JOIN sposoby_dostawy ON dostawy.id_sposobu_dostawy = sposoby_dostawy.id_sposobu_dostawy";

        connect = BazaDanych.connectToDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            zamowieniaData zamowieniaD;

            while (result.next()) {
                zamowieniaD = new zamowieniaData(result.getInt("zamowienia.id_zamowienia")
                        , result.getInt("id_uzytkownika")
                        , result.getString("imie")
                        , result.getString("nazwisko")
                        , result.getString("mail")
                        , result.getString("nr_telefonu")
                        , result.getString("kod_pocztowy")
                        , result.getString("miejscowosc")
                        , result.getString("ulica")
                        , result.getString("numer_domu")
                        , result.getString("numer_lokalu")
                        , result.getString("status_zamowienia")
                        , result.getDouble("cena_calkowita")
                        , result.getDate("data_zlozenia")
                        , result.getDate("przewidywany_termin_dostawy")
                        , result.getString("nazwa"));

                listData.add(zamowieniaD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Wyświetla dane zamówień w tabeli w interfejsie administratora.
     *
     * Metoda pobiera dane zamówień poprzez wywołanie metody `zamowieniaListData()`,
     * a następnie ustawia odpowiednie wartości fabryk właściwości dla każdej kolumny tabeli
     * na podstawie pól obiektów klasy `zamowieniaData`. Kolumny tabeli są powiązane z odpowiednimi
     * właściwościami obiektów klasy `zamowieniaData`. Dodatkowo, dla kolumny "cena" dokonuje przekształcenia
     * wartości liczbowej na format z dodanym sufiksem "zł". Na koniec ustawia listę danych zamówień
     * jako źródło danych dla tabeli.
     */
    public void zamowieniaShowListData() {
        zamowieniaList = zamowieniaListData();

        zamowienia_idZamowieniaColumn.setCellValueFactory(new PropertyValueFactory<>("zamowienieId"));
        zamowienia_idKlientaColumn.setCellValueFactory(new PropertyValueFactory<>("klientId"));
        zamowienia_imieColumn.setCellValueFactory(new PropertyValueFactory<>("imieKlienta"));
        zamowienia_nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("nazwiskoKlienta"));
        zamowienia_mailColumn.setCellValueFactory(new PropertyValueFactory<>("mailKlienta"));
        zamowienia_telColumn.setCellValueFactory(new PropertyValueFactory<>("nrTelKlienta"));
        zamowienia_kodPocztowyColumn.setCellValueFactory(new PropertyValueFactory<>("kodPocztowy"));
        zamowienia_miastoColumn.setCellValueFactory(new PropertyValueFactory<>("miasto"));
        zamowienia_ulicaColumn.setCellValueFactory(new PropertyValueFactory<>("ulica"));
        zamowienia_nrBudynkuColumn.setCellValueFactory(new PropertyValueFactory<>("nrBudynku"));
        zamowienia_nrMieszkaniaColumn.setCellValueFactory(new PropertyValueFactory<>("nrMieszkania"));
        zamowienia_statusColumn.setCellValueFactory(new PropertyValueFactory<>("statusZamowienia"));
        zamowienia_cenaColumn.setCellValueFactory(cellData -> {
            Double cena = cellData.getValue().getCenaZamowienia();
            String cenaZl = cena + " zł";
            return new SimpleStringProperty(cenaZl);
        });
        zamowienia_dataZlozeniaColumn.setCellValueFactory(new PropertyValueFactory<>("dataZlozenia"));
        zamowienia_dataDostawyColumn.setCellValueFactory(new PropertyValueFactory<>("dataDostawy"));
        zamowienia_sposobDostawyColumn.setCellValueFactory(new PropertyValueFactory<>("nazwaSposobuDostawy"));

        zamowienia_tableView.setItems(zamowieniaList);

    }

    /**
     * Pobiera dane zawartości zamówienia na podstawie wybranego zamówienia
     * i zwraca je jako listę obiektów klasy `zamowieniaZawartoscData`.
     *
     * Metoda pobiera wybrane zamówienie z `zamowienia_tableView` poprzez wywołanie
     * `getSelectionModel().getSelectedItem()`, a następnie tworzy zapytanie SQL
     * w celu pobrania danych zawartości zamówienia. Pobrane dane są przetwarzane
     * i tworzone są obiekty klasy `zamowieniaZawartoscData`, które są dodawane do
     * listy `listData`. Na koniec lista danych jest zwracana jako rezultat.
     *
     * @return lista danych zawartości zamówienia
     */
    public ObservableList<zamowieniaZawartoscData> zamowieniaZawartoscListData() {
        ObservableList<zamowieniaZawartoscData> listData = FXCollections.observableArrayList();

        zamowieniaData zamowieniaD = zamowienia_tableView.getSelectionModel().getSelectedItem();


        try {
            String sql = "SELECT produkty.id_produktu, produkty.nazwa, produkty.nazwa_producenta, zawartosc_zamowienia.cena_przed_zakupem,zawartosc_zamowienia.liczba_sztuk,produkty.nazwa_kategorii"
                    + " FROM zawartosc_zamowienia JOIN produkty ON zawartosc_zamowienia.id_produktu = produkty.id_produktu WHERE zawartosc_zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            connect = BazaDanych.connectToDb();

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            zamowieniaZawartoscData zamowieniaZawartoscD;

            while (result.next()) {
                zamowieniaZawartoscD = new zamowieniaZawartoscData(result.getInt("produkty.id_produktu")
                        , result.getString("produkty.nazwa")
                        , result.getString("produkty.nazwa_producenta")
                        , result.getDouble("zawartosc_zamowienia.cena_przed_zakupem")
                        , result.getInt("zawartosc_zamowienia.liczba_sztuk")
                        , result.getString("produkty.nazwa_kategorii"));

                listData.add(zamowieniaZawartoscD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Wyświetla dane zawartości zamówienia w tabeli `zamowienia_ZawartoscTableView`.
     *
     * Metoda pobiera dane zawartości zamówienia poprzez wywołanie metody `zamowieniaZawartoscListData()`,
     * a następnie konfiguruje odpowiednie wartości dla każdej kolumny w tabeli `zamowienia_ZawartoscTableView`.
     * Kolumny są powiązywane z odpowiednimi właściwościami obiektów klasy `zamowieniaZawartoscData`.
     * Dodatkowo, dla kolumny `zamowienia_cenaProduktu` tworzona jest specjalna wiązka, która formatuje
     * wartość ceny produktu przed wyświetleniem w tabeli. Na koniec, lista danych jest przypisana do tabeli
     * `zamowienia_ZawartoscTableView`, co powoduje wyświetlenie danych w interfejsie użytkownika.
     */
    public void zamowieniaZawartoscShowListData() {
        zamowieniaZawartoscList = zamowieniaZawartoscListData();

        zamowienia_idProduktu.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        zamowienia_iloscProduktu.setCellValueFactory(new PropertyValueFactory<>("liczbaSztuk"));
        zamowienia_nazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaProduktu"));
        zamowienia_producentProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaProducenta"));
        zamowienia_kategoriaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaKategori"));
        zamowienia_cenaProduktu.setCellValueFactory(cellData -> {
            Double cena = cellData.getValue().getCenaProduktu();
            String cenaZl = cena + " zł";
            return new SimpleStringProperty(cenaZl);
        });


        zamowienia_ZawartoscTableView.setItems(zamowieniaZawartoscList);

    }

    /**
     * Ustawia listę dostępnych statusów zamówienia w ComboBoxie `zamowienia_statusComboBox`.
     *
     * Metoda tworzy nową listę `listS` i wypełnia ją wartościami z listy `statusLista`.
     * Następnie, tworzy obiekt `ObservableList` o nazwie `listD` przy użyciu listy `listS`.
     * Na koniec, ustawia `listD` jako źródło danych dla ComboBoxa `zamowienia_statusComboBox`,
     * co powoduje wyświetlenie dostępnych statusów zamówienia w interfejsie użytkownika.
     */
    public void zamowieniaStatusZamowienia() {
        List<String> listS = new ArrayList<>();

        for (String data : statusLista) {
            listS.add(data);
        }

        ObservableList listD = FXCollections.observableArrayList(listS);
        zamowienia_statusComboBox.setItems(listD);
    }

    /**
     * Wyświetla szczegóły wybranego zamówienia oraz aktualizuje listę zawartości zamówienia.
     *
     * Metoda pobiera zaznaczone zamówienie z TableView `zamowienia_tableView` i indeks zaznaczonego wiersza.
     * Jeśli indeks jest mniejszy od -1, metoda kończy działanie.
     * W przeciwnym razie, metoda ustawia wartość DatePickera `zamowienia_datePicker` na datę dostawy z zamówienia.
     * Ustawia również wartości pól tekstowych `zamowienia_kodPocztowyTextField`, `zamowienia_miastoTextField`,
     * `zamowienia_ulicaTextField`, `zamowienia_nrBudynkuTextField` i `zamowienia_nrMieszkaniaTextField` na
     * odpowiednie wartości z zamówienia.
     * Następnie, ustawia wartość ComboBoxa `zamowienia_statusComboBox` na status zamówienia z zamówienia.
     * Wywołuje również metody `zamowieniaZawartoscShowListData()` i `zamowieniaZawartoscSearch()`, które odpowiednio
     * aktualizują listę zawartości zamówienia oraz uruchamiają wyszukiwanie w tej liście.
     */
    public void zamowieniaSelect() {
        zamowieniaData zamowieniaD = zamowienia_tableView.getSelectionModel().getSelectedItem();
        int num = zamowienia_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        zamowienia_datePicker.setValue(zamowieniaD.getDataDostawy().toLocalDate());
        zamowienia_kodPocztowyTextField.setText(String.valueOf(zamowieniaD.getKodPocztowy()));
        zamowienia_miastoTextField.setText(String.valueOf(zamowieniaD.getMiasto()));
        zamowienia_ulicaTextField.setText(String.valueOf(zamowieniaD.getUlica()));
        zamowienia_nrBudynkuTextField.setText(String.valueOf(zamowieniaD.getNrBudynku()));
        zamowienia_nrMieszkaniaTextField.setText(String.valueOf(zamowieniaD.getNrMieszkania()));
        zamowienia_statusComboBox.setValue(zamowieniaD.getStatusZamowienia());

        zamowieniaZawartoscShowListData();
        zamowieniaZawartoscSearch();

    }

    /**
     * Czyści pola formularza zamówienia, przywracając ich wartości początkowe lub ustawiając na wartości puste.
     *
     * Metoda ustawia wartość DatePickera `zamowienia_datePicker` na null, czyli czyści wybraną datę dostawy.
     * Następnie, ustawia puste wartości dla pól tekstowych: `zamowienia_kodPocztowyTextField`,
     * `zamowienia_miastoTextField`, `zamowienia_ulicaTextField`, `zamowienia_nrBudynkuTextField` oraz
     * `zamowienia_nrMieszkaniaTextField`.
     * Dodatkowo, czyści zaznaczenie ComboBoxa `zamowienia_statusComboBox`.
     */
    public void zamowieniaClear() {

        zamowienia_datePicker.setValue(null);
        zamowienia_kodPocztowyTextField.setText("");
        zamowienia_miastoTextField.setText("");
        zamowienia_ulicaTextField.setText("");
        zamowienia_nrBudynkuTextField.setText("");
        zamowienia_nrMieszkaniaTextField.setText("");
        zamowienia_statusComboBox.getSelectionModel().clearSelection();
    }

    /**
     * Filtruje i wyświetla listę zamówień na podstawie wprowadzonego wyszukiwania.
     *
     * Metoda pobiera wartość wprowadzoną do pola tekstowego `zamowienia_searchField` i filtruje listę zamówień (`zamowieniaList`)
     * na podstawie tego wyszukiwania. Wyszukiwanie jest przeprowadzane na podstawie różnych pól obiektów zamówień,
     * takich jak identyfikator zamówienia, identyfikator klienta, dane klienta, adres dostawy, status zamówienia, data dostawy,
     * sposób dostawy i cena zamówienia. Wyszukiwanie jest nierejestrowe na wielkość liter.
     *
     * Jeśli wprowadzone wyszukiwanie jest puste lub null, wszystkie zamówienia są wyświetlane.
     * W przeciwnym razie, tylko te zamówienia, które pasują do kryteriów wyszukiwania, są wyświetlane.
     *
     * Posortowana i przefiltrowana lista zamówień jest następnie przypisywana do kontrolki tabeli `zamowienia_tableView`.
     */
    public void zamowieniaSearch() {

        FilteredList<zamowieniaData> filter = new FilteredList<>(zamowieniaList, e -> true);

        zamowienia_searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getZamowienieId().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getKlientId().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getImieKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwiskoKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getMailKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNrTelKlienta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getKodPocztowy().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getMiasto().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getUlica().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNrBudynku().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNrMieszkania().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getStatusZamowienia().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getDataDostawy().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaSposobuDostawy().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getCenaZamowienia().toString().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<zamowieniaData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(zamowienia_tableView.comparatorProperty());
        zamowienia_tableView.setItems(sortedList);
    }

    /**
     * Aktualizuje wybrane zamówienie na podstawie wprowadzonych danych.
     *
     * Metoda pobiera zaznaczone zamówienie z tabeli `zamowienia_tableView` i próbuje zaktualizować
     * różne pola dostawy zamówienia na podstawie wprowadzonych danych. Pola, które mogą być zaktualizowane
     * to miasto (`zamowienia_miastoTextField`), kod pocztowy (`zamowienia_kodPocztowyTextField`), ulica (`zamowienia_ulicaTextField`),
     * numer budynku (`zamowienia_nrBudynkuTextField`), numer mieszkania (`zamowienia_nrMieszkaniaTextField`),
     * data dostawy (`zamowienia_datePicker`) oraz status zamówienia (`zamowienia_statusComboBox`).
     *
     * Przed dokonaniem aktualizacji, metoda sprawdza, czy co najmniej jedno pole zostało zaktualizowane.
     * Jeśli żadne pole nie zostało zaktualizowane, wyświetla się komunikat błędu.
     * W przeciwnym razie wyświetlany jest komunikat potwierdzający aktualizację, a następnie metoda
     * wykonuje zapytania SQL do bazy danych w celu aktualizacji poszczególnych pól dostawy zamówienia.
     *
     * Po zakończeniu aktualizacji, wyświetlany jest komunikat informacyjny, a lista zamówień jest odświeżana
     * przy użyciu metody `zamowieniaShowListData()`. Ponadto, metoda czyszcza wprowadzone dane
     * wywołując metodę `zamowieniaClear()`.
     *
     * Jeśli wystąpią błędy podczas aktualizacji, odpowiednie komunikaty błędu są wyświetlane.
     * Obsługiwane błędy to: próba aktualizacji niezaznaczonego zamówienia (NullPointerException)
     * oraz przekroczenie dozwolonej liczby znaków (MysqlDataTruncation).
     */
    public void zamowieniaUpdate() {

        zamowieniaData zamowieniaD = zamowienia_tableView.getSelectionModel().getSelectedItem();

        try {

            String cityUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET miejscowosc = '" + zamowienia_miastoTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String postCodeUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET kod_pocztowy = '" + zamowienia_kodPocztowyTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String streetUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET ulica = '" + zamowienia_ulicaTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String buildingUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET numer_domu = '" + zamowienia_nrBudynkuTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String placeUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET numer_lokalu = '" + zamowienia_nrMieszkaniaTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String dataDostawyUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET przewidywany_termin_dostawy = '" + zamowienia_datePicker.getValue() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String statusUpdate = "UPDATE zamowienia  SET status_zamowienia = '" + zamowienia_statusComboBox.getSelectionModel().getSelectedItem() + "'WHERE id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";


            connect = BazaDanych.connectToDb();
            statement = connect.createStatement();
            Alert alert;

            if (zamowienia_miastoTextField.getText().isEmpty()
                    && zamowienia_kodPocztowyTextField.getText().isEmpty()
                    && zamowienia_ulicaTextField.getText().isEmpty()
                    && zamowienia_nrBudynkuTextField.getText().isEmpty()
                    && zamowienia_nrMieszkaniaTextField.getText().isEmpty()
                    && zamowienia_statusComboBox.getSelectionModel().isEmpty()
                    && (zamowienia_datePicker.getValue() == null)) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wszystkie pola są pustę !");
                alert.showAndWait();
            } else {
                boolean miastoUpdated = false;
                boolean kodPocztowyUpdated = false;
                boolean ulicaUpdated = false;
                boolean nrBudynkuUpdated = false;
                boolean nrMieszkaniaUpdated = false;
                boolean dataUpdated = false;
                boolean statusUpdated = false;


                if (!zamowienia_miastoTextField.getText().isEmpty()) {
                    miastoUpdated = true;
                } else {
                    miastoUpdated = false;
                }

                if (!zamowienia_kodPocztowyTextField.getText().isEmpty()) {
                    kodPocztowyUpdated = true;
                } else {
                    kodPocztowyUpdated = false;
                }

                if (!zamowienia_ulicaTextField.getText().isEmpty()) {
                    ulicaUpdated = true;
                } else {
                    ulicaUpdated = false;
                }

                if (!zamowienia_nrBudynkuTextField.getText().isEmpty()) {
                    nrBudynkuUpdated = true;
                } else {
                    nrBudynkuUpdated = false;
                }

                if (!zamowienia_nrMieszkaniaTextField.getText().isEmpty()) {
                    nrMieszkaniaUpdated = true;
                } else {
                    nrMieszkaniaUpdated = false;
                }

                if (!(zamowienia_datePicker.getValue() == null)) {
                    dataUpdated = true;
                } else {
                    dataUpdated = false;
                }

                if (!zamowienia_statusComboBox.getSelectionModel().isEmpty()) {
                    statusUpdated = true;
                } else {
                    statusUpdated = false;
                }

                boolean anyFieldUpdated = miastoUpdated || kodPocztowyUpdated || ulicaUpdated || nrBudynkuUpdated || nrMieszkaniaUpdated || dataUpdated || statusUpdated;

                if (anyFieldUpdated) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Jestes pewnien ze chcesz ZAKTUALIZOWAC zamówienie o ID: " + zamowieniaD.getZamowienieId() + " ?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {

                        if (miastoUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizowal miasto dostarczenia zamowienia o ID: " +zamowieniaD.getZamowienieId() + " z miasta: " + zamowieniaD.getMiasto() + " na miasto: " + zamowienia_miastoTextField.getText());
                            statement.executeUpdate(cityUpdate);
                        }

                        if (kodPocztowyUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizowal kod pocztowy dostarczenia zamowienia o ID: " +zamowieniaD.getZamowienieId() + " z kodu: " + zamowieniaD.getKodPocztowy() + " na kod pocztowy: " + zamowienia_kodPocztowyTextField.getText());
                            statement.executeUpdate(postCodeUpdate);
                        }

                        if (ulicaUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizowal ulice dostarczenia zamowienia o ID: " +zamowieniaD.getZamowienieId() + " z ulicy: " + zamowieniaD.getUlica() + " na ulice: " + zamowienia_ulicaTextField.getText());
                            statement.executeUpdate(streetUpdate);
                        }

                        if (nrBudynkuUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizowal nr budynku dostarczenia zamowienia o ID: " +zamowieniaD.getZamowienieId() + " z nr budynku: " + zamowieniaD.getMiasto() + " na nr budynku: " + zamowienia_nrBudynkuTextField.getText());
                            statement.executeUpdate(buildingUpdate);
                        }

                        if (nrMieszkaniaUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizowal nr lokalu dostarczenia zamowienia o ID: " +zamowieniaD.getZamowienieId() + " z nr lokalu: " + zamowieniaD.getNrMieszkania() + " na nr lokalu: " + zamowienia_nrMieszkaniaTextField.getText());
                            statement.executeUpdate(placeUpdate);
                        }

                        if (dataUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizowal date dostawy dostarczenia zamowienia o ID: " +zamowieniaD.getZamowienieId() + " z daty: " + zamowieniaD.getDataDostawy() + " na date: " + zamowienia_datePicker.getValue());
                            statement.executeUpdate(dataDostawyUpdate);
                        }

                        if (statusUpdated) {
                            logger.info("Administrotr o ID: " + adminID + " zaktualizowal status zamowienia o ID: " +zamowieniaD.getZamowienieId() + " ze statusu: " + zamowieniaD.getStatusZamowienia() + " na status: " + zamowienia_statusComboBox.getSelectionModel().getSelectedItem());
                            statement.executeUpdate(statusUpdate);
                        }


                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Zaktualizowano pomyślnie");
                        alert.showAndWait();
                        zamowieniaShowListData();
                        zamowieniaClear();
                    }
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć zamówienie które chcesz zaktualizować !");
            alert.showAndWait();
        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }

    }

    /**
     * Metoda usuwa wybrane zamówienie wraz z powiązanymi danymi z tabeli zamówień, dostaw i zawartości zamówienia.
     * Po wybraniu zamówienia, wyświetla okno potwierdzenia usunięcia. Po potwierdzeniu usunięcia,
     * usuwa odpowiednie wpisy z bazy danych, aktualizuje wyświetlaną listę zamówień oraz czyści pola formularza.
     * Jeśli operacja usuwania zostanie anulowana lub nie zostanie wybrane żadne zamówienie, zostanie wyświetlony odpowiedni komunikat.
     */
    public void zamowieniaDelete() {

        zamowieniaData zamowieniaD = zamowienia_tableView.getSelectionModel().getSelectedItem();

        try {
            String sql = "DELETE zamowienia, dostawy, zawartosc_zamowienia FROM zamowienia LEFT JOIN dostawy ON zamowienia.id_zamowienia = dostawy.id_zamowienia LEFT JOIN zawartosc_zamowienia ON zamowienia.id_zamowienia = zawartosc_zamowienia.id_zamowienia WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ zamowienie o ID: " + zamowieniaD.getZamowienieId() + " ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logger.info("Administrotr o ID: " + adminID + " usunal zamowienie o ID: " +zamowieniaD.getZamowienieId());
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Usunięto pomyślnie");
                alert.showAndWait();
                zamowieniaShowListData();
                zamowieniaClear();
                zamowienia_ZawartoscTableView.getItems().clear();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć zamówienie które chcesz USUNĄĆ !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Metoda przeprowadza wyszukiwanie w tabeli zawartości zamówienia na podstawie wprowadzonego tekstu.
     * Tworzy filtr na podstawie podanej wartości wyszukiwania i porównuje ją z odpowiednimi polami danych.
     * Aktualizuje wyświetlaną listę zawartości zamówienia na podstawie wyników filtrowania.
     */
    public void zamowieniaZawartoscSearch() {

        FilteredList<zamowieniaZawartoscData> filter = new FilteredList<>(zamowieniaZawartoscList, e -> true);

        zamowienia_szczegolySearchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getItemId().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getCenaProduktu().toString().indexOf(searchKey) != -1) {
                    return true;
                }else if (predicateItemData.getNazwaProduktu().toString().indexOf(searchKey) != -1){
                    return true;
                }
                else if (predicateItemData.getNazwaProducenta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaKategori().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getCenaProduktu().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getLiczbaSztuk().toString().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<zamowieniaZawartoscData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(zamowienia_ZawartoscTableView.comparatorProperty());
        zamowienia_ZawartoscTableView.setItems(sortedList);
    }


    /**
     * Zwraca listę pracowników w postaci obiektu ObservableList<pracownicyData>.
     *
     * Metoda wykonuje zapytanie do bazy danych, pobiera dane z tabeli 'pracownicy' i tworzy obiekty pracownicyData
     * na podstawie otrzymanych wyników. Następnie dodaje te obiekty do listy ObservableList<pracownicyData> i zwraca ją.
     *
     * @return lista pracowników w postaci obiektu ObservableList<pracownicyData>
     */
    public ObservableList<pracownicyData> pracownicyListData() {
        ObservableList<pracownicyData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM pracownicy";

        connect = BazaDanych.connectToDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            pracownicyData pracownicyD;

            while (result.next()) {
                pracownicyD = new pracownicyData(result.getInt("id_pracownika")
                        , result.getString("imie")
                        , result.getString("nazwisko")
                        , result.getDouble("wynagrodzenie")
                        , result.getString("nr_telefonu")
                        , result.getString("adres_mailowy")
                        , result.getString("stanowisko"));

                listData.add(pracownicyD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Wyświetla dane pracowników w tabeli pracownicy_tableView.
     *
     * Metoda pobiera dane pracowników za pomocą metody pracownicyListData(), a następnie ustawia odpowiednie
     * wartości dla poszczególnych kolumn tabeli pracownicy_tableView, używając PropertyValueFactory do mapowania
     * pól obiektów pracownicyData na odpowiednie kolumny tabeli. Dodatkowo, dla kolumny pracownicy_zarobkiColumn
     * przekształca wartość wynagrodzenia pracownika na tekstowy format z dodanym sufiksem "zł". Na koniec,
     * ustawia otrzymaną listę pracowników jako źródło danych dla tabeli pracownicy_tableView.
     */
    public void pracownicyShowListData() {
        pracownicyList = pracownicyListData();

        pracownicy_idColumn.setCellValueFactory(new PropertyValueFactory<>("idPracownika"));
        pracownicy_imieColumn.setCellValueFactory(new PropertyValueFactory<>("imiePracownika"));
        pracownicy_nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("nazwiskoPracownika"));
        pracownicy_telColumn.setCellValueFactory(new PropertyValueFactory<>("telPracownika"));
        pracownicy_mailColumn.setCellValueFactory(new PropertyValueFactory<>("mialPracownika"));
        pracownicy_stanowiskoColumn.setCellValueFactory(new PropertyValueFactory<>("stanowiskoPracownika"));
        pracownicy_zarobkiColumn.setCellValueFactory(cellData -> {
            Double cena = cellData.getValue().getWynagrodzeniePracownika();
            String cenaZl = cena + " zł";
            return new SimpleStringProperty(cenaZl);
        });

        pracownicy_tableView.setItems(pracownicyList);

    }

    /**
     * Ustawia dostępne kategorie stanowisk pracowników w ComboBox pracownicy_stanowiskoPracownika.
     *
     * Metoda tworzy listę Stringów, 'listS', na podstawie danych zawartych w liście 'stanowiskoLista'.
     * Następnie konwertuje tę listę na ObservableList i ustawia ją jako źródło danych dla ComboBoxa pracownicy_stanowiskoPracownika.
     * Dzięki temu ComboBox wyświetla dostępne kategorie stanowisk pracowników, z których użytkownik może wybierać.
     */
    public void pracownicyKategoria() {
        List<String> listS = new ArrayList<>();

        for (String data : stanowiskoLista) {
            listS.add(data);
        }

        ObservableList listD = FXCollections.observableArrayList(listS);
        pracownicy_stanowiskoPracownika.setItems(listD);
    }

    /**
     * Wybiera i wyświetla dane wybranego pracownika z tabeli pracownicy_tableView.
     *
     * Metoda pobiera zaznaczony obiekt pracownicyData z tabeli pracownicy_tableView oraz indeks zaznaczonego wiersza.
     * Następnie aktualizuje pola tekstowe i ComboBox na formularzu, przypisując im odpowiednie wartości z wybranego pracownika.
     * Jeśli nie został wybrany żaden pracownik (indeks wiersza mniejszy od zera), metoda nie wykonuje żadnych operacji.
     */
    public void pracownicySelect() {
        pracownicyData pracownicyD = pracownicy_tableView.getSelectionModel().getSelectedItem();
        int num = pracownicy_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        pracownicy_imiePracownika.setText(String.valueOf(pracownicyD.getImiePracownika()));
        pracownicy_nazwiskoPracownika.setText(String.valueOf(pracownicyD.getNazwiskoPracownika()));
        pracownicy_telPracownika.setText(String.valueOf(pracownicyD.getTelPracownika()));
        pracownicy_mailPracownika.setText(String.valueOf(pracownicyD.getMialPracownika()));
        pracownicy_zarobkiPracownika.setText(String.valueOf(pracownicyD.getWynagrodzeniePracownika()));
        pracownicy_stanowiskoPracownika.setValue(pracownicyD.getStanowiskoPracownika());

    }

    /**
     * Dodaje nowego pracownika do bazy danych na podstawie wprowadzonych danych z formularza.
     *
     * Metoda wykonuje operację dodawania nowego pracownika do tabeli 'pracownicy' w bazie danych.
     * Sprawdza również, czy podany adres mailowy i numer telefonu są unikalne, aby uniknąć duplikatów.
     * Jeśli adres mailowy lub numer telefonu są już zarejestrowane, wyświetlane są odpowiednie komunikaty błędu.
     * Jeśli dane są poprawne i unikalne, wykonywane jest dodanie rekordu do bazy danych.
     * Po dodaniu pracownika, wyświetlane jest powiadomienie o pomyślnym dodaniu, aktualizowana jest lista pracowników
     * i formularz zostaje wyczyszczony.
     *
     * @throws NullPointerException                    jeśli któreś z pól formularza jest puste
     * @throws SQLIntegrityConstraintViolationException jeśli któreś z pól unikalnych (adres mailowy, numer telefonu) już istnieje w bazie danych
     * @throws MysqlDataTruncation                      jeśli przekroczono dozwoloną liczbę znaków w polach formularza
     * @throws SQLException                            jeśli wprowadzono nieprawidłowy format danych
     * @throws Exception                               jeśli wystąpił nieprzewidziany wyjątek
     */
    public void pracownicyAdd() {
        String sql = "INSERT INTO pracownicy (imie, nazwisko,nr_telefonu, adres_mailowy,stanowisko,wynagrodzenie) VALUES(?,?,?,?,?,?)";

        connect = BazaDanych.connectToDb();

        try {

            Alert alert;

            String checkDataMail = "SELECT adres_mailowy FROM pracownicy WHERE adres_mailowy = '" + pracownicy_mailPracownika.getText() + "'";
            String checkDataTel = "SELECT nr_telefonu FROM pracownicy WHERE nr_telefonu = '" + pracownicy_telPracownika.getText() + "'";

            statement = connect.createStatement();
            result = statement.executeQuery(checkDataMail);

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Adres Mailowy: " + pracownicy_mailPracownika.getText() + " już jest zarejestrowany");
                alert.showAndWait();
            }

            result = statement.executeQuery(checkDataTel);

            if (result.next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Numer Telefonu: " + pracownicy_telPracownika.getText() + " już jest zarejestrowany");
                alert.showAndWait();
            } else {

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, pracownicy_imiePracownika.getText());
                prepare.setString(2, pracownicy_nazwiskoPracownika.getText());
                prepare.setString(3, pracownicy_telPracownika.getText());
                prepare.setString(4, pracownicy_mailPracownika.getText());
                prepare.setString(5, (String) pracownicy_stanowiskoPracownika.getSelectionModel().getSelectedItem());
                prepare.setString(6, pracownicy_zarobkiPracownika.getText());

                logger.info("Administrotr o ID: " + adminID + " dodał nowego pracownika o adresie mailowym: " + pracownicy_mailPracownika.getText());

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Dodano pomyślnie");
                alert.showAndWait();

                pracownicyShowListData();
                pracownicyClear();
            }


        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę Uzupełnić Wszystkie Pola !");
            alert.showAndWait();
        } catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę Uzupełnić Wszystkie Pola !");
            alert.showAndWait();
        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadzono nieprawidłowy format !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Czyści pola tekstowe służące do dodawania nowego pracownika.
     *
     * Metoda czyści pola tekstowe oraz resetuje wybór w ComboBox pracownicy_stanowiskoPracownika,
     * przywracając pola tekstowe do pierwotnego pustego stanu.
     */
    public void pracownicyClear() {

        pracownicy_imiePracownika.setText("");
        pracownicy_nazwiskoPracownika.setText("");
        pracownicy_telPracownika.setText("");
        pracownicy_mailPracownika.setText("");
        pracownicy_zarobkiPracownika.setText("");
        pracownicy_stanowiskoPracownika.getSelectionModel().clearSelection();
    }

    /**
     * Aktulizuje informacje o wybranym pracowniku.
     *
     * Metoda sprawdza wprowadzone dane, a następnie aktualizuje informacje o wybranym pracowniku w bazie danych.
     * Jeśli któreś z pól tekstowych jest puste lub nie zostało wybrane stanowisko, wyświetla komunikat błędu.
     * Sprawdza również, czy wprowadzony adres e-mail lub numer telefonu nie są już zarejestrowane dla innego pracownika.
     * Po potwierdzeniu aktualizacji przez użytkownika, wykonywane są odpowiednie zapytania SQL i wyświetlany jest komunikat o sukcesie.
     * Następnie odświeżana jest lista pracowników i formularz zostaje wyczyszczony.
     *
     * @throws NullPointerException         gdy nie został wybrany żaden pracownik do aktualizacji
     * @throws MysqlDataTruncation          gdy wprowadzone dane przekraczają dozwoloną liczbę znaków w kolumnach tabeli
     * @throws SQLException                gdy wystąpił błąd zapytania SQL lub nieprawidłowy format danych
     * @throws Exception                   gdy wystąpił nieprzewidziany wyjątek
     */
    public void pracownicyUpdate() {

        pracownicyData pracownicyD = pracownicy_tableView.getSelectionModel().getSelectedItem();

        try {
            String nameUpdate = "UPDATE pracownicy SET imie = '" + pracownicy_imiePracownika.getText() + "'WHERE id_pracownika = '" + pracownicyD.getIdPracownika() + "'";
            String nazwiskoUpdate = "UPDATE pracownicy SET nazwisko = '" + pracownicy_nazwiskoPracownika.getText() + "'";
            String telUpdate = "UPDATE pracownicy SET nr_telefonu ='" + pracownicy_telPracownika.getText() + "'";
            String mailUpdate = "UPDATE pracownicy SET adres_mailowy = '" + pracownicy_mailPracownika.getText() + "'";
            String stanowiskoUpdate = "UPDATE pracownicy SET stanowisko = '" + pracownicy_stanowiskoPracownika.getSelectionModel().getSelectedItem() + "'";
            String wynagrodzenieUpdate = "UPDATE pracownicy SET wynagrodzenie ='" + pracownicy_zarobkiPracownika.getText() + "'";

            connect = BazaDanych.connectToDb();

            Alert alert;

            if (pracownicy_imiePracownika.getText().isEmpty()
                    && pracownicy_nazwiskoPracownika.getText().isEmpty()
                    && pracownicy_zarobkiPracownika.getText().isEmpty()
                    && pracownicy_telPracownika.getText().isEmpty()
                    && pracownicy_mailPracownika.getText().isEmpty()
                    && pracownicy_stanowiskoPracownika.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wszystkie pola są puste !");
                alert.showAndWait();
            } else {

                String checkDataMail = "SELECT adres_mailowy FROM pracownicy WHERE adres_mailowy = '" + pracownicy_mailPracownika.getText() + "' AND id_pracownika != '" + pracownicyD.getIdPracownika() + "'";
                String checkDataTel = "SELECT nr_telefonu FROM pracownicy WHERE nr_telefonu = '" + pracownicy_telPracownika.getText() + "' AND id_pracownika != '" + pracownicyD.getIdPracownika() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkDataMail);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Adres Mailowy: " + pracownicy_mailPracownika.getText() + " już jest zarejestrowany");
                    alert.showAndWait();
                }

                result = statement.executeQuery(checkDataTel);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Numer Telefonu: " + pracownicy_telPracownika.getText() + " już jest zarejestrowany");
                    alert.showAndWait();
                } else {
                    boolean imieUpdated = false;
                    boolean nazwiskoUpdated = false;
                    boolean mailUpdated = false;
                    boolean nrTelUpdated = false;
                    boolean stanowiskoUpdated = false;
                    boolean wynagordzenieUpdated = false;

                    if (!pracownicy_imiePracownika.getText().isEmpty()) {
                        imieUpdated = true;
                    } else {
                        imieUpdated = false;
                    }

                    if (!pracownicy_nazwiskoPracownika.getText().isEmpty()) {
                        nazwiskoUpdated = true;
                    } else {
                        nazwiskoUpdated = false;
                    }

                    if (!pracownicy_mailPracownika.getText().isEmpty()) {
                        mailUpdated = true;
                    } else {
                        mailUpdated = false;
                    }

                    if (!pracownicy_telPracownika.getText().isEmpty()) {
                        nrTelUpdated = true;
                    } else {
                        nrTelUpdated = false;
                    }

                    if (!pracownicy_zarobkiPracownika.getText().isEmpty()) {
                        wynagordzenieUpdated = true;
                    } else {
                        wynagordzenieUpdated = false;
                    }

                    if (!pracownicy_stanowiskoPracownika.getSelectionModel().isEmpty()) {
                        stanowiskoUpdated = true;
                    } else {
                        stanowiskoUpdated = false;
                    }

                    boolean anyFieldUpdated = imieUpdated || nazwiskoUpdated || mailUpdated || nrTelUpdated || stanowiskoUpdated || wynagordzenieUpdated;

                    if (anyFieldUpdated) {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Jestes pewnien ze chcesz ZAKTUALIZOWAC pracownika o ID: " + pracownicyD.getIdPracownika() + " ?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {

                            if (imieUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal imie pracownika o ID: " + pracownicyD.getIdPracownika() + " z imienia: " + pracownicyD.getImiePracownika() + " na imie: " + pracownicy_imiePracownika.getText());
                                statement.executeUpdate(nameUpdate);
                            }

                            if (nazwiskoUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal nazwisko pracownika o ID: " + pracownicyD.getIdPracownika() + " z nazwiska: " + pracownicyD.getNazwiskoPracownika() + " na nazwisko: " + pracownicy_nazwiskoPracownika.getText());
                                statement.executeUpdate(nazwiskoUpdate);
                            }

                            if (mailUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal adres mailowy pracownika o ID: " + pracownicyD.getIdPracownika() + " z adresu malowego: " + pracownicyD.getMialPracownika() + " na adres mailowy: " + pracownicy_mailPracownika.getText());
                                statement.executeUpdate(mailUpdate);
                            }

                            if (nrTelUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal nr telefonu pracownika o ID: " + pracownicyD.getIdPracownika() + " z nr telefonu: " + pracownicyD.getTelPracownika() + " na nr telefonu: " + pracownicy_telPracownika.getText());
                                statement.executeUpdate(telUpdate);
                            }

                            if (stanowiskoUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal stanowisko pracownika o ID: " + pracownicyD.getIdPracownika() + " ze stanowiska: " + pracownicyD.getStanowiskoPracownika() + " na stanowisko: " + pracownicy_stanowiskoPracownika.getSelectionModel().getSelectedItem());
                                statement.executeUpdate(stanowiskoUpdate);
                            }

                            if (wynagordzenieUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal wynagordzenie pracownika o ID: " + pracownicyD.getIdPracownika() + " z wynagordzenia: " + pracownicyD.getWynagrodzeniePracownika() + " zł na wynagrodzenie: " + pracownicy_zarobkiPracownika.getText() + " zł");
                                statement.executeUpdate(wynagrodzenieUpdate);
                            }


                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Zaktualizowano pomyślnie");
                            alert.showAndWait();
                            pracownicyShowListData();
                            pracownicyClear();
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć pracownika którego chcesz zaktualizować !");
            alert.showAndWait();
        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadzono nieprawidłowy format !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Usuwa wybranego pracownika z bazy danych.
     *
     * Metoda sprawdza, czy został wybrany pracownik do usunięcia. Wyświetla potwierdzenie zapytania i po jego zaakceptowaniu
     * usuwa pracownika z bazy danych. Następnie wyświetla komunikat o sukcesie, odświeża listę pracowników i czyści formularz.
     *
     * @throws NullPointerException         gdy nie został wybrany żaden pracownik do usunięcia
     * @throws Exception                   gdy wystąpił nieprzewidziany wyjątek
     */
    public void pracownicyDelete() {

        pracownicyData pracownicyD = pracownicy_tableView.getSelectionModel().getSelectedItem();

        try {
            String sql = "DELETE FROM pracownicy WHERE id_pracownika = '" + pracownicyD.getIdPracownika() + "'";
            connect = BazaDanych.connectToDb();
            Alert alert;

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ pracownika o ID: " + pracownicyD.getIdPracownika() + " ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                logger.info("Administrotr o ID: " + adminID + " usunał pracownika o adresie mailowym: " + pracownicyD.getMialPracownika());
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Usunięto pomyślnie");
                alert.showAndWait();
                pracownicyShowListData();
                pracownicyClear();
            }


        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć pracownika którego chcesz USUNĄĆ !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Wyszukuje pracowników na podstawie wprowadzonego tekstu.
     *
     * Metoda umożliwia wyszukiwanie pracowników na podstawie wprowadzonego tekstu. Tworzy filtr dla listy pracowników
     * i aktualizuje widok tabeli, wyświetlając tylko pasujące wyniki. Wyszukiwanie jest wykonywane na podstawie identyfikatora
     * pracownika, imienia, nazwiska, adresu mailowego, numeru telefonu, wynagrodzenia i stanowiska. Wprowadzony tekst jest
     * porównywany z wartościami poszczególnych pól, przy uwzględnieniu wielkości liter.
     */
    public void pracownicySearch() {

        FilteredList<pracownicyData> filter = new FilteredList<>(pracownicyList, e -> true);

        pracownicy_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getIdPracownika().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getImiePracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwiskoPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getMialPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getTelPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getWynagrodzeniePracownika().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getStanowiskoPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<pracownicyData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(pracownicy_tableView.comparatorProperty());
        pracownicy_tableView.setItems(sortedList);
    }


    /**
     * Pobiera dane dotyczące rabatów z bazy danych.
     *
     * Metoda wykonuje zapytanie do bazy danych w celu pobrania informacji dotyczących rabatów. Zwraca listę obiektów
     * {@link rabatyData}, zawierającą dane o każdym rabacie, włączając identyfikator rabatu, kod rabatowy i procentową
     * wartość zniżki.
     *
     * @return Lista obiektów {@link rabatyData} zawierająca dane o rabatach.
     */
    public ObservableList<rabatyData> rabatyListData() {
        ObservableList<rabatyData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM rabaty";

        connect = BazaDanych.connectToDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            rabatyData rabatyD;

            while (result.next()) {
                rabatyD = new rabatyData(result.getInt("id_rabatu")
                        , result.getString("kod")
                        , result.getInt("procent_znizki"));

                listData.add(rabatyD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Wyświetla dane dotyczące rabatów w tabeli.
     *
     * Metoda pobiera dane dotyczące rabatów za pomocą metody {@link #rabatyListData()}, a następnie przypisuje je do
     * odpowiednich kolumn tabeli w interfejsie użytkownika. Dane są wyświetlane w formie tabelarycznej, gdzie każda
     * kolumna reprezentuje różne atrybuty rabatu, takie jak identyfikator, kod rabatowy i procentowa wartość zniżki.
     */
    public void rabatyShowListData() {
        rabatyList = rabatyListData();

        rabaty_idColumn.setCellValueFactory(new PropertyValueFactory<>("idRabatu"));
        rabaty_kodColumn.setCellValueFactory(new PropertyValueFactory<>("kodRabatu"));
        rabaty_znizkaColumn.setCellValueFactory(cellData -> {
            Integer znizka = cellData.getValue().getZnizka();
            String znizkaProcent = znizka + " %";
            return new SimpleStringProperty(znizkaProcent);
        });

        rabaty_tableView.setItems(rabatyList);

    }

    /**
     * Wybiera rabat zaznaczony w tabeli.
     *
     * Metoda pobiera zaznaczony rabat z tabeli rabatów i ustawia odpowiednie wartości jego atrybutów
     * (kod rabatu i procent zniżki) w polach tekstowych w interfejsie użytkownika.
     * Jeśli żaden rabat nie jest zaznaczony lub indeks zaznaczonego rabatu jest nieprawidłowy,
     * nie są podejmowane żadne działania.
     */
    public void rabatySelect() {
        rabatyData rabatyD = rabaty_tableView.getSelectionModel().getSelectedItem();
        int num = rabaty_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        rabaty_kodRabatu.setText(String.valueOf(rabatyD.getKodRabatu()));
        rabaty_znizka.setText(String.valueOf(rabatyD.getZnizka()));

    }

    /**
     * Dodaje nowy rabat.
     *
     * Metoda odpowiada za dodawanie nowego rabatu do bazy danych. Pobiera wartości kodu rabatu i procentu zniżki
     * z odpowiednich pól tekstowych interfejsu użytkownika. Sprawdza, czy pola nie są puste. Jeśli są puste,
     * wyświetla komunikat o błędzie. W przeciwnym razie sprawdza, czy podany kod rabatu już istnieje w bazie danych.
     * Jeśli tak, wyświetla komunikat o błędzie. Jeśli kod rabatu jest unikalny, dokonuje dodania rabatu do bazy danych.
     * Jeśli operacja się powiedzie, wyświetla komunikat informacyjny o sukcesie, aktualizuje listę rabatów i czyści pola tekstowe.
     * W przypadku wystąpienia błędów, wyświetla odpowiednie komunikaty o błędach.
     */
    public void rabatyAdd() {
        String sql = "INSERT INTO rabaty (kod, procent_znizki) VALUES(?,?)";

        connect = BazaDanych.connectToDb();

        try {

            Alert alert;

            if (rabaty_kodRabatu.getText().isEmpty() || rabaty_znizka.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze uzupełnić wszystkie puste pola z wyjątkiem pola ID produktu");
                alert.showAndWait();
            } else {

                String checkCode = "SELECT kod FROM rabaty WHERE kod = '" + rabaty_kodRabatu.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkCode);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Kod rabatyu: " + rabaty_kodRabatu.getText() + " już jest zarejestrowany");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, rabaty_kodRabatu.getText());
                    prepare.setString(2, rabaty_znizka.getText());

                    try {
                        logger.info("Administrotr o ID: " + adminID + " dodał nowy kod rabatowy o kodzie: " + rabaty_kodRabatu.getText());
                        prepare.executeUpdate();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Dodano pomyślnie");
                        alert.showAndWait();

                        rabatyShowListData();
                        rabatyClear();
                    } catch (Exception e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Prosze użyć KROPKI jako separatora !!");
                        alert.showAndWait();
                    }
                }
            }

        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadzono nieprawidłowy format !");
            alert.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
        }
    }

    /**
     * Czyści pola tekstowe formularza rabatów.
     *
     * Metoda służy do wyczyszczenia pól tekstowych interfejsu użytkownika
     * przeznaczonych do wprowadzania danych dotyczących nowego rabatu.
     * Ustawia puste wartości tekstowe dla pola kodu rabatu i pola zniżki.
     * Po wywołaniu tej metody, pola tekstowe zostaną wyczyszczone i będą gotowe
     * do wprowadzenia nowych danych.
     */
    public void rabatyClear() {

        rabaty_kodRabatu.setText("");
        rabaty_znizka.setText("");

    }

    /**
     * Aktualizuje wybrany rabat.
     *
     * Metoda służy do aktualizacji danych wybranego rabatu w bazie danych na podstawie wprowadzonych zmian.
     * Pobiera wybrany rabat z tabeli rabatów, a następnie sprawdza, czy pola tekstowe zawierające nowe wartości kodu rabatu
     * i zniżki zostały uzupełnione. Jeśli wszystkie pola są puste, wyświetlany jest błąd.
     * W przeciwnym razie, sprawdzane jest, czy nowy kod rabatu jest unikalny. Jeśli nie, wyświetlany jest błąd.
     * Jeśli kod rabatu jest unikalny lub został wprowadzony inny kod, a także zniżka została wprowadzona,
     * wyświetlany jest komunikat z prośbą o potwierdzenie aktualizacji rabatu.
     * Po potwierdzeniu aktualizacji rabatu, wartości kodu i zniżki są aktualizowane w bazie danych,
     * a następnie wyświetlany jest komunikat informujący o pomyślnej aktualizacji.
     * Na koniec odświeżana jest lista rabatów, a pola tekstowe są wyczyszczane.
     */
    public void rabatyUpdate() {

        rabatyData rabatyD = rabaty_tableView.getSelectionModel().getSelectedItem();
        try {
            String kodUpdate = "UPDATE rabaty SET kod = '" + rabaty_kodRabatu.getText() + "'WHERE id_rabatu = '" + rabatyD.getIdRabatu() + "'";
            String znizkaUpdate = "UPDATE rabaty SET procent_znizki = '" + rabaty_znizka.getText() + "' WHERE id_rabatu = '" + rabatyD.getIdRabatu() + "'";

            connect = BazaDanych.connectToDb();
            Alert alert;

            if (rabaty_kodRabatu.getText().isEmpty()
                    && rabaty_znizka.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wszystkie pola są puste !");
                alert.showAndWait();
            } else {

                String checkCode = "SELECT kod FROM rabaty WHERE kod = '" + rabaty_kodRabatu.getText() + "' AND id_rabatu != '" + rabatyD.getIdRabatu() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkCode);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Kod rabatu: " + rabaty_kodRabatu.getText() + " już jest zarejestrowany");
                    alert.showAndWait();
                } else {

                    boolean kodUpdated = false;
                    boolean znizkaUpdated = false;

                    if (!rabaty_kodRabatu.getText().isEmpty()) {
                        kodUpdated = true;
                    } else {
                        kodUpdated = false;
                    }

                    if (!rabaty_znizka.getText().isEmpty()) {
                        znizkaUpdated = true;
                    } else {
                        znizkaUpdated = false;
                    }


                    boolean anyFieldUpdated = kodUpdated || znizkaUpdated;

                    if (anyFieldUpdated) {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Jestes pewnien ze chcesz ZAKTUALIZOWAC rabat o ID: " + rabatyD.getIdRabatu() + " ?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {

                            if (znizkaUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal procent znizki kodu rabatowego o id: " + rabatyD.getIdRabatu() + " z: " + rabatyD.getZnizka() + "% na: " + rabaty_znizka.getText() + "%");
                                statement.executeUpdate(znizkaUpdate);
                            }

                            if (kodUpdated) {
                                logger.info("Administrotr o ID: " + adminID + " zaktualizowal kod kodu rabatowego o id: " + rabatyD.getIdRabatu() + " z kodu: " + rabatyD.getKodRabatu() + " na kod: " + rabaty_kodRabatu.getText());
                                statement.executeUpdate(kodUpdate);
                            }

                            alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Zaktualizowano pomyślnie");
                            alert.showAndWait();
                            rabatyShowListData();
                            rabatyClear();
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć rabat który chcesz zaktualizować !");
            alert.showAndWait();
        } catch (MysqlDataTruncation e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Przekroczono dozwoloną liczbę znaków");
            alert.showAndWait();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadzono nieprawidłowy format !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }

    }

    /**
     * Usuwa wybrany rabat.
     *
     * Metoda służy do usuwania wybranego rabatu z bazy danych. Pobiera wybrany rabat z tabeli rabatów
     * i wyświetla potwierdzenie usunięcia. Po potwierdzeniu, rabat jest usuwany z bazy danych,
     * a następnie wyświetlany jest komunikat informujący o pomyślnym usunięciu.
     * Na koniec odświeżana jest lista rabatów, a pola tekstowe są wyczyszczane.
     */
    public void rabatyDelete() {

        rabatyData rabatyD = rabaty_tableView.getSelectionModel().getSelectedItem();
        try {
            String sql = "DELETE FROM rabaty WHERE id_rabatu = '" + rabatyD.getIdRabatu() + "'";
            connect = BazaDanych.connectToDb();
            Alert alert;

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ rabat o ID: " + rabatyD.getIdRabatu() + " ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                logger.info("Administrotr o ID: " + adminID + " usunał kod rabatowy: id: " + rabatyD.getKodRabatu());
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Usunięto pomyślnie");
                alert.showAndWait();
                rabatyShowListData();
                rabatyClear();
            }

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć rabat który chcesz USUNĄĆ !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }

    }

    /**
     * Wyszukuje rabat.
     *
     * Metoda służy do filtrowania listy rabatów na podstawie wprowadzonego tekstu wyszukiwania.
     * Wykorzystuje mechanizm filtracji i sortowania danych w tabeli rabatów. Wprowadzony tekst
     * wyszukiwania jest porównywany z polami ID rabatu, kodem rabatu i procentem zniżki.
     * Wszystkie rabaty, które pasują do kryteriów wyszukiwania, są wyświetlane w tabeli.
     *
     * @implNote Metoda korzysta z obiektów typu FilteredList i SortedList do realizacji
     * filtracji i sortowania danych w tabeli.
     */
    public void rabatySearch() {

        FilteredList<rabatyData> filter = new FilteredList<>(rabatyList, e -> true);

        rabaty_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getIdRabatu().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getKodRabatu().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getZnizka().toString().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<rabatyData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(rabaty_tableView.comparatorProperty());
        rabaty_tableView.setItems(sortedList);
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
    public void close() {
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

    /**
     * Wylogowuje administratora z aplikacji.
     *
     * Metoda jest wywoływana po kliknięciu przycisku "logOutBtn". Wyświetla potwierdzenie operacji za pomocą okna dialogowego
     * typu CONFIRMATION. Jeśli użytkownik potwierdzi operację, zostaje zarejestrowane wylogowanie w dzienniku zdarzeń,
     * aktualne okno aplikacji zostaje ukryte, a aplikacja powraca do ekranu logowania. W przypadku błędu podczas wylogowywania
     * zostaje zgłoszony wyjątek typu RuntimeException.
     *
     * @throws RuntimeException jeśli wystąpił błąd podczas wylogowywania użytkownika
     */
    @FXML
    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdź Operacje");
            alert.setHeaderText(null);
            alert.setContentText("Napewno chcesz się wylogować ?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logger.info("Administrotr o ID: " + adminID + " wylogowal sie");
                logOutBtn.getScene().getWindow().hide();
                DesktopShopProject desktopShopProject = new DesktopShopProject();
                Stage stage = new Stage();
                desktopShopProject.start(stage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Przełącza widoki między layoutami w aplikacji.
     *
     * Metoda jest wywoływana po kliknięciu na przyciski nawigacji.
     * Na podstawie źródła zdarzenia (event.getSource()), metoda zmienia widoczność odpowiednich paneli oraz
     * dostosowuje styl tła przycisków nawigacji. Dodatkowo, dla niektórych paneli, metoda wywołuje określone akcje,
     * takie jak pobranie danych, wyszukiwanie, aktualizacja wyświetlanych informacji itp.
     *
     * @param event zdarzenie, które wywołało metodę (kliknięcie na przycisk nawigacji)
     */
    public void switchForm(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            homePane.setVisible(true);
            itemsPane.setVisible(false);
            customerPane.setVisible(false);
            orderPane.setVisible(false);
            employePane.setVisible(false);
            discountPane.setVisible(false);

            homeBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            itemBtn.setStyle("-fx-background-color: transparent");
            customerBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            employeBtn.setStyle("-fx-background-color: transparent");
            discountBtn.setStyle("-fx-background-color: transparent");

            homeChart();
            homePracownicy();
            homeKlienci();
            homeZamowienia();
        } else if (event.getSource() == itemBtn) {
            homePane.setVisible(false);
            itemsPane.setVisible(true);
            customerPane.setVisible(false);
            orderPane.setVisible(false);
            employePane.setVisible(false);
            discountPane.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            itemBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            customerBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            employeBtn.setStyle("-fx-background-color: transparent");
            discountBtn.setStyle("-fx-background-color: transparent");

            addItemsShowListData();
            produktyKategoria();
            produktySearch();

        } else if (event.getSource() == customerBtn) {
            homePane.setVisible(false);
            itemsPane.setVisible(false);
            customerPane.setVisible(true);
            orderPane.setVisible(false);
            employePane.setVisible(false);
            discountPane.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            itemBtn.setStyle("-fx-background-color: transparent");
            customerBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            orderBtn.setStyle("-fx-background-color: transparent");
            employeBtn.setStyle("-fx-background-color: transparent");
            discountBtn.setStyle("-fx-background-color: transparent");

            klienciShowListData();
            klienciSearch();

        } else if (event.getSource() == orderBtn) {
            homePane.setVisible(false);
            itemsPane.setVisible(false);
            customerPane.setVisible(false);
            orderPane.setVisible(true);
            employePane.setVisible(false);
            discountPane.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            itemBtn.setStyle("-fx-background-color: transparent");
            customerBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            employeBtn.setStyle("-fx-background-color: transparent");
            discountBtn.setStyle("-fx-background-color: transparent");

            zamowieniaShowListData();
            zamowieniaStatusZamowienia();
            zamowieniaSearch();

        } else if (event.getSource() == employeBtn) {
            homePane.setVisible(false);
            itemsPane.setVisible(false);
            customerPane.setVisible(false);
            orderPane.setVisible(false);
            employePane.setVisible(true);
            discountPane.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            itemBtn.setStyle("-fx-background-color: transparent");
            customerBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            employeBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            discountBtn.setStyle("-fx-background-color: transparent");

            pracownicyShowListData();
            pracownicyKategoria();
            pracownicySearch();

        } else if (event.getSource() == discountBtn) {
            homePane.setVisible(false);
            itemsPane.setVisible(false);
            customerPane.setVisible(false);
            orderPane.setVisible(false);
            employePane.setVisible(false);
            discountPane.setVisible(true);

            homeBtn.setStyle("-fx-background-color: transparent");
            itemBtn.setStyle("-fx-background-color: transparent");
            customerBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            employeBtn.setStyle("-fx-background-color: transparent");
            discountBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");

            rabatyShowListData();
            rabatySearch();
        }
    }

    /**
     * Inicjalizuje kontroler widoku po załadowaniu sceny.
     *
     * Metoda jest wywoływana automatycznie po załadowaniu sceny i inicjalizuje stan początkowy kontrolera widoku.
     * Ustawia styl tła przycisku "itemsBtn" na przezroczysty. Wyświetla nazwę klienta zalogowanego użytkownika.
     * Wywołuje odpowiednie metody do pobrania danych, wyszukiwania, ustawienia spinnera, aktualizacji cen, itp.
     *
     * @param url             URL sceny, która została załadowana
     * @param resourceBundle zasoby powiązane z kontrolerem widoku
     */
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        homeBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
        homePracownicy();
        homeKlienci();
        homeZamowienia();
        homeChart();

        addItemsShowListData();
        produktyKategoria();
        produktySearch();

        klienciShowListData();
        klienciSearch();

        pracownicyShowListData();
        pracownicyKategoria();
        pracownicySearch();

        rabatyShowListData();
        rabatySearch();

        zamowieniaShowListData();
        zamowieniaStatusZamowienia();
        zamowieniaSearch();
        try {
            nameLabel.setText(LoginController.adminName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
