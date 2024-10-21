package ar.com.santanderrio.obp.servicios.configuration.bo.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.configuration.bo.CustomConfigBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.modulos.dao.ModuloPermisoDAO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class CustomConfigManagerImpl.
 */
@Component
public class CustomConfigBOImpl implements CustomConfigBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomConfigBOImpl.class);

    /** The Constant PERMISO_ESTADO. */
    private static final String PERMISO_ESTADO = "PERMISO_ESTADO";

    /** The modulo permiso DAO. */
    @Autowired
    private ModuloPermisoDAO moduloPermisoDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

	@Override
	public Respuesta<Void> set(String configID, Map<String, String> valueMap) {
		try {
			if (PERMISO_ESTADO.equals(configID)) {
				obtenerPermisosActualizados(valueMap);
				return respuestaFactory.crearRespuestaOk(Void.class);
			}
		} catch (DAOException e) {
			LOGGER.info("Error al setear custom config", e);
		}
		return respuestaFactory.crearRespuestaError("", 
				TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
	}

	/**
	 * Obtener permisos actualizados.
	 *
	 * @param valueMap the value map
	 * @return the map
	 * @throws DAOException the DAO exception
	 */
	@CachePut(cacheNames = { CacheConstants.Names.CACHE_MODULOS_PERMISOS })
	private Map<String, ModuloPermiso> obtenerPermisosActualizados(Map<String, String> valueMap) throws DAOException {
		Map<String, ModuloPermiso> modulosPermisos = moduloPermisoDAO.obtenerModulosPermisos();
		Iterator<Entry<String, String>> valueMapIterator = valueMap.entrySet().iterator();
		while (valueMapIterator.hasNext()) {
			Entry<String, String> valueToSet = valueMapIterator.next();
			ModuloPermiso permiso = modulosPermisos.get(valueToSet.getKey());
			if (permiso != null) {
				permiso.setModuloEstado(getModuloEstado(valueToSet.getValue()));
				LOGGER.info("CustomConfig: Actualizado ModuloEstado permiso {} : {}", valueToSet.getKey(), valueToSet.getValue());
			} else {
				LOGGER.info("CustomConfig: Permiso no encontrado: {}", valueToSet.getKey());
			}
		}
		return modulosPermisos;
	}

	/**
	 * Gets the modulo estado.
	 *
	 * @param val the val
	 * @return the modulo estado
	 */
	private ModuloEstado getModuloEstado(String val) {
		return ModuloEstado.values()[Integer.parseInt(val)];
	}

}
