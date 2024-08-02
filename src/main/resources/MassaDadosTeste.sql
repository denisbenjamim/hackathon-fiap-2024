INSERT INTO tb_autenticacao (cd_login, cd_hash_password) VALUES ('adj2','$2y$10$eeW6wqG9zgRq43kh9bCiMeE7Rrh6TIPmzmdVczZTvyuRh625Jz2Wi');

-- CLIENTE SEM CARTOES
INSERT INTO tb_cliente (cd_cpf, nm_cliente,ds_email,nr_telefone,nm_rua,nm_cidade, nm_estado,cd_cep,nm_pais) VALUES ('12345678900','NOME CLIENTE','EMAIL@EMAIL.COM','+55 11 1234-1234','nm_rua', 'nm_cidade', 'nm_estado', 'cd_cep','nm_pais');

-- CLIENTE COM 1 CARTAO JA CADASTRADO
INSERT INTO tb_cliente (cd_cpf, nm_cliente,ds_email,nr_telefone,nm_rua,nm_cidade, nm_estado,cd_cep,nm_pais) VALUES ('12345678902','CLIENTE COM 2 CARTOES','EMAIL@EMAIL.COM','+55 11 12343-1234','nm_rua', 'nm_cidade', 'nm_estado', 'cd_cep','nm_pais');
INSERT INTO tb_cartao (cd_cliente,nr_cartao,qt_limite, dt_validade,cd_cvv) VALUES ('12345678902', '1111 2222 3333 4440',1000, '2024-12-31', '123');

-- CLIENTE COM 2 CARTOES JA CADASTRADOS
INSERT INTO tb_cliente (cd_cpf, nm_cliente,ds_email,nr_telefone,nm_rua,nm_cidade, nm_estado,cd_cep,nm_pais) VALUES ('12345678901','CLIENTE COM 2 CARTOES','EMAIL@EMAIL.COM','+55 11 12341-1234','nm_rua', 'nm_cidade', 'nm_estado', 'cd_cep','nm_pais');
INSERT INTO tb_cartao (cd_cliente,nr_cartao,qt_limite, dt_validade,cd_cvv) VALUES ('12345678901', '1111 2222 3333 4441',1000, '2024-12-31', '123');
INSERT INTO tb_cartao (cd_cliente,nr_cartao,qt_limite, dt_validade,cd_cvv) VALUES ('12345678901', '1111 2222 3333 4442',1000, '2023-11-30', '1234');

-- PAGAMENTO AUTORIZADO
INSERT INTO tb_cliente (cd_cpf, nm_cliente,ds_email,nr_telefone,nm_rua,nm_cidade, nm_estado,cd_cep,nm_pais) VALUES ('92345678901','CLIENTE CONSULTA','EMAIL@EMAIL.COM','+55 11 12341-1234','nm_rua', 'nm_cidade', 'nm_estado', 'cd_cep','nm_pais');
INSERT INTO tb_cartao (cd_cliente,nr_cartao,qt_limite, dt_validade,cd_cvv) VALUES ('92345678901', '9111 2222 3333 4441',1000, '2024-12-31', '123');
INSERT INTO tb_pagamento_aprovado (cd_pagamento, cd_cliente, nr_cartao, vl_pagamento,ds_pagamento, tp_pagamento, tp_situacao) 
values ('b3eb2d34-9379-4617-bc06-5ecffc2aba97', '92345678901', '9111 2222 3333 4441', 500, 'compra mercadoria X', 'cartao_credito', 'APROVADO');
INSERT INTO tb_pagamento_recusado (cd_pagamento, cd_cliente, nr_cartao, vl_pagamento, tp_pagamento, tp_situacao) 
values ('b58bf0b7-c5f6-4efd-93ff-e6ac628062e0', '92345678901', '9111 2222 3333 4441', 1500, 'cartao_credito', 'RECUSADO');

