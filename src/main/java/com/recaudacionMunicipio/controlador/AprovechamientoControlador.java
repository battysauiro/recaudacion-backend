/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;



import com.recaudacionMunicipio.DTO.AprovechamientoDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.AprovechamientoServicioImpl;
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
@RequestMapping("/api/aprovechamiento")
public class AprovechamientoControlador {
    
    @Autowired
    private AprovechamientoServicioImpl aprovechamientoSer;

    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public entidadRespuesta<AprovechamientoDTO> listarAprovechamiento(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return aprovechamientoSer.findAll(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full")
    public entidadRespuesta<AprovechamientoCompletoDTO> listarContribucionCompleta(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return aprovechamientoSer.findAllC(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<AprovechamientoDTO> listarAprovechamiento(@PathVariable Integer page){
        return aprovechamientoSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public Page<AprovechamientoCompletoDTO> listarContribucionCompleta(@PathVariable Integer page){
        return aprovechamientoSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<AprovechamientoDTO> obtenerAprovechamiento(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/{id}")
    public ResponseEntity<AprovechamientoCompletoDTO> obtenerAprovechamientoCompleto(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoSer.findByIdCompleto(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<AprovechamientoDTO> guardarAprovechamiento(@RequestBody AprovechamientoDTO aprovechamientoDTO) {
        return new ResponseEntity<>(aprovechamientoSer.save(aprovechamientoDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<AprovechamientoDTO> actualizarAprovechamiento(@RequestBody AprovechamientoDTO aprovechamientoDTO, @PathVariable(name = "id") String id) {
        AprovechamientoDTO aprovechamientoRespuesta = aprovechamientoSer.update(aprovechamientoDTO, id);
        return new ResponseEntity<>(aprovechamientoRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarAprovechamiento(@PathVariable(name = "id") String id) {
        aprovechamientoSer.delete(id);
        return new ResponseEntity<>("aprovechamiento eliminado con exito", HttpStatus.OK);
    }
}
