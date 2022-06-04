CREATE OR REPLACE FUNCTION insert_historico_alteracao_produto_func()
  RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO historico_alteracao_produto( id_produto,
                                           descricao,
                                           nome,
                                           quantidade,
                                           valor_fornecedor,
                                           valor_revenda,
                                           created_at,
                                           id_categoria )
                                   VALUES( OLD.id_produto,
                                           OLD.descricao,
                                           OLD.nome,
                                           OLD.quantidade,
                                           OLD.valor_fornecedor,
                                           OLD.valor_revenda,
                                           CURRENT_TIMESTAMP,
                                           OLD.id_categoria );                                              

  RETURN NEW;                                            
  	
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_produto_alteracao BEFORE UPDATE 
  ON produto FOR EACH ROW EXECUTE PROCEDURE 
    insert_historico_alteracao_produto_func();

