

-- Drop table

-- DROP TABLE public.configs;

CREATE TABLE public.configs (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	staging_src_username varchar(100) NOT NULL,
	staging_src_pwd varchar(100) NOT NULL,
	staging_src_port int4 NOT NULL,
	staging_db_name varchar(100) NOT NULL,
	staging_src_host varchar(100) NOT NULL,
	wh_src_username varchar(100) NOT NULL,
	wh_src_pwd varchar(100) NOT NULL,
	wh_src_port int4 NOT NULL,
	wh_db_name varchar(100) NOT NULL,
	wh_src_host varchar(100) NOT NULL,
	mart_src_username varchar(100) NOT NULL,
	mart_src_pwd varchar(100) NOT NULL,
	mart_src_port int4 NOT NULL,
	mart_db_name varchar(100) NOT NULL,
	mart_src_host varchar(100) NOT NULL,
	download_path text NOT NULL,
	error_to_mail varchar(100) NOT NULL,
	flag varchar(100) NULL,
	status varchar(100) NULL,
	CONSTRAINT configs_pk PRIMARY KEY (id)
);


-- public.file_log definition

-- Drop table

-- DROP TABLE public.file_log;

CREATE TABLE public.file_log (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	name_file varchar(255) NULL,
	event_type varchar(255) NULL,
	status varchar(255) NULL,
	create_on timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT file_log_pk PRIMARY KEY (id)
);


-- public.file_config definition

-- Drop table

-- DROP TABLE public.file_config;

CREATE TABLE public.file_config (
	id int4 GENERATED ALWAYS AS IDENTITY NOT NULL,
	id_config int4 NOT NULL,
	name_file varchar(255) NULL,
	file_timestamp timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	file_path varchar(255) NULL,
	file_collumn_list text NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	created_by varchar(255) NULL,
	deleted int4 DEFAULT 1 NULL,
	status varchar(255) NULL,
	CONSTRAINT file_config_pk PRIMARY KEY (id),
	CONSTRAINT file_config_configs_fk FOREIGN KEY (id_config) REFERENCES public.configs(id)
);