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
@Table(name = "derechos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Derechos.findAll", query = "SELECT d FROM Derechos d"),
    @NamedQuery(name = "Derechos.findByIdContribucionDerechos", query = "SELECT d FROM Derechos d WHERE d.idContribucionDerechos = :idContribucionDerechos")})
public class Derechos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_contribucion_derechos")
    private String idContribucionDerechos;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "derechos")
    private Derechogeneral derechogeneral;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "derechos")
    private Derechoslicencias derechoslicencias;
    @JoinColumn(name = "id_tipo_derecho", referencedColumnName = "id_tipo_derecho")
    @ManyToOne(optional = false)
    private CatalogoDerecho idTipoDerecho;
    @JoinColumn(name = "id_contribucion_derechos", referencedColumnName = "codigo_contribucion", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Contribucion contribucion;

    public Derechos() {
    }

    public Derechos(String idContribucionDerechos) {
        this.idContribucionDerechos = idContribucionDerechos;
    }

    public String getIdContribucionDerechos() {
        return idContribucionDerechos;
    }

    public void setIdContribucionDerechos(String idContribucionDerechos) {
        this.idContribucionDerechos = idContribucionDerechos;
    }

    public Derechogeneral getDerechogeneral() {
        return derechogeneral;
    }

    public void setDerechogeneral(Derechogeneral derechogeneral) {
        this.derechogeneral = derechogeneral;
    }

    public Derechoslicencias getDerechoslicencias() {
        return derechoslicencias;
    }

    public void setDerechoslicencias(Derechoslicencias derechoslicencias) {
        this.derechoslicencias = derechoslicencias;
    }

    public CatalogoDerecho getIdTipoDerecho() {
        return idTipoDerecho;
    }

    public void setIdTipoDerecho(CatalogoDerecho idTipoDerecho) {
        this.idTipoDerecho = idTipoDerecho;
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
        hash += (idContribucionDerechos != null ? idContribucionDerechos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Derechos)) {
            return false;
        }
        Derechos other = (Derechos) object;
        if ((this.idContribucionDerechos == null && other.idContribucionDerechos != null) || (this.idContribucionDerechos != null && !this.idContribucionDerechos.equals(other.idContribucionDerechos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Derechos[ idContribucionDerechos=" + idContribucionDerechos + " ]";
    }
    
}
