-- SET SQL_SAFE_UPDATES = 0;

CREATE DATABASE dbsistemaVentasJava;
USE dbsistemaVentasJava;
/*-------- TABLAS --------*/
CREATE TABLE usuario(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    correo VARCHAR(50),
    clave VARCHAR(100),
    estado BIT DEFAULT 1,
    fecha_registro DATETIME DEFAULT NOW()    
);

CREATE TABLE categoria(
	id INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(50),
    estado BIT DEFAULT 1,
    fecha_registro DATETIME DEFAULT NOW()
);

CREATE TABLE cliente(
	id INT PRIMARY KEY AUTO_INCREMENT,
    dni VARCHAR(8),
    nombre VARCHAR(50),
    correo VARCHAR(50),
    telefono INT(9),
    estado BIT DEFAULT 1,
    fecha_registro DATETIME DEFAULT NOW()    
);

CREATE TABLE producto(
	id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50),
    stock INT NOT NULL DEFAULT 0,
	precio_compra DECIMAL(10,2) DEFAULT 0,
	precio_venta DECIMAL(10,2) DEFAULT 0,
    id_categoria INT,    
    estado BIT DEFAULT 1,
    fecha_registro DATETIME DEFAULT NOW(),    
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

CREATE TABLE facturas(
	id INT PRIMARY KEY AUTO_INCREMENT,
	numero_factura TEXT,
    id_cliente INT,
    total DECIMAL(10,2),
    fecha_registro DATETIME DEFAULT NOW(),
    FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

CREATE TABLE factura_detalle(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_factura INT,
    id_producto INT,
    cantidad INT,
    precio_unitario DECIMAL(10,2),    
    FOREIGN KEY (id_factura) REFERENCES facturas(id),
    FOREIGN KEY (id_producto) REFERENCES producto(id)
);


/*-------- INSERTS --------*/
INSERT INTO usuario(nombre, correo, clave) VALUES
('Manuel', 'barriosmanuel138@gmail.com','123'),
('Rosario', 'rosario125@gmail.com','456');

INSERT INTO categoria(descripcion) VALUES
('Teclados'),
('Mouses');

INSERT INTO cliente(dni, nombre, correo, telefono) VALUES
('78965412','Rosario','isa123@gmail.com',978411215),
('75252391','Manuel','barriosmanuel138@gmail.com',934191604);

INSERT INTO producto (nombre, stock, precio_compra, precio_venta, id_categoria) VALUES
('Teclado Mecanico ReDragon 3/4', 150, 100.0, 150.0, 1),
('Mouse Logitech G203', 150, 100.0, 150.0, 2);

INSERT INTO facturas (numero_factura, id_cliente, total) VALUES 
('A00001', 1, 400.50);

INSERT INTO factura_detalle (id_factura, id_producto, cantidad, precio_unitario) VALUES 
(21, 1, 2, 150),
(21, 2, 3, 150);



/*-------- SELECTS --------*/

SELECT * FROM usuario;
SELECT * FROM categoria;
SELECT * FROM cliente;
SELECT * FROM producto;
SELECT * FROM facturas;
SELECT * FROM factura_detalle;

SELECT f.fecha_registro, f.numero_factura, c.nombre, c.id, c.telefono, c.dni, f.total FROM facturas f LEFT JOIN cliente c ON f.id_cliente = c.id;

/*-------- DELETES --------*/

DELETE FROM facturas;
DELETE FROM factura_detalle;