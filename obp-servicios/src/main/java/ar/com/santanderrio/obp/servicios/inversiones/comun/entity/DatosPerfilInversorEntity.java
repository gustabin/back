package ar.com.santanderrio.obp.servicios.inversiones.comun.entity;


import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonProperty;


public class DatosPerfilInversorEntity extends EntityBaseInversiones{
	
	/** The nup*/
	@JsonProperty("Nup")
	private String nup;
	
	/** The DaysCount*/
	@JsonProperty("DaysCount")
	private int daysCount;
	
	/** The DaysCount*/
	@JsonProperty("Segment")
	private String segment;
	
	
	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}
	
	
	public int getDaysCount() {
		return daysCount;
	}

	public void setDaysCount(int daysCount) {
		this.daysCount = daysCount;
	}
	
	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	
}
