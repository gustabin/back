/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class RescateFondoInEntity.
 *
 * @author pablo.d.gargaglione
 */
public class RescateFondoInEntity {

	/** The Constant CUATRO_ESPACIOS. */
	private static final String CUATRO_ESPACIOS = "    ";

	/** The Constant CODIGO_AGENTE_CONSTANTE. */
	private static final String CODIGO_AGENTE_CONSTANTE = "001";

	/** The Constant CODIGO_CANAL_CONSTANTE. */
	private static final String CODIGO_CANAL_CONSTANTE = "991";

	/** The Constant FORMA_DE_PAGO_CONSTANTE. */
	private static final String FORMA_DE_PAGO_CONSTANTE = "02";

	/** The Constant N_CONSTANTE. */
	private static final String N_CONSTANTE = "N";

	/** Cuota partes, para rescate es 0 (14). */
	private static final String CUOTA_PARTES = "00000000000000";

	/** The terminal safe. */
	// A4
	private String terminalSafe = CUATRO_ESPACIOS;

	/** The codigo fondo. */
	// N3
	private String codigoFondo;

	/** The codigo cliente. */
	// N11
	private String codigoCliente;

	/** The codigo agente. */
	// CONSTANTE 001
	private String codigoAgente = CODIGO_AGENTE_CONSTANTE;

	/** The codigo canal. */
	// CONSTANTE 991
	private String codigoCanal = CODIGO_CANAL_CONSTANTE;

	/** The importe neto. */
	// N14(12,2)
	private String importeNeto;

	/** The cantidad cuotas. */
	// N14 (10,4)
	private String cantidadCuotas = CUOTA_PARTES;

	/** The forma de pago. */
	// N2
	private String formaDePago = FORMA_DE_PAGO_CONSTANTE;

	/** The tipoCuenta. */
	// N2
	private String tipoCuenta;

	/** The sucursal cuenta. */
	// N3
	private String sucursalCuenta;

	/** The numero cuenta. */
	// N6
	private String numeroCuenta;

	/** The impre solicitud. */
	// A1
	private String impreSolicitud = N_CONSTANTE;

	/** The moneda. */
	// 0 PESOS, 2 DOLARES N1
	private String moneda;

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
		return new HashCodeBuilder().append(terminalSafe).append(codigoFondo).append(codigoCliente).append(codigoAgente)
				.append(codigoCanal).append(formaDePago).append(importeNeto).append(cantidadCuotas).append(tipoCuenta)
				.append(sucursalCuenta).append(numeroCuenta).append(impreSolicitud).append(moneda).toHashCode();
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

		RescateFondoInEntity other = (RescateFondoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(terminalSafe, other.terminalSafe).append(codigoFondo, other.codigoFondo)
				.append(codigoCliente, other.codigoCliente).append(codigoAgente, other.codigoAgente)
				.append(codigoCanal, other.codigoCanal).append(formaDePago, other.formaDePago)
				.append(importeNeto, other.importeNeto).append(cantidadCuotas, other.cantidadCuotas)
				.append(tipoCuenta, other.tipoCuenta).append(sucursalCuenta, other.sucursalCuenta)
				.append(numeroCuenta, other.numeroCuenta).append(impreSolicitud, other.impreSolicitud)
				.append(moneda, other.moneda).isEquals();
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
		return new ToStringBuilder(this).append("terminalSafe", terminalSafe).append("codigoFondo", codigoFondo)
				.append("codigoCliente", codigoCliente).append("codigoAgente", codigoAgente)
				.append("codigoCanal", codigoCanal).append("formaDePago", formaDePago)
				.append("importeNeto", importeNeto).append("cantidadCuotas", cantidadCuotas)
				.append("tipoCuenta", tipoCuenta).append("sucursalCuenta", sucursalCuenta)
				.append("numeroCuenta", numeroCuenta).append("impreSolicitud", impreSolicitud).append("moneda", moneda)
				.toString();
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
	 * Gets the cantidad cuotas.
	 *
	 * @return the cantidad cuotas
	 */
	public String getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cantidad cuotas.
	 *
	 * @param cantidadCuotas
	 *            the new cantidad cuotas
	 */
	public void setCantidadCuotas(String cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * Gets the forma de pago.
	 *
	 * @return the forma de pago
	 */
	public String getFormaDePago() {
		return formaDePago;
	}

	/**
	 * Sets the forma de pago.
	 *
	 * @param formaDePago
	 *            the new forma de pago
	 */
	public void setFormaDePago(String formaDePago) {
		this.formaDePago = formaDePago;
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
	 * Gets the cuatro espacios.
	 *
	 * @return the cuatro espacios
	 */
	public static String getCuatroEspacios() {
		return CUATRO_ESPACIOS;
	}

	/**
	 * Gets the codigo agente constante.
	 *
	 * @return the codigo agente constante
	 */
	public static String getCodigoAgenteConstante() {
		return CODIGO_AGENTE_CONSTANTE;
	}

	/**
	 * Gets the codigo canal constante.
	 *
	 * @return the codigo canal constante
	 */
	public static String getCodigoCanalConstante() {
		return CODIGO_CANAL_CONSTANTE;
	}

	/**
	 * Gets the forma de pago constante.
	 *
	 * @return the forma de pago constante
	 */
	public static String getFormaDePagoConstante() {
		return FORMA_DE_PAGO_CONSTANTE;
	}

	/**
	 * Gets the n constante.
	 *
	 * @return the n constante
	 */
	public static String getnConstante() {
		return N_CONSTANTE;
	}

}
