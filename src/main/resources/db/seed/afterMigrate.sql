SET REFERENTIAL_INTEGRITY FALSE;

delete from CIDADE;
delete from COZINHA;
delete from ESTADO;
delete from FORMA_PAGAMENTO;
delete from GRUPO;
delete from GRUPO_PERMISSOES;
delete from PERMISSAO;
delete from PRODUTO;
delete from RESTAURANTE;
delete from RESTAURANTE_FORMAS_PAGAMENTO;
delete from USUARIO;
delete from USUARIO_GRUPOS;
delete from PEDIDO;
delete from ITEM_PEDIDO;

SET REFERENTIAL_INTEGRITY TRUE;

ALTER TABLE CIDADE ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE COZINHA ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE ESTADO ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE FORMA_PAGAMENTO ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE GRUPO ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE PERMISSAO ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE PRODUTO ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE RESTAURANTE ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE USUARIO ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE PEDIDO ALTER COLUMN ID RESTART WITH 1;
ALTER TABLE ITEM_PEDIDO ALTER COLUMN ID RESTART WITH 1;

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

INSERT INTO PERMISSAO (NOME, DESCRICAO) VALUES ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO PERMISSAO (NOME, DESCRICAO) VALUES ('EDITAR_COZINHAS', 'Permite editar cozinhas');
INSERT INTO PERMISSAO (NOME, DESCRICAO) VALUES ('CADASTRAR_COZINHAS', 'Permite cadastrar cozinhas');

INSERT INTO GRUPO (NOME) VALUES ('ADMINISTRADOR');
INSERT INTO GRUPO (NOME) VALUES ('USUARIO');

insert into grupo_permissoes (grupo_id, PERMISSOES_ID) values (1, 1), (1, 2), (1, 3);
insert into grupo_permissoes (grupo_id, PERMISSOES_ID) values (2, 1);

INSERT INTO USUARIO (NOME, EMAIL, SENHA, DATA_CADASTRO) VALUES ('Admin', 'eder@admin.com', '$2a$10$3', systimestamp);
INSERT INTO USUARIO (NOME, EMAIL, SENHA, DATA_CADASTRO) VALUES ('Usuario', 'ghy@user.com', '$2a$10$3', systimestamp);

INSERT INTO USUARIO_GRUPOS (USUARIO_ID, GRUPOS_ID) VALUES (1, 1), (2, 2);
