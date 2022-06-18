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
public class ContribucionFacturaDTO {
    
    private int contribucionFactura;
    private String idContribucion;
    private int idFactura;
    private int cantidad;

    public ContribucionFacturaDTO() {
    }

    public ContribucionFacturaDTO(int contribucionFactura, String idContribucion, int idFactura, int cantidad) {
        this.contribucionFactura = contribucionFactura;
        this.idContribucion = idContribucion;
        this.idFactura = idFactura;
        this.cantidad = cantidad;
    }

    public int getContribucionFactura() {
        return contribucionFactura;
    }

    public void setContribucionFactura(int contribucionFactura) {
        this.contribucionFactura = contribucionFactura;
    }

    public String getIdContribucion() {
        return idContribucion;
    }

    public void setIdContribucion(String idContribucion) {
        this.idContribucion = idContribucion;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
