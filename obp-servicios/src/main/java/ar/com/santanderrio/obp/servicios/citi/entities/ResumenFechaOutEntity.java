/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

/**
 * The Class ResumenFechaOutEntity.
 */
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NONE)
public class ResumenFechaOutEntity {
    
	/** The doc id. */
    @JsonProperty("docId")
    private String docId;
    
	/** The fecha. */
    @JsonProperty("fecha")
    private String fecha;

    /**
	 * Gets the doc id.
	 *
     * @return the docId
     */
    public String getDocId() {
        return docId;
    }

    /**
	 * Sets the doc id.
	 *
     * @param docId the docId to set
     */
    public void setDocId(String docId) {
        this.docId = docId;
    }

    /**
	 * Gets the fecha.
	 *
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
	 * Sets the fecha.
	 *
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
