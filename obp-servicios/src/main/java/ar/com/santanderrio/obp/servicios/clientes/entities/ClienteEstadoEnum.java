/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

/**
 * The Enum ClienteEstadoEnum.
 */
public enum ClienteEstadoEnum {

	/** The suscripto activo. */
	SUSCRIPTO_ACTIVO("SA", "Suscripto Activo"),

	/** The no suscripto. */
	NO_SUSCRIPTO("NS", "No Suscripto"),

	/** The suscripto no activo. */
	SUSCRIPTO_NO_ACTIVO("SN", "Suscripto No Activo"),

	/** The timeout. */
	TIMEOUT("TO", "Timeout");

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * From codigo string.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the cliente estado enum
	 */
	public static ClienteEstadoEnum fromCodigoString(String codigo) {
		ClienteEstadoEnum[] values = ClienteEstadoEnum.values();

		ClienteEstadoEnum response = null;

		for (ClienteEstadoEnum tipo : values) {
			if (tipo.getCodigo().equals(codigo)) {
				response = tipo;
			}
		}
		return response;
	}

	/**
	 * Instantiates a new cliente estado enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	ClienteEstadoEnum(String codigo, String descripcion) {
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
