package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.Random;

/**
 * Este enum SOLO se usa para obtener el nombre de la compania de celular desde clave online
 * @author Itr
 *
 */
public enum TipoDeCompaniasCelularClaveOnline {
	/** The movistar. */
	MOVISTAR("MOVI", "Movistar / Tuenti"),

	/** The personal. */
	PERSONAL("PERS", "Personal"),

	/** The claro. */
	CLARO("CTI", "Claro"),

	/** The nextel. */
	NEXTEL("NEXT", "Nextel");

	

	/** The codigo. */
	private String codigo;

	/** The descripcion. */
	private String descripcion;

	/**
	 * From codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the tipo compania enum
	 */
	public static TipoDeCompaniasCelularClaveOnline obtenerCompaniaSegunCodigo(String codigo) {
		TipoDeCompaniasCelularClaveOnline[] values = TipoDeCompaniasCelularClaveOnline.values();

		TipoDeCompaniasCelularClaveOnline response = null;

		for (TipoDeCompaniasCelularClaveOnline tipo : values) {
			if (tipo.getCodigo().equals(codigo)) {
				response = tipo;
			}
		}
		return response;
	}

	/**
	 * From descripcion.
	 *
	 * @param descripcion
	 *            the descripcion
	 * @return the tipo compania enum
	 */
	public static TipoDeCompaniasCelularClaveOnline obtenerCompaniaCelularRandom() {
		  Random random = new Random();
          return values()[random.nextInt(values().length)];
	}

	/**
	 * Instantiates a new tipo compania enum.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoDeCompaniasCelularClaveOnline(String codigo, String descripcion) {
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