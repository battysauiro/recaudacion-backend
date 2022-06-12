/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.PalacioMunicipalDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.PalacioMunicipalServicioImpl;
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
@RequestMapping("/api/palacioMunicipal")
public class PalacioMunicipalControlador {
    
    @Autowired
    private PalacioMunicipalServicioImpl palacioMunicipalImplSer;
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE"})
    @GetMapping()
    public List<PalacioMunicipalDTO> listarPalaciosMunicipales() {
        return palacioMunicipalImplSer.findAllC();
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<PalacioMunicipalDTO> listarPalacios(@PathVariable Integer page, @RequestParam(value = "pageSize", defaultValue = "6", required = false) int cantidadPagina) {
        return palacioMunicipalImplSer.findAll(page, cantidadPagina);
    }

    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE"})
    @GetMapping("/Ppage/{page}")
    public Page<PalacioMunicipalDTO> listarPalaciosMunicipales(@PathVariable Integer page) {
        return palacioMunicipalImplSer.findAll(PageRequest.of(page, 10));
    }

    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE"})
    @GetMapping("/{id}")
    public ResponseEntity<PalacioMunicipalDTO> obtenerPalacioMunicipal(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(palacioMunicipalImplSer.findById(id));
    }
    
    /**
     * @Secured({"ROLE_ADMIN"})
     * @PostMapping public ResponseEntity<PalacioMunicipalDTO>
     * guardarPalacioMunicipal(@RequestBody PalacioMunicipalDTO palacioDTO) {
     * return new ResponseEntity<>(palacioMunicipalImplSer.save(palacioDTO),
     * HttpStatus.CREATED);
    }
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping()
    public ResponseEntity<PalacioMunicipalDTO> crearPalacioMunicipal(@RequestBody PalacioMunicipalDTO palacioDTO) {
        Object obj = palacioMunicipalImplSer.crear(palacioDTO);
        if (!(obj instanceof PalacioMunicipalDTO)) {
            if ((int) obj == 0) {
                System.out.println("el municipio ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);
            }
        }
    
        return new ResponseEntity<>((PalacioMunicipalDTO) obj, HttpStatus.CREATED);

    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<PalacioMunicipalDTO> actualizarPalacioMunicipal(@RequestBody PalacioMunicipalDTO palacioDTO, @PathVariable(name = "id") Integer id) {
        PalacioMunicipalDTO palacioRespuesta = palacioMunicipalImplSer.update(palacioDTO, id);
        return new ResponseEntity<>(palacioRespuesta, HttpStatus.OK);
    }
    
    /**
     * @Secured({"ROLE_ADMIN"})
     * @DeleteMapping("/{id}") public ResponseEntity<String>
     * eliminarPalacioMunicipal(@PathVariable(name = "id") Integer id) {
     * palacioMunicipalImplSer.delete(id); return new ResponseEntity<>("palacio
     * municipal eliminado con exito", HttpStatus.OK);
    }
     */
    
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("idPalacio") Integer idPalacio){
       Map<String, Object> respuesta = new HashMap<>();
       PalacioMunicipalDTO palacioRespuesta=null;
       PalacioMunicipalDTO palacioMunicipal = palacioMunicipalImplSer.findById(idPalacio);
       if(!archivo.isEmpty()){
           String nombreArchivo= UUID.randomUUID().toString()+ "_"+ archivo.getOriginalFilename().replace(" ",  "");
           Path rutaArchivo= Paths.get("uploads/img_municipios").resolve(nombreArchivo).toAbsolutePath();
           
           try {
               Files.copy(archivo.getInputStream(), rutaArchivo);
           } catch (IOException ex) {
               respuesta.put("mensaje", "Error al subir la imagen "+nombreArchivo);
               respuesta.put("mensaje", ex.getMessage().concat(": ").concat(ex.getCause().getMessage()));
               return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
           }
           
           String nombreFotoAnterior=palacioMunicipal.getImagen();
           if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0){
               Path rutaFotoAnterior= Paths.get("uploads/img_municipios").resolve(nombreFotoAnterior).toAbsolutePath();
               File archivoFotoAnterior=rutaFotoAnterior.toFile();
               if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()){
                   archivoFotoAnterior.delete();
               }
           }
           //usuario.setUrl_imagen(nombreArchivo);
           palacioRespuesta = palacioMunicipalImplSer.updateImagen(nombreArchivo, idPalacio);
           
           respuesta.put("palacio", palacioRespuesta);
           respuesta.put("mensaje","Has subido correctamene la imagen: "+nombreArchivo );
       }
       return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.CREATED);
    }
    
    @GetMapping("/uploads/img/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
        Path rutaArchivo= Paths.get("uploads/img_municipios").resolve(nombreFoto).toAbsolutePath();
        Resource recurso=null;
       try {
           recurso = new UrlResource(rutaArchivo.toUri());
       } catch (MalformedURLException ex) {
           Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
       }
       
        if(!recurso.exists() && !recurso.isReadable()){
            rutaArchivo= Paths.get("src/main/resources/static/static/imagenes_municipios").resolve("no_usuario.png").toAbsolutePath();
        }
        HttpHeaders cabecera= new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+recurso.getFilename()+"\"");
        return new ResponseEntity<Resource>(recurso,cabecera,HttpStatus.OK);
    }
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPalacioMunicipal(@PathVariable Integer id) {
        //Empleado empleado = repositorio.findById(id)
        //		            .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));

        palacioMunicipalImplSer.delete(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
