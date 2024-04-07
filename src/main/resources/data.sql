INSERT INTO cozinha (ID, NOME) VALUES (1, 'Brasileira');
INSERT INTO cozinha (ID, NOME) VALUES (2, 'Japonesa');

INSERT INTO restaurante (NOME, TAXA_FRETE, COZINHA_ID) VALUES ('Paris 6', 30, 1);
INSERT INTO restaurante (NOME, TAXA_FRETE, COZINHA_ID) VALUES ('Pastel da Tia', 15, 1);
INSERT INTO restaurante (NOME, TAXA_FRETE, COZINHA_ID) VALUES ('Sushi do Shi', 25, 2);

INSERT INTO estado (ID, NOME) VALUES (1, 'São Paulo');
INSERT INTO estado (ID, NOME) VALUES (2, 'Rio de Janeiro');

INSERT INTO cidade (NOME, ESTADO_ID) VALUES ('São Paulo', 1);
INSERT INTO cidade (NOME, ESTADO_ID) VALUES ('Rio de Janeiro', 2);
INSERT INTO cidade (NOME, ESTADO_ID) VALUES ('Campinas', 1);
