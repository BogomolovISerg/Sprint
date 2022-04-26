CREATE DATABASE tovar;

CREATE SCHEMA public AUTHORIZATION postgres;
COMMENT ON SCHEMA public IS 'standard public schema for tovar database';

GRANT ALL ON SCHEMA public TO PUBLIC;
GRANT ALL ON SCHEMA public TO postgres;

CREATE TABLE public.tovar (
    id int PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DECIMAL NOT NULL);

CREATE UNIQUE INDEX products_id_uindex ON public.products (id);

INSERT INTO public.tovar (name, price) VALUES
('Кофе', 200.0),
('Чай', 100.0),
('Какао', 150.0),
('Лимонад', 70.0),
('Сидор', 125.0);