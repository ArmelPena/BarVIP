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
            String sql = "insert into Compras(FechaCompras, Monto, Estado, Cliente_Id, Pago_Id) values (?,?,?,?,?)";
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, Compras.getFechaCompras());
            ps.setDouble(2, Compras.getMonto());
            ps.setString(3, Compras.getEstado());
            ps.setInt(4, Compras.getCliente_Id());
            ps.setInt(5, Compras.getPago_Id());
            nRetorno = ps.executeUpdate();
            if(nRetorno == 1){
                sql = "Select @@IDENTITY AS Id";
                rs = ps.executeQuery(sql);
                rs.next();
                nIdCompras = rs.getInt("Id");
                rs.close();
            
                for (Carrito Detalle: Compras.getDetalleCompras()){
                    sql = "insert into detalle_compras("
                        + "Cantidad, "
                        + "PrecioCompra,"
                        + "Producto_Id, "
                        + "Compras_Id)" 
                        + "values(?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, Detalle.getCantidad());
                    ps.setDouble(2, Detalle.getPrecioCompra());
                    ps.setInt(3, Detalle.getProducto_Id());
                    ps.setInt(4, nIdCompras);                    
                    nRetorno = ps.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            nRetorno = 0;
            Logger.getLogger(ComprasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nRetorno;
    }
}
