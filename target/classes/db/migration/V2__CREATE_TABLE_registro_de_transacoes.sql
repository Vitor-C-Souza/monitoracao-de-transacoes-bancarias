CREATE TABLE registro_de_transacoes(
    id BIGINT(20) NOT NULL UNIQUE AUTO_INCREMENT,
    date_time_importacao DATETIME NOT NULL,
    date_transacoes DATE NOT NULL,
    PRIMARY KEY(id)
);