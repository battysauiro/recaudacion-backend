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
public class DerechosCompletoDTO extends ContribucionCompletaDTO{
    
    private String id_contribucion_derecho;
    private String catalogo_derechos;

    public DerechosCompletoDTO() {
    }

    public DerechosCompletoDTO(String id_contribucion_derecho, String catalogo_derechos, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.id_contribucion_derecho = id_contribucion_derecho;
        this.catalogo_derechos = catalogo_derechos;
    }

    public String getId_contribucion_derecho() {
        return id_contribucion_derecho;
    }

    public void setId_contribucion_derecho(String id_contribucion_derecho) {
        this.id_contribucion_derecho = id_contribucion_derecho;
    }

    public String getCatalogo_derechos() {
        return catalogo_derechos;
    }

    public void setCatalogo_derechos(String catalogo_derechos) {
        this.catalogo_derechos = catalogo_derechos;
    }
    
    
}
