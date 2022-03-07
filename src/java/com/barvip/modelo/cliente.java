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
public class cliente {
    int Id;
    String Dni;
    String Nombres;
    String Direccion;
    String Email;
    String Password;
    Integer Perfil;
    
    public cliente() {
    }

    public cliente(int Id, String Dni, String Nombres, String Direccion, String Email, String Password, int Perfil) {
        this.Id = Id;
        this.Dni = Dni;
        this.Nombres = Nombres;
        this.Direccion = Direccion;
        this.Email = Email;
        this.Password = Password;
        this.Perfil = Perfil;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }    
    
    public Integer getPerfil() {
        return Perfil;
    }

    public void setPerfil(Integer Perfil) {
        this.Perfil = Perfil;
    }    
}
