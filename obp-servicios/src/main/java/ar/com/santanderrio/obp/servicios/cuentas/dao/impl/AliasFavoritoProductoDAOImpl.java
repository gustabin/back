/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
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
import ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;
import oracle.jdbc.OracleTypes;

/**
 * The Class AliasFavoritoProductoDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class AliasFavoritoProductoDAOImpl extends BaseDatoDAOImpl implements AliasFavoritoProductoDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AliasFavoritoProductoDAOImpl.class);

	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";

	/** SCHEMA. */
	private static final String ALIAS_FAV_SCHEMA = "hbank";

	/** PACKAGE alias favorito. */
	private static final String ALIAS_FAV_PACKAGE = "HB_PKG_ALIAS_FAVORITA";

	/** PROCEDURE obtener. */
	private static final String ALIAS_FAV_OBTENER_PROCEDURE = "prc_obtener_alias_favoritos";

	/** PROCEDURE actualizar alias. */
	private static final String ALIAS_ACTUALIZAR_PROCEDURE = "prc_actualizar_alias";

	/** PROCEDURE actualizar favorito. */
	private static final String FAVORITO_ACTUALIZAR_PROCEDURE = "prc_actualizar_favorito";

	/** Parametro de entrada. */
	private static final String IN_ALIAS_OBTENER = "p_nup";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String OUT_ALIAS_OBTENER_CURSOR = "p_cursor";

	/** Codigo error tecnico. */
	private static final String OUT_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String OUT_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String OUT_AMIGABLE = "p_err_amigable";

	/** Parametro de entrada nup actualizar. */
	private static final String IN_ACTUALIZAR_NUP = "p_nup";

	/** Parametro de entrada alias actualizar. */
	private static final String IN_ACTUALIZAR_ALIAS = "p_alias";

	/** Parametro de entrada favorito actualizar. */
	private static final String IN_ACTUALIZAR_FAVORITO = "p_favorito";

	/** The Constant IN_ACTUALIZAR_NROCTAPROD. */
	private static final String IN_ACTUALIZAR_NROCTAPROD = "p_numero_cuenta_producto";
	
	   /** The Constant ERROR_EN_BASE. */
    private static final String ERROR_EN_BASE = "Error en base del store {}.{}.{} al solicitar informacion Advantage de {}.";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO#
	 * obtenerAliasFavoritoNup(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AliasFavoritoProducto> obtenerAliasFavoritoNup(Long nup) throws DAOException {
		List<AliasFavoritoProducto> listaAliasFavoritos = new ArrayList<AliasFavoritoProducto>();

		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_ALIAS_OBTENER, Types.NUMERIC));
		parametros.add(new SqlOutParameter(OUT_ALIAS_OBTENER_CURSOR, OracleTypes.CURSOR,
				new RowMapper<AliasFavoritoProducto>() {
					@Override
					public AliasFavoritoProducto mapRow(ResultSet rs, int rowNum) throws SQLException {
						AliasFavoritoProducto aliasFavoritoProducto = new AliasFavoritoProducto();
						aliasFavoritoProducto.setAlias(rs.getString("alias"));
						aliasFavoritoProducto.setFavorita(rs.getBoolean("favorita"));
						aliasFavoritoProducto.setNroCtaProducto(rs.getString("nroctaproducto"));
						return aliasFavoritoProducto;
					}
				}));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_ALIAS_OBTENER, nup);
		try {
			Map<String, Object> respuesta = consultar(ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, ALIAS_FAV_OBTENER_PROCEDURE,
					in, parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				listaAliasFavoritos = (List<AliasFavoritoProducto>) respuesta.get(OUT_ALIAS_OBTENER_CURSOR);
				if (CollectionUtils.isNotEmpty(listaAliasFavoritos)) {
					for (AliasFavoritoProducto item : listaAliasFavoritos) {
						item.setNup(nup);
					}
				}
			}
		} catch (DataAccessResourceFailureException dae) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, ALIAS_FAV_OBTENER_PROCEDURE, nup, dae);
            throw new DAOException(dae);
        } catch (SQLException sqle) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, ALIAS_FAV_OBTENER_PROCEDURE, nup, sqle);
            throw new DAOException(sqle);
        } catch (UncategorizedSQLException ex) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, ALIAS_FAV_OBTENER_PROCEDURE, nup, ex);
            throw new DAOException(ex);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, ALIAS_FAV_OBTENER_PROCEDURE, nup, invalidData);
            throw new DAOException(invalidData);
        } catch (IllegalStateException iex) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, ALIAS_FAV_OBTENER_PROCEDURE, nup, iex);
            throw new DAOException(iex);
        } catch (QueryTimeoutException qte) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, ALIAS_FAV_OBTENER_PROCEDURE, nup, qte);
            throw new DAOException(qte.getMessage());
        }
		
		return listaAliasFavoritos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO#
	 * actualizaAlias(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public void actualizaAlias(Long nup, String nroCtaProducto, String alias) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, nroCtaProducto {} y alias {}.",
				ALIAS_ACTUALIZAR_PROCEDURE, nup, nroCtaProducto, alias);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_ACTUALIZAR_NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_ACTUALIZAR_ALIAS, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_ACTUALIZAR_NROCTAPROD, Types.NUMERIC));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_ACTUALIZAR_NUP, nup)
				.addValue(IN_ACTUALIZAR_ALIAS, alias).addValue(IN_ACTUALIZAR_NROCTAPROD, nroCtaProducto);
		actualizar(parametros, in, ALIAS_ACTUALIZAR_PROCEDURE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.cuentas.dao.AliasFavoritoProductoDAO#
	 * actualizaFavorito(java.lang.Long, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public void actualizaFavorito(Long nup, String nroCtaProducto, Boolean favorito) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el nup {}, nroCtaProducto {} y favorito {}.",
				FAVORITO_ACTUALIZAR_PROCEDURE, nup, nroCtaProducto, favorito);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_ACTUALIZAR_NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_ACTUALIZAR_FAVORITO, Types.NUMERIC));
		parametros.add(new SqlParameter(IN_ACTUALIZAR_NROCTAPROD, Types.NUMERIC));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_ACTUALIZAR_NUP, nup)
				.addValue(IN_ACTUALIZAR_FAVORITO, favorito ? 1 : 0).addValue(IN_ACTUALIZAR_NROCTAPROD, nroCtaProducto);
		actualizar(parametros, in, FAVORITO_ACTUALIZAR_PROCEDURE);
	}

	/**
	 * Generalizar la llamada al store para las consultas de alias y favoritos.
	 *
	 * @param parametros
	 *            the parametros
	 * @param in
	 *            the in
	 * @param aliasActualizarProcedure
	 *            the alias actualizar procedure
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void actualizar(List<SqlParameter> parametros, SqlParameterSource in, String aliasActualizarProcedure)
			throws DAOException {
		try {
			Map<String, Object> respuesta = consultar(ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, aliasActualizarProcedure, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (!COD_RETORNO_OK.equals(codRetorno)) {
				LOGGER.info("Error al consumir el store {}.{}.{}.", ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE,
						aliasActualizarProcedure);
				throw new DAOException("Error al consultar el store " + aliasActualizarProcedure);
			}
		} catch (DataAccessResourceFailureException dae) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, aliasActualizarProcedure, in, dae);
            throw new DAOException(dae);
        } catch (SQLException sqle) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, aliasActualizarProcedure, in, sqle);
            throw new DAOException(sqle);
        } catch (UncategorizedSQLException ex) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, aliasActualizarProcedure, in, ex);
            throw new DAOException(ex);
        } catch (InvalidDataAccessApiUsageException invalidData) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, aliasActualizarProcedure, in, invalidData);
            throw new DAOException(invalidData);
        } catch (IllegalStateException iex) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, aliasActualizarProcedure, in, iex);
            throw new DAOException(iex);
        } catch (QueryTimeoutException qte) {
            LOGGER.error(ERROR_EN_BASE, ALIAS_FAV_SCHEMA, ALIAS_FAV_PACKAGE, aliasActualizarProcedure, in, qte);
            throw new DAOException(qte.getMessage());
        }
	}

}
