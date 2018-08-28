--select art.id_articulo,com.id_cliente,cli.nombre, com.id_empleado,art.descripcion,art.precio_cliente_sin_iva,artven.cantidad_articulo from "LBSASQL"."Compra" com join "LBSASQL"."Articulos_vendidos" artven on com.id_compra=artven.id_compra join "LBSASQL"."Articulo" art on artven.id_articulo=art.id_articulo join "LBSASQL"."Cliente" cli on com.id_cliente=cli.id_cliente;
CREATE OR REPLACE FUNCTION IngresarCompra(in tipoComprobante VARCHAR(3),in montocompra float,in cliente int,in empleado char(10))  
		BEGIN		
			insert into "LBSASQL"."Compra"( 
		 	tipo_comprobante_venta, fecha_compra, monto, id_cliente, id_empleado, reg_eliminado)
			VALUES (tipoComprobante, CURRENT_TIMESTAMP,montocompra,cliente,empleado, false)
																																				
		END																											 
	 LANGUAGE 'plpgsql'
	
INSERT INTO "LBSASQL"."Compra"(
	id_compra, tipo_comprobante_venta, fecha_compra, monto, id_cliente, id_empleado)
	VALUES (?, ?, ?, ?, ?, ?, ?);

																															 
CREATE OR REPLACE FUNCTION IngresarArticulosVendidos(in idCompra int,in idArticulo int,in cantidadArticulo int)  
		RETURNS VOID AS	$BODY$																															 
		BEGIN	
			INSERT INTO "LBSASQL"."Articulos_vendidos"(
				 id_compra, id_articulo, cantidad_articulo)
			VALUES (idCompra, idArticulo, cantidadArticulo);
																																																												 
		END;
$BODY$  LANGUAGE 'plpgsql' VOLATILE;
																															  
select IngresarCompra('fac',1000,6,'234757689')
			  
--select * from "LBSASQL"."Compra"

																													
									
select * from "LBSASQL"."Compra"
select * from "LBSASQL"."Compra" com join "LBSASQL"."Articulos_vendidos" art on com.id_compra=art.id_compra;
select * from "LBSASQL"."Articulos_vendidos";

--conocer la relacion entre articulo y su almacen. para probar el trigger... si se  ingresa 
--un articulo vendido entonce se restan la cantidad de articulos
select art.id_articulo, art.nombre, art.precio_cliente_sin_iva,artal.id_almacen,artal.cantidad_articulo_disponible from "LBSASQL"."Articulo" art join "LBSASQL"."Articulo_almacenado" artal on art.id_articulo=artal.id_articulo where artal.id_almacen=1;
	
--conocer la relacion entre articulo y su almacen. para probar el trigger... si se  ingresa 
--un articulo vendido entonce se restan la cantidad de articulos
select art.id_articulo, art.nombre, art.precio_cliente_sin_iva,artal.id_almacen,artal.cantidad_articulo_disponible from "LBSASQL"."Articulo" art join "LBSASQL"."Articulo_almacenado" artal on art.id_articulo=artal.id_articulo where artal.id_almacen=1;
																															
 create trigger ReducirCantidadArticulo after insert on "LBSASQL"."Articulos_vendidos" 
 for each row 
 begin
	update "LBSASQL"."Articulos_almacenado"  set cantidad_articulo_disponible=cantidad_articulo_disponible-new.cantidad_articulo where "LBSASQL"."Articulos_almacenado".id_articulo=new.id_articulo;
 end
 



		
																															 
																															 
																															 