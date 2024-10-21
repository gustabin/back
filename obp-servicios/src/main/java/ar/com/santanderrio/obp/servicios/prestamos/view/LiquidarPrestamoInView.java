package ar.com.santanderrio.obp.servicios.prestamos.view;

/**
 * 
 * Representa cuando se quiere liquidar un prestamo por parte del front
 *
 */
public class LiquidarPrestamoInView {

	private String tipoPrestamo;
	
	// datos validacion
	private String tokenPrisma;
	
	private String tokenSms;

	public String getTokenPrisma() {
		return tokenPrisma;
	}

	public void setTokenPrisma(String tokenPrisma) {
		this.tokenPrisma = tokenPrisma;
	}

	public String getTipoPrestamo() {
		return tipoPrestamo;
	}

	public void setTipoPrestamo(String tipoPrestamo) {
		this.tipoPrestamo = tipoPrestamo;
	}

	public String getTokenSms() {
		return tokenSms;
	}

	public void setTokenSms(String tokenSms) {
		this.tokenSms = tokenSms;
	}
}
