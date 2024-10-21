package ar.com.santanderrio.obp.servicios.nuevarecarga.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.QueryTimeoutException;
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
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;
import oracle.jdbc.OracleTypes;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class AgendaCelularDAOImpl  extends BaseDatoDAOImpl implements AgendaCelularDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaCelularDAOImpl.class);
	
	private static final String COD_RETORNO_OK = "0";
	
	/** Parametro de entrada nup actualizar. */
	private static final String NUP = "p_nup";
	
	private static final String CELULAR_SCHEMA = "hbank";

	private static final String CELULAR_PACKAGE = "HB_PKG_CELULARES";

	private static final String CELULAR_OBTENER_PROCEDURE = "prc_obtener_celulares";

	private static final String CELULAR_AGREGAR_PROCEDURE = "prc_agregar_celular";

	private static final String CELULAR_ACTUALIZAR_ALIAS_PROCEDURE = "prc_actualizar_alias_celular";
	
	private static final String CELULAR_ELIMINAR_PROCEDURE = "prc_eliminar_celular";
	
	private static final String CELULAR_EXISTE_PROCEDURE = "prc_existe_celular";
	
	private static final String CELULAR_OBTENER_ALIAS = "prc_obtener_alias";

	private static final String NRO_CELULAR = "p_numero";

	private static final String ALIAS_CELULAR = "p_alias";
	
	private static final String COMPANIA_CELULAR = "p_compania";

    private static final String ERROR_EN_BASE = "Error en base del store al intentar agregar celular";
    
	/** Codigo error tecnico. */
	private static final String OUT_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String OUT_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String OUT_AMIGABLE = "p_err_amigable";
	
	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_CELULAR_OBTENER_CURSOR = "p_cursor";

	@Override
	public void agregarCelular(Long nup, String numero, String alias, String compania) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, celular {} alias {} y compania {}.",
				CELULAR_AGREGAR_PROCEDURE, nup, numero, alias, compania);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(NRO_CELULAR, Types.VARCHAR));
		parametros.add(new SqlParameter(ALIAS_CELULAR, Types.VARCHAR));
		parametros.add(new SqlParameter(COMPANIA_CELULAR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(NUP, nup)
				.addValue(NRO_CELULAR, numero)
				.addValue(ALIAS_CELULAR, alias)
				.addValue(COMPANIA_CELULAR, compania);
		ejecutarProcedure(parametros, in, CELULAR_AGREGAR_PROCEDURE);
	}

	@Override
	public void actualizarAliasCelular(Long nup, String numero, String alias) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, celular {} alias {}",
				CELULAR_ACTUALIZAR_ALIAS_PROCEDURE, nup, numero, alias);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(NRO_CELULAR, Types.VARCHAR));
		parametros.add(new SqlParameter(ALIAS_CELULAR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(NUP, nup)
				.addValue(NRO_CELULAR, numero)
				.addValue(ALIAS_CELULAR, alias);
		ejecutarProcedure(parametros, in, CELULAR_ACTUALIZAR_ALIAS_PROCEDURE);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CelularView> obtenerCelularesNup(Long nup) throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(NUP, Types.NUMERIC));
		parametros.add(new SqlOutParameter(OUT_CELULAR_OBTENER_CURSOR, OracleTypes.CURSOR,
				new RowMapper<CelularView>() {
					@Override
					public CelularView mapRow(ResultSet rs, int rowNum) throws SQLException {
						CelularView celular = new CelularView();
						celular.setNumero(rs.getString("numero"));
						celular.setAlias(rs.getString("alias"));
						celular.setCompania(rs.getString("compania"));
						return celular;
					}
				}));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(NUP, nup);
		Map<String, Object> respuesta = ejecutarProcedure(parametros, in, CELULAR_OBTENER_PROCEDURE);
		return (List<CelularView>) respuesta.get(OUT_CELULAR_OBTENER_CURSOR);

	}

	@Override
	public void eliminarCelular(Long nup, String numero) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, celular {}",
				CELULAR_ELIMINAR_PROCEDURE, nup, numero);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(NRO_CELULAR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(NUP, nup)
				.addValue(NRO_CELULAR, numero);
		ejecutarProcedure(parametros, in, CELULAR_ELIMINAR_PROCEDURE);
	}
	
	@Override
	public String obtenerAlias(Long nup, String numero) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, celular {}",
				CELULAR_OBTENER_ALIAS, nup, numero);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(NRO_CELULAR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(ALIAS_CELULAR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(NUP, nup)
				.addValue(NRO_CELULAR, numero);
		Map<String, Object> respuesta = ejecutarProcedure(parametros, in, CELULAR_OBTENER_ALIAS);
		
		return (String) respuesta.get(ALIAS_CELULAR);
	}
	
	@Override
	public void existeCelular(Long nup, String numero) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, celular {}.",
				CELULAR_AGREGAR_PROCEDURE, nup, numero);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(NRO_CELULAR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(NUP, nup)
				.addValue(NRO_CELULAR, numero);
		ejecutarProcedure(parametros, in, CELULAR_EXISTE_PROCEDURE);
	}
	
	private Map<String, Object> ejecutarProcedure(List<SqlParameter> parametros, SqlParameterSource in, String celularProcedure)
			throws DAOException {
		try {
			Map<String, Object> respuesta = consultar(CELULAR_SCHEMA, CELULAR_PACKAGE, celularProcedure, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (!COD_RETORNO_OK.equals(codRetorno)) {
				LOGGER.info("Error al consumir el store {}.{}.{}.", CELULAR_SCHEMA, CELULAR_PACKAGE,
						celularProcedure);
				throw new DAOException("Error al consultar el store " + celularProcedure);
			}
			return respuesta;
		} catch (DataAccessResourceFailureException dae) {
			LOGGER.error(ERROR_EN_BASE, CELULAR_SCHEMA, CELULAR_PACKAGE, celularProcedure, in, dae);
			throw new DAOException(dae);
		} catch (SQLException sqle) {
			LOGGER.error(ERROR_EN_BASE, CELULAR_SCHEMA, CELULAR_PACKAGE, celularProcedure, in, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error(ERROR_EN_BASE, CELULAR_SCHEMA, CELULAR_PACKAGE, celularProcedure, in, ex);
			throw new DAOException(ex);
		} catch (InvalidDataAccessApiUsageException invalidData) {
			LOGGER.error(ERROR_EN_BASE, CELULAR_SCHEMA, CELULAR_PACKAGE, celularProcedure, in, invalidData);
			throw new DAOException(invalidData);
		} catch (IllegalStateException iex) {
			LOGGER.error(ERROR_EN_BASE, CELULAR_SCHEMA, CELULAR_PACKAGE, celularProcedure, in, iex);
			throw new DAOException(iex);
		} catch (QueryTimeoutException qte) {
			LOGGER.error(ERROR_EN_BASE, CELULAR_SCHEMA, CELULAR_PACKAGE, celularProcedure, in, qte);
			throw new DAOException(qte.getMessage());
		}
	}
	
	

}