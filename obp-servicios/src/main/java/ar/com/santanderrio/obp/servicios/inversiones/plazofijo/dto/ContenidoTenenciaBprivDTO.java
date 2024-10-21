/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class ContenidoTenenciaBprivDTO.
 */
public class ContenidoTenenciaBprivDTO {

	/** The numero Cuenta. */
	private String numeroCuenta;

	/** The tenenciaPlazoFijoDTO. */
	private TenenciaTotalPlazoFijoDTO tenenciaPlazoFijoDTO;

	/** The totalPesos. */
	private String totalPesos;

	/** The totalPesos. */
	private String totalDolares;

	/** The intervinientes. titular y cotitulares */
	private List<Interviniente> intervinientes;

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
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the tenencia plazo fijo DTO.
	 *
	 * @return the tenencia plazo fijo DTO
	 */
	public TenenciaTotalPlazoFijoDTO getTenenciaPlazoFijoDTO() {
		return tenenciaPlazoFijoDTO;
	}

	/**
	 * Sets the tenencia plazo fijo DTO.
	 *
	 * @param tenenciaPlazoFijoDTO
	 *            the new tenencia plazo fijo DTO
	 */
	public void setTenenciaPlazoFijoDTO(TenenciaTotalPlazoFijoDTO tenenciaPlazoFijoDTO) {
		this.tenenciaPlazoFijoDTO = tenenciaPlazoFijoDTO;
	}

	/**
	 * Gets the total pesos.
	 *
	 * @return the total pesos
	 */
	public String getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the new total pesos
	 */
	public void setTotalPesos(String totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the total dolares
	 */
	public String getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the new total dolares
	 */
	public void setTotalDolares(String totalDolares) {
		this.totalDolares = totalDolares;
	}

	/**
	 * Gets the intervinientes.
	 *
	 * @return the intervinientes
	 */
	public List<Interviniente> getIntervinientes() {
		return intervinientes;
	}

	/**
	 * Sets the intervinientes.
	 *
	 * @param intervinientes
	 *            the new intervinientes
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

}
