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
public class MunicipiosDTO {
    
    private int clave;
    private String nombre_municipios;
    private String cabecera_municipal;

    public MunicipiosDTO(int clave, String nombre_municipios, String cabecera_municipal) {
        this.clave = clave;
        this.nombre_municipios = nombre_municipios;
        this.cabecera_municipal = cabecera_municipal;
    }

    public MunicipiosDTO() {
    }
    
    

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre_municipios() {
        return nombre_municipios;
    }

    public void setNombre_municipios(String nombre_municipios) {
        this.nombre_municipios = nombre_municipios;
    }

    public String getCabecera_municipal() {
        return cabecera_municipal;
    }

    public void setCabecera_municipal(String cabecera_municipal) {
        this.cabecera_municipal = cabecera_municipal;
    }
    
    
}
