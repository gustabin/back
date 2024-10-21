package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class ECheqEndosadoEntity.
 */
public class ECheqEndosadoInEntity extends ECheqEntity {

	/** The cuit beneficiario original. */
	private String cuitBeneficiarioOriginal;
	
	
	/**
	 * Instantiates a new e cheq endosado in entity.
	 */
	public ECheqEndosadoInEntity() {
		super();
	}

	/**
	 * Instantiates a new e cheq endosado in entity.
	 *
	 * @param cuit the cuit
	 * @param fechaEmisionDesde the fecha emision desde
	 * @param fechaEmisionHasta the fecha emision hasta
	 * @param fechaPagoDesde the fecha pago desde
	 * @param fechaPagoHasta the fecha pago hasta
	 * @param estado the estado
	 * @param orderby the orderby
	 * @param nroPagina the nro pagina
	 * @param emisorCuit the emisor cuit
	 * @param chequeNumero the cheque numero
	 */
	public ECheqEndosadoInEntity(String cuit, String fechaEmisionDesde, String fechaEmisionHasta, String fechaPagoDesde,
			String fechaPagoHasta, String estado, String orderby, String nroPagina, String emisorCuit,
			String chequeNumero) {
		super(cuit, fechaEmisionDesde, fechaEmisionHasta, fechaPagoDesde, fechaPagoHasta, estado, orderby, nroPagina,
				emisorCuit, chequeNumero);
	}

	/**
	 * Instantiates a new e cheq endosado in entity.
	 *
	 * @param cuitBeneficiarioOriginal the cuit beneficiario original
	 */
	public ECheqEndosadoInEntity(String cuitBeneficiarioOriginal) {
		super();
		this.cuitBeneficiarioOriginal = cuitBeneficiarioOriginal;
	}

	/**
	 * Gets the cuit beneficiario original.
	 *
	 * @return the cuitBeneficiarioOriginal
	 */
	public String getCuitBeneficiarioOriginal() {
		return cuitBeneficiarioOriginal;
	}

	/**
	 * Sets the cuit beneficiario original.
	 *
	 * @param cuitBeneficiarioOriginal the cuitBeneficiarioOriginal to set
	 */
	public void setCuitBeneficiarioOriginal(String cuitBeneficiarioOriginal) {
		this.cuitBeneficiarioOriginal = cuitBeneficiarioOriginal;
	}
	
}
