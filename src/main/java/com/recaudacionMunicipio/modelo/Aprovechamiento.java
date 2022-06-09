/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "aprovechamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aprovechamiento.findAll", query = "SELECT a FROM Aprovechamiento a"),
    @NamedQuery(name = "Aprovechamiento.findByIdContribucionAprovechamiento", query = "SELECT a FROM Aprovechamiento a WHERE a.idContribucionAprovechamiento = :idContribucionAprovechamiento")})
public class Aprovechamiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_aprovechamiento")
    private String idContribucionAprovechamiento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "aprovechamiento")
    @JsonIgnore
    private Multavehicular multavehicular;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "aprovechamiento")
    @JsonIgnore
    private Multaebriedad multaebriedad;
    @JoinColumn(name = "id_tipo_aprovechamiento", referencedColumnName = "id_tipo_aprovechamiento")
    @ManyToOne(optional = false)
    private CatalogoAprovechamiento idTipoAprovechamiento;
    @JoinColumn(name = "id_contribucion_aprovechamiento", referencedColumnName = "codigo_contribucion", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Contribucion contribucion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "aprovechamiento")
    @JsonIgnore
    private Aprovechamientomulta aprovechamientomulta;

    public Aprovechamiento() {
    }

    public Aprovechamiento(String idContribucionAprovechamiento) {
        this.idContribucionAprovechamiento = idContribucionAprovechamiento;
    }

    public String getIdContribucionAprovechamiento() {
        return idContribucionAprovechamiento;
    }

    public void setIdContribucionAprovechamiento(String idContribucionAprovechamiento) {
        this.idContribucionAprovechamiento = idContribucionAprovechamiento;
    }

    public Multavehicular getMultavehicular() {
        return multavehicular;
    }

    public void setMultavehicular(Multavehicular multavehicular) {
        this.multavehicular = multavehicular;
    }

    public Multaebriedad getMultaebriedad() {
        return multaebriedad;
    }

    public void setMultaebriedad(Multaebriedad multaebriedad) {
        this.multaebriedad = multaebriedad;
    }

    public CatalogoAprovechamiento getIdTipoAprovechamiento() {
        return idTipoAprovechamiento;
    }

    public void setIdTipoAprovechamiento(CatalogoAprovechamiento idTipoAprovechamiento) {
        this.idTipoAprovechamiento = idTipoAprovechamiento;
    }

    public Contribucion getContribucion() {
        return contribucion;
    }

    public void setContribucion(Contribucion contribucion) {
        this.contribucion = contribucion;
    }

    public Aprovechamientomulta getAprovechamientomulta() {
        return aprovechamientomulta;
    }

    public void setAprovechamientomulta(Aprovechamientomulta aprovechamientomulta) {
        this.aprovechamientomulta = aprovechamientomulta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContribucionAprovechamiento != null ? idContribucionAprovechamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aprovechamiento)) {
            return false;
        }
        Aprovechamiento other = (Aprovechamiento) object;
        if ((this.idContribucionAprovechamiento == null && other.idContribucionAprovechamiento != null) || (this.idContribucionAprovechamiento != null && !this.idContribucionAprovechamiento.equals(other.idContribucionAprovechamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Aprovechamiento[ idContribucionAprovechamiento=" + idContribucionAprovechamiento + " ]";
    }
    
}
