/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class PagoPuntualDTO.
 */
public class PagoPuntualDTO {

	/** The codigo empresa. */
	private String codigoEmpresa;

	/** The tipo monto. */
	private String tipoMonto;

	/** The tipo seleccion. */
	private String tipoSeleccion;

	/** The numero factura. */
	private String numeroFactura;

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The sucursal. */
	private String sucursal;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The moneda. */
	private String moneda;

	/** The monto. */
	private String monto;

	/** The identificacion cliente. */
	private String identificacion;

	/** The monto id. */
	private String montoId;

	/**
	 * 
	 * Gets the codigo empresa.
	 *
	 * @return the codigo empresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * Sets the codigo empresa.
	 *
	 * @param codigoEmpresa
	 *            the new codigo empresa
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * Gets the tipo monto.
	 *
	 * @return the tipo monto
	 */
	public String getTipoMonto() {
		return tipoMonto;
	}

	/**
	 * Sets the tipo monto.
	 *
	 * @param tipoMonto
	 *            the new tipo monto
	 */
	public void setTipoMonto(String tipoMonto) {
		this.tipoMonto = tipoMonto;
	}

	/**
	 * Gets the tipo seleccion.
	 *
	 * @return the tipo seleccion
	 */
	public String getTipoSeleccion() {
		return tipoSeleccion;
	}

	/**
	 * Sets the tipo seleccion.
	 *
	 * @param tipoSeleccion
	 *            the new tipo seleccion
	 */
	public void setTipoSeleccion(String tipoSeleccion) {
		this.tipoSeleccion = tipoSeleccion;
	}

	/**
	 * Gets the numero factura.
	 *
	 * @return the numero factura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}

	/**
	 * Sets the numero factura.
	 *
	 * @param numeroFactura
	 *            the new numero factura
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
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
	 * Gets the monto.
	 *
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * Sets the monto.
	 *
	 * @param monto
	 *            the new monto
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * Gets the identificacion.
	 *
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Sets the identificacion.
	 *
	 * @param identificacion
	 *            the new identificacion
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * Gets the monto id.
	 *
	 * @return the monto id
	 */
	public String getMontoId() {
		return montoId;
	}

	/**
	 * Sets the monto id.
	 *
	 * @param montoId
	 *            the new monto id
	 */
	public void setMontoId(String montoId) {
		this.montoId = montoId;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoEmpresa);
		hcb.append(moneda);
		hcb.append(monto);
		hcb.append(tipoMonto);
		hcb.append(tipoSeleccion);
		hcb.append(numeroFactura);
		hcb.append(numeroCuenta);

		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagoPuntualDTO other = (PagoPuntualDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codigoEmpresa, other.getCodigoEmpresa());
		eb.append(moneda, other.getMoneda());
		eb.append(monto, other.tipoMonto);
		eb.append(tipoSeleccion, other.getTipoSeleccion());
		eb.append(numeroFactura, other.getNumeroFactura());
		eb.append(tipoSeleccion, other.getTipoSeleccion());
		eb.append(numeroFactura, other.getNumeroFactura());
		eb.append(numeroCuenta, other.getNumeroCuenta());

		return eb.isEquals();
	}

}
