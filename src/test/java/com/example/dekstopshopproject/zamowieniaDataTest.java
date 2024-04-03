package com.example.dekstopshopproject;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Date;

public class zamowieniaDataTest {

    @Test
    public void testGetters() {
        Integer zamowienieId = 1;
        Integer klientId = 100;
        String imieKlienta = "Jan";
        String nazwiskoKlienta = "Kowalski";
        String mailKlienta = "jan.kowalski@example.com";
        String nrTelKlienta = "123456789";
        String kodPocztowy = "00-123";
        String miasto = "Warszawa";
        String ulica = "Aleja Korfantego";
        String nrBudynku = "1";
        String nrMieszkania = "2";
        String statusZamowienia = "Zrealizowane";
        Double cenaZamowienia = 99.99;
        Date dataZlozenia = Date.valueOf("2023-06-19");
        Date dataDostawy = Date.valueOf("2023-06-21");
        String nazwaSposobuDostawy = "Kurier";

        zamowieniaData zamowienie = new zamowieniaData(zamowienieId, klientId, imieKlienta, nazwiskoKlienta,
                mailKlienta, nrTelKlienta, kodPocztowy, miasto, ulica, nrBudynku, nrMieszkania, statusZamowienia,
                cenaZamowienia, dataZlozenia, dataDostawy, nazwaSposobuDostawy);

        assertEquals(zamowienieId, zamowienie.getZamowienieId());
        assertEquals(klientId, zamowienie.getKlientId());
        assertEquals(imieKlienta, zamowienie.getImieKlienta());
        assertEquals(nazwiskoKlienta, zamowienie.getNazwiskoKlienta());
        assertEquals(mailKlienta, zamowienie.getMailKlienta());
        assertEquals(nrTelKlienta, zamowienie.getNrTelKlienta());
        assertEquals(kodPocztowy, zamowienie.getKodPocztowy());
        assertEquals(miasto, zamowienie.getMiasto());
        assertEquals(ulica, zamowienie.getUlica());
        assertEquals(nrBudynku, zamowienie.getNrBudynku());
        assertEquals(nrMieszkania, zamowienie.getNrMieszkania());
        assertEquals(statusZamowienia, zamowienie.getStatusZamowienia());
        assertEquals(cenaZamowienia, zamowienie.getCenaZamowienia(), 0.01);
        assertEquals(dataZlozenia, zamowienie.getDataZlozenia());
        assertEquals(dataDostawy, zamowienie.getDataDostawy());
        assertEquals(nazwaSposobuDostawy, zamowienie.getNazwaSposobuDostawy());
    }
}