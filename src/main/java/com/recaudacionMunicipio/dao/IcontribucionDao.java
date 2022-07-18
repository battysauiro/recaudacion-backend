/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.DTO.ContribucionDTO;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Impuesto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar
 */
@Repository
public interface IcontribucionDao extends JpaRepository<Contribucion, String>{ 
    
    //@Query ("SELECT c FROM Contribucion c where c.nivelContribucion=2")
    public Page<Contribucion> findByCodigoContribucionStartingWithIgnoreCaseOrConceptoContribucionContainingIgnoreCase(Pageable pageable,@Param("codigoContribucion")String codigoContribucion,@Param("conceptoContribucion")String conceptoContribucion);
    
    //@Query ("SELECT c FROM Contribucion c where c.nivelContribucion=2")
    //public Page<Impuesto> findByCodigoContribucionStartingWithIgnoreCaseOrConceptoContribucionContainingIgnoreCase(Pageable pageable,@Param("codigoContribucion")String codigoContribucion,@Param("conceptoContribucion")String conceptoContribucion);
}
