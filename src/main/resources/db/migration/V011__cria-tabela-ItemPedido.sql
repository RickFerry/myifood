CREATE TABLE item_pedido
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    preco_unitario DECIMAL,
    preco_total    DECIMAL,
    quantidade     INT,
    observacao     VARCHAR(255),
    pedido_id      BIGINT                                  NOT NULL,
    produto_id     BIGINT                                  NOT NULL,
    CONSTRAINT pk_itempedido PRIMARY KEY (id)
);

ALTER TABLE item_pedido
    ADD CONSTRAINT FK_ITEMPEDIDO_ON_PEDIDO FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE item_pedido
    ADD CONSTRAINT FK_ITEMPEDIDO_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produto (id);