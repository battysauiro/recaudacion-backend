/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "periodicidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodicidad.findAll", query = "SELECT p FROM Periodicidad p"),
    @NamedQuery(name = "Periodicidad.findByIdPeriodicidad", query = "SELECT p FROM Periodicidad p WHERE p.idPeriodicidad = :idPeriodicidad"),
    @NamedQuery(name = "Periodicidad.findByNombrePeriodicidad", query = "SELECT p FROM Periodicidad p WHERE p.nombrePeriodicidad = :nombrePeriodicidad")})
public class Periodicidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodicidad")
    private Integer idPeriodicidad;
    @Column(name = "nombre_periodicidad")
    private String nombrePeriodicidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodicidad")
    private List<Derechogeneral> derechogeneralList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodicidad")
    private List<Otrosproductos> otrosproductosList;

    public Periodicidad() {
    }

    public Periodicidad(Integer idPeriodicidad) {
        this.idPeriodicidad = idPeriodicidad;
    }

    public Integer getIdPeriodicidad() {
        return idPeriodicidad;
    }

    public void setIdPeriodicidad(Integer idPeriodicidad) {
        this.idPeriodicidad = idPeriodicidad;
    }

    public String getNombrePeriodicidad() {
        return nombrePeriodicidad;
    }

    public void setNombrePeriodicidad(String nombrePeriodicidad) {
        this.nombrePeriodicidad = nombrePeriodicidad;
    }

    @XmlTransient
    @JsonIgnore
    public List<Derechogeneral> getDerechogeneralList() {
        return derechogeneralList;
    }

    public void setDerechogeneralList(List<Derechogeneral> derechogeneralList) {
        this.derechogeneralList = derechogeneralList;
    }

    @XmlTransient
    @JsonIgnore
    public List<Otrosproductos> getOtrosproductosList() {
        return otrosproductosList;
    }

    public void setOtrosproductosList(List<Otrosproductos> otrosproductosList) {
        this.otrosproductosList = otrosproductosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodicidad != null ? idPeriodicidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodicidad)) {
            return false;
        }
        Periodicidad other = (Periodicidad) object;
        if ((this.idPeriodicidad == null && other.idPeriodicidad != null) || (this.idPeriodicidad != null && !this.idPeriodicidad.equals(other.idPeriodicidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Periodicidad[ idPeriodicidad=" + idPeriodicidad + " ]";
    }
    
}
