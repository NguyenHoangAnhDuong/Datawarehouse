-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION "user";

COMMENT ON SCHEMA public IS 'standard public schema';
-- public.temp_doji definition

-- Drop table

-- DROP TABLE temp_doji;

CREATE TABLE temp_doji (
	id text DEFAULT '"failed_data"'::text NULL,
	type_name text DEFAULT '"failed_data"'::text NULL,
	region text DEFAULT '"failed_data"'::text NULL,
	buy_value text DEFAULT '"failed_data"'::text NULL,
	sell_value text DEFAULT '"failed_data"'::text NULL,
	"source" text DEFAULT '"failed_data"'::text NULL
);


-- public.temp_sjc definition

-- Drop table

-- DROP TABLE temp_sjc;

CREATE TABLE temp_sjc (
	id text DEFAULT '"failed_load"'::text NULL,
	type_name text DEFAULT '"failed_load"'::text NULL,
	region text DEFAULT '"failed_load"'::text NULL,
	buy text DEFAULT '"failed_load"'::text NULL,
	buy_value text DEFAULT '"failed_load"'::text NULL,
	sell text DEFAULT '"failed_load"'::text NULL,
	sell_value text DEFAULT '"failed_load"'::text NULL,
	buy_differ text DEFAULT '"failed_load"'::text NULL,
	buy_differ_value text DEFAULT '"failed_load"'::text NULL,
	sell_differ text DEFAULT '"failed_load"'::text NULL,
	sell_differ_value text DEFAULT '"failed_load"'::text NULL,
	created_at text DEFAULT '"failed_load"'::text NULL
);


-- public.temp_pnj definition

-- Drop table

-- DROP TABLE temp_pnj;

CREATE TABLE temp_pnj (
	id text DEFAULT '"failed_data"'::text NULL,
	type_name text DEFAULT '"failed_data"'::text NULL,
	region text DEFAULT '"failed_data"'::text NULL,
	buy_value text DEFAULT '"failed_data"'::text NULL,
	sell_value text DEFAULT '"failed_data"'::text NULL,
	"source" text DEFAULT '"failed_data"'::text NULL
);


-- public.stg_doji_pnj definition

-- Drop table

-- DROP TABLE stg_doji_pnj;

CREATE TABLE stg_doji_pnj (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	type_name varchar(255) NOT NULL,
	region varchar(100) NOT NULL,
	buy_value numeric(10, 2) NOT NULL,
	sell_value numeric(10, 2) NOT NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	"source" varchar(10) NOT NULL,
	CONSTRAINT stg_doji_pnj_pk PRIMARY KEY (id)
);


-- public.stg_sjc definition

-- Drop table

-- DROP TABLE stg_sjc;

CREATE TABLE stg_sjc (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	type_name varchar(255) NOT NULL,
	region varchar(100) NOT NULL,
	buy_value numeric(10, 2) NOT NULL,
	sell_value numeric(10, 2) NOT NULL,
	buy_differ_value numeric(10, 2) DEFAULT 0 NULL,
	buy_differ varchar(10) NOT NULL,
	sell_differ_value numeric(10, 2) DEFAULT 0 NULL,
	sell_differ varchar(10) NOT NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	"source" varchar(10) DEFAULT '"SJC"'::character varying NULL,
	CONSTRAINT stg_sjc_pk PRIMARY KEY (id)
);
