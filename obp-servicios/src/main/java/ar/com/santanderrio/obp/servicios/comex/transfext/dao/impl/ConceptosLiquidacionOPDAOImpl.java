package ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ConceptosLiquidacionOPDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConceptoLiquidacionOPEntity;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.DestinatariosFrecuentesDAOImpl;
import oracle.jdbc.OracleTypes;

@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class ConceptosLiquidacionOPDAOImpl extends BaseDatoDAOImpl implements ConceptosLiquidacionOPDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinatariosFrecuentesDAOImpl.class);

	/** Constante COD_RESULTADO_OK. */
	private static final String COD_RESULTADO_OK = "0";

	/** The Constant SCHEMA NAME. */
	private static final String SCHEMA_NAME = "HBANK";

	/** The Constant P_CURSOR. */
	private static final String P_CURSOR = "p_cursor";

	/** The Constant P_RESULTADO. */
	private static final String P_RESULTADO = "p_resultado";

	/** The P_ERROR_TECNICO. */
	private static final String P_ERROR_TECNICO = "p_err_tecnico";

	/** The P_ERROR_AMIGABLE. */
	private static final String P_ERROR_AMIGABLE = "p_err_amigable";

	/** The COL_ID_CONCEPTO. */
	private static final String COL_ID_CONCEPTO = "ID_CONCEPTO";
	/** The COL_CODIGO_CONCEPTO. */
	private static final String COL_CODIGO_CONCEPTO = "CODIGO_CONCEPTO";
	/** The COL_CONCEPTO. */
	private static final String COL_CONCEPTO = "CONCEPTO";
	/** The COL_ADMITE_CANJE. */
	private static final String COL_ADMITE_CANJE = "ADMITECANJE";
	/** The COL_DOC_OBLIGATORIA. */
	private static final String COL_DOC_OBLIGATORIA = "DOCUMENTACIONOBLIGATORIA";
	/** The SP_OBTENER_CONCEPTOS. */
	private static final String SP_OBTENER_CONCEPTOS = "prc_obtener_conceptos";
	/** The PACKAGE_NAME. */
	private static final String PACKAGE_NAME = "HB_CONCEPTOS";

	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_CONCEPTOS_LIQ_ORDENPAGO })
	@Override
	public List<ConceptoLiquidacionOPEntity> obtenerConceptosLiquidacionOP() throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(P_CURSOR, OracleTypes.CURSOR, new RowMapper<ConceptoLiquidacionOPEntity>() {
			@Override
			public ConceptoLiquidacionOPEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				ConceptoLiquidacionOPEntity entity = new ConceptoLiquidacionOPEntity();
				entity.setIdConcepto(rs.getString(COL_ID_CONCEPTO));
				entity.setCodigoConcepto(rs.getString(COL_CODIGO_CONCEPTO));
				entity.setConcepto(rs.getString(COL_CONCEPTO));
				entity.setAdmiteCanje(rs.getString(COL_ADMITE_CANJE));
				entity.setDocumentacionObligatoria(rs.getString(COL_DOC_OBLIGATORIA));
				return entity;
			}
		}));
		parametros.add(new SqlOutParameter(P_RESULTADO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_TECNICO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_AMIGABLE, OracleTypes.VARCHAR));
		SqlParameterSource in = new MapSqlParameterSource();
		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, SP_OBTENER_CONCEPTOS, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(P_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				@SuppressWarnings("unchecked")
				List<ConceptoLiquidacionOPEntity> conceptosLiquidacionOP = (List<ConceptoLiquidacionOPEntity>) respuesta
						.get(P_CURSOR);
				return conceptosLiquidacionOP;
			} else {
				LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_OBTENER_CONCEPTOS);
				throw new DAOException();
			}
		} catch (SQLException e) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_OBTENER_CONCEPTOS);
			throw new DAOException(e);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_OBTENER_CONCEPTOS);
			throw new DAOException(ex);
		}
	}

	@CacheEvict(value = "conceptosCache", allEntries = true)
	@Override
	public void limpiarConceptos() {
		LOGGER.info("Se limpia la cache de conceptos.");
	}

}
