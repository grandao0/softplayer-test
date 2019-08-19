--prompt Loading CLIENTE...
insert into tb_cliente (id, nome, sexo, email, data_nascimento, naturalidade, nacionalidade, cpf, data_cadastro, data_atualizacao)
values (1, 'cassio', 'M', 'cassio@email.com', CURRENT_DATE(), 'Patos de Minas', 'Brasil', '123.456.789-10', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into tb_cliente (id, nome, sexo, email, data_nascimento, naturalidade, nacionalidade, cpf, data_cadastro, data_atualizacao)
values (2, 'augusto', 'M', 'augusto@email.com', CURRENT_DATE(), 'Patos de Minas', 'Brasil', '123.456.789-11', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into tb_cliente (id, nome, sexo, email, data_nascimento, naturalidade, nacionalidade, cpf, data_cadastro, data_atualizacao)
values (3, 'babilonia', 'F', 'babilonia@email.com', CURRENT_DATE(), 'Patos de Minas', 'Brasil', '123.456.789-12', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into tb_cliente (id, nome, sexo, email, data_nascimento, naturalidade, nacionalidade, cpf, data_cadastro, data_atualizacao)
values (4, 'simoes', 'M', 'simoes@email.com', CURRENT_DATE(), 'Patos de Minas', 'Brasil', '123.456.789-13', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
insert into tb_cliente (id, nome, sexo, email, data_nascimento, naturalidade, nacionalidade, cpf, data_cadastro, data_atualizacao)
values (5, 'rocha', 'OUTRO', 'rocha@email.com', CURRENT_DATE(), 'Patos de Minas', 'Brasil', '123.456.789-14', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());
--prompt 5 records loaded