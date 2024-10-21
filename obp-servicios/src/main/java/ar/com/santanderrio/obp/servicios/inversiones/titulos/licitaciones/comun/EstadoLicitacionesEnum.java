/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.comun;

/**
 * The Enum EstadoLicitacionesEnum.
 */
public enum EstadoLicitacionesEnum {

	/** Confirmada. */
	CONFIRMADA("Confirmada", "Confirmada"),

	/** Confirmada Mercado. */
	CONFIRMADA_MERCADO("Confirmada mercado", "Confirmada"),

	/** Pedido De Reversa. */
	PEDIDO_DE_REVERSA("Pedido de reversa", "Pedido de reversa"),

	/** Enviada. */
	/* Custodia */
	ENVIADA("Enviada", "Enviada"),

	/** No Adjudicada. */
	/* Custodia */
	NO_ADJUDICADA("No Adjudicada", "No Adjudicada"),

	/** Rechazada. */
	/* Custodia */
	PENDIENTE_INGRESO_MERCADO("Pendiente de Ingreso a Mercado", "Rechazada"),

	/** Rechazada. */
	/* Custodia */
	REVOCADA("Revocada", "Reversada"),

	/** Rechazada. */
	/* Custodia */
	ADJUDICADA("Adjudicada", "Adjudicada"),

	/** No aplica. */
	SIMULADA("Simulada", "Simulada"),

	/** The anulada. */
	ANULADA("Anulada", "Anulada"),

	/** The liquidada. */
	LIQUIDADA("Liquidada", "Liquidada");

	/**
	 * Estos 3 estados no deben mostrarse.
	 */

	/** The codigo. */
	private final String estado;

	/** The codigo. */
	private final String estadoResponse;

	/**
	 * Instantiates a new tipo banca enum.
	 *
	 * @param estado
	 *            the estado
	 * @param estadoResponse
	 *            the estado response
	 */
	EstadoLicitacionesEnum(String estado, String estadoResponse) {
		this.estado = estado;
		this.estadoResponse = estadoResponse;
	}

	/**
	 * PF para lazo fijo, FCI para fondos comunes de inversion, TV para tenencia
	 * consolidada.
	 *
	 * @return the codigo
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Gets the estado response.
	 *
	 * @return the estado response
	 */
	public String getEstadoResponse() {
		return estadoResponse;
	}

	/**
	 * From codigo producto dolares.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo fondo enum
	 */
	public static String obtenerCodigoResponse(String codigo) {
		EstadoLicitacionesEnum[] values = EstadoLicitacionesEnum.values();

		String response = codigo;

		for (EstadoLicitacionesEnum codigoOperacion : values) {
			if (codigoOperacion.getEstado().equalsIgnoreCase(codigo.trim())) {
				response = codigoOperacion.getEstadoResponse();
			}
		}
		return response;
	}
}
