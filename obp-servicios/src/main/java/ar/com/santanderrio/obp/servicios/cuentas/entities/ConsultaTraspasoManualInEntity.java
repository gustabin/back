/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class DetalleCuenta.
 */
public class ConsultaTraspasoManualInEntity {

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The ind debito credito CA. */
	private String indDebitoCreditoCA;

	/** The ind debito credito CC. */
	private String indDebitoCreditoCC;

	/** The importe debito credito. */
	private String importeDebitoCredito;

	/** The nio. */
	private String nio;

	/** The centro origen. */
	private String centroOrigen;

	/** The cliente. */
	private Cliente cliente;

	/** The divisa. */
	private String divisa;

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
	 * Gets the ind debito credito CA.
	 *
	 * @return the ind debito credito CA
	 */
	public String getIndDebitoCreditoCA() {
		return indDebitoCreditoCA;
	}

	/**
	 * Sets the ind debito credito CA.
	 *
	 * @param indDebitoCreditoCA
	 *            the new ind debito credito CA
	 */
	public void setIndDebitoCreditoCA(String indDebitoCreditoCA) {
		this.indDebitoCreditoCA = indDebitoCreditoCA;
	}

	/**
	 * Gets the ind debito credito CC.
	 *
	 * @return the ind debito credito CC
	 */
	public String getIndDebitoCreditoCC() {
		return indDebitoCreditoCC;
	}

	/**
	 * Sets the ind debito credito CC.
	 *
	 * @param indDebitoCreditoCC
	 *            the new ind debito credito CC
	 */
	public void setIndDebitoCreditoCC(String indDebitoCreditoCC) {
		this.indDebitoCreditoCC = indDebitoCreditoCC;
	}

	/**
	 * Gets the importe debito credito.
	 *
	 * @return the importe debito credito
	 */
	public String getImporteDebitoCredito() {
		return importeDebitoCredito;
	}

	/**
	 * Sets the importe debito credito.
	 *
	 * @param importeDebitoCredito
	 *            the new importe debito credito
	 */
	public void setImporteDebitoCredito(String importeDebitoCredito) {
		this.importeDebitoCredito = importeDebitoCredito;
	}

	/**
	 * Gets the nio.
	 *
	 * @return the nio
	 */
	public String getNio() {
		return nio;
	}

	/**
	 * Sets the nio.
	 *
	 * @param nio
	 *            the new nio
	 */
	public void setNio(String nio) {
		this.nio = nio;
	}

	/**
	 * Gets the centro origen.
	 *
	 * @return the centro origen
	 */
	public String getCentroOrigen() {
		return centroOrigen;
	}

	/**
	 * Sets the centro origen.
	 *
	 * @param centroOrigen
	 *            the new centro origen
	 */
	public void setCentroOrigen(String centroOrigen) {
		this.centroOrigen = centroOrigen;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConsultaTraspasoManualInEntity [sucursalCuenta=" + sucursalCuenta + ", nroCuenta=" + nroCuenta
				+ ", indDebitoCreditoCA=" + indDebitoCreditoCA + ", indDebitoCreditoCC=" + indDebitoCreditoCC
				+ ", importeDebitoCredito=" + importeDebitoCredito + ", nio=" + nio + ", centroOrigen=" + centroOrigen
				+ "]";
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
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

}
