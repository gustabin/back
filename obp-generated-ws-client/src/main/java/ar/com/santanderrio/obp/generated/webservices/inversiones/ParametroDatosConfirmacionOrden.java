package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * @author marcelo.ruiz
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParametroDatosConfirmacionOrden", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.ServiceContracts.Parameters", propOrder = {
	    "codCanal",
	    "idEvaluacion",
	    "nroOperacion"
	})
public class ParametroDatosConfirmacionOrden {

	@XmlElement(name = "CodCanal")
	private String codCanal;
	@XmlElement(name = "IdEvaluacion")
	private String idEvaluacion;
	@XmlElement(name = "NroOperacion")
	private String nroOperacion;

	public String getCodCanal() {
		return codCanal;
	}

	public void setCodCanal(String codCanal) {
		this.codCanal = codCanal;
	}

	public String getIdEvaluacion() {
		return idEvaluacion;
	}

	public void setIdEvaluacion(String idEvaluacion) {
		this.idEvaluacion = idEvaluacion;
	}

	public String getNroOperacion() {
		return nroOperacion;
	}

	public void setNroOperacion(String nroOperacion) {
		this.nroOperacion = nroOperacion;
	}

}
