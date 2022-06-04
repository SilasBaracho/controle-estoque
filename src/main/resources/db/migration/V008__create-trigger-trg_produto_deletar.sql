CREATE OR REPLACE FUNCTION valida_delete_produto_func()
  RETURNS TRIGGER AS $$

DECLARE
  --VAR
  id_produto           UUID; -- ID do Produto

BEGIN
  SELECT mov_est.id_produto 
    FROM movimentacao_estoque AS mov_est 
   WHERE mov_est.id_produto = NEW.id_produto 
     AND mov_est.tp_movimentacao = 'S' 
    INTO id_produto;
  
  IF id_produto IS NULL THEN
    RAISE EXCEPTION 'Foi encontrado uma venda com o produto informado. Não será possível realizar a exclusão do produto';
  END IF;
  
  RETURN NEW;                                            
  	
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_produto_deletar BEFORE DELETE 
  ON produto FOR EACH ROW EXECUTE PROCEDURE 
    valida_delete_produto_func();

