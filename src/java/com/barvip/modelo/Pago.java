/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelo;

/**
 *
 * @author ARMEL_000
 */
public class Pago {
    private int IdPago;
    private Double Monto;
    private String NombreCliente;
    private String Direccion;
    private String Telefono;
    private String ReferenciaDir;

    public Pago() {
    }

    public Pago(int IdPago, Double Monto, String NombreCliente, String Direccion, String Telefono, String ReferenciaDir) {
        this.IdPago = IdPago;
        this.Monto = Monto;
        this.NombreCliente = NombreCliente;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.ReferenciaDir = ReferenciaDir;
    }

    public int getIdPago() {
        return IdPago;
    }

    public void setIdPago(int IdPago) {
        this.IdPago = IdPago;
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double Monto) {
        this.Monto = Monto;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getReferenciaDir() {
        return ReferenciaDir;
    }

    public void setReferenciaDir(String ReferenciaDir) {
        this.ReferenciaDir = ReferenciaDir;
    }




    
}
