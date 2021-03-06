/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.modelo.ContribuyenteMoral;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oscar
 */
@Repository
public interface IcontribuyenteMoralDao extends JpaRepository<ContribuyenteMoral, String>{
    
    public ContribuyenteMoral findByIdContribuyenteMoral(@Param("idContribuyenteMoral")String idContribuyenteMoral);
    
    public List<ContribuyenteMoral> findByIdContribuyenteMoralStartingWithIgnoreCaseOrRazonSocialContribuyenteMoralStartingWithIgnoreCase(@Param("idContribuyenteMoral")String idContribuyenteMoral,
            @Param("razonSocialContribuyenteMoral")String razonSocialContribuyenteMoral);
}
