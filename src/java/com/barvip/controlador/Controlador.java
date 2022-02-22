/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barvip.controlador;

import com.barvip.modelo.Carrito;
import com.barvip.modelo.Compras;
import com.barvip.modelo.Pago;
import com.barvip.modelo.Producto;
import com.barvip.modelo.ProductoDAO;
import com.barvip.modelo.cliente;
import com.barvip.modelo.clienteDAO;
import com.barvip.modelodao.ComprasDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ARMEL_000
 */
public class Controlador extends HttpServlet {

    clienteDAO objClienteDAO = new clienteDAO();
    cliente objCliente = new cliente();
    int nResult = 0;
    
    ProductoDAO pdao = new ProductoDAO();
    List<Producto> productos = new ArrayList();
    
    Producto objProducto = new Producto();
    List<Carrito> ListaCarrito = new ArrayList<>();
    int Item;
    double TotalPagar = 0.0;
    int Cantidad = 1;
    int IdProd;
    Carrito objCarrito;
    
    String stNombres;
    String stEmail;
    String stIdentificacion;
    String stDireccion;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        productos = pdao.Listar();
        switch (accion) {
            case "GenerarCompra":
                objCliente = null;
                objCliente = new cliente();
                Pago objPago= new Pago();
                int nIdCliente = 12;
                int nIdPago = 19;
                Date dHoy = new Date();
                java.sql.Date sqlFecha = new java.sql.Date(dHoy.getTime());
                String strFecha = sqlFecha.toString();
                ComprasDAO objComprasDAO = new ComprasDAO();
                Compras objCompra = new Compras(nIdCliente,nIdPago,strFecha,TotalPagar,"Cancelado",ListaCarrito);
                int nRet = objComprasDAO.GenerarCompra(objCompra);
                if(nRet != 0 && TotalPagar > 0){
                    request.getRequestDispatcher("Vistas/mensaje.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("Vistas/error.jsp").forward(request, response);
                }
                break;
            case "tienda":
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("Vistas/tienda.jsp").forward(request, response);
                break;
            case "RegistrarUsuario":
                request.getRequestDispatcher("Vistas/RegistrarUsuario.jsp").forward(request, response);
                break;                
            case "Salir":
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            case "Ingresar":
                stNombres = request.getParameter("txtnom");
                stEmail = request.getParameter("txtCorreo");
                objCliente.setNombres(stNombres);
                objCliente.setEmail(stEmail);
                nResult = objClienteDAO.validar(objCliente);
                if(nResult == 1){
                    request.getSession().setAttribute("nom", stNombres);
                    request.getSession().setAttribute("correo", stEmail);
                    request.getRequestDispatcher("Vistas/inicio.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            case "Crear usuario":
                stIdentificacion = request.getParameter("txtident");
                stNombres = request.getParameter("txtnom");
                stEmail = request.getParameter("txtCorreo");
                stDireccion = request.getParameter("txtdireccion");
                
                objCliente.setNombres(stIdentificacion);
                objCliente.setNombres(stNombres);
                objCliente.setEmail(stEmail);
                objCliente.setEmail(stDireccion);
                nResult = objClienteDAO.validar(objCliente);
                if(nResult == 1){
                    request.getSession().setAttribute("nom", stNombres);
                    request.getSession().setAttribute("correo", stEmail);
                    request.getRequestDispatcher("Vistas/inicio.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;                
            case "Comprar":
                IdProd = Integer.parseInt(request.getParameter("id"));
                objProducto = pdao.ListarIdProducto(IdProd);
                Item = Item + 1;
                Carrito objCarrito = new Carrito();
                objCarrito.setItem(Item);
                objCarrito.setIdProducto(objProducto.getIdProducto());
                objCarrito.setNombres(objProducto.getNombres());
                objCarrito.setDescripcion(objProducto.getDescripcion());
                objCarrito.setPrecioCompra(objProducto.getPrecio());
                objCarrito.setCantidad(Cantidad);
                objCarrito.setSubtotal(Cantidad * objProducto.getPrecio());
                ListaCarrito.add(objCarrito);
                TotalPagar = 0.0;
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    TotalPagar = TotalPagar + ListaCarrito.get(i).getSubtotal();
                }
                request.setAttribute("carrito", ListaCarrito);
                request.setAttribute("contador", ListaCarrito.size());
                request.setAttribute("totalPagar", TotalPagar);
                request.getRequestDispatcher("Vistas/Carrito.jsp").forward(request, response);
                break;
            case "AgregarCarrito":
                int pos = 0;
                Cantidad = 1;
                IdProd = Integer.parseInt(request.getParameter("id"));
                objProducto = pdao.ListarIdProducto(IdProd);
                if (ListaCarrito.size() > 0) {
                    for (int i = 0; i < ListaCarrito.size(); i++) {
                        if (IdProd == ListaCarrito.get(i).getIdProducto()) {
                            pos = i;
                            i =  ListaCarrito.size() + 1;
                        }
                    }
                    if (IdProd == ListaCarrito.get(pos).getIdProducto()) {
                        Cantidad = ListaCarrito.get(pos).getCantidad() + Cantidad;
                        double subtotal = ListaCarrito.get(pos).getPrecioCompra() * Cantidad;
                        ListaCarrito.get(pos).setCantidad(Cantidad);
                        ListaCarrito.get(pos).setSubtotal(subtotal);
                    } else {
                        IdProd = Integer.parseInt(request.getParameter("id"));
                        objProducto = pdao.ListarIdProducto(IdProd);
                        Item = Item + 1;
                        objCarrito = new Carrito();
                        objCarrito.setItem(Item);
                        objCarrito.setIdProducto(objProducto.getIdProducto());
                        objCarrito.setNombres(objProducto.getNombres());
                        objCarrito.setDescripcion(objProducto.getDescripcion());
                        objCarrito.setPrecioCompra(objProducto.getPrecio());
                        objCarrito.setCantidad(Cantidad);
                        objCarrito.setSubtotal(Cantidad * objProducto.getPrecio());
                        ListaCarrito.add(objCarrito);
                    }
                } else {
                    IdProd = Integer.parseInt(request.getParameter("id"));
                    objProducto = pdao.ListarIdProducto(IdProd);
                    Item = Item + 1;
                    objCarrito = new Carrito();
                    objCarrito.setItem(Item);
                    objCarrito.setIdProducto(objProducto.getIdProducto());
                    objCarrito.setNombres(objProducto.getNombres());
                    objCarrito.setDescripcion(objProducto.getDescripcion());
                    objCarrito.setPrecioCompra(objProducto.getPrecio());
                    objCarrito.setCantidad(Cantidad);
                    objCarrito.setSubtotal(Cantidad * objProducto.getPrecio());
                    ListaCarrito.add(objCarrito);
                }

                request.setAttribute("contador", ListaCarrito.size());
                request.getRequestDispatcher("Controlador?accion=tienda").forward(request, response);
                break;
            case "Delete":
                int idItem = Integer.parseInt(request.getParameter("idi"));
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    if (ListaCarrito.get(i).getItem() == idItem) {
                        ListaCarrito.remove(i);
                    }
                }
                break;
            case "ActualizarCantidad":
                int idpro = Integer.parseInt(request.getParameter("idp"));
                int cant = Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    if (ListaCarrito.get(i).getIdProducto() == idpro) {
                        ListaCarrito.get(i).setCantidad(cant);
                        double subt = ListaCarrito.get(i).getPrecioCompra()*cant;
                        ListaCarrito.get(i).setSubtotal(subt);
                    }
                }
                break;
            case "Carrito":
                TotalPagar = 0.0;
                request.setAttribute("carrito", ListaCarrito);
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    TotalPagar = TotalPagar + ListaCarrito.get(i).getSubtotal();
                }
                request.setAttribute("totalPagar", TotalPagar);
                request.getRequestDispatcher("Vistas/Carrito.jsp").forward(request, response);
                break;
            default:
                request.getRequestDispatcher("Vistas/inicio.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
