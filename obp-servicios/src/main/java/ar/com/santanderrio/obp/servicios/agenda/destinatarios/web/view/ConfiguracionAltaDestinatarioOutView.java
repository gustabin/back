/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;

/**
 * The Class ConfiguracionAltaDestinatarioOutView.
 */
@XmlRootElement(name = "configuracionAltaDestinatarioView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfiguracionAltaDestinatarioOutView {

	/** The Nombre Y apellido. */
	private String titular;

	/** The numero cuil. */
	private String numeroCuil;

	/** The es cuit. */
	private boolean esCuit;

	/**
	 * Instantiates a new configuracion alta destinatario out view.
	 *
	 * @param dto
	 *            the dto
	 */
	public ConfiguracionAltaDestinatarioOutView(ConfiguracionAltaDestinatarioDTO dto) {
		this.titular = dto.getNombreYApellido();
		this.numeroCuil = dto.getNumeroCuil();
		this.esCuit = "T".equals(dto.getCuitOCuil());
	}

	/**
	 * Instantiates a new configuracion alta destinatario out view.
	 */
	public ConfiguracionAltaDestinatarioOutView() {
		super();
	}

	/**
	 * Gets the nombre Y apellido.
	 *
	 * @return the nombre Y apellido
	 */
	public String getTitular() {
		return titular;
	}

	/**
	 * Sets the nombre Y apellido.
	 *
	 * @param titular
	 *            the new titular
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}

	/**
	 * Gets the numero cuil.
	 *
	 * @return the numero cuil
	 */
	public String getNumeroCuil() {
		return numeroCuil;
	}

	/**
	 * Sets the numero cuil.
	 *
	 * @param numeroCuil
	 *            the new numero cuil
	 */
	public void setNumeroCuil(String numeroCuil) {
		this.numeroCuil = numeroCuil;
	}

	/**
	 * Checks if is es cuit.
	 *
	 * @return true, if is es cuit
	 */
	public boolean isEsCuit() {
		return esCuit;
	}

	/**
	 * Sets the es cuit.
	 *
	 * @param esCuit
	 *            the new es cuit
	 */
	public void setEsCuit(boolean esCuit) {
		this.esCuit = esCuit;
	}

}
