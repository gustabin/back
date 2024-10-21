/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ResumenAnteriorViewResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;

/**
 * Gestiona la logica relacionada a ResumenAnterior.
 * 
 * @author
 *
 */
public interface ResumenAnteriorManager {

	/**
	 * realiza obtenerResumen en ResumenAnterior.
	 *
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return respuesta con el objeto view response.
	 */
	Respuesta<ResumenAnteriorViewResponse> obtenerResumenesAnteriores(TarjetaActivaView tarjetaActiva);

	/**
	 * Grabar estadistica de resumenes anteriores.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 */
	void grabarEstadisticaDeResumenesAnteriores(EstadoRespuesta estadoRespuesta);
}
