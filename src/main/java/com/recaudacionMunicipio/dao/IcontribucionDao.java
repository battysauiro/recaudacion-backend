/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.DTO.ContribucionDTO;
import com.recaudacionMunicipio.modelo.Contribucion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar
 */
@Repository
public interface IcontribucionDao extends JpaRepository<Contribucion, String>{
    
    public List<Contribucion> findByCodigoContribucionStartingWithIgnoreCaseOrConceptoContribucionContainingIgnoreCase(@Param("codigoContribucion")String codigoContribucion,@Param("conceptoContribucion")String conceptoContribucion);
}
