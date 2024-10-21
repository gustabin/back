/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class LimitesYDisponiblesDTO.
 *
 * @author sabrina.cis
 */
public class LimitesYDisponiblesDTO {

	/** The nombre. */
	private String nombre;

	/** The fecha desde. */
	private String fechaDesde;

	/** The marca. */
	private String marca;

	/** The nro tarjeta. */
	private String nroTarjetaConFormato;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The nroCuentaProducto. */
	private String nroCuentaProducto;

	/** The String saldoCuenta. */
	private String saldoCuenta;

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

	/** The String consumoPesos. */
	private String consumoPesos;

	/** The String consumoDolares. */
	private String consumoDolares;

	/** The String saldoDisponibleCompras. */
	private String saldoDisponibleCompras;

	/**
	 * Gets the saldo disponible compras.
	 *
	 * @return the saldo disponible compras
	 */
	public String getsaldoDisponibleCompras() {
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
	 * Gets the consumo dolares.
	 *
	 * @return the consumo dolares
	 */
	public String getConsumoDolares() {
		return consumoDolares;
	}

	/**
	 * Sets the consumo dolares.
	 *
	 * @param consumoDolares
	 *            the new consumo dolares
	 */
	public void setConsumoDolares(String consumoDolares) {
		this.consumoDolares = consumoDolares;
	}

	/**
	 * Gets the consumo pesos.
	 *
	 * @return the consumo pesos
	 */
	public String getConsumoPesos() {
		return consumoPesos;
	}

	/**
	 * Sets the consumo pesos.
	 *
	 * @param consumoPesos
	 *            the new consumo pesos
	 */
	public void setConsumoPesos(String consumoPesos) {
		this.consumoPesos = consumoPesos;
	}

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fechaDesde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
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
	 * @return the nroTarjetaConFormato
	 */
	public String getNroTarjetaConFormato() {
		return nroTarjetaConFormato;
	}

	/**
	 * Sets the nro tarjeta con formato.
	 *
	 * @param nroTarjetaConFormato
	 *            the nroTarjetaConFormato to set
	 */
	public void setNroTarjetaConFormato(String nroTarjetaConFormato) {
		this.nroTarjetaConFormato = nroTarjetaConFormato;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nroTarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nroTarjeta to set
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the nro cuenta producto.
	 *
	 * @return the nroCuentaProducto
	 */
	public String getNroCuentaProducto() {
		return nroCuentaProducto;
	}

	/**
	 * Sets the nro cuenta producto.
	 *
	 * @param nroCuentaProducto
	 *            the nroCuentaProducto to set
	 */
	public void setNroCuentaProducto(String nroCuentaProducto) {
		this.nroCuentaProducto = nroCuentaProducto;
	}

	/**
	 * Gets the saldo disponible compras.
	 *
	 * @return the saldoCuenta
	 */
	public String getSaldoCuenta() {
		return saldoCuenta;
	}

	/**
	 * Sets the saldo cuentas.
	 *
	 * @param saldoCuenta
	 *            the SaldoCuenta to set
	 */
	public void setSaldoCuenta(String saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	/**
	 * Gets the limite compra pesos.
	 *
	 * @return the limiteCompraPesos
	 */
	public String getLimiteCompraPesos() {
		return limiteCompraPesos;
	}

	/**
	 * Sets the limite compra pesos.
	 *
	 * @param limiteCompraPesos
	 *            the limiteCompraPesos to set
	 */
	public void setLimiteCompraPesos(String limiteCompraPesos) {
		this.limiteCompraPesos = limiteCompraPesos;
	}

	/**
	 * Gets the limite compra dolares.
	 *
	 * @return the limiteCompraDolares
	 */
	public String getLimiteCompraDolares() {
		return limiteCompraDolares;
	}

	/**
	 * Sets the limite compra dolares.
	 *
	 * @param limiteCompraDolares
	 *            the limiteCompraDolares to set
	 */
	public void setLimiteCompraDolares(String limiteCompraDolares) {
		this.limiteCompraDolares = limiteCompraDolares;
	}

	/**
	 * Gets the saldo disponible cuotas.
	 *
	 * @return the saldoDisponibleCuotas
	 */
	public String getSaldoDisponibleCuotas() {
		return saldoDisponibleCuotas;
	}

	/**
	 * Sets the saldo disponible cuotas.
	 *
	 * @param saldoDisponibleCuotas
	 *            the saldoDisponibleCuotas to set
	 */
	public void setSaldoDisponibleCuotas(String saldoDisponibleCuotas) {
		this.saldoDisponibleCuotas = saldoDisponibleCuotas;
	}

	/**
	 * Gets the limite cuotas pesos.
	 *
	 * @return the limiteCuotasPesos
	 */
	public String getLimiteCuotasPesos() {
		return limiteCuotasPesos;
	}

	/**
	 * Sets the limite cuotas pesos.
	 *
	 * @param limiteCuotasPesos
	 *            the limiteCuotasPesos to set
	 */
	public void setLimiteCuotasPesos(String limiteCuotasPesos) {
		this.limiteCuotasPesos = limiteCuotasPesos;
	}

	/**
	 * Gets the limite cuotas dolares.
	 *
	 * @return the limiteCuotasDolares
	 */
	public String getLimiteCuotasDolares() {
		return limiteCuotasDolares;
	}

	/**
	 * Sets the limite cuotas dolares.
	 *
	 * @param limiteCuotasDolares
	 *            the limiteCuotasDolares to set
	 */
	public void setLimiteCuotasDolares(String limiteCuotasDolares) {
		this.limiteCuotasDolares = limiteCuotasDolares;
	}

	/**
	 * Gets the adelanto efectivo pesos.
	 *
	 * @return the adelantoEfectivoPesos
	 */
	public String getAdelantoEfectivoPesos() {
		return adelantoEfectivoPesos;
	}

	/**
	 * Sets the adelanto efectivo pesos.
	 *
	 * @param adelantoEfectivoPesos
	 *            the adelantoEfectivoPesos to set
	 */
	public void setAdelantoEfectivoPesos(String adelantoEfectivoPesos) {
		this.adelantoEfectivoPesos = adelantoEfectivoPesos;
	}

	/**
	 * Gets the limite adelanto efectivo pesos.
	 *
	 * @return the limiteAdelantoEfectivoPesos
	 */
	public String getLimiteAdelantoEfectivoPesos() {
		return limiteAdelantoEfectivoPesos;
	}

	/**
	 * Sets the limite adelanto efectivo pesos.
	 *
	 * @param limiteAdelantoEfectivoPesos
	 *            the limiteAdelantoEfectivoPesos to set
	 */
	public void setLimiteAdelantoEfectivoPesos(String limiteAdelantoEfectivoPesos) {
		this.limiteAdelantoEfectivoPesos = limiteAdelantoEfectivoPesos;
	}

	/**
	 * Gets the limite adelanto efectivo dolares.
	 *
	 * @return the limiteAdelantoEfectivoDolares
	 */
	public String getLimiteAdelantoEfectivoDolares() {
		return limiteAdelantoEfectivoDolares;
	}

	/**
	 * Sets the limite adelanto efectivo dolares.
	 *
	 * @param limiteAdelantoEfectivoDolares
	 *            the limiteAdelantoEfectivoDolares to set
	 */
	public void setLimiteAdelantoEfectivoDolares(String limiteAdelantoEfectivoDolares) {
		this.limiteAdelantoEfectivoDolares = limiteAdelantoEfectivoDolares;
	}

	/**
	 * Gets the codigo error.
	 *
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * Sets the codigo error.
	 *
	 * @param codigoError
	 *            the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
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
	 * Gets the checks for limite unificado.
	 *
	 * @return the hasLimiteUnificado
	 */
	public Boolean getHasLimiteUnificado() {
		return hasLimiteUnificado;
	}

	/**
	 * Sets the checks for limite unificado.
	 *
	 * @param hasLimiteUnificado
	 *            the hasLimiteUnificado to set
	 */
	public void setHasLimiteUnificado(Boolean hasLimiteUnificado) {
		this.hasLimiteUnificado = hasLimiteUnificado;
	}

	/**
	 * Gets the mostrar saldo en cuotas.
	 *
	 * @return the mostrarSaldoEnCuotas
	 */
	public Boolean getMostrarSaldoEnCuotas() {
		return mostrarSaldoEnCuotas;
	}

	/**
	 * Sets the mostrar saldo en cuotas.
	 *
	 * @param mostrarSaldoEnCuotas
	 *            the mostrarSaldoEnCuotas to set
	 */
	public void setMostrarSaldoEnCuotas(Boolean mostrarSaldoEnCuotas) {
		this.mostrarSaldoEnCuotas = mostrarSaldoEnCuotas;
	}

	/**
	 * Gets the mostrar saldo en compras.
	 *
	 * @return the mostrarSaldoEnCompras
	 */
	public Boolean getMostrarSaldoEnCompras() {
		return mostrarSaldoEnCompras;
	}

	/**
	 * Sets the mostrar saldo en compras.
	 *
	 * @param mostrarSaldoEnCompras
	 *            the mostrarSaldoEnCompras to set
	 */
	public void setMostrarSaldoEnCompras(Boolean mostrarSaldoEnCompras) {
		this.mostrarSaldoEnCompras = mostrarSaldoEnCompras;
	}

	/**
	 * Gets the checks if is limite compra pesos.
	 *
	 * @return the isLimiteCompraPesos
	 */
	public Boolean getIsLimiteCompraPesos() {
		return isLimiteCompraPesos;
	}

	/**
	 * Sets the checks if is limite compra pesos.
	 *
	 * @param isLimiteCompraPesos
	 *            the isLimiteCompraPesos to set
	 */
	public void setIsLimiteCompraPesos(Boolean isLimiteCompraPesos) {
		this.isLimiteCompraPesos = isLimiteCompraPesos;
	}

	/**
	 * Gets the checks if is limite compra dolares.
	 *
	 * @return the isLimiteCompraDolares
	 */
	public Boolean getIsLimiteCompraDolares() {
		return isLimiteCompraDolares;
	}

	/**
	 * Sets the checks if is limite compra dolares.
	 *
	 * @param isLimiteCompraDolares
	 *            the isLimiteCompraDolares to set
	 */
	public void setIsLimiteCompraDolares(Boolean isLimiteCompraDolares) {
		this.isLimiteCompraDolares = isLimiteCompraDolares;
	}

	/**
	 * Gets the checks if is limite cuotas pesos.
	 *
	 * @return the isLimiteCuotasPesos
	 */
	public Boolean getIsLimiteCuotasPesos() {
		return isLimiteCuotasPesos;
	}

	/**
	 * Sets the checks if is limite cuotas pesos.
	 *
	 * @param isLimiteCuotasPesos
	 *            the isLimiteCuotasPesos to set
	 */
	public void setIsLimiteCuotasPesos(Boolean isLimiteCuotasPesos) {
		this.isLimiteCuotasPesos = isLimiteCuotasPesos;
	}

	/**
	 * Gets the checks if is limite cuotas dolares.
	 *
	 * @return the isLimiteCuotasDolares
	 */
	public Boolean getIsLimiteCuotasDolares() {
		return isLimiteCuotasDolares;
	}

	/**
	 * Sets the checks if is limite cuotas dolares.
	 *
	 * @param isLimiteCuotasDolares
	 *            the isLimiteCuotasDolares to set
	 */
	public void setIsLimiteCuotasDolares(Boolean isLimiteCuotasDolares) {
		this.isLimiteCuotasDolares = isLimiteCuotasDolares;
	}

	/**
	 * Gets the checks if is limite adelanto pesos.
	 *
	 * @return the isLimiteAdelantoPesos
	 */
	public Boolean getIsLimiteAdelantoPesos() {
		return isLimiteAdelantoPesos;
	}

	/**
	 * Sets the checks if is limite adelanto pesos.
	 *
	 * @param isLimiteAdelantoPesos
	 *            the isLimiteAdelantoPesos to set
	 */
	public void setIsLimiteAdelantoPesos(Boolean isLimiteAdelantoPesos) {
		this.isLimiteAdelantoPesos = isLimiteAdelantoPesos;
	}

	/**
	 * Gets the checks if is limite adelanto dolares.
	 *
	 * @return the isLimiteAdelantoDolares
	 */
	public Boolean getIsLimiteAdelantoDolares() {
		return isLimiteAdelantoDolares;
	}

	/**
	 * Sets the checks if is limite adelanto dolares.
	 *
	 * @param isLimiteAdelantoDolares
	 *            the isLimiteAdelantoDolares to set
	 */
	public void setIsLimiteAdelantoDolares(Boolean isLimiteAdelantoDolares) {
		this.isLimiteAdelantoDolares = isLimiteAdelantoDolares;
	}

	/**
	 * Gets the checks for error.
	 *
	 * @return the hasError
	 */
	public Boolean getHasError() {
		return hasError;
	}

	/**
	 * Sets the checks for error.
	 *
	 * @param hasError
	 *            the hasError to set
	 */
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nombre);
		hcb.append(fechaDesde);
		hcb.append(adelantoEfectivoPesos);
		hcb.append(codigoError);
		hcb.append(limiteAdelantoEfectivoPesos);
		hcb.append(limiteCompraDolares);
		hcb.append(limiteCompraPesos);
		hcb.append(limiteCuotasPesos);
		hcb.append(marca);
		hcb.append(mensajeError);
		hcb.append(nroTarjeta);
		hcb.append(saldoCuenta);
		hcb.append(saldoDisponibleCuotas);
		hcb.append(legales);
		hcb.append(consumoPesos);
		hcb.append(consumoDolares);
		hcb.append(saldoDisponibleCompras);
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
		LimitesYDisponiblesDTO other = (LimitesYDisponiblesDTO) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(nombre, other.getNombre());
		eb.append(fechaDesde, other.getFechaDesde());
		eb.append(adelantoEfectivoPesos, other.getAdelantoEfectivoPesos());
		eb.append(codigoError, other.getCodigoError());
		eb.append(hasError, other.getHasError());
		eb.append(legales, other.getLegales());
		eb.append(limiteAdelantoEfectivoPesos, other.getLimiteAdelantoEfectivoPesos());
		eb.append(limiteCompraDolares, other.getLimiteCompraDolares());
		eb.append(limiteCompraPesos, other.getLimiteCompraPesos());
		eb.append(limiteCuotasPesos, other.getLimiteCuotasPesos());
		eb.append(marca, other.getMarca());
		eb.append(mensajeError, other.getMensajeError());
		eb.append(nroTarjeta, other.getNroTarjeta());
		eb.append(saldoCuenta, other.getSaldoCuenta());
		eb.append(saldoDisponibleCuotas, other.getSaldoDisponibleCuotas());
		eb.append(consumoPesos, other.getConsumoPesos());
		eb.append(consumoDolares, other.getConsumoDolares());
		eb.append(saldoDisponibleCompras, other.getsaldoDisponibleCompras());
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
		return new ToStringBuilder(this).append("nombre", nombre).append("fechaDesde", fechaDesde)
				.append("marca", marca).append("nroTarjetaConFormato", nroTarjetaConFormato)
				.append("nroTarjeta", nroTarjeta).append("nroCuentaProducto", nroCuentaProducto)
				.append("limiteCompraPesos", limiteCompraPesos).append("limiteCompraDolares", limiteCompraDolares)
				.append("saldoDisponibleCuotas", saldoDisponibleCuotas).append("limiteCuotasPesos", limiteCuotasPesos)
				.append("limiteCuotasDolares", limiteCuotasDolares)
				.append("adelantoEfectivoPesos", adelantoEfectivoPesos)
				.append("limiteAdelantoEfectivoPesos", limiteAdelantoEfectivoPesos)
				.append("limiteAdelantoEfectivoDolares", limiteAdelantoEfectivoDolares)
				.append("consumoPesos", consumoPesos).append("consumoDolares", consumoDolares)
				.append("saldoDisponibleCompras", saldoDisponibleCompras).append("mensajeError", mensajeError)
				.append("legales", legales).toString();
	}

}
