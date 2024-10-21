/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

/**
 * Tipo de Operacion para el servicio ACTAGEDEST.
 */
public enum TipoOperacionACTAGEDESTEnum {

	/** The alta. */
	// verificar porque al momento no se encontraba en la documentacion
	ALTA("A", "Alta"),
	/** Baja. */
	BAJA("B", "Baja"),
	/** Modificacion. */
	MODIFICACION("M", "Modificacion");

	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcion;

	/**
	 * Instantiates a new campo enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 */
	TipoOperacionACTAGEDESTEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
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
