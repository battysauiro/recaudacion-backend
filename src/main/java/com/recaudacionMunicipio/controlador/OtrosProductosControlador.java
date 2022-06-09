/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.OtrosProductosDTO;
import com.recaudacionMunicipio.DTO.entidades.OtrosProductosCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.OtrosProductosServicioImpl;
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
@RequestMapping("/api/otrosProductos")
public class OtrosProductosControlador {
    
    @Autowired
    private OtrosProductosServicioImpl otrosProductosImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<OtrosProductosDTO> listarOtrosProductos(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return otrosProductosImplSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full")
    public entidadRespuesta<OtrosProductosCompletoDTO> listarOtrosProductosCompleto(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return otrosProductosImplSer.findAllC(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("i/page/{page}")
    public Page<OtrosProductosDTO> listarOtrosProductos(@PathVariable Integer page){
        return otrosProductosImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public Page<OtrosProductosCompletoDTO> listarOtrosProductosCompleto(@PathVariable Integer page){
        return otrosProductosImplSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<OtrosProductosDTO> obtenerOtrosProductos(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(otrosProductosImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/{id}")
    public ResponseEntity<OtrosProductosCompletoDTO> obtenerOtrosProductosCompleto(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(otrosProductosImplSer.findByIdCompleto(id));
    }
    /**
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<OtrosProductosDTO> guardarOtrosProductos(@RequestBody OtrosProductosDTO otrosProductosDTO) {
        return new ResponseEntity<>(otrosProductosImplSer.save(otrosProductosDTO), HttpStatus.CREATED);
    }*/
    @Secured({"ROLE_ADMIN"})
    @PostMapping()    
    public ResponseEntity<OtrosProductosDTO> crearOtrosProductos(@RequestBody OtrosProductosDTO otrosProductosDTO) {
        Object obj=otrosProductosImplSer.crear(otrosProductosDTO);
        System.out.println((obj instanceof OtrosProductosDTO)+" es instancia ");
        if(!(obj instanceof OtrosProductosDTO)){
            if((int)obj ==0){
                System.out.println("la contribucion ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
            return new ResponseEntity<>((OtrosProductosDTO)obj, HttpStatus.CREATED);
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<OtrosProductosDTO> actualizarOtrosProductos(@RequestBody OtrosProductosDTO otrosProductosDTO, @PathVariable(name = "id") String id) {
        OtrosProductosDTO otrosProductosRespuesta = otrosProductosImplSer.update(otrosProductosDTO, id);
        return new ResponseEntity<>(otrosProductosRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarOtrosProductos(@PathVariable String id){
        otrosProductosImplSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
