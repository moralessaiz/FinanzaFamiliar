-- ============================================================
-- Sistema de Contabilidad Familiar (FinanzaFamiliar)
-- Creacion del usuario de MySQL que utiliza la aplicacion Java
-- para conectarse por JDBC (ver ConexionBD.java).
--
-- Ejecutar con un usuario administrador (root) DESPUES de
-- finanzafamiliar_movimientos.sql
-- ============================================================

CREATE USER IF NOT EXISTS 'finanza_user'@'localhost' IDENTIFIED BY 'Finanza2026*';

GRANT SELECT, INSERT, UPDATE, DELETE ON finanzafamiliar.* TO 'finanza_user'@'localhost';

FLUSH PRIVILEGES;

-- Verificacion de los permisos asignados
SHOW GRANTS FOR 'finanza_user'@'localhost';
