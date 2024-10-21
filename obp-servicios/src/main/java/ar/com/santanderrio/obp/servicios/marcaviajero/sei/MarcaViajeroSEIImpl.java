/*
 * 
 */
package ar.com.santanderrio.obp.servicios.marcaviajero.sei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.manager.MarcaViajeroManager;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ConfirmarNuevoViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.EliminarViajeView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.MarcaViajeroView;
import ar.com.santanderrio.obp.servicios.marcaviajero.web.view.NuevoViajeView;

/**
 * The Class MarcaViajeroSEIImpl.
 */
@Component
public class MarcaViajeroSEIImpl implements MarcaViajeroSEI {

	/** The manager. */
	@Autowired
	private MarcaViajeroManager manager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.marcaviajero.sei.MarcaViajeroSEI#
	 * obtenerCuentas()
	 */
	@Override
	public Respuesta<MarcaViajeroView> obtenerViajes() {
		return manager.obtenerViajes();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.marcaviajero.sei.MarcaViajeroSEI#
	 * nuevoViaje()
	 */
	@Override
	public Respuesta<NuevoViajeView> nuevoViaje() {
		return manager.nuevoViaje();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.marcaviajero.sei.MarcaViajeroSEI#confirmarViaje(ar.com.santanderrio.obp.servicios.marcaviajero.web.view.ConfirmarNuevoViajeView)
	 */
	@Override
	public Respuesta<Void> confirmarViaje(ConfirmarNuevoViajeView nuevoViaje) {
		return manager.confirmarNuevoViaje(nuevoViaje);
	}

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.marcaviajero.sei.MarcaViajeroSEI#
     * eliminarViaje(ar.com.santanderrio.obp.servicios.marcaviajero.web.view.
     * EliminarViajeView)
     */
    @Override
    public Respuesta<Void> eliminarViaje(EliminarViajeView eliminarViajeView) {
        return manager.eliminarViaje(eliminarViajeView);
    }

}
