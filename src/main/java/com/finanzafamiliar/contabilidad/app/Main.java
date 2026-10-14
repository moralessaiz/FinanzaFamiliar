package com.finanzafamiliar.contabilidad.app;

import com.finanzafamiliar.contabilidad.dao.MovimientoDAO;
import com.finanzafamiliar.contabilidad.dao.UsuarioDAO;
import com.finanzafamiliar.contabilidad.modelo.Movimiento;
import com.finanzafamiliar.contabilidad.modelo.Usuario;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase principal que demuestra el funcionamiento de los modulos de
 * Usuarios y Movimientos del Sistema de Contabilidad Familiar - FinanzaFamiliar,
 * ejecutando en consola las cuatro operaciones del CRUD sobre las
 * tablas "usuario" y "movimiento" por medio de JDBC.
 *
 * Evidencia GA7-220501096-AA2-EV01 - Codificacion de modulos del software.
 */
public class Main {

    public static void main(String[] args) {
        try {
            probarUsuarios(new UsuarioDAO());
            probarMovimientos(new MovimientoDAO());

            System.out.println("\n=== Prueba de CRUD finalizada correctamente ===");

        } catch (SQLException e) {
            System.out.println("Ocurrio un error al acceder a la base de datos: " + e.getMessage());
        }
    }

    /**
     * Ejecuta las cuatro operaciones del CRUD sobre la tabla "usuario".
     * El usuario de prueba se elimina al final, para no dejar registros
     * repetidos cada vez que se ejecuta el programa.
     */
    private static void probarUsuarios(UsuarioDAO usuarioDAO) throws SQLException {
        System.out.println("=== FinanzaFamiliar - Modulo de Usuarios (CRUD con JDBC) ===");

        // 1) CREATE - registrar un nuevo integrante del grupo familiar
        Usuario nuevoUsuario = new Usuario("Laura Morales", "laura.morales@finanzafamiliar.com", "Integrante");
        int idUsuarioGenerado = usuarioDAO.insertar(nuevoUsuario);
        System.out.println("\n[CREATE] Usuario insertado con id: " + idUsuarioGenerado);

        // 2) READ - consultar todos los usuarios
        System.out.println("\n[READ] Listado de usuarios registrados:");
        List<Usuario> usuarios = usuarioDAO.consultarTodos();
        for (Usuario usuario : usuarios) {
            System.out.println("  " + usuario);
        }

        // 3) READ - consultar un usuario especifico
        Usuario consultado = usuarioDAO.consultarPorId(idUsuarioGenerado);
        System.out.println("\n[READ] Usuario consultado por id (" + idUsuarioGenerado + "): " + consultado);

        // 4) UPDATE - actualizar el correo y el rol del usuario registrado
        consultado.setCorreo("laura.m@finanzafamiliar.com");
        consultado.setRol("Administradora");
        boolean actualizado = usuarioDAO.actualizar(consultado);
        System.out.println("\n[UPDATE] Usuario actualizado: " + actualizado);
        System.out.println("[UPDATE] Nuevo estado: " + usuarioDAO.consultarPorId(idUsuarioGenerado));

        // 5) DELETE - eliminar el usuario de prueba (no tiene movimientos asociados)
        boolean eliminado = usuarioDAO.eliminar(idUsuarioGenerado);
        System.out.println("\n[DELETE] Usuario con id " + idUsuarioGenerado + " eliminado: " + eliminado);

        // 6) READ final - confirmar el estado de la tabla luego del CRUD
        System.out.println("\n[READ] Listado final de usuarios:");
        for (Usuario usuario : usuarioDAO.consultarTodos()) {
            System.out.println("  " + usuario);
        }
    }

    /**
     * Ejecuta las cuatro operaciones del CRUD sobre la tabla "movimiento".
     */
    private static void probarMovimientos(MovimientoDAO movimientoDAO) throws SQLException {
        System.out.println("\n=== FinanzaFamiliar - Modulo de Movimientos (CRUD con JDBC) ===");

        // 1) CREATE - registrar un nuevo ingreso
        Movimiento nuevoIngreso = new Movimiento(
                "ingreso",
                new BigDecimal("2500000.00"),
                "Salario mensual",
                Date.valueOf(LocalDate.of(2026, 9, 5)),
                1,
                1
        );
        int idGenerado = movimientoDAO.insertar(nuevoIngreso);
        System.out.println("\n[CREATE] Movimiento insertado con id: " + idGenerado);

        // 2) CREATE - registrar un nuevo gasto
        Movimiento nuevoGasto = new Movimiento(
                "gasto",
                new BigDecimal("450000.00"),
                "Mercado del mes",
                Date.valueOf(LocalDate.of(2026, 9, 8)),
                3,
                1
        );
        int idGastoGenerado = movimientoDAO.insertar(nuevoGasto);
        System.out.println("[CREATE] Movimiento insertado con id: " + idGastoGenerado);

        // 3) READ - consultar todos los movimientos
        System.out.println("\n[READ] Listado de movimientos registrados:");
        List<Movimiento> movimientos = movimientoDAO.consultarTodos();
        for (Movimiento movimiento : movimientos) {
            System.out.println("  " + movimiento);
        }

        // 4) READ - consultar un movimiento especifico
        Movimiento consultado = movimientoDAO.consultarPorId(idGastoGenerado);
        System.out.println("\n[READ] Movimiento consultado por id (" + idGastoGenerado + "): " + consultado);

        // 5) UPDATE - actualizar el monto y el concepto del gasto registrado
        consultado.setMonto(new BigDecimal("470000.00"));
        consultado.setConcepto("Mercado del mes (ajustado)");
        boolean actualizado = movimientoDAO.actualizar(consultado);
        System.out.println("\n[UPDATE] Movimiento actualizado: " + actualizado);
        System.out.println("[UPDATE] Nuevo estado: " + movimientoDAO.consultarPorId(idGastoGenerado));

        // 6) DELETE - eliminar el movimiento de ingreso registrado inicialmente
        boolean eliminado = movimientoDAO.eliminar(idGenerado);
        System.out.println("\n[DELETE] Movimiento con id " + idGenerado + " eliminado: " + eliminado);

        // 7) READ final - confirmar el estado de la tabla luego del CRUD
        System.out.println("\n[READ] Listado final de movimientos:");
        for (Movimiento movimiento : movimientoDAO.consultarTodos()) {
            System.out.println("  " + movimiento);
        }
    }
}
