/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class ServicioAdheridoDeuda.
 *
 * @author B039636
 */
public class ServicioAdheridoDeuda implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2251613126476916223L;

	/** The fecha vencimiento. */
	private Date fechaVencimiento;

	/** The fecha debito revision. */
	private String fechaDebitoRevision;

	/** The id debito. */
	private String idDebito;

	/** The importe. */
	private BigDecimal importe;

	/** The importe original. */
	private BigDecimal importeOriginal;

	/** The cotizacion. */
	private String cotizacion;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal. */
	private String sucursal;

	/** The cuenta producto. */
	private String cuentaProducto;

	/** The tipo consulta. */
	private String tipoConsulta;

	/** The codigo estado. */
	private String codigoEstado;

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fechaVencimiento
	 */
	public Date getFechaVencimiento() {
		return fechaVencimiento == null ? null : new Date(fechaVencimiento.getTime());
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento == null ? null : new Date(fechaVencimiento.getTime());
	}

	/**
	 * Gets the fecha debito revision.
	 *
	 * @return the fechaDebitoRevision
	 */
	public String getFechaDebitoRevision() {
		return fechaDebitoRevision;
	}

	/**
	 * Sets the fecha debito revision.
	 *
	 * @param fechaDebitoRevision
	 *            the fechaDebitoRevision to set
	 */
	public void setFechaDebitoRevision(String fechaDebitoRevision) {
		this.fechaDebitoRevision = fechaDebitoRevision;
	}

	/**
	 * Gets the id debito.
	 *
	 * @return the idDebito
	 */
	public String getIdDebito() {
		return idDebito;
	}

	/**
	 * Sets the id debito.
	 *
	 * @param idDebito
	 *            the idDebito to set
	 */
	public void setIdDebito(String idDebito) {
		this.idDebito = idDebito;
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
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the importe original.
	 *
	 * @return the importeOriginal
	 */
	public BigDecimal getImporteOriginal() {
		return importeOriginal;
	}

	/**
	 * Sets the importe original.
	 *
	 * @param importeOriginal
	 *            the importeOriginal to set
	 */
	public void setImporteOriginal(BigDecimal importeOriginal) {
		this.importeOriginal = importeOriginal;
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
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	 *            the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the cuenta producto.
	 *
	 * @return the cuentaProducto
	 */
	public String getCuentaProducto() {
		return cuentaProducto;
	}

	/**
	 * Sets the cuenta producto.
	 *
	 * @param cuentaProducto
	 *            the cuentaProducto to set
	 */
	public void setCuentaProducto(String cuentaProducto) {
		this.cuentaProducto = cuentaProducto;
	}

	/**
	 * Gets the tipo consulta.
	 *
	 * @return the tipoConsulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * Sets the tipo consulta.
	 *
	 * @param tipoConsulta
	 *            the tipoConsulta to set
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * Gets the codigo estado.
	 *
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * Sets the codigo estado.
	 *
	 * @param codigoEstado
	 *            the codigoEstado to set
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

}
