/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import org.codehaus.jackson.map.annotate.JsonDeserialize;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.controller.DivisaEnumDeserializer;
import ar.com.santanderrio.obp.servicios.cuentas.web.controller.RangoFechaEnumDeserializer;
import ar.com.santanderrio.obp.servicios.general.entities.RangoFechaEnum;

/**
 * The Class ConsultaUltimosMovimientosView.
 */
public class ConsultaUltimosMovimientosView {

	/** The cuenta. */
	private String numero;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The moneda. */
	@JsonDeserialize(using = DivisaEnumDeserializer.class)
	private DivisaEnum moneda;

	/** The rango. */
	@JsonDeserialize(using = RangoFechaEnumDeserializer.class)
	private RangoFechaEnum rango;

	/** The importe desde. */
	private String importeDesde;

	/** The importe hasta. */
	private String importeHasta;

	/** The palabra clave. */
	private String palabraClave;

	/** The tipo cuenta. */
	private TipoCuenta tipoCuenta;
	
	/** The importe string. */
	private String importeString;

	/** The fecha string. */
	private String fechaString;
	
	/** The desde default. */
	private boolean desdeDefault;
	
	private Boolean primerIngreso;

	/**
	 * Gets the rango.
	 *
	 * @return the rango
	 */
	public RangoFechaEnum getRango() {
		return rango;
	}

	/**
	 * Setter para rango.
	 *
	 * @param rango
	 *            el nuevo rango
	 */
	public void setRango(RangoFechaEnum rango) {
		this.rango = rango;
	}

	/**
	 * Gets the palabra clave.
	 *
	 * @return the palabra clave
	 */
	public String getPalabraClave() {
		return palabraClave;
	}

	/**
	 * Setter para palabra clave.
	 *
	 * @param palabraClave
	 *            el nuevo palabra clave
	 */
	public void setPalabraClave(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public DivisaEnum getMoneda() {
		return moneda;
	}

	/**
	 * Setter para moneda.
	 *
	 * @param moneda
	 *            el nuevo moneda
	 */
	public void setMoneda(DivisaEnum moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the importe desde.
	 *
	 * @return the importe desde
	 */
	public String getImporteDesde() {
		return importeDesde;
	}

	/**
	 * Setter para importe desde.
	 *
	 * @param importeDesde
	 *            el nuevo importe desde
	 */
	public void setImporteDesde(String importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * Gets the importe hasta.
	 *
	 * @return the importe hasta
	 */
	public String getImporteHasta() {
		return importeHasta;
	}

	/**
	 * Setter para importe hasta.
	 *
	 * @param importeHasta
	 *            el nuevo importe hasta
	 */
	public void setImporteHasta(String importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the numero de cuenta
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Setter para cuenta.
	 *
	 * @param numero
	 *            the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Setter para fecha desde.
	 *
	 * @param fechaDesde
	 *            el nuevo fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Setter para fecha hasta.
	 *
	 * @param fechaHasta
	 *            el nuevo fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the importe string.
	 *
	 * @return the importe string
	 */
	public String getImporteString() {
		return importeString;
	}

	/**
	 * Sets the importe string.
	 *
	 * @param importeString
	 *            the new importe string
	 */
	public void setImporteString(String importeString) {
		this.importeString = importeString;
	}

	/**
	 * Gets the fecha string.
	 *
	 * @return the fecha string
	 */
	public String getFechaString() {
		return fechaString;
	}

	/**
	 * Sets the fecha string.
	 *
	 * @param fechaString
	 *            the new fecha string
	 */
	public void setFechaString(String fechaString) {
		this.fechaString = fechaString;
	}

	/**
	 * Gets the desde default.
	 *
	 * @return the desde default
	 */
	public boolean getDesdeDefault() {
		return desdeDefault;
	}

	/**
	 * Sets the desde default.
	 *
	 * @param desdeDefault the new desde default
	 */
	public void setDesdeDefault(boolean desdeDefault) {
		this.desdeDefault = desdeDefault;
	}

	/**
	 * @return the primerIngreso
	 */
	public Boolean getPrimerIngreso() {
		return primerIngreso;
	}

	/**
	 * @param primerIngreso the primerIngreso to set
	 */
	public void setPrimerIngreso(Boolean primerIngreso) {
		this.primerIngreso = primerIngreso;
	}

}
