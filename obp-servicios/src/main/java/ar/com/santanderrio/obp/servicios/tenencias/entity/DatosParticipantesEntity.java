package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class DatosParticipantesEntity {
	
	@JsonProperty("NroCuenta")
	private String nroCuenta;
	
	@JsonProperty("Participantes")
	private List<ParticipantesEntity> participantes;

	/**
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * @param nroCuenta the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * @return the participantes
	 */
	public List<ParticipantesEntity> getParticipantes() {
		return participantes;
	}

	/**
	 * @param participantes the participantes to set
	 */
	public void setParticipantes(List<ParticipantesEntity> participantes) {
		this.participantes = participantes;
	}
	

}
