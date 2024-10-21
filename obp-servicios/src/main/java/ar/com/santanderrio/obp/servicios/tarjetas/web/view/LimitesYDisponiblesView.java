/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;

/**
 * The Class LimitesYDisponiblesView.
 *
 * @author florencia.n.martinez
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class LimitesYDisponiblesView implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The marca. */
	private String marca;

	/** The nro tarjeta. */
	private String nroTarjetaConFormato;

	/** The nroCuentaProducto. */
	private String nroCuentaProducto;

	/** The String saldoDisponibleCompras. */
	private String saldoDisponibleCompras;

	/** The String limiteCompraPesos. */
	private String limiteCompraPesos;

	/** The String limiteCompraDolares. */
	private String limiteCompraDolares;

	/** The String saldoDisponibleCuotas. */
	private String saldoDisponibleCuotas;

	/** The String limiteCuotasPesos. */
	private String limiteCuotasPesos;

	/** The String limiteCuotasDolares. */
	private String limiteCuotasDolares;

	/** The String adelantoEfectivoPesos. */
	private String adelantoEfectivoPesos;

	/** The String limiteAdelantoEfectivoPesos. */
	private String limiteAdelantoEfectivoPesos;

	/** The String limiteAdelantoEfectivoDolares. */
	private String limiteAdelantoEfectivoDolares;

	/** The String codigoError. */
	private String codigoError;

	/** The String mensajeError. */
	private String mensajeError;

	/** The legales. */
	private String legales;

	/** The Boolean hasLimiteUnificado. */
	private Boolean hasLimiteUnificado = Boolean.FALSE;

	/** The is mostrarSaldoEnCuotas. */
	private Boolean mostrarSaldoEnCuotas = Boolean.TRUE;

	/** The is mostrarSaldoEnCompras. */
	private Boolean mostrarSaldoEnCompras = Boolean.TRUE;

	/** The Boolean isLimiteCompraPesos. */
	private Boolean isLimiteCompraPesos = Boolean.TRUE;

	/** The Boolean isLimiteCompraDolares. */
	private Boolean isLimiteCompraDolares = Boolean.FALSE;

	/** The Boolean isLimiteCuotasPesos. */
	private Boolean isLimiteCuotasPesos = Boolean.TRUE;

	/** The Boolean isLimiteCuotasDolares. */
	private Boolean isLimiteCuotasDolares = Boolean.FALSE;

	/** The Boolean isLimiteAdelantoPesos. */
	private Boolean isLimiteAdelantoPesos = Boolean.TRUE;

	/** The Boolean isLimiteAdelantoDolares. */
	private Boolean isLimiteAdelantoDolares = Boolean.FALSE;

	/** The Boolean hasError. */
	private Boolean hasError = Boolean.FALSE;

	/**
	 * Instantiates a new limites Y disponibles view.
	 *
	 * @param dto
	 *            the dto
	 */
	public LimitesYDisponiblesView(LimitesYDisponiblesDTO dto) {
		this.setAdelantoEfectivoPesos(dto.getAdelantoEfectivoPesos());
		this.setCodigoError(dto.getCodigoError());
		this.setHasError(dto.getHasError());
		this.setHasLimiteUnificado(dto.getHasLimiteUnificado());
		this.setIsLimiteAdelantoDolares(dto.getIsLimiteAdelantoDolares());
		this.setIsLimiteAdelantoPesos(dto.getIsLimiteAdelantoPesos());
		this.setIsLimiteCompraDolares(dto.getIsLimiteCompraDolares());
		this.setIsLimiteCompraPesos(dto.getIsLimiteCompraPesos());
		this.setIsLimiteCuotasDolares(dto.getIsLimiteCuotasDolares());
		this.setIsLimiteCuotasPesos(dto.getIsLimiteCuotasPesos());
		this.setLimiteAdelantoEfectivoDolares(dto.getLimiteAdelantoEfectivoDolares());
		this.setLimiteAdelantoEfectivoPesos(dto.getLimiteAdelantoEfectivoPesos());
		this.setLimiteCompraDolares(dto.getLimiteCompraDolares());
		this.setLimiteCompraPesos(dto.getLimiteCompraPesos());
		this.setIsLimiteCuotasDolares(dto.getIsLimiteCuotasDolares());
		this.setLimiteCuotasDolares(dto.getLimiteCuotasDolares());
		this.setLimiteCuotasPesos(dto.getLimiteCuotasPesos());
		this.setMarca(dto.getMarca());
		this.setMensajeError(dto.getMensajeError());
		this.setMostrarSaldoEnCompras(dto.getMostrarSaldoEnCompras());
		this.setMostrarSaldoEnCuotas(dto.getMostrarSaldoEnCuotas());
		this.setNroCuentaProducto(dto.getNroCuentaProducto());
		this.setNroTarjetaConFormato(dto.getNroTarjetaConFormato());
		this.setSaldoDisponibleCompras(dto.getsaldoDisponibleCompras());
		this.setSaldoDisponibleCuotas(dto.getSaldoDisponibleCuotas());
		this.setLegales(dto.getLegales());
	}

	/**
	 * Instantiates a new limites Y disponibles view.
	 */
	public LimitesYDisponiblesView() {
		super();
	}

	/**
	 * Gets the marca.
	 *
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Sets the marca.
	 *
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the nro tarjeta con formato.
	 *
	 * @return the nro tarjeta con formato
	 */
	public String getNroTarjetaConFormato() {
		return nroTarjetaConFormato;
	}

	/**
	 * Sets the nro tarjeta con formato.
	 *
	 * @param nroTarjetaConFormato
	 *            the new nro tarjeta con formato
	 */
	public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
		this.nroTarjetaConFormato = nroTarjetaConFormato;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nro cuenta producto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the new nro cuenta producto
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the saldo disponible compras.
	 *
	 * @return the saldo disponible compras
	 */
	public String getSaldoDisponibleCompras() {
		return saldoDisponibleCompras;
	}

	/**
	 * Sets the saldo disponible compras.
	 *
	 * @param saldoDisponibleCompras
	 *            the new saldo disponible compras
	 */
	public void setSaldoDisponibleCompras(String saldoDisponibleCompras) {
		this.saldoDisponibleCompras = saldoDisponibleCompras;
	}

	/**
	 * Gets the limite compra pesos.
	 *
	 * @return the limite compra pesos
	 */
	public String getLimiteCompraPesos() {
		return limiteCompraPesos;
	}

	/**
	 * Sets the limite compra pesos.
	 *
	 * @param limiteCompraPesos
	 *            the new limite compra pesos
	 */
	public void setLimiteCompraPesos(String limiteCompraPesos) {
		this.limiteCompraPesos = limiteCompraPesos;
	}

	/**
	 * Gets the limite compra dolares.
	 *
	 * @return the limite compra dolares
	 */
	public String getLimiteCompraDolares() {
		return limiteCompraDolares;
	}

	/**
	 * Sets the limite compra dolares.
	 *
	 * @param limiteCompraDolares
	 *            the new limite compra dolares
	 */
	public void setLimiteCompraDolares(String limiteCompraDolares) {
		this.limiteCompraDolares = limiteCompraDolares;
	}

	/**
	 * Gets the saldo disponible cuotas.
	 *
	 * @return the saldo disponible cuotas
	 */
	public String getSaldoDisponibleCuotas() {
		return saldoDisponibleCuotas;
	}

	/**
	 * Sets the saldo disponible cuotas.
	 *
	 * @param saldoDisponibleCuotas
	 *            the new saldo disponible cuotas
	 */
	public void setSaldoDisponibleCuotas(String saldoDisponibleCuotas) {
		this.saldoDisponibleCuotas = saldoDisponibleCuotas;
	}

	/**
	 * Gets the limite cuotas pesos.
	 *
	 * @return the limite cuotas pesos
	 */
	public String getLimiteCuotasPesos() {
		return limiteCuotasPesos;
	}

	/**
	 * Sets the limite cuotas pesos.
	 *
	 * @param limiteCuotasPesos
	 *            the new limite cuotas pesos
	 */
	public void setLimiteCuotasPesos(String limiteCuotasPesos) {
		this.limiteCuotasPesos = limiteCuotasPesos;
	}

	/**
	 * Gets the adelanto efectivo pesos.
	 *
	 * @return the adelanto efectivo pesos
	 */
	public String getAdelantoEfectivoPesos() {
		return adelantoEfectivoPesos;
	}

	/**
	 * Sets the adelanto efectivo pesos.
	 *
	 * @param adelantoEfectivoPesos
	 *            the new adelanto efectivo pesos
	 */
	public void setAdelantoEfectivoPesos(String adelantoEfectivoPesos) {
		this.adelantoEfectivoPesos = adelantoEfectivoPesos;
	}

	/**
	 * Gets the limite adelanto efectivo pesos.
	 *
	 * @return the limite adelanto efectivo pesos
	 */
	public String getLimiteAdelantoEfectivoPesos() {
		return limiteAdelantoEfectivoPesos;
	}

	/**
	 * Sets the limite adelanto efectivo pesos.
	 *
	 * @param limiteAdelantoEfectivoPesos
	 *            the new limite adelanto efectivo pesos
	 */
	public void setLimiteAdelantoEfectivoPesos(String limiteAdelantoEfectivoPesos) {
		this.limiteAdelantoEfectivoPesos = limiteAdelantoEfectivoPesos;
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigo error
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError
	 *            the new codigo error
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensaje error
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the new mensaje error
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Gets the checks for limite unificado.
	 *
	 * @return the checks for limite unificado
	 */
	public Boolean getHasLimiteUnificado() {
		return hasLimiteUnificado;
	}

	/**
	 * Sets the checks for limite unificado.
	 *
	 * @param hasLimiteUnificado
	 *            the new checks for limite unificado
	 */
	public void setHasLimiteUnificado(Boolean hasLimiteUnificado) {
		this.hasLimiteUnificado = hasLimiteUnificado;
	}

	/**
	 * Gets the mostrar saldo en cuotas.
	 *
	 * @return the mostrar saldo en cuotas
	 */
	public Boolean getMostrarSaldoEnCuotas() {
		return mostrarSaldoEnCuotas;
	}

	/**
	 * Sets the mostrar saldo en cuotas.
	 *
	 * @param mostrarSaldoEnCuotas
	 *            the new mostrar saldo en cuotas
	 */
	public void setMostrarSaldoEnCuotas(Boolean mostrarSaldoEnCuotas) {
		this.mostrarSaldoEnCuotas = mostrarSaldoEnCuotas;
	}

	/**
	 * Gets the mostrar saldo en compras.
	 *
	 * @return the mostrar saldo en compras
	 */
	public Boolean getMostrarSaldoEnCompras() {
		return mostrarSaldoEnCompras;
	}

	/**
	 * Sets the mostrar saldo en compras.
	 *
	 * @param mostrarSaldoEnCompras
	 *            the new mostrar saldo en compras
	 */
	public void setMostrarSaldoEnCompras(Boolean mostrarSaldoEnCompras) {
		this.mostrarSaldoEnCompras = mostrarSaldoEnCompras;
	}

	/**
	 * Gets the checks if is limite compra pesos.
	 *
	 * @return the checks if is limite compra pesos
	 */
	public Boolean getIsLimiteCompraPesos() {
		return isLimiteCompraPesos;
	}

	/**
	 * Sets the checks if is limite compra pesos.
	 *
	 * @param isLimiteCompraPesos
	 *            the new checks if is limite compra pesos
	 */
	public void setIsLimiteCompraPesos(Boolean isLimiteCompraPesos) {
		this.isLimiteCompraPesos = isLimiteCompraPesos;
	}

	/**
	 * Gets the checks if is limite compra dolares.
	 *
	 * @return the checks if is limite compra dolares
	 */
	public Boolean getIsLimiteCompraDolares() {
		return isLimiteCompraDolares;
	}

	/**
	 * Sets the checks if is limite compra dolares.
	 *
	 * @param isLimiteCompraDolares
	 *            the new checks if is limite compra dolares
	 */
	public void setIsLimiteCompraDolares(Boolean isLimiteCompraDolares) {
		this.isLimiteCompraDolares = isLimiteCompraDolares;
	}

	/**
	 * Gets the checks for error.
	 *
	 * @return the checks for error
	 */
	public Boolean getHasError() {
		return hasError;
	}

	/**
	 * Sets the checks for error.
	 *
	 * @param hasError
	 *            the new checks for error
	 */
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * Gets the limite cuotas dolares.
	 *
	 * @return the limite cuotas dolares
	 */
	public String getLimiteCuotasDolares() {
		return limiteCuotasDolares;
	}

	/**
	 * Sets the limite cuotas dolares.
	 *
	 * @param limiteCuotasDolares
	 *            the new limite cuotas dolares
	 */
	public void setLimiteCuotasDolares(String limiteCuotasDolares) {
		this.limiteCuotasDolares = limiteCuotasDolares;
	}

	/**
	 * Gets the limite adelanto efectivo dolares.
	 *
	 * @return the limite adelanto efectivo dolares
	 */
	public String getLimiteAdelantoEfectivoDolares() {
		return limiteAdelantoEfectivoDolares;
	}

	/**
	 * Sets the limite adelanto efectivo dolares.
	 *
	 * @param limiteAdelantoEfectivoDolares
	 *            the new limite adelanto efectivo dolares
	 */
	public void setLimiteAdelantoEfectivoDolares(String limiteAdelantoEfectivoDolares) {
		this.limiteAdelantoEfectivoDolares = limiteAdelantoEfectivoDolares;
	}

	/**
	 * Gets the checks if is limite cuotas pesos.
	 *
	 * @return the checks if is limite cuotas pesos
	 */
	public Boolean getIsLimiteCuotasPesos() {
		return isLimiteCuotasPesos;
	}

	/**
	 * Sets the checks if is limite cuotas pesos.
	 *
	 * @param isLimiteCuotasPesos
	 *            the new checks if is limite cuotas pesos
	 */
	public void setIsLimiteCuotasPesos(Boolean isLimiteCuotasPesos) {
		this.isLimiteCuotasPesos = isLimiteCuotasPesos;
	}

	/**
	 * Gets the checks if is limite cuotas dolares.
	 *
	 * @return the checks if is limite cuotas dolares
	 */
	public Boolean getIsLimiteCuotasDolares() {
		return isLimiteCuotasDolares;
	}

	/**
	 * Sets the checks if is limite cuotas dolares.
	 *
	 * @param isLimiteCuotasDolares
	 *            the new checks if is limite cuotas dolares
	 */
	public void setIsLimiteCuotasDolares(Boolean isLimiteCuotasDolares) {
		this.isLimiteCuotasDolares = isLimiteCuotasDolares;
	}

	/**
	 * Gets the checks if is limite adelanto pesos.
	 *
	 * @return the checks if is limite adelanto pesos
	 */
	public Boolean getIsLimiteAdelantoPesos() {
		return isLimiteAdelantoPesos;
	}

	/**
	 * Sets the checks if is limite adelanto pesos.
	 *
	 * @param isLimiteAdelantoPesos
	 *            the new checks if is limite adelanto pesos
	 */
	public void setIsLimiteAdelantoPesos(Boolean isLimiteAdelantoPesos) {
		this.isLimiteAdelantoPesos = isLimiteAdelantoPesos;
	}

	/**
	 * Gets the checks if is limite adelanto dolares.
	 *
	 * @return the checks if is limite adelanto dolares
	 */
	public Boolean getIsLimiteAdelantoDolares() {
		return isLimiteAdelantoDolares;
	}

	/**
	 * Sets the checks if is limite adelanto dolares.
	 *
	 * @param isLimiteAdelantoDolares
	 *            the new checks if is limite adelanto dolares
	 */
	public void setIsLimiteAdelantoDolares(Boolean isLimiteAdelantoDolares) {
		this.isLimiteAdelantoDolares = isLimiteAdelantoDolares;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(adelantoEfectivoPesos);
		hcb.append(codigoError);
		hcb.append(limiteAdelantoEfectivoPesos);
		hcb.append(limiteCompraDolares);
		hcb.append(limiteCompraPesos);
		hcb.append(limiteCuotasPesos);
		hcb.append(marca);
		hcb.append(mensajeError);
		hcb.append(saldoDisponibleCompras);
		hcb.append(saldoDisponibleCuotas);
		hcb.append(limiteCuotasDolares);
		hcb.append(limiteAdelantoEfectivoDolares);
		hcb.append(legales);
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
		LimitesYDisponiblesView other = (LimitesYDisponiblesView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(adelantoEfectivoPesos, other.getAdelantoEfectivoPesos());
		eb.append(codigoError, other.getCodigoError());
		eb.append(limiteAdelantoEfectivoPesos, other.getLimiteAdelantoEfectivoPesos());
		eb.append(limiteCompraDolares, other.getLimiteCompraDolares());
		eb.append(limiteCompraPesos, other.getLimiteCompraPesos());
		eb.append(limiteCuotasPesos, other.getLimiteCuotasPesos());
		eb.append(marca, other.getMarca());
		eb.append(mensajeError, other.getMensajeError());
		eb.append(saldoDisponibleCompras, other.getSaldoDisponibleCompras());
		eb.append(saldoDisponibleCuotas, other.getSaldoDisponibleCuotas());
		eb.append(limiteCuotasDolares, other.getLimiteCuotasDolares());
		eb.append(limiteAdelantoEfectivoDolares, other.getLimiteAdelantoEfectivoDolares());
		eb.append(legales, other.getLegales());
		return eb.isEquals();
	}

	/**
	 * To String.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("marca", marca).append("nroTarjetaConFormato", nroTarjetaConFormato)
				.append("nroCuentaProduct", nroCuentaProducto).append("saldoDisponibleCompras", saldoDisponibleCompras)
				.append("limiteCompraPesos", limiteCompraPesos).append("limiteCompraDolares", limiteCompraDolares)
				.append("saldoDisponibleCuotas", saldoDisponibleCuotas).append("limiteCuotasPesos", limiteCuotasPesos)
				.append("limiteCuotasDolares", limiteCuotasDolares)
				.append("adelantoEfectivoPesos", adelantoEfectivoPesos)
				.append("limiteAdelantoEfectivoPesos", limiteAdelantoEfectivoPesos)
				.append("limiteAdelantoEfectivoDolares", limiteAdelantoEfectivoDolares)
				.append("codigoError", codigoError).append("mensajeError", mensajeError).append("legales", legales)
				.toString();
	}

}
