/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.monedero.entities.DatosAltaTagMonederoEntity;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteActivacionTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ComprobanteAltaTagMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosParaActivacionView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.MonederoMovimientosView;

/**
 * The Interface AltaTagMonederoDAO.
 */
public interface AltaTagMonederoDAO {

	/**
	 * Ejecutar alta tag monedero.
	 *
	 * @param datosAltaTagMonedero
	 *            the datos alta tag monedero
	 * @param cliente
	 *            the cliente
	 * @return the comprobante alta tag monedero view
	 * @throws BusinessException
	 *             the business exception
	 */
	ComprobanteAltaTagMonederoView ejecutarAltaTagMonedero(DatosAltaTagMonederoEntity datosAltaTagMonedero,
			Cliente cliente) throws BusinessException;

	/**
	 * Generar comprobante alta tag monedero.
	 *
	 * @param comprobanteAltaTagMonederoView
	 *            the comprobante alta tag monedero view
	 * @return the reporte
	 */
	Reporte generarComprobanteAltaTagMonedero(ComprobanteAltaTagMonederoView comprobanteAltaTagMonederoView);

	/**
	 * Generar titular activo movimientos.
	 *
	 * @param monederoMovimientoView
	 *            the monedero movimiento view
	 * @return the reporte
	 */
	Reporte generarTitularActivoMovimientos(MonederoMovimientosView monederoMovimientoView);

	/**
	 * Activar monedero tag.
	 *
	 * @param datosParaActivacionView
	 *            the datos para activacion view
	 * @param cliente
	 *            the cliente
	 * @return the comprobante activacion view
	 */
	ComprobanteActivacionTagMonederoView activarMonederoTag(DatosParaActivacionView datosParaActivacionView,
			Cliente cliente);

	/**
	 * Generar comprobante activacion tag monedero.
	 *
	 * @param comprobanteActivacionTagMonederoView
	 *            the comprobante activacion tag monedero view
	 * @return the reporte
	 */
	Reporte generarComprobanteActivacionTagMonedero(
			ComprobanteActivacionTagMonederoView comprobanteActivacionTagMonederoView);

}
