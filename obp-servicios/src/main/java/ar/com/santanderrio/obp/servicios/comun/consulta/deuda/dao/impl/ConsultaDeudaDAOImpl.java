/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.ConsultaDeudaDAO;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;

/**
 * The Class ConsultaDeudaDAOImpl.
 */
@Repository
@Primary
@TargetSystem(DataBase.CLASIFDEUDORES)
public class ConsultaDeudaDAOImpl extends BaseDatoDAOImpl implements ConsultaDeudaDAO {

	/** The Constant FECHA_ULTIMA_CLASIFICACION. */
	private static final String FECHA_ULTIMA_CLASIFICACION = "v_fecha_ultima_clasificacion";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaDeudaDAOImpl.class);

	/** Constante COD_RESULTADO_OK. */
	private static final String COD_RESULTADO_OK = "0";

	/** Constante COD_RESULTADO_NOT_FOUND. */
	private static final String COD_RESULTADO_NOT_FOUND = "100";

	/** SCHEMA. */
	private static final String CONSULTA_DEUDA_SCHEMA = "DEUDORES";

	/** The Constant CONSULTA_DEUDA_PACKAGE. */
	private static final String CONSULTA_DEUDA_PACKAGE = "";

	/** PROCEDURE consultar deuda. */
	private static final String CONSULTA_DEUDA_PROCEDURE = "sp_datos_clasificacion";

	/** The Constant IN_CONSULTAR_DEUDA_NUP. */
	private static final String IN_CONSULTA_DEUDA_NUP = "V_NUP_IN";

	/** The Constant OUT_CONSULTAR_DEUDA_RESULTADO. */
	private static final String OUT_CONSULTA_DEUDA_RESULTADO = "v_Cod_Resultado";

	/** The Constant OUT_CONSULTAR_DEUDA_ERROR. */
	private static final String OUT_CONSULTA_DEUDA_ERROR = "v_Desc_Error_Tecnico";

	/** The Constant OUT_CONSULTAR_DEUDA_NUP. */
	private static final String OUT_CONSULTA_DEUDA_NUP = "v_nup_out";

	/** The Constant OUT_CONSULTAR_DEUDA_SITUACION. */
	private static final String OUT_CONSULTA_DEUDA_SITUACION = "v_situacion_bcra";

	/** The Constant OUT_CONSULTAR_DEUDA_SITUACION. */
	private static final String OUT_CONSULTA_DEUDA_FECHA_ULT_CLASIFICACION = FECHA_ULTIMA_CLASIFICACION;

	/** The Constant OUT_CONSULTAR_DEUDA_SITUACION. */
	private static final String OUT_CONSULTA_DEUDA_DESCRIPCION = "v_descripcion";

	/** The schema name. */
	@Value("${DEUDORES.SCHEMA}")
	private String schemaName;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.consulta.deuda.dao.
	 * ConsultaDeudaDAO#consultaDeuda(java.lang.String)
	 */
	@Override
	public ClasificacionDeuda consultaDeuda(String nup) throws DAOException {
		LOGGER.info("Se consultara el procedure {} con el contrato {}.", CONSULTA_DEUDA_PROCEDURE, nup);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		ClasificacionDeuda deuda = new ClasificacionDeuda();
		parametros.add(new SqlParameter(IN_CONSULTA_DEUDA_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONSULTA_DEUDA_RESULTADO, Types.NUMERIC));
		parametros.add(new SqlOutParameter(OUT_CONSULTA_DEUDA_ERROR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONSULTA_DEUDA_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONSULTA_DEUDA_SITUACION, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CONSULTA_DEUDA_FECHA_ULT_CLASIFICACION, Types.DATE));
		parametros.add(new SqlOutParameter(OUT_CONSULTA_DEUDA_DESCRIPCION, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CONSULTA_DEUDA_NUP, nup);

		try {
			Map<String, Object> respuesta = consultar(this.schemaName, "", CONSULTA_DEUDA_PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			BigDecimal codRetorno = (BigDecimal) respuesta.get(OUT_CONSULTA_DEUDA_RESULTADO);
			LOGGER.info("Procedure ejecutado {} salida {}.", CONSULTA_DEUDA_PROCEDURE, codRetorno);
			if (COD_RESULTADO_NOT_FOUND.equals(codRetorno.toString())) {
				deuda.setSituacionBcra(StringUtils.EMPTY);
				deuda.setDescripcion(StringUtils.EMPTY);
				LOGGER.info("Se consulto el registro {} correctamente, pero no se encontro el cliente.", deuda);
			} else if (!COD_RESULTADO_OK.equals(codRetorno.toString())) {
				LOGGER.info("Error en store {}.{}.{}. al crear el registro< {}.", CONSULTA_DEUDA_SCHEMA,
						CONSULTA_DEUDA_PACKAGE, CONSULTA_DEUDA_PROCEDURE, deuda);
				throw new DAOException("Error al consultar el registro en el store " + CONSULTA_DEUDA_PROCEDURE);
			} else {
				deuda.setSituacionBcra(respuesta.get(OUT_CONSULTA_DEUDA_SITUACION).toString());
				deuda.setDescripcion(respuesta.get(OUT_CONSULTA_DEUDA_DESCRIPCION).toString());
				deuda.setFechaClasificacion((Date) respuesta.get(FECHA_ULTIMA_CLASIFICACION));
				LOGGER.info("Se consulto el registro {} correctamente.", deuda);
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error en store {}. al consultar la deuda {}.", CONSULTA_DEUDA_SCHEMA, CONSULTA_DEUDA_PACKAGE,
					CONSULTA_DEUDA_PROCEDURE, nup, sqle);
			throw new DAOException(sqle);
		} catch (RuntimeException ex) {
			LOGGER.error("Error en base del store {}.al consultar la deuda {}.", CONSULTA_DEUDA_SCHEMA,
					CONSULTA_DEUDA_PACKAGE, CONSULTA_DEUDA_PROCEDURE, nup, ex);
			throw new DAOException(ex);
		}
		return deuda;
	}

}
