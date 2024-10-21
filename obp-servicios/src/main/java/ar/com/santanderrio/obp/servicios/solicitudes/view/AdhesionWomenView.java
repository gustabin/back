package ar.com.santanderrio.obp.servicios.solicitudes.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include = Inclusion.NON_NULL)
public class AdhesionWomenView {

	private List<DatosTarjetaWomen> listaTarjetasAdheridas;
	
	private List<DatosTarjetaWomen> listaTarjetasParaAdherir;
	
	private Boolean todasLasTarjetasAdheridas = Boolean.FALSE;
	
	private String mensajeWomen1;
	
	private String mensajeWomen2;
	
	private String mensajeLimiteTarjeta;
	
	private String errorServicio;
	
	private String mensajeConfiguracion;
	
	private String mensajeConfirmacionInformativo;
	
	private String mensajeConfirmacionStack;
	
	private String legalConfirmacion;
	
	private String mensajeFeedbackOK;
	
	private String mensajeFeedbackInformativo;
	
	private Boolean deeplink = Boolean.FALSE;

	
	public List<DatosTarjetaWomen> getListaTarjetasAdheridas() {
		return listaTarjetasAdheridas;
	}

	public void setListaTarjetasAdheridas(List<DatosTarjetaWomen> listaTarjetasAdheridas) {
		this.listaTarjetasAdheridas = listaTarjetasAdheridas;
	}

	public Boolean getTodasLasTarjetasAdheridas() {
		return todasLasTarjetasAdheridas;
	}

	public void setTodasLasTarjetasAdheridas(Boolean todasLasTarjetasAdheridas) {
		this.todasLasTarjetasAdheridas = todasLasTarjetasAdheridas;
	}

	public String getMensajeWomen1() {
		return mensajeWomen1;
	}

	public void setMensajeWomen1(String mensajeWomen1) {
		this.mensajeWomen1 = mensajeWomen1;
	}

	public String getMensajeWomen2() {
		return mensajeWomen2;
	}

	public void setMensajeWomen2(String mensajeWomen2) {
		this.mensajeWomen2 = mensajeWomen2;
	}

	public String getErrorServicio() {
		return errorServicio;
	}

	public void setErrorServicio(String errorServicio) {
		this.errorServicio = errorServicio;
	}

	public String getMensajeConfiguracion() {
		return mensajeConfiguracion;
	}

	public void setMensajeConfiguracion(String mensajeConfiguracion) {
		this.mensajeConfiguracion = mensajeConfiguracion;
	}

	public List<DatosTarjetaWomen> getListaTarjetasParaAdherir() {
		return listaTarjetasParaAdherir;
	}

	public void setListaTarjetasParaAdherir(List<DatosTarjetaWomen> listaTarjetasParaAdherir) {
		this.listaTarjetasParaAdherir = listaTarjetasParaAdherir;
	}

	public String getMensajeConfirmacionInformativo() {
		return mensajeConfirmacionInformativo;
	}

	public void setMensajeConfirmacionInformativo(String mensajeConfirmacionInformativo) {
		this.mensajeConfirmacionInformativo = mensajeConfirmacionInformativo;
	}

	public String getMensajeConfirmacionStack() {
		return mensajeConfirmacionStack;
	}

	public void setMensajeConfirmacionStack(String mensajeConfirmacionStack) {
		this.mensajeConfirmacionStack = mensajeConfirmacionStack;
	}

	public String getLegalConfirmacion() {
		return legalConfirmacion;
	}

	public void setLegalConfirmacion(String legalConfirmacion) {
		this.legalConfirmacion = legalConfirmacion;
	}

	public String getMensajeFeedbackOK() {
		return mensajeFeedbackOK;
	}

	public void setMensajeFeedbackOK(String mensajeFeedbackOK) {
		this.mensajeFeedbackOK = mensajeFeedbackOK;
	}

	public String getMensajeFeedbackInformativo() {
		return mensajeFeedbackInformativo;
	}

	public void setMensajeFeedbackInformativo(String mensajeFeedbackInformativo) {
		this.mensajeFeedbackInformativo = mensajeFeedbackInformativo;
	}

	public String getMensajeLimiteTarjeta() {
		return mensajeLimiteTarjeta;
	}

	public void setMensajeLimiteTarjeta(String mensajeLimiteTarjeta) {
		this.mensajeLimiteTarjeta = mensajeLimiteTarjeta;
	}

	public Boolean getDeeplink() {
		return deeplink;
	}

	public void setDeeplink(Boolean deeplink) {
		this.deeplink = deeplink;
	}
		
				
}