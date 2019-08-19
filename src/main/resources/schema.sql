--prompt Creating TB_CLIENTE...
create table tb_cliente
(
  id NUMBER NOT NULL AUTO_INCREMENT,
  nome VARCHAR2(1000) NOT NULL,
  sexo VARCHAR2(5),
  email VARCHAR2(100),
  data_nascimento VARCHAR2(10) NOT NULL,
  naturalidade VARCHAR2(100),
  nacionalidade VARCHAR2(100),
  cpf VARCHAR2(14) UNIQUE NOT NULL
)
;
create unique index IDX_ID_CLIENTE on tb_cliente (id);