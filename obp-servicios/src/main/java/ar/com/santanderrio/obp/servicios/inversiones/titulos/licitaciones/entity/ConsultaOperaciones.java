/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;

/**
 * The Class ConsultaOperaciones.
 */
public class ConsultaOperaciones {
	
	/** The datos. */
	@JsonProperty("Datos")
	private DatosConsultaOperaciones datos;
	
	
	/** The cuentas titulo. */
	private List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
		

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

	
	
}
