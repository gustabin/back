/**
 * 
 */
package ar.com.santanderrio.obp.base.dao.impl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.SqlReturnResultSet;

// TODO: Auto-generated Javadoc
/**
 * Manejar los errores relacionados a la respuesta del store cuando por ejemplo
 * un cursor declarado no esta presente.<br/>
 * Evita UncategorizedSQLException pero requiere controlar los
 * NullPointerException.<br/>
 * 
 * Ej: CallableStatementCallback, Cursor is closed.
 * 
 * @author sergio.e.goldentair
 *
 */
public class CustomJdbcTemplate extends JdbcTemplate {
	/** The Constant LOGGER. */
	private static final Logger LOGGER_TEMPLATE = LoggerFactory.getLogger(CustomJdbcTemplate.class);

	/**
	 * Instantiates a new custom jdbc template.
	 *
	 * @param dataSource
	 *            the data source
	 */
	public CustomJdbcTemplate(DataSource dataSource) {
		super(dataSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.core.JdbcTemplate#extractOutputParameters(java.
	 * sql.CallableStatement, java.util.List)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected Map<String, Object> extractOutputParameters(CallableStatement cs, List<SqlParameter> parameters)
			throws SQLException {
		Map returnedResults = new HashMap();
		int sqlColIndex = 1;
		for (int i = 0; i < parameters.size(); i++) {
			SqlParameter param = parameters.get(i);
			if (param instanceof SqlOutParameter) {
				SqlOutParameter outParam = (SqlOutParameter) param;
				if (outParam.isReturnTypeSupported()) {
					Object out = outParam.getSqlReturnType().getTypeValue(cs, sqlColIndex, outParam.getSqlType(),
							outParam.getTypeName());
					returnedResults.put(outParam.getName(), out);

				} else {
					try {
						Object out = cs.getObject(sqlColIndex);
						if (out instanceof ResultSet) {
							if (outParam.isResultSetSupported()) {
								returnedResults.putAll(processResultSet((ResultSet) out, outParam));
							} else {
								String rsName = outParam.getName();
								SqlReturnResultSet rsParam = new SqlReturnResultSet(rsName, new ColumnMapRowMapper());
								returnedResults.putAll(processResultSet(cs.getResultSet(), rsParam));
								if (LOGGER_TEMPLATE.isDebugEnabled()) {
									LOGGER_TEMPLATE.debug("Added default SqlReturnResultSet parameter named '{}'",
											rsName);
								}
							}
						} else {
							returnedResults.put(outParam.getName(), out);
						}
					} catch (SQLException e) {
						LOGGER_TEMPLATE.error(e.getMessage(), e);
					}
				}
			}
			if (!(param.isResultsParameter())) {
				sqlColIndex++;
			}
		}
		return returnedResults;
	}

}
