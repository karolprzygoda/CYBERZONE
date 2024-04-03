package com.example.dekstopshopproject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class BazaDanychTest {
    private Connection connection;

    @BeforeEach
    void setUp() {
        connection = BazaDanych.connectToDb();
    }

    @AfterEach
    void tearDown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConnectToDb() {
        assertNotNull(connection, "Połączenie z bazą danych nie powinno być null");
        try {
            assertFalse(connection.isClosed(), "Połączenie z bazą danych powinno być otwarte");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}