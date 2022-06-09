/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.TipoPagoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.TipoPago;
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
public class TipoPagoServicioImpl implements CrudServicio<TipoPagoDTO>{

    @Autowired
    private ItipoPagoDao tipoPagoDao;
    
    @Override
    public TipoPagoDTO findById(Integer id) {
        TipoPago tipoPago=tipoPagoDao.findById(id).orElse(null);
        TipoPagoDTO tipoPagoDTO = new TipoPagoDTO(tipoPago.getIdTipoPago(),tipoPago.getNombrePago());
        return tipoPagoDTO;
    }

    @Override
    public TipoPagoDTO save(TipoPagoDTO tipoPagoDTO) {
        TipoPago tipoPago= mapearEntidad(tipoPagoDTO);
        TipoPago newtipoPago=tipoPagoDao.save(tipoPago);
        TipoPagoDTO tipoPagoRespuesta= mapearDTO(newtipoPago);
  
        return tipoPagoRespuesta;
    }

    @Override
    public entidadRespuesta<TipoPagoDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<TipoPago> tipoPagoP =tipoPagoDao.findAll(pageable);
        List<TipoPago> listaTipoPago =tipoPagoP.getContent();
        List<TipoPagoDTO> lista= new ArrayList<>();
        for(TipoPago tipoPago:listaTipoPago){
            lista.add(new TipoPagoDTO(tipoPago.getIdTipoPago(),tipoPago.getNombrePago()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(tipoPagoP.getNumber());
        entidadrespuesta.setMedidaPagina(tipoPagoP.getSize());
        entidadrespuesta.setTotalElementos(tipoPagoP.getTotalElements());
        entidadrespuesta.setTotalPaginas(tipoPagoP.getTotalPages());
        entidadrespuesta.setUltima(tipoPagoP.isLast());
        entidadrespuesta.setPrimera(tipoPagoP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        TipoPago tipoPago=tipoPagoDao 
                .findById(id).orElse(null);
        
        tipoPagoDao.delete(tipoPago);
    }

    @Override
    public TipoPagoDTO update(TipoPagoDTO tipoPagoDTO, int id) {
        TipoPago tipoPago=tipoPagoDao 
                .findById(id).orElse(null);

        tipoPago.setNombrePago(tipoPagoDTO.getNombre_pago());

        TipoPago tipoPagoActualizado=tipoPagoDao.save(tipoPago);
        return mapearDTO(tipoPagoActualizado);
    }
    
    private TipoPagoDTO mapearDTO(TipoPago tipoPago){
        TipoPagoDTO tipoPagoDTO = new TipoPagoDTO();
        tipoPagoDTO.setNombre_pago(tipoPago.getNombrePago());
        
        return  tipoPagoDTO;
    }
    
    private TipoPago mapearEntidad(TipoPagoDTO tipoPagoDTO){
        TipoPago tipoPago = new TipoPago();
        tipoPago.setNombrePago(tipoPagoDTO.getNombre_pago());
       
        return tipoPago;
    }

    @Override
    public Page<TipoPagoDTO> findAll(Pageable pageable) {
        Page<TipoPago> listaTipoPago =tipoPagoDao.findAll(pageable);
        List<TipoPagoDTO> lista= new ArrayList<>();
        for(TipoPago tipoPago:listaTipoPago){
            lista.add(new TipoPagoDTO(tipoPago.getIdTipoPago(),tipoPago.getNombrePago()));
}
        return (Page<TipoPagoDTO>) lista;
    }
    
    public List<TipoPagoDTO> findAll() {
        List<TipoPago> listaTipoPago =tipoPagoDao.findAll();
        List<TipoPagoDTO> lista= new ArrayList<>();
        for(TipoPago tipoPago:listaTipoPago){
            lista.add(new TipoPagoDTO(tipoPago.getIdTipoPago(),tipoPago.getNombrePago()));
        }
        return lista;
    }
}
