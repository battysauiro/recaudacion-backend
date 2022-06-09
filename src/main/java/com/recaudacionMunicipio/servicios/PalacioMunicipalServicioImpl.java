/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.PalacioMunicipalDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ImunicipiosDao;
import com.recaudacionMunicipio.dao.IpalacioMunicipalDao;
import com.recaudacionMunicipio.modelo.Municipios;
import com.recaudacionMunicipio.modelo.Palaciomunicipal;
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
public class PalacioMunicipalServicioImpl implements CrudServicio<PalacioMunicipalDTO> {

    @Autowired
    private IpalacioMunicipalDao palacioMunicipalDao;
    
    @Autowired
    private ImunicipiosDao municipiolDao;
    
    @Override
    public PalacioMunicipalDTO findById(Integer id) {
        Palaciomunicipal palacioMunicipal = palacioMunicipalDao.findById(id).orElse(null);
        PalacioMunicipalDTO palacioMunicipalDTO = new PalacioMunicipalDTO(palacioMunicipal.getIdPalacio(), palacioMunicipal.getTelefono(), palacioMunicipal.getImagen(), palacioMunicipal.getIdMunicipio().getClave(), palacioMunicipal.getIdMunicipio().getNombreMunicipio());
        return palacioMunicipalDTO;
    }

    @Override
    public PalacioMunicipalDTO save(PalacioMunicipalDTO palacioMunicipalDTO) {
        Palaciomunicipal palacio = mapearEntidad(palacioMunicipalDTO);
        Palaciomunicipal newPalacio = palacioMunicipalDao.save(palacio);
        PalacioMunicipalDTO palacioRespuesta = mapearDTO(newPalacio);
  
        return palacioRespuesta;
    }

    public Object crear(PalacioMunicipalDTO palacioMunicipalDTO) {
        List<Palaciomunicipal> palacioL= palacioMunicipalDao.findAll();
        boolean bandera=false;
        for(Palaciomunicipal palacio :palacioL) {
            if(palacio.getIdMunicipio().getClave()==palacioMunicipalDTO.getMunicipio())
                 bandera=true;
        }
        if (bandera) {
            return 0;// significa que el municipio ya existe
        }
        if (!bandera) {
            Palaciomunicipal palacio = mapearEntidad(palacioMunicipalDTO);
            Palaciomunicipal newPalacio = palacioMunicipalDao.save(palacio);
            PalacioMunicipalDTO palacioRespuesta = mapearDTO(newPalacio);

            return palacioRespuesta;
        }
        return null;

    }

    @Override
    public entidadRespuesta<PalacioMunicipalDTO> findAll(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Palaciomunicipal> palacioMunicipalP = palacioMunicipalDao.findAll(pageable);
        List<Palaciomunicipal> listaPalacioMunicipal = palacioMunicipalP.getContent();
        List<PalacioMunicipalDTO> lista = new ArrayList<>();
        for (Palaciomunicipal palacioMunicipal : listaPalacioMunicipal) {
            lista.add(new PalacioMunicipalDTO(palacioMunicipal.getIdPalacio(), palacioMunicipal.getTelefono(), palacioMunicipal.getImagen(), palacioMunicipal.getIdMunicipio().getClave(), palacioMunicipal.getIdMunicipio().getNombreMunicipio()));
        }
        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(palacioMunicipalP.getNumber());
        entidadrespuesta.setMedidaPagina(palacioMunicipalP.getSize());
        entidadrespuesta.setTotalElementos(palacioMunicipalP.getTotalElements());
        entidadrespuesta.setTotalPaginas(palacioMunicipalP.getTotalPages());
        entidadrespuesta.setUltima(palacioMunicipalP.isLast());
        entidadrespuesta.setPrimera(palacioMunicipalP.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    public List<PalacioMunicipalDTO> findAllC() {
        List<Palaciomunicipal> listaPalacioMunicipal = palacioMunicipalDao.findAll();
        List<PalacioMunicipalDTO> lista = new ArrayList<>();
        for (Palaciomunicipal palacioMunicipal : listaPalacioMunicipal) {
            lista.add(new PalacioMunicipalDTO(palacioMunicipal.getIdPalacio(), palacioMunicipal.getTelefono(), palacioMunicipal.getImagen(), palacioMunicipal.getIdMunicipio().getClave(), palacioMunicipal.getIdMunicipio().getNombreMunicipio()));
            System.out.println("");
        }

        return lista;
    }

    public PalacioMunicipalDTO updateImagen(String ruta, Integer id) {
        Palaciomunicipal palacioMunicipal=palacioMunicipalDao 
                .findById(id).orElse(null);

        palacioMunicipal.setImagen(ruta);
        Palaciomunicipal palacioActualizado=palacioMunicipalDao.save(palacioMunicipal);
        return mapearDTO(palacioActualizado);
    }
    
    @Override
    public void delete(int id) {
        Palaciomunicipal palacio = palacioMunicipalDao
                .findById(id).orElse(null);
        
        palacioMunicipalDao.delete(palacio);
    }

    @Override
    public PalacioMunicipalDTO update(PalacioMunicipalDTO palacioMunicipalDTO, int id) {
        Palaciomunicipal palacio = palacioMunicipalDao
                .findById(id).orElse(null);

        palacio.setTelefono(palacioMunicipalDTO.getTelefono());
        //palacio.setImagen(palacioMunicipalDTO.getImagen());
        Municipios municipio = municipiolDao.findById(palacioMunicipalDTO.getMunicipio()).orElse(null);
        palacio.setIdMunicipio(municipio);
        
        Palaciomunicipal palacioActualizado = palacioMunicipalDao.save(palacio);
        return mapearDTO(palacioActualizado);
    }
    
    private PalacioMunicipalDTO mapearDTO(Palaciomunicipal palacio) {
        PalacioMunicipalDTO palacioMunicipalDTO = new PalacioMunicipalDTO();
        palacioMunicipalDTO.setTelefono(palacio.getTelefono());
        palacioMunicipalDTO.setImagen(palacio.getImagen());
        palacioMunicipalDTO.setMunicipio(palacio.getIdPalacio());
        return palacioMunicipalDTO;
    }
    
    private Palaciomunicipal mapearEntidad(PalacioMunicipalDTO palacioDTO) {
        Palaciomunicipal palacio = new Palaciomunicipal();
        palacio.setTelefono(palacioDTO.getTelefono());
        palacio.setImagen(palacioDTO.getImagen());
        Municipios municipio = municipiolDao.findById(palacioDTO.getMunicipio()).orElse(null);
        palacio.setIdMunicipio(municipio);
        
        return palacio;
    }

    @Override
    public Page<PalacioMunicipalDTO> findAll(Pageable pageable) {
        Page<Palaciomunicipal> listaPalacioMunicipal = palacioMunicipalDao.findAll(pageable);
        List<PalacioMunicipalDTO> lista = new ArrayList<>();
        for (Palaciomunicipal palacioMunicipal : listaPalacioMunicipal) {
            lista.add(new PalacioMunicipalDTO(palacioMunicipal.getIdPalacio(), palacioMunicipal.getTelefono(), palacioMunicipal.getImagen(), palacioMunicipal.getIdMunicipio().getClave(), palacioMunicipal.getIdMunicipio().getNombreMunicipio()));
}
        return (Page<PalacioMunicipalDTO>) lista;
    }
}
