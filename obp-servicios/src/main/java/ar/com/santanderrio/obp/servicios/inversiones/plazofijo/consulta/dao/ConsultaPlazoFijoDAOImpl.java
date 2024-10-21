/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.AccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DescripcionAccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEntity;
import oracle.jdbc.OracleTypes;

/**
 * The Class ConsultaPlazoFijoDAOImpl.
 */
@TargetSystem(DataBase.ESTADISTICAS)
@Component
public class ConsultaPlazoFijoDAOImpl extends BaseDatoDAOImpl implements ConsultaPlazoFijoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaPlazoFijoDAOImpl.class);

	/** The Constant CONTRATO_SCHEMA. */
	private static final String CONTRATO_SCHEMA = "HBANK";

	/** The Constant CONTRATO_PACKAGE. */
	private static final String CONTRATO_PACKAGE = "HB_PKG_PLAZO_FIJO";

	/** The Constant CONTRATO_CONSULTAR. */
	private static final String CONTRATO_CONSULTAR = "PRC_ACCION_AL_VENCIMIENTO";

	/** The Constant CONTRATO_CONSULTAR. */
	private static final String CONTRATO_CONSULTAR_DETALLE = "PRC_DESCRIPCION_ACCION_AL_VENC";

	/** The Constant PARAM_CURSOR. */
	private static final String PARAM_CURSOR = "p_cursor";

	/** The Constant IN_CODIGO_PLAZO. */
	private static final String IN_CODIGO_PLAZO = "p_codigo_plazo";

	/** The Constant IN_CODIGO_PLAZO. */
	private static final String IN_CODIGO_ACCION = "p_codigo_accion";

	/** The Constant OUT_CODIGO_ACCION. */
	private static final String OUT_CODIGO_ACCION = "CODIGO_ACCION";

	/** The Constant OUT_DESCRIPCION. */
	private static final String OUT_DESCRIPCION = "DESCRIPCION";

	/** The Constant DETALLE. */
	private static final String DETALLE = "DETALLE";

	/** The Constant OUT_RESULTADO. */
	private static final String OUT_RESULTADO = "p_resultado";

	/** The Constant OUT_ERROR_TECNICO. */
	private static final String OUT_ERROR_TECNICO = "p_err_tecnico";

	/** The Constant OUT_ERROR_AMIGABLE. */
	private static final String OUT_ERROR_AMIGABLE = "p_err_amigable";

	/** The Constant COD_RETORNO_OK. */
	private static final String COD_RETORNO_OK = "0";

	/** The Constant ERROR_TECNICO. */
	// TODO El codigo no es el correcto
	private static final String ERROR_TECNICO = "1";

	/** The Constant ERROR_AMIGABLE. */
	// TODO El codigo no es el correcto
	private static final String ERROR_AMIGABLE = "2";

	/** The Constant CODIGO_PLAZO. */
	private static final String CODIGO_PLAZO = "CODIGO_PLAZO";

	/** The Constant DESCRIPCION. */
	private static final String DESCRIPCION = "DESCRIPCION";

	/** The Constant ES_AJUSTABLE. */
	private static final String ES_AJUSTABLE = "ES_AJUSTABLE";

	/** The Constant ES_INTERESANTE. */
	private static final String ES_INTERESANTE = "ES_INTERESANTE";

	/** The Constant ES_PRECANCELABLE. */
	private static final String ES_PRECANCELABLE = "ES_PRECANCELABLE";

	/** The Constant ES_TASA_VARIABLE. */
	private static final String ES_TASA_VARIABLE = "ES_TASA_VARIABLE";

	/** The Constant HABILITADO. */
	private static final String HABILITADO = "HABILITADO";

	/** The Constant MOSTRAR_FREC_INTERESES. */
	private static final String MOSTRAR_FREC_INTERESES = "MOSTRAR_FREC_INTERESES";

	/** The Constant NOMBRE_LEGAL. */
	private static final String NOMBRE_LEGAL = "NOMBRE_LEGAL";

	/** The Constant AYUDA. */
	private static final String AYUDA = "AYUDA";

	/** The Constant SCHEMA_NAME. */
	private static final String SCHEMA_NAME = "HBANK";

	/** The Constant PACKAGE_NAME. */
	private static final String PACKAGE_NAME = "HB_PKG_PLAZO_FIJO";

	/** The Constant PROCEDURE_VER_PLAZOS_FIJOS. */
	private static final String PROCEDURE_VER_PLAZOS_FIJOS = "PRC_VER_PLAZOS_FIJOS";

	/** The Constant MIN_DIAS_LIQ_INT. */
	private static final String MIN_DIAS_LIQ_INT = "MIN_DIAS_LIQ_INT";

	/** The Constant MONEDA. */
	private static final String MONEDA = "MONEDA";

	/** The Constant PRIORIDAD. */
	private static final String PRIORIDAD = "PRIORIDAD";

	/** The Constant CODIGO_BPRIV. */
	private static final String CODIGO_BPRIV = "CODIGO_BP";

	/** The Constant IS_HABILITADO. */
	private static final String IS_HABILITADO = "1";

	/** The Constant CONSTANTE_CERO. */
	private static final String CONSTANTE_CERO = "0";

	/** The Constant LONGITUD_UNO. */
	private static final int LONGITUD_UNO = 1;

	/**
	 * Consultar accion al vencimiento.
	 *
	 * @param numeroPlazoFijo
	 *            the numero plazo fijo
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	private List<AccionAlVencimientoOutEntity> consultarAccionAlVencimiento(String numeroPlazoFijo)
			throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_CODIGO_PLAZO, Types.INTEGER));
		parametros.add(
				new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new RowMapper<AccionAlVencimientoOutEntity>() {
					@Override
					public AccionAlVencimientoOutEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
						AccionAlVencimientoOutEntity consultaAccionVencimiento = new AccionAlVencimientoOutEntity();
						consultaAccionVencimiento.setCodigoAccion(rs.getString(OUT_CODIGO_ACCION));
						consultaAccionVencimiento.setDescripcion(rs.getString(OUT_DESCRIPCION));
						consultaAccionVencimiento.setDetalle(rs.getString(DETALLE));
						return consultaAccionVencimiento;
					}

				}));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CODIGO_PLAZO, numeroPlazoFijo);

		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_CONSULTAR, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<AccionAlVencimientoOutEntity> consultaAccionVencimiento = (List<AccionAlVencimientoOutEntity>) respuesta
						.get(PARAM_CURSOR);
				return consultaAccionVencimiento;
			} else {
				manejarCasosAlternativos(codRetorno);
				throw new DAOException();
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			throw new DAOException(exception);
		}
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.ConsultaPlazoFijoDAO#obtenerDescripcionesAccionAlVencimiento()
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_DESCRIPCION_ACCIONES_VENCIMIENTO })
	@SuppressWarnings("unchecked")
	public List<DescripcionAccionAlVencimientoOutEntity> obtenerDescripcionesAccionAlVencimiento() throws DAOException{
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(IN_CODIGO_ACCION, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR,
				new RowMapper<DescripcionAccionAlVencimientoOutEntity>() {
					@Override
					public DescripcionAccionAlVencimientoOutEntity mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						DescripcionAccionAlVencimientoOutEntity consultaAccionVencimiento = new DescripcionAccionAlVencimientoOutEntity();
						consultaAccionVencimiento.setDescripcion(rs.getString(OUT_DESCRIPCION));
						consultaAccionVencimiento.setCodigo(rs.getString(OUT_CODIGO_ACCION));
						return consultaAccionVencimiento;
					}

				}));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(IN_CODIGO_ACCION, null);

		try {
			Map<String, Object> respuesta = consultar(CONTRATO_SCHEMA, CONTRATO_PACKAGE, CONTRATO_CONSULTAR_DETALLE, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<DescripcionAccionAlVencimientoOutEntity> consultaAccionVencimiento = (List<DescripcionAccionAlVencimientoOutEntity>) respuesta
						.get(PARAM_CURSOR);
				LOGGER.info("Se obtuvieron las acciones al vencimiento de la tabla");
				return consultaAccionVencimiento;
			} else {
				manejarCasosAlternativos(codRetorno);
				throw new DAOException();
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			throw new DAOException(exception);
		}
	}
	/**
	 * Consultar descripcion de accion al vencimiento.
	 *
	 * @param codigoAccion
	 *            the codigo de accion
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	public List<DescripcionAccionAlVencimientoOutEntity> obtenerDescripcionAccionAlVencimiento(String codigoAccion)
			throws DAOException {
		List<DescripcionAccionAlVencimientoOutEntity> accionAlVencimiento = ContextHolder.getContext().getBean(ConsultaPlazoFijoDAO.class)
				.obtenerDescripcionesAccionAlVencimiento();
		List<DescripcionAccionAlVencimientoOutEntity> response = new ArrayList<DescripcionAccionAlVencimientoOutEntity>();
		for(DescripcionAccionAlVencimientoOutEntity descripcion : accionAlVencimiento){
			if(descripcion.getCodigo().equals(codigoAccion)){
				response.add(descripcion);
			}
		}
		return response;
	}

	/**
	 * 
	 * Manejo de errores.
	 *
	 * @param codRetorno
	 *            the cod retorno
	 */
	private void manejarCasosAlternativos(String codRetorno) {
		if (ERROR_TECNICO.equals(codRetorno)) {

		}
		if (ERROR_AMIGABLE.equals(codRetorno)) {

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.
	 * ConsultaPlazoFijoDAO#obtenerPlazosFijos()
	 */
	@SuppressWarnings("unchecked")
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_PLAZOS_FIJOS })
	@Override
	public List<PlazoFijoEntity> obtenerPlazosFijos() throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new RowMapper<PlazoFijoEntity>() {
			@Override
			public PlazoFijoEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				PlazoFijoEntity plazoFijo = new PlazoFijoEntity();
				plazoFijo.setCodigoPlazo(rs.getString(CODIGO_PLAZO));
				plazoFijo.setDescripcion(rs.getString(DESCRIPCION));
				plazoFijo.setEsAjustable(rs.getString(ES_AJUSTABLE));
				plazoFijo.setEsInteresante(rs.getString(ES_INTERESANTE));
				plazoFijo.setEsPrecancelable(rs.getString(ES_PRECANCELABLE));
				plazoFijo.setEsTasaVariable(rs.getString(ES_TASA_VARIABLE));
				plazoFijo.setHabilitado(rs.getString(HABILITADO));
				plazoFijo.setMostrarFrecIntereses(rs.getString(MOSTRAR_FREC_INTERESES));
				plazoFijo.setNombreLegal(rs.getString(NOMBRE_LEGAL));
				plazoFijo.setAyuda(rs.getString(AYUDA));
				plazoFijo.setMinDiasLiqInt(rs.getString(MIN_DIAS_LIQ_INT));
				plazoFijo.setMoneda(rs.getString(MONEDA));
				plazoFijo.setPrioridad(rs.getString(PRIORIDAD));
				plazoFijo.setCodigoBPriv(rs.getString(CODIGO_BPRIV));

				return plazoFijo;
			}
		}));
		parametros.add(new SqlOutParameter(OUT_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(OUT_ERROR_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource();

		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME, PROCEDURE_VER_PLAZOS_FIJOS, in,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(OUT_RESULTADO);
			if (COD_RETORNO_OK.equals(codRetorno)) {
				List<PlazoFijoEntity> plazosFijosList = (List<PlazoFijoEntity>) respuesta.get(PARAM_CURSOR);
				for (PlazoFijoEntity plazoFijoEntity : plazosFijosList) {
					plazoFijoEntity
							.setAccionesAlVencimiento(consultarAccionAlVencimiento(plazoFijoEntity.getCodigoPlazo()));
				}
				return plazosFijosList;
			} else {
				throw new DAOException((String) respuesta.get(OUT_ERROR_AMIGABLE));
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage());
			throw new DAOException(exception);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.
	 * ConsultaPlazoFijoDAO#obtenerPlazosFijosHabilitados()
	 */
	@Override
	public List<PlazoFijoEntity> obtenerPlazosFijosHabilitados() throws DAOException {
		List<PlazoFijoEntity> plazosHabilitados = new ArrayList<PlazoFijoEntity>();
		List<PlazoFijoEntity> plazos = ContextHolder.getContext().getBean(ConsultaPlazoFijoDAO.class)
				.obtenerPlazosFijos();
		for (PlazoFijoEntity plazoFijo : plazos) {
			if (plazoFijo.getHabilitado().equals(IS_HABILITADO)) {
				plazosHabilitados.add(plazoFijo);
			}
		}
		return plazosHabilitados;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.
	 * ConsultaPlazoFijoDAO#obtenerPlazosFijosHabilitadosPorMoneda(java.lang.
	 * String)
	 */
	@Override
	public List<PlazoFijoEntity> obtenerPlazosFijosHabilitadosPorMoneda(String moneda) throws DAOException {
		List<PlazoFijoEntity> plazosHabilitados = new ArrayList<PlazoFijoEntity>();
		List<PlazoFijoEntity> plazos = ContextHolder.getContext().getBean(ConsultaPlazoFijoDAO.class)
				.obtenerPlazosFijos();
		for (PlazoFijoEntity plazoFijo : plazos) {
			if (plazoFijo.getHabilitado().equals(IS_HABILITADO)
					&& (formatearMoneda(plazoFijo.getMoneda()).equals(moneda) || TipoMonedaInversionEnum.AMBAS
							.getCodigoNumerico().equals(formatearMoneda(plazoFijo.getMoneda())))) {
				plazosHabilitados.add(plazoFijo);
			}
		}
		return plazosHabilitados;
	}

	/**
	 * Formatear moneda.
	 *
	 * @param monedaIn
	 *            the moneda in
	 * @return the string
	 */
	private String formatearMoneda(String monedaIn) {
		String result = monedaIn;
		if (monedaIn.length() == LONGITUD_UNO) {
			result = CONSTANTE_CERO + monedaIn;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.
	 * ConsultaPlazoFijoDAO#obtenerPorCodigo(java.lang.String)
	 */
	@Override
	public PlazoFijoEntity obtenerPorCodigo(String codigo) throws DAOException {
		List<PlazoFijoEntity> plazos = ContextHolder.getContext().getBean(ConsultaPlazoFijoDAO.class)
				.obtenerPlazosFijos();
		// SE REALIZA LA CONVERSION PARA LOS CASOS QUE HAYA CEROS DELANTE DEL
		// NUMERO
		codigo = Integer.valueOf(codigo).toString();
		for (PlazoFijoEntity plazoFijo : plazos) {
			if (codigo.equals(plazoFijo.getCodigoPlazo())) {
				return plazoFijo;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.
	 * ConsultaPlazoFijoDAO#vaciarCachePlazosFijos()
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_PLAZOS_FIJOS, allEntries = true)
	@Override
	public void vaciarCachePlazosFijos() {
		LOGGER.info("Se limpia la cache de fondos.");
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao.ConsultaPlazoFijoDAO#vaciarCacheAcciones()
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_DESCRIPCION_ACCIONES_VENCIMIENTO, allEntries = true)
	@Override
	public void vaciarCacheAcciones() {
		LOGGER.info("Se limpia la cache de acciones al vencimiento.");
		
	}

}
