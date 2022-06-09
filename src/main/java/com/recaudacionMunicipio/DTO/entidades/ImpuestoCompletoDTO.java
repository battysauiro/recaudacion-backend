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
public class ImpuestoCompletoDTO extends ContribucionCompletaDTO{
    
    private String nombre;
    private String id_impuesto;
    private String catalogo_impuesto;
    private float cantidad;

    public ImpuestoCompletoDTO() {
    }

    public ImpuestoCompletoDTO(String nombre,String id_impuesto, String catalogo_impuesto, float cantidad, String codigo_contribucion, String concepto_contribucion, String tipo_pago, String descripcion) {
        super(codigo_contribucion, concepto_contribucion, tipo_pago, descripcion);
        this.id_impuesto = id_impuesto;
        this.catalogo_impuesto = catalogo_impuesto;
        this.cantidad = cantidad;
        this.nombre=nombre;
    }

    public String getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(String id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public String getCatalogo_impuesto() {
        return catalogo_impuesto;
    }

    public void setCatalogo_impuesto(String catalogo_impuesto) {
        this.catalogo_impuesto = catalogo_impuesto;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
}
    
    
    
}
