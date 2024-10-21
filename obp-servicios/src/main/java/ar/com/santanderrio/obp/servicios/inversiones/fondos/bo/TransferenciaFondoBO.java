/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.ConfigTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarTransferenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;

/**
 * Gestiona la logica de negocio relacionada a Transferencia.
 * 
 * @author
 *
 */
public interface TransferenciaFondoBO {

	/**
	 * Obtener fondos suscriptos Y disponibles.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponibles(CuentasConsultaFondoDTO requestDTO,
			Cliente cliente);

	/**
	 * Obtener fondos suscriptos Y disponibles bpriv.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponiblesBpriv(CuentasConsultaFondoDTO requestDTO,
			Cliente cliente);

	/**
	 * Obtener datos config.
	 *
	 * @param configTransferenciaInView
	 *            the config transferencia in view
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ConfigTransferenciaDTO> obtenerDatosConfig(ConfigTransferenciaInView configTransferenciaInView,
			Cliente cliente);

	/**
	 * Simular transferencia.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TransferenciaDTO> simularTransferencia(TransferenciaDTO transferenciaDTO, Cliente cliente);

	/**
	 * Realiza la confirmación de una transferencia.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TransferenciaDTO> finalizarTransferirFondos(TransferenciaDTO requestDTO, Cliente cliente);

	/**
	 * Simular transferencia B priv.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @return the respuesta
	 */
	Respuesta<SimularSuscripcionOutDTO> simularTransferenciaBPriv(SimulacionInDTO requestDTO);

	/**
	 * Finalizar transferencia B priv.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<FinalizarTransferenciaBprivDTO> finalizarTransferenciaBPriv(FinalizarTransferenciaBprivDTO dtoRequest,
			Cliente cliente);

}
