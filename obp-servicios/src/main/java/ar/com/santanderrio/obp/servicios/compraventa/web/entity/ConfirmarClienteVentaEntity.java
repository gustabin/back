/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Class ConfirmarClienteVenta.
 *
 * @author dante.omar.olmedo
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfirmarClienteVentaEntity extends ConfirmarClienteCompraVenta {

	/** The importeVentaDolar. */
	private String importeVentaDolar;

	/** The importeAcreditarPesos. */
	private String importeAcreditarPesos;
	
	private AutentificacionDTO desafio;
	
	/** The es compra. */
	private Boolean esCompra = Boolean.TRUE;

	/** The es venta. */
	private Boolean esVenta = Boolean.FALSE;
	
	/**
	 * Booleano que indica si el usuario puede reintentar llegado a un error.
	 */
	private boolean puedeReintentar;

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = hashBuilder();
		hcb.append(importeVentaDolar);
		hcb.append(importeAcreditarPesos);
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
		ConfirmarClienteVentaEntity other = (ConfirmarClienteVentaEntity) obj;

		EqualsBuilder eb = equalsBuilder(null, other);
		eb.append(importeVentaDolar, other.importeVentaDolar);
		eb.append(importeAcreditarPesos, other.importeAcreditarPesos);
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConfirmarClienteVenta [" + toStringCompraVenta() + ", importeVentaDolar=" + importeVentaDolar
				+ ", importeAcreditarPesos=" + importeAcreditarPesos + "]";
	}

	/**
	 * Gets the importe venta dolar.
	 *
	 * @return the importe venta dolar
	 */
	public String getImporteVentaDolar() {
		return importeVentaDolar;
	}

	/**
	 * Sets the importe venta dolar.
	 *
	 * @param importeVentaDolar
	 *            the new importe venta dolar
	 */
	public void setImporteVentaDolar(String importeVentaDolar) {
		this.importeVentaDolar = importeVentaDolar;
	}

	/**
	 * Gets the importe acreditar pesos.
	 *
	 * @return the importe acreditar pesos
	 */
	public String getImporteAcreditarPesos() {
		return importeAcreditarPesos;
	}

	/**
	 * Sets the importe acreditar pesos.
	 *
	 * @param importeAcreditarPesos
	 *            the new importe acreditar pesos
	 */
	public void setImporteAcreditarPesos(String importeAcreditarPesos) {
		this.importeAcreditarPesos = importeAcreditarPesos;
	}

	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	public Boolean getEsCompra() {
		return esCompra;
	}

	public void setEsCompra(Boolean esCompra) {
		this.esCompra = esCompra;
	}

	public Boolean getEsVenta() {
		return esVenta;
	}

	public void setEsVenta(Boolean esVenta) {
		this.esVenta = esVenta;
	}

	public boolean isPuedeReintentar() {
		return puedeReintentar;
	}

	public void setPuedeReintentar(boolean puedeReintentar) {
		this.puedeReintentar = puedeReintentar;
	}

}
