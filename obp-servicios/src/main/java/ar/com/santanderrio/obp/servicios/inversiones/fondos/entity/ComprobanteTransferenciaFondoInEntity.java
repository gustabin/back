/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ComprobanteTransferenciaFondoInEntity.
 */
public class ComprobanteTransferenciaFondoInEntity {

	/** The Constant CUATRO_ESPACIOS. */
	private static final String CUATRO_ESPACIOS = "    ";

	/** The Constant PREFIJO_COD_AGENTE. */
	private static final String PREFIJO_COD_AGENTE = "001";

	/** the Constant PREFIJO_COD_CANAL. */
	private static final String PREFIJO_COD_CANAL = "991";

	/** The Constant PREFIJO_PORCENTAJE_COMISION. */
	private static final String PREFIJO_PORCENTAJE_COMISION = "0000";

	/** The Constant PREFIJO_IMPRE_SOLICITUD. */
	private static final String PREFIJO_IMPRE_SOLICITUD = "N";

	/** The Constant PREFIJO_CODIGO_CUSTODIA. */
	private static final String PREFIJO_CODIGO_CUSTODIA = "4";

	/** The Constant PREFIJO_TIPO_CUENTA. */
	private static final String PREFIJO_TIPO_CUENTA = "00";

	/** The Constant PREFIJO_SUCURSAL. */
	private static final String PREFIJO_SUCURSAL = "000";

	/** The Constant DIEZ_CEROS. */
	private static final String DIEZ_CEROS = "0000000000";

	/** The Constant DIEZ_ESPACIOS. */
	private static final String DIEZ_ESPACIOS = "          ";

	/** The Constant CATORCE_CEROS. */
	private static final String CATORCE_CEROS = "00000000000000";

	/** The cliente. */
	private Cliente cliente;

	/** The terminal safe. */
	// A4
	private String terminalSafe = CUATRO_ESPACIOS;

	/** The codigo fondo. */
	private String codigoFondo;

	/** The codigo cliente. */
	private String codigoCliente;

	/** The codigo agente. */
	private String codigoAgente = PREFIJO_COD_AGENTE;

	/** The codigo canal. */
	private String codigoCanal = PREFIJO_COD_CANAL;

	/** The importe neto. */
	private String importeNeto;

	/** The porcentaje comis. */
	private String porcentajeComis = PREFIJO_PORCENTAJE_COMISION;

	/** The impre solicitud. */
	private String impreSolicitud = PREFIJO_IMPRE_SOLICITUD;

	/** The codigo custodia actual. */
	private String codigoCustodiaActual = PREFIJO_CODIGO_CUSTODIA;

	/** The codigo custodia anterior. */
	private String codigoCustodiaAnterior = PREFIJO_CODIGO_CUSTODIA;

	/** The codigo fondo dest. */
	private String codigoFondoDest;

	/** The porcentaje comis D. */
	private String porcentajeComisD = PREFIJO_PORCENTAJE_COMISION;

	/** The tipo cuenta. */
	private String tipoCuenta = PREFIJO_TIPO_CUENTA;

	/** The sucursal cuenta. */
	private String sucursalCuenta = PREFIJO_SUCURSAL;

	/** The numero cuenta. */
	private String numeroCuenta = DIEZ_CEROS;

	/** The numero certificado revisar. */
	private String numeroCertificadoReservar = DIEZ_ESPACIOS;

	/** The cant cuotas partes. */
	private String cantCuotasPartes = CATORCE_CEROS;

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
	 * Gets the porcentaje comis.
	 *
	 * @return the porcentaje comis
	 */
	public String getPorcentajeComis() {
		return porcentajeComis;
	}

	/**
	 * Sets the porcentaje comis.
	 *
	 * @param porcentajeComis
	 *            the new porcentaje comis
	 */
	public void setPorcentajeComis(String porcentajeComis) {
		this.porcentajeComis = porcentajeComis;
	}

	/**
	 * Gets the impre solicitud.
	 *
	 * @return the impre solicitud
	 */
	public String getImpreSolicitud() {
		return impreSolicitud;
	}

	/**
	 * Sets the impre solicitud.
	 *
	 * @param impreSolicitud
	 *            the new impre solicitud
	 */
	public void setImpreSolicitud(String impreSolicitud) {
		this.impreSolicitud = impreSolicitud;
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
	 * Gets the porcentaje comis D.
	 *
	 * @return the porcentaje comis D
	 */
	public String getPorcentajeComisD() {
		return porcentajeComisD;
	}

	/**
	 * Sets the porcentaje comis D.
	 *
	 * @param porcentajeComisD
	 *            the new porcentaje comis D
	 */
	public void setPorcentajeComisD(String porcentajeComisD) {
		this.porcentajeComisD = porcentajeComisD;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the numero certificado reservar.
	 *
	 * @return the numero certificado reservar
	 */
	public String getNumeroCertificadoReservar() {
		return numeroCertificadoReservar;
	}

	/**
	 * Sets the numero certificado reservar.
	 *
	 * @param numeroCertificadoReservar
	 *            the new numero certificado reservar
	 */
	public void setNumeroCertificadoReservar(String numeroCertificadoReservar) {
		this.numeroCertificadoReservar = numeroCertificadoReservar;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cliente).append(terminalSafe).append(codigoFondo).append(codigoCliente)
				.append(codigoAgente).append(codigoCanal).append(importeNeto).append(porcentajeComis)
				.append(impreSolicitud).append(codigoCustodiaActual).append(codigoCustodiaAnterior)
				.append(codigoFondoDest).append(porcentajeComisD).append(tipoCuenta).append(sucursalCuenta)
				.append(numeroCuenta).append(numeroCertificadoReservar).append(cantCuotasPartes).toHashCode();
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

		FondoInEntity other = (FondoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("cliente", cliente).append("terminalSafe", terminalSafe)
				.append("codigoFondo", codigoFondo).append("codigoCliente", codigoCliente)
				.append("codigoAgente", codigoAgente).append("codigoCanal", codigoCanal)
				.append("importeNeto", importeNeto).append("porcentajeComis", porcentajeComis)
				.append("impreSolicitud", impreSolicitud).append("codigoCustodiaActual", codigoCustodiaActual)
				.append("codigoCustodiaAnterior", codigoCustodiaAnterior).append("codigoFondoDest", codigoFondoDest)
				.append("porcentajeComisD", porcentajeComisD).append("tipoCuenta", tipoCuenta)
				.append("sucursalCuenta", sucursalCuenta).append("sucursalCuenta", sucursalCuenta)
				.append("numeroCuenta", numeroCuenta).append("numeroCertificadoRevisar", numeroCertificadoReservar)
				.append("cantCuotasPartes", cantCuotasPartes).toString();
	}

}
