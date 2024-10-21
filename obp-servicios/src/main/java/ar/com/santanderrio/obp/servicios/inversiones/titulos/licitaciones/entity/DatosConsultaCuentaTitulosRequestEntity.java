/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaCuentaTitulosRequestEntity.
 */
public class DatosConsultaCuentaTitulosRequestEntity extends EntityBaseTitulos{
	
	/** The canal tipo. */
	@JsonProperty("ListaCuentasTitulos")
	private List<String> listaCuentasTitulos = new ArrayList<String>();
	
	/** The canal tipo. */
	@JsonProperty("SegmentoCliente")
	private String segmento;
	
	/** The canal tipo. */
	@JsonProperty("TipoOperacion")
	private String tipoOperacion;

	/**
	 * Gets the lista cuentas titulos.
	 *
	 * @return the listaCuentasTitulos
	 */
	public List<String> getListaCuentasTitulos() {
		return listaCuentasTitulos;
	}

	/**
	 * Sets the lista cuentas titulos.
	 *
	 * @param listaCuentasTitulos
	 *            the listaCuentasTitulos to set
	 */
	public void setListaCuentasTitulos(List<String> listaCuentasTitulos) {
		this.listaCuentasTitulos = listaCuentasTitulos;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the tipo operacion.
	 *
	 * @return the tipoOperacion
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Sets the tipo operacion.
	 *
	 * @param tipoOperacion
	 *            the tipoOperacion to set
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}	
}
