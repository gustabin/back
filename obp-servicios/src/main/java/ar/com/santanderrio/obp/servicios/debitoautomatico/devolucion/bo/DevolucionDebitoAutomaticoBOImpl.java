package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.bo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DatosComprobanteDevolucionDA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.dao.DevolucionDebitoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view.SolicitarDevolucionDAOutView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.SolicitudDevolucionDebitoOutEntity;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class DevolucionDebitoAutomaticoBOImpl.
 */
@Component
public class DevolucionDebitoAutomaticoBOImpl implements DevolucionDebitoAutomaticoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DevolucionDebitoAutomaticoBOImpl.class);
	
	/** The Constant CODIGO_RETORNO_OK. */
	private static final String CODIGO_RETORNO_OK = "00000000";
	
	/** The Constant CODIGO_RETORNO_ERROR_YA_SOLICITADO. */
	private static final String CODIGO_RETORNO_ERROR_YA_SOLICITADO = "10000153";

	private static final String CODIGO_RETORNO_ERROR_EN_CURSO = "10000156";
	
    private static final String FORMATO_NRO_COMPROBANTE = "yyMMddHHmmss";
	
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
	/** The devolucion debito automatico DAO. */
	@Autowired
	private DevolucionDebitoAutomaticoDAO devolucionDebitoAutomaticoDAO;
		
	/** The estadisticas manager. */
	@Autowired
	private EstadisticaManager estadisticasManager;
	
	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;
	
	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;
	
	/**
	 * Solicitud de devolucion. Se efectua luego de las 96 hrs si responde OK el servicio
	 * 
	 * Errors:
	 * Codigo de retorno 00000153 se debe informar un error indicando que la reversión ya fue solicitada. 
	 * Si el código de retorno es 0000047 → Devolver Error: "Sólo podés cancelar una devolución que fue solicitada en el día de hoy." [NO FUE RELEVADO ---> Se muestra como error ordinario]
	 * Para otros códigos de error distintos de cero informar el mensaje de error estándar de "Servicio no disponible".
	 *
	 * @param cliente the cliente
	 * @param cuit the cuit
	 * @param servicio the servicio
	 * @param partida the partida
	 * @param idCliente the id cliente
	 * @param fecha the fecha
	 * @param empresa the empresa
	 * @param importe the importe
	 * @return the respuesta
	 */
	@Override
	public Respuesta<SolicitarDevolucionDAOutView> ejecutarSolicitudDevolucionDA(Cliente cliente, String cuit, String servicio,
			String partida, String idCliente, String fecha, String empresa, String importe) {

		SolicitudDevolucionDebitoOutEntity rtaSolicitudDevolucionDA = devolucionDebitoAutomaticoDAO.ejecutarSolicitudDevolucionDA(cliente, cuit, servicio, partida, idCliente, fecha);
		
		if(rtaSolicitudDevolucionDA != null) {
			if(rtaSolicitudDevolucionDA.getCodigoResultadoExtendido().equals(CODIGO_RETORNO_OK)) {
				SolicitarDevolucionDAOutView body = new SolicitarDevolucionDAOutView();
				Date fechaHoy = new Date();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
				body.setFecha(sdf.format(fechaHoy) + " hs.");
				body.setIdComprobante(obtenerNumeroComprobante(fechaHoy));
				String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.SOLICITUD_DEVOLUCION_DA_FEEDBACK_OK, empresa, importe).getMensaje();
				body.setMensajeFeedbackOk(mensaje);
				DatosComprobanteDevolucionDA datosComprobante = sesionParametros.getDatosComprobanteDevolucionDA();
				datosComprobante.setNroComprobante(body.getIdComprobante());
				sesionParametros.setDatosComprobanteDevolucionDA(datosComprobante);
				estadisticasManager.add(EstadisticasConstants.SOLICITUD_DEVOLUCION_DA_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(body);
			} else if(rtaSolicitudDevolucionDA.getCodigoResultadoExtendido().equals(CODIGO_RETORNO_ERROR_YA_SOLICITADO) || rtaSolicitudDevolucionDA.getCodigoResultadoExtendido().equals(CODIGO_RETORNO_ERROR_EN_CURSO) ) {
				estadisticasManager.add(EstadisticasConstants.SOLICITUD_DEVOLUCION_DA_FEEDBACK, EstadisticasConstants.SOLICITUD_DEVOLUCION_DA_FEEDBACK_ERROR_YA_SOLICITADO);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SOLICITUD_DEVOLUCION_DEBITO_AUTOMATICO_CREADA, CodigoMensajeConstantes.SOLICITUD_DEVOLUCION_DA_FEEDBACK_ERROR_YA_SOLICITADA, importe, empresa, fecha);
			} else {
				estadisticasManager.add(EstadisticasConstants.SOLICITUD_DEVOLUCION_DA_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.SOLICITUD_DEVOLUCION_DA_FEEDBACK_ERROR, importe, empresa, fecha);
			}
		}

		estadisticasManager.add(EstadisticasConstants.SOLICITUD_DEVOLUCION_DA_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.SOLICITUD_DEVOLUCION_DA_FEEDBACK_ERROR, importe, empresa, fecha);
	}
	
	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Reporte> generarComprobantePDF(DatosComprobanteDevolucionDA datosComprobante) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = devolucionDebitoAutomaticoDAO.generarComprobantePDF(datosComprobante);
		LOGGER.info("Comprobante generado en BO");
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	
	/**
     *  
     * obtener nro de comprobante formateado yyMMddHHmmss.
     *
     * @param fechaComprobante the fecha comprobante
     * @return the string
     */
    private String obtenerNumeroComprobante(Date fechaComprobante) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_NRO_COMPROBANTE);
        return sdf.format(fechaComprobante);
    }
}
