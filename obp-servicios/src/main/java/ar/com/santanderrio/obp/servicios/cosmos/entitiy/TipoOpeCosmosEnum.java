/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cosmos.entitiy;


/**
 * The Enum TipoOpeCosmosEnum.
 */
public enum TipoOpeCosmosEnum {

	/** The devolucion pago. */
	DEVOLUCION_PAGO("9", "pedido_devolucion_pago"),
	
	PROBLEMAS_EXTRACCION("36", "problemas_extraccion"),
	
	PROBLEMAS_DEPOSITO_CUENTA("22", "problemas_deposito_cuenta"),
	
	PROBLEMAS_DEPOSITO_TARJETA("44", "problemas_deposito_tarjeta");

	/** The id. */
	private String id;

	/** The descripcion. */
	private String descripcion;


	/**
	 * Instantiates a new tipo ope cosmos enum.
	 *
	 * @param id the id
	 * @param descripcion the descripcion
	 */
	TipoOpeCosmosEnum(String id, String descripcion) {
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
	 * @param id the id
	 * @return the tipo ope cosmos enum
	 */
	public static TipoOpeCosmosEnum buscarPorId(String id) {
		TipoOpeCosmosEnum[] values = TipoOpeCosmosEnum.values();
		for (TipoOpeCosmosEnum desafioTipo : values) {
			if (desafioTipo.getId().equals(id)) {
				return desafioTipo;
			}
		}
		return null;
	}

}
