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
@Table(name = "impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i"),
    @NamedQuery(name = "Impuesto.findByIdContribucionImpuesto", query = "SELECT i FROM Impuesto i WHERE i.idContribucionImpuesto = :idContribucionImpuesto"),
    @NamedQuery(name = "Impuesto.findByCantidad", query = "SELECT i FROM Impuesto i WHERE i.cantidad = :cantidad")})
public class Impuesto implements Serializable {
        //SELECT i FROM Impuesto i join fetch i.contribucion where UPPER(i.idContribucionImpuesto)  = :idContribucionImpuesto or UPPER(i.contribucion.conceptoContribucion)=:idContribucionImpuesto
    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_impuesto")
    private String idContribucionImpuesto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @JoinColumn(name = "id_tipo_impuesto", referencedColumnName = "id_tipo_impuesto")
    @ManyToOne(optional = false)
    private CatalogoImpuesto idTipoImpuesto;
    @JoinColumn(name = "id_contribucion_impuesto", referencedColumnName = "codigo_contribucion", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Contribucion contribucion;

    public Impuesto() {
    }

    public Impuesto(String idContribucionImpuesto) {
        this.idContribucionImpuesto = idContribucionImpuesto;
    }

    public String getIdContribucionImpuesto() {
        return idContribucionImpuesto;
    }

    public void setIdContribucionImpuesto(String idContribucionImpuesto) {
        this.idContribucionImpuesto = idContribucionImpuesto;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public CatalogoImpuesto getIdTipoImpuesto() {
        return idTipoImpuesto;
    }

    public void setIdTipoImpuesto(CatalogoImpuesto idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }

    public Contribucion getContribucion() {
        return contribucion;
    }

    public void setContribucion(Contribucion contribucion) {
        this.contribucion = contribucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContribucionImpuesto != null ? idContribucionImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        if ((this.idContribucionImpuesto == null && other.idContribucionImpuesto != null) || (this.idContribucionImpuesto != null && !this.idContribucionImpuesto.equals(other.idContribucionImpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Impuesto[ idContribucionImpuesto=" + idContribucionImpuesto + " ]";
    }
    
}
