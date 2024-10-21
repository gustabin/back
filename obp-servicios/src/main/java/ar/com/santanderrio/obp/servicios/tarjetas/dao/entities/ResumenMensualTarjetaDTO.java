/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenMensualTarjetaDTO.
 */
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NONE)
public class ResumenMensualTarjetaDTO {

	/** The fecha. */
	private Date fecha;

	/** The doc id. */
	@JsonProperty("docId")
	private String docId;

	/** The carpeta. */
	private String carpeta;

	/** The id. */
	private int id;

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the carpeta.
	 *
	 * @return the carpeta
	 */
	public String getCarpeta() {
		return carpeta;
	}

	/**
	 * Sets the carpeta.
	 *
	 * @param carpeta
	 *            the new carpeta
	 */
	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResumenMensualTarjetaDTO [fecha=" + fecha + ", carpeta=" + carpeta + ", id=" + id + "]";
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the doc id.
	 *
	 * @return the doc id
	 */
	public String getDocId() {
		return docId;
	}

	/**
	 * Sets the doc id.
	 *
	 * @param docId
	 *            the new doc id
	 */
	public void setDocId(String docId) {
		this.docId = docId;
	}

}
