
package ar.com.santanderrio.obp.generated.webservices.inversiones;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * @author sergio.e.goldentair
 */


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultaPerfilInversor", namespace = "http://schemas.datacontract.org/2004/07/ISBAN.MESAC.ServiceContracts.Parameters", propOrder = {
	    "firmaDatosDentro",
	    "firmaDatosFirmados",
	    "firmaFormato",
	    "firmaHash",
	    "parametroDatos"
	})
public class ConsultaPerfilInversorReq {
	@XmlElement(name = "Firma_datos_dentro")
    private String firmaDatosDentro;
    @XmlElement(name = "Firma_datos_firmados")
    private String firmaDatosFirmados;
    @XmlElement(name = "Firma_formato")
    private String firmaFormato;
    @XmlElement(name = "Firma_hash")
    private String firmaHash;
    @XmlElement(name = "Datos")
    private ParametroDatos parametroDatos;
    
   
    
    

    /**
     * @return the firmaDatosDentro
     */
    public String getFirmaDatosDentro() {
        return firmaDatosDentro;
    }

    /**
     * @param firmaDatosDentro
     *            the firmaDatosDentro to set
     */
    public void setFirmaDatosDentro(String firmaDatosDentro) {
        this.firmaDatosDentro = firmaDatosDentro;
    }

    /**
     * @return the firmaDatosFirmados
     */
    public String getFirmaDatosFirmados() {
        return firmaDatosFirmados;
    }

    /**
     * @param firmaDatosFirmados
     *            the firmaDatosFirmados to set
     */
    public void setFirmaDatosFirmados(String firmaDatosFirmados) {
        this.firmaDatosFirmados = firmaDatosFirmados;
    }

    /**
     * @return the firmaFormato
     */
    public String getFirmaFormato() {
        return firmaFormato;
    }

    /**
     * @param firmaFormato
     *            the firmaFormato to set
     */
    public void setFirmaFormato(String firmaFormato) {
        this.firmaFormato = firmaFormato;
    }

    /**
     * @return the firmaHash
     */
    public String getFirmaHash() {
        return firmaHash;
    }

    /**
     * @param firmaHash
     *            the firmaHash to set
     */
    public void setFirmaHash(String firmaHash) {
        this.firmaHash = firmaHash;
    }

    /**
     * @return the parametroDatos
     */
    public ParametroDatos getParametroDatos() {
        return parametroDatos;
    }

    /**
     * @param parametroDatos
     *            the parametroDatos to set
     */
    public void setParametroDatos(ParametroDatos parametroDatos) {
        this.parametroDatos = parametroDatos;
    }
}
