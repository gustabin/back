package ar.com.santanderrio.obp.servicios.referidos.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.referidos.manager.ReferidosManager;
import ar.com.santanderrio.obp.servicios.referidos.sei.ReferidosSEI;
import ar.com.santanderrio.obp.servicios.referidos.view.DatosEstadisticasReferidosView;
import ar.com.santanderrio.obp.servicios.referidos.view.ReferidosInicioResponseView;

/**
 * Endpoints para referidos
 * @author A309331
 *
 */

@Component("referidosSEI")
public class ReferidosSEIImpl implements ReferidosSEI {

	@Autowired
	private ReferidosManager referidosManager;

	/**
	 * Obtiene y devuelve los datos necesarios para el inicio de referidos
	 */
	@Override
	public Respuesta<ReferidosInicioResponseView> obtenerInicioReferidos() {
		return referidosManager.obtenerInicioReferidos();
	}

	@Override
	public void grabarEstadisticaReferidos(DatosEstadisticasReferidosView referidosView) {
		referidosManager.grabarEstadisticaReferidos(referidosView);
	}

	@Override
	public Respuesta<ReferidosInicioResponseView> inicioReferidos() {
		return referidosManager.inicioReferidos();
	}
	
	
}
