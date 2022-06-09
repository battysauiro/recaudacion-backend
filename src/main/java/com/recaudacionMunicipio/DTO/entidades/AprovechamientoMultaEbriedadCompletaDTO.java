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
public class AprovechamientoMultaEbriedadCompletaDTO extends AprovechamientoCompletoDTO{
 
    private float uma_min;
    private float uma_max;
    private float cantidad_alcohol;

    public AprovechamientoMultaEbriedadCompletaDTO() {
    }

    public AprovechamientoMultaEbriedadCompletaDTO(float uma_min, float uma_max, float cantidad_alcohol, String id_contribucion_aprovechamiento, String catalogo_aprovechamiento, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(id_contribucion_aprovechamiento, catalogo_aprovechamiento, codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.uma_min = uma_min;
        this.uma_max = uma_max;
        this.cantidad_alcohol = cantidad_alcohol;
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

    public float getCantidad_alcohol() {
        return cantidad_alcohol;
    }

    public void setCantidad_alcohol(float cantidad_alcohol) {
        this.cantidad_alcohol = cantidad_alcohol;
    }
    
    
}
