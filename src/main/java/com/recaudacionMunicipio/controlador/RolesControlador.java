/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.RolesDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.RolesServicioImpl;
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
@RequestMapping("/api/roles")
public class RolesControlador {
    
    @Autowired
    private RolesServicioImpl rolesImplSer;
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public List<RolesDTO> listarRoles(){
        return rolesImplSer.findAllRoles();
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<RolesDTO> listarRoles(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return rolesImplSer.findAllRoles(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<RolesDTO> obtenerRol(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(rolesImplSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<RolesDTO> guardarRol(@RequestBody RolesDTO  rolesDTO) {
        return new ResponseEntity<>(rolesImplSer.save(rolesDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<RolesDTO> actualizarRol(@RequestBody RolesDTO rolesDTO, @PathVariable(name = "id") Integer id) {
        RolesDTO rolesRespuesta = rolesImplSer.update(rolesDTO, id);
        return new ResponseEntity<>(rolesRespuesta, HttpStatus.OK);
    }
    
    /**@Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable(name = "id") Integer id) {
        rolesImplSer.delete(id);
        return new ResponseEntity<>("rol eliminado con exito", HttpStatus.OK);
    }*/
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}") 
	public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Integer id){
		rolesImplSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
