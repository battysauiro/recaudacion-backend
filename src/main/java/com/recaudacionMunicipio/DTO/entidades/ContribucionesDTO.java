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
public class ContribucionesDTO {
    
    private String id_contribucion;
    private String nombre_tipo_contribucion;
    private String codigo_contribucion;
    private String concepto_contribucion; 
    private String catalogo_contribucion;
    private String descripcion;

    public ContribucionesDTO(String id_contribucion, String nombre_tipo_contribucion, String codigo_contribucion, String concepto_contribucion, String catalogo_contribucion, String descripcion) {
        this.id_contribucion = id_contribucion;
        this.nombre_tipo_contribucion = nombre_tipo_contribucion;
        this.codigo_contribucion = codigo_contribucion;
        this.concepto_contribucion = concepto_contribucion;
        this.catalogo_contribucion = catalogo_contribucion;
        this.descripcion = descripcion;
}

    public ContribucionesDTO() {
    }

    public String getId_contribucion() {
        return id_contribucion;
    }

    public void setId_contribucion(String id_contribucion) {
        this.id_contribucion = id_contribucion;
    }

    public String getNombre_tipo_contribucion() {
        return nombre_tipo_contribucion;
    }

    public void setNombre_tipo_contribucion(String nombre_tipo_contribucion) {
        this.nombre_tipo_contribucion = nombre_tipo_contribucion;
    }

    public String getCodigo_contribucion() {
        return codigo_contribucion;
    }

    public void setCodigo_contribucion(String codigo_contribucion) {
        this.codigo_contribucion = codigo_contribucion;
    }

    public String getConcepto_contribucion() {
        return concepto_contribucion;
    }

    public void setConcepto_contribucion(String concepto_contribucion) {
        this.concepto_contribucion = concepto_contribucion;
    }

    public String getCatalogo_contribucion() {
        return catalogo_contribucion;
    }

    public void setCatalogo_contribucion(String catalogo_contribucion) {
        this.catalogo_contribucion = catalogo_contribucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
