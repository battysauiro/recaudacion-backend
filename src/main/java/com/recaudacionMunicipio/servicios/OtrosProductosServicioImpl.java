/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.OtrosProductosDTO;
import com.recaudacionMunicipio.DTO.entidades.OtrosProductosCompletoDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcatalogoDescripcionDao;
import com.recaudacionMunicipio.dao.IcatalogoOtrosProductosDao;
import com.recaudacionMunicipio.dao.IcontribucionDao;
import com.recaudacionMunicipio.dao.IotrosProductosDao;
import com.recaudacionMunicipio.dao.IperiodicidadDao;
import com.recaudacionMunicipio.dao.ItipoPagoDao;
import com.recaudacionMunicipio.modelo.CatalogoDescripcion;
import com.recaudacionMunicipio.modelo.CatalogoOtrosProductos;
import com.recaudacionMunicipio.modelo.Contribucion;
import com.recaudacionMunicipio.modelo.Otrosproductos;
import com.recaudacionMunicipio.modelo.Periodicidad;
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
public class OtrosProductosServicioImpl implements Servicios<OtrosProductosDTO> {

    @Autowired
    private IotrosProductosDao otrosProductosDao;

    @Autowired
    private IperiodicidadDao periodicidadDao;

    @Autowired
    private IcatalogoOtrosProductosDao catalogoOtrosProductosDao;

    @Autowired
    private IcontribucionDao contribucionDao;

    @Autowired
    private ItipoPagoDao tipoPagoDao;

    @Autowired
    private IcatalogoDescripcionDao catalogoDescripcionDao;

    @Override
    public OtrosProductosDTO findById(String id) {
        Otrosproductos otrosProductos = otrosProductosDao.findById(id).orElse(null);
        OtrosProductosDTO otrosProductosDTO = new OtrosProductosDTO(otrosProductos.getIdContribucionProductos(), otrosProductos.getCantidad(), otrosProductos.getPeriodicidad().getIdPeriodicidad(), otrosProductos.getPeriodicidad().getNombrePeriodicidad(), otrosProductos.getIdOtrosProductos().getIdOtrosProductos(), otrosProductos.getIdOtrosProductos().getDescripcion(), otrosProductos.getContribucion().getCodigoContribucion(), otrosProductos.getContribucion().getConceptoContribucion(), otrosProductos.getContribucion().getIdTipoPago().getIdTipoPago(), otrosProductos.getContribucion().getIdTipoPago().getNombrePago(), otrosProductos.getContribucion().getIdDescripcion().getIdDescripcion(), otrosProductos.getContribucion().getIdDescripcion().getDescripcion(),otrosProductos.getContribucion().getNivelContribucion());
        return otrosProductosDTO;
    }

    public OtrosProductosCompletoDTO findByIdCompleto(String id) {
        Otrosproductos otrosProductos = otrosProductosDao.findById(id).orElse(null);
        OtrosProductosCompletoDTO otrosProductosCompletoDTO = new OtrosProductosCompletoDTO(otrosProductos.getIdContribucionProductos(), otrosProductos.getCantidad(), otrosProductos.getPeriodicidad().getNombrePeriodicidad(), otrosProductos.getIdOtrosProductos().getDescripcion(), otrosProductos.getContribucion().getCodigoContribucion(), otrosProductos.getContribucion().getConceptoContribucion(), otrosProductos.getContribucion().getIdTipoPago().getNombrePago(), otrosProductos.getContribucion().getIdDescripcion().getDescripcion());
        return otrosProductosCompletoDTO;
    }

    @Override
    public OtrosProductosDTO save(OtrosProductosDTO otrosProductosDTO) {
        Contribucion contribucion = new Contribucion();// contribucionDao.findById(impuestoDTO.getId_impuesto()).orElse(null);
        contribucion.setCodigoContribucion(otrosProductosDTO.getCodigo_contribucion());
        contribucion.setConceptoContribucion(otrosProductosDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(otrosProductosDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(otrosProductosDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);

        //Contribucion newContribucion=
        //contribucionDao.save(contribucion); ESTO AL PARECER SE PUEDE ELIMINAR
        Otrosproductos otrosProductos = mapearEntidad(otrosProductosDTO);
        otrosProductos.setContribucion(contribucion);
        Otrosproductos newOtrosProductos = otrosProductosDao.save(otrosProductos);
        OtrosProductosDTO otrosProductosRespuesta = mapearDTO(newOtrosProductos);

        return otrosProductosRespuesta;
    }

    public Object crear(OtrosProductosDTO otrosProductosDTO) {
        System.out.println(otrosProductosDao.existsById(otrosProductosDTO.getCodigo_contribucion()) + "existeeeeeeeeeeeeee ");
        if ((otrosProductosDao.existsById(otrosProductosDTO.getCodigo_contribucion()))) {
            return 0;// significa que usuario ya existe
        }
        if ((!otrosProductosDao.existsById(otrosProductosDTO.getCodigo_contribucion()))) {
            Contribucion contribucion = new Contribucion();// contribucionDao.findById(impuestoDTO.getId_impuesto()).orElse(null);
            contribucion.setCodigoContribucion(otrosProductosDTO.getCodigo_contribucion());
            contribucion.setConceptoContribucion(otrosProductosDTO.getConcepto_contribucion());
            TipoPago tipoPago = tipoPagoDao.findById(otrosProductosDTO.getId_tipo_pago()).orElse(null);
            contribucion.setIdTipoPago(tipoPago);
            CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(otrosProductosDTO.getId_descripcion()).orElse(null);
            contribucion.setIdDescripcion(catalogoDescripcion);

            //Contribucion newContribucion=
            //contribucionDao.save(contribucion); ESTO AL PARECER SE PUEDE ELIMINAR
            Otrosproductos otrosProductos = mapearEntidad(otrosProductosDTO);
            otrosProductos.setContribucion(contribucion);
            Otrosproductos newOtrosProductos = otrosProductosDao.save(otrosProductos);
            OtrosProductosDTO otrosProductosRespuesta = mapearDTO(newOtrosProductos);

            return otrosProductosRespuesta;
        }
        return null;

    }

    @Override
    public entidadRespuesta<OtrosProductosDTO> findAll(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Otrosproductos> otrosProductosP = otrosProductosDao.findAll(pageable);
        List<Otrosproductos> listaOtrosProductos = otrosProductosP.getContent();
        List<OtrosProductosDTO> lista = new ArrayList<>();
        for (Otrosproductos otrosProductos : listaOtrosProductos) {
            lista.add(new OtrosProductosDTO(otrosProductos.getIdContribucionProductos(), otrosProductos.getCantidad(), otrosProductos.getPeriodicidad().getIdPeriodicidad(), otrosProductos.getPeriodicidad().getNombrePeriodicidad(), otrosProductos.getIdOtrosProductos().getIdOtrosProductos(), otrosProductos.getIdOtrosProductos().getDescripcion(), otrosProductos.getContribucion().getCodigoContribucion(), otrosProductos.getContribucion().getConceptoContribucion(), otrosProductos.getContribucion().getIdTipoPago().getIdTipoPago(), otrosProductos.getContribucion().getIdTipoPago().getNombrePago(), otrosProductos.getContribucion().getIdDescripcion().getIdDescripcion(), otrosProductos.getContribucion().getIdDescripcion().getDescripcion(),otrosProductos.getContribucion().getNivelContribucion()));
        }
        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(otrosProductosP.getNumber());
        entidadrespuesta.setMedidaPagina(otrosProductosP.getSize());
        entidadrespuesta.setTotalElementos(otrosProductosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(otrosProductosP.getTotalPages());
        entidadrespuesta.setUltima(otrosProductosP.isLast());
        entidadrespuesta.setPrimera(otrosProductosP.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    public entidadRespuesta<OtrosProductosCompletoDTO> findAllC(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<Otrosproductos> otrosProductosP = otrosProductosDao.findAll(pageable);
        List<Otrosproductos> listaOtrosProductos = otrosProductosP.getContent();
        List<OtrosProductosCompletoDTO> lista = new ArrayList<>();
        for (Otrosproductos otrosProductos : listaOtrosProductos) {
            lista.add(new OtrosProductosCompletoDTO(otrosProductos.getIdContribucionProductos(), otrosProductos.getCantidad(), otrosProductos.getPeriodicidad().getNombrePeriodicidad(), otrosProductos.getIdOtrosProductos().getDescripcion(), otrosProductos.getContribucion().getCodigoContribucion(), otrosProductos.getContribucion().getConceptoContribucion(), otrosProductos.getContribucion().getIdTipoPago().getNombrePago(), otrosProductos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(otrosProductosP.getNumber());
        entidadrespuesta.setMedidaPagina(otrosProductosP.getSize());
        entidadrespuesta.setTotalElementos(otrosProductosP.getTotalElements());
        entidadrespuesta.setTotalPaginas(otrosProductosP.getTotalPages());
        entidadrespuesta.setUltima(otrosProductosP.isLast());
        entidadrespuesta.setPrimera(otrosProductosP.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(String id) {
        Otrosproductos otrosProductos = otrosProductosDao
                .findById(id).orElse(null);

        otrosProductosDao.delete(otrosProductos);
    }

    @Override
    public OtrosProductosDTO update(OtrosProductosDTO otrosProductosDTO, String id) {
        Contribucion contribucion = contribucionDao.findById(id).orElse(null);
        contribucion.setConceptoContribucion(otrosProductosDTO.getConcepto_contribucion());
        TipoPago tipoPago = tipoPagoDao.findById(otrosProductosDTO.getId_tipo_pago()).orElse(null);
        contribucion.setIdTipoPago(tipoPago);
        CatalogoDescripcion catalogoDescripcion = catalogoDescripcionDao.findById(otrosProductosDTO.getId_descripcion()).orElse(null);
        contribucion.setIdDescripcion(catalogoDescripcion);
        contribucionDao.save(contribucion);

        Otrosproductos otrosProductos = otrosProductosDao
                .findById(id).orElse(null);

        //otrosProductos.setIdContribucionProductos(otrosProductosDTO.getId_otros_productos());
        CatalogoOtrosProductos catalogoOtrosProductos = catalogoOtrosProductosDao.findById(otrosProductosDTO.getCatalogo_otros_productos()).orElse(null);
        otrosProductos.setCantidad(otrosProductosDTO.getCantidad());
        Periodicidad periodicidad = periodicidadDao.findById(otrosProductosDTO.getPeriodicidad()).orElse(null);
        otrosProductos.setPeriodicidad(periodicidad);
        otrosProductos.setIdOtrosProductos(catalogoOtrosProductos);
        //Contribucion contribucion = contribucionDao.findById(otrosProductosDTO.getId_otros_productos()).orElse(null);
        otrosProductos.setContribucion(contribucion);

        Otrosproductos otrosProductosActualizado = otrosProductosDao.save(otrosProductos);

        return mapearDTO(otrosProductosActualizado);
    }

    private OtrosProductosDTO mapearDTO(Otrosproductos otrosproductos) {
        OtrosProductosDTO otrosProductosDTO = new OtrosProductosDTO();
        otrosProductosDTO.setCodigo_contribucion(otrosproductos.getContribucion().getCodigoContribucion());
        otrosProductosDTO.setId_otros_productos(otrosproductos.getIdContribucionProductos());
        otrosProductosDTO.setCantidad(otrosproductos.getCantidad());
        otrosProductosDTO.setPeriodicidad(otrosproductos.getPeriodicidad().getIdPeriodicidad());
        otrosProductosDTO.setCatalogo_otros_productos(otrosproductos.getIdOtrosProductos().getIdOtrosProductos());

        return otrosProductosDTO;
    }

    private Otrosproductos mapearEntidad(OtrosProductosDTO otrosProductosDTO) {
        Otrosproductos otrosProductos = new Otrosproductos();

        otrosProductos.setIdContribucionProductos(otrosProductosDTO.getCodigo_contribucion());
        CatalogoOtrosProductos catalogoOtrosProductos = catalogoOtrosProductosDao.findById(otrosProductosDTO.getCatalogo_otros_productos()).orElse(null);
        otrosProductos.setCantidad(otrosProductosDTO.getCantidad());
        Periodicidad periodicidad = periodicidadDao.findById(otrosProductosDTO.getPeriodicidad()).orElse(null);
        otrosProductos.setPeriodicidad(periodicidad);
        otrosProductos.setIdOtrosProductos(catalogoOtrosProductos);
        //Contribucion contribucion = contribucionDao.findById(otrosProductosDTO.getId_otros_productos()).orElse(null);
        //otrosProductos.setContribucion(contribucion);

        return otrosProductos;
    }

    @Override
    public Page<OtrosProductosDTO> findAll(Pageable pageable) {
        Page<Otrosproductos> listaOtrosProductos = otrosProductosDao.findAll(pageable);
        List<OtrosProductosDTO> lista = new ArrayList<>();
        for (Otrosproductos otrosProductos : listaOtrosProductos) {
            lista.add(new OtrosProductosDTO(otrosProductos.getIdContribucionProductos(), otrosProductos.getCantidad(), otrosProductos.getPeriodicidad().getIdPeriodicidad(), otrosProductos.getPeriodicidad().getNombrePeriodicidad(), otrosProductos.getIdOtrosProductos().getIdOtrosProductos(), otrosProductos.getIdOtrosProductos().getDescripcion(), otrosProductos.getContribucion().getCodigoContribucion(), otrosProductos.getContribucion().getConceptoContribucion(), otrosProductos.getContribucion().getIdTipoPago().getIdTipoPago(), otrosProductos.getContribucion().getIdTipoPago().getNombrePago(), otrosProductos.getContribucion().getIdDescripcion().getIdDescripcion(), otrosProductos.getContribucion().getIdDescripcion().getDescripcion(),otrosProductos.getContribucion().getNivelContribucion()));
        }
        return (Page<OtrosProductosDTO>) lista;
    }

    public Page<OtrosProductosCompletoDTO> findAllC(Pageable pageable) {
        Page<Otrosproductos> listaOtrosProductos = otrosProductosDao.findAll(pageable);
        List<OtrosProductosCompletoDTO> lista = new ArrayList<>();
        for (Otrosproductos otrosProductos : listaOtrosProductos) {
            lista.add(new OtrosProductosCompletoDTO(otrosProductos.getIdContribucionProductos(), otrosProductos.getCantidad(), otrosProductos.getPeriodicidad().getNombrePeriodicidad(), otrosProductos.getIdOtrosProductos().getDescripcion(), otrosProductos.getContribucion().getCodigoContribucion(), otrosProductos.getContribucion().getConceptoContribucion(), otrosProductos.getContribucion().getIdTipoPago().getNombrePago(), otrosProductos.getContribucion().getIdDescripcion().getDescripcion()));
        }
        return (Page<OtrosProductosCompletoDTO>) lista;
    }

}
