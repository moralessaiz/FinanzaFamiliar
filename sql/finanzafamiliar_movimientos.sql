-- ============================================================
-- Sistema de Contabilidad Familiar (FinanzaFamiliar)
-- Script de creacion de base de datos y tablas para el modulo
-- de Movimientos (ingresos y gastos)
-- Evidencia GA7-220501096-AA2-EV01 - Codificacion de modulos del software
-- ============================================================

CREATE DATABASE IF NOT EXISTS finanzafamiliar CHARACTER SET utf8mb4;
USE finanzafamiliar;

-- Tabla de usuarios (integrantes del grupo familiar)
CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    rol VARCHAR(30) NOT NULL
);

-- Tabla de categorias de ingreso o gasto
CREATE TABLE IF NOT EXISTS categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    tipo VARCHAR(10) NOT NULL
);

-- Tabla de movimientos (ingresos y gastos del hogar)
CREATE TABLE IF NOT EXISTS movimiento (
    id_movimiento INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL,
    monto DECIMAL(12,2) NOT NULL,
    concepto VARCHAR(150) NOT NULL,
    fecha DATE NOT NULL,
    id_categoria INT NOT NULL,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_movimiento_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
    CONSTRAINT fk_movimiento_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- Datos iniciales de referencia
INSERT INTO usuario (nombre, correo, rol) VALUES
    ('Amalfi Aya', 'caterine7226@gmail.com', 'Administrador'),
    ('Cristhian Morales', 'moralessaiz@gmail.com', 'Administrador');

INSERT INTO categoria (nombre, tipo) VALUES
    ('Salario', 'ingreso'),
    ('Arriendo', 'gasto'),
    ('Mercado', 'gasto'),
    ('Servicios publicos', 'gasto');
