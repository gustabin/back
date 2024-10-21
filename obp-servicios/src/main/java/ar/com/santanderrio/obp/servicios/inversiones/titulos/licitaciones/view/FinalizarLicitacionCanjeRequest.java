package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

public class FinalizarLicitacionCanjeRequest {

	private String cuentaOperativa;
	private String tipoCuentaOperativa;
	private String montoADebitar;
	private String numeroOrden;
	private String moneda;
	private String descripcionEspecie;

	public String getCuentaOperativa() {
		return cuentaOperativa;
	}

	public void setCuentaOperativa(String cuentaOperativa) {
		this.cuentaOperativa = cuentaOperativa;
	}

	public String getTipoCuentaOperativa() {
		return tipoCuentaOperativa;
	}

	public void setTipoCuentaOperativa(String tipoCuentaOperativa) {
		this.tipoCuentaOperativa = tipoCuentaOperativa;
	}

	public String getMontoADebitar() {
		return montoADebitar;
	}

	public void setMontoADebitar(String montoADebitar) {
		this.montoADebitar = montoADebitar;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

}
