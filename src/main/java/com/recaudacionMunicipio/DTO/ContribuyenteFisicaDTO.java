/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO;

//import java.sql.Date;


import java.util.Date;





/**
 *
 * @author Oscar
 */
public class ContribuyenteFisicaDTO extends ContribuyenteDTO{
    
    private String id_contribuyente_fisica;
    private String curp;
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private Date fecha;

    public ContribuyenteFisicaDTO() {
}

    public ContribuyenteFisicaDTO(String id_contribuyente_fisica, String curp, String nombre, String apellido_p, String apellido_m, Date fecha, String rfc_contribuyente, String calle, String numero, String colonia, String codigo_postal) {
        super( rfc_contribuyente, calle, numero, colonia, codigo_postal);
        this.id_contribuyente_fisica = id_contribuyente_fisica;
        this.curp = curp;
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.fecha = fecha;
    }

    

    public String getId_contribuyente_fisica() {
        return id_contribuyente_fisica;
    }

    public void setId_contribuyente_fisica(String id_contribuyente_fisica) {
        this.id_contribuyente_fisica = id_contribuyente_fisica;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
