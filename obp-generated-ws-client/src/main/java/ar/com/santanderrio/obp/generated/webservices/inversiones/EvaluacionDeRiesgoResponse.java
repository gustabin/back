package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Mensaje;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.MensajeDisclaimerCumplimiento;

/**
 * 
 * @author marcelo.ruiz
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class EvaluacionDeRiesgoResponse {

	@XmlElement(name = "Cabecera")
    private String cabecera;
    @XmlElement(name = "Disclaimer")
    private String disclaimer;
    @XmlElement(name = "DisclaimerCumplimiento")
    private String disclaimerCumplimiento;
    @XmlElement(name = "IdEvaluacion")
    private String idEvaluacion;
    @XmlElement(name = "Pie")
    private String pie;
    @XmlElement(name = "TipoDisclaimer")
    private String tipoDisclaimer;
    
    private Mensaje mensaje;
    
    private MensajeDisclaimerCumplimiento mensajeDisclaimerCumplimiento;
    
	public String getCabecera() {
		return cabecera;
	}
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getDisclaimerCumplimiento() {
		return disclaimerCumplimiento;
	}
	public void setDisclaimerCumplimiento(String disclaimerCumplimiento) {
		this.disclaimerCumplimiento = disclaimerCumplimiento;
	}
	public String getIdEvaluacion() {
		return idEvaluacion;
	}
	public void setIdEvaluacion(String idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}
	public String getPie() {
		return pie;
	}
	public void setPie(String pie) {
		this.pie = pie;
	}
	public String getTipoDisclaimer() {
		return tipoDisclaimer;
	}
	public void setTipoDisclaimer(String tipoDisclaimer) {
		this.tipoDisclaimer = tipoDisclaimer;
	}
	public Mensaje getMensaje() {
		return mensaje;
	}
	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	public MensajeDisclaimerCumplimiento getMensajeDisclaimerCumplimiento() {
		return mensajeDisclaimerCumplimiento;
	}
	public void setMensajeDisclaimerCumplimiento(MensajeDisclaimerCumplimiento mensajeDisclaimerCumplimiento) {
		this.mensajeDisclaimerCumplimiento = mensajeDisclaimerCumplimiento;
	}  
    
}

