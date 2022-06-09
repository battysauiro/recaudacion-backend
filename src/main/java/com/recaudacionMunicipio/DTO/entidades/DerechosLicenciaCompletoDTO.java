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
public class DerechosLicenciaCompletoDTO extends DerechosCompletoDTO{
    
    private float expedicion;
    private float refrendo;

    public DerechosLicenciaCompletoDTO() {
    }

    public DerechosLicenciaCompletoDTO(float expedicion, float refrendo, String id_contribucion_derecho, String catalogo_derechos, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(id_contribucion_derecho, catalogo_derechos, codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.expedicion = expedicion;
        this.refrendo = refrendo;
    }

    public float getExpedicion() {
        return expedicion;
    }

    public void setExpedicion(float expedicion) {
        this.expedicion = expedicion;
    }

    public float getRefrendo() {
        return refrendo;
    }

    public void setRefrendo(float refrendo) {
        this.refrendo = refrendo;
    }
    
    
}
