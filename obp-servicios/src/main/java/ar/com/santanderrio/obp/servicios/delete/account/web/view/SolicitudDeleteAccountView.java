package ar.com.santanderrio.obp.servicios.delete.account.web.view;

public class SolicitudDeleteAccountView {

	private Boolean simulate;
	private Boolean cajaAhorro;
	private String cuenta;

	public Boolean getCajaAhorro() {
		return cajaAhorro;
	}

	public void setCajaAhorro(Boolean cajaAhorro) {
		this.cajaAhorro = cajaAhorro;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Boolean getSimulate() {
		return simulate;
	}

	public void setSimulate(Boolean simulate) {
		this.simulate = simulate;
	}
}
