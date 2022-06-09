/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.CatalogosDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoImpuestoDao;
import com.recaudacionMunicipio.modelo.CatalogoImpuesto;
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
public class CatalogoImpuestoServicioImpl implements CrudServicio<CatalogosDTO> {

    @Autowired
    private IcatalogoImpuestoDao catalogoImpuestoDao;
    
    @Override
    public CatalogosDTO findById(Integer id) {
        CatalogoImpuesto catalogoImpuesto =catalogoImpuestoDao.findById(id).orElse(null);
        CatalogosDTO catalogoImpuestos= new CatalogosDTO(catalogoImpuesto.getIdTipoImpuesto(),catalogoImpuesto.getDescripcion());
        return catalogoImpuestos;
    }

    @Override
    public CatalogosDTO save(CatalogosDTO catalogoDTO) {
        CatalogoImpuesto catalogoImpuesto= mapearEntidad(catalogoDTO);
        CatalogoImpuesto newCatalogo=catalogoImpuestoDao.save(catalogoImpuesto);
        CatalogosDTO catalogoRespuesta= mapearDTO(newCatalogo);
  
        return catalogoRespuesta;
    }

    @Override
    public entidadRespuesta<CatalogosDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<CatalogoImpuesto> catalogoImpuestoP= catalogoImpuestoDao.findAll(pageable);
        List<CatalogoImpuesto> listaCatalogoImpuesto= catalogoImpuestoP.getContent();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoImpuesto catalogo:listaCatalogoImpuesto ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoImpuesto(),catalogo.getDescripcion()) );
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(catalogoImpuestoP.getNumber());
        entidadrespuesta.setMedidaPagina(catalogoImpuestoP.getSize());
        entidadrespuesta.setTotalElementos(catalogoImpuestoP.getTotalElements());
        entidadrespuesta.setTotalPaginas(catalogoImpuestoP.getTotalPages());
        entidadrespuesta.setUltima(catalogoImpuestoP.isLast());
        entidadrespuesta.setPrimera(catalogoImpuestoP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(int id) {
        CatalogoImpuesto catalogoImpuesto=catalogoImpuestoDao 
                .findById(id).orElse(null);
        
        catalogoImpuestoDao.delete(catalogoImpuesto);
    }

    @Override
    public CatalogosDTO update(CatalogosDTO catalogoDTO, int id) {
        CatalogoImpuesto catalogo=catalogoImpuestoDao 
                .findById(id).orElse(null);
        
        catalogo.setDescripcion(catalogoDTO.getDescripcion());
        
        CatalogoImpuesto catalogoActualizado=catalogoImpuestoDao.save(catalogo);
        return mapearDTO(catalogoActualizado);
    }
    
    private CatalogosDTO mapearDTO(CatalogoImpuesto catalogoImpuesto){
        CatalogosDTO catalogoDTO = new CatalogosDTO();
        catalogoDTO.setId_catalogo(catalogoImpuesto.getIdTipoImpuesto());
        catalogoDTO.setDescripcion(catalogoImpuesto.getDescripcion());
        return  catalogoDTO;
    }
    
    private CatalogoImpuesto mapearEntidad(CatalogosDTO catalogoDTO){
        CatalogoImpuesto catalogoImpuesto = new CatalogoImpuesto();
        //catalogoAprovechamiento.setIdTipoAprovechamiento(catalogoDTO.getId_catalogo());
        catalogoImpuesto.setDescripcion(catalogoDTO.getDescripcion());
       
        return catalogoImpuesto;
    }
    
    @Override
    public Page<CatalogosDTO> findAll(Pageable pageable) {
        Page<CatalogoImpuesto> listaCatalogoImpuesto= catalogoImpuestoDao.findAll(pageable);
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoImpuesto catalogo:listaCatalogoImpuesto ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoImpuesto(),catalogo.getDescripcion()) );
}
        return (Page<CatalogosDTO>) lista;
    }
    
    public List<CatalogosDTO> findAllCatalogo() {
        List<CatalogoImpuesto> listaCatalogoImpuesto= catalogoImpuestoDao.findAll();
        List<CatalogosDTO> lista= new ArrayList<>();
        for(CatalogoImpuesto catalogo:listaCatalogoImpuesto ){
            lista.add(new CatalogosDTO(catalogo.getIdTipoImpuesto(),catalogo.getDescripcion()) );
        }
        return lista;
    }
}
