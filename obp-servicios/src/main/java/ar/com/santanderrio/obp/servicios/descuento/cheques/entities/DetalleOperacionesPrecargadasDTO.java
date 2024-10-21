/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Class DetalleOperacionesPrecargadasDTO.
 */
@JsonIgnoreProperties
public class DetalleOperacionesPrecargadasDTO implements Serializable{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -386579512426998090L;

	/** The importe acreditar. */
	private BigDecimal importeAcreditar;
	
	/** The cuenta. */
	private IdentificacionCuenta cuenta;
	
	/** The numero operacion. */
	private String numeroOperacion;
	
	/** The total cheques descontados. */
	private int totalChequesDescontados;
	
	/** The total. */
	private BigDecimal total;
	
	/** The impuesto. */
	private BigDecimal impuesto;
	
	/** The intereses. */
	private BigDecimal intereses;
	
	/** The a acreditar. */
	private BigDecimal aAcreditar;
	
	/** The cheques aceptados. */
	private List<ChequesAceptadosDTO> chequesAceptados = new ArrayList<ChequesAceptadosDTO>();
	
	/** The importe. */
	private BigDecimal importe;
	
	/** The total cheques rechazados. */
	private int totalChequesRechazados;
	
	/** The cheques rechazados. */
	private List<ChequesRechazadosDTO> chequesRechazados = new ArrayList<ChequesRechazadosDTO>();

	/** The com admin cheques. */
	private BigDecimal comAdminCheques;
	
	/** The tasa nominal anual. */
	private String tasaNominalAnual;
	
	/** The tasa efectiva anual. */
	private String tasaEfectivaAnual;
	
	/** The costo financiero total. */
	private String costoFinancieroTotal;
	
	/** The mensaje informativo. */
	private String mensajeInformativo;
	
	/** The legal pie 1. */
	private String legalPie1;
	
	/** The legal pie 2. */
	private String legalPie2;
	
	/** The legal pie 3. */
	private String legalPie3;
	
	/** The fecha alta. */
	private String fechaAlta;
	
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
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public IdentificacionCuenta getCuenta() {
		return cuenta;
	}

	/**
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the new cuenta
	 */
	public void setCuenta(IdentificacionCuenta cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the numero operacion.
	 *
	 * @return the numero operacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * Sets the numero operacion.
	 *
	 * @param numeroOperacion
	 *            the new numero operacion
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * Gets the total cheques descontados.
	 *
	 * @return the total cheques descontados
	 */
	public int getTotalChequesDescontados() {
		return totalChequesDescontados;
	}

	/**
	 * Sets the total cheques descontados.
	 *
	 * @param totalChequesDescontados
	 *            the new total cheques descontados
	 */
	public void setTotalChequesDescontados(int totalChequesDescontados) {
		this.totalChequesDescontados = totalChequesDescontados;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the new total
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * Gets the impuesto.
	 *
	 * @return the impuesto
	 */
	public BigDecimal getImpuesto() {
		return impuesto;
	}

	/**
	 * Sets the impuesto.
	 *
	 * @param impuesto
	 *            the new impuesto
	 */
	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}

	/**
	 * Gets the intereses.
	 *
	 * @return the intereses
	 */
	public BigDecimal getIntereses() {
		return intereses;
	}

	/**
	 * Sets the intereses.
	 *
	 * @param intereses
	 *            the new intereses
	 */
	public void setIntereses(BigDecimal intereses) {
		this.intereses = intereses;
	}

	/**
	 * Gets the a acreditar.
	 *
	 * @return the a acreditar
	 */
	public BigDecimal getaAcreditar() {
		return aAcreditar;
	}

	/**
	 * Sets the a acreditar.
	 *
	 * @param aAcreditar
	 *            the new a acreditar
	 */
	public void setaAcreditar(BigDecimal aAcreditar) {
		this.aAcreditar = aAcreditar;
	}

	/**
	 * Gets the cheques aceptados.
	 *
	 * @return the cheques aceptados
	 */
	public List<ChequesAceptadosDTO> getChequesAceptados() {
		return chequesAceptados;
	}

	/**
	 * Sets the cheques aceptados.
	 *
	 * @param chequesAceptados
	 *            the new cheques aceptados
	 */
	public void setChequesAceptados(List<ChequesAceptadosDTO> chequesAceptados) {
		this.chequesAceptados = chequesAceptados;
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
	 * Gets the total cheques rechazados.
	 *
	 * @return the total cheques rechazados
	 */
	public int getTotalChequesRechazados() {
		return totalChequesRechazados;
	}

	/**
	 * Sets the total cheques rechazados.
	 *
	 * @param totalChequesRechazados
	 *            the new total cheques rechazados
	 */
	public void setTotalChequesRechazados(int totalChequesRechazados) {
		this.totalChequesRechazados = totalChequesRechazados;
	}

	/**
	 * Gets the cheques rechazados.
	 *
	 * @return the cheques rechazados
	 */
	public List<ChequesRechazadosDTO> getChequesRechazados() {
		return chequesRechazados;
	}

	/**
	 * Sets the cheques rechazados.
	 *
	 * @param chequesRechazados
	 *            the new cheques rechazados
	 */
	public void setChequesRechazados(List<ChequesRechazadosDTO> chequesRechazados) {
		this.chequesRechazados = chequesRechazados;
	}

	/**
	 * Gets the com admin cheques.
	 *
	 * @return the com admin cheques
	 */
	public BigDecimal getComAdminCheques() {
		return comAdminCheques;
	}

	/**
	 * Sets the com admin cheques.
	 *
	 * @param comAdminCheques
	 *            the new com admin cheques
	 */
	public void setComAdminCheques(BigDecimal comAdminCheques) {
		this.comAdminCheques = comAdminCheques;
	}

	/**
	 * Gets the tasa nominal anual.
	 *
	 * @return the tasa nominal anual
	 */
	public String getTasaNominalAnual() {
		return tasaNominalAnual;
	}

	/**
	 * Sets the tasa nominal anual.
	 *
	 * @param tasaNominalAnual
	 *            the new tasa nominal anual
	 */
	public void setTasaNominalAnual(String tasaNominalAnual) {
		this.tasaNominalAnual = tasaNominalAnual;
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
	 * Gets the mensaje informativo.
	 *
	 * @return the mensaje informativo
	 */
	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	/**
	 * Sets the mensaje informativo.
	 *
	 * @param mensajeInformativo
	 *            the new mensaje informativo
	 */
	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}

	/**
	 * Gets the legal pie 1.
	 *
	 * @return the legal pie 1
	 */
	public String getLegalPie1() {
		return legalPie1;
	}

	/**
	 * Sets the legal pie 1.
	 *
	 * @param legalPie1
	 *            the new legal pie 1
	 */
	public void setLegalPie1(String legalPie1) {
		this.legalPie1 = legalPie1;
	}

	/**
	 * Gets the legal pie 2.
	 *
	 * @return the legal pie 2
	 */
	public String getLegalPie2() {
		return legalPie2;
	}

	/**
	 * Sets the legal pie 2.
	 *
	 * @param legalPie2
	 *            the new legal pie 2
	 */
	public void setLegalPie2(String legalPie2) {
		this.legalPie2 = legalPie2;
	}

	/**
	 * Gets the legal pie 3.
	 *
	 * @return the legal pie 3
	 */
	public String getLegalPie3() {
		return legalPie3;
	}

	/**
	 * Sets the legal pie 3.
	 *
	 * @param legalPie3
	 *            the new legal pie 3
	 */
	public void setLegalPie3(String legalPie3) {
		this.legalPie3 = legalPie3;
	}

	/**
	 * Gets the fecha alta.
	 *
	 * @return the fecha alta
	 */
	public String getFechaAlta() {
		return fechaAlta;
	}

	/**
	 * Sets the fecha alta.
	 *
	 * @param fechaAlta
	 *            the new fecha alta
	 */
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
		
}
