package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * 
 * @author marcelo.ruiz
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfirmacionOrdenResponse {

	@XmlElement(name = "CodigoResp")
    private String codigoResp;
    @XmlElement(name = "MensajeError")
    private String mensajeError;
	public String getCodigoResp() {
		return codigoResp;
	}
	public void setCodigoResp(String codigoResp) {
		this.codigoResp = codigoResp;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
    
   
}
