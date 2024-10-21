package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoCuotaInView {

	private String sucursalCuenta;
	private String tipoCuenta;
	private String nroCuenta;
	private String sucursalPrestamo;
	private BigDecimal monto;
	private String nroPrestamo;
	private BigDecimal cotizacionPrestamo; // para prestamos bi monetarios

	public PagoCuotaInView() {
		super();
	}

	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String numeroCuenta) {
		this.nroCuenta = numeroCuenta;
	}

	public String getSucursalPrestamo() {
		return sucursalPrestamo;
	}

	public void setSucursalPrestamo(String sucursalPrestamo) {
		this.sucursalPrestamo = sucursalPrestamo;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getNroPrestamo() {
		return nroPrestamo;
	}

	public void setNroPrestamo(String numeroPrestamo) {
		this.nroPrestamo = numeroPrestamo;
	}

	public BigDecimal getCotizacionPrestamo() {
		return cotizacionPrestamo;
	}

	public void setCotizacionPrestamo(BigDecimal cotizacionPrestamo) {
		this.cotizacionPrestamo = cotizacionPrestamo;
	}

}
