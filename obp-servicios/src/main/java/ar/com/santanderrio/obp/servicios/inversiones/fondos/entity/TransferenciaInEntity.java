/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */

public class TransferenciaInEntity {

	/** The cliente. */
	@NotNull
	private Cliente cliente;

	/** The terminal safe. */
	private String terminalSafe;

	/** The codigo fondo. */
	@NotNull
	@Size(min = 1, max = 3)
	private String codigoFondo;

	/** The codigo cliente. */
	@NotNull
	@Size(min = 1, max = 11)
	private String codigoCliente;

	/** The codigo agente. */
	@NotNull
	@Size(min = 1, max = 3)
	private String codigoAgente;

	/** The codigo canal. */
	@NotNull
	@Size(min = 1, max = 3)
	private String codigoCanal;

	/** The importe neto. */
	@NotNull
	@Size(min = 1, max = 14)
	@Pattern(regexp = "[0-9]*")
	private String importeNeto;

	/** The porcent comision. */
	@NotNull
	@Size(min = 1, max = 4)
	@Pattern(regexp = "[0-9]*")
	private String porcentComision;

	/** The impr solicitud. */
	@NotNull
	@Size(min = 1, max = 1)
	private String imprSolicitud;

	/** The codigo custodia actual. */
	@NotNull
	@Size(min = 1, max = 1)
	private String codigoCustodiaActual;

	/** The codigo custodia anterior. */
	@NotNull
	@Size(min = 1, max = 1)
	private String codigoCustodiaAnterior;

	/** The codigo fondo dest. */
	@NotNull
	@Size(min = 1, max = 3)
	private String codigoFondoDest;

	/** The porcent comision D. */
	@NotNull
	@Size(min = 1, max = 4)
	private String porcentComisionD;

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
				.append(porcentComisionD).toHashCode();
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

		TransferenciaInEntity other = (TransferenciaInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder().append(terminalSafe, other.terminalSafe)
				.append(codigoFondo, other.codigoFondo).append(codigoCliente, other.codigoCliente)
				.append(codigoAgente, other.codigoAgente).append(codigoCanal, other.codigoCanal)
				.append(importeNeto, other.importeNeto).append(porcentComision, other.porcentComision)
				.append(imprSolicitud, other.imprSolicitud).append(codigoCustodiaActual, other.codigoCustodiaActual)
				.append(codigoCustodiaAnterior, other.codigoCustodiaAnterior)
				.append(codigoFondoDest, other.codigoFondoDest).append(porcentComisionD, other.porcentComisionD);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("terminalSafe", terminalSafe).append("codigoFondo", codigoFondo)
				.append("codigoCliente", codigoCliente).append("codigoAgente", codigoAgente)
				.append("codigoCanal", codigoCanal).append("importeNeto", importeNeto)
				.append("porcentComision", porcentComision).append("imprSolicitud", imprSolicitud)
				.append("codigoCustodiaActual", codigoCustodiaActual)
				.append("codigoCustodiaAnterior", codigoCustodiaAnterior).append("codigoFondoDest", codigoFondoDest)
				.append("porcentComisionD", porcentComisionD).toString();
	}

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
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
