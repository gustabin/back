package ar.com.santanderrio.obp.servicios.debinrecurrente.bo;

/**
 * The Enum AccionRecurrenciaEnum.
 */
public enum AccionRecurrenciaEnum {

	/** The pausar. */
	PAUSAR("PAUSAR", "Pausar"),
	
	/** The reanudar. */
	REACTIVAR("REACTIVAR", "Reactivar"),
	
	/** The anular. */
	ANULAR("ANULAR", "Anular");
	
	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new estado transferencia Enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	AccionRecurrenciaEnum(String codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	
}
