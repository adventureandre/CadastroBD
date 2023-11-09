-- Loja.dbo.pessoas definition
-- Drop table

-- DROP TABLE Loja.dbo.pessoas;

CREATE TABLE Loja.dbo.pessoas (
                                  id_pessoa int IDENTITY(0,1) NOT NULL,
                                  nome varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
                                  endereco varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
                                  cidade varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
                                  telefone varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
                                  email varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
                                  estado varchar(5) COLLATE Latin1_General_CI_AS NULL,
                                  CONSTRAINT pessoas_PK PRIMARY KEY (id_pessoa)
);


-- Loja.dbo.produto definition

-- Drop table

-- DROP TABLE Loja.dbo.produto;

CREATE TABLE Loja.dbo.produto (
                                  id_produto int IDENTITY(0,1) NOT NULL,
                                  nome varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
                                  quantidade int NOT NULL,
                                  precoVenda decimal(10,2) NOT NULL,
                                  CONSTRAINT produto_PK PRIMARY KEY (id_produto)
);


-- Loja.dbo.usuario definition

-- Drop table

-- DROP TABLE Loja.dbo.usuario;

CREATE TABLE Loja.dbo.usuario (
                                  id_usuario int IDENTITY(0,1) NOT NULL,
    [login] varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
    senha varchar(100) COLLATE Latin1_General_CI_AS NOT NULL,
    CONSTRAINT usuario_PK PRIMARY KEY (id_usuario)
    );


-- Loja.dbo.movimento definition

-- Drop table

-- DROP TABLE Loja.dbo.movimento;

CREATE TABLE Loja.dbo.movimento (
                                    id_movimento int IDENTITY(0,1) NOT NULL,
                                    id_usuario int NOT NULL,
                                    id_pessoa int NOT NULL,
                                    id_produto int NOT NULL,
                                    quantidade int NOT NULL,
                                    tipo varchar(1) COLLATE Latin1_General_CI_AS NOT NULL,
                                    valorUnitario decimal(38,0) NOT NULL,
                                    CONSTRAINT movimento_PK PRIMARY KEY (id_movimento),
                                    CONSTRAINT movimento_FK FOREIGN KEY (id_pessoa) REFERENCES Loja.dbo.pessoas(id_pessoa) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT movimento_FK_produto FOREIGN KEY (id_produto) REFERENCES Loja.dbo.produto(id_produto) ON DELETE CASCADE ON UPDATE CASCADE,
                                    CONSTRAINT movimento_FK_usuario FOREIGN KEY (id_usuario) REFERENCES Loja.dbo.usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Loja.dbo.pessoa_fisica definition

-- Drop table

-- DROP TABLE Loja.dbo.pessoa_fisica;

CREATE TABLE Loja.dbo.pessoa_fisica (
                                        id_pessoa int NOT NULL,
                                        cpf varchar(14) COLLATE Latin1_General_CI_AS NOT NULL,
                                        CONSTRAINT PK__pessoa_f__C2215307704D50EE PRIMARY KEY (id_pessoa),
                                        CONSTRAINT pessoa_fisica_FK FOREIGN KEY (id_pessoa) REFERENCES Loja.dbo.pessoas(id_pessoa) ON DELETE CASCADE ON UPDATE CASCADE
);


-- Loja.dbo.pessoa_juridica definition

-- Drop table

-- DROP TABLE Loja.dbo.pessoa_juridica;

CREATE TABLE Loja.dbo.pessoa_juridica (
                                          id_pessoa int NOT NULL,
                                          cnpj varchar(18) COLLATE Latin1_General_CI_AS NOT NULL,
                                          CONSTRAINT PK__pessoa_j__C2215307A091C633 PRIMARY KEY (id_pessoa),
                                          CONSTRAINT pessoa_juridica_FK FOREIGN KEY (id_pessoa) REFERENCES Loja.dbo.pessoas(id_pessoa) ON DELETE CASCADE ON UPDATE CASCADE
);
