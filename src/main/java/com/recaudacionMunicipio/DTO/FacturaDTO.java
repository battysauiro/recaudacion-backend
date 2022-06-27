/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Oscar
 */
public class FacturaDTO {

    private int folio;
    private String usuario_id;
    private String contribuyente_id;
    private String domicilio;
    private String rmc;
    private Date fecha;
    private int descuento;
    private Double total;
    private List<ContribucionFacturaDTO> items;
    private boolean estado_pago;

    public FacturaDTO() {
    }

    public FacturaDTO(int folio, String usuario_id, String contribuyente_id,String domicilio, String rmc,Date fecha, int descuento, Double total, List<ContribucionFacturaDTO> items, boolean estado_pago) {
        this.folio = folio;
        this.usuario_id = usuario_id;
        this.contribuyente_id = contribuyente_id;
        this.domicilio=domicilio;
        this.rmc=rmc;
        this.fecha = fecha;
        this.descuento = descuento;
        this.total = total;
        this.items = items;
        this.estado_pago = estado_pago;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getContribuyente_id() {
        return contribuyente_id;
    }

    public void setContribuyente_id(String contribuyente_id) {
        this.contribuyente_id = contribuyente_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ContribucionFacturaDTO> getItems() {
        return items;
    }

    public boolean isEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(boolean estado_pago) {
        this.estado_pago = estado_pago;
    }

    public String getRmc() {
        return rmc;
    }

    public void setRmc(String rmc) {
        this.rmc = rmc;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
    
    

}
