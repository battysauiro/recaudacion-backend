/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.AprovechamientoMultaVehicularDTO;
import com.recaudacionMunicipio.DTO.entidades.AprovechamientoMultaVehicularCompletaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.ICatalogoAprovechamientoDao;
import com.recaudacionMunicipio.dao.IaprovechamientoDao;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.ImultaVehicularDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.dao.ItipoVehiculoDao;
import com.recaudacionMunicipio.modelo.Aprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoAprovechamiento;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Multavehicular;
import com.recaudacionMunicipio.modelo.TipoPago;
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
public class AprovechamientoMultaVehicularImpl implements Servicios<AprovechamientoMultaVehicularDTO> {

    @Autowired
    private IcontribucionDao contribucionDao;

    @Autowired
    private ItipoPagoDao tipoPagoDao;

    @Autowired
    private IcatalogoDescripcionDao catalogoDescripcionDao;

    @Autowired
    private IaprovechamientoDao aprovechamientoDao;

    @Autowired
    private ICatalogoAprovechamientoDao catalogoAprovechamientoDao;

    @Autowired
    private ImultaVehicularDao aprovechamientoMultaVehicularDao;

    @Autowired
    private ItipoVehiculoDao tipoVehiculoDao;

    @Override
    public AprovechamientoMultaVehicularDTO findById(String id) {
        Multavehicular aprovechamientoMultaVehicular = aprovechamientoMultaVehicularDao.findById(id).orElse(null);
        AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO = new AprovechamientoMultaVehicularDTO(aprovechamientoMultaVehicular.getIdContribucionMultaVehicular(), aprovechamientoMultaVehicular.getDescripcionArticulo(), aprovechamientoMultaVehicular.getUmaMin(), aprovechamientoMultaVehicular.getUmaMax(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getIdTipoVehiculo(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getNombreVehiculo(), aprovechamientoMultaVehicular.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion());
        return aprovechamientoMultaVehicularDTO;
    }

    public AprovechamientoMultaVehicularCompletaDTO findByIdCompleto(String id) {
        Multavehicular aprovechamientoMultaVehicular = aprovechamientoMultaVehicularDao.findById(id).orElse(null);
        AprovechamientoMultaVehicularCompletaDTO aprovechamientoMultaVehicularCompletaDTO = new AprovechamientoMultaVehicularCompletaDTO(aprovechamientoMultaVehicular.getDescripcionArticulo(), aprovechamientoMultaVehicular.getUmaMin(), aprovechamientoMultaVehicular.getUmaMax(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getNombreVehiculo(), aprovechamientoMultaVehicular.getIdContribucionMultaVehicular(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion());
        return aprovechamientoMultaVehicularCompletaDTO;
    }

    @Override
    public AprovechamientoMultaVehicularDTO save(AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO) {

        Contribucion contribucion = new Contribucion();
        contribucion.setCodigoContribucion(aprovechamientoMultaVehicularDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(aprovechamientoMultaVehicularDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(aprovechamientoMultaVehicularDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(aprovechamientoMultaVehicularDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        Aprovechamiento aprovechamiento = new Aprovechamiento();
        aprovechamiento.setIdContribucionAprovechamiento(aprovechamientoMultaVehicularDTO.getCodigo_contribucion());
        CatalogoAprovechamiento catalogo = catalogoAprovechamientoDao.findById(aprovechamientoMultaVehicularDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
        aprovechamiento.setContribucion(contribucion);
        aprovechamientoDao.save(aprovechamiento);

        Multavehicular aprovechamientoMultaVehicular = mapearEntidad(aprovechamientoMultaVehicularDTO);
        aprovechamientoMultaVehicular.setAprovechamiento(aprovechamiento);
        Multavehicular newAprovechamientoMultaVehicular = aprovechamientoMultaVehicularDao.save(aprovechamientoMultaVehicular);
        AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularRespuesta = mapearDTO(newAprovechamientoMultaVehicular);

        return aprovechamientoMultaVehicularRespuesta;
    }

    public Object crear(AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO) {
        System.out.println(aprovechamientoMultaVehicularDao.existsById(aprovechamientoMultaVehicularDTO.getCodigo_contribucion()) + "existeeeeeeeeeeeeee ");
        if ((aprovechamientoMultaVehicularDao.existsById(aprovechamientoMultaVehicularDTO.getCodigo_contribucion()))) {
            return 0;// significa que usuario ya existe
        }
        if ((!aprovechamientoMultaVehicularDao.existsById(aprovechamientoMultaVehicularDTO.getCodigo_contribucion()))) {
            Contribucion contribucion = new Contribucion();
            contribucion.setCodigoContribucion(aprovechamientoMultaVehicularDTO.getCodigo_contribucion());
            contribucion.setConceptoContribucion(aprovechamientoMultaVehicularDTO.getConcepto_contribucion());
            TipoPago tipoPago = tipoPagoDao.findById(aprovechamientoMultaVehicularDTO.getId_tipo_pago()).orElse(null);
            contribucion.setIdTipoPago(tipoPago);
            CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(aprovechamientoMultaVehicularDTO.getId_descripcion()).orElse(null);
            contribucion.setIdDescripcion(catalogoDescripcion);
            contribucionDao.save(contribucion);
            Aprovechamiento aprovechamiento = new Aprovechamiento();
            aprovechamiento.setIdContribucionAprovechamiento(aprovechamientoMultaVehicularDTO.getCodigo_contribucion());
            CatalogoAprovechamiento catalogo = catalogoAprovechamientoDao.findById(aprovechamientoMultaVehicularDTO.getId_catalogo()).orElse(null);
            aprovechamiento.setIdTipoAprovechamiento(catalogo);
            aprovechamiento.setContribucion(contribucion);
            aprovechamientoDao.save(aprovechamiento);

            Multavehicular aprovechamientoMultaVehicular = mapearEntidad(aprovechamientoMultaVehicularDTO);
            aprovechamientoMultaVehicular.setAprovechamiento(aprovechamiento);
            Multavehicular newAprovechamientoMultaVehicular = aprovechamientoMultaVehicularDao.save(aprovechamientoMultaVehicular);
            AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularRespuesta = mapearDTO(newAprovechamientoMultaVehicular);

            return aprovechamientoMultaVehicularRespuesta;
        }
        return null;

    }

    @Override
    public entidadRespuesta<AprovechamientoMultaVehicularDTO> findAll(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Multavehicular> multaVehicularP = aprovechamientoMultaVehicularDao.findAll(pageable);
        List<Multavehicular> listaMultaVehicular = multaVehicularP.getContent();
        List<AprovechamientoMultaVehicularDTO> lista = new ArrayList<>();
        for (Multavehicular aprovechamientoMultaVehicular : listaMultaVehicular) {
            lista.add(new AprovechamientoMultaVehicularDTO(aprovechamientoMultaVehicular.getIdContribucionMultaVehicular(), aprovechamientoMultaVehicular.getDescripcionArticulo(), aprovechamientoMultaVehicular.getUmaMin(), aprovechamientoMultaVehicular.getUmaMax(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getIdTipoVehiculo(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getNombreVehiculo(), aprovechamientoMultaVehicular.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(multaVehicularP.getNumber());
        entidadrespuesta.setMedidaPagina(multaVehicularP.getSize());
        entidadrespuesta.setTotalElementos(multaVehicularP.getTotalElements());
        entidadrespuesta.setTotalPaginas(multaVehicularP.getTotalPages());
        entidadrespuesta.setUltima(multaVehicularP.isLast());
        entidadrespuesta.setPrimera(multaVehicularP.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<AprovechamientoMultaVehicularCompletaDTO> findAllC(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Multavehicular> multaVehicularP = aprovechamientoMultaVehicularDao.findAll(pageable);
        List<Multavehicular> listaMultaVehicular = multaVehicularP.getContent();
        List<AprovechamientoMultaVehicularCompletaDTO> lista = new ArrayList<>();
        for (Multavehicular aprovechamientoMultaVehicular : listaMultaVehicular) {
            lista.add(new AprovechamientoMultaVehicularCompletaDTO(aprovechamientoMultaVehicular.getDescripcionArticulo(), aprovechamientoMultaVehicular.getUmaMin(), aprovechamientoMultaVehicular.getUmaMax(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getNombreVehiculo(), aprovechamientoMultaVehicular.getIdContribucionMultaVehicular(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(multaVehicularP.getNumber());
        entidadrespuesta.setMedidaPagina(multaVehicularP.getSize());
        entidadrespuesta.setTotalElementos(multaVehicularP.getTotalElements());
        entidadrespuesta.setTotalPaginas(multaVehicularP.getTotalPages());
        entidadrespuesta.setUltima(multaVehicularP.isLast());
        entidadrespuesta.setPrimera(multaVehicularP.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(String id) {
        Multavehicular aprovechamientoMultaVehicular = aprovechamientoMultaVehicularDao
                .findById(id).orElse(null);

        aprovechamientoMultaVehicularDao.delete(aprovechamientoMultaVehicular);
    }

    @Override
    public AprovechamientoMultaVehicularDTO update(AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO, String id) {

        Contribucion contribucion = contribucionDao.findById(id).orElse(null);
        contribucion.setConceptoContribucion(aprovechamientoMultaVehicularDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(aprovechamientoMultaVehicularDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(aprovechamientoMultaVehicularDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);
        Aprovechamiento aprovechamiento = aprovechamientoDao.findById(id).orElse(null);
        CatalogoAprovechamiento catalogo = catalogoAprovechamientoDao.findById(aprovechamientoMultaVehicularDTO.getId_catalogo()).orElse(null);
        aprovechamiento.setIdTipoAprovechamiento(catalogo);
        aprovechamientoDao.save(aprovechamiento);

        Multavehicular aprovechamientoMultaVehicular = aprovechamientoMultaVehicularDao
                .findById(id).orElse(null);

        aprovechamientoMultaVehicular.setDescripcionArticulo(aprovechamientoMultaVehicularDTO.getDescripcion_articulo());
        aprovechamientoMultaVehicular.setUmaMin(aprovechamientoMultaVehicularDTO.getUma_min());
        aprovechamientoMultaVehicular.setUmaMax(aprovechamientoMultaVehicularDTO.getUma_max());
        //aprovechamientoMultaVehicular.setAprovechamiento(aprovechamiento);
        Tipovehiculo tipoVehiculo = tipoVehiculoDao.findById(aprovechamientoMultaVehicularDTO.getTipo_vehiculo()).orElse(null);
        aprovechamientoMultaVehicular.setIdTipoVehiculo(tipoVehiculo);

        Multavehicular aprovechamientoMultaVehicularActualizado = aprovechamientoMultaVehicularDao.save(aprovechamientoMultaVehicular);

        return mapearDTO(aprovechamientoMultaVehicularActualizado);
    }

    private AprovechamientoMultaVehicularDTO mapearDTO(Multavehicular multavehicular) {
        AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO = new AprovechamientoMultaVehicularDTO();
        aprovechamientoMultaVehicularDTO.setCodigo_contribucion(multavehicular.getAprovechamiento().getContribucion().getCodigoContribucion());
        aprovechamientoMultaVehicularDTO.setId_multa_vehicular(multavehicular.getIdContribucionMultaVehicular());
        aprovechamientoMultaVehicularDTO.setDescripcion_articulo(multavehicular.getDescripcionArticulo());
        aprovechamientoMultaVehicularDTO.setUma_min(multavehicular.getUmaMin());
        aprovechamientoMultaVehicularDTO.setUma_max(multavehicular.getUmaMax());
        aprovechamientoMultaVehicularDTO.setTipo_vehiculo(multavehicular.getIdTipoVehiculo().getIdTipoVehiculo());

        return aprovechamientoMultaVehicularDTO;
    }

    private Multavehicular mapearEntidad(AprovechamientoMultaVehicularDTO aprovechamientoMultaVehicularDTO) {
        Multavehicular aprovechamientoMultaVehicular = new Multavehicular();
        //Aprovechamiento aprovechamiento =aprovechamientoDao.findById(aprovechamientoMultaVehicularDTO.getId_multa_vehicular()).orElse(null);
        aprovechamientoMultaVehicular.setIdContribucionMultaVehicular(aprovechamientoMultaVehicularDTO.getCodigo_contribucion());
        aprovechamientoMultaVehicular.setDescripcionArticulo(aprovechamientoMultaVehicularDTO.getDescripcion_articulo());
        aprovechamientoMultaVehicular.setUmaMin(aprovechamientoMultaVehicularDTO.getUma_min());
        aprovechamientoMultaVehicular.setUmaMax(aprovechamientoMultaVehicularDTO.getUma_max());
        //aprovechamientoMultaVehicular.setAprovechamiento(aprovechamiento);
        Tipovehiculo tipoVehiculo = tipoVehiculoDao.findById(aprovechamientoMultaVehicularDTO.getTipo_vehiculo()).orElse(null);
        aprovechamientoMultaVehicular.setIdTipoVehiculo(tipoVehiculo);

        return aprovechamientoMultaVehicular;
    }

    @Override
    public Page<AprovechamientoMultaVehicularDTO> findAll(Pageable pageable) {
        Page<Multavehicular> listaMultaVehicular = aprovechamientoMultaVehicularDao.findAll(pageable);
        List<AprovechamientoMultaVehicularDTO> lista = new ArrayList<>();
        for (Multavehicular aprovechamientoMultaVehicular : listaMultaVehicular) {
            lista.add(new AprovechamientoMultaVehicularDTO(aprovechamientoMultaVehicular.getIdContribucionMultaVehicular(), aprovechamientoMultaVehicular.getDescripcionArticulo(), aprovechamientoMultaVehicular.getUmaMin(), aprovechamientoMultaVehicular.getUmaMax(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getIdTipoVehiculo(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getNombreVehiculo(), aprovechamientoMultaVehicular.getAprovechamiento().getIdContribucionAprovechamiento(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getIdTipoAprovechamiento(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getIdTipoPago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getIdDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<AprovechamientoMultaVehicularDTO>) lista;
    }

    public Page<AprovechamientoMultaVehicularCompletaDTO> findAllC(Pageable pageable) {
        Page<Multavehicular> listaMultaVehicular = aprovechamientoMultaVehicularDao.findAll(pageable);
        List<AprovechamientoMultaVehicularCompletaDTO> lista = new ArrayList<>();
        for (Multavehicular aprovechamientoMultaVehicular : listaMultaVehicular) {
            lista.add(new AprovechamientoMultaVehicularCompletaDTO(aprovechamientoMultaVehicular.getDescripcionArticulo(), aprovechamientoMultaVehicular.getUmaMin(), aprovechamientoMultaVehicular.getUmaMax(), aprovechamientoMultaVehicular.getIdTipoVehiculo().getNombreVehiculo(), aprovechamientoMultaVehicular.getIdContribucionMultaVehicular(), aprovechamientoMultaVehicular.getAprovechamiento().getIdTipoAprovechamiento().getDescripcion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getCodigoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getConceptoContribucion(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdTipoPago().getNombrePago(), aprovechamientoMultaVehicular.getAprovechamiento().getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<AprovechamientoMultaVehicularCompletaDTO>) lista;
    }
}
