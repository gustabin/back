/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;

/**
 * The Class TarjetaView.
 * 
 * @author florencia.n.martinez
 *
 */
public class TarjetaView {

	/** The String marca. */
	private String marca;

	/** The String numero. */
	private String numero;

	/** The String numero. */
	private String numeroCuenta;

	/** The String alias. */
	private String alias;

	/** The String saldoDisponibleCuotas. */
	private String saldoDisponibleCuotas;

	/** The String saldoDisponibleCompras. */
	private String saldoDisponibleCompras;

	/** The Boolean hasLimiteUnificado. */
	private Boolean hasLimiteUnificado;

	/** The String consumoPesos. */
	private String consumoPesos;

	/** The String consumoDolares. */
	private String consumoDolares;

	/** The Boolean isAdicional. */
	private Boolean isAdicional;

	/** The Boolean hasError. */
	private Boolean hasError;

	/** The String fechaCierre. */
	private String fechaCierre;

	/** The String fechaVencimiento. */
	private String fechaVencimiento;

	/** The Boolean isRecargable. */
	private Boolean isRecargable;

	/** The Boolean hasConsumoDolarCero. */
	private Boolean hasConsumoDolarCero;

	/** The Boolean hasAlias. */
	private Boolean hasAlias;

	/** The Boolean isTitular. */
	private Boolean isTitular;

	/** The Boolean mostrarSaldoEnCuotas. */
	private Boolean mostrarSaldoEnCuotas;

	/** The Boolean mostrarSaldoEnCompras. */
	private Boolean mostrarSaldoEnCompras;

	/** The Boolean hasDisponibleCuotasCero. */
	private Boolean hasDisponibleCuotasCero;

	/** The Boolean hasDisponibleComprasCero. */
	private Boolean hasDisponibleComprasCero;

	/** The Boolean hasConsumoPesosCero. */
	private Boolean hasConsumoPesosCero;

	/** The Boolean isFavorito. */
	private Boolean isFavorito;
	
	private Boolean isReimprimibleTitular;
	
	/**
	 * The nroCuentaProducto. Se utiliza en financiacion de tarjetas.
	 */
	private String nroCuentaProducto;

	/** The nro tarjeta con formato. */
	private String nroTarjetaConFormato;
	
	/** The Boolean permiteResumenAnual. */
	private Boolean permiteResumenAnual;
	
	/**
	 * Instantiates a new tarjeta view.
	 */
	public TarjetaView() {
		super();
	}

	/**
	 * Instantiates a new tarjeta view.
	 *
	 * @param detalleDTO
	 *            the detalle DTO
	 * @param numeroCuenta
	 *            the numero cuenta
	 */
	public TarjetaView(DetalleTarjetaDTO detalleDTO, String numeroCuenta) {
		super();
		this.marca = detalleDTO.getMarca();
		this.numero = detalleDTO.getNroTarjetaConFormato();
		this.numeroCuenta = numeroCuenta;
		this.alias = detalleDTO.getAlias();
		this.saldoDisponibleCuotas = detalleDTO.getSaldoDisponibleCuotas();
		this.saldoDisponibleCompras = detalleDTO.getSaldoDisponibleCompras();
		this.hasLimiteUnificado = detalleDTO.getHasLimiteUnificado();
		this.consumoPesos = detalleDTO.getConsumoPesos();
		this.consumoDolares = detalleDTO.getConsumoDolares();
		this.isAdicional = detalleDTO.getIsAdicional();
		this.fechaCierre = detalleDTO.getFechaCierre();
		this.fechaVencimiento = detalleDTO.getFechaVencimiento();
		this.isRecargable = detalleDTO.getIsRecargable();
		this.hasConsumoDolarCero = detalleDTO.getEsConsumoDolarCero();
		this.hasAlias = !StringUtils.isEmpty(detalleDTO.getAlias());
		this.isTitular = detalleDTO.isTitular();
		this.mostrarSaldoEnCuotas = detalleDTO.getMostrarSaldoEnCuotas();
		this.mostrarSaldoEnCompras = detalleDTO.getMostrarSaldoEnCompras();
		this.hasDisponibleCuotasCero = detalleDTO.getEsSaldoEnCuotasCero();
		this.hasDisponibleComprasCero = detalleDTO.getEsSaldoEnComprasCero();
		this.hasConsumoPesosCero = detalleDTO.getEsConsumoPesosCero();
		this.isFavorito = detalleDTO.getIsFavorita();
		this.nroCuentaProducto = detalleDTO.getNroCuentaProducto();
		this.isReimprimibleTitular = detalleDTO.getIsReimprimibleTitular();
		this.permiteResumenAnual = detalleDTO.getPermiteResumenAnual();
	}

    /**
     * Instantiates a new tarjeta view.
     *
     * @param cuenta the cuenta
     * @param permiteResumenAnual the permite resumen anual
     */
    public TarjetaView(Cuenta cuenta, boolean permiteResumenAnual) {
        this.marca = cuenta.getTipoCuentaEnum().getDescripcion();
        this.numero = cuenta.getNroTarjetaCredito();
        this.nroTarjetaConFormato = TarjetaBOUtils.formatearNumeroTarjeta(this.numero, this.marca.toUpperCase());
        this.alias = cuenta.getAlias();
        this.isFavorito = cuenta.getIsFavorita();
        this.nroCuentaProducto = cuenta.getNroCuentaProducto();
        this.permiteResumenAnual = permiteResumenAnual;
    }

    /**
	 * Getter de marca.
	 * 
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * Setter de marca.
	 * 
	 * @param marca
	 *            the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Getter de numero.
	 * 
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numero cuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the new numero cuenta
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Setter de numero.
	 * 
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Getter de alias.
	 * 
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Setter de alias.
	 * 
	 * @param alias
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Getter de saldo disponible en cuotas.
	 * 
	 * @return the saldoDisponibleCuotas
	 */
	public String getSaldoDisponibleCuotas() {
		return saldoDisponibleCuotas;
	}

	/**
	 * Setter de saldo disponible en cuotas.
	 * 
	 * @param saldoDisponibleCuotas
	 *            the saldoDisponibleCuotas to set
	 */
	public void setSaldoDisponibleCuotas(String saldoDisponibleCuotas) {
		this.saldoDisponibleCuotas = saldoDisponibleCuotas;
	}

	/**
	 * Getter de saldo disponible en compras.
	 * 
	 * @return the saldoDisponibleCompras
	 */
	public String getSaldoDisponibleCompras() {
		return saldoDisponibleCompras;
	}

	/**
	 * Setter de saldo disponible en compras.
	 * 
	 * @param saldoDisponibleCompras
	 *            the saldoDisponibleCompras to set
	 */
	public void setSaldoDisponibleCompras(String saldoDisponibleCompras) {
		this.saldoDisponibleCompras = saldoDisponibleCompras;
	}

	/**
	 * Getter de limite unificado.
	 * 
	 * @return the hasLimiteUnificado
	 */
	public Boolean getHasLimiteUnificado() {
		return hasLimiteUnificado;
	}

	/**
	 * Setter de limite unificado.
	 * 
	 * @param hasLimiteUnificado
	 *            the hasLimiteUnificado to set
	 */
	public void setHasLimiteUnificado(Boolean hasLimiteUnificado) {
		this.hasLimiteUnificado = hasLimiteUnificado;
	}

	/**
	 * Getter de consumo en pesos.
	 * 
	 * @return the consumoPesos
	 */
	public String getConsumoPesos() {
		return consumoPesos;
	}

	/**
	 * Setter de consumo en pesos.
	 * 
	 * @param consumoPesos
	 *            the consumoPesos to set
	 */
	public void setConsumoPesos(String consumoPesos) {
		this.consumoPesos = consumoPesos;
	}

	/**
	 * Getter de consumo en dolares.
	 * 
	 * @return the consumoDolares
	 */
	public String getConsumoDolares() {
		return consumoDolares;
	}

	/**
	 * Setter de consumo en dolares.
	 * 
	 * @param consumoDolares
	 *            the consumoDolares to set
	 */
	public void setConsumoDolares(String consumoDolares) {
		this.consumoDolares = consumoDolares;
	}

	/**
	 * Getter de adicional.
	 * 
	 * @return the isAdicional
	 */
	public Boolean getIsAdicional() {
		return isAdicional;
	}

	/**
	 * Setter de adicional.
	 * 
	 * @param isAdicional
	 *            the isAdicional to set
	 */
	public void setIsAdicional(Boolean isAdicional) {
		this.isAdicional = isAdicional;
	}

	/**
	 * Getter de tiene error.
	 * 
	 * @return the hasError
	 */
	public Boolean getHasError() {
		return hasError;
	}

	/**
	 * Setter de tiene error.
	 * 
	 * @param hasError
	 *            the hasError to set
	 */
	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	/**
	 * Getter de fecha de cierre.
	 * 
	 * @return the fechaCierre
	 */
	public String getFechaCierre() {
		return fechaCierre;
	}

	/**
	 * Settter de fecha de cierre.
	 * 
	 * @param fechaCierre
	 *            the fechaCierre to set
	 */
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	/**
	 * Getter de fecha de vencimiento.
	 * 
	 * @return the fechaVencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Setter de fecha de vencimiento.
	 * 
	 * @param fechaVencimiento
	 *            the fechaVencimiento to set
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Getter de recargable.
	 * 
	 * @return the isRecargable
	 */
	public Boolean getIsRecargable() {
		return isRecargable;
	}

	/**
	 * Setter de recargable.
	 * 
	 * @param isRecargable
	 *            the isRecargable to set
	 */
	public void setIsRecargable(Boolean isRecargable) {
		this.isRecargable = isRecargable;
	}

	/**
	 * Getter de consumo dolar cero.
	 * 
	 * @return the hasConsumoDolarCero
	 */
	public Boolean getHasConsumoDolarCero() {
		return hasConsumoDolarCero;
	}

	/**
	 * Setter de consumo dolar cero.
	 * 
	 * @param hasConsumoDolarCero
	 *            the hasConsumoDolarCero to set
	 */
	public void setHasConsumoDolarCero(Boolean hasConsumoDolarCero) {
		this.hasConsumoDolarCero = hasConsumoDolarCero;
	}

	/**
	 * Getter de tiene alias.
	 * 
	 * @return the hasAlias
	 */
	public Boolean getHasAlias() {
		return hasAlias;
	}

	/**
	 * Setter de tiene alias.
	 * 
	 * @param hasAlias
	 *            the hasAlias to set
	 */
	public void setHasAlias(Boolean hasAlias) {
		this.hasAlias = hasAlias;
	}

	/**
	 * Getter de es titular.
	 * 
	 * @return the isTitular
	 */
	public Boolean getIsTitular() {
		return isTitular;
	}

	/**
	 * Setter de isTitular.
	 * 
	 * @param isTitular
	 *            the isTitular to set
	 */
	public void setIsTitular(Boolean isTitular) {
		this.isTitular = isTitular;
	}

	/**
	 * Getter de mostrarSaldoEnCuotas.
	 * 
	 * @return the mostrarSaldoEnCuotas
	 */
	public Boolean getMostrarSaldoEnCuotas() {
		return mostrarSaldoEnCuotas;
	}

	/**
	 * Setter de mostrarSaldoEnCuotas.
	 * 
	 * @param mostrarSaldoEnCuotas
	 *            the mostrarSaldoEnCuotas to set
	 */
	public void setMostrarSaldoEnCuotas(Boolean mostrarSaldoEnCuotas) {
		this.mostrarSaldoEnCuotas = mostrarSaldoEnCuotas;
	}

	/**
	 * Getter de mostrarSaldoEnCuotas.
	 * 
	 * @return the mostrarSaldoEnCompras
	 */
	public Boolean getMostrarSaldoEnCompras() {
		return mostrarSaldoEnCompras;
	}

	/**
	 * Setter de mostrarSaldoEnCompras.
	 * 
	 * @param mostrarSaldoEnCompras
	 *            the mostrarSaldoEnCompras to set
	 */
	public void setMostrarSaldoEnCompras(Boolean mostrarSaldoEnCompras) {
		this.mostrarSaldoEnCompras = mostrarSaldoEnCompras;
	}

	/**
	 * Getter de disponible en cuotas cero.
	 * 
	 * @return the hasDisponibleCuotasCero
	 */
	public Boolean getHasDisponibleCuotasCero() {
		return hasDisponibleCuotasCero;
	}

	/**
	 * Setter de disponible en cuotas cero.
	 * 
	 * @param hasDisponibleCuotasCero
	 *            the hasDisponibleCuotasCero to set
	 */
	public void setHasDisponibleCuotasCero(Boolean hasDisponibleCuotasCero) {
		this.hasDisponibleCuotasCero = hasDisponibleCuotasCero;
	}

	/**
	 * Getter de disponible en compras cero.
	 * 
	 * @return the hasDisponibleComprasCero
	 */
	public Boolean getHasDisponibleComprasCero() {
		return hasDisponibleComprasCero;
	}

	/**
	 * Setter de disponible en compras cero.
	 * 
	 * @param hasDisponibleComprasCero
	 *            the hasDisponibleComprasCero to set
	 */
	public void setHasDisponibleComprasCero(Boolean hasDisponibleComprasCero) {
		this.hasDisponibleComprasCero = hasDisponibleComprasCero;
	}

	/**
	 * Getter de consumo en pesos cero.
	 * 
	 * @return the hasConsumoPesosCero
	 */
	public Boolean getHasConsumoPesosCero() {
		return hasConsumoPesosCero;
	}

	/**
	 * Setter de consumo en pesos cero.
	 * 
	 * @param hasConsumoPesosCero
	 *            the hasConsumoPesosCero to set
	 */
	public void setHasConsumoPesosCero(Boolean hasConsumoPesosCero) {
		this.hasConsumoPesosCero = hasConsumoPesosCero;
	}

	/**
	 * Getter de es favorito.
	 * 
	 * @return the isFavorito
	 */
	public Boolean getIsFavorito() {
		return isFavorito;
	}

	/**
	 * Setter de es favorito.
	 * 
	 * @param isFavorito
	 *            the isFavorito to set
	 */
	public void setIsFavorito(Boolean isFavorito) {
		this.isFavorito = isFavorito;
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
	
	public Boolean getIsReimprimibleTitular() {
		return isReimprimibleTitular;
	}

	public void setIsReimprimibleTitular(Boolean isReimprimibleTitular) {
		this.isReimprimibleTitular = isReimprimibleTitular;
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
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(alias);
		hcb.append(consumoDolares);
		hcb.append(consumoPesos);
		hcb.append(fechaCierre);
		hcb.append(fechaVencimiento);
		hcb.append(hasAlias);
		hcb.append(hasConsumoPesosCero);
		hcb.append(hasConsumoDolarCero);
		hcb.append(hasDisponibleComprasCero);
		hcb.append(hasDisponibleCuotasCero);
		hcb.append(hasError);
		hcb.append(hasLimiteUnificado);
		hcb.append(isAdicional);
		hcb.append(isFavorito);
		hcb.append(isRecargable);
		hcb.append(isTitular);
		hcb.append(marca);
		hcb.append(mostrarSaldoEnCompras);
		hcb.append(mostrarSaldoEnCuotas);
		hcb.append(numero);
		hcb.append(numeroCuenta);
		hcb.append(saldoDisponibleCompras);
		hcb.append(saldoDisponibleCuotas);
		hcb.append(nroCuentaProducto);
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
		TarjetaView other = (TarjetaView) obj;

		EqualsBuilder eb = new EqualsBuilder();
		eb.append(alias, other.getAlias());
		eb.append(consumoDolares, other.getConsumoDolares());
		eb.append(consumoPesos, other.getConsumoPesos());
		eb.append(fechaCierre, other.getFechaCierre());
		eb.append(fechaVencimiento, other.getFechaVencimiento());
		eb.append(hasAlias, other.getHasAlias());
		eb.append(hasConsumoDolarCero, other.getHasConsumoDolarCero());
		eb.append(hasConsumoPesosCero, other.getHasConsumoPesosCero());
		eb.append(hasDisponibleComprasCero, other.getHasDisponibleComprasCero());
		eb.append(hasDisponibleCuotasCero, other.getHasDisponibleCuotasCero());
		eb.append(hasError, other.getHasError());
		eb.append(hasLimiteUnificado, other.getHasLimiteUnificado());
		eb.append(isAdicional, other.getIsAdicional());
		eb.append(isFavorito, other.getIsFavorito());
		eb.append(isRecargable, other.getIsRecargable());
		eb.append(isTitular, other.getIsTitular());
		eb.append(marca, other.getMarca());
		eb.append(mostrarSaldoEnCompras, other.getMostrarSaldoEnCompras());
		eb.append(mostrarSaldoEnCuotas, other.getMostrarSaldoEnCuotas());
		eb.append(numero, other.getNumero());
		eb.append(numeroCuenta, other.getNumeroCuenta());
		eb.append(saldoDisponibleCompras, other.getSaldoDisponibleCompras());
		eb.append(saldoDisponibleCuotas, other.getSaldoDisponibleCuotas());
		eb.append(nroCuentaProducto, other.getNroCuentaProducto());
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
		return "Tarjeta [marca=" + marca + ", numero=" + numero + ", numeroCuenta=" + numeroCuenta + ", alias=" + alias
				+ ", saldoDisponibleCuotas=" + saldoDisponibleCuotas + ", saldoDisponibleCompras="
				+ saldoDisponibleCompras + ", hasLimiteUnificado=" + hasLimiteUnificado + ", consumoPesos="
				+ consumoPesos + ", consumoDolares=" + consumoDolares + ", isAdicional=" + isAdicional + ", hasError="
				+ hasError + ", fechaCierre=" + fechaCierre + ", fechaVencimiento=" + fechaVencimiento
				+ ", isRecargable=" + isRecargable + ", hasConsumoDolarCero=" + hasConsumoDolarCero + ", hasAlias="
				+ hasAlias + ", isTitular=" + isTitular + ", mostrarSaldoEnCuotas=" + mostrarSaldoEnCuotas
				+ ", mostrarSaldoEnCompras=" + mostrarSaldoEnCompras + ", hasDisponibleCuotasCero="
				+ hasDisponibleCuotasCero + ", hasDisponibleComprasCero=" + hasDisponibleComprasCero
				+ ", hasConsumoPesosCero=" + hasConsumoPesosCero + ", isFavorito=" + isFavorito
				+ ", permiteResumenAnual=" + permiteResumenAnual + "]";
	}

}
