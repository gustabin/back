/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.entities;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ResumenCitiIn.
 */
public class ResumenCitiIn {
    
    
    /** The id. */
    @JsonProperty("docId")
    private String id;

    /**
	 * Gets the id.
	 *
	 * @return the id
	 */
    public String getId() {
        return id;
    }

    /**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
    public void setId(String id) {
        this.id = id;
    }
    
}