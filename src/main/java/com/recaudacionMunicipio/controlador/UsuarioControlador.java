/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.UsuarioDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.modelo.Usuario;
import com.recaudacionMunicipio.servicios.UsuarioServicio;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Oscar
 */
@RestController 
@RequestMapping("/api/usuario")
public class UsuarioControlador {
    
   @Autowired
    private UsuarioServicio usuarioImplSer;
    
    /**@GetMapping("/{id}")
    public ResponseEntity<Usuario>obtenerUser(@PathVariable(name = "id") String username){
        //return usuarioImplSer.findByUsername(username);
        return ResponseEntity.ok(usuarioImplSer.findByUsername(username));
    } */
   
   
   @Secured({"ROLE_ADMIN"})
   @GetMapping("/page/{page}")
    public entidadRespuesta<UsuarioDTO> listarUsuario(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return usuarioImplSer.findAll(page,cantidadPagina);
    }
    
    //@GetMapping("/page/{page}")
    //public Page<UsuarioDTO> listarUsuario(@PathVariable Integer page){
      //  return usuarioImplSer.findAll(PageRequest.of(page,10));
    //}
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(usuarioImplSer.findById(id));
    }
    
    /**@PostMapping
    public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioDTO  usuarioDTO) {
        return new ResponseEntity<>(usuarioImplSer.save(usuarioDTO), HttpStatus.CREATED);
    }*/
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping()    
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO  usuarioDTO) {
        Object obj=usuarioImplSer.crear(usuarioDTO);
        System.out.println((obj instanceof UsuarioDTO)+" es instancia ");
        if(!(obj instanceof UsuarioDTO)){
            if((int)obj ==0){
                System.out.println("el usuario ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);}
        }
            return new ResponseEntity<>((UsuarioDTO)obj, HttpStatus.CREATED);
        
    }
    
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("username") String username){
       Map<String, Object> respuesta = new HashMap<>();
       UsuarioDTO usuarioRespuesta=null;
       UsuarioDTO usuario = usuarioImplSer.findById(username);
       if(!archivo.isEmpty()){
           String nombreArchivo= UUID.randomUUID().toString()+ "_"+ archivo.getOriginalFilename().replace(" ",  "");
           Path rutaArchivo= Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
           
           try {
               Files.copy(archivo.getInputStream(), rutaArchivo);
           } catch (IOException ex) {
               respuesta.put("mensaje", "Error al subir la imagen "+nombreArchivo);
               respuesta.put("mensaje", ex.getMessage().concat(": ").concat(ex.getCause().getMessage()));
               return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
           }
           
           String nombreFotoAnterior=usuario.getUrl_imagen();
           if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0){
               Path rutaFotoAnterior= Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
               File archivoFotoAnterior=rutaFotoAnterior.toFile();
               if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                   archivoFotoAnterior.delete();
               }
           }
           //usuario.setUrl_imagen(nombreArchivo);
           usuarioRespuesta = usuarioImplSer.updateImagen(nombreArchivo, username);
           
           respuesta.put("usuario", usuarioRespuesta);
           respuesta.put("mensaje","Has subido correctamene la imagen: "+nombreArchivo );
       }
       return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Path rutaArchivo= Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        Resource recurso=null;
       try {
           recurso = new UrlResource(rutaArchivo.toUri());
       } catch (MalformedURLException ex) {
           Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        if(!recurso.exists() && !recurso.isReadable()){
            rutaArchivo= Paths.get("src/main/resources/static/static/imagenes").resolve("no_usuario.png").toAbsolutePath();
        }
        HttpHeaders cabecera= new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO, @PathVariable(name = "id") String id) {
        UsuarioDTO usuarioRespuesta = usuarioImplSer.update(usuarioDTO, id);
        return new ResponseEntity<>(usuarioRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}/{estado}")
    public ResponseEntity<UsuarioDTO> actualizarEstadosUsuario(@PathVariable(name = "id") String id,@PathVariable(name = "estado") boolean estado) {
        UsuarioDTO usuarioRespuesta = usuarioImplSer.updateEstado(estado, id);
        return new ResponseEntity<>(usuarioRespuesta, HttpStatus.OK);
    }
    
    /**@DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable(name = "id") String id) {
        usuarioImplSer.delete(id);
        return new ResponseEntity<>("usuario eliminado con exito", HttpStatus.OK);
    }*/
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable String id){
		//Empleado empleado = repositorio.findById(id)
		//		            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
        usuarioImplSer.delete(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}
