/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ParametrosOperacion.
 *
 * @author sabrina.cis
 */
public class ParametrosOperacion {

	/** The cliente. */
	private Cliente cliente;

	/** The nup tipo. */
	private String nupTipo;

	/** The nup num doc. */
	private String nupNumDoc;

	/** The cuenta destino. */
	private Cuenta cuentaDestino;

	/** The cuenta origen. */
	private Cuenta cuentaOrigen;

	/** The cotizacion. */
	private String cotizacion;

	/** The importe. */
	private String importe;

	/** The importe credito. */
	private String importeCredito;

	/** The importe debito. */
	private String importeDebito;

	/** The is dolar. */
	private Boolean isDolar;

	/** The legales. */
	private String legales;

	/**
	 * Gets the nup tipo.
	 *
	 * @return the nupTipo
	 */
	public String getNupTipo() {
		return nupTipo;
	}

	/**
	 * Sets the nup tipo.
	 *
	 * @param nupTipo
	 *            the nupTipo to set
	 */
	public void setNupTipo(String nupTipo) {
		this.nupTipo = nupTipo;
	}

	/**
	 * Gets the nup num doc.
	 *
	 * @return the nupNumDoc
	 */
	public String getNupNumDoc() {
		return nupNumDoc;
	}

	/**
	 * Sets the nup num doc.
	 *
	 * @param nupNumDoc
	 *            the nupNumDoc to set
	 */
	public void setNupNumDoc(String nupNumDoc) {
		this.nupNumDoc = nupNumDoc;
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
	 *            the cotizacion to set
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the importe credito.
	 *
	 * @return the importeCredito
	 */
	public String getImporteCredito() {
		return importeCredito;
	}

	/**
	 * Sets the importe credito.
	 *
	 * @param importeCredito
	 *            the importeCredito to set
	 */
	public void setImporteCredito(String importeCredito) {
		this.importeCredito = importeCredito;
	}

	/**
	 * Gets the importe debito.
	 *
	 * @return the importeDebito
	 */
	public String getImporteDebito() {
		return importeDebito;
	}

	/**
	 * Sets the importe debito.
	 *
	 * @param importeDebito
	 *            the importeDebito to set
	 */
	public void setImporteDebito(String importeDebito) {
		this.importeDebito = importeDebito;
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
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuentaDestino
	 */
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the cuentaDestino to set
	 */
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
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
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the checks if is dolar.
	 *
	 * @return the isDolar
	 */
	public Boolean getIsDolar() {
		return isDolar;
	}

	/**
	 * Sets the checks if is dolar.
	 *
	 * @param isDolar
	 *            the isDolar to set
	 */
	public void setIsDolar(Boolean isDolar) {
		this.isDolar = isDolar;
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

}
