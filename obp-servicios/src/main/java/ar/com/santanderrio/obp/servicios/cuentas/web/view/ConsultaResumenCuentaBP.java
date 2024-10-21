package ar.com.santanderrio.obp.servicios.cuentas.web.view;

public class ConsultaResumenCuentaBP {

	/** The numero cuenta. */
	private String numeroCuenta;

	/** The fechas. */
	private String[] fechas;

	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String[] getFechas() {
		return fechas;
	}

	public void setFechas(String[] fechas) {
		this.fechas = fechas;
	}
	
}