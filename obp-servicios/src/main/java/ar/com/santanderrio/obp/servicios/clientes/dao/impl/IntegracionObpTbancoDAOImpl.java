/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao.impl;

import java.math.BigDecimal;
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
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.dao.IntegracionObpTbancoDAO;

/**
 * The Class IntegracionObpTbancoDAOImpl.
 *
 * @author sergio.e.goldentair
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class IntegracionObpTbancoDAOImpl extends BaseDatoDAOImpl implements IntegracionObpTbancoDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(IntegracionObpTbancoDAOImpl.class);

	/** SCHEMA. */
	private static final String SCHEMA_NAME = "hbank";

	/** PACKAGE que determina destino del usuario. */
	private static final String PACKAGE_CLIENTE_TBANCO_NAME = "HB_PKG_CLIENTE_TBANCO";

	/** PROCEDURE HABILITADOTBANCO. */
	private static final String PROCEDURE_HABILITADOTBANCO_NAME = "HABILITADOTBANCO";

	/** Parametro de entrada nup. */
	private static final String PARAM_IN_NUP = "p_nup";

	/** 1 si esta habilitado a tbanco, 0 a obp y -1 si hay error. */
	private static final String PARAM_RESULTADO = "p_resultado";

	/** Codigo error tecnico oracle. */
	private static final String PARAM_TECNICO = "p_err_tecnico";

	/** Descripcion amigable del error. */
	private static final String PARAM_AMIGABLE = "p_err_amigable";

	/** HABILITO_NUP_EN_TBANCO es uno ya que esta habilitado en TBANCO. */
	private static final BigDecimal HABILITO_NUP_EN_TBANCO = new BigDecimal(1);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.clientes.dao.IntegracionObpTbancoDAO#
	 * usuarioHabilitadoAccederTbanco(java.lang.String)
	 */
	@Override
	public Boolean usuarioHabilitadoAccederTbanco(String nup) {
		LOGGER.debug("Consultar si el nup {} esta habilitado para ingresar a OBP o TBANCO.", nup);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(PARAM_IN_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_RESULTADO, Types.NUMERIC));
		parametros.add(new SqlOutParameter(PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(PARAM_IN_NUP, nup);
		try {
			Map<String, Object> respuestaStore = consultar(SCHEMA_NAME, PACKAGE_CLIENTE_TBANCO_NAME,
					PROCEDURE_HABILITADOTBANCO_NAME, in, parametros.toArray(new SqlParameter[parametros.size()]));
			return procesarRespuesta(respuestaStore, nup);
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}. se redirecciona a Obp", SCHEMA_NAME,
					PACKAGE_CLIENTE_TBANCO_NAME, PROCEDURE_HABILITADOTBANCO_NAME, sqle);
			return Boolean.FALSE;
		} catch (UncategorizedSQLException usqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}. se redirecciona a Obp", SCHEMA_NAME,
					PACKAGE_CLIENTE_TBANCO_NAME, PROCEDURE_HABILITADOTBANCO_NAME, usqle);
			return Boolean.FALSE;
		}

	}

	/**
	 * Si recibo un 1 indica que el nup esta habilitado para loguearse en
	 * TBanco, en otro caso debe ir a OBP.<br/>
	 * Si recibe un -1 indica que hubo un error en el store.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param nup
	 *            the nup
	 * @return the boolean
	 */
	private Boolean procesarRespuesta(Map<String, Object> respuesta, String nup) {
		BigDecimal estadoNup = (BigDecimal) respuesta.get(PARAM_RESULTADO);
		if (HABILITO_NUP_EN_TBANCO.equals(estadoNup)) {
			return Boolean.TRUE;
		} else {
			if (BigDecimal.valueOf(-1L).equals(estadoNup)) {
				LOGGER.error("Error en el store {}.{}.{}, code:= {}, mensaje:= {}", SCHEMA_NAME,
						PACKAGE_CLIENTE_TBANCO_NAME, PROCEDURE_HABILITADOTBANCO_NAME, respuesta.get(PARAM_TECNICO),
						respuesta.get(PARAM_AMIGABLE));
			}
			LOGGER.debug("Nup {} no habilitado para ingresar a TBANCO.", nup);
			return Boolean.FALSE;
		}
	}

}
