CREATE OR REPLACE FUNCTION valida_movimentacao_estoque_func()
  RETURNS TRIGGER AS $$

DECLARE
  --VAR
  qtd_disponivel INTEGER; --Quantidade disponível do produto em estoque

BEGIN
  SELECT quantidade FROM produto WHERE id_produto = NEW.id_produto INTO qtd_disponivel;

  IF NEW.tp_movimentacao = 'E' THEN

    UPDATE produto SET quantidade = qtd_disponivel + NEW.quantidade WHERE id_produto = NEW.id_produto;
    
  ELSIF NEW.tp_movimentacao = 'S' THEN

    IF qtd_disponivel < NEW.quantidade THEN
      RAISE EXCEPTION 'Quantidade indisponível em estoque';
    ELSE
      UPDATE produto SET quantidade = qtd_disponivel - NEW.quantidade WHERE id_produto = NEW.id_produto;
    END IF;

  END IF;

  RETURN NEW;                                            
  	
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_movimentacao_estoque BEFORE INSERT 
  ON movimentacao_estoque FOR EACH ROW EXECUTE PROCEDURE 
    valida_movimentacao_estoque_func();

