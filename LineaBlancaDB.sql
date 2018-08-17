-- -----------------------------------------------------
-- Schema LBSASQL Linea Blanca SA SQL
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS "LBSASQL"
    AUTHORIZATION postgres;

COMMENT ON SCHEMA "LBSASQL"
    IS 'Esquema de la base de datos de linea blanca sa';
	
-- -----------------------------------------------------	
--Almacen de Linea Blanca SA
-- -----------------------------------------------------
CREATE TABLE "LBSASQL"."Almacen" (
  "id_almacen" SERIAL,
  "nombre" VARCHAR(250),
  "codigo" VARCHAR(50),
  "area" FLOAT(4),
  "es_matriz" BOOLEAN,
  "es_bodega" BOOLEAN,
  "fecha_creación" TIMESTAMP,
  "fecha_actualizacion" TIMESTAMP,
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_almacen")
);

ALTER TABLE "LBSASQL"."Almacen"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Almacen"
    IS 'tabla que contiene la informacion de los locales de linea blanca sa';
	
-- -----------------------------------------------------	
--Telefonos del almacen	
-- -----------------------------------------------------
CREATE TABLE "LBSASQL"."telefono_almacen" (
  "id_telefono" SERIAL,
  "id_almacen" CHAR(13),
  "num_telefono" CHAR(9),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_telefono"),
  CONSTRAINT id_almacen FOREIGN KEY (id_almacen)
  REFERENCES "LBSASQL"."Almacen" (id_almacen) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."telefono_almacen"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."telefono_almacen"
    IS 'tabla que contiene la informacion de los telefonos de los locales de linea blanca sa';
	
-- -----------------------------------------------------	
--Empleados del almacen
-- -----------------------------------------------------
CREATE TABLE "LBSASQL"."Empleado" (
  "num_cedula" CHAR(10),
  "nombres" VARCHAR(50),
  "apellidos" VARCHAR(50),
  "usuario" VARCHAR(25),
  "correo" VARCHAR(100),
  "direccion" VARCHAR(250),
  "contraseña" VARCHAR(25),
  "rol_actual" VARCHAR(50),
  "fecha_contratacion" TIMESTAMP,
  "fecha_actualizacion" TIMESTAMP,
  "empleado activo" BOOLEAN,
  "id_almacen" INT,
  PRIMARY KEY ("num_cedula"),
  CONSTRAINT id_almacen FOREIGN KEY (id_almacen)
  REFERENCES "LBSASQL"."Almacen" (id_almacen) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Empleado"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Empleado"
    IS 'Tabla que contiene la informacion de los empleados, esta asociada a los Almacenes de Linea Blanca SA';
	
-- -----------------------------------------------------
--Telefonos de los empleados
-- -----------------------------------------------------
CREATE TABLE "telefono_empleado" (
  "id_telefono" INT,
  "num_cedula" CHAR(13),
  "num_telefono" CHAR(9),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_telefono"),
  CONSTRAINT num_cedula FOREIGN KEY (num_cedula)
  REFERENCES "LBSASQL"."Empleado" (num_cedula) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);	

ALTER TABLE "LBSASQL"."telefono_empleado"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."telefono_empleado"
    IS 'Tabla que contiene la informacion de los telefonos de los empleados de Linea Blanca SA';
	
	
-- -----------------------------------------------------
--Articulos de Linea Blanca SA
-- -----------------------------------------------------
CREATE TABLE "LBSASQL"."Articulo" (
  "id_articulo" SERIAL,
  "descripcion" VARCHAR(50),
  "categoria" VARCHAR(50),
  "marca" VARCHAR(50),
  "color" VARCHAR(16),
  "consumo_electrico" INT,
  "precio_cliente_sin_iva" FLOAT(4),
  "costo_proveedor" FLOAT(4),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_articulo")
);

ALTER TABLE "LBSASQL"."Articulo"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Articulo"
    IS 'Tabla que contiene la informacion de los articulos de Linea Blanca SA';
	
-- ---------------------------------------------------------
--Articulos en inventario de los locales de Linea Blanca SA
-- ---------------------------------------------------------
CREATE TABLE "LBSASQL"."Articulo_almacenado" (
  "id_almacen" INT,
  "id_articulo" INT,
  "fecha_llegada" TIMESTAMP,
  "cantidad_articulo_disponible" INT,
  "reabastecimiento_solicitado" BOOLEAN,
  PRIMARY KEY ("id_articulo"),  
  CONSTRAINT id_almacen FOREIGN KEY (id_almacen)
  REFERENCES "LBSASQL"."Almacen" (id_almacen) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_articulo FOREIGN KEY (id_articulo)
  REFERENCES "LBSASQL"."Articulo" (id_articulo) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);	
	
	
ALTER TABLE "LBSASQL"."Articulo_almacenado"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Articulo_almacenado"
    IS 'Tabla que contiene la informacion de los articulos almacenados en un local especifico de Linea Blanca SA';
-- -----------------------------------------------------
--Clientes de Linea Blanca SA
-- -----------------------------------------------------
CREATE TABLE "LBSASQL"."Cliente" (
  "id_cliente" SERIAL,
  "nombre" VARCHAR(50),
  "correo" VARCHAR(100),
  "direccion" VARCHAR(250),
  "fecha_creacion" TIMESTAMP,
  "fecha_ultima_actualizacion" TIMESTAMP,
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_cliente")
);	

ALTER TABLE "LBSASQL"."Cliente"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Cliente"
    IS 'Tabla que contiene la informacion de los clientes de Linea Blanca SA';

-- -----------------------------------------------------
--Clientes que son contribuyentes registrados
-- -----------------------------------------------------	
CREATE TABLE "LBSASQL"."Cliente_contribuyente_registrado" (
  "RUC" CHAR(13),
  "id_cliente" INT,
  "razon_social" VARCHAR(250),
  "es_contrib_especial" BOOLEAN,
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("RUC"),
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente)
  REFERENCES "LBSASQL"."Cliente" (id_cliente) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);
ALTER TABLE "LBSASQL"."Cliente_contribuyente_registrado"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Cliente_contribuyente_registrado"
    IS 'Tabla que contiene la informacion de los clientes, que tributariamente son contribuyentes registrados';	
	
	
-- -----------------------------------------------------
--Telefonos de los Clientes contribuyentes registrados
-- -----------------------------------------------------	
CREATE TABLE "LBSASQL"."telefono_c_contribuyente_registrado" (
  "id_telefono" SERIAL,
  "RUC" CHAR(13),
  "num_telefono" CHAR(9),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_telefono"),
  CONSTRAINT RUC FOREIGN KEY (RUC)
  REFERENCES "LBSASQL"."Cliente_contribuyente_registrado" (RUC) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."telefono_c_contribuyente_registrado"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."telefono_c_contribuyente_registrado"
    IS 'Tabla que contiene la informacion de los telefonos de los clientes ( de tipo contribuyente registrado) de Linea Blanca SA';

-- -----------------------------------------------------
-- Clientes que no son contribuyentes registrados
-- -----------------------------------------------------
CREATE TABLE "LBSASQL"."Cliente_ciudadano" (
  "num_cedula" CHAR(10),
  "id_cliente" INT,
  "apellidos" VARCHAR(50),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("num_cedula"),
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente)
  REFERENCES "LBSASQL"."Cliente" (id_cliente) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);	
ALTER TABLE "LBSASQL"."Cliente_ciudadano"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Cliente_ciudadano"
    IS 'Tabla que contiene la informacion de los clientes, que no son contribuyentes registrados';	
	
-- -----------------------------------------------------
--Telefonos de los Clientes que no son contribuyentes registrados
-- -----------------------------------------------------	
	CREATE TABLE "LBSASQL"."telefono_c_ciudadano" (
  "id_telefono" INT,
  "num_cedula" CHAR(10),
  "num_telefono" CHAR(9),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_telefono"),
  CONSTRAINT num_cedula FOREIGN KEY (num_cedula)
  REFERENCES "LBSASQL"."Cliente_ciudadano" (num_cedula) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."telefono_c_ciudadano"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."telefono_c_ciudadano"
    IS 'Tabla que contiene la informacion de los telefonos de los clientes ( que no son de tipo contribuyente registrado) de Linea Blanca SA';

	
-- -----------------------------------------------------
--Proveedores de Linea Blanca SA 
-- -----------------------------------------------------
	
CREATE TABLE "LBSASQL"."Proveedor_CR" (
  "id_proveedor" CHAR(13),
  "razon_social" VARCHAR(250),
  "direccion" VARCHAR(250),
  "correo" VARCHAR(250),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_proveedor")
);

ALTER TABLE "LBSASQL"."Proveedor_CR"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Proveedor_CR"
    IS 'Tabla que contiene la informacion de los proveedores de Linea Blanca SA, que tributariamente son contribuyentes registrados';	

-- -----------------------------------------------------
--Telefonos de los proveedores de Linea Blanca SA
-- -----------------------------------------------------		


CREATE TABLE "LBSASQL"."telefono_proveedor" (
  "id_telefono" INT,
  "id_proveedor" CHAR(13),
  "num_telefono" CHAR(9),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_telefono"),
  CONSTRAINT id_proveedor FOREIGN KEY (id_proveedor)
  REFERENCES "LBSASQL"."Proveedor_CR" (id_proveedor) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

	
-- -----------------------------------------------------
--Formas de pago de Linea Blanca SA 
-- -----------------------------------------------------	
CREATE TABLE "LBSASQL"."Forma_de_pago" (
  "id_forma_de_pago" SERIAL,
  "nombre_fdp" VARCHAR(50),
  "tasa_fdp" FLOAT(4),
  PRIMARY KEY ("id_forma_de_pago")
);
	
-- -------------------------------------------------------
--Registros de compras de los clientes de Linea Blanca SA 
-- -------------------------------------------------------	

CREATE TABLE "LBSASQL"."Compra" (
  "id_compra" SERIAL,
  "tipo_comprobante_venta" VARCHAR(3),
  "fecha_compra" INT,
  "monto" FLOAT(4),
  "id_cliente" INT,
  "id_empleado" CHAR(10),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_compra","tipo_comprobante_venta"),
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente)
  REFERENCES "LBSASQL"."Cliente" (id_cliente) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Compra"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Compra"
    IS 'Tabla que contiene la informacion de las compras realizadas por los clientes de Linea Blanca SA';	

-- -------------------------------------------------------------------
--Articulos asociados a las compras de los clientes de Linea Blanca SA 
-- -------------------------------------------------------------------
	
CREATE TABLE "LBSASQL"."Articulos_vendidos" (
  "id_compra" INT,
  "id_articulo" INT,
  "cantidad_articulo" INT,
  PRIMARY KEY ("id_compra","id_articulo"),  
  CONSTRAINT id_articulo FOREIGN KEY (id_articulo)
  REFERENCES "LBSASQL"."Articulo" (id_articulo) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);
ALTER TABLE "LBSASQL"."Articulos_vendidos"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Articulos_vendidos"
    IS 'Tabla que contiene la informacion de los articulos asociados a las compras de los clientes de Linea Blanca SA ';	
	
-- -------------------------------------------------------------------
--Pagos asociados a las compras de los clientes de Linea Blanca SA 
-- -------------------------------------------------------------------	
	
CREATE TABLE "LBSASQL"."Pago" (
  "id_pago" INT,
  "id_compra" INT,
  "id_forma_de_pago" INT,
  "pago_verificado" BOOLEAN,
  PRIMARY KEY ("id_pago"),  
  CONSTRAINT id_compra FOREIGN KEY (id_compra)
  REFERENCES "LBSASQL"."Compra" (id_compra) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_forma_de_pago FOREIGN KEY (id_forma_de_pago)
  REFERENCES "LBSASQL"."Forma_de_pago" (id_forma_de_pago) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);
ALTER TABLE "LBSASQL"."Pago"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Pago"
    IS 'Tabla que contiene la informacion de los pagos asociados a las compras de los clientes de Linea Blanca SA ';


-- -----------------------------------------------------------------------------------
--Comprobantes de retencion asociados a las compras de los clientes de Linea Blanca SA 
-- -----------------------------------------------------------------------------------



CREATE TABLE "LBSASQL"."Comprobante_retencion_cliente" (
  "id_comprobante_retencion" INT,
  "numero_autorizacion" VARCHAR(50),
  "tipo_comprobante_venta" VARCHAR(50),
  "valor_retenido" FLOAT(4),
  "tipo_de_impuesto" VARCHAR(50),
  "ejercicio_fiscal" VARCHAR(50),
  "codigo_del_impuesto" VARCHAR(50),
  "porcentaje_de_retencion" INT,
  "id_compra" INT,
  PRIMARY KEY ("id_comprobante_retencion","numero_autorizacion","id_compra")
);
ALTER TABLE "LBSASQL"."Comprobante_retencion_cliente"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Comprobante_retencion_cliente"
    IS 'Tabla que contiene la informacion de los comprobantes de retencion asociados a las compras de los clientes (contribuyentes registrados, especiales) de Linea Blanca SA ';



-- -----------------------------------------------------------
--Registros de cotizaciones de los clientes de Linea Blanca SA 
-- -----------------------------------------------------------	


CREATE TABLE "LBSASQL"."Cotizacion" (
  "id_cotizacion" SERIAL,
  "fecha_cotizacion" INT,
  "monto_estimado" FLOAT(4),
  "id_cliente" INT,
  "id_empleado" CHAR(10),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_cotizacion"),
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente)
  REFERENCES "LBSASQL"."Cliente" (id_cliente) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Cotizacion"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Cotizacion"
    IS 'Tabla que contiene la informacion de las cotizaciones realizadas por los clientes de Linea Blanca SA';

-- -----------------------------------------------------------------------------------
--Estimaciones de pago asociadas a las cotizaciones de los clientes de Linea Blanca SA 
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Pago_estimado" (
  "id_pago" INT,
  "id_cotizacion" INT,
  "id_forma_de_pago" INT,
  PRIMARY KEY ("id_pago"),  
  CONSTRAINT id_cotizacion FOREIGN KEY (id_cotizacion)
  REFERENCES "LBSASQL"."Cotizacion" (id_cotizacion) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_forma_de_pago FOREIGN KEY (id_forma_de_pago)
  REFERENCES "LBSASQL"."Forma_de_pago" (id_forma_de_pago) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Pago_estimado"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Pago_estimado"
    IS 'Tabla que contiene la informacion de las estimaciones de pago asociadas a las cotizaciones de los clientes de Linea Blanca SA ';

	
-- -----------------------------------------------------------------------------------
--Articulos asociados a las cotizaciones de los clientes de Linea Blanca SA 
-- -----------------------------------------------------------------------------------
CREATE TABLE "LBSASQL"."Articulos_cotizados" (
  "id_cotizacion" INT,
  "id_articulo" INT,
  "cantidad_articulo" INT,
  PRIMARY KEY ("id_cotizacion","id_articulo"),  
  CONSTRAINT id_articulo FOREIGN KEY (id_articulo)
  REFERENCES "LBSASQL"."Articulo" (id_articulo) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);
ALTER TABLE "LBSASQL"."Articulos_cotizados"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Articulos_cotizados"
    IS 'Tabla que contiene la informacion de los articulos asociados a las cotizaciones de los clientes de Linea Blanca SA ';	


	
-- -----------------------------------------------------------------------------------
--Articulos asociados a las peticiones de articulos a los proveedores de Linea Blanca SA 
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Articulo_solicitado" (
  "id_solicitud" SERIAL,
  "id_proveedor" CHAR(13),
  "id_articulo" INT,
  "id_empleado" CHAR(10),
  "cantidad_articulo_solicitado" INT,
  PRIMARY KEY ("id_solicitud"),  
  CONSTRAINT id_articulo FOREIGN KEY (id_articulo)
  REFERENCES "LBSASQL"."Articulo" (id_articulo) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT id_proveedor FOREIGN KEY (id_proveedor)
  REFERENCES "LBSASQL"."Proveedor_CR" (id_proveedor) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Articulos_cotizados"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Articulos_cotizados"
    IS 'Tabla que contiene la informacion de los articulos asociados a las peticiones de articulos a los proveedores de Linea Blanca SA ';	

	
-- -----------------------------------------------------------------------------------
--Tablas de Auditoria: Registros de eventos sobre la base de datos
-- -----------------------------------------------------------------------------------

-- -----------------------------------------------------------------------------------
--Registros en tabla Almacen 
-- -----------------------------------------------------------------------------------
CREATE TABLE "LBSASQL"."Registro_eventos_almacen" (
  "id_registro" SERIAL,
  "id_empleado" CHAR(10),
  "id_almacen" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_almacen FOREIGN KEY (id_almacen)
  REFERENCES "LBSASQL"."Almacen" (id_almacen) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Registro_eventos_almacen"
    OWNER to postgres;
-- -----------------------------------------------------------------------------------
--Registros en tabla Empleado
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Registro_eventos_empleado" (
  "id_registro" SERIAL,
  "id_empleado" CHAR(10),
  "id_empleado_modificado" CHAR(10),
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT id_empleado_modificado FOREIGN KEY (id_empleado_modificado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado_modificado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);


ALTER TABLE "LBSASQL"."Registro_eventos_empleado"
    OWNER to postgres;

-- -----------------------------------------------------------------------------------
--Registros en tabla Articulo
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Registro_eventos_articulo" (
  "id_registro" SERIAL,
  "id_empleado" CHAR(10),
  "id_articulo" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_articulo FOREIGN KEY (id_articulo)
  REFERENCES "LBSASQL"."Articulo" (id_articulo) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Registro_eventos_articulo"
    OWNER to postgres;

-- -----------------------------------------------------------------------------------
--Registros en tabla Cliente 
-- -----------------------------------------------------------------------------------
CREATE TABLE "LBSASQL"."Registro_eventos_cliente" (
  "id_registro" SERIAL,
  "id_empleado" CHAR(10),
  "id_cliente" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente)
  REFERENCES "LBSASQL"."Cliente" (id_cliente) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Registro_eventos_cliente"
    OWNER to postgres;
-- -----------------------------------------------------------------------------------
--Registros en tabla Proveedor_CR 
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Registro_eventos_proveedor" (
  "id_registro" SERIAL,
  "id_empleado" CHAR(10),
  "id_proveedor" CHAR(13),
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION, 
  CONSTRAINT id_proveedor FOREIGN KEY (id_proveedor)
  REFERENCES "LBSASQL"."Proveedor_CR" (id_proveedor) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);


ALTER TABLE "LBSASQL"."Registro_eventos_proveedor"
    OWNER to postgres;
-- -----------------------------------------------------------------------------------
--Registros en tabla Cotizacion
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Registro_eventos_cotizacion" (
  "id_registro" SERIAL,
  "id_empleado" CHAR(10),
  "id_cotizacion" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_cotizacion FOREIGN KEY (id_cotizacion)
  REFERENCES "LBSASQL"."Cotizacion" (id_cotizacion) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);



ALTER TABLE "LBSASQL"."Registro_eventos_cotizacion"
    OWNER to postgres;


-- -----------------------------------------------------------------------------------
--Registros en tabla Compra y su pago correspondiente
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Registro_eventos_compra" (
  "id_registro" SERIAL,
  "id_empleado" CHAR(10),
  "id_compra" INT,
  "id_pago" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado)
  REFERENCES "LBSASQL"."Empleado" (id_empleado) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_compra FOREIGN KEY (id_compra)
  REFERENCES "LBSASQL"."Compra" (id_compra) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION,  
  CONSTRAINT id_pago FOREIGN KEY (id_pago)
  REFERENCES "LBSASQL"."Pago" (id_pago) MATCH SIMPLE
  ON UPDATE NO ACTION
  ON DELETE NO ACTION
);

ALTER TABLE "LBSASQL"."Registro_eventos_compra"
    OWNER to postgres;




