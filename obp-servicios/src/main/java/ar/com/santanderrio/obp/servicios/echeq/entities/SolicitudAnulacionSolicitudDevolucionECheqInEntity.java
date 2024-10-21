package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class SolicitudAnulacionSolicitudDevolucionEcheqInEntity.
 */
public class SolicitudAnulacionSolicitudDevolucionECheqInEntity extends OperacionesECheqBeneficiarioInEntity {

	/** The importe cuenta. */
	private String importeCuenta;

	/** The tipo accion. */
	private String tipoAccion;

	/** The cuit emisor. */
	private String cuitEmisor;

	/** The razon social emisor. */
	private String razonSocialEmisor;

	/**
	 * Instantiates a new solicitud anulacion solicitud devolucion echeq in entity.
	 */
	public SolicitudAnulacionSolicitudDevolucionECheqInEntity() {
		super();
	}

	/**
	 * Instantiates a new solicitud anulacion solicitud devolucion echeq in entity.
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
	 * @param importeCuenta             the importe cuenta
	 * @param tipoAccion                the tipo accion
	 * @param cuitEmisor                the cuit emisor
	 * @param razonSocialEmisor         the razon social emisor
	 */
	public SolicitudAnulacionSolicitudDevolucionECheqInEntity(String idCheque, String tipoDocumento,
			String nroDocumento, String tipoCuenta, String sucursalCuenta, String numeroCuenta, String nroCheque,
			String fechaEmision, String fechaPago, String jSessionId, String tipoDocumentoBeneficiario,
			String nroDocumentoBeneficiario, String razonSocialBeneficiario, String importeCuenta, String tipoAccion,
			String cuitEmisor, String razonSocialEmisor) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, tipoDocumentoBeneficiario, nroDocumentoBeneficiario, razonSocialBeneficiario);
		this.importeCuenta = importeCuenta;
		this.tipoAccion = tipoAccion;
		this.cuitEmisor = cuitEmisor;
		this.razonSocialEmisor = razonSocialEmisor;
	}

	/**
	 * Gets the importe cuenta.
	 *
	 * @return the importe cuenta
	 */
	public String getImporteCuenta() {
		return importeCuenta;
	}

	/**
	 * Sets the importe cuenta.
	 *
	 * @param importeCuenta the new importe cuenta
	 */
	public void setImporteCuenta(String importeCuenta) {
		this.importeCuenta = importeCuenta;
	}

	/**
	 * Gets the tipo accion.
	 *
	 * @return the tipo accion
	 */
	public String getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * Sets the tipo accion.
	 *
	 * @param tipoAccion the new tipo accion
	 */
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	/**
	 * Gets the cuit emisor.
	 *
	 * @return the cuit emisor
	 */
	public String getCuitEmisor() {
		return cuitEmisor;
	}

	/**
	 * Sets the cuit emisor.
	 *
	 * @param cuitEmisor the new cuit emisor
	 */
	public void setCuitEmisor(String cuitEmisor) {
		this.cuitEmisor = cuitEmisor;
	}

	/**
	 * Gets the razon social emisor.
	 *
	 * @return the razon social emisor
	 */
	public String getRazonSocialEmisor() {
		return razonSocialEmisor;
	}

	/**
	 * Sets the razon social emisor.
	 *
	 * @param razonSocialEmisor the new razon social emisor
	 */
	public void setRazonSocialEmisor(String razonSocialEmisor) {
		this.razonSocialEmisor = razonSocialEmisor;
	}

}
