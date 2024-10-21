package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class ECheqEmitidoEntity.
 */
public class ECheqEmitidoInEntity extends ECheqEntity {

	/** The emisor cbu. */
	private String emisorCbu;

	/** The importe desde. */
	private String importeDesde;

	/** The importe hasta. */
	private String importeHasta;


	/**
	 * Instantiates a new e cheq emitido in entity.
	 */
	public ECheqEmitidoInEntity() {
		super();
	}

	/**
	 * Instantiates a new e cheq emitido in entity.
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
	public ECheqEmitidoInEntity(String cuit, String fechaEmisionDesde, String fechaEmisionHasta, String fechaPagoDesde,
			String fechaPagoHasta, String estado, String orderby, String nroPagina, String emisorCuit,
			String chequeNumero) {
		super(cuit, fechaEmisionDesde, fechaEmisionHasta, fechaPagoDesde, fechaPagoHasta, estado, orderby, nroPagina,
				emisorCuit, chequeNumero);
	}

	/**
	 * Gets the emisor cbu.
	 *
	 * @return the emisorCbu
	 */
	public String getEmisorCbu() {
		return emisorCbu;
	}

	/**
	 * Sets the emisor cbu.
	 *
	 * @param emisorCbu the emisorCbu to set
	 */
	public void setEmisorCbu(String emisorCbu) {
		this.emisorCbu = emisorCbu;
	}

	/**
	 * Gets the importe desde.
	 *
	 * @return the importeDesde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * Sets the importe desde.
	 *
	 * @param importeDesde the importeDesde to set
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * Gets the importe hasta.
	 *
	 * @return the importeHasta
	 */
	public String getImporteHasta() {
		return importeHasta;
	}

	/**
	 * Sets the importe hasta.
	 *
	 * @param importeHasta the importeHasta to set
	 */
	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}

	

}
