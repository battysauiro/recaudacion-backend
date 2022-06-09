/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.modelo.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public interface UsuarioServicioImpl {
    
    public Usuario findByUsername(String username);
}
