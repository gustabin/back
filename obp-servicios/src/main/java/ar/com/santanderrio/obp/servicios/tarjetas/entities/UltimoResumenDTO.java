/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class UltimoResumenDTO.
 */
public class UltimoResumenDTO {

	/** The fecha cierre actual. */
	private Date fechaCierreActual;

	/** The fecha vencimiento actual. */
	private Date fechaVencimientoActual;

	/** The total pesos. */
	private BigDecimal saldoPesos;

	/** The total dolares. */
	private BigDecimal saldoDolares;

	/** The pago minimo. */
	private BigDecimal pagoMinimo;

	/** The limite financiacion. */
	private BigDecimal limiteFinanciacion;

	/** The limite compra. */
	private BigDecimal limiteCompra;

	/** The limite compra en cuotas. */
	private BigDecimal limiteCompraEnCuotas;

	/** The son limites dolar. */
	private Boolean sonLimitesDolar;

	/** The fecha proximo cierre. */
	private Date fechaProximoCierre;

	/** The fecha proximo vencimiento. */
	private Date fechaProximoVencimiento;

	/** The fecha cierre anterior. */
	private Date fechaCierreAnterior;

	/** The fecha vencimiento anterior. */
	private Date fechaVencimientoAnterior;

	/** The tasa nominal anual pesos. */
	private BigDecimal tasaNominalAnualPesos;

	/** The tasa efectiva mensual pesos. */
	private BigDecimal tasaEfectivaMensualPesos;

	/** The tasa nominal anual dolares. */
	private BigDecimal tasaNominalAnualDolares;

	/** The tasa efectiva mensual dolares. */
	private BigDecimal tasaEfectivaMensualDolares;

	/** The marca. */
	private String marca;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The alias. */
	private String alias;

	/** The tiene alias. */
	private Boolean tieneAlias;

	/** The tarjetas. */
	private List<UltimoResumenTarjetaDTO> tarjetas;

	/** The otrosConceptos. */
	private List<LineaUltimoResumenTarjetaDTO> otrosConceptos;

	/**
	 * True si tiene solo una tarjeta con consumos, y esa tarjeta no es asociada.
	 */
	private Boolean muestraTarjetasConCabecera;

	/** Mensaje SEUO de tabla de legales. */
	private String mensajeSEUO;

	/** The terminos Y condiciones. */
	private String terminosYCondiciones;

	/** The mostrar opcion pago tarjeta credito. */
	private Boolean mostrarOpcionPagoTarjetaCredito;

	/** The mensaje opcion pago tarjeta credito. */
	private String mensajeOpcionPagoTarjetaCredito;

	/**
	 * Gets the muestra tarjetas con cabecera.
	 *
	 * @return the muestra tarjetas con cabecera
	 */
	public Boolean getMuestraTarjetasConCabecera() {
		return muestraTarjetasConCabecera;
	}

	/**
	 * Sets the muestra tarjetas con cabecera.
	 *
	 * @param muestraTarjetasConCabecera
	 *            the new muestra tarjetas con cabecera
	 */
	public void setMuestraTarjetasConCabecera(Boolean muestraTarjetasConCabecera) {
		this.muestraTarjetasConCabecera = muestraTarjetasConCabecera;
	}

	/**
	 * Gets the fecha cierre actual.
	 *
	 * @return the fechaCierreActual
	 */
	public Date getFechaCierreActual() {
		return fechaCierreActual;
	}

	/**
	 * Sets the fecha cierre actual.
	 *
	 * @param fechaCierreActual
	 *            the fechaCierreActual to set
	 */
	public void setFechaCierreActual(Date fechaCierreActual) {
		this.fechaCierreActual = fechaCierreActual;
	}

	/**
	 * Gets the fecha vencimiento actual.
	 *
	 * @return the fechaVencimientoActual
	 */
	public Date getFechaVencimientoActual() {
		return fechaVencimientoActual;
	}

	/**
	 * Sets the fecha vencimiento actual.
	 *
	 * @param fechaVencimientoActual
	 *            the fechaVencimientoActual to set
	 */
	public void setFechaVencimientoActual(Date fechaVencimientoActual) {
		this.fechaVencimientoActual = fechaVencimientoActual;
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pagoMinimo
	 */
	public BigDecimal getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the pagoMinimo to set
	 */
	public void setPagoMinimo(BigDecimal pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Gets the limite financiacion.
	 *
	 * @return the limiteFinanciacion
	 */
	public BigDecimal getLimiteFinanciacion() {
		return limiteFinanciacion;
	}

	/**
	 * Sets the limite financiacion.
	 *
	 * @param limiteFinanciacion
	 *            the limiteFinanciacion to set
	 */
	public void setLimiteFinanciacion(BigDecimal limiteFinanciacion) {
		this.limiteFinanciacion = limiteFinanciacion;
	}

	/**
	 * Gets the limite compra.
	 *
	 * @return the limiteCompra
	 */
	public BigDecimal getLimiteCompra() {
		return limiteCompra;
	}

	/**
	 * Sets the limite compra.
	 *
	 * @param limiteCompra
	 *            the limiteCompra to set
	 */
	public void setLimiteCompra(BigDecimal limiteCompra) {
		this.limiteCompra = limiteCompra;
	}

	/**
	 * Gets the limite compra en cuotas.
	 *
	 * @return the limiteCompraEnCuotas
	 */
	public BigDecimal getLimiteCompraEnCuotas() {
		return limiteCompraEnCuotas;
	}

	/**
	 * Sets the limite compra en cuotas.
	 *
	 * @param limiteCompraEnCuotas
	 *            the limiteCompraEnCuotas to set
	 */
	public void setLimiteCompraEnCuotas(BigDecimal limiteCompraEnCuotas) {
		this.limiteCompraEnCuotas = limiteCompraEnCuotas;
	}

	/**
	 * Gets the fecha proximo cierre.
	 *
	 * @return the fechaProximoCierre
	 */
	public Date getFechaProximoCierre() {
		return fechaProximoCierre;
	}

	/**
	 * Sets the fecha proximo cierre.
	 *
	 * @param fechaProximoCierre
	 *            the fechaProximoCierre to set
	 */
	public void setFechaProximoCierre(Date fechaProximoCierre) {
		this.fechaProximoCierre = fechaProximoCierre;
	}

	/**
	 * Gets the fecha proximo vencimiento.
	 *
	 * @return the fechaProximoVencimiento
	 */
	public Date getFechaProximoVencimiento() {
		return fechaProximoVencimiento;
	}

	/**
	 * Sets the fecha proximo vencimiento.
	 *
	 * @param fechaProximoVencimiento
	 *            the fechaProximoVencimiento to set
	 */
	public void setFechaProximoVencimiento(Date fechaProximoVencimiento) {
		this.fechaProximoVencimiento = fechaProximoVencimiento;
	}

	/**
	 * Gets the fecha cierre anterior.
	 *
	 * @return the fechaCierreAnterior
	 */
	public Date getFechaCierreAnterior() {
		return fechaCierreAnterior;
	}

	/**
	 * Sets the fecha cierre anterior.
	 *
	 * @param fechaCierreAnterior
	 *            the fechaCierreAnterior to set
	 */
	public void setFechaCierreAnterior(Date fechaCierreAnterior) {
		this.fechaCierreAnterior = fechaCierreAnterior;
	}

	/**
	 * Gets the fecha vencimiento anterior.
	 *
	 * @return the fechaVencimientoAnterior
	 */
	public Date getFechaVencimientoAnterior() {
		return fechaVencimientoAnterior;
	}

	/**
	 * Sets the fecha vencimiento anterior.
	 *
	 * @param fechaVencimientoAnterior
	 *            the fechaVencimientoAnterior to set
	 */
	public void setFechaVencimientoAnterior(Date fechaVencimientoAnterior) {
		this.fechaVencimientoAnterior = fechaVencimientoAnterior;
	}

	/**
	 * Gets the tasa nominal anual pesos.
	 *
	 * @return the tasaNominalAnualPesos
	 */
	public BigDecimal getTasaNominalAnualPesos() {
		return tasaNominalAnualPesos;
	}

	/**
	 * Sets the tasa nominal anual pesos.
	 *
	 * @param tasaNominalAnualPesos
	 *            the tasaNominalAnualPesos to set
	 */
	public void setTasaNominalAnualPesos(BigDecimal tasaNominalAnualPesos) {
		this.tasaNominalAnualPesos = tasaNominalAnualPesos;
	}

	/**
	 * Gets the tasa efectiva mensual pesos.
	 *
	 * @return the tasaEfectivaMensualPesos
	 */
	public BigDecimal getTasaEfectivaMensualPesos() {
		return tasaEfectivaMensualPesos;
	}

	/**
	 * Sets the tasa efectiva mensual pesos.
	 *
	 * @param tasaEfectivaMensualPesos
	 *            the tasaEfectivaMensualPesos to set
	 */
	public void setTasaEfectivaMensualPesos(BigDecimal tasaEfectivaMensualPesos) {
		this.tasaEfectivaMensualPesos = tasaEfectivaMensualPesos;
	}

	/**
	 * Gets the tasa nominal anual dolares.
	 *
	 * @return the tasaNominalAnualDolares
	 */
	public BigDecimal getTasaNominalAnualDolares() {
		return tasaNominalAnualDolares;
	}

	/**
	 * Sets the tasa nominal anual dolares.
	 *
	 * @param tasaNominalAnualDolares
	 *            the tasaNominalAnualDolares to set
	 */
	public void setTasaNominalAnualDolares(BigDecimal tasaNominalAnualDolares) {
		this.tasaNominalAnualDolares = tasaNominalAnualDolares;
	}

	/**
	 * Gets the tasa efectiva mensual dolares.
	 *
	 * @return the tasaEfectivaMensualDolares
	 */
	public BigDecimal getTasaEfectivaMensualDolares() {
		return tasaEfectivaMensualDolares;
	}

	/**
	 * Sets the tasa efectiva mensual dolares.
	 *
	 * @param tasaEfectivaMensualDolares
	 *            the tasaEfectivaMensualDolares to set
	 */
	public void setTasaEfectivaMensualDolares(BigDecimal tasaEfectivaMensualDolares) {
		this.tasaEfectivaMensualDolares = tasaEfectivaMensualDolares;
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
	 * Gets the numero tarjeta.
	 *
	 * @return the numeroTarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
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
	 *            the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the tiene alias.
	 *
	 * @return the tieneAlias
	 */
	public Boolean getTieneAlias() {
		return tieneAlias;
	}

	/**
	 * Sets the tiene alias.
	 *
	 * @param tieneAlias
	 *            the tieneAlias to set
	 */
	public void setTieneAlias(Boolean tieneAlias) {
		this.tieneAlias = tieneAlias;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<UltimoResumenTarjetaDTO> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<UltimoResumenTarjetaDTO> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the son limites dolar.
	 *
	 * @return the sonLimitesDolar
	 */
	public Boolean getSonLimitesDolar() {
		return sonLimitesDolar;
	}

	/**
	 * Sets the son limites dolar.
	 *
	 * @param sonLimitesDolar
	 *            the sonLimitesDolar to set
	 */
	public void setSonLimitesDolar(Boolean sonLimitesDolar) {
		this.sonLimitesDolar = sonLimitesDolar;
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldoPesos
	 */
	public BigDecimal getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the saldoPesos to set
	 */
	public void setSaldoPesos(BigDecimal saldoPesos) {
		this.saldoPesos = saldoPesos;
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldoDolares
	 */
	public BigDecimal getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the saldoDolares to set
	 */
	public void setSaldoDolares(BigDecimal saldoDolares) {
		this.saldoDolares = saldoDolares;
	}

	/**
	 * Gets the mensaje SEUO.
	 *
	 * @return the mensajeSEUO
	 */
	public String getMensajeSEUO() {
		return mensajeSEUO;
	}

	/**
	 * Sets the mensaje SEUO.
	 *
	 * @param mensajeSEUO
	 *            the mensajeSEUO to set
	 */
	public void setMensajeSEUO(String mensajeSEUO) {
		this.mensajeSEUO = mensajeSEUO;
	}

	/**
	 * Gets the terminos Y condiciones.
	 *
	 * @return the terminosYCondiciones
	 */
	public String getTerminosYCondiciones() {
		return terminosYCondiciones;
	}

	/**
	 * Sets the terminos Y condiciones.
	 *
	 * @param terminosYCondiciones
	 *            the terminosYCondiciones to set
	 */
	public void setTerminosYCondiciones(String terminosYCondiciones) {
		this.terminosYCondiciones = terminosYCondiciones;
	}

	/**
	 * Gets the mostrar opcion pago tarjeta credito.
	 *
	 * @return the mostrar opcion pago tarjeta credito
	 */
	public Boolean getMostrarOpcionPagoTarjetaCredito() {
		return mostrarOpcionPagoTarjetaCredito;
	}

	/**
	 * Sets the mostrar opcion pago tarjeta credito.
	 *
	 * @param mostrarOpcionPagoTarjetaCredito
	 *            the new mostrar opcion pago tarjeta credito
	 */
	public void setMostrarOpcionPagoTarjetaCredito(Boolean mostrarOpcionPagoTarjetaCredito) {
		this.mostrarOpcionPagoTarjetaCredito = mostrarOpcionPagoTarjetaCredito;
	}

	/**
	 * Gets the otrosConceptos.
	 *
	 * @return the otrosConceptos
	 */
	public List<LineaUltimoResumenTarjetaDTO> getOtrosConceptos() {
		return otrosConceptos;
	}

	/**
	 * Sets the otrosConceptosO.
	 *
	 * @param otrosConceptos
	 *            the new otros conceptos
	 */
	public void setOtrosConceptos(List<LineaUltimoResumenTarjetaDTO> otrosConceptos) {
		this.otrosConceptos = otrosConceptos;
	}

	/**
	 * Gets the mensaje opcion pago tarjeta credito.
	 *
	 * @return the mensaje opcion pago tarjeta credito
	 */
	public String getMensajeOpcionPagoTarjetaCredito() {
		return mensajeOpcionPagoTarjetaCredito;
	}

	/**
	 * Sets the mensaje opcion pago tarjeta credito.
	 *
	 * @param opcionPagoTarjetaAviso the new mensaje opcion pago tarjeta credito
	 */
	public void setMensajeOpcionPagoTarjetaCredito(String opcionPagoTarjetaAviso) {
		this.mensajeOpcionPagoTarjetaCredito = opcionPagoTarjetaAviso;
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
		hcb.append(fechaCierreActual);
		hcb.append(fechaCierreAnterior);
		hcb.append(fechaProximoCierre);
		hcb.append(fechaProximoVencimiento);
		hcb.append(fechaVencimientoActual);
		hcb.append(fechaVencimientoAnterior);
		hcb.append(limiteCompra);
		hcb.append(limiteCompraEnCuotas);
		hcb.append(limiteFinanciacion);
		hcb.append(marca);
		hcb.append(numeroTarjeta);
		hcb.append(saldoDolares);
		hcb.append(saldoPesos);
		hcb.append(pagoMinimo);
		hcb.append(sonLimitesDolar);
		hcb.append(tasaEfectivaMensualDolares);
		hcb.append(tasaEfectivaMensualPesos);
		hcb.append(tasaNominalAnualDolares);
		hcb.append(tasaNominalAnualPesos);
		hcb.append(tieneAlias);
		hcb.append(muestraTarjetasConCabecera);
		hcb.append(mensajeSEUO);
		hcb.append(mensajeOpcionPagoTarjetaCredito);
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
		UltimoResumenDTO other = (UltimoResumenDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(alias, other.getAlias());
		eb.append(fechaCierreActual, other.getFechaCierreActual());
		eb.append(fechaCierreAnterior, other.getFechaCierreAnterior());
		eb.append(fechaProximoCierre, other.getFechaProximoCierre());
		eb.append(fechaProximoVencimiento, other.getFechaProximoVencimiento());
		eb.append(fechaVencimientoActual, other.getFechaVencimientoActual());
		eb.append(fechaVencimientoAnterior, other.getFechaVencimientoAnterior());
		eb.append(limiteCompra, other.getLimiteCompra());
		eb.append(limiteCompraEnCuotas, other.getLimiteCompraEnCuotas());
		eb.append(limiteFinanciacion, other.getLimiteFinanciacion());
		eb.append(marca, other.getMarca());
		eb.append(numeroTarjeta, other.getNumeroTarjeta());
		eb.append(pagoMinimo, other.getPagoMinimo());
		eb.append(saldoDolares, other.getSaldoDolares());
		eb.append(saldoPesos, other.getSaldoPesos());
		eb.append(sonLimitesDolar, other.getSonLimitesDolar());
		eb.append(tasaEfectivaMensualDolares, other.getTasaEfectivaMensualDolares());
		eb.append(tasaEfectivaMensualPesos, other.getTasaEfectivaMensualPesos());
		eb.append(tasaNominalAnualDolares, other.getTasaNominalAnualDolares());
		eb.append(tasaNominalAnualPesos, other.getTasaNominalAnualPesos());
		eb.append(tieneAlias, other.getTieneAlias());
		eb.append(mensajeOpcionPagoTarjetaCredito, other.getMensajeOpcionPagoTarjetaCredito());
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
		return new ToStringBuilder(this).append("fechaCierreActual", fechaCierreActual)
				.append("fechaVencimientoActual", fechaVencimientoActual).append("saldoPesos", saldoPesos)
				.append("saldoDolares", saldoDolares).append("pagoMinimo", pagoMinimo)
				.append("limiteFinanciacion", limiteFinanciacion).append("limiteCompra", limiteCompra)
				.append("limiteCompraEnCuotas", limiteCompraEnCuotas).append("sonLimitesDolar", sonLimitesDolar)
				.append("fechaProximoCierre", fechaProximoCierre)
				.append("fechaProximoVencimiento", fechaProximoVencimiento)
				.append("fechaCierreAnterior", fechaCierreAnterior)
				.append("fechaVencimientoAnterior", fechaVencimientoAnterior)
				.append("tasaNominalAnualPesos", tasaNominalAnualPesos)
				.append("tasaEfectivaMensualPesos", tasaEfectivaMensualPesos)
				.append("tasaNominalAnualDolares", tasaNominalAnualDolares)
				.append("tasaEfectivaMensualDolares", tasaEfectivaMensualDolares).append("marca", marca)
				.append("numeroTarjeta", numeroTarjeta).append("alias", alias).append("tieneAlias", tieneAlias)
				.append("tarjetas", tarjetas).append("muestraTarjetasConCabecera", muestraTarjetasConCabecera)
				.append("mensajeSEUO", mensajeSEUO).append("terminosYCondiciones", terminosYCondiciones)
				.append("otrosConceptos", otrosConceptos).append("mensajeOpcionPagoTarjetaCredito", mensajeOpcionPagoTarjetaCredito).toString();
	}

}