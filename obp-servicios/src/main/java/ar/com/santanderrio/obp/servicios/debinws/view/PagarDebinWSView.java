package ar.com.santanderrio.obp.servicios.debinws.view;

import org.codehaus.jackson.annotate.JsonManagedReference;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class PagarDebinWSView.
 */
@SuppressWarnings("serial")
public class PagarDebinWSView extends RsaDTOParaDesafio {

	/** The mensaje. */
	private String mensaje;

	/** The is cuenta propia. */
	private boolean isCuentaPropia;

	/** The importe. */
	private String importe;

	/** The moneda. */
	private String moneda;

	/** The fecha ejecucion. */
	private String fechaEjecucion;

	/** The cbu vendedor. */
	private String cbuVendedor;

	/** The nombre solicitante. */
	private String nombreSolicitante;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha comprobante. */
	private String fechaComprobante;
	
	private String cuitVendedor;
	
	private String cuitComprador;
	
	private Integer cantDiasUltimoCambioClave;
	
	private Integer cantDiasUltimoCambioToken;

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
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the fecha ejecucion.
	 *
	 * @return the fecha ejecucion
	 */
	public String getFechaEjecucion() {
		return fechaEjecucion;
	}

	/**
	 * Sets the fecha ejecucion.
	 *
	 * @param fechaEjecucion the new fecha ejecucion
	 */
	public void setFechaEjecucion(String fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	/**
	 * Gets the cbu vendedor.
	 *
	 * @return the cbu vendedor
	 */
	public String getCbuVendedor() {
		return cbuVendedor;
	}

	/**
	 * Sets the cbu vendedor.
	 *
	 * @param cbuVendedor the new cbu vendedor
	 */
	public void setCbuVendedor(String cbuVendedor) {
		this.cbuVendedor = cbuVendedor;
	}

	/**
	 * Gets the nombre solicitante.
	 *
	 * @return the nombre solicitante
	 */
	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	/**
	 * Sets the nombre solicitante.
	 *
	 * @param nombreSolicitante the new nombre solicitante
	 */
	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	/**
	 * Sets the cuenta propia.
	 *
	 * @param isCuentaPropia the new cuenta propia
	 */
	public void setCuentaPropia(boolean isCuentaPropia) {
		this.isCuentaPropia = isCuentaPropia;
	}

	/** The desafio. */
	@JsonIgnore
	@JsonManagedReference
	private AutentificacionDTO desafio;

	/**
	 * Instantiates a new pagar debin WS view.
	 */
	public PagarDebinWSView() {
		super(OperacionesRSAEnum.PAGO_DEBIN);
	}

	/**
	 * Gets the desafio.
	 *
	 * @return the desafio
	 */
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	/**
	 * Sets the desafio.
	 *
	 * @param desafio the new desafio
	 */
	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje the new mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Checks if is cuenta propia.
	 *
	 * @return true, if is cuenta propia
	 */
	public boolean isCuentaPropia() {
		return isCuentaPropia;
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

	public String getCuitVendedor() {
		return cuitVendedor;
	}

	public void setCuitVendedor(String cuitVendedor) {
		this.cuitVendedor = cuitVendedor;
	}

	public String getCuitComprador() {
		return cuitComprador;
	}

	public void setCuitComprador(String cuitComprador) {
		this.cuitComprador = cuitComprador;
	}

	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

}
