/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.ContribucionDTO;
import com.recaudacionMunicipio.DTO.entidades.ContribucionCompletaDTO;
import com.recaudacionMunicipio.DTO.entidades.ContribucionesDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.ContribucionServicioImpl;
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
@RequestMapping("/api/contribucion")
public class ContribucionControlador {
    
    @Autowired
    private ContribucionServicioImpl contribucionImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public entidadRespuesta<ContribucionDTO> listarContribucion(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return contribucionImplSer.findAll(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public entidadRespuesta<ContribucionCompletaDTO> listarContribucionCompleta(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return contribucionImplSer.findAllC(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/contribuciones/page/{page}")
    public entidadRespuesta<ContribucionesDTO> listarContribuciones(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return contribucionImplSer.findAllContribuciones(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<ContribucionDTO> listarContribucion(@PathVariable Integer page){
        return contribucionImplSer.findAll(PageRequest.of(page,10));
    }
    
    //@Secured({"ROLE_ADMIN"})
    //@GetMapping("/full/page/{page}")
    //public Page<ContribucionCompletaDTO> listarContribucionCompleta(@PathVariable Integer page){
      //  return contribucionImplSer.findAllC(PageRequest.of(page,10));
    //}
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<ContribucionDTO> obtenerContribucion(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(contribucionImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
     @GetMapping("/full/{id}")
    public ResponseEntity<ContribucionCompletaDTO> obtenerContribucionCompleta(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(contribucionImplSer.findByIdCompleto(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<ContribucionDTO> guardarContribucion(@RequestBody ContribucionDTO contribucionDTO) {
        return new ResponseEntity<>(contribucionImplSer.save(contribucionDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<ContribucionDTO> actualizarContribucion(@RequestBody ContribucionDTO contribucionDTO, @PathVariable(name = "id") String id) {
        ContribucionDTO contribucionRespuesta = contribucionImplSer.update(contribucionDTO, id);
        return new ResponseEntity<>(contribucionRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarContribucion(@PathVariable(name = "id") String id) {
        contribucionImplSer.delete(id);
        return new ResponseEntity<>("contribucion eliminada con exito", HttpStatus.OK);
    }
}
