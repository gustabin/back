package ar.com.santanderrio.obp.servicios.campaniabeneficios.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosInView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaBeneficiosOutView;
import ar.com.santanderrio.obp.servicios.campaniabeneficios.view.CampaniaSuscripcionOutView;

/**
 * The Interface CampaniaBeneficiosMananager.
 */
public interface CampaniaBeneficiosMananager {

	/**
	 * Suscripcion campania beneficios.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<CampaniaSuscripcionOutView> suscripcionCampaniaBeneficios(CampaniaBeneficiosInView inView);
	
	/**
	 * Cons susc campania beneficios.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */
	Respuesta<CampaniaBeneficiosOutView> consSuscCampaniaBeneficios(CampaniaBeneficiosInView inView);

}
