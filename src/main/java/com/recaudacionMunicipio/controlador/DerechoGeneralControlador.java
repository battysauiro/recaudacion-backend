/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.DerechosGeneralDTO;
import com.recaudacionMunicipio.DTO.entidades.DerechosGeneralCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.DerechoGeneralServicioImpl;
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
@RequestMapping("/api/derechoGeneral")
public class DerechoGeneralControlador {
    
    @Autowired
    private DerechoGeneralServicioImpl DerechoGeneralImplSer;
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<DerechosGeneralDTO> listarDerechosGeneral(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return DerechoGeneralImplSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/full")
    public entidadRespuesta<DerechosGeneralCompletoDTO> listarDerechosGeneralCompleto(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return DerechoGeneralImplSer.findAllC(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("i/page/{page}")
    public Page<DerechosGeneralDTO> listarDerechosGeneral(@PathVariable Integer page){
        return DerechoGeneralImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/full/page/{page}")
    public Page<DerechosGeneralCompletoDTO> listarDerechosGeneralCompleto(@PathVariable Integer page){
        return DerechoGeneralImplSer.findAllC(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/{id}")
    public ResponseEntity<DerechosGeneralDTO> obtenerDerechoGeneral(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(DerechoGeneralImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/full/{id}")
    public ResponseEntity<DerechosGeneralCompletoDTO> obtenerDerechoGeneralCompleto(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(DerechoGeneralImplSer.findByIdCompleto(id));
    }
    /**
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<DerechosGeneralDTO> guardarDerechoGeneral(@RequestBody DerechosGeneralDTO derechosGeneralDTO) {
        return new ResponseEntity<>(DerechoGeneralImplSer.save(derechosGeneralDTO), HttpStatus.CREATED);
    }*/
    @Secured({"ROLE_ADMIN"})
    @PostMapping()    
    public ResponseEntity<DerechosGeneralDTO> crearDerechoGeneral(@RequestBody DerechosGeneralDTO derechosGeneralDTO) {
        Object obj=DerechoGeneralImplSer.crear(derechosGeneralDTO);
        System.out.println((obj instanceof DerechosGeneralDTO)+" es instancia ");
        if(!(obj instanceof DerechosGeneralDTO)){
            if((int)obj ==0){
                System.out.println("la contribucion ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
            return new ResponseEntity<>((DerechosGeneralDTO)obj, HttpStatus.CREATED);
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<DerechosGeneralDTO> actualizarDerechoGeneral(@RequestBody DerechosGeneralDTO derechosGeneralDTO, @PathVariable(name = "id") String id) {
        DerechosGeneralDTO derechoGeneralRespuesta = DerechoGeneralImplSer.update(derechosGeneralDTO, id);
        return new ResponseEntity<>(derechoGeneralRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarDerechoGeneral(@PathVariable String id){
        DerechoGeneralImplSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
