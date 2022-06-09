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
@Table(name = "catalogo_descripcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoDescripcion.findAll", query = "SELECT c FROM CatalogoDescripcion c"),
    @NamedQuery(name = "CatalogoDescripcion.findByIdDescripcion", query = "SELECT c FROM CatalogoDescripcion c WHERE c.idDescripcion = :idDescripcion"),
    @NamedQuery(name = "CatalogoDescripcion.findByDescripcion", query = "SELECT c FROM CatalogoDescripcion c WHERE c.descripcion = :descripcion")})
public class CatalogoDescripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_descripcion")
    private Integer idDescripcion;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDescripcion")
    private List<Contribucion> contribucionList;

    public CatalogoDescripcion() {
    }

    public CatalogoDescripcion(Integer idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public Integer getIdDescripcion() {
        return idDescripcion;
    }

    public void setIdDescripcion(Integer idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    @JsonIgnore
    public List<Contribucion> getContribucionList() {
        return contribucionList;
    }

    public void setContribucionList(List<Contribucion> contribucionList) {
        this.contribucionList = contribucionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDescripcion != null ? idDescripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoDescripcion)) {
            return false;
        }
        CatalogoDescripcion other = (CatalogoDescripcion) object;
        if ((this.idDescripcion == null && other.idDescripcion != null) || (this.idDescripcion != null && !this.idDescripcion.equals(other.idDescripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.CatalogoDescripcion[ idDescripcion=" + idDescripcion + " ]";
    }
    
}
