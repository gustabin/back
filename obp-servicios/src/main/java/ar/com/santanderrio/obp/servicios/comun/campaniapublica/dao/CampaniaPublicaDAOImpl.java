package ar.com.santanderrio.obp.servicios.comun.campaniapublica.dao;
 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.impl.LegalDAOImpl;
import oracle.jdbc.OracleTypes;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class CampaniaPublicaDAOImpl extends BaseDatoDAOImpl implements CampaniaPublicaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LegalDAOImpl.class);

	/** SCHEMA. */
	private static final String SCHEMA_NAME = "hbank";

	/** PACKAGE. */
	private static final String PACKAGE_NAME = "HB_PKG_CAMPANIAS_PUBLICAS";

	/** PROCEDURE LISTAR. */
	private static final String PROCEDURE_LISTAR_NAME = "prc_listar_campanias_publicas";
	
	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";
	
	/** nombre del parametro que referencia al cursor retornado. */
	private static final String PARAM_CURSOR = "p_cursor";

	/** Codigo error tecnico. */
	private static final String PARAM_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String PARAM_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String PARAM_AMIGABLE = "p_err_amigable";
	
	
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_CAMPANIAS_PUBLICAS })
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> obtenerGoToCampaniasPublicas() throws DAOException {
		LOGGER.info("Consultar listado de Legales.");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(addSqlOutCursorParameter());
		parametros.add(new SqlOutParameter(PARAM_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_AMIGABLE, Types.VARCHAR));

		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, null,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(PARAM_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				Map<String, String> legalesMap = (Map<String, String>) respuesta.get(PARAM_CURSOR);
				if (legalesMap.isEmpty()) {
					throw new DAOException("No se retorno ninguna campania publica.");
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
	
	private SqlOutParameter addSqlOutCursorParameter() {
		return new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, String> mapRet = new LinkedHashMap<String, String>();
				while (rs.next()) {
					mapRet.put(rs.getString("codigo_campania"), rs.getString("go_to"));
				}
				return mapRet;
			}
		});
	}

	@CacheEvict(value = CacheConstants.Names.CACHE_CAMPANIAS_PUBLICAS, allEntries = true)
	@Override
	public void limpiarCampaniasPublicas() {
		LOGGER.info("Se limpia la cache.");
		
	}

}
