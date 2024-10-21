/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Objeto utilizado junto a Respuesta<T> desde el BO al Manager.
 * 
 * @author
 *
 */
public class TransferenciaDTO extends BaseFondoAbstractDTO {

	/** The terminal safe. */
	private String terminalSafe;

	/** The codigo fondo. */
	private String codigoFondo;

	/** The codigo cliente. */
	private String codigoCliente;

	/** The codigo agente. */
	private String codigoAgente;

	/** The codigo canal. */
	private String codigoCanal;

	/** The cuenta titulo. */
	String cuentaTitulo;

	/** The importe neto. */
	private String importeNeto;

	/** The porcent comision. */
	private String porcentComision;

	/** The impr solicitud. */
	private String imprSolicitud;

	/** The codigo custodia actual. */
	private String codigoCustodiaActual;

	/** The codigo custodia anterior. */
	private String codigoCustodiaAnterior;

	/** The codigo fondo dest. */
	private String codigoFondoDest;

	/** The porcent comision D. */
	private String porcentComisionD;

	/** The numero certificado. */
	private String numeroCertificado;

	/** The cant cuotas partes. */
	private String cantCuotasPartes;

	/** The tipo cta deb. */
	private String tipoCtaDeb;

	/** The sucursal cta deb. */
	private String sucursalCtaDeb;

	/** The numero cuenta deb. */
	private String numeroCuentaDeb;

	/** The mensaje suscripcion. */
	private String mensajeSuscripcion;

	/** The codigo cuenta titulo. */
	private String codigoCuentaTitulo;

	/** The terminos Y condiciones. */
	private String terminosYCondiciones;

	/** The legales. */
	private String legales;

	/** The legales informacion. */
	private String legalesInformacion;

	/** The url reglamento. */
	private String urlReglamento;

	/** The moneda. */
	private String moneda;

	/** The numero operacion. */
	private String numeroOperacion;

	/**
	 * Gets the terminal safe.
	 *
	 * @return the terminal safe
	 */
	public String getTerminalSafe() {
		return terminalSafe;
	}

	/**
	 * Sets the terminal safe.
	 *
	 * @param terminalSafe
	 *            the new terminal safe
	 */
	public void setTerminalSafe(String terminalSafe) {
		this.terminalSafe = terminalSafe;
	}

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
	 * Gets the codigo cliente.
	 *
	 * @return the codigo cliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * Sets the codigo cliente.
	 *
	 * @param codigoCliente
	 *            the new codigo cliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * Gets the codigo agente.
	 *
	 * @return the codigo agente
	 */
	public String getCodigoAgente() {
		return codigoAgente;
	}

	/**
	 * Sets the codigo agente.
	 *
	 * @param codigoAgente
	 *            the new codigo agente
	 */
	public void setCodigoAgente(String codigoAgente) {
		this.codigoAgente = codigoAgente;
	}

	/**
	 * Gets the codigo canal.
	 *
	 * @return the codigo canal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * Sets the codigo canal.
	 *
	 * @param codigoCanal
	 *            the new codigo canal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public String getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(String importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the porcent comision.
	 *
	 * @return the porcent comision
	 */
	public String getPorcentComision() {
		return porcentComision;
	}

	/**
	 * Sets the porcent comision.
	 *
	 * @param porcentComision
	 *            the new porcent comision
	 */
	public void setPorcentComision(String porcentComision) {
		this.porcentComision = porcentComision;
	}

	/**
	 * Gets the impr solicitud.
	 *
	 * @return the impr solicitud
	 */
	public String getImprSolicitud() {
		return imprSolicitud;
	}

	/**
	 * Sets the impr solicitud.
	 *
	 * @param imprSolicitud
	 *            the new impr solicitud
	 */
	public void setImprSolicitud(String imprSolicitud) {
		this.imprSolicitud = imprSolicitud;
	}

	/**
	 * Gets the codigo custodia actual.
	 *
	 * @return the codigo custodia actual
	 */
	public String getCodigoCustodiaActual() {
		return codigoCustodiaActual;
	}

	/**
	 * Sets the codigo custodia actual.
	 *
	 * @param codigoCustodiaActual
	 *            the new codigo custodia actual
	 */
	public void setCodigoCustodiaActual(String codigoCustodiaActual) {
		this.codigoCustodiaActual = codigoCustodiaActual;
	}

	/**
	 * Gets the codigo custodia anterior.
	 *
	 * @return the codigo custodia anterior
	 */
	public String getCodigoCustodiaAnterior() {
		return codigoCustodiaAnterior;
	}

	/**
	 * Sets the codigo custodia anterior.
	 *
	 * @param codigoCustodiaAnterior
	 *            the new codigo custodia anterior
	 */
	public void setCodigoCustodiaAnterior(String codigoCustodiaAnterior) {
		this.codigoCustodiaAnterior = codigoCustodiaAnterior;
	}

	/**
	 * Gets the codigo fondo dest.
	 *
	 * @return the codigo fondo dest
	 */
	public String getCodigoFondoDest() {
		return codigoFondoDest;
	}

	/**
	 * Sets the codigo fondo dest.
	 *
	 * @param codigoFondoDest
	 *            the new codigo fondo dest
	 */
	public void setCodigoFondoDest(String codigoFondoDest) {
		this.codigoFondoDest = codigoFondoDest;
	}

	/**
	 * Gets the porcent comision D.
	 *
	 * @return the porcent comision D
	 */
	public String getPorcentComisionD() {
		return porcentComisionD;
	}

	/**
	 * Sets the porcent comision D.
	 *
	 * @param porcentComisionD
	 *            the new porcent comision D
	 */
	public void setPorcentComisionD(String porcentComisionD) {
		this.porcentComisionD = porcentComisionD;
	}

	/**
	 * Gets the codigo cuenta titulo.
	 *
	 * @return the codigoCuentaTitulo
	 */
	public String getCodigoCuentaTitulo() {
		return codigoCuentaTitulo;
	}

	/**
	 * Sets the codigo cuenta titulo.
	 *
	 * @param codigoCuentaTitulo
	 *            the codigoCuentaTitulo to set
	 */
	public void setCodigoCuentaTitulo(String codigoCuentaTitulo) {
		this.codigoCuentaTitulo = codigoCuentaTitulo;
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
	 * Gets the cant cuotas partes.
	 *
	 * @return the cant cuotas partes
	 */
	public String getCantCuotasPartes() {
		return cantCuotasPartes;
	}

	/**
	 * Sets the cant cuotas partes.
	 *
	 * @param cantCuotasPartes
	 *            the new cant cuotas partes
	 */
	public void setCantCuotasPartes(String cantCuotasPartes) {
		this.cantCuotasPartes = cantCuotasPartes;
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
	 * Gets the numero cuenta deb.
	 *
	 * @return the numero cuenta deb
	 */
	public String getNumeroCuentaDeb() {
		return numeroCuentaDeb;
	}

	/**
	 * Sets the numero cuenta deb.
	 *
	 * @param numeroCuentaDeb
	 *            the new numero cuenta deb
	 */
	public void setNumeroCuentaDeb(String numeroCuentaDeb) {
		this.numeroCuentaDeb = numeroCuentaDeb;
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
	 * Gets the numero operacion.
	 *
	 * @return the numero operacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the new numero operacion
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(terminalSafe).append(codigoFondo).append(codigoCliente).append(codigoAgente)
				.append(codigoCanal).append(importeNeto).append(porcentComision).append(imprSolicitud)
				.append(codigoCustodiaActual).append(codigoCustodiaAnterior).append(codigoFondoDest)
				.append(porcentComisionD).append(cantCuotasPartes).append(cuentaTitulo).append(tipoCtaDeb)
				.append(numeroCertificado).append(sucursalCtaDeb).append(numeroCuentaDeb).append(moneda)
				.append(getFechaHora()).append(numeroOperacion).toHashCode();
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

		TransferenciaDTO other = (TransferenciaDTO) obj;
		EqualsBuilder eb = new EqualsBuilder().append(terminalSafe, other.getTerminalSafe())
				.append(codigoFondo, other.getCodigoFondo()).append(codigoCliente, other.getCodigoCliente())
				.append(codigoAgente, other.getCodigoAgente()).append(codigoCanal, other.getCodigoCanal())
				.append(importeNeto, other.getImporteNeto()).append(porcentComision, other.getPorcentComision())
				.append(imprSolicitud, other.getImprSolicitud())
				.append(codigoCustodiaActual, other.getCodigoCustodiaActual())
				.append(codigoCustodiaAnterior, other.getCodigoCustodiaAnterior())
				.append(codigoFondoDest, other.getCodigoFondoDest())
				.append(porcentComisionD, other.getPorcentComisionD()).append(moneda, other.moneda)
				.append(getFechaHora(), other.getFechaHora()).append(cuentaTitulo, other.getCuentaTitulo())
				.append(numeroOperacion, other.getNumeroOperacion());
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
		return new ToStringBuilder(this).append("terminalSafe", getTerminalSafe())
				.append("codigoFondo", getCodigoFondo()).append("codigoCliente", getCodigoCliente())
				.append("codigoAgente", getCodigoAgente()).append("codigoCanal", getCodigoCanal())
				.append("importeNeto", getImporteNeto()).append("porcentComision", getPorcentComision())
				.append("imprSolicitud", getImprSolicitud()).append("codigoCustodiaActual", getCodigoCustodiaActual())
				.append("codigoCustodiaAnterior", getCodigoCustodiaAnterior())
				.append("codigoFondoDest", getCodigoFondoDest()).append("porcentComisionD", getPorcentComisionD())
				.append("cuentaTitulo", getCuentaTitulo()).append("fechaHora", getFechaHora())
				.append("moneda", getMoneda()).append("numeroOperacion", getNumeroOperacion()).toString();
	}

	/**
	 * Gets the terminos Y condiciones.
	 *
	 * @return the terminosYCondiciones
	 */
	public String getTerminosYCondiciones() {
		return terminosYCondiciones;
	}

	/**
	 * Sets the terminos Y condiciones.
	 *
	 * @param terminosYCondiciones
	 *            the terminosYCondiciones to set
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
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the url reglamento.
	 *
	 * @return the urlReglamento
	 */
	public String getUrlReglamento() {
		return urlReglamento;
	}

	/**
	 * Sets the url reglamento.
	 *
	 * @param urlReglamento
	 *            the urlReglamento to set
	 */
	public void setUrlReglamento(String urlReglamento) {
		this.urlReglamento = urlReglamento;
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
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the legales informacion.
	 *
	 * @return the legalesInformacion
	 */
	public String getLegalesInformacion() {
		return legalesInformacion;
	}

	/**
	 * Sets the legales informacion.
	 *
	 * @param legalesInformacion
	 *            the legalesInformacion to set
	 */
	public void setLegalesInformacion(String legalesInformacion) {
		this.legalesInformacion = legalesInformacion;
	}

}
