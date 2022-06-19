/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO;

/**
 *
 * @author Oscar
 */
public class ImpuestoDTO extends ContribucionDTO{
    
    private String id_impuesto;
    private int catalogo_impuesto;
    private String scatalogo_impuesto;
    private float cantidad;

    public ImpuestoDTO() {
    }

    public ImpuestoDTO(String id_impuesto, int catalogo_impuesto,String scatalogo_impuesto, float cantidad, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago, String tipo_pago, int id_descripcion, String descripcion,int nivelContribucion) {
        super(codigo_contribucion, concepto_contribucion, id_tipo_pago, tipo_pago, id_descripcion, descripcion,nivelContribucion);
        this.id_impuesto = id_impuesto;
        this.catalogo_impuesto = catalogo_impuesto;
        this.scatalogo_impuesto=scatalogo_impuesto;
        this.cantidad = cantidad;
    }


    

    
    public String getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(String id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public int getCatalogo_impuesto() {
        return catalogo_impuesto;
    }

    public void setCatalogo_impuesto(int catalogo_impuesto) {
        this.catalogo_impuesto = catalogo_impuesto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getScatalogo_impuesto() {
        return scatalogo_impuesto;
    }
    
    public void setScatalogo_impuesto(String scatalogo_impuesto) {
        this.scatalogo_impuesto = scatalogo_impuesto;
}

    
}
