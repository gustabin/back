/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Objeto utilizado para el Input del FondoSEI.
 * 
 * @author
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FondoView {

	/** The codigo fondo. */
	String codigoFondo;

	/** The moneda. */
	String moneda;

	/** The cuenta titulos. */
	String cuentaTitulos;

	/** The importe. */
	String importe;

	/** The numero cuenta debito. */
	String numeroCuentaDebito;

	/** The sucursal cuenta debito. */
	String sucursalCuentaDebito;

	/** The tipo cuenta debito. */
	String tipoCuentaDebito;

	/** The url reglamento. */
	String urlReglamento;

	/** The mensaje suscripcion. */
	String mensajeSuscripcion;

	/** The numero certificado. */
	String numeroComprobante;

	/** The cantidad intentos. */
	String cantidadIntentos;

	/** The cabecera. */
	String cabecera;

	/** The pie. */
	String pie;

	/** The nombre fondo. */
	String nombreFondo;

	/** The fechaHora. */
	String fechaHora;

	/** variable reintentar. */
	private String reintentar;

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
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulo.
	 *
	 * @param cuentaTitulo
	 *            the new cuenta titulo
	 */
	public void setCuentaTitulo(String cuentaTitulo) {
		this.cuentaTitulos = cuentaTitulo;
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
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cta deb.
	 *
	 * @param numeroCtaDeb
	 *            the new numero cta deb
	 */
	public void setNumeroCtaDeb(String numeroCtaDeb) {
		this.numeroCuentaDebito = numeroCtaDeb;
	}

	/**
	 * Gets the sucursal cta deb.
	 *
	 * @return the sucursal cta deb
	 */
	public String getSucursalCtaDeb() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cta deb.
	 *
	 * @param sucursalCtaDeb
	 *            the new sucursal cta deb
	 */
	public void setSucursalCtaDeb(String sucursalCtaDeb) {
		this.sucursalCuentaDebito = sucursalCtaDeb;
	}

	/**
	 * Gets the tipo cta deb.
	 *
	 * @return the tipo cta deb
	 */
	public String getTipoCtaDeb() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cta deb.
	 *
	 * @param tipoCtaDeb
	 *            the new tipo cta deb
	 */
	public void setTipoCtaDeb(String tipoCtaDeb) {
		this.tipoCuentaDebito = tipoCtaDeb;
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
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero certificado.
	 *
	 * @param numeroCertificado
	 *            the new numero certificado
	 */
	public void setNumeroComprobante(String numeroCertificado) {
		this.numeroComprobante = numeroCertificado;
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

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the pie.
	 *
	 * @return the pie
	 */
	public String getPie() {
		return pie;
	}

	/**
	 * Sets the pie.
	 *
	 * @param pie
	 *            the new pie
	 */
	public void setPie(String pie) {
		this.pie = pie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(codigoFondo).append(moneda).append(cuentaTitulos).append(importe)
				.append(numeroCuentaDebito).append(sucursalCuentaDebito).append(tipoCuentaDebito).append(urlReglamento)
				.append(mensajeSuscripcion).append(numeroComprobante).append(nombreFondo).append(reintentar)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
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

		FondoView other = (FondoView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(codigoFondo, other.getCodigoFondo()).append(moneda, other.getMoneda())
				.append(cuentaTitulos, other.getCuentaTitulo()).append(importe, other.getImporte())
				.append(numeroCuentaDebito, other.getNumeroCtaDeb())
				.append(sucursalCuentaDebito, other.getSucursalCtaDeb()).append(tipoCuentaDebito, other.getTipoCtaDeb())
				.append(urlReglamento, other.getUrlReglamento()).append(numeroComprobante, other.getNumeroComprobante())
				.append(nombreFondo, other.getNombreFondo()).append(mensajeSuscripcion, other.getMensajeSuscripcion())
				.append(reintentar, other.getReintentar()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("Codigo de fondo: ", codigoFondo).append("Moneda: ", moneda)
				.append("Cuenta titulo: ", cuentaTitulos).append("Importe: ", importe)
				.append("Numero de cuenta debito: ", numeroCuentaDebito)
				.append("Sucursal de cuenta debito: ", sucursalCuentaDebito)
				.append("Tipo de cuenta debito: ", tipoCuentaDebito).append("Tipo url Reglamento: ", urlReglamento)
				.append("numero de Certificado: ", numeroComprobante).append("nombre del fondo: ", nombreFondo)
				.append("reintentar: ", reintentar).append("Tipo mensaje Suscripcion: ", mensajeSuscripcion).toString();
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
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

}