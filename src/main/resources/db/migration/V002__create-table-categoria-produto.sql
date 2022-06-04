CREATE SEQUENCE IF NOT EXISTS id_categoria_produto_seq
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 99999999999
 START 1
 CACHE 1;

CREATE TABLE categoria_produto (
  id_categoria         INTEGER       DEFAULT NEXTVAL('id_categoria_produto_seq') PRIMARY KEY,
  descricao_categoria  VARCHAR(100)  NOT NULL,
  created_at           TIMESTAMP     NOT NULL,
  updated_at           TIMESTAMP
);

CREATE TRIGGER updated_at_categoria_produto BEFORE UPDATE
  ON categoria_produto FOR EACH ROW EXECUTE PROCEDURE 
    updated_at_column();

CREATE TRIGGER created_at_categoria_produto BEFORE INSERT
  ON categoria_produto FOR EACH ROW EXECUTE PROCEDURE 
    created_at_column();