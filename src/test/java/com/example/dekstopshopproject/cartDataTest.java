package com.example.dekstopshopproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class cartDataTest {

    @Test
    void testGetters() {
        Integer itemId = 1;
        String liczbaSztuk = "2";
        String nazwaProduktu = "Przykładowy produkt";
        String nazwaProducenta = "Przykładowy producent";
        String nazwaKategori = "Przykładowa kategoria";
        Double cenaSztuki = 10.99;

        cartData cart = new cartData(itemId, liczbaSztuk, nazwaProduktu, nazwaProducenta, nazwaKategori, cenaSztuki);

        assertEquals(itemId, cart.getItemId(), "Nieprawidłowy identyfikator przedmiotu");
        assertEquals(liczbaSztuk, cart.getLiczbaSztuk(), "Nieprawidłowa liczba sztuk przedmiotu");
        assertEquals(nazwaProduktu, cart.getNazwaProduktu(), "Nieprawidłowa nazwa produktu");
        assertEquals(nazwaProducenta, cart.getNazwaProducenta(), "Nieprawidłowa nazwa producenta");
        assertEquals(nazwaKategori, cart.getNazwaKategori(), "Nieprawidłowa nazwa kategorii produktu");
        assertEquals(cenaSztuki, cart.getCenaSztuki(), 0.01, "Nieprawidłowa cena sztuki produktu");
    }

    @Test
    void testSetLiczbaSztuk() {
        Integer itemId = 1;
        String liczbaSztuk = "2";
        String nazwaProduktu = "Przykładowy produkt";
        String nazwaProducenta = "Przykładowy producent";
        String nazwaKategori = "Przykładowa kategoria";
        Double cenaSztuki = 10.99;

        cartData cart = new cartData(itemId, liczbaSztuk, nazwaProduktu, nazwaProducenta, nazwaKategori, cenaSztuki);

        String nowaLiczbaSztuk = "3";
        cart.setLiczbaSztuk(nowaLiczbaSztuk);

        assertEquals(nowaLiczbaSztuk, cart.getLiczbaSztuk(), "Nieprawidłowa ustawiona liczba sztuk przedmiotu");
    }
}