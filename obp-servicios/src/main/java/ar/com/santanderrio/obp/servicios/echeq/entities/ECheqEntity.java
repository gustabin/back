package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class ECheqEntity.
 */
public class ECheqEntity {

	/** The cuit. */
	private String cuit;

	/** The fecha emision desde. */
	private String fechaEmisionDesde;

	/** The fecha emision hasta. */
	private String fechaEmisionHasta;

	/** The fecha pago desde. */
	private String fechaPagoDesde;

	/** The fecha pagohasta. */
	private String fechaPagoHasta;

	/** The estado. */
	private String estado;

	/** The orderby. */
	private String orderby;

	/** The nro pagina. */
	private String nroPagina;

	/** The emisor cuit. */
	private String emisorCuit;

	/** The cheque numero. */
	private String chequeNumero;
	
	/** The cantidad registro pagina. */
	private String cantidadRegistroPagina;
	
	/** The beneficiario doc tipo. */
	private String beneficiarioDocTipo;
	
	/** The beneficiario doc nro. */
	private String beneficiarioDocNro;

	/** The j session id. */
	private String jSessionId;

	/**
	 * Instantiates a new e cheq entity.
	 */
	public ECheqEntity() {
		super();
	}

	/**
	 * Instantiates a new e cheq entity.
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
	public ECheqEntity(String cuit, String fechaEmisionDesde, String fechaEmisionHasta, String fechaPagoDesde, String fechaPagoHasta,
			String estado, String orderby, String nroPagina, String emisorCuit, String chequeNumero) {
		super();
		this.cuit = cuit;
		this.fechaEmisionDesde = fechaEmisionDesde;
		this.fechaEmisionHasta = fechaEmisionHasta;
		this.fechaPagoDesde = fechaPagoDesde;
		this.fechaPagoHasta = fechaPagoHasta;
		this.estado = estado;
		this.orderby = orderby;
		this.nroPagina = nroPagina;
		this.emisorCuit = emisorCuit;
		this.chequeNumero = chequeNumero;
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
	 * Gets the fecha emision desde.
	 *
	 * @return the fecha emision desde
	 */
	public String getFechaEmisionDesde() {
		return fechaEmisionDesde;
	}

	/**
	 * Sets the fecha emision desde.
	 *
	 * @param fechaEmisionDesde the new fecha emision desde
	 */
	public void setFechaEmisionDesde(String fechaEmisionDesde) {
		this.fechaEmisionDesde = fechaEmisionDesde;
	}

	/**
	 * Gets the fecha emision hasta.
	 *
	 * @return the fecha emision hasta
	 */
	public String getFechaEmisionHasta() {
		return fechaEmisionHasta;
	}

	/**
	 * Sets the fecha emision hasta.
	 *
	 * @param fechaEmisionHasta the new fecha emision hasta
	 */
	public void setFechaEmisionHasta(String fechaEmisionHasta) {
		this.fechaEmisionHasta = fechaEmisionHasta;
	}

	/**
	 * Gets the fecha pago desde.
	 *
	 * @return the fecha pago desde
	 */
	public String getFechaPagoDesde() {
		return fechaPagoDesde;
	}

	/**
	 * Sets the fecha pago desde.
	 *
	 * @param fechaPagoDesde the new fecha pago desde
	 */
	public void setFechaPagoDesde(String fechaPagoDesde) {
		this.fechaPagoDesde = fechaPagoDesde;
	}

	public String getFechaPagoHasta() {
		return fechaPagoHasta;
	}

	public void setFechaPagoHasta(String fechaPagoHasta) {
		this.fechaPagoHasta = fechaPagoHasta;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the orderby.
	 *
	 * @return the orderby
	 */
	public String getOrderby() {
		return orderby;
	}

	/**
	 * Sets the orderby.
	 *
	 * @param orderby the new orderby
	 */
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}

	/**
	 * Gets the nro pagina.
	 *
	 * @return the nro pagina
	 */
	public String getNroPagina() {
		return nroPagina;
	}

	/**
	 * Sets the nro pagina.
	 *
	 * @param nroPagina the new nro pagina
	 */
	public void setNroPagina(String nroPagina) {
		this.nroPagina = nroPagina;
	}

	/**
	 * Gets the emisor cuit.
	 *
	 * @return the emisor cuit
	 */
	public String getEmisorCuit() {
		return emisorCuit;
	}

	/**
	 * Sets the emisor cuit.
	 *
	 * @param emisorCuit the new emisor cuit
	 */
	public void setEmisorCuit(String emisorCuit) {
		this.emisorCuit = emisorCuit;
	}

	/**
	 * Gets the cheque numero.
	 *
	 * @return the cheque numero
	 */
	public String getChequeNumero() {
		return chequeNumero;
	}

	/**
	 * Sets the cheque numero.
	 *
	 * @param chequeNumero the new cheque numero
	 */
	public void setChequeNumero(String chequeNumero) {
		this.chequeNumero = chequeNumero;
	}

	public String getCantidadRegistroPagina() {
		return cantidadRegistroPagina;
	}

	public void setCantidadRegistroPagina(String cantidadRegistroPagina) {
		this.cantidadRegistroPagina = cantidadRegistroPagina;
	}

	public String getBeneficiarioDocTipo() {
		return beneficiarioDocTipo;
	}

	public void setBeneficiarioDocTipo(String beneficiarioDocTipo) {
		this.beneficiarioDocTipo = beneficiarioDocTipo;
	}

	public String getBeneficiarioDocNro() {
		return beneficiarioDocNro;
	}

	public void setBeneficiarioDocNro(String beneficiarioDocNro) {
		this.beneficiarioDocNro = beneficiarioDocNro;
	}

	public String getjSessionId() {
		return jSessionId;
	}

	public void setjSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

}
