package ar.com.santanderrio.obp.servicios.prestamos.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.sei.PrestamoTasaCero;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ConfirmacionPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DatosPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoTasaCeroManager;

/**
 * The Class PrestamoTasaCeroSEIImpl.
 */
@Component("prestamoTasaCeroSEI")
public class PrestamoTasaCeroSEIImpl implements PrestamoTasaCero {

	/** The prestamo manager. */
	@Autowired
	private PrestamoTasaCeroManager prestamoTasaCeroManager;

	/**
	 * Inicio prestamos tasa cero.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<DatosPrestamoTasaCeroView> inicioPrestamosTasaCero() {
		return prestamoTasaCeroManager.inicioPrestamosTasaCero();
	}

	/**
	 * Solicitar prestamos tasa cero.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobantePrestamoTasaCeroView> solicitarPrestamosTasaCero(ConfirmacionPrestamoTasaCeroView view) {
		return prestamoTasaCeroManager.solicitarPrestamosTasaCero(view);
	}

}
