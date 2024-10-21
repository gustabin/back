/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FondoDTO extends BaseFondoAbstractDTO {

	/** The codigo fondo. */
	String codigoFondo;

	/** The nombre fondo. */
	String nombreFondo;

	/** The moneda. */
	String moneda;

	/** The cuenta titulo. */
	String cuentaTitulo;

	/** The importe. */
	String importe;

	/** The numero cta deb. */
	String numeroCtaDeb;

	/** The sucursal cta deb. */
	String sucursalCtaDeb;

	/** The tipo cta deb. */
	String tipoCtaDeb;

	/** The url reglamento. */
	String urlReglamento;

	/** The mensaje suscripcion. */
	String mensajeSuscripcion;

	/** The numero certificado. */
	String numeroCertificado;

	/** The terminos Y condiciones. */
	private String terminosYCondiciones;

	/** The legales. */
	private String legales;

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the cuenta titulo.
	 *
	 * @return the cuenta titulo
	 */
	public String getCuentaTitulo() {
		return cuentaTitulo;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulo = cuentaTitulo;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the numero cta deb.
	 *
	 * @return the numero cta deb
	 */
	public String getNumeroCtaDeb() {
		return numeroCtaDeb;
	}

	/**
	 * Sets the numero cta deb.
	 *
	 * @param numeroCtaDeb
	 *            the new numero cta deb
	 */
	public void setNumeroCtaDeb(String numeroCtaDeb) {
		this.numeroCtaDeb = numeroCtaDeb;
	}

	/**
	 * Gets the sucursal cta deb.
	 *
	 * @return the sucursal cta deb
	 */
	public String getSucursalCtaDeb() {
		return sucursalCtaDeb;
	}

	/**
	 * Sets the sucursal cta deb.
	 *
	 * @param sucursalCtaDeb
	 *            the new sucursal cta deb
	 */
	public void setSucursalCtaDeb(String sucursalCtaDeb) {
		this.sucursalCtaDeb = sucursalCtaDeb;
	}

	/**
	 * Gets the tipo cta deb.
	 *
	 * @return the tipo cta deb
	 */
	public String getTipoCtaDeb() {
		return tipoCtaDeb;
	}

	/**
	 * Sets the tipo cta deb.
	 *
	 * @param tipoCtaDeb
	 *            the new tipo cta deb
	 */
	public void setTipoCtaDeb(String tipoCtaDeb) {
		this.tipoCtaDeb = tipoCtaDeb;
	}

	/**
	 * Gets the url reglamento.
	 *
	 * @return the url reglamento
	 */
	public String getUrlReglamento() {
		return urlReglamento;
	}

	/**
	 * Sets the url reglamento.
	 *
	 * @param urlReglamento
	 *            the new url reglamento
	 */
	public void setUrlReglamento(String urlReglamento) {
		this.urlReglamento = urlReglamento;
	}

	/**
	 * Gets the mensaje suscripcion.
	 *
	 * @return the mensaje suscripcion
	 */
	public String getMensajeSuscripcion() {
		return mensajeSuscripcion;
	}

	/**
	 * Sets the mensaje suscripcion.
	 *
	 * @param mensajeSuscripcion
	 *            the new mensaje suscripcion
	 */
	public void setMensajeSuscripcion(String mensajeSuscripcion) {
		this.mensajeSuscripcion = mensajeSuscripcion;
	}

	/**
	 * Gets the numero certificado.
	 *
	 * @return the numero certificado
	 */
	public String getNumeroCertificado() {
		return numeroCertificado;
	}

	/**
	 * Sets the numero certificado.
	 *
	 * @param numeroCertificado
	 *            the new numero certificado
	 */
	public void setNumeroCertificado(String numeroCertificado) {
		this.numeroCertificado = numeroCertificado;
	}

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombre fondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the new nombre fondo
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(codigoFondo).append(moneda).append(cuentaTitulo).append(importe)
				.append(numeroCtaDeb).append(sucursalCtaDeb).append(tipoCtaDeb).append(urlReglamento)
				.append(mensajeSuscripcion).append(numeroCertificado).append(nombreFondo).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		FondoDTO other = (FondoDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(codigoFondo, other.getCodigoFondo()).append(moneda, other.getMoneda())
				.append(cuentaTitulo, other.getCuentaTitulo()).append(importe, other.getImporte())
				.append(numeroCtaDeb, other.getNumeroCtaDeb()).append(sucursalCtaDeb, other.getSucursalCtaDeb())
				.append(tipoCtaDeb, other.getTipoCtaDeb()).append(urlReglamento, other.getUrlReglamento())
				.append(mensajeSuscripcion, other.getMensajeSuscripcion()).append(nombreFondo, other.getNombreFondo())
				.append(numeroCertificado, other.getNumeroCertificado()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("Codigo de fondo: ", codigoFondo).append("Moneda: ", moneda)
				.append("Cuenta titulo: ", cuentaTitulo).append("Importe: ", importe)
				.append("Numero de cuenta debito: ", numeroCtaDeb).append("Sucursal de cuenta debito: ", sucursalCtaDeb)
				.append("Tipo de cuenta debito: ", tipoCtaDeb).append("Tipo url Reglamento: ", urlReglamento)
				.append("Numero de certificado: ", numeroCertificado).append("nombre del fondo: ", nombreFondo)
				.append("Tipo mensaje Suscripcion: ", mensajeSuscripcion).toString();
	}

	/**
	 * Gets the terminos Y condiciones.
	 *
	 * @return the terminos Y condiciones
	 */
	public String getTerminosYCondiciones() {
		return terminosYCondiciones;
	}

	/**
	 * Sets the terminos Y condiciones.
	 *
	 * @param terminosYCondiciones
	 *            the new terminos Y condiciones
	 */
	public void setTerminosYCondiciones(String terminosYCondiciones) {
		this.terminosYCondiciones = terminosYCondiciones;
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

}