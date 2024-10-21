/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.EliminarViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.NuevoViaje;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajeHabilitado;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.ViajesHabilitados;

/**
 * The Interface MarcaViajeroBO.
 */
public interface MarcaViajeroBO {

    /**
     * Obtener viajes.
     *
     * @param cliente
     *            the cliente
     * @param email
     *            the email
     * @return the respuesta
     */
    Respuesta<ViajesHabilitados> obtenerViajes(Cliente cliente, String email);

    /**
     * Nuevo viaje.
     *
     * @param cliente
     *            the cliente
     * @param mailCliente
     *            the mail cliente
     * @return the respuesta
     */
    Respuesta<NuevoViaje> nuevoViaje(Cliente cliente, String mailCliente);

    /**
	 * Confirmar nuevo viaje.
	 *
	 * @param viajeHabilitado
	 *            the viaje habilitado
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
    Respuesta<Void> confirmarNuevoViaje(ViajeHabilitado viajeHabilitado,
            Cliente cliente);

    /**
	 * Eliminar viaje.
	 *
	 * @param viaje
	 *            the viaje
	 * @return the respuesta
	 */
    Respuesta<Void> eliminarViaje(EliminarViaje viaje);

}
