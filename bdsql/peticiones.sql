-------------------------------------------
--Manejo de peticiones de Linea Blanca SA
-------------------------------------------
drop table "LBSASQL"."Peticion_modif_venta";
CREATE TABLE "LBSASQL"."Peticion_modif_venta"
(
    id_peticion serial NOT NULL,
    id_empleado char(10) NOT NULL,
    id_venta int NOT NULL,
    aprobacion_pendiente boolean NOT NULL,
    peticion_aceptada boolean NOT NULL,
	razon_modificacion varchar(250) NOT NULL,
    fecha_actualizacion timestamp without time zone DEFAULT current_timestamp NOT NULL,
    fecha_envio_peticion timestamp without time zone DEFAULT current_timestamp NOT NULL,

    PRIMARY KEY (id_peticion),
	CONSTRAINT id_venta FOREIGN KEY (id_venta)
        REFERENCES "LBSASQL"."Compra" (id_compra) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
        REFERENCES "LBSASQL"."Empleado" (num_cedula) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);



ALTER TABLE "LBSASQL"."Peticion_modif_venta"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Peticion_modif_venta"
    IS 'Aqui se almacenan las peticiones de modificacion de venta';
	
--valor de prueba	
	INSERT INTO "LBSASQL"."Peticion_modif_venta"(id_empleado, id_venta, aprobacion_pendiente, peticion_aceptada, razon_modificacion, fecha_actualizacion, fecha_envio_peticion)
	VALUES ('234757689' , 1, true, false,'Se facturo un producto erroneo y se cobro $100 extra al cliente', current_timestamp, current_timestamp);