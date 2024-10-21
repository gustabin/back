package ar.com.santanderrio.obp.servicios.delete.account.web.dto;

/**
 * @author A308529
 *
 */
public class GuardarInfoClienteDto {
	
	private String comentario;
	private String nup;
	private String comprobante;
	private String llamar;
	private String motivo;

	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	/**
	 * @return the llamar
	 */
	public String getLlamar() {
		return llamar;
	}
	/**
	 * @param llamar the llamar to set
	 */
	public void setLlamar(String llamar) {
		this.llamar = llamar;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}
	/**
	 * @param nup the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}
	/**
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}
	/**
	 * @param comprobante the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
	
}
