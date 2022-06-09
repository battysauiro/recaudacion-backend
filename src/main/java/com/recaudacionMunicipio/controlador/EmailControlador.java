/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.emailDTO;
import com.recaudacionMunicipio.servicios.EmailServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Oscar
 */
@RestController 
public class EmailControlador {
    
    @Autowired
    EmailServicio emailServicio;
    
    @GetMapping("/api/email/send")
    public ResponseEntity<?> sendEmail(){
        emailServicio.sendEmail();
        return new ResponseEntity("correo enviado con exito",HttpStatus.OK);
    }
    
    @PostMapping("/api/email/send-html")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody emailDTO emaillDTO){
        emailServicio.sendEmailTemplate(emaillDTO);
        return new ResponseEntity("correo con plantilla enviado con exito",HttpStatus.OK);
    }
    
}
