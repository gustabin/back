/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.dto;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class CompraVentaDolarInicio.
 *
 * @author florencia.n.martinez
 */
public class CompraVentaInicioDTO extends CompraVentaDTO {

	/** The cotizacion. */
	private Double cotizacion;

	/** The cotizacion string. */
	private String cotizacionString;

	/** The List<Cuenta> cuentas;. */
	private List<Cuenta> cuentas;

	/** The tipo operacion inicial. */
	private String tipoOperacionInicial = "compra";

	/**
	 * Instantiates a new compra venta inicio DTO.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 * @param cotizacionString
	 *            the cotizacion string
	 * @param cuentas
	 *            the cuentas
	 */
	public CompraVentaInicioDTO(Double cotizacion, String cotizacionString, List<Cuenta> cuentas) {
		super();
		this.cotizacion = cotizacion;
		this.cotizacionString = cotizacionString;
		this.cuentas = cuentas;
	}

	/**
	 * Instantiates a new compra venta inicio DTO.
	 */
	public CompraVentaInicioDTO() {
		super();
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public Double getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 */
	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the cotizacion string.
	 *
	 * @return the cotizacionString
	 */
	public String getCotizacionString() {
		return cotizacionString;
	}

	/**
	 * Sets the cotizacion string.
	 *
	 * @param cotizacionString
	 *            the cotizacionString to set
	 */
	public void setCotizacionString(String cotizacionString) {
		this.cotizacionString = cotizacionString;
	}

	/**
	 * Getter de aliasCtaOrigen.
	 * 
	 * @return the aliasCtaOrigen
	 */

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(getAliasCtaDestino());
		hcb.append(getAliasCtaOrigen());
		hcb.append(cotizacion);
		hcb.append(cotizacionString);
		hcb.append(getEsCompra());
		hcb.append(getEsVenta());
		hcb.append(getImporteDolares());
		hcb.append(getImportePesos());
		hcb.append(getMostrarNroCtaDestino());
		hcb.append(getMostrarNroCtaOrigen());
		hcb.append(getNroCuentaDestino());
		hcb.append(getNroCuentaOrigen());
		hcb.append(getSaldoCuentaDestino());
		hcb.append(getSaldoCuentaOrigen());
		hcb.append(getTieneAliasCtaDestino());
		hcb.append(getTieneAliasCtaOrigen());
		hcb.append(getTieneCtaDestino());
		hcb.append(getTieneCtaOrigen());
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
		CompraVentaInicioDTO other = (CompraVentaInicioDTO) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(getAliasCtaDestino(), other.getAliasCtaDestino());
		eb.append(getAliasCtaOrigen(), other.getAliasCtaOrigen());
		eb.append(cotizacion, other.getCotizacion());
		eb.append(cotizacionString, other.getCotizacionString());
		eb.append(getEsCompra(), other.getEsCompra());
		eb.append(getEsVenta(), other.getEsVenta());
		eb.append(getImporteDolares(), other.getImporteDolares());
		eb.append(getImportePesos(), other.getImportePesos());
		eb.append(getMostrarNroCtaDestino(), other.getMostrarNroCtaDestino());
		eb.append(getMostrarNroCtaOrigen(), other.getMostrarNroCtaOrigen());
		eb.append(getNroCuentaDestino(), other.getNroCuentaDestino());
		eb.append(getNroCuentaOrigen(), other.getNroCuentaOrigen());
		eb.append(getSaldoCuentaDestino(), other.getSaldoCuentaDestino());
		eb.append(getSaldoCuentaOrigen(), other.getSaldoCuentaOrigen());
		eb.append(getTieneAliasCtaDestino(), other.getTieneAliasCtaDestino());
		eb.append(getTieneAliasCtaOrigen(), other.getTieneAliasCtaOrigen());
		eb.append(getTieneCtaDestino(), other.getTieneCtaDestino());
		eb.append(getTieneCtaOrigen(), other.getTieneCtaOrigen());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("esCompra", super.getEsCompra()).append("esVenta", getEsVenta())
				.append("importePesos", getImportePesos()).append("importeDolares", getImporteDolares())
				.append("cotizacion", cotizacion).append("cotizacionString", cotizacionString)
				.append("aliasCtaOrigen", getAliasCtaOrigen()).append("tieneAliasCtaOrigen", getTieneAliasCtaOrigen())
				.append("nroCuentaOrigen", getNroCuentaOrigen()).append("mostrarNroCtaOrigen", getMostrarNroCtaOrigen())
				.append("saldoCuentaOrigen", getSaldoCuentaOrigen()).append("tieneCtaOrigen", getTieneCtaOrigen())
				.append("aliasCtaDestino", getAliasCtaDestino())
				.append("tieneAliasCtaDestino", getTieneAliasCtaDestino())
				.append("nroCuentaDestino", getNroCuentaDestino())
				.append("mostrarNroCtaDestino", getMostrarNroCtaDestino())
				.append("saldoCuentaDestino", getSaldoCuentaDestino()).append("tieneCtaDestino", getTieneCtaDestino())
				.append("cuentas", cuentas).toString();
	}

	/**
	 * Gets the tipo operacion inicial.
	 *
	 * @return the tipo operacion inicial
	 */
	public String getTipoOperacionInicial() {
		return tipoOperacionInicial;
	}

	/**
	 * Sets the tipo operacion inicial.
	 *
	 * @param tipoOperacionInicial
	 *            the new tipo operacion inicial
	 */
	public void setTipoOperacionInicial(String tipoOperacionInicial) {
		this.tipoOperacionInicial = tipoOperacionInicial;
	}

}