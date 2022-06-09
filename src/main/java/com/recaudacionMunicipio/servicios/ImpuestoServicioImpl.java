/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.ImpuestoDTO;
import com.recaudacionMunicipio.DTO.entidades.ImpuestoCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcatalogoImpuestoDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.IimpuestoDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.CatalogoImpuesto;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Impuesto;
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
public class ImpuestoServicioImpl implements Servicios<ImpuestoDTO>{

    @Autowired
    private IimpuestoDao impuestoDao;
    
    @Autowired
    private IcatalogoImpuestoDao catalogoImpuestoDao;
    
    @Autowired
    private IcontribucionDao contribucionDao;
   

    @Autowired
    private ItipoPagoDao tipoPagoDao;
    
    @Autowired
    private IcatalogoDescripcionDao catalogoDescripcionDao;
   
    @Override
    public ImpuestoDTO findById(String id) {
        Impuesto impuestos=impuestoDao.findById(id).orElse(null);
        ImpuestoDTO impuestoDTO = new ImpuestoDTO(impuestos.getIdContribucionImpuesto(), impuestos.getIdTipoImpuesto().getIdTipoImpuesto(),impuestos.getIdTipoImpuesto().getDescripcion(), impuestos.getCantidad(), impuestos.getContribucion().getCodigoContribucion(), impuestos.getContribucion().getConceptoContribucion(), impuestos.getContribucion().getIdTipoPago().getIdTipoPago(), impuestos.getContribucion().getIdTipoPago().getNombrePago(),impuestos.getContribucion().getIdDescripcion().getIdDescripcion(),
            impuestos.getContribucion().getIdDescripcion().getDescripcion());
        return impuestoDTO;
    }
    
    public ImpuestoCompletoDTO findByIdCompleto(String id) {
        Impuesto impuestos=impuestoDao.findById(id).orElse(null);
        ImpuestoCompletoDTO impuestoCompletoDTO = new ImpuestoCompletoDTO("Impuestos",impuestos.getIdContribucionImpuesto(), impuestos.getIdTipoImpuesto().getDescripcion(), impuestos.getCantidad(), impuestos.getContribucion().getCodigoContribucion(), impuestos.getContribucion().getConceptoContribucion(), impuestos.getContribucion().getIdTipoPago().getNombrePago(), impuestos.getContribucion().getIdDescripcion().getDescripcion());
        return impuestoCompletoDTO;
    }

    @Override
    public ImpuestoDTO save(ImpuestoDTO impuestoDTO) {
        
        Contribucion contribucion = new Contribucion();// contribucionDao.findById(impuestoDTO.getId_impuesto()).orElse(null);
        contribucion.setCodigoContribucion(impuestoDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(impuestoDTO.getConcepto_contribucion());
        TipoPago tipoPago =tipoPagoDao.findById(impuestoDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion=catalogoDescripcionDao.findById(impuestoDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        
        //Contribucion newContribucion=
        contribucionDao.save(contribucion);
        //impuesto.setContribucion(contribucion);
        Impuesto impuesto= mapearEntidad(impuestoDTO);
        impuesto.setContribucion(contribucion);
        //impuesto.setIdContribucionImpuesto(newContribucion.getIdContribucion());
        Impuesto newImpuesto=impuestoDao.save(impuesto);
        ImpuestoDTO impuestoRespuesta= mapearDTO(newImpuesto);
  
        return impuestoRespuesta;
    }
    
    public Object crear(ImpuestoDTO impuestoDTO) {
        System.out.println(impuestoDao.existsById(impuestoDTO.getCodigo_contribucion())+"existeeeeeeeeeeeeee ");
        if((impuestoDao.existsById(impuestoDTO.getCodigo_contribucion())))
            return 0;// significa que usuario ya existe
        if((!impuestoDao.existsById(impuestoDTO.getCodigo_contribucion()))){
            Impuesto impuesto= mapearEntidad(impuestoDTO);
        
            Impuesto impuestoNueva=impuestoDao.save(impuesto);
            ImpuestoDTO aprovechamientoRespuesta= mapearDTO(impuestoNueva);
            return aprovechamientoRespuesta;
        }
        return null;
      
    }

    @Override
    public entidadRespuesta<ImpuestoDTO> findAll(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Impuesto> impuestosP =impuestoDao.findAll(pageable);
        List<Impuesto> listaImpuestos =impuestosP.getContent();
        List<ImpuestoDTO> lista= new ArrayList<>();
        for(Impuesto impuestos:listaImpuestos){
            lista.add(new ImpuestoDTO(impuestos.getIdContribucionImpuesto(), impuestos.getIdTipoImpuesto().getIdTipoImpuesto(),impuestos.getIdTipoImpuesto().getDescripcion(), impuestos.getCantidad(), impuestos.getContribucion().getCodigoContribucion(), impuestos.getContribucion().getConceptoContribucion(), impuestos.getContribucion().getIdTipoPago().getIdTipoPago(), impuestos.getContribucion().getIdTipoPago().getNombrePago(),impuestos.getContribucion().getIdDescripcion().getIdDescripcion(),
            impuestos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(impuestosP.getNumber());
        entidadrespuesta.setMedidaPagina(impuestosP.getSize());
        entidadrespuesta.setTotalElementos(impuestosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(impuestosP.getTotalPages());
        entidadrespuesta.setUltima(impuestosP.isLast());
        entidadrespuesta.setPrimera(impuestosP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }
    
    public entidadRespuesta<ImpuestoCompletoDTO> findAllC(int numeroDePagina,int MedidaDePagina) {
        Pageable pageable= PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Impuesto> impuestosP =impuestoDao.findAll(pageable);
        List<Impuesto> listaImpuestos =impuestosP.getContent();
        List<ImpuestoCompletoDTO> lista= new ArrayList<>();
        for(Impuesto impuestos:listaImpuestos){
            lista.add(new ImpuestoCompletoDTO("Impuestos",impuestos.getIdContribucionImpuesto(), impuestos.getIdTipoImpuesto().getDescripcion(), impuestos.getCantidad(), impuestos.getContribucion().getCodigoContribucion(), impuestos.getContribucion().getConceptoContribucion(), impuestos.getContribucion().getIdTipoPago().getNombrePago(), impuestos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta=new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(impuestosP.getNumber());
        entidadrespuesta.setMedidaPagina(impuestosP.getSize());
        entidadrespuesta.setTotalElementos(impuestosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(impuestosP.getTotalPages());
        entidadrespuesta.setUltima(impuestosP.isLast());
        entidadrespuesta.setPrimera(impuestosP.isFirst());
        
        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(String id) {
        Impuesto impuesto=impuestoDao 
                .findById(id).orElse(null);
        
        impuestoDao.delete(impuesto);
    }

    @Override
    public ImpuestoDTO update(ImpuestoDTO impuestoDTO, String id) {
        Impuesto impuesto=impuestoDao 
                .findById(id).orElse(null);

        Contribucion contribucion = new Contribucion();// contribucionDao.findById(impuestoDTO.getId_impuesto()).orElse(null);
        contribucion.setCodigoContribucion(impuestoDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(impuestoDTO.getConcepto_contribucion());
        TipoPago tipoPago =tipoPagoDao.findById(impuestoDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion=catalogoDescripcionDao.findById(impuestoDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);

        //impuesto.setIdContribucionImpuesto(impuestoDTO.getId_impuesto());
        CatalogoImpuesto catalogoImpuesto = catalogoImpuestoDao.findById(impuestoDTO.getCatalogo_impuesto()).orElse(null);
        impuesto.setIdTipoImpuesto(catalogoImpuesto);
        impuesto.setCantidad(impuestoDTO.getCantidad());
        //Contribucion contribucion = contribucionDao.findById(impuestoDTO.getId_impuesto()).orElse(null);
        impuesto.setContribucion(contribucion);
        
        Impuesto impuestoActualizado=impuestoDao.save(impuesto);
        
        return mapearDTO(impuestoActualizado);
    }
    
    private ImpuestoDTO mapearDTO(Impuesto impuesto){
        ImpuestoDTO impuestoDTO = new ImpuestoDTO();
        impuestoDTO.setCodigo_contribucion(impuesto.getContribucion().getCodigoContribucion());
        impuestoDTO.setConcepto_contribucion(impuesto.getContribucion().getConceptoContribucion());
        impuestoDTO.setId_impuesto(impuesto.getIdContribucionImpuesto());
        impuestoDTO.setCatalogo_impuesto(impuesto.getIdTipoImpuesto().getIdTipoImpuesto());
        impuestoDTO.setCantidad(impuesto.getCantidad());
        
        return  impuestoDTO;
    }
    
    private Impuesto mapearEntidad(ImpuestoDTO impuestoDTO){
        Impuesto impuesto = new Impuesto();
       
        impuesto.setIdContribucionImpuesto(impuestoDTO.getCodigo_contribucion());
        CatalogoImpuesto catalogoImpuesto = catalogoImpuestoDao.findById(impuestoDTO.getCatalogo_impuesto()).orElse(null);
        impuesto.setIdTipoImpuesto(catalogoImpuesto);
        impuesto.setCantidad(impuestoDTO.getCantidad());
        //Contribucion contribucion = contribucionDao.findById(impuestoDTO.getId_impuesto()).orElse(null);
        //impuesto.setContribucion(contribucion);
   
        return impuesto;
    }

    @Override
    public Page<ImpuestoDTO> findAll(Pageable pageable) {
        Page<Impuesto> listaImpuestos =impuestoDao.findAll(pageable);
        List<ImpuestoDTO> lista= new ArrayList<>();
        for(Impuesto impuestos:listaImpuestos){
            lista.add(new ImpuestoDTO(impuestos.getIdContribucionImpuesto(), impuestos.getIdTipoImpuesto().getIdTipoImpuesto(),impuestos.getIdTipoImpuesto().getDescripcion(), impuestos.getCantidad(), impuestos.getContribucion().getCodigoContribucion(), impuestos.getContribucion().getConceptoContribucion(), impuestos.getContribucion().getIdTipoPago().getIdTipoPago(), impuestos.getContribucion().getIdTipoPago().getNombrePago(),impuestos.getContribucion().getIdDescripcion().getIdDescripcion(),
            impuestos.getContribucion().getIdDescripcion().getDescripcion()));
}
        return (Page<ImpuestoDTO>) lista;
    }
    
    public Page<ImpuestoCompletoDTO> findAllC(Pageable pageable) {
        Page<Impuesto> listaImpuestos =impuestoDao.findAll(pageable);
        List<ImpuestoCompletoDTO> lista= new ArrayList<>();
        for(Impuesto impuestos:listaImpuestos){
            lista.add(new ImpuestoCompletoDTO("Impuestos",impuestos.getIdContribucionImpuesto(), impuestos.getIdTipoImpuesto().getDescripcion(), impuestos.getCantidad(), impuestos.getContribucion().getCodigoContribucion(), impuestos.getContribucion().getConceptoContribucion(), impuestos.getContribucion().getIdTipoPago().getNombrePago(), impuestos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<ImpuestoCompletoDTO>) lista;
    }
}
