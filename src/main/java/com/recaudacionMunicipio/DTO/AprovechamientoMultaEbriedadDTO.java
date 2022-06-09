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
public class AprovechamientoMultaEbriedadDTO extends AprovechamientoDTO{
    
    private String id_apro_ebriedad;
    private float uma_min;
    private float uma_max;
    private float cantidad_alcohol;

    public AprovechamientoMultaEbriedadDTO() {
    }

    public AprovechamientoMultaEbriedadDTO(String id_apro_ebriedad, float uma_min, float uma_max, float cantidad_alcohol, String id_contribucion, int id_catalogo,String scatalogo, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago, int id_descripcion, String descripcion) {
        super(id_contribucion, id_catalogo,scatalogo, codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion,descripcion);
        this.id_apro_ebriedad = id_apro_ebriedad;
        this.uma_min = uma_min;
        this.uma_max = uma_max;
        this.cantidad_alcohol = cantidad_alcohol;
    }


    public String getId_apro_ebriedad() {
        return id_apro_ebriedad;
    }

    public void setId_apro_ebriedad(String id_apro_ebriedad) {
        this.id_apro_ebriedad = id_apro_ebriedad;
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
