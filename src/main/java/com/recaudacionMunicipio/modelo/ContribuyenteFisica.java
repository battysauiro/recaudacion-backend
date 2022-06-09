/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "contribuyente_fisica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ContribuyenteFisica.findAll", query = "SELECT c FROM ContribuyenteFisica c"),
    @NamedQuery(name = "ContribuyenteFisica.findByIdContribuyenteFisica", query = "SELECT c FROM ContribuyenteFisica c WHERE c.idContribuyenteFisica = :idContribuyenteFisica"),
    @NamedQuery(name = "ContribuyenteFisica.findByCurpContribuyenteFisica", query = "SELECT c FROM ContribuyenteFisica c WHERE c.curpContribuyenteFisica = :curpContribuyenteFisica"),
    @NamedQuery(name = "ContribuyenteFisica.findByNombreContribuyenteFisica", query = "SELECT c FROM ContribuyenteFisica c WHERE c.nombreContribuyenteFisica = :nombreContribuyenteFisica"),
    @NamedQuery(name = "ContribuyenteFisica.findByApellidoPContribuyenteFisica", query = "SELECT c FROM ContribuyenteFisica c WHERE c.apellidoPContribuyenteFisica = :apellidoPContribuyenteFisica"),
    @NamedQuery(name = "ContribuyenteFisica.findByApellidoMContribuyenteFisica", query = "SELECT c FROM ContribuyenteFisica c WHERE c.apellidoMContribuyenteFisica = :apellidoMContribuyenteFisica"),
    @NamedQuery(name = "ContribuyenteFisica.findByFechaNacimiento", query = "SELECT c FROM ContribuyenteFisica c WHERE c.fechaNacimiento = :fechaNacimiento")})
public class ContribuyenteFisica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_contribuyente_fisica",unique = true)
    private String idContribuyenteFisica;
    @Column(name = "curp_contribuyente_fisica",unique = true)
    private String curpContribuyenteFisica;
    @Column(name = "nombre_contribuyente_fisica")
    private String nombreContribuyenteFisica;
    @Column(name = "apellido_p_contribuyente_fisica")
    private String apellidoPContribuyenteFisica;
    @Column(name = "apellido_m_contribuyente_fisica")
    private String apellidoMContribuyenteFisica;
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @JoinColumn(name = "id_contribuyente_fisica", referencedColumnName = "rfc_contribuyente", insertable = false, updatable = false)
    @OneToOne(optional = false,cascade = CascadeType.ALL)
    private Contribuyente contribuyente;

    public ContribuyenteFisica() {
    }

    public ContribuyenteFisica(String idContribuyenteFisica) {
        this.idContribuyenteFisica = idContribuyenteFisica;
    }

    public ContribuyenteFisica(String idContribuyenteFisica, Date fechaNacimiento) {
        this.idContribuyenteFisica = idContribuyenteFisica;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdContribuyenteFisica() {
        return idContribuyenteFisica;
    }

    public void setIdContribuyenteFisica(String idContribuyenteFisica) {
        this.idContribuyenteFisica = idContribuyenteFisica;
    }

    public String getCurpContribuyenteFisica() {
        return curpContribuyenteFisica;
    }

    public void setCurpContribuyenteFisica(String curpContribuyenteFisica) {
        this.curpContribuyenteFisica = curpContribuyenteFisica;
    }

    public String getNombreContribuyenteFisica() {
        return nombreContribuyenteFisica;
    }

    public void setNombreContribuyenteFisica(String nombreContribuyenteFisica) {
        this.nombreContribuyenteFisica = nombreContribuyenteFisica;
    }

    public String getApellidoPContribuyenteFisica() {
        return apellidoPContribuyenteFisica;
    }

    public void setApellidoPContribuyenteFisica(String apellidoPContribuyenteFisica) {
        this.apellidoPContribuyenteFisica = apellidoPContribuyenteFisica;
    }

    public String getApellidoMContribuyenteFisica() {
        return apellidoMContribuyenteFisica;
    }

    public void setApellidoMContribuyenteFisica(String apellidoMContribuyenteFisica) {
        this.apellidoMContribuyenteFisica = apellidoMContribuyenteFisica;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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
        hash += (idContribuyenteFisica != null ? idContribuyenteFisica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContribuyenteFisica)) {
            return false;
        }
        ContribuyenteFisica other = (ContribuyenteFisica) object;
        if ((this.idContribuyenteFisica == null && other.idContribuyenteFisica != null) || (this.idContribuyenteFisica != null && !this.idContribuyenteFisica.equals(other.idContribuyenteFisica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.ContribuyenteFisica[ idContribuyenteFisica=" + idContribuyenteFisica + " ]";
    }
    
}
