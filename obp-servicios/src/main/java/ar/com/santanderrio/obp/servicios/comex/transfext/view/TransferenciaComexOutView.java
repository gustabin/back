/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class TransferenciaComexOutView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class TransferenciaComexOutView {

	/** The motivosComex. */
    private List<MotivoComexView> motivosComex;
    
	/** The motivosComex. */
    private List<MotivoComexView> motivosAyuda;
    
    /** The datosPersonales. */
    private DatosPersonalesView datosPersonales;
    
    /** The datosBeneficiario. */
    private DatosBeneficiarioOutView datosBeneficiario;
    
    /** The datosTransferencia. */
    private DatosTransferenciaOutView datosTransferencia;
    
    /** The legales. */
    private String legales;
    
	/** The consulta monedas list. */
	private List<ConsultaMonedasView> consultaMonedasList;

	/**
	 * Gets the motivos comex.
	 *
	 * @return the motivosComex
	 */
	public List<MotivoComexView> getMotivosComex() {
		return motivosComex;
	}

	/**
	 * Sets the motivos comex.
	 *
	 * @param motivosComex
	 *            the motivosComex to set
	 */
	public void setMotivosComex(List<MotivoComexView> motivosComex) {
		this.motivosComex = motivosComex;
	}

	/**
	 * Gets the motivos ayuda.
	 *
	 * @return the motivosAyuda
	 */
	public List<MotivoComexView> getMotivosAyuda() {
		return motivosAyuda;
	}

	/**
	 * Sets the motivos ayuda.
	 *
	 * @param motivosAyuda
	 *            the motivosAyuda to set
	 */
	public void setMotivosAyuda(List<MotivoComexView> motivosAyuda) {
		this.motivosAyuda = motivosAyuda;
	}

	/**
	 * Gets the datos personales.
	 *
	 * @return the datosPersonales
	 */
	public DatosPersonalesView getDatosPersonales() {
		return datosPersonales;
	}

	/**
	 * Sets the datos personales.
	 *
	 * @param datosPersonales
	 *            the datosPersonales to set
	 */
	public void setDatosPersonales(DatosPersonalesView datosPersonales) {
		this.datosPersonales = datosPersonales;
	}

	/**
	 * Gets the datos beneficiario.
	 *
	 * @return the datosBeneficiario
	 */
	public DatosBeneficiarioOutView getDatosBeneficiario() {
		return datosBeneficiario;
	}

	/**
	 * Sets the datos beneficiario.
	 *
	 * @param datosBeneficiario
	 *            the datosBeneficiario to set
	 */
	public void setDatosBeneficiario(DatosBeneficiarioOutView datosBeneficiario) {
		this.datosBeneficiario = datosBeneficiario;
	}

	/**
	 * Gets the datos transferencia.
	 *
	 * @return the datosTransferencia
	 */
	public DatosTransferenciaOutView getDatosTransferencia() {
		return datosTransferencia;
	}

	/**
	 * Sets the datos transferencia.
	 *
	 * @param datosTransferencia
	 *            the datosTransferencia to set
	 */
	public void setDatosTransferencia(DatosTransferenciaOutView datosTransferencia) {
		this.datosTransferencia = datosTransferencia;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * @return the consultaMonedasList
	 */
	public List<ConsultaMonedasView> getConsultaMonedasList() {
		return consultaMonedasList;
	}

	/**
	 * @param consultaMonedasList the consultaMonedasList to set
	 */
	public void setConsultaMonedasList(List<ConsultaMonedasView> consultaMonedasList) {
		this.consultaMonedasList = consultaMonedasList;
	}	
	
}