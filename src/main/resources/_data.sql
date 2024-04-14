INSERT INTO cozinha (ID, NOME) VALUES (1, 'Brasileira');
INSERT INTO cozinha (ID, NOME) VALUES (2, 'Japonesa');
INSERT INTO cozinha (ID, NOME) VALUES (3, 'Italiana');
INSERT INTO cozinha (ID, NOME) VALUES (4, 'Mexicana');
INSERT INTO cozinha (ID, NOME) VALUES (5, 'Chinesa');

INSERT INTO estado (ID, NOME) VALUES (1, 'São Paulo');
INSERT INTO estado (ID, NOME) VALUES (2, 'Rio de Janeiro');

INSERT INTO cidade (NOME, ESTADO_ID) VALUES ('São Paulo', 1);
INSERT INTO cidade (NOME, ESTADO_ID) VALUES ('Rio de Janeiro', 2);
INSERT INTO cidade (NOME, ESTADO_ID) VALUES ('Campinas', 1);

INSERT INTO restaurante (NOME, TAXA_FRETE, DATA_CADASTRO, DATA_ATUALIZACAO, COZINHA_ID, CIDADE_ID, ENDERECO_CEP, ENDERECO_COMPLEMENTO, ENDERECO_LOGRADOURO, ENDERECO_NUMERO, ENDERECO_BAIRRO)
VALUES ('Burger Top', 10, systimestamp, systimestamp, 3, 1, '00000-000', 'Comercio', 'Rua Xyz', '123', 'Centro');
INSERT INTO restaurante (NOME, TAXA_FRETE, DATA_CADASTRO, DATA_ATUALIZACAO, COZINHA_ID, CIDADE_ID, ENDERECO_CEP, ENDERECO_COMPLEMENTO, ENDERECO_LOGRADOURO, ENDERECO_NUMERO, ENDERECO_BAIRRO)
VALUES ('Burger King', 15, systimestamp, systimestamp, 1, 1, '11111-111', 'Comercio', 'Rua Xyz', '123', 'Centro');
INSERT INTO restaurante (NOME, TAXA_FRETE, DATA_CADASTRO, DATA_ATUALIZACAO, COZINHA_ID, CIDADE_ID, ENDERECO_CEP, ENDERECO_COMPLEMENTO, ENDERECO_LOGRADOURO, ENDERECO_NUMERO, ENDERECO_BAIRRO)
VALUES ('Burger Queen', 20, systimestamp, systimestamp, 4, 1, '22222-222', 'Comercio', 'Rua Xyz', '123', 'Centro');

INSERT INTO FORMA_PAGAMENTO (ID, DESCRICAO) VALUES (1, 'Dinheiro');
INSERT INTO FORMA_PAGAMENTO (ID, DESCRICAO) VALUES (2, 'Cartão de crédito');
INSERT INTO FORMA_PAGAMENTO (ID, DESCRICAO) VALUES (3, 'Cartão de débito');

INSERT INTO RESTAURANTE_FORMAS_PAGAMENTO (RESTAURANTE_ID, FORMAS_PAGAMENTO_ID)
VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 2), (3, 1), (3, 2);

INSERT INTO PRODUTO (ATIVO, DESCRICAO, NOME, PRECO, RESTAURANTE_ID)
VALUES (1, 'Hamburguer de carne suína, queijo, alface, tomate, cebola, picles e maionese', 'Hamburguer', 12.00, 1);
INSERT INTO PRODUTO (ATIVO, DESCRICAO, NOME, PRECO, RESTAURANTE_ID)
VALUES (1, 'Hamburguer de carne aviaria, queijo, alface, tomate, cebola, e maionese', 'Hamburguer', 11.00, 2);
INSERT INTO PRODUTO (ATIVO, DESCRICAO, NOME, PRECO, RESTAURANTE_ID)
VALUES (1, 'Hamburguer de carne bovina, queijo, alface, tomate, cebola e maionese', 'Hamburguer', 15.00, 3);
