/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.web.view;

import java.text.SimpleDateFormat;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionPrecargadaDTO;

/**
 * The Class OperacionPrecargadaView.
 */
public class OperacionPrecargadaView {

	/** The fecha. */
	private String fecha;
	
	/** The numero operacion. */
	private String numeroOperacion;
	
	/** The cant cheques. */
	private String cantCheques;
	
	/** The cheques rechazados. */
	private String chequesRechazados;
	
	/** The importe bruto. */
	private String importeBruto;
	
	/** The importe acreditado. */
	private String importeAcreditado;
	
	/** The Constant FORMATO_FECHA_MOBILE. */
	public static final String FORMATO_FECHA_MOBILE = "dd/MM/yy";
	
	/**
	 * Instantiates a new operacion precargada view.
	 *
	 * @param operacion
	 *            the operacion
	 * @param isMobile
	 *            the is mobile
	 */
	public OperacionPrecargadaView(OperacionPrecargadaDTO operacion, Boolean isMobile) {
		if(isMobile){
			SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_MOBILE);
			fecha = sdf.format(operacion.getFecha());
		} else {
			fecha = ISBANStringUtils.formatearFecha(operacion.getFecha());
		}
		numeroOperacion = operacion.getNumeroOperacion();
		cantCheques = String.valueOf(operacion.getCantCheques());
		chequesRechazados = String.valueOf(operacion.getChequesRechazados());
		importeBruto = ISBANStringUtils.formatearSaldo(operacion.getImporteBruto());
		importeAcreditado = ISBANStringUtils.formatearSaldo(operacion.getImporteAcreditado());
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	 * Gets the cant cheques.
	 *
	 * @return the cant cheques
	 */
	public String getCantCheques() {
		return cantCheques;
	}

	/**
	 * Sets the cant cheques.
	 *
	 * @param cantCheques
	 *            the new cant cheques
	 */
	public void setCantCheques(String cantCheques) {
		this.cantCheques = cantCheques;
	}

	/**
	 * Gets the cheques rechazados.
	 *
	 * @return the cheques rechazados
	 */
	public String getChequesRechazados() {
		return chequesRechazados;
	}

	/**
	 * Sets the cheques rechazados.
	 *
	 * @param chequesRechazados
	 *            the new cheques rechazados
	 */
	public void setChequesRechazados(String chequesRechazados) {
		this.chequesRechazados = chequesRechazados;
	}

	/**
	 * Gets the importe bruto.
	 *
	 * @return the importe bruto
	 */
	public String getImporteBruto() {
		return importeBruto;
	}

	/**
	 * Sets the importe bruto.
	 *
	 * @param importeBruto
	 *            the new importe bruto
	 */
	public void setImporteBruto(String importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * Gets the importe acreditado.
	 *
	 * @return the importe acreditado
	 */
	public String getImporteAcreditado() {
		return importeAcreditado;
	}

	/**
	 * Sets the importe acreditado.
	 *
	 * @param importeAcreditado
	 *            the new importe acreditado
	 */
	public void setImporteAcreditado(String importeAcreditado) {
		this.importeAcreditado = importeAcreditado;
	}

}
