--FXML INICIO VENDEDOR
use "LBSASQL"

SELECT * FROM "LBSASQL"."Articulo" where usuario='alvaz' and contraseña='1234'
 
SELECT * FROM "LBSASQL"."Empleado"Empleado where user='alvaz' and contraseña='1234'
SELECT * FROM "LBSASQL"."Empleado" where usuario='alvaz' and contraseña='1234'
SELECT * FROM "LBSASQL"."Empleado" where usuario='alvaz' and contraseña='1234'


delimiter $
 create procedure Ingresar_Factura_Externa(in nfactura char(15),in noDistribuidora char(20),in fecha date,in subtot float, in descuento float, in iva float, in total float,out fknoRegistro int)
 begin   
	INSERT INTO facturaExterna VALUES (0,nfactura,noDistribuidora,fecha,subtot,descuento,iva,total);
    SELECT noRegistro into fknoRegistro FROM facturaExterna order BY noRegistro DESC LIMIT 1;
end
$
 delimiter ;

--obtener data de la venta por medio del codigo
--select art.id_articulo,com.id_cliente,cli.nombre, com.id_empleado,art.descripcion,art.precio_cliente_sin_iva,artven.cantidad_articulo from "LBSASQL"."Compra" com join "LBSASQL"."Articulos_vendidos" artven on com.id_compra=artven.id_compra join "LBSASQL"."Articulo" art on artven.id_articulo=art.id_articulo join "LBSASQL"."Cliente" cli on com.id_cliente=cli.id_cliente;
CREATE OR REPLACE FUNCTION BuscarFactura(IN idCompra int, OUT idArticulo int, OUT descrip VARCHAR(50), OUT marc VARCHAR(50), OUT precio float, OUT cantidad float) 
	RETURNS SETOF RECORD AS $BODY$
		DECLARE
			reg RECORD;																														
		BEGIN																														 
			FOR reg IN SELECT art.id_articulo,com.id_cliente,art.marca,art.descripcion,art.descripcion,art.precio_cliente_sin_iva,artven.cantidad_articulo from "LBSASQL"."Compra" com join "LBSASQL"."Articulos_vendidos" artven on com.id_compra=artven.id_compra join "LBSASQL"."Articulo" art on artven.id_articulo=art.id_articulo join "LBSASQL"."Cliente" cli on com.id_cliente=cli.id_cliente WHERE com.id_compra=idCompra LOOP
				idArticulo := reg.id_articulo;
				descrip := reg.descripcion;
				marc:= reg.marca;
				precio := reg.precio_cliente_sin_iva;
				cantidad:=  reg.cantidad_articulo;
				REselect BuscarFactura(2) -- compra 2
TURN NEXT;	
				END LOOP;
				RETURN ;																																	
		END																											 
	$BODY$ 	LANGUAGE 'plpgsql'
	
