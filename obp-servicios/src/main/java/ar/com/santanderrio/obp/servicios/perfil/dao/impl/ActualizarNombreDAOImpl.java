package ar.com.santanderrio.obp.servicios.perfil.dao.impl;

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
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.perfil.dao.ActualizarNombreDAO;

@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class ActualizarNombreDAOImpl extends BaseDatoDAOImpl implements ActualizarNombreDAO{

	private static final Logger LOGGER = LoggerFactory.getLogger(ActualizarNombreDAOImpl.class);
	
	private static final String COD_RETORNO_OK = "0";
	
	private static final String NUP = "p_nup";
	
	private static final String SCHEMA = "hbank";

	private static final String PACKAGE = "HB_PKG_CLIENT_MASTER";

	private static final String ACTUALIZAR_NOMBRE_CLIENTE = "prc_actualizar_nombre_cliente";

	private static final String NOMBRE_CLIENTE = "p_nombre";

    private static final String ERROR_EN_BASE = "Error en base del store al intentar actualizar nombre";
    
	/** Codigo error tecnico. */
	private static final String OUT_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String OUT_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String OUT_AMIGABLE = "p_err_amigable";
	
	@Override
	public String actualizarNombreCliente(String nombre, Long nup) throws DAOException{

		LOGGER.info("Se consultara el procedure {} con el nup {}, y nombre {} ", ACTUALIZAR_NOMBRE_CLIENTE, nup, nombre);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(NUP, Types.NUMERIC));
		parametros.add(new SqlParameter(NOMBRE_CLIENTE, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue(NUP, nup)
				.addValue(NOMBRE_CLIENTE, nombre);

		try {
			Map<String, Object> respuesta = consultar(SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			String errorAmigable = (String) respuesta.get(OUT_AMIGABLE);
			if (COD_RETORNO_OK.equals(codRetorno)){
				LOGGER.info("Nombre actualizado correctamente!");
			} else if (errorAmigable.equals("El nombre que se quiere poner es identico al existente en la base")) {
				codRetorno = COD_RETORNO_OK;
				LOGGER.info("El nombre es idéntico al existente en la base");
			} else if(errorAmigable.equals("En este momento no es posible actualizar el nombre del cliente")) {
				LOGGER.info("Error al consumir el store {}.{}.{}.", SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE);
				throw new DAOException("Error al consultar el store " + ACTUALIZAR_NOMBRE_CLIENTE);
			}
			return codRetorno;
		} catch (DataAccessResourceFailureException dae) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE, in, dae);
			throw new DAOException(dae);
		} catch (SQLException sqle) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE, in, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE, in, ex);
			throw new DAOException(ex);
		} catch (InvalidDataAccessApiUsageException invalidData) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE, in, invalidData);
			throw new DAOException(invalidData);
		} catch (IllegalStateException iex) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE, in, iex);
			throw new DAOException(iex);
		} catch (QueryTimeoutException qte) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA, PACKAGE, ACTUALIZAR_NOMBRE_CLIENTE, in, qte);
			throw new DAOException(qte.getMessage());
		}
		
	}

}
