/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class DetalleTarjetaDTO.
 */
public class DetalleTarjetaDTO {

	/** The marca. */
	private String marca;

	/** The nro tarjeta. */
	private String nroTarjetaConFormato;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The nroCuentaProducto. */
	private String nroCuentaProducto;

	/** The nroSucursal. */
	private String nroSucursal;

	/** The alias. */
	private String alias;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The disponible en cuotas. */
	private String saldoDisponibleCuotas;

	/** The disponible en compras. */
	private String saldoDisponibleCompras;

	/** The is limite unificado. */
	private Boolean hasLimiteUnificado = Boolean.FALSE;

	/** The consumo saldo pesos. */
	private String consumoPesos;

	/** The consumo saldo dolares. */
	private String consumoDolares;

	/** The is adicional. */
	private Boolean isAdicional = Boolean.FALSE;

	/** The error. */
	private Boolean error = Boolean.FALSE;

	/** The fecha cierre. */
	private String fechaCierre;

	/** The fecha vencimiento. */
	private String fechaVencimiento;

	/** The fecha desde. */
	private Date fechaDesde;

	/** The is recargable. */
	private Boolean isRecargable = Boolean.FALSE;

	/** The is titular. */
	private Boolean isTitular = Boolean.FALSE;

	/** The is mostrarSaldoEnCuotas. */
	private Boolean mostrarSaldoEnCuotas = Boolean.TRUE;

	/** The is mostrarSaldoEnCompras. */
	private Boolean mostrarSaldoEnCompras = Boolean.TRUE;

	/** The is esSaldoEnCuotasCero. */
	private Boolean esSaldoEnCuotasCero = Boolean.TRUE;

	/** The is esSaldoEnComprasCero. */
	private Boolean esSaldoEnComprasCero = Boolean.TRUE;

	/** The is esConsumoPesosCero. */
	private Boolean esConsumoPesosCero = Boolean.TRUE;

	/** The is esConsumoPesosCero. */
	private Boolean esConsumoDolarCero = Boolean.TRUE;

	/** The is favorita. */
	private Boolean isFavorita = Boolean.FALSE;
	
	private Boolean isReimprimibleTitular;
	
	/** The permite resumen anual. */
	private Boolean permiteResumenAnual = Boolean.FALSE;

	/**
	 * Instantiates a new detalle tarjeta DTO.
	 */
	public DetalleTarjetaDTO() {
		super();
	}

	/**
	 * Gets the es saldo en cuotas cero.
	 *
	 * @return the esSaldoEnCuotasCero
	 */
	public Boolean getEsSaldoEnCuotasCero() {
		return esSaldoEnCuotasCero;
	}

	/**
	 * Sets the es saldo en cuotas cero.
	 *
	 * @param esSaldoEnCuotasCero
	 *            the esSaldoEnCuotasCero to set
	 */
	public void setEsSaldoEnCuotasCero(Boolean esSaldoEnCuotasCero) {
		this.esSaldoEnCuotasCero = esSaldoEnCuotasCero;
	}

	/**
	 * Gets the es saldo en compras cero.
	 *
	 * @return the esSaldoEnComprasCero
	 */
	public Boolean getEsSaldoEnComprasCero() {
		return esSaldoEnComprasCero;
	}

	/**
	 * Sets the es saldo en compras cero.
	 *
	 * @param esSaldoEnComprasCero
	 *            the esSaldoEnComprasCero to set
	 */
	public void setEsSaldoEnComprasCero(Boolean esSaldoEnComprasCero) {
		this.esSaldoEnComprasCero = esSaldoEnComprasCero;
	}

	/**
	 * Gets the checks if is titular.
	 *
	 * @return the isTitular
	 */
	public Boolean getIsTitular() {
		return isTitular;
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
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
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
	 * Gets the error.
	 *
	 * @return the error
	 */
	public Boolean getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the new error
	 */
	public void setError(Boolean error) {
		this.error = error;
	}

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
	 * Checks if is recargable.
	 *
	 * @return the boolean
	 */
	public Boolean isRecargable() {
		return isRecargable;
	}

	/**
	 * Sets the recargable.
	 *
	 * @param isRecargable
	 *            the new recargable
	 */
	public void setRecargable(Boolean isRecargable) {
		this.isRecargable = isRecargable;
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
	 * Gets the checks if is adicional.
	 *
	 * @return the checks if is adicional
	 */
	public Boolean getIsAdicional() {
		return isAdicional;
	}

	/**
	 * Sets the checks if is adicional.
	 *
	 * @param isAdicional
	 *            the new checks if is adicional
	 */
	public void setIsAdicional(Boolean isAdicional) {
		this.isAdicional = isAdicional;
	}

	/**
	 * Gets the checks if is recargable.
	 *
	 * @return the checks if is recargable
	 */
	public Boolean getIsRecargable() {
		return isRecargable;
	}

	/**
	 * Sets the checks if is recargable.
	 *
	 * @param isRecargable
	 *            the new checks if is recargable
	 */
	public void setIsRecargable(Boolean isRecargable) {
		this.isRecargable = isRecargable;
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
	 * Checks if is titular.
	 *
	 * @return the boolean
	 */
	public Boolean isTitular() {
		return isTitular;
	}

	/**
	 * Sets the checks if is titular.
	 *
	 * @param isTitular
	 *            the new checks if is titular
	 */
	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
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
	 * Gets the es consumo pesos cero.
	 *
	 * @return the es consumo pesos cero
	 */
	public Boolean getEsConsumoPesosCero() {
		return esConsumoPesosCero;
	}

	/**
	 * Sets the es consumo pesos cero.
	 *
	 * @param esConsumoPesosCero
	 *            the new es consumo pesos cero
	 */
	public void setEsConsumoPesosCero(Boolean esConsumoPesosCero) {
		this.esConsumoPesosCero = esConsumoPesosCero;
	}

	/**
	 * Gets the es consumo dolar cero.
	 *
	 * @return the esConsumoDolarCero
	 */
	public Boolean getEsConsumoDolarCero() {
		return esConsumoDolarCero;
	}

	/**
	 * Sets the es consumo dolar cero.
	 *
	 * @param esConsumoDolarCero
	 *            the esConsumoDolarCero to set
	 */
	public void setEsConsumoDolarCero(Boolean esConsumoDolarCero) {
		this.esConsumoDolarCero = esConsumoDolarCero;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fechaDesde
	 */
	public Date getFechaDesde() {
		Date fecha = null;
		if (fechaDesde != null) {
			fecha = new Date(fechaDesde.getTime());
		}
		return fecha;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the fechaDesde to set
	 */
	public void setFechaDesde(Date fechaDesde) {
		Date fecha = new Date(fechaDesde.getTime());
		this.fechaDesde = fecha;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the checks if is favorita.
	 *
	 * @return the checks if is favorita
	 */
	public Boolean getIsFavorita() {
		return isFavorita;
	}

	/**
	 * Sets the checks if is favorita.
	 *
	 * @param isFavorita
	 *            the new checks if is favorita
	 */
	public void setIsFavorita(Boolean isFavorita) {
		this.isFavorita = isFavorita;
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
	 * Gets the permite resumen anual.
	 *
	 * @return the permite resumen anual
	 */
	public Boolean getPermiteResumenAnual() {
		return permiteResumenAnual;
	}

	/**
	 * Sets the permite resumen anual.
	 *
	 * @param permiteResumenAnual the new permite resumen anual
	 */
	public void setPermiteResumenAnual(Boolean permiteResumenAnual) {
		this.permiteResumenAnual = permiteResumenAnual;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(alias);
		hcb.append(consumoDolares);
		hcb.append(consumoPesos);
		hcb.append(error);
		hcb.append(esConsumoDolarCero);
		hcb.append(esConsumoPesosCero);
		hcb.append(esSaldoEnComprasCero);
		hcb.append(esSaldoEnCuotasCero);
		hcb.append(fechaCierre);
		hcb.append(fechaVencimiento);
		hcb.append(fechaDesde);
		hcb.append(hasLimiteUnificado);
		hcb.append(isAdicional);
		hcb.append(isFavorita);
		hcb.append(isRecargable);
		hcb.append(isTitular);
		hcb.append(marca);
		hcb.append(mostrarSaldoEnCompras);
		hcb.append(mostrarSaldoEnCuotas);
		hcb.append(nroCuentaProducto);
		hcb.append(nroSucursal);
		hcb.append(nroTarjeta);
		hcb.append(nroTarjetaConFormato);
		hcb.append(saldoDisponibleCompras);
		hcb.append(saldoDisponibleCuotas);
		hcb.append(tipoCuenta);
		hcb.append(permiteResumenAnual);
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
		DetalleTarjetaDTO other = (DetalleTarjetaDTO) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(alias, other.getAlias());
		eb.append(consumoDolares, other.getConsumoDolares());
		eb.append(consumoPesos, other.getConsumoPesos());
		eb.append(error, other.getError());
		eb.append(esConsumoDolarCero, other.getEsConsumoDolarCero());
		eb.append(esConsumoPesosCero, other.getEsConsumoPesosCero());
		eb.append(esSaldoEnComprasCero, other.getEsSaldoEnComprasCero());
		eb.append(esSaldoEnCuotasCero, other.getEsSaldoEnCuotasCero());
		eb.append(fechaCierre, other.getFechaCierre());
		eb.append(fechaVencimiento, other.getFechaVencimiento());
		eb.append(fechaDesde, other.getFechaDesde());
		eb.append(hasLimiteUnificado, other.getHasLimiteUnificado());
		eb.append(isAdicional, other.getIsAdicional());
		eb.append(isFavorita, other.getIsFavorita());
		eb.append(isRecargable, other.getIsRecargable());
		eb.append(isTitular, other.getIsTitular());
		eb.append(marca, other.getMarca());
		eb.append(mostrarSaldoEnCompras, other.getMostrarSaldoEnCompras());
		eb.append(mostrarSaldoEnCuotas, other.getMostrarSaldoEnCuotas());
		eb.append(nroCuentaProducto, other.getNroCuentaProducto());
		eb.append(nroSucursal, other.getNroSucursal());
		eb.append(nroTarjeta, other.getNroTarjeta());
		eb.append(nroTarjetaConFormato, other.getNroTarjetaConFormato());
		eb.append(saldoDisponibleCompras, other.getSaldoDisponibleCompras());
		eb.append(saldoDisponibleCuotas, other.getSaldoDisponibleCuotas());
		eb.append(tipoCuenta, other.getTipoCuenta());
		eb.append(permiteResumenAnual, other.getPermiteResumenAnual());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "DetalleTarjeta [marca=" + marca + ", nroTarjetaConFormato=" + nroTarjetaConFormato + ", nroTarjeta="
				+ nroTarjeta + ", nroCuentaProducto=" + nroCuentaProducto + ", nroSucursal=" + nroSucursal + ", alias="
				+ alias + ", tipoCuenta=" + tipoCuenta + ", saldoDisponibleCuotas=" + saldoDisponibleCuotas
				+ ", saldoDisponibleCompras=" + saldoDisponibleCompras + ", hasLimiteUnificado=" + hasLimiteUnificado
				+ ", consumoPesos=" + consumoPesos + ", consumoDolares=" + consumoDolares + ", isAdicional="
				+ isAdicional + ", error=" + error + ", fechaCierre=" + fechaCierre + ", fechaVencimiento="
				+ fechaVencimiento + ", fechaDesde=" + fechaDesde + ", isRecargable=" + isRecargable + ", isTitular="
				+ isTitular + ", mostrarSaldoEnCuotas=" + mostrarSaldoEnCuotas + ", mostrarSaldoEnCompras="
				+ mostrarSaldoEnCompras + ", esSaldoEnCuotasCero=" + esSaldoEnCuotasCero + ", esSaldoEnComprasCero="
				+ esSaldoEnComprasCero + ", esConsumoPesosCero=" + esConsumoPesosCero + ", esConsumoDolarCero="
				+ esConsumoDolarCero + ", isFavorita=" + isFavorita + ", permiteResumenAnual=" + permiteResumenAnual + "]";
	}

	public void setIsReimprimibleTitular(Boolean isReimprimibleTitular) {
		this.isReimprimibleTitular = isReimprimibleTitular;
	}

	
	public Boolean getIsReimprimibleTitular() {
		return isReimprimibleTitular;
	}

}
