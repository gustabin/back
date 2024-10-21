/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.dao;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import oracle.jdbc.OracleTypes;

/**
 * The Class ModuloPermisoDAOImpl.
 *
 * @author sergio.e.goldentair
 */
@Repository("moduloPermisoDAO")
@TargetSystem(DataBase.ESTADISTICAS)
public class ModuloPermisoDAOImpl extends BaseDatoDAOImpl implements ModuloPermisoDAO {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ModuloPermisoDAOImpl.class);

	/** SCHEMA NAME PARA MODULOS. */
	private static final String MODULO_SCHEMA = "hbank";

	/** PACKAGE NAME PARA MODULOS. */
	private static final String MODULO_PACKAGE = "HB_PKG_MODULOS_PERMISOS";

	/** PROCEDURE NAME PARA MODULOS. */
	private static final String MODULO_PROCEDURE = "prc_listar_permisos";

	/** The Constant COD_RETORNO_OK. */
	private static final String MODULO_COD_RETORNO_OK = "0";

	/** nombre del parametro que referencia al cursor retornado. */
	private static final String MODULO_OUT_PARAM_CURSOR = "p_cursor";

	/** Codigo error tecnico para los modulos excluidos. */
	private static final String MODULO_OUT_PARAM_TECNICO = "p_err_tecnico";

	/** Codigo estado resultado para los modulos excluidos. */
	private static final String MODULO_OUT_PARAM_RESULTADO = "p_resultado";

	/** Descripcion amigable del error para los modulos excluidos. */
	private static final String MODULO_OUT_PARAM_AMIGABLE = "p_err_amigable";

	/** Campo de salida codigo de la query. */
	private static final String MODULO_OUT_P_OPERACION = "OPERACION";

	/** Campo de salida estado de la query. */
	private static final String MODULO_OUT_P_ESTADO = "ESTADO";

	/** Campo de salida mensaje de la query. */
	private static final String MODULO_OUT_P_TEXTO = "TEXTO";

	/** Campo de salida menu de la query. */
	private static final String MODULO_OUT_P_MENU = "MENU";

	/** Campo de salida menu de la query. */
	private final String fileAlternativo = "permisos.txt";

	/** The file path. */
	@Autowired
	private FilePath filePath;

	/** The permisos por base. */
	@Value("${PERMISOS_POR_BASE:true}")
	private Boolean permisosPorBase;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO#
	 * obtenerModulosPermisos()
	 */
	@Cacheable(cacheNames = { CacheConstants.Names.CACHE_MODULOS_PERMISOS })
	@Override
	public Map<String, ModuloPermiso> obtenerModulosPermisos() throws DAOException {
		if (permisosPorBase) {
			return obtenerModulosPermisosPorBase();
		} else {
			return obtenerModulosPermisosPorFile();
		}
	}

	/**
	 * Consultar la base de datos para levantar los permisos de cada modulo.
	 *
	 * @return the map
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	private Map<String, ModuloPermiso> obtenerModulosPermisosPorBase() throws DAOException {
		LOGGER.info("Obtener listado de Modulos excluidos desde la base de datos...");
		List<SqlParameter> parametros = new ArrayList<SqlParameter>();
		parametros.add(getSqlOutCursorParameter());
		parametros.add(new SqlOutParameter(MODULO_OUT_PARAM_RESULTADO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(MODULO_OUT_PARAM_TECNICO, Types.VARCHAR));
		parametros.add(new SqlOutParameter(MODULO_OUT_PARAM_AMIGABLE, Types.VARCHAR));

		try {
			Map<String, Object> respuesta = consultar(MODULO_SCHEMA, MODULO_PACKAGE, MODULO_PROCEDURE, null,
					parametros.toArray(new SqlParameter[parametros.size()]));
			String codRetorno = (String) respuesta.get(MODULO_OUT_PARAM_RESULTADO);
			if (MODULO_COD_RETORNO_OK.equals(codRetorno)) {
				return (Map<String, ModuloPermiso>) respuesta.get(MODULO_OUT_PARAM_CURSOR);
			} else {
				throw new DAOException("No se pueden levantar los permisos de la base.");
			}
		} catch (SQLException sqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}.", MODULO_SCHEMA, MODULO_PACKAGE, MODULO_PROCEDURE, sqle);
			throw new DAOException(sqle);
		} catch (UncategorizedSQLException usqle) {
			LOGGER.error("Error al consumir el store {}.{}.{}. errores internos.", MODULO_SCHEMA, MODULO_PACKAGE,
					MODULO_PROCEDURE, usqle);
			throw new DAOException(usqle);
		} catch (InvalidDataAccessApiUsageException idaaue) {
			LOGGER.error("Error al consumir el store {}.{}.{}. no hay grants.", MODULO_SCHEMA, MODULO_PACKAGE,
					MODULO_PROCEDURE, idaaue);
			throw new DAOException(idaaue);
		}
	}

	/**
	 * Levantar los permisos desde un archivo.
	 *
	 * @return the map
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Map<String, ModuloPermiso> obtenerModulosPermisosPorFile() throws DAOException {
		LOGGER.info("Obtener listado de Modulos excluidos desde file {}...", fileAlternativo);
		Map<String, ModuloPermiso> mapRet = new LinkedHashMap<String, ModuloPermiso>();
		try {
			List<String> lines = FileUtils.readLines(new File(filePath.getFilePath() + fileAlternativo),
					ISBANStringUtils.UTF8);
			for (String linea : lines) {
				String[] campos = StringUtils.split(linea, "|");
				ModuloPermiso permiso = new ModuloPermiso();
				AccionController accion = AccionController.obtenerEnumPorDescripcion(campos[0]);
				if (accion != null) {
					permiso.setAccionController(accion);
					permiso.setMensaje(campos[1]);
					permiso.setModuloEstado(ModuloEstado.values()[Long.valueOf(campos[2]).intValue()]);
					permiso.setMenu(Boolean.valueOf(campos[3]).booleanValue());
					mapRet.put(campos[0], permiso);
				} 

			}
		} catch (IOException e) {
			LOGGER.error("Error al leer el archivo.", e);
			throw new DAOException(e);
		} 
		return mapRet;
	}

	/**
	 * Armar configuracion de lectura respuesta llamada al store.
	 * 
	 * @return SqlOutParameter
	 */
	private SqlOutParameter getSqlOutCursorParameter() {
		return new SqlOutParameter(MODULO_OUT_PARAM_CURSOR, OracleTypes.CURSOR,
				new ResultSetExtractor<Map<String, ModuloPermiso>>() {
					@Override
					public Map<String, ModuloPermiso> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						Map<String, ModuloPermiso> mapRet = new LinkedHashMap<String, ModuloPermiso>();
						while (rs.next()) {
							ModuloPermiso permiso = new ModuloPermiso();
							AccionController accion = AccionController
									.obtenerEnumPorDescripcion(rs.getString(MODULO_OUT_P_OPERACION));
							if (accion != null) {
								permiso.setAccionController(accion);
							} else {
								throw new SQLException("La accion retornada para el permiso no es un valor valido.");
							}
							permiso.setMensaje(rs.getString(MODULO_OUT_P_TEXTO));
							permiso.setMenu(Boolean.valueOf(rs.getString(MODULO_OUT_P_MENU)).booleanValue());
							permiso.setModuloEstado(
									ModuloEstado.values()[Long.valueOf(rs.getLong(MODULO_OUT_P_ESTADO)).intValue()]);
							mapRet.put(rs.getString(MODULO_OUT_P_OPERACION), permiso);
						}
						return mapRet;
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO#
	 * limpiarModulosPermisos()
	 */
	@CacheEvict(value = CacheConstants.Names.CACHE_MODULOS_PERMISOS, allEntries = true)
	@Override
	public void limpiarModulosPermisos() {
		LOGGER.info("Limpiar cache {}.", CacheConstants.CACHE_MODULOS_PERMISOS);
	}

}
