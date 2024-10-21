/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aadvantage.dao.impl;

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
import ar.com.santanderrio.obp.servicios.aadvantage.dao.AadvantageDAO;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.DetallePuntosAadvantageDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageInEntity;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import oracle.jdbc.OracleTypes;

/**
 * The Class AadvantageDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ADVANTAGE)
public class AadvantageDAOImpl extends BaseDatoDAOImpl implements AadvantageDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AadvantageDAOImpl.class);
	
	/** The parametros. */
	private List<SqlParameter> parametros = new ArrayList<SqlParameter>();
	
	/** The Constant CONSULTA_PROCEDURE. */
	private static final String CONSULTA_PROCEDURE = "Se consultara el procedure {}.";

	/** The Constant PROCEDURE. */
	private static final String PROCEDURE = "SP_CONS_PUNTOS_AMERICAN";

	/** The Constant PACKAGE. */
	private static final String PACKAGE = "PKG_CAT";

	/** The Constant SOURCE. */
	private static final String SOURCE = "ADD01";

	/** The Constant MENSAJE_ERROR_EN_STORE. */
	private static final String MENSAJE_ERROR_EN_STORE = "Error en store {}.{}.{}. al solicitar la informacion Advantage de {}.";

	/** The Constant ERROR_EN_BASE. */
	private static final String ERROR_EN_BASE = "Error en base del store {}.{}.{} al solicitar informacion Advantage de {}.";

	/** The Constant ERROR_TIMEOUT. */
	private static final String ERROR_TIMEOUT = "Error de Timeout.";

	/** The Constant IN_NUP. */
	private static final String IN_NUP = "vnup";

	/** The Constant IN_CANT_MESES. */
	private static final String IN_CANT_MESES = "ncant_meses";
	
	/** The Constant OUT_CODIGO_RESULTADO. */
	private static final String OUT_CODIGO_RESULTADO = "vcod_resultado";
	
	/** The Constant OUT_ERROR_TECNICO. */
	private static final String OUT_ERROR_TECNICO = "desc_error_tecnico";
	
	/** The Constant OUT_ERROR_AMIGABLE. */
	private static final String OUT_ERROR_AMIGABLE = "desc_error_amigable";
	
	/** The Constant OUT_CURSOR. */
	private static final String OUT_CURSOR = "curpuntosacum";
	
	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";
	
	/** The Constant COD_RETORNO_USER_EXCEPTION. */
	private static final String COD_RETORNO_USER_EXCEPTION = "1";
	
	/** The Constant ERROR_TECNICO. */
	private static final String ERROR_TECNICO = "User-Defined Exception";
	
	/** The Constant ERROR_AMIGABLE. */
	private static final String ERROR_AMIGABLE = "No se encontraron datos con los filtros ingresados";
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.aadvantage.dao.AadvantageDAO#consultarMillas(ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageInEntity)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SolicitudMillasAadvantageOutEntity consultarMillas(SolicitudMillasAadvantageInEntity inEntity) throws DAOException {
		LOGGER.info(CONSULTA_PROCEDURE, PROCEDURE);
		List<DetallePuntosAadvantageDTO> listaDetallePuntos = new ArrayList<DetallePuntosAadvantageDTO>();
		
		Cliente cliente = inEntity.getCliente();
		SolicitudMillasAadvantageOutEntity outEntity = new SolicitudMillasAadvantageOutEntity();
		this.parametros = this.cargarParametros();
		
		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, cliente.getNup())
				.addValue(IN_CANT_MESES, inEntity.getCantMeses());
		try {
			Map<String, Object> respuesta = consultarDB(in);
			String codRetorno = (String) respuesta.get(OUT_CODIGO_RESULTADO);
			String errorTecnico = (String) respuesta.get(OUT_ERROR_TECNICO);
			String errorAmigable = (String) respuesta.get(OUT_ERROR_AMIGABLE);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				listaDetallePuntos = (List<DetallePuntosAadvantageDTO>) respuesta.get(OUT_CURSOR);
				outEntity.setIsSinMillas(listaDetallePuntos == null || listaDetallePuntos.isEmpty());
				outEntity.setListaDetalleMillas(listaDetallePuntos);
				outEntity.setIsErrorTecnico(false);
			} else if(COD_RETORNO_USER_EXCEPTION.equals(codRetorno) && ERROR_TECNICO.equalsIgnoreCase(errorTecnico) && ERROR_AMIGABLE.equalsIgnoreCase(errorAmigable)) {
				outEntity.setIsSinMillas(true);
				outEntity.setIsErrorTecnico(false);
			} else {
				outEntity.setIsErrorTecnico(true);
			}
		} catch (DataAccessResourceFailureException dae) {
			LOGGER.error(ERROR_EN_BASE, SOURCE, PACKAGE, PROCEDURE, cliente, dae);
			throw new DAOException(dae);
		} catch (SQLException sqle) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, SOURCE, PACKAGE, PROCEDURE, cliente, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException ex) {
			LOGGER.error(ERROR_EN_BASE, SOURCE, PACKAGE, PROCEDURE, cliente, ex);
			throw new DAOException(ex);
		} catch (InvalidDataAccessApiUsageException invalidData) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, SOURCE, PACKAGE, PROCEDURE, cliente, invalidData);
			throw new DAOException(invalidData);
		} catch (IllegalStateException iex) {
			LOGGER.error(MENSAJE_ERROR_EN_STORE, SOURCE, PACKAGE, PROCEDURE, cliente, iex);
			throw new DAOException(iex);
		} catch (QueryTimeoutException qte) {
			LOGGER.error(ERROR_TIMEOUT, SOURCE, PACKAGE, PROCEDURE, cliente, qte);
			throw new DAOException(qte.getMessage());
		}
		return outEntity; 
	}

	/**
	 * Cargar parametros.
	 *
	 * @return the list
	 */
	private List<SqlParameter> cargarParametros() {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlParameter(IN_CANT_MESES, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CODIGO_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CURSOR, OracleTypes.CURSOR,
				new RowMapper<DetallePuntosAadvantageDTO>() {
					@Override
					public DetallePuntosAadvantageDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
						DetallePuntosAadvantageDTO filaPuntosAadvantage = new DetallePuntosAadvantageDTO();
						filaPuntosAadvantage.setMesAnio(rs.getString("FMES_ANIO"));
						filaPuntosAadvantage.setPuntosVisa(rs.getString("FVISA"));
						filaPuntosAadvantage.setPuntosAmex(rs.getString("FAMEX"));
						filaPuntosAadvantage.setPuntosMaster(rs.getString("FMASTER"));
						filaPuntosAadvantage.setPuntosOtros(rs.getString("FOTROS"));
						filaPuntosAadvantage.setPuntosTotal(rs.getString("FTOTAL"));
						
						return filaPuntosAadvantage;
					}
				}));
		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

		return parametros;
	}
	
	/**
	 * Consultar DB.
	 *
	 * @param in
	 *            the in
	 * @return the map
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Map<String, Object> consultarDB(SqlParameterSource in) throws SQLException {

		return consultar(SOURCE, PACKAGE, PROCEDURE, in, this.parametros.toArray(new SqlParameter[parametros.size()]));
	}
	

/*
 * (non-Javadoc)
 *
 * @see ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl#customJdbc()
 */
 @Override
 protected boolean customJdbc() {
            return Boolean.TRUE;
  }
	
}
