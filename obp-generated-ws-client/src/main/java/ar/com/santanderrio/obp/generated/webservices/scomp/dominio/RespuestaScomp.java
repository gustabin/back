/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.scomp.dominio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class RespuestaScomp.
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "respuesta", namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaScomp", propOrder = { "comprobantes", "comprobante" })
public class RespuestaScomp {

	/** listado de comprobantes. */
	@XmlElementWrapper(name = "comprobantes")
	@XmlElement(name = "comprobante")
	private List<ComprobanteScomp> comprobantes;

	/** comprobante. */
	@XmlElement(name = "comprobante")
	private List<ComprobanteScomp> comprobante;

	/**
	 * Gets the comprobantes.
	 *
	 * @return the comprobante list
	 */
	public List<ComprobanteScomp> getComprobantes() {
		return comprobantes;
	}

	/**
	 * Sets the comprobantes.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobantes(List<ComprobanteScomp> comprobante) {
		this.comprobantes = comprobante;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public List<ComprobanteScomp> getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(List<ComprobanteScomp> comprobante) {
		this.comprobante = comprobante;
	}

	// public List<ComprobanteOrExtEntity> getComprobanteOrExtResponse() {
	// return comprobante;
	// }
	//
	// public void setComprobanteOrExtResponse(List<ComprobanteOrExtEntity>
	// comprobanteOrExtResponse) {
	// this.comprobante = comprobanteOrExtResponse;
	// }
}
