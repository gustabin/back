package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class RecurrenceDTO {
	
	@JsonProperty("recurrenceId")
	Integer recurrenceId;

	public Integer getRecurrenceId() {
		return recurrenceId;
	}

	public RecurrenceDTO(){}

	public RecurrenceDTO(Integer recurrenceId) {
		this.recurrenceId = recurrenceId;
	}

}
