package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EvaluacionDeRiesgo", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.ServiceContracts.Parameters")
public class EvaluacionDeRiesgoReq {

	@XmlElement(name = "Firma_datos_dentro")
    private String firmaDatosDentro;
    @XmlElement(name = "Firma_datos_firmados")
    private String firmaDatosFirmados;
    @XmlElement(name = "Firma_formato")
    private String firmaFormato;
    @XmlElement(name = "Firma_hash")
    private String firmaHash;
    @XmlElement(name = "Datos")
    private DatosEvaluacionRiesgo parametroDatos;
	public String getFirmaDatosDentro() {
		return firmaDatosDentro;
	}
	public void setFirmaDatosDentro(String firmaDatosDentro) {
		this.firmaDatosDentro = firmaDatosDentro;
	}
	public String getFirmaDatosFirmados() {
		return firmaDatosFirmados;
	}
	public void setFirmaDatosFirmados(String firmaDatosFirmados) {
		this.firmaDatosFirmados = firmaDatosFirmados;
	}
	public String getFirmaFormato() {
		return firmaFormato;
	}
	public void setFirmaFormato(String firmaFormato) {
		this.firmaFormato = firmaFormato;
	}
	public String getFirmaHash() {
		return firmaHash;
	}
	public void setFirmaHash(String firmaHash) {
		this.firmaHash = firmaHash;
	}
	public DatosEvaluacionRiesgo getParametroDatos() {
		return parametroDatos;
	}
	public void setParametroDatos(DatosEvaluacionRiesgo parametroDatos) {
		this.parametroDatos = parametroDatos;
	}
    
    
}
