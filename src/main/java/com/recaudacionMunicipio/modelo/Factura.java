/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({ 
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByFolio", query = "SELECT f FROM Factura f WHERE f.folio = :folio"),
    @NamedQuery(name = "Factura.findByFecha", query = "SELECT f FROM Factura f WHERE f.fecha = :fecha"),
    @NamedQuery(name = "Factura.findByDescuento", query = "SELECT f FROM Factura f WHERE f.descuento = :descuento"),
    @NamedQuery(name = "Factura.findByTotal", query = "SELECT f FROM Factura f WHERE f.total = :total"),
    @NamedQuery(name = "Factura.findByContribuyenteId", query = "SELECT f FROM Factura f WHERE f.contribuyenteId = :contribuyenteId"),
    @NamedQuery(name = "Factura.findByEstadoPago", query = "SELECT f FROM Factura f WHERE f.estadoPago = :estadoPago")})
public class Factura implements Serializable {
 
    private static final long serialVersionUID = 1L; 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "folio")
    private Integer folio;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "fecha_pagado")
    @Temporal(TemporalType.DATE)
    private Date fechaPagado;
    @Column(name = "descuento")
    private Integer descuento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @Column(name = "estado_pago")
    private Boolean estadoPago;
    @JoinColumn(name = "contribuyente_id", referencedColumnName = "rfc_contribuyente")
    @ManyToOne(fetch = FetchType.LAZY)
    private Contribuyente contribuyenteId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "username")
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Usuario usuarioId;
    @OneToMany(mappedBy = "facturaId",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Contribucionfactura> contribucionfacturaList;

    @PrePersist
    public void prePersist(){
        this.fecha= new Date();
        //this.fechaPagado= new Date();
    }
    
    public Factura() {
        contribucionfacturaList =new ArrayList<>();
    }

    public Factura(Integer folio) {
        this.folio = folio;
    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getDescuento() {
        return descuento;
    }
 
    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public Double getTotal() {
        Double totales=0.0;
        for(Contribucionfactura item: contribucionfacturaList){
            totales+=item.getImporte(null);
        }
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Contribuyente getContribuyenteId() {
        return contribuyenteId;
    }

    public void setContribuyenteId(Contribuyente contribuyenteId) {
        this.contribuyenteId = contribuyenteId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Boolean getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(Boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public Date getFechaPagado() {
        return fechaPagado;
    }

    public void setFechaPagado(Date fechaPagado) {
        this.fechaPagado = fechaPagado;
    }
    
    

    @XmlTransient
    @JsonIgnore
    public List<Contribucionfactura> getContribucionfacturaList() {
        return contribucionfacturaList;
    }

    public void setContribucionfacturaList(List<Contribucionfactura> contribucionfacturaList) {
        this.contribucionfacturaList = contribucionfacturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (folio != null ? folio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.folio == null && other.folio != null) || (this.folio != null && !this.folio.equals(other.folio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Factura[ folio=" + folio + " ]";
    }
    
}
