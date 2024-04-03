package com.example.dekstopshopproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class rabatyDataTest {

    @Test
    void testGetters() {
        Integer idRabatu = 1;
        String kodRabatu = "SUMMER2023";
        Integer znizka = 10;

        rabatyData rabat = new rabatyData(idRabatu, kodRabatu, znizka);

        assertEquals(idRabatu, rabat.getIdRabatu(), "Nieprawidłowy identyfikator rabatu");
        assertEquals(kodRabatu, rabat.getKodRabatu(), "Nieprawidłowy kod rabatowy");
        assertEquals(znizka, rabat.getZnizka(), "Nieprawidłowa wartość zniżki");
    }
}