/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.brayanfsilvar.ctrlactividades.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author BrayanFSilvaR
 */
@Entity
@Table(name = "tipo_identificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoIdentificacion.findAll", query = "SELECT t FROM TipoIdentificacion t"),
    @NamedQuery(name = "TipoIdentificacion.findByICodigo", query = "SELECT t FROM TipoIdentificacion t WHERE t.iCodigo = :iCodigo"),
    @NamedQuery(name = "TipoIdentificacion.findByCDescripcion", query = "SELECT t FROM TipoIdentificacion t WHERE t.cDescripcion = :cDescripcion"),
    @NamedQuery(name = "TipoIdentificacion.findByCAbreviatura", query = "SELECT t FROM TipoIdentificacion t WHERE t.cAbreviatura = :cAbreviatura")})
public class TipoIdentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "I_CODIGO")
    private Long iCodigo;

    @Size(min = 1, max = 100)
    @Column(name = "C_DESCRIPCION")
    private String cDescripcion;

    @Size(min = 1, max = 40)
    @Column(name = "C_ABREVIATURA")
    private String cAbreviatura;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(Long iCodigo) {
        this.iCodigo = iCodigo;
    }

    public TipoIdentificacion(Long iCodigo, String cDescripcion, String cAbreviatura) {
        this.iCodigo = iCodigo;
        this.cDescripcion = cDescripcion;
        this.cAbreviatura = cAbreviatura;
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

    public String getCAbreviatura() {
        return cAbreviatura;
    }

    public void setCAbreviatura(String cEstado) {
        this.cAbreviatura = cEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iCodigo != null ? iCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoIdentificacion)) {
            return false;
        }
        TipoIdentificacion other = (TipoIdentificacion) object;
        if ((this.iCodigo == null && other.iCodigo != null) || (this.iCodigo != null && !this.iCodigo.equals(other.iCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.brayanfsilvar.ctrlactividades.domain.TipoIdentificacion[ iCodigo=" + iCodigo + " ]";
    }

}
