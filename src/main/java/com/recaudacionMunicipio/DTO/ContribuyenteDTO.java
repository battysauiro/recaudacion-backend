/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO;

import java.util.List;

/**
 *
 * @author Oscar
 */
public class ContribuyenteDTO {
    
    //private int id_contribuyente;
    private String rfc_contribuyente;
    private String calle;
    private String numero;
    private String colonia;
    private String codigo_postal;
    private List<FacturaDTO> facturas;
    public ContribuyenteDTO() {
}

    public ContribuyenteDTO(String rfc_contribuyente, String calle, String numero, String colonia, String codigo_postal, List<FacturaDTO> facturas) {
        this.rfc_contribuyente = rfc_contribuyente;
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.codigo_postal = codigo_postal;
        this.facturas = facturas;
    }

   

    /** public int getId_contribuyente() {
        return id_contribuyente;
    }

    public void setId_contribuyente(int id_contribuyente) {
        this.id_contribuyente = id_contribuyente;
    } */

    public String getRfc_contribuyente() {
        return rfc_contribuyente;
    }

    public void setRfc_contribuyente(String rfc_contribuyente) {
        this.rfc_contribuyente = rfc_contribuyente;
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

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }
    
    
}
