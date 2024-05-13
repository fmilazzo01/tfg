-- Crear la tabla 'clientes'
drop database gym;
create database if not exists gym;
use gym;


CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellidos VARCHAR(50),
    fechanacimiento DATE,
    telefono VARCHAR(15),
    telemergencias VARCHAR(15),
    fechainicio DATE ,
    email VARCHAR(100)
);

CREATE TABLE pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT,
    fecha_pago DATE,
    tipo_pago ENUM('Mensualidad', 'Trimestre', 'Semestre', 'Ano'),
    membresia_hasta DATE,
    cantidad DECIMAL(10, 2),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);



select * from clientes;
select * from pagos;

SELECT * FROM clientes 
INNER JOIN pagos ON clientes.id = pagos.cliente_id 
WHERE pagos.membresia_hasta < CURDATE()


