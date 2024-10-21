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
 * The Class SaldosTasasUltimaLiquidacion. Clase que representa el tag
 * /tarjetas/tarjeta/liquidacion/tasas/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "tasas")
@XmlAccessorType(XmlAccessType.FIELD)
public class TasasUltimaLiquidacion {

	/** The List saldos. */
	@XmlElement(name = "tasa")
	private List<SaldoTasaUltimaLiquidacion> tasas;

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public List<SaldoTasaUltimaLiquidacion> getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the new tasas
	 */
	public void setTasas(List<SaldoTasaUltimaLiquidacion> tasas) {
		this.tasas = tasas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TasasUltimaLiquidacion [tasas=" + tasas + "]";
	}

}
