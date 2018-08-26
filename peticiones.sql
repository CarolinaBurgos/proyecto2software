-------------------------------------------
--Manejo de peticiones de Linea Blanca SA
-------------------------------------------

CREATE TABLE "LBSASQL"."Peticion_modif_venta"
(
    id_peticion serial NOT NULL,
    id_empleado char(10) NOT NULL,
    id_venta int NOT NULL,
    aprobacion_pendiente boolean NOT NULL,
    peticion_aceptada boolean NOT NULL,
    fecha_actualizacion timestamp without time zone NOT NULL,
    fecha_envio_peticion timestamp without time zone NOT NULL,
    PRIMARY KEY (id_peticion)
);

ALTER TABLE "LBSASQL"."Peticion_modif_venta"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Peticion_modif_venta"
    IS 'Aqui se almacenan las peticiones de modificacion de venta';