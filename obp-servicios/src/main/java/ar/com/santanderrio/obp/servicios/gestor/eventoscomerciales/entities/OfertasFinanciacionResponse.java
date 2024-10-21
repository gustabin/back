package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class OfertasFinanciacionResponse.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfertasFinanciacionResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The errors. */
	@JsonProperty("errors")
	private List<OfertasFinanciacionErrorEntity> errors;

	/** The offers. */
	@JsonProperty("offers")
	private List<OfertaFinanciacionEntity> offers;

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<OfertasFinanciacionErrorEntity> getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors the new errors
	 */
	public void setErrors(List<OfertasFinanciacionErrorEntity> errors) {
		this.errors = errors;
	}

	/**
	 * Gets the offers.
	 *
	 * @return the offers
	 */
	public List<OfertaFinanciacionEntity> getOffers() {
		return offers;
	}

	/**
	 * Sets the offers.
	 *
	 * @param offers the new offers
	 */
	public void setOffers(List<OfertaFinanciacionEntity> offers) {
		this.offers = offers;
	}

}
