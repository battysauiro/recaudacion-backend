/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.CatalogosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.CatalogoOtrosProductosServicioImpl;
import java.util.List;
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
@RequestMapping("/api/catalogoOtrosProductos")
public class CatalogoOtrosProductosControlador {
    
    @Autowired
    private CatalogoOtrosProductosServicioImpl catalogoOtrosProductosSer;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<CatalogosDTO> listarCatalogo(@RequestParam(value="pageNo",defaultValue = "0",required = false)int numeroDePagina,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina){
        return catalogoOtrosProductosSer.findAll(numeroDePagina,cantidadPagina);
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping()
    public List<CatalogosDTO> listarCatalogo(){
        return catalogoOtrosProductosSer.findAllCatalogo();
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("inicio/page/{page}")
    public Page<CatalogosDTO> listarCatalogo(@PathVariable Integer page){
        return catalogoOtrosProductosSer.findAll(PageRequest.of(page,10));
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<CatalogosDTO> obtenerCatalogo(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(catalogoOtrosProductosSer.findById(id));
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<CatalogosDTO> guardarCatalogo(@RequestBody CatalogosDTO  catalogoDTO) {
        return new ResponseEntity<>(catalogoOtrosProductosSer.save(catalogoDTO), HttpStatus.CREATED);
    }
    
    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<CatalogosDTO> actualizarCatalogo(@RequestBody CatalogosDTO catalogoDTO, @PathVariable(name = "id") Integer id) {
        CatalogosDTO otrosProductosRespuesta = catalogoOtrosProductosSer.update(catalogoDTO, id);
        return new ResponseEntity<>(otrosProductosRespuesta, HttpStatus.OK);
    }
    
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCatalogo(@PathVariable(name = "id") Integer id) {
        catalogoOtrosProductosSer.delete(id);
        return new ResponseEntity<>("catalogo otros productos eliminado con exito", HttpStatus.OK);
    }
}
