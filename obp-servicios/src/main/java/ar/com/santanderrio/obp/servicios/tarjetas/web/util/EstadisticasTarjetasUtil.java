/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.util;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

/**
 * The Class EstadisticasTarjetasUtil.
 */
public final class EstadisticasTarjetasUtil {

	/** The Constant MARCA_VISA. */
	public static final String MARCA_VISA = "VISA";

	/** The Constant MARCA_AMEX. */
	public static final String MARCA_AMEX = "AMEX";

	/** The Constant MARCA_MASTER. */
	public static final String MARCA_MASTER = "MASTER";

	/** The Constant MARCA_VISA_RECARGABLE. */
	public static final String MARCA_VISA_RECARGABLE = "VISA Recargable";

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
	 * Es tipo cuenta visa.
	 *
	 * @param marca
	 *            the marca
	 * @return the boolean
	 */
	private static Boolean esTipoCuentaVisa(String marca) {
		return MARCA_VISA.equals(marca);
	}

	/**
	 * Es tipo cuenta visa.
	 *
	 * @param marca
	 *            the marca
	 * @return the boolean
	 */
	private static Boolean esTipoCuentaVisaRecargable(String marca) {
		return MARCA_VISA_RECARGABLE.equals(marca);
	}

	/**
	 * Es tipo cuenta master.
	 *
	 * @param marca
	 *            the marca
	 * @return the boolean
	 */
	private static Boolean esTipoCuentaMaster(String marca) {
		return MARCA_MASTER.equals(marca);
	}

	/**
	 * Es tipo cuenta amex.
	 *
	 * @param marca
	 *            the marca
	 * @return the boolean
	 */
	private static Boolean esTipoCuentaAmex(String marca) {
		return MARCA_AMEX.equals(marca);
	}

	/**
	 * Codigo de Estadistica para Ultimos Consumos, segun la marca de la
	 * tarjeta.
	 *
	 * @param marca
	 *            the marca
	 * @return the codigo estadistica ultimos consumos
	 */
	public static String getCodigoEstadisticaUltimosConsumos(String marca) {
		if (marca != null && esTipoCuentaVisa(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ULTIMOS_CONSUMOS_TARJETA_VISA;
		}
		if (StringUtils.isNotBlank(marca) && esTipoCuentaVisaRecargable(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ULTIMOS_CONSUMOS_TARJETA_VISA_RECARGABLE;
		}
		if (marca != null && esTipoCuentaAmex(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ULTIMOS_CONSUMOS_TARJETA_AMEX;
		}

		if (marca != null && esTipoCuentaAmex(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ULTIMOS_CONSUMOS_TARJETA_AMEX;
		}

		if (marca != null && esTipoCuentaMaster(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ULTIMOS_CONSUMOS_TARJETA_MASTER;
		}

		return null;
	}

	/**
	 * Codigo de Estadistica para Consulta Financiacion, segun la marca de la
	 * tarjeta.
	 *
	 * @param marca
	 *            the marca
	 * @return the codigo estadistica consulta financiacion
	 */
	public static String getCodigoEstadisticaConsultaFinanciacion(String marca) {
		if (marca != null && esTipoCuentaVisa(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PLAN_V_VISA;
		}
		if (marca != null && esTipoCuentaAmex(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_PLAN_V_AMEX;
		}
		return null;
	}

	/**
	 * Gets the codigo estadistica consumos pendientes.
	 *
	 * @return the codigo estadistica consumos pendientes
	 */
	public static String getCodigoEstadisticaConsumosPendientes() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_NOTIFICACION_CONSUMOS_PENDIENTES_TARJETAS;
	}

	/**
	 * Gets the codigo estadistica consumos pendientes.
	 *
	 * @param marca
	 *            the marca
	 * @return the codigo estadistica consumos pendientes
	 */
	public static String getCodigoEstadisticaConsumosPendientes(String marca) {
		if (marca != null && esTipoCuentaVisa(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONSULTA_CONSUMOS_PENDIENTES_TARJETAS_VISA;
		}
		if (StringUtils.isNotBlank(marca) && esTipoCuentaVisaRecargable(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONSULTA_CONSUMOS_PENDIENTES_TARJETAS_VISA_RECARGABLES;
		}

		if (marca != null && esTipoCuentaAmex(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONSULTA_CONSUMOS_PENDIENTES_TARJETAS_AMEX;
		}

		if (marca != null && esTipoCuentaMaster(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CONSULTA_CONSUMOS_PENDIENTES_TARJETAS_MASTER;
		}
		return null;
	}

	/**
	 * Gets the codigo estadistica exportar ultimos consumos.
	 *
	 * @return the codigo estadistica exportar ultimos consumos
	 */
	public static String getCodigoEstadisticaExportarUltimosConsumos() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_EXPORTACION_ULTIMOS_CONSUMOS;
	}

	/**
	 * Gets the codigo estadistica tarjeta favorita.
	 *
	 * @return the codigo estadistica tarjeta favorita
	 */
	public static String getCodigoEstadisticaTarjetaFavorita() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETA_FAVORITA;
	}

	/**
	 * Gets the codigo estadistica alias.
	 *
	 * @return the codigo estadistica alias
	 */
	public static String getCodigoEstadisticaAlias() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ALIAS_TARJETAS;
	}

	/**
	 * Gets the codigo estadistica tarjetas.
	 *
	 * @return the codigo estadistica tarjetas
	 */
	public static String getCodigoEstadisticaTarjetas() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_TARJETAS;
	}

	/**
	 * Graba la estadistica de limites y disponibles segun la marca de la
	 * tarjeta de credito.
	 *
	 * @author florencia.n.martinez
	 * @param marca
	 *            the marca
	 * @return String
	 */
	public static String getCodigoEstadisticaLimitesYDisponibles(String marca) {
		if (marca != null && esTipoCuentaVisa(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LIMITES_DISPONIBLES_TARJETA_VISA;
		}
		if (marca != null && esTipoCuentaAmex(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_LIMITES_DISPONIBLES_TARJETA_AMEX;
		}
		return null;
	}

	/**
	 * Graba la estadistica de cuotas pendientes segun la marca de la tarjeta de
	 * credito.
	 *
	 * @param marca
	 *            the marca
	 * @return String
	 */
	public static String getCodigoEstadisticaCuotasPendientes(String marca) {
		if (marca != null && esTipoCuentaVisa(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CUOTAS_PENDIENTES_TARJETA_VISA;
		}
		if (marca != null && esTipoCuentaAmex(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CUOTAS_PENDIENTES_TARJETA_AMEX;
		}

		if (marca != null && esTipoCuentaMaster(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_CUOTAS_PENDIENTES_TARJETA_MASTER;
		}
		return null;
	}

	/**
	 * Graba la estadistica de resumenes anteriores segun la marca de la tarjeta
	 * de credito.
	 *
	 * @param marca
	 *            the marca
	 * @return String
	 */
	public static String getCodigoEstadisticaResumenesAnteriores(String marca) {
		if (marca != null && esTipoCuentaVisa(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_RESUMEN_ANTERIOR_TARJETA_VISA;
		}
		if (marca != null && esTipoCuentaAmex(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_RESUMEN_ANTERIOR_TARJETA_AMEX;
		}

		if (marca != null && esTipoCuentaMaster(marca)) {
			return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_RESUMEN_ANTERIOR_TARJETA_MASTER;
		}

		return null;
	}

	/**
	 * Obtiene codigo de estadistica para ultimo resumen de tarjetas.
	 *
	 * @return the codigo estadistica ultimo resumen
	 */
	public static String getCodigoEstadisticaUltimoResumen() {
		return EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_ULTIMO_RESUMEN;
	}

}
