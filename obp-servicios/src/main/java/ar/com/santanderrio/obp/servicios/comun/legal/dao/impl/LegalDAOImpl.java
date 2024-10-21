/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import oracle.jdbc.OracleTypes;

/**
 * DAO para obtener los legales de la aplicacion.
 *
 * @author B025331
 */
/**
 * @author sergio.e.goldentair
 *
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class LegalDAOImpl extends BaseDatoDAOImpl implements LegalDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LegalDAOImpl.class);

	/** SCHEMA. */
	private static final String SCHEMA_NAME = "hbank";

	/** PACKAGE. */
	private static final String PACKAGE_NAME = "HB_PKG_LEGALES";

	/** PROCEDURE LISTAR. */
	private static final String PROCEDURE_LISTAR_NAME = "prc_listar_legales_vigentes";

	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";

	/** Parametro de entrada fecha. */
	private static final String IN_LEGAL_FECHA = "p_fecha_actual";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String PARAM_CURSOR = "p_cursor";

	/** Codigo error tecnico. */
	private static final String PARAM_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String PARAM_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String PARAM_AMIGABLE = "p_err_amigable";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO#obtenerLegales
	 * ()
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_LEGALES })
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> obtenerLegales() throws DAOException {
		LOGGER.info("Consultar listado de Legales.");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_LEGAL_FECHA, Types.DATE));
		parametros.add(addSqlOutCursorParameter());
		parametros.add(new SqlOutParameter(PARAM_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_LEGAL_FECHA,
				new Date(System.currentTimeMillis()));
		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(PARAM_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				Map<String, String> legalesMap = (Map<String, String>) respuesta.get(PARAM_CURSOR);
				if (legalesMap.isEmpty()) {
					throw new DAOException("No se retornaron legales.");
				} else {
					return legalesMap;
				}
			} else {
				throw new DAOException((String) respuesta.get(PARAM_AMIGABLE));
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME,
					sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}.", SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME,
					sqle);
			throw new DAOException(sqle);
		}
	}

	/**
	 * Agregar logica de manejo del cursor para la salida del store invocado.
	 * 
	 * @return SqlOutParameter
	 */
	private SqlOutParameter addSqlOutCursorParameter() {
		return new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, String> mapRet = new LinkedHashMap<String, String>();
				while (rs.next()) {
					mapRet.put(rs.getString("id"), rs.getString("texto"));
				}
				return mapRet;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO#
	 * obtenerLegalPorCodigo(java.lang.String)
	 */
	@Override
	public String obtenerLegal(final String codigo) throws DAOException {
		/**
		 * Pide el bean al contexto ya que para utilizar la cache hay que entrar
		 * por metodos publicos por ser un proxy. No ir directo ya que no
		 * devuelve el valor cacheado.
		 *
		 */
		String legal = ContextHolder.getContext().getBean(LegalDAO.class).obtenerLegales().get(codigo);
		if (StringUtils.isEmpty(legal)) {
			LOGGER.info("El codigo {} de legal consultado no es valido.", codigo);
			throw new DAOException("El codigo consultado no es valido.");
		}
		return legal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO#vaciarLegales(
	 * )
	 */
	@CacheEvict(value = "legalesCache", allEntries = true)
	@Override
	public void limpiarLegales() {
		LOGGER.info("Se limpia la cache.");
	}

}