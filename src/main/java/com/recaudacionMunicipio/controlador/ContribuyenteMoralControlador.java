/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.ContribuyenteMoralDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.servicios.ContribuyenteMoralServicioImpl;
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
@RequestMapping("/api/contribuyenteMoral")
public class ContribuyenteMoralControlador {

    @Autowired
    private ContribuyenteMoralServicioImpl contribuyenteMoralImplSer;

    @Secured({"ROLE_ADMIN", "ROLE_PRESIDENTE", "ROLE_TESORERO", "ROLE_CONTADOR"})
    @GetMapping("/page/{page}")
    public entidadRespuesta<ContribuyenteMoralDTO> listarContribuyentesMoral(@PathVariable Integer page, @RequestParam(value = "pageSize", defaultValue = "6", required = false) int cantidadPagina) {
        return contribuyenteMoralImplSer.findAll(page, cantidadPagina);
    }

    @Secured({"ROLE_ADMIN","ROLE_PRESIDENTE","ROLE_TESORERO","ROLE_CONTADOR"})
    @GetMapping("/filtrar/{page}/{term}")
    public entidadRespuesta<ContribuyenteMoralDTO> contribuyenteByTermino(@PathVariable Integer page,@RequestParam(value = "pageSize",defaultValue = "10",required = false)int cantidadPagina,@PathVariable(name = "term") String term){
        return contribuyenteMoralImplSer.findByTermino(page,cantidadPagina,term);
    }
    //@Secured({"ROLE_ADMIN","ROLE_USER"})
    //@GetMapping("/page/{page}")
    //public Page<ContribuyenteMoralDTO> listarContribuyentesMoral(@PathVariable Integer page){
    //   return contribuyenteMoralImplSer.findAll(PageRequest.of(page,10));
    //}
    @Secured({"ROLE_ADMIN", "ROLE_PRESIDENTE", "ROLE_TESORERO", "ROLE_CONTADOR"})
    @GetMapping("/{id}")
    public ResponseEntity<ContribuyenteMoralDTO> obtenerContribuyenteMoral(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(contribuyenteMoralImplSer.findById(id));
    }

    /**
     * @Secured({"ROLE_ADMIN"})
     * @PostMapping public ResponseEntity<ContribuyenteMoralDTO>
     * guardarContribuyenteMoral(@RequestBody ContribuyenteMoralDTO
     * contribuyenteMoralDTO) { return new
     * ResponseEntity<>(contribuyenteMoralImplSer.save(contribuyenteMoralDTO),
     * HttpStatus.CREATED); }
     */
    @Secured({"ROLE_ADMIN", "ROLE_PRESIDENTE", "ROLE_TESORERO"})
    @PostMapping()
    public ResponseEntity<ContribuyenteMoralDTO> crearContribuyenteMoral(@RequestBody ContribuyenteMoralDTO contribuyenteMoralDTO) {
        Object obj = contribuyenteMoralImplSer.crear(contribuyenteMoralDTO);
        System.out.println((obj instanceof ContribuyenteMoralDTO) + " es instancia ");
        if (!(obj instanceof ContribuyenteMoralDTO)) {
            if ((int) obj == 0) {
                System.out.println("el contribuyente ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);
            }
            if ((int) obj == 0) {
                System.out.println("el contribuyente moral  ya existe");
                return new ResponseEntity<>(null, HttpStatus.FOUND);
            }
        }

        System.out.println("entroe en crear al contribuyente bien ");
        return new ResponseEntity<>((ContribuyenteMoralDTO) obj, HttpStatus.CREATED);

    }

    @Secured({"ROLE_ADMIN", "ROLE_PRESIDENTE", "ROLE_TESORERO"})
    @PutMapping("/{id}")
    public ResponseEntity<ContribuyenteMoralDTO> actualizarContribuyenteMoral(@RequestBody ContribuyenteMoralDTO contribuyenteMoralDTO, @PathVariable(name = "id") String id) {
        ContribuyenteMoralDTO contribuyenteMoralRespuesta = contribuyenteMoralImplSer.update(contribuyenteMoralDTO, id);
        return new ResponseEntity<>(contribuyenteMoralRespuesta, HttpStatus.OK);
    }

    /**
     * @Secured({"ROLE_ADMIN"})
     * @DeleteMapping("/{id}") public ResponseEntity<String>
     * eliminarContribuyenteMoral(@PathVariable(name = "id") Integer id) {
     * contribuyenteMoralImplSer.delete(id); return new
     * ResponseEntity<>("contribuyente moral eliminado con exito",
     * HttpStatus.OK);
    }
     */
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarContribuyenteMoral(@PathVariable String id) {
        contribuyenteMoralImplSer.delete(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
