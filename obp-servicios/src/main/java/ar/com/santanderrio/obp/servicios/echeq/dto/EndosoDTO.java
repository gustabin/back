package ar.com.santanderrio.obp.servicios.echeq.dto;

public class EndosoDTO {
	
	
	private String fecha;
	
	private String beneficiario;
	
	private String cuit;
	
	private Boolean esEmitidoInicial= Boolean.FALSE;

	private Boolean tachado = Boolean.FALSE;

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

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Boolean getTachado() {
		return tachado;
	}

	public void setTachado(Boolean tachado) {
		this.tachado = tachado;
	}
}
