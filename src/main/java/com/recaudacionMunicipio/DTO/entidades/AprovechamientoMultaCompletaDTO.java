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
public class AprovechamientoMultaCompletaDTO extends AprovechamientoCompletoDTO{
    
    private float cantidad;

    public AprovechamientoMultaCompletaDTO() {
    }

    public AprovechamientoMultaCompletaDTO(float cantidad, String id_contribucion_aprovechamiento, String catalogo_aprovechamiento, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(id_contribucion_aprovechamiento, catalogo_aprovechamiento, codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.cantidad = cantidad;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
