package ar.com.santanderrio.obp.servicios.echeq.view;

public class EndosoView {

	
	private String beneficiario;
	
	private String fecha;
	
	private String cuit;
	
	private Boolean esEmitidoInicial= Boolean.FALSE;

	public String getBbeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Boolean getEsEmitidoInicial() {
		return esEmitidoInicial;
	}

	public void setEsEmitidoInicial(Boolean esEmitidoInicial) {
		this.esEmitidoInicial = esEmitidoInicial;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

}
