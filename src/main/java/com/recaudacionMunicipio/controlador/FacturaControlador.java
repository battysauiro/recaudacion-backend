/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.FacturaDTO;
import com.recaudacionMunicipio.modelo.Factura;
import com.recaudacionMunicipio.servicios.ContribuyenteServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */
@RestController 
@RequestMapping("/api")
public class FacturaControlador {
    
    @Autowired
    private ContribuyenteServicioImpl contribuyenteImplSer;
    
    @GetMapping("/facturas/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public FacturaDTO show(@PathVariable int id){
        return contribuyenteImplSer.findFacturaById(id);
    }
    
}
