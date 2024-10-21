package ar.com.santanderrio.obp.servicios.echeq.dto;

public class ConfirmarOperacionOutDTO {
	
	/** The mensaje feedback. */
	private String mensajeFeedback;
	
	/** The fecha comprobante. */
	private String fechaComprobante;
	
	/** The numero comprobante. */
	private String numeroComprobante;

	/** The legales. */
	private String legales;
	
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	public String getFechaComprobante() {
		return fechaComprobante;
	}

	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	public String getLegales() {
		return legales;
	}

	public void setLegales(String legales) {
		this.legales = legales;
	}

}
