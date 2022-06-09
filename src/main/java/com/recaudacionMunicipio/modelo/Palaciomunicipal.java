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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "palaciomunicipal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Palaciomunicipal.findAll", query = "SELECT p FROM Palaciomunicipal p"),
    @NamedQuery(name = "Palaciomunicipal.findByIdPalacio", query = "SELECT p FROM Palaciomunicipal p WHERE p.idPalacio = :idPalacio"),
    @NamedQuery(name = "Palaciomunicipal.findByTelefono", query = "SELECT p FROM Palaciomunicipal p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Palaciomunicipal.findByImagen", query = "SELECT p FROM Palaciomunicipal p WHERE p.imagen = :imagen")})
public class Palaciomunicipal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_palacio")
    private Integer idPalacio;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "imagen")
    private String imagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPalacio")
    private List<Empleado> empleadoList;
    @JoinColumn(name = "id_municipio", referencedColumnName = "clave")
    @ManyToOne(optional = false)
    private Municipios idMunicipio;

    public Palaciomunicipal() {
    }

    public Palaciomunicipal(Integer idPalacio) {
        this.idPalacio = idPalacio;
    }

    public Integer getIdPalacio() {
        return idPalacio;
    }

    public void setIdPalacio(Integer idPalacio) {
        this.idPalacio = idPalacio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    @JsonIgnore
    public List<Empleado> getEmpleadoList() {
        return empleadoList;
    }

    public void setEmpleadoList(List<Empleado> empleadoList) {
        this.empleadoList = empleadoList;
    }

    public Municipios getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Municipios idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPalacio != null ? idPalacio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Palaciomunicipal)) {
            return false;
        }
        Palaciomunicipal other = (Palaciomunicipal) object;
        if ((this.idPalacio == null && other.idPalacio != null) || (this.idPalacio != null && !this.idPalacio.equals(other.idPalacio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Palaciomunicipal[ idPalacio=" + idPalacio + " ]";
    }
    
}
