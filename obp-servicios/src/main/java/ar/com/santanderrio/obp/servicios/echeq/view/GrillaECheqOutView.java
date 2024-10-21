package ar.com.santanderrio.obp.servicios.echeq.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


/**
 * The Class GrillaECheqOutView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GrillaECheqOutView {
	
	private TotalesECheqOutView totalesEcheq;
	
	private List<ECheqView> listaEcheqs;
	
	private Boolean hayMasRegistros;
	
	private String cantidadDiasDeLaConsulta;
	
	private String legal;

	public TotalesECheqOutView getTotalesEcheq() {
		return totalesEcheq;
	}

	public void setTotalesEcheq(TotalesECheqOutView totalesEcheq) {
		this.totalesEcheq = totalesEcheq;
	}

	public List<ECheqView> getListaEcheqs() {
		return listaEcheqs;
	}

	public void setListaEcheqs(List<ECheqView> listaEcheqs) {
		this.listaEcheqs = listaEcheqs;
	}

	public Boolean getHayMasRegistros() {
		return hayMasRegistros;
	}

	public void setHayMasRegistros(Boolean hayMasRegistros) {
		this.hayMasRegistros = hayMasRegistros;
	}

	public String getCantidadDiasDeLaConsulta() {
		return cantidadDiasDeLaConsulta;
	}

	public void setCantidadDiasDeLaConsulta(String cantidadDiasDeLaConsulta) {
		this.cantidadDiasDeLaConsulta = cantidadDiasDeLaConsulta;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

}
