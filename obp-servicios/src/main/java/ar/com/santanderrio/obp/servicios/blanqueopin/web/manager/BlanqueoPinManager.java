package ar.com.santanderrio.obp.servicios.blanqueopin.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.blanqueopin.web.view.BlanqueoPinInicioView;
import ar.com.santanderrio.obp.servicios.perfil.view.BlanqueoPinView;

public interface BlanqueoPinManager {

	/**
	 * Inicio blanqueo de pin
	 * 
	 * @return Respuesta<BlanqueoPinInicioView>
	 */
	public Respuesta<BlanqueoPinInicioView> inicioBlanqueoPin();

	/**
	 * Blanquea pin y devuelve feedback
	 * 
	 * @return
	 */
	public Respuesta<BlanqueoPinView> blanquearPinRSA(BlanqueoPinView blanqueoPin);

}
