package com.example.dekstopshopproject;

import java.sql.Date;

/**
 * Klasa reprezentująca dane klienta.
 *
 * Klasa `klienciData` przechowuje informacje o kliencie, takie jak identyfikator, imię,
 * nazwisko, adres e-mail, numer telefonu oraz data założenia konta. Zapewnia metody dostępowe
 * do poszczególnych pól.
 */
public class klienciData {
    private Integer klientId;
    private String imieKlienta;
    private String nazwiskoKlienta;
    private String mailKlienta;
    private String nrTelKlienta;
    private Date dataZalozeniaKonta;

    /**
     * Konstruktor klasy klienciData.
     *
     * Tworzy obiekt klasy klienciData z podanymi danymi klienta.
     *
     * @param klientId             Identyfikator klienta.
     * @param imieKlienta          Imię klienta.
     * @param nazwiskoKlienta      Nazwisko klienta.
     * @param mailKlienta          Adres e-mail klienta.
     * @param nrTelKlienta         Numer telefonu klienta.
     * @param dataZalozeniaKonta   Data założenia konta klienta.
     */
    public klienciData(Integer klientId, String imieKlienta, String nazwiskoKlienta, String mailKlienta,
                       String nrTelKlienta, Date dataZalozeniaKonta) {
        this.klientId = klientId;
        this.imieKlienta = imieKlienta;
        this.nazwiskoKlienta = nazwiskoKlienta;
        this.mailKlienta = mailKlienta;
        this.nrTelKlienta = nrTelKlienta;
        this.dataZalozeniaKonta = dataZalozeniaKonta;
    }

    /**
     * Metoda zwracająca identyfikator klienta.
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
     * Metoda zwracająca datę założenia konta klienta.
     *
     * @return Data założenia konta klienta.
     */
    public Date getDataZalozeniaKonta() {
        return dataZalozeniaKonta;
    }
}

