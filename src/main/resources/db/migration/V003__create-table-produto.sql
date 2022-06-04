CREATE TABLE IF NOT EXISTS produto (
  id_produto           UUID          NOT NULL,
  descricao            VARCHAR(1000),
  nome                 VARCHAR(100)  NOT NULL,
  quantidade           INTEGER       NOT NULL,
  valor_fornecedor     NUMERIC(19,2) NOT NULL,
  valor_revenda        NUMERIC(19,2) NOT NULL,
	created_at           TIMESTAMP     NOT NULL,
	updated_at           TIMESTAMP,
  id_categoria         BIGINT,
  PRIMARY KEY (id_produto),
  CONSTRAINT fk_produto_categoria_produto FOREIGN KEY (id_categoria) REFERENCES categoria_produto(id_categoria)
);

CREATE TRIGGER updated_at_produto BEFORE UPDATE
  ON produto FOR EACH ROW EXECUTE PROCEDURE 
    updated_at_column();

CREATE TRIGGER created_at_produto BEFORE INSERT
  ON produto FOR EACH ROW EXECUTE PROCEDURE 
    created_at_column();