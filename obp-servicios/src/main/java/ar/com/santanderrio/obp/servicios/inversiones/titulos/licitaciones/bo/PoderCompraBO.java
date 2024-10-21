/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;

/**
 * The Interface PoderCompraBO.
 */
public interface PoderCompraBO {
	
	/**
	 * Simular adhesion.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<AdhesionPDCOutDTO> simularAdhesion(AdhesionPDCRequestDTO request, Cliente cliente, TipoBancaEnum tipoBancaEnum);

	/**
	 * Finalizar adhesion.
	 *
	 * @param request
	 *            the request
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<FinalizarAdhesionDTO> finalizarAdhesion(FinalizarAdhesionPDC request, Cliente cliente, TipoBancaEnum tipoBancaEnum);
}
