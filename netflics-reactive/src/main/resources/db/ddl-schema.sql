-- Drop table

-- DROP TABLE public.ator_filme;
-- DROP TABLE public.filme;
-- DROP TABLE public.genero;
-- DROP TABLE public.ator;

CREATE TABLE IF NOT EXISTS public.genero (
	id uuid DEFAULT gen_random_uuid() ,
	create_datetime timestamp NULL,
	last_update_datetime timestamp NULL,
	user_change varchar(255) NULL,
	user_create varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT genero_pkey PRIMARY KEY (id)
);

-- Drop table

CREATE TABLE IF NOT EXISTS public.filme (
	id uuid DEFAULT gen_random_uuid() ,
	create_datetime timestamp NULL,
	last_update_datetime timestamp NULL,
	user_change varchar(255) NULL,
	user_create varchar(255) NULL,
	lancamento date NULL,
	orcamento float8 NULL,
	titulo varchar(255) NULL,
	id_genero uuid NULL,
	CONSTRAINT filme_pkey PRIMARY KEY (id),
	CONSTRAINT fkqbq6jc1r8xbpsqiu2jud0cqi8 FOREIGN KEY (id_genero) REFERENCES genero(id)
);


-- Drop table

CREATE TABLE IF NOT EXISTS public.ator (
	id uuid DEFAULT gen_random_uuid(),
	create_datetime timestamp NULL,
	last_update_datetime timestamp NULL,
	user_change varchar(255) NULL,
	user_create varchar(255) NULL,
	data_nascimento varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT ator_pkey PRIMARY KEY (id)
);


-- Drop table

CREATE TABLE IF NOT EXISTS public.ator_filme (
	id_ator uuid NOT NULL,
	id_filme uuid NOT NULL,
	CONSTRAINT fkmn1jayv41uhtoqlg8nq82omn0 FOREIGN KEY (id_ator) REFERENCES ator(id),
	CONSTRAINT fkmqwarofcqqkp2f0ud1m3qeluy FOREIGN KEY (id_filme) REFERENCES filme(id)
);
