--  VITOR TADEU SOARES DE SOUSA
-- Pedro Paulo Barretta
-- Felipe Ulson Sora




DROP TABLE t_cs_bairro CASCADE CONSTRAINTS;

DROP TABLE t_cs_carro CASCADE CONSTRAINTS;

DROP TABLE t_cs_cidade CASCADE CONSTRAINTS;

DROP TABLE t_cs_estado CASCADE CONSTRAINTS;

DROP TABLE t_cs_logradouro CASCADE CONSTRAINTS;

DROP TABLE t_cs_problemas CASCADE CONSTRAINTS;

DROP TABLE t_cs_telefone_usuario CASCADE CONSTRAINTS;

DROP TABLE t_cs_usuario CASCADE CONSTRAINTS;



CREATE TABLE t_cs_bairro (
    id_bairro             NUMBER(3) NOT NULL,
    t_cs_cidade_id_cidade NUMBER(4) NOT NULL,
    nm_bairro             VARCHAR2(45) NOT NULL,
    nm_zona_bairro        VARCHAR2(20) NOT NULL
);

ALTER TABLE t_cs_bairro ADD CONSTRAINT t_cs_bairro_pk PRIMARY KEY ( id_bairro );

CREATE TABLE t_cs_carro (
    id_carro                NUMBER(3) NOT NULL,
    t_cs_usuario_id_usuario NUMBER(3) NOT NULL,
    mc_carro                VARCHAR2(20) NOT NULL,
    md_carro                VARCHAR2(20) NOT NULL
);

COMMENT ON COLUMN t_cs_carro.id_carro IS
    'Este é o ID do carro do usuário';

COMMENT ON COLUMN t_cs_carro.mc_carro IS
    'Esse é o atributo onde fica a marca do carro';

COMMENT ON COLUMN t_cs_carro.md_carro IS
    'Este é o atributo onde fica o modelo do carro';

ALTER TABLE t_cs_carro ADD CONSTRAINT t_cs_carro_pk PRIMARY KEY ( id_carro );

CREATE TABLE t_cs_cidade (
    id_cidade             NUMBER(4) NOT NULL,
    t_cs_estado_id_estado NUMBER(3) NOT NULL,
    nm_cidade             VARCHAR2(45) NOT NULL,
    nr_ddd                NUMBER(3) NOT NULL
);

COMMENT ON COLUMN t_cs_cidade.id_cidade IS
    'Este é o atributo onde fica o id da cidade';

COMMENT ON COLUMN t_cs_cidade.nm_cidade IS
    'Este é o atributo onde fica o nome da cidade';

COMMENT ON COLUMN t_cs_cidade.nr_ddd IS
    'Este é o atributo onde fica o ddd da cidade';

ALTER TABLE t_cs_cidade ADD CONSTRAINT t_cs_cidade_pk PRIMARY KEY ( id_cidade );

CREATE TABLE t_cs_estado (
    id_estado NUMBER(3) NOT NULL,
    sg_estado CHAR(2) NOT NULL,
    nm_estado VARCHAR2(30) NOT NULL
);

ALTER TABLE t_cs_estado ADD CONSTRAINT t_cs_estado_pk PRIMARY KEY ( id_estado );

CREATE TABLE t_cs_logradouro (
    id_logradouro           NUMBER(3) NOT NULL,
    t_cs_bairro_id_bairro   NUMBER(3) NOT NULL,
    t_cs_usuario_id_usuario NUMBER(3) NOT NULL,
    nm_logradouro           VARCHAR2(30) NOT NULL,
    nr_logradouro           NUMBER(7) NOT NULL,
    nr_cep                  NUMBER(8) NOT NULL
);

COMMENT ON COLUMN t_cs_logradouro.id_logradouro IS
    'Este é o atributo onde fica o id do logradouro do usuário';

COMMENT ON COLUMN t_cs_logradouro.nm_logradouro IS
    'Este é o atributo onde fica o nome do logradouro do usuário';

COMMENT ON COLUMN t_cs_logradouro.nr_logradouro IS
    'Este é o atributo onde fica o número do logradouro do usuário';

COMMENT ON COLUMN t_cs_logradouro.nr_cep IS
    'Este é o atributo onde fica o número do cep do usuário';

ALTER TABLE t_cs_logradouro ADD CONSTRAINT t_cs_logradouro_pk PRIMARY KEY ( id_logradouro );

CREATE TABLE t_cs_problemas (
    id_problema         NUMBER(3) NOT NULL,
    t_cs_carro_id_carro NUMBER(3) NOT NULL,
    vl_problema         NUMBER(5, 2) NOT NULL,
    nm_problema         VARCHAR2(20) NOT NULL,
    tp_peca_problema    VARCHAR2(20) NOT NULL,
    dc_problema         VARCHAR2(30)
);

ALTER TABLE t_cs_problemas
    ADD CONSTRAINT ck_cs_vl_problema_carro CHECK ( vl_problema BETWEEN 100 AND 9999 );

COMMENT ON COLUMN t_cs_problemas.id_problema IS
    'Este é o atributo onde fica o id do problema.';

COMMENT ON COLUMN t_cs_problemas.vl_problema IS
    'Esté é o valor do problema do carro, tem no máximo 5 digitos e 2 casas decimais';

COMMENT ON COLUMN t_cs_problemas.nm_problema IS
    'Este é o nome do problema, exemplo (Óleo velho)';

COMMENT ON COLUMN t_cs_problemas.tp_peca_problema IS
    'Este é o atributo onde fica o tipo de peça onde está o problema';

COMMENT ON COLUMN t_cs_problemas.dc_problema IS
    'Este é o atributo onde fica a descrção do atributo, é opcional.';

ALTER TABLE t_cs_problemas ADD CONSTRAINT t_cs_problemas_pk PRIMARY KEY ( id_problema );

CREATE TABLE t_cs_telefone_usuario (
    t_cs_usuario_id_usuario NUMBER(3) NOT NULL,
    id_telefone             NUMBER(3) NOT NULL,
    nr_ddi                  NUMBER(3) NOT NULL,
    nr_ddd                  NUMBER(3) NOT NULL,
    nr_telefone             NUMBER(10) NOT NULL,
    tp_telefone             VARCHAR2(20) NOT NULL,
    st_telefone             VARCHAR2(15) NOT NULL
);

ALTER TABLE t_cs_telefone_usuario
    ADD CHECK ( tp_telefone IN ( 'Celular', 'Fixo' ) );

ALTER TABLE t_cs_telefone_usuario
    ADD CONSTRAINT ck_cs_status_telefone_paciente CHECK ( st_telefone IN ( 'A', 'I' ) );

COMMENT ON COLUMN t_cs_telefone_usuario.id_telefone IS
    'Este é o atributo onde fica o telefone do usuario';

COMMENT ON COLUMN t_cs_telefone_usuario.nr_ddi IS
    'Este é o atributo onde fica o DDI do telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.nr_ddd IS
    'Este é o atributo onde fica o DDD do telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.nr_telefone IS
    'Este é o atributo onde fica o número de telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.tp_telefone IS
    'Este é o atributo onde fica o tipo de telefone do usuário';

COMMENT ON COLUMN t_cs_telefone_usuario.st_telefone IS
    'Este é o atributo onde fica o status do telefone do usuário';

ALTER TABLE t_cs_telefone_usuario ADD CONSTRAINT t_cs_telefone_usuario_pk PRIMARY KEY ( id_telefone,
                                                                                        t_cs_usuario_id_usuario );

CREATE TABLE t_cs_usuario (
    id_usuario NUMBER(3) NOT NULL,
    us_cpf     NUMBER(11) NOT NULL,
    nm_usuario VARCHAR2(40) NOT NULL,
    em_usuario VARCHAR2(30) NOT NULL,
    sn_usuario VARCHAR2(30) NOT NULL
);

COMMENT ON COLUMN t_cs_usuario.id_usuario IS
    'Este é o atributo onde fica o ID do usuário.';

COMMENT ON COLUMN t_cs_usuario.us_cpf IS
    'Esté é o atributo onde fica o cpf do usuário';

COMMENT ON COLUMN t_cs_usuario.nm_usuario IS
    'Esté o atributo onde fica o nome do usuário.';

ALTER TABLE t_cs_usuario ADD CONSTRAINT t_cs_usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE t_cs_usuario ADD CONSTRAINT t_cs_usuario_us_cpf_un UNIQUE ( us_cpf );

ALTER TABLE t_cs_bairro
    ADD CONSTRAINT t_cs_bairro_t_cs_cidade_fk FOREIGN KEY ( t_cs_cidade_id_cidade )
        REFERENCES t_cs_cidade ( id_cidade );

ALTER TABLE t_cs_carro
    ADD CONSTRAINT t_cs_carro_t_cs_usuario_fk FOREIGN KEY ( t_cs_usuario_id_usuario )
        REFERENCES t_cs_usuario ( id_usuario );

ALTER TABLE t_cs_cidade
    ADD CONSTRAINT t_cs_cidade_t_cs_estado_fk FOREIGN KEY ( t_cs_estado_id_estado )
        REFERENCES t_cs_estado ( id_estado );

ALTER TABLE t_cs_logradouro
    ADD CONSTRAINT t_cs_logradouro_t_cs_bairro_fk FOREIGN KEY ( t_cs_bairro_id_bairro )
        REFERENCES t_cs_bairro ( id_bairro );

ALTER TABLE t_cs_logradouro
    ADD CONSTRAINT t_cs_logradouro_usuario_fk FOREIGN KEY ( t_cs_usuario_id_usuario )
        REFERENCES t_cs_usuario ( id_usuario );

ALTER TABLE t_cs_problemas
    ADD CONSTRAINT t_cs_problemas_t_cs_carro_fk FOREIGN KEY ( t_cs_carro_id_carro )
        REFERENCES t_cs_carro ( id_carro );

ALTER TABLE t_cs_telefone_usuario
    ADD CONSTRAINT t_cs_telefone_usuario_fk FOREIGN KEY ( t_cs_usuario_id_usuario )
        REFERENCES t_cs_usuario ( id_usuario );


INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (1, 'SP', 'São Paulo');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (2, 'RJ', 'Rio de Janeiro');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (3, 'MG', 'Minas Gerais');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (4, 'ES', 'Espírito Santo');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (5, 'BA', 'Bahia');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (6, 'PR', 'Paraná');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (7, 'SC', 'Santa Catarina');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (8, 'RS', 'Rio Grande do Sul');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (9, 'PE', 'Pernambuco');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (10, 'AM', 'Amazonas');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (11, 'GO', 'Goiás');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (12, 'PA', 'Pará');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (13, 'CE', 'Ceará');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (14, 'MA', 'Maranhão');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (15, 'MT', 'Mato Grosso');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (16, 'MS', 'Mato Grosso do Sul');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (17, 'PI', 'Piauí');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (18, 'TO', 'Tocantins');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (19, 'AL', 'Alagoas');
INSERT INTO T_CS_ESTADO (id_estado, sg_estado, nm_estado) VALUES (20, 'RO', 'Rondônia');

INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (1, 1, 'São Paulo', 11);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (2, 2, 'Rio de Janeiro', 21);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (3, 3, 'Belo Horizonte', 31);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (4, 4, 'Vitória', 27);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (5, 5, 'Salvador', 71);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (6, 6, 'Curitiba', 41);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (7, 7, 'Florianópolis', 48);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (8, 8, 'Porto Alegre', 51);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (9, 9, 'Recife', 81);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (10, 10, 'Manaus', 92);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (11, 11, 'Goiânia', 62);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (12, 12, 'Belém', 91);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (13, 13, 'Fortaleza', 85);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (14, 14, 'São Luís', 98);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (15, 15, 'Cuiabá', 65);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (16, 16, 'Campo Grande', 67);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (17, 17, 'Teresina', 86);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (18, 18, 'Palmas', 63);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (19, 19, 'Maceió', 82);
INSERT INTO T_CS_CIDADE (id_cidade, t_cs_estado_id_estado, nm_cidade, nr_ddd) VALUES (20, 20, 'Porto Velho', 69);

INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (1, 1, 'Vila Mariana', 'Centro-Sul');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (2, 2, 'Copacabana', 'Sul');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (3, 3, 'Savassi', 'Centro-Sul');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (4, 4, 'Jardim Camburi', 'Norte');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (5, 5, 'Rio Vermelho', 'Leste');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (6, 6, 'Batel', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (7, 7, 'Trindade', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (8, 8, 'Moinhos de Vento', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (9, 9, 'Boa Viagem', 'Sul');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (10, 10, 'Ponta Negra', 'Oeste');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (11, 11, 'Setor Bueno', 'Centro-Oeste');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (12, 12, 'Nazaré', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (13, 13, 'Meireles', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (14, 14, 'Renascença', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (15, 15, 'Jardim Cuiabá', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (16, 16, 'Jardim dos Estados', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (17, 17, 'Centro', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (18, 18, 'Plano Diretor Norte', 'Norte');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (19, 19, 'Pajuçara', 'Centro');
INSERT INTO T_CS_BAIRRO (id_bairro, t_cs_cidade_id_cidade, nm_bairro, nm_zona_bairro) VALUES (20, 20, 'Embratel', 'Centro');

INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (1, 1, 1, 'Rua Vergueiro', 1000, 12345000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (2, 2, 2, 'Avenida Atlântica', 2000, 22010000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (3, 3, 3, 'Rua dos Timbiras', 3000, 30120000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (4, 4, 4, 'Avenida Dante Michelini', 4000, 29000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (5, 5, 5, 'Rua Oswaldo Cruz', 5000, 40100000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (6, 6, 6, 'Rua Vicente Machado', 6000, 80000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (7, 7, 7, 'Avenida Beira-Mar', 7000, 88000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (8, 8, 8, 'Rua Padre Chagas', 8000, 90000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (9, 9, 9, 'Rua Antônio de Goes', 9000, 50000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (10, 10, 10, 'Avenida do Turismo', 10000, 69000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (11, 11, 11, 'Rua Figueiredo Magalhães', 11000, 22031000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (12, 12, 12, 'Avenida Paulista', 12000, 01311000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (13, 13, 13, 'Rua Consolação', 13000, 01302000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (14, 14, 14, 'Rua José Bonifácio', 14000, 01003000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (15, 15, 15, 'Avenida Brasil', 15000, 22231000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (16, 16, 16, 'Rua 7 de Abril', 16000, 01044000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (17, 17, 17, 'Rua da Alfândega', 17000, 20070000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (18, 18, 18, 'Rua Visconde de Pirajá', 18000, 22410000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (19, 19, 19, 'Rua Barão de Ipanema', 19000, 22071000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (20, 20, 20, 'Rua Dona Laura', 20000, 90430000);

INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (1, 'João Silva', '12345678901', 'joao.silva@email.com', 'JoaoSilva@2024');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (2, 'Maria Oliveira', '98765432100', 'maria.oliveira@email.com', 'MariaOliveira#987');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (3, 'Carlos Souza', '11122233344', 'carlos.souza@email.com', 'CarSou123!');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (4, 'Ana Lima', '44455566677', 'ana.lima@email.com', 'AnaLima@555');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (5, 'Paulo Ferreira', '88899900011', 'paulo.ferreira@email.com', 'PauloFer99@');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (6, 'Clara Martins', '33344455566', 'clara.martins@email.com', 'ClarMart2024#');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (7, 'Lucas Pereira', '77788899900', 'lucas.pereira@email.com', 'LucPereira_77');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (8, 'Juliana Santos', '22233344455', 'juliana.santos@email.com', 'JulianaS@2024');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (9, 'Pedro Rocha', '99911122233', 'pedro.rocha@email.com', 'PedRocha99!');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (10, 'Fernanda Alves', '55566677788', 'fernanda.alves@email.com', 'FernandaAlv123');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (11, 'Rafael Costa', '55544433322', 'rafael.costa@email.com', 'RafCosta#456');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (12, 'Isabel Silva', '44433322211', 'isabel.silva@email.com', 'IsaSilva2024!');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (13, 'Mariana Lopes', '33322211100', 'mariana.lopes@email.com', 'MariLopes_99');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (14, 'Bruno Almeida', '22211100099', 'bruno.almeida@email.com', 'BrunoA1234!');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (15, 'Tatiana Ramos', '11100099988', 'tatiana.ramos@email.com', 'TatiRamos_88');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (16, 'André Borges', '99988877766', 'andre.borges@email.com', 'AndreBorges@');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (17, 'Elaine Figueiredo', '88877766655', 'elaine.figueiredo@email.com', 'ElaineFigu_@22');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (18, 'Rodrigo Moraes', '77766655544', 'rodrigo.moraes@email.com', 'RodMor_123!');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (19, 'Beatriz Carvalho', '66655544433', 'beatriz.carvalho@email.com', 'BeatrizCar#45');
INSERT INTO T_CS_USUARIO (id_usuario, nm_usuario, us_cpf, em_usuario, sn_usuario) VALUES (20, 'Eduardo Gouveia', '55524433322', 'eduardo.gouveia@email.com', 'EduGouv2024!');

INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (1, 1, 1, 'Rua Vergueiro', 1000, 12345000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (2, 2, 2, 'Avenida Atlântica', 2000, 22010000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (3, 3, 3, 'Rua dos Timbiras', 3000, 30120000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (4, 4, 4, 'Avenida Dante Michelini', 4000, 29000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (5, 5, 5, 'Rua Oswaldo Cruz', 5000, 40100000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (6, 6, 6, 'Rua Vicente Machado', 6000, 80000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (7, 7, 7, 'Avenida Beira-Mar', 7000, 88000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (8, 8, 8, 'Rua Padre Chagas', 8000, 90000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (9, 9, 9, 'Rua Antônio de Goes', 9000, 50000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (10, 10, 10, 'Avenida do Turismo', 10000, 69000000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (11, 11, 11, 'Rua Figueiredo Magalhães', 11000, 22031000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (12, 12, 12, 'Avenida Paulista', 12000, 01311000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (13, 13, 13, 'Rua Consolação', 13000, 01302000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (14, 14, 14, 'Rua José Bonifácio', 14000, 01003000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (15, 15, 15, 'Avenida Brasil', 15000, 22231000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (16, 16, 16, 'Rua 7 de Abril', 16000, 01044000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (17, 17, 17, 'Rua da Alfândega', 17000, 20070000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (18, 18, 18, 'Rua Visconde de Pirajá', 18000, 22410000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (19, 19, 19, 'Rua Barão de Ipanema', 19000, 22071000);
INSERT INTO T_CS_LOGRADOURO (id_logradouro, t_cs_bairro_id_bairro, t_cs_usuario_id_usuario, nm_logradouro, nr_logradouro, nr_cep) VALUES (20, 20, 20, 'Rua Dona Laura', 20000, 90430000);

INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (1, 1, 'Toyota Corolla', 2019);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (2, 2, 'Honda Civic', 2020);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (3, 3, 'Ford Fiesta', 2018);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (4, 4, 'Chevrolet Onix', 2021);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (5, 5, 'Volkswagen Gol', 2017);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (6, 6, 'Renault Kwid', 2022);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (7, 7, 'Nissan Kicks', 2021);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (8, 8, 'Hyundai HB20', 2020);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (9, 9, 'Fiat Argo', 2019);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (10, 10, 'Jeep Compass', 2023);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (11, 11, 'Kia Seltos', 2022);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (12, 12, 'Peugeot 208', 2019);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (13, 13, 'Citroën C3', 2020);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (14, 14, 'Mazda CX-30', 2021);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (15, 15, 'Toyota Hilux', 2023);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (16, 16, 'Ford EcoSport', 2018);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (17, 17, 'Chery Tiggo 5', 2019);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (18, 18, 'Chevrolet Tracker', 2022);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (19, 19, 'Honda HR-V', 2023);
INSERT INTO T_CS_CARRO (id_carro, t_cs_usuario_id_usuario, mc_carro, md_carro) VALUES (20, 20, 'Volkswagen T-Cross', 2021);
 
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (1, 1, 55, 11, 912345678, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (2, 2, 55, 21, 923456789, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (3, 3, 55, 31, 934567890, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (4, 4, 55, 41, 945678901, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (5, 5, 55, 51, 956789012, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (6, 6, 55, 61, 967890123, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (7, 7, 55, 71, 978901234, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (8, 8, 55, 81, 989012345, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (9, 9, 55, 91, 990123456, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (10, 10, 55, 99, 901234567, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (11, 11, 55, 11, 912345679, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (12, 12, 55, 21, 923456790, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (13, 13, 55, 31, 934567891, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (14, 14, 55, 41, 945678902, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (15, 15, 55, 51, 956789013, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (16, 16, 55, 61, 967890234, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (17, 17, 55, 71, 978901345, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (18, 18, 55, 81, 989012456, 'Fixo', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (19, 19, 55, 91, 990123567, 'Celular', 'A');
INSERT INTO T_CS_TELEFONE_USUARIO (t_cs_usuario_id_usuario, id_telefone, nr_ddi, nr_ddd, nr_telefone, tp_telefone, st_telefone) VALUES (20, 20, 55, 99, 901234678, 'Fixo', 'A');
 
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (1, 1, 745, 'Falha motor', 'Motor', 'Superaquecimento');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (2, 2, 850, 'Troca embreagem', 'Embreagem', 'Desgaste excessivo');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (3, 3, 520, 'Pane elétrica', 'Elétrico', 'Falha no painel');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (4, 4, 920, 'Problema suspensão', 'Suspensão', 'Subst. traseira');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (5, 5, 680, 'Vazamento óleo', 'Motor', 'Vedação defeit.');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (6, 6, 950, 'Problema direção', 'Direção', 'Folga na caixa');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (7, 7, 575, 'Falha freios', 'Freios', 'Ineficaz e perigoso');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (8, 8, 640, 'Troca radiador', 'Arrefec.', 'Radiador vazando');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (9, 9, 765, 'Defeito alternador', 'Elétrico', 'Bateria sem carga');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (10, 10, 830, 'Problema ar-cond.', 'Ar-cond.', 'Parou de func.');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (11, 11, 990, 'Falha transmissão', 'Transmissão', 'Trancos ao trocar');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (12, 12, 605, 'Troca pastilhas', 'Freios', 'Desgaste completo');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (13, 13, 560, 'Subst. bateria', 'Bateria', 'Sem capacidade');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (14, 14, 715, 'Problema injeção', 'Injeção', 'Perda potência');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (15, 15, 850, 'Troca amortec.', 'Suspensão', 'Desgaste total');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (16, 16, 710, 'Vazamento freio', 'Freios', 'Vazamento fluido');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (17, 17, 980, 'Falha módulo', 'Injeção', 'Falha eletrônica');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (18, 18, 620, 'Desgaste rolamento', 'Suspensão', 'Necessita troca');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (19, 19, 535, 'Problema cardan', 'Transmissão', 'Folga no eixo');
INSERT INTO T_CS_PROBLEMAS (id_problema, t_cs_carro_id_carro, vl_problema, nm_problema, tp_peca_problema, dc_problema) 
VALUES (20, 20, 940, 'Defeito câmbio', 'Transmissão', 'Dificuldade engatar');


-- 1. Relatório com Classificação de Dados
-- Exemplo 1: Classificar os carros pelos valores de problemas mais caros
CREATE OR REPLACE VIEW vw_carros_problemas_caros AS
SELECT 
    c.id_carro AS carro_id,
    c.mc_carro AS modelo_carro,
    p.vl_problema AS valor_problema
FROM 
    T_CS_CARRO c
JOIN 
    T_CS_PROBLEMAS p ON c.id_carro = p.t_cs_carro_id_carro
ORDER BY 
    p.vl_problema DESC;
    
-- Exemplo 2: Listar usuários classificados pelo nome de usuário
CREATE OR REPLACE VIEW vw_usuarios_classificados AS
SELECT 
    u.id_usuario AS usuario_id,
    u.nm_usuario AS nome_usuario
FROM 
    T_CS_USUARIO u
ORDER BY 
    u.nm_usuario ASC;
    
-- 2. Relatório com Função Numérica Simples
-- Exemplo 1: Calcular a média do valor dos problemas dos carros
CREATE OR REPLACE VIEW vw_media_valor_problemas AS
SELECT 
    AVG(vl_problema) AS media_valor_problemas
FROM 
    T_CS_PROBLEMAS;
-- Exemplo 2: Calcular a soma total dos valores dos problemas para cada carro
CREATE OR REPLACE VIEW vw_total_valor_problemas_carro AS
SELECT 
    t_cs_carro_id_carro AS carro_id,
    SUM(vl_problema) AS total_valor_problemas
FROM 
    T_CS_PROBLEMAS
GROUP BY 
    t_cs_carro_id_carro;
    
-- 3. Relatório com Função de Grupo
-- Exemplo 1: Contar quantos problemas cada carro tem
CREATE OR REPLACE VIEW vw_quantidade_problemas_por_carro AS
SELECT 
    t_cs_carro_id_carro AS carro_id,
    COUNT(id_problema) AS quantidade_problemas
FROM 
    T_CS_PROBLEMAS
GROUP BY 
    t_cs_carro_id_carro;
-- Exemplo 2: Contar quantos carros estão registrados no sistema por usuário
CREATE OR REPLACE VIEW vw_quantidade_carros_por_usuario AS
SELECT 
    t_cs_usuario_id_usuario AS usuario_id,
    COUNT(id_carro) AS quantidade_carros
FROM 
    T_CS_CARRO
GROUP BY 
    t_cs_usuario_id_usuario;

-- 4. Relatório com Subconsulta
-- Exemplo 1: Listar os carros que têm problemas acima da média de valor de problemas
CREATE OR REPLACE VIEW vw_carros_problemas_acima_media AS
SELECT 
    c.id_carro AS carro_id,
    c.mc_carro AS modelo_carro
FROM 
    T_CS_CARRO c
WHERE 
    c.id_carro IN (
        SELECT 
            t_cs_carro_id_carro 
        FROM 
            T_CS_PROBLEMAS 
        WHERE 
            vl_problema > (SELECT AVG(vl_problema) FROM T_CS_PROBLEMAS)
    );
    
-- Exemplo 2: Mostrar os usuários que possuem carros com pelo menos um problema de transmissão
CREATE OR REPLACE VIEW vw_usuarios_com_problema_transmissao AS
SELECT 
    u.id_usuario AS usuario_id,
    u.nm_usuario AS nome_usuario
FROM 
    T_CS_USUARIO u
WHERE 
    u.id_usuario IN (
        SELECT 
            c.t_cs_usuario_id_usuario
        FROM 
            T_CS_CARRO c
        JOIN 
            T_CS_PROBLEMAS p ON c.id_carro = p.t_cs_carro_id_carro
        WHERE 
            p.tp_peca_problema = 'Transmissão'
    );
    
-- 5. Relatório com Junção de Tabelas
-- Exemplo 1: Listar o nome dos carros e o valor dos problemas relacionados a eles
CREATE OR REPLACE VIEW vw_carros_com_valores_problemas AS
SELECT 
    c.id_carro AS carro_id,
    c.mc_carro AS modelo_carro,
    p.vl_problema AS valor_problema
FROM 
    T_CS_CARRO c
JOIN 
    T_CS_PROBLEMAS p ON c.id_carro = p.t_cs_carro_id_carro;
-- Exemplo 2: Mostrar os problemas dos carros com o nome de seus respectivos usuários
CREATE OR REPLACE VIEW vw_problemas_com_usuarios AS
SELECT 
    u.id_usuario AS usuario_id,
    u.nm_usuario AS nome_usuario,
    c.id_carro AS carro_id,
    c.mc_carro AS modelo_carro,
    p.nm_problema AS nome_problema,
    p.vl_problema AS valor_problema
FROM 
    T_CS_USUARIO u
JOIN 
    T_CS_CARRO c ON u.id_usuario = c.t_cs_usuario_id_usuario
JOIN 
    T_CS_PROBLEMAS p ON c.id_carro = p.t_cs_carro_id_carro;
    
    
-- 1. Relatório com Classificação de Dados
--Exemplo 1: Classificar os carros pelos valores de problemas mais caros
SELECT * FROM vw_carros_problemas_caros;
-- Exemplo 2: Listar usuários classificados pelo nome de usuário
SELECT * FROM vw_usuarios_classificados;

-- 2. Relatório com Função Numérica Simples
-- Exemplo 1: Calcular a média do valor dos problemas dos carros
SELECT * FROM vw_media_valor_problemas;
--Exemplo 2: Calcular a soma total dos valores dos problemas para cada carro
SELECT * FROM vw_total_valor_problemas_carro;

--3. Relatório com Função de Grupo
--Exemplo 1: Contar quantos problemas cada carro tem
SELECT * FROM vw_quantidade_problemas_por_carro;
-- Exemplo 2: Contar quantos carros estão registrados no sistema por usuário
SELECT * FROM vw_quantidade_carros_por_usuario;

-- 4. Relatório com Subconsulta
-- Exemplo 1: Listar os carros que têm problemas acima da média de valor de problemas
SELECT * FROM vw_carros_problemas_acima_media;
-- Exemplo 2: Mostrar os usuários que possuem carros com pelo menos um problema de transmissão
SELECT * FROM vw_usuarios_com_problema_transmissao;

-- 5. Relatório com Junção de Tabelas
-- Exemplo 1: Listar o nome dos carros e o valor dos problemas relacionados a eles
SELECT * FROM vw_carros_com_valores_problemas;
-- Exemplo 2: Mostrar os problemas dos carros com o nome de seus respectivos usuários
SELECT * FROM vw_problemas_com_usuarios;

commit;
    
