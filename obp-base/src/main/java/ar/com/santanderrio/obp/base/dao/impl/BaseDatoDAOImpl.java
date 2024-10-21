/**
 * 
 */
package ar.com.santanderrio.obp.base.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.BaseDatoDAO;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDatoDAOImpl.
 *
 * @author sergio.e.goldentair
 */
@Repository
public class BaseDatoDAOImpl implements BaseDatoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDatoDAOImpl.class);

	/** The data source. */
	@Autowired
	private SystemRoutingDataSource dataSource;

	/** The time out. */
	@Value("${DB.TIMEOUT:30000}")
	private Long queryTimeout;
	
	/** The tags. */
	@Value("${TAGS.A.ELIMINAR.DB}")
	private String tags;

	/**
	 * Realiza la consulta a un store procedure que no esta dentro de un
	 * package.
	 *
	 * @param procedureName
	 *            the procedure name
	 * @param in
	 *            the in
	 * @param parametros
	 *            the parametros
	 * @return the map
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Map<String, Object> consultarProcedimiento(String procedureName, SqlParameterSource in,
			SqlParameter... parametros) throws SQLException {

		return this.consultar("", "", procedureName, in, parametros);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.legal.dao.impl.BaseDatoDAO#
	 * consultar(java.lang.String, java.lang.String, java.lang.String,
	 * org.springframework.jdbc.core.namedparam.SqlParameterSource,
	 * org.springframework.jdbc.core.SqlParameter[])
	 */
	@Override
	public Map<String, Object> consultar(String schemaName, String packageName, String procedureName,
			SqlParameterSource in, SqlParameter... parametros) throws SQLException {
		return consultarFunctionStore(schemaName, packageName, procedureName, in, false, parametros);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.dao.BaseDatoDAO#consultarFuncion(java.lang.
	 * String, java.lang.String, java.lang.String,
	 * org.springframework.jdbc.core.namedparam.SqlParameterSource,
	 * org.springframework.jdbc.core.SqlParameter[])
	 */
	@Override
	public Map<String, Object> consultarFuncion(String schemaName, String packageName, String functionName,
			SqlParameterSource in, SqlParameter... parametros) throws SQLException {
		return consultarFunctionStore(schemaName, packageName, functionName, in, true, parametros);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.base.dao.BaseDatoDAO#insertTable(java.lang.String, java.lang.String, org.springframework.jdbc.core.namedparam.MapSqlParameterSource)
	 */
	@Override
	public void insertTable(String schemaName, String tableName, MapSqlParameterSource in ) throws SQLException {
		if (loguearDatos()) {
			LOGGER.info("Tabla :{}", tableName);
		}
		if (LOGGER.isDebugEnabled() && loguearDatos()) {
			logParams(in);
		}
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		Long milisegEnSegundos = TimeUnit.MILLISECONDS.toSeconds(queryTimeout);
		jdbcTemplate.setQueryTimeout(milisegEnSegundos.intValue());
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
										.withSchemaName(schemaName)
										.withTableName(tableName);
		insert.withoutTableColumnMetaDataAccess();
		insert.setColumnNames(new ArrayList<String>(in.getValues().keySet()));
		
		insert.execute(in);
	}

	/**
	 * Unificar logica de invocacion a store o funciones.
	 *
	 * @param schemaName
	 *            the schema name
	 * @param packageName
	 *            the package name
	 * @param operacionName
	 *            the operacion name
	 * @param in
	 *            the in
	 * @param isfuncion
	 *            the isfuncion
	 * @param parametros
	 *            the parametros
	 * @return the map
	 */
	private Map<String, Object> consultarFunctionStore(String schemaName, String packageName, String operacionName,
			SqlParameterSource in, boolean isfuncion, SqlParameter[] parametros) {
		
		LOGGER.info("STORE :{}.{}.{}", schemaName, packageName, operacionName);
		logParams(in);
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		jdbcTemplate.setResultsMapCaseInsensitive(true);
		Long milisegEnSegundos = TimeUnit.MILLISECONDS.toSeconds(queryTimeout);
		jdbcTemplate.setQueryTimeout(milisegEnSegundos.intValue());
		SimpleJdbcCall procRead = null;
		if (isfuncion) {
			procRead = new SimpleJdbcCall(jdbcTemplate).withSchemaName(schemaName).withCatalogName(packageName)
					.withFunctionName(operacionName).declareParameters(parametros).withoutProcedureColumnMetaDataAccess();
		} else {
			procRead = new SimpleJdbcCall(jdbcTemplate).withSchemaName(schemaName).withCatalogName(packageName)
					.withProcedureName(operacionName).declareParameters(parametros).withoutProcedureColumnMetaDataAccess();
		}
		Map<String, Object> respuesta = procRead.execute(in);
		if (LOGGER.isDebugEnabled() && loguearDatos()) {
			logResultado(respuesta);
		}
		return respuesta;
	}

	/**
	 * Retornar la implementacion de jdbcTemplate segun las necesidades.
	 *
	 * @return the jdbc template
	 */
	private JdbcTemplate getJdbcTemplate() {
		if (customJdbc()) {
			return new CustomJdbcTemplate(dataSource);
		} else {
			return new JdbcTemplate(dataSource);
		}
	}

	/**
	 * Por defecto utiliza el jdbcTemplate provisto por spring, si se desea
	 * cambiar para que utilice el que sobreescribe la manera de leer los
	 * parametros retornados por el store y no rompa ante uno declarado que
	 * llega vacio poner en TRUE el retorno de este metodo.<br/>
	 * Evita UncategorizedSQLException pero requiere controlar los
	 * NullPointerException.
	 *
	 * @return true, if successful
	 */
	protected boolean customJdbc() {
		return false;
	}

	/**
	 * Por default se loguea lo que este marcardo.<br/>
	 * Sobreescribir este metodo en el dao que limpia el token cuando cierra la
	 * session.
	 *
	 * @return true, if successful
	 */
	protected boolean loguearDatos() {
		return true;
	}

	/**
	 * Log resultado.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	private void logResultado(Map<String, Object> respuesta) {
		Iterator<Entry<String, Object>> iterator = respuesta.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			LOGGER.info("OUT KEY :" + "." + entry.getKey() + " VALUE :" + entry.getValue());
		}

	}

	/**
	 * Log params.
	 *
	 * @param parametros
	 *            the parametros
	 */
	private void logParams(SqlParameterSource parametros) {
		if (parametros != null) {
			MapSqlParameterSource mapParametros = (MapSqlParameterSource) parametros;
			Map<String, Object> params = mapParametros.getValues();
			Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				if (validTag(entry.getKey())){
					LOGGER.info("IN KEY :" + "." + entry.getKey() + " VALUE :" + entry.getValue());
				}else {
					LOGGER.info("IN KEY :" + "." + entry.getKey() + " VALUE :" + "*********");
				}
				
			}
		}

	}
	
	/**
	 * Valido si el tag se debe loguear o no.
	 *
	 * @param key the key
	 * @return true, if successful
	 */
	private boolean validTag(String key){
		if (StringUtils.isEmpty(tags)) {
			return true;
		}
		String[] tagsAEliminar = tags.split(",");
		for (String palabraABuscar : tagsAEliminar) {
			if (!StringUtils.contains(key, palabraABuscar)) {
				continue;
			}
			return false;
		}
		return true;
	}
	
	
	

}
