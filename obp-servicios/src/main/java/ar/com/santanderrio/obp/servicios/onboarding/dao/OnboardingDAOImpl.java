/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import oracle.jdbc.OracleTypes;

/**
 * The Class OnboardingDAOImpl.
 */
@Repository("onboardingDAO")
@TargetSystem(DataBase.ESTADISTICAS)
public class OnboardingDAOImpl extends BaseDatoDAOImpl implements OnboardingDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OnboardingDAOImpl.class);

	/** The Constant SCHEMA. */
	private static final String SCHEMA = "hbank";

	/** The Constant PACKAGE. */
	private static final String PACKAGE = "HB_PKG_ONBOARDING";

	/** The Constant PROCEDURE_ESTADO. */
	private static final String PROCEDURE_ESTADO = "obtener_secciones_a_visitar";

	/** The Constant PROCEDURE_LISTO. */
	private static final String PROCEDURE_LISTO = "registrar_visitar";

	/** The Constant IN_NUP. */
	private static final String IN_NUP = "p_nup";

	/** The Constant IN_SECCION. */
	private static final String IN_SECCION = "p_seccion";

	/** The Constant IN_DISPOSITIVO. */
	private static final String IN_DISPOSITIVO = "p_dispositivo";

	/** The Constant OUT_CURSOR. */
	private static final String OUT_CURSOR = "p_cursor";

	/** The Constant OUT_RESULTADO. */
	private static final String OUT_RESULTADO = "p_resultado";

	/** The Constant OUT_ERROR_TECNICO. */
	private static final String OUT_ERROR_TECNICO = "p_err_tecnico";

	/** The Constant OUT_ERROR_AMIGABLE. */
	private static final String OUT_ERROR_AMIGABLE = "p_err_amigable";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.onboarding.dao.OnboardingDAO#
	 * obtenerSeccionesActivas(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> obtenerSeccionesActivas(String nupCliente, String dispositivo) throws DAOException {
		LOGGER.info("Obtener listado de estados del onboarding desde la base de datos.");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_DISPOSITIVO, Types.VARCHAR));
		parametros.add(getSqlOutCursorParameter());
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nupCliente).addValue(IN_DISPOSITIVO,
				dispositivo);
		try {
			Map<String, Object> respuesta = consultar(SCHEMA, PACKAGE, PROCEDURE_ESTADO, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if ("0".equals(codRetorno)) {
				return (List<String>) respuesta.get(OUT_CURSOR);
			} else {
				throw new DAOException("No se puede obtener el listado de estados de la base.");
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA, PACKAGE, PROCEDURE_ESTADO, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException usqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", SCHEMA, PACKAGE, PROCEDURE_ESTADO,
					usqle);
			throw new DAOException(usqle);
		} catch (InvalidDataAccessApiUsageException idaaue) {
			LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", SCHEMA, PACKAGE, PROCEDURE_ESTADO,
					idaaue);
			throw new DAOException(idaaue);
		}
	}

	/**
	 * Gets the sql out cursor parameter.
	 *
	 * @return the sql out cursor parameter
	 */
	private SqlOutParameter getSqlOutCursorParameter() {
		return new SqlOutParameter(OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.onboarding.dao.OnboardingDAO#
	 * informarListo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean informarListo(String nupCliente, String dispositivo, String seccion) throws DAOException {
		LOGGER.info("Informar listo de seccion a la base de datos.");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_SECCION, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_DISPOSITIVO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, nupCliente).addValue(IN_SECCION, seccion)
				.addValue(IN_DISPOSITIVO, dispositivo);
		try {
			Map<String, Object> respuesta = consultar(SCHEMA, PACKAGE, PROCEDURE_LISTO, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			return "0".equals(codRetorno);
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA, PACKAGE, PROCEDURE_LISTO, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException usqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", SCHEMA, PACKAGE, PROCEDURE_LISTO,
					usqle);
			throw new DAOException(usqle);
		} catch (InvalidDataAccessApiUsageException idaaue) {
			LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", SCHEMA, PACKAGE, PROCEDURE_LISTO,
					idaaue);
			throw new DAOException(idaaue);
		}
	}

}
