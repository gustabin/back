/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

// TODO: Auto-generated Javadoc
/**
 * The Class ObtenerPrestamosInDTO.
 */
public class ObtenerPrestamosInDTO {

	/** The cliente. */
	private Cliente cliente;

	/** The is prendario. */
	private Boolean isPrendario;

	/** The is hipotecario. */
	private Boolean isHipotecario;

	/** The is personal. */
	private Boolean isPersonal;
	
	/** The is personal. */
	private Boolean isPrestamoSueldo;

	/** The is todos. */
	private Boolean isTodos;

	/** The orden prestamos. */
	private OrdenPrestamos ordenPrestamos;
	
	/** calificaciones crediticias. */
	private List<CalificacionCrediticiaDTO> calificaciones;

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
	 * Gets the checks if is prendario.
	 *
	 * @return the checks if is prendario
	 */
	public Boolean getIsPrendario() {
		return isPrendario;
	}

	/**
	 * Sets the checks if is prendario.
	 *
	 * @param isPrendario
	 *            the new checks if is prendario
	 */
	public void setIsPrendario(Boolean isPrendario) {
		this.isPrendario = isPrendario;
	}

	/**
	 * Gets the checks if is hipotecario.
	 *
	 * @return the checks if is hipotecario
	 */
	public Boolean getIsHipotecario() {
		return isHipotecario;
	}

	/**
	 * Sets the checks if is hipotecario.
	 *
	 * @param isHipotecario
	 *            the new checks if is hipotecario
	 */
	public void setIsHipotecario(Boolean isHipotecario) {
		this.isHipotecario = isHipotecario;
	}

	/**
	 * Gets the checks if is personal.
	 *
	 * @return the checks if is personal
	 */
	public Boolean getIsPersonal() {
		return isPersonal;
	}

	/**
	 * Sets the checks if is personal.
	 *
	 * @param isPersonal
	 *            the new checks if is personal
	 */
	public void setIsPersonal(Boolean isPersonal) {
		this.isPersonal = isPersonal;
	}

	/**
	 * Gets the checks if is todos.
	 *
	 * @return the checks if is todos
	 */
	public Boolean getIsTodos() {
		return isTodos;
	}

	/**
	 * Sets the checks if is todos.
	 *
	 * @param isTodos
	 *            the new checks if is todos
	 */
	public void setIsTodos(Boolean isTodos) {
		this.isTodos = isTodos;
	}

	/**
	 * Gets the orden prestamos.
	 *
	 * @return the orden prestamos
	 */
	public OrdenPrestamos getOrdenPrestamos() {
		return ordenPrestamos;
	}

	/**
	 * Sets the orden prestamos.
	 *
	 * @param ordenPrestamos
	 *            the new orden prestamos
	 */
	public void setOrdenPrestamos(OrdenPrestamos ordenPrestamos) {
		this.ordenPrestamos = ordenPrestamos;
	}

	/**
	 * Gets the calificaciones.
	 *
	 * @return the calificaciones
	 */
	public List<CalificacionCrediticiaDTO> getCalificaciones() {
		return calificaciones;
	}

	/**
	 * Sets the calificaciones.
	 *
	 * @param calificaciones the new calificaciones
	 */
	public void setCalificaciones(List<CalificacionCrediticiaDTO> calificaciones) {
		this.calificaciones = calificaciones;
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

	
}
