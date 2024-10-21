/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.ConsultaTitularInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPinEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TitularOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.CuentaBloqueadaException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.DisponibleInsuficienteException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.ExcedeLimiteTransferenciaException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.ProblemasAlEfectuarTransferenciaException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.exception.SaldoCuotasParteInsuficienteException;
import ar.com.santanderrio.obp.servicios.inversiones.cuentatitulo.dao.CuentaTituloDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import oracle.jdbc.OracleTypes;

/**
 * The Class CuentaSaldoDAOImpl.
 *
 * @author pablo.d.gargaglione
 */

@Component
@TargetSystem(DataBase.BPRIV)
public class CuentaSaldoDAOImpl extends BaseDatoDAOImpl implements CuentaSaldoDAO {

	
	/** The Constant EXCEDE_LIMITE_TRANSFERENCIA. */
	private static final BigDecimal EXCEDE_LIMITE_TRANSFERENCIA = BigDecimal.valueOf(24302);
	
	/** The Constant CUENTA_BLOQUEADA. */
	private static final BigDecimal CUENTA_BLOQUEADA = BigDecimal.valueOf(24303);
	
	/** The Constant DISPONIBLE_INSUFICIENTE. */
	private static final BigDecimal DISPONIBLE_INSUFICIENTE = BigDecimal.valueOf(24301);
	
	/** The Constant SALDO_CUOTAS_PARTE_INSUFICIENTE. */
	private static final BigDecimal SALDO_CUOTAS_PARTE_INSUFICIENTE = BigDecimal.valueOf(24402);

	/** The Constant IMPORTE_NETO_MENOR_MINIMO. */
	private static final BigDecimal IMPORTE_NETO_MENOR_MINIMO = BigDecimal.valueOf(24403);
	
	/** The Constant PROBLEMAS_EFECTUAR_TRANSFERENCIA. */
	private static final BigDecimal PROBLEMAS_EFECTUAR_TRANSFERENCIA = BigDecimal.valueOf(24300);

	/** The Constant HORARIO_FINALIZADO. */
	private static final BigDecimal HORARIO_FINALIZADO = BigDecimal.valueOf(24500);	
	
	/** The Constant COD_ERROR_GENERICO. */
	private static final BigDecimal COD_ERROR_GENERICO = BigDecimal.valueOf(24304);
	
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaTituloDAOImpl.class);

	/** Constante COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "OK";

	/** The Constant IN_CUENTA. */
	private static final String IN_CUENTA = "P_CUENTA";
	
	/** The Constant IN_DIVISA. */
	private static final String IN_DIVISA ="P_DIVISA";

	/** The Constant IN_FECHA_DESDE. */
	private static final String IN_FECHA_DESDE_2 = "P_FECHAVALOR_DESDE";
	
	/** The Constant IN_FECHA_HASTA. */
	private static final String IN_FECHA_HASTA = "P_FECHA_HASTA"; 

	
	/** The Constant IN_FECHA_DESDE. */
	private static final String IN_FECHA_DESDE = "P_FECHA_DESDE";
	
	/** The Constant IN_FECHA_HASTA_2. */
	private static final String IN_FECHA_HASTA_2 = "P_FECHAVALOR_HASTA";
	
	/** The Constant IN_CANAL. */
	private static final String IN_CANAL = "P_CANAL";

	/** The Constant IN_USUARIO. */
	private static final String IN_USUARIO = "P_USUARIO";

	/** The Constant IN_PASSWORD. */
	private static final String IN_CONTRA = "P_PASSWORD";

	/** The Constant LOAD_SALDOS. */
	private static final String LOAD_SALDOS = "load_saldos";
	
	/** TheConstant LOAD_MOVIMIENTOS.*/
	private static final String LOAD_MOVIMIENTOS = "load_movimientos";

	/** The Constant CUENTA_SALDO_SCHEMA. */
	private static final String CUENTA_SALDO_SCHEMA = "BCAINV";

	/** The Constant CUENTA_SALDO_PACKAGE. */
	private static final String CUENTA_SALDO_PACKAGE = "PKG_BP_ONLINE_BANKING";

	/** The Constant OUT_RESULTADO. */
	private static final Object OUT_RESULTADO = "P_RETORNO";
	
	/** The Constant P_COD_RETORNO. */
	private static final Object P_COD_RETORNO ="P_COD_RETORNO";

	/** The Constant OUT_CURSOR. */
	private static final Object OUT_CURSOR = "P_CURSOR";

	/** The Constant OUT_DESCRIPCION. */
	private static final Object OUT_DESCRIPCION = "P_DESCRIPCION";

	/** The Constant CUENTA. */
	private static final Object CUENTA = "CUENTA";
	
	/** The Constant DIVISA. */
	private static final Object DIVISA = "DIVISA";
	
	/** The numero movimiento. */
	private static final Object NUMEROMOV ="NROMOV";
	
	/** The numero orden. */
	private static final Object NUMERO_ORDEN ="P_NUM_ORDEN";
	
	/** The numero orden. */
	private static final Object P_ESTADO ="P_ESTADO";
	
	/** The Fecha Valor. */
	private static final Object FECHAVALOR ="FECHA_VALOR";
	
	/** The Fecha Operacion. */
	private static final Object FECHAOPERACION ="FECHA_OPER";
	
	/** The consepto. */
	private static final Object CONCEPTO ="CONCEPTO";
	
	/** The texto del apunte. */
	private static final Object TEXTODELAPUNTE ="TEXTO_DEL_APUNTE";
	
	/** The saldo inicio. */
	private static final Object SALDOINICIO ="SALDO_INICIO";
	
	/** The ingresos. */
	private static final Object INGRESOS ="INGRESOS";
	
	/** The egresos. */
	private static final Object EGRESOS ="EGRESOS";
	
	/** The saldo final. */
	private static final Object SALDOFINAL ="SALDO_FINAL";
	
	/** The usuario. */
	private static final Object USUARIO ="USUARIO";
	
	/** The indicador movimiento. */
	private static final Object INDMOV ="INDICADOR_MOVIMIENTO";
	

	/** The Constant DESCRIPCION. */
	private static final Object DESCRIPCION = "DESCRIPCION";

	/** The Constant SUCURSAL. */
	private static final Object SUCURSAL = "SUCURSAL";

	/** The Constant ASESOR. */
	private static final Object ASESOR = "ASESOR";

	/** The Constant FECHA. */
	private static final Object FECHA = "FECHA";

	/** The Constant C_AHORRO_$. */
	private static final Object C_AHORRO_$ = "C.Ahorro $";

	/** The Constant C_AHORRO_U$S. */
	private static final Object C_AHORRO_U$S = "C.Ahorro U$S";
	
	/** The Constant CODIGO_LOGGER. */
	private static final String CODIGO_LOGGER = "{} : {}";	
		
	/** The Constant IN_USUARIO_ALTAIR. */
	private static final String IN_USUARIO_ALTAIR = "P_USR_ALTAIR";
	
	/** The Constant IN_PASSWORD_ALTAIR. */
	private static final String IN_CONTRA_ALTAIR = "P_PASS_ALTAIR";
	
	/** The Constant IN_CUENTA_ALTAIR. */
	private static final String IN_CUENTA_ALTAIR = "P_CUENTA_ALTAIR";
	
	/** The Constant SUCURSAL_ALTAIR. */
	private static final String IN_SUCURSAL_ALTAIR = "P_SUCURSAL_ALTAIR";
	
	/** The Constant LOAD_SALDOS. */
	private static final String LOAD_TITULAR = "load_titular";
			
	/** The Constant EJECUTAR_TRANSFERENCIA. */
	private static final String EJECUTAR_TRANSFERENCIA = "ejecutar_transferencia";
	
	/** The Constant IN_SUCURSAL_SECUNDARIA. */
	private static final String IN_SUCURSAL_SECUNDARIA = "P_SUCURSAL_SECUNDARIA";
	
	/** The Constant IN_CUENTA_SECUNDARIA. */
	private static final String IN_CUENTA_SECUNDARIA = "P_CUENTA_SECUNDARIA";
	
	/** The Constant IN_DIVISA_MOVIMIENTO. */
	private static final String IN_DIVISA_MOVIMIENTO = "P_DIVISA_MOVIMIENTO";

	/** The Constant IN_IMPORTE_MOVIMIENTO. */
	private static final String IN_IMPORTE_MOVIMIENTO = "P_IMPORTE_MOVIMIENTO";
	
	/** The Constant IN_TIPO_MOVIMIENTO. */
	private static final String IN_TIPO_MOVIMIENTO = "P_TIPO_MOVIMIENTO";
	
	/** The Constant IN_NOMBRE_DESTINO. */
	private static final String IN_NOMBRE_DESTINO = "P_NOMBRE_DESTINO";
	
	/** The Constant OUT_NOMBRE. */
	private static final String OUT_NOMBRE = "P_NOMBRE";
	
	/** The Constant OUT_APELLIDO. */
	private static final String OUT_APELLIDO = "P_APELLIDO";
	
	/** The Constant OUT_APELLIDO. */
	private static final String OUT_CODIGO_RETORNO = "P_COD_RETORNO";
	
	/** The Constant OUT_DESCRIPCION_TRANSACCION. */
	private static final String OUT_DESCRIPCION_TRANSACCION = "P_DESCRIPCION";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CuentaSaldoOutEntity verSaldos(CuentaSaldoInEntity in) {
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		LOGGER.info("Obteniendo Saldo de las cuentas de banca privada de un cliente para la cuenta: {}",
				in.getCuenta());
		try {
			List<RtaLoadSaldo> rta = ejecutarStoredProcedureBusquedaSaldo(in);
			cuentaSaldoOutEntity.setRespuesta(rta);

		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			return null;
		}
		return cuentaSaldoOutEntity;
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO#verSaldosCuentasBancaPrivada(ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity)
	 */
	@Override
	public CuentaSaldoOutEntity verSaldosCuentasBancaPrivada(CuentaSaldoInEntity in) throws DAOException {
		CuentaSaldoOutEntity cuentaSaldoOutEntity = new CuentaSaldoOutEntity();
		LOGGER.info("Obteniendo Saldo de las cuentas de banca privada de un cliente para la cuenta: {}",
				in.getCuenta());
		List<RtaLoadSaldo> rta = ejecutarStoredProcedureBusquedaSaldo(in);
		cuentaSaldoOutEntity.setRespuesta(rta);

		return cuentaSaldoOutEntity;
	}

	/**
	 * Ejecutar stored procedure busqueda saldo.
	 *
	 * @param inEntity
	 *            the in
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	private List<RtaLoadSaldo> ejecutarStoredProcedureBusquedaSaldo(CuentaSaldoInEntity inEntity) throws DAOException {

		List<RtaLoadSaldo> rtaCuentaSaldos = new ArrayList<RtaLoadSaldo>();
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();

		parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_FECHA_DESDE, Types.DATE));
		parametros.add(new SqlParameter(IN_FECHA_HASTA, Types.DATE));
		parametros.add(new SqlParameter(IN_CANAL, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_USUARIO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRA, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) P_COD_RETORNO, Types.INTEGER));
		parametros.add(new SqlOutParameter((String) OUT_DESCRIPCION_TRANSACCION, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) OUT_CURSOR, OracleTypes.CURSOR));

		LOGGER.info("SP : {}, {}, {}", CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_SALDOS);
		LOGGER.info(CODIGO_LOGGER, IN_CUENTA, inEntity.getCuenta());
		LOGGER.info(CODIGO_LOGGER, IN_FECHA_DESDE, inEntity.getFechaDesde());
		LOGGER.info(CODIGO_LOGGER, IN_FECHA_HASTA, inEntity.getFechaHasta());
		LOGGER.info(CODIGO_LOGGER, IN_CANAL, inEntity.getCanal());
		LOGGER.info(CODIGO_LOGGER, IN_USUARIO, inEntity.getUsuario());

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, inEntity.getCuenta())
				.addValue(IN_FECHA_DESDE, inEntity.getFechaDesde()).addValue(IN_FECHA_HASTA, inEntity.getFechaHasta())
				.addValue(IN_CANAL, inEntity.getCanal()).addValue(IN_USUARIO, inEntity.getUsuario())
				.addValue(IN_CONTRA, inEntity.getPass());

		try {
			Map<String, Object> respuesta = consultar(CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_SALDOS, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				LOGGER.info("Cod. retorno : {}", codRetorno);
				LOGGER.info("rta: " + respuesta.get(OUT_CURSOR));
				List<Object> cuentaSaldos = (List<Object>) respuesta.get(OUT_CURSOR);
				for (int i = 0; i < cuentaSaldos.size(); i++) {
					rtaCuentaSaldos.add(crearCuentaSaldo((Map<String, Object>) cuentaSaldos.get(0)));
				}
			} else {
				LOGGER.debug("Cod. retorno : {}", codRetorno);
				throw new DAOException((String) respuesta.get(OUT_DESCRIPCION));
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_SALDOS, sqle);
			throw new DAOException(sqle);
		} catch (Exception sqle) {
			LOGGER.error("Error al consumir el store :org.springframework.dao.QueryTimeoutException.",
					CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_SALDOS, sqle);
			throw new DAOException(sqle);
		}
		return rtaCuentaSaldos;
	}

	/**
	 * Crear cuenta saldo.
	 *
	 * @param map
	 *            the map
	 * @return the rta load saldo
	 */
	private RtaLoadSaldo crearCuentaSaldo(Map<String, Object> map) {

		LOGGER.info(CODIGO_LOGGER, CUENTA, map.get(CUENTA));
		LOGGER.info(CODIGO_LOGGER, DESCRIPCION, map.get(DESCRIPCION));
		LOGGER.info(CODIGO_LOGGER, SUCURSAL, map.get(SUCURSAL));
		LOGGER.info(CODIGO_LOGGER, ASESOR, map.get(ASESOR));
		LOGGER.info(CODIGO_LOGGER, FECHA, map.get(FECHA));
		LOGGER.info(CODIGO_LOGGER, C_AHORRO_$, map.get(C_AHORRO_$));
		LOGGER.info(CODIGO_LOGGER, C_AHORRO_U$S, map.get(C_AHORRO_U$S));
		
		return new RtaLoadSaldo(map.get(CUENTA).toString(), map.get(DESCRIPCION).toString().trim(),
				map.get(SUCURSAL).toString(), map.get(ASESOR).toString(), map.get(FECHA).toString(),
				map.get(C_AHORRO_$).toString(), map.get(C_AHORRO_U$S).toString());

	}

	
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO#verMovimientosCuentaBancaPrivada(ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.MovimientosCuentaBPinEntity)
	 */
	@Override
	public MovimientosCuentaBPOutEntity verMovimientosCuentaBancaPrivada(MovimientosCuentaBPinEntity in)throws DAOException {
		MovimientosCuentaBPOutEntity movimientosCuentaBPOutEntity = new MovimientosCuentaBPOutEntity();
		
		LOGGER.info("Obteniendo Movimientos de banca privada de un cliente para la cuenta: {} y divisa : {}: ",
				in.getCuenta(), in.getDivisa());
		List<RtaMovimientosCuentaBP> rta = ejecutarStoredProcedureVerMovimientosCuentaBP(in);
		movimientosCuentaBPOutEntity.setRespuesta(rta);
		return movimientosCuentaBPOutEntity;
			
	}
	

	
	
	/**
	 * Crear movimientos cuenta banca privada.
	 *
	 * @param map
	 *            the map
	 * @return the rta load saldo
	 */
	private RtaMovimientosCuentaBP crearMovimientoCuentaBP(Map<String, Object> map) {

		LOGGER.info(CODIGO_LOGGER, SUCURSAL, map.get(SUCURSAL));
		LOGGER.info(CODIGO_LOGGER, CUENTA, map.get(CUENTA));
		LOGGER.info(CODIGO_LOGGER, DIVISA, map.get(DIVISA));
		LOGGER.info(CODIGO_LOGGER, NUMEROMOV, map.get(NUMEROMOV));
		LOGGER.info(CODIGO_LOGGER, FECHAVALOR, map.get(FECHAVALOR));
		LOGGER.info(CODIGO_LOGGER, FECHAOPERACION, map.get(FECHAOPERACION));
		LOGGER.info(CODIGO_LOGGER, CONCEPTO, map.get(CONCEPTO));		
		LOGGER.info(CODIGO_LOGGER, TEXTODELAPUNTE, map.get(TEXTODELAPUNTE));
		LOGGER.info(CODIGO_LOGGER, SALDOINICIO, map.get(SALDOINICIO));
		LOGGER.info(CODIGO_LOGGER, INGRESOS, map.get(INGRESOS));
		LOGGER.info(CODIGO_LOGGER, EGRESOS, map.get(EGRESOS));
		LOGGER.info(CODIGO_LOGGER, SALDOFINAL, map.get(SALDOFINAL));
		LOGGER.info(CODIGO_LOGGER, USUARIO, map.get(USUARIO));
		LOGGER.info(CODIGO_LOGGER, INDMOV, map.get(INDMOV));
		
		return new RtaMovimientosCuentaBP.RtaMovimientosCuentaBPBuilder().sucursal(map.get(SUCURSAL).toString())
																		 .cuenta(map.get(CUENTA).toString().trim())
																		 .divisa(map.get(DIVISA).toString())
																		 .numeroMov(map.get(NUMEROMOV).toString())
																		 .fechaValor(map.get(FECHAVALOR).toString())
																		 .fechaOperacion(map.get(FECHAOPERACION).toString())
																		 .concepto(map.get(CONCEPTO).toString())
																		 .textoDelApunte(map.get(TEXTODELAPUNTE) == null ? StringUtils.EMPTY : map.get(TEXTODELAPUNTE).toString())
																		 .saldoInicio(map.get(SALDOINICIO).toString())
																		 .ingresos(map.get(INGRESOS).toString())
																		 .egresos(map.get(EGRESOS).toString())
																		 .saldoFinal(map.get(SALDOFINAL).toString())
																		 .indMov(map.get(INDMOV).toString())
																		 .build();
																		 
	}
	
	
	
	
	/**
	 * Ejecutar stored procedure ver movimientos banca privada.
	 *
	 * @param inEntity
	 *            the in
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	public List<RtaMovimientosCuentaBP> ejecutarStoredProcedureVerMovimientosCuentaBP(MovimientosCuentaBPinEntity inEntity)throws DAOException {
		
		List<RtaMovimientosCuentaBP> rtaMovimientosCuentaBPs = new ArrayList<RtaMovimientosCuentaBP>();
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();

		parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_DIVISA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_FECHA_DESDE_2, Types.DATE));
		parametros.add(new SqlParameter(IN_FECHA_HASTA_2, Types.DATE));
		parametros.add(new SqlParameter(IN_CANAL, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_USUARIO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRA, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) P_COD_RETORNO, Types.INTEGER));
		parametros.add(new SqlOutParameter((String) OUT_DESCRIPCION, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) OUT_CURSOR, OracleTypes.CURSOR));
		
		LOGGER.info("SP : {}, {}, {}", CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_MOVIMIENTOS);
		LOGGER.info(CODIGO_LOGGER, IN_CUENTA, inEntity.getCuenta());
		LOGGER.info(CODIGO_LOGGER, IN_DIVISA, inEntity.getDivisa());
		LOGGER.info(CODIGO_LOGGER, IN_FECHA_DESDE_2, inEntity.getFechaDesde());
		LOGGER.info(CODIGO_LOGGER, IN_FECHA_HASTA_2, inEntity.getFechaHasta());
		LOGGER.info(CODIGO_LOGGER, IN_CANAL, inEntity.getCanal());
		LOGGER.info(CODIGO_LOGGER, IN_USUARIO, inEntity.getUsuario());
		
		
		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, inEntity.getCuenta()).addValue(IN_DIVISA, inEntity.getDivisa())
				.addValue(IN_FECHA_DESDE_2, inEntity.getFechaDesde()).addValue(IN_FECHA_HASTA_2, inEntity.getFechaHasta())
				.addValue(IN_CANAL, inEntity.getCanal()).addValue(IN_USUARIO, inEntity.getUsuario())
				.addValue(IN_CONTRA, inEntity.getPass());

		try {
			Map<String, Object> respuesta = consultar(CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_MOVIMIENTOS, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				LOGGER.info("Cod. retorno : {}", codRetorno);
				LOGGER.info("rta: " + respuesta.get(OUT_CURSOR));
				List<Object> movimientosCuentaBP = (List<Object>) respuesta.get(OUT_CURSOR);
				for (int i = 0; i < movimientosCuentaBP.size(); i++) {
					rtaMovimientosCuentaBPs.add(crearMovimientoCuentaBP((Map<String, Object>) movimientosCuentaBP.get(i)));
				}
			} else {
				LOGGER.debug("Cod. retorno : {}", codRetorno);
				throw new DAOException((String) respuesta.get(OUT_DESCRIPCION));
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_MOVIMIENTOS, sqle);
			throw new DAOException(sqle);
		} catch (Exception sqle) {
			LOGGER.error("Error al consumir el store :org.springframework.dao.QueryTimeoutException.",
					CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_MOVIMIENTOS, sqle);
			throw new DAOException(sqle);
		}
		return rtaMovimientosCuentaBPs;
		
	}

	
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO#consultaTitular(ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.ConsultaTitularInEntity)
	 */
	@Override
	public TitularOutEntity consultaTitular(ConsultaTitularInEntity in) throws DAOException {
		TitularOutEntity titularOutEntity = new TitularOutEntity();
		LOGGER.info("Obteniendo el titular de la cuenta destino de la transferencia para la cuenta: {}",
				in.getCuentaAltair());
		try {
			RtaLoadTitular rta = ejecutarStoredLoadTitular(in);
			titularOutEntity.setRespuesta(rta);

		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return titularOutEntity;
	}


	
	
	/**
	 * Ejecuta el store LOAD_TITULAR.
	 *
	 * @param inEntity
	 *            the in
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */

	private RtaLoadTitular ejecutarStoredLoadTitular(ConsultaTitularInEntity inEntity) throws DAOException {

		RtaLoadTitular rtaLoadTitular = new RtaLoadTitular("","");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_USUARIO_ALTAIR, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRA_ALTAIR, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_SUCURSAL_ALTAIR, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CUENTA_ALTAIR, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CANAL, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_APELLIDO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_NOMBRE, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) P_COD_RETORNO, Types.DECIMAL));
		parametros.add(new SqlOutParameter((String) OUT_DESCRIPCION, Types.VARCHAR));
		
		
		LOGGER.info("SP : {}, {}, {}", CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_TITULAR);
		LOGGER.info(CODIGO_LOGGER, IN_CUENTA_ALTAIR, inEntity.getCuentaAltair());
		LOGGER.info(CODIGO_LOGGER, IN_CANAL, inEntity.getCanal());
		LOGGER.info(CODIGO_LOGGER, IN_USUARIO_ALTAIR, inEntity.getUsuario());
		LOGGER.info(CODIGO_LOGGER, IN_CONTRA_ALTAIR, inEntity.getPass());

		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(IN_CUENTA_ALTAIR, inEntity.getCuentaAltair())
				.addValue(IN_SUCURSAL_ALTAIR, inEntity.getSucursalAltair()).addValue(IN_CANAL, inEntity.getCanal())
				.addValue(IN_USUARIO_ALTAIR, inEntity.getUsuario()).addValue(IN_CONTRA_ALTAIR, inEntity.getPass());

		try {
			Map<String, Object> respuesta = consultar(CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_TITULAR, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			LOGGER.info("Cod. retorno : {}", codRetorno);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				rtaLoadTitular = parsearRespuesta(respuesta);			
			} else {
				LOGGER.debug("Cod. retorno : {}", codRetorno);
				throw new DAOException((String) respuesta.get(OUT_DESCRIPCION));
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_TITULAR, sqle);
			throw new DAOException(sqle);
		} catch (Exception sqle) {
			LOGGER.error("Error al consumir el store :org.springframework.dao.QueryTimeoutException.",
					CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, LOAD_TITULAR, sqle);
			throw new DAOException(sqle);
		}
		return rtaLoadTitular;
	}

	
	/**
	 * Armo el objeto respuesta con el mapa que retorno la ejecucion del SP.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the Titular
	 */
	private RtaLoadTitular parsearRespuesta(Map<String, Object> respuesta) {
		RtaLoadTitular rtaLoadTitular = new RtaLoadTitular("","");
		rtaLoadTitular.setNombre((String) respuesta.get(OUT_NOMBRE));		
		rtaLoadTitular.setApellido((String) respuesta.get(OUT_APELLIDO));  
		return rtaLoadTitular;
	}
	
	
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.dao.CuentaSaldoDAO#ejecutarTransferenciaBancaPrivada(ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.TransferenciaBPInEntity)
	 */
	@Override
	public TransferenciaBPOutEntity ejecutarTransferenciaBancaPrivada(TransferenciaBPInEntity in) throws DAOException {

		TransferenciaBPOutEntity transferenciaBPOutEntity = new TransferenciaBPOutEntity();
		LOGGER.info("Ejecuta la transferencia para la cuenta: {} y nombre destino: {}",
				in.getCuenta(), in.getNombreDestino());
		
			RtaTransferenciaBP rta = ejecutarStoredEjecutarTransferencia(in);
			transferenciaBPOutEntity.setRespuesta(rta);
		return transferenciaBPOutEntity;
		
	}
	
		
	
	/**
	 * Ejecuta el store ejecutar_transferencia.
	 *
	 * @param inEntity
	 *            the in
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
		
	private RtaTransferenciaBP ejecutarStoredEjecutarTransferencia(TransferenciaBPInEntity inEntity) throws DAOException {

		RtaTransferenciaBP rtaTransferenciaBP = new RtaTransferenciaBP("","");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CUENTA_SECUNDARIA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_SUCURSAL_SECUNDARIA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_DIVISA_MOVIMIENTO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_IMPORTE_MOVIMIENTO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_TIPO_MOVIMIENTO, Types.VARCHAR));		
		parametros.add(new SqlParameter(IN_CANAL, Types.VARCHAR));	
		parametros.add(new SqlParameter(IN_USUARIO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_NOMBRE_DESTINO, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) NUMERO_ORDEN, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) P_ESTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter((String) P_COD_RETORNO, Types.DECIMAL));
		parametros.add(new SqlOutParameter((String) OUT_DESCRIPCION, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, inEntity.getCuenta())
				.addValue(IN_CUENTA_SECUNDARIA, inEntity.getCuentaSecundaria())
				.addValue(IN_SUCURSAL_SECUNDARIA, inEntity.getSucursalSecundaria())
				.addValue(IN_DIVISA_MOVIMIENTO, inEntity.getDivisaMovimiento())
				.addValue(IN_IMPORTE_MOVIMIENTO, inEntity.getImporteMovimiento())
				.addValue(IN_TIPO_MOVIMIENTO, inEntity.getTipoMovimiento())
				.addValue(IN_CANAL, inEntity.getCanal())
				.addValue(IN_USUARIO, inEntity.getUsuario()).addValue(IN_CONTRA, inEntity.getPass()).addValue(IN_NOMBRE_DESTINO, inEntity.getNombreDestino());

		try {
			Map<String, Object> respuesta = consultar(CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, EJECUTAR_TRANSFERENCIA, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			BigDecimal  pretorno =  (BigDecimal) respuesta.get(P_COD_RETORNO);
			
			LOGGER.info("Cod. retorno : {}", codRetorno);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				rtaTransferenciaBP = parsearRespuestansferencia(respuesta);	
			
			}else {
				LOGGER.debug("Cod. retorno : {}", codRetorno);
				String descError = (String) respuesta.get(OUT_DESCRIPCION);
				manejarCasosAlternativos(pretorno, descError);
			}
		} catch (SQLException sqle) {
			manejarErrorInesperado(sqle, EJECUTAR_TRANSFERENCIA);
		} catch (UncategorizedSQLException sqle) {
			manejarErrorInesperado(sqle, EJECUTAR_TRANSFERENCIA);
		} catch (QueryTimeoutException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} ", CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, EJECUTAR_TRANSFERENCIA,
					sqle);
			throw new TimeOutException("Error de timeOut en query.");
		}
		return rtaTransferenciaBP;
	}
	
	
	
	/**
	 * Armo el objeto respuesta con el mapa que retorno la ejecucion del SP.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the RespuestaTransferencia.
	 */
	private RtaTransferenciaBP parsearRespuestansferencia(Map<String, Object> respuesta) {
		RtaTransferenciaBP rtaTransferenciaBP = new RtaTransferenciaBP("","");
		rtaTransferenciaBP.setCodigoResultado(respuesta.get(OUT_CODIGO_RETORNO).toString());
		rtaTransferenciaBP.setDescrResultado((String) respuesta.get(OUT_DESCRIPCION_TRANSACCION)); 
		rtaTransferenciaBP.setComprobanteTransaccion((String)respuesta.get(NUMERO_ORDEN));
		return rtaTransferenciaBP;
	}
	
	
	
	/**
	 * Manejar casos alternativos.
	 *
	 * @param codRetorno
	 *            the cod retorno
	 * @param descError
	 *            the desc error
	 * @throws ExcedeLimiteTransferenciaException
	 *             the excede limite transferencia exception
	 * @throws CuentaBloqueadaException
	 *             the cuenta bloqueada exception
	 * @throws DisponibleInsuficienteException
	 *             the disponible insuficiente exception
	 * @throws SaldoCuotasParteInsuficienteException
	 *             the saldo cuotas parte insuficiente exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ImporteMenorAlMinimoException
	 *             the importe menor al minimo exception
	 * @throws ProblemasAlEfectuarTransferenciaException
	 *             the problemas al efectuar transferencia exception
	 * @throws FueraDeHorarioException
	 *             the fuera de horario exception
	 */
	private void manejarCasosAlternativos(BigDecimal codRetorno, String descError)
			throws ExcedeLimiteTransferenciaException, CuentaBloqueadaException, DisponibleInsuficienteException,
			SaldoCuotasParteInsuficienteException, DAOException, ImporteMenorAlMinimoException,
			ProblemasAlEfectuarTransferenciaException, FueraDeHorarioException {

		if (EXCEDE_LIMITE_TRANSFERENCIA.equals(codRetorno)) {
			throw new ExcedeLimiteTransferenciaException(descError);
		}
		if (CUENTA_BLOQUEADA.equals(codRetorno)) {
			throw new CuentaBloqueadaException(descError);
		}
		if (DISPONIBLE_INSUFICIENTE.equals(codRetorno)) {
			throw new DisponibleInsuficienteException(descError);
		}
		if (SALDO_CUOTAS_PARTE_INSUFICIENTE.equals(codRetorno)) {
			throw new SaldoCuotasParteInsuficienteException(descError);
		}
		if (COD_ERROR_GENERICO.equals(codRetorno)) {
			throw new DAOException(descError, codRetorno.toString());
		}
		if (IMPORTE_NETO_MENOR_MINIMO.equals(codRetorno)) {
			throw new ImporteMenorAlMinimoException(descError);
		}
		if (PROBLEMAS_EFECTUAR_TRANSFERENCIA.equals(codRetorno)) {
			throw new ProblemasAlEfectuarTransferenciaException(descError);
		}
		if (HORARIO_FINALIZADO.equals(codRetorno)) {
			throw new FueraDeHorarioException(descError);
		}
		else{
			throw new DAOException(descError, codRetorno.toString());
		}
		
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
		LOGGER.error("Error al consumir el store {}.{}.{} ", CUENTA_SALDO_SCHEMA, CUENTA_SALDO_PACKAGE, contrato, sqle);
		throw new SimulacionDAOException(sqle);
	}
	
	
}