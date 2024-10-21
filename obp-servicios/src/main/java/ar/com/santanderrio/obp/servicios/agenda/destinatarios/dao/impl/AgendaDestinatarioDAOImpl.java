/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.impl;

import java.util.List;

import ar.com.santanderrio.obp.servicios.api.transfers.recipients.RecipientsApi;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.exception.RecipientsApiException;
import ar.com.santanderrio.obp.servicios.api.transfers.recipients.mapper.ConsultaAgendaDestinatarioMapper;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricRegisterBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.DetalleError;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferMetricInfoDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferStatus;
import ar.com.santanderrio.obp.servicios.transferencias.utils.TransferUtils;
import org.apache.bval.constraints.EmailValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ConsultaAgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.LlamadaAgendamientoEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.util.CuentasBancaPrivadaUtil;

/**
 * The Class AgendaDestinatarioDAOImpl.
 */
@Component
public class AgendaDestinatarioDAOImpl extends IatxBaseDAO implements AgendaDestinatarioDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaDestinatarioDAOImpl.class);

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;
	
	/** The Constant ERROR_CUENTA_MIGRADA. */
	private static final int ERROR_CUENTA_MIGRADA = 10010094;

	/** The Constant ERROR_SERVICIO. */
	public static final String ERROR_SERVICIO = "0001";

	/** The Constant VEINTE. */
	private static final int VEINTE = 20;

	/** The Constant QUINCE. */
	private static final int QUINCE = 15;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	@Autowired
	private RecipientsApi recipientsApi;

	@Autowired
	private MetricBuilder metricBuilder;

	@Autowired
	private MetricRegisterBO metricRegisterBO;

	@Value("${RECIPIENTS-API.ENABLED.NUPS.LIST}")
	private String recipientsApiEnabledNups;

	/** The canal banca privada. */
	@Value("${CANAL.BANCA.PRIVADA}")
	private String canalBP; 
	
	/** The sub canal banca privada. */
	@Value("${SUB.CANAL.BANCA.PRIVADA}")
	private String subCanalBP;

	/**
	 * Consulta a iatx para consulta de destinatario.
	 *
	 * @param inEntity
	 *            the entity
	 * @return entidad con la informacion de retorno de iatx de la consulta de
	 *         destinatario
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ConsultaAgendaDestinatarioOutEntity consultar(ConsultaAgendaDestinatarioInEntity inEntity)
			throws DAOException {

		try {

			LOGGER.info("Inicio checkeo de nup para determinar si debe consultar los destinatarios a traves de recipients-API o coordinador");

			if(TransferUtils.isNupEnabled(inEntity.getCliente().getNup(), recipientsApiEnabledNups)) {

				LOGGER.info("Se procede a obtener los destinatarios a traves de Recipients-API");

				return ConsultaAgendaDestinatarioMapper
						.toConsultaAgendaDestinatarioOutEntity(recipientsApi.getAllRecipients(inEntity.getCliente().getNup()));

			}

		} catch (Exception e) {

			LOGGER.error("Error al obtener los destinatarios a traves de Recipients-API", e);

			registerMetricRecipientsApiError(inEntity.getCliente().getNup());

		}

		LOGGER.info("Se procede a obtener los destinatarios a traves del coordinador");

		return realizarConsultaPorCoordinador(inEntity);

	}

	private void registerMetricRecipientsApiError(String nup) {

		TransferMetricInfoDTO transferMetricInfoDTO = new TransferMetricInfoDTO(nup, TransferStatus.FAIL, false);
		transferMetricInfoDTO.setDetalleError(DetalleError.RECIPIENTS_API_ERROR);

		metricRegisterBO.register(metricBuilder.createMetricTransfer(transferMetricInfoDTO));

	}

	private ConsultaAgendaDestinatarioOutEntity realizarConsultaPorCoordinador(ConsultaAgendaDestinatarioInEntity inEntity) throws DAOException {

		ConsultaAgendaDestinatarioOutEntity outEntity = new ConsultaAgendaDestinatarioOutEntity();

		if (!ValidationEntity.validate(inEntity)) {

			outEntity.setCodigoRetornoExtendido("0001");

			return outEntity;

		}

		try {

			ConsultaAgendaDestinatarioOutEntity res = ejecucionConsulta(outEntity, inEntity);

			if (res != null) {

				return res;

			}

		} catch (DAOException e) {

			LOGGER.error(e.getMessage(), e);

			throw new DAOException(e);

		}

		return outEntity;

	}

	/**
	 * Ejecucion consulta.
	 *
	 * @param outEntity
	 *            the out entity
	 * @param inEntity
	 *            the in entity
	 * @return the consulta agenda destinatario out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaAgendaDestinatarioOutEntity ejecucionConsulta(ConsultaAgendaDestinatarioOutEntity outEntity,
			ConsultaAgendaDestinatarioInEntity inEntity) throws DAOException {
		String servicio = "CNSAGEDEST";
		String version = "120";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		Boolean masRegistros = Boolean.TRUE;
		while (masRegistros && ValidationEntity.validate(inEntity)) {
			ConsultaAgendaDestinatarioOutEntity consultaAgendaDestinatarioOutEntity = new ConsultaAgendaDestinatarioOutEntity();
			try {
                IatxRequestData iatxRequestData = generateRequestDataCNSAGEDEST(inEntity);
				
				if(inEntity.getTieneCuetaBancaP()) {//banca privada
					iatxRequestData.setCanalTipo(canalBP);
					iatxRequestData.setSubCanalTipo(subCanalBP);
				}
				
				iatxRequest.setData(iatxRequestData);
				IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
				int errorCode = iatxResponse.getErrorCode();
				if (OK_CODIGO_RETORNO == errorCode) {
					consultaAgendaDestinatarioOutEntity = processTrama(iatxResponse.getTrama(),
							ConsultaAgendaDestinatarioOutEntity.class);
					masRegistros = consultaAgendaDestinatarioOutEntity.getHayMasRegistros();
				} else {
					return returnErrorRellamada(esRellamada(outEntity), outEntity, errorCode);
				}
			} catch (IatxException e) {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
			outEntity.getDestinatarios().addAll(consultaAgendaDestinatarioOutEntity.getDestinatarios());

			if (masRegistros) {
				recargar(inEntity, consultaAgendaDestinatarioOutEntity.getDestinatarios());
			}
		}
		return null;
	}

	/**
	 * Agrega/Elimina un destinatario de iatx.
	 *
	 * @param inEntity
	 *            the entity
	 * @param ip
	 *            the ip
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return entidad con la informacion de retorno de iatx de la consulta de
	 *         destinatario
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public AgendaDestinatarioOutEntity eliminarUAgregar(AgendaDestinatarioInEntity inEntity, String ip,
			TipoOperacionACTAGEDESTEnum tipoOperacion) throws DAOException {
		AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();

		if (tipoOperacion.equals(TipoOperacionACTAGEDESTEnum.ALTA) && validar(inEntity)) {
			outEntity.setCodigoRetornoExtendido(ERROR_SERVICIO);
			return outEntity;
		}

		String servicio = "ACTAGEDEST";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestDataACTAGEDEST(inEntity, tipoOperacion, ip);
			if(inEntity.getIsTransferenciaBP()) {//banca privada
				iatxRequestData.setCanalTipo(canalBP);
				iatxRequestData.setSubCanalTipo(subCanalBP);
			}
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				outEntity = crearOutEntity(iatxResponse);
			} else {
				LOGGER.debug("Codigo de error no esperado de iatx en servicio CNSAGEDEST. ");
				outEntity.setCodigoRetornoExtendido(String.valueOf(iatxResponse.getErrorCode()));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return outEntity;
	}

	/**
	 * Validar.
	 *
	 * @param inEntity
	 *            the in entity
	 * @return the boolean
	 */
	private Boolean validar(AgendaDestinatarioInEntity inEntity) {
		EmailValidator emailValidator = new EmailValidator();
		if (!ValidationEntity.validate(inEntity) || !inEntity.getDireccionCorreo().equals(StringUtils.repeat(" ", 100))
				&& (!emailValidator.isValid(inEntity.getDireccionCorreo().trim(), null)
						|| inEntity.getDireccionCorreo().startsWith(" "))) {
			return true;
		}
		return false;
	}

	/**
	 * Valida la cuenta del cliente.
	 * 
	 * @param entity
	 *            the entity
	 * @return the datos cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public DatosCliente validarCuenta(ValidacionCuentaInEntity entity) throws DAOException {
		String servicio = "CNSCTATIT_";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		DatosCliente datosCliente = new DatosCliente();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSCTATIT(entity);
			
			if(CuentasBancaPrivadaUtil.isCuentaBP(entity.getSucursalCuenta())) {
				iatxRequestData.setCanalTipo(canalBP);
				iatxRequestData.setSubCanalTipo(subCanalBP);
			}
			
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				datosCliente = parsearDatosCliente(iatxResponse);
			} else if (ERROR_CUENTA_MIGRADA == errorCode) { // Cierre de sucursales
				datosCliente.setCodigoError(errorCode);
				datosCliente.setMensajeError(iatxResponse.getErrorMessage());
			} else  {
				datosCliente.setCodigoError(errorCode);
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return datosCliente;

	}

	/**
	 * Valida la cuenta del cliente.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the datos cliente
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ValidacionCuentaOutCBUEntity validarCuentaTransferenciaCBU(ValidacionCuentaInCBUEntity transferencia)
			throws DAOException {
		String servicio = "CNSTITCBU_";
		String version = "110";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		ValidacionCuentaOutCBUEntity datosCliente = new ValidacionCuentaOutCBUEntity();
		try {
			IatxRequestData iatxRequestData = generateRequestDataCNSTITCBU(transferencia);
			if(CuentasBancaPrivadaUtil.isCuentaBP(transferencia.getSucursalCuentaCredito())) {
				iatxRequestData.setCanalTipo(canalBP);
				iatxRequestData.setSubCanalTipo(subCanalBP);
			}
			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				datosCliente = processTrama(iatxResponse.getTrama(), ValidacionCuentaOutCBUEntity.class);
			} else {
				datosCliente.setCodigoRetornoExtendido(String.valueOf(errorCode));
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
		return datosCliente;

	}

	/**
	 * Generate request data CNSTITCBU.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSTITCBU(ValidacionCuentaInCBUEntity transferencia) {
		IatxRequestData iatxRequestData = new IatxRequestData(transferencia.getCliente());

		// A2
		iatxRequestData.addBodyValue(transferencia.getTipoCuentaCredito());

		// A3
		iatxRequestData.addBodyValue(transferencia.getSucursalCuentaCredito());

		// A7
		iatxRequestData.addBodyValue(transferencia.getNumeroCuentaCredito());

		// A22
		iatxRequestData.addBodyValue(transferencia.getCbu());

		// A18
		iatxRequestData.addBodyValue(transferencia.getNroTarjeta());

		// N12
		iatxRequestData.addBodyValue(transferencia.getDireccionIp());
		return iatxRequestData;
	}

	/**
	 * Crea el objeto AgendaDestinatarioOutEntity.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the agenda destinatario out entity
	 */
	private AgendaDestinatarioOutEntity crearOutEntity(IatxResponse iatxResponse) {
		AgendaDestinatarioOutEntity outEntity = processTrama(iatxResponse.getTrama(),
				AgendaDestinatarioOutEntity.class);
		// fecha formato AAAAMMDD
		outEntity.setFecha(iatxResponse.getFecha());
		// hora formato HH:MM
		String[] horaConSegundos = iatxResponse.getHora().split(":");
		outEntity.setHora(horaConSegundos[0] + ISBANStringUtils.DOS_PUNTOS_STRING + horaConSegundos[1]);
		outEntity.setNroComprobante(iatxResponse.getNroComprobante());
		return outEntity;
	}

	/**
	 * Es rellamada.
	 *
	 * @param out
	 *            the out
	 * @return the boolean
	 */
	private Boolean esRellamada(ConsultaAgendaDestinatarioOutEntity out) {
		return !out.getDestinatarios().isEmpty();
	}

	/**
	 * setea el codigo de error y los flags de rellamada en el objeto
	 * consultaAgendaDestinatarioOutEntityRetorno.
	 *
	 * @param esRellamada
	 *            the es rellamada
	 * @param consultaAgendaDestinatarioOutEntityRetorno
	 *            the consulta agenda destinatario out entity retorno
	 * @param errorCode
	 *            the error code
	 * @return the consulta agenda destinatario out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultaAgendaDestinatarioOutEntity returnErrorRellamada(Boolean esRellamada,
			ConsultaAgendaDestinatarioOutEntity consultaAgendaDestinatarioOutEntityRetorno, int errorCode)
			throws DAOException {
		if (esRellamada) {
			LOGGER.debug("Error del servio iatx CNSAGEDEST en una re-invocacion. ");
			consultaAgendaDestinatarioOutEntityRetorno.setErrorRellamada(Boolean.TRUE);
		}
		consultaAgendaDestinatarioOutEntityRetorno.setCodigoRetornoExtendido(String.valueOf(errorCode));
		return consultaAgendaDestinatarioOutEntityRetorno;
	}

	/**
	 * Recargar.
	 *
	 * @param entity
	 *            the entity
	 * @param destinatarios
	 *            the destinatarios
	 */
	private void recargar(ConsultaAgendaDestinatarioInEntity entity, List<DestinatarioEntity> destinatarios) {
		DestinatarioEntity destinatarioEntity = destinatarios.get(destinatarios.size() - NumberUtils.INTEGER_ONE);
		entity.setLlamada(LlamadaAgendamientoEnum.CONTINUAR.getCampo());
		entity.setTipoCuentaCredito(destinatarioEntity.getTipoCuentaDestinatario());
		entity.setSucursalCuentaCredito(destinatarioEntity.getSucursalCuentaDestinatario());
		entity.setNumeroCuentaCredito(destinatarioEntity.getNumeroCuentaDestinatario());
		entity.setCbu(destinatarioEntity.getCbuDestinatario());
		entity.setTipoDocumentoDestinatario(destinatarioEntity.getTipoDocumentoDestinatario());
		entity.setDocumentoDestino(destinatarioEntity.getDocumentoDestinatario());
		entity.setRealizarRellamado(Boolean.FALSE);
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio iatx.
	 * 
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSAGEDEST(ConsultaAgendaDestinatarioInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		// A01
		iatxRequestData.addBodyValue(entity.getTipoConsulta());
		// A02
		iatxRequestData.addBodyValue(entity.getLlamada());
		// A02
		iatxRequestData.addBodyValue(entity.getTipoCuentaCredito());
		// A04
		iatxRequestData.addBodyValue(entity.getSucursalCuentaCredito());
		// A12
		iatxRequestData.addBodyValue(entity.getNumeroCuentaCredito());
		// A22
		iatxRequestData.addBodyValue(entity.getCbu());
		// A02
		iatxRequestData.addBodyValue(entity.getTipoDocumentoDestinatario());
		// A11
		iatxRequestData.addBodyValue(entity.getDocumentoDestino());
		// A03
		iatxRequestData.addBodyValue(entity.getTipoAgenda());

		// iatxRequestData.addBodyValue(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING,
		// VEINTE));

		return iatxRequestData;
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio iatx.
	 *
	 * @param entity
	 *            the entity
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param ip
	 *            the ip
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataACTAGEDEST(AgendaDestinatarioInEntity entity,
			TipoOperacionACTAGEDESTEnum tipoOperacion, String ip) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		// A03
		iatxRequestData.addBodyValue(entity.getTipoAgendaOcurrencia());
		// A01
		iatxRequestData.addBodyValue(tipoOperacion.getCampo());
		// A02
		iatxRequestData.addBodyValue(entity.getTipoCuentaDestinatario());
		// A04
		iatxRequestData.addBodyValue(entity.getSucursalCuentaDestinatario());
		// A12
		iatxRequestData.addBodyValue(entity.getNumeroCuentaDestinatario());

		// A22
		iatxRequestData.addBodyValue(entity.getCbuDestinatario());
		// A50
		iatxRequestData.addBodyValue(entity.getBancoDestinatario());
		// A02
		iatxRequestData.addBodyValue(entity.getTipoDocumentoDestinatario());
		// A11
		iatxRequestData.addBodyValue(entity.getDocumentoDestinatario());
		// A30
		iatxRequestData.addBodyValue(entity.getDescripcionCuentaDestinatario());

		// A10
		iatxRequestData.addBodyValue(entity.getCaracteristicasCuentaDestinatario());
		// A641
		iatxRequestData.addBodyValue(entity.getTitular());
		// A100
		iatxRequestData.addBodyValue(entity.getDireccionCorreo());
		// A15
		iatxRequestData.addBodyValue(StringUtils.rightPad(ip, QUINCE, ISBANStringUtils.ESPACIO_STRING));
		// A16
		iatxRequestData.addBodyValue(entity.getTelefonoDestinatario());

		if (entity.getAlias() == null) {
			iatxRequestData.addBodyValue(StringUtils.repeat(ISBANStringUtils.ESPACIO_STRING, VEINTE));
		} else {
			iatxRequestData.addBodyValue(entity.getAlias());
		}

		return iatxRequestData;
	}

	/**
	 * Crea el objeto IatxRequestData con la informacion de entity.
	 *
	 * @param entity
	 *            the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataCNSCTATIT(ValidacionCuentaInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getTipoCuenta());
		iatxRequestData.addBodyValue(entity.getSucursalCuenta());
		iatxRequestData.addBodyValue(entity.getNumeroCuenta());
		iatxRequestData.addBodyValue(entity.getInformarCuil());
		return iatxRequestData;
	}

	/**
	 * transforma la respuesta del servicio a un objeto del tipo DatosCliente.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the datos cliente
	 */
	private DatosCliente parsearDatosCliente(IatxResponse iatxResponse) {
		DatosCliente datosCliente = new DatosCliente();
		datosCliente.setNombre(iatxResponse.getNextData());
		datosCliente.setTipoCUILCUIT(iatxResponse.getNextData());
		datosCliente.setNumeroCUILCUIT(iatxResponse.getNextData());
		datosCliente.setNup(iatxResponse.getNextData());
		datosCliente.setCodigoError(iatxResponse.getErrorCode());
		return datosCliente;
	}

}
