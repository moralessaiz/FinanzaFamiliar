package com.finanzafamiliar.contabilidad.modelo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Clase modelo (POJO) que representa un movimiento (ingreso o gasto)
 * del Sistema de Contabilidad Familiar - FinanzaFamiliar.
 *
 * Evidencia GA7-220501096-AA2-EV01 - Codificacion de modulos del software.
 */
public class Movimiento {

    private int idMovimiento;
    private String tipo;
    private BigDecimal monto;
    private String concepto;
    private Date fecha;
    private int idCategoria;
    private int idUsuario;

    public Movimiento() {
    }

    public Movimiento(String tipo, BigDecimal monto, String concepto, Date fecha, int idCategoria, int idUsuario) {
        this.tipo = tipo;
        this.monto = monto;
        this.concepto = concepto;
        this.fecha = fecha;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    public Movimiento(int idMovimiento, String tipo, BigDecimal monto, String concepto, Date fecha, int idCategoria, int idUsuario) {
        this.idMovimiento = idMovimiento;
        this.tipo = tipo;
        this.monto = monto;
        this.concepto = concepto;
        this.fecha = fecha;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "idMovimiento=" + idMovimiento +
                ", tipo='" + tipo + '\'' +
                ", monto=" + monto +
                ", concepto='" + concepto + '\'' +
                ", fecha=" + fecha +
                ", idCategoria=" + idCategoria +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
