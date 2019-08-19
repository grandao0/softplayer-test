--prompt Creating TB_CLIENTE...
CREATE TABLE tb_cliente
(
  id SERIAL PRIMARY KEY,
  nome VARCHAR2(150) NOT NULL,
  sexo VARCHAR2(5),
  email VARCHAR2(100),
  data_nascimento VARCHAR2(10) NOT NULL,
  naturalidade VARCHAR2(100),
  nacionalidade VARCHAR2(100),
  cpf VARCHAR2(14) UNIQUE NOT NULL,
  data_cadastro TIMESTAMP NOT NULL,
  data_atualizacao TIMESTAMP NOT NULL
);
CREATE UNIQUE index IDX_ID_CLIENTE ON tb_cliente (id);