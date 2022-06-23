/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.FacturaDTO;
import com.recaudacionMunicipio.modelo.Factura;
import com.recaudacionMunicipio.servicios.ContribuyenteServicioImpl;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */
@RestController 
@RequestMapping("/api")
public class FacturaControlador {
    
    @Autowired
    private ContribuyenteServicioImpl contribuyenteImplSer;
    
    @GetMapping("/facturas/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public FacturaDTO show(@PathVariable int id){
        return contribuyenteImplSer.findFacturaById(id);
    }
    
    @GetMapping("/facturas-contribuyente/{id}/{contribucion}")
    @ResponseStatus(code = HttpStatus.OK)
    public boolean byContribuyente(@PathVariable String id,@PathVariable String contribucion){
        return contribuyenteImplSer.findByContribuyenteId(id,contribucion);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/facturas/{id}") 
	public ResponseEntity<Map<String,Boolean>> eliminarContribuyenteFisica1(@PathVariable int id){
		contribuyenteImplSer.deleteFacturaById(id);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
    
    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO"})
    @PostMapping("/facturas")    
    public ResponseEntity<FacturaDTO> crearFactura(@RequestBody FacturaDTO facturaDTO ) {
        Factura factura=contribuyenteImplSer.saveFactura(facturaDTO);
        
        return new ResponseEntity<>(new FacturaDTO(), HttpStatus.CREATED); 
    }
    
    @GetMapping("/facturas/img/idsadministracion")
    public ResponseEntity<Resource> verFoto(){
        Path rutaArchivo= Paths.get("uploads").resolve("logoIDS.PNG").toAbsolutePath();
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
        
}
