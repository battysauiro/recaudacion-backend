/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.EmpleadoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.EmpleadoServicioImpl;
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
@RequestMapping("/api/empleado")
public class EmpleadoControlador {
    
    @Autowired
    private EmpleadoServicioImpl EmpleadoSer;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<EmpleadoDTO> listarEmpleado(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "6",required = false)int cantidadPagina){
        return EmpleadoSer.findAll(page,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public List<EmpleadoDTO> listarEmpleado(){
        return EmpleadoSer.findAllEmpleados();
    }
    
    //@Secured({"ROLE_ADMIN"})
    //@GetMapping("/page/inicio/{page}")
    //public Page<EmpleadoDTO> listarEmpleado(@PathVariable Integer page){
      //  return EmpleadoSer.findAll(PageRequest.of(page,10));
    //}
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerEmpleado(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(EmpleadoSer.findById(id));
    }
    
    /**@Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<EmpleadoDTO> guardarEmpleado(@RequestBody EmpleadoDTO empleadoDTO) {
        return new ResponseEntity<>(EmpleadoSer.save(empleadoDTO), HttpStatus.CREATED);
    }*/
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping()    
    public ResponseEntity<EmpleadoDTO> crearEmpleado(@RequestBody EmpleadoDTO  empleadoDTO) {
        Object obj=EmpleadoSer.crear(empleadoDTO);
        System.out.println((obj instanceof EmpleadoDTO)+" es instancia ");
        if(!(obj instanceof EmpleadoDTO)){
            if((int)obj ==0){
                System.out.println("el empleado ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
            return new ResponseEntity<>((EmpleadoDTO)obj, HttpStatus.CREATED);
        
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> actualizarEmpleado(@RequestBody EmpleadoDTO empleadoDTO, @PathVariable(name = "id") String id) {
        EmpleadoDTO tipoContribucionRespuesta = EmpleadoSer.update(empleadoDTO, id);
        return new ResponseEntity<>(tipoContribucionRespuesta, HttpStatus.OK);
    }
    
    //@Secured({"ROLE_ADMIN"})
    //@DeleteMapping("/{id}")
    //public ResponseEntity<String> eliminarEmpleado(@PathVariable(name = "id") Integer id) {
      //  EmpleadoSer.delete(id);
        //return new ResponseEntity<>("empleado eliminado con exito", HttpStatus.OK);
    //}
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable String id){
		//Empleado empleado = repositorio.findById(id)
		//		            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
        EmpleadoSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
