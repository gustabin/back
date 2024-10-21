/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.custodia.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaBPInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.Tenencia;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.TenenciaBP;
import oracle.jdbc.OracleTypes;

/**
 * The Class CustodiaDAOImpl.
 *
 * @author pablo.d.gargaglione
 */

@Component
@TargetSystem(DataBase.BPRIV)
public class CustodiaDAOImpl extends BaseDatoDAOImpl implements CustodiaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CustodiaDAOImpl.class);

	/** parametro entrada cuenta. */
	private static final String IN_CUENTA = "P_CUENTA";
	/** parametro entrada fecha. */
	private static final String IN_FECHA = "P_FECHA";
	/** parametro entrada especie. */
	private static final String IN_ESPECIE = "P_TIPO_ESPECIE";

	/** parametro entrada canal. */
	private static final String IN_CANAL = "P_CANAL";
	/** parametro salida descripcion error. */
	private static final String OUT_DESCRIPCION = "P_DESCRIPCION";

	/** SCHEMA. */
	private static final String CUSTODIA_SCHEMA = "BCAINV";

	/** PACKAGE contrato. */
	private static final String CUSTODIA_PACKAGE = "PKG_BP_ONLINE_BANKING";

	/** PROCEDURE load_custodia_ol. */
	private static final String LOAD_CUSTODIA_OL = "load_custodia_ol";

	/** PROCEDURE load_custodia. */
	private static final String LOAD_CUSTODIA = "load_custodia";

	/** Codigo estado resultado. */
	private static final String OUT_RESULTADO = "P_RETORNO";
	
	/** The Constant P_COD_RETORNO. */
	private static final String P_COD_RETORNO = "P_COD_RETORNO";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_CURSOR = "P_CURSOR";

	/** Constante COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "OK";

	/**
	 * The data source.
	 *
	 * @param custodiaInEntity
	 *            the custodia in entity
	 * @return the custodia out entity
	 * @throws DAOException
	 *             the DAO exception
	 */

	/**
	 * Llamada al SP PKG_BP_ONLINE_BANKING.load_custodia_ol trae el valor de una
	 * tenencia en particular para realizar un rescate
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CustodiaOutEntity verTenenciaOl(CustodiaInEntity custodiaInEntity) throws DAOException {
		CustodiaOutEntity rta = new CustodiaOutEntity();
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();

		parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_FECHA, Types.DATE));
		parametros.add(new SqlParameter(IN_ESPECIE, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(P_COD_RETORNO, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CURSOR, OracleTypes.CURSOR));

		LOGGER.info("SP : " + CUSTODIA_SCHEMA + "." + CUSTODIA_PACKAGE + "." + LOAD_CUSTODIA_OL);
		LOGGER.info(IN_CUENTA + " : " + custodiaInEntity.getCuenta());
		LOGGER.info(IN_FECHA + " : " + custodiaInEntity.getFecha());
		LOGGER.info(IN_ESPECIE + " : " + custodiaInEntity.getTipoEspecie());

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, custodiaInEntity.getCuenta())
				.addValue(IN_FECHA, custodiaInEntity.getFecha())
				.addValue(IN_ESPECIE, custodiaInEntity.getTipoEspecie());

		try {
			Map<String, Object> respuesta = consultar(CUSTODIA_SCHEMA, CUSTODIA_PACKAGE, LOAD_CUSTODIA_OL, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				LOGGER.info("Cod. retorno : " + codRetorno);
				LOGGER.info("rta: " + respuesta.get(OUT_CURSOR));
				List<Object> tenencias = (List<Object>) respuesta.get(OUT_CURSOR);
				List<Tenencia> rtaTenencias = new ArrayList<Tenencia>();
				for (int i = 0; i < tenencias.size(); i++) {
					rtaTenencias.add(createTenencia((Map) tenencias.get(i)));
				}
				rta.setListaTenencia(rtaTenencias);
			} else {
				LOGGER.debug("Cod. retorno : " + codRetorno);
				throw new DAOException((String) respuesta.get(OUT_DESCRIPCION));
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CUSTODIA_SCHEMA, CUSTODIA_PACKAGE, LOAD_CUSTODIA_OL, sqle);
			throw new DAOException(sqle);
		} catch (QueryTimeoutException e) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CUSTODIA_SCHEMA, CUSTODIA_PACKAGE, LOAD_CUSTODIA_OL, e);
			throw new DAOException(e);
		}
		return rta;
	}

	/**
	 * Crea un objeto tenencia a partir del mapa retornado del store.
	 *
	 * @param data
	 *            the data
	 * @return the tenencia
	 */
	@SuppressWarnings("rawtypes")
	private Tenencia createTenencia(Map data) {
		Tenencia tenencia = new Tenencia();
		tenencia.setSucursal((BigDecimal) data.get("SUCURSAL"));
		tenencia.setNroCuenta((BigDecimal) data.get("NRO_CUENTA"));
		tenencia.setEspecieTipo((String) data.get("ESPECIE__TIPO"));
		tenencia.setEspecieCodigo((BigDecimal) data.get("ESPECIE__CODIGO"));
		tenencia.setEspecieDescripcion((String) data.get("ESPECIE_DESCRIPCION"));
		tenencia.setTenenciaNominal((BigDecimal) data.get("TENENCIA_NOMINAL"));
		tenencia.setValorResidual((BigDecimal) data.get("VALOR_RESIDUAL"));
		tenencia.setTenenciaValuada((BigDecimal) data.get("TENENCIA_VALUADA"));
		tenencia.setCotizacion((BigDecimal) data.get("COTIZACION"));
		tenencia.setMonedaTipo((BigDecimal) data.get("MONEDA_TIPO"));
		tenencia.setNombreMoneda((String) data.get("NOMBRE_MONEDA"));
		tenencia.setFechaCotizacion((BigDecimal) data.get("FECHA_COTIZACION"));
		tenencia.setHoraCotizacion((BigDecimal) data.get("HORA_COTIZACION"));
		tenencia.setCodEstado((String) data.get("COD_ESTADO"));
		tenencia.setEstadoDescripcion((String) data.get("ESTADO_DESCRIPCION"));
		tenencia.setSiglaFondo((String) data.get("SIGLA_FONDO"));
		tenencia.setSaldoBruto((BigDecimal) data.get("SALDO_BRUTO"));
		tenencia.setTotalSuscripciones((BigDecimal) data.get("TOTAL_SUSCRIPCIONES"));
		tenencia.setCantCuotasSuscriptas((BigDecimal) data.get("CANT_CUOTAS_SUSCRIPTAS"));
		tenencia.setTotalRescates((BigDecimal) data.get("TOTAL_RESCATES"));
		tenencia.setCantidadCuotasRescatadas((BigDecimal) data.get("CANTIDAD_CUOTAS_RESCATADAS"));
		tenencia.setFechaBloqueo((BigDecimal) data.get("FECHA_BLOQUEO"));
		tenencia.setFechaRespuesta((BigDecimal) data.get("FECHA_RESPUESTA"));
		tenencia.setHoraRespuesta((BigDecimal) data.get("HORA_RESPUESTA"));
		tenencia.setCodError((BigDecimal) data.get("COD_ERROR"));
		tenencia.setDescError((String) data.get("DESC_ERROR"));
		return tenencia;
	}

	/**
	 * Llamada al SP PKG_BP_ONLINE_BANKING.load_custodia el cual muestra la
	 * tenencia de fondos de un cliente de Banca Privada es un sumarizado de los
	 * fondos del cliente
	 *
	 * @param custodiaBPInEntity
	 *            the custodia BP in entity
	 * @return the custodia BP out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public CustodiaBPOutEntity verTenenciaBP(CustodiaBPInEntity custodiaBPInEntity) throws DAOException {
		LOGGER.info("Obteniendo Tenencia de fondos BP,  para la cuenta: {}", custodiaBPInEntity.getCuenta());
		CustodiaBPOutEntity custodiaBPOutEntity = new CustodiaBPOutEntity();
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();

		parametros.add(new SqlParameter(IN_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_FECHA, Types.DATE));
		parametros.add(new SqlParameter(IN_CANAL, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(P_COD_RETORNO, Types.INTEGER));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CURSOR, OracleTypes.CURSOR));
		
		LOGGER.info("SP : " + LOAD_CUSTODIA);
		LOGGER.info(IN_CUENTA + " : " + custodiaBPInEntity.getCuenta());
		LOGGER.info(IN_FECHA + " : " + custodiaBPInEntity.getFecha());
		LOGGER.info(IN_CANAL + " : " + custodiaBPInEntity.getCanal());

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CUENTA, custodiaBPInEntity.getCuenta())
				.addValue(IN_FECHA, custodiaBPInEntity.getFecha()).addValue(IN_CANAL, custodiaBPInEntity.getCanal());

		try {
			Map<String, Object> respuesta = consultar(CUSTODIA_SCHEMA, CUSTODIA_PACKAGE, LOAD_CUSTODIA, in,
					parametros.toArray(new SqlParameter[parametros.size()]));

			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				LOGGER.info("Cod. retorno : " + codRetorno);
				LOGGER.info("rta: " + respuesta.get(OUT_CURSOR));
				List<Object> tenencias = (List<Object>) respuesta.get(OUT_CURSOR);
				List<TenenciaBP> rtaTenencias = new ArrayList<TenenciaBP>();
				for (int i = 0; i < tenencias.size(); i++) {
					rtaTenencias.add(createTenenciaBP((Map) tenencias.get(0)));
				}
				custodiaBPOutEntity.setRespuesta(rtaTenencias);
			} else {
				LOGGER.debug("Cod. retorno : " + codRetorno);
				throw new DAOException((String) respuesta.get(OUT_DESCRIPCION));
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CUSTODIA_SCHEMA, CUSTODIA_PACKAGE, LOAD_CUSTODIA, sqle);
		}

		return custodiaBPOutEntity;
	}

	/**
	 * Creates the tenencia BP.
	 *
	 * @param data
	 *            the data
	 * @return the tenencia BP
	 */
	private TenenciaBP createTenenciaBP(Map data) {
		TenenciaBP tenencia = new TenenciaBP();

		return tenencia;
	}
}
