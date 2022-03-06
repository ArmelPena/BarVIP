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
import com.barvip.modelodao.ClienteDAO;
import com.barvip.modelodao.ComprasDAO;
import com.barvip.modelodao.PagosDAO;
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

    Pago objPago = new Pago();
    PagosDAO objPagosDAO = new PagosDAO();
    
    ClienteDAO objClienteDAO = new ClienteDAO();
    cliente objCliente = new cliente();
    int nResult = 0;
    
    ProductoDAO pdao = new ProductoDAO();
    List<Producto> productos = new ArrayList();
    
    Producto objProducto = new Producto();
    List<Carrito> ListaCarrito = new ArrayList<>();
    int Item;
    double TotalPagar = 0.0;
    int Cantidad = 1;
    int Producto_Id;
    Carrito objCarrito;
    
    String stNombres;
    String stEmail;
    String stIdentificacion;
    String stDireccion;
    String stPassword;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        productos = pdao.Listar();
        switch (accion) {
            case "Generar compra":
                objCliente = null;
                objCliente = new cliente();
                objPago = null;
                objPagosDAO = null;
                objPagosDAO = new PagosDAO();
                
                int Cliente_Id = 17;
                Date dHoy = new Date();
                java.sql.Date sqlFecha = new java.sql.Date(dHoy.getTime());
                String strFecha = sqlFecha.toString();
                
                String stNombreCliente = request.getParameter("txtnombrecliente");
                String stDireccio = request.getParameter("txtdireccio");
                String stReferenciaDir = request.getParameter("txtreferenciadir");
                String stTelefono = request.getParameter("txttelefono");
                
                objPago = new Pago(0, 0.0, null, null, null, null);
                objPago.setId(0);
                objPago.setMonto(TotalPagar);
                objPago.setNombreCliente(stNombreCliente);
                objPago.setDireccion(stDireccio);
                objPago.setReferenciaDir(stReferenciaDir);
                objPago.setTelefono(stTelefono);
                nResult = objPagosDAO.GenerarPago(objPago);
                if (nResult > 0){
                    int Pago_Id = nResult;              
                    ComprasDAO objComprasDAO = new ComprasDAO();
                    Compras objCompra = new Compras(0,strFecha,TotalPagar,"Pendiente Pago",Cliente_Id,Pago_Id,ListaCarrito);
                    int nRet = objComprasDAO.GenerarCompra(objCompra);
                    if(nRet != 0 && TotalPagar > 0){
                        request.getRequestDispatcher("Vistas/mensaje.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("Vistas/error.jsp").forward(request, response);
                    }
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
                stPassword = ""; //request.getParameter("txtdireccion");
                
                objCliente.setDni(stIdentificacion);
                objCliente.setNombres(stNombres);
                objCliente.setEmail(stEmail);
                objCliente.setDireccion(stDireccion);
                objCliente.setPassword(stPassword);
                nResult = objClienteDAO.crear(objCliente);
                if(nResult == 1){
                    request.getSession().setAttribute("nom", stNombres);
                    request.getSession().setAttribute("correo", stEmail);
                    request.getRequestDispatcher("Vistas/inicio.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;                
            case "Comprar":
                Producto_Id = Integer.parseInt(request.getParameter("id"));
                objProducto = pdao.ListarIdProducto(Producto_Id);
                Item = Item + 1;
                Carrito objCarrito = new Carrito();
                objCarrito.setItem(Item);
                objCarrito.setProducto_Id(objProducto.getId());
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
                Producto_Id = Integer.parseInt(request.getParameter("id"));
                objProducto = pdao.ListarIdProducto(Producto_Id);
                if (ListaCarrito.size() > 0) {
                    for (int i = 0; i < ListaCarrito.size(); i++) {
                        if (Producto_Id == ListaCarrito.get(i).getProducto_Id()) {
                            pos = i;
                            i =  ListaCarrito.size() + 1;
                        }
                    }
                    if (Producto_Id == ListaCarrito.get(pos).getProducto_Id()) {
                        Cantidad = ListaCarrito.get(pos).getCantidad() + Cantidad;
                        double subtotal = ListaCarrito.get(pos).getPrecioCompra() * Cantidad;
                        ListaCarrito.get(pos).setCantidad(Cantidad);
                        ListaCarrito.get(pos).setSubtotal(subtotal);
                    } else {
                        Producto_Id = Integer.parseInt(request.getParameter("id"));
                        objProducto = pdao.ListarIdProducto(Producto_Id);
                        Item = Item + 1;
                        objCarrito = new Carrito();
                        objCarrito.setItem(Item);
                        objCarrito.setProducto_Id(objProducto.getId());
                        objCarrito.setNombres(objProducto.getNombres());
                        objCarrito.setDescripcion(objProducto.getDescripcion());
                        objCarrito.setPrecioCompra(objProducto.getPrecio());
                        objCarrito.setCantidad(Cantidad);
                        objCarrito.setSubtotal(Cantidad * objProducto.getPrecio());
                        ListaCarrito.add(objCarrito);
                    }
                } else {
                    Producto_Id = Integer.parseInt(request.getParameter("id"));
                    objProducto = pdao.ListarIdProducto(Producto_Id);
                    Item = Item + 1;
                    objCarrito = new Carrito();
                    objCarrito.setItem(Item);
                    objCarrito.setProducto_Id(objProducto.getId());
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
                int Item = Integer.parseInt(request.getParameter("item"));
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    if (ListaCarrito.get(i).getItem() == Item) {
                        ListaCarrito.remove(i);
                    }
                }
                break;
            case "ActualizarCantidad":
                Producto_Id = Integer.parseInt(request.getParameter("idpro"));
                int cant = Integer.parseInt(request.getParameter("Cantidad"));
                for (int i = 0; i < ListaCarrito.size(); i++) {
                    if (ListaCarrito.get(i).getProducto_Id() == Producto_Id) {
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
