/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoMultaCompletaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.AprovechamientoMultaServicioImpl;
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
@RequestMapping("/api/aprovechamientoMulta")
public class AprovechamientoMultaControlador {
    
    @Autowired
    private AprovechamientoMultaServicioImpl aprovechamientoMultaSer;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<AprovechamientoMultaDTO> listarAprovechamientoMulta(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return aprovechamientoMultaSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full")
    public entidadRespuesta<AprovechamientoMultaCompletaDTO> listarMultaCompleta(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return aprovechamientoMultaSer.findAllC(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/filtrar/{idContribucionMulta}")
    public List<AprovechamientoMultaDTO> AprovechamientoMultaById(@PathVariable String idContribucionMulta){
        return aprovechamientoMultaSer.findByIdContribucion(idContribucionMulta);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/page/{page}")
    public Page<AprovechamientoMultaCompletaDTO> listarMultaCompleta(@PathVariable Integer page){
        return aprovechamientoMultaSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<AprovechamientoMultaDTO> obtenerAprovechamientoMulta(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoMultaSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/full/{id}")
    public ResponseEntity<AprovechamientoMultaCompletaDTO> obtenerAprovechamientoMultaCompleta(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoMultaSer.findByIdCompleto(id));
    }
    /**
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<AprovechamientoMultaDTO> guardarAprovechamientoMulta(@RequestBody AprovechamientoMultaDTO aprovechamientoMultaDTO) {
        return new ResponseEntity<>(aprovechamientoMultaSer.save(aprovechamientoMultaDTO), HttpStatus.CREATED);
    }*/
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping()    
    public ResponseEntity<AprovechamientoMultaDTO> crearAprovechamientoMulta(@RequestBody AprovechamientoMultaDTO aprovechamientoMultaDTO) {
        Object obj=aprovechamientoMultaSer.crear(aprovechamientoMultaDTO);
        System.out.println((obj instanceof AprovechamientoMultaDTO)+" es instancia ");
        if(!(obj instanceof AprovechamientoMultaDTO)){
            if((int)obj ==0){
                System.out.println("la contribucion ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
            return new ResponseEntity<>((AprovechamientoMultaDTO)obj, HttpStatus.CREATED);
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<AprovechamientoMultaDTO> actualizarAprovechamientoMulta(@RequestBody AprovechamientoMultaDTO aprovechamientoMultaDTO, @PathVariable(name = "id") String id) {
        AprovechamientoMultaDTO aprovechamientoMultaRespuesta = aprovechamientoMultaSer.update(aprovechamientoMultaDTO, id);
        return new ResponseEntity<>(aprovechamientoMultaRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarAprovechamientoMulta(@PathVariable String id){
        aprovechamientoMultaSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}