/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class PagosUltimaLiquidacion. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/pagos/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "pagos")
@XmlAccessorType(XmlAccessType.FIELD)
public class PagosUltimaLiquidacion {

	/** The List pagosUltimaLiquidacion. */
	@XmlElement(name = "pago")
	private List<PagoUltimaLiquidacion> pagos;

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public List<PagoUltimaLiquidacion> getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the pagos to set
	 */
	public void setPagos(List<PagoUltimaLiquidacion> pagos) {
		this.pagos = pagos;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PagosUltimaLiquidacion [pagos=" + pagos + "]";
	}

}
