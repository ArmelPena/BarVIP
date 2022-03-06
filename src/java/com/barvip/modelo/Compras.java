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
    private int Id;
    private String FechaCompras;
    private Double monto;
    private String Estado;
    private int Cliente_Id;
    private int Pago_Id;
    private List<Carrito> DetalleCompras;

    public Compras() {
    }

    public Compras(int Id, String FechaCompras, Double monto, String Estado, int Cliente_Id, int Pago_Id, List<Carrito> DetalleCompras) {
        this.Id = Id;
        this.FechaCompras = FechaCompras;
        this.monto = monto;
        this.Estado = Estado;
        this.Cliente_Id = Cliente_Id;
        this.Pago_Id = Pago_Id;
        this.DetalleCompras = DetalleCompras;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public int getCliente_Id() {
        return Cliente_Id;
    }

    public void setCliente_Id(int Cliente_Id) {
        this.Cliente_Id = Cliente_Id;
    }

    public int getPago_Id() {
        return Pago_Id;
    }

    public void setPago_Id(int Pago_Id) {
        this.Pago_Id = Pago_Id;
    }

    public List<Carrito> getDetalleCompras() {
        return DetalleCompras;
    }

    public void setDetalleCompras(List<Carrito> DetalleCompras) {
        this.DetalleCompras = DetalleCompras;
    }
}
