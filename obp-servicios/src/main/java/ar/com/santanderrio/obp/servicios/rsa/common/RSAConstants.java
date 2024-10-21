/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.common;

/**
 * The Class RSAConstants.
 */
public final class RSAConstants {

	/** The Constant EVT_CLTDEF_ADHPAGOAUTO. */
	public static final String EVT_CLTDEF_ADHPAGOAUTO = "DIRECT_DEBIT_ADHERENCE";

	/** The Constant EVT_CLTDEF_CAMBIOLIMITEEXT. */
	public static final String EVT_CLTDEF_CAMBIOLIMITEEXT = "CHANGE_LIMIT_EXT";

	/** The Constant EVT_CLTDEF_DONACIONES. */
	public static final String EVT_CLTDEF_DONACIONES = "NEW_PAYMENT";

	/** The Constant EVT_CLTDEF_NUEVOPAGO. */
	public static final String EVT_CLTDEF_NUEVOPAGO = "NEW_PAYMENT";

	/** The Constant EVT_CLTDEF_PAGOAGENDADO. */
	public static final String EVT_CLTDEF_PAGOAGENDADO = "SCHEDULED_PAYMENT";

	/** The Constant EVT_CLTDEF_PAGODECOMPRAS. */
	public static final String EVT_CLTDEF_PAGODECOMPRAS = "PURCHASE_PAYMENT";

	/** The Constant EVT_CLTDEF_PAGODESUELDOS. */
	public static final String EVT_CLTDEF_PAGODESUELDOS = "MULTIPLE_SALARIES_PAYMENTS";

	/** The Constant EVT_CLTDEF_RECARGACELSMS. */
	public static final String EVT_CLTDEF_RECARGACELSMS = "CELLPHONE_ENROLLMENT";

	/** The Constant EVT_CLTDEF_RECARGATJRECA. */
	public static final String EVT_CLTDEF_RECARGATJRECA = "RELOAD_CARD";

	/** The Constant EVT_CLTDEF_REIMPRESIONTARJ. */
	public static final String EVT_CLTDEF_REIMPRESIONTARJ = "PLASTIC_REPRINT";

	/** The Constant EVT_CLTDEF_RESCATEDEFONDOS. */
	public static final String EVT_CLTDEF_RESCATEDEFONDOS = "GET_FUNDS";

	/** The Constant EVT_CLTDEF_VENTADEBONOS. */
	public static final String EVT_CLTDEF_VENTADEBONOS = "SALE_OF_BONDS";

	/** The Constant NEW_REQUEST_EDITION_ATM. */
	public static final String NEW_REQUEST_EDITION_ATM = "NEW_REQUEST_EDITION_ATM";

	/** The Constant NEW_REQUEST_ATM. */
	public static final String NEW_REQUEST_ATM = "NEW_REQUEST_ATM";

	/** The Constant GESTION_ALIAS. */
	public static final String GESTION_ALIAS = "GESTION_ALIAS";

	/** Extraccion y compras en el exterior. */
	public static final String EVT_CLTDEF_MODCTAPREF = "WITHDRAWAL_LIMIT_CHANGE";
	
	/** Pagar Debin **/
	public static final String PAGO_DEBIN = "PAGO_DEBIN";
	
	/** Solicitud Debin **/
	public static final String GENERACION_DEBIN = "GENERACION_DEBIN";

	/** Adhesion Debin **/
	public static final String ADHESION_DEBIN_RECURRENTE = "ADHESION_DEBIN_RECURRENTE";

	/** Liquidacion inmediata prestamo encolado **/
	public static final String LIQ_INMEDIATA = "LIQ_INMEDIATA";

	public static final String ANULACION = "ANULACION";

	public static final String CAMBIO_CUENTA_EXTERIOR = "CAMBIO_CUENTA_EXTERIOR";
	

	/**
	 * Instantiates a new RSA constants.
	 */
	private RSAConstants() {
	}

}
