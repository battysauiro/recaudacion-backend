/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "derechogeneral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Derechogeneral.findAll", query = "SELECT d FROM Derechogeneral d"),
    @NamedQuery(name = "Derechogeneral.findByIdContribucionDerechosLicencias", query = "SELECT d FROM Derechogeneral d WHERE d.idContribucionDerechosLicencias = :idContribucionDerechosLicencias"),
    @NamedQuery(name = "Derechogeneral.findByCantidad", query = "SELECT d FROM Derechogeneral d WHERE d.cantidad = :cantidad")})
public class Derechogeneral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_derechos_licencias")
    private String idContribucionDerechosLicencias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @JoinColumn(name = "id_contribucion_derechos_licencias", referencedColumnName = "id_contribucion_derechos", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Derechos derechos;
    @JoinColumn(name = "id_periodicidad", referencedColumnName = "id_periodicidad")
    @ManyToOne(optional = false)
    private Periodicidad idPeriodicidad;

    public Derechogeneral() {
    }

    public Derechogeneral(String idContribucionDerechosLicencias) {
        this.idContribucionDerechosLicencias = idContribucionDerechosLicencias;
    }

    public String getIdContribucionDerechosLicencias() {
        return idContribucionDerechosLicencias;
    }

    public void setIdContribucionDerechosLicencias(String idContribucionDerechosLicencias) {
        this.idContribucionDerechosLicencias = idContribucionDerechosLicencias;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public Derechos getDerechos() {
        return derechos;
    }

    public void setDerechos(Derechos derechos) {
        this.derechos = derechos;
    }

    public Periodicidad getIdPeriodicidad() {
        return idPeriodicidad;
    }

    public void setIdPeriodicidad(Periodicidad idPeriodicidad) {
        this.idPeriodicidad = idPeriodicidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContribucionDerechosLicencias != null ? idContribucionDerechosLicencias.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Derechogeneral)) {
            return false;
        }
        Derechogeneral other = (Derechogeneral) object;
        if ((this.idContribucionDerechosLicencias == null && other.idContribucionDerechosLicencias != null) || (this.idContribucionDerechosLicencias != null && !this.idContribucionDerechosLicencias.equals(other.idContribucionDerechosLicencias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Derechogeneral[ idContribucionDerechosLicencias=" + idContribucionDerechosLicencias + " ]";
    }
    
}
