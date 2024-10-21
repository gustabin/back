/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class CuentaPrestamoDebitoAdherido.
 */
public class CuentaPrestamoDebitoAdherido extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numero. */
	private String numero;

	/** The nro sucursal. */
	private String nroSucursal;

	/** The tipo. */
	private String tipo;

	/** The monto A pagar. */
	private String montoAPagar;

	/** The fecha inicio. */
	private String fechaInicio;

	/** The impconce. */
	private String impconce;
	
	/** The cft. */
	private BigDecimal cft;
	
	/** The cftsimp. */
	private BigDecimal cftsimp;
	
	/** The unidad exposicion. */
	private String unidadExposicion;
	
	/** The cotizacionUnidadExp. */
	private BigDecimal cotizacionUnidadExp;
	
	/** The fechaCotizacion. */
	private String fechaCotizacion;
	
	/** The impSolVisualizar. */
	private BigDecimal impSolAVisualizar;

	/** The numeroPropuesta. */
	private String numeroPropuesta;

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the nro sucursal.
	 *
	 * @return the nro sucursal
	 */
	public String getNroSucursal() {
		return nroSucursal;
	}

	/**
	 * Sets the nro sucursal.
	 *
	 * @param nroSucursal
	 *            the new nro sucursal
	 */
	public void setNroSucursal(String nroSucursal) {
		this.nroSucursal = nroSucursal;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the monto A pagar.
	 *
	 * @return the monto A pagar
	 */
	public String getMontoAPagar() {
		return montoAPagar;
	}

	/**
	 * Sets the monto A pagar.
	 *
	 * @param montoAPagar
	 *            the new monto A pagar
	 */
	public void setMontoAPagar(String montoAPagar) {
		this.montoAPagar = montoAPagar;
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio
	 *            the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Gets the impconce.
	 *
	 * @return the impconce
	 */
	public String getImpconce() {
		return impconce;
	}

	/**
	 * Sets the impconce.
	 *
	 * @param impconce
	 *            the impconce to set
	 */
	public void setImpconce(String impconce) {
		this.impconce = impconce;
	}

	/**
	 * Gets the cft.
	 *
	 * @return the cft
	 */
	public BigDecimal getCft() {
		return cft;
	}
	
	/**
	 * Sets the cft.
	 *
	 * @param cft
	 *            the cft to set
	 */
	public void setCft(BigDecimal cft) {
		this.cft = cft;
	}

	/**
	 * Gets the cftsimp.
	 *
	 * @return the cftsimp
	 */
	public BigDecimal getCftsimp() {
		return cftsimp;
	}

	/**
	 * Sets the cftsimp.
	 *
	 * @param cftsimp
	 *            the cftsimp to set
	 */
	public void setCftsimp(BigDecimal cftsimp) {
		this.cftsimp = cftsimp;
	}

	/**
	 * Gets the unidad exposicion.
	 *
	 * @return the unidad exposicion
	 */
	public String getUnidadExposicion() {
		return unidadExposicion;
	}

	/**
	 * Sets the unidad exposicion.
	 *
	 * @param unidadExposicion
	 *            the unidad exposicion to set
	 */
	public void setUnidadExposicion(String unidadExposicion) {
		this.unidadExposicion = unidadExposicion;
	}

	/**
	 * Gets the cotizacion unidad exp.
	 *
	 * @return the cotizacion unidad exp
	 */
	public BigDecimal getCotizacionUnidadExp() {
		return cotizacionUnidadExp;
	}

	/**
	 * Sets the cotizacion unidad exp.
	 *
	 * @param cotizacionUnidadExp
	 *            the cotizacion unidad exp to set
	 */
	public void setCotizacionUnidadExp(BigDecimal cotizacionUnidadExp) {
		this.cotizacionUnidadExp = cotizacionUnidadExp;
	}

	/**
	 * Gets the fecha cotizacion.
	 *
	 * @return the fecha cotizacion
	 */
	public String getFechaCotizacion() {
		return fechaCotizacion;
	}

	/**
	 * Sets the fecha cotizacion.
	 *
	 * @param fechaCotizacion
	 *            the fecha cotizacion to set
	 */
	public void setFechaCotizacion(String fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}

	/**
	 * Gets the imp sol a visualizar.
	 *
	 * @return the imp sol a visualizar
	 */
	public BigDecimal getImpSolAVisualizar() {
		return impSolAVisualizar;
	}

	/**
	 * Sets the imp sol a visualizar.
	 *
	 * @param impSolAVisualizar the impSolAVisualizar to set
	 */
	public void setImpSolAVisualizar(BigDecimal impSolAVisualizar) {
		this.impSolAVisualizar = impSolAVisualizar;
	}

	public String getNumeroPropuesta() {
		return numeroPropuesta;
	}

	public void setNumeroPropuesta(String numeroPropuesta) {
		this.numeroPropuesta = numeroPropuesta;
	}

	public String parseNumeroPropuesta() {
		return numeroPropuesta;
	};
	
	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fechaInicio);
		hcb.append(impconce);
		hcb.append(montoAPagar);
		hcb.append(nroSucursal);
		hcb.append(numero);
		hcb.append(tipo);
		hcb.append(cft);
		hcb.append(cftsimp);
		hcb.append(unidadExposicion);
		hcb.append(cotizacionUnidadExp);
		hcb.append(fechaCotizacion);
		hcb.append(impSolAVisualizar);
		hcb.append(numeroPropuesta);
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
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CuentaPrestamoDebitoAdherido other = (CuentaPrestamoDebitoAdherido) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(fechaInicio, other.getFechaInicio());
		eb.append(impconce, other.getImpconce());
		eb.append(montoAPagar, other.getMontoAPagar());
		eb.append(nroSucursal, other.getNroSucursal());
		eb.append(numero, other.getNumero());
		eb.append(tipo, other.getTipo());
		eb.append(cft, other.getCft());
		eb.append(cftsimp, other.getCftsimp());
		eb.append(unidadExposicion, other.getUnidadExposicion());
		eb.append(cotizacionUnidadExp, other.getCotizacionUnidadExp());
		eb.append(fechaCotizacion, other.getFechaCotizacion());
		eb.append(impSolAVisualizar, other.getImpSolAVisualizar());
		eb.append(numeroPropuesta, other.getNumeroPropuesta());
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
		return new ToStringBuilder(this).append("numero", numero).append("nroSucursal", nroSucursal)
				.append("tipo", tipo).append("montoAPagar", montoAPagar).append("fechaInicio", fechaInicio)
				.append("impconce", impconce).append("cft", cft)
				.append("cftsimp", cftsimp).append("unidadExposicion", unidadExposicion)
				.append("cotizacionUnidadExp", cotizacionUnidadExp).append("fechaCotizacion", fechaCotizacion)
				.append("impSolVisualizar", impSolAVisualizar).append("numeroPropuesta", numeroPropuesta)
				.toString();
	}

}