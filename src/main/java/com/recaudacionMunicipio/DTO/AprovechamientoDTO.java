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
public class AprovechamientoDTO extends ContribucionDTO{
    
    private String id_contribucion;
    private int id_catalogo;
    private String scatalogo;

    public AprovechamientoDTO() {
    }


    public AprovechamientoDTO(String id_contribucion, int id_catalogo,String scatalogo, String codigo_contribucion, String concepto_contribucion, int id_tipo_pago,String tipo_pago, int id_descripcion,String descripcion) {
        super(codigo_contribucion, concepto_contribucion, id_tipo_pago,tipo_pago, id_descripcion,descripcion);
        this.id_contribucion = id_contribucion;
        this.id_catalogo = id_catalogo;
        this.scatalogo=scatalogo;
    }

    

    public String getId_contribucion() {
        return id_contribucion;
    }

    public void setId_contribucion(String id_contribucion) {
        this.id_contribucion = id_contribucion;
    }

    public int getId_catalogo() {
        return id_catalogo;
    }

    public void setId_catalogo(int id_catalogo) {
        this.id_catalogo = id_catalogo;
    }

    public String getScatalogo() {
        return scatalogo;
}

    public void setScatalogo(String scatalogo) {
        this.scatalogo = scatalogo;
    }
    
    
}
