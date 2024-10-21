package ar.com.santanderrio.obp.servicios.prestamos.view;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CancelacionAnticipadaInView {

	@JsonProperty("loanBranch")
	private String sucursalPrestamo;

	@JsonProperty("loanNumber")
	private String nroPrestamo;

	@JsonProperty("currency")
	private String moneda;

	@JsonProperty("accountBranch")
	private String sucursalCuenta;

	@JsonProperty("accountNumber")
	private String nroCuenta;

	public CancelacionAnticipadaInView() {
		super();
	}

	public String getSucursalPrestamo() {
		return sucursalPrestamo;
	}

	public void setSucursalPrestamo(String sucursalPrestamo) {
		this.sucursalPrestamo = sucursalPrestamo;
	}

	public String getNroPrestamo() {
		return nroPrestamo;
	}

	public void setNroPrestamo(String nroPrestamo) {
		this.nroPrestamo = nroPrestamo;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}
}
