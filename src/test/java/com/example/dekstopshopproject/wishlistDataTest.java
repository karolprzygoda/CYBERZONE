package com.example.dekstopshopproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

class wishlistDataTest {

    @Test
    void testGetters() {
        Integer itemId = 1;
        String nazwaProduktu = "Smartphone";
        String nazwaProducenta = "Samsung";
        Double cenaProduktu = 999.99;
        Integer dostepnoscProduktu = 5;
        String nazwaKategorii = "Elektronika";
        Date dataWyprodukowania = Date.valueOf("2023-05-01");

        wishlistData item = new wishlistData(itemId, nazwaProduktu, nazwaProducenta, cenaProduktu,
                dostepnoscProduktu, nazwaKategorii, dataWyprodukowania);

        assertEquals(itemId, item.getItemId(), "Nieprawidłowy identyfikator elementu");
        assertEquals(nazwaProduktu, item.getNazwaProduktu(), "Nieprawidłowa nazwa produktu");
        assertEquals(nazwaProducenta, item.getNazwaProducenta(), "Nieprawidłowa nazwa producenta");
        assertEquals(cenaProduktu, item.getCenaProduktu(), 0.01, "Nieprawidłowa cena produktu");
        assertEquals(dostepnoscProduktu, item.getDostepnoscProdutku(), "Nieprawidłowa dostępność produktu");
        assertEquals(nazwaKategorii, item.getNazwaKategori(), "Nieprawidłowa nazwa kategorii");
        assertEquals(dataWyprodukowania, item.getDataWyprodukowania(), "Nieprawidłowa data wyprodukowania");
    }
}