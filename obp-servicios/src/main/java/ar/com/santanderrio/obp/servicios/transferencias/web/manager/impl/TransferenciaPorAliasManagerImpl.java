/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.api.transfers.fundsperformances.IFundsMessageHelper;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AlycsDAO;
import ar.com.santanderrio.obp.servicios.transferencias.utils.TransferUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.TransferenciaPorAliasBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.Transferencia2;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaPorAliasManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class TransferenciaManagerImpl.
 *
 * @author emilio.watemberg
 */
@Component
public class TransferenciaPorAliasManagerImpl extends Transferencia2 implements TransferenciaPorAliasManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaPorAliasManagerImpl.class);

	/** MSJ SOLICITANDO NUEVA_TRANSFERENCIA_ALIAS. */
	private static final String MSJ_SOLICITANDO_NUEVA_TRANSFERENCIA_ALIAS = "Solicitando nueva transferencia por alias.";

	/** The Constant CUENTA_SIN_CTAS_CON_MONEDA_INDICADA. */
	private static final String CLIENTE_SIN_CTAS_CON_MONEDA_INDICADA = "Cliente sin cuentas con moneda destino valida.";

	/** The Constant ERROR_AL_CARGAR_LA_VISTA_CON_EL_DTO. */
	private static final String ERROR_AL_CARGAR_LA_VISTA_CON_EL_DTO = "Error al cargar la vista con el DTO.";

	/** Error, alias con formato incorrecto. */
	private static final String ALIAS_CON_FORMATO_INCORRECTO = "Error, alias con formato incorrecto.";

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The transferencia manager. */
	@Autowired
	private TransferenciaManager transferenciaManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The transferencia por alias BO. */
	@Autowired
	private TransferenciaPorAliasBO transferenciaPorAliasBO;

	/** The mensaje. */
	@Autowired
	private MensajeManager mensajeManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	@Autowired
	private AlycsDAO alycsDAO;

	@Autowired
	private IFundsMessageHelper fundsMessageHelper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.web.manager.
	 * TransferenciaPorAliasManager#solicitarNuevaTransferencia(ar.com.
	 * santanderrio.obp.servicios.transferencias.web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> solicitarNuevaTransferencia(TransferenciaView transferenciaView, String agent) {
		LOGGER.info(MSJ_SOLICITANDO_NUEVA_TRANSFERENCIA_ALIAS);
		limpiarSesion(transferenciaView);
		if (!transferenciaView.isFromAgendaDestinatario()) TransferenciaUtil.limpiarDatosTransferenciaDestinoDeSesionParametros(sesionParametros);

		if (!ISBANStringUtils.isFormatoAliasValido(transferenciaView.getAliasDestino())
				&& !transferenciaView.isFromAgendaDestinatario()) {
			LOGGER.info(ALIAS_CON_FORMATO_INCORRECTO);
			Mensaje mensaje = mensajeManager.obtenerMensajePorCodigo(CodigoMensajeConstantes.FORMATO_ALIAS_INEXISTENTE);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
					mensaje.getMensaje(), TipoError.ALIAS_INEXISTENTE.getDescripcion());
		}

		Cuenta cuentaOrigenConMonedaDeDestinoValida = TransferenciaUtil.obtenerPrimerCuentaConMonedaDestino(
				sesionCliente.getCliente().getCuentas(), transferenciaView.getMoneda());
		if(cuentaOrigenConMonedaDeDestinoValida == null ) {
			cuentaOrigenConMonedaDeDestinoValida = TransferenciaUtil.obtenerPrimerCuentaConMonedaDestino(
					sesionCliente.getCliente().getCuentasBPTransferibles(), transferenciaView.getMoneda());
		}

		if (null != cuentaOrigenConMonedaDeDestinoValida) {
			Respuesta<TransferenciaView> respuestaTransferenciaView = consultarDatosTitularidadExtendida(transferenciaView, agent);
			if (!EstadoRespuesta.ERROR.equals(respuestaTransferenciaView.getEstadoRespuesta())) {
			    super.obtenerMensajeSiHorarioDeTransferenciaNoEsValido(respuestaTransferenciaView);
			}

			if (respuestaTransferenciaView.getRespuesta() != null) {

				this.setShowMsgMEP(respuestaTransferenciaView.getRespuesta());
				this.fundsMessageValidations(respuestaTransferenciaView.getRespuesta());
			}

			return respuestaTransferenciaView;
		} else {
			LOGGER.info(CLIENTE_SIN_CTAS_CON_MONEDA_INDICADA);
			grabarEstadisticaDeSolicitudDeTransferencia(transferenciaView,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			Mensaje mensaje = mensajeManager
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.CUENTA_SIN_CTAS_CON_MONEDA_INDICADA);
			return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(TransferenciaView.class,
					mensaje.getMensaje(), TipoError.CLIENTE_NO_COINCIDE_MONEDA.getDescripcion());
		}
	}

	/**
	 * Consultar datos titularidad extendida.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param agent
	 *            the agent
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> consultarDatosTitularidadExtendida(TransferenciaView transferenciaView,
			String agent) {
		Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = transferenciaPorAliasBO
				.consultarDatosTitularidadExtendida(transferenciaView, sesionCliente.getCliente(), agent);
		return analizarRespuestaConsultaTitularidad(transferenciaView, respuestaTransferenciaDTO);

	}

	/**
	 * Analizar respuesta consulta titularidad.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param respuestaTransferenciaDTO
	 *            the resp
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaView> analizarRespuestaConsultaTitularidad(TransferenciaView transferenciaView,
			Respuesta<TransferenciaDTO> respuestaTransferenciaDTO) {
		Respuesta<TransferenciaView> respuestaFinal;
		if (EstadoRespuesta.OK.equals(respuestaTransferenciaDTO.getEstadoRespuesta())) {
			grabarEstadisticaDeSolicitudDeTransferencia(transferenciaView,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			respuestaFinal = respuestaFactory
					.crearRespuestaOk(convertirDTOaView(respuestaTransferenciaDTO.getRespuesta(), transferenciaView));
			return respuestaFinal;
		} else if (EstadoRespuesta.WARNING.equals(respuestaTransferenciaDTO.getEstadoRespuesta())) {
			grabarEstadisticaDeSolicitudDeTransferencia(transferenciaView,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			String tipoErrorRespuesta = respuestaTransferenciaDTO.getItemsMensajeRespuesta().get(0).getTipoError();
			if (TipoError.ERROR_NO_CONTEMPLADO.getDescripcion().equals(tipoErrorRespuesta)
					|| TipoError.ERROR_GENERICO.getDescripcion().equals(tipoErrorRespuesta)
					|| TipoError.ALIAS_ELIMINADO.getDescripcion().equals(tipoErrorRespuesta)
					|| TipoError.ALIAS_INEXISTENTE.getDescripcion().equals(tipoErrorRespuesta)) {
				transferenciaView.setAliasDestino(null);
				transferenciaView.setErrorConAlias(true);
			}
			if (transferenciaView.getImporte() != null)
				transferenciaView
						.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(transferenciaView.getImporte())));
			respuestaFinal = new Respuesta<TransferenciaView>();
			respuestaFinal.setRespuesta(transferenciaView);
			respuestaFinal.setEstadoRespuesta(respuestaTransferenciaDTO.getEstadoRespuesta());
			respuestaFinal.add(respuestaTransferenciaDTO.getItemsMensajeRespuesta().get(0));
			return respuestaFinal;
		} else {
			grabarEstadisticaDeSolicitudDeTransferencia(transferenciaView,
					EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			respuestaFinal = new Respuesta<TransferenciaView>();
			respuestaFinal.setEstadoRespuesta(respuestaTransferenciaDTO.getEstadoRespuesta());
			respuestaFinal.add(respuestaTransferenciaDTO.getItemsMensajeRespuesta().get(0));
			return respuestaFinal;
		}
	}

	private void setShowMsgMEP(TransferenciaView transferenciaView){

		try {

			List<String> alycsCuits = alycsDAO.getCuitsAlycs();

			if(TransferUtils.isRecipientCuitAlyc(transferenciaView.getCuit(), alycsCuits)){

				transferenciaView.setShowMsgMEP(true);
			}

		} catch (DAOException e) {

			LOGGER.error("Error al obtener el listado de cuits de Alycs", e);
		}

	}

	private void fundsMessageValidations(TransferenciaView transferenciaView) {

		Cliente cliente = sesionCliente.getCliente();

		if (fundsMessageHelper.showFundMessage(transferenciaView, cliente)) {

			String performanceFund = fundsMessageHelper.getPerformanceFund();

			if (!performanceFund.isEmpty()) {

				transferenciaView.setShowMsgFund(true);

				transferenciaView.setPerformanceFund(performanceFund);

				boolean hasTitleAccountBP = fundsMessageHelper.hasTitleAccountBP(cliente);
				boolean hasTitleAccountRetail = fundsMessageHelper.hasTitleAccountRetail(cliente);

				transferenciaView.setSegmentBP(hasTitleAccountBP);
				transferenciaView.setAccountTitle(hasTitleAccountRetail || hasTitleAccountBP);
			}
		}
	}

	/**
	 * Limpiar sesion.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 */
	private void limpiarSesion(TransferenciaView transferenciaView) {
		sesionParametros.setValidacionHash(null);
		sesionParametros.setDesafioEnCurso(null);
		sesionParametros.setExisteDesafioEnCurso(Boolean.FALSE);
		sesionParametros.setRsaRiesgoTransferenciaDTO(null);
		if (transferenciaView.getIdSesion() == null) {
			transferenciaView.generarNuevoIdSesion();
		}
	}

	/**
	 * Convertir DTO a view.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param transferenciaView
	 *            the transferencia view
	 * @return the transferencia view
	 */
	private TransferenciaView convertirDTOaView(TransferenciaDTO transferenciaDTO,
			TransferenciaView transferenciaView) {
		CuentasView cuentasViewFiltradas = new CuentasView();
		cuentasViewFiltradas.setCuentas(transferenciaDTO.getCuentasSaldosFiltrada());
		try {
			return cargarView(transferenciaView, transferenciaDTO, cuentasViewFiltradas);
		} catch (BusinessException e) {
			LOGGER.error(ERROR_AL_CARGAR_LA_VISTA_CON_EL_DTO, e);
			throw new RobotException(ERROR_AL_CARGAR_LA_VISTA_CON_EL_DTO);
		}
	}

	/**
	 * Grabar estadistica de solicitud de transferencia.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @param codigoEstado
	 *            the codigo estado
	 */
	private void grabarEstadisticaDeSolicitudDeTransferencia(TransferenciaView transferencia, String codigoEstado) {
		if (transferencia.isFromAgendaDestinatario())
			// TODO: por que si desde destinatario grabo una estadistica que
			// dice "RIO_RIO". O esta mal el nombre o no tiene sentido grabar
			// esto???
			estadisticaManager.add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_TRANSFERENCIA_RIO_RIO,
					codigoEstado);
		else
			estadisticaManager.add(EstadisticasConstants.CODIGO_SOLICITUD_TRANSFERENCIA_POR_ALIAS, codigoEstado);
	}

	/**
	 * Este metodo carga la vista que se retorna al front.
	 *
	 * @param transferenciaView
	 *            the transferencia view
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cuentasViewFiltradas
	 *            the cuentas view filtradas
	 * @return the transferencia view
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaView cargarView(TransferenciaView transferenciaView, TransferenciaDTO transferenciaDTO,
			CuentasView cuentasViewFiltradas) throws BusinessException {
		transferenciaView.setMensajesAyuda(transferenciaManager.obtenerAyudasConTemplate());
		return transferenciaManager.cargarVistaSolicitar(transferenciaView, sesionCliente.getCliente(),
				cuentasViewFiltradas, transferenciaDTO, transferenciaDTO.isHaciaCuentaPropia(),
				!transferenciaDTO.isHaciaOtroBanco());
	}

}
