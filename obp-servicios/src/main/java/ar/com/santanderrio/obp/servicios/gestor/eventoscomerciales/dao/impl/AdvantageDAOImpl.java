/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.AdvantageDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GrupoAfinidadClienteOutEntity;
import oracle.jdbc.OracleTypes;

/**
 * The Class AdvantageDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ADVANTAGE)
public class AdvantageDAOImpl extends BaseDatoDAOImpl implements AdvantageDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvantageDAOImpl.class);

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;
	
    @Autowired
    private SesionCliente sesionCliente;
	/** The Constant CONSULTA_PROCEDURE. */
	private static final String CONSULTA_PROCEDURE = "Se consultara el procedure {}.";

	/** The Constant PROCEDURE. */
	private static final String PROCEDURE = "sp_cons_socio_american";

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

	/** The Constant OUT_CODIGO_RESULTADO. */
	private static final String OUT_CODIGO_RESULTADO = "vcod_resultado";

	/** The Constant OUT_CURSOR. */
	private static final String OUT_CURSOR = "cursocio";

	/** The Constant OUT_DESCRIPCION_ERROR. */
	private static final String OUT_DESCRIPCION_ERROR = "desc_error_tecnico";

	/** The Constant OUT_DESCRIPCION_ERROR_AMIGABLE. */
	private static final String OUT_DESCRIPCION_ERROR_AMIGABLE = "desc_error_amigable";

	/** The Constant MENSAJE_SIN_DATOS_ADVANTAGE. */
	private static final String MENSAJE_SIN_DATOS_ADVANTAGE = "No se encontraron datos con los filtros ingresados";
	
	/** The Constant SUPERCLUB. */
	private static final String SUPERCLUB = "SUPERCLUB";
	
	/** The Constant AADVANTAGE. */
	private static final String AADVANTAGE = "AADVANTAGE";

    private static final String S = "S";
    
    
    private static final String A = "A";
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.
	 * AdvantageDAO#consultarUsuarioEsSuperclub(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<GrupoAfinidadClienteOutEntity> consultarUsuarioEsSuperclub(Cliente cliente) throws DAOException {
		LOGGER.info(CONSULTA_PROCEDURE, PROCEDURE);
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_NUP, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CODIGO_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_CURSOR, OracleTypes.CURSOR, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(2);
			}
		}));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION_ERROR, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_DESCRIPCION_ERROR_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_NUP, cliente.getNup());
		Respuesta<GrupoAfinidadClienteOutEntity> respuestaFinal = new Respuesta<GrupoAfinidadClienteOutEntity>();
		
		try {
			Map<String, Object> respuesta = consultar(SOURCE, PACKAGE, PROCEDURE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			BigDecimal codRetorno = new BigDecimal((String) respuesta.get(OUT_CODIGO_RESULTADO));
			String mensajeRetorno = (String) respuesta.get(OUT_DESCRIPCION_ERROR_AMIGABLE);
			GrupoAfinidadClienteOutEntity grupoAfinidadCliente = new GrupoAfinidadClienteOutEntity(); 
			
			if (BigDecimal.ONE.equals(codRetorno) && MENSAJE_SIN_DATOS_ADVANTAGE.equals(mensajeRetorno)) {
				grupoAfinidadCliente.setGrupoAfinidad(SUPERCLUB);
                sesionCliente.getCliente().setProgramaBeneficios(S);
				respuestaFinal = respuestaFactory.crearRespuestaOk(grupoAfinidadCliente);
			} else if (BigDecimal.ZERO.equals(codRetorno)) {

				grupoAfinidadCliente.setGrupoAfinidad(AADVANTAGE);
                sesionCliente.getCliente().setProgramaBeneficios(A);
				
				@SuppressWarnings("unchecked")
				List<String> cursorNumeroSocio = (List<String>) respuesta.get(OUT_CURSOR);

                if (cursorNumeroSocio != null && !cursorNumeroSocio.isEmpty()) {
                       grupoAfinidadCliente.setGrupoAfinidad(AADVANTAGE);
                       sesionCliente.getCliente().setProgramaBeneficios(A);
                       String nroSocio = cursorNumeroSocio.get(0);
                       grupoAfinidadCliente.setNumeroSocio(nroSocio);
				} else {
					grupoAfinidadCliente.setNumeroSocio(StringUtils.EMPTY);
				}
				respuestaFinal = respuestaFactory.crearRespuestaOk(grupoAfinidadCliente);
				
			} else if(BigDecimal.ONE.equals(codRetorno) && !MENSAJE_SIN_DATOS_ADVANTAGE.equals(mensajeRetorno)) {
				respuestaFinal = respuestaFactory.crearRespuestaError(null);
			} else {
				respuestaFinal = respuestaFactory.crearRespuestaError(null);
			}
			return respuestaFinal;
			
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
