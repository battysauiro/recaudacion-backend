/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.ContribuyenteDTO;
import com.recaudacionMunicipio.DTO.ContribuyenteFisicaDTO;
import com.recaudacionMunicipio.modelo.Contribuyente;
import com.recaudacionMunicipio.servicios.ContribuyenteServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */
@RestController 
@RequestMapping("/api/contribuyente")
public class ContribuyenteControlador {
    @Autowired
    private ContribuyenteServicioImpl contribuyenteImplSer;
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/{id}")
    public ResponseEntity<ContribuyenteDTO> obtenerContribuyenteFisica(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(contribuyenteImplSer.findById(id)); 
    }
    
}
