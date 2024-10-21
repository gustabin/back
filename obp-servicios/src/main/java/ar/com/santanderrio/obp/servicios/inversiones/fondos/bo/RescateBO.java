/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;

/**
 * The Interface RescateBO.
 *
 * @author b039920
 */
public interface RescateBO {

	/**
	 * Finalizar rescate de un fondo, este servicio se debe invocar luego de
	 * aceptar la confirmacion del rescate de un fondo.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<FinalizarRescateDTO> finalizarRescate(FinalizarRescateInDTO dtoRequest, Cliente cliente);
	
	/**
	 * Finalizar rescate de un fondo ex-citi, este servicio se debe invocar luego de
	 * aceptar la confirmacion del rescate de un fondo ex-citi.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<FinalizarRescateDTO> finalizarRescateFDC(FinalizarRescateInDTO dtoRequest, Cliente cliente);
	
	/**
	 * Simular rescate fondo.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<SimularRescateOutDTO> simularRescateFondo(SimularRescateInDTO dtoRequest, Cliente cliente);

	/**
	 * Simular rescate fondo B priv.
	 *
	 * @param generateDTO
	 *            the generate DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<SimularRescateOutDTO> simularRescateFondoBPriv(SimulacionInDTO generateDTO, Cliente cliente);

	/**
	 * Obtiene la lista de fondos Suscriptos.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptos(CuentasConsultaFondoDTO viewRequest, Cliente cliente);

	/**
	 * Obtener fondos suscriptos B priv.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosBPriv(CuentasConsultaFondoDTO requestDTO,
			Cliente cliente);

	/**
	 * Finalizar rescate de un fondo, este servicio se debe invocar luego de
	 * aceptar la confirmacion del rescate de un fondo.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<FinalizarRescateBPrivDTO> finalizarRescateBPriv(FinalizarRescateBPrivInDTO dtoRequest, Cliente cliente);

	/**
	 * Metodo que ejecutar el rescate desde la grilla para devolver el plazo
	 * efectivo del fondo, la cuenta operativa sin formatear y los limites
	 * minimo y maximo del rescate.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param cliente
	 *            the cliente
	 * @return Plazo efectivo y Cuenta operativa sin formatear
	 */
	Respuesta<RescateDesdeGrilla> obtenerRescateDesdeGrilla(RescateDesdeGrillaInView viewRequest, Cliente cliente);
	
	Respuesta<FinalizarRescateView> finalizarRescateFondoPorEspecie(FinalizarRescateInView viewRequest, Cliente cliente, String banca);
	
	Respuesta<FinalizarRescateView> finalizarRescateFondoPorEspecieBPriv(FinalizarRescateBPrivInView viewRequest, Cliente cliente, String banca);

}
