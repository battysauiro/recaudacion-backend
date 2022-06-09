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
public class AprovechamientoMultaVehicularCompletaDTO extends AprovechamientoCompletoDTO{
    
    
    private String descripcion_articulo;
    private float uma_min;
    private float uma_max;
    private String tipo_vehiculo;

    public AprovechamientoMultaVehicularCompletaDTO() {
    }

    public AprovechamientoMultaVehicularCompletaDTO(String descripcion_articulo, float uma_min, float uma_max, String tipo_vehiculo, String id_contribucion_aprovechamiento, String catalogo_aprovechamiento, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(id_contribucion_aprovechamiento, catalogo_aprovechamiento, codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.descripcion_articulo = descripcion_articulo;
        this.uma_min = uma_min;
        this.uma_max = uma_max;
        this.tipo_vehiculo = tipo_vehiculo;
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

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }
    
    
}
