CREATE TABLE restaurante
(
    id                   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    nome                 VARCHAR(255),
    taxa_frete           DECIMAL,
    data_cadastro        TIMESTAMP                               NOT NULL,
    data_atualizacao     TIMESTAMP                               NOT NULL,
    cozinha_id           BIGINT,
    endereco_cep         VARCHAR(255),
    endereco_logradouro  VARCHAR(255),
    endereco_numero      VARCHAR(255),
    endereco_complemento VARCHAR(255),
    endereco_bairro      VARCHAR(255),
    cidade_id            BIGINT,
    CONSTRAINT pk_restaurante PRIMARY KEY (id)
);

CREATE TABLE restaurante_formas_pagamento
(
    formas_pagamento_id BIGINT NOT NULL,
    restaurante_id      BIGINT NOT NULL,
    CONSTRAINT pk_restaurante_formas_pagamento PRIMARY KEY (formas_pagamento_id, restaurante_id)
);

CREATE TABLE restaurante_produto
(
    produto_id     BIGINT NOT NULL,
    restaurante_id BIGINT NOT NULL,
    CONSTRAINT pk_restaurante_produto PRIMARY KEY (produto_id, restaurante_id)
);

CREATE TABLE restaurante_responsaveis
(
    responsavel_id BIGINT NOT NULL,
    restaurante_id BIGINT NOT NULL,
    CONSTRAINT pk_restaurante_responsaveis PRIMARY KEY (responsavel_id, restaurante_id)
);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_CIDADE FOREIGN KEY (cidade_id) REFERENCES cidade (id);

ALTER TABLE restaurante
    ADD CONSTRAINT FK_RESTAURANTE_ON_COZINHA FOREIGN KEY (cozinha_id) REFERENCES cozinha (id);

ALTER TABLE restaurante_formas_pagamento
    ADD CONSTRAINT fk_resforpag_on_forma_pagamento FOREIGN KEY (formas_pagamento_id) REFERENCES forma_pagamento (id);

ALTER TABLE restaurante_formas_pagamento
    ADD CONSTRAINT fk_resforpag_on_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante_produto
    ADD CONSTRAINT fk_respro_on_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE restaurante_produto
    ADD CONSTRAINT fk_respro_on_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante_responsaveis
    ADD CONSTRAINT fk_resres_on_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante_responsaveis
    ADD CONSTRAINT fk_resres_on_usuario FOREIGN KEY (responsavel_id) REFERENCES usuario (id);