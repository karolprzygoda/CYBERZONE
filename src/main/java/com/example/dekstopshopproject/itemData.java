package com.example.dekstopshopproject;

import java.sql.Date;

/**
 * Klasa reprezentująca dane produktu.
 *
 * Klasa `itemData` przechowuje informacje o produkcie, takie jak identyfikator, nazwa,
 * producent, cena, dostępność, kategoria oraz data wyprodukowania. Zapewnia metody dostępowe
 * do poszczególnych pól.
 */
public class itemData {
    private Integer itemId,dostepnoscProdutku;
    private String nazwaProduktu,nazwaProducenta,nazwaKategori;
    private Double cenaProduktu;
    private Date dataWyprodukowania;

    /**
     * Konstruktor klasy itemData.
     *
     * Tworzy obiekt klasy itemData z podanymi danymi produktu.
     *
     * @param itemId              Identyfikator produktu.
     * @param nazwaProduktu       Nazwa produktu.
     * @param nazwaProducenta     Nazwa producenta produktu.
     * @param cenaProduktu        Cena produktu.
     * @param dostepnoscProdutku  Dostępność produktu.
     * @param nazwaKategori       Nazwa kategorii produktu.
     * @param dataWyprodukowania  Data wyprodukowania produktu.
     */
    public itemData(Integer itemId, String nazwaProduktu, String nazwaProducenta,  Double cenaProduktu, Integer dostepnoscProdutku, String nazwaKategori, Date dataWyprodukowania)
    {
        this.itemId = itemId;
        this.nazwaProduktu = nazwaProduktu;
        this.nazwaProducenta = nazwaProducenta;
        this.cenaProduktu = cenaProduktu;
        this.dostepnoscProdutku = dostepnoscProdutku;
        this.nazwaKategori = nazwaKategori;
        this.dataWyprodukowania = dataWyprodukowania;
    }

    /**
     * Metoda zwracająca identyfikator produktu.
     *
     * @return Identyfikator produktu.
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Metoda zwracająca dostępność produktu.
     *
     * @return Dostępność produktu.
     */
    public Integer getDostepnoscProdutku() {
        return dostepnoscProdutku;
    }

    /**
     * Metoda zwracająca nazwę produktu.
     *
     * @return Nazwa produktu.
     */
    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    /**
     * Metoda zwracająca nazwę producenta produktu.
     *
     * @return Nazwa producenta produktu.
     */
    public String getNazwaProducenta() {
        return nazwaProducenta;
    }

    /**
     * Metoda zwracająca nazwę kategorii produktu.
     *
     * @return Nazwa kategorii produktu.
     */
    public String getNazwaKategori() {
        return nazwaKategori;
    }

    /**
     * Metoda zwracająca cenę produktu.
     *
     * @return Cena produktu.
     */
    public Double getCenaProduktu() {
        return cenaProduktu;
    }

    /**
     * Metoda zwracająca datę wyprodukowania produktu.
     *
     * @return Data wyprodukowania produktu.
     */
    public Date getDataWyprodukowania() {
        return dataWyprodukowania;
    }

}
