/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BrayanFSilvaR
 */
@Entity
@Table(name = "actividades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByICodigo", query = "SELECT a FROM Actividad a WHERE a.iCodigo = :iCodigo"),
    @NamedQuery(name = "Actividad.findByCTitulo", query = "SELECT a FROM Actividad a WHERE a.cTitulo = :cTitulo"),
    @NamedQuery(name = "Actividad.findByCDescripcion", query = "SELECT a FROM Actividad a WHERE a.cDescripcion = :cDescripcion"),
    @NamedQuery(name = "Actividad.findByFFechaEstimadaEjecucion", query = "SELECT a FROM Actividad a WHERE a.fFechaEstimadaEjecucion = :fFechaEstimadaEjecucion"),
    @NamedQuery(name = "Actividad.findByIDiasRetraso", query = "SELECT a FROM Actividad a WHERE a.iDiasRetraso = :iDiasRetraso")})
public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_CODIGO")
    private Long iCodigo;

    @Size(min = 1, max = 50)
    @Column(name = "C_TITULO")
    private String cTitulo;

    @Size(min = 1, max = 50)
    @Column(name = "C_DESCRIPCION")
    private String cDescripcion;

    @Column(name = "F_FECHA_ESTIMADA_EJECUCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fFechaEstimadaEjecucion;

    @Column(name = "I_DIAS_RETRASO")
    private long iDiasRetraso;

    @JoinColumn(name = "I_PERSONA_ASIGNADA", referencedColumnName = "I_CODIGO")
    @ManyToOne(optional = false)
    private Persona iPersonaAsignada;

    @JoinColumn(name = "I_ESTADO_ACTIVIDAD", referencedColumnName = "I_CODIGO")
    @ManyToOne(optional = false)
    private EstadoActividad iEstadoActividad;

    @Transient
    private Long lMarcaTiempoEjecucionEstimada;


    public Actividad() {
    }

    public Actividad(Long iCodigo) {
        this.iCodigo = iCodigo;
    }
    
    public Long getICodigo() {
        return iCodigo;
    }

    public void setICodigo(Long iCodigo) {
        this.iCodigo = iCodigo;
    }

    public String getCTitulo() {
        return cTitulo;
    }

    public void setCTitulo(String cTitulo) {
        this.cTitulo = cTitulo;
    }

    public String getCDescripcion() {
        return cDescripcion;
    }

    public void setCDescripcion(String cDescripcion) {
        this.cDescripcion = cDescripcion;
    }

    public Date getFFechaEstimadaEjecucion() {
        return fFechaEstimadaEjecucion;
    }

    public void setFFechaEstimadaEjecucion(Date fFechaEstimadaEjecucion) {
        this.fFechaEstimadaEjecucion = fFechaEstimadaEjecucion;
    }

    public long getIDiasRetraso() {
        return iDiasRetraso;
    }

    public void setIDiasRetraso(long iDiasRetraso) {
        this.iDiasRetraso = iDiasRetraso;
    }

    public Persona getIPersonaAsignada() {
        return iPersonaAsignada;
    }

    public void setIPersonaAsignada(Persona iPersonaAsignada) {
        this.iPersonaAsignada = iPersonaAsignada;
    }

    public EstadoActividad getIEstadoActividad() {
        return iEstadoActividad;
    }

    public void setIEstadoActividad(EstadoActividad iEstadoActividad) {
        this.iEstadoActividad = iEstadoActividad;
    }
        
    public Long getLMarcaTiempoEjecucionEstimada() {
        return lMarcaTiempoEjecucionEstimada;
    }

    public void setLMarcaTiempoEjecucionEstimada(Long lMarcaTiempoEjecucionEstimada) {
        this.lMarcaTiempoEjecucionEstimada = lMarcaTiempoEjecucionEstimada;
    }


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
        Actividad other = (Actividad) object;
        if ((this.iCodigo == null && other.iCodigo != null) || (this.iCodigo != null && !this.iCodigo.equals(other.iCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.brayanfsilvar.ctrlactividades.domain.Actividad[ iCodigo=" + iCodigo + " ]";
    }
}
