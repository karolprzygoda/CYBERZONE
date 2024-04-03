package com.example.dekstopshopproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class pracownicyDataTest {

    @Test
    void testGetters() {
        Integer idPracownika = 1;
        String imiePracownika = "Jan";
        String nazwiskoPracownika = "Kowalski";
        Double wynagrodzeniePracownika = 5000.0;
        String telPracownika = "123456789";
        String mailPracownika = "jan.kowalski@example.com";
        String stanowiskoPracownika = "Pracownik";

        pracownicyData pracownik = new pracownicyData(idPracownika, imiePracownika, nazwiskoPracownika, wynagrodzeniePracownika,
                telPracownika, mailPracownika, stanowiskoPracownika);

        assertEquals(idPracownika, pracownik.getIdPracownika(), "Nieprawidłowy identyfikator pracownika");
        assertEquals(imiePracownika, pracownik.getImiePracownika(), "Nieprawidłowe imię pracownika");
        assertEquals(nazwiskoPracownika, pracownik.getNazwiskoPracownika(), "Nieprawidłowe nazwisko pracownika");
        assertEquals(wynagrodzeniePracownika, pracownik.getWynagrodzeniePracownika(), "Nieprawidłowe wynagrodzenie pracownika");
        assertEquals(telPracownika, pracownik.getTelPracownika(), "Nieprawidłowy numer telefonu pracownika");
        assertEquals(mailPracownika, pracownik.getMialPracownika(), "Nieprawidłowy adres e-mail pracownika");
        assertEquals(stanowiskoPracownika, pracownik.getStanowiskoPracownika(), "Nieprawidłowe stanowisko pracownika");
    }
}