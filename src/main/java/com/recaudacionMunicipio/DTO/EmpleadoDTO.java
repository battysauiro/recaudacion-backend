/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.recaudacionMunicipio.modelo.Palaciomunicipal;

/**
 *
 * @author Oscar
 */
public class EmpleadoDTO {
    
    private String curp;
    private String nombre;
    private String apellido_p;
    private String apellido_m;  
    //@JsonProperty    
    //private palacioMunicipalDTO palacio;
    private int palacio;
    private String nombre_palacio;

    public EmpleadoDTO(String curp, String nombre, String apellido_p, String apellido_m, int palacio,String nombre_palacio) {
        this.curp = curp;
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.palacio = palacio;
        this.nombre_palacio=nombre_palacio;
    }

    public EmpleadoDTO() {
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public int getPalacio() {
        return palacio;
    }

    public void setPalacio(int palacio) {
        this.palacio = palacio;
    }
    
    
    public String getNombre_palacio() {
        return nombre_palacio;
}

    public void setNombre_palacio(String nombre_palacio) {
        this.nombre_palacio = nombre_palacio;
    }
    
    
}
