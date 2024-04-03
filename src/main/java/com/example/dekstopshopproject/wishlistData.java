package com.example.dekstopshopproject;

import java.sql.Date;
/**
 * Klasa reprezentująca dane dotyczące listy życzeń.
 *
 * Klasa `wishlistData` przechowuje informacje dotyczące elementów listy życzeń,
 * takie jak identyfikator, nazwa produktu, nazwa producenta, cena produktu,
 * dostępność produktu, nazwa kategorii oraz data wyprodukowania. Dostarcza metody
 * dostępowe do odczytu poszczególnych pól.
 */
public class wishlistData {
    private Integer itemId, dostepnoscProdutku;
    private String nazwaProduktu, nazwaProducenta, nazwaKategori;
    private Double cenaProduktu;
    private Date dataWyprodukowania;

    /**
     * Konstruktor parametryczny klasy wishlistData.
     *
     * @param itemId               Identyfikator elementu na liście życzeń.
     * @param nazwaProduktu        Nazwa produktu.
     * @param nazwaProducenta      Nazwa producenta.
     * @param cenaProduktu         Cena produktu.
     * @param dostepnoscProdutku   Dostępność produktu.
     * @param nazwaKategori        Nazwa kategorii, do której należy produkt.
     * @param dataWyprodukowania   Data wyprodukowania produktu.
     */
    public wishlistData(Integer itemId, String nazwaProduktu, String nazwaProducenta,
                        Double cenaProduktu, Integer dostepnoscProdutku,
                        String nazwaKategori, Date dataWyprodukowania) {
        this.itemId = itemId;
        this.nazwaProduktu = nazwaProduktu;
        this.nazwaProducenta = nazwaProducenta;
        this.cenaProduktu = cenaProduktu;
        this.dostepnoscProdutku = dostepnoscProdutku;
        this.nazwaKategori = nazwaKategori;
        this.dataWyprodukowania = dataWyprodukowania;
    }

    /**
     * Metoda zwracająca identyfikator elementu na liście życzeń.
     *
     * @return Identyfikator elementu na liście życzeń.
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
     * Metoda zwracająca nazwę producenta.
     *
     * @return Nazwa producenta.
     */
    public String getNazwaProducenta() {
        return nazwaProducenta;
    }

    /**
     * Metoda zwracająca nazwę kategorii, do której należy produkt.
     *
     * @return Nazwa kategorii.
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
