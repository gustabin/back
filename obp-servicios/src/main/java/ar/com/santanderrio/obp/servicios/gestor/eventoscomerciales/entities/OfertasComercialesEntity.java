/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class OfertasComercialesEntity.
 *
 * @author florencia.n.martinez
 */
public class OfertasComercialesEntity {

	/** The nup. */
	@JsonProperty("nup")
	private String nup;

	/** The jsessionid. */
	@JsonProperty("jsessionId")
	private String jsessionId;

	/** The datos oferta sc. */
	@JsonProperty("datos_oferta_sc")
	private String datosOfertaSc;
	
	/** The puntos superclub. */
	@JsonProperty("puntos_superclub")
	private String puntosSuperclub;

	/** The cant ofertas. */
	@JsonProperty("cant_ofertas")
	private String cantOfertas;

	/** The ofertas. */
	@JsonProperty("ofertas")
	private List<OfertaComercialEntity> ofertas;

	/** The codigo error. */
	@JsonProperty("codigoError")
	private String codigoError;

	/** The descripcion error. */
	@JsonProperty("descripcionError")
	private String descripcionError;
	
	/** The id encuesta. */
	@JsonProperty("id_encuesta")
	private String idEncuesta;
	
	/** The cant preguntas. */
	@JsonProperty("cant_preguntas")
	private String cantPreguntas;
	
	/** The preguntas enc. */
	@JsonProperty("preguntas_enc")
	private List<PreguntaEncuestaEntity> preguntasEnc;
	
	/** The monto disponible ppp */
	@JsonProperty("monto_disponible_ppp")
	private String montoDisponiblePpp;
	
	@JsonProperty("max_cuota_ppp")
	private String maxCuotaPpp;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the jsessionid.
	 *
	 * @return the jsessionid
	 */
	public String getJsessionId() {
		return jsessionId;
	}

	/**
	 * Sets the jsessionid.
	 *
	 * @param jsessionid
	 *            the jsessionid to set
	 */
	public void setJsessionId(String jsessionid) {
		this.jsessionId = jsessionid;
	}

	/**
	 * Gets the datos oferta sc.
	 *
	 * @return the datosOfertaSc
	 */
	public String getDatosOfertaSc() {
		return datosOfertaSc;
	}

	/**
	 * Sets the datos oferta sc.
	 *
	 * @param datosOfertaSc
	 *            the datosOfertaSc to set
	 */
	public void setDatosOfertaSc(String datosOfertaSc) {
		this.datosOfertaSc = datosOfertaSc;
	}

	/**
	 * Gets the puntos superclub.
	 *
	 * @return the puntosSuperclub
	 */
	public String getPuntosSuperclub() {
		return puntosSuperclub;
	}

	/**
	 * Sets the puntos superclub.
	 *
	 * @param puntosSuperclub
	 *            the puntosSuperclub to set
	 */
	public void setPuntosSuperclub(String puntosSuperclub) {
		this.puntosSuperclub = puntosSuperclub;
	}

	/**
	 * Gets the cant ofertas.
	 *
	 * @return the cantOfertas
	 */
	public String getCantOfertas() {
		return cantOfertas;
	}

	/**
	 * Sets the cant ofertas.
	 *
	 * @param cantOfertas
	 *            the cantOfertas to set
	 */
	public void setCantOfertas(String cantOfertas) {
		this.cantOfertas = cantOfertas;
	}

	/**
	 * Gets the ofertas.
	 *
	 * @return the ofertas
	 */
	public List<OfertaComercialEntity> getOfertas() {
		return ofertas;
	}

	/**
	 * Sets the ofertas.
	 *
	 * @param ofertas
	 *            the ofertas to set
	 */
	public void setOfertas(List<OfertaComercialEntity> ofertas) {
		this.ofertas = ofertas;
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError
	 *            the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the descripcion error.
	 *
	 * @return the descripcionError
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * Sets the descripcion error.
	 *
	 * @param descripcionError
	 *            the descripcionError to set
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	/**
	 * Gets the id encuesta.
	 *
	 * @return the id encuesta
	 */
	public String getIdEncuesta() {
		return idEncuesta;
	}

	/**
	 * Sets the id encuesta.
	 *
	 * @param idEncuesta
	 *            the id encuesta to set
	 */
	public void setIdEncuesta(String idEncuesta) {
		this.idEncuesta = idEncuesta;
	}

	/**
	 * Gets the cant preguntas.
	 *
	 * @return the cantPreguntas
	 */
	public String getCantPreguntas() {
		return cantPreguntas;
	}

	/**
	 * Sets the cant preguntas.
	 *
	 * @param cantPreguntas
	 *            the cant preguntas to set
	 */
	public void setCantPreguntas(String cantPreguntas) {
		this.cantPreguntas = cantPreguntas;
	}

	/**
	 * Gets the preguntas enc.
	 *
	 * @return the preguntas enc
	 */
	public List<PreguntaEncuestaEntity> getPreguntasEnc() {
		return preguntasEnc;
	}

	/**
	 * Sets the preguntas enc.
	 *
	 * @param preguntasEnc
	 *            the preguntas enc to set
	 */
	public void setPreguntasEnc(List<PreguntaEncuestaEntity> preguntasEnc) {
		this.preguntasEnc = preguntasEnc;
	}
	
	public String getMontoDisponiblePpp() {
		return montoDisponiblePpp;
	}

	public void setMontoDisponiblePpp(String montoDisponiblePpp) {
		this.montoDisponiblePpp = montoDisponiblePpp;
	}

	public String getMaxCuotaPpp() {
		return maxCuotaPpp;
	}

	public void setMaxCuotaPpp(String maxCuotaPpp) {
		this.maxCuotaPpp = maxCuotaPpp;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantOfertas);
		hcb.append(codigoError);
		hcb.append(datosOfertaSc);
		hcb.append(descripcionError);
		hcb.append(jsessionId);
		hcb.append(nup);
		hcb.append(puntosSuperclub);
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
		OfertasComercialesEntity other = (OfertasComercialesEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantOfertas, other.getCantOfertas());
		eb.append(codigoError, other.getCodigoError());
		eb.append(datosOfertaSc, other.getDatosOfertaSc());
		eb.append(descripcionError, other.getDescripcionError());
		eb.append(jsessionId, other.getJsessionId());
		eb.append(nup, other.getNup());
		eb.append(puntosSuperclub, other.getPuntosSuperclub());
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
		return new ToStringBuilder(this).append("nup", nup).append("jsessionid", jsessionId)
				.append("datosOfertaSc", datosOfertaSc).append("puntosSuperclub", puntosSuperclub)
				.append("cantOfertas", cantOfertas).append("ofertas", ofertas).append("codigoError", codigoError)
				.append("descripcionError", descripcionError).append("idEncuesta", idEncuesta)
				.append("cantPreguntas", cantPreguntas).append("preguntasEnc", preguntasEnc).toString();
	}

}
