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
public class PeriodicidadDTO {
    
    private int id_periodicidad;
    private String nombre_periodicidad;

    public PeriodicidadDTO() {
    }

    
    public PeriodicidadDTO(int id_periodicidad,String nombre_periodicidad) {
        this.id_periodicidad=id_periodicidad;
        this.nombre_periodicidad = nombre_periodicidad;
    }

    public String getNombre_periodicidad() {
        return nombre_periodicidad;
    }

    public void setNombre_periodicidad(String nombre_periodicidad) {
        this.nombre_periodicidad = nombre_periodicidad;
    }
    
    public int getId_periodicidad() {
        return id_periodicidad;
    }
    
    public void setId_periodicidad(int id_periodicidad) {
        this.id_periodicidad = id_periodicidad;
}
    
    
}
