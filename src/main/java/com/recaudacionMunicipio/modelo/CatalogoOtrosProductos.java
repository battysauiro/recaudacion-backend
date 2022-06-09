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
@Table(name = "catalogo_otros_productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoOtrosProductos.findAll", query = "SELECT c FROM CatalogoOtrosProductos c"),
    @NamedQuery(name = "CatalogoOtrosProductos.findByIdOtrosProductos", query = "SELECT c FROM CatalogoOtrosProductos c WHERE c.idOtrosProductos = :idOtrosProductos"),
    @NamedQuery(name = "CatalogoOtrosProductos.findByDescripcion", query = "SELECT c FROM CatalogoOtrosProductos c WHERE c.descripcion = :descripcion")})
public class CatalogoOtrosProductos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_otros_productos")
    private Integer idOtrosProductos;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOtrosProductos")
    private List<Otrosproductos> otrosproductosList;

    public CatalogoOtrosProductos() {
    }

    public CatalogoOtrosProductos(Integer idOtrosProductos) {
        this.idOtrosProductos = idOtrosProductos;
    }

    public Integer getIdOtrosProductos() {
        return idOtrosProductos;
    }

    public void setIdOtrosProductos(Integer idOtrosProductos) {
        this.idOtrosProductos = idOtrosProductos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idOtrosProductos != null ? idOtrosProductos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoOtrosProductos)) {
            return false;
        }
        CatalogoOtrosProductos other = (CatalogoOtrosProductos) object;
        if ((this.idOtrosProductos == null && other.idOtrosProductos != null) || (this.idOtrosProductos != null && !this.idOtrosProductos.equals(other.idOtrosProductos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.CatalogoOtrosProductos[ idOtrosProductos=" + idOtrosProductos + " ]";
    }
    
}
