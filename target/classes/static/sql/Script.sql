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
id_tipo int primary key auto_increment,
tipo VARCHAR(255) not null
);

CREATE TABLE tb_usuario (
id_usuario INT PRIMARY KEY auto_increment,
nombres VARCHAR(255) not null,
usua VARCHAR(255) not null unique,
clave VARCHAR(255) not null,
id_tipo int not null default '2',
foreign key (id_tipo) references tb_tipo_user(id_tipo)
);

insert into tb_tipo_user values (1, 'Administrador');
insert into tb_tipo_user values (2, 'cliente');
INSERT INTO tb_usuario (id_usuario, nombres, usua, clave, id_tipo) 
VALUES (null, 'Admin', 'Admin', '123', 2);


/*Ventas*/
CREATE TABLE systemproyect.tb_venta (
    id_venta CHAR(5) PRIMARY KEY,
    contenido_id INT,
	fecha_registro date NOT NULL,
	total DECIMAL(10,2) NOT NULL,
	usuario_id INT,
	CONSTRAINT ventas_fk1 FOREIGN KEY(contenido_id) REFERENCES tb_Contenido(id_con),
    CONSTRAINT ventas_fk2 FOREIGN KEY(usuario_id) REFERENCES tb_usuario(id_usuario),
	constraint ventas_check CHECK (fecha_registro>'2018-01-01')
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

/*Detalle Ventas*/
/*
CREATE TABLE systemproyect.tb_detalle_venta (
	id_detalle INT auto_increment NOT NULL,
	venta_id CHAR(5) NOT NULL,
	contenido_id INT NOT NULL,
	cantidad INT NULL,
	precio DECIMAL(10,2) NOT NULL,
	CONSTRAINT tb_detalle_venta_pk PRIMARY KEY (id_detalle),
	CONSTRAINT tb_detalle_venta_FK FOREIGN KEY (venta_id) REFERENCES systemproyect.tb_venta(id_venta),
	CONSTRAINT tb_detalle_venta_FK_1 FOREIGN KEY (contenido_id) REFERENCES systemproyect.tb_contenido(id_con)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;
*/
-- Insert tabla venta
INSERT INTO `systemproyect`.`tb_venta`
(`id_venta`,
`contenido_id`,
`fecha_registro`,
`total`,
`usuario_id`)
VALUES
('A123',2,'2023-11-10',14.99,1),
('A124',4,'2023-11-10',9.99,1);


SELECT * FROM systemproyect.tb_contenido;

select * from tb_venta;

SELECT SUM(total) AS total_ventas FROM tb_venta;



