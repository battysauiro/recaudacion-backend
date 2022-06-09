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
@Table(name = "multavehicular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Multavehicular.findAll", query = "SELECT m FROM Multavehicular m"),
    @NamedQuery(name = "Multavehicular.findByIdContribucionMultaVehicular", query = "SELECT m FROM Multavehicular m WHERE m.idContribucionMultaVehicular = :idContribucionMultaVehicular"),
    @NamedQuery(name = "Multavehicular.findByDescripcionArticulo", query = "SELECT m FROM Multavehicular m WHERE m.descripcionArticulo = :descripcionArticulo"),
    @NamedQuery(name = "Multavehicular.findByUmaMin", query = "SELECT m FROM Multavehicular m WHERE m.umaMin = :umaMin"),
    @NamedQuery(name = "Multavehicular.findByUmaMax", query = "SELECT m FROM Multavehicular m WHERE m.umaMax = :umaMax")})
public class Multavehicular implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_multa_vehicular")
    private String idContribucionMultaVehicular;
    @Column(name = "descripcion_articulo")
    private String descripcionArticulo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "uma_min")
    private Float umaMin;
    @Column(name = "uma_max")
    private Float umaMax;
    @JoinColumn(name = "id_contribucion_multa_vehicular", referencedColumnName = "id_contribucion_aprovechamiento", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Aprovechamiento aprovechamiento;
    @JoinColumn(name = "id_tipo_vehiculo", referencedColumnName = "id_tipo_vehiculo")
    @ManyToOne(optional = false)
    private Tipovehiculo idTipoVehiculo;

    public Multavehicular() {
    }

    public Multavehicular(String idContribucionMultaVehicular) {
        this.idContribucionMultaVehicular = idContribucionMultaVehicular;
    }

    public String getIdContribucionMultaVehicular() {
        return idContribucionMultaVehicular;
    }

    public void setIdContribucionMultaVehicular(String idContribucionMultaVehicular) {
        this.idContribucionMultaVehicular = idContribucionMultaVehicular;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
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

    public Aprovechamiento getAprovechamiento() {
        return aprovechamiento;
    }

    public void setAprovechamiento(Aprovechamiento aprovechamiento) {
        this.aprovechamiento = aprovechamiento;
    }

    public Tipovehiculo getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Tipovehiculo idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContribucionMultaVehicular != null ? idContribucionMultaVehicular.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multavehicular)) {
            return false;
        }
        Multavehicular other = (Multavehicular) object;
        if ((this.idContribucionMultaVehicular == null && other.idContribucionMultaVehicular != null) || (this.idContribucionMultaVehicular != null && !this.idContribucionMultaVehicular.equals(other.idContribucionMultaVehicular))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Multavehicular[ idContribucionMultaVehicular=" + idContribucionMultaVehicular + " ]";
    }
    
}
