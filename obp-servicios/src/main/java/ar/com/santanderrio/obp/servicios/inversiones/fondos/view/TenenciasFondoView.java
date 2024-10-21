/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaConTenenciaDTO;

/**
 * Representa las tenencias de un cliente en una banca en particular
 * PRIVADA/RETAIL.
 *
 * @author marcelo.ruiz
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TenenciasFondoView {

	/** The lista cuentas. */
	private List<CuentaConTenenciaDTO> listaCuentas;

	/** Legales. */
	private String legales;

	/** Mensaje sin tenencias mobile. */
	private String mensajeSinTenenciasMobile;

	/** Mensaje cuenta bloqueada. */
	private String mensajeCuentaBloqueada;
	
	private boolean nuevaApertura;

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
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
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

	public boolean isNuevaApertura() {
		return nuevaApertura;
	}

	public void setNuevaApertura(boolean nuevaApertura) {
		this.nuevaApertura = nuevaApertura;
	}
	
	

}
