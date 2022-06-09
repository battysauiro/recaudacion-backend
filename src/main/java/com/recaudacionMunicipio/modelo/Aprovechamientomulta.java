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
@Table(name = "aprovechamientomulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aprovechamientomulta.findAll", query = "SELECT a FROM Aprovechamientomulta a"),
    @NamedQuery(name = "Aprovechamientomulta.findByIdContribucionMulta", query = "SELECT a FROM Aprovechamientomulta a WHERE a.idContribucionMulta = :idContribucionMulta"),
    @NamedQuery(name = "Aprovechamientomulta.findByCantidad", query = "SELECT a FROM Aprovechamientomulta a WHERE a.cantidad = :cantidad")})
public class Aprovechamientomulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_multa")
    private String idContribucionMulta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @JoinColumn(name = "id_contribucion_multa", referencedColumnName = "id_contribucion_aprovechamiento", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Aprovechamiento aprovechamiento;

    public Aprovechamientomulta() {
    }

    public Aprovechamientomulta(String idContribucionMulta) {
        this.idContribucionMulta = idContribucionMulta;
    }

    public String getIdContribucionMulta() {
        return idContribucionMulta;
    }

    public void setIdContribucionMulta(String idContribucionMulta) {
        this.idContribucionMulta = idContribucionMulta;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
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
        hash += (idContribucionMulta != null ? idContribucionMulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aprovechamientomulta)) {
            return false;
        }
        Aprovechamientomulta other = (Aprovechamientomulta) object;
        if ((this.idContribucionMulta == null && other.idContribucionMulta != null) || (this.idContribucionMulta != null && !this.idContribucionMulta.equals(other.idContribucionMulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Aprovechamientomulta[ idContribucionMulta=" + idContribucionMulta + " ]";
    }
    
}
