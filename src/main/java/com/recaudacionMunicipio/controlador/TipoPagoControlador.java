/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.TipoPagoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.TipoPagoServicioImpl;
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
@RequestMapping("/api/tipoPago")
public class TipoPagoControlador {
    
    @Autowired
    private TipoPagoServicioImpl tipoPagoImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public List<TipoPagoDTO> listarTiposPagos(){
        return tipoPagoImplSer.findAll();
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public Page<TipoPagoDTO> listarTiposPagos(@PathVariable Integer page){
        return tipoPagoImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<TipoPagoDTO> obtenerTipoPago(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(tipoPagoImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<TipoPagoDTO> guardarTipoPago(@RequestBody TipoPagoDTO  tipoPagoDTO) {
        return new ResponseEntity<>(tipoPagoImplSer.save(tipoPagoDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<TipoPagoDTO> actualizarTipoPago(@RequestBody TipoPagoDTO tipoPagoDTO, @PathVariable(name = "id") Integer id) {
        TipoPagoDTO tipoPagoRespuesta = tipoPagoImplSer.update(tipoPagoDTO, id);
        return new ResponseEntity<>(tipoPagoRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTipoPago(@PathVariable(name = "id") Integer id) {
        tipoPagoImplSer.delete(id);
        return new ResponseEntity<>("tipo Pago eliminado con exito", HttpStatus.OK);
    }
}
