package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class BeneficiarioOutEntity.
 */
public class BeneficiarioOutEntity {

	/** The documento. */
	private String documento;

	/** The tipo documento. */
	private String tipoDocumento;

	/** The ben documento. */
	private String benDocumento;

	/** The ben tipo documento. */
	private String benTipoDocumento;

	/** The ben razon social. */
	private String benRazonSocial;

	/**
	 * Instantiates a new beneficiario out entity.
	 */
	public BeneficiarioOutEntity() {
		super();
	}

	/**
	 * Instantiates a new beneficiario DTO.
	 *
	 * @param documento the documento
	 * @param tipoDocumento the tipo documento
	 * @param benDocumento the ben documento
	 * @param benTipoDocumento the ben tipo documento
	 * @param benRazonSocial the ben razon social
	 */
	public BeneficiarioOutEntity(String documento, String tipoDocumento, String benDocumento, String benTipoDocumento,
			String benRazonSocial) {
		super();
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.benDocumento = benDocumento;
		this.benTipoDocumento = benTipoDocumento;
		this.benRazonSocial = benRazonSocial;
	}

	/**
	 * Gets the documento.
	 *
	 * @return the documento
	 */
	public String getDocumento() {
		return documento;
	}

	/**
	 * Sets the documento.
	 *
	 * @param documento the new documento
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Sets the tipo documento.
	 *
	 * @param tipoDocumento the new tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the ben documento.
	 *
	 * @return the ben documento
	 */
	public String getBenDocumento() {
		return benDocumento;
	}

	/**
	 * Sets the ben documento.
	 *
	 * @param benDocumento the new ben documento
	 */
	public void setBenDocumento(String benDocumento) {
		this.benDocumento = benDocumento;
	}

	/**
	 * Gets the ben tipo documento.
	 *
	 * @return the ben tipo documento
	 */
	public String getBenTipoDocumento() {
		return benTipoDocumento;
	}

	/**
	 * Sets the ben tipo documento.
	 *
	 * @param benTipoDocumento the new ben tipo documento
	 */
	public void setBenTipoDocumento(String benTipoDocumento) {
		this.benTipoDocumento = benTipoDocumento;
	}

	/**
	 * Gets the ben razon social.
	 *
	 * @return the ben razon social
	 */
	public String getBenRazonSocial() {
		return benRazonSocial;
	}

	/**
	 * Sets the ben razon social.
	 *
	 * @param benRazonSocial the new ben razon social
	 */
	public void setBenRazonSocial(String benRazonSocial) {
		this.benRazonSocial = benRazonSocial;
	}

}
