/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaActivaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.CuotasPendientesView;

/**
 * The Interface CuotasPendienteManager.
 */
public interface CuotasPendienteManager {

	/**
	 * Obtener cuotas pendientes. De la tarjeta seleccionada en Selector de
	 * Tarjetasse busca todas las compras con cuotas pendientes por pagar,
	 * separadas por tarjeta.
	 *
	 * @param tarjetaActiva
	 *            the tarjeta activa
	 * @return the respuesta
	 */
	Respuesta<CuotasPendientesView> obtenerCuotasPendientes(TarjetaActivaView tarjetaActiva);

	/**
	 * Descargar excel cuotas pendientes.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarExcelCuotasPendientes();
}
