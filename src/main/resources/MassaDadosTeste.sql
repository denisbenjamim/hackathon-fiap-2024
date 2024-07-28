-- CLIENTE SEM CARTOES
INSERT INTO tb_cliente (cd_cpf, nm_cliente,ds_email,nr_telefone,nm_rua,nm_cidade, nm_estado,cd_cep,nm_pais) VALUES ('12345678900','NOME CLIENTE','EMAIL@EMAIL.COM','nr_telefone','nm_rua', 'nm_cidade', 'nm_estado', 'cd_cep','nm_pais');

-- CLIENTE COM 1 CARTAO JA CADASTRADO
INSERT INTO tb_cliente (cd_cpf, nm_cliente,ds_email,nr_telefone,nm_rua,nm_cidade, nm_estado,cd_cep,nm_pais) VALUES ('12345678902','CLIENTE COM 2 CARTOES','EMAIL@EMAIL.COM','nr_telefone','nm_rua', 'nm_cidade', 'nm_estado', 'cd_cep','nm_pais');
INSERT INTO tb_cartao (cd_cliente,nr_cartao,qt_limite, dt_validade,cd_cvv) VALUES ('12345678902', '1111 2222 3333 4440',1000, '11/12', '123');

-- CLIENTE COM 2 CARTOES JA CADASTRADOS
INSERT INTO tb_cliente (cd_cpf, nm_cliente,ds_email,nr_telefone,nm_rua,nm_cidade, nm_estado,cd_cep,nm_pais) VALUES ('12345678901','CLIENTE COM 2 CARTOES','EMAIL@EMAIL.COM','nr_telefone','nm_rua', 'nm_cidade', 'nm_estado', 'cd_cep','nm_pais');
INSERT INTO tb_cartao (cd_cliente,nr_cartao,qt_limite, dt_validade,cd_cvv) VALUES ('12345678901', '1111 2222 3333 4441',1000, '11/12', '123');
INSERT INTO tb_cartao (cd_cliente,nr_cartao,qt_limite, dt_validade,cd_cvv) VALUES ('12345678901', '1111 2222 3333 4442',1000, '10/12', '1234');