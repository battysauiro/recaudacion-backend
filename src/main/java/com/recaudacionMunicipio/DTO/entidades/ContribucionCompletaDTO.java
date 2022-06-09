/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO.entidades;

/**
 *
 * @author Oscar
 */
public class ContribucionCompletaDTO {
    
    private String codigo_contribucion;
    private String concepto_contribucion; 
    private String tipo_pago;
    private String descripcion;


    public ContribucionCompletaDTO(String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        this.codigo_contribucion = codigo_contribucion;
        this.concepto_contribucion = concepto_contribucion;
        this.tipo_pago = tipo_pago;
        this.descripcion = descripcion;
    }
    
    public ContribucionCompletaDTO() {
        
    }
    
    

    public String getCodigo_contribucion() {
        return codigo_contribucion;
    }

    public void setCodigo_contribucion(String codigo_contribucion) {
        this.codigo_contribucion = codigo_contribucion;
    }

    public String getConcepto_contribucion() {
        return concepto_contribucion;
    }

    public void setConcepto_contribucion(String concepto_contribucion) {
        this.concepto_contribucion = concepto_contribucion;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
