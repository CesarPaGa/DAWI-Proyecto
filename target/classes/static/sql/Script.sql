-- -------------------------------------------------- --
-- base de datos creada para systemProyect.0 --
-- -------------------------------------------------- --
-- eliminacion si existe
drop database if exists systemProyect;

-- creacion y uso de la bd
create database systemProyect;
use systemProyect;

-- creacion de tablas y relaciones para tb_libro

/*	Contenidos	*/
create table tb_tipo(
id_tipo INT PRIMARY KEY auto_increment,
tipo VARCHAR(255) not null
);

create table tb_genero(
id_genero INT PRIMARY KEY auto_increment,
genero VARCHAR(255) not null
);
CREATE TABLE tb_Contenido (
    id_con INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    idioma VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    puntuacion DECIMAL(10, 2) NOT NULL,
    duracion DECIMAL(10, 2) NOT NULL,
    id_tipo INT NOT NULL,
    id_genero INT NOT NULL,
    anio_publicacion INT NOT NULL,
    FOREIGN KEY (id_tipo) REFERENCES tb_tipo(id_tipo),
    FOREIGN KEY (id_genero) REFERENCES tb_genero(id_genero)
);

INSERT INTO tb_tipo (id_tipo, tipo) VALUES (null, 'Series'),(null, 'Peliculas'), (null, 'Trailers'),(null, 'Otros');
INSERT INTO tb_genero (id_genero, genero) VALUES (null, 'Accion'),(null, 'Romance'), (null, 'Aventura'),(null, 'Fantasia');

INSERT INTO tb_Contenido (nombre, director, descripcion, idioma, precio, puntuacion, duracion, id_tipo, id_genero, anio_publicacion)
VALUES ('Stranger Things', 'Duffer Brothers', 'Serie de ciencia ficción y terror', 'Inglés', 19.99, 8.5, 45.5, 1, 3, 2016);

INSERT INTO tb_Contenido (nombre, director, descripcion, idioma, precio, puntuacion, duracion, id_tipo, id_genero, anio_publicacion)
VALUES ('El Padrino', 'Francis Ford Coppola', 'Clásico del cine de mafia', 'Español', 14.99, 9.2, 175.5, 2, 1, 1972);

INSERT INTO tb_Contenido (nombre, director, descripcion, idioma, precio, puntuacion, duracion, id_tipo, id_genero, anio_publicacion)
VALUES ('Jurassic Park', 'Steven Spielberg', 'Aventura con dinosaurios', 'Inglés', 12.99, 8.7, 127.5, 2, 3, 1993);

INSERT INTO tb_Contenido (nombre, director, descripcion, idioma, precio, puntuacion, duracion, id_tipo, id_genero, anio_publicacion)
VALUES ('The Notebook', 'Nick Cassavetes', 'Historia romántica', 'Inglés', 9.99, 8.0, 124.5, 2, 2, 2004);

INSERT INTO tb_Contenido (nombre, director, descripcion, idioma, precio, puntuacion, duracion, id_tipo, id_genero, anio_publicacion)
VALUES ('The Mandalorian', 'Jon Favreau', 'Serie de ciencia ficción y western', 'Español', 24.99, 8.9, 40.5, 1, 3, 2019);

/*	Usuarios	*/
CREATE TABLE tb_tipo_user (
idTipo int primary key auto_increment,
tipo VARCHAR(255) not null
);

CREATE TABLE tb_usuario (
idUsuario INT PRIMARY KEY auto_increment,
nombres VARCHAR(255) not null,
usua VARCHAR(255) not null unique,
clave VARCHAR(255) not null,
idTipo int not null default '2',
foreign key (idTipo) references tb_tipo_user (idTipo)
);

insert into tb_tipo_user values (1, 'Administrador');
insert into tb_tipo_user values (2, 'cliente');
INSERT INTO tb_usuario (idUsuario, nombres, usua, clave, idTipo) 
VALUES (null, 'Admin', 'Admin', '123', 2);

