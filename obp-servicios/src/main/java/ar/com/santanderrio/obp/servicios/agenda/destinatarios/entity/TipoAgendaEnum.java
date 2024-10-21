/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

/**
 * Distintos tipos de agenda.
 *
 */
public enum TipoAgendaEnum {

	/** The cpi fci c. */
	AGENDA_RIO("RIO", "Agenda rio", "RIO"),
	/** The opprog. */
	AGENDA_OTROS_BANCOS("OB ", "Agenda otros Bancos", "OTROS_BANCOS"),

	/** The agenda extracciones. */
	AGENDA_EXTRACCIONES("EXT", "Agenda Extracciones", "ENVIO_EFECTIVO"),

	/** The todas las agendas. */
	AGENDA_ALIAS("ALI", "Agenda Alias", "ALIAS"),

	/** The todas las agendas. */
	TODAS_LAS_AGENDAS("TOD", "Todas las agendas", "");

	/** The campo. */
	String campo;

	/** The descripcion. */
	String descripcion;

	/** The tag. */
	String tag;

	/**
	 * Instantiates a new campo enum.
	 *
	 * @param campo
	 *            the campo
	 * @param descripcion
	 *            the descripcion
	 * @param tag
	 *            the tag
	 */
	TipoAgendaEnum(String campo, String descripcion, String tag) {
		this.campo = campo;
		this.descripcion = descripcion;
		this.tag = tag;
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

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

}
