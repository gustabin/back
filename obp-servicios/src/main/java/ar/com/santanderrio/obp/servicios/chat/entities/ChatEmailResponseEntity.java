/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chat.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class ChatEmailResponseEntity.
 *
 * @author Federico_Puente
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatEmailResponseEntity {

    /** The interactionId. */
    @JsonProperty("interactionId")
    private String interactionId;

    /**
	 * Gets the interaction id.
	 *
	 * @return the interactionId
	 */
    public String getInteractionId() {
        return interactionId;
    }

    /**
	 * Sets the interaction id.
	 *
	 * @param interactionId
	 *            the interactionId to set
	 */
    public void setInteractionId(String interactionId) {
        this.interactionId = interactionId;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "{ ChatResponseEntity[interactionId=" + interactionId + "] }";
    }

}
