/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.TipoVehiculoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ItipoVehiculoDao;
import com.recaudacionMunicipio.modelo.Tipovehiculo;
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
public class TipoVehiculoServicioImpl implements CrudServicio<TipoVehiculoDTO>{

    @Autowired
    private ItipoVehiculoDao tipoVehiculoDao;
    
    @Override
    public TipoVehiculoDTO findById(Integer id) {
        Tipovehiculo tipoVehiculo=tipoVehiculoDao.findById(id).orElse(null);
        TipoVehiculoDTO tipoVehiculoDTO = new TipoVehiculoDTO(tipoVehiculo.getIdTipoVehiculo(),tipoVehiculo.getNombreVehiculo());
        return tipoVehiculoDTO;
    }

    @Override
    public TipoVehiculoDTO save(TipoVehiculoDTO tipoVehiculoDTO) {
        Tipovehiculo tipoVehiculo= mapearEntidad(tipoVehiculoDTO);
        Tipovehiculo newTipoVehiculo=tipoVehiculoDao.save(tipoVehiculo);
        TipoVehiculoDTO tipoVehiculoRespuesta= mapearDTO(newTipoVehiculo);
  
        return tipoVehiculoRespuesta;
    }

    @Override
    public entidadRespuesta<TipoVehiculoDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Tipovehiculo> tipoVehiculoP =tipoVehiculoDao.findAll(pageable);
        List<Tipovehiculo> listaTipoVehiculo =tipoVehiculoP.getContent();
        List<TipoVehiculoDTO> lista= new ArrayList<>();
        for(Tipovehiculo tipoVehiculo:listaTipoVehiculo){
            lista.add(new TipoVehiculoDTO(tipoVehiculo.getIdTipoVehiculo(),tipoVehiculo.getNombreVehiculo()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(tipoVehiculoP.getNumber());
        entidadrespuesta.setMedidaPagina(tipoVehiculoP.getSize());
        entidadrespuesta.setTotalElementos(tipoVehiculoP.getTotalElements());
        entidadrespuesta.setTotalPaginas(tipoVehiculoP.getTotalPages());
        entidadrespuesta.setUltima(tipoVehiculoP.isLast());
        entidadrespuesta.setPrimera(tipoVehiculoP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        Tipovehiculo tipoVehiculo=tipoVehiculoDao 
                .findById(id).orElse(null);
        
        tipoVehiculoDao.delete(tipoVehiculo);
    }

    @Override
    public TipoVehiculoDTO update(TipoVehiculoDTO tipoVehiculoDTO, int id) {
        Tipovehiculo tipoVehiculo=tipoVehiculoDao 
                .findById(id).orElse(null);

        tipoVehiculo.setNombreVehiculo(tipoVehiculoDTO.getNombre_vehiculo());
        
        
        Tipovehiculo tipoVehiculoActualizado=tipoVehiculoDao.save(tipoVehiculo);
        return mapearDTO(tipoVehiculoActualizado);
    }
    
    private TipoVehiculoDTO mapearDTO(Tipovehiculo tipoVehiculo){
        TipoVehiculoDTO tipoVehiculoDTO = new TipoVehiculoDTO();
        tipoVehiculoDTO.setNombre_vehiculo(tipoVehiculo.getNombreVehiculo());
        
        return  tipoVehiculoDTO;
    }
    
    private Tipovehiculo mapearEntidad(TipoVehiculoDTO tipoVehiculoDTO){
        Tipovehiculo tipoVehiculo = new Tipovehiculo();
        tipoVehiculo.setNombreVehiculo(tipoVehiculoDTO.getNombre_vehiculo());
       
        return tipoVehiculo;
    }

    @Override
    public Page<TipoVehiculoDTO> findAll(Pageable pageable) {
        Page<Tipovehiculo> listaTipoVehiculo =tipoVehiculoDao.findAll(pageable);
        List<TipoVehiculoDTO> lista= new ArrayList<>();
        for(Tipovehiculo tipoVehiculo:listaTipoVehiculo){
            lista.add(new TipoVehiculoDTO(tipoVehiculo.getIdTipoVehiculo(),tipoVehiculo.getNombreVehiculo()));
}
        return (Page<TipoVehiculoDTO>) lista;
    }
    
    public List<TipoVehiculoDTO> findAll() {
        List<Tipovehiculo> listaTipoVehiculo =tipoVehiculoDao.findAll();
        List<TipoVehiculoDTO> lista= new ArrayList<>();
        for(Tipovehiculo tipoVehiculo:listaTipoVehiculo){
            lista.add(new TipoVehiculoDTO(tipoVehiculo.getIdTipoVehiculo(),tipoVehiculo.getNombreVehiculo()));
        }
        return lista;
    }
}
