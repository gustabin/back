/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.signer.TokenSigner;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.ConfirmacionSolicitudPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.PlanVService;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.SolicitudPlanV;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.ConsultaFinanciacionDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosConfirmacionFinanciacionTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.SimulacionPlanVRequestDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;

/**
 * The Class ConsultaFinanciacionDAOImpl.
 *
 * @author sergio.e.goldentair
 */
@Component
public class ConsultaFinanciacionDAOImpl implements ConsultaFinanciacionDAO {

	/** The Constant DIGITO_VERIFICADOR_OFFSET. */
	private static final int DIGITO_VERIFICADOR_OFFSET = 18;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaFinanciacionDAOImpl.class);

	/** The Constant TOKEN_FACTORY_CODE. */
	private static final String TOKEN_FACTORY_CODE = "consultaFinanciacion";

	/** Constante del nombre del servcio de Baja de TJ. */
	private static final String NOMBRE_SERVICIO_SIMFIPLANV = "SIMFIPLANV";

	/** Constante del nombre del servcio de Baja de TJ. */
	private static final String VERSION_SERVICIO_SIMFIPLANV = "100";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final int OK_CODIGO_RETORNO = 0;

	/** The Constant TipoCuenta_VISA_Codigo. */
	private static final String TipoCuenta_VISA_Codigo = "1";

	/** The Constant TipoCuenta_AMEX_Codigo. */
	private static final String TipoCuenta_AMEX_Codigo = "4";

	/** The Constant GET_INFORMACION_PLAN_V_TOKEN_FACTORY_CODE. */
	private static final String GET_INFORMACION_PLAN_V_TOKEN_FACTORY_CODE = "getInformacionPlanV";

	/** The Constant CONFIRMAR_SOLICITUD_PLAN_V_TOKEN_FACTORY_CODE. */
	private static final String CONFIRMAR_SOLICITUD_PLAN_V_TOKEN_FACTORY_CODE = "confirmarSolicitudPlanV";

	/** The gestor plan V. */
	@Autowired
	@Qualifier("planVGestor")
	private GestionarWSAbstract<PlanVService> gestorPlanV;

	/** The token signer. */
	@Autowired
	private TokenSigner tokenSigner;

	/** The iatx comm. */
	@Autowired
	private IatxComm iatxComm;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.tarjetas.dao.ConsultaFinanciacionDAO#
	 * obtenerListaFinanciacion()
	 */
	@Override
	public List<SolicitudPlanV> obtenerListaFinanciacion(Cuenta cuenta) throws DAOException {
		LOGGER.info("Obtener lista de Financiacion para {}.", TOKEN_FACTORY_CODE);
		String tokenFirmado = tokenSigner.obtenerTokenFirmado(TOKEN_FACTORY_CODE, cuenta);
		List<SolicitudPlanV> listSolicitudPlanV = consumirWsPlanV(tokenFirmado);
		LOGGER.info("El ws retorna {} planes de financiacion.", listSolicitudPlanV.size());
		return listSolicitudPlanV;
	}

	/**
	 * Consumir ws plan V.
	 *
	 * @param tokenFirmado
	 *            the token firmado
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<SolicitudPlanV> consumirWsPlanV(String tokenFirmado) throws DAOException {
		PlanVService planVService = null;
		try {
			planVService = gestorPlanV.obtenerPort();
			return planVService.getSolicitudesPlanV(tokenFirmado);
		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} finally {
			gestorPlanV.liberarPort(planVService);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.ConsultaFinanciacionDAO#
	 * obtenerInformacionPlanV(ar.com.santanderrio.obp.servicios.cuentas.
	 * entities.Cuenta)
	 */
	@Override
	public InformacionPlanV obtenerInformacionPlanV(Cuenta cuenta) throws DAOException {
		LOGGER.info("Obtener informacion de financiacion PlanV");
		// Se utiliza el mismo token factory ya que pide lo mismo
		String tokenFirmado = tokenSigner.obtenerTokenFirmado(GET_INFORMACION_PLAN_V_TOKEN_FACTORY_CODE, cuenta);
		InformacionPlanV informacionPlanV = consumirGetInformacionPlanV(tokenFirmado);
		LOGGER.info("Código retorno WS getInformacionPlanV: {}", informacionPlanV.getCodigoRespuesta());
		return informacionPlanV;
	}

	/**
	 * Consumir get informacion plan V.
	 *
	 * @param tokenFirmado
	 *            the token firmado
	 * @return the informacion plan V
	 * @throws DAOException
	 *             the DAO exception
	 */
	private InformacionPlanV consumirGetInformacionPlanV(String tokenFirmado) throws DAOException {
		PlanVService planVService = null;
		try {
			planVService = gestorPlanV.obtenerPort();
			InformacionPlanV informacionPlanVRespuesta = planVService.getInformacionPlanV(tokenFirmado);
			int codigoRespuesta = Integer.parseInt(informacionPlanVRespuesta.getCodigoRespuesta());
			LOGGER.info("El ws planv#getInformacionPlanV responde con codigo {}", codigoRespuesta);
			if (OK_CODIGO_RETORNO != codigoRespuesta) {
				throw new DAOException();
			}
			return informacionPlanVRespuesta;
		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		} finally {
			gestorPlanV.liberarPort(planVService);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.ConsultaFinanciacionDAO#
	 * simulacionFinanciacionPlanV(ar.com.santanderrio.obp.servicios.tarjetas.
	 * entities.ConsultaFinanciacionDTO)
	 */
	@Override
	public SimulacionPlanVDTO simulacionFinanciacionPlanV(SimulacionPlanVRequestDTO simulacionPlanVRequestDTO,
			Cliente cliente) throws DAOException, SimulacionDAOException {

		try {
			IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO_SIMFIPLANV, VERSION_SERVICIO_SIMFIPLANV);
			IatxRequestData iatxRequestData = generarRequestDataSimulacionPlanV(simulacionPlanVRequestDTO, cliente);

			iatxRequest.setData(iatxRequestData);
			IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
			int errorCode = iatxResponse.getErrorCode();
			switch (errorCode) {
			case OK_CODIGO_RETORNO:
				return parsearSimulacion(iatxResponse);
			default:
				throw new SimulacionDAOException(iatxResponse.getErrorMessage());
			}
		} catch (IatxException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e);
		}
	}

	/**
	 * Parsear simulacion.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @return the respuesta
	 */
	private SimulacionPlanVDTO parsearSimulacion(IatxResponse iatxResponse) {
		SimulacionPlanVDTO simulacionDTO = new SimulacionPlanVDTO();
		simulacionDTO.setCargoTipo(iatxResponse.getNextData());
		simulacionDTO.setCargoValor(iatxResponse.getNextData());
		simulacionDTO.setSeguroCobra(iatxResponse.getNextData());
		simulacionDTO.setSeguroTasa(iatxResponse.getNextData());
		simulacionDTO.setSeguroIVA(iatxResponse.getNextData());
		simulacionDTO.setIVAintereses(iatxResponse.getNextData());
		simulacionDTO.setDifiereCuota(iatxResponse.getNextData());
		simulacionDTO.setFinanciaCuota(iatxResponse.getNextData());
		simulacionDTO.setPenaltyTipo(iatxResponse.getNextData());
		simulacionDTO.setPenaltyValor(iatxResponse.getNextData());
		simulacionDTO.setTasasRango1(iatxResponse.getNextData());
		simulacionDTO.setTasasRango2(iatxResponse.getNextData());
		simulacionDTO.setTasasRango3(iatxResponse.getNextData());
		simulacionDTO.setTasasRango4(iatxResponse.getNextData());
		simulacionDTO.setTasasRango5(iatxResponse.getNextData());
		simulacionDTO.setTasasRango6(iatxResponse.getNextData());
		simulacionDTO.setTasasValor1(iatxResponse.getNextData());
		simulacionDTO.setTasasValor2(iatxResponse.getNextData());
		simulacionDTO.setTasasValor3(iatxResponse.getNextData());
		simulacionDTO.setTasasValor4(iatxResponse.getNextData());
		simulacionDTO.setTasasValor5(iatxResponse.getNextData());
		simulacionDTO.setTasasValor6(iatxResponse.getNextData());
		simulacionDTO.setTasaEfecMensual(iatxResponse.getNextData());
		simulacionDTO.setModeloLiq(iatxResponse.getNextData());
		simulacionDTO.setTotalCOC(iatxResponse.getNextData());
		simulacionDTO.setTotalCargo(iatxResponse.getNextData());
		simulacionDTO.setTotalSeguro(iatxResponse.getNextData());
		simulacionDTO.setTotalIVA(iatxResponse.getNextData());
		simulacionDTO.setImporteCuota(iatxResponse.getNextData());
		simulacionDTO.setCostoFinancieroTotal(iatxResponse.getNextData());
		simulacionDTO.setEstadoSimulacion(iatxResponse.getNextData());
		return simulacionDTO;
	}

	/**
	 * Generar request data simulacion plan V. 1 Tipo_cuenta A01 2
	 * Nro_Cuenta_Visa N10 3 Importe N09(7e2d) 4 Moneda A03 5 Cuotas N02 6
	 * Tipo_Plan_V A01
	 *
	 * @param simulacionPlanVRequestDTO
	 *            the simulacion plan V request DTO
	 * @param cliente
	 *            the cliente
	 * @return the iatx request data
	 */
	private IatxRequestData generarRequestDataSimulacionPlanV(SimulacionPlanVRequestDTO simulacionPlanVRequestDTO,
			Cliente cliente) {
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);

		String tipo = simulacionPlanVRequestDTO.getTipoCuenta();
		if (tipo.equals(TipoCuenta.VISA.name()) || tipo.equals(TipoCuenta_VISA_Codigo)) {
			iatxRequestData.addBodyValue(TipoCuenta_VISA_Codigo);
			iatxRequestData.addBodyValue(
					ISBANStringUtils.formateadorConCerosIzq(simulacionPlanVRequestDTO.getNumeroDeCuentaProducto(), 10));
		} else if (tipo.equals(TipoCuenta.AMEX.name())) {
			iatxRequestData.addBodyValue(TipoCuenta_AMEX_Codigo);
			iatxRequestData
					.addBodyValue(
							ISBANStringUtils
									.formateadorConCerosIzq(
											simulacionPlanVRequestDTO.getNumeroDeCuentaProducto()
													.concat(simulacionPlanVRequestDTO.getCbu().substring(
															DIGITO_VERIFICADOR_OFFSET, DIGITO_VERIFICADOR_OFFSET + 1)),
											10));

		}
		iatxRequestData
				.addBodyValue(ISBANStringUtils.ajustadorBigDecimalIatx(simulacionPlanVRequestDTO.getImporte(), 9));
		iatxRequestData.addBodyValue(DivisaEnum.PESO.getCodigo());
		iatxRequestData.addBodyValue(
				ISBANStringUtils.formateadorConCerosIzq(simulacionPlanVRequestDTO.getCantidadDeCuotas(), 2));
		iatxRequestData.addBodyValue(simulacionPlanVRequestDTO.getTipoPlanV());

		return iatxRequestData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.tarjetas.dao.ConsultaFinanciacionDAO#
	 * ejecutarFinanciacionTarjeta(ar.com.santanderrio.obp.servicios.tarjetas.
	 * entities.FinanciacionTarjetaView)
	 */
	public ConfirmacionSolicitudPlanV ejecutarFinanciacionTarjeta(DatosConfirmacionFinanciacionTarjetaDTO datos)
			throws DAOException {
		LOGGER.info("Inicia ejecucion de financiacion de tarjeta");
		String tokenFirmado = tokenSigner.obtenerTokenFirmado(CONFIRMAR_SOLICITUD_PLAN_V_TOKEN_FACTORY_CODE, datos);
		ConfirmacionSolicitudPlanV confirmacionSolicitudPlanV = consumirConfirmarSolicitudPlanV(tokenFirmado);
		LOGGER.info("Código retorno WS confirmarSolicitudPlanV: {}", confirmacionSolicitudPlanV.getCodigoRespuesta());
		return confirmacionSolicitudPlanV;
	}

	/**
	 * Consumir ejecutar financiacion tarjeta.
	 *
	 * @author Manuel.Vargas B041299
	 * @param tokenFirmado
	 *            the token firmado
	 * @return the informacion plan V
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConfirmacionSolicitudPlanV consumirConfirmarSolicitudPlanV(String tokenFirmado) throws DAOException {
		PlanVService planVService = null;
		try {
			planVService = gestorPlanV.obtenerPort();
			ConfirmacionSolicitudPlanV confirmarSolicitudPlanVRespuesta = planVService
					.confirmarSolicitudPlanV(tokenFirmado);
			int codigoRespuesta = Integer.parseInt(confirmarSolicitudPlanVRespuesta.getCodigoRespuesta());
			LOGGER.info("El ws planv#confirmarSolicitudPlanV respondie con codigo {}", codigoRespuesta);
			if (OK_CODIGO_RETORNO != codigoRespuesta) {
				throw new DAOException();
			}
			return confirmarSolicitudPlanVRespuesta;
		} catch (javax.xml.ws.soap.SOAPFaultException sfe) {
			LOGGER.error("Error al consumir el ws, {}, con codigo {}", sfe.getMessage(), sfe.getFault().getFaultCode(),
					sfe);
			throw new DAOException(sfe, sfe.getMessage(), sfe.getFault().getFaultCode());
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			gestorPlanV.liberarPort(planVService);
		}
	}
}
