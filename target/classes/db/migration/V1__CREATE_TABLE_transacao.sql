CREATE TABLE transacao(
    id BIGINT(20) NOT NULL UNIQUE AUTO_INCREMENT,
    banco_origem VARCHAR(30) NOT NULL,
    agencia_origem VARCHAR(30) NOT NULL,
    conta_origem VARCHAR(30) NOT NULL,
    banco_destino VARCHAR(30) NOT NULL,
    agencia_destino VARCHAR(30) NOT NULL,
    conta_destino VARCHAR(30) NOT NULL,
    valor DOUBLE NOT NULL,
    date_time DATETIME NOT NULL,
    PRIMARY KEY(id)
);