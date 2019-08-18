# --- Creation of events table

# --- !Ups
CREATE TABLE public.gastos (
	id serial NOT NULL,
	titulo varchar(50) NOT NULL,
	tipo varchar(20) NOT NULL,
	descripcion varchar(100) NULL,
	valor numeric(12,2) NOT NULL,
	fecha timestamp NOT NULL
);
COMMENT ON TABLE public.gastos IS 'almacena los gastos';

# --- !Downs
drop table public.gastos;