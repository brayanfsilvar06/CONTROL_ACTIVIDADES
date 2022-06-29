/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BrayanFSilvaR
 */
@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByICodigo", query = "SELECT p FROM Persona p WHERE p.iCodigo = :iCodigo"),
    @NamedQuery(name = "Persona.findByCNumIdent", query = "SELECT p FROM Persona p WHERE p.cNumeroIdentificacion = :cNumeroIdentificacion"),
    @NamedQuery(name = "Persona.findByCNombres", query = "SELECT p FROM Persona p WHERE p.cNombres = :cNombres"),
    @NamedQuery(name = "Persona.findByCPrimerApellido", query = "SELECT p FROM Persona p WHERE p.cPrimerApellido = :cPrimerApellido"),
    @NamedQuery(name = "Persona.findByCSegundoApellido", query = "SELECT p FROM Persona p WHERE p.cSegundoApellido = :cSegundoApellido"),
    @NamedQuery(name = "Persona.findByCNumCel", query = "SELECT p FROM Persona p WHERE p.cNumCel = :cNumCel"),
    @NamedQuery(name = "Persona.findByCEmail", query = "SELECT p FROM Persona p WHERE p.cEmail = :cEmail"),
    @NamedQuery(name = "Persona.findByCEmpleado", query = "SELECT p FROM Persona p WHERE p.cEmpleado = :cEmpleado")

})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_CODIGO")
    private Long iCodigo;

    @Size(min = 1, max = 50)
    @Column(name = "C_NUM_IDENTIFICACION")
    private String cNumeroIdentificacion;

    @Size(min = 1, max = 150)
    @Column(name = "C_NOMBRES")
    private String cNombres;

    @Size(min = 1, max = 100)
    @Column(name = "C_PRIMER_APELLIDO")
    private String cPrimerApellido;

    @Size(min = 1, max = 100)
    @Column(name = "C_SEGUNDO_APELLIDO")
    private String cSegundoApellido;

    @Size(min = 1, max = 15)
    @Column(name = "C_NUM_CEL")
    private String cNumCel;

    @Size(min = 1, max = 80)
    @Column(name = "C_EMAIL")
    private String cEmail;

    @Size(min = 1, max = 3)
    @Column(name = "C_EMPLEADO")
    private String cEmpleado;

    @JoinColumn(name = "I_TIPO_IDENTIFICACION", referencedColumnName = "I_CODIGO")
    @ManyToOne(optional = false)
    private TipoIdentificacion iTipoIdentificacion;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iPersonaAsignada")
//    private List<Actividad> actividadList;

    public Persona() {
    }

    public Persona(Long iCodigo) {
        this.iCodigo = iCodigo;
    }

    public Persona(Long iCodigo, String cNombres, String cPrimerApellido, String cEmail) {
        this.iCodigo = iCodigo;
        this.cNombres = cNombres;
        this.cPrimerApellido = cPrimerApellido;
        this.cEmail = cEmail;
    }

    public Long getICodigo() {
        return iCodigo;
    }

    public void setICodigo(Long iCodigo) {
        this.iCodigo = iCodigo;
    }

    public String getCNombres() {
        return cNombres;
    }

    public void setCNombres(String cNombres) {
        this.cNombres = cNombres;
    }

    public String getCPrimerApellido() {
        return cPrimerApellido;
    }

    public void setCPrimerApellido(String cPrimerApellido) {
        this.cPrimerApellido = cPrimerApellido;
    }

    public String getCEmail() {
        return cEmail;
    }

    public void setCEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public String getCSegundoApellido() {
        return cSegundoApellido;
    }

    public void setCSegundoApellido(String cSegundoApellido) {
        this.cSegundoApellido = cSegundoApellido;
    }

    public String getCEmpleado() {
        return cEmpleado;
    }

    public void setCEmpleado(String cEmpleado) {
        this.cEmpleado = cEmpleado;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return iTipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion iTipoIdentificacion) {
        this.iTipoIdentificacion = iTipoIdentificacion;
    }

    public String getCNumCel() {
        return cNumCel;
    }

    public void setCNumCel(String cNumCel) {
        this.cNumCel = cNumCel;
    }

    public String getCNumeroIdentificacion() {
        return cNumeroIdentificacion;
    }

    public void setCNumeroIdentificacion(String cNumeroIdentificacion) {
        this.cNumeroIdentificacion = cNumeroIdentificacion;
    }

//    public List<Actividad> getActividadList() {
//        return actividadList;
//    }
//
//    public void setActividadList(List<Actividad> actividadList) {
//        this.actividadList = actividadList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iCodigo != null ? iCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.iCodigo == null && other.iCodigo != null) || (this.iCodigo != null && !this.iCodigo.equals(other.iCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.brayanfsilvar.ctrlactividades.domain.Persona[ iCodigo=" + iCodigo + " ]";
    }

}
