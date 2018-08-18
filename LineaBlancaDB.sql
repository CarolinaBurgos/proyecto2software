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
  "id_almacen" SERIAL UNIQUE NOT NULL,
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
  "id_telefono" SERIAL UNIQUE NOT NULL,
  "id_almacen" INT,
  "num_telefono" CHAR(10),
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
  "num_cedula" CHAR(10) UNIQUE NOT NULL,
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
CREATE TABLE "LBSASQL"."telefono_empleado" (
  "id_telefono" SERIAL UNIQUE NOT NULL,
  "num_cedula" CHAR(13),
  "num_telefono" CHAR(10),
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
  "id_articulo" SERIAL UNIQUE NOT NULL,
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
  "id_art_alm" SERIAL UNIQUE NOT NULL,
  "id_almacen" INT NOT NULL,
  "id_articulo" INT NOT NULL,
  "fecha_llegada" TIMESTAMP,
  "cantidad_articulo_disponible" INT,
  "reabastecimiento_solicitado" BOOLEAN,
  PRIMARY KEY ("id_art_alm"),  
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
  "id_cliente" SERIAL UNIQUE NOT NULL,
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
  "RUC" CHAR(13) UNIQUE NOT NULL,
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
  "id_telefono" SERIAL UNIQUE NOT NULL,
  "RUC" CHAR(13),
  "num_telefono" CHAR(9),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_telefono")
);

ALTER TABLE "LBSASQL"."telefono_c_contribuyente_registrado"
    OWNER to postgres,
    ADD CONSTRAINT RUC FOREIGN KEY ("RUC")
    REFERENCES "LBSASQL"."Cliente_contribuyente_registrado" ("RUC") MATCH FULL
    ON UPDATE NO ACTION
    ON DELETE NO ACTION;
COMMENT ON TABLE "LBSASQL"."telefono_c_contribuyente_registrado"
    IS 'Tabla que contiene la informacion de los telefonos de los clientes ( de tipo contribuyente registrado) de Linea Blanca SA';

-- -----------------------------------------------------
-- Clientes que no son contribuyentes registrados
-- -----------------------------------------------------
CREATE TABLE "LBSASQL"."Cliente_ciudadano" (
  "num_cedula" CHAR(10) UNIQUE NOT NULL,
  "id_cliente" INT,
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
  "id_telefono" SERIAL UNIQUE NOT NULL,
  "num_cedula" CHAR(10),
  "num_telefono" CHAR(10),
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
  "id_proveedor" CHAR(13) UNIQUE NOT NULL,
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
  "id_telefono" SERIAL UNIQUE NOT NULL,
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
  "id_forma_de_pago" SERIAL UNIQUE NOT NULL,
  "nombre_fdp" VARCHAR(50),
  "tasa_fdp" FLOAT(4),
  PRIMARY KEY ("id_forma_de_pago")
);
	
-- -------------------------------------------------------
--Registros de compras de los clientes de Linea Blanca SA 
-- -------------------------------------------------------	

CREATE TABLE "LBSASQL"."Compra" (
  "id_compra" SERIAL UNIQUE NOT NULL,
  "tipo_comprobante_venta" VARCHAR(3),
  "fecha_compra" TIMESTAMP,
  "monto" FLOAT(4),
  "id_cliente" INT,
  "id_empleado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" ("num_cedula"),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_compra","tipo_comprobante_venta"),
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente)
  REFERENCES "LBSASQL"."Cliente" (id_cliente) MATCH SIMPLE
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
  "id_articulo_vendido" SERIAL UNIQUE NOT NULL,
  "id_compra" INT,
  "id_articulo" INT,
  "cantidad_articulo" INT,
  PRIMARY KEY ("id_articulo_vendido"),  
  CONSTRAINT id_articulo FOREIGN KEY (id_articulo)
  REFERENCES "LBSASQL"."Articulo" (id_articulo) MATCH SIMPLE,
  CONSTRAINT id_compra FOREIGN KEY (id_compra)
  REFERENCES "LBSASQL"."Compra" (id_compra) MATCH SIMPLE
  ON UPDATE CASCADE
  ON DELETE CASCADE
);
ALTER TABLE "LBSASQL"."Articulos_vendidos"
    OWNER to postgres;
COMMENT ON TABLE "LBSASQL"."Articulos_vendidos"
    IS 'Tabla que contiene la informacion de los articulos asociados a las compras de los clientes de Linea Blanca SA ';	
	
-- -------------------------------------------------------------------
--Pagos asociados a las compras de los clientes de Linea Blanca SA 
-- -------------------------------------------------------------------	
	
CREATE TABLE "LBSASQL"."Pago" (
  "id_pago" SERIAL UNIQUE NOT NULL,
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
  "id_comprobante_retencion" INT UNIQUE NOT NULL,
  "numero_autorizacion" VARCHAR(50) NOT NULL,
  "tipo_comprobante_venta" VARCHAR(50),
  "valor_retenido" FLOAT(4),
  "tipo_de_impuesto" VARCHAR(50),
  "ejercicio_fiscal" VARCHAR(50),
  "codigo_del_impuesto" VARCHAR(50),
  "porcentaje_de_retencion" INT,
  "id_compra" INT NOT NULL,
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
  "id_cotizacion" SERIAL UNIQUE NOT NULL,
  "fecha_cotizacion" TIMESTAMP,
  "monto_estimado" FLOAT(4),
  "id_cliente" INT,
  "id_empleado" CHAR(10) REFERENCES "LBSASQL"."Empleado" ("num_cedula"),
  "reg_eliminado" BOOLEAN,
  PRIMARY KEY ("id_cotizacion"),
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente)
  REFERENCES "LBSASQL"."Cliente" (id_cliente) MATCH SIMPLE
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
  "id_pago" SERIAL UNIQUE NOT NULL,
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
  "id_art_cotizado" SERIAL UNIQUE NOT NULL,
  "id_cotizacion" INT,
  "id_articulo" INT,
  "cantidad_articulo" INT,
  PRIMARY KEY ("id_art_cotizado"),  
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
  "id_solicitud" SERIAL UNIQUE NOT NULL,
  "id_proveedor" CHAR(13),
  "id_articulo" INT,
  "id_empleado" CHAR(10) REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "cantidad_articulo_solicitado" INT,
  PRIMARY KEY ("id_solicitud"),  
  CONSTRAINT id_articulo FOREIGN KEY (id_articulo)
  REFERENCES "LBSASQL"."Articulo" (id_articulo) MATCH SIMPLE
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
  "id_registro" SERIAL UNIQUE NOT NULL,
  "id_empleado" CHAR(10) REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "id_almacen" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
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
  "id_registro" SERIAL UNIQUE NOT NULL,
  "id_empleado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "id_empleado_modificado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro")
);


ALTER TABLE "LBSASQL"."Registro_eventos_empleado"
    OWNER to postgres;

-- -----------------------------------------------------------------------------------
--Registros en tabla Articulo
-- -----------------------------------------------------------------------------------

CREATE TABLE "LBSASQL"."Registro_eventos_articulo" (
  "id_registro" SERIAL UNIQUE NOT NULL,
  "id_empleado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "id_articulo" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"), 
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
  "id_registro" SERIAL UNIQUE NOT NULL,
  "id_empleado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "id_cliente" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
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
  "id_registro" SERIAL UNIQUE NOT NULL,
  "id_empleado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "id_proveedor" CHAR(13),
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
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
  "id_registro" SERIAL UNIQUE NOT NULL,
  "id_empleado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "id_cotizacion" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"),
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
  "id_registro" SERIAL UNIQUE NOT NULL,
  "id_empleado" CHAR(10)  REFERENCES "LBSASQL"."Empleado" (num_cedula),
  "id_compra" INT,
  "id_pago" INT,
  "fecha_modificacion" TIMESTAMP,
  "tipo_accion_realizada" VARCHAR(50),
  PRIMARY KEY ("id_registro"), 
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

CREATE OR REPLACE FUNCTION BuscarCliente(IN cedula int, OUT nameCL VARCHAR(50), OUT dirCL VARCHAR(250), OUT correoCL VARCHAR(100)) 
	RETURNS SETOF RECORD AS $BODY$
		DECLARE
			reg RECORD;																															
		BEGIN																														 
			FOR reg IN SELECT nombre, direccion, correo FROM "LBSASQL"."Cliente" WHERE id_cliente = cedula LOOP
				nameCL := reg.nombre;
				dirCL := reg.direccion;
				correoCL := reg.correo;
				RETURN NEXT;	
				END LOOP;
				RETURN ;																																	
		END																											 
	$BODY$ 	LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION BuscarEmpleadoUsuario(IN cedula CHAR(10), OUT nameEM VARCHAR(50), OUT apellidoEM VARCHAR(40), OUT usEM VARCHAR(25), OUT correoEM VARCHAR(100), OUT dirEM VARCHAR(250), OUT cargoEM VARCHAR(50)) 
	RETURNS SETOF RECORD AS $BODY$
		DECLARE
			regEM RECORD;																																
		BEGIN																														 
			FOR regEM IN SELECT nombres, apellidos, usuario, correo, direccion, rol_actual FROM "LBSASQL"."Empleado" WHERE num_cedula = cedula LOOP
				nameEM := regEM.nombres;
				apellidoEM := regEM.apellidos;
				usEM := regEM.usuario;
				dirEM := regEM.direccion;
				correoEM := regEM.correo;
				cargoEM := regEM.rol_actual;
				RETURN NEXT;	
				END LOOP;
				RETURN ;																																	
		END																											 
	$BODY$ 	LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION BuscarArticuloCategoria(IN cat VARCHAR(50), OUT cod int, OUT des VARCHAR(50),OUT cat2 VARCHAR(50), OUT mar VARCHAR(50), OUT col VARCHAR(16), OUT consumo int, OUT precio FLOAT, OUT stck int)
	RETURNS SETOF RECORD AS $BODY$
		DECLARE
			regCat RECORD;
			regID RECORD;
		BEGIN																														 
			FOR regCAT IN SELECT id_articulo, descripcion, categoria, marca, color, consumo_electrico, precio_cliente_sin_iva FROM "LBSASQL"."Articulo" WHERE categoria = cat LOOP
				cod := regCAT.id_articulo;
				des := regCAT.descripcion;
				cat2 := regCAT.categoria;
				mar := regCAT.marca;
				col := regCAT.color;
				consumo := regCAT.consumo_electrico;
				precio := regCAT.precio_cliente_sin_iva;
				FOR regID IN SELECT cantidad_articulo_disponible FROM "LBSASQL"."Articulo_almacenado" WHERE cod = "LBSASQL"."Articulo_almacenado".id_articulo LOOP
					stck := regID.cantidad_articulo_disponible;
					END LOOP;
				RETURN NEXT;	
				END LOOP;
				RETURN ;																																	
		END																											 
$BODY$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION BuscarArticuloDescripcion(IN d VARCHAR(50), OUT cod int, OUT des VARCHAR(50),OUT cat2 VARCHAR(50), OUT mar VARCHAR(50), OUT col VARCHAR(16), OUT consumo int, OUT precio FLOAT, OUT stck int)
	RETURNS SETOF RECORD AS $BODY$
		DECLARE
			regCat RECORD;
			regID RECORD;
		BEGIN																														 
			FOR regCAT IN SELECT id_articulo, descripcion, categoria, marca, color, consumo_electrico, precio_cliente_sin_iva FROM "LBSASQL"."Articulo" WHERE descripcion = d LOOP
				cod := regCAT.id_articulo;
				des := regCAT.descripcion;
				cat2 := regCAT.categoria;
				mar := regCAT.marca;
				col := regCAT.color;
				consumo := regCAT.consumo_electrico;
				precio := regCAT.precio_cliente_sin_iva;
				FOR regID IN SELECT cantidad_articulo_disponible FROM "LBSASQL"."Articulo_almacenado" WHERE cod = "LBSASQL"."Articulo_almacenado".id_articulo LOOP
					stck := regID.cantidad_articulo_disponible;
					END LOOP;
				RETURN NEXT;	
				END LOOP;
				RETURN ;																																	
		END																											 
$BODY$ LANGUAGE 'plpgsql';

SELECT BuscarArticuloDescripcion('Cocina Induccion 3 hornillas');