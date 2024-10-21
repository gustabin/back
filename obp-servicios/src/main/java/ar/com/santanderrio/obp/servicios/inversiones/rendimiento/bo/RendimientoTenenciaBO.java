/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.RendimientoDTO;

/**
 * The Interface RendimientoTenenciaBO.
 */
public interface RendimientoTenenciaBO {

	/**
	 * Obtener rendimiento tenencia RTL.
	 *
	 * @param cliente
	 *            the cliente
	 * @param codigoProducto
	 *            the codigo producto
	 * @return the respuesta
	 */
	public Respuesta<RendimientoDTO> obtenerRendimientoTenenciaRTL(Cliente cliente, String codigoProducto);
	
	/**
	 * Obtener rendimiento tenencia B priv.
	 *
	 * @param cliente
	 *            the cliente
	 * @param codigoProducto
	 *            the codigo producto
	 * @return the respuesta
	 */
	public Respuesta<RendimientoBPrivDTO> obtenerRendimientoTenenciaBPriv(Cliente cliente, String codigoProducto);
}
