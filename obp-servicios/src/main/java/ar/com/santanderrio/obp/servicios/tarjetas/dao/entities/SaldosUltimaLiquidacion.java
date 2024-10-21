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
 * /tarjetas/tarjeta/liquidacion/saldos/
 * 
 * @author florencia.n.martinez
 *
 */
@XmlRootElement(name = "saldos")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaldosUltimaLiquidacion {

	/** The List saldos. */
	@XmlElement(name = "saldo")
	private List<SaldoTasaUltimaLiquidacion> saldos;

	/**
	 * Gets the saldos.
	 *
	 * @return the saldos
	 */
	public List<SaldoTasaUltimaLiquidacion> getSaldos() {
		return saldos;
	}

	/**
	 * Sets the saldos.
	 *
	 * @param saldos
	 *            the saldos to set
	 */
	public void setSaldos(List<SaldoTasaUltimaLiquidacion> saldos) {
		this.saldos = saldos;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SaldosTasasUltimaLiquidacion [saldos=" + saldos + "]";
	}

}
