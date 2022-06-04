CREATE SEQUENCE IF NOT EXISTS id_historico_alteracao_produto_seq
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 99999999999
 START 1
 CACHE 1;

 CREATE TABLE historico_alteracao_produto (
  id_historico_alteracao_produto         INTEGER       DEFAULT NEXTVAL('id_historico_alteracao_produto_seq') PRIMARY KEY,
  id_produto                             UUID          NOT NULL,
  descricao                              VARCHAR(1000),
  nome                                   VARCHAR(100)  NOT NULL,
  quantidade                             INTEGER NOT   NULL,
  valor_fornecedor                       NUMERIC(19,2) NOT NULL,
  valor_revenda                          NUMERIC(19,2) NOT NULL,
  created_at                             TIMESTAMP     NOT NULL,
  id_categoria                           BIGINT,
  CONSTRAINT fk_produto_historico_alteracao_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE
);

CREATE TRIGGER updated_at_historico_alteracao_produto BEFORE UPDATE
  ON historico_alteracao_produto FOR EACH ROW EXECUTE PROCEDURE 
    updated_at_column();

CREATE TRIGGER created_at_historico_alteracao_produto BEFORE INSERT
  ON historico_alteracao_produto FOR EACH ROW EXECUTE PROCEDURE 
    created_at_column();

    