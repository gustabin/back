/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones;

/**
 * The Enum TipoPapel.
 *
 * @author pablo.d.gargaglione
 */

public enum TipoPapel {

	/** The bon. */
	BON(0, "Bonos", "BON"),
	/** The shs. */
	SHS(1, "Acciones", "SHS"),
	/** The fci. */
	FCI(2, "Fondo", "FCI"),
	/** The bys. */
	BYS(3, "Bonos y Acciones", "BYS"),
	/** The vacio. */
	VACIO(4, "Todos", "   "),
	
	/** The fdc. */
	FDC(5, "FondoCiti", "FDC");

	/** The codigo. */
	private Integer codigo;

	/** The descripcion. */
	private String descripcion;

	/** The abreviatura. */
	private String abreviatura;

	/**
	 * Instantiates a new tipo papel.
	 *
	 * @param codigo
	 *            the codigo
	 * @param descripcion
	 *            the descripcion
	 * @param abreviatura
	 *            the abreviatura
	 */
	TipoPapel(Integer codigo, String descripcion, String abreviatura) {
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.abreviatura = abreviatura;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public Integer getCodigo() {
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

	/**
	 * Gets the abreviatura.
	 *
	 * @return the abreviatura
	 */
	public String getAbreviatura() {
		return abreviatura;
	}

}
