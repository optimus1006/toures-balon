DO
$do$
    BEGIN
        IF EXISTS (SELECT FROM pg_database WHERE datname = 'Clientes-Write') THEN
            RAISE NOTICE 'Database already exists';  -- optional
        ELSE
            PERFORM dblink_exec('dbname=' || current_database()  -- current db
                        , 'CREATE DATABASE Clientes-Write');
        END IF;
        IF EXISTS (SELECT FROM pg_database WHERE datname = 'Clientes-Read') THEN
            RAISE NOTICE 'Database already exists';  -- optional
        ELSE
            PERFORM dblink_exec('dbname=' || current_database()  -- current db
                        , 'CREATE DATABASE Clientes-Read');
        END IF;
    END
$do$;
\connect Clientes-Write

INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'AVION', 250, 200, 'TON');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'BARCO', 500, 1000, 'TON');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'AUTOBUS', 120, 300, 'KG');
	
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'VAN', 80, 250, 'KG');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'CAMIONETA', 8, 200, 'KG');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'CARRO', 4, 80, 'KG');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'CARRO ALQUILADO', 5, 80, 'KG');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('HOTEL');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('MOTEL');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('RESORT');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('VILLA');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('CABAÑA');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('DEPORTIVO');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('MUSICAL');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('CONCIERTO');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('OTRO');
\connect Clientes-Read

INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'AVION', 250, 200, 'TON');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'BARCO', 500, 1000, 'TON');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'AUTOBUS', 120, 300, 'KG');
	
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'VAN', 80, 250, 'KG');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'CAMIONETA', 8, 200, 'KG');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'CARRO', 4, 80, 'KG');
	
INSERT INTO public.tipo_transporte(
	descripcion, capacidad_personas, capacidad_equipaje, tipo_capacidad_carga)
	VALUES ( 'CARRO ALQUILADO', 5, 80, 'KG');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('HOTEL');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('MOTEL');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('RESORT');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('VILLA');
	
INSERT INTO public.tipo_hospedaje(
	 descripcion)
	VALUES ('CABAÑA');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('DEPORTIVO');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('MUSICAL');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('CONCIERTO');
	
INSERT INTO public.tipo_evento(
	descripcion)
	VALUES ('OTRO');