/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;


import com.recaudacionMunicipio.DTO.PeriodicidadDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.PeriodicidadServicioImpl;
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
@RequestMapping("/api/periodicidad")
public class PeriodicidadControlador {
    
    @Autowired
    private PeriodicidadServicioImpl periodicidadImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public List<PeriodicidadDTO> listarPeriodicidad(){
        return periodicidadImplSer.findAllP();
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<PeriodicidadDTO> listarPeriodicidad(@PathVariable Integer page){
        return periodicidadImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<PeriodicidadDTO> obtenerPeriodicidad(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(periodicidadImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<PeriodicidadDTO> guardarPeriodicidad(@RequestBody PeriodicidadDTO  periodicidadDTO) {
        return new ResponseEntity<>(periodicidadImplSer.save(periodicidadDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<PeriodicidadDTO> actualizarPeriodicidad(@RequestBody PeriodicidadDTO periodicidadDTO, @PathVariable(name = "id") Integer id) {
        PeriodicidadDTO periodicidadRespuesta = periodicidadImplSer.update(periodicidadDTO, id);
        return new ResponseEntity<>(periodicidadRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPeriodicidad(@PathVariable(name = "id") Integer id) {
        periodicidadImplSer.delete(id);
        return new ResponseEntity<>("periodicidad eliminada con exito", HttpStatus.OK);
    }
}
