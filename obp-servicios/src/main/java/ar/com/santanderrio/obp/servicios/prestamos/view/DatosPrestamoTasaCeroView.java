/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.pagos.entities.TarjetaPrestamoTasaCero;

// TODO: Auto-generated Javadoc
/**
 * The Class DatosPrestamoTasaCeroView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosPrestamoTasaCeroView {

	/** The celular. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String celular;
	
	/** The mail. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String mail;
	
	/** The cuit. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String cuit;
	
	/** The legal. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String legal;
	
	/** The tarjeta. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String tarjeta;
	
	/** The emitir tarjeta. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private Boolean emitirTarjeta;
	
	/** The importe solicitado. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String importeSolicitado;
	
	/** The habilitado prestamo. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String habilitadoPrestamo;

	/** The cards. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private List<TarjetaPrestamoTasaCero> cards;

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
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
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
	 * Gets the importe solicitado.
	 *
	 * @return the importe solicitado
	 */
	public String getImporteSolicitado() {
		return importeSolicitado;
	}

	/**
	 * Sets the importe solicitado.
	 *
	 * @param importeSolicitado the new importe solicitado
	 */
	public void setImporteSolicitado(String importeSolicitado) {
		this.importeSolicitado = importeSolicitado;
	}

	/**
	 * Gets the habilitado prestamo.
	 *
	 * @return the habilitado prestamo
	 */
	public String getHabilitadoPrestamo() {
		return habilitadoPrestamo;
	}

	/**
	 * Sets the habilitado prestamo.
	 *
	 * @param habilitadoPrestamo the new habilitado prestamo
	 */
	public void setHabilitadoPrestamo(String habilitadoPrestamo) {
		this.habilitadoPrestamo = habilitadoPrestamo;
	}

	/**
	 * Gets the cards.
	 *
	 * @return the cards
	 */
	public List<TarjetaPrestamoTasaCero> getCards() {
		return cards;
	}

	/**
	 * Sets the cards.
	 *
	 * @param cards the new cards
	 */
	public void setCards(List<TarjetaPrestamoTasaCero> cards) {
		this.cards = cards;
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
