
INSERT INTO "LBSASQL"."Articulo"(
	id_articulo, nombre, descripcion, categoria, marca, color, consumo_electrico, precio_cliente_sin_iva, costo_proveedor, reg_eliminado)
	VALUES (1, 'Cocina','Cocina Induccion 1 hornilla','cocina', 'INDURAMA', 'Blanca', 30, 350, 260, false),
		   (2, 'Cocina','Cocina Induccion 3 hornillas','cocina', 'INDURAMA', 'Beige', 105, 500, 380, false),
		   (3, 'Regrigeradora','Refrigeradora 1 puerta','refrigeradora', 'DUREX', 'Negra', 662 , 479, 400, false),
		   (4, 'Regrigeradora','Refrigeradora 2 puerta','refrigeradora', 'DUREX', 'Blanca', 800 , 700, 600, false),
		   (5, 'Lavadora','Lavadora Carga Superior','lavadora', 'Mabe', 'Blanca', 400 , 380, 280, false),
		   (6, 'Lavadora','Lavadora Carga Frontal','lavadora', 'Mabe', 'Blanca', 400 , 380, 280, false),
		   (7, 'Lavadora','Lavadora Secadora','lavadora', 'Mabe', 'Gris', 510 , 650, 550, false);
	
--select * from "LBSASQL"."Articulo" WHERE categoria like '%lavadora%' 

INSERT INTO "LBSASQL"."Almacen"(
	id_almacen, nombre, codigo, area, es_matriz, es_bodega, "fecha_creación", fecha_actualizacion, reg_eliminado)
	VALUES (1, 'Almacen Sur', '000001000',1,false,false, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (2, 'Bodega Sur', '111110000',1,false,true, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (3, 'Matriz Centro', '0123456789',2,true,false, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (4, 'Almacen Norte', '000002000',3,false,false, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (5, 'Bodega Norte Sur', '222220000',3,false,true, '2019-08-10' ,  CURRENT_TIMESTAMP, false);

--select * from "LBSASQL"."Almacen";

INSERT INTO "LBSASQL".telefono_almacen(
	id_almacen, num_telefono, reg_eliminado)
	VALUES (1,'0912325464', false),
		   (2,'0912213423', false),
		   (3,'0912346644', false),
		   (4,'0912457567', false),
		   (5,'0912456322', false);

--select * from "LBSASQL"."telefono_almacen";


INSERT INTO "LBSASQL"."Articulo_almacenado"(
	id_almacen, id_articulo, fecha_llegada, cantidad_articulo_disponible, reabastecimiento_solicitado)
	VALUES (1, 1, '2019-08-12', 10, false),
		   (1, 2, '2019-08-12', 4, false),
		   (2, 3, '2019-08-12', 3, false),
		   (2, 4, '2019-08-12', 5, false),
		   (2, 5, '2019-08-12', 5, false),
		   (3, 6, '2019-08-12', 6, false),
		   (3, 7, '2019-08-12', 8, false),
		   (3, 3, '2019-08-12', 15, false),
		   (4, 4, '2019-08-12', 4, false),
		   (5, 5, '2019-08-12', 5, false),
		   (5, 1, '2019-08-12', 8, false);	
		   
--delete from "LBSASQL"."Articulo_almacenado";
--select * from "LBSASQL"."Articulo_almacenado";

INSERT INTO "LBSASQL"."Cliente"(
	nombre, correo, direccion, fecha_creacion, fecha_ultima_actualizacion, reg_eliminado)
	VALUES ('Ivan Correa','ivcorrea@hotmail.com' , 'Urdesa Central','2019-08-12', CURRENT_TIMESTAMP, false),
		   ('julio Rosado','jurosado@hotmail.com' , 'Guasmo Sur','2019-08-12', CURRENT_TIMESTAMP, false),
		   ('Angel Moya','anmoya@hotmail.com' , 'La joya','2019-08-12', CURRENT_TIMESTAMP, false),
		   ('Han Solo','hasolo@hotmail.com' , 'samanes','2019-08-12', CURRENT_TIMESTAMP, false),
		   ('Pedro Garcia','pegarcia@hotmail.com' , 'Urdesa Central','2019-08-12', CURRENT_TIMESTAMP, false),
		   ('Carlos Marquez','camarquez@hotmail.com' ,'Chongon','2019-08-12', CURRENT_TIMESTAMP, false),
		   ('Bryan Correa','brycorrea@hotmail.com' , 'Portete y la 17','2019-08-12', CURRENT_TIMESTAMP, false);
	
--select * from "LBSASQL"."Cliente"

INSERT INTO "LBSASQL"."Empleado"(
	num_cedula, nombres, apellidos, usuario, correo, direccion, "contraseña", rol_actual, fecha_contratacion, fecha_actualizacion, "empleado activo", id_almacen)
	VALUES ('234757689', 'Alicia', 'Vazquez', 'alvaz', 'alvazquez@hotmail.com', 'La rotonda', '1234', 'vendedor', '2019-08-12', CURRENT_TIMESTAMP, true,1),
		   ('354672837', 'Paola', 'Delgado', 'paodel', 'alvazquez@hotmail.com', 'Puerto Azul', '1234', 'administrador', '2019-08-12', CURRENT_TIMESTAMP, true,1),
		   ('647627384', 'Carlos', 'Burgos', 'carbur', 'alvazquez@hotmail.com', 'Ceibos', '1234', 'superadministrador', '2019-08-12', CURRENT_TIMESTAMP, true,1),
		   ('123453312', 'Juan', 'Parra', 'jupar', 'alvazquez@hotmail.com', 'Trinitaria', '1234', 'gerente', '2019-08-12', CURRENT_TIMESTAMP, true,1),
		   ('474334522', 'Pilar', 'Mora', 'pimor', 'alvazquez@hotmail.com', 'Guangala', '1234', 'vendedor', '2019-08-12', CURRENT_TIMESTAMP, true,3),
		   ('234234235', 'Irene', 'Alvarez', 'irealv', 'alvazquez@hotmail.com', 'Acacias', '1234', 'vendedor', '2019-08-12', CURRENT_TIMESTAMP, true,3),
		   ('324234235', 'Sebastian', 'Medina', 'sebamed', 'alvazquez@hotmail.com', 'Neptuno', '1234', 'superadministrador', '2019-08-12', CURRENT_TIMESTAMP, true,3),
		   ('546675882', 'Patricia', 'Sanz', 'patsan', 'alvazquez@hotmail.com', 'Omicron', '1234', 'gerente', '2019-08-12', CURRENT_TIMESTAMP, true,3),
		   ('856836346', 'Diego', 'Flores', 'dieflo', 'alvazquez@hotmail.com', 'Duran', '1234', 'superadministrador', '2019-08-12', CURRENT_TIMESTAMP, true,4),
		   ('012891243', 'Alexandra', 'Ramos', 'alexram', 'alvazquez@hotmail.com', 'Milagro', '1234', 'vendedor', '2019-08-12', CURRENT_TIMESTAMP,true,4),
		   ('121231212', 'Manuel', 'Garcia', 'mangar', 'alvazquez@hotmail.com', 'Peñas', '1234', 'vendedor', '2019-08-12', CURRENT_TIMESTAMP, true,4),
		   ('346486342', 'Sara', 'Mendez', 'sarmend', 'alvazquez@hotmail.com', 'Suburbio', '1234', 'gerente', '2019-08-12', CURRENT_TIMESTAMP, true,4);

--select * from "LBSASQL"."Empleado" where rol_actual like 'vendedor'

INSERT INTO "LBSASQL".telefono_empleado(
	num_cedula, num_telefono, reg_eliminado)
	VALUES ('234757689', '0912314862', false),
		   ('354672837', '0945734532', false),
		   ('647627384', '0946547423', false),
		   ('123453312', '0932412412', false),
		   ('474334522', '0932535475', false),
		   ('234234235', '0912312423', false),
		   ('324234235', '0912432534', false),
		   ('546675882', '0923412435', false),
		   ('856836346', '0953574576', false),
		   ('012891243', '0923454236', false),
		   ('121231212', '0923464686', false),
		   ('346486342', '0923534567', false);

--select * from "LBSASQL"."telefono_empleado"


INSERT INTO "LBSASQL"."Registro_eventos_almacen"(
	id_empleado, id_almacen, fecha_modificacion, tipo_accion_realizada)
	VALUES ( 123453312, 1, CURRENT_TIMESTAMP, 'Inventario de cocinas'),
		   ( 546675882, 3, CURRENT_TIMESTAMP, 'Inventario de entrega a locales');

--select * from "LBSASQL"."Registro_eventos_almacen" 



INSERT INTO "LBSASQL"."Registro_eventos_cliente"(
	id_empleado, id_cliente, fecha_modificacion, tipo_accion_realizada)
	VALUES ('234757689', 1, CURRENT_TIMESTAMP, 'Actualizacion de datos'),
		   ('474334522', 4, CURRENT_TIMESTAMP, 'Actualizacion de datos'),
		   ('012891243', 5, CURRENT_TIMESTAMP, 'Actualizacion de datos'),
		   ('121231212', 2, CURRENT_TIMESTAMP, 'Actualizacion de datos'),
		   ('012891243', 7, CURRENT_TIMESTAMP, 'Actualizacion de datos'),
		   ('234757689', 1, CURRENT_TIMESTAMP, 'Actualizacion de datos');
	
--select * from "LBSASQL"."Registro_eventos_cliente"

INSERT INTO "LBSASQL"."Cliente_contribuyente_registrado"(
	"RUC", id_cliente, razon_social, es_contrib_especial, reg_eliminado)
	VALUES ('0036547826735', 1, 'S.A', false, false),
		   ('0046346534532', 2, 'S.A.C', false, false),
		   ('0023455346477', 3, 'E.I.R.L', false, false),
		   ('0033452342321', 4, 'S.A', false, false);
		   
--select * from "LBSASQL"."Cliente_contribuyente_registrado"


INSERT INTO "LBSASQL".telefono_c_contribuyente_registrado(
	"RUC", num_telefono, reg_eliminado)
	VALUES ('0036547826735', 0925735464, false),
		   ('0046346534532', 0932412112, false),
		   ('0023455346477', 0912424512, false),
		   ('0033452342321', 0943654532, false);

--select * from "LBSASQL"."telefono_c_contribuyente_registrado"

INSERT INTO "LBSASQL"."Cliente_ciudadano"(
	num_cedula, id_cliente, reg_eliminado)
	VALUES ('0927463754', 5, false),
		   ('0952374232', 6, false),
		   ('0923432233', 7, false);
	
--select * from "LBSASQL"."Cliente_ciudadano"

INSERT INTO "LBSASQL".telefono_c_ciudadano(
	num_cedula, num_telefono, reg_eliminado)
	VALUES ('0927463754', '0921312334', false),
	  	   ('0952374232', '0912371231', false),
		   ('0923432233', '0978765638', false);
	
--select * from "LBSASQL"."telefono_c_ciudadano"

INSERT INTO "LBSASQL"."Proveedor_CR"(
	id_proveedor, razon_social, direccion, correo, reg_eliminado)
	VALUES ('0912123121212', 'MABE ECUADOR S.A.', 'Av. de los Shyris, Quito 170135', 'mabeEcuador@mabe.com', false),
	  	   ('0190061264001', 'INDURAMA S.A.', 'Carcelén, Quito', 'induramaEcuador@indurama.com', false),
		   ('0990020086001', 'DUREX S.A.', 'Av. Amazonas, Quito', 'durexEcuador@durex.com', false);

--select * from "LBSASQL"."Proveedor_CR"

INSERT INTO "LBSASQL"."Registro_eventos_proveedor"(
	id_empleado, id_proveedor, fecha_modificacion, tipo_accion_realizada)
	VALUES ('354672837', '0912123121212', '2019-08-12', 'Entrega de lavadoras Carga superior');
	
--select * from "LBSASQL"."Registro_eventos_proveedor"

 --('fac',CURRENT_TIMESTAMP, 300, 3, 647627384, false),
		   --('fac',CURRENT_TIMESTAMP, 180, 2, 474334522, false),
		   --('fac',CURRENT_TIMESTAMP, 60, 7, 121231212, false);
		   
INSERT INTO "LBSASQL"."Compra"(
	 tipo_comprobante_venta, fecha_compra, monto, id_cliente, id_empleado, reg_eliminado)
	VALUES ('fac',CURRENT_TIMESTAMP, 380, 3, 234757689, false),
		   ('fac',CURRENT_TIMESTAMP, 1320, 2, 474334522, false),
		   ('fac',CURRENT_TIMESTAMP, 1100, 7, 121231212, false);

INSERT INTO "LBSASQL"."Articulos_vendidos"(
	id_compra, id_articulo, cantidad_articulo)
	VALUES (1, 1, 1),
		   (2, 4, 1),
		   (2, 5, 1),
		   (3, 2, 1),
		   (3, 5, 1);

--select * from "LBSASQL"."Articulos_vendidos"



INSERT INTO "LBSASQL"."Cotizacion"(
	fecha_cotizacion, monto_estimado, id_cliente, id_empleado, reg_eliminado)
	VALUES (CURRENT_TIMESTAMP, 388, 3, 474334522,false),--centro
		   (CURRENT_TIMESTAMP, 380, 3, 234757689,false),--sur
		   (CURRENT_TIMESTAMP, 380, 3, 121231212,false),
		   (CURRENT_TIMESTAMP, 1320, 2, 474334522,false); --norte
	

--select * from "LBSASQL"."Cotizacion"

INSERT INTO "LBSASQL"."Articulos_cotizados"(
	id_cotizacion, id_articulo, cantidad_articulo)
	VALUES (1, 1, 1),
		   (2, 1, 1),
		   (3, 1, 1),
		   (4, 4, 1),
		   (4, 5, 1);
--select * from "LBSASQL"."Articulos_cotizados"
	
	

--NO MOVER
--Reducir en 1 a la cantidad de cada articulo luego de guardar un registro de Articulo_vendido
CREATE OR REPLACE FUNCTION ReducirCantidadArticulo() RETURNS TRIGGER AS $ReducirCantidadArticulo$
  DECLARE
  BEGIN
        update "LBSASQL"."Articulo_almacenado"  set cantidad_articulo_disponible=cantidad_articulo_disponible-new.cantidad_articulo where "LBSASQL"."Articulo_almacenado".id_articulo=new.id_articulo;
    RETURN NULL;
  END;
$ReducirCantidadArticulo$ LANGUAGE plpgsql;


CREATE TRIGGER ReducirCantidadArticulo after insert on "LBSASQL"."Articulos_vendidos" 
for each row
    EXECUTE PROCEDURE ReducirCantidadArticulo()
	
	
	
	