/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.ImpuestoDTO;
import com.recaudacionMunicipio.DTO.entidades.ImpuestoCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.ImpuestoServicioImpl;
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
@RequestMapping("/api/impuestos")
public class ImpuestoControlador {
    
    @Autowired
    private ImpuestoServicioImpl impuestoSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<ImpuestoDTO> listarImpuesto(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return impuestoSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public entidadRespuesta<ImpuestoCompletoDTO> listarImpuestoCompleto(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return impuestoSer.findAllC(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("i/page/{page}")
    public Page<ImpuestoDTO> listarImpuesto(@PathVariable Integer page){
        return impuestoSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/test/{page}")
    public Page<ImpuestoCompletoDTO> listarImpuestoCompleto(@PathVariable Integer page){
        return impuestoSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<ImpuestoDTO> obtenerImpuesto(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(impuestoSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/{id}")
    public ResponseEntity<ImpuestoCompletoDTO> obtenerImpuestoCompleto(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(impuestoSer.findByIdCompleto(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<ImpuestoDTO> guardarImpuesto(@RequestBody ImpuestoDTO impuestoDTO) {
        return new ResponseEntity<>(impuestoSer.save(impuestoDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<ImpuestoDTO> actualizarImpuesto(@RequestBody ImpuestoDTO impuestoDTO, @PathVariable(name = "id") String id) {
        ImpuestoDTO impuestoRespuesta = impuestoSer.update(impuestoDTO, id);
        return new ResponseEntity<>(impuestoRespuesta, HttpStatus.OK);
    }
    
    /**
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImpuesto(@PathVariable(name = "id") Integer id) {
        impuestoSer.delete(id);
        return new ResponseEntity<>("contribucion impuesto eliminado con exito", HttpStatus.OK);
    }*/
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}") 
	public ResponseEntity<Map<String,Boolean>> eliminarImpuesto(@PathVariable String id){
		impuestoSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
