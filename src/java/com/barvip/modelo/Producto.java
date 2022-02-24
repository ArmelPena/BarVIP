/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelo;

import java.io.InputStream;

/**
 *
 * @author ARMEL_000
 */
public class Producto {
    int IdProducto;
    String Nombres;
    InputStream Foto;
    String Descripcion;
    Double Precio;
    int Stock;

    public Producto() {
    }

    public Producto(int IdProducto, String Nombres, InputStream Foto, String Descripcion, Double Precio, int Stock) {
        this.IdProducto = IdProducto;
        this.Nombres = Nombres;
        this.Foto = Foto;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Stock = Stock;
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

    public InputStream getFoto() {
        return Foto;
    }

    public void setFoto(InputStream Foto) {
        this.Foto = Foto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double Precio) {
        this.Precio = Precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    
}
