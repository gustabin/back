/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class TarjetaCuotasPendientesEntity. Clase que representa el tag
 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/
 */
@XmlRootElement(name = "tarjeta")
@XmlAccessorType(XmlAccessType.FIELD)
public class TarjetaCuotasPendientesEntity {

	/** The String codigoTarjeta. */
	@XmlAttribute(name = "codigoTarjeta")
	private String codigoTarjeta;

	/** The dolares. */
	@XmlElement(name = "dolares")
	private String dolares;

	/** The pesos. */
	@XmlElement(name = "pesos")
	private String pesos;

	/** The detalle cuota pendiente list. */
	@XmlElement(name = "cuota")
	private List<DetalleCuotaPendienteEntity> detalleCuotaPendienteList;

	/**
	 * Gets the String codigoTarjeta.
	 *
	 * @return the String codigoTarjeta
	 */
	public String getCodigoTarjeta() {
		return codigoTarjeta;
	}

	/**
	 * Sets the String codigoTarjeta.
	 *
	 * @param codigoTarjeta
	 *            the new String codigoTarjeta
	 */
	public void setCodigoTarjeta(String codigoTarjeta) {
		this.codigoTarjeta = codigoTarjeta;
	}

	/**
	 * Gets the detalle cuota pendiente list.
	 *
	 * @return the detalle cuota pendiente list
	 */
	public List<DetalleCuotaPendienteEntity> getDetalleCuotaPendienteList() {
		return detalleCuotaPendienteList;
	}

	/**
	 * Sets the detalle cuota pendiente list.
	 *
	 * @param detalleCuotaPendienteList
	 *            the new detalle cuota pendiente list
	 */
	public void setDetalleCuotaPendienteList(List<DetalleCuotaPendienteEntity> detalleCuotaPendienteList) {
		this.detalleCuotaPendienteList = detalleCuotaPendienteList;
	}

	/**
	 * Gets the dolares.
	 *
	 * @return the dolares
	 */
	public String getDolares() {
		return dolares;
	}

	/**
	 * Sets the dolares.
	 *
	 * @param dolares
	 *            the new dolares
	 */
	public void setDolares(String dolares) {
		this.dolares = dolares;
	}

	/**
	 * Gets the pesos.
	 *
	 * @return the pesos
	 */
	public String getPesos() {
		return pesos;
	}

	/**
	 * Sets the pesos.
	 *
	 * @param pesos
	 *            the new pesos
	 */
	public void setPesos(String pesos) {
		this.pesos = pesos;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigoTarjeta);
		hcb.append(detalleCuotaPendienteList);
		hcb.append(dolares);
		hcb.append(pesos);

		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
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
		TarjetaCuotasPendientesEntity other = (TarjetaCuotasPendientesEntity) obj;

		EqualsBuilder eb = new EqualsBuilder();

		eb.append(codigoTarjeta, other.getCodigoTarjeta());
		eb.append(detalleCuotaPendienteList, other.getDetalleCuotaPendienteList());
		eb.append(dolares, other.getDolares());
		eb.append(pesos, other.getPesos());

		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TarjetaCuotasPendientes [codigoTarjeta=" + codigoTarjeta + ", dolares=" + dolares + ", pesos=" + pesos
				+ ", detalleCuotaPendienteList=" + detalleCuotaPendienteList + "]";
	}
}
