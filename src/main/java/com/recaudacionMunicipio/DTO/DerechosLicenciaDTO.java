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
public class DerechosLicenciaDTO extends DerechosDTO{
    
    private String id_derecho_licencia;
    private float expedicion;
    private float refrendo;

    public DerechosLicenciaDTO() {
    }

    public DerechosLicenciaDTO(String id_derecho_licencia, float expedicion, float refrendo, String id_derecho, int catalogo_derechos,String scatalogo_derechos, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago, int id_descripcion,String descripcion) {
        super(id_derecho, catalogo_derechos,scatalogo_derechos, codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion,descripcion);
        this.id_derecho_licencia = id_derecho_licencia;
        this.expedicion = expedicion;
        this.refrendo = refrendo;
    }


    public String getId_derecho_licencia() {
        return id_derecho_licencia;
    }

    public void setId_derecho_licencia(String id_derecho_licencia) {
        this.id_derecho_licencia = id_derecho_licencia;
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
