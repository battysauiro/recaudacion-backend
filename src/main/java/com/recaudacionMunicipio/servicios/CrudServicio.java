/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public interface CrudServicio<T>{
    
    public T findById(Integer id);   
    
    public T save(T tipo);
    
    public entidadRespuesta<T> findAll(int numeroDePagina,int MedidaDePagina);
    
    public Page<T> findAll(Pageable pageable);
    
    public void delete(int id);
    
    public T update(T tipo , int id);
}
