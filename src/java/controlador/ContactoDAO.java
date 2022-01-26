/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import modelo.Connections;
import modelo.Contacto;

/**
 *
 * @author jurgen
 */
@ManagedBean
@ViewScoped
public class ContactoDAO implements Serializable {

    /**
     * Creates a new instance of ContactoDAO
     */
    private Contacto contacto = new Contacto();
    private List<SelectItem> tipoConsulta;
    private Connections cone;

    public ContactoDAO() {
    }

    @PostConstruct
    public void init() {
        consultas();
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public List<SelectItem> getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(List<SelectItem> tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public void submit() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correct", "Correct");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void consultas() {
        SelectItemGroup gr1 = new SelectItemGroup("Consultas");
        gr1.setSelectItems(new SelectItem[]{
            new SelectItem("Consulta"),
            new SelectItem("Recomendaciones"),
            new SelectItem("Quejas")});
        this.tipoConsulta = new ArrayList<>();
        this.tipoConsulta.add(gr1);
    }

    public String guardarConsulta() {
        this.cone = new Connections();
        String sql = "INSERT INTO tb_consulta (nombre,email,pais,direccion,telefono,tipoConsulta,comentario) VALUES('"
                + this.contacto.getNombre() + "','"
                + this.contacto.getEmail() + "','"
                + this.contacto.getPais() + "','"
                + this.contacto.getDireccion() + "','"
                + this.contacto.getTelefono() + "','"
                + this.contacto.getTipoConsulta() + "','"
                + this.contacto.getComentario() + "');";
        System.err.println(sql);
        this.cone.setProcess(sql);
        this.cone.close();
        FacesMessage msg = new FacesMessage("Consulta enviada");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "contact?faces-redirect=true";
    }//guardar
    
    
}//fin de la clase
