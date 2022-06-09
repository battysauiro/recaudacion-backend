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
public class UsuarioDTO {
    

 
    private String password;
    private String email;
    private String puesto;
    private boolean estado;
    private String sEstado;
    private String url_imagen;
    private String id_empleado;
    private String nombre_empleado;
    private int id_rol;
    private String nombre_rol;

    public UsuarioDTO(String password, String email, String puesto, boolean estado, String sEstado, String url_imagen,String id_empleado,String nombre_empleado,int id_rol,String nombre_rol) {
        this.password = password;
        this.email = email;
        this.puesto = puesto;
        this.estado = estado;
        this.sEstado=sEstado;
        this.url_imagen = url_imagen;
        this.id_empleado=id_empleado;
        this.nombre_empleado=nombre_empleado;
        this.id_rol=id_rol;
        this.nombre_rol=nombre_rol;
    }

    public UsuarioDTO() {
    }

    
    
    /**public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }
    
    public String getNombre_empleado() {
        return nombre_empleado;
    }
    
    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
}

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    public String getsEstado() {
        return sEstado;
    }

    public void setsEstado(String sEstado) {
        this.sEstado = sEstado;
    }
    
    
}
