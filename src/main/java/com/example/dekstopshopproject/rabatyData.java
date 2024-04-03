package com.example.dekstopshopproject;

/**
 * Klasa reprezentująca dane dotyczące rabatu.
 *
 * Klasa `rabatyData` przechowuje informacje dotyczące rabatu, takie jak identyfikator, kod rabatowy
 * oraz wartość zniżki. Dostarcza metody dostępowe do odczytu poszczególnych pól.
 */
public class rabatyData {

    private Integer idRabatu, znizka;
    private String kodRabatu;

    /**
     * Konstruktor parametryczny klasy rabatyData.
     *
     * @param idRabatu   Identyfikator rabatu.
     * @param kodRabatu  Kod rabatowy.
     * @param znizka     Wartość zniżki.
     */
    public rabatyData(Integer idRabatu, String kodRabatu, Integer znizka) {
        this.idRabatu = idRabatu;
        this.kodRabatu = kodRabatu;
        this.znizka = znizka;
    }

    /**
     * Metoda zwracająca identyfikator rabatu.
     *
     * @return Identyfikator rabatu.
     */
    public Integer getIdRabatu() {
        return idRabatu;
    }

    /**
     * Metoda zwracająca kod rabatowy.
     *
     * @return Kod rabatowy.
     */
    public String getKodRabatu() {
        return kodRabatu;
    }

    /**
     * Metoda zwracająca wartość zniżki.
     *
     * @return Wartość zniżki.
     */
    public Integer getZnizka() {
        return znizka;
    }
}