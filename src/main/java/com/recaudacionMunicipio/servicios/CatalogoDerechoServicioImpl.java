/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.CatalogosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoDerechoDao;
import com.recaudacionMunicipio.modelo.CatalogoDerecho;
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
public class CatalogoDerechoServicioImpl implements CrudServicio<CatalogosDTO>{

    @Autowired
    private IcatalogoDerechoDao catalogoDerechoDao;

    @Override
    public CatalogosDTO findById(Integer id) {
        CatalogoDerecho catalogoDerecho =catalogoDerechoDao.findById(id).orElse(null);
        CatalogosDTO catalogoDerechos= new CatalogosDTO(catalogoDerecho.getIdTipoDerecho(),catalogoDerecho.getDescripcion());
        return catalogoDerechos;
    }

    @Override
    public CatalogosDTO save(CatalogosDTO catalogoDTO) {
        CatalogoDerecho catalogoDerecho= mapearEntidad(catalogoDTO);
        CatalogoDerecho newCatalogo=catalogoDerechoDao.save(catalogoDerecho);
        CatalogosDTO catalogoRespuesta= mapearDTO(newCatalogo);
  
        return catalogoRespuesta;
    }

    @Override
    public entidadRespuesta<CatalogosDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<CatalogoDerecho> catalogoDerechoP= catalogoDerechoDao.findAll(pageable);
        List<CatalogoDerecho> listaCatalogoDerecho=catalogoDerechoP.getContent();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoDerecho catalogo:listaCatalogoDerecho ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoDerecho(),catalogo.getDescripcion()) );
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(catalogoDerechoP.getNumber());
        entidadrespuesta.setMedidaPagina(catalogoDerechoP.getSize());
        entidadrespuesta.setTotalElementos(catalogoDerechoP.getTotalElements());
        entidadrespuesta.setTotalPaginas(catalogoDerechoP.getTotalPages());
        entidadrespuesta.setUltima(catalogoDerechoP.isLast());
        entidadrespuesta.setPrimera(catalogoDerechoP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        CatalogoDerecho catalogoDerecho=catalogoDerechoDao 
                .findById(id).orElse(null);
        
        catalogoDerechoDao.delete(catalogoDerecho);
    }

    @Override
    public CatalogosDTO update(CatalogosDTO catalogoDTO, int id) {
        CatalogoDerecho catalogo=catalogoDerechoDao 
                .findById(id).orElse(null);

        //catalogo.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogo.setDescripcion(catalogoDTO.getDescripcion());
        
        CatalogoDerecho catalogoActualizado=catalogoDerechoDao.save(catalogo);
        return mapearDTO(catalogoActualizado);
    }
    
    private CatalogosDTO mapearDTO(CatalogoDerecho catalogoDerecho){
        CatalogosDTO catalogoDTO = new CatalogosDTO();
        catalogoDTO.setId_catalogo(catalogoDerecho.getIdTipoDerecho());
        catalogoDTO.setDescripcion(catalogoDerecho.getDescripcion());
        return  catalogoDTO;
    }
    
    private CatalogoDerecho mapearEntidad(CatalogosDTO catalogoDTO){
        CatalogoDerecho catalogoAprovechamiento = new CatalogoDerecho();
        //catalogoAprovechamiento.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogoAprovechamiento.setDescripcion(catalogoDTO.getDescripcion());
       
        return catalogoAprovechamiento;
    }
    
    @Override
    public Page<CatalogosDTO> findAll(Pageable pageable) {
        Page<CatalogoDerecho> listaCatalogoDerecho= catalogoDerechoDao.findAll(pageable);
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoDerecho catalogo:listaCatalogoDerecho ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoDerecho(),catalogo.getDescripcion()) );
}
        return (Page<CatalogosDTO>) lista;
    }
    
    public List<CatalogosDTO> findAllCatalogo() {
        List<CatalogoDerecho> listaCatalogoDerecho= catalogoDerechoDao.findAll();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoDerecho catalogo:listaCatalogoDerecho ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoDerecho(),catalogo.getDescripcion()) );
        }
        return lista;
    }
    
}
