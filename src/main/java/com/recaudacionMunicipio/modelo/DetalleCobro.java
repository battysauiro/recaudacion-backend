/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "detalle_cobro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCobro.findAll", query = "SELECT d FROM DetalleCobro d"),
    @NamedQuery(name = "DetalleCobro.findByIdDetalleCobro", query = "SELECT d FROM DetalleCobro d WHERE d.idDetalleCobro = :idDetalleCobro"),
    @NamedQuery(name = "DetalleCobro.findByFechaPago", query = "SELECT d FROM DetalleCobro d WHERE d.fechaPago = :fechaPago"),
    @NamedQuery(name = "DetalleCobro.findByFolio", query = "SELECT d FROM DetalleCobro d WHERE d.folio = :folio"),
    @NamedQuery(name = "DetalleCobro.findByDescuento", query = "SELECT d FROM DetalleCobro d WHERE d.descuento = :descuento"),
    @NamedQuery(name = "DetalleCobro.findBySubtotal", query = "SELECT d FROM DetalleCobro d WHERE d.subtotal = :subtotal"),
    @NamedQuery(name = "DetalleCobro.findByTotal", query = "SELECT d FROM DetalleCobro d WHERE d.total = :total")})
public class DetalleCobro implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle_cobro")
    private Integer idDetalleCobro;
    @Basic(optional = false)
    @Column(name = "fecha_pago")
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    @Basic(optional = false)
    @Column(name = "folio")
    private String folio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "descuento")
    private Float descuento;
    @Column(name = "subtotal")
    private Float subtotal;
    @Column(name = "total")
    private Float total;
    @JoinColumn(name = "contribucion_id_contribucion", referencedColumnName = "codigo_contribucion")
    @ManyToOne(optional = false)
    private Contribucion contribucionIdContribucion;
    @JoinColumn(name = "contribuyente_id_contribuyente", referencedColumnName = "rfc_contribuyente")
    @ManyToOne(optional = false)
    private Contribuyente contribuyenteIdContribuyente;
    @JoinColumn(name = "usuario_id_usuario", referencedColumnName = "username")
    @ManyToOne(optional = false)
    private Usuario usuarioIdUsuario;

    public DetalleCobro() {
}

    public DetalleCobro(Integer idDetalleCobro) {
        this.idDetalleCobro = idDetalleCobro;
    }

    public DetalleCobro(Integer idDetalleCobro, Date fechaPago, String folio) {
        this.idDetalleCobro = idDetalleCobro;
        this.fechaPago = fechaPago;
        this.folio = folio;
    }

    public Integer getIdDetalleCobro() {
        return idDetalleCobro;
    }

    public void setIdDetalleCobro(Integer idDetalleCobro) {
        this.idDetalleCobro = idDetalleCobro;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Float getDescuento() {
        return descuento;
    }

    public void setDescuento(Float descuento) {
        this.descuento = descuento;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Contribucion getContribucionIdContribucion() {
        return contribucionIdContribucion;
    }

    public void setContribucionIdContribucion(Contribucion contribucionIdContribucion) {
        this.contribucionIdContribucion = contribucionIdContribucion;
    }

    public Contribuyente getContribuyenteIdContribuyente() {
        return contribuyenteIdContribuyente;
    }

    public void setContribuyenteIdContribuyente(Contribuyente contribuyenteIdContribuyente) {
        this.contribuyenteIdContribuyente = contribuyenteIdContribuyente;
    }

    public Usuario getUsuarioIdUsuario() {
        return usuarioIdUsuario;
    }

    public void setUsuarioIdUsuario(Usuario usuarioIdUsuario) {
        this.usuarioIdUsuario = usuarioIdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalleCobro != null ? idDetalleCobro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCobro)) {
            return false;
        }
        DetalleCobro other = (DetalleCobro) object;
        if ((this.idDetalleCobro == null && other.idDetalleCobro != null) || (this.idDetalleCobro != null && !this.idDetalleCobro.equals(other.idDetalleCobro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.DetalleCobro[ idDetalleCobro=" + idDetalleCobro + " ]";
    }
    
}
