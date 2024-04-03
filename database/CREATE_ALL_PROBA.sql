CREATE TABLE uzytkownik 
(
	id_uzytkownika INT PRIMARY KEY AUTO_INCREMENT,
	imie VARCHAR(50) NOT NULL,
	nazwisko VARCHAR(50) NOT NULL,
	mail VARCHAR(50) NOT NULL UNIQUE,
	nr_telefonu VARCHAR(15) NOT NULL UNIQUE,
	haslo VARCHAR(30) NOT NULL,
	data_rejestracji DATETIME
);


CREATE TABLE produkty
(
    id_produktu INT PRIMARY KEY AUTO_INCREMENT,
    nazwa VARCHAR(60) NOT NULL,
	nazwa_producenta VARCHAR(30) NOT NULL,
    cena FLOAT NOT NULL,
    liczba_dostepnych INT NOT NULL,
    nazwa_kategorii VARCHAR(25) NOT NULL CHECK (nazwa_kategorii IN 
    ('procesor', 'karta graficzna', 'płyta główna', 'zasilacz komputerowy', 'pamięć RAM', 'głośniki', 'klawiatura','myszka','monitor','dysk HDD','dysk SSD','drukarka','obudowa')),
    data_wyprodukowania DATE NOT NULL
);

CREATE TABLE lista_przedmiotow
(
	id_lista_przedmiotow INT PRIMARY KEY AUTO_INCREMENT,
	id_produktu INT REFERENCES produkty(id_produktu),
	id_lista_zyczen INT REFERENCES lista_zyczen(id_listy)
);

CREATE TABLE lista_zyczen
(
	id_listy INT PRIMARY KEY AUTO_INCREMENT,
	id_uzytkownika INT NOT NULL REFERENCES uzytkownicy(id_uzytkownika)
);

CREATE TABLE rabaty
(
	id_rabatu INT PRIMARY KEY AUTO_INCREMENT,
    kod VARCHAR(10) NOT NULL,
    procent_znizki INT NOT NULL
);

CREATE TABLE koszyk
(
    id_koszyka INT PRIMARY KEY AUTO_INCREMENT,
	id_uzytkownika INT NULL DEFAULT NULL REFERENCES uzytkownicy(id_uzytkownika)
);

CREATE TABLE zawartosci_koszykow
(
    id_zawartosci_koszyka INT PRIMARY KEY AUTO_INCREMENT,
	id_koszyka INT NOT NULL REFERENCES koszyk(id_koszyka),
    id_produktu INT NOT NULL REFERENCES produkty(id_produktu),
    liczba_sztuk INT NOT NULL
);

CREATE TABLE sposoby_platnosci
(
    id_platnosci INT PRIMARY KEY AUTO_INCREMENT,
    nazwa VARCHAR(45) NOT NULL,
    koszt_sposobu_platnosci FLOAT NOT NULL
);

CREATE TABLE zamowienia
(
    id_zamowienia INT PRIMARY KEY AUTO_INCREMENT,
	id_koszyka INT NOT NULL REFERENCES koszyki(id_koszyka),
    id_uzytkownika INT NULL DEFAULT NULL REFERENCES uzytkownicy(id_uztkownika),
    data_zlozenia DATE NOT NULL,
    imie VARCHAR(50) NOT NULL,
	nazwisko VARCHAR(50) NOT NULL,
	mail VARCHAR(50) NOT NULL,
	nr_telefonu VARCHAR(15) NOT NULL,
    status_zamowienia VARCHAR(25) NOT NULL CHECK (status_zamowienia IN 
    ('oczekuje na realizacje', 'w trakcie pakowania', 'gotowe do wysylki', 'w drodze', 'odebrane', 'anulowano', 'nie odebrano')),
	id_rabatu INT NULL DEFAULT NULL REFERENCES rabaty(id_rabatu),
    id_platnosci INT NOT NULL REFERENCES sposoby_platnosci(id_platnosci),
    cena_calkowita FLOAT
);

CREATE TABLE zawartosc_zamowienia
(
	id_zawartosci_zamowienia INT PRIMARY KEY AUTO_INCREMENT,
    id_zamowienia INT REFERENCES zamowienia(id_zamowienia),
    id_produktu INT REFERENCES produkty(id_produktu),
    liczba_sztuk INT,
    cena_przed_zakupem FLOAT
);

CREATE TABLE sposoby_dostawy
(
    id_sposobu_dostawy INT PRIMARY KEY AUTO_INCREMENT,
    nazwa VARCHAR(45) NOT NULL,
    koszt_dostawy FLOAT NOT NULL
);

CREATE TABLE dostawy
(
    id_dostawy INT PRIMARY KEY AUTO_INCREMENT,
	id_sposobu_dostawy INT NOT NULL REFERENCES sposoby_dostawy(id_sposobu_dostawy),
	id_zamowienia INT REFERENCES zamowienia(id_zamowienia),
    miejscowosc VARCHAR(60) NOT NULL,
	kod_pocztowy VARCHAR(6) NOT NULL,
    ulica VARCHAR(60) NOT NULL,
    numer_domu VARCHAR(10) NOT NULL,
    numer_lokalu VARCHAR(10) NULL DEFAULT NULL,
    przewidywany_termin_dostawy DATE NOT NULL
);

CREATE TABLE pracownicy
(
    id_pracownika INT PRIMARY KEY AUTO_INCREMENT,
    imie VARCHAR(90) NOT NULL,
    nazwisko VARCHAR(90) NOT NULL,
    wynagrodzenie FLOAT NOT NULL,
    nr_telefonu VARCHAR(15) NOT NULL,
    adres_mailowy VARCHAR(95) NOT NULL,
    stanowisko VARCHAR(40) NOT NULL  CHECK (stanowisko IN ('Dział Obsługi Klienta', 
    'Informatyk', 'Koordynator Projektu', 'Analityk Systemowy'))
);

CREATE TABLE administrator
(
	id_admin INT PRIMARY KEY AUTO_INCREMENT,
    imie VARCHAR(90) NOT NULL,
    nazwisko VARCHAR(90) NOT NULL,
    adres_mailowy VARCHAR(95) NOT NULL,
    haslo VARCHAR(30) NOT NULL
);