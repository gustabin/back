package ar.com.santanderrio.obp.servicios.debinrecurrente.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RecurrenciaView {

	private String id;

	public Boolean pending;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getPending() { return pending; }

	public void setPending(Boolean pending) { this.pending = pending; }

}
