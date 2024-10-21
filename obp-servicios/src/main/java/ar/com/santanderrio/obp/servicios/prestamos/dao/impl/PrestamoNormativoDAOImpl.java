package ar.com.santanderrio.obp.servicios.prestamos.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoNormativoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoNormativoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoNormativoOutEntity;
import oracle.jdbc.OracleTypes;

@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class PrestamoNormativoDAOImpl extends BaseDatoDAOImpl implements PrestamoNormativoDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoNormativoDAOImpl.class);
	
	private static final String SCHEMA_NAME = "HBANK";
	
	private static final String PACKAGE_NAME = "HB_PKG_CREDITOS_BCRA_A6949";
	
	private static final String PROCEDURE_NAME = "CONSULTAR_PRESTAMO";

	private static final String PARAM_SUC_NVO = "L_SUC_NVO";

	private static final String PARAM_CTA_NVO = "L_CTA_NVO";

	private static final String PARAM_CURSOR = "p_cursor";

	private static final String PARAM_RESULTADO = "p_resultado";

	private static final String PARAM_AMIGABLE = "p_err_amigable";

	private static final String PARAM_TECNICO = "p_err_tecnico";

	private static final int P_SUC_NUEVO = 1;

	private static final int P_CTA_NUEVO = 2;
	
	private static final String COD_RESULTADO_OK = "0";

	@Override
	public List<PrestamoNormativoOutEntity> consultarNroPrestamoViejo(PrestamoNormativoInEntity inEntity) throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(PARAM_SUC_NVO, Types.VARCHAR));
		parametros.add(new SqlParameter(PARAM_CTA_NVO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new RowMapper<PrestamoNormativoOutEntity>() {
			@Override
			public PrestamoNormativoOutEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				PrestamoNormativoOutEntity prestamoNormativoOutEntity = new PrestamoNormativoOutEntity();
				prestamoNormativoOutEntity.setSucursal(rs.getString(P_SUC_NUEVO));
				prestamoNormativoOutEntity.setCuenta(rs.getString(P_CTA_NUEVO));
				return prestamoNormativoOutEntity;
			}
		}));
		parametros.add(new SqlOutParameter(PARAM_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_AMIGABLE, Types.VARCHAR));
		
		SqlParameterSource in = new MapSqlParameterSource().addValue(PARAM_SUC_NVO, inEntity.getSucursal()).addValue(PARAM_CTA_NVO, inEntity.getCuenta());
		
		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME,
					PROCEDURE_NAME, in, parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(PARAM_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				@SuppressWarnings("unchecked")
				List<PrestamoNormativoOutEntity> prestamoNormativoList = (List<PrestamoNormativoOutEntity>) respuesta
						.get(PARAM_CURSOR);
				return prestamoNormativoList;
			} else {
				throw new DAOException((String) respuesta.get(PARAM_AMIGABLE));
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
			throw new DAOException(exception);
		}
	}
}
