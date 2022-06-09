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
@Table(name = "catalogo_impuesto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoImpuesto.findAll", query = "SELECT c FROM CatalogoImpuesto c"),
    @NamedQuery(name = "CatalogoImpuesto.findByIdTipoImpuesto", query = "SELECT c FROM CatalogoImpuesto c WHERE c.idTipoImpuesto = :idTipoImpuesto"),
    @NamedQuery(name = "CatalogoImpuesto.findByDescripcion", query = "SELECT c FROM CatalogoImpuesto c WHERE c.descripcion = :descripcion")})
public class CatalogoImpuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_impuesto")
    private Integer idTipoImpuesto;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoImpuesto")
    private List<Impuesto> impuestoList;

    public CatalogoImpuesto() {
    }

    public CatalogoImpuesto(Integer idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }

    public Integer getIdTipoImpuesto() {
        return idTipoImpuesto;
    }

    public void setIdTipoImpuesto(Integer idTipoImpuesto) {
        this.idTipoImpuesto = idTipoImpuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    @JsonIgnore
    public List<Impuesto> getImpuestoList() {
        return impuestoList;
    }

    public void setImpuestoList(List<Impuesto> impuestoList) {
        this.impuestoList = impuestoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoImpuesto != null ? idTipoImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoImpuesto)) {
            return false;
        }
        CatalogoImpuesto other = (CatalogoImpuesto) object;
        if ((this.idTipoImpuesto == null && other.idTipoImpuesto != null) || (this.idTipoImpuesto != null && !this.idTipoImpuesto.equals(other.idTipoImpuesto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.CatalogoImpuesto[ idTipoImpuesto=" + idTipoImpuesto + " ]";
    }
    
}
