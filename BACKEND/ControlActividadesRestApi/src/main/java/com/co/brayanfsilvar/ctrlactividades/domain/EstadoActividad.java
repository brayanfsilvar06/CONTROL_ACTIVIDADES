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
@Table(name = "estado_actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoActividad.findAll", query = "SELECT ea FROM EstadoActividad ea"),
    @NamedQuery(name = "EstadoActividad.findByICodigo", query = "SELECT ea FROM EstadoActividad ea WHERE ea.iCodigo = :iCodigo"),
    @NamedQuery(name = "EstadoActividad.findByCDescripcion", query = "SELECT ea FROM EstadoActividad ea WHERE ea.cDescripcion = :cDescripcion")})
public class EstadoActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_CODIGO")
    private Long iCodigo;

    @Size(min = 1, max = 80)
    @Column(name = "C_DESCRIPCION")
    private String cDescripcion;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iEstadoActividad")
//    private List<Actividad> actividadList;

    public EstadoActividad() {
    }

    public EstadoActividad(Long iCodigo) {
        this.iCodigo = iCodigo;
    }

    public Long getICodigo() {
        return iCodigo;
    }

    public void setICodigo(Long iCodigo) {
        this.iCodigo = iCodigo;
    }

    public String getCDescripcion() {
        return cDescripcion;
    }

    public void setCDescripcion(String cDescripcion) {
        this.cDescripcion = cDescripcion;
    }
//
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
        if (!(object instanceof EstadoActividad)) {
            return false;
        }
        EstadoActividad other = (EstadoActividad) object;
        if ((this.iCodigo == null && other.iCodigo != null) || (this.iCodigo != null && !this.iCodigo.equals(other.iCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.brayanfsilvar.ctrlactividades.domain.EstadoActividad[ iCodigo=" + iCodigo + " ]";
    }

}
