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
public class TipoVehiculoDTO {
    
    private int id_tipo_vehiculo;
    private String nombre_vehiculo;

    public TipoVehiculoDTO(int id_tipo_vehiculo,String nombre_vehiculo) {
        this.id_tipo_vehiculo=id_tipo_vehiculo;
        this.nombre_vehiculo = nombre_vehiculo;
    }

    public TipoVehiculoDTO() {
    }
    
    

    public String getNombre_vehiculo() {
        return nombre_vehiculo;
    }

    public void setNombre_vehiculo(String nombre_vehiculo) {
        this.nombre_vehiculo = nombre_vehiculo;
    }
    
    public int getId_tipo_vehiculo() {
        return id_tipo_vehiculo;
    }
    
    public void setId_tipo_vehiculo(int id_tipo_vehiculo) {
        this.id_tipo_vehiculo = id_tipo_vehiculo;
}
    
    
}
