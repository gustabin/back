/**
 * 
 */
package ar.com.santanderrio.obp.servicios.scomp.client.entities;

import ar.com.santanderrio.obp.servicios.scomp.client.entities.ComprobanteOrExtEntity;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class ComprobanteResponse.
 *
 * @author sergio.e.goldentair
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comprobanteOrExtResponse", propOrder = { "comprobante" })
public class ComprobanteOrExtResponse {

    /** The respuesta scomp. */
//	@XmlElement
//	@XmlElementWrapper(name = "respuesta")
	@XmlElement(name = "comprobante")
    protected List<ComprobanteOrExtEntity> comprobante = new ArrayList<ComprobanteOrExtEntity>();

	public List<ComprobanteOrExtEntity> getListaComprobantes() {
		return comprobante;
	}

	public void setListaComprobantes(List<ComprobanteOrExtEntity> comprobante) {
		this.comprobante = comprobante;
	}

}
