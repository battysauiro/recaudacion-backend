/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Impuesto;
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
public interface IimpuestoDao extends JpaRepository<Impuesto, String>{
    
    //@Query("SELECT i FROM Impuesto i join fetch i.contribucion WHERE UPPER(i.idContribucionImpuesto) like % UPPER(?1) or i.contribucion.conceptoContribucion like % UPPER(?2)")
    // public Page<Impuesto> findByIdContribucionImpuestoStartingWithIgnoreCaseOrConceptoContribucionContainingIgnoreCase(Pageable pageable,@Param("idContribucionImpuesto")String idContribucionImpuesto,@Param("conceptoContribucion")String conceptoContribucion);
    
}
