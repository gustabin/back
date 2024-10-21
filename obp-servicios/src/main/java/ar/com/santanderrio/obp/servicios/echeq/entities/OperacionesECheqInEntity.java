package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

/**
 * The Class OperacionesEcheqentity.
 */
public abstract class OperacionesECheqInEntity {

	/** The id cheque. */
	private String idCheque;

	/** The tipo documento. */
	private String tipoDocumento;

	/** The nro documento. */
	private String nroDocumento;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The nro cheque. */
	private String nroCheque;

	/** The fecha emision. */
	private String fechaEmision;

	/** The fecha pago. */
	private String fechaPago;

	/** The J session id. */
	private String JSessionId;

	private OperacionEcheqEnum accionEcheq;
	
	private String nombreServicio;
	
	private String versionServicio;
	
	/**
	 * Instantiates a new operaciones echeq in entity.
	 */
	public OperacionesECheqInEntity() {
		super();
	}

	/**
     * @return the nombreServicio
     */
    public String getNombreServicio() {
        return nombreServicio;
    }

    /**
     * @param nombreServicio the nombreServicio to set
     */
    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    /**
     * @return the versionServicio
     */
    public String getVersionServicio() {
        return versionServicio;
    }

    /**
     * @param versionServicio the versionServicio to set
     */
    public void setVersionServicio(String versionServicio) {
        this.versionServicio = versionServicio;
    }

    /**
	 * Instantiates a new operaciones echeq in entity.
	 *
	 * @param idCheque       the id cheque
	 * @param tipoDocumento  the tipo documento
	 * @param nroDocumento   the nro documento
	 * @param tipoCuenta     the tipo cuenta
	 * @param sucursalCuenta the sucursal cuenta
	 * @param numeroCuenta   the numero cuenta
	 * @param nroCheque      the nro cheque
	 * @param fechaEmision   the fecha emision
	 * @param fechaPago      the fecha pago
	 * @param jSessionId     the j session id
	 */
	public OperacionesECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento, String tipoCuenta,
			String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision, String fechaPago,
			String jSessionId) {
		super();
		this.idCheque = idCheque;
		this.tipoDocumento = tipoDocumento;
		this.nroDocumento = nroDocumento;
		this.tipoCuenta = tipoCuenta;
		this.sucursalCuenta = sucursalCuenta;
		this.numeroCuenta = numeroCuenta;
		this.nroCheque = nroCheque;
		this.fechaEmision = fechaEmision;
		this.fechaPago = fechaPago;
		JSessionId = jSessionId;
	}

	/**
	 * Gets the id cheque.
	 *
	 * @return the id cheque
	 */
	public String getIdCheque() {
		return idCheque;
	}

	/**
	 * Sets the id cheque.
	 *
	 * @param idCheque the new id cheque
	 */
	public void setIdCheque(String idCheque) {
		this.idCheque = idCheque;
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
	 * Gets the nro documento.
	 *
	 * @return the nro documento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento the new nro documento
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the nro cheque.
	 *
	 * @return the nro cheque
	 */
	public String getNroCheque() {
		return nroCheque;
	}

	/**
	 * Sets the nro cheque.
	 *
	 * @param nroCheque the new nro cheque
	 */
	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}

	/**
	 * Gets the fecha emision.
	 *
	 * @return the fecha emision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * Sets the fecha emision.
	 *
	 * @param fechaEmision the new fecha emision
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * Gets the fecha pago.
	 *
	 * @return the fecha pago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago the new fecha pago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Gets the j session id.
	 *
	 * @return the j session id
	 */
	public String getJSessionId() {
		return JSessionId;
	}

	/**
	 * Sets the j session id.
	 *
	 * @param jSessionId the new j session id
	 */
	public void setJSessionId(String jSessionId) {
		JSessionId = jSessionId;
	}

    /**
     * @return the accionEcheq
     */
    public OperacionEcheqEnum getAccionEcheq() {
        return accionEcheq;
    }

    /**
     * @param accionEcheq the accionEcheq to set
     */
    public void setAccionEcheq(OperacionEcheqEnum accionEcheq) {
        this.accionEcheq = accionEcheq;
    }

}