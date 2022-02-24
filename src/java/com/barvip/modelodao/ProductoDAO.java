/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.modelo;

import com.barvip.config.Conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ARMEL_000
 */
public class ProductoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public Producto ListarIdProducto(int IdProd){
        String sql = "select * from producto where IdProducto="+IdProd;
        Producto objProducto = new Producto();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                objProducto.setIdProducto(rs.getInt(1));
                objProducto.setNombres(rs.getString(2));
                objProducto.setFoto(rs.getBinaryStream(3));
                objProducto.setDescripcion(rs.getString(4));
                objProducto.setPrecio(rs.getDouble(5));
                objProducto.setStock(rs.getInt(6));
                
            }
        } catch (Exception e) {
        }
        return objProducto;
    }
    public List Listar(){
        List<Producto> productos = new ArrayList();
        String sql = "select * from producto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombres(rs.getString(2));
                producto.setFoto(rs.getBinaryStream(3));
                producto.setDescripcion(rs.getString(4));
                producto.setPrecio(rs.getDouble(5));
                producto.setStock(rs.getInt(6));
                productos.add(producto);
                System.out.println(rs.getInt(1));
            }        
        } catch (Exception e){
        }
        return productos;
    }
    
    public void ListarImg(int id, HttpServletResponse response){
        String sql = "select * from producto where IdProducto = " + id;
        InputStream inputstream = null;
        OutputStream outputstream = null;
        BufferedInputStream bufferedinputstream = null;
        BufferedOutputStream bufferedoutputstream = null;
        try{
            outputstream = response.getOutputStream();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                inputstream = rs.getBinaryStream("Foto");
            }
            bufferedinputstream = new BufferedInputStream(inputstream);
            bufferedoutputstream = new BufferedOutputStream(outputstream);
            int i=0;
            while((i=bufferedinputstream.read())!=-1){
                bufferedoutputstream.write(i);
            }
        } catch (Exception e){
        }
    }
}
