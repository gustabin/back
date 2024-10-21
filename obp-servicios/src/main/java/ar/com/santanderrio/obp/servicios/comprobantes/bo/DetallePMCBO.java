/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

/**
 * The Interface DetallePMCBO.
 *
 * @author luis.pedro.lopez
 */
public interface DetallePMCBO {

	/**
	 * Obtener detalle PMC.
	 *
	 * @param fiid
	 *            the fiid
	 * @param codigoValidacion
	 *            the codigo validacion
	 * @param empresa
	 *            the empresa
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<DetalleComprobanteDTO> obtenerDetallePMC(String fiid, String codigoValidacion, String empresa,
			Cliente cliente);

	/**
	 * Obtener comprobantes por empresas.
	 *
	 * @param transaccion
	 *            the transaccion
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<ComprobantesDTO> obtenerComprobantesPorEmpresas(TransaccionDTO transaccion, Cliente cliente);

}
