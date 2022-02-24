/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelodao;

import com.barvip.modelo.cliente;
import com.barvip.modelo.iCliente;

/**
 *
 * @author ARMEL_000
 */
import com.barvip.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO implements iCliente {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int nResult = 0;

    @Override
    public int validar(cliente cli) {
        try {
            String sql = "Select * from cliente where (Nombres=? and Email=?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombres());
            ps.setString(2, cli.getEmail());
            rs = ps.executeQuery();
            if (rs.next()) {
                cli.setNombres(rs.getString("Nombres"));
                cli.setEmail(rs.getString("Email"));
                nResult = 1;
            } else {
                nResult = 0;
            }

        } catch (SQLException ex) {
            nResult = 0;
            Logger.getLogger(iCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nResult;
    }

    @Override
    public int crear(cliente cli) {
        try {
            con = cn.getConnection();
            String sql = "insert into cliente(Dni, Nombres, Direccion, Email, Password) values (?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNombres());
            ps.setString(3, cli.getDireccion());
            ps.setString(4, cli.getEmail());
            ps.setString(5, cli.getPassword());
            nResult = ps.executeUpdate();
        } catch (SQLException ex) {
            nResult = 0;
            Logger.getLogger(iCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nResult;
    }

}
