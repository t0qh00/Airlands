/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author jurgen
 */
public class Usuario {
    String nombre,correo,telefono,nombreUsuario,contraseña,validacion;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String telefono, String nombreUsuario, String contraseña, String validacion) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.validacion = validacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getValidacion() {
        return validacion;
    }

    public void setValidacion(String validacion) {
        this.validacion = validacion;
    }
    
}
