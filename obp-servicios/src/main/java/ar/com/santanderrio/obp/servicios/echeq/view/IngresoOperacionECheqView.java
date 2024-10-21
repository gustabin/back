package ar.com.santanderrio.obp.servicios.echeq.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.echeq.entities.RetrieveMarketInfrastructureInOutEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

/**
 * The Class IngresoOperacionECheqView.
 */
public class IngresoOperacionECheqView {
	
	/** The operacion. */
	OperacionEcheqEnum operacion;

	/** The ingreso desde recibidos. */
	private Boolean ingresoDesdeRecibidos = Boolean.FALSE;

	/** The ingreso desde emitidos. */
	private Boolean ingresoDesdeEmitidos = Boolean.FALSE;

	/** The ingreso desde endosados. */
	private Boolean ingresoDesdeEndosados = Boolean.FALSE;
	
	/** The ingreso desde cedidos. */
	private Boolean ingresoDesdeCedidos = Boolean.FALSE;

	/** The cuentas operacion. */
	private List<CuentaView> cuentasOperacion;

	/** The rango fecha pago alta. */
	private String rangoFechaPagoAlta;

	/** The modalidades. */
	private List<Opcion> modalidades;
	
	/** The endosos. */
	private List<Opcion> tiposEndoso;

	/** The id. */
	private String id;

	/** The mensaje popup. */
	private String mensajePopup;

	/** The j session id. */
	private String jSessionId;

	/** The legal. */
	private String legal;

	// Atributos de detalle Etapa 2 (a definir)
	/** The fecha ultima modificacion. */
	private String fechaUltimaModificacion;
	
	private String importe;
	
	/** Modalidad del cheque*/
	private String modalidadEcheq;
	
	/** The mensaje de ayuda. */
	private String mensajeAyuda;
	
	//private RetrieveMarketInfrastructureInOutEntity retrieveMarketInf;

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
	 * obtener ingreso desde cedidos
	 * @return
	 */
	public Boolean getIngresoDesdeCedidos() {
		return ingresoDesdeCedidos;
	}

	/**
	 * setear ingreso desde cedidos
	 * @param ingresoDesdeCedidos
	 */
	public void setIngresoDesdeCedidos(Boolean ingresoDesdeCedidos) {
		this.ingresoDesdeCedidos = ingresoDesdeCedidos;
	}

	/**
	 * Gets the cuentas operacion.
	 *
	 * @return the cuentas operacion
	 */
	public List<CuentaView> getCuentasOperacion() {
		return cuentasOperacion;
	}

	/**
	 * Sets the cuentas operacion.
	 *
	 * @param cuentasOperacion the new cuentas operacion
	 */
	public void setCuentasOperacion(List<CuentaView> cuentasOperacion) {
		this.cuentasOperacion = cuentasOperacion;
	}

	/**
	 * Gets the rango fecha pago alta.
	 *
	 * @return the rango fecha pago alta
	 */
	public String getRangoFechaPagoAlta() {
		return rangoFechaPagoAlta;
	}

	/**
	 * Sets the rango fecha pago alta.
	 *
	 * @param rangoFechaPagoAlta the new rango fecha pago alta
	 */
	public void setRangoFechaPagoAlta(String rangoFechaPagoAlta) {
		this.rangoFechaPagoAlta = rangoFechaPagoAlta;
	}

	/**
	 * Gets the modalidades.
	 *
	 * @return the modalidades
	 */
	public List<Opcion> getModalidades() {
		return modalidades;
	}

	/**
	 * Sets the modalidades.
	 *
	 * @param modalidades the new modalidades
	 */
	public void setModalidades(List<Opcion> modalidades) {
		this.modalidades = modalidades;
	}

	public List<Opcion> getTiposEndoso() {
		return tiposEndoso;
	}

	public void setTiposEndoso(List<Opcion> tiposEndoso) {
		this.tiposEndoso = tiposEndoso;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the mensaje popup.
	 *
	 * @return the mensaje popup
	 */
	public String getMensajePopup() {
		return mensajePopup;
	}

	/**
	 * Sets the mensaje popup.
	 *
	 * @param mensajePopup the new mensaje popup
	 */
	public void setMensajePopup(String mensajePopup) {
		this.mensajePopup = mensajePopup;
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
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the fecha ultima modificacion.
	 *
	 * @return the fecha ultima modificacion
	 */
	public String getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	/**
	 * Sets the fecha ultima modificacion.
	 *
	 * @param fechaUltimaModificacion the new fecha ultima modificacion
	 */
	public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	/**
	 * Gets the tipo grilla.
	 *
	 * @return the tipo grilla
	 */
	public String getTipoGrilla() {
		if (ingresoDesdeEmitidos) {
			return "EMITIDOS";
		} else if (ingresoDesdeEndosados) {
			return "ENDOSADOS";
		} else if (ingresoDesdeRecibidos) {
			return "RECIBIDOS";
		} else if(ingresoDesdeCedidos) {
			return "CEDIDOS";
		}
		return "";
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	public String getModalidadEcheq() {
		return modalidadEcheq;
	}

	public void setModalidadEcheq(String modalidadEcheq) {
		this.modalidadEcheq = modalidadEcheq;
	}

	public String getMensajeAyuda() {
		return mensajeAyuda;
	}

	public void setMensajeAyuda(String mensajeAyuda) {
		this.mensajeAyuda = mensajeAyuda;
	}

	/*public RetrieveMarketInfrastructureInOutEntity getRetrieveMarketInf() {
		return retrieveMarketInf;
	}

	public void setRetrieveMarketInf(RetrieveMarketInfrastructureInOutEntity retrieveMarketInf) {
		this.retrieveMarketInf = retrieveMarketInf;
	}*/

}
