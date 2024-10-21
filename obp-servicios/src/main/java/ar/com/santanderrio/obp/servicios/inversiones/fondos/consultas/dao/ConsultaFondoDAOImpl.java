/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import oracle.jdbc.OracleTypes;

/**
 * The Class ConsultaFondoDAOImpl.
 */
@Component
@TargetSystem(DataBase.ESTADISTICAS)
public class ConsultaFondoDAOImpl extends BaseDatoDAOImpl implements ConsultaFondoDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaFondoDAOImpl.class);

	/** SCHEMA. */
	private static final String SCHEMA_NAME = "hbank";

	/** PACKAGE. */
	private static final String PACKAGE_NAME = "HB_PKG_VER_FONDOS";

	/** The procedure version. */
	@Value("${VERFONDOS.VERSION}")
	private String procedureVersion;
	
	private static final String REPATRIACION="REP";

	/** PROCEDURE. */
	private static final String PROCEDURE_NAME = "ver_fondos";

	/** Constante COD_RESULTADO_OK. */
	private static final String COD_RESULTADO_OK = "0";

	/** Constante P_RETORNO. */

	private static final String PARAM_CURSOR = "p_cursor";

	/** The Constant PARAM_TECNICO. */
	private static final String PARAM_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado. */
	private static final String PARAM_RESULTADO = "p_resultado";

	/** Descripcion amigable del error. */
	private static final String PARAM_AMIGABLE = "p_err_amigable";

	/** The Constant PARAM_CODIGO_FONDO. */
	private static final String PARAM_CODIGO_FONDO = "p_codigo_fondo";

	/** The Constant PARAM_BANCA. */
	private static final String PARAM_BANCA = "p_banca";

	/** The Constant P_ID. */
	private static final int P_ID = 1;

	/** The Constant P_CODIGO_FONDO. */
	private static final int P_CODIGO_FONDO = 2;

	/** The Constant P_ESPECIE. */
	private static final int P_ESPECIE = 3;

	/** The Constant P_NOMBRE_FONDO. */
	private static final int P_NOMBRE_FONDO = 4;

	/** The Constant P_MONEDA. */
	private static final int P_MONEDA = 5;

	/** The Constant P_LIM_MIN_SUSCRIPCION. */
	private static final int P_LIM_MIN_SUSCRIPCION = 6;

	/** The Constant P_LIM_MAX_SUSCRIPCION. */
	private static final int P_LIM_MAX_SUSCRIPCION = 7;

	/** The Constant P_LIM_MIN_RESCATE. */
	private static final int P_LIM_MIN_RESCATE = 8;

	/** The Constant P_LIM_MAX_RESCATE. */
	private static final int P_LIM_MAX_RESCATE = 9;

	/** The Constant P_PLAZO_EFECTIVO. */
	private static final int P_PLAZO_EFECTIVO = 10;

	/** The Constant P_CONTRATO_SUSCRIPCION. */
	private static final int P_CONTRATO_SUSCRIPCION = 11;

	/** The Constant P_CODIGO_AGRUPADOR. */
	private static final int P_CODIGO_AGRUPADOR = 12;

	/** The Constant P_ORDEN_AGRUPACION. */
	private static final int P_ORDEN_AGRUPACION = 13;

	/** The Constant P_DESCRIPCION. */
	private static final int P_DESCRIPCION = 14;

	/** The Constant P_LINK_CARTERA. */
	private static final int P_LINK_CARTERA = 15;

	/** The Constant P_LINK_REGLA. */
	private static final int P_LINK_REGLA = 16;

	/** The Constant P_HABILITAR_SUSCRIPCION. */
	private static final int P_HABILITAR_SUSCRIPCION = 17;

	/** The Constant P_AGRUPADOR_SUSCRIPCION. */
	private static final int P_AGRUPADOR_SUSCRIPCION = 18;

	/** The Constant P_HABILITAR_RESCATE. */
	private static final int P_HABILITAR_RESCATE = 19;

	/** The Constant P_HABILITAR_TRANSFERENCIA. */
	private static final int P_HABILITAR_TRANSFERENCIA = 20;

	/** The Constant P_TIPO_BANCA. */
	private static final int P_TIPO_BANCA = 21;

	/** The Constant P_ID_LEGALES. */
	private static final int P_ID_LEGALES = 22;

	/** The Constant P_ID_LEGALES_2. */
	private static final int P_ID_LEGALES_2 = 23;

	/** The Constant P_ID_LEGALES_3. */
	private static final int P_ID_LEGALES_3 = 24;

	/** The Constant DESCRIP_MAS. */
	private static final int DESCRIP_MAS = 25;

	/** The Constant HORARIO. */
	private static final int HORARIO = 26;

	/** The Constant HONO_ADMIN. */
	private static final int COMISION_TOTAL = 27;

	/** The Constant HONO_ENTRADA. */
	private static final int HONO_ENTRADA = 28;

	/** The Constant HONO_SALIDA. */
	private static final int HONO_SALIDA = 29;

	/** The Constant HONO_SALIDA. */
	private static final int S_GTE = 30;

	/** The Constant HONO_SALIDA. */
	private static final int S_DEP = 31;

	/** The Constant AMBAS_BANCAS. */
	private static final Object AMBAS_BANCAS = "RP";
	
	/** The Constant EXCITI. */
	private static final int EXCITI = 32;
	
	/**
	 * {@inheritDoc}
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_FONDOS })
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsultaFondoOutEntity> obtenerFondos() throws DAOException {
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(new SqlParameter(PARAM_CODIGO_FONDO, Types.VARCHAR));
		parametros.add(new SqlParameter(PARAM_BANCA, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_CURSOR, OracleTypes.CURSOR, new RowMapper<ConsultaFondoOutEntity>() {
			@Override
			public ConsultaFondoOutEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				ConsultaFondoOutEntity consultaFondoOutEntity = new ConsultaFondoOutEntity();
				consultaFondoOutEntity.setCodigoFondo(rs.getString(P_ID));
				consultaFondoOutEntity.setCodigoFondo(rs.getString(P_CODIGO_FONDO));
				consultaFondoOutEntity.setEspecie(rs.getString(P_ESPECIE));
				consultaFondoOutEntity.setNombreFondo(rs.getString(P_NOMBRE_FONDO));
				consultaFondoOutEntity.setMoneda(rs.getString(P_MONEDA));
				consultaFondoOutEntity.setLimiteMinimoSuscripcion(rs.getString(P_LIM_MIN_SUSCRIPCION));
				consultaFondoOutEntity.setLimiteMaximoSuscripcion(rs.getString(P_LIM_MAX_SUSCRIPCION));
				consultaFondoOutEntity.setLimiteMinimoRescate(rs.getString(P_LIM_MIN_RESCATE));
				consultaFondoOutEntity.setLimiteMaximoRescate(rs.getString(P_LIM_MAX_RESCATE));
				consultaFondoOutEntity.setPlazoEfectivo(rs.getString(P_PLAZO_EFECTIVO));
				consultaFondoOutEntity.setContratoSuscripcion(rs.getString(P_CONTRATO_SUSCRIPCION));
				consultaFondoOutEntity.setContratoSuscripcion(rs.getString(P_CONTRATO_SUSCRIPCION));
				consultaFondoOutEntity.setCodigoAgrupador(rs.getString(P_CODIGO_AGRUPADOR));
				consultaFondoOutEntity.setOrdenAgrupacion(rs.getString(P_ORDEN_AGRUPACION));
				consultaFondoOutEntity.setDescripcion(rs.getString(P_DESCRIPCION));
				consultaFondoOutEntity.setLinkCartera(rs.getString(P_LINK_CARTERA));
				consultaFondoOutEntity.setLinkRegla(rs.getString(P_LINK_REGLA));
				consultaFondoOutEntity.setHabilitarSuscripcion(rs.getString(P_HABILITAR_SUSCRIPCION));
				consultaFondoOutEntity.setAgrupadorSuscripcion(rs.getString(P_AGRUPADOR_SUSCRIPCION));
				consultaFondoOutEntity.setHabilitarRescate(rs.getString(P_HABILITAR_RESCATE));
				consultaFondoOutEntity.setHabilitarTransferencia(rs.getString(P_HABILITAR_TRANSFERENCIA));
				consultaFondoOutEntity.setTipoBanca(rs.getString(P_TIPO_BANCA));
				consultaFondoOutEntity.setIdMensajeGastos(rs.getString(P_ID_LEGALES));
				consultaFondoOutEntity.setDescripcionLarga(rs.getString(DESCRIP_MAS));
				consultaFondoOutEntity.setHorario(rs.getString(HORARIO));
				consultaFondoOutEntity.setIdLegalesInformacion(rs.getString(P_ID_LEGALES_2));
				consultaFondoOutEntity.setIdLegales3(rs.getString(P_ID_LEGALES_3));
				consultaFondoOutEntity.setDescripcionAdicional(rs.getString(DESCRIP_MAS));
				consultaFondoOutEntity.setHonorariosAdmin(rs.getString(COMISION_TOTAL));
				consultaFondoOutEntity.setHonorariosEntrada(rs.getString(HONO_ENTRADA));
				consultaFondoOutEntity.setHonorariosSalida(rs.getString(HONO_SALIDA));
				consultaFondoOutEntity.setsGTE(rs.getString(S_GTE));
				consultaFondoOutEntity.setsDEP(rs.getString(S_DEP));
				consultaFondoOutEntity.setExCiti(rs.getString(EXCITI));
				if(rs.getString(P_ESPECIE)!=null && rs.getString(P_ESPECIE).equals(REPATRIACION)) {
					consultaFondoOutEntity.setRepatriacion(true);
				}
				return consultaFondoOutEntity;
			}
		}));
		parametros.add(new SqlOutParameter(PARAM_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(PARAM_AMIGABLE, Types.VARCHAR));

		SqlParameterSource in = new MapSqlParameterSource().addValue(PARAM_CODIGO_FONDO, "").addValue(PARAM_BANCA, "");

		try {
			Map<String, Object> respuesta = consultar(SCHEMA_NAME, PACKAGE_NAME + procedureVersion,
					PROCEDURE_NAME +  "_V2", in, parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(PARAM_RESULTADO);
			if (COD_RESULTADO_OK.equals(codRetorno)) {
				List<ConsultaFondoOutEntity> consultaFondoOutEntityList = (List<ConsultaFondoOutEntity>) respuesta
						.get(PARAM_CURSOR);
				return consultaFondoOutEntityList;
			} else {
				throw new DAOException((String) respuesta.get(PARAM_AMIGABLE));
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
			throw new DAOException(exception);
		}
	}

	/**
	 * Pide el bean al contexto ya que para utilizar la cache hay que entrar por
	 * metodos publicos por ser un proxy. No ir directo ya que no devuelve el
	 * valor cacheado.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the consulta fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ConsultaFondoOutEntity obtenerPorCodigo(String codigo) throws DAOException {
		List<ConsultaFondoOutEntity> fondos = ContextHolder.getContext().getBean(ConsultaFondoDAO.class)
				.obtenerFondos();
		for (ConsultaFondoOutEntity fondo : fondos) {
			if (fondo.getCodigoFondo().equals(codigo)) {
				return fondo;
			}
		}
		LOGGER.info("No se encontro fondo con el codido {} .", codigo);
		throw new DAOException("El codigo consultado no es valido.");

	}
	
	/**
	 * Pide el bean al contexto ya que para utilizar la cache hay que entrar por
	 * metodos publicos por ser un proxy. No ir directo ya que no devuelve el
	 * valor cacheado.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the consulta fondo out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Override
	public ConsultaFondoOutEntity obtenerPorCodigo(int codigo) throws DAOException {
		List<ConsultaFondoOutEntity> fondos = ContextHolder.getContext().getBean(ConsultaFondoDAO.class)
				.obtenerFondos();
		for (ConsultaFondoOutEntity fondo : fondos) {
			if (Integer.valueOf(fondo.getCodigoFondo()).intValue() == codigo) {
				return fondo;
			}
		}
		LOGGER.info("No se encontro fondo con el codido {} .", codigo);
		throw new DAOException("El codigo consultado no es valido.");

	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.
	 * ConsultaFondoDAO#obtenerPorBanca(java.lang.String)
	 */
	@Override
	public List<ConsultaFondoOutEntity> obtenerPorBanca(String banca) throws DAOException {
		List<ConsultaFondoOutEntity> fondosPorBanca = new ArrayList<ConsultaFondoOutEntity>();
		List<ConsultaFondoOutEntity> fondos = ContextHolder.getContext().getBean(ConsultaFondoDAO.class)
				.obtenerFondos();
		for (ConsultaFondoOutEntity fondo : fondos) {
			if (fondo.getTipoBanca().equals(banca) || fondo.getTipoBanca().equals(AMBAS_BANCAS)) {
				fondosPorBanca.add(fondo);
			}
		}
		/*
		int i=0;
		int indexFondo=0;
		boolean rep=false;
		for(ConsultaFondoOutEntity fondo:fondosPorBanca) {
			if(fondo.getCodigoFondo().equals("007")) {
				rep=true;
				indexFondo=i;
			}
		i++;
		}
		if(rep) {
			fondosPorBanca.get(indexFondo).setRepatriacion(true);
		}
		*/
		return fondosPorBanca;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.
	 * ConsultaFondoDAO#vaciarCache()
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_FONDOS, allEntries = true)
	@Override
	public void vaciarCache() {
		LOGGER.info("Se limpia la cache de fondos.");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.
	 * ConsultaFondoDAO#obtenerFondos(org.apache.commons.collections.Predicate)
	 */
	@Override
	public List<ConsultaFondoOutEntity> obtenerFondos(Predicate predicate) throws DAOException {
		List<ConsultaFondoOutEntity> fondosFiltrados = new ArrayList<ConsultaFondoOutEntity>();
		List<ConsultaFondoOutEntity> fondos = ContextHolder.getContext().getBean(ConsultaFondoDAO.class)
				.obtenerFondos();
		for (ConsultaFondoOutEntity fondo : fondos) {
			if (predicate.evaluate(fondo)) {
				fondosFiltrados.add(fondo);
			}
		}
		return fondosFiltrados;
	}

}
