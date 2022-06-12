/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.ContribuyenteFisicaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.ContribuyenteFisicaServicioImpl;
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
@RequestMapping("/api/contribuyenteFisica")
public class ContribuyenteFisicaControlador {
    
    @Autowired
    private ContribuyenteFisicaServicioImpl contribuyenteFisicaImplSer;
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<ContribuyenteFisicaDTO> listarContribuyentesFisica(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return contribuyenteFisicaImplSer.findAll(page,cantidadPagina);
}
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/page/test/{page}")
    public Page<ContribuyenteFisicaDTO> listarContribuyentesFisica(@PathVariable Integer page){
        return contribuyenteFisicaImplSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/{id}")
    public ResponseEntity<ContribuyenteFisicaDTO> obtenerContribuyenteFisica(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(contribuyenteFisicaImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/filtrar/{term}")
    public List<ContribuyenteFisicaDTO> contribuyenteByCurp(@PathVariable String term){
        return contribuyenteFisicaImplSer.findByCurp(term);
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO"})
    @PostMapping()    
    public ResponseEntity<ContribuyenteFisicaDTO> guardarContribuyenteFisica(@RequestBody ContribuyenteFisicaDTO contribuyenteFisicaDTO) {
        Object obj=contribuyenteFisicaImplSer.crear(contribuyenteFisicaDTO);
        System.out.println((obj instanceof ContribuyenteFisicaDTO)+" es instancia ");
        if(!(obj instanceof ContribuyenteFisicaDTO)){
            if((int)obj ==0){
                System.out.println("el contribuyente ya existe");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);}
            if((int)obj ==1){
                System.out.println("la curp a existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
        
            System.out.println("entroe en crear al contribuyente bien ");
            return new ResponseEntity<>((ContribuyenteFisicaDTO)obj, HttpStatus.CREATED);
        
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO"})
    @PutMapping("/{id}")
    public ResponseEntity<ContribuyenteFisicaDTO> actualizarContribuyenteFisica(@RequestBody ContribuyenteFisicaDTO contribuyenteFisicaDTO, @PathVariable(name = "id") String id) {
        ContribuyenteFisicaDTO contribuyenteFisicaRespuesta = contribuyenteFisicaImplSer.update(contribuyenteFisicaDTO, id);
        return new ResponseEntity<>(contribuyenteFisicaRespuesta, HttpStatus.OK);
    }
    
    //@Secured({"ROLE_ADMIN"})
   /* @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarContribuyenteFisica(@PathVariable(name = "id") Integer id) {
        contribuyenteFisicaImplSer.delete(id);
        return new ResponseEntity<>("contribuyente fisica eliminado con exito", HttpStatus.OK);
    }*/
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}") 
	public ResponseEntity<Map<String,Boolean>> eliminarContribuyenteFisica1(@PathVariable String id){
		//Empleado empleado = repositorio.findById(id)
		//		            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
		contribuyenteFisicaImplSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
