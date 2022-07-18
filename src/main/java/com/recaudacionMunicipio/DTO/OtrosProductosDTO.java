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
public class OtrosProductosDTO extends ContribucionDTO{
    
    private String id_otros_productos;
    private float cantidad;
    private int periodicidad;
    private String speriodicidad;
    private int catalogo_otros_productos;
    private String scatalogo_otros_productos;

    public OtrosProductosDTO() {
    }

 
    
    public OtrosProductosDTO(String id_otros_productos, float cantidad, int periodicidad,String speriodicidad, int catalogo_otros_productos, String scatalogo_otros_productos,String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago, int id_descripcion, String descripcion,int nivelContribucion,String nivelContribucionS) {
        super(codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion,descripcion,nivelContribucion,nivelContribucionS);
        this.id_otros_productos = id_otros_productos;
        this.cantidad = cantidad;
        this.periodicidad = periodicidad;
        this.speriodicidad= speriodicidad;
        this.catalogo_otros_productos = catalogo_otros_productos;
        this.scatalogo_otros_productos=scatalogo_otros_productos;
    }

    public String getId_otros_productos() {
        return id_otros_productos;
    }

    public void setId_otros_productos(String id_otros_productos) {
        this.id_otros_productos = id_otros_productos;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public int getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(int periodicidad) {
        this.periodicidad = periodicidad;
    }

    public int getCatalogo_otros_productos() {
        return catalogo_otros_productos;
    }

    public void setCatalogo_otros_productos(int catalogo_otros_productos) {
        this.catalogo_otros_productos = catalogo_otros_productos;
    }

    public String getSperiodicidad() {
        return speriodicidad;
}

    public void setSperiodicidad(String speriodicidad) {
        this.speriodicidad = speriodicidad;
    }

    public String getScatalogo_otros_productos() {
        return scatalogo_otros_productos;
    }

    public void setScatalogo_otros_productos(String scatalogo_otros_productos) {
        this.scatalogo_otros_productos = scatalogo_otros_productos;
    }

    
}
