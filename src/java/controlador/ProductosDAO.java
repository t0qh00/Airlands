/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import modelo.Connections;
import modelo.Factura;
import modelo.Productos;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author jurgen
 */
@ManagedBean
@ViewScoped
public class ProductosDAO implements Serializable {

    private Productos producto = new Productos();
    private List<Productos> listaProductos;
    private List<Factura> listaFacturas;
    private Connections cone;
    private static Factura factura = new Factura();

    /**
     * Creates a new instance of ProductosDAO
     */
    public ProductosDAO() {
    }

    @PostConstruct
    public void init() {
        reporte();
        reportAlqui();
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public List<Productos> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Productos> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Connections getCone() {
        return cone;
    }

    public void setCone(Connections cone) {
        this.cone = cone;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    public String fac(String vehiculo, String monto) {
        factura.setUsuario(null);
        factura.setNombreA(null);
        factura.setMonto(null);
        factura.setFecha(null);
        this.factura.setUsuario(LoginDAO.NOMBRE);
        this.factura.setNombreA(vehiculo);
        this.factura.setMonto(monto);
        return "beforeTicket?faces-redirect=true";
    }

    public String cancelar() {
        System.err.print("asd");
        return "rent?faces-redirect=true";
    }

    public String tot() {
        double dias = Double.valueOf(factura.getFecha());
        double monto = Double.valueOf(factura.getMonto());
        monto = monto * dias;
        double iva = monto * 0.13;
        double total = iva + monto;
        factura.setMonto(String.valueOf(total));
        System.err.println(factura.getMonto());
        return "ticket?faces-redirect=true";
    }

    public String guardarF() {
        this.cone = new Connections();
        String sql = "INSERT INTO tb_factura (usuario,nombreA,fecha,monto) VALUES('"
                + factura.getUsuario() + "','"
                + factura.getNombreA() + "','"
                + factura.getFecha() + "','"
                + factura.getMonto() + "');";
        System.err.println(sql);
        this.cone.setProcess(sql);
        this.cone.close();
        FacesMessage msg = new FacesMessage("Alquiler agregado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "rent?faces-redirect=true";
    }

    public void reporte() {
        this.listaProductos = new ArrayList<>();
        this.cone = new Connections();
        ResultSet res = this.cone.getConsult("SELECT * FROM tb_productos");
        try {
            while (res.next()) {
                Productos product = new Productos();
                product.setNombre(res.getString("nombre"));
                product.setDescripcion(res.getString("descripcion"));
                product.setImagen(res.getString("imagen"));
                product.setPrecio(res.getInt("precio"));
                this.listaProductos.add(product);
            }
        } catch (SQLException ex) {
        }
    }//fin del reporte

    public String guardarProducto() {
        this.cone = new Connections();
        String sql = "INSERT INTO tb_productos (nombre,descripcion,imagen,precio) VALUES('"
                + this.producto.getNombre() + "','"
                + this.producto.getDescripcion() + "','"
                + this.producto.getImagen() + "','"
                + this.producto.getPrecio() + "');";
        System.err.println(sql);
        this.cone.setProcess(sql);
        this.cone.close();
        FacesMessage msg = new FacesMessage("Producto agregado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "admin?faces-redirect=true";
    }//guardar

    public void reportAlqui() {
        this.listaFacturas = new ArrayList<>();
        Connections conexion = new Connections();
        String sql = "SELECT * FROM tb_factura";
        ResultSet result = conexion.getConsult(sql);
        try {
            while (result.next()) {
                Factura fac = new Factura();
                fac.setUsuario(result.getString("usuario"));
                fac.setNombreA(result.getString("nombreA"));
                fac.setMonto(result.getString("monto"));
                fac.setFecha(result.getString("fecha"));
                this.listaFacturas.add(fac);
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar");
        }
    }//fin del reporte

    public String deleteP(String nombre) {
        Connections conexion = new Connections();
        String sql = "DELETE FROM tb_productos WHERE nombre='" + nombre + "';";
        conexion = new Connections();
        conexion.setProcess(sql);
        conexion.close();
        //report();
        FacesMessage msg = new FacesMessage("User Delete");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "admin?faces-redirect=true";
    }

    public void onRowEdit(RowEditEvent event) {
        Connections conexion = new Connections();
        Productos pro = (Productos) event.getObject();
        String sql = "UPDATE tb_productos SET nombre='" + pro.getNombre()
                + "', descripcion='" + pro.getDescripcion()
                + "', precio='" + pro.getPrecio()
                + "', imagen='" + pro.getImagen()
                + "' WHERE nombre='" + pro.getNombre()+ "';";
        conexion.setProcess(sql);
        System.err.println(sql);
        conexion.close();
        FacesMessage msg = new FacesMessage("User Edited");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {

    }
}
