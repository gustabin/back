package ar.com.santanderrio.obp.servicios.echeq.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;

/**
 * The Class ComprobanteECheqDTO.
 */
public class ComprobanteECheqDTO {

	/** The operacion. */
	private OperacionEcheqEnum operacion;

	/** The nombre beneficiario. */
	private String nombreBeneficiario;

	/** The cuit beneficiario. */
	private String cuitBeneficiario;
	
	/** The importe. */
	private String importe;
	
	/** The cuenta. */
	private Cuenta cuenta;

	/** The fecha pago. */
	private String fechaPago;

	/** The modalidad. */
	private String modalidad;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha comprobante. */
	private String fechaComprobante;

	/** The fecha emision. */
	private String fechaEmision;
	
	/** The estado. */
	private String estado;

	/** The emisor cuit. */
	private String emisorCuit;

	/** The emisor razon social. */
	private String emisorRazonSocial;

	/** The numero cheque. */
	private String numeroCheque;

	/** The legales. */
	private String legales;

	/** The tipo endoso. */
	private String tipoEndoso;
	
	// Detalle
	
	/** The grilla origen. */
	private String grillaOrigen;

	/** The motivo. */
	private String motivo;

	/** The endosos. */
	private List<EndosoDTO> endosos;
	
	/** The cedidos. */
	private List<CesionDTO> cedidos;

	/** The cuenta numero. */
	private String cuentaNumero;

	/** The cuenta tipo. */
	private String cuentaTipo;

	/** The cuenta alias. */
	private String cuentaAlias;
	
	/** The caracter. */
	private String caracter;
	
	/** The Cheque tipo. */
	private String ChequeTipo;
	
	/** The domicilio emisor. */
	private String domicilioEmisor;
	
	/** The entidad girada. */
	private String entidadGirada;
	
	/** The domicilio pago. */
	private String domicilioPago;
	
	/** The cmc 7. */
	private String cmc7;

	/** The id. */
	private String id;
	
	/** The domicilio beneficiario. */
	private String domicilioBeneficiario;


	/**
	 * Sets the operacion.
	 *
	 * @param operacion the new operacion
	 */
	public void setOperacion(OperacionEcheqEnum operacion) {
		this.operacion = operacion;
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public Object getOperacion() {
		return this.operacion;
	}

	/**
	 * Gets the nombre beneficiario.
	 *
	 * @return the nombre beneficiario
	 */
	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	/**
	 * Sets the nombre beneficiario.
	 *
	 * @param nombreBeneficiario the new nombre beneficiario
	 */
	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	/**
	 * Gets the cuit beneficiario.
	 *
	 * @return the cuit beneficiario
	 */
	public String getCuitBeneficiario() {
		return cuitBeneficiario;
	}

	/**
	 * Sets the cuit beneficiario.
	 *
	 * @param cuitBeneficiario the new cuit beneficiario
	 */
	public void setCuitBeneficiario(String cuitBeneficiario) {
		this.cuitBeneficiario = cuitBeneficiario;
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
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public Cuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta the new cuenta
	 */
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	 * Gets the emisor razon social.
	 *
	 * @return the emisor razon social
	 */
	public String getEmisorRazonSocial() {
		return emisorRazonSocial;
	}

	/**
	 * Sets the emisor razon social.
	 *
	 * @param emisorRazonSocial the new emisor razon social
	 */
	public void setEmisorRazonSocial(String emisorRazonSocial) {
		this.emisorRazonSocial = emisorRazonSocial;
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
	 * Gets the grilla origen.
	 *
	 * @return the grilla origen
	 */
	public String getGrillaOrigen() {
		return grillaOrigen;
	}

	/**
	 * Sets the grilla origen.
	 *
	 * @param tipoDetalle the new grilla origen
	 */
	public void setGrillaOrigen(String tipoDetalle) {
		this.grillaOrigen = tipoDetalle;
	}

	/**
	 * Gets the motivo.
	 *
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo the new motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Gets the endosos.
	 *
	 * @return the endosos
	 */
	public List<EndosoDTO> getEndosos() {
		return endosos;
	}

	/**
	 * Sets the endosos.
	 *
	 * @param endosos the new endosos
	 */
	public void setEndosos(List<EndosoDTO> endosos) {
		this.endosos = endosos;
	}

	public List<CesionDTO> getCedidos() {
		return cedidos;
	}

	public void setCedidos(List<CesionDTO> cedidos) {
		this.cedidos = cedidos;
	}

	/**
	 * Gets the cuenta numero.
	 *
	 * @return the cuenta numero
	 */
	public String getCuentaNumero() {
		return cuentaNumero;
	}

	/**
	 * Sets the cuenta numero.
	 *
	 * @param cuentaNumero the new cuenta numero
	 */
	public void setCuentaNumero(String cuentaNumero) {
		this.cuentaNumero = cuentaNumero;
	}

	/**
	 * Gets the cuenta tipo.
	 *
	 * @return the cuenta tipo
	 */
	public String getCuentaTipo() {
		return cuentaTipo;
	}

	/**
	 * Sets the cuenta tipo.
	 *
	 * @param cuentaTipo the new cuenta tipo
	 */
	public void setCuentaTipo(String cuentaTipo) {
		this.cuentaTipo = cuentaTipo;
	}

	/**
	 * Gets the cuenta alias.
	 *
	 * @return the cuenta alias
	 */
	public String getCuentaAlias() {
		return cuentaAlias;
	}

	/**
	 * Sets the cuenta alias.
	 *
	 * @param cuentaAlias the new cuenta alias
	 */
	public void setCuentaAlias(String cuentaAlias) {
		this.cuentaAlias = cuentaAlias;
	}

	/**
	 * Gets the caracter.
	 *
	 * @return the caracter
	 */
	public String getCaracter() {
		return caracter;
	}

	/**
	 * Sets the caracter.
	 *
	 * @param caracter the new caracter
	 */
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	/**
	 * Gets the cheque tipo.
	 *
	 * @return the cheque tipo
	 */
	public String getChequeTipo() {
		return ChequeTipo;
	}

	/**
	 * Sets the cheque tipo.
	 *
	 * @param chequeTipo the new cheque tipo
	 */
	public void setChequeTipo(String chequeTipo) {
		ChequeTipo = chequeTipo;
	}

	/**
	 * Gets the domicilio emisor.
	 *
	 * @return the domicilio emisor
	 */
	public String getDomicilioEmisor() {
		return domicilioEmisor;
	}

	/**
	 * Sets the domicilio emisor.
	 *
	 * @param domicilioEmisor the new domicilio emisor
	 */
	public void setDomicilioEmisor(String domicilioEmisor) {
		this.domicilioEmisor = domicilioEmisor;
	}

	/**
	 * Gets the entidad girada.
	 *
	 * @return the entidad girada
	 */
	public String getEntidadGirada() {
		return entidadGirada;
	}

	/**
	 * Sets the entidad girada.
	 *
	 * @param entidadGirada the new entidad girada
	 */
	public void setEntidadGirada(String entidadGirada) {
		this.entidadGirada = entidadGirada;
	}

	/**
	 * Gets the domicilio pago.
	 *
	 * @return the domicilio pago
	 */
	public String getDomicilioPago() {
		return domicilioPago;
	}

	/**
	 * Sets the domicilio pago.
	 *
	 * @param domicilioPago the new domicilio pago
	 */
	public void setDomicilioPago(String domicilioPago) {
		this.domicilioPago = domicilioPago;
	}

	/**
	 * Gets the cmc 7.
	 *
	 * @return the cmc 7
	 */
	public String getCmc7() {
		return cmc7;
	}

	/**
	 * Sets the cmc 7.
	 *
	 * @param cmc7 the new cmc 7
	 */
	public void setCmc7(String cmc7) {
		this.cmc7 = cmc7;
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

	public String getDomicilioBeneficiario() {
		return domicilioBeneficiario;
	}

	public void setDomicilioBeneficiario(String domicilioBeneficiario) {
		this.domicilioBeneficiario = domicilioBeneficiario;
	}

}
