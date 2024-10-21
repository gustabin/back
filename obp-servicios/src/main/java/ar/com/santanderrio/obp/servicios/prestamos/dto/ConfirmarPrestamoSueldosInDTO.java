/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;


import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;


// TODO: Auto-generated Javadoc
/**
 * The Class ObtenerPrestamosInDTO.
 */
public class ConfirmarPrestamoSueldosInDTO {

	/** The cliente. */
	private Cliente cliente;
	
	/** The is personal. */
	private Boolean isPrestamoSueldo;
	
	/** The monto prestamo seleccion. */
	private String montoPrestamoSeleccion;
	
	/** The email. */
	private String email;
	
	/** The Idcuentas pesos. */
	private Cuenta cuentasPesos;
	
	/** The adjunta documentacion. */
	private String adjuntaDocumentacion;


	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	/**
	 * Gets the checks if is prestamo sueldo.
	 *
	 * @return the checks if is prestamo sueldo
	 */
	public Boolean getIsPrestamoSueldo() {
		return isPrestamoSueldo;
	}

	/**
	 * Sets the checks if is prestamo sueldo.
	 *
	 * @param isPrestamoSueldo the new checks if is prestamo sueldo
	 */
	public void setIsPrestamoSueldo(Boolean isPrestamoSueldo) {
		this.isPrestamoSueldo = isPrestamoSueldo;
	}

	/**
	 * Gets the monto prestamo seleccion.
	 *
	 * @return the monto prestamo seleccion
	 */
	public String getMontoPrestamoSeleccion() {
		return montoPrestamoSeleccion;
	}

	/**
	 * Sets the monto prestamo seleccion.
	 *
	 * @param montoPrestamoSeleccion the new monto prestamo seleccion
	 */
	public void setMontoPrestamoSeleccion(String montoPrestamoSeleccion) {
		this.montoPrestamoSeleccion = montoPrestamoSeleccion;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the cuentas pesos.
	 *
	 * @return the cuentas pesos
	 */
	public Cuenta getCuentasPesos() {
		return cuentasPesos;
	}

	/**
	 * Sets the cuentas pesos.
	 *
	 * @param cuentasPesos the new cuentas pesos
	 */
	public void setCuentasPesos(Cuenta cuentasPesos) {
		this.cuentasPesos = cuentasPesos;
	}

	/**
	 * Gets the adjunta documentacion.
	 *
	 * @return the adjunta documentacion
	 */
	public String getAdjuntaDocumentacion() {
		return adjuntaDocumentacion;
	}

	/**
	 * Sets the adjunta documentacion.
	 *
	 * @param adjuntaDocumentacion the new adjunta documentacion
	 */
	public void setAdjuntaDocumentacion(String adjuntaDocumentacion) {
		this.adjuntaDocumentacion = adjuntaDocumentacion;
	}
			
}
