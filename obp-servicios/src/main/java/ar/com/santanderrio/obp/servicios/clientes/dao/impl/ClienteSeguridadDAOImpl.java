package ar.com.santanderrio.obp.servicios.clientes.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteSeguridadDAO;
import oracle.jdbc.OracleTypes;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class ClienteSeguridadDAOImpl extends BaseDatoDAOImpl implements ClienteSeguridadDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteSeguridadDAOImpl.class);
	
	/** SCHEMA. */
	private static final String SCHEMA_NAME = "hbank";
	
	/** Parametro de entrada nup. */
	private static final String PARAM_IN_NUP = "p_nup";

	/** 1 si esta habilitado a tbanco, 0 a obp y -1 si hay error. */
	private static final String PARAM_RESULTADO = "p_resultado";

	/** Codigo error tecnico oracle. */
	private static final String PARAM_TECNICO = "p_err_tecnico";

	/** Descripcion amigable del error. */
	private static final String PARAM_AMIGABLE = "p_err_amigable";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String PARAM_CURSOR = "p_cursor";
	
	/** Nombre del package*/
	private static final String PKG_CLIENTE_CONTROL_SEG = "HB_PKG_CLIENTE_CONTROL_SEG";

	/** Nombre del stored procedure de consulta */
	private static final String PROCEDURE_CONSULTAR_DIAS_DESDE_ACT = "CONSULTAR_DIAS_DESDE_ACT";
	
	/** Nombre del stored procedure de actualizacion */
	private static final String PROCEDURE_ACTUALIZAR_FECHAS = "ACTUALIZAR_FECHAS";

	/** Nombre de param que indica si actualiza fecha clave */
	private static final String PARAM_P_ACT_FECHA_CLAVE = "P_ACT_FECHA_CLAVE";

	/** Nombre de param que indica si actualiza fecha token */
	private static final String PARAM_P_ACT_FECHA_TOKEN = "P_ACT_FECHA_TOKEN";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BigDecimal> obtenerAntiguedadDiasUltCambioClaveToken(Long nup) {
		
		LOGGER.debug("Consultar si el nup {} tiene antiguedad de cambio de clave y/o token.", nup);
		
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(PARAM_IN_NUP, Types.NUMERIC));
		parametros.add(addSqlOutCursorParameter());
		parametros.add(new SqlOutParameter(PARAM_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_AMIGABLE, Types.VARCHAR));

		try {
			SqlParameterSource in = new MapSqlParameterSource().addValue(PARAM_IN_NUP, nup);
			
			Map<String, Object> respuestaStore = consultar(SCHEMA_NAME, PKG_CLIENTE_CONTROL_SEG,
					PROCEDURE_CONSULTAR_DIAS_DESDE_ACT, in, parametros.toArray(new SqlParameter[parametros.size()]));
			
			return respuestaStore.get(PARAM_CURSOR) == null ? null : (List<BigDecimal>) respuestaStore.get(PARAM_CURSOR);
			
		} catch (SQLException sqle) {
			
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA_NAME,
					PKG_CLIENTE_CONTROL_SEG, PROCEDURE_CONSULTAR_DIAS_DESDE_ACT, sqle);
			return null;
			
		} catch (UncategorizedSQLException usqle) {
			
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA_NAME,
					PKG_CLIENTE_CONTROL_SEG, PROCEDURE_CONSULTAR_DIAS_DESDE_ACT, usqle);
			return null;
			
		}
	}
	
	private SqlOutParameter addSqlOutCursorParameter() {
		return new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new ResultSetExtractor<List<BigDecimal>>() {
			@Override
			public List<BigDecimal> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<BigDecimal> listRet = new LinkedList<BigDecimal>();
				while (rs.next()) {
					listRet.add(rs.getBigDecimal("dias_clave"));
					listRet.add(rs.getBigDecimal("dias_token"));
				}
				return listRet;
			}
		});
	}

	@Override
	public Boolean actualizarFechaActualizacionClave(Long nup) {
		
		LOGGER.debug("Actualiza la ultima fecha de cambio de clave, nup {}", nup);
		
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(PARAM_IN_NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(PARAM_P_ACT_FECHA_CLAVE, Types.NUMERIC));
		parametros.add(new SqlParameter(PARAM_P_ACT_FECHA_TOKEN, Types.NUMERIC));
		parametros.add(new SqlOutParameter(PARAM_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_AMIGABLE, Types.VARCHAR));

		try {
			SqlParameterSource in = new MapSqlParameterSource().addValue(PARAM_IN_NUP, nup)
				.addValue(PARAM_P_ACT_FECHA_CLAVE, 1)
				.addValue(PARAM_P_ACT_FECHA_TOKEN, 0);
			
			consultar(SCHEMA_NAME, PKG_CLIENTE_CONTROL_SEG,
					PROCEDURE_ACTUALIZAR_FECHAS, in, 
					parametros.toArray(new SqlParameter[parametros.size()]));
			
			return Boolean.TRUE;
			
		} catch (SQLException sqle) {
			
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA_NAME,
					PKG_CLIENTE_CONTROL_SEG, PROCEDURE_ACTUALIZAR_FECHAS, sqle);
			return Boolean.FALSE;
			
		} catch (UncategorizedSQLException usqle) {
			
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA_NAME,
					PKG_CLIENTE_CONTROL_SEG, PROCEDURE_ACTUALIZAR_FECHAS, usqle);
			return Boolean.FALSE;
		}
	}

}
