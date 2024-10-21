package ar.com.santanderrio.obp.servicios.inversiones.maps.bo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.MapServiceDAO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.ReportesMapsDAO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.AdhesionesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.InicioServiciosDeInversionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.AltaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionMapsDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.BajaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionMapsDatosRequest;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionMapsResponse;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaAdhesionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.ConsultaFeriadosRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.DatosFeriadosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ConsultaAdhesionControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.ControlMaps;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FechaCompuestaControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.LegalControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.UnknownControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlMapValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlServicioErrorValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ControlServicioValidationException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.DisclaimerReenvioException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.exception.ServicioMapsException;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;

@Component("serviciosDeInversionBO")
public class ServiciosDeInversionBOImpl implements ServiciosDeInversionBO {

	private static final String AR = "AR";

	private static final String SIN_PADRE_LISTA = "Padre de Lista Inexistente.";

	private static final String ERROR_DEFINICION_OBLIGATORIA = "La estructura no cumple con la definición obligatoria.";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosDeInversionBOImpl.class);

	private static final String SEGMENTO_BANCA_PRIVADA = "BP";

	private static final String SUBCANAL_BANCA_PRIVADA = "0000";

	private static final String CANAL_BANCA_PRIVADA = "79";

	private static final String SEGMENTO_RETAIL = "RTL";

	private static final String SUBCANAN_BANCA_RETAIL = "0099";

	private static final String CANAL_BANCA_RETAIL = "04";

	private static final String ESTADO_CONSULTA = "consulta";

	private static final String ESTADO_CONFIRMACION = "confirmacion";

	private static final String ESTADO_SIMULACION = "simulacion";

	private static final String BANCA_RETAIL = SEGMENTO_RETAIL;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private MapServiceDAO mapServiceDAO;

	@Autowired
	private ReportesMapsDAO reportesMapsDAO;
	
	/** The Class SesionCliente */
	@Autowired
	protected SesionCliente sesionCliente;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.bo.
	 * ServiciosDeInversionBO#inicioServiciosDeInversion(ar.com.santanderrio.obp
	 * .servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<InicioServiciosDeInversionDTO> inicioServiciosDeInversion(Cliente cliente) {

		InicioServiciosDeInversionDTO inicioServiciosDeInversionDTO = new InicioServiciosDeInversionDTO(false, false);
		if (!CollectionUtils.isEmpty(cliente.getCuentasPrivadas())) {
			inicioServiciosDeInversionDTO.setBpriv(true);
		}

		if (!CollectionUtils.isEmpty(cliente.getCuentasRetail())) {
			inicioServiciosDeInversionDTO.setRtl(true);
		}
		return respuestaFactory.crearRespuestaOk(InicioServiciosDeInversionDTO.class, inicioServiciosDeInversionDTO);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.bo.
	 * ServiciosDeInversionBO#obtenerControlesDisponibles(ar.com.santanderrio.
	 * obp.servicios.clientes.entities.Cliente, java.lang.String)
	 */
	@Override
	public Respuesta<FormularioControl> obtenerControlesDisponibles(Cliente cliente, String banca) {

		AltaAdhesionMapsResponse response = null;
		Boolean sinError = Boolean.TRUE;
		try {
			AltaAdhesionMapsRequestEntity request = crearRequestAdhesion(cliente.getNup(), banca);
			response = mapServiceDAO.altaAdhesionMaps(request);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener controles disponibles. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		FormularioControl formularioControl = null;
		try {
			formularioControl = response.getDatos();
			formularioControl.validateConsulta();
			for (ControlMaps control : formularioControl.getItems()) {
				validarControl(sinError, control);
			}
		} catch (ControlMapValidationException e) {
			LOGGER.error(ERROR_DEFINICION_OBLIGATORIA, e);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (sinError) {
			return respuestaFactory.crearRespuestaOk(FormularioControl.class, formularioControl);
		}
		return respuestaFactory.crearRespuestaWarning(formularioControl, "", TipoError.ALGUN_SERVICIO_ERRONEO,
				StringUtils.EMPTY);
	}

	/**
	 * @param sinError
	 * @param control
	 * @throws ControlMapValidationException
	 */
	private void validarControl(Boolean sinError, ControlMaps control) throws ControlMapValidationException {
		try {
			control.validate();
		} catch (ControlServicioValidationException e) {
			LOGGER.error("SIN DATOS.", e);
		} catch (ControlServicioErrorValidationException e) {
			LOGGER.error("Algunas con error.", e);
			sinError = Boolean.FALSE;
		}
	}

	/**
	 * @param nup
	 * @param tipoBanca
	 * @return
	 */
	private AltaAdhesionMapsRequestEntity crearRequestAdhesion(String nup, String tipoBanca) {
		AltaAdhesionMapsRequestEntity entity = new AltaAdhesionMapsRequestEntity();
		AltaAdhesionMapsDatosRequest datos = new AltaAdhesionMapsDatosRequest();
		if (BANCA_RETAIL.equalsIgnoreCase(tipoBanca)) {
			entity.setCanal(CANAL_BANCA_RETAIL);
			entity.setSubCanal(SUBCANAN_BANCA_RETAIL);
			datos.setSegmento(SEGMENTO_RETAIL);
		} else {
			entity.setCanal(CANAL_BANCA_PRIVADA);
			entity.setSubCanal(SUBCANAL_BANCA_PRIVADA);
			datos.setSegmento(SEGMENTO_BANCA_PRIVADA);
		}
		datos.setNup(nup);
		datos.setEstado(ESTADO_CONSULTA);
		entity.setDatos(datos);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.bo.
	 * ServiciosDeInversionBO#altaServicio(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.inversiones.maps.view.
	 * FormulariosAltaInicioInView)
	 */
	@Override
	public Respuesta<FormularioControl> altaServicio(String nup, FormulariosAltaInicioInView inView) {
		AltaAdhesionMapsResponse response = null;
		try {
			AltaAdhesionMapsRequestEntity request = crearRequestAdhesionAlta(nup, inView);
			response = mapServiceDAO.altaAdhesionMaps(request);
		} catch (DAOException e) {
			LOGGER.error("Error al simular alta adhesion. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return procesarRespuestaMaps(response.getDatos());
	}

	/**
	 * @param formularioControl
	 * @return
	 */
	private Respuesta<FormularioControl> procesarRespuestaMaps(FormularioControl formularioControl) {

		List<String> padreIds = new ArrayList<String>();

		try {
			// quitar filtro controles desconpcidos
			borrarControlesDesconocidos(formularioControl);
			formularioControl.validate();
			for (ControlMaps control : formularioControl.getItems()) {
				control.validate();
				if (!StringUtils.isEmpty(control.padreId())) {
					padreIds.add(control.padreId().trim());
				}

			}
		} catch (DisclaimerReenvioException e) {
			return respuestaFactory.crearRespuestaWarning(formularioControl, "", TipoError.WARNING, "");
		} catch (ControlMapValidationException e) {
			LOGGER.error(ERROR_DEFINICION_OBLIGATORIA, e);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		for (ControlMaps control : formularioControl.getItems()) {
			padreIds.removeAll(Collections.singleton(control.getId().trim()));
		}

		if (!CollectionUtils.isEmpty(padreIds)) {
			LOGGER.error(SIN_PADRE_LISTA);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(FormularioControl.class, formularioControl);

	}

	/**
	 * Borra los controles desconocidos. Esto debería quitarse cuando se desarrollen
	 * la totalidad de los controles
	 * 
	 * @param formularioControl
	 */
	private void borrarControlesDesconocidos(FormularioControl formularioControl) {
		List<ControlMaps> controlesDesconocidos = new ArrayList<ControlMaps>();
		for (ControlMaps control : formularioControl.getItems()) {
			if (control instanceof UnknownControl) {
				controlesDesconocidos.add(control);
			}
		}
		formularioControl.getItems().removeAll(controlesDesconocidos);
	}

	/**
	 * @param nup
	 * @param inView
	 * @return
	 */
	private AltaAdhesionMapsRequestEntity crearRequestAdhesionAlta(String nup, FormulariosAltaInicioInView inView) {
		AltaAdhesionMapsRequestEntity req = crearRequestAdhesion(nup, inView.getBanca());
		AltaAdhesionMapsDatosRequest datos = new AltaAdhesionMapsDatosRequest();
		datos.setTitulo(inView.getTitulo());
		datos.setIdServicio(inView.getIdServicio());
		datos.setSegmento(inView.getBanca());
		datos.setNup(nup);
		datos.setCodFondo(inView.getCodFondo());
		datos.setCuentaTitulos(inView.getCuentaTitulos());
		datos.setCuentaOperativa(inView.getCuentaOperativa());
		datos.setOperacion(inView.getOperacion());
		datos.setMoneda(inView.getMoneda());
		req.setDatos(datos);
		datos.setEstado(ESTADO_CONSULTA);
		return req;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.bo.
	 * ServiciosDeInversionBO#altaServicioFlujo(java.lang.String,
	 * ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.
	 * FormularioControl)
	 */
	@Override
	public Respuesta<FormularioControl> altaServicioFlujo(String nup, FormularioControl formularioControl) {
		AltaAdhesionMapsRequestEntity request = crearRequestAdhesionFlujo(nup, formularioControl);
		AltaAdhesionMapsResponse response = null;
		FormularioControl datos = new FormularioControl();
		try {
			response = mapServiceDAO.altaAdhesionMaps(request);
//			datos = response.getDatos();
//			ConsultaFeriadosRequestEntity requestFeriado = crearRequestFeri();
//			datos.setFeriados(mapServiceDAO.consultaFeriados(requestFeriado));
//			response.setDatos(datos);
		} catch (ServicioMapsException e) {
			LOGGER.error("Error alta adhesion. ", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error al simular alta adhesion.", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (ESTADO_CONFIRMACION.equals(response.getDatos().getEstado()) && (response.getDatos().getError() != 0)) {
			LOGGER.error("Error alta adhesion Maps. En estado confirmacion");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ALTA_ADHESION_MAPS,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return procesarRespuestaMaps(response.getDatos());
	}

	/**
	 * @param nup
	 * @param formularioControl
	 * @return
	 */
	private AltaAdhesionMapsRequestEntity crearRequestAdhesionFlujo(String nup, FormularioControl formularioControl) {
		AltaAdhesionMapsRequestEntity req = crearRequestAdhesion(nup, formularioControl.getSegmento());
		req.setDatos(formularioControl);
		return req;
	}

	private ConsultaFeriadosRequestEntity crearRequestFeri() {
		ConsultaFeriadosRequestEntity request = new ConsultaFeriadosRequestEntity();
		DatosFeriadosEntity datos = new DatosFeriadosEntity();
		datos.setFiltroPais(AR);
		datos.setIp(sesionCliente.getIpCliente());
		datos.setUsuario(sesionCliente.getResumenCliente().getUsuarioRacf());
		request.setCanal(CANAL_BANCA_RETAIL);
		request.setDatos(datos);
		request.setSubCanal(SUBCANAN_BANCA_RETAIL);
		
		return request;
	}
	
	@Override
	public Respuesta<GrillaConsultaAdhesionDTO> consultaAdhesion(Cliente cliente, String banca) {
		ConsultaAdhesionMapsResponse response;
		vaciarCacheSuscripcionesMaps();
		try {
			response = obtenerRespuestaConsultaAdhesionValidada(cliente, banca);
		} catch (ControlMapValidationException e) {
			LOGGER.error(ERROR_DEFINICION_OBLIGATORIA, e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener las suscripciones del servicio.", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return crearRespuestaConsultaAdhesion(response.getDatos().getItems());
	}

	private Respuesta<GrillaConsultaAdhesionDTO> crearRespuestaConsultaAdhesion(List<ControlMaps> listaControles) {
		GrillaConsultaAdhesionDTO grillaDTO = new GrillaConsultaAdhesionDTO();

		ConsultaAdhesionControl ca = (ConsultaAdhesionControl) listaControles.get(0);

		for (FormularioControl fc : ca.getActivas()) {
			AdhesionesDTO ad = new AdhesionesDTO();
			ad.setId(fc.getIdAdhesion());
			for (ControlMaps cm2 : fc.getItems()) {
				if (cm2.obtenerAlias() != null) {
					ad.setServicio(cm2.obtenerAlias());
				}

				if (cm2.obtenerDescripcionDinamica() != null) {
					ad.setDescripcion(cm2.obtenerDescripcionDinamica());
				}

				if (cm2.obtenerEstadoAdhesion() != null) {
					ad.setEstado(cm2.obtenerEstadoAdhesion());
				}

				if (cm2 instanceof FechaCompuestaControl) {
					for (ControlMaps cm3 : ((FechaCompuestaControl) cm2).getItems()) {
						if (cm3.obtenerFechaDesde() != null) {
							ad.setVigenciaDesde(cm3.obtenerFechaDesde());
						}

						if (cm3.obtenerFechaHasta() != null) {
							ad.setVigenciaHasta(cm3.obtenerFechaHasta());
						}
					}
				}
			}
			grillaDTO.getSuscripcionesActivas().add(ad);
		}

		for (FormularioControl fc : ca.getInactivas()) {
			AdhesionesDTO ad = new AdhesionesDTO();
			ad.setId(fc.getIdAdhesion());
			for (ControlMaps cm2 : fc.getItems()) {
				if (cm2.obtenerAlias() != null) {
					ad.setServicio(cm2.obtenerAlias());
				}

				if (cm2.obtenerDescripcionDinamica() != null) {
					ad.setDescripcion(cm2.obtenerDescripcionDinamica());
				}

				if (cm2.obtenerEstadoAdhesion() != null) {
					ad.setEstado(cm2.obtenerEstadoAdhesion());
				}

				if (cm2 instanceof FechaCompuestaControl) {
					for (ControlMaps cm3 : ((FechaCompuestaControl) cm2).getItems()) {
						if (cm3.obtenerFechaDesde() != null) {
							ad.setVigenciaDesde(cm3.obtenerFechaDesde());
						}

						if (cm3.obtenerFechaHasta() != null) {
							ad.setVigenciaHasta(cm3.obtenerFechaHasta());
						}
					}
				}
			}
			grillaDTO.getSuscripcionesInactivas().add(ad);
		}

		for (ControlMaps cm : listaControles) {
			if (cm instanceof LegalControl) {
				grillaDTO.getItems().add(cm);
			}
		}

		if (grillaDTO.getSuscripcionesActivas().isEmpty() && grillaDTO.getSuscripcionesInactivas().isEmpty()) {
			return respuestaFactory.crearRespuestaWarning(grillaDTO, "", TipoError.WARNING_SIN_SUSCRIPCIONES,
					CodigoMensajeConstantes.CONSULTA_ADHESIONES_SIN_SUSCRIPCIONES);
		}

		return respuestaFactory.crearRespuestaOk(GrillaConsultaAdhesionDTO.class, grillaDTO);
	}

	ConsultaAdhesionRequestEntity crearRequest(Cliente cliente, String banca) {
		ConsultaAdhesionRequestEntity request = new ConsultaAdhesionRequestEntity();
		ConsultaAdhesionMapsDatosRequest datos = new ConsultaAdhesionMapsDatosRequest();
		if (BANCA_RETAIL.equalsIgnoreCase(banca)) {
			request.setCanal(CANAL_BANCA_RETAIL);
			request.setSubCanal(SUBCANAN_BANCA_RETAIL);
			datos.setSegmento(SEGMENTO_RETAIL);
		} else {
			request.setCanal(CANAL_BANCA_PRIVADA);
			request.setSubCanal(SUBCANAL_BANCA_PRIVADA);
			datos.setSegmento(SEGMENTO_BANCA_PRIVADA);
		}
		datos.setId(null);
		datos.setIdServicio(null);
		datos.setIdAdhesion(null);
		datos.setItems(null);
		datos.setEstado("consulta");
		datos.setNup(cliente.getNup());
		request.setDatos(datos);
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.bo.
	 * ServiciosDeInversionBO#descargaComprobanteAltaAdhesion(ar.com.
	 * santanderrio.obp.servicios.inversiones.maps.entity.controles.
	 * FormularioControl)
	 */
	@Override
	public Respuesta<Reporte> descargaComprobanteAltaAdhesion(FormularioControl formularioControl) {
		Reporte reporte = null;
		try {
			reporte = reportesMapsDAO.descargaComprobanteAltaAdhesion(formularioControl);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(Reporte.class, StringUtils.EMPTY, StringUtils.EMPTY);
		}
		return respuestaFactory.crearRespuestaOk(reporte);
	}

	@Override
	public Respuesta<Reporte> descargaComprobanteBajaAdhesion(FormularioControl formularioControl) {
		Reporte reporte = null;
		try {
			reporte = reportesMapsDAO.descargaComprobanteBajaAdhesion(formularioControl);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(Reporte.class, StringUtils.EMPTY, StringUtils.EMPTY);
		}
		return respuestaFactory.crearRespuestaOk(reporte);
	}

	@Override
	public Respuesta<FormularioControl> obtenerDetalleSuscripcion(Cliente cliente,
			DetalleSuscripcionView detalleSuscripcionView) {
		ConsultaAdhesionMapsResponse response;
		try {
			response = obtenerRespuestaConsultaAdhesionValidada(cliente, detalleSuscripcionView.getBanca());
		} catch (ControlMapValidationException e) {
			LOGGER.error(ERROR_DEFINICION_OBLIGATORIA, e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e) {
			LOGGER.error("Error al obtener las suscripciones del servicio.", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return armarRespuestaDetalleSuscripcion((ConsultaAdhesionControl) response.getDatos().getItems().get(0),
				detalleSuscripcionView.getIdServicio());
	}

	private Respuesta<FormularioControl> armarRespuestaDetalleSuscripcion(
			ConsultaAdhesionControl consultaAdhesionControl, long id) {
		Respuesta<FormularioControl> respuesta = new Respuesta<FormularioControl>();
		for (FormularioControl fc : consultaAdhesionControl.getActivas()) {
			if (fc.getIdAdhesion() == id) {
				return respuestaFactory.crearRespuestaOk(FormularioControl.class, fc);
			}
		}

		for (FormularioControl fc : consultaAdhesionControl.getInactivas()) {
			if (fc.getIdAdhesion() == id) {
				return respuestaFactory.crearRespuestaOk(FormularioControl.class, fc);
			}
		}
		return respuesta;
	}

	/**
	 * Devuelve una respuesta del tipo ConsultaAdhesionMapsResponse con las
	 * validaciones de estructura correspondientes tanto para la grilla como para el
	 * detalle de la misma y cachea la respuesta del servicio.
	 * 
	 * @param cliente
	 * @param banca
	 * @return
	 * @throws ControlMapValidationException
	 * @throws DAOException
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_SUSCRIPCIONES_MAPS })
	private ConsultaAdhesionMapsResponse obtenerRespuestaConsultaAdhesionValidada(Cliente cliente, String banca)
			throws ControlMapValidationException, DAOException {
		ConsultaAdhesionMapsResponse respuesta;
		try {
			respuesta = mapServiceDAO.consultaAdhesionMaps(crearRequest(cliente, banca));
			respuesta.getDatos().validateConsulta();

			for (ControlMaps cm : respuesta.getDatos().getItems()) {
				cm.validate();
			}

			List<String> padreIds = new ArrayList<String>();
			ConsultaAdhesionControl ca = (ConsultaAdhesionControl) respuesta.getDatos().getItems().get(0);

			for (FormularioControl fc : ca.getActivas()) {
				borrarControlesDesconocidos(fc);
				fc.validate();

				for (ControlMaps control : fc.getItems()) {
					control.validate();
					if (!control.getBloqueado() && !StringUtils.isEmpty(control.padreId())) {
						padreIds.add(control.padreId().trim());
					}

				}

				for (ControlMaps control : fc.getItems()) {
					padreIds.remove(control.getId().trim());
				}

				if (!CollectionUtils.isEmpty(padreIds)) {
					LOGGER.error(SIN_PADRE_LISTA);
					throw new ControlMapValidationException("Padre de Lista Inexistente");
				}
			}

			for (FormularioControl fc : ca.getInactivas()) {
				borrarControlesDesconocidos(fc);
				fc.validate();

				for (ControlMaps control : fc.getItems()) {
					control.validate();
					if (!control.getBloqueado() && !StringUtils.isEmpty(control.padreId())) {
						padreIds.add(control.padreId().trim());
					}

				}

				for (ControlMaps control : fc.getItems()) {
					padreIds.remove(control.getId().trim());
				}
				if (!CollectionUtils.isEmpty(padreIds)) {
					LOGGER.error(SIN_PADRE_LISTA);
					throw new ControlMapValidationException("Padre de Lista Inexistente");
				}
			}

		} catch (DAOException e) {
			LOGGER.error("Error al consultar las adhesiones.", e.getMessage(), e);
			throw new DAOException("Error al consultar las adhesiones.");
		} catch (ControlMapValidationException e) {
			LOGGER.error(ERROR_DEFINICION_OBLIGATORIA, e);
			throw new ControlMapValidationException(ERROR_DEFINICION_OBLIGATORIA);
		}
		return respuesta;
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_SUSCRIPCIONES_MAPS, allEntries = true)
	@Override
	public void vaciarCacheSuscripcionesMaps() {
		LOGGER.info("Se limpia la cache de suscripciones MAPS.");
	}

	@Override
	public Respuesta<FormularioControl> bajaAdhesion(Cliente cliente, BajaAdhesionView bajaAdhesionView) {
		ConsultaAdhesionMapsResponse response;
		try {
			response = mapServiceDAO.bajaAdhesionMaps(crearRequestBajaAdhesion(cliente, bajaAdhesionView.getBanca(),
					ESTADO_SIMULACION, bajaAdhesionView.getIdAdhesion(), null));
		} catch (DAOException e) {
			LOGGER.error("Error al obtener las suscripciones del servicio.", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return armarRespuestaBajaAdhesion(response);
	}

	private Respuesta<FormularioControl> armarRespuestaBajaAdhesion(ConsultaAdhesionMapsResponse response) {
		if (response.getCodigo() != 0) {
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_BAJA_ADHESION_MAPS,
					CodigoMensajeConstantes.ERROR_BAJA_ADHESION_MAPS);
		}
		try {
			response.getDatos().validate();
			for (ControlMaps cm : response.getDatos().getItems()) {
				cm.validate();
			}
		} catch (ControlMapValidationException e) {
			LOGGER.info(ERROR_DEFINICION_OBLIGATORIA, e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaOk(FormularioControl.class, response.getDatos());
	}

	BajaAdhesionRequestEntity crearRequestBajaAdhesion(Cliente cliente, String banca, String estado, long idAdhesion,
			String idSimulacion) {
		BajaAdhesionRequestEntity request = new BajaAdhesionRequestEntity();
		BajaAdhesionMapsDatosRequest datos = new BajaAdhesionMapsDatosRequest();
		if (BANCA_RETAIL.equalsIgnoreCase(banca)) {
			request.setCanal(CANAL_BANCA_RETAIL);
			request.setSubCanal(SUBCANAN_BANCA_RETAIL);
			datos.setSegmento(SEGMENTO_RETAIL);
		} else {
			request.setCanal(CANAL_BANCA_PRIVADA);
			request.setSubCanal(SUBCANAL_BANCA_PRIVADA);
			datos.setSegmento(SEGMENTO_BANCA_PRIVADA);
		}
		datos.setId(null);
		datos.setIdServicio(null);
		datos.setIdAdhesion(idAdhesion);
		datos.setItems(null);
		datos.setEstado(estado);
		datos.setNup(cliente.getNup());
		datos.setIdSimulacion(idSimulacion);
		request.setDatos(datos);
		return request;
	}

	@Override
	public Respuesta<FormularioControl> bajaAdhesion(FormularioControl formularioControl, Cliente cliente) {
		ConsultaAdhesionMapsResponse response;
		try {
			response = mapServiceDAO.bajaAdhesionMaps(
					crearRequestBajaAdhesion(cliente, formularioControl.getSegmento(), formularioControl.getEstado(),
							formularioControl.getIdAdhesion(), formularioControl.getIdSimulacion()));
		} catch (DAOException e) {
			LOGGER.error("Error al obtener las suscripciones del servicio.", e.getMessage(), e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return armarRespuestaBajaAdhesion(response);
	}
}
