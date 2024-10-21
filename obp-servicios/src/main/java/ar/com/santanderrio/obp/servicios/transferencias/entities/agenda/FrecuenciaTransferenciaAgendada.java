/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities.agenda;

/**
 * The Enum FrecuenciaTransferenciaAgendada.
 */
public enum FrecuenciaTransferenciaAgendada {

	/** The frecuencia e. */
	FRECUENCIA_E("S", "Frecuencia de transferencia automática"),
	/** The frecuencia i. */
	FRECUENCIA_I("S", "Frecuencia recordatorio de transferencia"),
	/** The mismo dia todas semanas. */
	MISMO_DIA_TODAS_SEMANAS("TS", "Mismo día, todas las semanas"),
	/** The mismo dia 2 semanas. */
	MISMO_DIA_2_SEMANAS("2S", "Mismo día, cada dos semanas"),
	/** The todos meses mismo dia. */
	TODOS_MESES_MISMO_DIA("TM", "Mismo día, todos los meses"),
	/** The cada 2 meses. */
	CADA_2_MESES("2M", "Cada dos meses"),
	/** The cada 3 meses. */
	CADA_3_MESES("3M", "Cada tres meses"),
	/** The cada 6 meses. */
	CADA_6_MESES("6M", "Cada seis meses"),
	/** The cada anio. */
	CADA_ANIO("1A", "Mismo día, una vez al año");

	/** The descripcion. */
	private String descripcion;

	/** The codigo. */
	private String codigo;

	/**
	 * Instantiates a new frecuencia transferencia agendada.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	FrecuenciaTransferenciaAgendada(String codigo, String descripcion) {
		this.descripcion = descripcion;
		this.codigo = codigo;
	}

	/**
	 * From descripcion.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the frecuencia transferencia agendada
	 */
	public static FrecuenciaTransferenciaAgendada fromDescripcion(String descripcion) {
		FrecuenciaTransferenciaAgendada[] values = FrecuenciaTransferenciaAgendada.values();

		FrecuenciaTransferenciaAgendada response = null;

		for (FrecuenciaTransferenciaAgendada frecuencia : values) {
			if (frecuencia.getDescripcion().equalsIgnoreCase(descripcion)) {
				response = frecuencia;
				break;
			}
		}
		return response;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

}
