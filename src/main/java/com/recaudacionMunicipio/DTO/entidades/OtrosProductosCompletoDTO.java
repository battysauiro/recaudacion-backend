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
public class OtrosProductosCompletoDTO extends ContribucionCompletaDTO{
    
    private String id_otros_productos;
    private float cantidad;
    private String periodicidad;
    private String catalogo_otros_productos;

    public OtrosProductosCompletoDTO() {
    }

    public OtrosProductosCompletoDTO(String id_otros_productos, float cantidad, String periodicidad, String catalogo_otros_productos, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.id_otros_productos = id_otros_productos;
        this.cantidad = cantidad;
        this.periodicidad = periodicidad;
        this.catalogo_otros_productos = catalogo_otros_productos;
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

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public String getCatalogo_otros_productos() {
        return catalogo_otros_productos;
    }

    public void setCatalogo_otros_productos(String catalogo_otros_productos) {
        this.catalogo_otros_productos = catalogo_otros_productos;
    }
    
    
}
