package ar.com.santanderrio.obp.servicios.solicitudes.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class PersonasVap {
	@JsonProperty("NUP")
	private String nup;
	
	@JsonProperty("TipoDocumento")
	private String tipoDocumento;
	
	@JsonProperty("NumeroDocumento")
	private String numeroDocumento;
			
	@JsonProperty("FechaNacimiento")
	private String fechaNacimiento;
	
	@JsonProperty("PermiteContinuar")
	private boolean permiteContinuar;
	
	@JsonProperty("Motivo")
	private String motivo;
	
	@JsonProperty("Riesgo")
	private String riesgo;
	
	@JsonProperty("TipoPersona")
	private String tipoPersona;
	
	@JsonProperty("FaltaEncuestaRiesgo")
	private boolean faltaEncuestaRiesgo;
	
	@JsonProperty("FechaPerfilado")
	private String fechaPerfilado;
	
	@JsonProperty("FaltaEncuestaFatca")
	private boolean faltaEncuestaFatca;
					
	@JsonProperty("IndicadorFirmante00")
	private boolean indicadorFirmante00;
									
	@JsonProperty("CalidadParticipante")
	private String calidadParticipante;
	
	public PersonasVap() {}

	public PersonasVap(String nup, String tipoDocumento, String numeroDocumento, String fechaNacimiento,
			String tipoPersona, String calidadParticipante) {
		super();
		this.nup = nup;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoPersona = tipoPersona;
		this.calidadParticipante = calidadParticipante;
	}

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public boolean isIndicadorFirmante00() {
		return indicadorFirmante00;
	}

	public void setIndicadorFirmante00(boolean indicadorFirmante00) {
		this.indicadorFirmante00 = indicadorFirmante00;
	}

	public String getCalidadParticipante() {
		return calidadParticipante;
	}

	public void setCalidadParticipante(String calidadParticipante) {
		this.calidadParticipante = calidadParticipante;
	}

	public boolean isPermiteContinuar() {
		return permiteContinuar;
	}

	public void setPermiteContinuar(boolean permiteContinuar) {
		this.permiteContinuar = permiteContinuar;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getRiesgo() {
		return riesgo;
	}

	public void setRiesgo(String riesgo) {
		this.riesgo = riesgo;
	}

	public boolean isFaltaEncuestaRiesgo() {
		return faltaEncuestaRiesgo;
	}

	public void setFaltaEncuestaRiesgo(boolean faltaEncuestaRiesgo) {
		this.faltaEncuestaRiesgo = faltaEncuestaRiesgo;
	}

	public boolean isFaltaEncuestaFatca() {
		return faltaEncuestaFatca;
	}

	public void setFaltaEncuestaFatca(boolean faltaEncuestaFatca) {
		this.faltaEncuestaFatca = faltaEncuestaFatca;
	}

	public String getFechaPerfilado() {
		return fechaPerfilado;
	}

	public void setFechaPerfilado(String fechaPerfilado) {
		this.fechaPerfilado = fechaPerfilado;
	}

}
