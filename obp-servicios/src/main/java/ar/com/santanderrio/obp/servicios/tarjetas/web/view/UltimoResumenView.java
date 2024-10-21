/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenTarjetaDTO;

/**
 * View para ultimo resumen de tarjetas.
 *
 * @author federico.n.flores
 */
public class UltimoResumenView {

	/** The marca. */
	private String marca;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The alias. */
	private String alias;

	/** The tiene alias. */
	private Boolean tieneAlias;

	/** The fecha ultimo periodo. */
	private String fechaUltimoPeriodo;

	/** The fecha cierre actual. */
	private String fechaCierreActual;

	/** The fecha vencimiento actual. */
	private String fechaVencimientoActual;

	/** The saldo pesos. */
	private String saldoPesos;

	/** The saldo dolares. */
	private String saldoDolares;

	/** The pago minimo. */
	private String pagoMinimo;

	/** The limite financiacion. */
	private String limiteFinanciacion;

	/** The limite compra. */
	private String limiteCompra;

	/** The limite compra cuotas. */
	private String limiteCompraCuotas;

	/** The son limites dolar. */
	private Boolean sonLimitesDolar;

	/** The fecha proximo cierre. */
	private String fechaProximoCierre;

	/** The fecha proximo vencimiento. */
	private String fechaProximoVencimiento;

	/** The fecha cierre anterior. */
	private String fechaCierreAnterior;

	/** The fecha vencimiento anterior. */
	private String fechaVencimientoAnterior;

	/** The tasa nominal anual pesos. */
	private String tasaNominalAnualPesos;

	/** The tasa nominal anual dolares. */
	private String tasaNominalAnualDolares;

	/** The tasa efectiva mensual pesos. */
	private String tasaEfectivaMensualPesos;

	/** The tasa efectiva mensual dolares. */
	private String tasaEfectivaMensualDolares;

	/** The mensaje SEUO. */
	private String mensajeSEUO;

	/** The muestra tarjetas con cabecera. */
	private Boolean muestraTarjetasConCabecera;

	/** The tarjetas. */
	private List<UltimoResumenTarjetaView> tarjetas;

	/** The mostrar opcion pago tarjeta credito. */
	private Boolean mostrarOpcionPagoTarjetaCredito;

	/** The mensaje opcion pago tarjeta credito. */
	private String mensajeOpcionPagoTarjetaCredito;

	/** The conceptos. */
	private OtrosConceptosUltimoResumenView otrosConceptos;
	
	/** The legal. */
	private String legal;

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "dd/MM/yyyy";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/** The Constant FECHA_GUIONES. */
	private static final String FECHA_GUIONES = "-/-/-";

	/**
	 * Constructor de UltimoResumenView con argumento UltimoResumenDTO;.
	 *
	 * @param dto
	 *            the dto
	 */
	public UltimoResumenView(UltimoResumenDTO dto) {
		super();
		this.setMarca(dto.getMarca());
		this.setNumeroTarjeta(dto.getNumeroTarjeta());
		this.setAlias(dto.getAlias());
		this.setTieneAlias(dto.getTieneAlias());
		this.setFechaUltimoPeriodo(dto.getFechaVencimientoActual());
		this.setFechaCierreActual(dto.getFechaCierreActual());
		this.setFechaVencimientoActual(dto.getFechaVencimientoActual());
		this.setSaldoPesos(dto.getSaldoPesos());
		this.setSaldoDolares(dto.getSaldoDolares());
		this.setPagoMinimo(dto.getPagoMinimo());
		this.setLimiteFinanciacion(dto.getLimiteFinanciacion());
		this.setLimiteCompra(dto.getLimiteCompra());
		this.setLimiteCompraCuotas(dto.getLimiteCompraEnCuotas());
		this.setSonLimitesDolar(dto.getSonLimitesDolar());
		this.setFechaProximoCierre(dto.getFechaProximoCierre());
		this.setFechaProximoVencimiento(dto.getFechaProximoVencimiento());
		this.setFechaCierreAnterior(dto.getFechaCierreAnterior());
		this.setFechaVencimientoAnterior(dto.getFechaVencimientoAnterior());
		this.setTasaEfectivaMensualPesos(dto.getTasaEfectivaMensualPesos());
		this.setTasaNominalAnualPesos(dto.getTasaNominalAnualPesos());
		this.setTasaEfectivaMensualDolares(dto.getTasaEfectivaMensualDolares());
		this.setTasaNominalAnualDolares(dto.getTasaNominalAnualDolares());
		this.setMensajeSEUO(dto.getMensajeSEUO());
		this.setMuestraTarjetasConCabecera(dto.getMuestraTarjetasConCabecera());
		this.setMostrarOpcionPagoTarjetaCredito(dto.getMostrarOpcionPagoTarjetaCredito());
		this.setMensajeOpcionPagoTarjetaCredito(dto.getMensajeOpcionPagoTarjetaCredito());
		List<UltimoResumenTarjetaView> tarjetasView = new ArrayList<UltimoResumenTarjetaView>();
		for (UltimoResumenTarjetaDTO tarjetaDTO : dto.getTarjetas()) {
			UltimoResumenTarjetaView tarjetaView = new UltimoResumenTarjetaView(tarjetaDTO);
			tarjetasView.add(tarjetaView);
		}
		this.setTarjetas(tarjetasView);
		this.setOtrosConceptos(new OtrosConceptosUltimoResumenView(dto.getOtrosConceptos()));
		this.setLegal(dto.getTerminosYCondiciones());
	}

	/**
	 * Instantiates a new UltimoResumenView.
	 */
	public UltimoResumenView() {
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
	 *            the new marca
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}

	/**
	 * Gets the numero tarjeta.
	 *
	 * @return the numero tarjeta
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Sets the numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the new numero tarjeta
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
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the tiene alias.
	 *
	 * @return the tiene alias
	 */
	public Boolean getTieneAlias() {
		return tieneAlias;
	}

	/**
	 * Sets the tiene alias.
	 *
	 * @param tieneAlias
	 *            the new tiene alias
	 */
	public void setTieneAlias(Boolean tieneAlias) {
		this.tieneAlias = tieneAlias;
	}

	/**
	 * Gets the fecha ultimo periodo.
	 *
	 * @return the fecha ultimo periodo
	 */
	public String getFechaUltimoPeriodo() {
		return fechaUltimoPeriodo;
	}

	/**
	 * Sets the fecha ultimo periodo.
	 *
	 * @param fechaUltimoPeriodo
	 *            the new fecha ultimo periodo
	 */
	public void setFechaUltimoPeriodo(Date fechaUltimoPeriodo) {
		if (fechaUltimoPeriodo == null) {
			this.fechaUltimoPeriodo = GUION;
		}
		this.fechaUltimoPeriodo = TarjetaBOUtils.convertirFechaAlEspaniol(fechaUltimoPeriodo);

	}

	/**
	 * Gets the fecha cierre actual.
	 *
	 * @return the fecha cierre actual
	 */
	public String getFechaCierreActual() {
		return fechaCierreActual;
	}

	/**
	 * Sets the fecha cierre actual.
	 *
	 * @param fechaCierreActual
	 *            the new fecha cierre actual
	 */
	public void setFechaCierreActual(Date fechaCierreActual) {
		if (fechaCierreActual == null) {
			this.fechaCierreActual = FECHA_GUIONES;
		}
		this.fechaCierreActual = ISBANStringUtils.formatearFecha(fechaCierreActual, FORMATO_FECHA);
	}

	/**
	 * Gets the fecha vencimiento actual.
	 *
	 * @return the fecha vencimiento actual
	 */
	public String getFechaVencimientoActual() {
		return fechaVencimientoActual;
	}

	/**
	 * Sets the fecha vencimiento actual.
	 *
	 * @param fechaVencimientoActual
	 *            the new fecha vencimiento actual
	 */
	public void setFechaVencimientoActual(Date fechaVencimientoActual) {
		if (fechaVencimientoActual == null) {
			this.fechaVencimientoActual = FECHA_GUIONES;
		}
		this.fechaVencimientoActual = ISBANStringUtils.formatearFecha(fechaVencimientoActual, FORMATO_FECHA);
	}

	/**
	 * Gets the saldo pesos.
	 *
	 * @return the saldo pesos
	 */
	public String getSaldoPesos() {
		return saldoPesos;
	}

	/**
	 * Sets the saldo pesos.
	 *
	 * @param saldoPesos
	 *            the new saldo pesos
	 */
	public void setSaldoPesos(BigDecimal saldoPesos) {
		this.saldoPesos = ISBANStringUtils.formatearSaldoConSigno(saldoPesos);
	}

	/**
	 * Gets the saldo dolares.
	 *
	 * @return the saldo dolares
	 */
	public String getSaldoDolares() {
		return saldoDolares;
	}

	/**
	 * Sets the saldo dolares.
	 *
	 * @param saldoDolares
	 *            the new saldo dolares
	 */
	public void setSaldoDolares(BigDecimal saldoDolares) {
		this.saldoDolares = ISBANStringUtils.formatearSaldoConSigno(saldoDolares);
	}

	/**
	 * Gets the pago minimo.
	 *
	 * @return the pago minimo
	 */
	public String getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Sets the pago minimo.
	 *
	 * @param pagoMinimo
	 *            the new pago minimo
	 */
	public void setPagoMinimo(BigDecimal pagoMinimo) {
		this.pagoMinimo = ISBANStringUtils.formatearSaldoConSigno(pagoMinimo);
	}

	/**
	 * Gets the limite financiacion.
	 *
	 * @return the limite financiacion
	 */
	public String getLimiteFinanciacion() {
		return limiteFinanciacion;
	}

	/**
	 * Sets the limite financiacion.
	 *
	 * @param limiteFinanciacion
	 *            the new limite financiacion
	 */
	public void setLimiteFinanciacion(BigDecimal limiteFinanciacion) {
		this.limiteFinanciacion = ISBANStringUtils.formatearSaldoConSigno(limiteFinanciacion);
	}

	/**
	 * Gets the limite compra.
	 *
	 * @return the limite compra
	 */
	public String getLimiteCompra() {
		return limiteCompra;
	}

	/**
	 * Sets the limite compra.
	 *
	 * @param limiteCompra
	 *            the new limite compra
	 */
	public void setLimiteCompra(BigDecimal limiteCompra) {
		this.limiteCompra = ISBANStringUtils.formatearSaldoConSigno(limiteCompra);
	}

	/**
	 * Gets the limite compra cuotas.
	 *
	 * @return the limite compra cuotas
	 */
	public String getLimiteCompraCuotas() {
		return limiteCompraCuotas;
	}

	/**
	 * Sets the limite compra cuotas.
	 *
	 * @param limiteCompraCuotas
	 *            the new limite compra cuotas
	 */
	public void setLimiteCompraCuotas(BigDecimal limiteCompraCuotas) {
		this.limiteCompraCuotas = ISBANStringUtils.formatearSaldoConSigno(limiteCompraCuotas);
	}

	/**
	 * Gets the son limites dolar.
	 *
	 * @return the son limites dolar
	 */
	public Boolean getSonLimitesDolar() {
		return sonLimitesDolar;
	}

	/**
	 * Sets the son limites dolar.
	 *
	 * @param sonLimitesDolar
	 *            the new son limites dolar
	 */
	public void setSonLimitesDolar(Boolean sonLimitesDolar) {
		this.sonLimitesDolar = sonLimitesDolar;
	}

	/**
	 * Gets the fecha proximo cierre.
	 *
	 * @return the fecha proximo cierre
	 */
	public String getFechaProximoCierre() {
		return fechaProximoCierre;
	}

	/**
	 * Sets the fecha proximo cierre.
	 *
	 * @param fechaProximoCierre
	 *            the new fecha proximo cierre
	 */
	public void setFechaProximoCierre(Date fechaProximoCierre) {
		if (fechaProximoCierre == null) {
			this.fechaProximoCierre = FECHA_GUIONES;
		}
		this.fechaProximoCierre = ISBANStringUtils.formatearFecha(fechaProximoCierre, FORMATO_FECHA);
	}

	/**
	 * Gets the fecha proximo vencimiento.
	 *
	 * @return the fecha proximo vencimiento
	 */
	public String getFechaProximoVencimiento() {
		return fechaProximoVencimiento;
	}

	/**
	 * Sets the fecha proximo vencimiento.
	 *
	 * @param fechaProximoVencimiento
	 *            the new fecha proximo vencimiento
	 */
	public void setFechaProximoVencimiento(Date fechaProximoVencimiento) {
		if (fechaProximoVencimiento == null) {
			this.fechaProximoVencimiento = FECHA_GUIONES;
		}
		this.fechaProximoVencimiento = ISBANStringUtils.formatearFecha(fechaProximoVencimiento, FORMATO_FECHA);
	}

	/**
	 * Gets the fecha cierre anterior.
	 *
	 * @return the fecha cierre anterior
	 */
	public String getFechaCierreAnterior() {
		return fechaCierreAnterior;
	}

	/**
	 * Sets the fecha cierre anterior.
	 *
	 * @param fechaCierreAnterior
	 *            the new fecha cierre anterior
	 */
	public void setFechaCierreAnterior(Date fechaCierreAnterior) {
		if (fechaCierreAnterior == null) {
			this.fechaCierreAnterior = FECHA_GUIONES;
		}
		this.fechaCierreAnterior = ISBANStringUtils.formatearFecha(fechaCierreAnterior, FORMATO_FECHA);
	}

	/**
	 * Gets the fecha vencimiento anterior.
	 *
	 * @return the fecha vencimiento anterior
	 */
	public String getFechaVencimientoAnterior() {
		return fechaVencimientoAnterior;
	}

	/**
	 * Sets the fecha vencimiento anterior.
	 *
	 * @param fechaVencimientoAnterior
	 *            the new fecha vencimiento anterior
	 */
	public void setFechaVencimientoAnterior(Date fechaVencimientoAnterior) {
		if (fechaVencimientoAnterior == null) {
			this.fechaVencimientoAnterior = FECHA_GUIONES;
		}
		this.fechaVencimientoAnterior = ISBANStringUtils.formatearFecha(fechaVencimientoAnterior, FORMATO_FECHA);
	}

	/**
	 * Gets the tasa nominal anual pesos.
	 *
	 * @return the tasa nominal anual pesos
	 */
	public String getTasaNominalAnualPesos() {
		return tasaNominalAnualPesos;
	}

	/**
	 * Sets the tasa nominal anual pesos.
	 *
	 * @param tasaNominalAnualPesos
	 *            the new tasa nominal anual pesos
	 */
	public void setTasaNominalAnualPesos(BigDecimal tasaNominalAnualPesos) {
		this.tasaNominalAnualPesos = ISBANStringUtils.formatearSaldoConSigno(tasaNominalAnualPesos);
	}

	/**
	 * Gets the tasa nominal anual dolares.
	 *
	 * @return the tasa nominal anual dolares
	 */
	public String getTasaNominalAnualDolares() {
		return tasaNominalAnualDolares;
	}

	/**
	 * Sets the tasa nominal anual dolares.
	 *
	 * @param tasaNominalAnualDolares
	 *            the new tasa nominal anual dolares
	 */
	public void setTasaNominalAnualDolares(BigDecimal tasaNominalAnualDolares) {
		this.tasaNominalAnualDolares = ISBANStringUtils.formatearSaldoConSigno(tasaNominalAnualDolares);
	}

	/**
	 * Gets the tasa efectiva mensual pesos.
	 *
	 * @return the tasa efectiva mensual pesos
	 */
	public String getTasaEfectivaMensualPesos() {
		return tasaEfectivaMensualPesos;
	}

	/**
	 * Sets the tasa efectiva mensual pesos.
	 *
	 * @param tasaEfectivaMensualPesos
	 *            the new tasa efectiva mensual pesos
	 */
	public void setTasaEfectivaMensualPesos(BigDecimal tasaEfectivaMensualPesos) {
		this.tasaEfectivaMensualPesos = ISBANStringUtils.formatearSaldoConSigno(tasaEfectivaMensualPesos);
	}

	/**
	 * Gets the tasa efectiva mensual dolares.
	 *
	 * @return the tasa efectiva mensual dolares
	 */
	public String getTasaEfectivaMensualDolares() {
		return tasaEfectivaMensualDolares;
	}

	/**
	 * Sets the tasa efectiva mensual dolares.
	 *
	 * @param tasaEfectivaMensualDolares
	 *            the new tasa efectiva mensual dolares
	 */
	public void setTasaEfectivaMensualDolares(BigDecimal tasaEfectivaMensualDolares) {
		this.tasaEfectivaMensualDolares = ISBANStringUtils.formatearSaldoConSigno(tasaEfectivaMensualDolares);
	}

	/**
	 * Gets the mensaje SEUO.
	 *
	 * @return the mensaje SEUO
	 */
	public String getMensajeSEUO() {
		return mensajeSEUO;
	}

	/**
	 * Sets the mensaje SEUO.
	 *
	 * @param mensajeSEUO
	 *            the new mensaje SEUO
	 */
	public void setMensajeSEUO(String mensajeSEUO) {
		this.mensajeSEUO = mensajeSEUO;
	}

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
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<UltimoResumenTarjetaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<UltimoResumenTarjetaView> tarjetas) {
		this.tarjetas = tarjetas;
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
	 * Gets the otros conceptos.
	 *
	 * @return the otrosConceptos
	 */
	public OtrosConceptosUltimoResumenView getOtrosConceptos() {
		return otrosConceptos;
	}

	/**
	 * Sets the otros conceptos.
	 *
	 * @param otrosConceptos
	 *            the otrosConceptos to set
	 */
	public void setOtrosConceptos(OtrosConceptosUltimoResumenView otrosConceptos) {
		this.otrosConceptos = otrosConceptos;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
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
		hcb.append(marca);
		hcb.append(numeroTarjeta);
		hcb.append(alias);
		hcb.append(tieneAlias);
		hcb.append(fechaUltimoPeriodo);
		hcb.append(fechaCierreActual);
		hcb.append(fechaVencimientoActual);
		hcb.append(saldoPesos);
		hcb.append(saldoDolares);
		hcb.append(pagoMinimo);
		hcb.append(limiteFinanciacion);
		hcb.append(limiteCompra);
		hcb.append(limiteCompraCuotas);
		hcb.append(sonLimitesDolar);
		hcb.append(fechaProximoCierre);
		hcb.append(fechaProximoVencimiento);
		hcb.append(fechaCierreAnterior);
		hcb.append(fechaVencimientoAnterior);
		hcb.append(tasaNominalAnualPesos);
		hcb.append(tasaNominalAnualDolares);
		hcb.append(tasaEfectivaMensualPesos);
		hcb.append(tasaEfectivaMensualDolares);
		hcb.append(mensajeSEUO);
		hcb.append(muestraTarjetasConCabecera);
		hcb.append(tarjetas);
		hcb.append(otrosConceptos);
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
		UltimoResumenView other = (UltimoResumenView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(marca, other.marca);
		eb.append(numeroTarjeta, other.numeroTarjeta);
		eb.append(tieneAlias, other.tieneAlias);
		eb.append(mensajeOpcionPagoTarjetaCredito, other.mensajeOpcionPagoTarjetaCredito);
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
		return new ToStringBuilder(this).append("marca", marca).append("numeroTarjeta", numeroTarjeta)
				.append("alias", alias).append("tieneAlias", tieneAlias)
				.append("fechaUltimoPeriodo", fechaUltimoPeriodo).append("fechaCierreActual", fechaCierreActual)
				.append("fechaVencimientoActual", fechaVencimientoActual).append("saldoPesos", saldoPesos)
				.append("saldoDolares", saldoDolares).append("pagoMinimo", pagoMinimo)
				.append("limiteFinanciacion", limiteFinanciacion).append("limiteCompra", limiteCompra)
				.append("limiteCompraCuotas", limiteCompraCuotas).append("sonLimitesDolar", sonLimitesDolar)
				.append("fechaProximoCierre", fechaProximoCierre)
				.append("fechaProximoVencimiento", fechaProximoVencimiento)
				.append("fechaCierreAnterior", fechaCierreAnterior)
				.append("fechaVencimientoAnterior", fechaVencimientoAnterior)
				.append("tasaNominalAnualPesos", tasaNominalAnualPesos)
				.append("tasaNominalAnualDolares", tasaNominalAnualDolares)
				.append("tasaEfectivaMensualPesos", tasaEfectivaMensualPesos)
				.append("tasaEfectivaMensualDolares", tasaEfectivaMensualDolares).append("mensajeSEUO", mensajeSEUO)
				.append("muestraTarjetasConCabecera", muestraTarjetasConCabecera).append("tarjetas", tarjetas)
				.append("otrosConceptos", otrosConceptos).append("mensajeOpcionPagoTarjetaCredito", mensajeOpcionPagoTarjetaCredito).toString();
	}

}