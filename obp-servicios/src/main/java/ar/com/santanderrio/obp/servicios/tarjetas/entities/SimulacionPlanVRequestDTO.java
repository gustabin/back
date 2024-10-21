/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;

/**
 * DTO para la invocacion del servicio de iatx SIMFIPLANV.100
 * 
 * @author manuel.vargas
 *
 */
public class SimulacionPlanVRequestDTO {

	/** The tipo cuenta. proviene de IDECLSTDO.Tipo_Cuenta - A01 */
	private String tipoCuenta;

	/**
	 * The numero de cuenta producto. proviene de IDECLSTDO.Nro_Cuenta_Producto
	 * - N10
	 */
	private String numeroDeCuentaProducto;

	/** The importe a financiar - proviene del campo. */
	private BigDecimal importe;

	/** The moneda del credito. */
	private String monedaDelCredito;

	/** The cantidad de cuotas. */
	private String cantidadDeCuotas;

	/** The cbu. */
	private String cbu;

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * The tipo plan V._A01 TIPO_PLANV_SALDOS
	 */
	private String tipoPlanV = "S";

	/**
	 * Instantiates a new simulacion request DTO.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @param numeroDeCuentaProducto
	 *            the numero de cuenta producto
	 * @param importe
	 *            the importe maximo
	 * @param monedaDelCredito
	 *            the moneda del credito
	 * @param cantidadDeCuotas
	 *            the cantidad de cuotas
	 * @param cbu
	 *            the cbu
	 */
	public SimulacionPlanVRequestDTO(String tipoCuenta, String numeroDeCuentaProducto, BigDecimal importe,
			String monedaDelCredito, String cantidadDeCuotas, String cbu) {
		super();
		this.tipoCuenta = tipoCuenta;
		this.numeroDeCuentaProducto = numeroDeCuentaProducto;
		this.importe = importe;
		this.monedaDelCredito = monedaDelCredito;
		this.cantidadDeCuotas = cantidadDeCuotas;
		this.cbu = cbu;
	}

	/**
	 * Instantiates a new simulacion plan V request DTO.
	 */
	public SimulacionPlanVRequestDTO() {
		super();
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
	 * Gets the numero de cuenta producto.
	 *
	 * @return the numero de cuenta producto
	 */
	public String getNumeroDeCuentaProducto() {
		return numeroDeCuentaProducto;
	}

	/**
	 * Sets the numero de cuenta producto.
	 *
	 * @param numeroDeCuentaProducto
	 *            the new numero de cuenta producto
	 */
	public void setNumeroDeCuentaProducto(String numeroDeCuentaProducto) {
		this.numeroDeCuentaProducto = numeroDeCuentaProducto;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the new importe
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the moneda del credito.
	 *
	 * @return the moneda del credito
	 */
	public String getMonedaDelCredito() {
		return monedaDelCredito;
	}

	/**
	 * Sets the moneda del credito.
	 *
	 * @param monedaDelCredito
	 *            the new moneda del credito
	 */
	public void setMonedaDelCredito(String monedaDelCredito) {
		this.monedaDelCredito = monedaDelCredito;
	}

	/**
	 * Gets the cantidad de cuotas.
	 *
	 * @return the cantidad de cuotas
	 */
	public String getCantidadDeCuotas() {
		return cantidadDeCuotas;
	}

	/**
	 * Sets the cantidad de cuotas.
	 *
	 * @param cantidadDeCuotas
	 *            the new cantidad de cuotas
	 */
	public void setCantidadDeCuotas(String cantidadDeCuotas) {
		this.cantidadDeCuotas = cantidadDeCuotas;
	}

	/**
	 * Gets the tipo plan V.
	 *
	 * @return the tipo plan V
	 */
	public String getTipoPlanV() {
		return tipoPlanV;
	}

	/**
	 * Sets the tipo plan V.
	 *
	 * @param tipoPlanV
	 *            the new tipo plan V
	 */
	public void setTipoPlanV(String tipoPlanV) {
		this.tipoPlanV = tipoPlanV;
	}

}
