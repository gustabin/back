package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class ConsultaImporteTotalesInEntity.
 */
public class ConsultaImporteTotalesInEntity {

	/** The cuit. */
	private String cuit;
	
	/** The rango fecha emision. */
	private String rangoFechaEmision;
	
	/** The beneficiario doc tipo. */
	private String beneficiarioDocTipo;
	
	/** The beneficiario doc nro. */
	private String beneficiarioDocNro;
	
	/** The j session id. */
	private String jSessionId;

	/**
	 * Instantiates a new consulta importe totales in entity.
	 */
	public ConsultaImporteTotalesInEntity() {
		super();
	}
	
	/**
	 * Instantiates a new consulta importe totales in entity.
	 *
	 * @param cuit the cuit
	 * @param rangoFechaEmision the rango fecha emision
	 * @param beneficiarioDocTipo the beneficiario doc tipo
	 * @param beneficiarioDocNro the beneficiario doc nro
	 * @param jSessionId the j session id
	 */
	public ConsultaImporteTotalesInEntity(String cuit, String rangoFechaEmision,
			String beneficiarioDocTipo, String beneficiarioDocNro, String jSessionId) {
		super();
		this.cuit = cuit;
		this.rangoFechaEmision = rangoFechaEmision;
		this.beneficiarioDocTipo = beneficiarioDocTipo;
		this.beneficiarioDocNro = beneficiarioDocNro;
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
	 * Gets the beneficiario doc tipo.
	 *
	 * @return the beneficiario doc tipo
	 */
	public String getBeneficiarioDocTipo() {
		return beneficiarioDocTipo;
	}

	/**
	 * Sets the beneficiario doc tipo.
	 *
	 * @param beneficiarioDocTipo the new beneficiario doc tipo
	 */
	public void setBeneficiarioDocTipo(String beneficiarioDocTipo) {
		this.beneficiarioDocTipo = beneficiarioDocTipo;
	}

	/**
	 * Gets the beneficiario doc nro.
	 *
	 * @return the beneficiario doc nro
	 */
	public String getBeneficiarioDocNro() {
		return beneficiarioDocNro;
	}

	/**
	 * Sets the beneficiario doc nro.
	 *
	 * @param beneficiarioDocNro the new beneficiario doc nro
	 */
	public void setBeneficiarioDocNro(String beneficiarioDocNro) {
		this.beneficiarioDocNro = beneficiarioDocNro;
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
