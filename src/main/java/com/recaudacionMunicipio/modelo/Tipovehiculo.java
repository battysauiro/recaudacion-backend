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
@Table(name = "tipovehiculo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipovehiculo.findAll", query = "SELECT t FROM Tipovehiculo t"),
    @NamedQuery(name = "Tipovehiculo.findByIdTipoVehiculo", query = "SELECT t FROM Tipovehiculo t WHERE t.idTipoVehiculo = :idTipoVehiculo"),
    @NamedQuery(name = "Tipovehiculo.findByNombreVehiculo", query = "SELECT t FROM Tipovehiculo t WHERE t.nombreVehiculo = :nombreVehiculo")})
public class Tipovehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_vehiculo")
    private Integer idTipoVehiculo;
    @Column(name = "nombre_vehiculo")
    private String nombreVehiculo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoVehiculo")
    private List<Multavehicular> multavehicularList;

    public Tipovehiculo() {
    }

    public Tipovehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public Integer getIdTipoVehiculo() {
        return idTipoVehiculo;
    }

    public void setIdTipoVehiculo(Integer idTipoVehiculo) {
        this.idTipoVehiculo = idTipoVehiculo;
    }

    public String getNombreVehiculo() {
        return nombreVehiculo;
    }

    public void setNombreVehiculo(String nombreVehiculo) {
        this.nombreVehiculo = nombreVehiculo;
    }

    @XmlTransient
    @JsonIgnore
    public List<Multavehicular> getMultavehicularList() {
        return multavehicularList;
    }

    public void setMultavehicularList(List<Multavehicular> multavehicularList) {
        this.multavehicularList = multavehicularList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoVehiculo != null ? idTipoVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipovehiculo)) {
            return false;
        }
        Tipovehiculo other = (Tipovehiculo) object;
        if ((this.idTipoVehiculo == null && other.idTipoVehiculo != null) || (this.idTipoVehiculo != null && !this.idTipoVehiculo.equals(other.idTipoVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Tipovehiculo[ idTipoVehiculo=" + idTipoVehiculo + " ]";
    }
    
}
