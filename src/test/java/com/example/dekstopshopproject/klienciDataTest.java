package com.example.dekstopshopproject;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

class klienciDataTest {

    @Test
    void testGetters() {
        Integer klientId = 1;
        String imieKlienta = "Jan";
        String nazwiskoKlienta = "Kowalski";
        String mailKlienta = "jan.kowalski@example.com";
        String nrTelKlienta = "123456789";
        Date dataZalozeniaKonta = Date.valueOf("2022-01-01");

        klienciData klient = new klienciData(klientId, imieKlienta, nazwiskoKlienta, mailKlienta, nrTelKlienta, dataZalozeniaKonta);

        assertEquals(klientId, klient.getKlientId(), "Nieprawidłowy identyfikator klienta");
        assertEquals(imieKlienta, klient.getImieKlienta(), "Nieprawidłowe imię klienta");
        assertEquals(nazwiskoKlienta, klient.getNazwiskoKlienta(), "Nieprawidłowe nazwisko klienta");
        assertEquals(mailKlienta, klient.getMailKlienta(), "Nieprawidłowy adres e-mail klienta");
        assertEquals(nrTelKlienta, klient.getNrTelKlienta(), "Nieprawidłowy numer telefonu klienta");
        assertEquals(dataZalozeniaKonta, klient.getDataZalozeniaKonta(), "Nieprawidłowa data założenia konta klienta");
    }
}