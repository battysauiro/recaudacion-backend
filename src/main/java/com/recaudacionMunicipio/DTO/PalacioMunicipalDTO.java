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
public class PalacioMunicipalDTO {
    private int id;
    private String telefono;
    private String imagen;
    private int municipio;
    private String nombre_municipio;

    public PalacioMunicipalDTO(int id,String telefono, String imagen, int municipio,String nombre_municipio) {
        this.id=id;
        this.telefono = telefono;
        this.imagen = imagen;
        this.municipio = municipio;
        this.nombre_municipio=nombre_municipio;
    }

    public PalacioMunicipalDTO() {
    }

    
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMunicipio() {
        return municipio;
    }

    public void setMunicipio(int municipio) {
        this.municipio = municipio;
    }
    
    public String getNombre_municipio() {
        return nombre_municipio;
    }
    
    public void setNombre_municipio(String nombre_municipio) {
        this.nombre_municipio = nombre_municipio;
}
    
    
}
