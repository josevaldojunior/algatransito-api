CREATE TABLE ALGATRANSITO.AUTUACAO(
                                      ID BIGINT NOT NULL AUTO_INCREMENT,
                                      VEICULO_ID BIGINT NOT NULL,
                                      DESCRICAO TEXT NOT NULL,
                                      VALOR_MULTA DECIMAL(10,2) NOT NULL,
                                      DATA_OCORRENCIA DATETIME NOT NULL,
                                      PRIMARY KEY (ID)
);

ALTER TABLE ALGATRANSITO.AUTUACAO ADD CONSTRAINT FK_AUTUACAO_VEICULO
    FOREIGN KEY (VEICULO_ID) REFERENCES VEICULO(ID);