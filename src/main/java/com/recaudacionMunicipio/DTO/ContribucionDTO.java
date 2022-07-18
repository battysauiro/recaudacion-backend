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
public class ContribucionDTO {
    
    //private int id_contribucion;
    private String codigo_contribucion;
    private String concepto_contribucion;
    private int id_tipo_pago;
    private String tipo_pago;
    private int id_descripcion;
    private String descripcion;
    private int nivelContribucion;
    private String nivelContribucionS;

    public ContribucionDTO() {
    }

    public ContribucionDTO(String codigo_contribucion, String concepto_contribucion, int id_tipo_pago, String tipo_pago, int id_descripcion,String descripcion,int nivelContribucion,String nivelContribucionS) {
        this.codigo_contribucion = codigo_contribucion;
        this.concepto_contribucion = concepto_contribucion;
        this.id_tipo_pago = id_tipo_pago;
        this.tipo_pago=tipo_pago;
        this.id_descripcion = id_descripcion;
        this.descripcion=descripcion;
        this.nivelContribucion=nivelContribucion;
        this.nivelContribucionS=nivelContribucionS;
    }

    /**public int getId_contribucion() {
        return id_contribucion;
    }

    public void setId_contribucion(int id_contribucion) {
        this.id_contribucion = id_contribucion;
    }*/

    public String getCodigo_contribucion() {
        return codigo_contribucion;
    }

    public void setCodigo_contribucion(String codigo_contribucion) {
        this.codigo_contribucion = codigo_contribucion;
    }

    public String getConcepto_contribucion() {
        return concepto_contribucion;
    }

    public void setConcepto_contribucion(String concepto_contribucion) {
        this.concepto_contribucion = concepto_contribucion;
    }

    public int getId_tipo_pago() {
        return id_tipo_pago;
    }

    public void setId_tipo_pago(int id_tipo_pago) {
        this.id_tipo_pago = id_tipo_pago;
    }

    public int getId_descripcion() {
        return id_descripcion;
    }

    public void setId_descripcion(int id_descripcion) {
        this.id_descripcion = id_descripcion;
    }
    
    public String getTipo_pago() {
        return tipo_pago;
    }
    
    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }
    
    public String getDescripcion() {
        return descripcion;
}
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNivelContribucion() {
        return nivelContribucion;
    }

    public void setNivelContribucion(int nivelContribucion) {
        this.nivelContribucion = nivelContribucion;
    }

    public String getNivelContribucionS() {
        return nivelContribucionS;
    }

    public void setNivelContribucionS(String nivelContribucionS) {
        this.nivelContribucionS = nivelContribucionS;
    }
    


}
    
    


