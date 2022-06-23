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
public class DerechosGeneralDTO extends DerechosDTO{
    
    private String id_derecho_general;
    private float cantidad;
    private int id_periodicidad;
    private String periodicidad;

    public DerechosGeneralDTO() {
    }

    public DerechosGeneralDTO(String id_derecho_general, float cantidad, int id_periodicidad, String periodicidad, String id_derecho, int catalogo_derechos,String scatalogo_derechos, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago, int id_descripcion,String descripcion,int nivelContribucion) {
        super(id_derecho, catalogo_derechos,scatalogo_derechos, codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion,descripcion,nivelContribucion);
        this.id_derecho_general = id_derecho_general;
        this.cantidad = cantidad;
        this.id_periodicidad = id_periodicidad;
        this.periodicidad=periodicidad;
    }

    public String getId_derecho_general() {
        return id_derecho_general;
    }

    public void setId_derecho_general(String id_derecho_general) {
        this.id_derecho_general = id_derecho_general;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_periodicidad() {
        return id_periodicidad;
    }

    public void setId_periodicidad(int id_periodicidad) {
        this.id_periodicidad = id_periodicidad;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }
    
    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
}

    
    
}
