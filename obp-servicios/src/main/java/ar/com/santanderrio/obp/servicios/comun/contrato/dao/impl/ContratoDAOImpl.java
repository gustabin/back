/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.contrato.dao.ContratoDAO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.ContratoInEntity;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import oracle.jdbc.OracleTypes;

/**
 * The Class ContratoDAOImpl.
 *
 * @author pablo.d.gargaglione
 */

@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class ContratoDAOImpl extends BaseDatoDAOImpl implements ContratoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ContratoDAOImpl.class);

	/** Constante COD_RESULTADO_OK. */
	private static final String COD_RESULTADO_OK = "0";

	/** PARAMETRO ENTRADA DNI. */
	private static final String IN_CONTRATO_DNI = "p_dni";

	/** PARAMETRO ENTRADA FECHA NACIMIENTO. */
	private static final String IN_CONTRATO_FECHA = "p_fec_nac";

	/** PARAMETRO ENTRADA CAMPO. */
	private static final String IN_CONTRATO_CAMPO = "p_campo";

	/** PARAMETRO ENTRADA SINONIMO. */
	private static final String IN_CONTRATO_SINONIMO = "p_sinonimo";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_CONTRATO_CURSOR = "p_cursor";

	/** Codigo estado resultado. */
	private static final String OUT_CONTRATO_RESULTADO = "p_resultado";

	/** Codigo error tecnico. */
	private static final String OUT_CONTRATO_TECNICO = "p_err_tecnico";

	/** Descripcion amigable del error. */
	private static final String OUT_CONTRATO_AMIGABLE = "p_err_amigable";

	/** SCHEMA. */
	private static final String CONTRATO_SCHEMA = "hbank";

	/** PACKAGE contrato. */
	private static final String CONTRATO_PACKAGE = "HB_PKG_CONTRATOS";

	/** PROCEDURE contrato. */
	private static final String CONTRATO_VER_PROCEDURE = "ver_contrato";

	/** PROCEDURE contrato. */
	private static final String CONTRATO_ACEPTAR_PROCEDURE = "aceptar_contrato";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String verContrato(ContratoInEntity input, CampoEnum campoEnum, SinonimoEnum sinonimoEnum)
			throws DAOException {
		String aceptacionContrato = null;
		String dniSinCeros = ISBANStringUtils.eliminarCeros(input.getDni());
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_CONTRATO_DNI, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRATO_FECHA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRATO_CAMPO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRATO_SINONIMO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONTRATO_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONTRATO_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONTRATO_AMIGABLE, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONTRATO_CURSOR, OracleTypes.CURSOR, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		}));
		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CONTRATO_DNI, dniSinCeros)
				.addValue(IN_CONTRATO_FECHA, input.getFechaNac()).addValue(IN_CONTRATO_CAMPO, campoEnum)
				.addValue(IN_CONTRATO_SINONIMO, sinonimoEnum);
		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_VER_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_CONTRATO_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				List<String> contratos = (List<String>) respuesta.get(OUT_CONTRATO_CURSOR);
				if (contratos.size() > 0) {
					aceptacionContrato = contratos.get(0);
				} else {
					LOGGER.info(
							"No hay valores retornados para el store {} con dni {}, fecha {}, campo {} y sinonimo {}.",
							CONTRATO_VER_PROCEDURE, dniSinCeros, input.getFechaNac(), campoEnum, sinonimoEnum);
				}
			} else {
				LOGGER.info("Error controlado al consultar el store {} con dni {}, fecha {}, campo {} y sinonimo {}.",
						CONTRATO_VER_PROCEDURE, dniSinCeros, input.getFechaNac(), campoEnum, sinonimoEnum);
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_VER_PROCEDURE, dniSinCeros, input.getFechaNac(),
					campoEnum, sinonimoEnum, sqle);
		} catch (Exception sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
					CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_VER_PROCEDURE, dniSinCeros, input.getFechaNac(),
					campoEnum, sinonimoEnum, sqle);
		}
		return aceptacionContrato;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String aceptarContrato(ContratoInEntity input, CampoEnum campoEnum, SinonimoEnum sinonimoEnum)
			throws DAOException {

		String retorno = "ERROR";
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_CONTRATO_DNI, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRATO_FECHA, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRATO_CAMPO, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CONTRATO_SINONIMO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONTRATO_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONTRATO_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONTRATO_AMIGABLE, Types.VARCHAR));
		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CONTRATO_DNI, input.getDni())
				.addValue(IN_CONTRATO_FECHA, input.getFechaNac()).addValue(IN_CONTRATO_CAMPO, campoEnum)
				.addValue(IN_CONTRATO_SINONIMO, sinonimoEnum);
		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_ACEPTAR_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_CONTRATO_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				retorno = "OK";
			} else {
				LOGGER.info("Error controlado al consultar el store {} con dni {}, fecha {}, campo {} y sinonimo {}.",
						CONTRATO_ACEPTAR_PROCEDURE, input.getDni(), input.getFechaNac(), campoEnum, sinonimoEnum);
			}
		} catch (SQLException sqle) {
			manejarErrorInesperado(input, sqle, campoEnum, sinonimoEnum);
		} catch (UncategorizedSQLException sqle) {
			manejarErrorInesperado(input, sqle, campoEnum, sinonimoEnum);
		}
		return retorno;
	}

	/**
	 * Maneja error inesperado al ejecutar el store.
	 *
	 * @param input
	 *            the input
	 * @param sqle
	 *            the sqle
	 * @param campoEnum
	 *            the campo enum
	 * @param sinonimoEnum
	 *            the sinonimo enum
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void manejarErrorInesperado(ContratoInEntity input, Exception sqle, CampoEnum campoEnum,
			SinonimoEnum sinonimoEnum) throws DAOException {
		LOGGER.error("Error al consumir el store {}.{}.{} con dni {}, fecha {}, campo {} y sinonimo {}.",
				CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_ACEPTAR_PROCEDURE, input.getDni(), input.getFechaNac(),
				campoEnum, sinonimoEnum, sqle);
		throw new DAOException(sqle);
	}

}
