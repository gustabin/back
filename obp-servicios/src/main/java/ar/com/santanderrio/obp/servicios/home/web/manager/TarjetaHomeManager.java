/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeTarjetaView;

/**
 * Grupo asociado a Tarjetas.
 *
 * @author B039543
 */
public interface TarjetaHomeManager extends GrupoHomeManager {

	/**
	 * Obtener saldo tarjeta.
	 *
	 * @param cajaTarjetasHomeView
	 *            the caja tarjetas home view
	 * @return the respuesta
	 */
	Respuesta<CajaHomeTarjetaView> obtenerSaldoTarjeta(CajaHomeTarjetaView cajaTarjetasHomeView);
	

	/**
	 * Obtener validacion afinidad.
	 */
	void obtenerValidacionAfinidad();

}
