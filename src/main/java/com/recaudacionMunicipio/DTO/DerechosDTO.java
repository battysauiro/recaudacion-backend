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
public class DerechosDTO extends ContribucionDTO{
    
    private String id_derecho;
    private int catalogo_derechos;
    private String scatalogo_derechos;

    public DerechosDTO() {
    }

    public DerechosDTO(String id_derecho, int catalogo_derechos,String scatalogo_derechos, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago,  int id_descripcion,String descripcion) {
        super(codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion,descripcion);
        this.id_derecho = id_derecho;
        this.catalogo_derechos = catalogo_derechos;
        this.scatalogo_derechos=scatalogo_derechos;
    }


    public String getId_derecho() {
        return id_derecho;
    }

    public void setId_derecho(String id_derecho) {
        this.id_derecho = id_derecho;
    }

    public int getCatalogo_derechos() {
        return catalogo_derechos;
    }

    public void setCatalogo_derechos(int catalogo_derechos) {
        this.catalogo_derechos = catalogo_derechos;
    }

    public String getScatalogo_derechos() {
        return scatalogo_derechos;
    }
    
    public void setScatalogo_derechos(String scatalogo_derechos) {
        this.scatalogo_derechos = scatalogo_derechos;
    }
    
    
    
}
