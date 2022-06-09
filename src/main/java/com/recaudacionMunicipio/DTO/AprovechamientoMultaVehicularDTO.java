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
public class AprovechamientoMultaVehicularDTO extends AprovechamientoDTO{
    
    private String id_multa_vehicular;
    private String descripcion_articulo;
    private float uma_min;
    private float uma_max;
    private int tipo_vehiculo;
    private String svehiculo;

    public AprovechamientoMultaVehicularDTO() {
    }

    public AprovechamientoMultaVehicularDTO(String id_multa_vehicular, String descripcion_articulo, float uma_min, float uma_max, int tipo_vehiculo,String svehiculo, String id_contribucion, int id_catalogo,String scatalogo, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago, int id_descripcion,String descripcion) {
        super(id_contribucion, id_catalogo,scatalogo, codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion,descripcion);
        this.id_multa_vehicular = id_multa_vehicular;
        this.descripcion_articulo = descripcion_articulo;
        this.uma_min = uma_min;
        this.uma_max = uma_max;
        this.tipo_vehiculo = tipo_vehiculo;
        this.svehiculo=svehiculo;
    }

    

    public String getId_multa_vehicular() {
        return id_multa_vehicular;
    }

    public void setId_multa_vehicular(String id_multa_vehicular) {
        this.id_multa_vehicular = id_multa_vehicular;
    }

    public String getDescripcion_articulo() {
        return descripcion_articulo;
    }

    public void setDescripcion_articulo(String descripcion_articulo) {
        this.descripcion_articulo = descripcion_articulo;
    }

    public float getUma_min() {
        return uma_min;
    }

    public void setUma_min(float uma_min) {
        this.uma_min = uma_min;
    }

    public float getUma_max() {
        return uma_max;
    }

    public void setUma_max(float uma_max) {
        this.uma_max = uma_max;
    }

    public int getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(int tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public String getSvehiculo() {
        return svehiculo;
    }
    
    public void setSvehiculo(String svehiculo) {
        this.svehiculo = svehiculo;
    }
    
    
    
}
