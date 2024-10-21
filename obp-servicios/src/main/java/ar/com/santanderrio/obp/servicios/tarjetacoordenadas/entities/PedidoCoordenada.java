/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * Encapsula los datos necesarios para solicitar las coordenadas.
 */
public class PedidoCoordenada implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8123601017872430754L;

	/** Ip de la maquina, en 12 digitos. */
	private String ip;

	/** Importe de la operacion a realizar, opcional. */
	private BigDecimal importe;

	/** The operacion. */
	private TarjetaCoordenadaOperacionEnum operacion;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The referencia. */
	private String referencia;

	/**
	 * Instantiates a new pedido coordenada.
	 */
	public PedidoCoordenada() {
	}

	/**
	 * Instantiates a new pedido coordenada.
	 *
	 * @param operacion
	 *            the operacion
	 */
	public PedidoCoordenada(TarjetaCoordenadaOperacionEnum operacion) {
		this.operacion = operacion;
	}

	/**
	 * Instantiates a new pedido coordenada.
	 *
	 * @param operacion
	 *            the operacion
	 * @param ip
	 *            the ip
	 */
	public PedidoCoordenada(TarjetaCoordenadaOperacionEnum operacion, String ip) {
		this.operacion = operacion;
		this.ip = ip;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the operacion.
	 *
	 * @return the operacion
	 */
	public TarjetaCoordenadaOperacionEnum getOperacion() {
		return operacion;
	}

	/**
	 * Sets the operacion.
	 *
	 * @param operacion
	 *            the new operacion
	 */
	public void setOperacion(TarjetaCoordenadaOperacionEnum operacion) {
		this.operacion = operacion;
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
	 * Gets the referencia.
	 *
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Sets the referencia.
	 *
	 * @param referencia
	 *            the new referencia
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((operacion == null) ? 0 : operacion.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PedidoCoordenada other = (PedidoCoordenada) obj;
		return new EqualsBuilder().append(ip, other.ip).append(operacion, other.operacion).isEquals();

	}

}
