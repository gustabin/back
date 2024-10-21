/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dto;

/**
 * The Class ConsultaOperacionesDTO.
 *
 * @author IT Resources
 */
public class ConsultaOperacionesDTO implements Comparable<ConsultaOperacionesDTO> {
	
	/** The fecha operacion. */
	private String fechaOperacion;
	
	/** The destintario. */
	private String destinatario;
		
	/** The cod estado. */
	private String codEstado;
	
	/** The estado descripcion. */
	private String estadoDescripcion;

	/** The cod moneda. */
	private String codMoneda;
	
	/** The monto. */
	private String monto;
	
	/** The nro form. */
	private Long nroForm;
	
	/** The cuenta cliente. */
	private String cuentaCliente;
	
	/**
	 * Gets the fecha operacion.
	 *
	 * @return the fechaOperacion
	 */
	public String getFechaOperacion() {
		return fechaOperacion;
	}

	/**
	 * Sets the fecha operacion.
	 *
	 * @param fechaOperacion
	 *            the fechaOperacion to set
	 */
	public void setFechaOperacion(String fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	/**
	 * Gets the destinatario.
	 *
	 * @return the destinatario
	 */
	public String getDestinatario() {
		return destinatario;
	}

	/**
	 * Sets the destinatario.
	 *
	 * @param destinatario
	 *            the destinatario to set
	 */
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	/**
	 * Gets the cod estado.
	 *
	 * @return the codEstado
	 */
	public String getCodEstado() {
		return codEstado;
	}

	/**
	 * Sets the cod estado.
	 *
	 * @param codEstado
	 *            the codEstado to set
	 */
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * Gets the estado descripcion.
	 *
	 * @return the estadoDescripcion
	 */
	public String getEstadoDescripcion() {
		return estadoDescripcion;
	}

	/**
	 * Sets the estado descripcion.
	 *
	 * @param estadoDescripcion
	 *            the estadoDescripcion to set
	 */
	public void setEstadoDescripcion(String estadoDescripcion) {
		this.estadoDescripcion = estadoDescripcion;
	}

	
	/**
	 * Gets the cod moneda.
	 *
	 * @return the codMoneda
	 */
	public String getCodMoneda() {
		return codMoneda;
	}

	/**
	 * Sets the cod moneda.
	 *
	 * @param codMoneda
	 *            the codMoneda to set
	 */
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}

	/**
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the nro form.
	 *
	 * @return the nroForm
	 */
	public Long getNroForm() {
		return nroForm;
	}

	/**
	 * Sets the nro form.
	 *
	 * @param nroForm
	 *            the nroForm to set
	 */
	public void setNroForm(Long nroForm) {
		this.nroForm = nroForm;
	}
	
	/**
	 * Gets the cuenta cliente.
	 *
	 * @return the cuentaCliente
	 */
	public String getCuentaCliente() {
		return cuentaCliente;
	}

	/**
	 * Sets the cuenta cliente.
	 *
	 * @param cuentaCliente
	 *            the cuentaCliente to set
	 */
	public void setCuentaCliente(String cuentaCliente) {
		this.cuentaCliente = cuentaCliente;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ConsultaOperacionesDTO o) {
		return  o.nroForm.compareTo(this.nroForm);
	}
	

}
