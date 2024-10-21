package ar.com.santanderrio.obp.servicios.prestamos.contracts;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class TransactionEvent {

	@JsonProperty("topic")
    private String topic;
	@JsonProperty("message")
    private Object message;

    public TransactionEvent(String topic, Object message){
        this.topic = topic;
        this.message = message;
    }
}