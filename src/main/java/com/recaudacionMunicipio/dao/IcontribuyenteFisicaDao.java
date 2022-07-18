/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.modelo.ContribuyenteFisica;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar
 */
@Repository
public interface IcontribuyenteFisicaDao extends JpaRepository<ContribuyenteFisica, String>{
    
    public ContribuyenteFisica findByCurpContribuyenteFisica(@Param("curpContribuyenteFisica")String curpContribuyenteFisica);
    /**public List<ContribuyenteFisica> findByCurpContribuyenteFisicaStartingWithIgnoreCaseOrIdContribuyenteFisicaStartingWithIgnoreCaseOrNombreContribuyenteFisicaStartingWithIgnoreCaseOrApellidoPContribuyenteFisicaStartingWithIgnoreCaseOrApellidoMContribuyenteFisicaStartingWithIgnoreCase(@Param("curpContribuyenteFisica")String curpContribuyenteFisica,@Param("id_contribuyente_fisica")String id_contribuyente_fisica,
            @Param("nombreContribuyenteFisica")String nombreContribuyenteFisica,
            @Param("apellidoPContribuyenteFisica")String apellidoPContribuyenteFisica,
            @Param("apellidoMContribuyenteFisica")String apellidoMContribuyenteFisica);*/
    
    public Page<ContribuyenteFisica> findByCurpContribuyenteFisicaStartingWithIgnoreCaseOrIdContribuyenteFisicaStartingWithIgnoreCaseOrNombreContribuyenteFisicaStartingWithIgnoreCaseOrApellidoPContribuyenteFisicaStartingWithIgnoreCaseOrApellidoMContribuyenteFisicaStartingWithIgnoreCase(Pageable pageable,@Param("curpContribuyenteFisica")String curpContribuyenteFisica,@Param("id_contribuyente_fisica")String id_contribuyente_fisica,
            @Param("nombreContribuyenteFisica")String nombreContribuyenteFisica,
            @Param("apellidoPContribuyenteFisica")String apellidoPContribuyenteFisica,
            @Param("apellidoMContribuyenteFisica")String apellidoMContribuyenteFisica);
}

