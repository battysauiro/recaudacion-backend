/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaEbriedadDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoMultaEbriedadCompletaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.modelo.Multaebriedad;
import com.recaudacionMunicipio.servicios.AprovechamientoMultaEbriedadServicioImpl;
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
@RequestMapping("/api/multaEbriedad")
public class AprovechamientoMultaEbriedadControlador {
    
    @Autowired
    private AprovechamientoMultaEbriedadServicioImpl aprovechamientoMultaEbriedadSer;

    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/page/{page}")
    public entidadRespuesta listarMultasEbriedad(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return aprovechamientoMultaEbriedadSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("i/page/{page}")   
    public Page<AprovechamientoMultaEbriedadDTO> listarMultasEbriedad(@PathVariable Integer page){
        return aprovechamientoMultaEbriedadSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/full")
    public entidadRespuesta<AprovechamientoMultaEbriedadCompletaDTO> listarMultaEbriedadCompleta(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return aprovechamientoMultaEbriedadSer.findAllC(numeroDePagina,cantidadPagina);   
    }
    
    //@Secured({"ROLE_ADMIN"})
    //@GetMapping("/full/page/{page}")
    //public Page<Multaebriedad> listarMultaEbriedadCompleta(@PathVariable Integer page){
     //   return aprovechamientoMultaEbriedadSer.findAllCc(PageRequest.of(page,10));
    //}
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/{id}")
    public ResponseEntity<AprovechamientoMultaEbriedadDTO> obtenerMultaEbriedad(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoMultaEbriedadSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/full/{id}")
    public ResponseEntity<AprovechamientoMultaEbriedadCompletaDTO> obtenerAprovechamientoMultaEbriedadCompleta(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(aprovechamientoMultaEbriedadSer.findByIdCompleto(id));
    }
     /**
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<AprovechamientoMultaEbriedadDTO> guardarAprovechamientoMultaEbriedad(@RequestBody AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO) {
        return new ResponseEntity<>(aprovechamientoMultaEbriedadSer.save(aprovechamientoMultaEbriedadDTO), HttpStatus.CREATED);
    }*/
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping()    
    public ResponseEntity<AprovechamientoMultaEbriedadDTO> crearAprovechamientoMultaEbriedad(@RequestBody AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO) {
        Object obj=aprovechamientoMultaEbriedadSer.crear(aprovechamientoMultaEbriedadDTO);
        System.out.println((obj instanceof AprovechamientoMultaEbriedadDTO)+" es instancia ");
        if(!(obj instanceof AprovechamientoMultaEbriedadDTO)){
            if((int)obj ==0){
                System.out.println("la contribucion ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
            return new ResponseEntity<>((AprovechamientoMultaEbriedadDTO)obj, HttpStatus.CREATED);
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<AprovechamientoMultaEbriedadDTO> actualizarAprovechamientoMultaEbriedad(@RequestBody AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadDTO, @PathVariable(name = "id") String id) {
        AprovechamientoMultaEbriedadDTO aprovechamientoMultaEbriedadRespuesta = aprovechamientoMultaEbriedadSer.update(aprovechamientoMultaEbriedadDTO, id);
        return new ResponseEntity<>(aprovechamientoMultaEbriedadRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarAprovechamientoMultaEbriedad(@PathVariable String id){
        aprovechamientoMultaEbriedadSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
