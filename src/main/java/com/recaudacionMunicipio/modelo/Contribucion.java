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
@Table(name = "contribucion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contribucion.findAll", query = "SELECT c FROM Contribucion c"),
    @NamedQuery(name = "Contribucion.findByCodigoContribucion", query = "SELECT c FROM Contribucion c WHERE c.codigoContribucion = :codigoContribucion"),
    @NamedQuery(name = "Contribucion.findByConceptoContribucion", query = "SELECT c FROM Contribucion c WHERE c.conceptoContribucion = :conceptoContribucion")})
public class Contribucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_contribucion")
    private String codigoContribucion;
    @Column(name = "concepto_contribucion")
    private String conceptoContribucion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contribucionIdContribucion")
    private List<DetalleCobro> detalleCobroList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contribucion")
    private Derechos derechos;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contribucion")
    private Impuesto impuesto;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contribucion")
    private Aprovechamiento aprovechamiento;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contribucion")
    private Otrosproductos otrosproductos;
    @JoinColumn(name = "id_descripcion", referencedColumnName = "id_descripcion")
    @ManyToOne(optional = false)
    private CatalogoDescripcion idDescripcion;
    @JoinColumn(name = "id_tipo_pago", referencedColumnName = "id_tipo_pago")
    @ManyToOne(optional = false)
    private TipoPago idTipoPago;

    public Contribucion() {
    }

    public Contribucion(String codigoContribucion) {
        this.codigoContribucion = codigoContribucion;
    }

    public String getCodigoContribucion() {
        return codigoContribucion;
    }

    public void setCodigoContribucion(String codigoContribucion) {
        this.codigoContribucion = codigoContribucion;
    }

    public String getConceptoContribucion() {
        return conceptoContribucion;
    }

    public void setConceptoContribucion(String conceptoContribucion) {
        this.conceptoContribucion = conceptoContribucion;
    }

    @XmlTransient
    @JsonIgnore
    public List<DetalleCobro> getDetalleCobroList() {
        return detalleCobroList;
    }

    public void setDetalleCobroList(List<DetalleCobro> detalleCobroList) {
        this.detalleCobroList = detalleCobroList;
    }

    public Derechos getDerechos() {
        return derechos;
    }

    public void setDerechos(Derechos derechos) {
        this.derechos = derechos;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public Aprovechamiento getAprovechamiento() {
        return aprovechamiento;
    }

    public void setAprovechamiento(Aprovechamiento aprovechamiento) {
        this.aprovechamiento = aprovechamiento;
    }

    public Otrosproductos getOtrosproductos() {
        return otrosproductos;
    }

    public void setOtrosproductos(Otrosproductos otrosproductos) {
        this.otrosproductos = otrosproductos;
    }

    public CatalogoDescripcion getIdDescripcion() {
        return idDescripcion;
    }

    public void setIdDescripcion(CatalogoDescripcion idDescripcion) {
        this.idDescripcion = idDescripcion;
    }

    public TipoPago getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(TipoPago idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoContribucion != null ? codigoContribucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contribucion)) {
            return false;
        }
        Contribucion other = (Contribucion) object;
        if ((this.codigoContribucion == null && other.codigoContribucion != null) || (this.codigoContribucion != null && !this.codigoContribucion.equals(other.codigoContribucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Contribucion[ codigoContribucion=" + codigoContribucion + " ]";
    }
    
}
