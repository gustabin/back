package ar.com.santanderrio.obp.servicios.echeq.dto;

/**
 * The Class GrillaECheqInDTO.
 */
public class GrillaECheqInDTO {
	
	/** The ingreso desde recibidos. */
	private Boolean ingresoDesdeRecibidos = Boolean.FALSE;

	/** The ingreso desde emitidos. */
	private Boolean ingresoDesdeEmitidos = Boolean.FALSE;
	
	/** The ingreso desde endosados. */
	private Boolean ingresoDesdeEndosados = Boolean.FALSE;
	
	/** ingreso desde cedidos*/
	private Boolean ingresoDesdeCedidos = Boolean.FALSE;
	
	/** The es primer L lamado. */
	private Boolean esPrimerLLamado = Boolean.FALSE;
	
	/** The order fecha emision. */
	private Boolean orderFechaEmision = Boolean.FALSE;
		
	/** The fecha emision desde. */
	private String fechaEmisionDesde;

	/** The fecha emision hasta. */
	private String fechaEmisionHasta;

	/** The estado. */
	private String estado;
		
	/** The numero cheque. */
	private String numeroCheque;
	
	/** The cuit buscador. */
	private String cuitBuscador;
	
	/** The j session id. */
	private String jSessionId;
	
	/**
	 * Instantiates a new grilla E cheq in DTO.
	 */
	public GrillaECheqInDTO() {
	}
	
	/**
	 * Gets the ingreso desde recibidos.
	 *
	 * @return the ingreso desde recibidos
	 */
	public Boolean getIngresoDesdeRecibidos() {
		return ingresoDesdeRecibidos;
	}

	/**
	 * Sets the ingreso desde recibidos.
	 *
	 * @param ingresoDesdeRecibidos the new ingreso desde recibidos
	 */
	public void setIngresoDesdeRecibidos(Boolean ingresoDesdeRecibidos) {
		this.ingresoDesdeRecibidos = ingresoDesdeRecibidos;
	}

	/**
	 * Gets the ingreso desde emitidos.
	 *
	 * @return the ingreso desde emitidos
	 */
	public Boolean getIngresoDesdeEmitidos() {
		return ingresoDesdeEmitidos;
	}

	/**
	 * Sets the ingreso desde emitidos.
	 *
	 * @param ingresoDesdeEmitidos the new ingreso desde emitidos
	 */
	public void setIngresoDesdeEmitidos(Boolean ingresoDesdeEmitidos) {
		this.ingresoDesdeEmitidos = ingresoDesdeEmitidos;
	}

	/**
	 * Gets the ingreso desde endosados.
	 *
	 * @return the ingreso desde endosados
	 */
	public Boolean getIngresoDesdeEndosados() {
		return ingresoDesdeEndosados;
	}

	/**
	 * Sets the ingreso desde endosados.
	 *
	 * @param ingresoDesdeEndosados the new ingreso desde endosados
	 */
	public void setIngresoDesdeEndosados(Boolean ingresoDesdeEndosados) {
		this.ingresoDesdeEndosados = ingresoDesdeEndosados;
	}

	public Boolean getIngresoDesdeCedidos() {
		return ingresoDesdeCedidos;
	}

	public void setIngresoDesdeCedidos(Boolean ingresoDesdeCedidos) {
		this.ingresoDesdeCedidos = ingresoDesdeCedidos;
	}

	/**
	 * Gets the es primer L lamado.
	 *
	 * @return the es primer L lamado
	 */
	public Boolean getEsPrimerLLamado() {
		return esPrimerLLamado;
	}

	/**
	 * Sets the es primer L lamado.
	 *
	 * @param esPrimerLLamado the new es primer L lamado
	 */
	public void setEsPrimerLLamado(Boolean esPrimerLLamado) {
		this.esPrimerLLamado = esPrimerLLamado;
	}

	/**
	 * Gets the order fecha emision.
	 *
	 * @return the order fecha emision
	 */
	public Boolean getOrderFechaEmision() {
		return orderFechaEmision;
	}

	/**
	 * Sets the order fecha emision.
	 *
	 * @param orderFechaEmision the new order fecha emision
	 */
	public void setOrderFechaEmision(Boolean orderFechaEmision) {
		this.orderFechaEmision = orderFechaEmision;
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

	/**
	 * Gets the numero cheque.
	 *
	 * @return the numero cheque
	 */
	public String getNumeroCheque() {
		return numeroCheque;
	}

	/**
	 * Sets the numero cheque.
	 *
	 * @param numeroCheque the new numero cheque
	 */
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	/**
	 * Gets the cuit buscador.
	 *
	 * @return the cuit buscador
	 */
	public String getCuitBuscador() {
		return cuitBuscador;
	}

	/**
	 * Sets the cuit buscador.
	 *
	 * @param cuitBuscador the new cuit buscador
	 */
	public void setCuitBuscador(String cuitBuscador) {
		this.cuitBuscador = cuitBuscador;
	}
	
}
