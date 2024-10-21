/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.entities;

/**
 * The Enum DesafioTipo.
 */
public enum DesafioTipo {

	/** The tarjeta de coordenadas. */
	TARJETA_DE_COORDENADAS("COORDENADAS", "Tarjeta de coordenandas"),
	/** The banelco 8 digitos. */
	BANELCO_8_DIGITOS("BANELCO", "Banelco 8 dígitos"),
	/** The otp. */
	OTP("OTP", "OTP"),
	/** The soft token. */
	SOFT_TOKEN("SOFTTOKEN", "Soft Token");

	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;

	/**
	 * Instantiates a new desafio tipo.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 */
	DesafioTipo(String id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Buscar por id.
	 *
	 * @param id
	 *            the id
	 * @return the desafio tipo
	 */
	public static DesafioTipo buscarPorId(String id) {
		DesafioTipo[] values = DesafioTipo.values();
		for (DesafioTipo desafioTipo : values) {
			if (desafioTipo.getId().equals(id)) {
				return desafioTipo;
			}
		}
		return null;
	}
}
