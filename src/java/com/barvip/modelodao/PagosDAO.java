/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelodao;

import com.barvip.config.Conexion;
import com.barvip.modelo.Pago;
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
public class PagosDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int nRetorno=0;
    
    public int GenerarPago(Pago objPago){
        try {
            String sql = "insert into Pago(Monto, NombreCliente, Direccion, Telefono, ReferenciaDir) values (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, objPago.getMonto());
            ps.setString(2, objPago.getNombreCliente());
            ps.setString(3, objPago.getDireccion());
            ps.setString(4, objPago.getTelefono());
            ps.setString(5, objPago.getReferenciaDir());
            nRetorno = ps.executeUpdate();
            if(nRetorno == 1){
                sql = "Select @@IDENTITY AS IdPago";
                rs = ps.executeQuery(sql);
                rs.next();
                nRetorno = rs.getInt("IdPago");
                rs.close();
            }
        } catch (SQLException ex) {
            nRetorno = 0;
            Logger.getLogger(PagosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nRetorno;
    }
}
