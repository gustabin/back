/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */

public class FondoInEntity {

	/** Valor por defecto en llama a servicio. */
	private static String NRO_CERTIFICADO_REVERSAR_DEFAULT = "0000000000";

	/** Valor por defecto en llama a servicio. */
	private static String MONTO_REVERSAR_DEFAULT = "00000000000000";

	/** The Constant PREFIJO_COD_CLIENTE. */
	private static final String PREFIJO_COD_CLIENTE = "001";

	/** The Constant PREFIJO_COD_CANAL. */
	private static final String PREFIJO_COD_CANAL = "991";

	/** Valor por defecto en llama a servicio. */
	private static final String CERO_CUOTAS_PARTES = "00000000000000";

	/** The cliente. */
	@NotNull
	private Cliente cliente;

	/** The terminal safe. */
	private String terminalSafe = "    ";

	/** The codigo fondo. */
	@NotNull
	@Pattern(regexp = "[0-9]{3}", message = "{validation.debitoautomatico.importeLimite}")
	private String codigoFondo;

	/** The codigo cliente. */
	@NotNull
	@Pattern(regexp = "[0-9]{11}")
	private String codigoCliente;

	/** The codigo agente. */
	@NotNull
	@Pattern(regexp = "[0-9]{3}")
	private String codigoAgente = PREFIJO_COD_CLIENTE;

	/** The codigo canal. */
	@NotNull
	@Pattern(regexp = "[0-9]{3}")
	private String codigoCanal = PREFIJO_COD_CANAL;

	/** The importe bruto. */
	@NotNull
	@Pattern(regexp = "[0-9]{14}")
	private String importeBruto;

	/** The cantidad cuotas partes. */
	@NotNull
	@Pattern(regexp = "[0-9]{14}")
	private String cantidadCuotasPartes = CERO_CUOTAS_PARTES;

	/** The forma pago. */
	@NotNull
	@Pattern(regexp = "[0-9]{2}")
	private String formaPago = "02";

	/** The tipo cuenta. */
	@NotNull
	@Pattern(regexp = "[0-9]{2}")
	private String tipoCuenta;

	/** The suc cuenta. */
	@NotNull
	@Pattern(regexp = "[0-9]{3}")
	private String sucCuenta;

	/** The nro cuenta. */
	@NotNull
	@Pattern(regexp = "[0-9]{6}")
	private String nroCuenta;

	/** The impre solicitud. */
	@NotNull
	@Pattern(regexp = "N")
	private String impreSolicitud = "N";

	/** The moneda. */
	@NotNull
	@Pattern(regexp = "(0|2)")
	private String moneda;

	/** The nro certif A reversar. */
	@NotNull
	@Pattern(regexp = "0")
	private String nroCertifAReversar = NRO_CERTIFICADO_REVERSAR_DEFAULT;

	/** The monto A reversar. */
	@NotNull
	@Pattern(regexp = "0")
	private String montoAReversar = MONTO_REVERSAR_DEFAULT;

	/** The importe rescate comision. */
	@NotNull
	@Pattern(regexp = "[0-9]{14}")
	private String importeRescateComision;

	/** The importe rescate neto. */
	@NotNull
	@Pattern(regexp = "[0-9]{14}")
	private String importeRescateNeto;

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
		this.codigoCliente = PREFIJO_COD_CLIENTE + StringUtils.leftPad(codigoCliente, 8, ISBANStringUtils.ZERO_STR);
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
	 * Gets the cantidad cuotas partes.
	 *
	 * @return the cantidad cuotas partes
	 */
	public String getCantidadCuotasPartes() {
		return cantidadCuotasPartes;
	}

	/**
	 * Sets the cantidad cuotas partes.
	 *
	 * @param cantidadCuotasPartes
	 *            the new cantidad cuotas partes
	 */
	public void setCantidadCuotasPartes(String cantidadCuotasPartes) {
		this.cantidadCuotasPartes = cantidadCuotasPartes;
	}

	/**
	 * Gets the forma pago.
	 *
	 * @return the forma pago
	 */
	public String getFormaPago() {
		return formaPago;
	}

	/**
	 * Sets the forma pago.
	 *
	 * @param formaPago
	 *            the new forma pago
	 */
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
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
	 * Gets the suc cuenta.
	 *
	 * @return the suc cuenta
	 */
	public String getSucCuenta() {
		return sucCuenta;
	}

	/**
	 * Sets the suc cuenta.
	 *
	 * @param sucCuenta
	 *            the new suc cuenta
	 */
	public void setSucCuenta(String sucCuenta) {
		this.sucCuenta = sucCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
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
	 * Gets the nro certif A reversar.
	 *
	 * @return the nro certif A reversar
	 */
	public String getNroCertifAReversar() {
		return nroCertifAReversar;
	}

	/**
	 * Sets the nro certif A reversar.
	 *
	 * @param nroCertifAReversar
	 *            the new nro certif A reversar
	 */
	public void setNroCertifAReversar(String nroCertifAReversar) {
		this.nroCertifAReversar = nroCertifAReversar;
	}

	/**
	 * Gets the monto A reversar.
	 *
	 * @return the monto A reversar
	 */
	public String getMontoAReversar() {
		return montoAReversar;
	}

	/**
	 * Sets the monto A reversar.
	 *
	 * @param montoAReversar
	 *            the new monto A reversar
	 */
	public void setMontoAReversar(String montoAReversar) {
		this.montoAReversar = montoAReversar;
	}

	/**
	 * Gets the importe rescate comision.
	 *
	 * @return the importe rescate comision
	 */
	public String getImporteRescateComision() {
		return importeRescateComision;
	}

	/**
	 * Sets the importe rescate comision.
	 *
	 * @param importeRescateComision
	 *            the new importe rescate comision
	 */
	public void setImporteRescateComision(String importeRescateComision) {
		this.importeRescateComision = importeRescateComision;
	}

	/**
	 * Gets the importe rescate neto.
	 *
	 * @return the importe rescate neto
	 */
	public String getImporteRescateNeto() {
		return importeRescateNeto;
	}

	/**
	 * Sets the importe rescate neto.
	 *
	 * @param importeRescateNeto
	 *            the new importe rescate neto
	 */
	public void setImporteRescateNeto(String importeRescateNeto) {
		this.importeRescateNeto = importeRescateNeto;
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
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
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
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

}
