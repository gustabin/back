/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

// TODO: Auto-generated Javadoc
/**
 * The Class PrestamosView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrestamosView {

	/** The prestamos. */
	private List<PrestamoView> prestamos;

	/** The prestamos open credit OLYMPUS. */
	private PrestamosOpenCreditView prestamosOpenCredit;

	/** The sin prestamos prendarios url. */
	private String sinPrestamosPrendariosUrl;

	/** The sin prestamos hipotecarios url. */
	private String sinPrestamosHipotecariosUrl;
	
	/** The legal prestamos personales. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String legalPrestamosPersonales;
	
	/** The legal stop debit prestamos. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String legalStopDebitPrestamos;
	
	/** Mensaje contextual prestamos preaprobados. */
	@JsonSerialize(include = Inclusion.NON_NULL)
	private String mensajeContextual;
	
	/*private PrestamoPreaprobadoMonoproductoView prestamoPreaprobadoMonoproducto;*/

	/**
	 * Gets the prestamos.
	 *
	 * @return the prestamos
	 */
	public List<PrestamoView> getPrestamos() {
		return prestamos;
	}

	/**
	 * Sets the prestamos.
	 *
	 * @param prestamos
	 *            the new prestamos
	 */
	public void setPrestamos(List<PrestamoView> prestamos) {
		this.prestamos = prestamos;
	}

	/**
	 * Adds the prestamos.
	 *
	 * @param prestamo
	 *            the prestamo
	 */
	public void addPrestamos(PrestamoView prestamo) {
		if (CollectionUtils.isEmpty(this.prestamos)) {
			this.prestamos = new ArrayList<PrestamoView>();
		}
		this.prestamos.add(prestamo);
	}

	/**
	 * Gets the prestamos open credit.
	 *
	 * @return the prestamos open credit
	 */
	public PrestamosOpenCreditView getPrestamosOpenCredit() {
		return prestamosOpenCredit;
	}

	/**
	 * Sets the prestamos open credit.
	 *
	 * @param prestamosOpenCredit
	 *            the new prestamos open credit
	 */
	public void setPrestamosOpenCredit(PrestamosOpenCreditView prestamosOpenCredit) {
		this.prestamosOpenCredit = prestamosOpenCredit;
	}

	/**
	 * Gets the sin prestamos prendarios url.
	 *
	 * @return the sin prestamos prendarios url
	 */
	public String getSinPrestamosPrendariosUrl() {
		return sinPrestamosPrendariosUrl;
	}

	/**
	 * Sets the sin prestamos prendarios url.
	 *
	 * @param sinPrestamosPrendariosUrl
	 *            the new sin prestamos prendarios url
	 */
	public void setSinPrestamosPrendariosUrl(String sinPrestamosPrendariosUrl) {
		this.sinPrestamosPrendariosUrl = sinPrestamosPrendariosUrl;
	}

	/**
	 * Gets the sin prestamos hipotecarios url.
	 *
	 * @return the sin prestamos hipotecarios url
	 */
	public String getSinPrestamosHipotecariosUrl() {
		return sinPrestamosHipotecariosUrl;
	}

	/**
	 * Sets the sin prestamos hipotecarios url.
	 *
	 * @param sinPrestamosHipotecariosUrl
	 *            the new sin prestamos hipotecarios url
	 */
	public void setSinPrestamosHipotecariosUrl(String sinPrestamosHipotecariosUrl) {
		this.sinPrestamosHipotecariosUrl = sinPrestamosHipotecariosUrl;
	}

	/**
	 * Gets the legal prestamos personales.
	 *
	 * @return the legal prestamos personales
	 */
	public String getLegalPrestamosPersonales() {
		return legalPrestamosPersonales;
	}

	/**
	 * Sets the legal prestamos personales.
	 *
	 * @param legalPrestamosPersonales the new legal prestamos personales
	 */
	public void setLegalPrestamosPersonales(String legalPrestamosPersonales) {
		this.legalPrestamosPersonales = legalPrestamosPersonales;
	}

	/**
	 * Gets the legal stop debit prestamos.
	 *
	 * @return the legal stop debit prestamos
	 */
	public String getLegalStopDebitPrestamos() {
		return legalStopDebitPrestamos;
	}

	/**
	 * Sets the legal stop debit prestamos.
	 *
	 * @param legalStopDebitPrestamos the new legal stop debit prestamos
	 */
	public void setLegalStopDebitPrestamos(String legalStopDebitPrestamos) {
		this.legalStopDebitPrestamos = legalStopDebitPrestamos;
	}

	public String getMensajeContextual() {
		return mensajeContextual;
	}

	public void setMensajeContextual(String mensajeContextual) {
		this.mensajeContextual = mensajeContextual;
	}

	/*public PrestamoPreaprobadoMonoproductoView getPrestamoPreaprobadoMonoproducto() {
		return prestamoPreaprobadoMonoproducto;
	}

	public void setPrestamoPreaprobadoMonoproducto(PrestamoPreaprobadoMonoproductoView prestamoPreaprobadoMonoproducto) {
		this.prestamoPreaprobadoMonoproducto = prestamoPreaprobadoMonoproducto;
	}*/		
	
}
