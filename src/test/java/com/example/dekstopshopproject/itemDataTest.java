package com.example.dekstopshopproject;

import org.junit.jupiter.api.Test;
import java.sql.Date;
import static org.junit.jupiter.api.Assertions.*;

class itemDataTest {

    @Test
    void testGetters() {
        Integer itemId = 1;
        String nazwaProduktu = "Przykładowy produkt";
        String nazwaProducenta = "Przykładowy producent";
        Double cenaProduktu = 19.99;
        Integer dostepnoscProduktu = 10;
        String nazwaKategori = "Przykładowa kategoria";
        Date dataWyprodukowania = Date.valueOf("2022-01-01");

        itemData item = new itemData(itemId, nazwaProduktu, nazwaProducenta, cenaProduktu, dostepnoscProduktu, nazwaKategori, dataWyprodukowania);

        assertEquals(itemId, item.getItemId(), "Nieprawidłowy identyfikator produktu");
        assertEquals(nazwaProduktu, item.getNazwaProduktu(), "Nieprawidłowa nazwa produktu");
        assertEquals(nazwaProducenta, item.getNazwaProducenta(), "Nieprawidłowa nazwa producenta");
        assertEquals(cenaProduktu, item.getCenaProduktu(), 0.01, "Nieprawidłowa cena produktu");
        assertEquals(dostepnoscProduktu, item.getDostepnoscProdutku(), "Nieprawidłowa dostępność produktu");
        assertEquals(nazwaKategori, item.getNazwaKategori(), "Nieprawidłowa nazwa kategorii produktu");
        assertEquals(dataWyprodukowania, item.getDataWyprodukowania(), "Nieprawidłowa data wyprodukowania produktu");
    }
}