/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.bo.Cvv2BO;

/**
 * Esta clase la implementa {@link Auntentificacion} para validaciones CVV2.
 *
 */
@Component
public class Cvv2Desafio extends Desafio<AutentificacionDTO> {

	/** The cvv2 BO. */
	@Autowired
	private Cvv2BO cvv2BO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio#
	 * solicitar()
	 */
	@Override
	public Respuesta<AutentificacionDTO> solicitar() {
		return cvv2BO.solicitarCvv2(super.codigoEstadisticaSolicitud);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio#
	 * ejecutar(java.lang.Object)
	 */
	@Override
	public Respuesta<AutentificacionDTO> ejecutar(AutentificacionDTO autentificacionDTO) {
		return cvv2BO.validarCvv2(autentificacionDTO, super.codigoEstadisticaValidacion);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Desafio<AutentificacionDTO> o) {
		return Comparators.PRIORIDAD.compare(this, o);
	}

}
