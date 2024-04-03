package com.example.dekstopshopproject;

/**
 * Reprezentuje dane dotyczące zawartości zamówienia w sklepie.
 * Klasa przechowuje informacje o identyfikatorze produktu, nazwie produktu, nazwie producenta,
 * cenie produktu, liczbie sztuk oraz nazwie kategorii.
 */
public class zamowieniaZawartoscData {

    private Integer itemId, liczbaSztuk;
    private String nazwaProduktu, nazwaProducenta, nazwaKategori;
    private Double cenaProduktu;

    /**
     * Tworzy nowy obiekt zamowieniaZawartoscData z określonymi wartościami.
     *
     * @param itemId            identyfikator produktu
     * @param nazwaProduktu     nazwa produktu
     * @param nazwaProducenta   nazwa producenta
     * @param cenaProduktu      cena produktu
     * @param liczbaSztuk       liczba sztuk zamówionych produktów
     * @param nazwaKategori     nazwa kategorii produktu
     */
    public zamowieniaZawartoscData(Integer itemId, String nazwaProduktu, String nazwaProducenta,
                                   Double cenaProduktu, Integer liczbaSztuk, String nazwaKategori) {
        this.itemId = itemId;
        this.nazwaProduktu = nazwaProduktu;
        this.nazwaProducenta = nazwaProducenta;
        this.cenaProduktu = cenaProduktu;
        this.liczbaSztuk = liczbaSztuk;
        this.nazwaKategori = nazwaKategori;
    }

    /**
     * Zwraca identyfikator produktu.
     *
     * @return identyfikator produktu
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Zwraca liczbę sztuk zamówionych produktów.
     *
     * @return liczba sztuk zamówionych produktów
     */
    public Integer getLiczbaSztuk() {
        return liczbaSztuk;
    }

    /**
     * Zwraca nazwę produktu.
     *
     * @return nazwa produktu
     */
    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    /**
     * Zwraca nazwę producenta.
     *
     * @return nazwa producenta
     */
    public String getNazwaProducenta() {
        return nazwaProducenta;
    }

    /**
     * Zwraca nazwę kategorii produktu.
     *
     * @return nazwa kategorii produktu
     */
    public String getNazwaKategori() {
        return nazwaKategori;
    }

    /**
     * Zwraca cenę produktu.
     *
     * @return cena produktu
     */
    public Double getCenaProduktu() {
        return cenaProduktu;
    }
}