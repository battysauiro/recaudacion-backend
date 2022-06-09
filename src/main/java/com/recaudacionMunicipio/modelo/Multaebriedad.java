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
@Table(name = "multaebriedad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Multaebriedad.findAll", query = "SELECT m FROM Multaebriedad m"),
    @NamedQuery(name = "Multaebriedad.findByIdContribucionMultaEbriedad", query = "SELECT m FROM Multaebriedad m WHERE m.idContribucionMultaEbriedad = :idContribucionMultaEbriedad"),
    @NamedQuery(name = "Multaebriedad.findByUmaMin", query = "SELECT m FROM Multaebriedad m WHERE m.umaMin = :umaMin"),
    @NamedQuery(name = "Multaebriedad.findByUmaMax", query = "SELECT m FROM Multaebriedad m WHERE m.umaMax = :umaMax"),
    @NamedQuery(name = "Multaebriedad.findByCantidadAlcohol", query = "SELECT m FROM Multaebriedad m WHERE m.cantidadAlcohol = :cantidadAlcohol")})
public class Multaebriedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_multa_ebriedad")
    private String idContribucionMultaEbriedad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "uma_min")
    private Float umaMin;
    @Column(name = "uma_max")
    private Float umaMax;
    @Column(name = "cantidad_alcohol")
    private Float cantidadAlcohol;
    @JoinColumn(name = "id_contribucion_multa_ebriedad", referencedColumnName = "id_contribucion_aprovechamiento", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Aprovechamiento aprovechamiento;

    public Multaebriedad() {
    }

    public Multaebriedad(String idContribucionMultaEbriedad) {
        this.idContribucionMultaEbriedad = idContribucionMultaEbriedad;
    }

    public String getIdContribucionMultaEbriedad() {
        return idContribucionMultaEbriedad;
    }

    public void setIdContribucionMultaEbriedad(String idContribucionMultaEbriedad) {
        this.idContribucionMultaEbriedad = idContribucionMultaEbriedad;
    }

    public Float getUmaMin() {
        return umaMin;
    }

    public void setUmaMin(Float umaMin) {
        this.umaMin = umaMin;
    }

    public Float getUmaMax() {
        return umaMax;
    }

    public void setUmaMax(Float umaMax) {
        this.umaMax = umaMax;
    }

    public Float getCantidadAlcohol() {
        return cantidadAlcohol;
    }

    public void setCantidadAlcohol(Float cantidadAlcohol) {
        this.cantidadAlcohol = cantidadAlcohol;
    }

    public Aprovechamiento getAprovechamiento() {
        return aprovechamiento;
    }

    public void setAprovechamiento(Aprovechamiento aprovechamiento) {
        this.aprovechamiento = aprovechamiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContribucionMultaEbriedad != null ? idContribucionMultaEbriedad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multaebriedad)) {
            return false;
        }
        Multaebriedad other = (Multaebriedad) object;
        if ((this.idContribucionMultaEbriedad == null && other.idContribucionMultaEbriedad != null) || (this.idContribucionMultaEbriedad != null && !this.idContribucionMultaEbriedad.equals(other.idContribucionMultaEbriedad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Multaebriedad[ idContribucionMultaEbriedad=" + idContribucionMultaEbriedad + " ]";
    }
    
}
