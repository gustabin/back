/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

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
public class SuscripcionFondoInEntity {

	/** The Constant CODIGO_AGENTE_CONSTANTE. */
	private static final String CODIGO_AGENTE_CONSTANTE = "001";

	/** The Constant CODIGO_CANAL_CONSTANTE. */
	private static final String CODIGO_CANAL_CONSTANTE = "991";

	/** The Constant FORMA_DE_PAGO_CONSTANTE. */
	private static final String FORMA_DE_PAGO_CONSTANTE = "02";

	/** The Constant PORCENTAJE_COMISION_CONSTANTE. */
	private static final String PORCENTAJE_COMISION_CONSTANTE = "0000";

	/** The Constant CUATRO_ESPACIOS. */
	private static final String CUATRO_ESPACIOS = "    ";

	/** The Constant DIEZ_ESPACIOS. */
	private static final String DIEZ_ESPACIOS = "          ";

	/** The Constant CATORCE_CEROS. */
	private static final String CATORCE_CEROS = "00000000000000";

	/** The Constant N_CONSTANTE. */
	private static final String N_CONSTANTE = "N";

	/** The Constant CUATRO_CONSTANTE. */
	private static final String CUATRO_CONSTANTE = "4";

	/** The Constant CERO_CONSTANTE. */
	private static final String CERO_CONSTANTE = "0";

	/** The cliente. */
	private Cliente cliente;

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

	/** The importe bruto. */
	// N14(12,2)
	private String importeBruto;

	/** The porcentaje comision. */
	// CONSTANTE 0000 N4(2,2)
	private String porcentajeComision = PORCENTAJE_COMISION_CONSTANTE;

	/** The forma de pago. */
	// N2
	private String formaDePago = FORMA_DE_PAGO_CONSTANTE;

	/** The nombre banco. */
	// A10
	private String nombreBanco = DIEZ_ESPACIOS;

	/** The numero cheque. */
	// A10
	private String numeroCheque = DIEZ_ESPACIOS;

	/** The tipo cuenta debito. */
	// N2
	private String tipoCuentaDebito;

	/** The sucursal cuenta debito. */
	// N3
	private String sucursalCuentaDebito;

	/** The numero cuenta debito. */
	// N10
	private String numeroCuentaDebito;

	/** The impre solicitud. */
	// A1
	private String impreSolicitud = N_CONSTANTE;

	/** The cotizacion cambio. */
	// N14(12,2)
	private String cotizacionCambio = CATORCE_CEROS;

	/** The fecha rescate futuro. */
	// A10
	private String fechaRescateFuturo = DIEZ_ESPACIOS;

	/** The codigo custodia actual. */
	// N1
	private String codigoCustodiaActual = CUATRO_CONSTANTE;

	/** The dia clearing cheques. */
	// N1
	private String diaClearingCheques = CERO_CONSTANTE;

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
		return new HashCodeBuilder().append(cliente).append(terminalSafe).append(codigoFondo).append(codigoCliente)
				.append(codigoAgente).append(codigoCanal).append(importeBruto).append(porcentajeComision)
				.append(formaDePago).append(nombreBanco).append(numeroCheque).append(tipoCuentaDebito)
				.append(sucursalCuentaDebito).append(numeroCuentaDebito).append(impreSolicitud).append(cotizacionCambio)
				.append(fechaRescateFuturo).append(codigoCustodiaActual).append(diaClearingCheques).append(moneda)
				.toHashCode();
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

		SuscripcionFondoInEntity other = (SuscripcionFondoInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(cliente, other.cliente).append(terminalSafe, other.terminalSafe)
				.append(codigoFondo, other.codigoFondo).append(codigoCliente, other.codigoCliente)
				.append(codigoAgente, other.codigoAgente).append(codigoCanal, other.codigoCanal)
				.append(importeBruto, other.importeBruto).append(porcentajeComision, other.porcentajeComision)
				.append(formaDePago, other.formaDePago).append(nombreBanco, other.nombreBanco)
				.append(numeroCheque, other.numeroCheque).append(tipoCuentaDebito, other.tipoCuentaDebito)
				.append(sucursalCuentaDebito, other.sucursalCuentaDebito)
				.append(numeroCuentaDebito, other.numeroCuentaDebito).append(impreSolicitud, other.impreSolicitud)
				.append(cotizacionCambio, other.cotizacionCambio).append(fechaRescateFuturo, other.fechaRescateFuturo)
				.append(codigoCustodiaActual, other.codigoCustodiaActual)
				.append(diaClearingCheques, other.diaClearingCheques).append(moneda, other.moneda).isEquals();
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
		return new ToStringBuilder(this).append(cliente).append(terminalSafe).append(codigoFondo).append(codigoCliente)
				.append(codigoAgente).append(codigoCanal).append(importeBruto).append(porcentajeComision)
				.append(formaDePago).append(nombreBanco).append(numeroCheque).append(tipoCuentaDebito)
				.append(sucursalCuentaDebito).append(numeroCuentaDebito).append(impreSolicitud).append(cotizacionCambio)
				.append(fechaRescateFuturo).append(codigoCustodiaActual).append(diaClearingCheques).append(moneda)
				.toString();
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
	 * Gets the importe bruto.
	 *
	 * @return the importe bruto
	 */
	public String getImporteBruto() {
		return importeBruto;
	}

	/**
	 * Sets the importe bruto.
	 *
	 * @param importeBruto
	 *            the new importe bruto
	 */
	public void setImporteBruto(String importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * Gets the porcentaje comision.
	 *
	 * @return the porcentaje comision
	 */
	public String getPorcentajeComision() {
		return porcentajeComision;
	}

	/**
	 * Sets the porcentaje comision.
	 *
	 * @param porcentajeComision
	 *            the new porcentaje comision
	 */
	public void setPorcentajeComision(String porcentajeComision) {
		this.porcentajeComision = porcentajeComision;
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
	 * Gets the nombre banco.
	 *
	 * @return the nombre banco
	 */
	public String getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * Sets the nombre banco.
	 *
	 * @param nombreBanco
	 *            the new nombre banco
	 */
	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	/**
	 * Gets the numero cheque.
	 *
	 * @return the numero cheque
	 */
	public String getNumeroCheque() {
		return numeroCheque;
	}

	/**
	 * Sets the numero cheque.
	 *
	 * @param numeroCheque
	 *            the new numero cheque
	 */
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}

	/**
	 * Gets the tipo cuenta debito.
	 *
	 * @return the tipo cuenta debito
	 */
	public String getTipoCuentaDebito() {
		return tipoCuentaDebito;
	}

	/**
	 * Sets the tipo cuenta debito.
	 *
	 * @param tipoCuentaDebito
	 *            the new tipo cuenta debito
	 */
	public void setTipoCuentaDebito(String tipoCuentaDebito) {
		this.tipoCuentaDebito = tipoCuentaDebito;
	}

	/**
	 * Gets the sucursal cuenta debito.
	 *
	 * @return the sucursal cuenta debito
	 */
	public String getSucursalCuentaDebito() {
		return sucursalCuentaDebito;
	}

	/**
	 * Sets the sucursal cuenta debito.
	 *
	 * @param sucursalCuentaDebito
	 *            the new sucursal cuenta debito
	 */
	public void setSucursalCuentaDebito(String sucursalCuentaDebito) {
		this.sucursalCuentaDebito = sucursalCuentaDebito;
	}

	/**
	 * Gets the numero cuenta debito.
	 *
	 * @return the numero cuenta debito
	 */
	public String getNumeroCuentaDebito() {
		return numeroCuentaDebito;
	}

	/**
	 * Sets the numero cuenta debito.
	 *
	 * @param numeroCuentaDebito
	 *            the new numero cuenta debito
	 */
	public void setNumeroCuentaDebito(String numeroCuentaDebito) {
		this.numeroCuentaDebito = numeroCuentaDebito;
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
	 * Gets the cotizacion cambio.
	 *
	 * @return the cotizacion cambio
	 */
	public String getCotizacionCambio() {
		return cotizacionCambio;
	}

	/**
	 * Sets the cotizacion cambio.
	 *
	 * @param cotizacionCambio
	 *            the new cotizacion cambio
	 */
	public void setCotizacionCambio(String cotizacionCambio) {
		this.cotizacionCambio = cotizacionCambio;
	}

	/**
	 * Gets the fecha rescate futuro.
	 *
	 * @return the fecha rescate futuro
	 */
	public String getFechaRescateFuturo() {
		return fechaRescateFuturo;
	}

	/**
	 * Sets the fecha rescate futuro.
	 *
	 * @param fechaRescateFuturo
	 *            the new fecha rescate futuro
	 */
	public void setFechaRescateFuturo(String fechaRescateFuturo) {
		this.fechaRescateFuturo = fechaRescateFuturo;
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
	 * Gets the dia clearing cheques.
	 *
	 * @return the dia clearing cheques
	 */
	public String getDiaClearingCheques() {
		return diaClearingCheques;
	}

	/**
	 * Sets the dia clearing cheques.
	 *
	 * @param diaClearingCheques
	 *            the new dia clearing cheques
	 */
	public void setDiaClearingCheques(String diaClearingCheques) {
		this.diaClearingCheques = diaClearingCheques;
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
	 * Gets the diez espacios.
	 *
	 * @return the diez espacios
	 */
	public static String getDiezEspacios() {
		return DIEZ_ESPACIOS;
	}

	/**
	 * Gets the doce espacios.
	 *
	 * @return the doce espacios
	 */
	public static String getDoceEspacios() {
		return CATORCE_CEROS;
	}

	/**
	 * Gets the n constante.
	 *
	 * @return the n constante
	 */
	public static String getnConstante() {
		return N_CONSTANTE;
	}

	/**
	 * Gets the cuatro constante.
	 *
	 * @return the cuatro constante
	 */
	public static String getCuatroConstante() {
		return CUATRO_CONSTANTE;
	}

	/**
	 * Gets the cero constante.
	 *
	 * @return the cero constante
	 */
	public static String getCeroConstante() {
		return CERO_CONSTANTE;
	}

}
