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
@Table(name = "catalogo_derecho")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoDerecho.findAll", query = "SELECT c FROM CatalogoDerecho c"),
    @NamedQuery(name = "CatalogoDerecho.findByIdTipoDerecho", query = "SELECT c FROM CatalogoDerecho c WHERE c.idTipoDerecho = :idTipoDerecho"),
    @NamedQuery(name = "CatalogoDerecho.findByDescripcion", query = "SELECT c FROM CatalogoDerecho c WHERE c.descripcion = :descripcion")})
public class CatalogoDerecho implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_derecho")
    private Integer idTipoDerecho;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDerecho")
    private List<Derechos> derechosList;

    public CatalogoDerecho() {
    }

    public CatalogoDerecho(Integer idTipoDerecho) {
        this.idTipoDerecho = idTipoDerecho;
    }

    public Integer getIdTipoDerecho() {
        return idTipoDerecho;
    }

    public void setIdTipoDerecho(Integer idTipoDerecho) {
        this.idTipoDerecho = idTipoDerecho;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    @JsonIgnore
    public List<Derechos> getDerechosList() {
        return derechosList;
    }

    public void setDerechosList(List<Derechos> derechosList) {
        this.derechosList = derechosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDerecho != null ? idTipoDerecho.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoDerecho)) {
            return false;
        }
        CatalogoDerecho other = (CatalogoDerecho) object;
        if ((this.idTipoDerecho == null && other.idTipoDerecho != null) || (this.idTipoDerecho != null && !this.idTipoDerecho.equals(other.idTipoDerecho))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.CatalogoDerecho[ idTipoDerecho=" + idTipoDerecho + " ]";
    }
    
}
