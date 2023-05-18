DROP SCHEMA IF EXISTS unigame;
CREATE SCHEMA IF NOT EXISTS unigame;
USE unigame;

/*----------------------ENTITA'-----------------------*/
CREATE TABLE amministratore (
    codice_fiscale VARCHAR(16) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    pass_word VARCHAR(50) NOT NULL,
    ruolo VARCHAR(15) DEFAULT "admin",
    retribuzione_annuale int,
    PRIMARY KEY(codice_fiscale)
);

CREATE TABLE gestore_assistenza (
    codice_fiscale VARCHAR(16) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    pass_word VARCHAR(50) NOT NULL,
    ruolo VARCHAR(15) DEFAULT "gestAssist",
    retribuzione_annuale int,
    PRIMARY KEY(codice_fiscale)
);

CREATE TABLE cliente(
    codice_fiscale VARCHAR(16) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cognome VARCHAR(50) NOT NULL,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    pass_word VARCHAR(50) NOT NULL,
    ruolo VARCHAR(15) DEFAULT "cliente",
    ind_fatturazione VARCHAR(50),
    data_di_nascita DATE NOT NULL,
    conta_ordine_rel int DEFAULT 0,
    sospeso boolean DEFAULT 0,
    PRIMARY KEY(codice_fiscale)
);

CREATE TABLE num_telefono(
    numero BIGINT NOT NULL,
    cliente VARCHAR(16),
    amministratore VARCHAR(16),
    gest_assist VARCHAR(16),
    PRIMARY KEY(numero),
    FOREIGN KEY(cliente) REFERENCES cliente(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(amministratore) REFERENCES amministratore(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(gest_assist) REFERENCES gestore_assistenza(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE software_house(
    nome VARCHAR(50) NOT NULL,
    locazione VARCHAR(50) NOT NULL,
    anno_fondazione DATE NOT NULL,
    PRIMARY KEY(nome)
);

CREATE TABLE videogioco(
    id BIGINT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    prezzo DECIMAL(5, 2) NOT NULL,
    quantita int NOT NULL,
    pegi enum('tre', 'sette', 'dodici', 'sedidci', 'diciotto'),
    anno_produzione int,
    disponibile boolean NOT NULL,
    produttore VARCHAR(50) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(produttore) REFERENCES software_house(nome) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE prodotto_fisico(
    id BIGINT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    prezzo DECIMAL(5, 2) NOT NULL,
    quantita int NOT NULL,
    disponibile boolean NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE chiave_attivazione(
    chiave BIGINT NOT NULL,
    videogioco BIGINT NOT NULL,
    PRIMARY KEY(chiave),
    FOREIGN KEY(videogioco) REFERENCES videogioco(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ticket(
    num_ticket int NOT NULL,
    data_e_ora DATETIME NOT NULL,
    cliente VARCHAR(16) NOT NULL,
    gest_ass VARCHAR(16) NOT NULL,
    categoria enum('account', 'pagamento', 'ordine', 'altro') NOT NULL,
    messaggio VARCHAR(500) NOT NULL,
    risolto boolean NOT NULL,
    PRIMARY KEY(num_ticket),
    FOREIGN KEY(cliente) REFERENCES cliente(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(gest_ass) REFERENCES gestore_assistenza(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE recensione(
    id int AUTO_INCREMENT NOT NULL,
    cliente VARCHAR(16) NOT NULL,
    prodotto BIGINT,
    videogioco BIGINT,
    data_e_ora DATETIME NOT NULL,
    descrizione VARCHAR(500),
    indice_di_gradimento enum('uno','due','tre','quattro','cinque') NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(cliente) REFERENCES cliente(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(prodotto) REFERENCES prodotto_fisico(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(videogioco) REFERENCES videogioco(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ordine(
    id int NOT NULL AUTO_INCREMENT,
    data_e_ora DATETIME NOT NULL,
    importo_totale DECIMAL(6,2) NOT NULL,
    num_carta BIGINT NOT NULL,
    num_ordini int NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE fattura(
    id int NOT NULL AUTO_INCREMENT,
    ordine int NOT NULL,
    prezzo_totale DECIMAL(6, 2) NOT NULL,
    data_e_ora DATETIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(ordine) REFERENCES ordine(id) ON UPDATE CASCADE ON DELETE CASCADE
);

/*
CREATE TABLE fattura(
    id int NOT NULL AUTO_INCREMENT,
    ordine int NOT NULL,
    prezzo_totale DECIMAL(6, 2) NOT NULL,
    ind_fatturazione VARCHAR(50) NOT NULL,
    num_carta BIGINT NOT NULL,
    data_e_ora DATETIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(ordine) REFERENCES ordine(id) ON UPDATE CASCADE ON DELETE CASCADE 
); */

/*----------------------RELAZIONI-----------------------*/
CREATE TABLE gestisce_pf(
    amministratore VARCHAR(16) NOT NULL,
    prodotto BIGINT NOT NULL,
    PRIMARY KEY(amministratore, prodotto),
    FOREIGN KEY(amministratore) REFERENCES amministratore(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(prodotto) REFERENCES prodotto_fisico(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE gestisce_v(
    amministratore VARCHAR(16) NOT NULL,
    videogioco BIGINT NOT NULL,
    PRIMARY KEY(amministratore, videogioco),
    FOREIGN KEY(amministratore) REFERENCES amministratore(codice_fiscale) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(videogioco) REFERENCES videogioco(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE comprende_op(
    prodotto BIGINT NOT NULL,
    ordine int NOT NULL,
    PRIMARY KEY(prodotto, ordine),
    FOREIGN KEY(prodotto) REFERENCES prodotto_fisico (id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(ordine) REFERENCES ordine(id) ON UPDATE CASCADE ON DELETE CASCADE

);

CREATE TABLE comprende_ov(
    videogioco BIGINT NOT NULL,
    ordine int NOT NULL,
    PRIMARY KEY(videogioco, ordine),
    FOREIGN KEY(videogioco) REFERENCES videogioco(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY(ordine) REFERENCES ordine(id) ON UPDATE CASCADE ON DELETE CASCADE
);
