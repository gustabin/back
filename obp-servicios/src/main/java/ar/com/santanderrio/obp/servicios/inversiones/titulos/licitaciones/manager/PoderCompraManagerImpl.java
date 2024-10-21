package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ReporteComprobantePDFBO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentosEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.PoderCompraBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteActivarPoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompraView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticaComprobantePoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarAdhesionPDCResponse;

/**
 * The Class PoderCompraManagerImpl.
 */
@Component("poderCompraManager")
public class PoderCompraManagerImpl extends BaseManager implements PoderCompraManager {

	/** The poder compra BO. */
	@Autowired
	private PoderCompraBO poderCompraBO;

	/** The sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The reporte BO. */
	@Autowired
	private ReporteComprobantePDFBO reporteBO;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PoderCompraManagerImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<AdhesionPDCOutDTO> simularAdhesionPDC(AdhesionPDCRequestDTO request) {
		
		Respuesta<AdhesionPDCOutDTO> respuesta = simularAdhesionPoderCompra(request, TipoBancaEnum.BANCA_PERSONAL, 
				EstadisticasConstants.SIMULACION_ADHESION_PDC);
		if(EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuesta;
		}
		recuperarAyudaDeLegales(respuesta);
		estadisticaManager.add(EstadisticasConstants.SIMULACION_ADHESION_PDC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuesta;
	}

	private Respuesta<AdhesionPDCOutDTO> simularAdhesionPoderCompra(AdhesionPDCRequestDTO request, TipoBancaEnum banca,
			String estadistica) {
		Respuesta<AdhesionPDCOutDTO> respuesta = poderCompraBO.simularAdhesion(request, sesionCliente.getCliente(),
				banca);
		if (EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			LOGGER.error("Error al consultar el servicio de adhesion PDC");
			estadisticaManager.add(estadistica,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		ContadorIntentos contador = new ContadorIntentos();
		contador.setIdView(ContadorIntentosEnum.ADHESION_PODER_COMPRA, 2, "");
		contador.setEstado(false);
		sessionParametros.setContador(contador);
		sessionParametros.setIdSimulacionPDC(respuesta.getRespuesta().getIdSimCuentaPdc());
		return respuesta;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<AdhesionPDCOutDTO> simularAdhesionPDCBPriv(AdhesionPDCRequestDTO request) {
		
		Respuesta<AdhesionPDCOutDTO> respuesta = simularAdhesionPoderCompra(request, TipoBancaEnum.BANCA_PRIVADA, 
				EstadisticasConstants.SIMULACION_ADHESION_PDC);
		if(EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuesta;
		}
		recuperarAyudaDeLegales(respuesta);
		estadisticaManager.add(EstadisticasConstants.SIMULACION_ADHESION_PDC,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuesta;
	}

	private void recuperarAyudaDeLegales(Respuesta<AdhesionPDCOutDTO> respuesta) {
		try {
            respuesta.getRespuesta().setMensajeAyuda(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_AYUDA_ADHESION_PDC));
        } catch (DAOException e) {
            LOGGER.error("Error al obtener el legal de ayuda");
        }
	}
	
	
	private Respuesta<FinalizarAdhesionPDCResponse> controlConfirmacionYReintentosPDC(FinalizarAdhesionPDC request) {
		
		if (sessionParametros.getContador() == null) {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		
		if (sessionParametros.getIdSimulacionPDC() == null) {
			LOGGER.error("Error, No existe el id de la simulacion");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		if (!request.isTilde()) {
			estadisticaManager.add(EstadisticasConstants.CHECK_ADHESION_PDC,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		request.setIdSimCuentaPdc(sessionParametros.getIdSimulacionPDC());
		
		return respuestaFactory.crearRespuestaOk(FinalizarAdhesionPDCResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDC(FinalizarAdhesionPDC request) {

		Respuesta<FinalizarAdhesionPDCResponse> respuesta = controlConfirmacionYReintentosPDC(request);
		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return Respuesta.copy(FinalizarAdhesionPDCResponse.class, respuesta);
		}
		Boolean permiteReintentar = sessionParametros.getContador()
				.gestionarContador(ContadorIntentosEnum.ADHESION_PODER_COMPRA).permiteReintentar();
		Respuesta<FinalizarAdhesionDTO> respuestaBO = poderCompraBO.finalizarAdhesion(request,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PERSONAL);
		return manejarRespuestaConfirmacion(permiteReintentar, respuestaBO,
				EstadisticasConstants.FINALIZAR_ADHESION_PDC);
	}

	private Respuesta<FinalizarAdhesionPDCResponse> manejarRespuestaConfirmacion(Boolean permiteReintentar,
			Respuesta<FinalizarAdhesionDTO> respuestaBO, String estadistica) {

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			FinalizarAdhesionPDCResponse response = crearResponseFinalizarAdhesion(respuestaBO.getRespuesta());
			response.setMensaje(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_OK).getMensaje());
			sessionParametros.setIdSimulacionPDC(null);
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			sessionParametros.setLegalAdhesionPDC(response.getLegal());
			response.setFechaComprobante(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
			return respuestaFactory.crearRespuestaOk(FinalizarAdhesionPDCResponse.class, response);
		} else {
			estadisticaManager.add(estadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (permiteReintentar) {
				LOGGER.error("Error al consultar servicio de adhesion PDC, permite reintentos");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_CON_REINTENTOS,
						CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR);
			} else {
				LOGGER.error("Error al consultar servicio de adhesion PDC");
				sessionParametros.setIdSimulacionPDC(null);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
						CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDCBPriv(FinalizarAdhesionPDC request) {

		boolean permiteReintentar;
		ContadorIntentos contador = sessionParametros.getContador();
		if (sessionParametros.getContador() != null) {
			permiteReintentar = contador.gestionarContador(ContadorIntentosEnum.ADHESION_PODER_COMPRA)
					.permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (!request.isTilde()) {
			estadisticaManager.add(EstadisticasConstants.CHECK_ADHESION_PDC,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}

		if (sessionParametros.getIdSimulacionPDC() != null) {
			request.setIdSimCuentaPdc(sessionParametros.getIdSimulacionPDC());
		} else {
			LOGGER.error("Error, no existe el id de la simulacion");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		Respuesta<FinalizarAdhesionDTO> respuestaBO = poderCompraBO.finalizarAdhesion(request,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PRIVADA);

		if (EstadoRespuesta.OK.equals(respuestaBO.getEstadoRespuesta())) {
			FinalizarAdhesionPDCResponse response =  crearResponseFinalizarAdhesion(respuestaBO.getRespuesta());
			sessionParametros.setLegalAdhesionPDC(response.getLegal());
			response.setMensaje(
					mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_OK).getMensaje());
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_ADHESION_PDC,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(FinalizarAdhesionPDCResponse.class, response);
		} else {
			estadisticaManager.add(EstadisticasConstants.FINALIZAR_ADHESION_PDC,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (permiteReintentar) {
				LOGGER.error("Error al consultar servicio de adhesion PDC, permite reintentos");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_CON_REINTENTOS,
						CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR);
			} else {
				LOGGER.error("Error al consultar servicio de adhesion PDC");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_REINTENTOS_AGOTADOS,
						CodigoMensajeConstantes.FINALIZAR_ADHESION_PDC_ERROR);
			}
		}
	}

	/**
	 * Crear respuesta del finalizar adhesion pdc.
	 *
	 * @param respuestaBO
	 *            the respuesta BO
	 * @param respuestaManager
	 *            the respuesta manager
	 * @return the finalizar adhesion PDC response
	 */
	private FinalizarAdhesionPDCResponse crearResponseFinalizarAdhesion(FinalizarAdhesionDTO respuestaBO) {
		
		FinalizarAdhesionPDCResponse respuestaManager = new FinalizarAdhesionPDCResponse();
		respuestaManager.setMoneda(respuestaBO.getMapFormCampos().get("CodigoMoneda"));
		respuestaManager.setFechaVigencia(respuestaBO.getMapFormCampos().get("FechaVigenciaDesde"));
		respuestaManager.setComprobante(respuestaBO.getMapFormCampos().get("Comprobante"));
		respuestaManager.setFechaSolicitud(respuestaBO.getMapFormCampos().get("FechaSolicitud"));
		respuestaManager.setLegal(respuestaBO.getLegal());
		return respuestaManager;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ComprobanteOrdenCompraView> verComprobanteActivarPoderComprar(
			EstadisticaComprobantePoderCompra request) {

		ComprobanteOrdenCompraView comprobanteResponse = new ComprobanteOrdenCompraView();
		comprobanteResponse.setMensajeError(
				mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS).getMensaje());
		comprobanteResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		if (request.isLegal()) {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ACTIVAR_PODER_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			estadisticaManager.add(EstadisticasConstants.VER_COMPROBANTE_ACTIVAR_PODER_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaFactory.crearRespuestaOk(ComprobanteOrdenCompraView.class, comprobanteResponse);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ReporteView> descargarAceptarPoderCompra(ComprobanteActivarPoderCompra viewRequest,
			TipoBancaEnum tipoBancaEnum) {
		Respuesta<Reporte> reporte;
		Respuesta<ReporteView> respuestaView;
		viewRequest.setLegales(sessionParametros.getLegalAdhesionPDC());
		reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ACTIVAR_PODER_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.DESCARGA_PDF_ACTIVAR_PODER_COMPRA,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}

	
	/**
	 * Adhesion poder de compra desde venta titulos valores
	 * 
	 */
	@Override
	public Respuesta<AdhesionPDCOutDTO> configurarAdhesionPoderCompra(AdhesionPDCRequestDTO request) {
		
		String numeroCuenta = request.getNroCuentaOperativa();
		if(numeroCuenta!= null) {
			numeroCuenta = numeroCuenta.split("-")[1];
			numeroCuenta = numeroCuenta.replace("/","");
			request.setNroCuentaOperativa(numeroCuenta);
		}
		request.setMoneda((request.getMoneda().equals("Pesos"))?"ARS":"USD");
		
		String cuentaTitulo = StringUtils.remove(request.getNroCuentaTitulos(), "Cuenta títulos ");
		request.setNroCuentaTitulos(cuentaTitulo.replace("/", ""));
		
		Respuesta<AdhesionPDCOutDTO> respuesta = simularAdhesionPoderCompra(request, TipoBancaEnum.BANCA_PERSONAL, 
				EstadisticasConstants.ORDEN_VENTA_ACTIVAR_PODER_COMPRA_INICIO);
		if(EstadoRespuesta.ERROR.equals(respuesta.getEstadoRespuesta())) {
			return respuesta;
		}
		recuperarAyudaDeLegales(respuesta);
		estadisticaManager.add(EstadisticasConstants.ORDEN_VENTA_ACTIVAR_PODER_COMPRA_INICIO,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuesta;
	}
	
	private void recuperarAyuda(Respuesta<AdhesionPDCOutDTO> respuesta, String codigoMensaje) {
            try {
				respuesta.getRespuesta().setMensajeAyuda(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_AYUDA_ADHESION_PDC));
			} catch (DAOException e) {
				e.printStackTrace();
			}
//        		mensajeBO.obtenerMensajePorCodigo(codigoMensaje).getMensaje());
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<FinalizarAdhesionPDCResponse> confirmarAdhesionPDC(FinalizarAdhesionPDC request) {

		Respuesta<FinalizarAdhesionPDCResponse> respuesta = controlConfirmacionYReintentosPDC(request);
		if (!EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return Respuesta.copy(FinalizarAdhesionPDCResponse.class, respuesta);
		}
		Boolean permiteReintentar = sessionParametros.getContador()
				.gestionarContador(ContadorIntentosEnum.ADHESION_PODER_COMPRA, Boolean.FALSE ).permiteReintentar();
		Respuesta<FinalizarAdhesionDTO> respuestaBO = poderCompraBO.finalizarAdhesion(request,
				sesionCliente.getCliente(), TipoBancaEnum.BANCA_PERSONAL);
		return manejarRespuestaConfirmacion(permiteReintentar, respuestaBO,
				EstadisticasConstants.CONFIRMAR_ADHESION_PDC);
	}

	@Override
	public Respuesta<Void> verComprobanteActivarPoderComprarVTV() {
		estadisticaManager.add(EstadisticasConstants.ORDEN_VENTA_ACTIVAR_PODER_COMPRA_ACCESO_COMPROBANTE,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(null);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteActivarPoderComprarVTV(ComprobanteActivarPoderCompra viewRequest) {
		
		Respuesta<ReporteView> respuestaView;
		viewRequest.setLegales(sessionParametros.getLegalAdhesionPDC());
		Respuesta<Reporte> reporte = reporteBO.obtenerComprobantePDF(viewRequest);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			respuestaView = respuestaFactory.crearRespuestaOk(reporteView);
			estadisticaManager.add(EstadisticasConstants.ORDEN_VENTA_ACTIVAR_PODER_COMPRA_DESCARGA_COMPROBANTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		} else {
			respuestaView = respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
			estadisticaManager.add(EstadisticasConstants.ORDEN_VENTA_ACTIVAR_PODER_COMPRA_DESCARGA_COMPROBANTE,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return respuestaView;
	}
}