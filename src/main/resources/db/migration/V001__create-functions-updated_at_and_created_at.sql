CREATE OR REPLACE FUNCTION updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql';

CREATE OR REPLACE FUNCTION created_at_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.created_at = now(); 
   RETURN NEW;
END;
$$ language 'plpgsql';