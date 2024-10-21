package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class OperacionesECheqEmisorInEntity.
 */
public class OperacionesECheqEmisorInEntity extends OperacionesECheqInEntity {

	/** The cuit emisor. */
	private String cuitEmisor;

	/** The razon social emisor. */
	private String razonSocialEmisor;

	/**
	 * Instantiates a new operaciones E cheq emisor in entity.
	 */
	public OperacionesECheqEmisorInEntity() {
		super();
	}

	/**
	 * Instantiates a new operaciones E cheq emisor in entity.
	 *
	 * @param idCheque          the id cheque
	 * @param tipoDocumento     the tipo documento
	 * @param nroDocumento      the nro documento
	 * @param tipoCuenta        the tipo cuenta
	 * @param sucursalCuenta    the sucursal cuenta
	 * @param numeroCuenta      the numero cuenta
	 * @param nroCheque         the nro cheque
	 * @param fechaEmision      the fecha emision
	 * @param fechaPago         the fecha pago
	 * @param jSessionId        the j session id
	 * @param cuitEmisor        the cuit emisor
	 * @param razonSocialEmisor the razon social emisor
	 */
	public OperacionesECheqEmisorInEntity(String idCheque, String tipoDocumento, String nroDocumento, String tipoCuenta,
			String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision, String fechaPago,
			String jSessionId, String cuitEmisor, String razonSocialEmisor) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId);
		this.cuitEmisor = cuitEmisor;
		this.razonSocialEmisor = razonSocialEmisor;
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