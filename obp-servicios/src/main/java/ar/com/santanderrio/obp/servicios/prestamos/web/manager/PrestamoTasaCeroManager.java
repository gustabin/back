/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.web.manager;

import org.springframework.stereotype.Component;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ConfirmacionPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.DatosPrestamoTasaCeroView;
import ar.com.santanderrio.obp.servicios.prestamos.view.SolicitudPrestamoTasaCeroView;


// TODO: Auto-generated Javadoc
/**
 * The Interface PrestamoTasaCeroManager.
 */
@Component
public interface PrestamoTasaCeroManager {
	
	/**
	 * Inicio prestamos tasa cero.
	 *
	 * @return the respuesta
	 */
	Respuesta<DatosPrestamoTasaCeroView> inicioPrestamosTasaCero();
	
	
	/**
	 * Solicitar prestamos tasa cero.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	Respuesta<ComprobantePrestamoTasaCeroView> solicitarPrestamosTasaCero(ConfirmacionPrestamoTasaCeroView view);
	
}
