/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.dto.TipoDomicilioEnum;
import ar.com.santanderrio.obp.servicios.perfil.manager.CambioDomicilioManager;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosDomicilioView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.bo.ReimpresionTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ComprobanteAltaReimpresionTarjetas;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.DatosReimpresionComprobante;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.TarjetaSolicitadaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager.ReimpresionTarjetasManager;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetas;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.ComprobanteAltaReimpresionView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomicilioView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.MotivoView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.SolicitudReimpresionTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.TarjetaCandidataView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.TarjetaSolicitadaView;

/**
 * The Class ReimpresionTarjetasManagerImpl.
 */
@Component
public class ReimpresionTarjetasManagerImpl implements ReimpresionTarjetasManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReimpresionTarjetasManagerImpl.class);

    /** The cambio domicilio manager. */
    @Autowired
    private CambioDomicilioManager cambioDomicilioManager;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The reimpresion tarjetas BO. */
    @Autowired
    private ReimpresionTarjetasBO reimpresionTarjetasBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /**
     * Validar la operacion y gestionar el desafio RSA.
     */
    @Autowired
    private DesafioOperacionRSA<AltaDatosReimpresionTarjetasView> desafioOperacionRSA;

    /** The valor desafio reimpresion trj. */
    @Value("${TRJCOORD.OPERAINDISTINTO.REIMPRESIONTRJ:3}")
    private Integer valorDesafioReimpresionTrj;

    /** The motivos str. */
    @Value("${MOTIVOS}")
    private String motivosStr;

    /** MARCA BANELCO. */
    private static final String MARCA_BANELCO_UTILS = "VISA Debito";

    private static final String MARCA_VISA_UTILS = "VISA";
    
    /** MARCA_BANELCO_VIEW. */
    private static final String MARCA_BANELCO_VIEW = "VISA Débito";

    /** Motivo destruccion. */
    private static final String MOTIVO_DESTRUCCION_VIEW = "Destrucción y/o Deterioro";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager.
     * ReimpresionTarjetasManager#getDatosIniciales()
     */
    @Override
    public Respuesta<SolicitudReimpresionTarjetasView> getDatosIniciales() {
        Respuesta<SolicitudReimpresionTarjetasView> respuesta = new Respuesta<SolicitudReimpresionTarjetasView>();
        SolicitudReimpresionTarjetasView solicitudReimpresionTarjetasView = new SolicitudReimpresionTarjetasView();
        Respuesta<List<TarjetaCandidataDTO>> candidatasDTOs = reimpresionTarjetasBO.getTarjetasCandidatasReimpresion();
        if (EstadoRespuesta.OK.equals(candidatasDTOs.getEstadoRespuesta())) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
            List<TarjetaCandidataView> candidatas = buildRespuestaTarjetasCandidatas(candidatasDTOs.getRespuesta());
            solicitudReimpresionTarjetasView.setTarjetasCandidatas(candidatas);
            solicitudReimpresionTarjetasView.setMsjAlertaCantidadTarjetas(
                    mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.REIMPRESION_MSJ_ALERTA_CANT_TARJETAS)
                            .getMensaje());
            solicitudReimpresionTarjetasView.setMsjAlertaRoboHurto(mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.REIMPRESION_MSJ_ALERTA_ROBO_HURTO).getMensaje());

            respuesta.setRespuesta(solicitudReimpresionTarjetasView);
        } else {
            respuesta = Respuesta.copy(SolicitudReimpresionTarjetasView.class, candidatasDTOs);
        }
        return respuesta;
    }

    /**
     * Builds the respuesta tarjetas candidatas.
     *
     * @param listaCandidatasDTOs
     *            the lista candidatas DT os
     * @return the list
     */
    private List<TarjetaCandidataView> buildRespuestaTarjetasCandidatas(List<TarjetaCandidataDTO> listaCandidatasDTOs) {
        List<TarjetaCandidataView> result = new ArrayList<TarjetaCandidataView>();
        for (TarjetaCandidataDTO tarjetaCandidataDTO : listaCandidatasDTOs) {
            TarjetaCandidataView view = new TarjetaCandidataView();
            view.setAlias(tarjetaCandidataDTO.getAlias());
            view.setNro(tarjetaCandidataDTO.getNro());
            view.setNroTarjetaConFormato(tarjetaCandidataDTO.getNroTarjetaConFormato());
            if (MARCA_BANELCO_UTILS.equals(tarjetaCandidataDTO.getTipoDescripcion())) {
                view.setTipoCuentaDescripcion(MARCA_BANELCO_VIEW);
            } else {
                view.setTipoCuentaDescripcion(tarjetaCandidataDTO.getTipoDescripcion());
            }
            
			if ((StringUtils.contains(tarjetaCandidataDTO.getTipoDescripcion(), MARCA_VISA_UTILS)
					|| StringUtils.contains(tarjetaCandidataDTO.getTipoDescripcion(), MARCA_BANELCO_UTILS)
					|| StringUtils.contains(tarjetaCandidataDTO.getTipoDescripcion(), MARCA_BANELCO_VIEW))
							&& (!esVisaRecargable(tarjetaCandidataDTO.getCuentaRel()))) {

				view.setPuedeSerContactless(Boolean.TRUE);

			}
            
            view.setTitular(tarjetaCandidataDTO.getTitular());
            result.add(view);
        }
        return result;
    }

    
    private boolean esVisaRecargable(Cuenta cuenta) {
    	return cuenta.esVisaRecargableContactless();
    }
    
    
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager.
     * ReimpresionTarjetasManager#getDomiciliosDisponibles()
     */
    @Override
    public Respuesta<DomiciliosDisponiblesView> getDomiciliosDisponibles() {
        Respuesta<DomiciliosDisponiblesView> respuesta;
        Respuesta<DatosDomicilioView> respuestaDomicilios = cambioDomicilioManager
                .obtenerDomiciliosContacto(Boolean.TRUE);
        if (EstadoRespuesta.OK.equals(respuestaDomicilios.getEstadoRespuesta())) {
            DomiciliosDisponiblesView domiciliosDisponibles = buildDomicilioView(respuestaDomicilios.getRespuesta());

            domiciliosDisponibles.setMensajeInformacion(mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_ERROR_HURTO_ROBO)
                    .getMensaje());
            domiciliosDisponibles.setMensajeInformacionUrl(mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_ERROR_HURTO_ROBO_URL)
                    .getMensaje());

            respuesta = respuestaFactory.crearRespuestaOk(domiciliosDisponibles, StringUtils.EMPTY,
                    CodigoMensajeConstantes.AVISO_REIMPRESION);
            ItemMensajeRespuesta item = new ItemMensajeRespuesta();
            String mensajeCuerpo = mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CUERPO_AYUDA_REIMPRESION_PARRAFO_1).getMensaje();
            mensajeCuerpo += mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.CUERPO_AYUDA_REIMPRESION_PARRAFO_2).getMensaje();
            item.setMensaje(mensajeCuerpo);
            respuesta.getItemsMensajeRespuesta().add(item);
        } else {
            respuesta = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_REIMPRESION);
        }
        return respuesta;
    }

    /**
     * Builds the domicilio view.
     *
     * @param datosDomicilioView
     *            the datos domicilio view
     * @return the domicilios disponibles view
     */
    private DomiciliosDisponiblesView buildDomicilioView(DatosDomicilioView datosDomicilioView) {
        DomiciliosDisponiblesView domiciliosDisponiblesView = new DomiciliosDisponiblesView();
        List<DomicilioView> domiciliosDisponibles = new ArrayList<DomicilioView>();
        
        DomicilioView domLaboral = null;
        List<DomicilioView> domLaborales = new ArrayList<DomicilioView>();
        
        for (CambioDomicilioView cambioDomicilioView : datosDomicilioView.getDomicilios()) {
            TipoDomicilioEnum tipoDomicilio = TipoDomicilioEnum.getTipoDomicilio(cambioDomicilioView.getTipoDomicilio());
            if (muestaDomicilio(tipoDomicilio)) {
                DomicilioView domicilioView = new DomicilioView();
                domicilioView.setDomicilioId(cambioDomicilioView.getDomicilioId());
                domicilioView.setDomicilio(this.buildDomicilioCompleto(cambioDomicilioView));
                domicilioView.setApt(cambioDomicilioView.getApt());
                domicilioView.setCalle(cambioDomicilioView.getCalle());
                domicilioView.setCaracteristica(cambioDomicilioView.getCaracteristica());
                domicilioView.setCodigoPostal(cambioDomicilioView.getCodigoPostal());
                domicilioView.setComuna(cambioDomicilioView.getComuna());
                domicilioView.setDepartamento(cambioDomicilioView.getDepartamento());
                domicilioView.setLocalidad(cambioDomicilioView.getLocalidad());
                domicilioView.setNumeroTelefono(cambioDomicilioView.getNumeroTelefono());
                domicilioView.setPiso(cambioDomicilioView.getPiso());
                domicilioView.setPrefijo(cambioDomicilioView.getPrefijo());
                domicilioView.setProvincia(cambioDomicilioView.getProvincia());
                domicilioView.setDescProvincia(cambioDomicilioView.getDescProvincia());
                domicilioView.setTelefono(cambioDomicilioView.getTelefono());
                domicilioView.setTipoDomicilio(tipoDomicilio.getDescripcion());
                domicilioView.setSecuenciaDomicilio(cambioDomicilioView.getSecuenciaDomicilio());
                domicilioView.setDescPais(cambioDomicilioView.getDescPais());
                domicilioView.setTieneProductos(!cambioDomicilioView.getDescripcionesProductos().isEmpty());
                domicilioView.setModificable(esDomicilioModificable(tipoDomicilio));
                domiciliosDisponibles.add(domicilioView);
                if (TipoDomicilioEnum.TIPO_DOMICLIO_LABORAL.equals(tipoDomicilio)) {
                    domLaborales.add(domLaboral);
                    domLaboral = domicilioView;
                }
            }
        }
        domiciliosDisponibles.removeAll(domLaborales);
        
        domiciliosDisponiblesView.setDomiciliosDisponibles(domiciliosDisponibles);
        domiciliosDisponiblesView.setProvincias(datosDomicilioView.getProvincias());
        domiciliosDisponiblesView.setMotivos(buildMotivos());
        return domiciliosDisponiblesView;
    }

    private boolean esDomicilioModificable(TipoDomicilioEnum tipoDomicilio) {
        return TipoDomicilioEnum.TIPO_DOMICILIO_PRINCIPAL.equals(tipoDomicilio)
                || TipoDomicilioEnum.TIPO_DOMICLIO_LABORAL.equals(tipoDomicilio)
                || TipoDomicilioEnum.TIPO_ALTERNATIVO.equals(tipoDomicilio);
    }

    private boolean muestaDomicilio(TipoDomicilioEnum tipoDomicilio) {
        return esDomicilioModificable(tipoDomicilio)
                || TipoDomicilioEnum.TIPO_DOMICLIO_PRE.equals(tipoDomicilio)
                || TipoDomicilioEnum.TIPO_BANCA_PRIVADA.equals(tipoDomicilio);
    }
    
    /**
     * Builds the motivos.
     *
     * @return the list
     */
    private List<MotivoView> buildMotivos() {
        List<MotivoView> listaMotivoView = new ArrayList<MotivoView>();
            	
    	String[] motivos = motivosStr.split("\\|");
    	
    	for (String motivo : motivos) {
    		String[] motivoAtributos = motivo.split("\\,");
    		MotivoView motivoView = new MotivoView();
    		motivoView.setId(motivoAtributos[0]);
    		motivoView.setDescripcion(motivoAtributos[1]);
    		listaMotivoView.add(motivoView);    		
    	}
       
        return listaMotivoView;
    }

    /**
     * Builds the domicilio completo.
     *
     * @param cambioDomicilioView
     *            the cambio domicilio view
     * @return the string
     */
    private String buildDomicilioCompleto(CambioDomicilioView cambioDomicilioView) {
        StringBuilder domicilio = new StringBuilder();
        domicilio.append(cambioDomicilioView.getCalle());
        domicilio.append(" Nº ");
        domicilio.append(cambioDomicilioView.getApt());
        domicilio.append(StringUtils.EMPTY);
        if (StringUtils.isNotBlank(cambioDomicilioView.getPiso())) {
            domicilio.append(" piso ");
            domicilio.append(cambioDomicilioView.getPiso());
        }
        if (StringUtils.isNotBlank(cambioDomicilioView.getDepartamento())) {
            domicilio.append(" \"");
            domicilio.append(cambioDomicilioView.getDepartamento());
            domicilio.append("\"");
        }
        domicilio.append(", ");
        domicilio.append(cambioDomicilioView.getLocalidad());
        domicilio.append(", ");
        domicilio.append(cambioDomicilioView.getDescProvincia());
        domicilio.append(", ");
        domicilio.append(cambioDomicilioView.getDescPais());
        domicilio.append(".");
        return domicilio.toString();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager.
     * ReimpresionTarjetasManager#altaReimpresionTarjetas(ar.com.santanderrio.
     * obp.servicios.tarjetas.reimpresion.web.view.
     * AltaDatosReimpresionTarjetasView)
     */
    @Override
    public Respuesta<AltaDatosReimpresionTarjetasView> altaReimpresionTarjetas(
            AltaDatosReimpresionTarjetasView altaDatosReimpresionTarjetasView) {

        if (altaDatosReimpresionTarjetasView.getDesafio() == null) {
            cargarHashValidacion(crearMapaDeLaVista(altaDatosReimpresionTarjetasView));
        }

        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        Respuesta<AltaDatosReimpresionTarjetasView> respuesta = desafioOperacionRSA.validarOperacionRSA(
                altaDatosReimpresionTarjetasView, valorDesafioReimpresionTrj, autentificacionCodEstDTO);

        if (respuesta == null || !EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
            return respuesta;
        }

        validarHash(crearMapaDeLaVista(altaDatosReimpresionTarjetasView));

        AltaDatosReimpresionTarjetas altaDatosReimpresionTarjetas = buildAltaDatosReimpresionTarjetas(
                altaDatosReimpresionTarjetasView);

        Respuesta<ComprobanteAltaReimpresionTarjetas> respuestaBO = reimpresionTarjetasBO
                .solicitudReimpresionTarjetas(altaDatosReimpresionTarjetas);
        if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())
                || EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {

            respuesta = buildRespuestaAltaReimpresion(respuestaBO.getRespuesta(),
                    altaDatosReimpresionTarjetasView.getDomicilios());
        }
        validarCodigoEstadisticaReimpresionTarjeta(respuestaBO.getRespuesta().getTarjetaSolicitadas());

        return respuesta;
    }
    
    /**
     * Validar codigo estadistica reimpresion tarjeta.
     *
     * @param tarjetasSolicitadas
     *            the tarjetas solicitadas
     */
    private void validarCodigoEstadisticaReimpresionTarjeta(List<TarjetaSolicitadaDTO> tarjetasSolicitadas) {
        for (TarjetaSolicitadaDTO ts : tarjetasSolicitadas) {
            if (ts.getIsOk()) {
                grabarEstadisticaReimpresion(ts, true);
                grabarEstadisticaContactless(ts, true);
            } else {
                grabarEstadisticaReimpresion(ts, false);
                grabarEstadisticaContactless(ts, false);
            }
        }
    }

    /**
     * Grabar estadistica reimpresion.
     *
     * @param ts
     *            the ts
     * @param estadoEstadistica
     *            the estado estadistica
     */
    private void grabarEstadisticaReimpresion(TarjetaSolicitadaDTO ts, Boolean estadoEstadistica) {
        if (StringUtils.equals(MARCA_BANELCO_UTILS, ts.getTipoCuenta())) {
            estadisticaManager.add("11158",
                    Boolean.TRUE.equals(estadoEstadistica) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                            : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        } else {
            estadisticaManager.add("11159",
                    Boolean.TRUE.equals(estadoEstadistica) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                            : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
    }
    
    private void grabarEstadisticaContactless(TarjetaSolicitadaDTO ts, Boolean estadoEstadistica) {
    	if (StringUtils.equals(MARCA_BANELCO_UTILS, ts.getTipoCuenta()) || StringUtils.equals(MARCA_BANELCO_VIEW, ts.getTipoCuenta()) && ts.isEsContactless()) {
        	estadisticaManager.add(EstadisticasConstants.SOLICITUD_REIMPRESION_TARJETA_DEBITO_CONTACTLESS,
                    Boolean.TRUE.equals(estadoEstadistica) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                            : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    	 } else if (StringUtils.equals( ts.getTipoCuenta(),MARCA_VISA_UTILS) && ts.isEsContactless()) {
            estadisticaManager.add(EstadisticasConstants.SOLICITUD_REIMPRESION_TARJETA_CREDITO_CONTACTLESS,
                    Boolean.TRUE.equals(estadoEstadistica) ? EstadisticasConstants.CODIGO_ESTADISTICAS_OK
                            : EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
    }
    

    /**
     * Builds the alta datos reimpresion tarjetas.
     *
     * @param altaDatosReimpresionTarjetasView
     *            the alta datos reimpresion tarjetas view
     * @return the alta datos reimpresion tarjetas
     */
    private AltaDatosReimpresionTarjetas buildAltaDatosReimpresionTarjetas(
            AltaDatosReimpresionTarjetasView altaDatosReimpresionTarjetasView) {
        AltaDatosReimpresionTarjetas altaDatosReimpresionTarjetas = new AltaDatosReimpresionTarjetas();
        altaDatosReimpresionTarjetas.setTarjetasSolicitadas(altaDatosReimpresionTarjetasView.getTarjetasSolicitadas());
        altaDatosReimpresionTarjetas.setDomicilios(altaDatosReimpresionTarjetasView.getDomicilios());
        altaDatosReimpresionTarjetas.setMotivos(altaDatosReimpresionTarjetasView.getMotivos());
        return altaDatosReimpresionTarjetas;
    }

    /**
     * Crear mapa de la vista.
     *
     * @param altaDatosReimpresionTarjetasView
     *            the alta datos reimpresion tarjetas view
     * @return the map
     */
    public Map<String, Object> crearMapaDeLaVista(AltaDatosReimpresionTarjetasView altaDatosReimpresionTarjetasView) {
        LOGGER.info("Creando hash de los atributos...");
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        int contador = 0;
        for (TarjetaSolicitadaDTO tarjetaDTO : altaDatosReimpresionTarjetasView.getTarjetasSolicitadas()) {
            mapaAtributos.put(String.valueOf(contador), tarjetaDTO != null ? tarjetaDTO.getNro() : "");
            contador++;

        }

        return mapaAtributos;
    }

    /**
     * Cargar hash.
     *
     * @param viewMap
     *            the view map
     */
    public void cargarHashValidacion(Map<String, Object> viewMap) {
        String hashView = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Se guarda el hash en sesion.");
        sesionParametros.setValidacionHash(hashView);
    }

    /**
     * Validar hash.
     *
     * @param viewMap
     *            the view map
     */
    public void validarHash(Map<String, Object> viewMap) {
        String inputHash = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Validando el hash {} de la sesion con el hash de la entidad {}",
                sesionParametros.getValidacionHash(), inputHash);
        HashUtils.compareHash(sesionParametros.getValidacionHash(), inputHash);
    }

    /**
     * Builds the respuesta alta reimpresion.
     *
     * @param comprobanteAltaReimpresionTarjetas
     *            the comprobante alta reimpresion tarjetas
     * @param domicilios
     *            the domicilios
     * @return the respuesta
     */
    private Respuesta<AltaDatosReimpresionTarjetasView> buildRespuestaAltaReimpresion(
            ComprobanteAltaReimpresionTarjetas comprobanteAltaReimpresionTarjetas, List<DomicilioView> domicilios) {

        Respuesta<AltaDatosReimpresionTarjetasView> respuesta = respuestaFactory
                .crearRespuestaOk(AltaDatosReimpresionTarjetasView.class);
        DatosReimpresionComprobante datosReimpresionComprobante = new DatosReimpresionComprobante();
        AltaDatosReimpresionTarjetasView comprobanteAltaReimpresionTarjetasView = new AltaDatosReimpresionTarjetasView();
        ComprobanteAltaReimpresionView comprobanteAltaReimpresionView = new ComprobanteAltaReimpresionView();
        List<TarjetaSolicitadaView> tarjetas = new ArrayList<TarjetaSolicitadaView>();
        Boolean hayTarjetaConError = Boolean.FALSE;
        Boolean todasConError = Boolean.FALSE;
        int contadorErrores = 0;
        String mensajeOK = StringUtils.EMPTY;
        String mensajeTarjetaConError = StringUtils.EMPTY;
        for (TarjetaSolicitadaDTO tarjetaSolicitada : comprobanteAltaReimpresionTarjetas.getTarjetaSolicitadas()) {
            if (!tarjetaSolicitada.getIsOk()) {
                hayTarjetaConError = Boolean.TRUE;
                contadorErrores++;
                if (tarjetaSolicitada.isSolicitudEnCurso()) {

                    mensajeOK = MessageFormat.format(mensajeManager
                            .obtenerMensajePorCodigo(CodigoMensajeConstantes.REIMPRESION_SOLICITUD_EN_CURSO)
                            .getMensaje(), tarjetaSolicitada.getTipoCuenta());

                    tarjetaSolicitada.setMensaje(mensajeOK);

                } else {

                    mensajeOK = MessageFormat.format(mensajeManager
                            .obtenerMensajePorCodigo(CodigoMensajeConstantes.REIMPRESION_TARJETA_ERROR_GENERICO)
                            .getMensaje(), tarjetaSolicitada.getTipoCuenta());

                    tarjetaSolicitada.setMensaje(mensajeOK);
                }
            }
            tarjetas.add(buildTarjetaSolicitadaView(tarjetaSolicitada));
        }
        if (contadorErrores == tarjetas.size()) {
            todasConError = Boolean.TRUE;
        }
        comprobanteAltaReimpresionView.setDomicilios(domicilios);
        datosReimpresionComprobante.setDomicilios(domicilios);
        comprobanteAltaReimpresionView.setTarjetasSolicitadas(tarjetas);
        datosReimpresionComprobante.setTarjetas(tarjetas);
        comprobanteAltaReimpresionView.setFechaHora(
                ISBANStringUtils.formatearFecha(new Date(), ISBANStringUtils.FORMATO_FECHA_HORA_COMPROBANTE));
        comprobanteAltaReimpresionTarjetasView.setComprobante(comprobanteAltaReimpresionView);

        if (comprobanteAltaReimpresionTarjetas.getTarjetaSolicitadas().size() == 1) {

            mensajeTarjetaConError = obtenerMensajeReimpresionSimple(
                    comprobanteAltaReimpresionTarjetas.getTarjetaSolicitadas().get(0), respuesta);

        } else if (comprobanteAltaReimpresionTarjetas.getTarjetaSolicitadas().size() > 1) {

            if (todasConError) {
                mensajeOK = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_ERROR)
                        .getMensaje();

                mensajeTarjetaConError = mensajeOK;
            } else if (hayTarjetaConError) {
                mensajeOK = mensajeManager
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_OK_PARCIAL).getMensaje();
                mensajeTarjetaConError = mensajeOK + mensajeManager
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_CONSULTA_TRACKING)
                        .getMensaje();

            } else {
                mensajeOK = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_OK)
                        .getMensaje();
                mensajeTarjetaConError = mensajeOK + mensajeManager
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_CONSULTA_TRACKING)
                        .getMensaje();
            }
        }

        comprobanteAltaReimpresionTarjetasView.setMensaje(mensajeTarjetaConError);
        respuesta.setRespuesta(comprobanteAltaReimpresionTarjetasView);
        sesionParametros.setDatosReimpresionComprobante(datosReimpresionComprobante);
        return respuesta;
    }

    /**
     * obtenerMensajeReimpresionSimple.
     *
     * @param tarjetaSolicitadaDTO
     *            the tarjeta solicitada DTO
     * @param respuesta
     *            the respuesta
     * @return the string
     */
    private String obtenerMensajeReimpresionSimple(TarjetaSolicitadaDTO tarjetaSolicitadaDTO,
            Respuesta<AltaDatosReimpresionTarjetasView> respuesta) {

        List<ItemMensajeRespuesta> itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();

        String mensajeTarjetaConError = StringUtils.EMPTY;
        String tarjetaMensaje = tarjetaSolicitadaDTO.getTipoCuenta();

        if (tarjetaSolicitadaDTO.getAlias() != null && !"".equals(tarjetaSolicitadaDTO.getAlias())) {
            tarjetaMensaje = tarjetaMensaje.concat(" ").concat(tarjetaSolicitadaDTO.getAlias());

        } else {
            tarjetaMensaje = tarjetaMensaje.concat(" ").concat(tarjetaSolicitadaDTO.getNroTarjetaConFormato());
        }
        if (!tarjetaSolicitadaDTO.getIsOk()) {
            TipoError tipoError;
            if (tarjetaSolicitadaDTO.isSolicitudEnCurso()) {
                mensajeTarjetaConError = tarjetaSolicitadaDTO.getMensaje();
                tipoError = TipoError.ERROR_SOLICITUD_EN_CURSO;
            } else {
                mensajeTarjetaConError = MessageFormat.format(mensajeManager
                        .obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_ERROR_SIMPLE)
                        .getMensaje(), tarjetaSolicitadaDTO.getTipoCuenta());
                tipoError = TipoError.ERROR_GENERICO;
            }
            ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensajeTarjetaConError);
            item.setTipoError(tipoError.getDescripcion());
            itemsMensajeRespuesta.add(item);
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
            respuesta.setItemMensajeRespuesta(itemsMensajeRespuesta);
        } else {

            mensajeTarjetaConError = MessageFormat.format(mensajeManager
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_REIMPRESION_OK_SIMPLE).getMensaje(),
                    tarjetaSolicitadaDTO.getTipoCuenta());

        }
        return mensajeTarjetaConError;
    }

    /**
     * Asignar estadisticas de autenticacion.
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken("11516");
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco("11155");
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudCoordenadas("11153");
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionCoordenadas("11156");
        return autentificacionCodEstDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.manager.
     * ReimpresionTarjetasManager#descargarComprobante()
     */
    @Override
    public Respuesta<ReporteView> descargarComprobante() {
        Respuesta<ReporteView> respuestaView = null;
        DatosReimpresionComprobante datos = sesionParametros.getDatosReimpresionComprobante();
        ReporteView reporteView = new ReporteView();
        try {
            Respuesta<Reporte> respuestaReporte = reimpresionTarjetasBO.descargarComprobante(datos);
            if (respuestaReporte.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                respuestaView = new Respuesta<ReporteView>();
                respuestaView.setEstadoRespuesta(respuestaReporte.getEstadoRespuesta());
                reporteView = ReporteView.fromReporte(respuestaReporte.getRespuesta());
                respuestaView.setRespuesta(reporteView);
                estadisticaManager.add(EstadisticasConstants.REIMPRESION_TARJETAS_COMPROBANTE,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            } else {
                respuestaView = new Respuesta<ReporteView>();
                respuestaView.setEstadoRespuesta(respuestaReporte.getEstadoRespuesta());
                estadisticaManager.add(EstadisticasConstants.REIMPRESION_TARJETAS_COMPROBANTE,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
            }
        } catch (Exception ex) {
            estadisticaManager.add(EstadisticasConstants.REIMPRESION_TARJETAS_COMPROBANTE,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        }
        return respuestaView;
    }

    /**
     * Builds the tarjeta solicitada view.
     *
     * @param tarjetaSolicitada
     *            the tarjeta solicitada
     * @return the tarjeta solicitada view
     */
    private TarjetaSolicitadaView buildTarjetaSolicitadaView(TarjetaSolicitadaDTO tarjetaSolicitada) {
        TarjetaSolicitadaView tarjetaSolicitadaView = new TarjetaSolicitadaView();
        tarjetaSolicitadaView.setMotivo(tarjetaSolicitada.getMotivoReimpresion());
        tarjetaSolicitadaView.setNroTarjetaConFormato(tarjetaSolicitada.getNroTarjetaConFormato());
        tarjetaSolicitadaView.setSolicitudNro(tarjetaSolicitada.getSolicitudNro());
        tarjetaSolicitadaView.setMensaje(tarjetaSolicitada.getMensaje());

        if (MARCA_BANELCO_UTILS.equals(tarjetaSolicitada.getTipoCuenta())) {
            tarjetaSolicitadaView.setTipoCuenta(MARCA_BANELCO_VIEW);
        } else {
            tarjetaSolicitadaView.setTipoCuenta(tarjetaSolicitada.getTipoCuenta());
        }
        tarjetaSolicitadaView.setTitular(tarjetaSolicitada.getTitular());
        tarjetaSolicitadaView.setAlias(tarjetaSolicitada.getAlias());
        tarjetaSolicitadaView.setIsOk(tarjetaSolicitada.getIsOk());
        return tarjetaSolicitadaView;

    }
    
    public static void main(String[] args) {
		
    	String motivoLista = "02,Robo y/o Hurto|06,Destruccion y/o Deterioro|09,ContactLess";
    	
    	String[] motivos = motivoLista.split("\\|");
    	
    	List<MotivoView> listaMotivoView = new ArrayList<MotivoView>();
    	for (String motivo : motivos) {
    		String[] motivoAtributos = motivo.split("\\,");
    		MotivoView motivoView = new MotivoView();
    		motivoView.setId(motivoAtributos[0]);
    		motivoView.setDescripcion(motivoAtributos[1]);
    		listaMotivoView.add(motivoView);    		
    	}
    	
    	for (MotivoView motivoViewSolo : listaMotivoView) {
    		System.out.println(motivoViewSolo.getId() + " " + motivoViewSolo.getDescripcion());
    	}
	}

}
