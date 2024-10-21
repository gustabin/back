/*
 * 
 */

package ar.com.santanderrio.obp.servicios.perfil.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.perfil.dao.ContratosPerfilDAO;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratoPerfil;
import ar.com.santanderrio.obp.servicios.perfil.entities.ContratosPerfil;

/**
 * The class ContratosPerfilDAOImpl.
 */
@Component
public class ContratosPerfilDAOImpl implements ContratosPerfilDAO {

    /** The titulo mya. */
    @Value("${SERVICIOS.TERMINOSYCONDICIONESDEMENSAJESYAVISOS.TITULO}")
    private String tituloMya;

    /** The url mya. */
    @Value("${SERVICIOS.TERMINOSYCONDICIONESDEMENSAJESYAVISOS}")
    private String urlMya;

    /** The titulo sorpresa. */
    @Value("${SERVICIOS.CONTRATODEMENSAJESYAVISOSSORPRESA.TITULO}")
    private String tituloSorpresa;

    /** The url sorpresa. */
    @Value("${SERVICIOS.CONTRATODEMENSAJESYAVISOSSORPRESA}")
    private String urlSorpresa;

    /** The titulo online banking. */
    @Value("${SERVICIOS.CONTRATODEONLINEBANKING.TITULO}")
    private String tituloOnlineBanking;

    /** The url online banking. */
    @Value("${SERVICIOS.CONTRATODEONLINEBANKING}")
    private String urlOnlineBanking;

    /** The titulo superclub. */
    @Value("${SERVICIOS.TERMINOSYCONDICIONESSUPERCLUB.TITULO}")
    private String tituloSuperclub;

    /** The url superclub. */
    @Value("${SERVICIOS.TERMINOSYCONDICIONESSUPERCLUB}")
    private String urlSuperclub;

    /** The titulo tyc tarjeta recargable. */
    @Value("${PRODUCTOS.TERMINOSYCONDICIONESDETARJETARECARGABLE.TITULO}")
    private String tituloTycTarjetaRecargable;

    /** The url tyc tarjeta recargable. */
    @Value("${PRODUCTOS.TERMINOSYCONDICIONESDETARJETARECARGABLE}")
    private String urlTycTarjetaRecargable;

    /** The titulo comisiones tarjeta recargable. */
    @Value("${PRODUCTOS.COMISIONESDETARJETARECARGABLE.TITULO}")
    private String tituloComisionesTarjetaRecargable;

    /** The url comisiones tarjeta recargable. */
    @Value("${PRODUCTOS.COMISIONESDETARJETARECARGABLE}")
    private String urlComisionesTarjetaRecargable;

    /** The titulo comparacion de comisiones. */
    @Value("${COMPARACIONDECOMISIONES.TITULO}")
    private String tituloComparacionDeComisiones;

    /** The url comparacion de comisiones. */
    @Value("${COMPARACIONDECOMISIONES}")
    private String urlComparacionDeComisiones;

    /** The titulo regimen transparencia bcra. */
    @Value("${REGIMENDETRANSPARENCIADELBCRA.TITULO}")
    private String tituloRegimenTransparenciaBcra;

    /** The url regimen transparencia bcra. */
    @Value("${REGIMENDETRANSPARENCIADELBCRA}")
    private String urlRegimenTransparenciaBcra;

    /** The titulo despegar. */
    @Value("${SERVICIOS.TERMINOSYCONDICIONESDESCCONDESPEGAR.TITULO}")
    private String tituloDespegar;
    
    /** The url despegar. */
    @Value("${SERVICIOS.TERMINOSYCONDICIONESDESCCONDESPEGAR}")
    private String urlDespegar;

    /** The titulo envio efectivo. */
    @Value("${PRODUCTOS.CONDICIONESDEENVIODEEFECTIVO.TITULO}")
    private String tituloEnvioEfectivo;
    
    /** The url envio efectivo. */
    @Value("${PRODUCTOS.CONDICIONESDEENVIODEEFECTIVO}")
    private String urlEnvioEfectivo;

    /** The titulo monedero. */
    @Value("${PRODUCTOS.TERMINOSYCONDICIONESMONEDERO.TITULO}")
    private String tituloMonedero;

    /** The url monedero. */
    @Value("${PRODUCTOS.TERMINOSYCONDICIONESMONEDERO}")
    private String urlMonedero;

    /** The titulo todo pago. */
    @Value("${PRODUCTOS.TERMINOSYCONDICIONESTODOPAGO.TITULO}")
    private String tituloTodoPago;

    /** The url todo pago. */
    @Value("${PRODUCTOS.TERMINOSYCONDICIONESTODOPAGO}")
    private String urlTodoPago;
    
    @Value("${PRESTAMO.PREAPROBADO.MONOPRODUCTO.TYC.TITULO}")
    private String tituloTyCPrestamoPreaprobado;
    
    @Value("${PRESTAMO.PREAPROBADO.MONOPRODUCTO.TYC}")
    private String urlTyCPrestamoPreaprobado;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.perfil.dao.ContratosPerfilDAO#
     * consultarContratos()
     */
    @Override
    public ContratosPerfil consultarContratos() {
        ContratosPerfil contratosPerfil = new ContratosPerfil();
        contratosPerfil.setContratosServicios(obtenerContratosServicios());
        contratosPerfil.setContratosProductos(obtenerContratosProductos());
        contratosPerfil.setContratosComunicacionesBcra(obtenerContratosComunicacionesBcra());
        return contratosPerfil;
    }

    /**
	 * Obtener contratos servicios.
	 *
	 * @return the list
	 */
    private List<ContratoPerfil> obtenerContratosServicios() {
        List<ContratoPerfil> listContratos = new ArrayList<ContratoPerfil>();
        listContratos.add(new ContratoPerfil(tituloOnlineBanking, urlOnlineBanking));
        listContratos.add(new ContratoPerfil(tituloMya, urlMya));
        listContratos.add(new ContratoPerfil(tituloSorpresa, urlSorpresa));
        listContratos.add(new ContratoPerfil(tituloSuperclub, urlSuperclub));
        listContratos.add(new ContratoPerfil(tituloDespegar, urlDespegar));

        return listContratos;
    }

    /**
	 * Obtener contratos productos.
	 *
	 * @return the list
	 */
    private List<ContratoPerfil> obtenerContratosProductos() {
        List<ContratoPerfil> listContratos = new ArrayList<ContratoPerfil>();
        listContratos.add(new ContratoPerfil(tituloTycTarjetaRecargable, urlTycTarjetaRecargable));
        listContratos.add(new ContratoPerfil(tituloComisionesTarjetaRecargable, urlComisionesTarjetaRecargable));
        listContratos.add(new ContratoPerfil(tituloTyCPrestamoPreaprobado, urlTyCPrestamoPreaprobado));
        listContratos.add(new ContratoPerfil(tituloEnvioEfectivo, urlEnvioEfectivo));
        listContratos.add(new ContratoPerfil(tituloMonedero, urlMonedero));
        listContratos.add(new ContratoPerfil(tituloTodoPago, urlTodoPago));
        
        return listContratos;
    }

    /**
	 * Obtener contratos comunicaciones bcra.
	 *
	 * @return the list
	 */
    private List<ContratoPerfil> obtenerContratosComunicacionesBcra() {
        List<ContratoPerfil> listContratos = new ArrayList<ContratoPerfil>();
        listContratos.add(new ContratoPerfil(tituloComparacionDeComisiones, urlComparacionDeComisiones));
        listContratos.add(new ContratoPerfil(tituloRegimenTransparenciaBcra, urlRegimenTransparenciaBcra));
        return listContratos;
    }

}
