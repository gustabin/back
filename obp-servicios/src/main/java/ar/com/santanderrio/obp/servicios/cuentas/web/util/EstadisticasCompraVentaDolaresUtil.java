/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

/**
 * The Class EstadisticasCompraVentaDolaresUtil.
 *
 * @author sabrina.cis
 */
public class EstadisticasCompraVentaDolaresUtil {

	/**
	 * Gets the codigo estadistica ok.
	 *
	 * @return the codigo estadistica ok
	 */
	public static String getCodigoEstadisticaOk() {
		return EstadisticasConstants.CODIGO_ESTADISTICAS_OK;
	}

	/**
	 * Gets the codigo estadistica error.
	 *
	 * @return the codigo estadistica error
	 */
	public static String getCodigoEstadisticaError() {
		return EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR;
	}

	/**
	 * Codigo de Estadistica para Compra de Dolares CC pesos: 10475.
	 *
	 * @author sabrina.cis
	 * @return the codigo estadistica compra dolares CC
	 */
	public static String getCodigoEstadisticaCompraDolaresCC() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPRA_DOLARES_CC;
	}

	/**
	 * Codigo de Estadistica para Compra de Dolares CA pesos:10476.
	 *
	 * @author sabrina.cis
	 * @return the codigo estadistica compra dolares CA
	 */
	public static String getCodigoEstadisticaCompraDolaresCA() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPRA_DOLARES_CA;
	}

	/**
	 * Codigo de Estadistica para Compra de Dolares CU pesos: 10477.
	 *
	 * @author sabrina.cis
	 * @return the codigo estadistica compra dolares CU
	 */
	public static String getCodigoEstadisticaCompraDolaresCU() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_COMPRA_DOLARES_CU;
	}

	/**
	 * Codigo de Estadistica para Venta de Dolares CU pesos : 10480.
	 *
	 * @author sabrina.cis
	 * @return the codigo estadistica venta dolares CU
	 */
	public static String getCodigoEstadisticaVentaDolaresCU() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VENTA_DOLARES_CU;
	}

}
