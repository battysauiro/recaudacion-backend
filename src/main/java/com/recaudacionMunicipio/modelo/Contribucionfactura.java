/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.modelo;

import java.io.Serializable;
import java.util.Map;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "contribucionfactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contribucionfactura.findAll", query = "SELECT c FROM Contribucionfactura c"),
    @NamedQuery(name = "Contribucionfactura.findByIdcontribucionFactura", query = "SELECT c FROM Contribucionfactura c WHERE c.idcontribucionFactura = :idcontribucionFactura"),
    @NamedQuery(name = "Contribucionfactura.findByCantidad", query = "SELECT c FROM Contribucionfactura c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "Contribucionfactura.find", query = "SELECT c FROM Contribucionfactura c WHERE c.cantidad = :cantidad")})
public class Contribucionfactura implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Basic(optional = false)
    @Column(name = "idcontribucionFactura")
    private Integer idcontribucionFactura;
    @Column(name = "cantidad")
    private Integer cantidad;
    @JoinColumn(name = "contribucion_id", referencedColumnName = "codigo_contribucion")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Contribucion contribucionId;
    @JoinColumn(name = "factura_id", referencedColumnName = "folio")
    @ManyToOne
    private Factura facturaId;

    //private Map<String, Double> respuesta;
    public Contribucionfactura() {
    }

    public Contribucionfactura(Integer idcontribucionFactura) {
        this.idcontribucionFactura = idcontribucionFactura;
    }

    public Integer getIdcontribucionFactura() {
        return idcontribucionFactura;
    }

    public void setIdcontribucionFactura(Integer idcontribucionFactura) {
        this.idcontribucionFactura = idcontribucionFactura;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Contribucion getContribucionId() {
        return contribucionId;
    }

    public void setContribucionId(Contribucion contribucionId) {
        this.contribucionId = contribucionId;
    }

    public Factura getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Factura facturaId) {
        this.facturaId = facturaId;
    }
    
    public Double getImporte(Map<String, Double> respuesta){
        /**double precio=0;
        System.out.println(contribucionId.getNivelContribucion());
        if(contribucionId.getNivelContribucion()==4){
            if(contribucionId.getIdTipoPago().getIdTipoPago()==1)
                return cantidad.doubleValue()*96.22;
            else
               return cantidad.doubleValue(); 
        }
        if(contribucionId.getNivelContribucion()==5){
             
            if(contribucionId.getIdTipoPago().getIdTipoPago()==1){
                return cantidad.doubleValue()*96.22;   
            }
            else
                return cantidad.doubleValue();
        }
        if(contribucionId.getNivelContribucion()==6){
            if(contribucionId.getIdTipoPago().getIdTipoPago()==1){
                return cantidad.doubleValue()*96.22;   
            }
            else
                return cantidad.doubleValue();
        }
        if(contribucionId.getNivelContribucion()==2){
            if(contribucionId.getIdTipoPago().getIdTipoPago()==1){
                return cantidad.doubleValue()*96.22;   
            }
            else
                return cantidad.doubleValue();
        }
        if(contribucionId.getNivelContribucion()==3){
            if(contribucionId.getIdTipoPago().getIdTipoPago()==1)
                    return cantidad.doubleValue()*96.22;  
            else
               return cantidad.doubleValue();   
            
        }
        
        if(contribucionId.getNivelContribucion()==1){
            if(contribucionId.getIdTipoPago().getIdTipoPago()==1){
                return cantidad.doubleValue()*96.22;}
            else{
               return cantidad.doubleValue(); }
        }
        if(contribucionId.getNivelContribucion()==7){
            if(contribucionId.getIdTipoPago().getIdTipoPago()==1)
                return cantidad.doubleValue()*96.22;
            else
               return cantidad.doubleValue(); 
        }
        
        return cantidad.doubleValue(); 
        * 
    }*/
        double precio=0;
     return precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontribucionFactura != null ? idcontribucionFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contribucionfactura)) {
            return false;
        }
        Contribucionfactura other = (Contribucionfactura) object;
        if ((this.idcontribucionFactura == null && other.idcontribucionFactura != null) || (this.idcontribucionFactura != null && !this.idcontribucionFactura.equals(other.idcontribucionFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.recaudacionMunicipio.modelo.Contribucionfactura[ idcontribucionFactura=" + idcontribucionFactura + " ]";
    }
    
}
