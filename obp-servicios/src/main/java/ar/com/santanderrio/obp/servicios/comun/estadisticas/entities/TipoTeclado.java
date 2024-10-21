/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadisticas.entities;

/**
 * Los diferentes tipos de teclado para ingreso de datos utilizado en el canal.
 * 
 * @author B025331
 *
 */
public enum TipoTeclado {

	/** Teclado Virtual *. */
	TECLADO_VIRTUAL("V"),

	/** Teclado Fisico *. */
	TECLADO_FISICO("F");

	/** The tipo teclado. */
	private String tipoTeclado;

	/**
	 * Instantiates a new tipo teclado.
	 *
	 * @param tipoTeclado
	 *            the tipo teclado
	 */
	TipoTeclado(String tipoTeclado) {
		this.tipoTeclado = tipoTeclado;
	}

	/**
	 * Gets the tipo teclado.
	 *
	 * @return the tipo teclado
	 */
	public String getTipoTeclado() {
		return this.tipoTeclado;
	}

	/**
	 * Gets the tipo teclado from descripcion.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the tipo teclado from descripcion
	 */
	public static TipoTeclado getTipoTecladoFromDescripcion(String descripcion) {
		TipoTeclado[] values = TipoTeclado.values();

		TipoTeclado response = null;

		for (TipoTeclado tipoTecladoEnum : values) {
			if (tipoTecladoEnum.getTipoTeclado().equals(descripcion)) {
				response = tipoTecladoEnum;
			}
		}
		return response;
	}

}
