package com.finanzafamiliar.contabilidad.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase encargada de gestionar la conexion a la base de datos MySQL
 * del Sistema de Contabilidad Familiar - FinanzaFamiliar, utilizando JDBC.
 *
 * Sigue los pasos descritos en el material de apoyo "Desarrollo de
 * aplicaciones web con Java" (SENA, 2024, seccion 1.2): importar el
 * driver JDBC correspondiente al SGBD, inicializarlo con Class.forName
 * y crear el objeto Connection por medio del DriverManager.
 *
 * Evidencia GA7-220501096-AA2-EV01 - Codificacion de modulos del software.
 */
public class ConexionBD {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/finanzafamiliar?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USUARIO = "finanza_user";
    private static final String CONTRASENA = "Finanza2026*";

    /**
     * Abre y retorna una nueva conexion JDBC hacia la base de datos
     * finanzafamiliar.
     */
    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontro el driver JDBC de MySQL: " + e.getMessage(), e);
        }
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    /**
     * Cierra una conexion JDBC de forma segura, evitando que un error
     * al cerrar detenga la ejecucion del programa.
     */
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("No fue posible cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
