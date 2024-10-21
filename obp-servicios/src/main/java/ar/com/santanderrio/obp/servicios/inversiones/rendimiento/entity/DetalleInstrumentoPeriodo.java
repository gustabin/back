/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity;

import java.math.BigDecimal;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaOPEntity;

/**
 * The Class DetalleInstrumentoPeriodo.
 */
public class DetalleInstrumentoPeriodo {

	/** The codigo periodo. */
	@JsonProperty("CodigoPeriodo")
	private String codigoPeriodo;
	
	/** The descripcion periodo. */
	@JsonProperty("DescripcionPeriodo")
	private String descripcionPeriodo;
	
	/** The periodo fecha inicial. */
	@JsonProperty("PeriodoFechaInicial")
	private String periodoFechaInicial;
	
	/** The periodo fecha final. */
	@JsonProperty("PeriodoFechaFinal")
	private String periodoFechaFinal;
	
	/** The moneda. */
	@JsonProperty("Moneda")
	private String moneda;
	
	/** The lista cuentas. */
	@JsonProperty("ListaCuentas")
	private List<CuentaOPEntity> listaCuentas;
	
	/** The especie. */
	@JsonProperty("Especie")
	private String especie;
	
	/** The descripcion especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;
	
	/** The tenencia nominal no realizada. */
	@JsonProperty("TenenciaNominalNoRealizada")
	private BigDecimal tenenciaNominalNoRealizada;
	
	/** The ppp fifo no realizada. */
	@JsonProperty("PPPFifoNoRealizada")
	private BigDecimal pppFifoNoRealizada;
	
	/** The tenencia valuada costo. */
	@JsonProperty("TenenciaValuadaCosto")
	private BigDecimal tenenciaValuadaCosto;
	
	/** The precio especie. */
	@JsonProperty("PrecioEspecie")
	private BigDecimal precioEspecie;
	
	/** The tenencia valuada. */
	@JsonProperty("TenenciaValuada")
	private BigDecimal tenenciaValuada;
	
	/** The rentabilidad no realizada. */
	@JsonProperty("RentabilidadNoRealizada")
	private BigDecimal rentabilidadNoRealizada;
	
	/** The tenencia nominal realizada. */
	@JsonProperty("TenenciaNominalRealizada")
	private BigDecimal tenenciaNominalRealizada;
	
	/** The ppp fifo realizada. */
	@JsonProperty("PPPFifoRealizada")
	private BigDecimal pppFifoRealizada;
	
	/** The costo. */
	@JsonProperty("Costo")
	private BigDecimal costo;
	
	/** The gastos impuestos. */
	@JsonProperty("GastosImpuestos")
	private BigDecimal gastosImpuestos;
	
	/** The ventas. */
	@JsonProperty("Ventas")
	private BigDecimal ventas;
	
	/** The rentabilidad realizada. */
	@JsonProperty("RentabilidadRealizada")
	private BigDecimal rentabilidadRealizada;
	
	/** The rentabilidad neta. */
	@JsonProperty("RentabilidadNeta")
	private BigDecimal rentabilidadNeta;

	
	/**
	 * Gets the codigo periodo.
	 *
	 * @return the codigo periodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * Sets the codigo periodo.
	 *
	 * @param codigoPeriodo
	 *            the new codigo periodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * Gets the descripcion periodo.
	 *
	 * @return the descripcion periodo
	 */
	public String getDescripcionPeriodo() {
		return descripcionPeriodo;
	}

	/**
	 * Sets the descripcion periodo.
	 *
	 * @param descripcionPeriodo
	 *            the new descripcion periodo
	 */
	public void setDescripcionPeriodo(String descripcionPeriodo) {
		this.descripcionPeriodo = descripcionPeriodo;
	}

	/**
	 * Gets the periodo fecha inicial.
	 *
	 * @return the periodo fecha inicial
	 */
	public String getPeriodoFechaInicial() {
		return periodoFechaInicial;
	}

	/**
	 * Sets the periodo fecha inicial.
	 *
	 * @param periodoFechaInicial
	 *            the new periodo fecha inicial
	 */
	public void setPeriodoFechaInicial(String periodoFechaInicial) {
		this.periodoFechaInicial = periodoFechaInicial;
	}

	/**
	 * Gets the periodo fecha final.
	 *
	 * @return the periodo fecha final
	 */
	public String getPeriodoFechaFinal() {
		return periodoFechaFinal;
	}

	/**
	 * Sets the periodo fecha final.
	 *
	 * @param periodoFechaFinal
	 *            the new periodo fecha final
	 */
	public void setPeriodoFechaFinal(String periodoFechaFinal) {
		this.periodoFechaFinal = periodoFechaFinal;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the lista cuentas.
	 *
	 * @return the lista cuentas
	 */
	public List<CuentaOPEntity> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the new lista cuentas
	 */
	public void setListaCuentas(List<CuentaOPEntity> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the tenencia nominal no realizada.
	 *
	 * @return the tenencia nominal no realizada
	 */
	public BigDecimal getTenenciaNominalNoRealizada() {
		return tenenciaNominalNoRealizada;
	}

	/**
	 * Sets the tenencia nominal no realizada.
	 *
	 * @param tenenciaNominalNoRealizada
	 *            the new tenencia nominal no realizada
	 */
	public void setTenenciaNominalNoRealizada(BigDecimal tenenciaNominalNoRealizada) {
		this.tenenciaNominalNoRealizada = tenenciaNominalNoRealizada;
	}

	/**
	 * Gets the ppp fifo no realizada.
	 *
	 * @return the ppp fifo no realizada
	 */
	public BigDecimal getPppFifoNoRealizada() {
		return pppFifoNoRealizada;
	}

	/**
	 * Sets the ppp fifo no realizada.
	 *
	 * @param pppFifoNoRealizada
	 *            the new ppp fifo no realizada
	 */
	public void setPppFifoNoRealizada(BigDecimal pppFifoNoRealizada) {
		this.pppFifoNoRealizada = pppFifoNoRealizada;
	}

	/**
	 * Gets the tenencia valuada costo.
	 *
	 * @return the tenencia valuada costo
	 */
	public BigDecimal getTenenciaValuadaCosto() {
		return tenenciaValuadaCosto;
	}

	/**
	 * Sets the tenencia valuada costo.
	 *
	 * @param tenenciaValuadaCosto
	 *            the new tenencia valuada costo
	 */
	public void setTenenciaValuadaCosto(BigDecimal tenenciaValuadaCosto) {
		this.tenenciaValuadaCosto = tenenciaValuadaCosto;
	}

	/**
	 * Gets the precio especie.
	 *
	 * @return the precio especie
	 */
	public BigDecimal getPrecioEspecie() {
		return precioEspecie;
	}

	/**
	 * Sets the precio especie.
	 *
	 * @param precioEspecie
	 *            the new precio especie
	 */
	public void setPrecioEspecie(BigDecimal precioEspecie) {
		this.precioEspecie = precioEspecie;
	}

	/**
	 * Gets the tenencia valuada.
	 *
	 * @return the tenencia valuada
	 */
	public BigDecimal getTenenciaValuada() {
		return tenenciaValuada;
	}

	/**
	 * Sets the tenencia valuada.
	 *
	 * @param tenenciaValuada
	 *            the new tenencia valuada
	 */
	public void setTenenciaValuada(BigDecimal tenenciaValuada) {
		this.tenenciaValuada = tenenciaValuada;
	}

	/**
	 * Gets the rentabilidad no realizada.
	 *
	 * @return the rentabilidad no realizada
	 */
	public BigDecimal getRentabilidadNoRealizada() {
		return rentabilidadNoRealizada;
	}

	/**
	 * Sets the rentabilidad no realizada.
	 *
	 * @param rentabilidadNoRealizada
	 *            the new rentabilidad no realizada
	 */
	public void setRentabilidadNoRealizada(BigDecimal rentabilidadNoRealizada) {
		this.rentabilidadNoRealizada = rentabilidadNoRealizada;
	}

	/**
	 * Gets the tenencia nominal realizada.
	 *
	 * @return the tenencia nominal realizada
	 */
	public BigDecimal getTenenciaNominalRealizada() {
		return tenenciaNominalRealizada;
	}

	/**
	 * Sets the tenencia nominal realizada.
	 *
	 * @param tenenciaNominalRealizada
	 *            the new tenencia nominal realizada
	 */
	public void setTenenciaNominalRealizada(BigDecimal tenenciaNominalRealizada) {
		this.tenenciaNominalRealizada = tenenciaNominalRealizada;
	}

	/**
	 * Gets the ppp fifo realizada.
	 *
	 * @return the ppp fifo realizada
	 */
	public BigDecimal getPppFifoRealizada() {
		return pppFifoRealizada;
	}

	/**
	 * Sets the ppp fifo realizada.
	 *
	 * @param pppFifoRealizada
	 *            the new ppp fifo realizada
	 */
	public void setPppFifoRealizada(BigDecimal pppFifoRealizada) {
		this.pppFifoRealizada = pppFifoRealizada;
	}

	/**
	 * Gets the costo.
	 *
	 * @return the costo
	 */
	public BigDecimal getCosto() {
		return costo;
	}

	/**
	 * Sets the costo.
	 *
	 * @param costo
	 *            the new costo
	 */
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}

	/**
	 * Gets the gastos impuestos.
	 *
	 * @return the gastos impuestos
	 */
	public BigDecimal getGastosImpuestos() {
		return gastosImpuestos;
	}

	/**
	 * Sets the gastos impuestos.
	 *
	 * @param gastosImpuestos
	 *            the new gastos impuestos
	 */
	public void setGastosImpuestos(BigDecimal gastosImpuestos) {
		this.gastosImpuestos = gastosImpuestos;
	}

	/**
	 * Gets the ventas.
	 *
	 * @return the ventas
	 */
	public BigDecimal getVentas() {
		return ventas;
	}

	/**
	 * Sets the ventas.
	 *
	 * @param ventas
	 *            the new ventas
	 */
	public void setVentas(BigDecimal ventas) {
		this.ventas = ventas;
	}

	/**
	 * Gets the rentabilidad realizada.
	 *
	 * @return the rentabilidad realizada
	 */
	public BigDecimal getRentabilidadRealizada() {
		return rentabilidadRealizada;
	}

	/**
	 * Sets the rentabilidad realizada.
	 *
	 * @param rentabilidadRealizada
	 *            the new rentabilidad realizada
	 */
	public void setRentabilidadRealizada(BigDecimal rentabilidadRealizada) {
		this.rentabilidadRealizada = rentabilidadRealizada;
	}

	/**
	 * Gets the rentabilidad neta.
	 *
	 * @return the rentabilidad neta
	 */
	public BigDecimal getRentabilidadNeta() {
		return rentabilidadNeta;
	}

	/**
	 * Sets the rentabilidad neta.
	 *
	 * @param rentabilidadNeta
	 *            the new rentabilidad neta
	 */
	public void setRentabilidadNeta(BigDecimal rentabilidadNeta) {
		this.rentabilidadNeta = rentabilidadNeta;
	}
	
}
