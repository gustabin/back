package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import ar.com.santanderrio.obp.servicios.transferencias.dao.DestinatariosFrecuentesDAO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesEntity;
import oracle.jdbc.OracleTypes;

@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class DestinatariosFrecuentesDAOImpl extends BaseDatoDAOImpl implements DestinatariosFrecuentesDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinatariosFrecuentesDAOImpl.class);

	/** Constante COD_RESULTADO_OK. */
	private static final String COD_RESULTADO_OK = "0";

	/** The Constant SCHEMA NAME. */
	private static final String SCHEMA_NAME = "HBANK";

	/** The Constant PACKAGE_NAME. */
	private static final String PACKAGE_NAME = "HB_PKG_DESTINATARIOS_FAVORITOS";

	/** The Constant SP_OBTENER_FAVS. */
	private static final String SP_OBTENER_FAVS = "prc_obtener_favoritos";

	/** The Constant SP_CREAR_FAV. */
	private static final String SP_CREAR_FAV = "prc_crear_favorito";

	/** The Constant SP_BAJA_FAV. */
	private static final String SP_BAJA_FAV = "prc_borrar_favorito";

	/** The Constant P_NUP. */
	private static final String P_ID = "p_id";

	/** The Constant P_NUP. */
	private static final String P_NUP = "p_nup";

	/** The Constant P_TIPO_CUENTA. */
	private static final String P_TIPO_CUENTA = "p_tipo_cuenta";

	/** The Constant P_SUCURSAL. */
	private static final String P_SUCURSAL = "p_sucursal";

	/** The Constant P_NRO_CUENTA. */
	private static final String P_NRO_CUENTA = "p_nro_cuenta";

	/** The Constant P_CBU. */
	private static final String P_CBU = "p_cbu";

	/** The Constant P_RESULTADO. */
	private static final String P_RESULTADO = "p_resultado";

	/** The Constant P_SUSCRIPTO. */
	private static final String P_CURSOR = "p_cursor";

	/** The P_ERROR_TECNICO. */
	private static final String P_ERROR_TECNICO = "p_err_tecnico";

	/** The P_ERROR_AMIGABLE. */
	private static final String P_ERROR_AMIGABLE = "p_err_amigable";

	/** The COL_CBU. */
	private static final String COL_CBU = "CBU";

	/** The COL_NROCTA. */
	private static final String COL_NROCTA = "NROCTA";

	/** The COL_SUCURSAL. */
	private static final String COL_SUCURSAL = "SUCURSAL";

	/** The COL_TIPO_CUENTA. */
	private static final String COL_TIPO_CUENTA = "TIPO_CUENTA";

	/** The COL_NUP. */
	private static final String COL_NUP = "NUP";

	/** The COL_ID. */
	private static final String COL_ID = "ID";

	@Override
	public List<DestinatariosFrecuentesEntity> obtenerDestinatariosFrecuentes(String nup) throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(P_NUP, Types.VARCHAR));
		parametros
				.add(new SqlOutParameter(P_CURSOR, OracleTypes.CURSOR, new RowMapper<DestinatariosFrecuentesEntity>() {
					@Override
					public DestinatariosFrecuentesEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						DestinatariosFrecuentesEntity entity = new DestinatariosFrecuentesEntity();
						entity.setId(rs.getInt(COL_ID));
						entity.setNup(rs.getString(COL_NUP));
						entity.setTipoCuenta(rs.getString(COL_TIPO_CUENTA));
						entity.setSucursal(rs.getString(COL_SUCURSAL));
						entity.setNroCuenta(rs.getString(COL_NROCTA));
						entity.setCbu(rs.getString(COL_CBU));
						return entity;
					}
				}));
		parametros.add(new SqlOutParameter(P_RESULTADO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_TECNICO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_AMIGABLE, OracleTypes.VARCHAR));
		SqlParameterSource in = new MapSqlParameterSource().addValue(P_NUP, nup);
		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, SP_OBTENER_FAVS, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(P_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				@SuppressWarnings("unchecked")
				List<DestinatariosFrecuentesEntity> destinatariosFrecuentes = (List<DestinatariosFrecuentesEntity>) respuesta
						.get(P_CURSOR);
				return destinatariosFrecuentes;
			} else {
				LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_OBTENER_FAVS);
				throw new DAOException();
			}
		} catch (SQLException e) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_OBTENER_FAVS);
			throw new DAOException(e);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_CREAR_FAV);
			throw new DAOException(ex);
		}
	}

	@Override
	public String altaDestinatarioFavorito(DestinatariosFrecuentesEntity destinatario) throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(P_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(P_TIPO_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(P_SUCURSAL, Types.VARCHAR));
		parametros.add(new SqlParameter(P_NRO_CUENTA, Types.VARCHAR));
		parametros.add(new SqlParameter(P_CBU, Types.VARCHAR));
		parametros.add(new SqlOutParameter(P_RESULTADO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ID, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_TECNICO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_AMIGABLE, OracleTypes.VARCHAR));
		SqlParameterSource in = new MapSqlParameterSource().addValue(P_NUP, destinatario.getNup())
				.addValue(P_TIPO_CUENTA, destinatario.getTipoCuenta()).addValue(P_SUCURSAL, destinatario.getSucursal())
				.addValue(P_NRO_CUENTA, destinatario.getNroCuenta()).addValue(P_CBU, destinatario.getCbu());
		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, SP_CREAR_FAV, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(P_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				return (String) respuesta.get(P_ID);
			} else {
				String errorTecnico = (String) respuesta.get(P_ERROR_TECNICO);
				LOGGER.error("Error en SP {}.{}.{}. {}", SCHEMA_NAME, PACKAGE_NAME, SP_CREAR_FAV, errorTecnico);
				throw new DAOException();
			}
		} catch (SQLException e) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_CREAR_FAV);
			throw new DAOException(e);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_CREAR_FAV);
			throw new DAOException(ex);
		}
	}

	@Override
	public Boolean bajaDestinatarioFavorito(String id) throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(P_ID, Types.VARCHAR));
		parametros.add(new SqlOutParameter(P_RESULTADO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_TECNICO, OracleTypes.VARCHAR));
		parametros.add(new SqlOutParameter(P_ERROR_AMIGABLE, OracleTypes.VARCHAR));
		SqlParameterSource in = new MapSqlParameterSource().addValue(P_ID, id);
		Map<String, Object> respuesta;
		try {
			respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, SP_BAJA_FAV, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(P_RESULTADO);
			return COD_RESULTADO_OK.equals(codRetorno);
		} catch (SQLException e) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_BAJA_FAV);
			throw new DAOException(e);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error("Error en SP {}.{}.{}", SCHEMA_NAME, PACKAGE_NAME, SP_CREAR_FAV);
			throw new DAOException(ex);
		}
	}
}