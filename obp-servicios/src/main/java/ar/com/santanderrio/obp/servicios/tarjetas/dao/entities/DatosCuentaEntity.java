/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class DatosCuentaEntity.
 */
@XmlRootElement(name = "datosCuenta")
@XmlAccessorType(XmlAccessType.FIELD)
public class DatosCuentaEntity {

	/** The limite unificado. */
	@XmlAttribute(name = "limitesUnificados")
	private String limitesUnificados;

	/** The totales. */
	@XmlElement(name = "totales")
	private TotalesEntity totales;

	/** The limites. */
	@XmlElement(name = "limites")
	private LimitesTarjetaLT limites;

	/** The fecha cierre. */
	@XmlElement(name = "fechaCierre")
	private String fechaCierre;

	/** The fecha vencimiento. */
	@XmlElement(name = "fechaVto")
	private String fechaVencimiento;

	/**
	 * Gets the fecha cierre.
	 *
	 * @return the fecha cierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Sets the fecha cierre.
	 *
	 * @param fechaCierre
	 *            the new fecha cierre
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento
	 *            the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the totales.
	 *
	 * @return the totales
	 */
	public TotalesEntity getTotales() {
		return totales;
	}

	/**
	 * Sets the totales.
	 *
	 * @param totales
	 *            the new totales
	 */
	public void setTotales(TotalesEntity totales) {
		this.totales = totales;
	}

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public LimitesTarjetaLT getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the limites to set
	 */
	public void setLimites(LimitesTarjetaLT limites) {
		this.limites = limites;
	}

	/**
	 * Gets the limites unificados.
	 *
	 * @return the limitesUnificados
	 */
	public String getLimitesUnificados() {
		return limitesUnificados;
	}

	/**
	 * Sets the limites unificados.
	 *
	 * @param limitesUnificados
	 *            the limitesUnificados to set
	 */
	public void setLimitesUnificados(String limitesUnificados) {
		this.limitesUnificados = limitesUnificados;
	}
	
	@Override
	public String toString() {
		return "DatosCuentaEntity [limitesUnificados=" + limitesUnificados + ", fechaCierre=" + fechaCierre + ", fechaVencimiento=" + fechaVencimiento
				+ ", totales=" + totales + ", limites=" + limites + "]";
	}

}
