/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class Filtros.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "tpoComp", "cuentasList"})
public class FiltroListaComprobantesOrExt {

    /** The tpo comp. */
    @XmlElement(name = "tpo-comp")
    protected String tpoComp = "17";

    /** The categorias list. */
    @XmlElementWrapper(name = "ext")
    @XmlElement(name = "Cta")
    protected List<String> cuentasList = new ArrayList<String>();

	public String getTpoComp() {
		return tpoComp;
	}

	public List<String> getCuentasList() {
		return cuentasList;
	}

	public void setTpoComp(String tpoComp) {
		this.tpoComp = tpoComp;
	}

	public void setCuentasList(List<String> cuentasList) {
		this.cuentasList = cuentasList;
	}

}
