/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DetalleLoteDatos.
 */
public class DetalleLoteDatos {

	/** The ceros. */
	private String ceros;

	/** The cantidad ocurrencias. */
	private String cantidadOcurrencias;

	/** The detalle datos. */
	private List<DetalleRecepcionDatos> detalleDatos = new ArrayList<DetalleRecepcionDatos>();

	/** The texto tabla mensajes. */
	private String textoTablaMensajes;

	/** The id sistema. */
	private String idSistema;

	/** The cant desc status resultado. */
	private String cantDescStatusResultado;

	/** The descripciones status resultado. */
	private String descripcionesStatusResultado;

	/**
	 * Instantiates a new detalle lote datos.
	 */
	public DetalleLoteDatos() {
		for (int i = 0; i < 10; i++) {
			detalleDatos.add(new DetalleRecepcionDatos());
		}
	}

	/**
	 * Gets the ceros.
	 *
	 * @return the ceros
	 */
	public String getCeros() {
		return ceros;
	}

	/**
	 * Sets the ceros.
	 *
	 * @param ceros
	 *            the new ceros
	 */
	public void setCeros(String ceros) {
		this.ceros = ceros;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidad ocurrencias
	 */
	public String getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the new cantidad ocurrencias
	 */
	public void setCantidadOcurrencias(String cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the detalle datos.
	 *
	 * @return the detalle datos
	 */
	public List<DetalleRecepcionDatos> getDetalleDatos() {
		return detalleDatos;
	}

	/**
	 * Sets the detalle datos.
	 *
	 * @param detalleDatos
	 *            the new detalle datos
	 */
	public void setDetalleDatos(List<DetalleRecepcionDatos> detalleDatos) {
		this.detalleDatos = detalleDatos;
	}

	/**
	 * Gets the id sistema.
	 *
	 * @return the id sistema
	 */
	public String getIdSistema() {
		return idSistema;
	}

	/**
	 * Sets the id sistema.
	 *
	 * @param idSistema
	 *            the new id sistema
	 */
	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	/**
	 * Gets the cant desc status resultado.
	 *
	 * @return the cant desc status resultado
	 */
	public String getCantDescStatusResultado() {
		return cantDescStatusResultado;
	}

	/**
	 * Sets the cant desc status resultado.
	 *
	 * @param cantDescStatusResultado
	 *            the new cant desc status resultado
	 */
	public void setCantDescStatusResultado(String cantDescStatusResultado) {
		this.cantDescStatusResultado = cantDescStatusResultado;
	}

	/**
	 * Gets the descripciones status resultado.
	 *
	 * @return the descripciones status resultado
	 */
	public String getDescripcionesStatusResultado() {
		return descripcionesStatusResultado;
	}

	/**
	 * Sets the descripciones status resultado.
	 *
	 * @param descripcionesStatusResultado
	 *            the new descripciones status resultado
	 */
	public void setDescripcionesStatusResultado(String descripcionesStatusResultado) {
		this.descripcionesStatusResultado = descripcionesStatusResultado;
	}

	/**
	 * Gets the texto tabla mensajes.
	 *
	 * @return the texto tabla mensajes
	 */
	public String getTextoTablaMensajes() {
		return textoTablaMensajes;
	}

	/**
	 * Sets the texto tabla mensajes.
	 *
	 * @param string
	 *            the new texto tabla mensajes
	 */
	public void setTextoTablaMensajes(String string) {
		this.textoTablaMensajes = string;
	}

}
