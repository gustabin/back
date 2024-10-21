/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.RespuestasEncuestaDTO;

/**
 * The Class RegistrarEncuestaEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class RegistrarEncuestaEntity {

	/** The nup. */
	@JsonProperty("nup")
	private String nup;
	
	/** The cod evento. */
	@JsonProperty("cod_evento")
	private String codEvento;

	/** The nombre evento. */
	@JsonProperty("nombre_evento")
	private String nombreEvento;
	
	/** The id encuesta. */
	@JsonProperty("id_encuesta")
	private String idEncuesta;

	/** The jsession id. */
	@JsonProperty("jsession_id")
	private String jsessionId;
	
	/** The respuestas enc. */
	@JsonProperty("respuestas_enc")
	private List<RespuestasEncuestaDTO> respuestasEncuesta;
	
	/**
	 * Instantiates a new registrar encuesta entity.
	 *
	 * @param nup
	 *            the nup
	 * @param eventosComercialesDTO
	 *            the eventos comerciales DTO
	 */
	public RegistrarEncuestaEntity(String nup, EventosComercialesDTO eventosComercialesDTO) {
		this.nup = nup;
		this.codEvento = "15";
		this.nombreEvento = "registrarEncuesta";
		this.idEncuesta = eventosComercialesDTO.getIdEncuesta();
		this.respuestasEncuesta = eventosComercialesDTO.getRespuestasEncuesta();
		this.jsessionId = eventosComercialesDTO.getJsessionId();
	}

	/**
     * Gets the orden pregunta.
     *
     * @return the orden pregunta
     */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the orden pregunta.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
     * Gets the orden pregunta.
     *
     * @return the orden pregunta
     */
	public String getCodEvento() {
		return codEvento;
	}

	/**
	 * Sets the orden pregunta.
	 *
	 * @param codEvento
	 *            the new cod evento
	 */
	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	/**
     * Gets the orden pregunta.
     *
     * @return the orden pregunta
     */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 * Sets the orden pregunta.
	 *
	 * @param nombreEvento
	 *            the new nombre evento
	 */
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	/**
     * Gets the orden pregunta.
     *
     * @return the orden pregunta
     */
	public String getIdEncuesta() {
		return idEncuesta;
	}

	/**
	 * Sets the orden pregunta.
	 *
	 * @param idEncuesta
	 *            the new id encuesta
	 */
	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	/**
     * Gets the orden pregunta.
     *
     * @return the orden pregunta
     */
	public List<RespuestasEncuestaDTO> getRespuestasEncuesta() {
		return respuestasEncuesta;
	}

	/**
	 * Sets the orden pregunta.
	 *
	 * @param respuestasEncuesta
	 *            the new respuestas encuesta
	 */
	public void setRespuestasEncuesta(List<RespuestasEncuestaDTO> respuestasEncuesta) {
		this.respuestasEncuesta = respuestasEncuesta;
	}

	/**
     * Gets the orden pregunta.
     *
     * @return the orden pregunta
     */
	public String getJsessionId() {
		return jsessionId;
	}
	
	/**
	 * Sets the orden pregunta.
	 *
	 * @param jsessionId
	 *            the new jsession id
	 */
	public void setJsessionId(String jsessionId) {
		this.jsessionId = jsessionId;
	}
	
	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nup);
		hcb.append(codEvento);
		hcb.append(nombreEvento);
		hcb.append(idEncuesta);
		hcb.append(respuestasEncuesta);
		hcb.append(jsessionId);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrarEncuestaEntity other = (RegistrarEncuestaEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nup, other.getNup());
		eb.append(codEvento, other.getCodEvento());
		eb.append(nombreEvento, other.getNombreEvento());
		eb.append(idEncuesta, other.getIdEncuesta());
		eb.append(respuestasEncuesta, other.getRespuestasEncuesta());
		eb.append(jsessionId, other.getJsessionId());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("nup", nup).append("codEvento", codEvento).append("nombreEvento", nombreEvento)
				.append("idEncuesta", idEncuesta).append("respuestasEncuesta", respuestasEncuesta).append("jsessionId", jsessionId).toString();
	}
}
