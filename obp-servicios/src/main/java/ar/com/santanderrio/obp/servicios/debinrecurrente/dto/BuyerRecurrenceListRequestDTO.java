package ar.com.santanderrio.obp.servicios.debinrecurrente.dto;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


@JsonSerialize(include = Inclusion.NON_NULL)
public class BuyerRecurrenceListRequestDTO {

	@JsonProperty("recurrences")
	List<RecurrenceBuyerItemDTO>recurrences;
	
	@JsonProperty("totalPages")
	Integer totalPages;

	public List<RecurrenceBuyerItemDTO> getRecurrences() {
		return recurrences;
	}

	public void setRecurrences(List<RecurrenceBuyerItemDTO> recurrences) {
		this.recurrences = recurrences;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
}
