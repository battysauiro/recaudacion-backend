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
public class AprovechamientoMultaDTO extends AprovechamientoDTO{
    private String id_aprovechamiento_multa;
    private float cantidad;

    public AprovechamientoMultaDTO() {
    }

    public AprovechamientoMultaDTO(String id_aprovechamiento_multa, float cantidad) {
        this.id_aprovechamiento_multa = id_aprovechamiento_multa;
        this.cantidad = cantidad;
    }

    public AprovechamientoMultaDTO(String id_aprovechamiento_multa, float cantidad, String id_contribucion, int id_catalogo,String scatalogo, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago, int id_descripcion, String descripcion) {
        super(id_contribucion, id_catalogo,scatalogo, codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion, descripcion);
        this.id_aprovechamiento_multa = id_aprovechamiento_multa;
        this.cantidad = cantidad;
    }

        
    
    public String getId_aprovechamiento_multa() {
        return id_aprovechamiento_multa;
    }

    public void setId_aprovechamiento_multa(String id_aprovechamiento_multa) {
        this.id_aprovechamiento_multa = id_aprovechamiento_multa;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    
    
}
