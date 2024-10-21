/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class RequestCNSTITCBU.
 */
public class RequestCNSTITCBU {

	/** The tipo Cuenta. */
	private String tipoCuenta;

	/** The nro Sucursal. */
	private String nroSucursal;

	/** The nro Cuenta. */
	private String nroCuenta;

	/** The cbu Destino. */
	private String cbuDestino;

	/** The canal id. */
	private String nroTarjeta;

	/** The direccion IP. */
	private String direccionIP;

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return tipo Cuenta
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
	 * Gets the nro sucursal.
	 *
	 * @return nro Sucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *            the new nro sucursal
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return nro Cuenta
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
	 * Gets the cbu destino.
	 *
	 * @return cbu Destino
	 */
	public String getCbuDestino() {
		return cbuDestino;
	}

	/**
	 * Sets the cbu destino.
	 *
	 * @param cbuDestino
	 *            the new cbu destino
	 */
	public void setCbuDestino(String cbuDestino) {
		this.cbuDestino = cbuDestino;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return nro Tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the direccion IP.
	 *
	 * @return direccion IP
	 */
	public String getDireccionIP() {
		return direccionIP;
	}

	/**
	 * Sets the direccion IP.
	 *
	 * @param direccionIP
	 *            the new direccion IP
	 */
	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder().append(tipoCuenta).append(nroSucursal).append(nroCuenta)
				.append(cbuDestino).append(nroTarjeta).append(direccionIP);
		return hcb.toHashCode();

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
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RequestCNSTITCBU other = (RequestCNSTITCBU) obj;
		EqualsBuilder eb = new EqualsBuilder().append(tipoCuenta, other.tipoCuenta)
				.append(nroSucursal, other.nroSucursal).append(nroCuenta, other.nroCuenta)
				.append(cbuDestino, other.cbuDestino).append(nroTarjeta, other.nroTarjeta)
				.append(direccionIP, other.direccionIP);

		return eb.isEquals();

	}
}
