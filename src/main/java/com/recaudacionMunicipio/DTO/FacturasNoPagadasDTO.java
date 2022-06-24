/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO;

import java.util.Date;

/**
 *
 * @author Oscar
 */
public class FacturasNoPagadasDTO {
    
    private int folio;
    private String cajero;
    private String contribuyente;
    private String contribucion;
    private Date fecha_solicitud;
    private int descuento;
    private double total;
    private String estado_pago;

    public FacturasNoPagadasDTO() {
    }

    public FacturasNoPagadasDTO(int folio, String cajero, String contribuyente, String contribucion, Date fecha_solicitud, int descuento, double total, String estado_pago) {
        this.folio = folio;
        this.cajero = cajero;
        this.contribuyente = contribuyente;
        this.contribucion = contribucion;
        this.fecha_solicitud = fecha_solicitud;
        this.descuento = descuento;
        this.total = total;
        this.estado_pago = estado_pago;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(String contribuyente) {
        this.contribuyente = contribuyente;
    }

    public String getContribucion() {
        return contribucion;
    }

    public void setContribucion(String contribucion) {
        this.contribucion = contribucion;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }
    
    
}
