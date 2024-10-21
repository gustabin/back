package ar.com.santanderrio.obp.servicios.nuevarecarga.entity;

public class Celular {

	private String numero;
	private String alias;
	private String companiaAgenda;
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String denominacion) {
		this.alias = denominacion;
	}
	public String getCompaniaAgenda() {
		return companiaAgenda;
	}
	public void setCompaniaAgenda(String compania) {
		this.companiaAgenda = compania;
	}
}
