/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DatoClienteDebitoTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DebitoAutomaticoTarjetaDTO;

/**
 * The Interface DebitoAutomaticoTarjetaBO.
 *
 * @author florencia.n.martinez
 */
public interface DebitoAutomaticoTarjetaBO {

	/**
	 * Obtiene una respuesta DTO de la adhesion al debito automatico en la
	 * tarjeta de credito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosClienteDebito
	 *            the datos cliente debito
	 * @param nroTarjetaEnmascarado
	 *            the nro tarjeta enmascarado
	 * @return the debito automatico tarjeta DTO
	 */
	Respuesta<DebitoAutomaticoTarjetaDTO> obtenerAdhesionDebitoTarjeta(Cliente cliente,
			DatoClienteDebitoTarjetaInDTO datosClienteDebito, String nroTarjetaEnmascarado);
}
