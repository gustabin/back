/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.List;

/**
 * Representa las tenencias de un cliente en una banca en particular
 * PRIVADA/RETAIL.
 *
 * @author marcelo.ruiz
 */
public class TenenciasFondoDTO {

	/** The lista cuentas. */
	private List<CuentaConTenenciaDTO> listaCuentas;

	/** Mensaje cuenta bloqueada. */
	private String mensajeCuentaBloqueada;

	/** Mensaje sin tenencias mobile. */
	private String mensajeSinTenenciasMobile;

	/**
	 * Gets the lista cuentas.
	 *
	 * @return the listaCuentas
	 */
	public List<CuentaConTenenciaDTO> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the listaCuentas to set
	 */
	public void setListaCuentas(List<CuentaConTenenciaDTO> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the mensaje cuenta bloqueada.
	 *
	 * @return the mensajeCuentaBloqueada
	 */
	public String getMensajeCuentaBloqueada() {
		return mensajeCuentaBloqueada;
	}

	/**
	 * Sets the mensaje cuenta bloqueada.
	 *
	 * @param mensajeCuentaBloqueada
	 *            the mensajeCuentaBloqueada to set
	 */
	public void setMensajeCuentaBloqueada(String mensajeCuentaBloqueada) {
		this.mensajeCuentaBloqueada = mensajeCuentaBloqueada;
	}

	/**
	 * Gets the mensaje sin tenencias mobile.
	 *
	 * @return the mensajeSinTenenciasMobile
	 */
	public String getMensajeSinTenenciasMobile() {
		return mensajeSinTenenciasMobile;
	}

	/**
	 * Sets the mensaje sin tenencias mobile.
	 *
	 * @param mensajeSinTenenciasMobile
	 *            the mensajeSinTenenciasMobile to set
	 */
	public void setMensajeSinTenenciasMobile(String mensajeSinTenenciasMobile) {
		this.mensajeSinTenenciasMobile = mensajeSinTenenciasMobile;
	}

}
