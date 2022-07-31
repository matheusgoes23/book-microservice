DROP SEQUENCE IF EXISTS public.create_table_cambio_seq;
CREATE SEQUENCE public.create_table_cambio_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

DROP TABLE IF EXISTS public.cambio;
CREATE TABLE public.cambio (
  id SERIAL PRIMARY KEY,
  from_currency CHAR(3) NOT NULL,
  to_currency CHAR(3) NOT NULL,
  conversion_factor decimal(65,2) NOT NULL
)
