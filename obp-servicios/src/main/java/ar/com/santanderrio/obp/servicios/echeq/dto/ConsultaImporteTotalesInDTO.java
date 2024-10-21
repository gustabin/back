package ar.com.santanderrio.obp.servicios.echeq.dto;

/**
 * The Class ConsultaImporteTotalesInDTO.
 */
public class ConsultaImporteTotalesInDTO {

	/** The cuit. */
	private String cuit;

	/** The rango fecha emision. */
	private String rangoFechaEmision;

	/** The j session id. */
	private String jSessionId;


	/**
	 * Instantiates a new consulta importe totales in DTO.
	 */
	public ConsultaImporteTotalesInDTO() {
		super();
	}

	/**
	 * Instantiates a new consulta importe totales in DTO.
	 *
	 * @param cuit the cuit
	 * @param rangoFechaEmision the rango fecha emision
	 * @param jSessionId the j session id
	 */
	public ConsultaImporteTotalesInDTO(String cuit, String rangoFechaEmision, String jSessionId) {
		super();
		this.cuit = cuit;
		this.rangoFechaEmision = rangoFechaEmision;
		this.jSessionId = jSessionId;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the rango fecha emision.
	 *
	 * @return the rango fecha emision
	 */
	public String getRangoFechaEmision() {
		return rangoFechaEmision;
	}

	/**
	 * Sets the rango fecha emision.
	 *
	 * @param rangoFechaEmision the new rango fecha emision
	 */
	public void setRangoFechaEmision(String rangoFechaEmision) {
		this.rangoFechaEmision = rangoFechaEmision;
	}

	/**
	 * Gets the j session id.
	 *
	 * @return the j session id
	 */
	public String getjSessionId() {
		return jSessionId;
	}

	/**
	 * Sets the j session id.
	 *
	 * @param jSessionId the new j session id
	 */
	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}	

}
