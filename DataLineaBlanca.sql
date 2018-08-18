INSERT INTO "LBSASQL"."Articulo"(
	id_articulo, descripcion, categoria, marca, color, consumo_electrico, precio_cliente_sin_iva, costo_proveedor, reg_eliminado)
	VALUES (1, 'Cocina Induccion 1 hornilla','cocinas', 'INDURAMA', 'Blanca', 30, 350, 260, false),
		   (2, 'Cocina Induccion 3 hornillas','cocinas', 'INDURAMA', 'Beige', 105, 500, 380, false),
		   (3, 'Refrigeradora 1 puerta','refrigeradora', 'DUREX', 'Negra', 662 , 479, 400, false),
		   (4, 'Refrigeradora 2 puerta','refrigeradora', 'DUREX', 'Blanca', 800 , 700, 600, false),
		   (5, 'Lavadora Carga Superior','lavadora', 'Mabe', 'Blanca', 400 , 380, 280, false),
		   (6, 'Lavadora Carga Frontal','lavadora', 'Mabe', 'Blanca', 400 , 380, 280, false),
		   (7, 'Lavadora Secadora','lavadora', 'Mabe', 'Gris', 510 , 650, 550, false);
	
--select * from "LBSASQL"."Articulo" WHERE categoria like '%lavadora%' 

INSERT INTO "LBSASQL"."Almacen"(
	id_almacen, nombre, codigo, area, es_matriz, es_bodega, "fecha_creación", fecha_actualizacion, reg_eliminado)
	VALUES (1, 'Almacen Sur', '000001000',1,false,false, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (2, 'Bodega Sur', '111110000',1,false,true, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (3, 'Matriz Centro', '0123456789',2,true,false, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (4, 'Almacen Norte', '000002000',3,false,false, '2019-08-10' ,  CURRENT_TIMESTAMP, false),
		   (5, 'Bodega Norte Sur', '222220000',3,false,true, '2019-08-10' ,  CURRENT_TIMESTAMP, false);

select * from "LBSASQL"."Almacen";

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
	VALUES ("Ivan Correa","ivcorrea@hotmail.com" , "Urdesa Central",'2019-08-12', CURRENT_TIMESTAMP, false),
		   ("julio Rosado","jurosado@hotmail.com" , "Guasmo Sur",'2019-08-12', CURRENT_TIMESTAMP, false),
		   ("Angel Moya","anmoya@hotmail.com" , "La joya",'2019-08-12', CURRENT_TIMESTAMP, false),
		   ("Han Solo","hasolo@hotmail.com" , "samanes",'2019-08-12', CURRENT_TIMESTAMP, false),
		   ("Pedro Garcia","pegarcia@hotmail.com" , "Urdesa Central",'2019-08-12', CURRENT_TIMESTAMP, false),
		   ("Carlos Marquez","camarquez@hotmail.com" , "Chongon",'2019-08-12', CURRENT_TIMESTAMP, false),
		   ("Bryan Correa","brycorrea@hotmail.com" , "Portete y la 17",'2019-08-12', CURRENT_TIMESTAMP, false);
	
	select * from "LBSASQL"."Cliente"