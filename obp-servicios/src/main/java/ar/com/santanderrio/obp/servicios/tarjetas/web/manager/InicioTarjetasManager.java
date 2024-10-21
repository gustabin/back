/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaSeleccionada;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LimitesYDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.StackTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;
import ar.com.santanderrio.obp.servicios.token.externos.view.TokenView;

/**
 * The Interface InicioTarjetasManager.
 * 
 * @author florencia.n.martinez
 *
 */
public interface InicioTarjetasManager {

	/**
	 * Gets the tarjetas.
	 *
	 * @param tarjetaSeleccionada
	 *            the tarjeta seleccionada
	 * @return the tarjetas.
	 */
	Respuesta<TarjetasView> getTarjetas(TarjetaSeleccionada tarjetaSeleccionada);

	/**
	 * Gets the tarjetas List.
	 *
	 * @return the Lis of tarjetas.
	 */
	Respuesta<StackTarjetasView> getlistaTarjetasCliente();
	
	/**
	 * Guardar estadistica de Tarjetas.
	 *
	 * @param codTransaccion
	 *            the codigo transaccion
	 */
	void guardarEstadisticaTarjetas(String codTransaccion);

	/**
	 * Actualizar alias.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param alias
	 *            the alias
	 * @return the respuesta
	 */
	Respuesta<Void> actualizarAlias(String cuenta, String alias);

	/**
	 * Se actualiza la tarjeta favorita.
	 * 
	 * @param cuenta
	 *            the cuenta
	 * @return Respuesta<Void>
	 */
	Respuesta<Void> actualizarTarjetaFavorita(String cuenta);

	/**
	 * Obtener limites Y disponibles.
	 *
	 * @param idCuenta
	 *            the id cuenta
	 * @return the respuesta
	 */
	Respuesta<LimitesYDisponiblesView> obtenerLimitesYDisponibles(String idCuenta);

	
	/**
	 * Validar el reemplazo de tarjetas.
	 *
	 * @return Respuesta<TarjetasView>
	 */
	Respuesta<TarjetasView> validarReemplazoDeTarjetas();

	/**
	 * Obtener token acceso resumen anual.
	 *
	 * @param tarjetaActiva the tarjeta activa
	 * @return the respuesta
	 */
	Respuesta<TokenView> obtenerTokenAccesoResumenAnual(TarjetaActivaView tarjetaActiva);
}
