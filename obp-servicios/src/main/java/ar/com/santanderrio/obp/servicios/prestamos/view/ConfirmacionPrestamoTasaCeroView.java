/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosPrestamoTasaCeroView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfirmacionPrestamoTasaCeroView {

	/** The celular. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String celular;
	
	/** The mail. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String mail;
	
	/** The cuit. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String cuit;
	
	
	/** The tarjeta. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String tarjeta;
	
	/** The tarjeta. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String tarjetaId;
	
	/** The emitir tarjeta. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private Boolean emitirTarjeta;

	/** The tipo. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String tipo;

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular the new celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the tarjeta.
	 *
	 * @return the tarjeta
	 */
	public String getTarjeta() {
		return tarjeta;
	}

	/**
	 * Sets the tarjeta.
	 *
	 * @param tarjeta the new tarjeta
	 */
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	/**
	 * Gets the emitir tarjeta.
	 *
	 * @return the emitir tarjeta
	 */
	public Boolean getEmitirTarjeta() {
		return emitirTarjeta;
	}

	/**
	 * Sets the emitir tarjeta.
	 *
	 * @param emitirTarjeta the new emitir tarjeta
	 */
	public void setEmitirTarjeta(Boolean emitirTarjeta) {
		this.emitirTarjeta = emitirTarjeta;
	}

	/**
	 * Gets the tarjeta id.
	 *
	 * @return the tarjeta id
	 */
	public String getTarjetaId() {
		return tarjetaId;
	}

	/**
	 * Sets the tarjeta id.
	 *
	 * @param tarjetaId the new tarjeta id
	 */
	public void setTarjetaId(String tarjetaId) {
		this.tarjetaId = tarjetaId;
	}

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
	
}
