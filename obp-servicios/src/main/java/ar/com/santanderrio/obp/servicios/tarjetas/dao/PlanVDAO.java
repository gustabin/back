/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConfirmacionSolicitudPlanV;

/**
 * The Interface PlanVDAO.
 */
public interface PlanVDAO {

	/**
	 * Confirmar solicitud plan v.
	 *
	 * @param token
	 *            the token
	 * @return the confirmacion solicitud plan v
	 */
	ConfirmacionSolicitudPlanV confirmarSolicitudPlanV(String token);

}
