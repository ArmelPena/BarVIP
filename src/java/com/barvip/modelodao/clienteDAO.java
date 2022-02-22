/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelo;

import com.barvip.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ARMEL_000
 */
public class clienteDAO implements Validar{

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int nResult=0;
    
    @Override
    public int validar(cliente cli) {
        try {
            String sql = "Select * from cliente where (Nombres=? and Email=?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombres());
            ps.setString(2, cli.getEmail());
            rs=ps.executeQuery();
            if(rs.next()){
                cli.setNombres(rs.getString("Nombres"));
                cli.setEmail(rs.getString("Email"));
                nResult = 1;
            }else{
                nResult = 0;
            }
            
        } catch (SQLException ex) {
            nResult = 0;
            Logger.getLogger(clienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nResult;
    }
    
}
