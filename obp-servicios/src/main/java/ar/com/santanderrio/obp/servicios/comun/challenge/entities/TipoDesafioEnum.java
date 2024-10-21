/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.entities;

/**
 * The Enum TipoDesafioEnum.
 */
public enum TipoDesafioEnum {

	/** The CVV2. */
	CVV2("CVV2", "CVV2", 550),
	/** The banelco 8 digitos. */
	BANELCO("BANELCO", "Banelco 8 dígitos", 550),
	/** The coordenadas. */
	COORDENADAS("COORDENADAS", "Tarjeta de coordenandas", 800),
	/** The token. */
	TOKEN("TOKEN", "Soft Token", 850),
	/** The sin desafio. */
	SIN_DESAFIO("SIN_DESAFIO", "Sin desafio", 000);

	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;

	/** The level. */
	private int level;

	/**
	 * Instantiates a new tipo desafio enum.
	 *
	 * @param id
	 *            the id
	 * @param descripcion
	 *            the descripcion
	 * @param level
	 *            the level
	 */
	TipoDesafioEnum(String id, String descripcion, int level) {
		this.id = id;
		this.descripcion = descripcion;
		this.level = level;
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
	 * @return the tipo desafio enum
	 */
	public static TipoDesafioEnum buscarPorId(String id) {
		TipoDesafioEnum[] values = TipoDesafioEnum.values();
		for (TipoDesafioEnum desafioTipo : values) {
			if (desafioTipo.getId().equals(id)) {
				return desafioTipo;
			}
		}
		return null;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

}
