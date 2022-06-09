/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;


import com.recaudacionMunicipio.DTO.emailDTO;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 *
 * @author Oscar
 */
@Service
public class EmailServicio {
    
    @Autowired
    JavaMailSender javaMailSender;
    
    @Autowired
    TemplateEngine templateEngine;
    
    @Value("${mail.urlFront}")
    private String urlFront;
            
    public void sendEmail(){
    
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom("Battyguide8428@gmail.com");
        message.setTo("davidcrespo941@gmail.com");
        message.setSubject("verificando que si envie el correo");
        message.setText("este contenido va dirigido a crespo");
        
        javaMailSender.send(message);
    }
    
    public void sendEmailTemplate(emailDTO emaillDTO){
        MimeMessage message= javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            Context context= new Context();
            Map<String, Object> model=new HashMap<>();
            model.put("userName",emaillDTO.getUserName());
            model.put("url",urlFront+emaillDTO.getJwt());
            context.setVariables(model);
            
            String htmlText= templateEngine.process("email-template", context);
            helper.setFrom(emaillDTO.getMailFrom());
            helper.setTo(emaillDTO.getMailTo());
            helper.setSubject(emaillDTO.getSubject());
            helper.setText(htmlText,true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
