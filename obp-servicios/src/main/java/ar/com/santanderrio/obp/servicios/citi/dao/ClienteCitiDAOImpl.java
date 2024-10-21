/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.dao;

import java.math.BigDecimal;
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
import ar.com.santanderrio.obp.servicios.citi.entities.RepuestaSPClienteCitiEntity;


/**
 * The Class ClienteCitiDAOImpl.
 */
@Repository
@TargetSystem(DataBase.CITI)
public class ClienteCitiDAOImpl extends BaseDatoDAOImpl implements ClienteCitiDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteCitiDAOImpl.class);

	/** The Constant SCHEMA NAME. */
	private static final String SCHEMA_NAME = "OLYMPO";

	/** The Constant PACKAGE_NAME. */
	private static final String PACKAGE_NAME = "PKG_CITI_CLIENT_IDENTIFICATION";

	/** The Constant PROCEDURE LISTAR NAME. */
	private static final String PROCEDURE_LISTAR_NAME = "CITI_CLIENT_IDENTIFICATION";

	/** The Constant PI CANAL. */
	private static final String PI_CANAL = "pi_canal";

	/** The Constant PI NUP. */
	private static final String PI_NUP = "pi_nup";

	/** The Constant LOGGER. */
	private static final String PI_USUARIO = "pi_usuario";

	/** The Constant PO_CODIGO. */
	private static final String PO_CODIGO = "po_codigo";

	/** The Constant PO_NUP. */
	private static final String PO_NUP = "po_nup";

	/** The Constant PO_SALDOS. */
	private static final String PO_SALDOS = "po_saldos";

	/** The Constant POD_PRODUCTOS. */
	private static final String POD_PRODUCTOS = "po_productos";

	/** The PO_DESCRIPCION_ERROR. */
	private static final String PO_DESCRIPCION_ERROR = "po_descripcion_error";

	/** The Constant COD_RETORNO_OK. */
	private static final BigDecimal COD_RETORNO_OK = new BigDecimal("0");

	/** The Constant OBP. */
	private static final String OBP = "OBP";

	/** The Constant ERROR_EN_BASE. */
	private static final String ERROR_EN_BASE = "Error en base del store {}.{}.{} al solicitar informacion.";

	/** The Constant MENSAJE_ERROR_EN_STORE. */
	private static final String MENSAJE_ERROR_EN_STORE = "Error en store {}.{}.{}. al solicitar la informacion.";

	/** The Constant ERROR_TIMEOUT. */
	private static final String ERROR_TIMEOUT = "Error de Timeout.";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.citi.dao.ClienteCitiDAO#
	 * consultarCitiClienteIdentificacion(java.lang.String)
	 */
	@Override
	public RepuestaSPClienteCitiEntity consultarCitiClienteIdentificacion(String nup) throws DAOException {
		LOGGER.info("Consultar si el cliente es CITI.");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(PI_CANAL, Types.VARCHAR));
		parametros.add(new SqlParameter(PI_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(PI_USUARIO, Types.VARCHAR));

		parametros.add(new SqlOutParameter(PO_CODIGO, Types.NUMERIC));
		parametros.add(new SqlOutParameter(PO_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PO_SALDOS, Types.NUMERIC));
		parametros.add(new SqlOutParameter(POD_PRODUCTOS, Types.NUMERIC));
		parametros.add(new SqlOutParameter(PO_DESCRIPCION_ERROR, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(PI_CANAL, OBP).addValue(PI_NUP, nup)
		        .addValue(PI_USUARIO, nup);
		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, in,
			        parametros.toArray(new SqlParameter[parametros.size()]));
			BigDecimal codRetorno = (BigDecimal) respuesta.get(PO_CODIGO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				return new RepuestaSPClienteCitiEntity((String) respuesta.get(PO_NUP),
				        (BigDecimal) respuesta.get(PO_SALDOS), (BigDecimal) respuesta.get(POD_PRODUCTOS));
			} else {
				throw new DAOException((String) respuesta.get(PO_DESCRIPCION_ERROR));
			}
		} catch (DataAccessResourceFailureException dae) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, dae);
			throw new DAOException(dae);
		} catch (SQLException sqle) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error(ERROR_EN_BASE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, ex);
			throw new DAOException(ex);
		} catch (InvalidDataAccessApiUsageException invalidData) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, invalidData);
			throw new DAOException(invalidData);
		} catch (IllegalStateException iex) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, iex);
			throw new DAOException(iex);
		} catch (QueryTimeoutException qte) {
			LOGGER.error(ERROR_TIMEOUT, SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_LISTAR_NAME, qte);
			throw new DAOException(qte.getMessage());
		}
	}

}
