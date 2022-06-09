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
@Table(name = "derechoslicencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Derechoslicencias.findAll", query = "SELECT d FROM Derechoslicencias d"),
    @NamedQuery(name = "Derechoslicencias.findByIdContribucionDerechosLicencias", query = "SELECT d FROM Derechoslicencias d WHERE d.idContribucionDerechosLicencias = :idContribucionDerechosLicencias"),
    @NamedQuery(name = "Derechoslicencias.findByExpedicion", query = "SELECT d FROM Derechoslicencias d WHERE d.expedicion = :expedicion"),
    @NamedQuery(name = "Derechoslicencias.findByRefrendo", query = "SELECT d FROM Derechoslicencias d WHERE d.refrendo = :refrendo")})
public class Derechoslicencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_derechos_licencias")
    private String idContribucionDerechosLicencias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "expedicion")
    private Float expedicion;
    @Column(name = "refrendo")
    private Float refrendo;
    @JoinColumn(name = "id_contribucion_derechos_licencias", referencedColumnName = "id_contribucion_derechos", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Derechos derechos;

    public Derechoslicencias() {
    }

    public Derechoslicencias(String idContribucionDerechosLicencias) {
        this.idContribucionDerechosLicencias = idContribucionDerechosLicencias;
    }

    public String getIdContribucionDerechosLicencias() {
        return idContribucionDerechosLicencias;
    }

    public void setIdContribucionDerechosLicencias(String idContribucionDerechosLicencias) {
        this.idContribucionDerechosLicencias = idContribucionDerechosLicencias;
    }

    public Float getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(Float expedicion) {
        this.expedicion = expedicion;
    }

    public Float getRefrendo() {
        return refrendo;
    }

    public void setRefrendo(Float refrendo) {
        this.refrendo = refrendo;
    }

    public Derechos getDerechos() {
        return derechos;
    }

    public void setDerechos(Derechos derechos) {
        this.derechos = derechos;
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
        if (!(object instanceof Derechoslicencias)) {
            return false;
        }
        Derechoslicencias other = (Derechoslicencias) object;
        if ((this.idContribucionDerechosLicencias == null && other.idContribucionDerechosLicencias != null) || (this.idContribucionDerechosLicencias != null && !this.idContribucionDerechosLicencias.equals(other.idContribucionDerechosLicencias))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Derechoslicencias[ idContribucionDerechosLicencias=" + idContribucionDerechosLicencias + " ]";
    }
    
}
