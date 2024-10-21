/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dto;

import java.util.Date;
import java.util.Map;

/**
 * The Class ConsultaDetalleDebinWSOutDTO.
 */
public class ConsultaDetalleDebinWSOutDTO {

	/** The importe solicitado. */
	private String importeSolicitado;

	/** The estado. */
	private String estado;

	/** The fecha solicitud. */
	private Date fechaSolicitud;

	/** The fecha vencimiento. */
	private Date fechaVencimiento;

	/** The descripcion. */
	private String descripcion;

	/** The concepto. */
	private String concepto;

	/** The id debin. */
	private String idDebin;

	/** The moneda. */
	private String moneda;

	/** The preautorizado. */
	private boolean preautorizado;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha comprobante. */
	private String fechaComprobante;

	/** The comprador. */
	private CompradorDTO comprador;

	/** The vendedor. */
	private VendedorDTO vendedor;

	/** The operacion en proceso. */
	private boolean operacionEnProceso;

	/** The id cuentas debin. */
	private Map<Integer, String> idCuentasDebin;

	/** The puntaje. */
	private Integer puntaje;

	/**
	 * Checks if is operacion en proceso.
	 *
	 * @return true, if is operacion en proceso
	 */
	public boolean isOperacionEnProceso() {
		return operacionEnProceso;
	}

	/**
	 * Sets the operacion en proceso.
	 *
	 * @param operacionEnProceso the new operacion en proceso
	 */
	public void setOperacionEnProceso(boolean operacionEnProceso) {
		this.operacionEnProceso = operacionEnProceso;
	}

	/**
	 * Gets the comprador.
	 *
	 * @return the comprador
	 */
	public CompradorDTO getComprador() {
		return comprador;
	}

	/**
	 * Sets the comprador.
	 *
	 * @param comprador the new comprador
	 */
	public void setComprador(CompradorDTO comprador) {
		this.comprador = comprador;
	}

	/**
	 * Gets the vendedor.
	 *
	 * @return the vendedor
	 */
	public VendedorDTO getVendedor() {
		return vendedor;
	}

	/**
	 * Sets the vendedor.
	 *
	 * @param vendedor the new vendedor
	 */
	public void setVendedor(VendedorDTO vendedor) {
		this.vendedor = vendedor;
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
	 * Checks if is preautorizado.
	 *
	 * @return true, if is preautorizado
	 */
	public boolean isPreautorizado() {
		return preautorizado;
	}

	/**
	 * Sets the preautorizado.
	 *
	 * @param preautorizado the new preautorizado
	 */
	public void setPreautorizado(boolean preautorizado) {
		this.preautorizado = preautorizado;
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
	 * Gets the importe solicitado.
	 *
	 * @return the importe solicitado
	 */
	public String getImporteSolicitado() {
		return importeSolicitado;
	}

	/**
	 * Sets the importe solicitado.
	 *
	 * @param importeSolicitado the new importe solicitado
	 */
	public void setImporteSolicitado(String importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
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
	 * Gets the fecha solicitud.
	 *
	 * @return the fecha solicitud
	 */
	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	/**
	 * Sets the fecha solicitud.
	 *
	 * @param fechaSolicitud the new fecha solicitud
	 */
	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento the new fecha vencimiento
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto the new concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the id debin.
	 *
	 * @return the id debin
	 */
	public String getIdDebin() {
		return idDebin;
	}

	/**
	 * Sets the id debin.
	 *
	 * @param idDebin the new id debin
	 */
	public void setIdDebin(String idDebin) {
		this.idDebin = idDebin;
	}

	/**
	 * Gets the id cuentas debin.
	 *
	 * @return the id cuentas debin
	 */
	public Map<Integer, String> getIdCuentasDebin() {
		return idCuentasDebin;
	}

	/**
	 * Sets the id cuentas debin.
	 *
	 * @param idCuentasDebin the id cuentas debin
	 */
	public void setIdCuentasDebin(Map<Integer, String> idCuentasDebin) {
		this.idCuentasDebin = idCuentasDebin;
	}

	/**
	 * Gets the puntaje.
	 *
	 * @return the puntaje
	 */
	public Integer getPuntaje() {
		return puntaje;
	}

	/**
	 * Sets the puntaje.
	 *
	 * @param puntaje the new puntaje
	 */
	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}

}
