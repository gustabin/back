package ar.com.santanderrio.obp.servicios.queue.entities;

/**
 * The Enum QueueSTOperations.
 */
public enum QueueSTOperations {

	/** The compraventa. */
	COMPRAVENTA("QUEUE_ST.COMPRAVENTA.HORA.DESDE", "QUEUE_ST.COMPRAVENTA.HORA.HASTA"),

	/** The login. */
	LOGIN("QUEUE_ST.LOGIN.HORA.DESDE", "QUEUE_ST.LOGIN.HORA.HASTA");

	/**
	 * Instantiates a new queue ST operations.
	 *
	 * @param paramDesde the param desde
	 * @param paramHasta the param hasta
	 */
	private QueueSTOperations(String paramDesde, String paramHasta) {
		this.paramDesde = paramDesde;
		this.paramHasta = paramHasta;
	}

	/** The param desde. */
	private String paramDesde;

	/** The param hasta. */
	private String paramHasta;

	/**
	 * Gets the param desde.
	 *
	 * @return the param desde
	 */
	public String getParamDesde() {
		return paramDesde;
	}

	/**
	 * Gets the param hasta.
	 *
	 * @return the param hasta
	 */
	public String getParamHasta() {
		return paramHasta;
	}

}
