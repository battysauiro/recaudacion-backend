/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.controlador;

import com.recaudacionMunicipio.DTO.CambiarPasswordDTO;
import com.recaudacionMunicipio.DTO.emailDTO;
import com.recaudacionMunicipio.modelo.Usuario;
import com.recaudacionMunicipio.servicios.EmailServicio;
import com.recaudacionMunicipio.servicios.UsuarioServicio;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
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
      
    @Autowired
    UsuarioServicio usuarioServicio;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Value("${spring.mail.username}")
    private String mailFrom;
    
    @GetMapping("/api/email/send")
    public ResponseEntity<?> sendEmail(){
        emailServicio.sendEmail();
        return new ResponseEntity("correo enviado con exito",HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/api/email/send-html")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody emailDTO emaillDTO){
        Usuario usuario =usuarioServicio.findByUsername(emaillDTO.getMailTo());
        Map<String, Object> respuesta = new HashMap<>();
        if(usuario==null){
            respuesta.put("mensaje", "No existe usuario con esas credenciales ");
            return new ResponseEntity<Map<String,Object>>(respuesta,HttpStatus.OK);
        }
        emaillDTO.setMailFrom(mailFrom);
        emaillDTO.setMailTo(usuario.getUsername());
        emaillDTO.setSubject("Cambio de Contraseña");
        emaillDTO.setUserName(usuario.getIdEmpleado().getNombre());
        UUID uuid = UUID.randomUUID();
        String tokenPassword=uuid.toString();
        emaillDTO.setJwt(tokenPassword);
        usuario.setTokenPassword(tokenPassword);
        usuarioServicio.saveCorreo(usuario);
        emailServicio.sendEmailTemplate(emaillDTO);
        respuesta.put("mensaje", "Te hemos enviado un correo");
        return new ResponseEntity(respuesta,HttpStatus.OK);
    }
    
    @PostMapping("/api/cambiar-password")
    public ResponseEntity<?> cambiarContraseña(@Valid @RequestBody CambiarPasswordDTO cambiarPasswordDTO, BindingResult bindingResult){
        Map<String, Object> respuesta = new HashMap<>();
        if(bindingResult.hasErrors()){
            respuesta.put("mensaje", "campos erroneos");
            return new ResponseEntity(respuesta,HttpStatus.BAD_REQUEST);
        }
        if(!cambiarPasswordDTO.getPassword().equals(cambiarPasswordDTO.getConfirmarPassword())){
            respuesta.put("mensaje", "las contraseñas no coinciden");
            return new ResponseEntity(respuesta,HttpStatus.BAD_REQUEST);
        }
        Usuario usuario = usuarioServicio.findByTokenPassword(cambiarPasswordDTO.getTokenPassword());
        System.out.println(usuario.getTokenPassword());
        if(usuario==null){ 
            respuesta.put("mensaje", "este usuario no existe");
            return new ResponseEntity(respuesta,HttpStatus.OK);
        }
        String newPassword = passwordEncoder.encode(cambiarPasswordDTO.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);
        usuarioServicio.saveCorreo(usuario);
        respuesta.put("mensaje", "contraseña actualizada");
        return new ResponseEntity(respuesta,HttpStatus.OK);
    }
    
}
