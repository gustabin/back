package ar.com.santanderrio.obp.servicios.echeq.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

/**
 * The Class ConfirmarAltaEndosarECheqView.
 */
@SuppressWarnings("serial")
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfirmarAltaEndosarECheqView extends RsaDTOParaDesafio {

	/** The id cheque. */
	private String idCheque;

	/** The operacion. */
	private OperacionEcheqEnum operacion;

	/** The importe. */
	private String importe;

	/** The cuenta debito seleccionada. */
	private String cuentaDebitoSeleccionada;
	
	/** The ingreso desde recibidos. */
	private Boolean ingresoDesdeRecibidos = Boolean.FALSE;

	/** The ingreso desde emitidos. */
	private Boolean ingresoDesdeEmitidos = Boolean.FALSE;

	/** The ingreso desde endosados. */
	private Boolean ingresoDesdeEndosados = Boolean.FALSE;
	
	/** The ingreso desde cedidos. */
	private Boolean ingresoDesdeCedidos = Boolean.FALSE;

	/** The fecha pago. */
	private String fechaPago;

	/** The modalidad. */
	private String modalidad;
	
	/** The tipo endoso. */
	private String tipoEndoso;

	/** The j session id. */
	private String jSessionId;
	
	/** The mensaje feedback. */
	private String mensajeFeedback;
	
	/** The fecha comprobante. */
	private String fechaComprobante;
	
	/** The numero comprobante. */
	private String numeroComprobante;

	/** The legales. */
	private String legales;
	
	/** Domicilio del beneficiario */
	private String domicilio;

    /**
     * Instantiates a new confirmar alta endosar E cheq view.
     */
    public ConfirmarAltaEndosarECheqView() {
        super(null);
    }

	/**
	 * Instantiates a new confirmar alta endosar E cheq view.
	 *
	 * @param tipoOperacion the tipo operacion
	 */
	public ConfirmarAltaEndosarECheqView(OperacionesRSAEnum tipoOperacion) {
		super(tipoOperacion);
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
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public OperacionEcheqEnum getOperacion() {
		return operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion the new operacion
	 */
	public void setOperacion(OperacionEcheqEnum operacion) {
		this.operacion = operacion;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the cuenta debito seleccionada.
	 *
	 * @return the cuenta debito seleccionada
	 */
	public String getCuentaDebitoSeleccionada() {
		return cuentaDebitoSeleccionada;
	}

	/**
	 * Sets the cuenta debito seleccionada.
	 *
	 * @param cuentaDebitoSeleccionada the new cuenta debito seleccionada
	 */
	public void setCuentaDebitoSeleccionada(String cuentaDebitoSeleccionada) {
		this.cuentaDebitoSeleccionada = cuentaDebitoSeleccionada;
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
	
	/**
	 * obtiene ingreso desde cedidos
	 * @return
	 */
	public Boolean getIngresoDesdeCedidos() {
		return ingresoDesdeCedidos;
	}

	/**
	 * setea ingreso desde cedidos
	 * @param ingresoDesdeCedidos
	 */
	public void setIngresoDesdeCedidos(Boolean ingresoDesdeCedidos) {
		this.ingresoDesdeCedidos = ingresoDesdeCedidos;
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
	 * Gets the modalidad.
	 *
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * Sets the modalidad.
	 *
	 * @param modalidad the new modalidad
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * Gets the tipo endoso.
	 *
	 * @return the tipo endoso
	 */
	public String getTipoEndoso() {
		return tipoEndoso;
	}

	/**
	 * Sets the tipo endoso.
	 *
	 * @param tipoEndoso the new tipo endoso
	 */
	public void setTipoEndoso(String tipoEndoso) {
		this.tipoEndoso = tipoEndoso;
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
	 * Gets the mensaje feedback.
	 *
	 * @return the mensaje feedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the fecha comprobante.
	 *
	 * @return the fecha comprobante
	 */
	public String getFechaComprobante() {
		return fechaComprobante;
	}

	/**
	 * Sets the fecha comprobante.
	 *
	 * @param fechaComprobante the new fecha comprobante
	 */
	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

}
