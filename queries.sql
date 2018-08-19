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

																													
																															  
																															 