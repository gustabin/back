/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * Clase que modela una cuenta.
 * 
 * @author marcelo.ruiz
 *
 */
public class CuentasConsultaFondoDTO {

	/** The Constant LARGO_NUMERO_CUENTA. */
	private static final int LARGO_NUMERO_CUENTA = 16;

	/** The tipo banca. */
	private String tipoBanca;

	/** The cabecera stack. */
	private String cabeceraStack;

	/** The cuentas titulo. */
	private List<CuentaTituloDTO> cuentasTitulo;

	/** The fondos totales. */
	private List<FondoResumidoDTO> fondosTotales;

	/** The intervinientes. titular y cotitulares */
	private List<Interviniente> intervinientes;

	/** The mensaje informacion. */
	private String mensajeInformacion;

	/** The texto buscador. */
	private String textoBuscador;
	
	

	/**
	 * Gets the tipo banca.
	 *
	 * @return the tipo banca
	 */
	public String getTipoBanca() {
		return tipoBanca;
	}

	/**
	 * Sets the tipo banca.
	 *
	 * @param tipoBanca
	 *            the new tipo banca
	 */
	public void setTipoBanca(String tipoBanca) {
		this.tipoBanca = tipoBanca;
	}

	/**
	 * Gets the cabecera stack.
	 *
	 * @return the cabecera stack
	 */
	public String getCabeceraStack() {
		return cabeceraStack;
	}

	/**
	 * Sets the cabecera stack.
	 *
	 * @param cabeceraStack
	 *            the new cabecera stack
	 */
	public void setCabeceraStack(String cabeceraStack) {
		this.cabeceraStack = cabeceraStack;
	}

	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<CuentaTituloDTO> getCuentasTitulo() {
		return cuentasTitulo;
	}

	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<CuentaTituloDTO> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
	}

	/**
	 * Gets the fondos totales.
	 *
	 * @return the fondos totales
	 */
	public List<FondoResumidoDTO> getFondosTotales() {
		return fondosTotales;
	}

	/**
	 * Sets the fondos totales.
	 *
	 * @param fondosTotales
	 *            the new fondos totales
	 */
	public void setFondosTotales(List<FondoResumidoDTO> fondosTotales) {
		this.fondosTotales = fondosTotales;
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

	/**
	 * Gets the cuenta by numero.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the cuenta by numero
	 */
	public CuentaTituloDTO getCuentaByNumero(String numeroCuenta) {
		numeroCuenta = StringUtils.leftPad(numeroCuenta, LARGO_NUMERO_CUENTA, '0');
		for (CuentaTituloDTO cuentaTituloDTO : this.getCuentasTitulo()) {
			if (cuentaTituloDTO.getNroCuenta().equals(numeroCuenta)) {
				return cuentaTituloDTO;
			}
		}
		return null;
	}

	/**
	 * Gets the mensaje informacion.
	 *
	 * @return the mensajeInformacion
	 */
	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	/**
	 * Sets the mensaje informacion.
	 *
	 * @param mensajeInformacion
	 *            the mensajeInformacion to set
	 */
	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}

	/**
	 * Gets the texto buscador.
	 *
	 * @return the textoBuscador
	 */
	public String getTextoBuscador() {
		return textoBuscador;
	}

	/**
	 * Sets the texto buscador.
	 *
	 * @param textoBuscador
	 *            the textoBuscador to set
	 */
	public void setTextoBuscador(String textoBuscador) {
		this.textoBuscador = textoBuscador;
	}

}
