package ar.com.santanderrio.obp.servicios.login.manager.microfront.strategies;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;

@Component
public class SantanderExpressStrategyImpl  extends MicrofrontStrategyImpl {

	@Override
	public Boolean debeAgregarCampania(String nup) {
		return false;
	}

	@Override
	public String obtenerLink() {
		return AccionController.IR_EXTRACCION_SANTANDER_EXPRESS.getDescripcion();
	}

}
