/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import java.util.List;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.funds.FundApiErrors;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApiConstants;
import ar.com.santanderrio.obp.servicios.api.funds.entities.redemption.*;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.beanio.InvalidRecordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteRescateCitiEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteRescateEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaAgendamientoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatoConsultaAgedaRequest;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosFondosAgendEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoCitiInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondosAgendamientoResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoOutEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ErrorRescateCitiFondoCompassException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ServicioCerradoExCitiException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ServicioDeshabilitadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class RescateDAOImpl.
 */
@Component("rescateDAO")
public class RescateDAOImpl extends IatxBaseDAO implements RescateDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RescateDAOImpl.class);

	/** The Constant PREFIJO_CODIGO_CLIENTE. */
	private static final String PREFIJO_CODIGO_CLIENTE = "001";

	private static final String IMPORTE_RESCATE_COMISION_CERO = "00000000000000";

	private static final String SUPER_AHORRO_PESOS_CUOTA = "SUPER AHORRO $ CUOTA";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO. */
	private static final int CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO = 90000100;

	/** The Constant FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int COMPROBANTE_RESCATE_FUERA_HORARIO_CODIGO_RETORNO = 99000024;

	/** The Constant COMPROBANTE_RESCATE_CITI_FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int COMPROBANTE_RESCATE_CITI_FUERA_HORARIO_CODIGO_RETORNO = 10000102;

	/** The Constant COMPROBANTE_RESCATE_CITI_FUERA_HORARIO_CODIGO_RETORNO. */
	private static final int SERVICIO_RESCATE_EX_CITI_CERRADO = 10000222;

	/** The Constant CODIGO_ERROR_CUENTA_SIN_OPERAR. */
	private static final int CODIGO_ERROR_CUENTA_SIN_OPERAR = 10000117;

	/** The Constant SALDO_INSUFICIENTE. */
	private static final int SALDO_INSUFICIENTE = 99000174;

	/** The Constant SUPERA_SALDO_DISPONIBLE. */
	private static final int SUPERA_SALDO_DISPONIBLE = 10000174;

	/** The Constant ID_SISTEMA. */
	private static final String ID_SISTEMA = "FCI";

	private static final String ID_SISTEMA_CCO = "CCO";

	/** The Constant FUERA_DE_HORARIO. */
	private static final int FUERA_DE_HORARIO = 10000024;

	private static final int SRV_DESHABILITADO = 10000346;

	private static final int SRV_DESHABILITADO_CCO = 10099906;

	/** The Constant FUERA_DE_HORARIO. */
	private static final int FUERA_DE_HORARIO_CCO = 18888801;

	/** The time out. */
	@Value("#{'${FONDOS.CITI.COMPASS:}'.split(',')}")
	private List<Integer> fondosCitiCompass;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/** The rest web client. */
	@Autowired
	private RestWebClient restWebClient;

	/** The Constant NOMBRE_WS. */
	private static final String NOMBRE_WS = "INVERSIONES.MAPS";

	/** The inversion WS helper. */
	@Autowired
	private InversionWSHelper inversionWSHelper;

	@Autowired
	private FundsApi fundsApi;

	@Autowired
	private SesionParametros sessionParametros;

	/** The dato. */
	@Value("${INVERSIONES.FIRMA.DATO:Prueba}")
	private String dato;

	/** The firmar. */
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;

	/** The path tenencia valuada detalle fondo online. */
	private static String pathConsultaFondosAgendamiento = "/ConsultaFondosAGD/";

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio RESFCI____.
	 *
	 * @param entity the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataRESFCI(FondoInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getTerminalSafe());
		iatxRequestData.addBodyValue(entity.getCodigoFondo());
		iatxRequestData.addBodyValue(entity.getCodigoCliente());
		iatxRequestData.addBodyValue(entity.getCodigoAgente());
		iatxRequestData.addBodyValue(entity.getCodigoCanal());
		iatxRequestData.addBodyValue(entity.getImporteBruto());
		iatxRequestData.addBodyValue(entity.getCantidadCuotasPartes());
		iatxRequestData.addBodyValue(entity.getFormaPago());
		iatxRequestData.addBodyValue(entity.getTipoCuenta());
		iatxRequestData.addBodyValue(entity.getSucCuenta());
		iatxRequestData.addBodyValue(entity.getNroCuenta());
		iatxRequestData.addBodyValue(entity.getImpreSolicitud());
		iatxRequestData.addBodyValue(entity.getMoneda());
		iatxRequestData.addBodyValue(entity.getNroCertifAReversar());
		iatxRequestData.addBodyValue(entity.getMontoAReversar());
		iatxRequestData.addBodyValue(entity.getImporteRescateComision());
		iatxRequestData.addBodyValue(entity.getImporteRescateNeto());
		return iatxRequestData;
	}

	/**
	 * Genera el objeto IatxRequestData para llamar al servicio RESFCICITI.
	 *
	 * @param entity the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataRESFCICITI(FondoCitiInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(entity.getCliente());
		iatxRequestData.addBodyValue(entity.getCodigoFondo());
		iatxRequestData.addBodyValue(entity.getCuentaTitulo());
		iatxRequestData.addBodyValue(entity.getImporteBruto());
		iatxRequestData.addBodyValue(entity.getCantidadCuotasPartes());
		iatxRequestData.addBodyValue(entity.getFormaPago());
		iatxRequestData.addBodyValue(entity.getTipoCuenta());
		iatxRequestData.addBodyValue(entity.getSucCuenta());
		iatxRequestData.addBodyValue(entity.getNroCuenta());
		iatxRequestData.addBodyValue(entity.getMoneda());

		return iatxRequestData;
	}

	/**
	 * Logica errores comprobante rescate.
	 *
	 * @param rta       the rta
	 * @param errorCode the error code
	 * @throws DAOException the DAO exception
	 */
	public void logicaErroresComprobanteRescate(IatxResponse rta, int errorCode) throws DAOException {
		if (CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO == errorCode) {
			throw new ImporteMenorAlMinimoException();
		}
		if (COMPROBANTE_RESCATE_FUERA_HORARIO_CODIGO_RETORNO == errorCode
				&& ID_SISTEMA.equalsIgnoreCase(rta.getErrorSystem())) {
			throw new FueraDeHorarioException();
		}
		if (CODIGO_ERROR_CUENTA_SIN_OPERAR == errorCode) {
			throw new CuentaSinOperarException();
		}
		if (SALDO_INSUFICIENTE == errorCode) {
			throw new SaldoInsuficienteException();
		}

		if (FUERA_DE_HORARIO_CCO == errorCode && ID_SISTEMA_CCO.equalsIgnoreCase(rta.getErrorSystem())) {
			throw new FueraDeHorarioException();
		}

		LOGGER.debug("Codigo de error no esperado de iatx en servicio RESFCI____160. ");
		throw new DAOException();
	}

	/**
	 * Logica errores comprobante rescate CITI.
	 *
	 * @param rta       the rta
	 * @param errorCode the error code
	 * @throws DAOException the DAO exception
	 */
	public void logicaErroresComprobanteRescateCiti(IatxResponse rta, int errorCode) throws DAOException {
		if (COMPROBANTE_RESCATE_CITI_FUERA_HORARIO_CODIGO_RETORNO == errorCode) {
			throw new FueraDeHorarioException();
		}

		if (SERVICIO_RESCATE_EX_CITI_CERRADO == errorCode) {
			LOGGER.error("Error Servicio RESFCICITI100 cerrado: {}", rta.getErrorMessage());
			throw new ServicioCerradoExCitiException();
		}

		LOGGER.debug("Codigo de error no esperado de iatx en servicio RESFCICITI100. ");
		throw new DAOException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ComprobanteRescateEntity comprobanteRescate(FondoInEntity entity) throws DAOException {
		try {
			if(fundsApi.checkBouncerAccess(FundsApiConstants.Paths.REDEMPTIONS_BFF, FundsApiConstants.Bouncer.ACCESS, entity.getCliente().getNup())) {
				LOGGER.info("Inicia confirmacion de rescate por medio del bff de Rescates para el nup {}", entity.getCliente().getNup());
				return confirmacionRescateBFFRedemptions(entity);
			} else {
				LOGGER.info("Inicia confirmacion de rescate por medio del servicio de IATX para el nup {}", entity.getCliente().getNup());
				return confirmacionRescateIATXService(entity);
			}
		} catch (DAOException ex) {
			throw new DAOException(ex, ex.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RescateFondoOutEntity simulacionRescate(Cliente cliente, RescateFondoInEntity entity) throws DAOException {
		try {
			if(fundsApi.checkBouncerAccess(FundsApiConstants.Paths.REDEMPTIONS_BFF, FundsApiConstants.Bouncer.ACCESS, cliente.getNup())) {
				LOGGER.info("Inicia simulacion de rescate por medio del bff de Rescates para el nup {}", cliente.getNup());
				return simulacionRescateBFFRedemptions(cliente, entity);
			} else {
				LOGGER.info("Inicia simulacion de rescate por medio del servicio de IATX para el nup {}", cliente.getNup());
				return simulacionRescateIATXService(cliente, entity);
			}
		} catch (SaldoInsuficienteException ex) {
			throw new SaldoInsuficienteException();
		} catch (DAOException ex) {
			throw new DAOException(ex, ex.getMessage());
		}
	}

	/**
	 * Generate request data rescate fondo. servicio CNSRESFCI_140
	 * 
	 * @param cliente the cliente
	 * @param entity  the entity
	 * @return the iatx request data
	 */
	private IatxRequestData generateRequestDataRescateFondo(Cliente cliente, RescateFondoInEntity entity) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(entity.getTerminalSafe());
		iatxRequestData.addBodyValue(entity.getCodigoFondo());
		iatxRequestData.addBodyValue(entity.getCodigoCliente());
		iatxRequestData.addBodyValue(entity.getCodigoAgente());
		iatxRequestData.addBodyValue(entity.getCodigoCanal());
		iatxRequestData.addBodyValue(entity.getImporteNeto());
		iatxRequestData.addBodyValue(entity.getCantidadCuotas());
		iatxRequestData.addBodyValue(entity.getFormaDePago());
		iatxRequestData.addBodyValue(entity.getTipoCuenta());
		iatxRequestData.addBodyValue(entity.getSucursalCuenta());
		iatxRequestData.addBodyValue(entity.getNumeroCuenta());
		iatxRequestData.addBodyValue(entity.getImpreSolicitud());
		iatxRequestData.addBodyValue(entity.getMoneda());
		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.RescateDAO#
	 * comprobanteRescateCiti(ar.com.santanderrio.obp.servicios.inversiones.fondos.
	 * entity.FondoCitiInEntity)
	 */
	@Override
	public ComprobanteRescateCitiEntity comprobanteRescateCiti(FondoCitiInEntity entity) throws DAOException {
		String servicio = "RESFCICITI";
		String version = "100";
		ComprobanteRescateCitiEntity out = null;
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestDataRESFCICITI(entity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse rta = iatxComm.exec(iatxRequest);
			int errorCode = rta.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				out = processTrama(rta.getTrama(), ComprobanteRescateCitiEntity.class);

			} else if (fondosCitiCompass.contains(Integer.valueOf(entity.getCodigoFondo()))) {
				LOGGER.error("Error Servicio RESFCICITI100 para rescatar el fondo compass: {}",
						entity.getCodigoFondo());
				throw new ErrorRescateCitiFondoCompassException();
			} else {
				logicaErroresComprobanteRescateCiti(rta, errorCode);
			}
		} catch (IatxException e) {
			if (e.getMessage().equals(TIMEOUT_EXCEPTION)) {
				LOGGER.error(e.getMessage(), e);
				throw new TimeOutException(e.getMessage());
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		} catch (InvalidRecordException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
		return out;
	}

	@Override
	public boolean consultaFondosAgendamiento(String codigoFondo) {
		ConsultaAgendamientoRequestEntity request = armarRequestConsultaFondos(codigoFondo);
		FondosAgendamientoResponseEntity response = new FondosAgendamientoResponseEntity();
		boolean resp = false;

		try {
			WebClient consultaAgendamientoService = crearLlamadaAWebService(pathConsultaFondosAgendamiento);
			response = consultaAgendamientoService.post(request, FondosAgendamientoResponseEntity.class);
			if (response == null || response.getCodigo() != 0) {
				throw new DAOException();
			}
			if (!response.getDatos().isEmpty()) {
				for (DatosFondosAgendEntity fondos : response.getDatos()) {
					if (fondos.getCodigoFondo().equals(codigoFondo)) {
						resp = fondos.isPuedeRescatar();
					}
				}
			}

		} catch (DAOException e) {
			e.printStackTrace();
		}
		return resp;
	}

	/**
	 * Crear llamada A web service.
	 *
	 * @param pathConsultaFondosAgendamiento the path de consulta
	 * @return the web client
	 * @throws DAOException the DAO exception
	 */
	private WebClient crearLlamadaAWebService(String pathConsultaFondosAgendamiento) throws DAOException {

		WebClient service = restWebClient.obtenerClienteRest(NOMBRE_WS);
		service.accept(MediaType.APPLICATION_JSON);
		service.type(MediaType.APPLICATION_JSON + ";charset=UTF-8").accept(MediaType.APPLICATION_JSON);
		service.path(pathConsultaFondosAgendamiento);

		return service;
	}

	private ConsultaAgendamientoRequestEntity armarRequestConsultaFondos(String codigoFondo) {
		ConsultaAgendamientoRequestEntity request = new ConsultaAgendamientoRequestEntity();
		DatoConsultaAgedaRequest datos = new DatoConsultaAgedaRequest();
		datos.setCodigoFondo(codigoFondo);
		request.setDatos(datos);
		request.setFirma(generarFirma());
		request.setDato(dato);

		return request;
	}

	private String generarFirma() {
		String firma = "";
		if (Boolean.TRUE.equals(Boolean.parseBoolean(firmar))) {
			try {
				firma = inversionWSHelper.getDatosFirmados(dato);
			} catch (Exception e) {
				LOGGER.error("Error creando firma para Consulta fondos rescate agendamiento", e);
			}
		}
		return firma;
	}

	private ComprobanteRescateEntity confirmacionRescateBFFRedemptions(FondoInEntity in) throws DAOException {
		String identificadorSuperAhorroPesosCuota = "007";
		try {
			String transtactionToken = sessionParametros.getTransactionTokenRedemptionFund();
			ConfirmRedemptionRequestEntity request = new ConfirmRedemptionRequestEntity(transtactionToken);

			ConfirmRedemptionResponseDataEntity confirmationRedemption = fundsApi.confirmRedemption(request);

			ComprobanteRescateEntity comprobanteRescate = new ComprobanteRescateEntity();

			String totalCuotasPartes = formatearYgregarCerosIzquierda(confirmationRedemption.getShareAmount(), 14);
			String importeRescateNeto = formatearYgregarCerosIzquierda(confirmationRedemption.getNetAmount(), 14);

			comprobanteRescate.setNroRescate(StringUtils.leftPad(confirmationRedemption.getCertificateId(), 10, ISBANStringUtils.ZERO_STR));
			comprobanteRescate.setDescripcionMoneda(in.getMoneda());
			comprobanteRescate.setImporteRescateNeto(importeRescateNeto);
			comprobanteRescate.setImporteRescateComision(IMPORTE_RESCATE_COMISION_CERO);
			comprobanteRescate.setImporteRescateBruto(importeRescateNeto);
			comprobanteRescate.setTotalCuotasPartes(totalCuotasPartes);

			if(in.getCodigoFondo().equals(identificadorSuperAhorroPesosCuota)) {
				comprobanteRescate.setNombreFondo(SUPER_AHORRO_PESOS_CUOTA);
			}

			return comprobanteRescate;
		} catch (ApiException ex) {
			throw new DAOException(ex.getErrorResponse().getMessage(), ex.getErrorResponse().getCode());
		}
	}

	public ComprobanteRescateEntity confirmacionRescateIATXService(FondoInEntity entity) throws DAOException {
		String servicio = "RESFCI____";
		String version = "160";
		ComprobanteRescateEntity out = null;
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		try {
			IatxRequestData iatxRequestData = generateRequestDataRESFCI(entity);
			iatxRequest.setData(iatxRequestData);
			IatxResponse rta = iatxComm.exec(iatxRequest);
			int errorCode = rta.getErrorCode();
			if (OK_CODIGO_RETORNO == errorCode) {
				out = processTrama(rta.getTrama(), ComprobanteRescateEntity.class);

			} else {
				logicaErroresComprobanteRescate(rta, errorCode);
			}
		} catch (IatxException e) {
			if (e.getMessage().equals(TIMEOUT_EXCEPTION)) {
				LOGGER.error(e.getMessage(), e);
				throw new TimeOutException(e.getMessage());
			} else {
				LOGGER.error(e.getMessage(), e);
				throw new DAOException(e);
			}
		} catch (InvalidRecordException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException();
		}
		return out;
	}

	private RescateFondoOutEntity simulacionRescateBFFRedemptions(Cliente cliente, RescateFondoInEntity entity) throws DAOException {
		String identificadorMonedaPesos = "0";
		try {
			String operativeAccountPrefix = obtenerPrefijoCuentaOperativa(cliente.getCuentas(), entity.getNumeroCuenta());
			String currency = identificadorMonedaPesos.equals(entity.getMoneda()) ? DivisaEnum.PESO.getCodigo() : DivisaEnum.DOLAR.getCodigo();
			Double value = Double.valueOf(entity.getImporteNeto());

			SimulateRedemptionRequestEntity request = new SimulateRedemptionRequestEntity();
			request.setNup(cliente.getNup());
			request.setBranch(entity.getSucursalCuenta());
			request.setInvestmentAccount(entity.getCodigoCliente());
			request.setOperativeAccount(entity.getNumeroCuenta());
			request.setOperativeAccountPrefix(operativeAccountPrefix);
			request.setOperativeAccountType(entity.getTipoCuenta());
			request.setFundCode(entity.getCodigoFondo());
			request.setCurrency(currency);
			request.setValue(value);

			SimulateRedemptionResponseDataEntity simulateRedemption = fundsApi.simulateRedemption(request);
			RescateFondoOutEntity rescateFondoOutEntity = new RescateFondoOutEntity();

			String totalCuotasPartes = formatearYgregarCerosIzquierda(simulateRedemption.getShareAmount(), 14);
			String importeRescateNeto = formatearYgregarCerosIzquierda(simulateRedemption.getNetAmount(), 14);

			rescateFondoOutEntity.setTotalCuotasPartes(totalCuotasPartes);
			rescateFondoOutEntity.setImporteRescateNeto(importeRescateNeto);
			rescateFondoOutEntity.setImporteRescateComision(IMPORTE_RESCATE_COMISION_CERO);

			sessionParametros.setTransactionTokenRedemptionFund(simulateRedemption.getTransactionToken());

			return rescateFondoOutEntity;
		} catch (ApiException ex) {
			switch (FundApiErrors.valueOf(ex.getErrorResponse().getCode())) {
				case APF_131:
				case APF_130:
					throw new FueraDeHorarioException();
				case FCI_174:
					throw new SaldoInsuficienteException();
				default:
					throw new DAOException(ex.getErrorResponse().getMessage(), ex.getErrorResponse().getCode());
			}
		}
	}

	private RescateFondoOutEntity simulacionRescateIATXService(Cliente cliente, RescateFondoInEntity entity) throws DAOException {
		String servicio = "CNSRESFCI_";
		String version = "140";
		IatxRequest iatxRequest = new IatxRequest(servicio, version);
		IatxResponse iatxResponse;
		RescateFondoOutEntity rescateFondoOutEntity;

		entity.setImporteNeto(ISBANStringUtils.formatearImporteRescate(entity.getImporteNeto()));
		entity.setCodigoCliente(PREFIJO_CODIGO_CLIENTE + StringUtils.leftPad(entity.getCodigoCliente(), 8, ISBANStringUtils.ZERO_STR));

		try {
			IatxRequestData iatxRequestData = generateRequestDataRescateFondo(cliente, entity);
			iatxRequest.setData(iatxRequestData);
			iatxResponse = iatxComm.exec(iatxRequest);
		} catch (IatxException e) {
			throw new DAOException(e, e.getMessage());
		} catch (Exception e) {
			throw new DAOException(e, e.getMessage());
		}
		int errorCode = iatxResponse.getErrorCode();
		if (OK_CODIGO_RETORNO == errorCode) {
			rescateFondoOutEntity = processTrama(iatxResponse.getTrama(), RescateFondoOutEntity.class);
		} else {
			if (FUERA_DE_HORARIO == errorCode) {
				throw new FueraDeHorarioException();
			}
			if (SRV_DESHABILITADO == errorCode) {
				throw new ServicioDeshabilitadoException();
			}
			if (SRV_DESHABILITADO_CCO == errorCode) {
				throw new ServicioDeshabilitadoException();
			}
			if (SUPERA_SALDO_DISPONIBLE == errorCode) {
				throw new SaldoInsuficienteException();
			}

			if (FUERA_DE_HORARIO_CCO == errorCode && ID_SISTEMA_CCO.equalsIgnoreCase(iatxResponse.getErrorSystem())) {
				throw new FueraDeHorarioException();
			}

			LOGGER.debug(iatxResponse.getErrorMessage());
			throw new DAOException(null, iatxResponse.getErrorMessage(), String.valueOf(errorCode));
		}
		return rescateFondoOutEntity;
	}

	private String obtenerPrefijoCuentaOperativa(List<Cuenta> cuentas, String cuentaBuscada) throws DAOException {
		for (Cuenta cuenta: cuentas) {
			Integer numeroCuentaProducto = Integer.valueOf(cuenta.getNroCuentaProducto());
			boolean encontrado = Integer.valueOf(cuentaBuscada).equals(numeroCuentaProducto);

			if(encontrado) {
				String productoAltair = ISBANStringUtils.eliminarCeros(cuenta.getProductoAltair());
				return StringUtils.rightPad(productoAltair, 3, ISBANStringUtils.ZERO_STR);
			}
		}
		throw new DAOException(String.format("No se pudo determinar el prefijo para la cuenta %s", cuentaBuscada));
	}

	private String formatearYgregarCerosIzquierda(Number numerico, Integer size) {
		String numberFormat = String.valueOf(numerico).replace(".","");
		return StringUtils.leftPad(numberFormat, size, ISBANStringUtils.ZERO_STR);
	}
}
