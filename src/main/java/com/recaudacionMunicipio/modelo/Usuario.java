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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
    @NamedQuery(name = "Usuario.findByPuestoUsuario", query = "SELECT u FROM Usuario u WHERE u.puestoUsuario = :puestoUsuario"),
    @NamedQuery(name = "Usuario.findByEstadoUsuario", query = "SELECT u FROM Usuario u WHERE u.estadoUsuario = :estadoUsuario"),
    @NamedQuery(name = "Usuario.findByUrlImagenUsuario", query = "SELECT u FROM Usuario u WHERE u.urlImagenUsuario = :urlImagenUsuario")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Column(name = "puesto_usuario")
    private String puestoUsuario;
    @Column(name = "estado_usuario")
    private Boolean estadoUsuario;
    @Column(name = "url_imagen_usuario")
    private String urlImagenUsuario;
   
    @ManyToMany()
    private List<Roles> rolesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioIdUsuario")
    private List<DetalleCobro> detalleCobroList;
    @JoinColumn(name = "id_empleado", referencedColumnName = "curp")
    @ManyToOne(optional = false)
    private Empleado idEmpleado;

    public Usuario() {
    }

    public Usuario(String username) {
        this.username = username;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPuestoUsuario() {
        return puestoUsuario;
    }

    public void setPuestoUsuario(String puestoUsuario) {
        this.puestoUsuario = puestoUsuario;
    }

    public Boolean getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getUrlImagenUsuario() {
        return urlImagenUsuario;
    }

    public void setUrlImagenUsuario(String urlImagenUsuario) {
        this.urlImagenUsuario = urlImagenUsuario;
    }

    

    @XmlTransient
    @JsonIgnore
    public List<Roles> getRolesList1() {
        return rolesList;
    }

    public void setRolesList1(List<Roles> rolesList1) {
        this.rolesList = rolesList1;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleCobro> getDetalleCobroList() {
        return detalleCobroList;
    }

    public void setDetalleCobroList(List<DetalleCobro> detalleCobroList) {
        this.detalleCobroList = detalleCobroList;
    }

    public Empleado getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Usuario[ username=" + username + " ]";
    }
    
}
