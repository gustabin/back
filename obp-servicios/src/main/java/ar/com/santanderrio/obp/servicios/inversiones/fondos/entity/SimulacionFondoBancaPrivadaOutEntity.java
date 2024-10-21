/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado para retornar del DAO.
 * 
 * @author
 *
 */
public class SimulacionFondoBancaPrivadaOutEntity {

	/** The numero orden. */
	private String numeroOrden;

	/** The numero certificado. */
	private String numeroCertificado;

	/** The cuotas partes. */
	private String cuotasPartes;

	/** The importe. */
	private String importe;

	/** The dentro del perfil. */
	private String dentroDelPerfil;

	/** The disclaimer. */
	private String disclaimer;

	/** The cotizacion. */
	private String cotizacion;

	/** The retorno servicio. */
	private String retornoServicio;

	/** The codigo retorno. */
	private String codigoRetorno;

	/** The mensaje del servicio. */
	private String mensajeDelServicio;

	/** The capital. */
	private BigDecimal capital;

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
		return new HashCodeBuilder().append(numeroOrden).append(numeroCertificado).append(cuotasPartes).append(importe)
				.append(dentroDelPerfil).append(disclaimer).append(cotizacion).append(retornoServicio)
				.append(codigoRetorno).append(mensajeDelServicio).append(capital).toHashCode();

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

		SimulacionFondoBancaPrivadaOutEntity other = (SimulacionFondoBancaPrivadaOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append(numeroOrden, other.numeroOrden)
				.append(numeroCertificado, other.numeroCertificado).append(cuotasPartes, other.cuotasPartes)
				.append(importe, other.importe).append(dentroDelPerfil, other.dentroDelPerfil)
				.append(disclaimer, other.disclaimer).append(cotizacion, other.cotizacion)
				.append(retornoServicio, other.retornoServicio).append(codigoRetorno, other.codigoRetorno)
				.append(mensajeDelServicio, other.mensajeDelServicio).append(capital, other.capital);
		return eb.isEquals();
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
		return new ToStringBuilder(this).append("numeroOrden", numeroOrden)
				.append("numeroCertificado", numeroCertificado).append("cuotasPartes", cuotasPartes)
				.append("importe", importe).append("dentroDelPerfil", dentroDelPerfil).append("disclaimer", disclaimer)
				.append("cotizacion", cotizacion).append("retornoServicio", retornoServicio)
				.append("codigoRetorno", codigoRetorno).append("mensajeDelServicio", mensajeDelServicio)
				.append("capital", capital).toString();
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
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
	 * Gets the cuotas partes.
	 *
	 * @return the cuotas partes
	 */
	public String getCuotasPartes() {
		return cuotasPartes;
	}

	/**
	 * Sets the cuotas partes.
	 *
	 * @param cuotasPartes
	 *            the new cuotas partes
	 */
	public void setCuotasPartes(String cuotasPartes) {
		this.cuotasPartes = cuotasPartes;
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
	 * Gets the dentro del perfil.
	 *
	 * @return the dentro del perfil
	 */
	public String getDentroDelPerfil() {
		return dentroDelPerfil;
	}

	/**
	 * Sets the dentro del perfil.
	 *
	 * @param dentroDelPerfil
	 *            the new dentro del perfil
	 */
	public void setDentroDelPerfil(String dentroDelPerfil) {
		this.dentroDelPerfil = dentroDelPerfil;
	}

	/**
	 * Gets the disclaimer.
	 *
	 * @return the disclaimer
	 */
	public String getDisclaimer() {
		return disclaimer;
	}

	/**
	 * Sets the disclaimer.
	 *
	 * @param disclaimer
	 *            the new disclaimer
	 */
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the new cotizacion
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the retorno servicio.
	 *
	 * @return the retorno servicio
	 */
	public String getRetornoServicio() {
		return retornoServicio;
	}

	/**
	 * Sets the retorno servicio.
	 *
	 * @param retornoServicio
	 *            the new retorno servicio
	 */
	public void setRetornoServicio(String retornoServicio) {
		this.retornoServicio = retornoServicio;
	}

	/**
	 * Gets the codigo retorno.
	 *
	 * @return the codigo retorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}

	/**
	 * Sets the codigo retorno.
	 *
	 * @param codigoRetorno
	 *            the new codigo retorno
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}

	/**
	 * Gets the mensaje del servicio.
	 *
	 * @return the mensaje del servicio
	 */
	public String getMensajeDelServicio() {
		return mensajeDelServicio;
	}

	/**
	 * Sets the mensaje del servicio.
	 *
	 * @param mensajeDelServicio
	 *            the new mensaje del servicio
	 */
	public void setMensajeDelServicio(String mensajeDelServicio) {
		this.mensajeDelServicio = mensajeDelServicio;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

}
