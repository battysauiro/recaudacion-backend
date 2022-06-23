/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "contribuyente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contribuyente.findAll", query = "SELECT c FROM Contribuyente c"),
    @NamedQuery(name = "Contribuyente.findByRfcContribuyente", query = "SELECT c FROM Contribuyente c WHERE c.rfcContribuyente = :rfcContribuyente"),
    @NamedQuery(name = "Contribuyente.findByCalle", query = "SELECT c FROM Contribuyente c WHERE c.calle = :calle"),
    @NamedQuery(name = "Contribuyente.findByNumero", query = "SELECT c FROM Contribuyente c WHERE c.numero = :numero"),
    @NamedQuery(name = "Contribuyente.findByColonia", query = "SELECT c FROM Contribuyente c WHERE c.colonia = :colonia"),
    @NamedQuery(name = "Contribuyente.findByCp", query = "SELECT c FROM Contribuyente c WHERE c.cp = :cp")})
public class Contribuyente implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rfc_contribuyente",unique = true)
    private String rfcContribuyente;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero")
    private String numero;
    @Column(name = "colonia")
    private String colonia;
    @Column(name = "cp")
    private String cp;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contribuyente")
    private ContribuyenteFisica contribuyenteFisica;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contribuyente")
    private ContribuyenteMoral contribuyenteMoral;
    @JsonIgnoreProperties({"contribuyenteId"})
    @OneToMany(mappedBy = "contribuyenteId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Factura> facturaList;

    public Contribuyente() {
        this.facturaList = new ArrayList<>();
    }

    public Contribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getRfcContribuyente() {
        return rfcContribuyente;
    }

    public void setRfcContribuyente(String rfcContribuyente) {
        this.rfcContribuyente = rfcContribuyente;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
    
    @XmlTransient
    @JsonIgnore
    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public ContribuyenteFisica getContribuyenteFisica() {
        return contribuyenteFisica;
    }

    public void setContribuyenteFisica(ContribuyenteFisica contribuyenteFisica) {
        this.contribuyenteFisica = contribuyenteFisica;
    }

    public ContribuyenteMoral getContribuyenteMoral() {
        return contribuyenteMoral;
    }

    public void setContribuyenteMoral(ContribuyenteMoral contribuyenteMoral) {
        this.contribuyenteMoral = contribuyenteMoral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rfcContribuyente != null ? rfcContribuyente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contribuyente)) {
            return false;
        }
        Contribuyente other = (Contribuyente) object;
        if ((this.rfcContribuyente == null && other.rfcContribuyente != null) || (this.rfcContribuyente != null && !this.rfcContribuyente.equals(other.rfcContribuyente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Contribuyente[ rfcContribuyente=" + rfcContribuyente + " ]";
    }

    
    
    
}
