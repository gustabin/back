package ar.com.santanderrio.obp.servicios.segmento.dto;

import java.util.List;

public class SubscriptionResponse {

	private List<SubscriptionResponseDTO> subscriptions;

	
	public List<SubscriptionResponseDTO> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<SubscriptionResponseDTO> subscriptions) {
		this.subscriptions = subscriptions;
	}
	
}
