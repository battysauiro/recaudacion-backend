/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.DerechosLicenciaDTO;
import com.recaudacionMunicipio.DTO.entidades.DerechosLicenciaCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.DerechosLicenciasServicioImpl;
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
@RequestMapping("/api/derechoLicencias")
public class DerechoLicenciasControlador {
    
    @Autowired
    private DerechosLicenciasServicioImpl DerechoLicenciasImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<DerechosLicenciaDTO> listarDerechosLicencias(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return DerechoLicenciasImplSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("i/page/{page}")
    public entidadRespuesta<DerechosLicenciaCompletoDTO> listarDerechosLicenciaCompleto(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return DerechoLicenciasImplSer.findAllC(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("inicio/page/{page}")
    public Page<DerechosLicenciaDTO> listarDerechosLicencias(@PathVariable Integer page){
        return DerechoLicenciasImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public Page<DerechosLicenciaCompletoDTO> listarDerechosLicenciaCompleto(@PathVariable Integer page){
        return DerechoLicenciasImplSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<DerechosLicenciaDTO> obtenerDerechoLicencia(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(DerechoLicenciasImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/{id}")
    public ResponseEntity<DerechosLicenciaCompletoDTO> obtenerDerechoLicenciaCompleto(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(DerechoLicenciasImplSer.findByIdCompleto(id));
    }
    /**
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<DerechosLicenciaDTO> guardarDerechoLicencia(@RequestBody DerechosLicenciaDTO derechosLicenciaDTO) {
        return new ResponseEntity<>(DerechoLicenciasImplSer.save(derechosLicenciaDTO), HttpStatus.CREATED);
    }*/
    @Secured({"ROLE_ADMIN"})
    @PostMapping()    
    public ResponseEntity<DerechosLicenciaDTO> crearDerechoLicencia(@RequestBody DerechosLicenciaDTO derechosLicenciaDTO) {
        Object obj=DerechoLicenciasImplSer.crear(derechosLicenciaDTO);
        System.out.println((obj instanceof DerechosLicenciaDTO)+" es instancia ");
        if(!(obj instanceof DerechosLicenciaDTO)){
            if((int)obj ==0){
                System.out.println("la contribucion ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
            return new ResponseEntity<>((DerechosLicenciaDTO)obj, HttpStatus.CREATED);
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<DerechosLicenciaDTO> actualizarDerechoLicencia(@RequestBody DerechosLicenciaDTO derechosLicenciaDTO, @PathVariable(name = "id") String id) {
        DerechosLicenciaDTO derechoLicenciaRespuesta = DerechoLicenciasImplSer.update(derechosLicenciaDTO, id);
        return new ResponseEntity<>(derechoLicenciaRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarDerechoLicencia(@PathVariable String id){
        DerechoLicenciasImplSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
