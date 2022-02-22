/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelodao;

import com.barvip.config.Conexion;
import com.barvip.modelo.Carrito;
import com.barvip.modelo.Compras;
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
public class ComprasDAO {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int nRetorno=0;
    
    public int GenerarCompra(Compras Compras){
        try {
            int nIdCompras;
            String sql = "insert into Compras(IdCliente, IdPago, FechaCompras, Monto, Estado) values (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, Compras.getIdCliente());
            ps.setInt(2, Compras.getIdPago());
            ps.setString(3, Compras.getFechaCompras());
            ps.setDouble(4, Compras.getMonto());
            ps.setString(5, Compras.getEstado());
            nRetorno = ps.executeUpdate();
            if(nRetorno == 0){
                sql = "Select @@IDENTITY AS IdCompras";
                rs = ps.executeQuery(sql);
                rs.next();
                nIdCompras = rs.getInt("IdCompras");
                rs.close();
            
                for (Carrito Detalle: Compras.getDetalleCompras()){
                    sql = "insert into detalle_compras("
                        + "IdProducto, "
                        + "IdCompras, "
                        + "Cantidad, "
                        + "PrecioCompra) "
                        + "values(?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, Detalle.getIdProducto());
                    ps.setInt(2, nIdCompras);
                    ps.setInt(3, Detalle.getCantidad());
                    ps.setDouble(4, Detalle.getPrecioCompra());
                    nRetorno = ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            nRetorno = 1;
            Logger.getLogger(ComprasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nRetorno;
    }
}
