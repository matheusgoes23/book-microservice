DROP SEQUENCE IF EXISTS public.create_table_book_seq;
CREATE SEQUENCE public.create_table_book_seq
    INCREMENT 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1;

DROP TABLE IF EXISTS public.book;
CREATE TABLE public.book (
  id SERIAL PRIMARY KEY,
  author VARCHAR(255),
  launch_date TIMESTAMP NOT NULL,
  price DECIMAL(65,2) NOT NULL,
  title VARCHAR(255)
)
