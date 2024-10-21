package ar.com.santanderrio.obp.servicios.compraventa.entities;

public class RequestBancaPrivadaEntity {

	/** The numbole. */
	private String boleto;
	
	/** The impabon. */
	private String canTitu;
	
	/** The impcarg. */
	private String capital;
	
	/** The impcoti. */
	private String precUni;
	
	/** The tipo ord. */
	private String tipoOrd;

	public RequestBancaPrivadaEntity(String boleto, String canTitu, String capital, String precUni, String tipoOrd) {
		super();
		this.boleto = boleto;
		this.canTitu = canTitu;
		this.capital = capital;
		this.precUni = precUni;
		this.tipoOrd = tipoOrd;
	}

	public String getBoleto() {
		return boleto;
	}

	public void setBoleto(String boleto) {
		this.boleto = boleto;
	}

	public String getCanTitu() {
		return canTitu;
	}

	public void setCanTitu(String canTitu) {
		this.canTitu = canTitu;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getPrecUni() {
		return precUni;
	}

	public void setPrecUni(String precUni) {
		this.precUni = precUni;
	}

	public String getTipoOrd() {
		return tipoOrd;
	}

	public void setTipoOrd(String tipoOrd) {
		this.tipoOrd = tipoOrd;
	}
}
