/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.MunicipiosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ImunicipiosDao;
import com.recaudacionMunicipio.modelo.Municipios;
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
public class MunicipiosServicioImpl implements CrudServicio<MunicipiosDTO>{

    @Autowired
    private ImunicipiosDao municipiosDao;
    
    @Override
    public MunicipiosDTO findById(Integer id) {
        Municipios municipios=municipiosDao.findById(id).orElse(null);
        MunicipiosDTO municipiosDTO = new MunicipiosDTO(municipios.getClave(), municipios.getNombreMunicipio(), municipios.getCabeceraMunicipal());
        return municipiosDTO;
    }

    @Override
    public MunicipiosDTO save(MunicipiosDTO municipiosDTO) {
        Municipios municipio= mapearEntidad(municipiosDTO);
        Municipios newMunicipio=municipiosDao.save(municipio);
        MunicipiosDTO municipioRespuesta= mapearDTO(newMunicipio);
  
        return municipioRespuesta;
    }

    @Override
    public entidadRespuesta<MunicipiosDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Municipios> municipiosP =municipiosDao.findAll(pageable);
        List<Municipios> listaMunicipios =municipiosP.getContent();
        List<MunicipiosDTO> lista= new ArrayList<>();
        for(Municipios municipios:listaMunicipios){
            lista.add(new MunicipiosDTO(municipios.getClave(), municipios.getNombreMunicipio(), municipios.getCabeceraMunicipal()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(municipiosP.getNumber());
        entidadrespuesta.setMedidaPagina(municipiosP.getSize());
        entidadrespuesta.setTotalElementos(municipiosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(municipiosP.getTotalPages());
        entidadrespuesta.setUltima(municipiosP.isLast());
        entidadrespuesta.setPrimera(municipiosP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        Municipios municipio=municipiosDao 
                .findById(id).orElse(null);
        
        municipiosDao.delete(municipio);
    }

    @Override
    public MunicipiosDTO update(MunicipiosDTO municipioDTO, int id) {
        Municipios municipio=municipiosDao 
                .findById(id).orElse(null);

        municipio.setClave(municipioDTO.getClave());
        municipio.setNombreMunicipio(municipioDTO.getNombre_municipios());
        municipio.setCabeceraMunicipal(municipioDTO.getCabecera_municipal());
        
        Municipios municipioActualizado=municipiosDao.save(municipio);
        return mapearDTO(municipioActualizado);
    }
    
    private MunicipiosDTO mapearDTO(Municipios municipios){
        MunicipiosDTO municipioDTO = new MunicipiosDTO();
        municipioDTO.setClave(municipios.getClave());
        municipioDTO.setNombre_municipios(municipios.getNombreMunicipio());
        municipioDTO.setCabecera_municipal(municipios.getCabeceraMunicipal());
        return  municipioDTO;
    }
    
    private Municipios mapearEntidad(MunicipiosDTO municipiosDTO){
        Municipios municipios = new Municipios();
        municipios.setClave(municipiosDTO.getClave());
        municipios.setNombreMunicipio(municipiosDTO.getNombre_municipios());
        municipios.setCabeceraMunicipal(municipiosDTO.getCabecera_municipal());
       
        return municipios;
    }

    @Override
    public Page<MunicipiosDTO> findAll(Pageable pageable) {
        Page<Municipios> listaMunicipios =municipiosDao.findAll(pageable);
        List<MunicipiosDTO> lista= new ArrayList<>();
        for(Municipios municipios:listaMunicipios){
            lista.add(new MunicipiosDTO(municipios.getClave(), municipios.getNombreMunicipio(), municipios.getCabeceraMunicipal()));
}
        return (Page<MunicipiosDTO>) lista;
    }
    
    public List<MunicipiosDTO> findAllM() {
        List<Municipios> listaMunicipios =municipiosDao.findAll();
        List<MunicipiosDTO> lista= new ArrayList<>();
        for(Municipios municipios:listaMunicipios){
            lista.add(new MunicipiosDTO(municipios.getClave(), municipios.getNombreMunicipio(), municipios.getCabeceraMunicipal()));
        }
        return lista;
    }
}
