/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

/**
 * The Enum TipoPrestamoEnum.
 *
 * @author B039636
 */

public enum TipoPrestamoEnum {

	/** The otrosprestamos. */
	OTROSPRESTAMOS(0, "Otros Préstamos"),
	/** The prendario. */
	PRENDARIO(2, "Préstamo Prendario"),
	/** The personal. */
	PERSONAL(4, "Préstamo Personal"),
	/** The solafirma. */
	SOLAFIRMA(5, "Préstamo Sola Firma"),
	/** The adelantosespeciales. */
	ADELANTOSESPECIALES(6, "Adelantos Especiales"),
	/** The descuentosdescontados. */
	DESCUENTOSDESCONTADOS(7, "Documento descontados"),
	/** The hipotecarios. */
	HIPOTECARIOS(8, "Préstamo Hipotecario"),
	/** The plandepagos. */
	PLANDEPAGOS(9, "Plan de Pagos"),
	/** The UVA. */
	UVA(10, "Préstamo Personal UVA");

	/** The id. */
	private int id;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new tipo prestamo enum.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
	TipoPrestamoEnum(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/**
	 * From id string.
	 *
	 * @param id
	 *            the id
	 * @return the tipo prestamo enum
	 */
	public static TipoPrestamoEnum fromIdString(String id) {
		TipoPrestamoEnum[] values = TipoPrestamoEnum.values();
		int intId = Integer.parseInt(id);
		TipoPrestamoEnum response = null;

		for (TipoPrestamoEnum tipoPrestamo : values) {
			if (tipoPrestamo.id == intId) {
				response = tipoPrestamo;
			}
		}
		return response;
	}

	/**
	 * From string.
	 *
	 * @param id
	 *            the id
	 * @return the tipo prestamo enum
	 */
	public static TipoPrestamoEnum fromString(String id) {
		TipoPrestamoEnum[] values = TipoPrestamoEnum.values();
		TipoPrestamoEnum response = null;

		for (TipoPrestamoEnum tipoPrestamo : values) {
			if (tipoPrestamo.name().equals(id)) {
				response = tipoPrestamo;
			}
		}
		return response;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
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
