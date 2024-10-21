package ar.com.santanderrio.obp.servicios.queue.entities;

/**
 * The Enum TurnStatesEnum.
 */
public enum TurnStatesEnum {

	/** The turn status issued. */
	TURN_STATUS_ISSUED("issued"),

	/** The turn status ready. */
	TURN_STATUS_READY("ready"),

	/** The turn status finished. */
	TURN_STATUS_FINISHED("finished");

	/** The codigo. */
	private String codigo;

	/**
	 * Instantiates a new queue turn states enum.
	 *
	 * @param codigo the codigo
	 */
	private TurnStatesEnum(String codigo) {
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
