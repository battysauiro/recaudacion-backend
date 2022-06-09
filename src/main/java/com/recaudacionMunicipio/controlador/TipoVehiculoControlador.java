/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.TipoVehiculoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.TipoVehiculoServicioImpl;
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
@RequestMapping("/api/tipoVehiculo")
public class TipoVehiculoControlador {
    
    @Autowired
    private TipoVehiculoServicioImpl tipoVehiculoImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public List<TipoVehiculoDTO> listarTipoVehiculo(){
        return tipoVehiculoImplSer.findAll();
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<TipoVehiculoDTO> listarTipoVehiculo(@PathVariable Integer page){
        return tipoVehiculoImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<TipoVehiculoDTO> obtenerTipoVehiculo(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(tipoVehiculoImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<TipoVehiculoDTO> guardarTipoVehiculo(@RequestBody TipoVehiculoDTO  tipoVehiculoDTO) {
        return new ResponseEntity<>(tipoVehiculoImplSer.save(tipoVehiculoDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<TipoVehiculoDTO> actualizarTipoVehiculo(@RequestBody TipoVehiculoDTO tipoVehiculoDTO, @PathVariable(name = "id") Integer id) {
        TipoVehiculoDTO tipoVehiculoRespuesta = tipoVehiculoImplSer.update(tipoVehiculoDTO, id);
        return new ResponseEntity<>(tipoVehiculoRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTipoVehiculo(@PathVariable(name = "id") Integer id) {
        tipoVehiculoImplSer.delete(id);
        return new ResponseEntity<>("tipo vehiculo eliminado con exito", HttpStatus.OK);
    }
}
