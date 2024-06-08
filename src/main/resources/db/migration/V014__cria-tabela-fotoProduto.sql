CREATE TABLE foto_produto
(
    produto_id   BIGINT NOT NULL,
    nome_arquivo VARCHAR(255),
    descricao    VARCHAR(255),
    content_type VARCHAR(255),
    tamanho      BIGINT,
    CONSTRAINT pk_fotoproduto PRIMARY KEY (produto_id)
);

ALTER TABLE foto_produto
    ADD CONSTRAINT FK_FOTOPRODUTO_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produto (id);