package com.example.dekstopshopproject;

/**
        * Klasa cartData reprezentuje dane przedmiotu w koszyku.
        */
public class cartData {
    private Integer itemId;
    private String nazwaProduktu, nazwaProducenta, nazwaKategori, liczbaSztuk;
    private Double cenaSztuki;

    /**
     * Konstruktor tworzy nowy obiekt cartData z podanymi danymi.
     *
     * @param itemId            Identyfikator przedmiotu
     * @param liczbaSztuk       Liczba sztuk przedmiotu w koszyku
     * @param nazwaProduktu     Nazwa produktu
     * @param nazwaProducenta   Nazwa producenta
     * @param nazwaKategori     Nazwa kategorii produktu
     * @param cenaSztuki        Cena jednej sztuki produktu
     */
    public cartData(Integer itemId, String liczbaSztuk, String nazwaProduktu, String nazwaProducenta, String nazwaKategori, Double cenaSztuki) {
        this.itemId = itemId;
        this.liczbaSztuk = liczbaSztuk;
        this.nazwaProduktu = nazwaProduktu;
        this.nazwaProducenta = nazwaProducenta;
        this.nazwaKategori = nazwaKategori;
        this.cenaSztuki = cenaSztuki;
    }

    /**
     * Metoda zwraca identyfikator przedmiotu.
     *
     * @return Identyfikator przedmiotu
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * Metoda zwraca liczbę sztuk przedmiotu w koszyku.
     *
     * @return Liczba sztuk przedmiotu w koszyku
     */
    public String getLiczbaSztuk() {
        return liczbaSztuk;
    }

    /**
     * Metoda zwraca nazwę produktu.
     *
     * @return Nazwa produktu
     */
    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    /**
     * Metoda zwraca nazwę producenta.
     *
     * @return Nazwa producenta
     */
    public String getNazwaProducenta() {
        return nazwaProducenta;
    }

    /**
     * Metoda zwraca nazwę kategorii produktu.
     *
     * @return Nazwa kategorii produktu
     */
    public String getNazwaKategori() {
        return nazwaKategori;
    }

    /**
     * Metoda zwraca cenę jednej sztuki produktu.
     *
     * @return Cena jednej sztuki produktu
     */
    public Double getCenaSztuki() {
        return cenaSztuki;
    }

    /**
     * Metoda ustawia liczbę sztuk przedmiotu w koszyku.
     *
     * @param liczbaSztuk Liczba sztuk przedmiotu w koszyku
     */
    public void setLiczbaSztuk(String liczbaSztuk) {
        this.liczbaSztuk = liczbaSztuk;
    }
}
