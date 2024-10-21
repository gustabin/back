package ar.com.santanderrio.obp.servicios.configuration.manager.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.configuration.bo.CustomConfigBO;
import ar.com.santanderrio.obp.servicios.configuration.bo.impl.CustomConfigBOImpl;
import ar.com.santanderrio.obp.servicios.configuration.manager.CustomConfigManager;
import ar.com.santanderrio.obp.servicios.configuration.view.CustomConfigView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CustomConfigManagerImpl.
 */
@Component
public class CustomConfigManagerImpl implements CustomConfigManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomConfigBOImpl.class);

	/** The custom config BO. */
    @Autowired
	private CustomConfigBO customConfigBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The admin hash. */
    @Value("${CUSTOM.CONFIG.HASH:A9C9DF9897C9E75759382BD6EF20083E}")
	private String adminHash;

	@Override
	public Respuesta<Void> set(CustomConfigView view) {
		String adminID = view.getAdminID();
		try {
			if (isValidAdminId(adminID)) {
				return customConfigBO.set(view.getConfigID(), view.getValueMap());
			}
		} catch (Exception e) {
			LOGGER.error("Error al setear custom config", e);
		}
		return respuestaFactory.crearRespuestaError("", 
				TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ERROR_GENERICO);
	}

	/**
	 * Checks if is valid admin id.
	 *
	 * @param adminId the admin id
	 * @return true, if is valid admin id
	 */
	private boolean isValidAdminId(String adminId) {
		if (adminId == null) {
			return false;
		}
		String hash = HashUtils.obtenerHash(adminId);
        return adminHash.equals(hash);
	}

}
