/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.bo;

import java.util.List;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.DetalleCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TenenciaConsolidadaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TotalesTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;

/**
 * The Interface InversionesBO.
 */
public interface InversionesBO {

	/**
	 * Consultar perfil inversor.
	 *
	 * @param nup
	 *            the nup
	 * @return the respuesta
	 */
	Respuesta<PerfilInversorResponse> consultarPerfilInversor(boolean esBancaPrivada);

	/**
	 * Inicio fondos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoDeOperacion
	 *            the tipo de operacion
	 * @return the respuesta
	 */
	Respuesta<InicioFondoDTO> inicioInversiones(Cliente cliente, String tipoDeOperacion);

	/**
	 * Obtiene la tenencia para el tipo de operacion solicitado FCI: Fondos PF:
	 * Plazofijo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoDeOperacion
	 *            the tipo de operacion
	 * @return the respuesta
	 */

	/**
	 * Obtiene los totales de la tenencia consolidada
	 * 
	 * @param cliente
	 * @param tipoDeOperacion
	 * @return
	 */
	Respuesta<TotalesTenenciaDTO> obtenerTotalesTenencia(Cliente cliente, String tipoDeOperacion, TipoBancaEnum banca);

	/**
	 * Obtener tenencia consolidada por producto.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TenenciaConsolidadaPorProductoDTO> obtenerTenenciaConsolidadaPorProducto(Cliente cliente);


	/**
	 * Obtener custodia.
	 *
	 * @param cliente
	 *            the cliente
	 * @param detalleIn
	 *            the detalle in
	 * @return the detalle custodia DTO
	 */
	DetalleCustodiaDTO obtenerCustodia(Cliente cliente, DetalleCustodiaInView detalleIn);

	
	/**
	 * Obtener tenencia consolidada por producto BPriv.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	Respuesta<TenenciaConsolidadaBPrivDTO> obtenerTenenciaConsolidadaPorProductoBPriv(Cliente cliente);

	/**
	 * Obtener datos custodia respuesta error.
	 *
	 * @param cliente
	 *            the cliente
	 * @param detalleIn
	 *            the detalle in
	 * @return the detalle custodia DTO
	 */
	DetalleCustodiaDTO obtenerDatosCustodiaRespuestaError(Cliente cliente, DetalleCustodiaInView detalleIn);
	
	Respuesta<Reporte> obtenerTenenciaConsolidadaReporte(List<TenenciaProductosPorMonedaView> listaTenencias, Cliente cliente);

	String obtenerDescripcionLargaSegunPerfil(String idPerfil);

	Respuesta<Reporte> obtenerTenenciaConsolidadaReporteBP(List<TenenciaPorCuentaBPrivDTO> listaTenenciasBP, Cliente cliente);
	
	/**
	 * Obtiene los totales de la tenencia consolidada
	 * 
	 * @param cliente
	 * @param tipoDeOperacion
	 * @return
	 */
	Respuesta<TarjetaTenenciaConsolidadaView> obtenerTotalesTenenciaHome(Cliente cliente, TipoBancaEnum banca);
}
