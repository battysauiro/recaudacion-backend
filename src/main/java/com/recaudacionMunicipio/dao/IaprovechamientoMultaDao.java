/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaDTO;
import com.recaudacionMunicipio.modelo.Aprovechamientomulta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar
 */
@Repository
public interface IaprovechamientoMultaDao extends JpaRepository<Aprovechamientomulta, String>{
    
    public List<Aprovechamientomulta>findByIdContribucionMultaStartingWith(@Param("idContribucionMulta")String idContribucionMulta);
    
}
