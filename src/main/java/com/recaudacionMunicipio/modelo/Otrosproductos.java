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
@Table(name = "otrosproductos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Otrosproductos.findAll", query = "SELECT o FROM Otrosproductos o"),
    @NamedQuery(name = "Otrosproductos.findByIdContribucionProductos", query = "SELECT o FROM Otrosproductos o WHERE o.idContribucionProductos = :idContribucionProductos"),
    @NamedQuery(name = "Otrosproductos.findByCantidad", query = "SELECT o FROM Otrosproductos o WHERE o.cantidad = :cantidad")})
public class Otrosproductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_productos")
    private String idContribucionProductos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Float cantidad;
    @JoinColumn(name = "id_otros_productos", referencedColumnName = "id_otros_productos")
    @ManyToOne(optional = false)
    private CatalogoOtrosProductos idOtrosProductos;
    @JoinColumn(name = "id_contribucion_productos", referencedColumnName = "codigo_contribucion", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Contribucion contribucion;
    @JoinColumn(name = "periodicidad", referencedColumnName = "id_periodicidad")
    @ManyToOne(optional = false)
    private Periodicidad periodicidad;

    public Otrosproductos() {
    }

    public Otrosproductos(String idContribucionProductos) {
        this.idContribucionProductos = idContribucionProductos;
    }

    public String getIdContribucionProductos() {
        return idContribucionProductos;
    }

    public void setIdContribucionProductos(String idContribucionProductos) {
        this.idContribucionProductos = idContribucionProductos;
    }

    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public CatalogoOtrosProductos getIdOtrosProductos() {
        return idOtrosProductos;
    }

    public void setIdOtrosProductos(CatalogoOtrosProductos idOtrosProductos) {
        this.idOtrosProductos = idOtrosProductos;
    }

    public Contribucion getContribucion() {
        return contribucion;
    }

    public void setContribucion(Contribucion contribucion) {
        this.contribucion = contribucion;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContribucionProductos != null ? idContribucionProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Otrosproductos)) {
            return false;
        }
        Otrosproductos other = (Otrosproductos) object;
        if ((this.idContribucionProductos == null && other.idContribucionProductos != null) || (this.idContribucionProductos != null && !this.idContribucionProductos.equals(other.idContribucionProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Otrosproductos[ idContribucionProductos=" + idContribucionProductos + " ]";
    }
    
}
