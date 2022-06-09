--
-- File generated with SQLiteStudio v3.2.1 on qui jun 9 17:34:49 2022
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: tbl_estoque
CREATE TABLE tbl_estoque ("pk_est.id" INTEGER PRIMARY KEY AUTOINCREMENT, est_quantidade INTEGER NOT NULL, est_preco DOUBLE NOT NULL, est_tipo_mov INTEGER NOT NULL, est_data DATE NOT NULL, fk_usu_id INTEGER REFERENCES tbl_usuario (pk_usu_id) NOT NULL, fk_pro_id INTEGER REFERENCES tbl_produto (pk_pro_id) NOT NULL);

-- Table: tbl_produto
CREATE TABLE tbl_produto (pk_pro_id INTEGER PRIMARY KEY AUTOINCREMENT, pro_descricao VARCHAR (150) NOT NULL, pro_quantidade INTEGER, pro_valor DOUBLE NOT NULL);

-- Table: tbl_usuario
CREATE TABLE tbl_usuario (pk_usu_id INTEGER PRIMARY KEY AUTOINCREMENT, usu_nome VARCHAR (100) NOT NULL, usu_login VARCHAR (50) NOT NULL, usu_senha VARCHAR (20) NOT NULL);
INSERT INTO tbl_usuario (pk_usu_id, usu_nome, usu_login, usu_senha) VALUES (1, 'adrian', 'adrian', '123');
INSERT INTO tbl_usuario (pk_usu_id, usu_nome, usu_login, usu_senha) VALUES (2, 'Filipe', 'filipe', '123');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
