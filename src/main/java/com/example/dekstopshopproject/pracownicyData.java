package com.example.dekstopshopproject;

/**
 * Klasa reprezentująca dane pracownika.
 *
 * Klasa `pracownicyData` przechowuje informacje dotyczące pracownika, takie jak identyfikator,
 * imię, nazwisko, numer telefonu, adres e-mail, stanowisko oraz wynagrodzenie. Dostarcza metody
 * dostępowe do odczytu poszczególnych pól.
 */
public class pracownicyData {

    private Integer idPracownika;
    private String imiePracownika, nazwiskoPracownika, telPracownika, mialPracownika, stanowiskoPracownika;
    private Double wynagrodzeniePracownika;

    /**
     * Konstruktor parametryczny klasy pracownicyData.
     *
     * @param idPracownika           Identyfikator pracownika.
     * @param imiePracownika         Imię pracownika.
     * @param nazwiskoPracownika     Nazwisko pracownika.
     * @param wynagrodzeniePracownika Wynagrodzenie pracownika.
     * @param telPracownika          Numer telefonu pracownika.
     * @param mialPracownika         Adres e-mail pracownika.
     * @param stanowiskoPracownika   Stanowisko pracownika.
     */
    public pracownicyData(Integer idPracownika, String imiePracownika, String nazwiskoPracownika, Double wynagrodzeniePracownika, String telPracownika, String mialPracownika, String stanowiskoPracownika) {
        this.idPracownika = idPracownika;
        this.imiePracownika = imiePracownika;
        this.nazwiskoPracownika = nazwiskoPracownika;
        this.telPracownika = telPracownika;
        this.mialPracownika = mialPracownika;
        this.stanowiskoPracownika = stanowiskoPracownika;
        this.wynagrodzeniePracownika = wynagrodzeniePracownika;
    }

    /**
     * Metoda zwracająca identyfikator pracownika.
     *
     * @return Identyfikator pracownika.
     */
    public Integer getIdPracownika() {
        return idPracownika;
    }

    /**
     * Metoda zwracająca imię pracownika.
     *
     * @return Imię pracownika.
     */
    public String getImiePracownika() {
        return imiePracownika;
    }

    /**
     * Metoda zwracająca nazwisko pracownika.
     *
     * @return Nazwisko pracownika.
     */
    public String getNazwiskoPracownika() {
        return nazwiskoPracownika;
    }

    /**
     * Metoda zwracająca numer telefonu pracownika.
     *
     * @return Numer telefonu pracownika.
     */
    public String getTelPracownika() {
        return telPracownika;
    }

    /**
     * Metoda zwracająca adres e-mail pracownika.
     *
     * @return Adres e-mail pracownika.
     */
    public String getMialPracownika() {
        return mialPracownika;
    }

    /**
     * Metoda zwracająca stanowisko pracownika.
     *
     * @return Stanowisko pracownika.
     */
    public String getStanowiskoPracownika() {
        return stanowiskoPracownika;
    }

    /**
     * Metoda zwracająca wynagrodzenie pracownika.
     *
     * @return Wynagrodzenie pracownika.
     */
    public Double getWynagrodzeniePracownika() {
        return wynagrodzeniePracownika;
    }
}
