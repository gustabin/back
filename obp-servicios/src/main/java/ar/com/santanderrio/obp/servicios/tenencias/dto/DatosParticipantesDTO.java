package ar.com.santanderrio.obp.servicios.tenencias.dto;

import java.util.List;

public class DatosParticipantesDTO {
	
	private String numeroCuenta;
	
	private List<ParticipantesPLDTO> participantesDTO;

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the participantesDTO
	 */
	public List<ParticipantesPLDTO> getParticipantesDTO() {
		return participantesDTO;
	}

	/**
	 * @param participantesDTO the participantesDTO to set
	 */
	public void setParticipantesDTO(List<ParticipantesPLDTO> participantesDTO) {
		this.participantesDTO = participantesDTO;
	}

	

}
