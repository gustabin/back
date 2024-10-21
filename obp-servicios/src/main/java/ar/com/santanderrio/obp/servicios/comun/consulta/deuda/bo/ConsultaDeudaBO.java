/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.consulta.deuda.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.consulta.deuda.entity.ClasificacionDeuda;

/**
 * The Interface ConsultaDeudaBO.
 */
public interface ConsultaDeudaBO {

	/**
	 * Consultar deuda.
	 *
	 * @param nup
	 *            the nup
	 * @return the respuesta
	 */
	Respuesta<ClasificacionDeuda> consultarDeuda(String nup);

}
