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
@Table(name = "contribuyente_moral")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContribuyenteMoral.findAll", query = "SELECT c FROM ContribuyenteMoral c"),
    @NamedQuery(name = "ContribuyenteMoral.findByIdContribuyenteMoral", query = "SELECT c FROM ContribuyenteMoral c WHERE c.idContribuyenteMoral = :idContribuyenteMoral"),
    @NamedQuery(name = "ContribuyenteMoral.findByRazonSocialContribuyenteMoral", query = "SELECT c FROM ContribuyenteMoral c WHERE c.razonSocialContribuyenteMoral = :razonSocialContribuyenteMoral")})
public class ContribuyenteMoral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_contribuyente_moral",unique = true )
    private String idContribuyenteMoral;
    @Column(name = "razon_social_contribuyente_moral")
    private String razonSocialContribuyenteMoral;
    @JoinColumn(name = "id_contribuyente_moral", referencedColumnName = "rfc_contribuyente", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Contribuyente contribuyente;

    public ContribuyenteMoral() {
    }

    public ContribuyenteMoral(String idContribuyenteMoral) {
        this.idContribuyenteMoral = idContribuyenteMoral;
    }

    public String getIdContribuyenteMoral() {
        return idContribuyenteMoral;
    }

    public void setIdContribuyenteMoral(String idContribuyenteMoral) {
        this.idContribuyenteMoral = idContribuyenteMoral;
    }

    public String getRazonSocialContribuyenteMoral() {
        return razonSocialContribuyenteMoral;
    }

    public void setRazonSocialContribuyenteMoral(String razonSocialContribuyenteMoral) {
        this.razonSocialContribuyenteMoral = razonSocialContribuyenteMoral;
    }

    public Contribuyente getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Contribuyente contribuyente) {
        this.contribuyente = contribuyente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContribuyenteMoral != null ? idContribuyenteMoral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContribuyenteMoral)) {
            return false;
        }
        ContribuyenteMoral other = (ContribuyenteMoral) object;
        if ((this.idContribuyenteMoral == null && other.idContribuyenteMoral != null) || (this.idContribuyenteMoral != null && !this.idContribuyenteMoral.equals(other.idContribuyenteMoral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.ContribuyenteMoral[ idContribuyenteMoral=" + idContribuyenteMoral + " ]";
    }
    
}
