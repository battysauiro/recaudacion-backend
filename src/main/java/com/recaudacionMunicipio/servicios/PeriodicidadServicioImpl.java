/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.PeriodicidadDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IperiodicidadDao;
import com.recaudacionMunicipio.modelo.Periodicidad;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oscar
 */
@Service
public class PeriodicidadServicioImpl implements CrudServicio<PeriodicidadDTO>{

    @Autowired
    private IperiodicidadDao periodicidadDao;
    
    @Override
    public PeriodicidadDTO findById(Integer id) {
        Periodicidad periodicidad=periodicidadDao.findById(id).orElse(null);
        PeriodicidadDTO periodicidadDTO = new PeriodicidadDTO(periodicidad.getIdPeriodicidad(),periodicidad.getNombrePeriodicidad());
        return periodicidadDTO;
    }

    @Override
    public PeriodicidadDTO save(PeriodicidadDTO periodicidadDTO) {
        Periodicidad periodicidad= mapearEntidad(periodicidadDTO);
        Periodicidad newPeriodicidad=periodicidadDao.save(periodicidad);
        PeriodicidadDTO periodicidadRespuesta= mapearDTO(newPeriodicidad);
  
        return periodicidadRespuesta;
    }

    @Override
    public entidadRespuesta<PeriodicidadDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Periodicidad> periodicidadP =periodicidadDao.findAll(pageable);
        List<Periodicidad> listaPeriodicidad =periodicidadP.getContent();
        List<PeriodicidadDTO> lista= new ArrayList<>();
        for(Periodicidad periodicidad:listaPeriodicidad){
            lista.add(new PeriodicidadDTO(periodicidad.getIdPeriodicidad(),periodicidad.getNombrePeriodicidad()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(periodicidadP.getNumber());
        entidadrespuesta.setMedidaPagina(periodicidadP.getSize());
        entidadrespuesta.setTotalElementos(periodicidadP.getTotalElements());
        entidadrespuesta.setTotalPaginas(periodicidadP.getTotalPages());
        entidadrespuesta.setUltima(periodicidadP.isLast());
        entidadrespuesta.setPrimera(periodicidadP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        Periodicidad periodicidad=periodicidadDao 
                .findById(id).orElse(null);
        
        periodicidadDao.delete(periodicidad);
    }

    @Override
    public PeriodicidadDTO update(PeriodicidadDTO periodicidadDTO, int id) {
        Periodicidad periodicidad=periodicidadDao 
                .findById(id).orElse(null);

        periodicidad.setNombrePeriodicidad(periodicidadDTO.getNombre_periodicidad());
        
        Periodicidad periodicidadActualizado=periodicidadDao.save(periodicidad);
        return mapearDTO(periodicidadActualizado);
    }
    
    private PeriodicidadDTO mapearDTO(Periodicidad periodicidad){
        PeriodicidadDTO periodicidadDTO = new PeriodicidadDTO();
        periodicidadDTO.setNombre_periodicidad(periodicidad.getNombrePeriodicidad());
        return  periodicidadDTO;
    }
    
    private Periodicidad mapearEntidad(PeriodicidadDTO periodicidadDTO){
        Periodicidad periodicidad = new Periodicidad();
        periodicidad.setNombrePeriodicidad(periodicidadDTO.getNombre_periodicidad());
       
        return periodicidad;
    }

    @Override
    public Page<PeriodicidadDTO> findAll(Pageable pageable) {
        Page<Periodicidad> listaPeriodicidad =periodicidadDao.findAll(pageable);
        List<PeriodicidadDTO> lista= new ArrayList<>();
        for(Periodicidad periodicidad:listaPeriodicidad){
            lista.add(new PeriodicidadDTO(periodicidad.getIdPeriodicidad(),periodicidad.getNombrePeriodicidad()));
}
        return (Page<PeriodicidadDTO>) lista;
    }
    
    public List<PeriodicidadDTO> findAllP() {
        List<Periodicidad> listaPeriodicidad =periodicidadDao.findAll();
        List<PeriodicidadDTO> lista= new ArrayList<>();
        for(Periodicidad periodicidad:listaPeriodicidad){
            lista.add(new PeriodicidadDTO(periodicidad.getIdPeriodicidad(),periodicidad.getNombrePeriodicidad()));
        }
        return lista;
    }
}
