/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.resumen.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.resumen.dao.ResumenDAO;
import ar.com.santanderrio.obp.servicios.comun.resumen.entity.ResumenInEntity;

/**
 * The Class ResumenDAOImpl.
 */
@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class ResumenDAOImpl extends BaseDatoDAOImpl implements ResumenDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResumenDAOImpl.class);

	/** Constante COD_RESULTADO_OK. */
	private static final String COD_RESULTADO_OK = "0";

	/** The Constant IN_RESUMEN_NUP. */
	private static final String IN_RESUMEN_NUP = "p_nup";

	/** The Constant IN_RESUMEN_DETALLE_CAMBIO. */
	private static final String IN_RESUMEN_DETALLE_CAMBIO = "p_detalle_cambio";

	/** The Constant OUT_RESUMEN_RESULTADO. */
	private static final String OUT_RESUMEN_RESULTADO = "p_resultado";

	/** The Constant OUT_RESUMEN_ERROR_TECNICO. */
	private static final String OUT_RESUMEN_TECNICO = "p_err_tecnico";

	/** The Constant OUT_RESUMEN_ERROR_AMIGABLE. */
	private static final String OUT_RESUMEN_AMIGABLE = "p_err_amigable";
	/** The Constant RESUMEN_SCHEMA. */
	private static final String RESUMEN_SCHEMA = "hbank";

	/** The Constant CONTRATO_PACKAGE. */
	private static final String RESUMEN_PACKAGE = "HB_PKG_ENC_RES";

	/** The Constant RESUMEN_REGISTRAR_PROCEDURE. */
	private static final String RESUMEN_REGISTRAR_PROCEDURE = "prc_insertar_enc_res";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String grabarMotivo(ResumenInEntity input) throws DAOException {
		String retorno = "ERROR";
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_RESUMEN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_RESUMEN_DETALLE_CAMBIO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESUMEN_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESUMEN_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_RESUMEN_AMIGABLE, Types.VARCHAR));
		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_RESUMEN_NUP, input.getNup())
				.addValue(IN_RESUMEN_DETALLE_CAMBIO, input.getMotivo());

		try {
			Map<String, Object> respuesta = consultar(RESUMEN_SCHEMA, RESUMEN_PACKAGE, RESUMEN_REGISTRAR_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESUMEN_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				retorno = "OK";
			} else {
				LOGGER.info("Error controlado al consultar el store {} con nup {} y motivo {}.",
						RESUMEN_REGISTRAR_PROCEDURE, input.getNup(), input.getMotivo());

			}
		} catch (SQLException sqle) {
			manejarErrorInesperado(input, sqle);
		} catch (UncategorizedSQLException sqle) {
			manejarErrorInesperado(input, sqle);
		}
		return retorno;
	}

	/**
	 * Maneja error inesperado al ejecutar el store.
	 *
	 * @param input
	 *            the input
	 * @param sqle
	 *            the sqle
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void manejarErrorInesperado(ResumenInEntity input, Exception sqle) throws DAOException {
		LOGGER.error("Error al grabar en el store {}.{}.{} con nup {} y motivo {}.", RESUMEN_SCHEMA, RESUMEN_PACKAGE,
				RESUMEN_REGISTRAR_PROCEDURE, input.getNup(), input.getMotivo(), sqle);
		throw new DAOException(sqle);
	}

}
