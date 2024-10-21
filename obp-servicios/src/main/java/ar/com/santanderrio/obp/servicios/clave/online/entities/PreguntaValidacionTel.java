package ar.com.santanderrio.obp.servicios.clave.online.entities;

import java.util.List;

/**
 * The Class PreguntaValidacionTel.
 */
public class PreguntaValidacionTel extends MetodoAutenticacion {

	/** The pregunta. */
	private String pregunta;

	/** The id pregunta. */
	private String idPregunta;

	/** The indice. */
	private Integer indice;

	/** The cantidad respuestas. */
	private Integer cantidadRespuestas;

	/** The respuestas. */
	private List<RespuestaAutenticacion> respuestas;

	/** The opcion correcta. */
	private String opcionCorrecta;

	/** The opcion correcta. */
	private int ciclo;

	/** The opcion correcta. */
	private String idSesion;

	/** The opcion correcta. */
	private boolean opcionNinguna;

	/** The opcion tipoAuthValsgTel. */
	private String tipoAuthValsgTel;

	/** The opcion tipoAuthValsgTel. */
	private String numeroValsgTel;

	/** The checkWhatsappHabilitado. */
	private Boolean checkWhatsappHabilitado;
	
	/**
	 * @return the numeroValsgTel
	 */
	public String getNumeroValsgTel() {
		return numeroValsgTel;
	}

	/**
	 * @param numeroValsgTel the numeroValsgTel to set
	 */
	public void setNumeroValsgTel(String numeroValsgTel) {
		this.numeroValsgTel = numeroValsgTel;
	}

	/**
	 * @return the ciclo
	 */
	public int getCiclo() {
		return ciclo;
	}

	/**
	 * @param ciclo the ciclo to set
	 */
	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * @param idSesion the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * @param pregunta the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * @return the idPregunta
	 */
	public String getIdPregunta() {
		return idPregunta;
	}

	/**
	 * @param idPregunta the idPregunta to set
	 */
	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}

	/**
	 * @return the indice
	 */
	public Integer getIndice() {
		return indice;
	}

	/**
	 * @param indice the indice to set
	 */
	public void setIndice(Integer indice) {
		this.indice = indice;
	}

	/**
	 * @return the cantidadRespuestas
	 */
	public Integer getCantidadRespuestas() {
		return cantidadRespuestas;
	}

	/**
	 * @param cantidadRespuestas the cantidadRespuestas to set
	 */
	public void setCantidadRespuestas(Integer cantidadRespuestas) {
		this.cantidadRespuestas = cantidadRespuestas;
	}

	/**
	 * @return the respuestas
	 */
	public List<RespuestaAutenticacion> getRespuestas() {
		return respuestas;
	}

	/**
	 * @param respuestas the respuestas to set
	 */
	public void setRespuestas(List<RespuestaAutenticacion> respuestas) {
		this.respuestas = respuestas;
	}

	/**
	 * @return the opcionCorrecta
	 */
	public String getOpcionCorrecta() {
		return opcionCorrecta;
	}

	/**
	 * @param opcionCorrecta the opcionCorrecta to set
	 */
	public void setOpcionCorrecta(String opcionCorrecta) {
		this.opcionCorrecta = opcionCorrecta;
	}

	/**
	 * @return the opcionNinguna
	 */
	public boolean isOpcionNinguna() {
		return opcionNinguna;
	}

	/**
	 * @param opcionNinguna the opcionNinguna to set
	 */
	public void setOpcionNinguna(boolean opcionNinguna) {
		this.opcionNinguna = opcionNinguna;
	}

	/**
	 * @return the tipoAuthValsgTel
	 */
	public String getTipoAuthValsgTel() {
		return tipoAuthValsgTel;
	}

	/**
	 * @param tipoAuthValsgTel the tipoAuthValsgTel to set
	 */
	public void setTipoAuthValsgTel(String tipoAuthValsgTel) {
		this.tipoAuthValsgTel = tipoAuthValsgTel;
	}

	/**
	 * @return the checkWhatsappHabilitado
	 */
	public Boolean getCheckWhatsappHabilitado() {
		return checkWhatsappHabilitado;
	}

	/**
	 * @param checkWhatsappHabilitado
	 *  the checkWhatsappHabilitado to set
	 */
	public void setCheckWhatsappHabilitado(Boolean checkWhatsappHabilitado) {
		this.checkWhatsappHabilitado = checkWhatsappHabilitado;
	}
	
}
