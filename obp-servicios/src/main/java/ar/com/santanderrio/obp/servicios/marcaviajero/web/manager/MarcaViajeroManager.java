/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ConfirmarNuevoViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.EliminarViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.MarcaViajeroView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.NuevoViajeView;

/**
 * The Interface MarcaViajeroManager.
 */
public interface MarcaViajeroManager {

	/**
	 * Obtener viajes.
	 *
	 * @return the respuesta
	 */
	Respuesta<MarcaViajeroView> obtenerViajes();

	/**
	 * Nuevo viaje.
	 *
	 * @return the respuesta
	 */
	Respuesta<NuevoViajeView> nuevoViaje();

	/**
	 * Confirmar nuevo viaje.
	 *
	 * @param nuevoViaje
	 *            the nuevo viaje
	 * @return the respuesta
	 */
	Respuesta<Void> confirmarNuevoViaje(ConfirmarNuevoViajeView nuevoViaje);

	/**
	 * Eliminar viaje.
	 *
	 * @param eliminarViajeView the eliminar viaje view
	 * @return the respuesta
	 */
	Respuesta<Void> eliminarViaje(EliminarViajeView eliminarViajeView);
	
}
