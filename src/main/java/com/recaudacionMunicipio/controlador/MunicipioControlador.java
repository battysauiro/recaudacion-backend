/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.MunicipiosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.MunicipiosServicioImpl;
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
@RequestMapping("/api/municipios")
public class MunicipioControlador {
    
    @Autowired
    private MunicipiosServicioImpl municipiosImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public List<MunicipiosDTO> listarMunicipios(){
        return municipiosImplSer.findAllM();
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<MunicipiosDTO> listarMunicipios(@PathVariable Integer page){
        return municipiosImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<MunicipiosDTO> obtenerMunicipio(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(municipiosImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<MunicipiosDTO> guardarMunicipio(@RequestBody MunicipiosDTO  municipioDTO) {
        return new ResponseEntity<>(municipiosImplSer.save(municipioDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<MunicipiosDTO> actualizarMunicipio(@RequestBody MunicipiosDTO municipioDTO, @PathVariable(name = "id") Integer id) {
        MunicipiosDTO municipioRespuesta = municipiosImplSer.update(municipioDTO, id);
        return new ResponseEntity<>(municipioRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMunicipio(@PathVariable(name = "id") Integer id) {
        municipiosImplSer.delete(id);
        return new ResponseEntity<>("municipio eliminado con exito", HttpStatus.OK);
    }
}
