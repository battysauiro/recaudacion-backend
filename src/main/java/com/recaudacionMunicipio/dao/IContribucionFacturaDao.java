/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.modelo.Contribucionfactura;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Oscar
 */
public interface IContribucionFacturaDao extends JpaRepository<Contribucionfactura, Integer>{
     
}
