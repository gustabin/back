/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

/**
 * The Enum Segmento.
 */
public enum Segmento {

	/** Banca personal (o retail). */
	BANCA_PERSONAL("RTL"),

	/** Banca privada. */
	BANCA_PRIVADA("BP");

	/** The codigo. */
	private final String codigo;

	/**
	 * Instantiates a new segmento.
	 *
	 * @param codigo
	 *            the codigo
	 */
	Segmento(String codigo) {
		this.codigo = codigo;
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
