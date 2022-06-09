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
public class AprovechamientoCompletoDTO extends ContribucionCompletaDTO{
    
    private String id_contribucion_aprovechamiento;
    private String catalogo_aprovechamiento;

    public AprovechamientoCompletoDTO() {
    }

    public AprovechamientoCompletoDTO(String id_contribucion_aprovechamiento, String catalogo_aprovechamiento, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.id_contribucion_aprovechamiento = id_contribucion_aprovechamiento;
        this.catalogo_aprovechamiento = catalogo_aprovechamiento;
    }

    public String getId_contribucion_aprovechamiento() {
        return id_contribucion_aprovechamiento;
    }

    public String getCatalogo_aprovechamiento() {
        return catalogo_aprovechamiento;
    }

    
    
    
}
