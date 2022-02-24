/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelo;

import java.util.List;

/**
 *
 * @author ARMEL_000
 */
public class Compras {
    private int IdCompras;
    private int IdCliente;
    private int IdPago;
    private String FechaCompras;
    private Double monto;
    private String Estado;
    private List<Carrito> DetalleCompras;

    public Compras() {
    }

    public Compras(int IdCliente, int IdPago, String FechaCompras, Double monto, String Estado, List<Carrito> DetalleCompras) {
        this.IdCliente = IdCliente;
        this.IdPago = IdPago;
        this.FechaCompras = FechaCompras;
        this.monto = monto;
        this.Estado = Estado;
        this.DetalleCompras = DetalleCompras;
    }

    public int getIdCompras() {
        return IdCompras;
    }

    public void setIdCompras(int IdCompras) {
        this.IdCompras = IdCompras;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdPago() {
        return IdPago;
    }

    public void setIdPago(int IdPago) {
        this.IdPago = IdPago;
    }

    public String getFechaCompras() {
        return FechaCompras;
    }

    public void setFechaCompras(String FechaCompras) {
        this.FechaCompras = FechaCompras;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public List<Carrito> getDetalleCompras() {
        return DetalleCompras;
    }

    public void setDetalleCompras(List<Carrito> DetalleCompras) {
        this.DetalleCompras = DetalleCompras;
    }

}
