--Delete previous tables
DROP TABLE Productos;
DROP TABLE Familias;
--DROP TABLE Usuarios;
DROP TABLE Roles;
DROP TABLE Clientes;

create table Clientes
(
        NICK VARCHAR(255) NOT NULL PRIMARY KEY,
	DNI VARCHAR(10),
	NOMBRE VARCHAR(50),
        CORREO VARCHAR(50),
        CLAVE VARCHAR(255),
        IMAGEN VARCHAR(255)
        
);

-- Insert sample records
insert into Clientes (nick,dni,nombre,correo,clave,imagen) VALUES ('admin','11111111-A','Manuel García','asdf1@gmail.com','admin','defecto.png');
insert into Clientes (nick,dni,nombre,correo,clave,imagen) VALUES ('marial','22222222-B','María López','asdf2@gmail.com','1234','defecto.png');
insert into Clientes (nick,dni,nombre,correo,clave,imagen) VALUES ('user','33333333-C','Samuel Aranda','asdf3@gmail.com','user','defecto.png');
insert into Clientes (nick,dni,nombre,correo,clave,imagen) VALUES ('sonia92','44444444-D','Sonia Pérez','asdf4@gmail.com','1234','defecto.png');


-- ####################         PRODUCTOS       #################### 
create table Productos
(
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
	NOMBRE VARCHAR(50),
	DESCRIPCION VARCHAR(400),
        VALORACION DECIMAL(5) DEFAULT 0  NOT NULL,
        PRECIO DECIMAL(5),
        FAMILIA INTEGER,
        IMAGEN VARCHAR(50)
);

-- Insert sample records
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 1', 'Excelente relación calidad/precio para este PC con componentes de primeras marcas, la máxima garantía y gran rendimiento, ideal para usuarios que buscan hacer "de todo un poco".',
 2, 100, 1, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 2', 'Sabemos que sus datos son importantes para usted. Por eso fabricamos el disco interno de acuerdo con nuestros exigentes requisitos de durabilidad, resistencia a los impactos y fiabilidad a largo plazo. A continuación protegemos el disco con una duradera carcasa diseñada para ofrecer estilo y protección.',
 4, 50, 3, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 3', 'Gregorio miró hacia la ventana; estaba nublado, y sobre el cinc del alféizar repiqueteaban las gotas de lluvia, lo que le hizo sentir una gran melancolía. «Bueno -pensó-; ¿y si siguiese durmiendo un rato y me olvidase de todas estas locuras?»',
 5, 42.5, 1, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 25', 'Exhíbanse politiquillos zafios, con orejas kilométricas y uñas de gavilán. El cadáver de Wamba, rey godo de España, fue exhumado y trasladado en una caja de zinc que pesó un kilo. El pingüino Wenceslao hizo kilómetros bajo exhaustiva lluvia y frío, añoraba a su querido cachorro. El veloz murciélago hindú comía feliz cardillo y kiwi.',
 1, 82, 1, 'pdefecto.png');

INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 8', 'Sábete, Sancho, que no es un hombre más que otro si no hace más que otro. Todas estas borrascas que nos suceden son señales de que presto ha de serenar el tiempo y han de sucedernos bien las cosas; porque no es posible que el mal ni el bien sean durables, y de aquí se sigue que, habiendo durado mucho el mal, el bien está ya cerca.', 
3, 156, 2, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 9', 'han de sucedernos bien las cosas; porque no es posible que el mal ni el bien sean durables, y de aquí se sigue que, habiendo durado mucho el mal, el bien está ya cerca. Así que, no debes congojarte por las desgracias que a mí me suceden, pues a ti no te cabe parte dellas. Y, viéndole don Quijote de aquella manera, con muestras de tanta tristeza',
 3, 69, 2, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 10', ' Sancho, que no es un hombre más que otro si no hace más que otro. Todas estas borrascas que nos suceden son señales de que presto ha de serenar el tiempo y han de sucedernos bien las cosas; porque no es posible que el mal ni el bien sean durables, y de aquí se sigue que, habiendo durado ',
 4, 87, 3, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 11', 'Muy lejos, más allá de las montañas de palabras, alejados de los países de las vocales y las consonantes, viven los textos simulados. Viven aislados en casas de letras, en la costa de la semántica, un gran océano de lenguas. Un riachuelo llamado Pons fluye por su pueblo y los abastece', 
5, 212, 3, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 12', 'Li Europan lingues es membres del sam familie. Lor separat existentie es un myth. Por scientie, musica, sport etc, litot Europa usa li sam vocabular. Li lingues differe solmen in li grammatica, li pro',
 3, 56, 3, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 13', 'Una mañana, tras un sueño intranquilo, Gregorio Samsa se despertó convertido en un monstruoso insecto. Estaba echado de espaldas sobre un duro caparazón y, al alzar la cabeza, vio su vientre convexo y oscuro, surcado por curvadas callosidades, sobre el que casi no se aguantaba la colcha',
 2, 75, 2, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 14', 'Quiere la boca exhausta vid, kiwi, piña y fugaz jamón. Fabio me exige, sin tapujos, que añada cerveza al whisky. Jovencillo emponzoñado de whisky, ¡qué figurota exhibes! La cigüeña tocaba cada vez mejor el saxofón y el búho pedía kiwi y queso. El jefe buscó el éxtasis en un imprevisto baño de whisky',
 4, 91, 2, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 15', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia',
 5, 111, 1, 'pdefecto.png');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, FAMILIA, IMAGEN) 
	VALUES ('producto 16', 'Reina en mi espíritu una alegría admirable, muy parecida a las dulces alboradas de la primavera, de que gozo aquí con delicia. Estoy solo, y me felicito de vivir en este país, el más a propósito para almas como la mía, soy tan dichoso, mi querido amigo, me sojuzga de tal modo la idea de reposar, que', 
0, 99, 3, 'pdefecto.png');


-- ####################         FAMILIAS       #################### 
create table Familias
(
	ID INTEGER NOT NULL PRIMARY KEY,
	NOMBRE VARCHAR(50),
	DESCRIPCION VARCHAR(300)
);

-- Insert sample records
INSERT INTO TIENDAONLINE.FAMILIAS (ID, NOMBRE, DESCRIPCION) 
	VALUES (1,'familia 1', 'descripcion para la familia 1');
INSERT INTO TIENDAONLINE.FAMILIAS (ID, NOMBRE, DESCRIPCION) 
	VALUES (2,'familia 2', 'descripcion para la familia 2');
INSERT INTO TIENDAONLINE.FAMILIAS (ID, NOMBRE, DESCRIPCION) 
	VALUES (3,'familia 3', 'descripcion para la familia 3');

-- ####################         ROLES           #################### 

create table Roles
(
	usuario VARCHAR(50) not null,
	rol VARCHAR(50) not null
);

insert into Roles values ('admin','administrador');
insert into Roles values ('marial','cliente');
insert into Roles values ('sonia92','cliente');
insert into Roles values ('user','cliente');