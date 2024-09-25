CREATE TABLE Estado(
	nome VARCHAR(30) NOT NULL,
    sigla VARCHAR(2) NOT NULL,
    CONSTRAINT PK_SIGLA PRIMARY KEY (sigla)
);

CREATE TABLE Cidade(
      id_cidade SERIAL,
      nome VARCHAR(70) NOT NULL,
      slug VARCHAR(70),
      fk_sigla VARCHAR(2) NOT NULL,
      CONSTRAINT PK_ID_CIDADE PRIMARY KEY (id_cidade),
      CONSTRAINT FK_SIGLA FOREIGN KEY (fk_sigla) REFERENCES Estado(sigla)
);

CREATE TABLE Bairro(
    id_bairro SERIAL,
    nome VARCHAR(90) NOT NULL,
    fk_id_cidade INT NOT NULL,
    CONSTRAINT PK_ID_BAIRRO PRIMARY KEY (id_bairro),
    CONSTRAINT FK_ID_CIDADE FOREIGN KEY (fk_id_cidade) REFERENCES Cidade(id_cidade)
);

CREATE TABLE Logradouro(
    id_logradouro SERIAL,
    cep VARCHAR(15) UNIQUE NOT NULL,
    nome VARCHAR(50) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    fk_id_cidade INT NOT NULL,
    fk_id_bairro INT NOT NULL,
    CONSTRAINT PK_ID_LOGRADOURO PRIMARY KEY (id_logradouro),
    CONSTRAINT FK_ID_CIDADE FOREIGN KEY (fk_id_cidade) REFERENCES Cidade(id_cidade),
    CONSTRAINT FK_ID_BAIRRO FOREIGN KEY (fk_id_bairro) REFERENCES Bairro(id_bairro)
);

CREATE TABLE Endereco(
    id_endereco SERIAL,
    numero INT NOT NULL,
    complemento VARCHAR(100),
    fk_id_logradouro INT NOT NULL,
    CONSTRAINT PK_ID_ENDERECO PRIMARY KEY (id_endereco),
    CONSTRAINT FK_ID_LOGRADOURO FOREIGN KEY (fk_id_logradouro) REFERENCES Logradouro(id_logradouro) ON DELETE CASCADE
);

CREATE TABLE Usuario(
    id_usuario SERIAL,
    cpf VARCHAR(14) NOT NULL,
    nome VARCHAR(50) NOT NULL,
    senha VARCHAR(50) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    admin BOOLEAN,
    observacao TEXT,
    fk_id_endereco INT,
    CONSTRAINT PK_ID_USUARIO PRIMARY KEY (id_usuario),
	CONSTRAINT CK_TELEFONE_USUARIO CHECK (LENGTH(telefone) >= 15),
	CONSTRAINT UQ_CPF_USUARIO UNIQUE (cpf),
    CONSTRAINT FK_ID_ENDERECO FOREIGN KEY (fk_id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Cliente(
    id_cliente SERIAL,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(20),
    observacao TEXT,
    fk_id_endereco INT,
    CONSTRAINT PK_ID_CLIENTE PRIMARY KEY (id_cliente),
	CONSTRAINT UQ_CPF_CLIENTE UNIQUE (cpf),
	CONSTRAINT CK_TELEFONE_CLIENTE CHECK (LENGTH(telefone) >= 15),
    CONSTRAINT FK_ID_ENDERECO FOREIGN KEY (fk_id_endereco) REFERENCES Endereco(id_endereco)
);

CREATE TABLE Categoria(
    nome_categoria VARCHAR(20),
    CONSTRAINT PK_NOME_CATEGORIA PRIMARY KEY(nome_categoria)
);

CREATE TABLE Produto(
    id_produto SERIAL,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(50),
    preco NUMERIC(10,2) NOT NULL,
    unidade VARCHAR(20) NOT NULL,
    quantidade INT DEFAULT 0,
    fk_nome_categoria VARCHAR(20) NOT NULL,
    CONSTRAINT PK_ID_PRODUTO PRIMARY KEY (id_produto),
    CONSTRAINT FK_NOME_CATEGORIA FOREIGN KEY (fk_nome_categoria) REFERENCES Categoria(nome_categoria)
);

CREATE TABLE Historico(
    id_historico SERIAL,
    data VARCHAR(20),
    tempo VARCHAR(20),
    preco_total NUMERIC(10,2),
    metodo_pagamento VARCHAR(25) NOT NULL,
    fk_id_cliente INT,
    fk_id_usuario INT NOT NULL,
    CONSTRAINT PK_ID_HISTORICO PRIMARY KEY (id_historico),
    CONSTRAINT FK_ID_CLIENTE FOREIGN KEY (fk_id_cliente) REFERENCES Cliente(id_cliente),
    CONSTRAINT FK_ID_USUARIO FOREIGN KEY (fk_id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Compra(
    id_compra SERIAL,
    preco NUMERIC(10,2) NOT NULL,
    unidade VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    fk_id_historico INT NOT NULL,
    fk_id_produto INT NOT NULL,
    CONSTRAINT PK_ID_COMPRA PRIMARY KEY (id_compra),
    CONSTRAINT FK_ID_HISTORICO FOREIGN KEY (fk_id_historico) REFERENCES Historico(id_historico),
    CONSTRAINT FK_ID_PRODUTO FOREIGN KEY (fk_id_produto) REFERENCES Produto(id_produto)
);

INSERT INTO Usuario (nome, cpf, senha, telefone, admin) VALUES('adm','000.000.000-00','123','(00) 00000-0000', true);

--CRIAÇÃO DE PROCEDURE
CREATE PROCEDURE InserirCliente (nome VARCHAR(50), cpf VARCHAR(14), telefone VARCHAR(20), observacao TEXT)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO cliente (nome, cpf, telefone, observacao) VALUES (nome, cpf, telefone, observacao);
END $$;

CREATE PROCEDURE InserirClienteComEndereco (nome VARCHAR(50), cpf VARCHAR(14), telefone VARCHAR(20), observacao TEXT, id_endereco INT)
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO cliente (nome, cpf, telefone, observacao, fk_id_endereco) VALUES (nome, cpf, telefone, observacao, id_endereco);
END $$;

CREATE PROCEDURE InserirProduto (nome VARCHAR(50), descricao VARCHAR(50), preco NUMERIC(10,2), unidade VARCHAR(20), quantidade INT, fk_nome_categoria VARCHAR(20))
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO Produto (nome, descricao, preco, unidade, quantidade, fk_nome_categoria) 
VALUES (nome, descricao, preco, unidade, quantidade, fk_nome_categoria);
END $$;

CREATE PROCEDURE UpdateProduto (nome_produto VARCHAR(50), descricao_produto VARCHAR(50), preco_produto NUMERIC(10,2), unidade_produto VARCHAR(20), quantidade_produto INT, nome_categoria VARCHAR(20), idProduto INT)
LANGUAGE plpgsql
AS $$
BEGIN
UPDATE produto SET nome = nome_produto, descricao = descricao_produto, preco = preco_produto, unidade = unidade_produto, quantidade = quantidade_produto, fk_nome_categoria = nome_categoria
WHERE id_produto = idProduto;
END $$;

--CRIAÇÃO DE VIEW
CREATE VIEW view_usuario
AS
SELECT U.id_usuario, U.nome AS nome_usuario, U.cpf, U.telefone, U."admin", CID.nome	AS nome_cidade, LGD.cep, LGD.nome AS nome_logradouro, BAI.nome AS nome_bairro
FROM usuario AS U
LEFT JOIN endereco AS E ON U.fk_id_endereco = E.id_endereco
LEFT JOIN logradouro AS LGD ON E.fk_id_logradouro = LGD.id_logradouro
LEFT JOIN bairro AS BAI ON LGD.fk_id_bairro = BAI.id_bairro
LEFT JOIN cidade AS CID ON LGD.fk_id_cidade = CID.id_cidade;

CREATE VIEW view_cliente
AS
SELECT CLI.id_cliente, CLI.nome AS nome_cliente, CLI.cpf, CLI.telefone, CID.nome AS nome_cidade, LGD.cep, LGD.nome AS nome_logradouro, BAI.nome AS nome_bairro
FROM cliente AS CLI
LEFT JOIN endereco AS E ON CLI.fk_id_endereco = E.id_endereco
LEFT JOIN logradouro AS LGD ON E.fk_id_logradouro = LGD.id_logradouro
LEFT JOIN bairro AS BAI ON LGD.fk_id_bairro = BAI.id_bairro
LEFT JOIN cidade AS CID ON LGD.fk_id_cidade = CID.id_cidade;

--CRIAÇÃO DE FUNCTION
CREATE FUNCTION listarComprasPorIdCliente(id_cliente int) 
RETURNS TABLE (
	id_compra INT,
	total_compra numeric(10,2),
	pagamento VARCHAR(25)
)
AS $$
BEGIN
	RETURN QUERY
	SELECT id_historico AS id_compra, preco_total AS total_compra, metodo_pagamento AS pagamento 
	FROM historico 
	WHERE fk_id_cliente = id_cliente;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION listarProdutosCompradosPorIdHistorico(id_historico INT)
RETURNS TABLE (
	id_itemCompra INT,
	nome VARCHAR(50),
	preco_total NUMERIC(10,2),
    unidade VARCHAR(25),
    qtd INT
)
AS $$
BEGIN
	RETURN QUERY
	SELECT COM.id_compra AS id_itemCompra, PROD.nome AS nome, (COM.preco*COM.quantidade) AS preco_total, COM.unidade AS unidade, COM.quantidade AS qtd
	FROM compra AS COM
	INNER JOIN produto AS PROD ON fk_id_produto = id_produto
	WHERE fk_id_historico = id_historico;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION diminuirQuantidadeProdutoNoEstoque()
RETURNS TRIGGER 
AS $$
BEGIN
	UPDATE produto 
	SET quantidade = (quantidade - NEW.quantidade)
	WHERE id_produto = NEW.fk_id_produto;
	
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--CRIAÇÃO DE TRIGGER
CREATE TRIGGER atualizarProdutoEstoqueAposInserirItemNoCarrinho 
AFTER INSERT ON compra
FOR EACH ROW
EXECUTE FUNCTION diminuirQuantidadeProdutoNoEstoque();

--CRIAÇÃO DE RULE
CREATE RULE cpf_invalido_cliente AS
ON INSERT TO cliente
WHERE LENGTH(NEW.cpf) != 14
DO INSTEAD NOTHING

CREATE RULE cpf_invalido_usuario AS
ON INSERT TO usuario
WHERE LENGTH(NEW.cpf) != 14
DO INSTEAD NOTHING

CREATE RULE produto_invalido AS
ON INSERT TO produto
WHERE NEW.preco < 0
DO INSTEAD NOTHING;
