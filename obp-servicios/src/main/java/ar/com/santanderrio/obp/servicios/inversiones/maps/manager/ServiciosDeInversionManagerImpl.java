package ar.com.santanderrio.obp.servicios.inversiones.maps.manager;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.PerfilInversorEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.bo.ServiciosDeInversionBO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.GrillaConsultaAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dto.InicioServiciosDeInversionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.maps.entity.controles.FormularioControl;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BajaAdhesionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetallePerfilInversorView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.DetalleSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.FormulariosAltaInicioInView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.InicioServiciosDeInversionView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.ObtenerDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.maps.view.VerDetallePerfilInversorView;

@Component
public class ServiciosDeInversionManagerImpl implements ServiciosDeInversionManager {

	/** The BANCA_RTL. */
	private static final String BANCA_RTL = "RTL";

	/** The BANCA_BP. */
	private static final String BANCA_BP = "BP";

	/** The ESTADO_SIMULACION. */
	private static final String ESTADO_SIMULACION = "simulacion";

	/** The ESTADO_CARGA. */
	private static final String ESTADO_CARGA = "carga";
	
	/** The ESTADO_DISCLAIMER. */
	private static final String ESTADO_DISCLAIMER = "disclaimer";
			
	/** The ESTADO_CONFIRMACION. */
	private static final String ESTADO_CONFIRMACION = "confirmacion";
	
	private static final String CTA_TITULO_SERVICE="ACT";
	
	private static final String VMEP_SERVICE="VMEP";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosDeInversionManagerImpl.class);

	@Autowired
	private SesionCliente sesionCliente;

	/** The serviciosDeInversion BO. */
	@Autowired
	private ServiciosDeInversionBO serviciosDeInversionBO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/**
	 * The inversiones BO
	 */
	@Autowired
	private InversionesBO inversionesBO;

	/** The Constant DATE_FORMAT. */
	private static final String DATE_FORMAT = "dd/MM/yyyy - HH:mm";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.manager.
	 * ServiciosDeInversionManager#inicioServiciosDeInversion()
	 */
	@Override
	public Respuesta<InicioServiciosDeInversionView> inicioServiciosDeInversion() {

		Respuesta<InicioServiciosDeInversionDTO> respuestaBO = serviciosDeInversionBO
				.inicioServiciosDeInversion(sesionCliente.getCliente());
		InicioServiciosDeInversionView outView = createRetornoView(respuestaBO.getRespuesta());
		return respuestaFactory.crearRespuestaOk(InicioServiciosDeInversionView.class, outView);
	}

	private InicioServiciosDeInversionView createRetornoView(InicioServiciosDeInversionDTO dto) {
		InicioServiciosDeInversionView outView = new InicioServiciosDeInversionView();
		if (dto != null) {
			try {
				BeanUtils.copyProperties(outView, dto);
			} catch (Exception e) {
				LOGGER.error("Error creando View", e);
			}
		}
		return outView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.manager.
	 * ServiciosDeInversionManager#obtenerControlesDisponibles(ar.com.
	 * santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView)
	 */
	@Override
	public Respuesta<ObtenerDisponiblesOutView> obtenerControlesDisponibles(BancaInversionesView inView) {

		Respuesta<FormularioControl> respuestaBO = serviciosDeInversionBO
				.obtenerControlesDisponibles(sesionCliente.getCliente(), inView.getBanca().toUpperCase());

		String estadisticaSegunBanca = EstadisticasConstants.CONTROLES_DISPONIBLES_HOME_MAPS_BPRIV;
		if (BANCA_RTL.equalsIgnoreCase(inView.getBanca())) {
			estadisticaSegunBanca = EstadisticasConstants.CONTROLES_DISPONIBLES_HOME_MAPS_BPERS;
		}

		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ObtenerDisponiblesOutView outView = new ObtenerDisponiblesOutView(respuestaBO.getRespuesta());

		if (EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {
			String tipoError = respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError();

			if (TipoError.ALGUN_SERVICIO_ERRONEO.getDescripcion().equals(tipoError)) {
				estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);

				if (outView.tieneServicios()) {
					return respuestaFactory.crearRespuestaOk(ObtenerDisponiblesOutView.class, outView);
				} else {
					return respuestaFactory.crearRespuestaWarning(ObtenerDisponiblesOutView.class, outView,
							TipoError.ERROR_SIN_SERVICIOS, CodigoMensajeConstantes.ERROR_SERVICIOS_INVERSION_BLOQUEADOS,
							"");
				}
			}
		}

		estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		if (!outView.tieneServicios()) {
			return respuestaFactory.crearRespuestaWarning(ObtenerDisponiblesOutView.class, outView,
					TipoError.ERROR_SIN_SERVICIOS, CodigoMensajeConstantes.ERROR_SERVICIOS_INVERSION_BLOQUEADOS, "");

		}
		return respuestaFactory.crearRespuestaOk(ObtenerDisponiblesOutView.class, outView);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.manager.
	 * ServiciosDeInversionManager#altaServicio(ar.com.santanderrio.obp.
	 * servicios.inversiones.maps.view.FormulariosAltaInicioInView)
	 */
	@Override
	public Respuesta<FormularioControl> altaServicio(FormulariosAltaInicioInView inView) {
	    
		Respuesta<FormularioControl> respuestaBO = serviciosDeInversionBO.altaServicio(sesionCliente.getCliente().getNup(), inView);
		
		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
		    estadisticaManager.add(EstadisticasConstants.ALTA_SERVICIOS_MAPS_ERROR, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		    return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, 
		            TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);          
		}	
		
		String estadisticaSegunBanca = EstadisticasConstants.ALTA_SERVICIOS_MAPS_BP;
		if (ESTADO_CARGA.equals(respuestaBO.getRespuesta().getEstado()) && EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			if (BANCA_RTL.equalsIgnoreCase(inView.getBanca())) {
				estadisticaSegunBanca = EstadisticasConstants.ALTA_SERVICIOS_MAPS_RTL;
			}
		}
				
		if (ESTADO_DISCLAIMER.equals(respuestaBO.getRespuesta().getEstado()) && EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaSegunBanca = EstadisticasConstants.DISCLAIMER_SERVICIOS_MAPS_BP;
			if (BANCA_RTL.equalsIgnoreCase(inView.getBanca())) {
				estadisticaSegunBanca = EstadisticasConstants.DISCLAIMER_SERVICIOS_MAPS_RTL;
			}			
		}
		
		estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		
		if(EstadoRespuesta.WARNING.equals(respuestaBO.getEstadoRespuesta())) {
			respuestaBO = altaServicioFlujo(respuestaBO.getRespuesta());
		}
		
		return respuestaBO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.manager.
	 * ServiciosDeInversionManager#altaServicioFlujo(ar.com.santanderrio.obp.
	 * servicios.inversiones.maps.entity.controles.FormularioControl)
	 */
	@Override
	public Respuesta<FormularioControl> altaServicioFlujo(FormularioControl inView) {

		Respuesta<FormularioControl> respuestaBO = serviciosDeInversionBO
				.altaServicioFlujo(sesionCliente.getCliente().getNup(), inView);

		if (EstadoRespuesta.ERROR.equals(respuestaBO.getEstadoRespuesta())) {
			String estadistica = EstadisticasConstants.ALTA_SERVICIOS_MAPS_ERROR;
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaBO;
		}
		grabarEstadisticaOK(inView.getSegmento(), respuestaBO.getRespuesta().getEstado());

		if (ESTADO_CONFIRMACION.equals(respuestaBO.getRespuesta().getEstado())) {
			respuestaBO.getRespuesta().setFechaComprobante(obtenerFechaHora(new Date()));
			if(respuestaBO.getRespuesta().getIdServicio().contentEquals(CTA_TITULO_SERVICE)) {
				return respuestaFactory.crearRespuestaOk(respuestaBO.getRespuesta(), StringUtils.EMPTY, CodigoMensajeConstantes.SERVICIOS_CTA_TIT_FEEDBACK_OK);
			}else {
				if(respuestaBO.getRespuesta().getIdServicio().contentEquals(VMEP_SERVICE)) {
					return respuestaFactory.crearRespuestaOk(respuestaBO.getRespuesta(), StringUtils.EMPTY, CodigoMensajeConstantes.SERVICIOS_VMEP_OK);
				}else {
				return respuestaFactory.crearRespuestaOk(respuestaBO.getRespuesta(), StringUtils.EMPTY, CodigoMensajeConstantes.SERVICIOS_INVERSION_FEEDBACK_OK);	
				}
			}
		}
		
		return respuestaBO;
	}

	/**
	 * Obtiene la fecha y la hora.
	 *
	 * @param fecha
	 *            the fecha
	 * @return the string
	 */
	private String obtenerFechaHora(Date fecha) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		return format.format(fecha);
	}

	/**
	 * Graba estadistica OK segun estado devuelto por maps
	 * 
	 * @param segmento
	 * @param estado
	 */
	private void grabarEstadisticaOK(String segmento, String estado) {

		String estadisticaSegunBanca = null;
		if (ESTADO_CARGA.equals(estado)) {
			estadisticaSegunBanca = EstadisticasConstants.ALTA_SERVICIOS_MAPS_BP;
			if (BANCA_RTL.equalsIgnoreCase(segmento)) {
				estadisticaSegunBanca = EstadisticasConstants.ALTA_SERVICIOS_MAPS_RTL;
			}
		}

		if (ESTADO_SIMULACION.equals(estado)) {
			estadisticaSegunBanca = EstadisticasConstants.ALTA_SERVICIOS_FLUJO_MAPS_RTL;
			if (BANCA_BP.equalsIgnoreCase(segmento)) {
				estadisticaSegunBanca = EstadisticasConstants.ALTA_SERVICIOS_FLUJO_MAPS_BP;
			}
		}

		if (ESTADO_CONFIRMACION.equals(estado)) {
			estadisticaSegunBanca = EstadisticasConstants.CONFIRMACION_SERVICIOS_FLUJO_MAPS_RTL;
			if (BANCA_BP.equalsIgnoreCase(segmento)) {
				estadisticaSegunBanca = EstadisticasConstants.CONFIRMACION_SERVICIOS_FLUJO_MAPS_BP;
			}
		}
				
		if (ESTADO_DISCLAIMER.equals(estado)) {
			estadisticaSegunBanca = EstadisticasConstants.DISCLAIMER_SERVICIOS_MAPS_RTL;
			if (BANCA_BP.equalsIgnoreCase(segmento)) {
				estadisticaSegunBanca = EstadisticasConstants.DISCLAIMER_SERVICIOS_MAPS_BP;
			}
		}				
		estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.manager.
	 * ServiciosDeInversionManager#verDetallePerfil(ar.com.santanderrio.obp.
	 * servicios.inversiones.maps.view.VerDetallePerfilInversorView)
	 */
	@Override
	public Respuesta<DetallePerfilInversorView> verDetallePerfil(VerDetallePerfilInversorView inView) {

		PerfilInversorEnum perfilInversor = getPerfil(inView.getPerfilDeInversor());

		if (perfilInversor == null) {
			return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		String descripcionPerfil = inversionesBO.obtenerDescripcionLargaSegunPerfil(perfilInversor.getIdPerfil());
		DetallePerfilInversorView detallePerfilView = new DetallePerfilInversorView();
		detallePerfilView.setDescripcionPerfil(descripcionPerfil);
		detallePerfilView.setIdPerfil(perfilInversor.getIdPerfil());
		return respuestaFactory.crearRespuestaOk(DetallePerfilInversorView.class, detallePerfilView);
	}

	private PerfilInversorEnum getPerfil(String perfil) {

		if (perfil == null) {
			return null;
		}
		if ("Sin Perfil".equals(perfil)) {
			return PerfilInversorEnum.NO_PERFILADO;
		}
		return PerfilInversorEnum.obtenerPerfilPorDescripcion(ISBANStringUtils.convertirStringToCamelcase(perfil));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.manager.
	 * ServiciosDeInversionManager#notificarGotoTenenciaConsolidada(ar.com.
	 * santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView)
	 */
	@Override
	public Respuesta<Void> notificarGotoTenenciaConsolidada(BancaInversionesView inView) {
		String estadisticaAcceso = EstadisticasConstants.ESTADISTICA_MAPS_GOTO_TENENCIA_BP;
		if (BANCA_RTL.equalsIgnoreCase(inView.getBanca())) {
			estadisticaAcceso = EstadisticasConstants.ESTADISTICA_MAPS_GOTO_TENENCIA_RTL;
		}
		estadisticaManager.add(estadisticaAcceso, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	@Override
	public Respuesta<GrillaConsultaAdhesionDTO> consultaAdhesion(BancaInversionesView inView) {
		Respuesta<GrillaConsultaAdhesionDTO> respuesta = serviciosDeInversionBO
				.consultaAdhesion(sesionCliente.getCliente(), inView.getBanca().toUpperCase());

		String estadisticaSegunBanca = EstadisticasConstants.ESTADISTICA_MAPS_CONSULTA_ADHESIONES_RTL;
		if (BANCA_BP.equalsIgnoreCase(inView.getBanca())) {
			estadisticaSegunBanca = EstadisticasConstants.ESTADISTICA_MAPS_CONSULTA_ADHESIONES_BPRIV;
		}

		grabarEstadisticaSegunRespuesta(respuesta.getEstadoRespuesta(), estadisticaSegunBanca);

		return respuesta;
	}

	private void grabarEstadisticaSegunRespuesta(EstadoRespuesta estadoRespuesta, String estadisticaSegunBanca) {
		if (EstadoRespuesta.ERROR.equals(estadoRespuesta)) {
			estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		} else {
			estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.maps.manager.
	 * ServiciosDeInversionManager#accesoComprobanteAltaAdhesion(ar.com.
	 * santanderrio.obp.servicios.inversiones.maps.view.BancaInversionesView)
	 */
	@Override
	public Respuesta<Void> accesoComprobanteAltaAdhesion(BancaInversionesView inView) {
		String estadisticaComprobante = EstadisticasConstants.ESTADISTICA_ACCESO_COMPROBANTE_ALTA_ADHESION_MAPS_BP;
		if (BANCA_RTL.equalsIgnoreCase(inView.getBanca())) {
			estadisticaComprobante = EstadisticasConstants.ESTADISTICA_ACCESO_COMPROBANTE_ALTA_ADHESION_MAPS_RTL;
		}
		estadisticaManager.add(estadisticaComprobante, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	@Override
	public Respuesta<ReporteView> descargaComprobanteAltaAdhesion(FormularioControl formularioControl) {

		String estadisticaSegunBanca = EstadisticasConstants.DESCARGA_COMPROBANTE_MAPS_ALTA_ADHESIONES_RTL;
		String codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		Respuesta<Reporte> reporteRespuesta = serviciosDeInversionBO.descargaComprobanteAltaAdhesion(formularioControl);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		} else {
			codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		}

		if (BANCA_BP.equalsIgnoreCase(formularioControl.getSegmento())) {
			estadisticaSegunBanca = EstadisticasConstants.DESCARGA_COMPROBANTE_MAPS_ALTA_ADHESIONES_BPRIV;
		}
		estadisticaManager.add(estadisticaSegunBanca, codigoEstadistica);
		return respuestaView;
	}

	@Override
	public Respuesta<FormularioControl> obtenerDetalleSuscripcion(DetalleSuscripcionView detalleSuscripcionView) {
		return serviciosDeInversionBO.obtenerDetalleSuscripcion(sesionCliente.getCliente(), detalleSuscripcionView);
	}

	@Override
	public Respuesta<FormularioControl> bajaAdhesion(BajaAdhesionView bajaAdhesionView) {
		Respuesta<FormularioControl> respuestaBO = serviciosDeInversionBO.bajaAdhesion(sesionCliente.getCliente(),
				bajaAdhesionView);

		if (!EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ERROR_BAJA_ADHESION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaBO;
		}

		String estadisticaSegunBanca = EstadisticasConstants.BAJA_ADHESION_SIMULACION_BP;
		if (BANCA_RTL.equalsIgnoreCase(respuestaBO.getRespuesta().getSegmento())) {
			estadisticaSegunBanca = EstadisticasConstants.BAJA_ADHESION_SIMULACION_RTL;
		}
		estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		return respuestaBO;
	}

	@Override
	public Respuesta<FormularioControl> bajaAdhesion(FormularioControl inView) {
		Respuesta<FormularioControl> respuestaBO = serviciosDeInversionBO.bajaAdhesion(inView,
				sesionCliente.getCliente());

		if (!EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			estadisticaManager.add(EstadisticasConstants.ERROR_BAJA_ADHESION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaBO;
		}

		String estadisticaSegunBanca = EstadisticasConstants.BAJA_ADHESION_SIMULACION_BP;
		if (ESTADO_SIMULACION.equals(respuestaBO.getRespuesta().getEstado())) {
			if (BANCA_RTL.equalsIgnoreCase(respuestaBO.getRespuesta().getSegmento())) {
				estadisticaSegunBanca = EstadisticasConstants.BAJA_ADHESION_SIMULACION_RTL;
			}
			estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		if (ESTADO_CONFIRMACION.equals(respuestaBO.getRespuesta().getEstado())) {
			respuestaBO.getRespuesta().setFechaComprobante(obtenerFechaHora(new Date()));
			if (BANCA_RTL.equalsIgnoreCase(respuestaBO.getRespuesta().getSegmento())) {
				estadisticaSegunBanca = EstadisticasConstants.BAJA_ADHESION_CONFIRMACION_RTL;
			} else {
				estadisticaSegunBanca = EstadisticasConstants.BAJA_ADHESION_CONFIRMACION_BP;
			}
			estadisticaManager.add(estadisticaSegunBanca, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(respuestaBO.getRespuesta(), StringUtils.EMPTY,
					CodigoMensajeConstantes.SERVICIOS_INVERSION_FEEDBACK_BAJA_OK);
		}

		return respuestaBO;
	}

	@Override
	public Respuesta<Void> grabarEstadisticaComprobanteBaja(BancaInversionesView inView) {
		String estadisticaComprobante = EstadisticasConstants.BAJA_ADHESION_VER_COMPROBANTE_BP;
		if (BANCA_RTL.equalsIgnoreCase(inView.getBanca())) {
			estadisticaComprobante = EstadisticasConstants.BAJA_ADHESION_VER_COMPROBANTE_RTL;
		}
		estadisticaManager.add(estadisticaComprobante, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}

	@Override
	public Respuesta<ReporteView> descargaComprobanteBajaAdhesion(FormularioControl formularioControl) {

		String estadisticaSegunBanca = EstadisticasConstants.DESCARGA_COMPROBANTE_MAPS_BAJA_ADHESIONES_RTL;
		String codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
		Respuesta<Reporte> reporteRespuesta = serviciosDeInversionBO.descargaComprobanteBajaAdhesion(formularioControl);
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		} else {
			codigoEstadistica = EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
		}

		if (BANCA_BP.equalsIgnoreCase(formularioControl.getSegmento())) {
			estadisticaSegunBanca = EstadisticasConstants.DESCARGA_COMPROBANTE_MAPS_BAJA_ADHESIONES_BPRIV;
		}
		estadisticaManager.add(estadisticaSegunBanca, codigoEstadistica);
		return respuestaView;
	}

}
