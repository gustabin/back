/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

/**
 * The Enum TagItemMensajeTarjetaEnum.
 *
 * @author florencia.n.martinez
 */
public enum TagItemMensajeTarjetaEnum {

	/** The limitesydisponibles. */
	LIMITESYDISPONIBLES("limitesYDisponibles"),

	/** The consulta financiacion. */
	CONSULTA_FINANCIACION("consultaFinanciacion"),

	/** The sin cuotas pendiente. */
	SIN_CUOTAS_PENDIENTE("sinCuotasPendiente"),

	/** The error carga cuotas pendientes. */
	ERROR_CARGA_CUOTAS_PENDIENTES("errorCargaCuotasPendiente"),

	/** The consumo tarjeta default. */
	CONSUMO_TARJETA_DEFAULT("consumoTarjetaDefault"),

	/** The ultimoresumen. */
	ULTIMORESUMEN("ultimoResumen"),

	/** The Constant TAG_TARJETA. */
	TAG_TARJETA("tarjetas["),

	/** The Constant TAG_TARJETA_CIERRE. */
	TAG_TARJETA_CIERRE("]"),

	/** The tag ultimos consumos. */
	TAG_SIN_CONSUMOS("sinConsumos"),

	/** The consumos pendientes. */
	CONSUMOS_PENDIENTES("consumosPendientes"),

	/** The ultimos consumos. */
	ULTIMOS_CONSUMOS("ultimosConsumos"),

	/** The error generico ultimos consumos. */
	ERROR_GENERICO_ULTIMOS_CONSUMOS("errorGenericoUltimosConsumos");

	/** The descripcionTag. */
	private String descripcionTag;

	/**
	 * Instantiates a new descripcionTag item mensaje tarjeta enum.
	 *
	 * @param descripcionTag
	 *            the descripcionTag
	 */
	private TagItemMensajeTarjetaEnum(String descripcionTag) {
		this.descripcionTag = descripcionTag;
	}

	/**
	 * Gets the descripcionTag.
	 *
	 * @return the descripcionTag
	 */
	public String getDescripcionTag() {
		return descripcionTag;
	}

}
