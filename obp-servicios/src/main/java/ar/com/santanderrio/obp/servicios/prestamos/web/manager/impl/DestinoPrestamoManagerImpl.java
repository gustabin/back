/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.DestinoPrestamoManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;

/**
 * The Class DestinoPrestamoManagerImpl.
 */
@Component
public class DestinoPrestamoManagerImpl implements DestinoPrestamoManager {

	/** The destino prestamo bo. */
	@Autowired
	private DestinoPrestamoBO destinoPrestamoBo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.prestamos.web.manager.
	 * DestinoPrestamoManager#vaciarDestinosPrestamo()
	 */
	@Override
	public Respuesta<Boolean> vaciarDestinosPrestamo() {
		return destinoPrestamoBo.vaciarDestinosPrestamo();
	}
}
