package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class OperacionesECheqBeneficiarioInEntity.
 */
public class OperacionesECheqBeneficiarioInEntity extends OperacionesECheqInEntity {

	/** The tipo documento beneficiario. */
	private String tipoDocumentoBeneficiario;

	/** The nro documento beneficiario. */
	private String nroDocumentoBeneficiario;

	/** The razon social beneficiario. */
	private String razonSocialBeneficiario;

	/**
	 * Instantiates a new operaciones E cheq beneficiario in entity.
	 */
	public OperacionesECheqBeneficiarioInEntity() {
		super();
	}

	/**
	 * Instantiates a new operaciones E cheq beneficiario in entity.
	 *
	 * @param idCheque                  the id cheque
	 * @param tipoDocumento             the tipo documento
	 * @param nroDocumento              the nro documento
	 * @param tipoCuenta                the tipo cuenta
	 * @param sucursalCuenta            the sucursal cuenta
	 * @param numeroCuenta              the numero cuenta
	 * @param nroCheque                 the nro cheque
	 * @param fechaEmision              the fecha emision
	 * @param fechaPago                 the fecha pago
	 * @param jSessionId                the j session id
	 * @param tipoDocumentoBeneficiario the tipo documento beneficiario
	 * @param nroDocumentoBeneficiario  the nro documento beneficiario
	 * @param razonSocialBeneficiario   the razon social beneficiario
	 */
	public OperacionesECheqBeneficiarioInEntity(String idCheque, String tipoDocumento, String nroDocumento,
			String tipoCuenta, String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision,
			String fechaPago, String jSessionId, String tipoDocumentoBeneficiario, String nroDocumentoBeneficiario,
			String razonSocialBeneficiario) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId);
		this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
		this.nroDocumentoBeneficiario = nroDocumentoBeneficiario;
		this.razonSocialBeneficiario = razonSocialBeneficiario;
	}

	/**
	 * Gets the tipo documento beneficiario.
	 *
	 * @return the tipo documento beneficiario
	 */
	public String getTipoDocumentoBeneficiario() {
		return tipoDocumentoBeneficiario;
	}

	/**
	 * Sets the tipo documento beneficiario.
	 *
	 * @param tipoDocumentoBeneficiario the new tipo documento beneficiario
	 */
	public void setTipoDocumentoBeneficiario(String tipoDocumentoBeneficiario) {
		this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
	}

	/**
	 * Gets the nro documento beneficiario.
	 *
	 * @return the nro documento beneficiario
	 */
	public String getNroDocumentoBeneficiario() {
		return nroDocumentoBeneficiario;
	}

	/**
	 * Sets the nro documento beneficiario.
	 *
	 * @param nroDocumentoBeneficiario the new nro documento beneficiario
	 */
	public void setNroDocumentoBeneficiario(String nroDocumentoBeneficiario) {
		this.nroDocumentoBeneficiario = nroDocumentoBeneficiario;
	}

	/**
	 * Gets the razon social beneficiario.
	 *
	 * @return the razon social beneficiario
	 */
	public String getRazonSocialBeneficiario() {
		return razonSocialBeneficiario;
	}

	/**
	 * Sets the razon social beneficiario.
	 *
	 * @param razonSocialBeneficiario the new razon social beneficiario
	 */
	public void setRazonSocialBeneficiario(String razonSocialBeneficiario) {
		this.razonSocialBeneficiario = razonSocialBeneficiario;
	}

}
