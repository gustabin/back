/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaOperaciones;

/**
 * The Class ConsultarOrdenesViewRequest.
 */
public class ConsultarOrdenesViewRequest {
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaOperaciones datos;
	
	
	/** The cuentas titulo. */
	private List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
	
	/** The fecha desde. */
	private String fechaDesde;
	
	/** The fecha hasta. */
	private String fechaHasta;
	
	/** The cantidad nominales. */
	private String cantidadNominalDesde;
	
	/** The cantidad nominal hasta. */
	private String cantidadNominalHasta;
	
	/** The especie. */
	private String especie;
	

	/**
	 * Gets the datos.
	 *
	 * @return the datos
	 */
	public DatosConsultaOperaciones getDatos() {
		return datos;
	}


	/**
	 * Sets the datos.
	 *
	 * @param datos
	 *            the new datos
	 */
	public void setDatos(DatosConsultaOperaciones datos) {
		this.datos = datos;
	}


	/**
	 * Gets the cuentas titulo.
	 *
	 * @return the cuentas titulo
	 */
	public List<CuentaTituloView> getCuentasTitulo() {
		return cuentasTitulo;
	}


	/**
	 * Sets the cuentas titulo.
	 *
	 * @param cuentasTitulo
	 *            the new cuentas titulo
	 */
	public void setCuentasTitulo(List<CuentaTituloView> cuentasTitulo) {
		this.cuentasTitulo = cuentasTitulo;
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
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
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
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}



	/**
	 * Gets the cantidad nominal desde.
	 *
	 * @return the cantidad nominal desde
	 */
	public String getCantidadNominalDesde() {
		return cantidadNominalDesde;
	}


	/**
	 * Sets the cantidad nominal desde.
	 *
	 * @param cantidadNominalDesde
	 *            the new cantidad nominal desde
	 */
	public void setCantidadNominalDesde(String cantidadNominalDesde) {
		this.cantidadNominalDesde = cantidadNominalDesde;
	}


	/**
	 * Gets the cantidad nominal hasta.
	 *
	 * @return the cantidad nominal hasta
	 */
	public String getCantidadNominalHasta() {
		return cantidadNominalHasta;
	}


	/**
	 * Sets the cantidad nominal hasta.
	 *
	 * @param cantidadNominalHasta
	 *            the new cantidad nominal hasta
	 */
	public void setCantidadNominalHasta(String cantidadNominalHasta) {
		this.cantidadNominalHasta = cantidadNominalHasta;
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
	

}
