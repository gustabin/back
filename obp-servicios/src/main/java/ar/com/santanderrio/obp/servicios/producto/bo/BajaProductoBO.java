/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.producto.dto.BajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.dto.ObtenerBajaProductoDTO;
import ar.com.santanderrio.obp.servicios.producto.view.ComprobanteBajaProductoView;

/**
 * The Interface BajaProductoBO.
 */
public interface BajaProductoBO {

	/**
	 * Solicitar baja producto.
	 *
	 * @param bajaProductoDTO
	 *            the baja producto DTO
	 * @return the respuesta
	 */
	Respuesta<BajaProductoDTO> solicitarBajaProducto(BajaProductoDTO bajaProductoDTO);

	/**
	 * Obtener productos baja tarjeta.
	 *
	 * @return the respuesta
	 */
	Respuesta<ObtenerBajaProductoDTO> obtenerProductosBajaTarjeta();

	/**
	 * Obtener productos paquetes cuenta.
	 *
	 * @return the respuesta
	 */
	Respuesta<ObtenerBajaProductoDTO> obtenerProductosPaquetesCuenta();

	/**
	 * Generar comprobante.
	 *
	 * @param comprobanteBajaProducto
	 *            the comprobante baja producto
	 * @return the respuesta
	 */
	Respuesta<Reporte> generarComprobante(ComprobanteBajaProductoView comprobanteBajaProducto);

	Respuesta<BajaProductoDTO> solicitarArrepentimientoProducto(BajaProductoDTO bajaProductoDTO);
	
}
