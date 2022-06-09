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
public class RolesDTO {
    
    private int id_rol;
    private String descripcion;

    public RolesDTO() {
    }

    
    
    public RolesDTO(int id_rol,String descripcion) {
        this.id_rol=id_rol;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public int getId_rol() {
        return id_rol;
    }
    
    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
}
    
    
}
