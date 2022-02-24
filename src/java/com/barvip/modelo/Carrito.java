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
public class Carrito {
    int Item;
    int IdProducto;
    String Nombres;
    String Descripcion;
    double PrecioCompra;
    int Cantidad;
    double Subtotal;

    public Carrito() {
    }

    public Carrito(int Item, int IdProducto, String Nombres, String Descripcion, double PrecioCompra, int Cantidad, double Subtotal) {
        this.Item = Item;
        this.IdProducto = IdProducto;
        this.Nombres = Nombres;
        this.Descripcion = Descripcion;
        this.PrecioCompra = PrecioCompra;
        this.Cantidad = Cantidad;
        this.Subtotal = Subtotal;
    }

    public int getItem() {
        return Item;
    }

    public void setItem(int Item) {
        this.Item = Item;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public double getPrecioCompra() {
        return PrecioCompra;
    }

    public void setPrecioCompra(double PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(double Subtotal) {
        this.Subtotal = Subtotal;
    }
    
    
}
