package com.example.dekstopshopproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class zamowieniaZawartoscDataTest {

    private zamowieniaZawartoscData zawartosc;

    @BeforeEach
    void setUp() {
        Integer itemId = 1;
        String nazwaProduktu = "Telewizor";
        String nazwaProducenta = "Samsung";
        Double cenaProduktu = 1999.99;
        Integer liczbaSztuk = 2;
        String nazwaKategori = "Elektronika";

        zawartosc = new zamowieniaZawartoscData(itemId, nazwaProduktu, nazwaProducenta,
                cenaProduktu, liczbaSztuk, nazwaKategori);
    }

    @AfterEach
    void tearDown() {
        zawartosc = null;
    }

    @Test
    void testGetItemId() {
        Integer expectedItemId = 1;
        Integer actualItemId = zawartosc.getItemId();
        assertEquals(expectedItemId, actualItemId);
    }

    @Test
    void testGetLiczbaSztuk() {
        Integer expectedLiczbaSztuk = 2;
        Integer actualLiczbaSztuk = zawartosc.getLiczbaSztuk();
        assertEquals(expectedLiczbaSztuk, actualLiczbaSztuk);
    }

    @Test
    void testGetNazwaProduktu() {
        String expectedNazwaProduktu = "Telewizor";
        String actualNazwaProduktu = zawartosc.getNazwaProduktu();
        assertEquals(expectedNazwaProduktu, actualNazwaProduktu);
    }

    @Test
    void testGetNazwaProducenta() {
        String expectedNazwaProducenta = "Samsung";
        String actualNazwaProducenta = zawartosc.getNazwaProducenta();
        assertEquals(expectedNazwaProducenta, actualNazwaProducenta);
    }

    @Test
    void testGetNazwaKategori() {
        String expectedNazwaKategori = "Elektronika";
        String actualNazwaKategori = zawartosc.getNazwaKategori();
        assertEquals(expectedNazwaKategori, actualNazwaKategori);
    }

    @Test
    void testGetCenaProduktu() {
        Double expectedCenaProduktu = 1999.99;
        Double actualCenaProduktu = zawartosc.getCenaProduktu();
        assertEquals(expectedCenaProduktu, actualCenaProduktu, 0.01);
    }
}