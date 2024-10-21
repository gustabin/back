package ar.com.santanderrio.obp.servicios.solicitudes.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class BeneficioTransferiTuSueldoView {

	private String inicio;
	
	private String cuerpo;
	
	private List<CuentaMontoBeneficioView> cuentasMontos;
	
	private String legales;
	
	private MensajeFeedbackOk mensajeFeedbackOk;

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public List<CuentaMontoBeneficioView> getCuentasMontos() {
		return cuentasMontos;
	}

	public void setCuentasMontos(List<CuentaMontoBeneficioView> cuentasMontos) {
		this.cuentasMontos = cuentasMontos;
	}

	public String getLegales() {
		return legales;
	}

	public void setLegales(String legales) {
		this.legales = legales;
	}

	public MensajeFeedbackOk getMensajeFeedbackOk() {
		return mensajeFeedbackOk;
	}

	public void setMensajeFeedbackOk(MensajeFeedbackOk mensajeFeedbackOk) {
		this.mensajeFeedbackOk = mensajeFeedbackOk;
	}	
	
}
