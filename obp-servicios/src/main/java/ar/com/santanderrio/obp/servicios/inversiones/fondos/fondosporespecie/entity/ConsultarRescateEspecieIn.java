package ar.com.santanderrio.obp.servicios.inversiones.fondos.fondosporespecie.entity;

public class ConsultarRescateEspecieIn {

	private String nup;
	private String banca;
	private String codigoFondo;
	private String canal;
	private String subcanal;

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}

	public String getCodigoFondo() {
		return codigoFondo;
	}

	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getSubcanal() {
		return subcanal;
	}

	public void setSubcanal(String subcanal) {
		this.subcanal = subcanal;
	}

}
