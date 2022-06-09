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
public class CatalogosDTO { 
    
    
    
    private int id_catalogo;
    private String descripcion;

    public CatalogosDTO(int id_catalogo ,String descripcion) {
        this.id_catalogo=id_catalogo;
        this.descripcion = descripcion;
    }
    
    public CatalogosDTO() {
        
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }
    
    
    
}
