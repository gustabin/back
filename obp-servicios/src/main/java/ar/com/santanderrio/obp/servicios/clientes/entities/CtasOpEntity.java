package ar.com.santanderrio.obp.servicios.clientes.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class CtasOpEntity {
	
	@JsonProperty("Id")
	private int numeroCuentaOp;
	
	@JsonProperty("Moneda")
	private String moneda;
	
	@JsonProperty("Sucursal")
	private String sucursal;
			
	@JsonProperty("Tipo")
	private String tipo;

	public int getNumeroCuentaOp() {
		return numeroCuentaOp;
	}

	public void setNumeroCuentaOp(int numeroCuentaOp) {
		this.numeroCuentaOp = numeroCuentaOp;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	

}
