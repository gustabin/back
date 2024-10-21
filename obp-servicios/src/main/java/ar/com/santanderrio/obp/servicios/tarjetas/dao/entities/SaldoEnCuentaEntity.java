/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class SaldoEnCuentaEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/saldoenCuenta/
 *
 * @author sergio.e.goldentair
 */
@XmlRootElement(name = "saldoenCuenta")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaldoEnCuentaEntity {

	/** The limites unificados. */
	@XmlAttribute(name = "limitesUnificados")
	private String limitesUnificados;

	/** The fechas. */
	@XmlElement(name = "fechas")
	private FechasTarjeta fechas;

	/** The pagos. */
	@XmlElement(name = "pagos")
	private PagosTarjeta pagos;

	/** The limites. */
	@XmlElement(name = "limites")
	private LimitesTarjeta limites;

	/** The saldos. */
	@XmlElement(name = "saldos")
	private SaldosTarjeta saldos;

	/** The tasas. */
	@XmlElement(name = "tasas")
	private TasaTarjeta tasas;

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

	/**
	 * Gets the fechas.
	 *
	 * @return the fechas
	 */
	public FechasTarjeta getFechas() {
		return fechas;
	}

	/**
	 * Sets the fechas.
	 *
	 * @param fechas
	 *            the fechas to set
	 */
	public void setFechas(FechasTarjeta fechas) {
		this.fechas = fechas;
	}

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public PagosTarjeta getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the pagos to set
	 */
	public void setPagos(PagosTarjeta pagos) {
		this.pagos = pagos;
	}

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public LimitesTarjeta getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the limites to set
	 */
	public void setLimites(LimitesTarjeta limites) {
		this.limites = limites;
	}

	/**
	 * Gets the saldos.
	 *
	 * @return the saldos
	 */
	public SaldosTarjeta getSaldos() {
		return saldos;
	}

	/**
	 * Sets the saldos.
	 *
	 * @param saldos
	 *            the saldos to set
	 */
	public void setSaldos(SaldosTarjeta saldos) {
		this.saldos = saldos;
	}

	/**
	 * Gets the tasas.
	 *
	 * @return the tasas
	 */
	public TasaTarjeta getTasas() {
		return tasas;
	}

	/**
	 * Sets the tasas.
	 *
	 * @param tasas
	 *            the tasas to set
	 */
	public void setTasas(TasaTarjeta tasas) {
		this.tasas = tasas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fechas);
		hcb.append(limites);
		hcb.append(limitesUnificados);
		hcb.append(pagos);
		hcb.append(saldos);
		hcb.append(tasas);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SaldoEnCuentaEntity other = (SaldoEnCuentaEntity) obj;
		return new EqualsBuilder().append(fechas, other.fechas).append(limites, other.limites)
				.append(limitesUnificados, other.limitesUnificados).append(pagos, other.pagos)
				.append(saldos, other.saldos).append(tasas, other.tasas).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaldoEnCuentaEntity [limitesUnificados=" + limitesUnificados + ", fechas=" + fechas + ", pagos=" + pagos
				+ ", limites=" + limites + ", saldos=" + saldos + ", tasas=" + tasas + "]";
	}

}