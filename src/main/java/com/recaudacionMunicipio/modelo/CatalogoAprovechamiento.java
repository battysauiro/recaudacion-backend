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
@Table(name = "catalogo_aprovechamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CatalogoAprovechamiento.findAll", query = "SELECT c FROM CatalogoAprovechamiento c"),
    @NamedQuery(name = "CatalogoAprovechamiento.findByIdTipoAprovechamiento", query = "SELECT c FROM CatalogoAprovechamiento c WHERE c.idTipoAprovechamiento = :idTipoAprovechamiento"),
    @NamedQuery(name = "CatalogoAprovechamiento.findByDescripcion", query = "SELECT c FROM CatalogoAprovechamiento c WHERE c.descripcion = :descripcion")})
public class CatalogoAprovechamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_aprovechamiento")
    private Integer idTipoAprovechamiento;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoAprovechamiento")
    private List<Aprovechamiento> aprovechamientoList;

    public CatalogoAprovechamiento() {
    }

    public CatalogoAprovechamiento(Integer idTipoAprovechamiento) {
        this.idTipoAprovechamiento = idTipoAprovechamiento;
    }

    public Integer getIdTipoAprovechamiento() {
        return idTipoAprovechamiento;
    }

    public void setIdTipoAprovechamiento(Integer idTipoAprovechamiento) {
        this.idTipoAprovechamiento = idTipoAprovechamiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    @JsonIgnore
    public List<Aprovechamiento> getAprovechamientoList() {
        return aprovechamientoList;
    }

    public void setAprovechamientoList(List<Aprovechamiento> aprovechamientoList) {
        this.aprovechamientoList = aprovechamientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoAprovechamiento != null ? idTipoAprovechamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CatalogoAprovechamiento)) {
            return false;
        }
        CatalogoAprovechamiento other = (CatalogoAprovechamiento) object;
        if ((this.idTipoAprovechamiento == null && other.idTipoAprovechamiento != null) || (this.idTipoAprovechamiento != null && !this.idTipoAprovechamiento.equals(other.idTipoAprovechamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.CatalogoAprovechamiento[ idTipoAprovechamiento=" + idTipoAprovechamiento + " ]";
    }
    
}
