package com.example.dekstopshopproject;

import java.sql.Date;

/**
 * Klasa reprezentująca dane dotyczące zamówienia.
 *
 * Klasa `zamowieniaData` przechowuje informacje dotyczące zamówienia,
 * takie jak identyfikator zamówienia, identyfikator klienta, imię i nazwisko klienta,
 * adres e-mail klienta, numer telefonu klienta, kod pocztowy, miasto, ulica, numer budynku,
 * numer mieszkania, status zamówienia, cena zamówienia, data złożenia zamówienia,
 * data dostawy oraz nazwa sposobu dostawy. Dostarcza metody dostępowe do odczytu poszczególnych pól.
 */
public class zamowieniaData {
    private Integer zamowienieId, klientId;
    private String imieKlienta, nazwiskoKlienta, mailKlienta, nrTelKlienta, kodPocztowy, miasto, ulica, nrBudynku, nrMieszkania, statusZamowienia, nazwaSposobuDostawy;
    private Double cenaZamowienia;
    private Date dataDostawy, dataZlozenia;

    /**
     * Konstruktor parametryczny klasy zamowieniaData.
     *
     * @param zamowienieId           Identyfikator zamówienia.
     * @param klientId               Identyfikator klienta składającego zamówienie.
     * @param imieKlienta            Imię klienta.
     * @param nazwiskoKlienta        Nazwisko klienta.
     * @param mailKlienta            Adres e-mail klienta.
     * @param nrTelKlienta           Numer telefonu klienta.
     * @param kodPocztowy            Kod pocztowy.
     * @param miasto                 Miasto.
     * @param ulica                  Ulica.
     * @param nrBudynku              Numer budynku.
     * @param nrMieszkania           Numer mieszkania.
     * @param statusZamowienia       Status zamówienia.
     * @param cenaZamowienia         Cena zamówienia.
     * @param dataZlozenia           Data złożenia zamówienia.
     * @param dataDostawy            Data dostawy zamówienia.
     * @param nazwaSposobuDostawy    Nazwa sposobu dostawy.
     */
    public zamowieniaData(Integer zamowienieId, Integer klientId, String imieKlienta, String nazwiskoKlienta,
                          String mailKlienta, String nrTelKlienta, String kodPocztowy, String miasto,
                          String ulica, String nrBudynku, String nrMieszkania, String statusZamowienia,
                          Double cenaZamowienia, Date dataZlozenia, Date dataDostawy,
                          String nazwaSposobuDostawy) {
        this.zamowienieId = zamowienieId;
        this.klientId = klientId;
        this.imieKlienta = imieKlienta;
        this.nazwiskoKlienta = nazwiskoKlienta;
        this.mailKlienta = mailKlienta;
        this.nrTelKlienta = nrTelKlienta;
        this.kodPocztowy = kodPocztowy;
        this.miasto = miasto;
        this.ulica = ulica;
        this.nrBudynku = nrBudynku;
        this.nrMieszkania = nrMieszkania;
        this.statusZamowienia = statusZamowienia;
        this.cenaZamowienia = cenaZamowienia;
        this.dataZlozenia = dataZlozenia;
        this.dataDostawy = dataDostawy;
        this.nazwaSposobuDostawy = nazwaSposobuDostawy;
    }

    /**
     * Metoda zwracająca identyfikator zamówienia.
     *
     * @return Identyfikator zamówienia.
     */
    public Integer getZamowienieId() {
        return zamowienieId;
    }

    /**
     * Metoda zwracająca identyfikator klienta składającego zamówienie.
     *
     * @return Identyfikator klienta.
     */
    public Integer getKlientId() {
        return klientId;
    }

    /**
     * Metoda zwracająca imię klienta.
     *
     * @return Imię klienta.
     */
    public String getImieKlienta() {
        return imieKlienta;
    }

    /**
     * Metoda zwracająca nazwisko klienta.
     *
     * @return Nazwisko klienta.
     */
    public String getNazwiskoKlienta() {
        return nazwiskoKlienta;
    }

    /**
     * Metoda zwracająca adres e-mail klienta.
     *
     * @return Adres e-mail klienta.
     */
    public String getMailKlienta() {
        return mailKlienta;
    }

    /**
     * Metoda zwracająca numer telefonu klienta.
     *
     * @return Numer telefonu klienta.
     */
    public String getNrTelKlienta() {
        return nrTelKlienta;
    }

    /**
     * Metoda zwracająca kod pocztowy.
     *
     * @return Kod pocztowy.
     */
    public String getKodPocztowy() {
        return kodPocztowy;
    }

    /**
     * Metoda zwracająca miasto.
     *
     * @return Miasto.
     */
    public String getMiasto() {
        return miasto;
    }

    /**
     * Metoda zwracająca ulicę.
     *
     * @return Ulica.
     */
    public String getUlica() {
        return ulica;
    }

    /**
     * Metoda zwracająca numer budynku.
     *
     * @return Numer budynku.
     */
    public String getNrBudynku() {
        return nrBudynku;
    }

    /**
     * Metoda zwracająca numer mieszkania.
     *
     * @return Numer mieszkania.
     */
    public String getNrMieszkania() {
        return nrMieszkania;
    }

    /**
     * Metoda zwracająca status zamówienia.
     *
     * @return Status zamówienia.
     */
    public String getStatusZamowienia() {
        return statusZamowienia;
    }

    /**
     * Metoda zwracająca cenę zamówienia.
     *
     * @return Cena zamówienia.
     */
    public Double getCenaZamowienia() {
        return cenaZamowienia;
    }

    /**
     * Metoda zwracająca datę złożenia zamówienia.
     *
     * @return Data złożenia zamówienia.
     */
    public Date getDataZlozenia() {
        return dataZlozenia;
    }

    /**
     * Metoda zwracająca datę dostawy zamówienia.
     *
     * @return Data dostawy zamówienia.
     */
    public Date getDataDostawy() {
        return dataDostawy;
    }

    /**
     * Metoda zwracająca nazwę sposobu dostawy.
     *
     * @return Nazwa sposobu dostawy.
     */
    public String getNazwaSposobuDostawy() {
        return nazwaSposobuDostawy;
    }
}
