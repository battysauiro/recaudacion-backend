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
public class DerechosGeneralCompletoDTO extends DerechosCompletoDTO{
    
    private float cantidad;
    private String periodicidad;

    public DerechosGeneralCompletoDTO() {
    }

    public DerechosGeneralCompletoDTO(float cantidad, String periodicidad, String id_contribucion_derecho, String catalogo_derechos, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(id_contribucion_derecho, catalogo_derechos, codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.cantidad = cantidad;
        this.periodicidad = periodicidad;
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
    
    
}
