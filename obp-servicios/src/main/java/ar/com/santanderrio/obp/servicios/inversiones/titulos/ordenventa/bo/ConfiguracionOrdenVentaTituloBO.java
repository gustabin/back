/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.dto.ConfiguracionOrdenVentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;

/**
 * The Interface ConfiguracionOrdenVentaTituloBO.
 */
public interface ConfiguracionOrdenVentaTituloBO {

	/**
	 * Obtener configuracion orden venta.
	 *
	 * @param cliente
	 *            the cliente
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionOrdenVentaDTO> obtenerConfiguracionOrdenVenta(Cliente cliente, ConfiguracionOrdenVentaInView datosEntrada);
	
}
