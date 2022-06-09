/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.DerechosDTO;
import com.recaudacionMunicipio.DTO.entidades.DerechosCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.DerechosServicioImpl;
import java.util.List;
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
@RequestMapping("/api/derechos")
public class DerechosControlador {
    
    @Autowired
    private DerechosServicioImpl DerechosImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public entidadRespuesta<DerechosDTO> listarDerechos(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return DerechosImplSer.findAll(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full")
    public entidadRespuesta<DerechosCompletoDTO> listarDerechosCompleto(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return DerechosImplSer.findAllC(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<DerechosDTO> listarDerechos(@PathVariable Integer page){
        return DerechosImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public Page<DerechosCompletoDTO> listarDerechosCompleto(@PathVariable Integer page){
        return DerechosImplSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<DerechosDTO> obtenerDerecho(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(DerechosImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/{id}")
    public ResponseEntity<DerechosCompletoDTO> obtenerDerechoCompleto(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(DerechosImplSer.findByIdCompleto(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<DerechosDTO> guardarDerecho(@RequestBody DerechosDTO derechosDTO) {
        return new ResponseEntity<>(DerechosImplSer.save(derechosDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<DerechosDTO> actualizarDerecho(@RequestBody DerechosDTO derechosDTO, @PathVariable(name = "id") String id) {
        DerechosDTO derechoRespuesta = DerechosImplSer.update(derechosDTO, id);
        return new ResponseEntity<>(derechoRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDerecho(@PathVariable(name = "id") String id) {
        DerechosImplSer.delete(id);
        return new ResponseEntity<>("contribucion derecho eliminado con exito", HttpStatus.OK);
    }
}
