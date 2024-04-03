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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.dekstopshopproject.LoginController.clientId;

/**
 * Kontroler interfejsu uzytkownika
 */
public class ClientDashBoardController implements Initializable {

    private static final Logger logger = LogManager.getLogger(ClientDashBoardController.class);

    @FXML
    private Button accountBtn;

    @FXML
    private AnchorPane accountPane;

    @FXML
    private Button cartBtn;

    @FXML
    private AnchorPane cartPane;

    @FXML
    private Button closeBtn;

    @FXML
    private Button itemsBtn;

    @FXML
    private AnchorPane itemsPane;

    @FXML
    private Button logOutBtn;

    @FXML
    private Button minBtn;

    @FXML
    private Label nameLabel;
    @FXML
    private Button usunKontoBtn;

    @FXML
    private Button orderBtn;

    @FXML
    private AnchorPane orderPane;
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
    private TableView<itemData> produkty_ProduktyTableView;

    @FXML
    private TableColumn<itemData, String> produkty_idProdutku;

    @FXML
    private TextField produkty_searchField;
    @FXML
    private Button koszyk_enterBtn;


    @FXML
    private AnchorPane wishlListPane;

    @FXML
    private Button wishlistBtn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_cenaColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_dataDostawyColumn;

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
    private TextField zamowienia_searchField;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_sposobDostawyColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_statusColumn;

    @FXML
    private TableView<zamowieniaData> zamowienia_tableView;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_telColumn;

    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_ulicaColumn;

    @FXML
    private TextField zamowienia_ulicaTextField;


    @FXML
    private TableColumn<cartData, String> koszyk_idProduktu;

    @FXML
    private TableColumn<cartData, String> koszyk_Producent;

    @FXML
    private TextField koszyk_cenaCalkowitaLabel;

    @FXML
    private TableColumn<cartData, String> koszyk_cenaSztuki;

    @FXML
    private ComboBox<?> koszyk_dostawaComboBox;

    @FXML
    private TableColumn<cartData, String> koszyk_iloscSztuk;

    @FXML
    private TableColumn<cartData, String> koszyk_kategoria;

    @FXML
    private TextField koszyk_kodPocztowyTextField;

    @FXML
    private TextField koszyk_kodRabatowyTextField;

    @FXML
    private TextField koszyk_miejscowoscTextField;

    @FXML
    private TableColumn<cartData, String> koszyk_nazwaProduktu;

    @FXML
    private TextField koszyk_nrBudynkuTextField;

    @FXML
    private TextField koszyk_nrLokaluTextField;

    @FXML
    private ComboBox<?> koszyk_platnosciComboBox;

    @FXML
    private TextField koszyk_ulicaTextField;

    @FXML
    private TableView<cartData> koszyk_tableView;

    @FXML
    private Spinner<Integer> produkty_spinner;
    @FXML
    private TextField koszyk_searchField;
    @FXML
    private Button koszyk_clearBtn;
    @FXML
    private TableColumn<wishlistData, String> wishlist_Cena;

    @FXML
    private TableColumn<wishlistData, String> wishlist_DataProdukcji;

    @FXML
    private TableColumn<wishlistData, String> wishlist_Dostepnosc;

    @FXML
    private TableColumn<wishlistData, String> wishlist_Kategoria;

    @FXML
    private TableColumn<wishlistData, String> wishlist_NazwaProduktu;

    @FXML
    private TableColumn<wishlistData, String> wishlist_Producent;

    @FXML
    private TableView<wishlistData> wishlist_ProduktyTableView;

    @FXML
    private TableColumn<wishlistData, String> wishlist_idProdutku;

    @FXML
    private TextField wishlist_searchField;

    @FXML
    private Spinner<Integer> wishlist_spinner;
    @FXML
    private TableColumn<klienciData, String> konto_dataZalozeniaColumn;

    @FXML
    private PasswordField konto_hasloTextField;

    @FXML
    private TableColumn<klienciData, String> konto_imieColumn;

    @FXML
    private TextField konto_imieTextField;

    @FXML
    private TableColumn<klienciData, String> konto_liczbaZamowienColumn;

    @FXML
    private TableColumn<klienciData, String> konto_mailColumn;

    @FXML
    private TextField konto_mailTextField;

    @FXML
    private TableColumn<klienciData, String> konto_nazwiskoColumn;

    @FXML
    private TextField konto_nazwiskoTextField;

    @FXML
    private TableColumn<klienciData, String> konto_nrTelefonuColumn;

    @FXML
    private TextField konto_numerTelefonuTextField;

    @FXML
    private TableView<klienciData> konto_tableView;
    @FXML
    private TableColumn<zamowieniaData, String> zamowienia_dataZlozeniaColumn;

    @FXML
    private Button kontaktBtn;

    @FXML
    private TableView<pracownicyData> kontakt_TableView;

    @FXML
    private TableColumn<pracownicyData, String> kontakt_imieColumn;

    @FXML
    private TableColumn<pracownicyData, String> kontakt_mailColumn;

    @FXML
    private TableColumn<pracownicyData, String> kontakt_nazwiskoColumn;

    @FXML
    private TableColumn<pracownicyData, String> kontakt_nrTelefonuColumn;

    @FXML
    private TextField kontakt_searchField;

    @FXML
    private TableColumn<pracownicyData, String> kontakt_stanowiskoColumn;
    @FXML
    private AnchorPane kontaktPane;

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
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_producentProduktu;

    @FXML
    private TableColumn<zamowieniaZawartoscData, String> zamowienia_nazwaProduktu;

    @FXML
    private TextField zamowienia_zawartoscSearchField;

    private double cenaCalkowita;

    private Integer ilosc;

    private SpinnerValueFactory<Integer> spinner, spinnerWishlist;

    DecimalFormat decimalFormat = new DecimalFormat("#.##");

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private ObservableList<itemData> addItemsList = FXCollections.observableArrayList();
    private ObservableList<cartData> cartList = FXCollections.observableArrayList();
    private ObservableList<zamowieniaData> zamowieniaList = FXCollections.observableArrayList();
    private ObservableList<wishlistData> wishlistList = FXCollections.observableArrayList();
    private ObservableList<klienciData> klienciList = FXCollections.observableArrayList();
    private ObservableList<pracownicyData> pracownicyList = FXCollections.observableArrayList();
    private ObservableList<zamowieniaZawartoscData> zamowieniaZawartoscList = FXCollections.observableArrayList();
    private boolean wasPressed = false;

    private String[] sposobDostawyLista = {"Kurier z dostawą jutro 29.99 zł", "Kurier 14.99 zł", "KPaczkomaty 24/7 6.99 zł"};
    private String[] sposobPlatnosciLista = {"Google Pay 0 zł", "Karta płatnicza przez Internet 0 zł", "Szybki przelew online 2.99 zł", "Apple Pay 0 zł", "Blik 3.99 zł", "Przelew tradycyjny 5.99 zł"};

    /**
     * Pobiera dane produktów z bazy danych i zwraca je jako listę obiektów typu itemData.
     *
     * Metoda wykonuje zapytanie SQL do bazy danych w celu pobrania wszytskich produktów. Następnie tworzy obiekty typu itemData
     * na podstawie otrzymanych wyników i dodaje je do listy ObservableList. Ostatecznie zwraca utworzoną listę.
     *
     * @return ObservableList zawierająca dane produktów.
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
     * Aktualizuje widok tabeli produktów przez dodanie danych z listy addItemsListData().
     *
     * Metoda pobiera dane produktów za pomocą metody addItemsListData() i wykorzystuje je do aktualizacji widoku tabeli produktów.
     * Ustawia odpowiednie wartości dla każdej kolumny tabeli, używając PropertyValueFactory do powiązania z właściwościami obiektów itemData.
     * Ostatecznie ustawia elementy tabeli na podstawie otrzymanych danych.
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
     * Wyszukuje produkty na podstawie wprowadzonego tekstu i aktualizuje widok tabeli produktów.
     *
     * Metoda wykonuje wyszukiwanie produktów na podstawie wprowadzonego tekstu w polu wyszukiwania.
     * Wykorzystuje filtr FilteredList, który zawiera listę produktów (addItemsList) i inicjalizuje go z predykatem przyjmującym wszystkie elementy.
     * Następnie dodaje nasłuchiwanie zmian w tekście pola wyszukiwania i ustawia nowy predykat filtru zgodnie z wprowadzonym tekstem.
     * Wyszukiwanie jest wykonane na podstawie kilku kryteriów, takich jak identyfikator produktu, nazwa produktu, producent, cena, dostępność,
     * nazwa kategorii oraz data wyprodukowania. Ostatecznie sortuje i ustawia elementy tabeli produktów zgodnie z wynikami wyszukiwania.
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

    /**
     * Konfiguruje spinner dla ilości produktów.
     *
     * Metoda tworzy nową instancję klasy SpinnerValueFactory.IntegerSpinnerValueFactory.
     * Ustawia zakres wartości spinnera od 1 do 10, z domyślną wartością początkową 1.
     * Służy do określenia ilości sztuk produktu którego użytkownik chce dodać do koszyka
     */
    public void produktySpinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        produkty_spinner.setValueFactory(spinner);
    }

    /**
     * Przypisuje zmiennej ilosc liczbe pobrną ze spinnera.
     *
     * Jej zadaniem jest przekazanie wybranej ilości sztuk danego produktu ze spinnera przez użytkownika do bazy danych po dodaniu do koszyka
     */
    public void produktyQty() {
        ilosc = produkty_spinner.getValue();
    }

    /**
     * Dodaje wybrany produkt do koszyka.
     *
     * Metoda wykonuje operację dodawania produktu do koszyka na podstawie wybranego elementu z tabeli produktów.
     * Sprawdza dostępność produktu oraz ilość dostępnych sztuk przed dodaniem do koszyka.
     * W przypadku przekroczenia dostępnej ilości lub błędu w wykonaniu operacji, wyświetlane są odpowiednie alerty z informacjami.
     * Po udanym dodaniu, aktualizuje informacje w bazie danych i wyświetla komunikat o powodzeniu.
     */
    public void produktyAddToCart() {
        String sql = "INSERT INTO zawartosci_koszykow (id_koszyka, id_produktu, liczba_sztuk) VALUES ( ?, ?, ?)";
        itemData itemD = produkty_ProduktyTableView.getSelectionModel().getSelectedItem();
        try {
            String avaibleCheck = "SELECT * FROM (\n" +
                    "    SELECT SUM(zawartosci_koszykow.liczba_sztuk + '" + produkty_spinner.getValue() + "') AS suma_przedmiotow\n" +
                    "    FROM zawartosci_koszykow\n" +
                    "    JOIN produkty ON zawartosci_koszykow.id_produktu = produkty.id_produktu\n" +
                    "    WHERE zawartosci_koszykow.id_koszyka = '" + clientId + "'\n" +
                    "        AND produkty.id_produktu = '" + itemD.getItemId() + "'\n" +
                    ") AS subquery\n" +
                    "WHERE suma_przedmiotow > (SELECT liczba_dostepnych FROM produkty WHERE id_produktu = '" + itemD.getItemId() + "')";
            prepare = connect.prepareStatement(sql);

            String checkNull = "SELECT liczba_dostepnych FROM produkty WHERE id_produktu = '" + itemD.getItemId() + "'";

            prepare.setInt(1, clientId);
            prepare.setInt(2, itemD.getItemId());
            prepare.setInt(3, produkty_spinner.getValue());

            result = statement.executeQuery(checkNull);
            result.next();
            Integer liczba = result.getInt("liczba_dostepnych");

            if (liczba < spinner.getValue()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Przekroczono ilość dostepnych w sklepie sztuk dla produktu: " + itemD.getNazwaProduktu());
                spinner.setValue(1);
                alert.showAndWait();
            } else if (prepare.executeQuery(avaibleCheck).next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Przekroczono ilość dostepnych w sklepie sztuk dla produktu: " + itemD.getNazwaProduktu());
                spinner.setValue(1);
                alert.showAndWait();
            } else {

                if (prepare.executeQuery("SELECT * FROM zawartosci_koszykow WHERE id_koszyka = '" + clientId + "' AND id_produktu = '" + itemD.getItemId() + "'").next()) {

                    sql = "UPDATE zawartosci_koszykow SET liczba_sztuk = liczba_sztuk + '" + produkty_spinner.getValue() + "' WHERE id_koszyka = '" + clientId + "' AND id_produktu = '" + itemD.getItemId() + "'";

                    prepare.executeUpdate(sql);

                    logger.info("Użytkownik: " + clientId + " dodał: " + produkty_spinner.getValue() +" sztuk produktu: " + itemD.getNazwaProduktu() + " do koszyka");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodano pomyślnie");
                    spinner.setValue(1);
                    alert.showAndWait();
                } else {


                    logger.info("Użytkownik: " + clientId + " dodał: " + produkty_spinner.getValue() +" sztuk produktu: " + itemD.getNazwaProduktu() + " do koszyka");
                    prepare.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodano pomyślnie");
                    spinner.setValue(1);
                    alert.showAndWait();
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć produkt który chcesz dodać do koszyka !");
            spinner.setValue(1);
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Dodaje wybrany produkt do listy życzeń użytkownika.
     *
     * Metoda wykonuje operację dodawania produktu do listy życzeń na podstawie wybranego elementu z tabeli produktów.
     * Sprawdza, czy produkt już znajduje się na liście życzeń użytkownika przed dodaniem.
     * W przypadku już istniejącego produktu na liście, wyświetla odpowiedni alert z informacją.
     * Po udanym dodaniu, aktualizuje informacje w bazie danych i wyświetla komunikat o powodzeniu.
     */
    public void produktyAddToWishList() {
        String sql = "INSERT INTO lista_przedmiotow (id_lista_zyczen, id_produktu) VALUES ( ?, ?)";
        itemData itemD = produkty_ProduktyTableView.getSelectionModel().getSelectedItem();

        try {
            prepare = connect.prepareStatement(sql);


            prepare.setInt(1, clientId);
            prepare.setInt(2, itemD.getItemId());

            if (prepare.executeQuery("SELECT * FROM lista_przedmiotow WHERE id_lista_zyczen = '" + clientId + "' AND id_produktu = '" + itemD.getItemId() + "'").next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Produkt: " + itemD.getNazwaProduktu() + " już jest na liście życzeń");
                spinner.setValue(1);
                alert.showAndWait();
            } else {

                logger.info("Użytkownik: " + clientId + " dodał porokt o id: " + itemD.getItemId() + " do listy zyczen");
                prepare.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Dodano pomyślnie");
                spinner.setValue(1);
                alert.showAndWait();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć produkt który chcesz dodać do listy życzeń !");
            spinner.setValue(1);
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Pobiera dane dotyczące produktów w koszyku użytkownika.
     *
     * Metoda wykonuje zapytanie do bazy danych w celu pobrania danych dotyczących produktów w koszyku
     * użytkownika o danym identyfikatorze klienta (clientId). Następnie tworzy obiekty klasy cartData
     * na podstawie pobranych wyników i dodaje je do listy listData. Zwraca finalną listę z danymi.
     *
     * @return Lista danych produktów w koszyku użytkownika.
     */
    public ObservableList<cartData> cartListData() {
        ObservableList<cartData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM produkty JOIN zawartosci_koszykow ON produkty.id_produktu = zawartosci_koszykow.id_produktu WHERE zawartosci_koszykow.id_koszyka = '" + clientId + "'";

        connect = BazaDanych.connectToDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            cartData cartD;

            while (result.next()) {
                cartD = new cartData(result.getInt("produkty.id_produktu")
                        , result.getString("zawartosci_koszykow.liczba_sztuk")
                        , result.getString("produkty.nazwa")
                        , result.getString("produkty.nazwa_producenta")
                        , result.getString("produkty.nazwa_kategorii")
                        , result.getDouble("produkty.cena"));

                listData.add(cartD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Wyświetla dane dotyczące produktów w koszyku w tabeli.
     *
     * Metoda pobiera dane dotyczące produktów w koszyku, wywołując metodę cartListData(),
     * a następnie przypisuje odpowiednie wartości do poszczególnych kolumn tabeli koszyka.
     * Dodatkowo umożliwia edycję ilości sztuk produktów w koszyku poprzez interakcję z tabelą.
     * Po dokonaniu zmiany ilości sztuk, aktualizuje dane w bazie danych i wywołuje metodę
     * showFullPrice() w celu odświeżenia ceny całkowitej koszyku.
     */
    public void koszykShowListData() {

        cartList = cartListData();
        koszyk_idProduktu.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        koszyk_iloscSztuk.setCellFactory(TextFieldTableCell.forTableColumn());
        koszyk_iloscSztuk.setCellValueFactory(new PropertyValueFactory<>("liczbaSztuk"));
        koszyk_nazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaProduktu"));
        koszyk_Producent.setCellValueFactory(new PropertyValueFactory<>("nazwaProducenta"));
        koszyk_kategoria.setCellValueFactory(new PropertyValueFactory<>("nazwaKategori"));
        koszyk_cenaSztuki.setCellValueFactory(cellData -> {
            Double cena = cellData.getValue().getCenaSztuki();
            String cenaZl = cena + " zł";
            return new SimpleStringProperty(cenaZl);
        });

        koszyk_iloscSztuk.setOnEditCommit(event -> {
            try {

                String newQuantity = event.getNewValue();

                int rowIndex = event.getTablePosition().getRow();

                cartData cartD = koszyk_tableView.getItems().get(rowIndex);

                cartD.setLiczbaSztuk(newQuantity);

                cartData selectedItem = koszyk_tableView.getSelectionModel().getSelectedItem();

                String sql = "UPDATE zawartosci_koszykow SET liczba_sztuk = '" + newQuantity + "' WHERE id_koszyka = '" + clientId + "' AND id_produktu = '" + cartD.getItemId() + "'";


                String avaibleCheck = "SELECT zawartosci_koszykow.liczba_sztuk FROM zawartosci_koszykow\n" +
                        "JOIN produkty ON zawartosci_koszykow.id_produktu = produkty.id_produktu\n" +
                        "WHERE zawartosci_koszykow.id_koszyka = '" + clientId + "' AND produkty.id_produktu = '" + cartD.getItemId() + "' AND '" + selectedItem.getLiczbaSztuk() + "' > produkty.liczba_dostepnych";

                statement = connect.createStatement();

                if (statement.executeQuery(avaibleCheck).next()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Przekroczono ilość dostepnych w sklepie sztuk dla produktu: " + cartD.getNazwaProduktu());
                    koszykShowListData();
                    alert.showAndWait();
                } else {

                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    showFullPrice();
                }
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wprowadzono nieprawidłowy format !");
                koszykShowListData();
                alert.showAndWait();
            }
        });

        koszyk_tableView.setItems(cartList);

    }

    /**
     * Wyświetla cenę całkowitą koszyka wraz z uwzględnieniem dodatkowych kosztów, takich jak koszt dostawy, koszt płatności
     * i ewentualny rabat.
     *
     * Metoda oblicza pełną cenę koszyka na podstawie danych zawartych w tabeli zawartosci_koszykow w bazie danych.
     * Dodatkowo uwzględnia koszt dostawy i koszt płatności wybranych przez użytkownika. Jeśli został wprowadzony kod rabatowy,
     * to zostaje zastosowany odpowiedni rabat. Wartość pełnej ceny jest wyświetlana w etykiecie koszyk_cenaCalkowitaLabel.
     * Metoda również obsługuje zdarzenie naciśnięcia przycisku koszyk_clearBtn, które przywraca pierwotną wartość ceny całkowitej.
     */
    public void showFullPrice() {


        String sql = "SELECT ROUND(SUM((cena*liczba_sztuk)),2) AS cena_calkowita\n" +
                "FROM zawartosci_koszykow \n" +
                "JOIN produkty ON zawartosci_koszykow.id_produktu = produkty.id_produktu\n" +
                "WHERE id_koszyka = '" + clientId + "'";

        String sposob_dostawy = "SELECT koszt_dostawy FROM sposoby_dostawy WHERE id_sposobu_dostawy ='" + (koszyk_dostawaComboBox.getSelectionModel().getSelectedIndex() + 1) + "'";
        String sposob_platnosci = "SELECT koszt_sposobu_platnosci FROM sposoby_platnosci WHERE id_platnosci ='" + (koszyk_platnosciComboBox.getSelectionModel().getSelectedIndex() + 1) + "'";
        String rabat = "SELECT procent_znizki FROM rabaty WHERE kod = '" + koszyk_kodRabatowyTextField.getText() + "'";

        try {
            statement = connect.createStatement();
            result = statement.executeQuery(sql);
            if (result.next()) {
                cenaCalkowita = result.getDouble("cena_calkowita");
                double baza = cenaCalkowita;


                if (koszyk_clearBtn.isFocused()) {
                    cenaCalkowita = baza;
                    wasPressed = false;
                }


                if (koszyk_enterBtn.isFocused()) {

                    if (!koszyk_kodRabatowyTextField.getText().isEmpty()) {
                        result = statement.executeQuery(rabat);
                        if (result.next()) {
                            double procentZnizki = result.getDouble("procent_znizki");
                            if (procentZnizki != 0) {
                                cenaCalkowita -= (cenaCalkowita * procentZnizki / 100);
                                Alert alert = new Alert((Alert.AlertType.INFORMATION));
                                alert.setTitle("Information Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Wprowadzono pooprawny kod rabatowy");
                                alert.showAndWait();
                                wasPressed = true;
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Wprowadzono błędny kod rabatowy");
                            alert.showAndWait();
                            koszyk_kodRabatowyTextField.clear();
                        }
                    }
                }

                // Pozostała część kodu bez zmian
                if (koszyk_dostawaComboBox.getSelectionModel().getSelectedItem() == null || koszyk_platnosciComboBox.getSelectionModel().getSelectedItem() == null) {
                    if (koszyk_dostawaComboBox.getSelectionModel().getSelectedItem() == null && koszyk_platnosciComboBox.getSelectionModel().getSelectedItem() != null) {
                        result = statement.executeQuery(sposob_platnosci);
                        if (result.next()) {
                            cenaCalkowita += result.getDouble("koszt_sposobu_platnosci");
                        }
                        koszyk_cenaCalkowitaLabel.setText(String.valueOf(decimalFormat.format(cenaCalkowita)) + " zł");
                    } else if (koszyk_platnosciComboBox.getSelectionModel().getSelectedItem() == null && koszyk_dostawaComboBox.getSelectionModel().getSelectedItem() != null) {
                        result = statement.executeQuery(sposob_dostawy);
                        if (result.next()) {
                            cenaCalkowita += result.getDouble("koszt_dostawy");
                        }
                        koszyk_cenaCalkowitaLabel.setText(String.valueOf(decimalFormat.format(cenaCalkowita)) + " zł");
                    } else {
                        koszyk_cenaCalkowitaLabel.setText(String.valueOf(decimalFormat.format(cenaCalkowita)) + " zł");
                    }
                } else {
                    result = statement.executeQuery(sposob_dostawy);
                    if (result.next()) {
                        cenaCalkowita += result.getDouble("koszt_dostawy");
                    }
                    result = statement.executeQuery(sposob_platnosci);
                    if (result.next()) {
                        cenaCalkowita += result.getDouble("koszt_sposobu_platnosci");
                    }
                    koszyk_cenaCalkowitaLabel.setText(String.valueOf(decimalFormat.format(cenaCalkowita)) + " zł");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ustawia dostępne opcje sposobu dostawy w ComboBoxie.
     *
     * Metoda pobiera listę dostępnych sposobów dostawy (sposobDostawyLista) i dodaje je do ComboBoxa koszyk_dostawaComboBox,
     * umożliwiając użytkownikowi wybór jednej z opcji. Każda opcja jest reprezentowana jako tekstowa wartość.
     * Metoda konwertuje listę na ObservableList i przypisuje go do ComboBoxa, dzięki czemu opcje są widoczne dla użytkownika.
     */
    public void sposobDostawy() {
        List<String> listS = new ArrayList<>();

        for (String data : sposobDostawyLista) {
            listS.add(data);
        }

        ObservableList listD = FXCollections.observableArrayList(listS);
        koszyk_dostawaComboBox.setItems(listD);
    }

    /**
     * Ustawia dostępne opcje sposobu płatności w ComboBoxie.
     *
     * Metoda pobiera listę dostępnych sposobów płatności (sposobPlatnosciLista) i dodaje je do ComboBoxa koszyk_platnosciComboBox,
     * umożliwiając użytkownikowi wybór jednej z opcji. Każda opcja jest reprezentowana jako tekstowa wartość.
     * Metoda konwertuje listę na ObservableList i przypisuje go do ComboBoxa, dzięki czemu opcje są widoczne dla użytkownika.
     */
    public void sposobPlatnosci() {
        List<String> listS = new ArrayList<>();

        for (String data : sposobPlatnosciLista) {
            listS.add(data);
        }

        ObservableList listD = FXCollections.observableArrayList(listS);
        koszyk_platnosciComboBox.setItems(listD);
    }

    /**
     * Wyszukuje i filtruje dane w tabeli koszyka na podstawie wprowadzonego tekstu w polu wyszukiwania.
     *
     * Metoda umożliwia użytkownikowi wyszukiwanie konkretnych pozycji w tabeli koszyka na podstawie wprowadzonego tekstu.
     * Tworzy filtr oparty na wprowadzonym tekście i stosuje go do listy danych koszyka (cartList),
     * aby wyświetlić tylko pasujące do kryteriów wyszukiwania pozycje w tabeli.
     * Filtr uwzględnia kilka kolumn w tabeli, takich jak ID przedmiotu, nazwa, producent, cena, liczba sztuk i kategoria.
     * Jeśli wprowadzony tekst jest pusty, wszystkie pozycje są wyświetlane.
     * Wynikowy posortowany zbiór danych jest przypisywany do tabeli koszyka (koszyk_tableView) w celu wyświetlenia wyników.
     */
    public void koszykSearch() {

        FilteredList<cartData> filter = new FilteredList<>(cartList, e -> true);

        koszyk_searchField.textProperty().addListener((observable, oldValue, newValue) -> {

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
                } else if (predicateItemData.getCenaSztuki().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getLiczbaSztuk().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaKategori().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<cartData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(koszyk_tableView.comparatorProperty());
        koszyk_tableView.setItems(sortedList);
    }

    /**
     * Czyści zawartość pól formularza koszyka oraz wybór sposobu dostawy i płatności.
     *
     * Metoda resetuje zawartość pól tekstowych (miejscowość, kod pocztowy, ulica, nr budynku, nr lokalu, kod rabatowy)
     * oraz kasuje wybór sposobu dostawy i płatności w formularzu koszyka.
     * Po wyczyszczeniu pól formularza, metoda wywołuje ponowne obliczenie i wyświetlenie pełnej ceny zamówienia.
     */
    public void koszykClear() {

        koszyk_miejscowoscTextField.setText("");
        koszyk_kodPocztowyTextField.setText("");
        koszyk_ulicaTextField.setText("");
        koszyk_nrBudynkuTextField.setText("");
        koszyk_nrLokaluTextField.setText("");
        koszyk_kodRabatowyTextField.setText("");
        koszyk_dostawaComboBox.getSelectionModel().clearSelection();
        koszyk_platnosciComboBox.getSelectionModel().clearSelection();

        showFullPrice();
    }

    /**
     * Usuwa zaznaczony produkt z koszyka.
     *
     * Metoda pobiera zaznaczony produkt z tabeli koszyka, wyświetla potwierdzenie usunięcia
     * i w przypadku potwierdzenia usuwa produkt z bazy danych w zawartosci koszyka odpowiedniego użytkownika. Następnie odświeża widok koszyka,
     * oraz oblicza i wyświetla ponownie pełną cenę zamówienia.
     * Jeśli nie został zaznaczony żaden produkt, wyświetla odpowiedni komunikat o błędzie.
     * W przypadku wystąpienia nieoczekiwanego wyjątku, zapisuje informację o wyjątku w logach.
     */
    public void koszykDelete() {

        cartData cartD = koszyk_tableView.getSelectionModel().getSelectedItem();
        int num = koszyk_tableView.getSelectionModel().getSelectedIndex();

        try {
            String sql = "DELETE FROM zawartosci_koszykow WHERE id_produktu = '" + cartD.getItemId() + "' AND id_koszyka = '" + clientId + "'";
            connect = BazaDanych.connectToDb();

            if ((num - 1) < -1) {
                return;
            }

            Alert alert;


            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);

            alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ produkt z koszyka o ID: " + cartD.getItemId() + " ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                logger.info("Użytkownik: " + clientId + " usunal produkt o ID: " + cartD.getItemId() + " z koszyka");
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Usunięto pomyślnie");
                alert.showAndWait();
                koszykShowListData();
                koszykClear();
                showFullPrice();
            }


        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć produkt który chcesz usunąć z koszyka !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }

    }

    /**
     * Tworzy zamówienie na podstawie danych z koszyka.
     *
     * Metoda pobiera informacje o kliencie, kodzie rabatowym, wybranej dostawie i płatności,
     * a następnie tworzy nowe zamówienie w bazie danych na podstawie tych danych. Dodaje
     * również zawartość zamówienia do bazy danych. Jeśli wszystkie wymagane pola są uzupełnione,
     * wyświetla potwierdzenie złożenia zamówienia. Po potwierdzeniu, zamówienie jest dodawane
     * do bazy danych, a zawartość koszyka jest usuwana. Informacje o zamówieniu i zmianie
     * stanu magazynowego są rejestrowane w logach. W przypadku błędów lub wyjątków, wyświetlane
     * są odpowiednie komunikaty lub informacje o błędzie, a także logowane są informacje o wyjątku.
     */
    public void addToOrder() {
        String getCustomerInfo = "SELECT imie, nazwisko, mail, nr_telefonu FROM uzytkownik WHERE id_uzytkownika = '" + clientId + "'";
        String getDiscountID = "SELECT id_rabatu FROM rabaty WHERE kod = '" + koszyk_kodRabatowyTextField.getText() + "'";
        String insertIntoOrder = "INSERT INTO zamowienia (id_koszyka, id_uzytkownika, data_zlozenia, status_zamowienia, id_rabatu, id_platnosci, cena_calkowita,imie,nazwisko,mail,nr_telefonu) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        String deleteFromCart = "DELETE FROM zawartosci_koszykow WHERE id_koszyka = '" + clientId + "'";
        String idProduktuSelect = "SELECT id_produktu FROM zawartosci_koszykow WHERE id_koszyka = '" + clientId + "'";
        String insertIntoShipping = "INSERT INTO dostawy (id_zamowienia, id_sposobu_dostawy, miejscowosc, kod_pocztowy, ulica, numer_domu, numer_lokalu, przewidywany_termin_dostawy) VALUES (?,?,?,?,?,?,?,?)";
        String getOrderID = "SELECT id_zamowienia FROM zamowienia  WHERE id_uzytkownika = '" + clientId + "'ORDER BY id_zamowienia DESC LIMIT 1";
        String insertIntoZawartoscZamowienia = "INSERT INTO zawartosc_zamowienia (id_zamowienia,id_produktu,liczba_sztuk,cena_przed_zakupem) VALUES (?,?,?,?)";

        connect = BazaDanych.connectToDb();

        try {
            Alert alert;

            if (koszyk_miejscowoscTextField.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze wprowadźić miejscowość do której ma zostać dostarczone zamówienie");
                alert.showAndWait();
            } else if (koszyk_kodPocztowyTextField.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze wprowadźić kod pocztowy miejscowości do której ma zostać dostarczone zamówienie");
                alert.showAndWait();
            } else if (koszyk_ulicaTextField.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze wprowadźić ulice na której znajduję się budynek do którgo ma zostać dostarczone zamówienie");
                alert.showAndWait();
            } else if (koszyk_nrBudynkuTextField.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze wprowadźić numer budynku do którgo ma zostać dostarczone zamówienie");
                alert.showAndWait();
            } else if (koszyk_dostawaComboBox.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze wybrać sposób dostawy");
                alert.showAndWait();
            } else if (koszyk_platnosciComboBox.getSelectionModel().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Prosze wybrać sposób płatności");
                alert.showAndWait();

            } else {
                String imie = null;
                String nazwisko = null;
                String mail = null;
                String nr_telefonu = null;
                int rabatID = 1;


                LocalDate localDate = LocalDate.now();
                java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

                statement = connect.createStatement();
                result = statement.executeQuery(getCustomerInfo);

                if (result.next()) {
                    imie = result.getString("imie");
                    nazwisko = result.getString("nazwisko");
                    mail = result.getString("mail");
                    nr_telefonu = result.getString("nr_telefonu");
                }

                result = statement.executeQuery(getDiscountID);

                if (result.next() && wasPressed == true) {
                    rabatID = Integer.parseInt(result.getString("id_rabatu"));
                    logger.info("Użytkownik: " + clientId + " uzył kodu rabatowego o id: " + rabatID);
                }


                prepare = connect.prepareStatement(insertIntoOrder);

                prepare.setInt(1, clientId);
                prepare.setInt(2, clientId);
                prepare.setDate(3, sqlDate);
                prepare.setString(4, "oczekuje na realizacje");
                prepare.setInt(5, rabatID);
                prepare.setInt(6, (koszyk_platnosciComboBox.getSelectionModel().getSelectedIndex()) + 1);
                prepare.setDouble(7, cenaCalkowita);
                prepare.setString(8, imie);
                prepare.setString(9, nazwisko);
                prepare.setString(10, mail);
                prepare.setString(11, nr_telefonu);

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);

                alert.setContentText("Jestes pewnien ze chcesz złożyć zamówienie i zapłacić: " + decimalFormat.format(cenaCalkowita) + "zł ?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {


                    prepare.executeUpdate();

                    prepare = connect.prepareStatement(insertIntoZawartoscZamowienia);

                    ArrayList<Integer> idProduktuLista = new ArrayList<>();
                    ArrayList<Integer> koszykQuantitytoZawartoscZamowienia = new ArrayList<>();
                    ArrayList<Double> koszykCenaProduktu = new ArrayList<>();

                    int orderID = 0;

                    result = prepare.executeQuery(getOrderID);

                    if (result.next()) {
                        orderID = result.getInt("id_zamowienia");
                    }

                    result = prepare.executeQuery(idProduktuSelect);

                    while (result.next()) {
                        int idProduktu = result.getInt("id_produktu");
                        idProduktuLista.add(idProduktu);
                    }

                    for (int idProduktu : idProduktuLista) {
                        String getOrderedItemQuantity = "SELECT liczba_sztuk FROM zawartosci_koszykow WHERE id_koszyka = '" + clientId + "' AND id_produktu = '" + idProduktu + "'";
                        String getProcuctPrice = "SELECT cena FROM produkty WHERE id_produktu = '" + idProduktu + "'";
                        result = statement.executeQuery(getOrderedItemQuantity);
                        if (result.next()) {
                            int koszykIlosc = result.getInt("liczba_sztuk");
                            koszykQuantitytoZawartoscZamowienia.add(koszykIlosc);
                        }
                        result = statement.executeQuery(getProcuctPrice);
                        if (result.next()) {
                            double produktPrice = result.getDouble("cena");
                            koszykCenaProduktu.add(produktPrice);
                        }
                    }

                    for (int i = 0; i < idProduktuLista.size(); i++) {
                        int idProduktu = idProduktuLista.get(i);
                        int koszykIlosc = koszykQuantitytoZawartoscZamowienia.get(i);
                        double produktPrice = koszykCenaProduktu.get(i);

                        prepare.setInt(1, orderID);
                        prepare.setInt(2, idProduktu);
                        prepare.setInt(3, koszykIlosc);
                        prepare.setDouble(4, produktPrice);
                        prepare.executeUpdate();
                    }


                    LocalDate days;

                    if (koszyk_dostawaComboBox.getSelectionModel().getSelectedIndex() == 0) {
                        days = localDate.plusDays(1);
                    } else {
                        days = localDate.plusDays(7);
                    }

                    Date futureDate = Date.valueOf(days);

                    result = prepare.executeQuery(getOrderID);


                    prepare = connect.prepareStatement(insertIntoShipping);

                    prepare.setInt(1, orderID);
                    prepare.setInt(2, (koszyk_dostawaComboBox.getSelectionModel().getSelectedIndex()) + 1);
                    prepare.setString(3, koszyk_miejscowoscTextField.getText());
                    prepare.setString(4, koszyk_kodPocztowyTextField.getText());
                    prepare.setString(5, koszyk_ulicaTextField.getText());
                    prepare.setString(6, koszyk_nrBudynkuTextField.getText());
                    prepare.setString(7, koszyk_nrLokaluTextField.getText());
                    prepare.setDate(8, futureDate);

                    prepare.executeUpdate();

                    ArrayList<Integer> idLista = new ArrayList<>();


                    result = statement.executeQuery(idProduktuSelect);
                    while (result.next()) {
                        int idProduktu = result.getInt("id_produktu");
                        idLista.add(idProduktu);
                    }

                    for (int idProduktu : idLista) {
                        String getOrderedItemQuantity = "SELECT liczba_sztuk FROM zawartosci_koszykow WHERE id_koszyka = '" + clientId + "' AND id_produktu = '" + idProduktu + "'";
                        result = statement.executeQuery(getOrderedItemQuantity);
                        if (result.next()) {
                            int koszykQuantity = result.getInt("liczba_sztuk");
                            String updateQuantity = "UPDATE produkty SET liczba_dostepnych = liczba_dostepnych - '" + koszykQuantity + "' WHERE id_produktu = '" + idProduktu + "'";
                            statement.executeUpdate(updateQuantity);
                        }
                    }

                    statement.executeUpdate(deleteFromCart);

                    logger.info("Użytkownik: " + clientId + " złożył nowe zamówienie o id: " + orderID);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Złożono pomyślnie !");
                    alert.showAndWait();
                    koszykShowListData();
                    koszykClear();
                    showFullPrice();
                }

            }


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
     * Pobiera listę danych dotyczących zamówień klienta.
     *
     * Metoda wykonuje zapytanie do bazy danych w celu pobrania informacji dotyczących zamówień
     * danego klienta. Następnie tworzy obiekty klasy `zamowieniaData` na podstawie otrzymanych danych
     * i dodaje je do listy `ObservableList`. Zapytanie bazodanowe łączy trzy tabele: `zamowienia`,
     * `dostawy` i `sposoby_dostawy` w celu uzyskania pełnych informacji o zamówieniu, dostawie i sposobie dostawy.
     * Ostatecznie, zwraca utworzoną listę danych.
     *
     * @return Lista danych dotyczących zamówień klienta.
     */
    public ObservableList<zamowieniaData> zamowieniaListData() {
        ObservableList<zamowieniaData> listData = FXCollections.observableArrayList();

        String sql = "SELECT zamowienia.id_zamowienia, zamowienia.id_uzytkownika,data_zlozenia, status_zamowienia, cena_calkowita,imie,nazwisko,mail,nr_telefonu,miejscowosc, kod_pocztowy,ulica, numer_domu, numer_lokalu, przewidywany_termin_dostawy,nazwa FROM zamowienia\n" +
                "JOIN dostawy ON zamowienia.id_zamowienia = dostawy.id_zamowienia\n" +
                "JOIN sposoby_dostawy ON dostawy.id_sposobu_dostawy = sposoby_dostawy.id_sposobu_dostawy WHERE zamowienia.id_uzytkownika = '" + clientId + "'";

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
     * Wyświetla dane zamówień w tabeli.
     *
     * Metoda pobiera listę danych dotyczących zamówień klienta przy użyciu metody `zamowieniaListData()`.
     * Następnie konfiguruje kolumny tabeli, przypisując odpowiednie wartości do poszczególnych komórek
     * na podstawie właściwości obiektów `zamowieniaData`. Kolumny są skonfigurowane z użyciem klasy
     * `PropertyValueFactory` oraz lambda wyrażenia dla kolumny "cenaColumn", aby przekształcić wartość
     * ceny zamówienia na postać tekstową z oznaczeniem waluty. Na koniec, ustawia utworzoną listę danych
     * jako źródło danych dla tabeli.
     */
    public void zamowieniaShowListData() {
        zamowieniaList = zamowieniaListData();

        zamowienia_idZamowieniaColumn.setCellValueFactory(new PropertyValueFactory<>("zamowienieId"));
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
     * Pobiera dane dotyczące zawartości zamówienia i zwraca je jako listę obiektów typu `zamowieniaZawartoscData`.
     *
     * Metoda pobiera dane dotyczące produktów zawartych w wybranym zamówieniu klienta.
     * Wykorzystuje wartość zaznaczonego elementu w tabeli zamówień (`zamowienia_tableView`) jako
     * obiekt `zamowieniaData` dla którego pobiera identyfikator zamówienia (`zamowieniaD.getZamowienieId()`).
     * Następnie, na podstawie identyfikatora zamówienia, pobiera dane dotyczące zawartości zamówienia
     * z bazy danych za pomocą zapytania SQL. Wyniki zapytania są przetwarzane w pętli, a każdy rekord
     * jest konwertowany na obiekt typu `zamowieniaZawartoscData` i dodawany do listy danych `listData`.
     * Na koniec, zwraca utworzoną listę danych.
     *
     * @return lista danych zawartości zamówienia (obiekty typu `zamowieniaZawartoscData`).
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
     * Wyświetla dane dotyczące zawartości zamówienia w tabeli.
     *
     * Metoda pobiera listę danych zawartości zamówienia (`zamowieniaZawartoscList`) za pomocą
     * wywołania metody `zamowieniaZawartoscListData()`. Następnie, konfiguruje komórki w tabeli
     * `zamowienia_ZawartoscTableView`, aby pobierały odpowiednie wartości z obiektów typu `zamowieniaZawartoscData`.
     * Ustala powiązania pomiędzy nazwami kolumn a nazwami pól w obiektach `zamowieniaZawartoscData`.
     * Dodatkowo, dla kolumny zawierającej cenę produktu, zastosowano niestandardową funkcję fabryczną
     * `cellData -> { ... }`, która konwertuje wartość ceny na format tekstowy z symbolem waluty "zł".
     * Na koniec, ustawia dane w tabeli na podstawie utworzonej listy danych `zamowieniaZawartoscList`.
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
     * Wyświetla wybrane dane zamówienia i inicjuje wyświetlanie zawartości zamówienia.
     *
     * Metoda pobiera wybraną pozycję z tabeli `zamowienia_tableView` za pomocą metody
     * `getSelectedItem()`, a następnie pobiera indeks wybranej pozycji za pomocą metody
     * `getSelectedIndex()`. Jeżeli indeks jest mniejszy od -1, metoda kończy działanie.
     * W przeciwnym razie, ustawia wartości pól tekstowych na odpowiednie dane z obiektu
     * typu `zamowieniaData`. Pole tekstowe `zamowienia_kodPocztowyTextField` otrzymuje
     * wartość kodu pocztowego, pole tekstowe `zamowienia_miastoTextField` otrzymuje wartość
     * miasta, pole tekstowe `zamowienia_ulicaTextField` otrzymuje wartość ulicy,
     * pole tekstowe `zamowienia_nrBudynkuTextField` otrzymuje wartość numeru budynku,
     * a pole tekstowe `zamowienia_nrMieszkaniaTextField` otrzymuje wartość numeru mieszkania.
     * Następnie, wywołuje metody `zamowieniaZawartoscShowListData()` i `zamowieniaZawartoscSearch()`,
     * aby wyświetlić zawartość zamówienia oraz przeprowadzić wyszukiwanie w tej zawartości.
     */
    public void zamowieniaSelect() {
        zamowieniaData zamowieniaD = zamowienia_tableView.getSelectionModel().getSelectedItem();
        int num = zamowienia_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        zamowienia_kodPocztowyTextField.setText(String.valueOf(zamowieniaD.getKodPocztowy()));
        zamowienia_miastoTextField.setText(String.valueOf(zamowieniaD.getMiasto()));
        zamowienia_ulicaTextField.setText(String.valueOf(zamowieniaD.getUlica()));
        zamowienia_nrBudynkuTextField.setText(String.valueOf(zamowieniaD.getNrBudynku()));
        zamowienia_nrMieszkaniaTextField.setText(String.valueOf(zamowieniaD.getNrMieszkania()));

        zamowieniaZawartoscShowListData();
        zamowieniaZawartoscSearch();

    }

    /**
     * Czyści pola tekstowe z danymi zamówienia.
     *
     * Metoda ustawia puste wartości w polach tekstowych `zamowienia_kodPocztowyTextField`,
     * `zamowienia_miastoTextField`, `zamowienia_ulicaTextField`, `zamowienia_nrBudynkuTextField`
     * oraz `zamowienia_nrMieszkaniaTextField`. Powoduje to wyczyszczenie tych pól z wcześniej
     * wprowadzonych danych zamówienia, umożliwiając wprowadzenie nowych danych.
     */
    public void zamowieniaClear() {

        zamowienia_kodPocztowyTextField.setText("");
        zamowienia_miastoTextField.setText("");
        zamowienia_ulicaTextField.setText("");
        zamowienia_nrBudynkuTextField.setText("");
        zamowienia_nrMieszkaniaTextField.setText("");
    }

    /**
     * Wyszukuje i filtruje dane zamówień na podstawie podanego tekstu.
     *
     * Metoda wykonuje wyszukiwanie i filtrowanie danych w tabeli zamówień na podstawie
     * podanego tekstu w polu wyszukiwania (`zamowienia_searchField`). Wykorzystuje
     * mechanizm `FilteredList` oraz `SortedList` w celu filtrowania i sortowania danych.
     *
     * Wyszukiwanie  sprawdza, czy podany tekst występuje w różnych kolumnach danych zamówień, takich jak identyfikator
     * zamówienia, identyfikator klienta, imię klienta, nazwisko klienta, adres e-mail
     * klienta, numer telefonu klienta, kod pocztowy, miasto, ulica, numer budynku,
     * numer mieszkania, status zamówienia, data dostawy, nazwa sposobu dostawy oraz
     * cena zamówienia.
     *
     * Jeśli podany tekst jest pusty lub `null`, wszystkie dane zamówień zostaną wyświetlone.
     * W przeciwnym razie zostaną wyświetlone tylko te dane, które pasują do podanego tekstu.
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
     * Aktualizuje dane zamówienia na podstawie wprowadzonych zmian.
     *
     * Metoda aktualizuje dane zamówienia na podstawie wprowadzonych zmian w polach
     * tekstowych, takich jak miasto, kod pocztowy, ulica, numer budynku i numer mieszkania.
     * W przypadku braku wprowadzonych zmian w żadnym z pól, metoda nie wykonuje żadnej
     * aktualizacji.
     *
     * Przed aktualizacją sprawdzane jest, czy wszystkie pola tekstowe są puste. Jeśli tak,
     * wyświetlany jest komunikat o błędzie. W przeciwnym razie, po potwierdzeniu przez
     * użytkownika, metoda wykonuje odpowiednie aktualizacje w bazie danych. Po zakończeniu
     * aktualizacji wyświetlany jest komunikat informujący o sukcesie, a lista zamówień jest
     * odświeżana i pola tekstowe zostają wyczyszczone.
     *
     * Jeśli nie jest zaznaczone żadne zamówienie w tabeli, wyświetlany jest komunikat o błędzie.
     * Jeśli wprowadzone dane przekraczają dozwoloną liczbę znaków w bazie danych, wyświetlany
     * jest odpowiedni komunikat o błędzie.
     *
     * Metoda obsługuje również ogólne wyjątki, które mogą wystąpić podczas operacji na bazie
     * danych lub w przypadku nieprzewidzianych błędów.
     */
    public void zamowieniaUpdate() {

        zamowieniaData zamowieniaD = zamowienia_tableView.getSelectionModel().getSelectedItem();

        try {

            String cityUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET miejscowosc = '" + zamowienia_miastoTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String postCodeUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET kod_pocztowy = '" + zamowienia_kodPocztowyTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String streetUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET ulica = '" + zamowienia_ulicaTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String buildingUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET numer_domu = '" + zamowienia_nrBudynkuTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";
            String placeUpdate = "UPDATE dostawy JOIN zamowienia ON dostawy.id_zamowienia = zamowienia.id_zamowienia SET numer_lokalu = '" + zamowienia_nrMieszkaniaTextField.getText() + "'WHERE zamowienia.id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";

            connect = BazaDanych.connectToDb();
            Alert alert;

            if (zamowienia_miastoTextField.getText().isEmpty()
                    && zamowienia_kodPocztowyTextField.getText().isEmpty()
                    && zamowienia_ulicaTextField.getText().isEmpty()
                    && zamowienia_nrBudynkuTextField.getText().isEmpty()
                    && zamowienia_nrMieszkaniaTextField.getText().isEmpty()) {

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

                boolean anyFieldUpdated = miastoUpdated || kodPocztowyUpdated || ulicaUpdated || nrBudynkuUpdated || nrMieszkaniaUpdated;

                if (anyFieldUpdated) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Jestes pewnien ze chcesz ZAKTUALIZOWAC zamówienie o ID: " + zamowieniaD.getZamowienieId() + " ?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {

                        if (miastoUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktualizował miasto dostawy zamowienia o ID: " + zamowieniaD.getZamowienieId() + " na miasto: " + zamowienia_miastoTextField.getText());
                            statement.executeUpdate(cityUpdate);
                        }

                        if (kodPocztowyUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktualizował kod-pocztowy dostawy zamowienia o ID: " + zamowieniaD.getZamowienieId() + " na kod pocztowy: " + zamowienia_kodPocztowyTextField.getText());
                            statement.executeUpdate(postCodeUpdate);
                        }

                        if (ulicaUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktualizował ulice dostawy zamowienia o ID: " + zamowieniaD.getZamowienieId() + " na ulice: " + zamowienia_ulicaTextField.getText());
                            statement.executeUpdate(streetUpdate);
                        }

                        if (nrBudynkuUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktualizował nr Budynku dostawy zamowienia o ID: " + zamowieniaD.getZamowienieId() + " na nr Budynku: " + zamowienia_nrBudynkuTextField.getText());
                            statement.executeUpdate(buildingUpdate);
                        }

                        if (nrMieszkaniaUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktualizował nr Lokalu dostawy zamowienia o ID: " + zamowieniaD.getZamowienieId() + " na lokal: " + zamowienia_nrMieszkaniaTextField.getText());
                            statement.executeUpdate(placeUpdate);
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
     * Anuluje wybrane zamówienie na podstawie zaznaczenia w tabeli.
     *
     * Metoda anuluje wybrane zamówienie na podstawie zaznaczenia w tabeli. Najpierw pobiera
     * wybrane zamówienie z tabeli i tworzy zapytanie SQL, które ustawia status zamówienia
     * na "anulowano". Następnie wyświetla potwierdzenie dla użytkownika, czy na pewno chce
     * anulować wybrane zamówienie. Po potwierdzeniu, metoda wykonuje zapytanie SQL, loguje
     * operację anulowania zamówienia, wyświetla komunikat informujący o sukcesie, odświeża
     * listę zamówień i czyści pola tekstowe.
     *
     * Jeśli nie jest zaznaczone żadne zamówienie w tabeli, wyświetlany jest komunikat o błędzie.
     * Metoda obsługuje również ogólne wyjątki, które mogą wystąpić podczas operacji na bazie
     * danych lub w przypadku nieprzewidzianych błędów.
     */
    public void anulujZamowienie() {

        zamowieniaData zamowieniaD = zamowienia_tableView.getSelectionModel().getSelectedItem();

        try {
            String sql = "UPDATE zamowienia SET status_zamowienia = 'anulowano' WHERE id_zamowienia = '" + zamowieniaD.getZamowienieId() + "'";

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Jestes pewnien ze chcesz anulować zamowienie o ID: " + zamowieniaD.getZamowienieId() + " ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logger.info("Użytkownik: " + clientId + " anulowal zamowienie o ID: " + zamowieniaD.getZamowienieId());
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Anulowano pomyślnie");
                alert.showAndWait();
                zamowieniaShowListData();
                zamowieniaClear();
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć zamówienie które chcesz anulować !");
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Filtruje zawartość tabeli zamówieniaZawartoscTableView na podstawie wprowadzonego wyszukiwania.
     *
     * Metoda zawiera funkcjonalność filtrowania zawartości tabeli `zamowieniaZawartoscTableView`
     * na podstawie wprowadzonego wyszukiwania w polu `zamowienia_zawartoscSearchField`. Wykorzystuje
     * `FilteredList`, aby utworzyć filtr oparty na `zamowieniaZawartoscList` (lista danych tabeli).
     * Następnie słucha zmian w tekście wyszukiwania i aktualizuje filtr w zależności od wprowadzonych
     * wartości. Filtruje dane na podstawie kilku kolumn tabeli, takich jak ID przedmiotu, cena produktu,
     * nazwa producenta, nazwa kategorii i liczba sztuk. Na koniec, wynikowy przefiltrowany zestaw danych
     * jest sortowany i przypisywany do tabeli `zamowienia_ZawartoscTableView`.
     */
    public void zamowieniaZawartoscSearch() {

        FilteredList<zamowieniaZawartoscData> filter = new FilteredList<>(zamowieniaZawartoscList, e -> true);

        zamowienia_zawartoscSearchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getItemId().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getCenaProduktu().toString().indexOf(searchKey) != -1) {
                    return true;
                }else if(predicateItemData.getNazwaProduktu().toString().indexOf(searchKey) != -1) {
                    return true;
                }else if (predicateItemData.getNazwaProducenta().toLowerCase().indexOf(searchKey) != -1) {
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
     * Pobiera dane przedmiotów na liście życzeń użytkownika.
     *
     * Metoda wykonuje zapytanie SQL, aby pobrać dane przedmiotów na liście życzeń
     * użytkownika o podanym identyfikatorze `clientId`. Zapytanie łączy tabelę
     * `produkty` z tabelą `lista_przedmiotow` na podstawie identyfikatora produktu.
     * Następnie tworzy obiekty klasy `wishlistData` na podstawie wyników zapytania
     * i dodaje je do listy obserwowalnej `listData`. Kolejnośc kolumn wynikowych:
     * ID produktu, nazwa, nazwa producenta, cena, liczba dostępnych, nazwa kategorii,
     * data wyprodukowania. Po zakończeniu przetwarzania zwraca listę typu ObservableList
     * zawierającą dane przedmiotów na liście życzeń.
     *
     * @return Lista  zawierająca dane przedmiotów na liście życzeń użytkownika.
     */
    public ObservableList<wishlistData> wishlistListData() {
        ObservableList<wishlistData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM produkty JOIN lista_przedmiotow ON produkty.id_produktu = lista_przedmiotow.id_produktu WHERE id_lista_zyczen = '" + clientId + "'";

        connect = BazaDanych.connectToDb();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            wishlistData wishlistD;

            while (result.next()) {
                wishlistD = new wishlistData(result.getInt("id_produktu")
                        , result.getString("nazwa")
                        , result.getString("nazwa_producenta")
                        , result.getDouble("cena")
                        , result.getInt("liczba_dostepnych")
                        , result.getString("nazwa_kategorii")
                        , result.getDate("data_wyprodukowania"));

                listData.add(wishlistD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    /**
     * Wyświetla dane przedmiotów na liście życzeń w widoku tabeli.
     *
     * Metoda pobiera dane przedmiotów na liście życzeń, wywołując metodę `wishlistListData()`.
     * Następnie konfiguruje kolumny w widoku tabeli, aby wyświetlić odpowiednie wartości dla
     * poszczególnych pól obiektów klasy `wishlistData`. Ustawia fabrykę wartości dla każdej kolumny,
     * aby przypisać odpowiednie właściwości obiektów `wishlistData` do komórek tabeli. Kolejnośc
     * kolumn: ID produktu, nazwa produktu, producent, cena, dostępność, kategoria, data produkcji.
     * Na koniec, ustawia listę obserwowalną `wishlistList` jako źródło danych dla tabeli `wishlist_ProduktyTableView`.
     */
    public void wishlistShowListData() {
        wishlistList = wishlistListData();
        wishlist_idProdutku.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        wishlist_NazwaProduktu.setCellValueFactory(new PropertyValueFactory<>("nazwaProduktu"));
        wishlist_Producent.setCellValueFactory(new PropertyValueFactory<>("nazwaProducenta"));
        wishlist_Cena.setCellValueFactory(cellData -> {
            Double cena = cellData.getValue().getCenaProduktu();
            String cenaZl = cena + " zł";
            return new SimpleStringProperty(cenaZl);
        });
        wishlist_Dostepnosc.setCellValueFactory(new PropertyValueFactory<>("dostepnoscProdutku"));
        wishlist_Kategoria.setCellValueFactory(new PropertyValueFactory<>("nazwaKategori"));
        wishlist_DataProdukcji.setCellValueFactory(new PropertyValueFactory<>("dataWyprodukowania"));

        wishlist_ProduktyTableView.setItems(wishlistList);

    }

    /**
     * Konfiguruje spinner liczby przedmiotów na liście życzeń.
     *
     * Metoda tworzy nową fabrykę wartości dla spinnera, która pozwala użytkownikowi wybrać
     * liczbę przedmiotów z zakresu od 1 do 10. Domyślnie ustawia wartość spinnera na 0. Następnie
     * przypisuje fabrykę wartości do kontrolki spinnera `wishlist_spinner`, aby umożliwić użytkownikowi
     * wybieranie liczby przedmiotów na liście życzeń.
     */
    public void wishlistSpinner() {
        spinnerWishlist = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        wishlist_spinner.setValueFactory(spinnerWishlist);
    }

    /**
     * Dodaje wybrany przedmiot z listy życzeń do koszyka.
     *
     * Metoda pobiera wybrany przedmiot z tabeli `wishlist_ProduktyTableView` i dodaje go do koszyka. Sprawdza
     * dostępność przedmiotu w sklepie oraz sprawdza, czy liczba sztuk przekracza dostępność. Jeśli liczba
     * sztuk przekracza dostępność, wyświetla komunikat o błędzie. W przeciwnym razie dodaje przedmiot do koszyka
     * lub aktualizuje liczbę sztuk, jeśli przedmiot już znajduje się w koszyku. Po dodaniu przedmiotu wyświetla
     * komunikat o sukcesie. Jeśli nie wybrano żadnego przedmiotu, wyświetla odpowiedni komunikat.
     *
     * Metoda obsługuje również wyjątki, loguje je i wyświetla odpowiednie komunikaty błędów.
     */
    public void wishlistAddToCart() {
        String sql = "INSERT INTO zawartosci_koszykow (id_koszyka, id_produktu, liczba_sztuk) VALUES ( ?, ?, ?)";
        try {
            wishlistData wishlistD = wishlist_ProduktyTableView.getSelectionModel().getSelectedItem();
            String avaibleCheck = "SELECT * FROM (\n" +
                    "    SELECT SUM(zawartosci_koszykow.liczba_sztuk + '" + wishlist_spinner.getValue() + "') AS suma_przedmiotow\n" +
                    "    FROM zawartosci_koszykow\n" +
                    "    JOIN produkty ON zawartosci_koszykow.id_produktu = produkty.id_produktu\n" +
                    "    WHERE zawartosci_koszykow.id_koszyka = '" + clientId + "'\n" +
                    "        AND produkty.id_produktu = '" + wishlistD.getItemId() + "'\n" +
                    ") AS subquery\n" +
                    "WHERE suma_przedmiotow > (SELECT liczba_dostepnych FROM produkty WHERE id_produktu = '" + wishlistD.getItemId() + "')";
            prepare = connect.prepareStatement(sql);


            prepare.setInt(1, clientId);
            prepare.setInt(2, wishlistD.getItemId());
            prepare.setInt(3, wishlist_spinner.getValue());


            if (prepare.executeQuery(avaibleCheck).next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Przekroczono ilość dostepnych w sklepie sztuk dla produktu: " + wishlistD.getNazwaProduktu());
                spinnerWishlist.setValue(1);
                alert.showAndWait();
            } else {


                if (prepare.executeQuery("SELECT * FROM zawartosci_koszykow WHERE id_koszyka = '" + clientId + "' AND id_produktu = '" + wishlistD.getItemId() + "'").next()) {

                    sql = "UPDATE zawartosci_koszykow SET liczba_sztuk = liczba_sztuk + '" + wishlist_spinner.getValue() + "' WHERE id_koszyka = '" + clientId + "' AND id_produktu = '" + wishlistD.getItemId() + "'";

                    logger.info("Użytkownik: " + clientId + " dodał: " + spinnerWishlist.getValue() +" sztuk produktu: " + wishlistD.getNazwaProduktu() + " do listy zyczen");

                    prepare.executeUpdate(sql);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodano pomyślnie");
                    spinnerWishlist.setValue(1);
                    alert.showAndWait();
                } else {

                    logger.info("Użytkownik: " + clientId + " dodał: " + spinnerWishlist.getValue() +" sztuk produktu: " + wishlistD.getNazwaProduktu() + " do listy zyczen");

                    prepare.executeUpdate();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Dodano pomyślnie");
                    spinner.setValue(1);
                    alert.showAndWait();
                }
            }
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć produkt który chcesz dodać do koszyka !");
            spinner.setValue(1);
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }
    }

    /**
     * Usuwa wybrany przedmiot z listy życzeń.
     *
     * Metoda pobiera wybrany przedmiot z tabeli `wishlist_ProduktyTableView` i usuwa go z listy życzeń.
     * Wyświetla potwierdzenie z prośbą o potwierdzenie usunięcia przedmiotu. Jeśli użytkownik potwierdzi
     * usunięcie, metoda usuwa przedmiot z bazy danych i wyświetla komunikat o sukcesie. Następnie odświeża
     * widok listy życzeń. Jeśli nie wybrano żadnego przedmiotu, wyświetla odpowiedni komunikat.
     *
     * Metoda obsługuje również wyjątki, loguje je i wyświetla odpowiednie komunikaty błędów.
     */
    public void wishlistDelete() {

        wishlistData wishlistD = wishlist_ProduktyTableView.getSelectionModel().getSelectedItem();
        int num = wishlist_ProduktyTableView.getSelectionModel().getSelectedIndex();

        try {

            String sql = "DELETE FROM lista_przedmiotow WHERE id_produktu = '" + wishlistD.getItemId() + "' AND id_lista_zyczen = '" + clientId + "'";
            connect = BazaDanych.connectToDb();

            if ((num - 1) < -1) {
                return;
            }

            Alert alert;


            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);

            alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ produkt z listy życzeń o ID: " + wishlistD.getItemId() + " ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                logger.info("Użytkownik: " + clientId + " usunal  produkt: " + wishlistD.getNazwaProduktu() + " z listy zyczen");
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Usunięto pomyślnie");
                alert.showAndWait();

                wishlistShowListData();

            }


        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Proszę zaznaczyć produkt który chcesz usunąć z listy życzeń !");
            spinner.setValue(1);
            alert.showAndWait();
        } catch (Exception e) {
            logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
            e.printStackTrace();
        }

    }

    /**
     * Wyszukuje przedmioty na liście życzeń na podstawie wprowadzonych danych.
     *
     * Metoda umożliwia wyszukiwanie przedmiotów na liście życzeń na podstawie wartości wprowadzonych
     * do pola tekstowego `wishlist_searchField`. Wyszukiwanie odbywa się na podstawie kilku kryteriów,
     * takich jak identyfikator przedmiotu, cena, nazwa produktu, nazwa producenta, nazwa kategorii,
     * dostępność produktu oraz data produkcji. Metoda aktualizuje listę wyświetlanych przedmiotów
     * na podstawie wyników wyszukiwania.
     */
    public void wishlistSearch() {

        FilteredList<wishlistData> filter = new FilteredList<>(wishlistList, e -> true);

        wishlist_searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateItemData.getItemId().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getCenaProduktu().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaProduktu().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaProducenta().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwaKategori().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getDostepnoscProdutku().toString().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getDataWyprodukowania().toString().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<wishlistData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(wishlist_ProduktyTableView.comparatorProperty());
        wishlist_ProduktyTableView.setItems(sortedList);
    }


    /**
     * Pobiera dane klienta na podstawie identyfikatora klienta.
     *
     * Metoda pobiera dane klienta na podstawie identyfikatora klienta `clientId` z tabeli "uzytkownik".
     * Tworzy obiekty klasy `klienciData` na podstawie pobranych danych i dodaje je do listy `listData`.
     * Następnie zwraca tę listę.
     *
     * @return Lista zawierająca dane klienta.
     */
    public ObservableList<klienciData> klienciListData() {
        ObservableList<klienciData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM uzytkownik WHERE id_uzytkownika = '" + clientId + "'";

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
     * Wyświetla dane klienta w tabeli konta.
     *
     * Metoda pobiera dane klienta za pomocą metody `klienciListData()` i przypisuje je do listy `klienciList`.
     * Następnie konfiguruje mapowanie wartości do kolumn w tabeli `konto_tableView` na podstawie właściwości obiektów klasy `klienciData`.
     * Dodatkowo, dla kolumny "konto_liczbaZamowienColumn", wykonuje zapytanie do tabeli "zamowienia" w celu pobrania liczby zamówień dla danego klienta.
     * Wartość liczby zamówień jest obliczana na podstawie identyfikatora klienta `clientId`.
     * Na końcu ustawia elementy tabeli na podstawie `klienciList`.
     */
    public void klienciShowListData() {
        klienciList = klienciListData();

        konto_imieColumn.setCellValueFactory(new PropertyValueFactory<>("imieKlienta"));
        konto_nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("nazwiskoKlienta"));
        konto_mailColumn.setCellValueFactory(new PropertyValueFactory<>("mailKlienta"));
        konto_nrTelefonuColumn.setCellValueFactory(new PropertyValueFactory<>("nrTelKlienta"));
        konto_dataZalozeniaColumn.setCellValueFactory(new PropertyValueFactory<>("dataZalozeniaKonta"));
        konto_liczbaZamowienColumn.setCellValueFactory(cellData -> {
            String sql = "SELECT COUNT(id_zamowienia) AS suma_zamowien FROM zamowienia WHERE id_uzytkownika = '" + clientId + "'";
            Integer sumaZamowien = 0;
            try {
                result = statement.executeQuery(sql);

                if (result.next()) {
                    sumaZamowien = result.getInt("suma_zamowien");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }


            return new SimpleStringProperty(String.valueOf(sumaZamowien));
        });

        konto_tableView.setItems(klienciList);

    }

    /**
     * Czyści pola tekstowe w sekcji konta.
     *
     * Metoda ustawia puste wartości dla pól tekstowych w sekcji konta, takich jak imię, nazwisko, adres e-mail, hasło i numer telefonu.
     * Po wywołaniu tej metody, pola tekstowe zostaną wyczyszczone.
     */
    public void kontoClear() {

        konto_imieTextField.setText("");
        konto_nazwiskoTextField.setText("");
        konto_mailTextField.setText("");
        konto_hasloTextField.setText("");
        konto_numerTelefonuTextField.setText("");

    }

    /**
     * Aktualizuje dane konta użytkownika.
     *
     * Metoda służy do aktualizacji danych konta użytkownika na podstawie wprowadzonych zmian w polach tekstowych.
     * Sprawdza, które pola zostały zmienione, a następnie wykonuje odpowiednie zapytania aktualizujące dane w bazie danych.
     * Przed wykonaniem aktualizacji, metoda sprawdza, czy pola tekstowe nie są puste oraz czy wprowadzone adres e-mail
     * i numer telefonu nie są już zarejestrowane dla innego użytkownika. Po pomyślnej aktualizacji, wyświetla informację
     * o sukcesie, czyści pola tekstowe i odświeża listę danych konta.
     */
    public void kontoUpdate() {

        String updateImie = "UPDATE uzytkownik SET imie = '" + konto_imieTextField.getText() + "' WHERE id_uzytkownika = '" + clientId + "'";
        String updateNazwisko = "UPDATE uzytkownik SET nazwisko = '" + konto_nazwiskoTextField.getText() + "' WHERE id_uzytkownika = '" + clientId + "'";
        String updateMail = "UPDATE uzytkownik SET mail = '" + konto_mailTextField.getText() + "' WHERE id_uzytkownika = '" + clientId + "'";
        String updateNrTel = "UPDATE uzytkownik SET nr_telefonu = '" + konto_numerTelefonuTextField.getText() + "' WHERE id_uzytkownika = '" + clientId + "'";
        String updateHaslo = "UPDATE uzytkownik SET haslo = '" + konto_hasloTextField.getText() + "' WHERE id_uzytkownika = '" + clientId + "'";

        connect = BazaDanych.connectToDb();
        try {
            Alert alert;

            if (konto_imieTextField.getText().isEmpty()
                    && konto_nazwiskoTextField.getText().isEmpty()
                    && konto_mailTextField.getText().isEmpty()
                    && konto_numerTelefonuTextField.getText().isEmpty()
                    && konto_hasloTextField.getText().isEmpty()) {

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Wszystkie pola są pustę !");
                alert.showAndWait();
            } else if (prepare.executeQuery("SELECT * FROM uzytkownik WHERE mail = '" + konto_mailTextField.getText() + "'AND id_uzytkownika != '" + clientId + "'").next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Adres Mailowy: " + konto_mailTextField.getText() + " jest już zarejestrowany");
                alert.showAndWait();
            } else if (prepare.executeQuery("SELECT * FROM uzytkownik WHERE nr_telefonu = '" + konto_numerTelefonuTextField.getText() + "'AND id_uzytkownika != '" + clientId + "'").next()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Numer Telefonu: " + konto_numerTelefonuTextField.getText() + " jest już zarejestrowany");
                alert.showAndWait();
            } else {
                boolean imieUpdated = false;
                boolean nazwiskoUpdated = false;
                boolean mailUpdated = false;
                boolean nrTelefonuupdated = false;
                boolean hasloUpdated = false;


                if (!konto_imieTextField.getText().isEmpty()) {
                    imieUpdated = true;
                } else {
                    imieUpdated = false;
                }

                if (!konto_nazwiskoTextField.getText().isEmpty()) {
                    nazwiskoUpdated = true;
                } else {
                    nazwiskoUpdated = false;
                }

                if (!konto_numerTelefonuTextField.getText().isEmpty()) {
                    nrTelefonuupdated = true;
                } else {
                    nrTelefonuupdated = false;
                }

                if (!konto_mailTextField.getText().isEmpty()) {
                    mailUpdated = true;
                } else {
                    mailUpdated = false;
                }

                if (!konto_hasloTextField.getText().isEmpty()) {
                    hasloUpdated = true;
                } else {
                    hasloUpdated = false;
                }

                boolean anyFieldUpdated = imieUpdated || nazwiskoUpdated || mailUpdated || nrTelefonuupdated || hasloUpdated;

                if (anyFieldUpdated) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Jestes pewnien ze chcesz ZAKTUALIZOWAC dane konta ?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {

                        if (imieUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktaulizował imie na: " + konto_imieTextField.getText());
                            statement.executeUpdate(updateImie);
                        }

                        if (nazwiskoUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktaulizował nazwisko na: " + konto_nazwiskoTextField.getText());
                            statement.executeUpdate(updateNazwisko);
                        }

                        if (mailUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktaulizował mail na: " + konto_mailTextField.getText());
                            statement.executeUpdate(updateMail);
                        }

                        if (nrTelefonuupdated) {
                            logger.info("Użytkownik: " + clientId + " zaktaulizował numer telefonu na: " + konto_numerTelefonuTextField.getText());
                            statement.executeUpdate(updateNrTel);
                        }

                        if (hasloUpdated) {
                            logger.info("Użytkownik: " + clientId + " zaktaulizował haslo ");
                            statement.executeUpdate(updateHaslo);
                        }


                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Zaktualizowano pomyślnie");
                        alert.showAndWait();
                        klienciShowListData();
                        kontoClear();
                    }
                }
            }
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
     * Usuwa konto użytkownika wraz z powiązanymi danymi.
     *
     * Metoda służy do usunięcia konta użytkownika wraz z powiązanymi danymi, takimi jak zawartość koszyków, listy życzeń
     * oraz zamówienia. Najpierw wyświetla potwierdzenie usunięcia konta, a następnie wykonuje zapytania usuwające
     * powiązane dane z bazy danych. Po usunięciu konta, zamknie aktualne okno i otworzy nowe okno logowania i rejestracji.
     */
    public void kontoDelete() {

        String sql = "DELETE FROM zawartosci_koszykow WHERE id_koszyka IN (SELECT id_koszyka FROM koszyk WHERE id_uzytkownika = '" + clientId + "')";
        String sql2 = "DELETE FROM koszyk WHERE id_uzytkownika = '" + clientId + "'";
        String sql3 = "DELETE FROM lista_przedmiotow WHERE id_lista_zyczen IN (SELECT id_listy FROM lista_zyczen WHERE id_uzytkownika = '" + clientId + "')";
        String sql4 = "DELETE FROM lista_zyczen WHERE id_uzytkownika = '" + clientId + "'";
        String sql5 = "DELETE FROM uzytkownik WHERE id_uzytkownika = '" + clientId + "'";
        String sql6 = "UPDATE zamowienia SET id_uzytkownika = NULL WHERE id_uzytkownika = '" + clientId + "';";
        connect = BazaDanych.connectToDb();

        Alert alert;

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Jestes pewnien ze chcesz USUNĄĆ konto ?");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            try {
                logger.info("Użytkownik: " + clientId + " usunal konto ");
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                statement.executeUpdate(sql2);
                statement.executeUpdate(sql3);
                statement.executeUpdate(sql4);
                statement.executeUpdate(sql5);
                statement.executeUpdate(sql6);

                usunKontoBtn.getScene().getWindow().hide();
                DesktopShopProject desktopShopProject = new DesktopShopProject();
                Stage stage = new Stage();
                desktopShopProject.start(stage);


            } catch (Exception e) {
                logger.error("wykonał się nie przewidziany wyjątek w metodzie: " + e.getClass().getEnclosingMethod().getName());
                e.printStackTrace();
            }
        }

    }

    /**
     * Pobiera dane pracowników działu obsługi klienta.
     *
     * Metoda pobiera dane pracowników działu obsługi klienta z bazy danych i zwraca je w postaci listy obiektów typu
     * pracownicyData. Wykonuje zapytanie SQL, aby pobrać odpowiednie rekordy z tabeli pracownicy, gdzie stanowisko jest
     * ustawione na "Dział Obsługi Klienta". Następnie tworzy obiekty pracownicyData na podstawie wyników zapytania i dodaje
     * je do listy. Jeśli wystąpi błąd podczas pobierania danych, wypisuje stos śladów błędu.
     *
     * @return lista danych pracowników działu obsługi klienta
     */
    public ObservableList<pracownicyData> pracownicyListData() {
        ObservableList<pracownicyData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM pracownicy WHERE stanowisko = 'Dział Obsługi Klienta'";

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
     * Wyświetla dane pracowników działu obsługi klienta w tabeli kontakt_TableView.
     *
     * Metoda pobiera dane pracowników działu obsługi klienta, korzystając z metody pracownicyListData(),
     * a następnie wyświetla je w tabeli kontakt_TableView. Ustawia odpowiednie wartości dla kolumn w tabeli,
     * używając PropertyValueFactory do powiązania wartości z polami obiektów pracownicyData. W rezultacie
     * tabela zostaje zaktualizowana, wyświetlając dane pracowników.
     */
    public void pracownicyShowListData() {
        pracownicyList = pracownicyListData();

        kontakt_imieColumn.setCellValueFactory(new PropertyValueFactory<>("imiePracownika"));
        kontakt_nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("nazwiskoPracownika"));
        kontakt_nrTelefonuColumn.setCellValueFactory(new PropertyValueFactory<>("telPracownika"));
        kontakt_mailColumn.setCellValueFactory(new PropertyValueFactory<>("mialPracownika"));
        kontakt_stanowiskoColumn.setCellValueFactory(new PropertyValueFactory<>("stanowiskoPracownika"));


        kontakt_TableView.setItems(pracownicyList);

    }

    /**
     * Wyszukuje pracowników działu obsługi klienta na podstawie wprowadzonego tekstu w polu kontakt_searchField.
     *
     * Metoda wykonuje filtrowanie danych pracowników działu obsługi klienta na podstawie wprowadzonego tekstu w polu
     * kontakt_searchField. Tworzy obiekt FilteredList, który inicjalnie zawiera wszystkie elementy z listy pracowników.
     * Następnie ustawia Predicate dla filtru, sprawdzając czy wprowadzony tekst zawiera się w imieniu, nazwisku, adresie
     * mailowym, numerze telefonu lub stanowisku pracownika. Jeśli wprowadzony tekst jest pusty, wszystkie elementy są
     * zwracane. Wynik filtracji jest sortowany i przypisywany do tabeli kontakt_TableView, co powoduje wyświetlenie
     * wyników wyszukiwania.
     */
    public void pracownicySearch() {

        FilteredList<pracownicyData> filter = new FilteredList<>(pracownicyList, e -> true);

        kontakt_searchField.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateItemData -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();


                if (predicateItemData.getImiePracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getNazwiskoPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getMialPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getTelPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else if (predicateItemData.getStanowiskoPracownika().toLowerCase().indexOf(searchKey) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        SortedList<pracownicyData> sortedList = new SortedList<>(filter);
        sortedList.comparatorProperty().bind(kontakt_TableView.comparatorProperty());
        kontakt_TableView.setItems(sortedList);
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
        logger.info("Aplikacja została zamknieta");
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
     * Wylogowuje użytkownika z aplikacji.
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
                logger.info("Użytkownik: " + clientId + " wylogował się ");
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
     * Metoda jest wywoływana po kliknięciu na przyciski nawigacji (itemsBtn, cartBtn, orderBtn, wishlistBtn, accountBtn, kontaktBtn).
     * Na podstawie źródła zdarzenia (event.getSource()), metoda zmienia widoczność odpowiednich paneli oraz
     * dostosowuje styl tła przycisków nawigacji. Dodatkowo, dla niektórych paneli, metoda wywołuje określone akcje,
     * takie jak pobranie danych, wyszukiwanie, aktualizacja wyświetlanych informacji itp.
     *
     * @param event zdarzenie, które wywołało metodę (kliknięcie na przycisk nawigacji)
     */
    public void switchForm(ActionEvent event) {
        if (event.getSource() == itemsBtn) {
            itemsPane.setVisible(true);
            cartPane.setVisible(false);
            orderPane.setVisible(false);
            wishlListPane.setVisible(false);
            accountPane.setVisible(false);
            kontaktPane.setVisible(false);

            itemsBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            cartBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            wishlistBtn.setStyle("-fx-background-color: transparent");
            accountBtn.setStyle("-fx-background-color: transparent");
            kontaktBtn.setStyle("-fx-background-color: transparent");

            addItemsShowListData();
            produktySearch();
            produktySpinner();

        } else if (event.getSource() == cartBtn) {
            itemsPane.setVisible(false);
            cartPane.setVisible(true);
            orderPane.setVisible(false);
            wishlListPane.setVisible(false);
            accountPane.setVisible(false);
            kontaktPane.setVisible(false);

            itemsBtn.setStyle("-fx-background-color: transparent");
            cartBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            orderBtn.setStyle("-fx-background-color: transparent");
            wishlistBtn.setStyle("-fx-background-color: transparent");
            accountBtn.setStyle("-fx-background-color: transparent");
            kontaktBtn.setStyle("-fx-background-color: transparent");

            koszykShowListData();
            showFullPrice();
            koszyk_dostawaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                showFullPrice();
            });
            koszyk_platnosciComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                showFullPrice();
            });
            koszyk_enterBtn.setOnAction(e -> {
                showFullPrice(); // Wywołanie metody showFullPrice() po kliknięciu przycisku
            });
            sposobDostawy();
            sposobPlatnosci();
            koszykSearch();

        } else if (event.getSource() == orderBtn) {
            itemsPane.setVisible(false);
            cartPane.setVisible(false);
            orderPane.setVisible(true);
            wishlListPane.setVisible(false);
            accountPane.setVisible(false);
            kontaktPane.setVisible(false);

            itemsBtn.setStyle("-fx-background-color: transparent");
            cartBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            wishlistBtn.setStyle("-fx-background-color: transparent");
            accountBtn.setStyle("-fx-background-color: transparent");
            kontaktBtn.setStyle("-fx-background-color: transparent");

            zamowieniaShowListData();
            zamowieniaSearch();

        } else if (event.getSource() == wishlistBtn) {
            itemsPane.setVisible(false);
            cartPane.setVisible(false);
            orderPane.setVisible(false);
            wishlListPane.setVisible(true);
            accountPane.setVisible(false);
            kontaktPane.setVisible(false);

            itemsBtn.setStyle("-fx-background-color: transparent");
            cartBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            wishlistBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            accountBtn.setStyle("-fx-background-color: transparent");
            kontaktBtn.setStyle("-fx-background-color: transparent");

            wishlistShowListData();
            wishlistSpinner();
            wishlistSearch();

        } else if (event.getSource() == accountBtn) {
            itemsPane.setVisible(false);
            cartPane.setVisible(false);
            orderPane.setVisible(false);
            wishlListPane.setVisible(false);
            accountPane.setVisible(true);
            kontaktPane.setVisible(false);

            itemsBtn.setStyle("-fx-background-color: transparent");
            cartBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            wishlistBtn.setStyle("-fx-background-color: transparent");
            accountBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");
            kontaktBtn.setStyle("-fx-background-color: transparent");

            klienciShowListData();
        } else if (event.getSource() == kontaktBtn) {
            itemsPane.setVisible(false);
            cartPane.setVisible(false);
            orderPane.setVisible(false);
            wishlListPane.setVisible(false);
            accountPane.setVisible(false);
            kontaktPane.setVisible(true);

            itemsBtn.setStyle("-fx-background-color: transparent");
            cartBtn.setStyle("-fx-background-color: transparent");
            orderBtn.setStyle("-fx-background-color: transparent");
            wishlistBtn.setStyle("-fx-background-color: transparent");
            accountBtn.setStyle("-fx-background-color: transparent");
            kontaktBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");

            pracownicyShowListData();
            pracownicySearch();
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
    public void initialize(URL url, ResourceBundle resourceBundle) {

        itemsBtn.setStyle("-fx-background-color: rgba(255, 255, 255, 0.3)");

        try {
            nameLabel.setText(LoginController.clientName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addItemsShowListData();
        produktySearch();
        produktySpinner();


        koszykShowListData();
        showFullPrice();
        koszyk_dostawaComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showFullPrice();
        });
        koszyk_platnosciComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showFullPrice();
        });

        koszyk_enterBtn.setOnAction(e -> {
            showFullPrice(); // Wywołanie metody showFullPrice() po kliknięciu przycisku
        });
        sposobDostawy();
        sposobPlatnosci();
        koszykSearch();


        zamowieniaShowListData();
        zamowieniaSearch();

        wishlistShowListData();
        wishlistSpinner();
        wishlistSearch();

        klienciShowListData();

        pracownicyShowListData();
        pracownicySearch();
    }
}
