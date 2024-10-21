/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Class ChequesSimuladosDTO.
 */
public class ChequesSimuladosDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1930955022084990332L;

	/** The importe acreditar. */
	private BigDecimal importeAcreditar;
	
	/** The importe total. */
	private BigDecimal importeTotal;
	
	/** The cuenta debito. */
	private IdentificacionCuenta cuentaDebito;
	
	/** The numero de operacion. */
	private BigInteger numeroDeOperacion;
	
	/** The cheques descontados. */
	private int chequesDescontados;
	
	/** The importe cheque. */
	private BigDecimal importeCheque;
	
	/** The importe impuestos. */
	private BigDecimal importeImpuestos;
	
	/** The importe intereses. */
	private BigDecimal importeIntereses;
	
	/** The importe neto. */
	private BigDecimal importeNeto;

	/** The lista aceptados. */
	private List<ChequeSimuladoDTO> listaAceptados = new ArrayList<ChequeSimuladoDTO>();
	
	/** The lista rechazados. */
	private List<ChequeSimuladoDTO> listaRechazados = new ArrayList<ChequeSimuladoDTO>();
	
	/** The comision adic. */
	private BigDecimal comisionAdic;
	
	/** The tasa aplicada. */
	private String tasaAplicada;
	
	/** The tasa efectiva anual. */
	private String tasaEfectivaAnual;
	
	/** The costo financiero total. */
	private String costoFinancieroTotal;
	
	/** The legal 1. */
	private String legal1;
	
	/** The legal 2. */
	private String legal2;
	
	/** The legal 3. */
	private String legal3;
	
	/** The legal 4. */
	private String legal4;
	
	private String legal5;
	
	/** The mensaje feedback. */
	private String mensajeFeedback;
	
	/** The fecha alta. */
	private Date fechaAlta;

	/**
	 * Gets the importe acreditar.
	 *
	 * @return the importe acreditar
	 */
	public BigDecimal getImporteAcreditar() {
		return importeAcreditar;
	}

	/**
	 * Sets the importe acreditar.
	 *
	 * @param importeAcreditar
	 *            the new importe acreditar
	 */
	public void setImporteAcreditar(BigDecimal importeAcreditar) {
		this.importeAcreditar = importeAcreditar;
	}

	/**
	 * Gets the importe total.
	 *
	 * @return the importe total
	 */
	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	/**
	 * Sets the importe total.
	 *
	 * @param importeTotal
	 *            the new importe total
	 */
	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	/**
	 * Gets the cuenta debito.
	 *
	 * @return the cuenta debito
	 */
	public IdentificacionCuenta getCuentaDebito() {
		return cuentaDebito;
	}

	/**
	 * Sets the cuenta debito.
	 *
	 * @param cuentaDebito
	 *            the new cuenta debito
	 */
	public void setCuentaDebito(IdentificacionCuenta cuentaDebito) {
		this.cuentaDebito = cuentaDebito;
	}

	/**
	 * Gets the numero de operacion.
	 *
	 * @return the numero de operacion
	 */
	public BigInteger getNumeroDeOperacion() {
		return numeroDeOperacion;
	}

	/**
	 * Sets the numero de operacion.
	 *
	 * @param numeroDeOperacion
	 *            the new numero de operacion
	 */
	public void setNumeroDeOperacion(BigInteger numeroDeOperacion) {
		this.numeroDeOperacion = numeroDeOperacion;
	}

	/**
	 * Gets the cheques descontados.
	 *
	 * @return the cheques descontados
	 */
	public int getChequesDescontados() {
		return chequesDescontados;
	}

	/**
	 * Sets the cheques descontados.
	 *
	 * @param chequesDescontados
	 *            the new cheques descontados
	 */
	public void setChequesDescontados(int chequesDescontados) {
		this.chequesDescontados = chequesDescontados;
	}

	/**
	 * Gets the importe cheque.
	 *
	 * @return the importe cheque
	 */
	public BigDecimal getImporteCheque() {
		return importeCheque;
	}

	/**
	 * Sets the importe cheque.
	 *
	 * @param importeCheque
	 *            the new importe cheque
	 */
	public void setImporteCheque(BigDecimal importeCheque) {
		this.importeCheque = importeCheque;
	}

	/**
	 * Gets the importe impuestos.
	 *
	 * @return the importe impuestos
	 */
	public BigDecimal getImporteImpuestos() {
		return importeImpuestos;
	}

	/**
	 * Sets the importe impuestos.
	 *
	 * @param importeImpuestos
	 *            the new importe impuestos
	 */
	public void setImporteImpuestos(BigDecimal importeImpuestos) {
		this.importeImpuestos = importeImpuestos;
	}

	/**
	 * Gets the importe intereses.
	 *
	 * @return the importe intereses
	 */
	public BigDecimal getImporteIntereses() {
		return importeIntereses;
	}

	/**
	 * Sets the importe intereses.
	 *
	 * @param importeIntereses
	 *            the new importe intereses
	 */
	public void setImporteIntereses(BigDecimal importeIntereses) {
		this.importeIntereses = importeIntereses;
	}

	/**
	 * Gets the importe neto.
	 *
	 * @return the importe neto
	 */
	public BigDecimal getImporteNeto() {
		return importeNeto;
	}

	/**
	 * Sets the importe neto.
	 *
	 * @param importeNeto
	 *            the new importe neto
	 */
	public void setImporteNeto(BigDecimal importeNeto) {
		this.importeNeto = importeNeto;
	}

	/**
	 * Gets the lista aceptados.
	 *
	 * @return the lista aceptados
	 */
	public List<ChequeSimuladoDTO> getListaAceptados() {
		return listaAceptados;
	}

	/**
	 * Sets the lista aceptados.
	 *
	 * @param listaAceptados
	 *            the new lista aceptados
	 */
	public void setListaAceptados(List<ChequeSimuladoDTO> listaAceptados) {
		this.listaAceptados = listaAceptados;
	}

	/**
	 * Gets the lista rechazados.
	 *
	 * @return the lista rechazados
	 */
	public List<ChequeSimuladoDTO> getListaRechazados() {
		return listaRechazados;
	}

	/**
	 * Sets the lista rechazados.
	 *
	 * @param listaRechazados
	 *            the new lista rechazados
	 */
	public void setListaRechazados(List<ChequeSimuladoDTO> listaRechazados) {
		this.listaRechazados = listaRechazados;
	}

	/**
	 * Gets the comision adic.
	 *
	 * @return the comision adic
	 */
	public BigDecimal getComisionAdic() {
		return comisionAdic;
	}

	/**
	 * Sets the comision adic.
	 *
	 * @param comisionAdic
	 *            the new comision adic
	 */
	public void setComisionAdic(BigDecimal comisionAdic) {
		this.comisionAdic = comisionAdic;
	}

	/**
	 * Gets the tasa aplicada.
	 *
	 * @return the tasa aplicada
	 */
	public String getTasaAplicada() {
		return tasaAplicada;
	}

	/**
	 * Sets the tasa aplicada.
	 *
	 * @param tasaAplicada
	 *            the new tasa aplicada
	 */
	public void setTasaAplicada(String tasaAplicada) {
		this.tasaAplicada = tasaAplicada;
	}

	/**
	 * Gets the tasa efectiva anual.
	 *
	 * @return the tasa efectiva anual
	 */
	public String getTasaEfectivaAnual() {
		return tasaEfectivaAnual;
	}

	/**
	 * Sets the tasa efectiva anual.
	 *
	 * @param tasaEfectivaAnual
	 *            the new tasa efectiva anual
	 */
	public void setTasaEfectivaAnual(String tasaEfectivaAnual) {
		this.tasaEfectivaAnual = tasaEfectivaAnual;
	}

	/**
	 * Gets the costo financiero total.
	 *
	 * @return the costo financiero total
	 */
	public String getCostoFinancieroTotal() {
		return costoFinancieroTotal;
	}

	/**
	 * Sets the costo financiero total.
	 *
	 * @param costoFinancieroTotal
	 *            the new costo financiero total
	 */
	public void setCostoFinancieroTotal(String costoFinancieroTotal) {
		this.costoFinancieroTotal = costoFinancieroTotal;
	}

	/**
	 * Gets the legal 1.
	 *
	 * @return the legal 1
	 */
	public String getLegal1() {
		return legal1;
	}

	/**
	 * Sets the legal 1.
	 *
	 * @param legal1
	 *            the new legal 1
	 */
	public void setLegal1(String legal1) {
		this.legal1 = legal1;
	}

	/**
	 * Gets the legal 2.
	 *
	 * @return the legal 2
	 */
	public String getLegal2() {
		return legal2;
	}

	/**
	 * Sets the legal 2.
	 *
	 * @param legal2
	 *            the new legal 2
	 */
	public void setLegal2(String legal2) {
		this.legal2 = legal2;
	}

	/**
	 * Gets the legal 3.
	 *
	 * @return the legal 3
	 */
	public String getLegal3() {
		return legal3;
	}

	/**
	 * Sets the legal 3.
	 *
	 * @param legal3
	 *            the new legal 3
	 */
	public void setLegal3(String legal3) {
		this.legal3 = legal3;
	}

	/**
	 * Gets the mensaje feedback.
	 *
	 * @return the mensaje feedback
	 */
	public String getMensajeFeedback() {
		return mensajeFeedback;
	}

	/**
	 * Sets the mensaje feedback.
	 *
	 * @param mensajeFeedback
	 *            the new mensaje feedback
	 */
	public void setMensajeFeedback(String mensajeFeedback) {
		this.mensajeFeedback = mensajeFeedback;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public Date getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	/**
	 * Gets the legal 4.
	 *
	 * @return the legal 4
	 */
	public String getLegal4() {
		return legal4;
	}

	/**
	 * Sets the legal 4.
	 *
	 * @param legal4
	 *            the new legal 4
	 */
	public void setLegal4(String legal4) {
		this.legal4 = legal4;
	}

	public String getLegal5() {
		return legal5;
	}

	public void setLegal5(String legal5) {
		this.legal5 = legal5;
	}
	
}
