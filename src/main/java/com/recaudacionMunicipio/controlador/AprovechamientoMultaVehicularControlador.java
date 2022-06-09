/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaVehicularDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoMultaVehicularCompletaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.AprovechamientoMultaVehicularImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */
@RestController 
@RequestMapping("/api/multaVehicular")
public class AprovechamientoMultaVehicularControlador {
    
    @Autowired
    private AprovechamientoMultaVehicularImpl aprovechamientoMultaVehicularSer;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<AprovechamientoMultaVehicularDTO> listarMultasVehicular(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return aprovechamientoMultaVehicularSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full")
    public entidadRespuesta<AprovechamientoMultaVehicularCompletaDTO> listarMultaVehicularCompleta(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return aprovechamientoMultaVehicularSer.findAllC(numeroDePagina,cantidadPagina);
    }
    
    @GetMapping("i/page/{page}")
    public Page<AprovechamientoMultaVehicularDTO> listarMultasVehicular(@PathVariable Integer page){
        return aprovechamientoMultaVehicularSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public Page<AprovechamientoMultaVehicularCompletaDTO> listarMultaVehicularCompleta(@PathVariable Integer page){
        return aprovechamientoMultaVehicularSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<AprovechamientoMultaVehicularDTO> obtenerMultaVehicular(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoMultaVehicularSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/{id}")
    public ResponseEntity<AprovechamientoMultaVehicularCompletaDTO> obtenerAprovechamientoMultaVehicularCompleta(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoMultaVehicularSer.findByIdCompleto(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<AprovechamientoMultaVehicularDTO> guardarAprovechamientoMultaVehicular(@RequestBody AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO) {
        return new ResponseEntity<>(aprovechamientoMultaVehicularSer.save(aprovechamientoMultaVehicularDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<AprovechamientoMultaVehicularDTO> actualizarAprovechamientoMultaVehicular(@RequestBody AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO, @PathVariable(name = "id") String id) {
        AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularRespuesta = aprovechamientoMultaVehicularSer.update(aprovechamientoMultaVehicularDTO, id);
        return new ResponseEntity<>(aprovechamientoMultaVehicularRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarAprovechamientoMultaVehicular(@PathVariable String id){
        aprovechamientoMultaVehicularSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
