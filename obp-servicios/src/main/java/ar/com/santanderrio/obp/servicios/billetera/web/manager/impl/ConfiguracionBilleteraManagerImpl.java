/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.manager.impl;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.bo.ConfiguracionBilleteraBO;
import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraDTO;
import ar.com.santanderrio.obp.servicios.billetera.dto.BilleteraInDTO;
import ar.com.santanderrio.obp.servicios.billetera.web.manager.ConfiguracionBilleteraManager;
import ar.com.santanderrio.obp.servicios.billetera.web.view.BilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.FeedbackBilleteraView;
import ar.com.santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Class ConfiguracionBilleteraManagerImpl.
 */
@Component("configuracionBilleteraManager")
public class ConfiguracionBilleteraManagerImpl extends BilleteraManagerImpl implements ConfiguracionBilleteraManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracionBilleteraManagerImpl.class);

	/** The configuracion billetera BO. */
	@Autowired
	private ConfiguracionBilleteraBO configuracionBilleteraBO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * ConfiguracionBilleteraManager#confirmarConfiguracion(ar.com.santanderrio.
	 * obp.servicios.billetera.web.view.BilleteraView)
	 */
	@Override
	public Respuesta<BilleteraView> confirmarConfiguracion(BilleteraView viewRequest) {
		try {
			if (viewRequest.getDesafio() == null) {
				cargarHash(crearMapaDeLaVista(viewRequest));
			}
			Respuesta<BilleteraView> respuestaRsa = billeteraHelper.ejecutarValidacionRsa(viewRequest,
					BilleteraConstants.MODE_MOD);
			if (!EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {
				return respuestaRsa;
			}
			validarHash(crearMapaDeLaVista(viewRequest));
			return configurarBilletera(viewRequest);
		} catch (Exception e) {
			LOGGER.error("Error al confirmar configuracion de Billetera", e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * ConfiguracionBilleteraManager#descargaComprobanteConfiguracion()
	 */
	@Override
	public Respuesta<ReporteView> descargaComprobanteConfiguracion() {
		Respuesta<Reporte> reporteRespuesta = configuracionBilleteraBO.generarComprobante();
		Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
		if (reporteRespuesta.getRespuesta() != null) {
			ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
			respuestaView.setRespuesta(reporteView);
		}
		estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.billetera.web.manager.
	 * ConfiguracionBilleteraManager#preConfirmarConfiguracion(ar.com.
	 * santanderrio.obp.servicios.billetera.web.view.PreConfirmarBilleteraView)
	 */
	@Override
	public Respuesta<PreConfirmarBilleteraView> preConfirmarConfiguracion(PreConfirmarBilleteraView viewRequest) {
		return preConfirmar(viewRequest, EstadisticasConstants.BILLETERA_MODIFICACION);
	}

	/**
	 * configurarBilletera.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	private Respuesta<BilleteraView> configurarBilletera(BilleteraView viewRequest) {
		BilleteraInDTO inDTO = billeteraHelper.crearBilleteraInDTO(viewRequest);
		Respuesta<BilleteraDTO> respuestaConfiguracionBilletera = configuracionBilleteraBO.configurarBilletera(inDTO);
		if (EstadoRespuesta.OK.equals(respuestaConfiguracionBilletera.getEstadoRespuesta())) {
			BilleteraView configuracionBilleteraView;
			try {
				configuracionBilleteraView = crearRetornoConfiguracionBilletera(
						respuestaConfiguracionBilletera.getRespuesta());
			} catch (ParseException e) {
				LOGGER.error("Error al crear retorno para Configuracion de Billetera.", e);
				estadisticaManager.add(EstadisticasConstants.BILLETERA_MODIFICACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODIFICACION_BILLETERA,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
			}

			// Fija la marca de adhesion en HB_CLIENT_MASTER
			RegistroSesion registroSesion = sesionParametros.getRegistroSession();
			if (respuestaConfiguracionBilletera.getRespuesta().getMarcarAdhesion()
					&& !billeteraBO.marcarAdhesion(sesionCliente.getCliente(), registroSesion)) {
				estadisticaManager.add(EstadisticasConstants.BILLETERA_MODIFICACION,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_MODIFICACION_BILLETERA,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_IMPACTO_BILLETERA);
			}

			estadisticaManager.add(EstadisticasConstants.BILLETERA_MODIFICACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaOk(configuracionBilleteraView);
		} else {
			estadisticaManager.add(EstadisticasConstants.BILLETERA_MODIFICACION,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(null,
					respuestaConfiguracionBilletera.getItemsMensajeRespuesta());
		}
	}

	/**
	 * Crear retorno configuracion billetera.
	 *
	 * @param billeteraDTO
	 *            the billetera DTO
	 * @return the billetera view
	 * @throws ParseException
	 *             the parse exception
	 */
	private BilleteraView crearRetornoConfiguracionBilletera(BilleteraDTO billeteraDTO) throws ParseException {
		BilleteraView retornoView = new BilleteraView();
		retornoView.setFecha(billeteraDTO.getFecha());
		retornoView.setHora(billeteraDTO.getHora());
		retornoView.setNroComprobante(billeteraDTO.getNroComprobante());
		retornoView.setMensaje("");
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(CodigoMensajeConstantes.LEGALES_CONSERVAR_COMPROBANTE);
		if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			retornoView.setLegalesSEO(respuestaLegales.getRespuesta());
		}

		// Valores Feedback
		FeedbackBilleteraView feedbackView = billeteraHelper.fillFeedbackView(billeteraDTO);
		retornoView.setFeedback(feedbackView);

		return retornoView;
	}

}
