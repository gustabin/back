/*
 * 
 */
package ar.com.santanderrio.obp.servicios.logger.dao.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import ar.com.santanderrio.obp.base.dao.impl.BaseDatoDAOImpl;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.credential.DataBase;
import ar.com.santanderrio.obp.servicios.logger.dao.entities.LogError;

/**
 * The Class LogErrorDAOImpl.
 */
@Repository
@TargetSystem(DataBase.ESTADISTICAS)
public class LogErrorDAOImpl extends BaseDatoDAOImpl implements LogErrorDAO {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(LogErrorDAOImpl.class);
    
    /** The Constant MENSAJE_ERROR_EN_STORE. */
    private static final String MENSAJE_ERROR_EN_TABLA = "Error al consultar la base al solicitar informacion log de error {}.";

    /** The Constant ERROR_EN_BASE. */
    private static final String ERROR_EN_BASE = "Error al consultar la base al solicitar informacion log de error {}.";
    
    /** The Constant ERROR_TIMEOUT. */
    private static final String ERROR_TIMEOUT = "Error de Timeout.";
    
    /** The Constant SCHEMA. */
    private static final String SCHEMA = "HBANK";
    
    /** The Constant TABLA. */
    private static final String TABLA = "HB_ERROR_LOG";
    /** Indice del query de insert para NUP. */
    private static final String NUP = "NUP";
    /** Indice del query de insert para fecha de error. */
    private static final String FECHA_ERROR = "FECHA_ERROR";
    /** Indice del query de insert para documento. */
    private static final String DOCUMENTO = "DOCUMENTO";
    /** Indice del query de insert para fecha de nacimiento. */
    private static final String FECHA_NACIMIENTO = "FECHA_NACIMIENTO";
    /** Indice del query de insert para servicio. */
    private static final String SERVICIO = "SERVICIO";
    /** Indice del query de insert para severidad. */
    private static final String SEVERIDAD = "SEVERIDAD";
    /** Indice del query de insert para descripcion de error servicio. */
    private static final String DESCRIPCION_ERROR_SERVICIO = "DESCRIPCION_ERROR_SERVICIO";
    /** Indice del query de insert para clase de java. */
    private static final String CLASE_JAVA = "CLASE_JAVA";
    /** Indice del query de insert para archivo de log. */
    private static final String ARCHIVO_LOG = "ARCHIVO_LOG";
    /** Indice del query de insert para servidor. */
    private static final String SERVIDOR = "SERVIDOR";
    /** Indice del query de insert para descripcion de java. */
    private static final String DESCRIPCION_JAVA = "DESCRIPCION_JAVA";
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.comun.estadistica.dao.impl.LogErrorDAO#agregar(ar.com.santanderrio.obp.servicios.comun.estadistica.dao.impl.LogError)
     */
    @Override
    public void agregar(LogError logError) {
        if (logError != null && this.seDebeLoguear(logError)) {
            LOGGER.info("Se consultara la tabla {} con el logger {}.", TABLA, logError);
            MapSqlParameterSource  parametros = new MapSqlParameterSource();
            agregarParametro(parametros, NUP, logError.getNup());
            agregarParametro(parametros, FECHA_ERROR, logError.getFechaError());
            agregarParametro(parametros, DOCUMENTO, logError.getDocumento());
            agregarParametro(parametros, FECHA_NACIMIENTO, logError.getFechaNacimiento());
            agregarParametro(parametros, SERVICIO, logError.getServicio());
            agregarParametro(parametros, SEVERIDAD, logError.getSeveridad());
            agregarParametro(parametros, DESCRIPCION_ERROR_SERVICIO, logError.getDescripcionErrorServicio());
            agregarParametro(parametros, CLASE_JAVA, logError.getClaseJava());
            agregarParametro(parametros, ARCHIVO_LOG, logError.getArchivoLog());
            agregarParametro(parametros, SERVIDOR, logError.getServidor());
            agregarParametro(parametros, DESCRIPCION_JAVA, logError.getDescripcionJava());
            
            try {
                super.insertTable(SCHEMA, TABLA, parametros);
            } catch (DataAccessResourceFailureException dae) {
                LOGGER.error(ERROR_EN_BASE, logError, dae);
            } catch (SQLException sqle) {
                LOGGER.error(MENSAJE_ERROR_EN_TABLA, logError, sqle);
            } catch (UncategorizedSQLException ex) {
                LOGGER.error(ERROR_EN_BASE, logError, ex);
            } catch (InvalidDataAccessApiUsageException invalidData) {
                LOGGER.error(MENSAJE_ERROR_EN_TABLA, logError, invalidData);
            } catch (IllegalStateException iex) {
                LOGGER.error(MENSAJE_ERROR_EN_TABLA, logError, iex);
            } catch (QueryTimeoutException qte) {
                LOGGER.error(ERROR_TIMEOUT, logError, qte);
            }
        }
    }
    
    /**
	 * Se debe loguear el elemento.
	 *
	 * @param logError
	 *            the log error
	 * @return true, if successful
	 */
    private boolean seDebeLoguear(LogError logError) {
		return logError != null && StringUtils.isNotEmpty(logError.getDocumento());
	}

	/**
     * Agregar parametro.
     *
     * @param parametros the parametros
     * @param paramName the param name
     * @param object the object
     */
    private void agregarParametro(MapSqlParameterSource  parametros, String paramName, Object object) {
        if ( object == null ) { 
        	return; 
        }
    	if ( !(object instanceof String) 
    		|| (object instanceof String && StringUtils.isNotEmpty((String)object))) {
    		parametros.addValue(paramName, object);
    	}
        
    }

}
