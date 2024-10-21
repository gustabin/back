/*
 * 
 */
package ar.com.santanderrio.obp.servicios.session.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.com.santanderrio.obp.servicios.session.dao.SessionControlDAO;
import ar.com.santanderrio.obp.servicios.session.dto.SessionControlDTO;
import oracle.jdbc.OracleTypes;

/**
 * Este DAO es el encargado de gestionar el control de session
 *
 * Created by pablo.martin.gore on 8/31/2016.
 */

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class SessionControlDAOImpl extends BaseDatoDAOImpl implements SessionControlDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionControlDAOImpl.class);

	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";

	/** SCHEMA Control de Session. */
	private static final String CONTROL_SESSION_SCHEMA = "HBANK";

	/** PACKAGE Control de Session. */
	private static final String CONTROL_SESSION_PACKAGE = "HB_PKG_CONTROL_SESIONES";

	/** PROCEDURE obtener estado de Session. */
	private static final String CONTROL_OBTENER_PROCEDURE = "OBTENER_ESTADO_SESION";

	/** PROCEDURE guardar o modificar Session. */
	private static final String CONTROL_SAVE_OR_UPDATE_PROCEDURE = "GUARDAR_O_MODIFICAR_SESION";

	/** Parametro de entrada NUP. */
	private static final String IN_OBTENER_NUP = "p_nup";

	/** Parametro de entrada TOKEN. */
	private static final String IN_OBTENER_TOKEN = "p_token";

	/** Parametro de entrada Fecha Login servidor. */
	private static final String IN_OBTENER_ULT_LOGIN = "p_ult_login";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_OBTENER_CURSOR = "result_cursor";

	/** Codigo retorno. */
	private static final String OUT_OBTENER_COD = "p_cod_ret";

	/** Codigo descripcion. */
	private static final String OUT_OBTENER_DESC = "p_desc_retorno";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.session.dao.SessionControlDAO#
	 * saveOrUpdate(java.lang.Long, java.lang.String)
	 */
	@Override
	public void saveOrUpdate(Long nup, String token) throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_OBTENER_NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_OBTENER_TOKEN, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_OBTENER_ULT_LOGIN, Types.TIMESTAMP));
		parametros.add(new SqlOutParameter(OUT_OBTENER_COD, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_OBTENER_DESC, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_OBTENER_NUP, nup)
				.addValue(IN_OBTENER_TOKEN, token).addValue(IN_OBTENER_ULT_LOGIN, Calendar.getInstance().getTime());
		try {
			Map<String, Object> respuesta = consultar(CONTROL_SESSION_SCHEMA, CONTROL_SESSION_PACKAGE,
					CONTROL_SAVE_OR_UPDATE_PROCEDURE, in, parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_OBTENER_COD);
			if (!COD_RETORNO_OK.equals(codRetorno)) {
				throw new DAOException((String) respuesta.get(OUT_OBTENER_DESC));
			}

		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con el nup {}", CONTROL_SESSION_SCHEMA,
					CONTROL_SESSION_PACKAGE, CONTROL_SAVE_OR_UPDATE_PROCEDURE, nup, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException e) {
			LOGGER.error("Error de base en el store {}.{}.{} con el input {}", CONTROL_SESSION_SCHEMA,
					CONTROL_SESSION_PACKAGE, CONTROL_SAVE_OR_UPDATE_PROCEDURE, nup, e);
			throw new DAOException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.session.dao.SessionControlDAO#get(java.
	 * lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SessionControlDTO get(Long nup) throws DAOException {
		SessionControlDTO dtoRetorno = null;
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_OBTENER_NUP, Types.NUMERIC));
		parametros.add(new SqlOutParameter(OUT_OBTENER_CURSOR, OracleTypes.CURSOR, new RowMapper<SessionControlDTO>() {
			@Override
			public SessionControlDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				SessionControlDTO dto = new SessionControlDTO();
				dto.setNup(rs.getLong("NUP"));
				dto.setToken(rs.getString("TOKEN"));
				dto.setInsert(rs.getTimestamp("F_ALTA_REGISTRO"));
				dto.setUpdate(rs.getTimestamp("F_MODIFICACION_REGISTRO"));
				return dto;
			}
		}));
		parametros.add(new SqlOutParameter(OUT_OBTENER_COD, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_OBTENER_DESC, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_OBTENER_NUP, nup);
		try {
			Map<String, Object> respuesta = consultar(CONTROL_SESSION_SCHEMA, CONTROL_SESSION_PACKAGE,
					CONTROL_OBTENER_PROCEDURE, in, parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_OBTENER_COD);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<SessionControlDTO> listaSessionControlDTO = (List<SessionControlDTO>) respuesta
						.get(OUT_OBTENER_CURSOR);
				if (CollectionUtils.isNotEmpty(listaSessionControlDTO)) {
					dtoRetorno = listaSessionControlDTO.get(0);
				}
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con el input {}", CONTROL_SESSION_SCHEMA,
					CONTROL_SESSION_PACKAGE, CONTROL_OBTENER_PROCEDURE, nup, sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException e) {
			LOGGER.error("Error de base en el store {}.{}.{} con el input {}", CONTROL_SESSION_SCHEMA,
					CONTROL_SESSION_PACKAGE, CONTROL_OBTENER_PROCEDURE, nup, e);
			throw new DAOException(e);
		}
		return dtoRetorno;
	}

	/**
	 * No quitar este metodo aca ya que sino genera que se lance la limpieza del
	 * token fuera del hilo de ejecucion y rompe el logout.
	 *
	 * @return true, if successful
	 */
	@Override
	protected boolean loguearDatos() {
		return false;
	}

}
