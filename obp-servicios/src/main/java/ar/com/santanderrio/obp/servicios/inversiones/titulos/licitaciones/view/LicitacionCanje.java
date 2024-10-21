package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

public class LicitacionCanje {
	
	private String especieDestino;

	private String monedaEspecieDestino;

	private double minimo;

	private double incrementoMinimo;

	private String tipoPrecioDato;
	
	private String codigoEspecie;
	
	private short lugar;
	
	private double precio;

	public String getEspecieDestino() {
		return especieDestino;
	}

	public void setEspecieDestino(String especieDestino) {
		this.especieDestino = especieDestino;
	}

	public String getMonedaEspecieDestino() {
		return monedaEspecieDestino;
	}

	public void setMonedaEspecieDestino(String monedaEspecieDestino) {
		this.monedaEspecieDestino = monedaEspecieDestino;
	}

	public double getMinimo() {
		return minimo;
	}

	public void setMinimo(double minimo) {
		this.minimo = minimo;
	}

	public double getIncrementoMinimo() {
		return incrementoMinimo;
	}

	public void setIncrementoMinimo(double incrementoMinimo) {
		this.incrementoMinimo = incrementoMinimo;
	}

	public String getTipoPrecioDato() {
		return tipoPrecioDato;
	}

	public void setTipoPrecioDato(String tipoPrecioDato) {
		this.tipoPrecioDato = tipoPrecioDato;
	}

	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	public short getLugar() {
		return lugar;
	}

	public void setLugar(short lugar) {
		this.lugar = lugar;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
