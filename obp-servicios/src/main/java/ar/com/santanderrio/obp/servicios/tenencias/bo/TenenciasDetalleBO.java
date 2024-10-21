/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDetalleDTO;
import ar.com.santanderrio.obp.servicios.tenencias.dto.TenenciasDetalleInDTO;
import ar.com.santanderrio.obp.servicios.tenencias.entity.DatosRespuestaHabilitaCompraVentaUSDEntity;

/**
 * Gestiona la logica de negocio relacionada a detalle de Tenencias.
 * 
 * @author desa
 *
 */
public interface TenenciasDetalleBO {

	/**
	 * realiza consultar detalle de Tenencias.
	 *
	 * @param reqTenencias
	 *            the req tenencias
	 * @param tipoDetalle
	 *            the tipo detalle
	 * @return respuesta con el objeto dto response.
	 */
	Respuesta<TenenciasDetalleDTO> consultarDetalleTenencias(TenenciasDetalleInDTO reqTenencias, int tipoDetalle);

	/**
	 * Consultar detalle tenencias completo.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @return the respuesta
	 */
	Respuesta<TenenciasDetalleDTO> consultarDetalleTenenciasCompleto(TenenciasDetalleInDTO dtoRequest);
	
	/**
	 * Consulta si el nup esta habilitado a comprar o vender USD
	 * @param nup
	 * @return
	 */
	Respuesta<DatosRespuestaHabilitaCompraVentaUSDEntity> cnsHabilitaCompraVentaUSD(String nup);

}