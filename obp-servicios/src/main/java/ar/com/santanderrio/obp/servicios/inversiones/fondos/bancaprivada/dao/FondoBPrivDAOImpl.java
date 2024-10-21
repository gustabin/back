/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.funds.FundApiErrors;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApiConstants;
import ar.com.santanderrio.obp.servicios.api.funds.entities.redemption.ConfirmRedemptionRequestEntity;
import ar.com.santanderrio.obp.servicios.api.funds.entities.redemption.ConfirmRedemptionResponseDataEntity;
import ar.com.santanderrio.obp.servicios.api.funds.entities.redemption.SimulateRedemptionRequestEntity;
import ar.com.santanderrio.obp.servicios.api.funds.entities.redemption.SimulateRedemptionResponseDataEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaBancaPrivada;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.constants.BPFondosOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.model.DatosBuscadosCuentaOperativa;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class FondoBPrivDAOImpl.
 *
 * @author marcelo.ruiz
 */
@Component
@TargetSystem(DataBase.BPRIV)
public class FondoBPrivDAOImpl extends BaseDatoDAOImpl implements FondoBPrivDAO {

	@Autowired
	private FundsApi fundsApi;

	@Autowired
	private SesionParametros sessionParametros;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FondoBPrivDAOImpl.class);

	/** codigo de la operacion. */
	private static final String IN_TIPO = "P_TIPO";
	/** cuenta seleccionada. */
	private static final String IN_CUENTA = "P_CUENTA";

	/** codigo del fondo seleccionado. */
	private static final String IN_ESPECIE = "P_ESPECIE";

	/** codigo del fondo destino seleccionado. */
	private static final String IN_ESPECIE_DESTINO = "P_ESPECIE_DESTINO";

	/** moneda del fondo. */
	private static final String IN_MONEDA = "P_MONEDA";
	/** . */
	private static final String IN_CUOTAS_PARTES = "P_CUOTAS_PARTES";

	/** importe a invertir. */
	private static final String IN_CAPITAL = "P_CAPITAL";

	/** usuario racf. */
	private static final String IN_USUARIO = "P_USUARIO";

	/** pass racf. */
	private static final String IN_PASSWORD = "P_PASSWORD";

	/** The Constant IN_P_DENTRO_DEL_PERFIL. */
	private static final String P_DENTRO_DEL_PERFIL = "P_DENTRO_DEL_PERFIL";

	/** Constante COD_RETORNO_OK. */
	private static final BigDecimal COD_RETORNO_OK = BigDecimal.valueOf(0);

	/** The Constant COD_ERROR_GENERICO. */
	private static final BigDecimal COD_ERROR_GENERICO = BigDecimal.valueOf(24304);

	/** The Constant SALDO_INSUFICIENTE. */
	private static final BigDecimal SALDO_INSUFICIENTE = BigDecimal.valueOf(24401);
	
	/** The Constant SALDO_NO_DISPONIBLE. */
	private static final BigDecimal SALDO_NO_DISPONIBLE = BigDecimal.valueOf(24402);

	/** The Constant HORARIO_FINALIZADO. */
	private static final BigDecimal HORARIO_FINALIZADO = BigDecimal.valueOf(24500);

	/** The Constant IMPORTE_NETO_MENOR_AL_MINIMO. */
	private static final BigDecimal IMPORTE_NETO_MENOR_AL_MINIMO = BigDecimal.valueOf(24403);

	/** The Constant TIME_OUT. */
	private static final BigDecimal TIME_OUT = BigDecimal.valueOf(24400);
	
	/** The Constant CUENTA_BLOQUEADA. */
	private static final BigDecimal CUENTA_BLOQUEADA = BigDecimal.valueOf(24303);;

	/** SCHEMA. */
	private static final String CONTRATO_SCHEMA = "BCAINV";

	/** PACKAGE contrato. */
	private static final String CONTRATO_PACKAGE = "PKG_BP_ONLINE_BANKING";

	/** PROCEDURE SIMULAR. */
	private static final String CONTRATO_SIMULAR = "simular_fondos";

	/** The Constant CONTRATO_SUSCRIBIR. */
	private static final String CONTRATO_SUSCRIBIR = "ejecutar_fondos";

	/** The Constant OUT_NUM_ORDEN. */
	private static final String OUT_NUM_ORDEN = "P_NUM_ORDEN";

	/** The Constant OUT_NUM_CERTIFICADO. */
	private static final String OUT_NUM_CERTIFICADO = "P_NUM_CERTIFICADO_FM";

	/** The Constant OUT_CUOTAS_PARTES. */
	private static final String OUT_CUOTAS_PARTES = "P_CUOTAS_PARTES_FM";

	/** The Constant OUT_CAPITAL. */
	private static final String OUT_CAPITAL = "P_CAPITAL_FM";

	/** The Constant OUT_DENTRO_DEL_PERFIL. */
	private static final String OUT_DENTRO_DEL_PERFIL = "P_DENTRO_DEL_PERFIL";

	/** The Constant OUT_DISCLAIMER. */
	private static final String OUT_DISCLAIMER = "P_DISCLAIMER";

	/** The Constant OUT_COTIZACION. */
	private static final String OUT_COTIZACION = "P_COTIZACION";

	/** The Constant OUT_COD_RETORNO. */
	private static final String OUT_COD_RETORNO = "P_COD_RETORNO";

	/** The Constant OUT_DESCRIPCION. */
	private static final String OUT_DESCRIPCION = "P_DESCRIPCION";
	
	/** The Constant OUT_DESCRIPCION. */
	private static final String OUT_RETORNO = "P_RETORNO";

	private static final String BRANCH_BANCA_PRIVADA = "250";

	private static final String IDENTIFICADOR_MONEDA_PESOS = "ARG";

	/**
	 * {@inheritDoc}
	 * 
	 * @throws DAOException
	 */
	@Override
	public SimulacionFondoBancaPrivadaOutEntity simularSuscripcionBPriv(SimulacionFondoBancaPrivadaInEntity suscripcionInEntity) throws DAOException {
		return ejecutarOperacionSimulacion(suscripcionInEntity, BPFondosOperaciones.SIMULACION_SUSCRIPCION);
	}

	@Override
	public SimulacionFondoBancaPrivadaOutEntity simularRescateBPriv(SimulacionFondoBancaPrivadaInEntity rescateInEntity) throws DAOException {
		try {
			String nup = rescateInEntity.getCliente().getNup();
			if(fundsApi.checkBouncerAccess(FundsApiConstants.Paths.REDEMPTIONS_BFF, FundsApiConstants.Bouncer.ACCESS, nup)) {
				LOGGER.info("Inicia simulacion de rescate BP por medio del bff de Rescates para el nup {}", nup);
				return simulacionRescateBFFRedemptions(rescateInEntity);
			} else {
				LOGGER.info("Inicia simulacion de rescate BP por medio del servicio de IATX para el nup {}", nup);
				return ejecutarOperacionSimulacion(rescateInEntity, BPFondosOperaciones.SIMULACION_RESCATE);
			}
		} catch (SaldoInsuficienteException ex) {
			throw new SaldoInsuficienteException();
		} catch (DAOException ex) {
			throw new DAOException(ex, ex.getMessage());
		}
	}

	@Override
	public SimulacionFondoBancaPrivadaOutEntity simularTransferenciaBPriv(SimulacionFondoBancaPrivadaInEntity transferirInEntity) throws DAOException {
		return ejecutarOperacionSimulacion(transferirInEntity, BPFondosOperaciones.SIMULACION_TRANSFERENCIA);
	}

	@Override
	public EjecucionFondoBancaPrivadaOutEntity suscribir(EjecucionFondoBancaPrivadaInEntity entity) throws DAOException {
		return ejecutarOperacion(entity, BPFondosOperaciones.SUSCRIPCION);
	}

	@Override
	public EjecucionFondoBancaPrivadaOutEntity rescatar(EjecucionFondoBancaPrivadaInEntity entity) throws DAOException {
		try {
			if(fundsApi.checkBouncerAccess(FundsApiConstants.Paths.REDEMPTIONS_BFF, FundsApiConstants.Bouncer.ACCESS, entity.getCliente().getNup())) {
				LOGGER.info("Inicia confirmacion de rescate BP por medio del bff de Rescates para el nup {}",
						entity.getCliente().getNup());
				return confirmacionRescateBFFRedemptions();
			} else {
				LOGGER.info("Inicia confirmacion de rescate BP por medio del servicio de IATX para el nup {}",
						entity.getCliente().getNup());
				return ejecutarOperacion(entity, BPFondosOperaciones.RESCATE);
			}
		} catch (DAOException ex) {
			throw new DAOException(ex, ex.getMessage());
		}
	}

	@Override
	public EjecucionFondoBancaPrivadaOutEntity transferir(EjecucionFondoBancaPrivadaInEntity entity) throws DAOException {
		return ejecutarOperacion(entity, BPFondosOperaciones.TRANSFERENCIA);
	}

	/**
	 * Armo el objeto respuesta con el mapa que retorno la ejecucion del SP.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the simular suscripcion B priv out
	 */
	private SimulacionFondoBancaPrivadaOutEntity parsearRespuesta(Map<String, Object> respuesta) {
		SimulacionFondoBancaPrivadaOutEntity rtaSimulacion = new SimulacionFondoBancaPrivadaOutEntity();
		rtaSimulacion.setNumeroOrden((String) respuesta.get(OUT_NUM_ORDEN));
		rtaSimulacion.setNumeroCertificado((String) respuesta.get(OUT_NUM_CERTIFICADO));
		rtaSimulacion.setCuotasPartes((String) respuesta.get(OUT_CUOTAS_PARTES));
		rtaSimulacion.setCapital((BigDecimal) respuesta.get(OUT_CAPITAL));
		rtaSimulacion.setDentroDelPerfil((String) respuesta.get(OUT_DENTRO_DEL_PERFIL));
		rtaSimulacion.setDisclaimer((String) respuesta.get(OUT_DISCLAIMER));
		rtaSimulacion.setCotizacion((String) respuesta.get(OUT_COTIZACION));
		return rtaSimulacion;
	}

	/**
	 * Manejar casos alternativos.
	 *
	 * @param codRetorno
	 *            the cod retorno
	 * @param descError
	 *            the desc error
	 * @throws SaldoInsuficienteException
	 *             the saldo insuficiente exception
	 * @throws FueraDeHorarioException
	 *             the fuera de horario exception
	 * @throws ImporteLimiteException
	 *             the importe limite exception
	 * @throws TimeOutException
	 *             the time out exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void manejarCasosAlternativos(BigDecimal codRetorno, String descError) throws SaldoInsuficienteException,
			FueraDeHorarioException, ImporteLimiteException, TimeOutException, DAOException {
		if (SALDO_INSUFICIENTE.equals(codRetorno)) {
			throw new SaldoInsuficienteException(descError);
		}
		if (SALDO_NO_DISPONIBLE.equals(codRetorno)) {
			throw new SaldoInsuficienteException(descError);
		}
		if (HORARIO_FINALIZADO.equals(codRetorno)) {
			throw new FueraDeHorarioException();
		}
		if (IMPORTE_NETO_MENOR_AL_MINIMO.equals(codRetorno)) {
			throw new ImporteLimiteException(descError);
		}
		if (TIME_OUT.equals(codRetorno)) {
			throw new TimeOutException(descError);
		}
		if (COD_ERROR_GENERICO.equals(codRetorno)) {
			throw new DAOException(descError, codRetorno.toString());
		}
		if (CUENTA_BLOQUEADA.equals(codRetorno)) {
			throw new DAOException(descError, codRetorno.toString());
		}
		throw new DAOException(descError, codRetorno.toString());
	}

	/**
	 * Armo el objeto respuesta con el mapa que retorno la ejecucion del SP
	 * Suscripcion.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the ejecucion fondo out entity
	 */
	private EjecucionFondoBancaPrivadaOutEntity parsearRespuestaSuscribir(Map<String, Object> respuesta) {
		EjecucionFondoBancaPrivadaOutEntity rtaSuscripcion = new EjecucionFondoBancaPrivadaOutEntity();
		rtaSuscripcion.setNroOrden((String) respuesta.get(OUT_NUM_ORDEN));
		rtaSuscripcion.setNroCertificado(respuesta.get(OUT_NUM_CERTIFICADO).toString());
		if (respuesta.get(OUT_CUOTAS_PARTES) != null) {
			rtaSuscripcion.setCuotaPartes(respuesta.get(OUT_CUOTAS_PARTES).toString());
		}
		rtaSuscripcion.setCapital(respuesta.get(OUT_CAPITAL).toString());
		rtaSuscripcion.setDisclaimer((String) respuesta.get(OUT_DISCLAIMER));
		rtaSuscripcion.setCotizacion((String) respuesta.get(OUT_COTIZACION));
		return rtaSuscripcion;
	}

	/**
	 * Manejar error inesperado.
	 *
	 * @param sqle
	 *            the sqle
	 * @param contrato
	 *            the contrato
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void manejarErrorInesperado(Exception sqle, String contrato) throws DAOException {
		LOGGER.error("Error al consumir el store {}.{}.{} ", CONTRATO_SCHEMA, CONTRATO_PACKAGE, contrato, sqle);
		throw new SimulacionDAOException(sqle);
	}

	private SimulacionFondoBancaPrivadaOutEntity ejecutarOperacionSimulacion(SimulacionFondoBancaPrivadaInEntity inEntity, BPFondosOperaciones operacion) throws DAOException {
		SimulacionFondoBancaPrivadaOutEntity simulacion = new SimulacionFondoBancaPrivadaOutEntity();

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_TIPO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ESPECIE, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_MONEDA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CUOTAS_PARTES, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_CAPITAL, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_ESPECIE_DESTINO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_USUARIO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_PASSWORD, Types.VARCHAR));

		parametros.add(new SqlOutParameter(OUT_NUM_ORDEN, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_NUM_CERTIFICADO, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_CUOTAS_PARTES, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_CAPITAL, Types.DECIMAL));
		parametros.add(new SqlOutParameter(OUT_DENTRO_DEL_PERFIL, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_DISCLAIMER, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_COTIZACION, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_RETORNO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_COD_RETORNO, Types.DECIMAL));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_TIPO, operacion.getCodigoOperacion())
				.addValue(IN_CUENTA, inEntity.getCuentaTitulo()).addValue(IN_ESPECIE, inEntity.getEspecie())
				.addValue(IN_MONEDA, inEntity.getMoneda()).addValue(IN_CUOTAS_PARTES, 0)
				.addValue(IN_CAPITAL, inEntity.getCapital()).addValue(IN_ESPECIE_DESTINO, inEntity.getEspecieDestino())
				.addValue(IN_USUARIO, inEntity.getUssRacf())
				.addValue(IN_PASSWORD, inEntity.getPassRacf())
				.addValue(IN_USUARIO, null).addValue(IN_PASSWORD, null);

		LOGGER.info(CONTRATO_SCHEMA + "." + CONTRATO_PACKAGE + "." + CONTRATO_SIMULAR);
		LOGGER.info(IN_TIPO + " : " + inEntity.getCodigoOperacion());
		LOGGER.info(IN_CUENTA + " : " + inEntity.getCuentaTitulo());
		LOGGER.info(IN_ESPECIE + " : " + inEntity.getEspecie());
		LOGGER.info(IN_MONEDA + " : " + inEntity.getMoneda());
		LOGGER.info(IN_CUOTAS_PARTES + " : " + inEntity.getCuotasPartes());
		LOGGER.info(IN_CAPITAL + " : " + inEntity.getCapital().toString());
		LOGGER.info(IN_ESPECIE_DESTINO + " : " + inEntity.getEspecieDestino());
		LOGGER.info(IN_USUARIO + " : " + inEntity.getUssRacf());
		LOGGER.info(IN_PASSWORD + " : " + inEntity.getPassRacf());

		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_SIMULAR, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RETORNO);
			LOGGER.info("{} : {}", OUT_COD_RETORNO, codRetorno);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				simulacion = parsearRespuesta(respuesta);
			} else {
				String descError = (String) respuesta.get(OUT_DESCRIPCION);
				if (HORARIO_FINALIZADO.equals(codRetorno)) {
					throw new FueraDeHorarioException();
				} else {
					throw new DAOException(descError, codRetorno.toString());
				}
			}
		} catch (SQLException sqle) {
			manejarErrorInesperado(sqle, CONTRATO_SIMULAR);
		} catch (UncategorizedSQLException sqle) {
			manejarErrorInesperado(sqle, CONTRATO_SIMULAR);
		}
		return simulacion;
	}

	private SimulacionFondoBancaPrivadaOutEntity simulacionRescateBFFRedemptions(SimulacionFondoBancaPrivadaInEntity entity) throws DAOException {
		String currency = IDENTIFICADOR_MONEDA_PESOS.equals(entity.getMoneda()) ? DivisaEnum.PESO.getCodigo() : DivisaEnum.DOLAR.getCodigo();
		String operativeAccountPrefix = entity.getCuentaTitulo().substring(0,3);
		String operativeAccount = entity.getCuentaTitulo().substring(3);
		try {
			DatosBuscadosCuentaOperativa datosBuscadosCuentaOperativa = obtenerCuentaTituloYTipoCuenta(
					entity.getCliente().getCuentasBancaPrivada(),
					operativeAccount
			);

			SimulateRedemptionRequestEntity request = new SimulateRedemptionRequestEntity();

			request.setNup(entity.getCliente().getNup());
			request.setBranch(BRANCH_BANCA_PRIVADA);
			request.setValue(entity.getCapital().doubleValue());
			request.setFundCode(entity.getCodigoFondo());
			request.setCurrency(currency);
			request.setOperativeAccountPrefix(operativeAccountPrefix);
			request.setOperativeAccount(operativeAccount);
			request.setOperativeAccountType(datosBuscadosCuentaOperativa.getTipoCuenta());
			request.setInvestmentAccount(datosBuscadosCuentaOperativa.getCuentaTitulos());

			SimulateRedemptionResponseDataEntity simulateRedemption = fundsApi.simulateRedemption(request);
			SimulacionFondoBancaPrivadaOutEntity simulacionFondoBancaPrivadaOutEntity = new SimulacionFondoBancaPrivadaOutEntity();

			simulacionFondoBancaPrivadaOutEntity.setCapital((BigDecimal) simulateRedemption.getNetAmount());
			simulacionFondoBancaPrivadaOutEntity.setCuotasPartes(String.valueOf(simulateRedemption.getShareAmount()));
			simulacionFondoBancaPrivadaOutEntity.setNumeroCertificado(simulateRedemption.getCertificateId());

			sessionParametros.setTransactionTokenRedemptionFund(simulateRedemption.getTransactionToken());

			return simulacionFondoBancaPrivadaOutEntity;
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

	private EjecucionFondoBancaPrivadaOutEntity ejecutarOperacion(EjecucionFondoBancaPrivadaInEntity entity, BPFondosOperaciones operacion) throws DAOException {
		LOGGER.info("ejecuta operacion {} para el usuario: {}", operacion, entity.getUsuarioRacf());
		EjecucionFondoBancaPrivadaOutEntity suscripcion = new EjecucionFondoBancaPrivadaOutEntity();
		List<SqlParameter> param = new ArrayList<SqlParameter>();

		param.add(new SqlParameter(IN_TIPO, Types.VARCHAR));
		param.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		param.add(new SqlParameter(IN_ESPECIE, Types.VARCHAR));
		param.add(new SqlParameter(IN_MONEDA, Types.VARCHAR));
		param.add(new SqlParameter(IN_CUOTAS_PARTES, Types.NUMERIC));
		param.add(new SqlParameter(IN_CAPITAL, Types.NUMERIC));
		param.add(new SqlParameter(IN_ESPECIE_DESTINO, Types.VARCHAR));
		param.add(new SqlParameter(IN_USUARIO, Types.VARCHAR));
		param.add(new SqlParameter(IN_PASSWORD, Types.VARCHAR));
		param.add(new SqlOutParameter(OUT_NUM_ORDEN, Types.VARCHAR));
		param.add(new SqlOutParameter(OUT_NUM_CERTIFICADO, Types.INTEGER));
		param.add(new SqlOutParameter(OUT_CUOTAS_PARTES, Types.INTEGER));
		param.add(new SqlOutParameter(OUT_CAPITAL, Types.DECIMAL));
		param.add(new SqlInOutParameter(P_DENTRO_DEL_PERFIL, Types.VARCHAR));
		param.add(new SqlOutParameter(OUT_DISCLAIMER, Types.VARCHAR));
		param.add(new SqlOutParameter(OUT_COTIZACION, Types.INTEGER));
		param.add(new SqlOutParameter(OUT_RETORNO, Types.VARCHAR));
		param.add(new SqlOutParameter(OUT_COD_RETORNO, Types.DECIMAL));
		param.add(new SqlOutParameter(OUT_DESCRIPCION, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(IN_TIPO, operacion.getCodigoOperacion())
				.addValue(IN_CUENTA, entity.getNroCuenta())
				.addValue(IN_ESPECIE, entity.getEspecie())
				.addValue(IN_MONEDA, entity.getMoneda())
				.addValue(IN_CUOTAS_PARTES, null)
				.addValue(IN_CAPITAL, entity.getCapital())
				.addValue(IN_ESPECIE_DESTINO, entity.getEspecieDestino())
				.addValue(P_DENTRO_DEL_PERFIL, entity.getIsPerfilInversion())
				.addValue(IN_USUARIO, entity.getUsuarioRacf())
				.addValue(IN_PASSWORD, entity.getPasswordRacf());



		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_SUSCRIBIR, in,
					param.toArray(new SqlParameter[param.size()]));
			BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_COD_RETORNO);
			LOGGER.info("{} : {}", OUT_COD_RETORNO, codRetorno);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				suscripcion = parsearRespuestaSuscribir(respuesta);
			} else {
				String descError = (String) respuesta.get(OUT_DESCRIPCION);
				manejarCasosAlternativos(codRetorno, descError);
			}
		} catch (SQLException sqle) {
			manejarErrorInesperado(sqle, CONTRATO_SUSCRIBIR);
		} catch (UncategorizedSQLException sqle) {
			manejarErrorInesperado(sqle, CONTRATO_SUSCRIBIR);
		} catch (QueryTimeoutException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} ", CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_SUSCRIBIR,
					sqle);
			throw new TimeOutException("Error de timeOut en query.");
		}

		return suscripcion;
	}

	private EjecucionFondoBancaPrivadaOutEntity confirmacionRescateBFFRedemptions() throws DAOException {
		try {
			String transtactionToken = sessionParametros.getTransactionTokenRedemptionFund();
			ConfirmRedemptionRequestEntity request = new ConfirmRedemptionRequestEntity(transtactionToken);

			ConfirmRedemptionResponseDataEntity confirmationRedemption = fundsApi.confirmRedemption(request);
			EjecucionFondoBancaPrivadaOutEntity out = new EjecucionFondoBancaPrivadaOutEntity();

			out.setCapital(String.valueOf(confirmationRedemption.getNetAmount()));
			out.setCuotaPartes(String.valueOf(confirmationRedemption.getShareAmount()));
			out.setNroCertificado(confirmationRedemption.getCertificateId());

			return out;
		} catch (ApiException ex) {
			throw new DAOException(ex.getErrorResponse().getMessage(), ex.getErrorResponse().getCode());
		}
	}

	private DatosBuscadosCuentaOperativa obtenerCuentaTituloYTipoCuenta(List<CuentaBancaPrivada> cuentas, String cuentaOperativaVinculada) throws DAOException {
		for (CuentaBancaPrivada cuenta: cuentas) {
			Cuenta cuentaOperativa = cuenta.getCuentaOperativa();
			String cuentaFormateada = ISBANStringUtils.formatearNumeroCuenta(cuentaOperativa.getNroCuentaProducto()).replaceAll("/","");
			if(cuentaFormateada.equals(cuentaOperativaVinculada)) {
				DatosBuscadosCuentaOperativa datos = new DatosBuscadosCuentaOperativa();
				datos.setCuentaTitulos(cuentaOperativa.getNroCuentaTit());
				datos.setTipoCuenta(cuentaOperativa.getTipoCuenta());
				return datos;
			}
		}
		throw new DAOException(String.format("[BANCA PRIVADA]: No se pudo determinar la cuenta titulos para la cuenta operativa %s", cuentaOperativaVinculada));
	}
}
