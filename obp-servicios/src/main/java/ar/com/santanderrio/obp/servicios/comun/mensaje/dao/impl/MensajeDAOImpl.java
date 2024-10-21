/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import oracle.jdbc.OracleTypes;

/**
 * The Class MensajeDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class MensajeDAOImpl extends BaseDatoDAOImpl implements MensajeDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MensajeDAOImpl.class);

	/** SCHEMA. */
	private static final String MENSAJE_SCHEMA = "hbank";

	/** PACKAGE Mensajes. */
	private static final String MENSAJE_PACKAGE = "HB_PKG_MENSAJES_ERROR";

	/** PROCEDURE Mensajes. */
	private static final String MENSAJE_PROCEDURE = "prc_obtener_mensaje_error";

	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";

	/** Parametro de entrada. */
	private static final String IN_MENSAJE_CODIGO = "p_codigo_error";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_MENSAJE_CURSOR = "p_cursor";

	/** Codigo error tecnico. */
	private static final String OUT_MENSAJE_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String OUT_MENSAJE_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String OUT_MENSAJE_AMIGABLE = "p_err_amigable";

	/** The mensaje Error Generico. */
	@Value("${MENSAJE_ERROR_GENERICO}")
	private String mensajeErrorGenerico;

	/**
	 * Instantiates a new mensaje dao impl.
	 */
	public MensajeDAOImpl() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO#
	 * obtenerMensaje(java.lang.String)
	 * 
	 * TODO: USAR MensajeDAO#obtenerMensajePorCodigo
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Deprecated
	public Mensaje obtenerMensaje(String codigoMensaje) {
		Mensaje mensaje = new Mensaje();
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_MENSAJE_CODIGO, Types.NUMERIC));
		parametros.add(new SqlOutParameter(OUT_MENSAJE_CURSOR, OracleTypes.CURSOR, new RowMapper<Mensaje>() {
			@Override
			public Mensaje mapRow(ResultSet rs, int rowNum) throws SQLException {
				Mensaje mensaje = new Mensaje();
				mensaje.setCodigo(rs.getString(1));
				mensaje.setMensaje(rs.getString(6));
				return mensaje;
			}
		}));
		parametros.add(new SqlOutParameter(OUT_MENSAJE_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_MENSAJE_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_MENSAJE_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_MENSAJE_CODIGO, codigoMensaje);
		try {
			Map<String, Object> respuesta = consultar(MENSAJE_SCHEMA, MENSAJE_PACKAGE, MENSAJE_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_MENSAJE_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<Mensaje> mensajes = (List<Mensaje>) respuesta.get(OUT_MENSAJE_CURSOR);
				if (mensajes.size() > 0) {
					mensaje = mensajes.get(0);
				} else {
					mensaje.setCodigo(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
					mensaje.setMensaje(mensajeErrorGenerico);
					mensaje.setGenerico(Boolean.TRUE);
				}
			} else {
				mensaje.setCodigo(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
				mensaje.setMensaje(mensajeErrorGenerico);
				mensaje.setGenerico(Boolean.TRUE);
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con el input {}", MENSAJE_SCHEMA, MENSAJE_PACKAGE,
					MENSAJE_PROCEDURE, codigoMensaje, sqle);
			mensaje.setCodigo(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO);
			mensaje.setMensaje(mensajeErrorGenerico);
			mensaje.setGenerico(Boolean.TRUE);
		}
		return mensaje;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.comun.mensaje.dao.MensajeDAO#
	 * obtenerMensajeDescripcion(java.lang.String)
	 * 
	 * TODO: USAR MensajeDAO#obtenerMensajePorCodigo
	 */
	@Override
	@Deprecated
	public String obtenerMensajeDescripcion(String codigoMensaje) {
		String descripcion = null;
		Mensaje tablaMensaje = obtenerMensaje(codigoMensaje);
		if (tablaMensaje != null) {
			descripcion = tablaMensaje.getMensaje();
		}
		return descripcion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO#
	 * obtenerMensajePorCodigo(java.lang.String)
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_MENSAJES }, key = "#codigoMensaje")
	@SuppressWarnings("unchecked")
	@Override
	public Mensaje obtenerMensajePorCodigo(String codigoMensaje) {
		Mensaje mensaje = new Mensaje();
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_MENSAJE_CODIGO, Types.NUMERIC));
		parametros.add(getSqlOutCursorParameter());
		parametros.add(new SqlOutParameter(OUT_MENSAJE_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_MENSAJE_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_MENSAJE_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_MENSAJE_CODIGO, codigoMensaje);
		try {
			Map<String, Object> respuesta = consultar(MENSAJE_SCHEMA, MENSAJE_PACKAGE, MENSAJE_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_MENSAJE_RESULTADO);

			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<Mensaje> mensajes = (List<Mensaje>) respuesta.get(OUT_MENSAJE_CURSOR);
				if (mensajes.size() > 0) {
					mensaje = mensajes.get(0);
				} else {
					throw new RobotException(mensajeErrorGenerico);
				}
			} else {
				throw new RobotException(mensajeErrorGenerico);
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{} con el input {}", MENSAJE_SCHEMA, MENSAJE_PACKAGE,
					MENSAJE_PROCEDURE, codigoMensaje, sqle);
			throw new RobotException(mensajeErrorGenerico);
		} catch (RuntimeException e) {
			LOGGER.error("Error al consumir el store {}.{}.{}.", MENSAJE_SCHEMA, MENSAJE_PACKAGE, MENSAJE_PROCEDURE, e);
			throw new RobotException(mensajeErrorGenerico);
		}

		return mensaje;
	}

	/**
	 * Armar mapeo del cursor respuesta del store.
	 *
	 * @return the sql out cursor parameter
	 */
	private SqlOutParameter getSqlOutCursorParameter() {
		return new SqlOutParameter(OUT_MENSAJE_CURSOR, OracleTypes.CURSOR, new RowMapper<Mensaje>() {
			@Override
			public Mensaje mapRow(ResultSet rs, int rowNum) throws SQLException {
				Mensaje mensaje = new Mensaje();
				mensaje.setCodigo(rs.getString(1));
				mensaje.setMensaje(rs.getString(6));
				return mensaje;
			}
		});
	}
}
