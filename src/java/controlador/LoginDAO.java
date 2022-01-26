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
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import modelo.Administrativo;
import modelo.Connections;
import modelo.Productos;
import modelo.Usuario;

/**
 *
 * @author jurgen
 */
@ManagedBean
@ViewScoped
public class LoginDAO implements Serializable {

    private Administrativo admin = new Administrativo();
    private Usuario user = new Usuario();
    private Connections cone;
    private List<Administrativo> listaAdmin;
    public static String NOMBRE;

    /**
     * Creates a new instance of LoginDAO
     */
    public LoginDAO() {
    }
    @PostConstruct
    public void init(){
        reportA();
    }

    public static String getNOMBRE() {
        return NOMBRE;
    }

    public static void setNOMBRE(String NOMBRE) {
        LoginDAO.NOMBRE = NOMBRE;
    }

    public Administrativo getAdmin() {
        return admin;
    }

    public void setAdmin(Administrativo admin) {
        this.admin = admin;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Connections getCone() {
        return cone;
    }

    public void setCone(Connections cone) {
        this.cone = cone;
    }

    public List<Administrativo> getListaAdmin() {
        return listaAdmin;
    }

    public void setListaAdmin(List<Administrativo> listaAdmin) {
        this.listaAdmin = listaAdmin;
    }

    public String guardarUsuario() {
        this.cone = new Connections();
        if (this.user.getContraseña().equals(this.user.getValidacion())) {
            String sql = "INSERT INTO tb_usuarios (nombre,correo,telefono,nombreUsuario,contraseña,validacion) VALUES('"
                    + this.user.getNombre() + "','"
                    + this.user.getCorreo() + "','"
                    + this.user.getTelefono() + "','"
                    + this.user.getNombreUsuario() + "','"
                    + this.user.getContraseña() + "','"
                    + this.user.getValidacion() + "');";
            System.err.println(sql);
            this.cone.setProcess(sql);
            this.cone.close();
            FacesMessage msg = new FacesMessage("Usuario agregado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "loginU?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage("Las contraseñas no coinciden");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return "";
    }//guardarUsuario

    public String loginU() {
        this.cone = new Connections();
        ResultSet res = this.cone.getConsult("SELECT * FROM tb_usuarios WHERE nombreUsuario='" + this.user.getNombreUsuario() + "';");
        try {
            while (res.next()) {
                Usuario usu = new Usuario();
                usu.setNombre(res.getString("nombre"));
                usu.setCorreo(res.getString("correo"));
                usu.setTelefono(res.getString("telefono"));
                usu.setNombreUsuario(res.getString("nombreUsuario"));
                usu.setContraseña(res.getString("contraseña"));
                usu.setValidacion(res.getString("validacion"));
                if (usu.getContraseña().equals(this.user.getContraseña())) {
                    NOMBRE = usu.getNombre();
                    return "rent?faces-redirect=true";
                } else {
                    FacesMessage msg = new FacesMessage("Contraseña incorrecta");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        } catch (SQLException ex) {
        }
        FacesMessage msg = new FacesMessage("Usuario o contraseña incorrecta");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }//fin del loginU

    public String guardarAdmin() {
        this.cone = new Connections();
        String sql = "INSERT INTO tb_administrativo (usuario,contraseña) VALUES('"
                + this.admin.getUsuario() + "','"
                + this.admin.getContraseña() + "');";
        System.err.println(sql);
        this.cone.setProcess(sql);
        this.cone.close();
        FacesMessage msg = new FacesMessage("ingresado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "regUser?faces-redirect=true";
    }//guardarAdmin

    public String loginA() {
        this.cone = new Connections();
        ResultSet res = this.cone.getConsult("SELECT * FROM tb_administrativo WHERE usuario='" + this.admin.getUsuario() + "';");
        try {
            while (res.next()) {
                Administrativo ad = new Administrativo();
                ad.setUsuario(res.getString("usuario"));
                ad.setContraseña(res.getString("contraseña"));
                if (ad.getContraseña().equals(this.admin.getContraseña())) {
                    return "admin?faces-redirect=true";
                } else {
                    FacesMessage msg = new FacesMessage("Contraseña incorrecta");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
        } catch (SQLException ex) {
        }
        FacesMessage msg = new FacesMessage("Usuario o contraseña incorrecta");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "";
    }//fin del loginA

    public String deleteA(String usuario) {
        Connections conexion = new Connections();
        String sql = "DELETE FROM tb_administrativo WHERE usuario='" + usuario + "';";
        conexion = new Connections();
        conexion.setProcess(sql);
        conexion.close();
        //report();
        FacesMessage msg = new FacesMessage("User Delete");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "regUser?faces-redirect=true";
    }
    
    public void reportA() {
        this.listaAdmin = new ArrayList<>();
        Connections conexion = new Connections();
        String sql = "SELECT * FROM tb_administrativo";
        ResultSet result = conexion.getConsult(sql);
        try {
            while (result.next()) {
                Administrativo ad = new Administrativo();
                ad.setUsuario(result.getString("usuario"));
                ad.setContraseña(result.getString("contraseña"));
                this.listaAdmin.add(ad);
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar");
        }
    }//fin del reporte
    
}//fin de la clase
