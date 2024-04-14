CREATE TABLE produto
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    nome           VARCHAR(255),
    descricao      VARCHAR(255),
    preco          DECIMAL,
    ativo          BOOLEAN,
    restaurante_id BIGINT,
    CONSTRAINT pk_produto PRIMARY KEY (id)
);

ALTER TABLE produto
    ADD CONSTRAINT FK_PRODUTO_ON_RESTAURANTE FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);