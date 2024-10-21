/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ConsultaOperacionesOutView.
 *
 * @author IT Resources
 */
public class ConsultaOperacionesOutView implements Cloneable{
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultaOperacionesOutView.class);

	/** The cantidad transferencias guardadas. */
	private Integer cantidadTransferenciasGuardadas;
	
	/** The cantidad transferencias pendientes. */
	private Integer cantidadTransferenciasPendientes;
	
	/** The cantidad transferencias rechazadas. */
	private Integer cantidadTransferenciasRechazadas;
	
	/** The operaciones. */
	private List<ConsultaOperacionesView> operaciones;
	
	/** The consulta monedas list. */
	private List<ConsultaMonedasView> consultaMonedasList;
		
    /** si hay mas operaciones para mostrar. */
    private Boolean hayMasOperaciones = Boolean.FALSE;
    
    /** The estados. */
    private List<EstadosTransferenciaView> estados;
    
    private String avisoLegal;
    
    private String avisoLegalPopup;


	/**
	 * Gets the cantidad transferencias guardadas.
	 *
	 * @return the cantidadTransferenciasGuardadas
	 */
	public Integer getCantidadTransferenciasGuardadas() {
		return cantidadTransferenciasGuardadas;
	}

	/**
	 * Sets the cantidad transferencias guardadas.
	 *
	 * @param cantidadTransferenciasGuardadas
	 *            the cantidadTransferenciasGuardadas to set
	 */
	public void setCantidadTransferenciasGuardadas(Integer cantidadTransferenciasGuardadas) {
		this.cantidadTransferenciasGuardadas = cantidadTransferenciasGuardadas;
	}

	/**
	 * Gets the cantidad transferencias pendientes.
	 *
	 * @return the cantidadTransferenciasPendientes
	 */
	public Integer getCantidadTransferenciasPendientes() {
		return cantidadTransferenciasPendientes;
	}

	/**
	 * Sets the cantidad transferencias pendientes.
	 *
	 * @param cantidadTransferenciasPendientes
	 *            the cantidadTransferenciasPendientes to set
	 */
	public void setCantidadTransferenciasPendientes(Integer cantidadTransferenciasPendientes) {
		this.cantidadTransferenciasPendientes = cantidadTransferenciasPendientes;
	}

	/**
	 * Gets the cantidad transferencias rechazadas.
	 *
	 * @return the cantidadTransferenciasRechazadas
	 */
	public Integer getCantidadTransferenciasRechazadas() {
		return cantidadTransferenciasRechazadas;
	}

	/**
	 * Sets the cantidad transferencias rechazadas.
	 *
	 * @param cantidadTransferenciasRechazadas
	 *            the cantidadTransferenciasRechazadas to set
	 */
	public void setCantidadTransferenciasRechazadas(Integer cantidadTransferenciasRechazadas) {
		this.cantidadTransferenciasRechazadas = cantidadTransferenciasRechazadas;
	}

	/**
	 * Gets the operaciones.
	 *
	 * @return the operaciones
	 */
	public List<ConsultaOperacionesView> getOperaciones() {
		return operaciones;
	}

	/**
	 * Sets the operaciones.
	 *
	 * @param operaciones
	 *            the operaciones to set
	 */
	public void setOperaciones(List<ConsultaOperacionesView> operaciones) {
		this.operaciones = operaciones;
	}

	/**
	 * Gets the hay mas operaciones.
	 *
	 * @return the hayMasOperaciones
	 */
	public Boolean getHayMasOperaciones() {
		return hayMasOperaciones;
	}

	/**
	 * Sets the hay mas operaciones.
	 *
	 * @param hayMasOperaciones
	 *            the hayMasOperaciones to set
	 */
	public void setHayMasOperaciones(Boolean hayMasOperaciones) {
		this.hayMasOperaciones = hayMasOperaciones;
	}

	/**
	 * Gets the consulta monedas list.
	 *
	 * @return the consultaMonedasList
	 */
	public List<ConsultaMonedasView> getConsultaMonedasList() {
		return consultaMonedasList;
	}

	/**
	 * Sets the consulta monedas list.
	 *
	 * @param consultaMonedasList
	 *            the consultaMonedasList to set
	 */
	public void setConsultaMonedasList(List<ConsultaMonedasView> consultaMonedasList) {
		this.consultaMonedasList = consultaMonedasList;
	}
	
	

	/**
	 * Gets the estados.
	 *
	 * @return the estados
	 */
	public List<EstadosTransferenciaView> getEstados() {
		return estados;
	}

	/**
	 * Sets the estados.
	 *
	 * @param estados
	 *            the estados to set
	 */
	public void setEstados(List<EstadosTransferenciaView> estados) {
		this.estados = estados;
	}
	
	public String getAvisoLegal() {
		return avisoLegal;
	}

	public void setAvisoLegal(String avisoLegal) {
		this.avisoLegal = avisoLegal;
	}
	
		public String getAvisoLegalPopup() {
		return avisoLegalPopup;
	}

	public void setAvisoLegalPopup(String avisoLegalPopup) {
		this.avisoLegalPopup = avisoLegalPopup;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public ConsultaOperacionesOutView clone() {
		try {
			return (ConsultaOperacionesOutView) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Error clonando Objeto", e);
			return null;
		}
	}
		
}
