/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class SaldosYMovimientosMonedero.
 */
public class SaldosYMovimientosMonederoView {

	/** The has error. */
	private Boolean hasError;

	/** The numero. */
	private String numero;

	/** The is titular. */
	private Boolean isTitular;

	/** The nombre adicional. */
	private String nombreAdicional;

	/** The activo. */
	private Boolean activo;

	/** The saldo. */
	private String saldo;

	/** The lineas. */
	private List<LineaView> lineas;

	/** The nro cuenta producto. */
	private String nroCuentaProducto;

	/** The id cuenta. */
	private String idCuenta;

	/**
	 * Gets the checks for error.
	 *
	 * @return the checks for error
	 */
	public Boolean getHasError() {
		return hasError;
	}

	/**
	 * Sets the checks for error.
	 *
	 * @param hasError
	 *            the new checks for error
	 */
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * Gets the lineas.
	 *
	 * @return the lineas
	 */
	public List<LineaView> getLineas() {
		return lineas;
	}

	/**
	 * Sets the lineas.
	 *
	 * @param lineas
	 *            the new lineas
	 */
	public void setLineas(List<LineaView> lineas) {
		this.lineas = lineas;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the checks if is titular.
	 *
	 * @return the checks if is titular
	 */
	public Boolean getIsTitular() {
		return isTitular;
	}

	/**
	 * Sets the checks if is titular.
	 *
	 * @param isTitular
	 *            the new checks if is titular
	 */
	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	/**
	 * Gets the nombre adicional.
	 *
	 * @return the nombre adicional
	 */
	public String getNombreAdicional() {
		return nombreAdicional;
	}

	/**
	 * Sets the nombre adicional.
	 *
	 * @param nombreAdicional
	 *            the new nombre adicional
	 */
	public void setNombreAdicional(String nombreAdicional) {
		this.nombreAdicional = nombreAdicional;
	}

	/**
	 * Gets the activo.
	 *
	 * @return the activo
	 */
	public Boolean getActivo() {
		return activo;
	}

	/**
	 * Sets the activo.
	 *
	 * @param activo
	 *            the new activo
	 */
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nro cuenta producto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the new nro cuenta producto
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the id cuenta.
	 *
	 * @return the idCuenta
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Sets the id cuenta.
	 *
	 * @param idCuenta
	 *            the idCuenta to set
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(hasError);

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
		SaldosYMovimientosMonederoView other = (SaldosYMovimientosMonederoView) obj;

		EqualsBuilder eb = new EqualsBuilder();

		eb.append(hasError, other.getHasError());
		eb.append(numero, other.getNumero());
		eb.append(isTitular, other.getIsTitular());
		eb.append(activo, other.getActivo());
		eb.append(saldo, other.getSaldo());
		eb.append(nroCuentaProducto, other.getNroCuentaProducto());

		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaldosYMovimientosMonedero [hasError=" + hasError + ", numero=" + numero + ", isTitular=" + isTitular
				+ ", activo=" + activo + ", saldo=" + saldo + ", lineas=" + lineas + ", nroCuentaProducto="
				+ nroCuentaProducto + "]";
	}

}
