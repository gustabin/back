/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.io.Serializable;

/**
 * The Class ConfirmacionSolicitudPlanV.
 *
 * @author Gabriel_Vigano
 */
public class ConfirmacionSolicitudPlanV implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The codigo respuesta. */
	private String codigoRespuesta;

	/** The costo financiero total. */
	private double costoFinancieroTotal;

	/** The importe cuotas. */
	private double importeCuotas;

	/** The moneda. */
	private String moneda;

	/** The monto cargos administrativos. */
	private double montoCargosAdministrativos;

	/** The monto seguro de vida. */
	private double montoSeguroDeVida;

	/** The monto solicitud. */
	private double montoSolicitud;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The numero tarjeta. */
	private String numeroTarjeta;

	/** The plan cuotas. */
	private int planCuotas;

	/** The tna. */
	private double tna;

	/** The total iva. */
	private double totalIVA;

	/**
	 * Gets the codigo respuesta.
	 *
	 * @return the codigoRespuesta
	 */
	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * Setter para codigo respuesta.
	 *
	 * @param codigoRespuesta
	 *            the codigoRespuesta to set
	 */
	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * Gets the costo financiero total.
	 *
	 * @return the costoFinancieroTotal
	 */
	public double getCostoFinancieroTotal() {
		return costoFinancieroTotal;
	}

	/**
	 * Setter para costo financiero total.
	 *
	 * @param costoFinancieroTotal
	 *            the costoFinancieroTotal to set
	 */
	public void setCostoFinancieroTotal(double costoFinancieroTotal) {
		this.costoFinancieroTotal = costoFinancieroTotal;
	}

	/**
	 * Gets the importe cuotas.
	 *
	 * @return the importeCuotas
	 */
	public double getImporteCuotas() {
		return importeCuotas;
	}

	/**
	 * Setter para importe cuotas.
	 *
	 * @param importeCuotas
	 *            the importeCuotas to set
	 */
	public void setImporteCuotas(double importeCuotas) {
		this.importeCuotas = importeCuotas;
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
	 * Setter para moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the monto cargos administrativos.
	 *
	 * @return the montoCargosAdministrativos
	 */
	public double getMontoCargosAdministrativos() {
		return montoCargosAdministrativos;
	}

	/**
	 * Setter para monto cargos administrativos.
	 *
	 * @param montoCargosAdministrativos
	 *            the montoCargosAdministrativos to set
	 */
	public void setMontoCargosAdministrativos(double montoCargosAdministrativos) {
		this.montoCargosAdministrativos = montoCargosAdministrativos;
	}

	/**
	 * Gets the monto seguro de vida.
	 *
	 * @return the montoSeguroDeVida
	 */
	public double getMontoSeguroDeVida() {
		return montoSeguroDeVida;
	}

	/**
	 * Setter para monto seguro de vida.
	 *
	 * @param montoSeguroDeVida
	 *            the montoSeguroDeVida to set
	 */
	public void setMontoSeguroDeVida(double montoSeguroDeVida) {
		this.montoSeguroDeVida = montoSeguroDeVida;
	}

	/**
	 * Gets the monto solicitud.
	 *
	 * @return the montoSolicitud
	 */
	public double getMontoSolicitud() {
		return montoSolicitud;
	}

	/**
	 * Setter para monto solicitud.
	 *
	 * @param montoSolicitud
	 *            the montoSolicitud to set
	 */
	public void setMontoSolicitud(double montoSolicitud) {
		this.montoSolicitud = montoSolicitud;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Setter para numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
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
	 * Setter para numero tarjeta.
	 *
	 * @param numeroTarjeta
	 *            the numeroTarjeta to set
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * Gets the plan cuotas.
	 *
	 * @return the planCuotas
	 */
	public int getPlanCuotas() {
		return planCuotas;
	}

	/**
	 * Setter para plan cuotas.
	 *
	 * @param planCuotas
	 *            the planCuotas to set
	 */
	public void setPlanCuotas(int planCuotas) {
		this.planCuotas = planCuotas;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public double getTna() {
		return tna;
	}

	/**
	 * Setter para tna.
	 *
	 * @param tna
	 *            the tna to set
	 */
	public void setTna(double tna) {
		this.tna = tna;
	}

	/**
	 * Gets the total iva.
	 *
	 * @return the totalIVA
	 */
	public double getTotalIVA() {
		return totalIVA;
	}

	/**
	 * Setter para total iva.
	 *
	 * @param totalIVA
	 *            the totalIVA to set
	 */
	public void setTotalIVA(double totalIVA) {
		this.totalIVA = totalIVA;
	}

}
