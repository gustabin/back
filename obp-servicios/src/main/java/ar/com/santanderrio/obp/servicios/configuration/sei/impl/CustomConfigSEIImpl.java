package ar.com.santanderrio.obp.servicios.configuration.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.configuration.manager.CustomConfigManager;
import ar.com.santanderrio.obp.servicios.configuration.sei.CustomConfigSEI;
import ar.com.santanderrio.obp.servicios.configuration.view.CustomConfigView;

/**
 * The Class CustomConfigSEIImpl.
 */
@Component("customConfigSEI")
public class CustomConfigSEIImpl implements CustomConfigSEI {

    @Autowired
	private CustomConfigManager customConfigManager;

	@Override
	public Respuesta<Void> set(CustomConfigView view) {
		return customConfigManager.set(view);
	}

}
