--Delete previous tables
DROP TABLE Productos;
DROP TABLE Familias;
DROP TABLE Usuarios;
DROP TABLE Roles;
DROP TABLE Clientes;

create table Clientes
(
        NICK VARCHAR(20) NOT NULL PRIMARY KEY,
	DNI VARCHAR(10),
	NOMBRE VARCHAR(50),
        CORREO VARCHAR(50),
        CLAVE VARCHAR(25)
        
);

-- Insert sample records
insert into Clientes (nick,dni,nombre,correo,clave) VALUES ('manu','11111111-A','Manuel García','asdf1@gmail.com','1234');
insert into Clientes (nick,dni,nombre,correo,clave) VALUES ('marial','22222222-B','María López','asdf2@gmail.com','1234');
insert into Clientes (nick,dni,nombre,correo,clave) VALUES ('samuel33','33333333-C','Samuel Aranda','asdf3@gmail.com','1234');
insert into Clientes (nick,dni,nombre,correo,clave) VALUES ('sonia92','44444444-D','Sonia Pérez','asdf4@gmail.com','1234');


-- ####################         PRODUCTOS       #################### 
create table Productos
(
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
	NOMBRE VARCHAR(50),
	DESCRIPCION VARCHAR(300),
        VALORACION DECIMAL(5) DEFAULT 0  NOT NULL,
        PRECIO DECIMAL(5),
        IMAGEN VARCHAR(50)
);

-- Insert sample records
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, IMAGEN) 
	VALUES ('producto 1', 'descripcion para el producto 1', 5, 100, 'producto1');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, IMAGEN) 
	VALUES ('producto 2', 'descripcion para el producto 2', 8, 50, 'producto2');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, IMAGEN) 
	VALUES ('producto 3', 'descripcion para el producto 3', 4, 42.5, 'producto3');
INSERT INTO TIENDAONLINE.PRODUCTOS (NOMBRE, DESCRIPCION, VALORACION, PRECIO, IMAGEN) 
	VALUES ('producto 25', 'descripcion para el producto asdf', 7, 82, 'producto4');


-- ####################         FAMILIAS       #################### 
create table Familias
(
	ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,
	NOMBRE VARCHAR(50),
	DESCRIPCION VARCHAR(300)
);

-- Insert sample records
INSERT INTO TIENDAONLINE.FAMILIAS (NOMBRE, DESCRIPCION) 
	VALUES ('familia 1', 'descripcion para la familia 1');
INSERT INTO TIENDAONLINE.FAMILIAS (NOMBRE, DESCRIPCION) 
	VALUES ('familia 2', 'descripcion para la familia 2');


-- ####################         USUARIOS        #################### 

--create table Usuarios
--(
--	usuario VARCHAR(50) not null primary key,
--	clave VARCHAR(50) not null
--);

--insert into Usuarios values ('admin','admin');
--insert into Usuarios values ('maria','clavemaria');
--insert into Usuarios values ('carlos','clavecarlos');

-- ####################         ROLES           #################### 

create table Roles
(
	usuario VARCHAR(50) not null,
	rol VARCHAR(50) not null
);

insert into Roles values ('admin','administrador');
insert into Roles values ('maria','cliente');
insert into Roles values ('carlos','cliente');