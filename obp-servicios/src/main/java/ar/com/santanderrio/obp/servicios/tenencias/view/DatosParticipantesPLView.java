package ar.com.santanderrio.obp.servicios.tenencias.view;

import java.util.List;

public class DatosParticipantesPLView {
	
	private String numeroCuenta;
	
	private List<ParticipantesPLView> participantesView;

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
	 * @return the participantesView
	 */
	public List<ParticipantesPLView> getParticipantesView() {
		return participantesView;
	}

	/**
	 * @param participantesView the participantesView to set
	 */
	public void setParticipantesView(List<ParticipantesPLView> participantesView) {
		this.participantesView = participantesView;
	}


}
