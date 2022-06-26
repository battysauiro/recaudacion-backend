/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.servicios;

import com.recaudacionMunicipio.DTO.ContribucionFacturaDTO;
import com.recaudacionMunicipio.DTO.ContribuyenteFisicaDTO;
import com.recaudacionMunicipio.DTO.FacturaDTO;
import com.recaudacionMunicipio.DTO.entidadesRespuesta.entidadRespuesta;
import com.recaudacionMunicipio.dao.IcontribuyenteDao;
import com.recaudacionMunicipio.dao.IcontribuyenteFisicaDao;
import com.recaudacionMunicipio.modelo.Contribucionfactura;
import com.recaudacionMunicipio.modelo.Contribuyente;
import com.recaudacionMunicipio.modelo.ContribuyenteFisica;
import com.recaudacionMunicipio.modelo.Factura;
import java.util.ArrayList;
import java.util.Date;

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
public class ContribuyenteFisicaServicioImpl implements Servicios<ContribuyenteFisicaDTO> {

    @Autowired
    private IcontribuyenteFisicaDao contribuyenteFisicaDao;

    @Autowired
    private IcontribuyenteDao contribuyenteDao;

    @Override
    public ContribuyenteFisicaDTO findById(String id) {
        ContribuyenteFisica contribuyenteFisica = contribuyenteFisicaDao.findById(id).orElse(null);
        List<Factura> f = contribuyenteFisica.getContribuyente().getFacturaList();
        List<FacturaDTO> facturaDTO = new ArrayList<>();
        List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
        for (Factura factura : f) {
            List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
            for (Contribucionfactura contribucionf : contribucionfactura) {
                CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
            }
            facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(),"", factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO, factura.getEstadoPago()));
        }
        ContribuyenteFisicaDTO contribuyenteFisicaDTO = new ContribuyenteFisicaDTO(contribuyenteFisica.getIdContribuyenteFisica(), contribuyenteFisica.getCurpContribuyenteFisica(), contribuyenteFisica.getNombreContribuyenteFisica(), contribuyenteFisica.getApellidoPContribuyenteFisica(), contribuyenteFisica.getApellidoMContribuyenteFisica(), contribuyenteFisica.getFechaNacimiento(), contribuyenteFisica.getContribuyente().getRfcContribuyente(), contribuyenteFisica.getContribuyente().getCalle(), contribuyenteFisica.getContribuyente().getNumero(), contribuyenteFisica.getContribuyente().getColonia(), contribuyenteFisica.getContribuyente().getCp(), facturaDTO);
        return contribuyenteFisicaDTO;
    }
    
    @Override
    public ContribuyenteFisicaDTO save(ContribuyenteFisicaDTO contribuyenteFisicaDTO) {
        System.out.println(contribuyenteDao.existsById(contribuyenteFisicaDTO.getRfc_contribuyente()) + "existeeeeeeeeeeeeee " + contribuyenteFisicaDTO.getRfc_contribuyente());
        if ((!contribuyenteDao.existsById(contribuyenteFisicaDTO.getRfc_contribuyente())) && contribuyenteFisicaDao.findByCurpContribuyenteFisica(contribuyenteFisicaDTO.getCurp()) == null) {
            Contribuyente contribuyente = new Contribuyente();
            contribuyente.setRfcContribuyente(contribuyenteFisicaDTO.getRfc_contribuyente());
            contribuyente.setCalle(contribuyenteFisicaDTO.getCalle());
            contribuyente.setNumero(contribuyenteFisicaDTO.getNumero());
            contribuyente.setColonia(contribuyenteFisicaDTO.getColonia());
            contribuyente.setCp(contribuyenteFisicaDTO.getCodigo_postal());
            //Contribuyente newContribuyente= 

            contribuyenteDao.save(contribuyente);
            ContribuyenteFisica contribuyenteFisica = mapearEntidad(contribuyenteFisicaDTO);
            contribuyenteFisica.setContribuyente(contribuyente);
            //contribuyenteFisica.setIdContribuyenteFisica(contribuyente.getRfcContribuyente());
            ContribuyenteFisica newContribuyenteFisica = contribuyenteFisicaDao.save(contribuyenteFisica);
            ContribuyenteFisicaDTO contribuyenteFisicaRespuesta = mapearDTO(newContribuyenteFisica);

            return contribuyenteFisicaRespuesta;
        } else {
            return null;
        }
    }

    public Object crear(ContribuyenteFisicaDTO contribuyenteFisicaDTO) {
        System.out.println(contribuyenteDao.existsById(contribuyenteFisicaDTO.getRfc_contribuyente()) + "existeeeeeeeeeeeeee " + contribuyenteFisicaDTO.getRfc_contribuyente());
        if ((contribuyenteDao.existsById(contribuyenteFisicaDTO.getRfc_contribuyente()))) {
            return 0;// significa que el rfc existe
        }
        if (contribuyenteFisicaDao.findByCurpContribuyenteFisica(contribuyenteFisicaDTO.getCurp()) != null) {
            return 1;// significa que la curp existe
        }
        if ((!contribuyenteDao.existsById(contribuyenteFisicaDTO.getRfc_contribuyente())) && contribuyenteFisicaDao.findByCurpContribuyenteFisica(contribuyenteFisicaDTO.getCurp()) == null) {
            Contribuyente contribuyente = new Contribuyente();
            contribuyente.setRfcContribuyente(contribuyenteFisicaDTO.getRfc_contribuyente());
            contribuyente.setCalle(contribuyenteFisicaDTO.getCalle());
            contribuyente.setNumero(contribuyenteFisicaDTO.getNumero());
            contribuyente.setColonia(contribuyenteFisicaDTO.getColonia());
            contribuyente.setCp(contribuyenteFisicaDTO.getCodigo_postal());
            //Contribuyente newContribuyente= 

            contribuyenteDao.save(contribuyente);
            ContribuyenteFisica contribuyenteFisica = mapearEntidad(contribuyenteFisicaDTO);
            contribuyenteFisica.setContribuyente(contribuyente);
            //contribuyenteFisica.setIdContribuyenteFisica(contribuyente.getRfcContribuyente());
            ContribuyenteFisica newContribuyenteFisica = contribuyenteFisicaDao.save(contribuyenteFisica);
            ContribuyenteFisicaDTO contribuyenteFisicaRespuesta = mapearDTO(newContribuyenteFisica);
            System.out.println("entro en creaaaaaaaar");
            return contribuyenteFisicaRespuesta;
        }
        return null;

    }

    public entidadRespuesta<ContribuyenteFisicaDTO> findAll(int numeroDePagina, int MedidaDePagina) {
        Pageable pageable = PageRequest.of(numeroDePagina, MedidaDePagina);
        Page<ContribuyenteFisica> contribuyenteFisicaP = contribuyenteFisicaDao.findAll(pageable);
        List<ContribuyenteFisica> listaContribuyenteFisica = contribuyenteFisicaP.getContent();
        List<ContribuyenteFisicaDTO> lista = new ArrayList<>();

        for (ContribuyenteFisica contribuyenteFisica : listaContribuyenteFisica) {
            List<Factura> f = contribuyenteFisica.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(),"", factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO, factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteFisicaDTO(contribuyenteFisica.getIdContribuyenteFisica(), contribuyenteFisica.getCurpContribuyenteFisica(), contribuyenteFisica.getNombreContribuyenteFisica(), contribuyenteFisica.getApellidoPContribuyenteFisica(), contribuyenteFisica.getApellidoMContribuyenteFisica(), contribuyenteFisica.getFechaNacimiento(), contribuyenteFisica.getContribuyente().getRfcContribuyente(), contribuyenteFisica.getContribuyente().getCalle(), contribuyenteFisica.getContribuyente().getNumero(), contribuyenteFisica.getContribuyente().getColonia(), contribuyenteFisica.getContribuyente().getCp(), facturaDTO));
        }

        entidadRespuesta entidadrespuesta = new entidadRespuesta();
        entidadrespuesta.setContenido(lista);
        entidadrespuesta.setNumeroPagina(contribuyenteFisicaP.getNumber());
        entidadrespuesta.setMedidaPagina(contribuyenteFisicaP.getSize());
        entidadrespuesta.setTotalElementos(contribuyenteFisicaP.getTotalElements());
        entidadrespuesta.setTotalPaginas(contribuyenteFisicaP.getTotalPages());
        entidadrespuesta.setUltima(contribuyenteFisicaP.isLast());
        entidadrespuesta.setPrimera(contribuyenteFisicaP.isFirst());

        return entidadrespuesta;
        //return lista;
    }

    @Override
    public void delete(String id) {
        ContribuyenteFisica contribuyenteFisica = contribuyenteFisicaDao
                .findById(id).orElse(null);

        contribuyenteFisicaDao.delete(contribuyenteFisica);
    }

    @Override
    public ContribuyenteFisicaDTO update(ContribuyenteFisicaDTO contribuyenteFisicaDTO, String id) {
        ContribuyenteFisica contribuyenteFisica = contribuyenteFisicaDao
                .findById(id).orElse(null);

        Contribuyente contribuyente = contribuyenteDao
                .findById(id).orElse(null);
        //contribuyenteFisica.setIdContribuyenteFisica(contribuyenteFisicaDTO.getId_contribuyente_fisica());
        //contribuyente.setRfcContribuyente(contribuyenteFisicaDTO.getRfc_contribuyente());
        contribuyente.setCalle(contribuyenteFisicaDTO.getCalle());
        contribuyente.setNumero(contribuyenteFisicaDTO.getNumero());
        contribuyente.setColonia(contribuyenteFisicaDTO.getColonia());
        contribuyente.setCp(contribuyenteFisicaDTO.getCodigo_postal());

        contribuyenteFisica.setCurpContribuyenteFisica(contribuyenteFisicaDTO.getCurp());
        contribuyenteFisica.setNombreContribuyenteFisica(contribuyenteFisicaDTO.getNombre());
        contribuyenteFisica.setApellidoPContribuyenteFisica(contribuyenteFisicaDTO.getApellido_p());
        contribuyenteFisica.setApellidoMContribuyenteFisica(contribuyenteFisicaDTO.getApellido_m());
        contribuyenteFisica.setFechaNacimiento(contribuyenteFisicaDTO.getFecha());
        contribuyenteFisica.setContribuyente(contribuyente);

        ContribuyenteFisica contribuyenteFisicaActualizado = contribuyenteFisicaDao.save(contribuyenteFisica);

        return mapearDTO(contribuyenteFisicaActualizado);
    }

    public List<ContribuyenteFisicaDTO> findByTermino(String term) {
        List<ContribuyenteFisica> listaContribuyente = contribuyenteFisicaDao.findByCurpContribuyenteFisicaStartingWithIgnoreCaseOrIdContribuyenteFisicaStartingWithIgnoreCaseOrNombreContribuyenteFisicaStartingWithIgnoreCaseOrApellidoPContribuyenteFisicaStartingWithIgnoreCaseOrApellidoMContribuyenteFisicaStartingWithIgnoreCase(term, term, term, term, term);
        List<ContribuyenteFisicaDTO> lista = new ArrayList<>();
        for (ContribuyenteFisica contribuyente : listaContribuyente) {
            List<Factura> f = contribuyente.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(),"", factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO, factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteFisicaDTO(contribuyente.getIdContribuyenteFisica(), contribuyente.getCurpContribuyenteFisica(), contribuyente.getNombreContribuyenteFisica(), contribuyente.getApellidoPContribuyenteFisica(), contribuyente.getApellidoMContribuyenteFisica(), contribuyente.getFechaNacimiento(), contribuyente.getContribuyente().getRfcContribuyente(), contribuyente.getContribuyente().getCalle(), contribuyente.getContribuyente().getNumero(), contribuyente.getContribuyente().getColonia(), contribuyente.getContribuyente().getCp(), facturaDTO));
        }
        return lista;
    }
    
    //lista los contribuyentes
    public List<ContribuyenteFisicaDTO> listarContribuentesFisicas() {
        List<ContribuyenteFisica> listaContribuyente = contribuyenteFisicaDao.findAll();
        List<ContribuyenteFisicaDTO> lista = new ArrayList<>();
        for (ContribuyenteFisica contribuyente : listaContribuyente) {
            List<Factura> f = contribuyente.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(),"", factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO, factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteFisicaDTO(contribuyente.getIdContribuyenteFisica(), contribuyente.getCurpContribuyenteFisica(), contribuyente.getNombreContribuyenteFisica(), contribuyente.getApellidoPContribuyenteFisica(), contribuyente.getApellidoMContribuyenteFisica(), contribuyente.getFechaNacimiento(), contribuyente.getContribuyente().getRfcContribuyente(), contribuyente.getContribuyente().getCalle(), contribuyente.getContribuyente().getNumero(), contribuyente.getContribuyente().getColonia(), contribuyente.getContribuyente().getCp(), facturaDTO));
        }
        return lista;
    }

    private ContribuyenteFisicaDTO mapearDTO(ContribuyenteFisica contribuyenteFisica) {
        ContribuyenteFisicaDTO contribuyenteFisicaDTO = new ContribuyenteFisicaDTO();
        contribuyenteFisicaDTO.setId_contribuyente_fisica(contribuyenteFisica.getIdContribuyenteFisica());
        contribuyenteFisicaDTO.setCurp(contribuyenteFisica.getCurpContribuyenteFisica());
        contribuyenteFisicaDTO.setNombre(contribuyenteFisica.getNombreContribuyenteFisica());
        contribuyenteFisicaDTO.setApellido_p(contribuyenteFisica.getApellidoPContribuyenteFisica());
        contribuyenteFisicaDTO.setApellido_m(contribuyenteFisica.getApellidoMContribuyenteFisica());
        contribuyenteFisicaDTO.setFecha(contribuyenteFisica.getFechaNacimiento());

        return contribuyenteFisicaDTO;
    }

    private ContribuyenteFisica mapearEntidad(ContribuyenteFisicaDTO contribuyenteFisicaDTO) {
        ContribuyenteFisica contribuyenteFisica = new ContribuyenteFisica();
        contribuyenteFisica.setIdContribuyenteFisica(contribuyenteFisicaDTO.getRfc_contribuyente());
        contribuyenteFisica.setCurpContribuyenteFisica(contribuyenteFisicaDTO.getCurp());
        contribuyenteFisica.setNombreContribuyenteFisica(contribuyenteFisicaDTO.getNombre());
        contribuyenteFisica.setApellidoPContribuyenteFisica(contribuyenteFisicaDTO.getApellido_p());
        contribuyenteFisica.setApellidoMContribuyenteFisica(contribuyenteFisicaDTO.getApellido_m());
        contribuyenteFisica.setFechaNacimiento(contribuyenteFisicaDTO.getFecha());
        //Contribuyente contribuyente =contribuyenteDao.findById(contribuyenteFisica.getIdContribuyenteFisica()).orElse(null);
        //contribuyenteFisica.setContribuyente(contribuyente);

        return contribuyenteFisica;
    }

    @Override
    public Page<ContribuyenteFisicaDTO> findAll(Pageable pageable) {
        Page<ContribuyenteFisica> listaContribuyenteFisica = contribuyenteFisicaDao.findAll(pageable);
        List<ContribuyenteFisicaDTO> lista = new ArrayList<>();
        for (ContribuyenteFisica contribuyenteFisica : listaContribuyenteFisica) {
            List<Factura> f = contribuyenteFisica.getContribuyente().getFacturaList();
            List<FacturaDTO> facturaDTO = new ArrayList<>();
            List<ContribucionFacturaDTO> CfacturaDTO = new ArrayList<>();
            for (Factura factura : f) {
                List<Contribucionfactura> contribucionfactura = factura.getContribucionfacturaList();
                for (Contribucionfactura contribucionf : contribucionfactura) {
                    CfacturaDTO.add(new ContribucionFacturaDTO(contribucionf.getIdcontribucionFactura(), contribucionf.getContribucionId().getCodigoContribucion(), contribucionf.getFacturaId().getFolio(), contribucionf.getCantidad()));
                }
                facturaDTO.add(new FacturaDTO(factura.getFolio(), factura.getUsuarioId().getUsername(), factura.getContribuyenteId().getRfcContribuyente(),"", factura.getFecha(), factura.getDescuento(), factura.getTotal(), CfacturaDTO, factura.getEstadoPago()));
            }
            lista.add(new ContribuyenteFisicaDTO(contribuyenteFisica.getIdContribuyenteFisica(), contribuyenteFisica.getCurpContribuyenteFisica(), contribuyenteFisica.getNombreContribuyenteFisica(), contribuyenteFisica.getApellidoPContribuyenteFisica(), contribuyenteFisica.getApellidoMContribuyenteFisica(), new Date(), contribuyenteFisica.getContribuyente().getRfcContribuyente(), contribuyenteFisica.getContribuyente().getCalle(), contribuyenteFisica.getContribuyente().getNumero(), contribuyenteFisica.getContribuyente().getColonia(), contribuyenteFisica.getContribuyente().getCp(), facturaDTO));
        }
        return (Page<ContribuyenteFisicaDTO>) lista;
    }
}
