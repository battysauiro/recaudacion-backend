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
public class ContribuyenteMoralDTO extends ContribuyenteDTO{
    
    private String id_contribuyente_moral;
    private String razon_social;

    public ContribuyenteMoralDTO() {
}

    public ContribuyenteMoralDTO(String id_contribuyente_moral, String razon_social, String rfc_contribuyente, String calle, String numero, String colonia, String codigo_postal) {
        super( rfc_contribuyente, calle, numero, colonia, codigo_postal);
        this.id_contribuyente_moral = id_contribuyente_moral;
        this.razon_social = razon_social;
    }

    

    public String getId_contribuyente_moral() {
        return id_contribuyente_moral;
    }

    public void setId_contribuyente_moral(String id_contribuyente_moral) {
        this.id_contribuyente_moral = id_contribuyente_moral;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }
    
    
}
