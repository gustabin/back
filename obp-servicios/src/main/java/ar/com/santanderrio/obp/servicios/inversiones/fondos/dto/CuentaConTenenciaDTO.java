/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class CuentaConTenenciaDTO.
 */
public class CuentaConTenenciaDTO {

	/** The numero cuenta titulo. */
	private String numeroCuentaTitulo;

	/** The numero cuenta titulo. */
	private String sucursal;

	/** The intervinientes. */
	private List<Interviniente> intervinientes;

	/** The respuesta. */
	private Respuesta<TenenciaPorFondoDTO> respuesta;

	/** cuenta bloqueada. */
	private boolean cuentaBloqueada = false;
	
	/** cuenta bloqueada. */
	private boolean repatriacion = false;
	
	/** cta operativa asociada formateada (Repatriacion). */
	private String cuentaOperativa;

	/**
	 * Gets the numero cuenta titulo.
	 *
	 * @return the numeroCuentaTitulo
	 */
	public String getNumeroCuentaTitulo() {
		return numeroCuentaTitulo;
	}

	/**
	 * Sets the numero cuenta titulo.
	 *
	 * @param numeroCuentaTitulo
	 *            the numeroCuentaTitulo to set
	 */
	public void setNumeroCuentaTitulo(String numeroCuentaTitulo) {
		this.numeroCuentaTitulo = numeroCuentaTitulo;
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
	 *            the intervinientes to set
	 */
	public void setIntervinientes(List<Interviniente> intervinientes) {
		this.intervinientes = intervinientes;
	}

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public Respuesta<TenenciaPorFondoDTO> getRespuesta() {
		return respuesta;
	}

	/**
	 * Sets the respuesta.
	 *
	 * @param respuesta
	 *            the respuesta to set
	 */
	public void setRespuesta(Respuesta<TenenciaPorFondoDTO> respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Checks if is cuenta bloqueada.
	 *
	 * @return the cuentaBloqueada
	 */
	public boolean isCuentaBloqueada() {
		return cuentaBloqueada;
	}

	/**
	 * Sets the cuenta bloqueada.
	 *
	 * @param cuentaBloqueada
	 *            the cuentaBloqueada to set
	 */
	public void setCuentaBloqueada(boolean cuentaBloqueada) {
		this.cuentaBloqueada = cuentaBloqueada;
	}

	public boolean isRepatriacion() {
		return repatriacion;
	}

	public void setRepatriacion(boolean repatriacion) {
		this.repatriacion = repatriacion;
	}

	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}
	
	
	
	
}
