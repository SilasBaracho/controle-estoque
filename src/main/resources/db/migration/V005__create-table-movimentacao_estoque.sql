CREATE SEQUENCE IF NOT EXISTS id_movimentacao_estoque_seq
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 99999999999
 START 1
 CACHE 1;

CREATE TABLE movimentacao_estoque (
  id_movimentacao_estoque                INTEGER       DEFAULT NEXTVAL('id_movimentacao_estoque_seq') PRIMARY KEY,
  id_produto                             UUID          NOT NULL,
  tp_movimentacao                        VARCHAR(2)    NOT NULL,
  quantidade                             INTEGER       NOT NULL,
  --valor_venda                            NUMERIC(19,2) NOT NULL,
  created_at                             TIMESTAMP     NOT NULL,
  CONSTRAINT fk_produto_movimentacao_estoque FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE,
  CONSTRAINT tp_movimentacao_constraint CHECK (tp_movimentacao = 'E' OR tp_movimentacao = 'S') 
);

CREATE TRIGGER created_at_movimentacao_estoque BEFORE INSERT
  ON movimentacao_estoque FOR EACH ROW EXECUTE PROCEDURE 
    created_at_column();