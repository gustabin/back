package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class SolicitarFinanciacionEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class SolicitarFinanciacionEntity {

	/** The offers. */
	@JsonProperty("offers")
	private List<SolicitudFinanciacionEntity> offers;
	
	/**
	 * Gets the offers.
	 *
	 * @return the offers
	 */
	public List<SolicitudFinanciacionEntity> getOffers() {
		return offers;
	}

	/**
	 * Sets the offers.
	 *
	 * @param offers the new offers
	 */
	public void setOffers(List<SolicitudFinanciacionEntity> offers) {
		this.offers = offers;
	}

}
