package ar.com.santanderrio.obp.servicios.debinrecurrente.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.RecurrenceActionEnum;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dao.DebinRecurrenteApiDAO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dao.DebinRecurrenteDAO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.RecurrenceDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.entity.ConsultaDebinOutEntity;
import ar.com.santanderrio.obp.servicios.debinrecurrente.entity.CreditoPorContracargoOutEntity;
import ar.com.santanderrio.obp.servicios.debinrecurrente.manager.ObtenerRecurrenciasView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.AccionRecurrenciaResponseView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.CrearRecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.DatosComprobanteDebinRecurrente;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.RecurrenciaView;
import ar.com.santanderrio.obp.servicios.debinws.bo.DebinWSBO;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.SolicitarContracargoDebinOutDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaMigradaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The Class DebinRecurrenteBOImpl.
 */
@Component
public class DebinRecurrenteBOImpl implements DebinRecurrenteBO {
	private static final Logger LOGGER = LoggerFactory.getLogger(DebinRecurrenteBOImpl.class);

	public static final String SUCCESS_MESSAGE_INACTIVATE_TEMP = "¡Listo! Pausaste tu adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";
	public static final String SUCCESS_MESSAGE_DEACTIVATE = "¡Listo! Rechazaste la solicitud de adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";
	public static final String SUCCESS_MESSAGE_ACTIVATE = "¡Listo! Adheriste a DEBIN Recurrente a [EMPRESA] / [SERVICIO].";
	public static final String SUCCESS_MESSAGE_REACTIVATE = "¡Listo! Reactivaste tu adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";
	public static final String SUCCESS_MESSAGE_INACTIVATE_DEF = "¡Listo! Diste de baja tu adhesión a DEBIN Recurrente de [EMPRESA] / [SERVICIO].";

	/** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    
    /** The debin recurrente DAO. */
    @Autowired
    private DebinRecurrenteDAO debinRecurrenteDAO;
    
    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;
	
    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    @Autowired
    @Qualifier("debinWSBOImpl")
    private DebinWSBO debinWsBo;
    
    @Autowired
    private SesionParametros sesionParametros;
    
    /** The detalle cuenta DAO. */
    @Autowired
    private DetalleCuentaDAO detalleCuentaDAO;
    
    @Autowired
    @Qualifier("debinWSDAOImpl")
    private DebinWSDAO debinWsDao;

    /** The debin recurrent api dao. */
	@Autowired
	@Qualifier("debinRecurrenteApiDAOImpl")
	private DebinRecurrenteApiDAO debinRecurrenteApiDAO;

    private static final String COD_RETORNO_EXTENDIDO_OK = "00000000";

	/**
	 * Crear recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	public Respuesta<RecurrenceDTO> crearRecurrencia(CrearRecurrenciaView recurrencia){
		try {
			RecurrenceDTO respuestaServicio =  debinRecurrenteDAO.crearRecurrencia(recurrencia);
			return respuestaServicio != null ?respuestaFactory.crearRespuestaOk(respuestaServicio):
					respuestaFactory.<RecurrenceDTO>crearRespuestaError(null, TipoError.ERROR_DEBIN_YA_EXISTE,
							CodigoMensajeConstantes.ERROR_DEBIN_YA_EXISTE);

		} catch (Exception e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
		}
	}

	/**
	 * Pausar recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> pausarRecurrencia(RecurrenciaView recurrencia) {

		return invokeApplyAction(recurrencia,
				                SUCCESS_MESSAGE_INACTIVATE_TEMP,
				                 RecurrenceActionEnum.INACTIVATE_TEMP,

				                 EstadisticasConstants.PAUSAR_RECURRENCIA);

	}

	/**
	 * Desubscribir recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> reactivarRecurrencia(RecurrenciaView recurrencia) {
		return recurrencia.getPending() ?
				invokeApplyAction(recurrencia,
						SUCCESS_MESSAGE_ACTIVATE,
						RecurrenceActionEnum.CONFIRM,
						EstadisticasConstants.ACTIVAR_RECURRENCIA) :
				invokeApplyAction(recurrencia,
						DebinRecurrenteBOImpl.SUCCESS_MESSAGE_REACTIVATE,
						RecurrenceActionEnum.REACTIVATE,
						EstadisticasConstants.REACTIVAR_RECURRENCIA);
	}

	/**
	 * Desubscribir recurrencia.
	 *
	 * @param recurrencia the recurrencia
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AccionRecurrenciaResponseView> desubscribirRecurrencia(RecurrenciaView recurrencia) {
		return recurrencia.getPending() ?
				invokeApplyAction(recurrencia,
						SUCCESS_MESSAGE_DEACTIVATE,
						RecurrenceActionEnum.REJECT,
						EstadisticasConstants.DESACTIVAR_RECURRENCIA) :
				invokeApplyAction(recurrencia,
						SUCCESS_MESSAGE_INACTIVATE_DEF,
						RecurrenceActionEnum.INACTIVATE_DEF,
						EstadisticasConstants.DESUBSCRIBIR_RECURRENCIA);
	}

	private Respuesta<AccionRecurrenciaResponseView> invokeApplyAction(RecurrenciaView recurrencia, String successfulMessage, RecurrenceActionEnum recurrenceAction, String estadisticasConstants) {

		AccionRecurrenciaResponseView respuestaServicio = null;

		try {
			respuestaServicio =  debinRecurrenteDAO.applyActionToRecurrence(recurrencia, recurrenceAction, successfulMessage);
			if(respuestaServicio != null) {
				estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuestaFactory.crearRespuestaOk(respuestaServicio);
			} else {
				estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			}
		} catch (Exception e) {
			estadisticaManager.add(estadisticasConstants, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, CodigoMensajeConstantes.DEBINREC_ACCION_ERROR, recurrenceAction.toString());
		}
	}

	/**
	 * Pasos para efectuar el desconocimiento del pago:
	 * 	1 - Llamar a CNSDEBIN consulta de debin para obtener los datos (y corroborar que existe)
	 * 	2 - llamar a ws solicitarContracargo para efectuar contracargo.
	 *  3 - Verificar que la cuenta sea valida (CNSCTATIT u otra forma que tengamos para validarlo...) en caso de que sea migrada.
	 * 	4 - Llamar a DEBCREDBN para efectuar el credito por contracargo
	 *
	 */
	@Override
	public Respuesta<Void> desconocerPago(Cliente cliente, String debinId) {
		
		try {
			ConsultaDebinOutEntity consultaDebin = debinRecurrenteDAO.consultaDebin(cliente, debinId);
			if(consultaDebin.getCodigoRetornoExtendido().equals(COD_RETORNO_EXTENDIDO_OK)) {
				Respuesta<SolicitarContracargoDebinOutDTO> responseWs = debinWsBo.solicitarContracargo(cliente, debinId);
				if(responseWs.getEstadoRespuesta().equals(EstadoRespuesta.OK)){
					if(cliente.getIsCuentaMigrada()) {
						try {
							Cliente clienteRespuesta = validarCuenta(cliente);
				            if (clienteRespuesta == null) {
				            	LOGGER.error("Problemas al verificar la cuenta migrada");
				            	return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
				            }
						} catch (CuentaMigradaException e) {
							LOGGER.error("Problemas al verificar la cuenta migrada. Cierre de sucursales", e);
							return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
				        }
					}
					
					String idContracargo = responseWs.getRespuesta().getIdContracargo();
					String canal = consultaDebin.getListaDebines().get(0).getCanal();
					CreditoPorContracargoOutEntity creditoPorContracargo = debinRecurrenteDAO.creditoPorContracargo(cliente, canal, idContracargo );
					if(creditoPorContracargo.getCodigoRetornoExtendido().equals(COD_RETORNO_EXTENDIDO_OK)) {
						return this.respuestaFactory.crearRespuestaOk(Void.class);	
					} else {
						LOGGER.error("Error al invocar a creditoPorContracargo");
			            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
					}
				} else {
					LOGGER.error("Error al invocar a solicitarContracargo");
		            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
				}
			} else {
				LOGGER.error("Error al invocar GNSDEBIN");
				return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
			}
		} catch (DAOException e) {
			LOGGER.error("Error en los servicios que invocan a Mainframe");
			return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
		}
	}

	private Cliente validarCuenta(Cliente cliente) throws DAOException, CuentaMigradaException {
		String numero = sesionParametros.getDetalleDebin().getComprador().getNumero();
		String tipo = sesionParametros.getDetalleDebin().getComprador().getTipo();
		String sucursal = sesionParametros.getDetalleDebin().getComprador().getSucursal();
		Cliente clienteRespuesta = detalleCuentaDAO.obtenerCuenta(cliente, tipo, sucursal, numero);
		return clienteRespuesta;
	}

	/**
	 * Obtener recurrencias por comprador.
	 *
	 * @param cliente the cliente
	 * @param view the view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<BuyerRecurrenceListRequestDTO> obtenerRecurrenciasPorComprador(Cliente cliente, ObtenerRecurrenciasView view) {
		try {
			BuyerRecurrenceListRequestDTO obtenerRecurrenciasPorComprador = debinRecurrenteApiDAO.getRecurrenceList( view.getCbu(), cliente.getNumeroCUILCUIT(), view.getNumeroPagina());
				return obtenerRecurrenciasPorComprador != null ?respuestaFactory.crearRespuestaOk(obtenerRecurrenciasPorComprador):
						respuestaFactory.crearRespuestaWarning(BuyerRecurrenceListRequestDTO.class,"","");

		}catch (DAOException e) {
            return this.respuestaFactory.crearRespuestaError("", TipoError.DEBINWS_ERROR_GENERICO, CodigoMensajeConstantes.DEBINWS_ERROR_GENERICO);
		}
	}
	
	/**
	 * Generar comprobante PDF.
	 *
	 * @param datosComprobante the datos comprobante
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Reporte> generarComprobantePDF(DatosComprobanteDebinRecurrente datosComprobante) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = debinRecurrenteDAO.generarComprobantePDF(datosComprobante);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

}
