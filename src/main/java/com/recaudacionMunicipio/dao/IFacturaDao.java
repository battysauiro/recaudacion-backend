/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.dao;

import com.recaudacionMunicipio.modelo.Contribuyente;
import com.recaudacionMunicipio.modelo.ContribuyenteFisica;
import com.recaudacionMunicipio.modelo.ContribuyenteMoral;
import com.recaudacionMunicipio.modelo.Factura;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Oscar
 */
public interface IFacturaDao extends JpaRepository<Factura, Integer> {
    public List<Factura> findByContribuyenteId(@Param("contribuyenteId")Contribuyente contribuyenteId);
    public List<Factura> findByEstadoPago(@Param("estadoPago")Boolean estadoPago);
    public List<Factura> findByContribuyenteIdAndEstadoPago(@Param("contribuyenteId")Contribuyente contribuyenteId,@Param("estadoPago")Boolean estadoPago);
    
     
}
