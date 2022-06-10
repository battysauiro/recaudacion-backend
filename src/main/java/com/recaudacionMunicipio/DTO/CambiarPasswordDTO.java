/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.DTO;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Oscar
 */
public class CambiarPasswordDTO {
    
    @NotBlank
    private String password;
    @NotBlank
    private String confirmarPassword;
    @NotBlank
    private String tokenPassword;

    public CambiarPasswordDTO() {
    }

    public CambiarPasswordDTO(String password, String confirmarPassword, String tokenPassword) {
        this.password = password;
        this.confirmarPassword = confirmarPassword;
        this.tokenPassword = tokenPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }

    public String getTokenPassword() {
        return tokenPassword;
    }

    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }
    
    
    
}
