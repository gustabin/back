/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.util.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.AutentificacionManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.util.view.MicroFEAccessView;
import ar.com.santanderrio.obp.servicios.comun.view.FechaView;

/**
 * The Interface ComunManager.
 */
public interface ComunManager {

	/**
	 * Gets the fecha actual.
	 *
	 * @return the fecha actual
	 */
	FechaView getFechaActual();

	/**
	 * Este metodo se encarga de vaciar el desafio en curso de la sesion. Accion
	 * necesaria para el boton cancelar del popup de desafios.
	 *
	 * @author emilio.watemberg.
	 * @see {@link AutentificacionManagerImpl}
	 * @since Aug 7, 2017.
	 */
	void cancelarDesafioEnCurso();

	Respuesta<Void> grabarEstadisticaVisualizacionResumenTyC();

	/**
	 * Acceso MF.
	 *
	 * @param microFEAccessView the micro FE access view
	 * @return the respuesta
	 */
	Respuesta<Void> accesoMF(MicroFEAccessView microFEAccessView);

}
