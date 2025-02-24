CREATE DATABASE techmanagerdb
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    LOCALE_PROVIDER = 'libc'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

----------------------------------------------------------

CREATE TABLE public."user"
(
    id serial NOT NULL,
    full_name character varying(200) NOT NULL,
    email character varying(200) NOT NULL,
    birth_date date,
    phone character varying(20),
    user_type character varying(30) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."user"
    OWNER to postgres;