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
public class TipoPagoDTO {
    
    private int id_pago;
    private String nombre_pago;

    public TipoPagoDTO() {
    }

    
    
    public TipoPagoDTO(int id_pago,String nombre_pago) {
        this.id_pago=id_pago;
        this.nombre_pago = nombre_pago;
    }

    public String getNombre_pago() {
        return nombre_pago;
    }

    public void setNombre_pago(String nombre_pago) {
        this.nombre_pago = nombre_pago;
    }
    
    public int getId_pago() {
        return id_pago;
    }
    
    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
}
    
    
}
