/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * The Enum CuitEnum.
 *
 * @author sabrina.cis
 */
public enum CuitEnum {

	/** The cuit. */
	CUIT("CUIT", "CUIT"),

	/** The obtener detalle. */
	CUIT_DESTINATARIO("CUIT_DESTINATARIO", "CUIT/CUIL"),

	/** The cuit empleado. */
	CUIT_EMPLEADO("CUIT_EMPLEADO", "CUIT/CUIL del empleado"),

	/** The cuit empleador. */
	CUIT_EMPLEADOR("CUIT_DESTINATARIO", "CUIT/CUIL del empleador"),

	/** The cuit contribuyente. */
	CUIT_CONTRIBUYENTE("CUIT_CONTRIBUYENTE", "CUIT/CUIL del contribuyente"),

	/** The cuit destinatario transferencias. */
	CUIT_DESTINATARIO_TRANSFERENCIAS("CUIT_DESTINATARIO_TRANSFERENCIAS", "CUIT/CUIL del destinatario"),

	/** The cuit del generador del VEP. */
	CUIT_GENERADOR_VEP("CUIT_GENERADOR_VEP", "CUIT/CUIL del generador de VEP");

	/** The id. */
	private String id;

	/** The detalle. */
	private String detalle;

	/**
	 * Instantiates a new comprobantes BO enum.
	 *
	 * @param id
	 *            the id
	 * @param detalle
	 *            the detalle
	 */
	CuitEnum(String id, String detalle) {
		this.id = id;
		this.detalle = detalle;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

}
